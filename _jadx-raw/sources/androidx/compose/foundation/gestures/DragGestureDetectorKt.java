package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: DragGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a5\u0010\r\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u000fH\u0082Hø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u0013\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\f\u001aa\u0010\u0015\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u001726\u0010\u0018\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001aY\u0010!\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a!\u0010%\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b&\u0010\f\u001aM\u0010'\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010)2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u0019H\u0082Hø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001aY\u0010-\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@ø\u0001\u0000¢\u0006\u0004\b.\u0010$\u001a!\u0010/\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b0\u0010\f\u001aa\u00101\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u001726\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0080@ø\u0001\u0000¢\u0006\u0004\b2\u0010 \u001aY\u00103\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n26\u0010\"\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@ø\u0001\u0000¢\u0006\u0004\b4\u0010$\u001a\u0080\u0001\u00105\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010;\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001aà\u0001\u00105\u001a\u00020\u001e*\u0002062K\u00107\u001aG\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(?\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(A\u0012\u0004\u0012\u00020\u001e0>2!\u00108\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001e0\u000f2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e092\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u0010092\b\u0010C\u001a\u0004\u0018\u00010)26\u0010;\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0080@¢\u0006\u0002\u0010D\u001a\u0080\u0001\u0010E\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010;\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110*¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001a\u0080\u0001\u0010F\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010G\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001a\u0080\u0001\u0010H\u001a\u00020\u001e*\u0002062\u0014\b\u0002\u00107\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u001e0\u000f2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e092\u000e\b\u0002\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0926\u0010I\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u001e0\u0019H\u0086@¢\u0006\u0002\u0010=\u001a3\u0010J\u001a\u00020\u0010*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\bK\u0010\u0012\u001aS\u0010J\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000f2\b\u0010(\u001a\u0004\u0018\u00010)2\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u000fH\u0080Hø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a3\u0010O\u001a\u00020\u0010*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\bP\u0010\u0012\u001a\u001e\u0010Q\u001a\u00020\u0010*\u00020R2\u0006\u0010\t\u001a\u00020\nH\u0002ø\u0001\u0000¢\u0006\u0004\bS\u0010T\u001a\u001e\u0010U\u001a\u00020\u0005*\u00020V2\u0006\u0010\u0016\u001a\u00020\u0017H\u0000ø\u0001\u0000¢\u0006\u0004\bW\u0010X\u001a3\u0010Y\u001a\u00020\u0010*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001e0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\bZ\u0010\u0012\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006["}, d2 = {"defaultTouchSlop", "Landroidx/compose/ui/unit/Dp;", "F", "mouseSlop", "mouseToTouchSlopRatio", "", "awaitDragOrCancellation", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "awaitDragOrCancellation-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrUp", "hasDragged", "Lkotlin/Function1;", "", "awaitDragOrUp-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitHorizontalDragOrCancellation", "awaitHorizontalDragOrCancellation-rnUCldI", "awaitHorizontalPointerSlopOrCancellation", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "onPointerSlopReached", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "change", "overSlop", "", "awaitHorizontalPointerSlopOrCancellation-gDDlDlE", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitHorizontalTouchSlopOrCancellation", "onTouchSlopReached", "awaitHorizontalTouchSlopOrCancellation-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitLongPressOrCancellation", "awaitLongPressOrCancellation-rnUCldI", "awaitPointerSlopOrCancellation", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "Landroidx/compose/ui/geometry/Offset;", "awaitPointerSlopOrCancellation-pn7EDYM", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILandroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitTouchSlopOrCancellation", "awaitTouchSlopOrCancellation-jO51t88", "awaitVerticalDragOrCancellation", "awaitVerticalDragOrCancellation-rnUCldI", "awaitVerticalPointerSlopOrCancellation", "awaitVerticalPointerSlopOrCancellation-gDDlDlE", "awaitVerticalTouchSlopOrCancellation", "awaitVerticalTouchSlopOrCancellation-jO51t88", "detectDragGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDragStart", "onDragEnd", "Lkotlin/Function0;", "onDragCancel", "onDrag", "dragAmount", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function3;", "down", "slopTriggerChange", "overSlopOffset", "shouldAwaitTouchSlop", "orientationLock", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGesturesAfterLongPress", "detectHorizontalDragGestures", "onHorizontalDrag", "detectVerticalDragGestures", "onVerticalDrag", "drag", "drag-jO51t88", "motionConsumed", "drag-VnAYq1g", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "horizontalDrag", "horizontalDrag-jO51t88", "isPointerUp", "Landroidx/compose/ui/input/pointer/PointerEvent;", "isPointerUp-DmW0f2w", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "pointerSlop", "Landroidx/compose/ui/platform/ViewConfiguration;", "pointerSlop-E8SPZFQ", "(Landroidx/compose/ui/platform/ViewConfiguration;I)F", "verticalDrag", "verticalDrag-jO51t88", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DragGestureDetectorKt {
    private static final float mouseToTouchSlopRatio;
    private static final float mouseSlop = Dp.m7016constructorimpl((float) 0.125d);
    private static final float defaultTouchSlop = Dp.m7016constructorimpl(18);

    /* JADX WARN: Code duplicated, block: B:24:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f8 A[LOOP:0: B:23:0x00d5->B:27:0x00f8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x010c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0199 -> B:59:0x01a0). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m769awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m769awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:17:0x0050  */
    /* JADX WARN: Code duplicated, block: B:22:0x005d  */
    /* JADX WARN: Code duplicated, block: B:24:0x0064  */
    /* JADX WARN: Code duplicated, block: B:26:0x0069  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0050 -> B:18:0x0053). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: drag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m775dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, long r8, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r10, kotlin.coroutines.Continuation<? super java.lang.Boolean> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            r0.<init>(r11)
        L19:
            r11 = r0
            java.lang.Object r0 = r11.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            r3 = 1
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L2f;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2f:
            java.lang.Object r7 = r11.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r11.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r8 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r8
            kotlin.ResultKt.throwOnFailure(r0)
            r9 = r0
            goto L53
        L3c:
            kotlin.ResultKt.throwOnFailure(r0)
            r4 = r8
            r8 = r7
            r7 = r10
        L42:
            r11.L$0 = r8
            r11.L$1 = r7
            r11.label = r3
            java.lang.Object r9 = m762awaitDragOrCancellationrnUCldI(r8, r4, r11)
            if (r9 != r1) goto L50
            return r1
        L50:
            r6 = r0
            r0 = r9
            r9 = r6
        L53:
            androidx.compose.ui.input.pointer.PointerInputChange r0 = (androidx.compose.ui.input.pointer.PointerInputChange) r0
            if (r0 != 0) goto L5d
            r10 = 0
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)
            return r10
        L5d:
            r10 = r0
            boolean r0 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r10)
            if (r0 == 0) goto L69
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r0
        L69:
            r7.invoke(r10)
            long r4 = r10.getId()
            r0 = r9
            goto L42
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m775dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a9 A[LOOP:0: B:22:0x0089->B:26:0x00a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:58:0x00b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0070 -> B:21:0x0079). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m762awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, long r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m762awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object detectDragGestures(PointerInputScope $this$detectDragGestures, final Function1<? super Offset, Unit> function1, final Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super Unit> continuation) {
        Object objDetectDragGestures = detectDragGestures($this$detectDragGestures, new Function3<PointerInputChange, PointerInputChange, Offset, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
                m781invoke0AR0LA0(pointerInputChange, pointerInputChange2, offset.getPackedValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-0AR0LA0, reason: not valid java name */
            public final void m781invoke0AR0LA0(PointerInputChange pointerInputChange, PointerInputChange slopTriggerChange, long j) {
                function1.invoke(Offset.m4282boximpl(slopTriggerChange.getPosition()));
            }
        }, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                invoke2(pointerInputChange);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PointerInputChange it) {
                function0.invoke();
            }
        }, function2, new Function0<Boolean>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return true;
            }
        }, null, function3, continuation);
        return objDetectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGestures : Unit.INSTANCE;
    }

    public static final Object detectDragGestures(PointerInputScope $this$detectDragGestures, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function2, Orientation orientationLock, Function2<? super PointerInputChange, ? super Offset, Unit> function4, Continuation<? super Unit> continuation) {
        Ref.LongRef overSlop = new Ref.LongRef();
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectDragGestures, new AnonymousClass9(function2, overSlop, orientationLock, function3, function4, function0, function1, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$9, reason: invalid class name */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$9", f = "DragGestureDetector.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4}, l = {247, 253, 972, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, InputDeviceCompat.SOURCE_GAMEPAD}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "initialDown", "awaitTouchSlop", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2dpn7EDYM$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2dpn7EDYM$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$drag_u2dVnAYq1g$iv", "orientation$iv", "$this$awaitDragOrUp_u2djO51t88$iv$iv", "pointer$iv$iv"}, s = {"L$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$2", "L$3", "L$4"})
    static final class AnonymousClass9 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
        final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
        final /* synthetic */ Orientation $orientationLock;
        final /* synthetic */ Ref.LongRef $overSlop;
        final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
        float F$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass9(Function0<Boolean> function0, Ref.LongRef longRef, Orientation orientation, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Function0<Unit> function1, Function1<? super PointerInputChange, Unit> function4, Continuation<? super AnonymousClass9> continuation) {
            super(2, continuation);
            this.$shouldAwaitTouchSlop = function0;
            this.$overSlop = longRef;
            this.$orientationLock = orientation;
            this.$onDragStart = function3;
            this.$onDrag = function2;
            this.$onDragCancel = function1;
            this.$onDragEnd = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass9 anonymousClass9 = new AnonymousClass9(this.$shouldAwaitTouchSlop, this.$overSlop, this.$orientationLock, this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, continuation);
            anonymousClass9.L$0 = obj;
            return anonymousClass9;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass9) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:147:0x01b1 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:34:0x017b  */
        /* JADX WARN: Code duplicated, block: B:37:0x019c A[LOOP:2: B:33:0x0179->B:37:0x019c, LOOP_END] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x012c -> B:73:0x0276). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x01b5 -> B:73:0x0276). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01c5 -> B:73:0x0276). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0201 -> B:73:0x0276). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x022c -> B:73:0x0276). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x0265 -> B:69:0x026c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:89:0x02fa -> B:90:0x02ff). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 1062
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.AnonymousClass9.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5", f = "DragGestureDetector.kt", i = {0, 1, 2}, l = {323, 324, 329}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0", "L$0"})
    static final class C03165 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03165(Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super C03165> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onDragEnd = function0;
            this.$onDragCancel = function2;
            this.$onDrag = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03165 c03165 = new C03165(this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$onDrag, continuation);
            c03165.L$0 = obj;
            return c03165;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C03165) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:24:0x006f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:25:0x0070  */
        /* JADX WARN: Code duplicated, block: B:28:0x0075 A[Catch: CancellationException -> 0x00e7, TRY_LEAVE, TryCatch #3 {CancellationException -> 0x00e7, blocks: (B:26:0x0071, B:28:0x0075, B:22:0x005b), top: B:57:0x005b }] */
        /* JADX WARN: Code duplicated, block: B:30:0x009e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:31:0x009f  */
        /* JADX WARN: Code duplicated, block: B:34:0x00a9 A[Catch: CancellationException -> 0x00df, TryCatch #0 {CancellationException -> 0x00df, blocks: (B:32:0x00a1, B:34:0x00a9, B:36:0x00ba, B:38:0x00c9, B:39:0x00cc, B:40:0x00d1, B:41:0x00d8), top: B:53:0x00a1 }] */
        /* JADX WARN: Code duplicated, block: B:36:0x00ba A[Catch: CancellationException -> 0x00df, TryCatch #0 {CancellationException -> 0x00df, blocks: (B:32:0x00a1, B:34:0x00a9, B:36:0x00ba, B:38:0x00c9, B:39:0x00cc, B:40:0x00d1, B:41:0x00d8), top: B:53:0x00a1 }] */
        /* JADX WARN: Code duplicated, block: B:38:0x00c9 A[Catch: CancellationException -> 0x00df, TryCatch #0 {CancellationException -> 0x00df, blocks: (B:32:0x00a1, B:34:0x00a9, B:36:0x00ba, B:38:0x00c9, B:39:0x00cc, B:40:0x00d1, B:41:0x00d8), top: B:53:0x00a1 }] */
        /* JADX WARN: Code duplicated, block: B:41:0x00d8 A[Catch: CancellationException -> 0x00df, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00df, blocks: (B:32:0x00a1, B:34:0x00a9, B:36:0x00ba, B:38:0x00c9, B:39:0x00cc, B:40:0x00d1, B:41:0x00d8), top: B:53:0x00a1 }] */
        /* JADX WARN: Code duplicated, block: B:61:0x00cc A[SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CancellationException e;
            C03165 c03165;
            AwaitPointerEventScope awaitPointerEventScope;
            C03165 c03166;
            Object objM767awaitLongPressOrCancellationrnUCldI;
            PointerInputChange pointerInputChange;
            Object objM775dragjO51t88;
            List<PointerInputChange> changes;
            int i;
            int size;
            PointerInputChange pointerInputChange2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                switch (r1) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        this.L$0 = awaitPointerEventScope2;
                        this.label = 1;
                        Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope2, false, null, this, 2, null);
                        if (objAwaitFirstDown$default == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = objAwaitFirstDown$default;
                        awaitPointerEventScope = awaitPointerEventScope2;
                        c03166 = this;
                        try {
                            c03166.L$0 = awaitPointerEventScope;
                            c03166.label = 2;
                            objM767awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m767awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, ((PointerInputChange) obj).getId(), c03166);
                            if (objM767awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            obj = objM767awaitLongPressOrCancellationrnUCldI;
                            pointerInputChange = (PointerInputChange) obj;
                            if (pointerInputChange != null) {
                                c03166.$onDragStart.invoke(Offset.m4282boximpl(pointerInputChange.getPosition()));
                                long id = pointerInputChange.getId();
                                final Function2<PointerInputChange, Offset, Unit> function2 = c03166.$onDrag;
                                c03166.L$0 = awaitPointerEventScope;
                                c03166.label = 3;
                                objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88(awaitPointerEventScope, id, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.5.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange3) {
                                        invoke2(pointerInputChange3);
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(PointerInputChange it) {
                                        function2.invoke(it, Offset.m4282boximpl(PointerEventKt.positionChange(it)));
                                        it.consume();
                                    }
                                }, c03166);
                                if (objM775dragjO51t88 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                c03165 = c03166;
                                obj = objM775dragjO51t88;
                                try {
                                    if (((Boolean) obj).booleanValue()) {
                                        changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                                        size = changes.size();
                                        for (i = 0; i < size; i++) {
                                            pointerInputChange2 = changes.get(i);
                                            if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                                pointerInputChange2.consume();
                                            }
                                        }
                                        c03165.$onDragEnd.invoke();
                                    } else {
                                        c03165.$onDragCancel.invoke();
                                    }
                                } catch (CancellationException e2) {
                                    e = e2;
                                    c03165.$onDragCancel.invoke();
                                    throw e;
                                }
                            }
                            return Unit.INSTANCE;
                        } catch (CancellationException e3) {
                            c03165 = c03166;
                            e = e3;
                            c03165.$onDragCancel.invoke();
                            throw e;
                        }
                    case 1:
                        AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        awaitPointerEventScope = awaitPointerEventScope3;
                        c03166 = this;
                        c03166.L$0 = awaitPointerEventScope;
                        c03166.label = 2;
                        objM767awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m767awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, ((PointerInputChange) obj).getId(), c03166);
                        if (objM767awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = objM767awaitLongPressOrCancellationrnUCldI;
                        pointerInputChange = (PointerInputChange) obj;
                        if (pointerInputChange != null) {
                            c03166.$onDragStart.invoke(Offset.m4282boximpl(pointerInputChange.getPosition()));
                            long id2 = pointerInputChange.getId();
                            final Function2<? super PointerInputChange, ? super Offset, Unit> function3 = c03166.$onDrag;
                            c03166.L$0 = awaitPointerEventScope;
                            c03166.label = 3;
                            objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88(awaitPointerEventScope, id2, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.5.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange3) {
                                    invoke2(pointerInputChange3);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange it) {
                                    function3.invoke(it, Offset.m4282boximpl(PointerEventKt.positionChange(it)));
                                    it.consume();
                                }
                            }, c03166);
                            if (objM775dragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            c03165 = c03166;
                            obj = objM775dragjO51t88;
                            if (((Boolean) obj).booleanValue()) {
                                changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                                size = changes.size();
                                while (i < size) {
                                    pointerInputChange2 = changes.get(i);
                                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                        pointerInputChange2.consume();
                                    }
                                }
                                c03165.$onDragEnd.invoke();
                            } else {
                                c03165.$onDragCancel.invoke();
                            }
                        }
                        return Unit.INSTANCE;
                    case 2:
                        AwaitPointerEventScope awaitPointerEventScope4 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        awaitPointerEventScope = awaitPointerEventScope4;
                        c03166 = this;
                        obj = obj;
                        pointerInputChange = (PointerInputChange) obj;
                        if (pointerInputChange != null) {
                            c03166.$onDragStart.invoke(Offset.m4282boximpl(pointerInputChange.getPosition()));
                            long id3 = pointerInputChange.getId();
                            final Function2<? super PointerInputChange, ? super Offset, Unit> function4 = c03166.$onDrag;
                            c03166.L$0 = awaitPointerEventScope;
                            c03166.label = 3;
                            objM775dragjO51t88 = DragGestureDetectorKt.m775dragjO51t88(awaitPointerEventScope, id3, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGesturesAfterLongPress.5.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange3) {
                                    invoke2(pointerInputChange3);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PointerInputChange it) {
                                    function4.invoke(it, Offset.m4282boximpl(PointerEventKt.positionChange(it)));
                                    it.consume();
                                }
                            }, c03166);
                            if (objM775dragjO51t88 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            c03165 = c03166;
                            obj = objM775dragjO51t88;
                            if (((Boolean) obj).booleanValue()) {
                                changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                                size = changes.size();
                                while (i < size) {
                                    pointerInputChange2 = changes.get(i);
                                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                        pointerInputChange2.consume();
                                    }
                                }
                                c03165.$onDragEnd.invoke();
                            } else {
                                c03165.$onDragCancel.invoke();
                            }
                        }
                        return Unit.INSTANCE;
                    case 3:
                        c03165 = this;
                        AwaitPointerEventScope awaitPointerEventScope5 = (AwaitPointerEventScope) c03165.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            awaitPointerEventScope = awaitPointerEventScope5;
                            obj = obj;
                            c03165 = c03165;
                            if (((Boolean) obj).booleanValue()) {
                                changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                                size = changes.size();
                                while (i < size) {
                                    pointerInputChange2 = changes.get(i);
                                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                        pointerInputChange2.consume();
                                    }
                                }
                                c03165.$onDragEnd.invoke();
                            } else {
                                c03165.$onDragCancel.invoke();
                            }
                            return Unit.INSTANCE;
                        } catch (CancellationException e4) {
                            e = e4;
                            c03165.$onDragCancel.invoke();
                            throw e;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (CancellationException e5) {
                e = e5;
                c03165 = r1;
            }
        }
    }

    public static final Object detectDragGesturesAfterLongPress(PointerInputScope $this$detectDragGesturesAfterLongPress, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectDragGesturesAfterLongPress, new C03165(function1, function0, function2, function3, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f9 A[LOOP:0: B:23:0x00d6->B:27:0x00f9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x010d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x01aa -> B:59:0x01b1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m772awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m772awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f0 A[LOOP:0: B:23:0x00cd->B:27:0x00f0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x0104 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x01a4 -> B:59:0x01ad). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    public static final java.lang.Object m771awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m771awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:27:0x00da A[LOOP:0: B:23:0x00b7->B:27:0x00da, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:73:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0096 -> B:22:0x00a3). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: verticalDrag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m779verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r23, long r24, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super java.lang.Boolean> r27) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m779verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a9 A[LOOP:0: B:22:0x0089->B:26:0x00a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:61:0x00b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0070 -> B:21:0x0079). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m770awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, long r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m770awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {479, 481, 489}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"})
    static final class C03245 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onVerticalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03245(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function3, Continuation<? super C03245> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onVerticalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03245 c03245 = new C03245(this.$onDragStart, this.$onVerticalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c03245.L$0 = obj;
            return c03245;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C03245) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x007e A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:16:0x007f  */
        /* JADX WARN: Code duplicated, block: B:19:0x0084  */
        /* JADX WARN: Code duplicated, block: B:21:0x00bb A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:22:0x00bc  */
        /* JADX WARN: Code duplicated, block: B:25:0x00c5  */
        /* JADX WARN: Code duplicated, block: B:26:0x00cb  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            C03245 c03245;
            final Ref.FloatRef overSlop;
            AwaitPointerEventScope $this$awaitEachGesture2;
            PointerInputChange drag;
            C03245 c03246;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture3;
                    this.label = 1;
                    Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture3, false, null, this, 2, null);
                    if (objAwaitFirstDown$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture = $this$awaitEachGesture3;
                    c03245 = this;
                    $result = objAwaitFirstDown$default;
                    PointerInputChange down = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    c03245.L$0 = $this$awaitEachGesture;
                    c03245.L$1 = overSlop;
                    c03245.label = 2;
                    $result = DragGestureDetectorKt.m771awaitVerticalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down.getId(), down.getType(), new Function2<PointerInputChange, Float, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$drag$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Float f) {
                            invoke(pointerInputChange, f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PointerInputChange change, float over) {
                            change.consume();
                            overSlop.element = over;
                        }
                    }, c03245);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture2 = $this$awaitEachGesture;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        c03245.$onDragStart.invoke(Offset.m4282boximpl(drag.getPosition()));
                        c03245.$onVerticalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id = drag.getId();
                        final Function2<PointerInputChange, Float, Unit> function2 = c03245.$onVerticalDrag;
                        c03245.L$0 = null;
                        c03245.L$1 = null;
                        c03245.label = 3;
                        $result = DragGestureDetectorKt.m779verticalDragjO51t88($this$awaitEachGesture2, id, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectVerticalDragGestures.5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                function2.invoke(it, Float.valueOf(Offset.m4294getYimpl(PointerEventKt.positionChange(it))));
                                it.consume();
                            }
                        }, c03245);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c03246 = c03245;
                        if (((Boolean) $result).booleanValue()) {
                            c03246.$onDragEnd.invoke();
                        } else {
                            c03246.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    AwaitPointerEventScope $this$awaitEachGesture4 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = $this$awaitEachGesture4;
                    c03245 = this;
                    PointerInputChange down2 = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    c03245.L$0 = $this$awaitEachGesture;
                    c03245.L$1 = overSlop;
                    c03245.label = 2;
                    $result = DragGestureDetectorKt.m771awaitVerticalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down2.getId(), down2.getType(), new Function2<PointerInputChange, Float, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$drag$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Float f) {
                            invoke(pointerInputChange, f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PointerInputChange change, float over) {
                            change.consume();
                            overSlop.element = over;
                        }
                    }, c03245);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture2 = $this$awaitEachGesture;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        c03245.$onDragStart.invoke(Offset.m4282boximpl(drag.getPosition()));
                        c03245.$onVerticalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id2 = drag.getId();
                        final Function2<? super PointerInputChange, ? super Float, Unit> function3 = c03245.$onVerticalDrag;
                        c03245.L$0 = null;
                        c03245.L$1 = null;
                        c03245.label = 3;
                        $result = DragGestureDetectorKt.m779verticalDragjO51t88($this$awaitEachGesture2, id2, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectVerticalDragGestures.5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                function3.invoke(it, Float.valueOf(Offset.m4294getYimpl(PointerEventKt.positionChange(it))));
                                it.consume();
                            }
                        }, c03245);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c03246 = c03245;
                        if (((Boolean) $result).booleanValue()) {
                            c03246.$onDragEnd.invoke();
                        } else {
                            c03246.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    Ref.FloatRef overSlop2 = (Ref.FloatRef) this.L$1;
                    $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    overSlop = overSlop2;
                    c03245 = this;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        c03245.$onDragStart.invoke(Offset.m4282boximpl(drag.getPosition()));
                        c03245.$onVerticalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id3 = drag.getId();
                        final Function2<? super PointerInputChange, ? super Float, Unit> function4 = c03245.$onVerticalDrag;
                        c03245.L$0 = null;
                        c03245.L$1 = null;
                        c03245.label = 3;
                        $result = DragGestureDetectorKt.m779verticalDragjO51t88($this$awaitEachGesture2, id3, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectVerticalDragGestures.5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                function4.invoke(it, Float.valueOf(Offset.m4294getYimpl(PointerEventKt.positionChange(it))));
                                it.consume();
                            }
                        }, c03245);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c03246 = c03245;
                        if (((Boolean) $result).booleanValue()) {
                            c03246.$onDragEnd.invoke();
                        } else {
                            c03246.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    c03246 = this;
                    ResultKt.throwOnFailure($result);
                    if (((Boolean) $result).booleanValue()) {
                        c03246.$onDragEnd.invoke();
                    } else {
                        c03246.$onDragCancel.invoke();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object detectVerticalDragGestures(PointerInputScope $this$detectVerticalDragGestures, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Float, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectVerticalDragGestures, new C03245(function1, function3, function0, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f9 A[LOOP:0: B:23:0x00d6->B:27:0x00f9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x010d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x01aa -> B:59:0x01b1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m766awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m766awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:27:0x00f0 A[LOOP:0: B:23:0x00cd->B:27:0x00f0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x0104 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x01a4 -> B:59:0x01ad). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    public static final java.lang.Object m765awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m765awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:27:0x00da A[LOOP:0: B:23:0x00b7->B:27:0x00da, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:73:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0096 -> B:22:0x00a3). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: horizontalDrag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m776horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r23, long r24, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r26, kotlin.coroutines.Continuation<? super java.lang.Boolean> r27) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m776horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a9 A[LOOP:0: B:22:0x0089->B:26:0x00a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:61:0x00b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0070 -> B:21:0x0079). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m764awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r20, long r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m764awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DragGestureDetector.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {627, 629, 640}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"})
    static final class C03205 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onHorizontalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03205(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function3, Continuation<? super C03205> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onHorizontalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03205 c03205 = new C03205(this.$onDragStart, this.$onHorizontalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c03205.L$0 = obj;
            return c03205;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C03205) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x007f A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:16:0x0080  */
        /* JADX WARN: Code duplicated, block: B:19:0x0085  */
        /* JADX WARN: Code duplicated, block: B:21:0x00bc A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:22:0x00bd  */
        /* JADX WARN: Code duplicated, block: B:25:0x00c6  */
        /* JADX WARN: Code duplicated, block: B:26:0x00cc  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            AwaitPointerEventScope $this$awaitEachGesture;
            C03205 c03205;
            final Ref.FloatRef overSlop;
            AwaitPointerEventScope $this$awaitEachGesture2;
            PointerInputChange drag;
            C03205 c03206;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                    this.L$0 = $this$awaitEachGesture3;
                    this.label = 1;
                    Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture3, false, null, this, 2, null);
                    if (objAwaitFirstDown$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture = $this$awaitEachGesture3;
                    c03205 = this;
                    $result = objAwaitFirstDown$default;
                    PointerInputChange down = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    c03205.L$0 = $this$awaitEachGesture;
                    c03205.L$1 = overSlop;
                    c03205.label = 2;
                    $result = DragGestureDetectorKt.m765awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down.getId(), down.getType(), new Function2<PointerInputChange, Float, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Float f) {
                            invoke(pointerInputChange, f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PointerInputChange change, float over) {
                            change.consume();
                            overSlop.element = over;
                        }
                    }, c03205);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture2 = $this$awaitEachGesture;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        c03205.$onDragStart.invoke(Offset.m4282boximpl(drag.getPosition()));
                        c03205.$onHorizontalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id = drag.getId();
                        final Function2<PointerInputChange, Float, Unit> function2 = c03205.$onHorizontalDrag;
                        c03205.L$0 = null;
                        c03205.L$1 = null;
                        c03205.label = 3;
                        $result = DragGestureDetectorKt.m776horizontalDragjO51t88($this$awaitEachGesture2, id, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectHorizontalDragGestures.5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                function2.invoke(it, Float.valueOf(Offset.m4293getXimpl(PointerEventKt.positionChange(it))));
                                it.consume();
                            }
                        }, c03205);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c03206 = c03205;
                        if (((Boolean) $result).booleanValue()) {
                            c03206.$onDragEnd.invoke();
                        } else {
                            c03206.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    AwaitPointerEventScope $this$awaitEachGesture4 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    $this$awaitEachGesture = $this$awaitEachGesture4;
                    c03205 = this;
                    PointerInputChange down2 = (PointerInputChange) $result;
                    overSlop = new Ref.FloatRef();
                    c03205.L$0 = $this$awaitEachGesture;
                    c03205.L$1 = overSlop;
                    c03205.label = 2;
                    $result = DragGestureDetectorKt.m765awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitEachGesture, down2.getId(), down2.getType(), new Function2<PointerInputChange, Float, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange, Float f) {
                            invoke(pointerInputChange, f.floatValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(PointerInputChange change, float over) {
                            change.consume();
                            overSlop.element = over;
                        }
                    }, c03205);
                    if ($result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this$awaitEachGesture2 = $this$awaitEachGesture;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        c03205.$onDragStart.invoke(Offset.m4282boximpl(drag.getPosition()));
                        c03205.$onHorizontalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id2 = drag.getId();
                        final Function2<? super PointerInputChange, ? super Float, Unit> function3 = c03205.$onHorizontalDrag;
                        c03205.L$0 = null;
                        c03205.L$1 = null;
                        c03205.label = 3;
                        $result = DragGestureDetectorKt.m776horizontalDragjO51t88($this$awaitEachGesture2, id2, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectHorizontalDragGestures.5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                function3.invoke(it, Float.valueOf(Offset.m4293getXimpl(PointerEventKt.positionChange(it))));
                                it.consume();
                            }
                        }, c03205);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c03206 = c03205;
                        if (((Boolean) $result).booleanValue()) {
                            c03206.$onDragEnd.invoke();
                        } else {
                            c03206.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    Ref.FloatRef overSlop2 = (Ref.FloatRef) this.L$1;
                    $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    overSlop = overSlop2;
                    c03205 = this;
                    drag = (PointerInputChange) $result;
                    if (drag != null) {
                        c03205.$onDragStart.invoke(Offset.m4282boximpl(drag.getPosition()));
                        c03205.$onHorizontalDrag.invoke(drag, Boxing.boxFloat(overSlop.element));
                        long id3 = drag.getId();
                        final Function2<? super PointerInputChange, ? super Float, Unit> function4 = c03205.$onHorizontalDrag;
                        c03205.L$0 = null;
                        c03205.L$1 = null;
                        c03205.label = 3;
                        $result = DragGestureDetectorKt.m776horizontalDragjO51t88($this$awaitEachGesture2, id3, new Function1<PointerInputChange, Unit>() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt.detectHorizontalDragGestures.5.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(PointerInputChange pointerInputChange) {
                                invoke2(pointerInputChange);
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(PointerInputChange it) {
                                function4.invoke(it, Float.valueOf(Offset.m4293getXimpl(PointerEventKt.positionChange(it))));
                                it.consume();
                            }
                        }, c03205);
                        if ($result == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c03206 = c03205;
                        if (((Boolean) $result).booleanValue()) {
                            c03206.$onDragEnd.invoke();
                        } else {
                            c03206.$onDragCancel.invoke();
                        }
                    }
                    return Unit.INSTANCE;
                case 3:
                    c03206 = this;
                    ResultKt.throwOnFailure($result);
                    if (((Boolean) $result).booleanValue()) {
                        c03206.$onDragEnd.invoke();
                    } else {
                        c03206.$onDragCancel.invoke();
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public static final Object detectHorizontalDragGestures(PointerInputScope $this$detectHorizontalDragGestures, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Float, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$detectHorizontalDragGestures, new C03205(function1, function3, function0, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:27:0x00e4 A[LOOP:0: B:23:0x00bf->B:27:0x00e4, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:70:0x00fb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x009d -> B:22:0x00ab). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: drag-VnAYq1g, reason: not valid java name */
    public static final java.lang.Object m773dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope r23, long r24, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r26, androidx.compose.foundation.gestures.Orientation r27, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r28, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r29) {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m773dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, androidx.compose.foundation.gestures.Orientation, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: drag-VnAYq1g$$forInline, reason: not valid java name */
    private static final Object m774dragVnAYq1g$$forInline(AwaitPointerEventScope $this$drag_u2dVnAYq1g, long pointerId, Function1<? super PointerInputChange, Unit> function1, Orientation orientation, Function1<? super PointerInputChange, Boolean> function2, Continuation<? super PointerInputChange> continuation) {
        int i;
        AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88$iv;
        Object it$iv$iv;
        PointerInputChange dragEvent$iv;
        float motionChange;
        Object it$iv$iv2;
        int i2 = 0;
        PointerEventPass pointerEventPass = null;
        if (m777isPointerUpDmW0f2w($this$drag_u2dVnAYq1g.getCurrentEvent(), pointerId)) {
            return null;
        }
        long pointer = pointerId;
        while (true) {
            AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88$iv2 = $this$drag_u2dVnAYq1g;
            Ref.LongRef pointer$iv = new Ref.LongRef();
            pointer$iv.element = pointer;
            while (true) {
                InlineMarker.mark(0);
                Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default($this$awaitDragOrUp_u2djO51t88$iv2, pointerEventPass, continuation, 1, pointerEventPass);
                InlineMarker.mark(1);
                PointerEvent event$iv = (PointerEvent) objAwaitPointerEvent$default;
                List<PointerInputChange> changes = event$iv.getChanges();
                int size = changes.size();
                int index$iv$iv$iv = 0;
                while (true) {
                    if (index$iv$iv$iv >= size) {
                        i = i2;
                        $this$awaitDragOrUp_u2djO51t88$iv = $this$awaitDragOrUp_u2djO51t88$iv2;
                        it$iv$iv = null;
                        break;
                    }
                    List<PointerInputChange> list = changes;
                    Object item$iv$iv$iv = list.get(index$iv$iv$iv);
                    it$iv$iv = item$iv$iv$iv;
                    PointerInputChange it$iv = (PointerInputChange) it$iv$iv;
                    i = i2;
                    $this$awaitDragOrUp_u2djO51t88$iv = $this$awaitDragOrUp_u2djO51t88$iv2;
                    if (Boolean.valueOf(PointerId.m5723equalsimpl0(it$iv.getId(), pointer$iv.element)).booleanValue()) {
                        break;
                    }
                    index$iv$iv$iv++;
                    changes = list;
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                }
                PointerInputChange pointerInputChange = (PointerInputChange) it$iv$iv;
                if (pointerInputChange == null) {
                    dragEvent$iv = null;
                    break;
                }
                dragEvent$iv = pointerInputChange;
                if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent$iv)) {
                    List<PointerInputChange> changes2 = event$iv.getChanges();
                    int index$iv$iv$iv2 = 0;
                    int size2 = changes2.size();
                    while (true) {
                        if (index$iv$iv$iv2 >= size2) {
                            it$iv$iv2 = null;
                            break;
                        }
                        Object item$iv$iv$iv2 = changes2.get(index$iv$iv$iv2);
                        it$iv$iv2 = item$iv$iv$iv2;
                        PointerInputChange it$iv2 = (PointerInputChange) it$iv$iv2;
                        if (Boolean.valueOf(it$iv2.getPressed()).booleanValue()) {
                            break;
                        }
                        index$iv$iv$iv2++;
                    }
                    PointerInputChange otherDown$iv = (PointerInputChange) it$iv$iv2;
                    if (otherDown$iv == null) {
                        break;
                    }
                    pointer$iv.element = otherDown$iv.getId();
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                    pointerEventPass = null;
                } else {
                    PointerInputChange it = dragEvent$iv;
                    long positionChange = PointerEventKt.positionChangeIgnoreConsumed(it);
                    if (orientation == null) {
                        motionChange = Offset.m4291getDistanceimpl(positionChange);
                    } else {
                        motionChange = orientation == Orientation.Vertical ? Offset.m4294getYimpl(positionChange) : Offset.m4293getXimpl(positionChange);
                    }
                    if (Boolean.valueOf(!(motionChange == 0.0f)).booleanValue()) {
                        break;
                    }
                    i2 = i;
                    $this$awaitDragOrUp_u2djO51t88$iv2 = $this$awaitDragOrUp_u2djO51t88$iv;
                    pointerEventPass = null;
                }
            }
            if (dragEvent$iv == null || function2.invoke(dragEvent$iv).booleanValue()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent$iv)) {
                return dragEvent$iv;
            }
            function1.invoke(dragEvent$iv);
            pointer = dragEvent$iv.getId();
            i2 = i;
            pointerEventPass = null;
        }
    }

    /* JADX INFO: renamed from: awaitDragOrUp-jO51t88, reason: not valid java name */
    private static final Object m763awaitDragOrUpjO51t88(AwaitPointerEventScope $this$awaitDragOrUp_u2djO51t88, long pointerId, Function1<? super PointerInputChange, Boolean> function1, Continuation<? super PointerInputChange> continuation) {
        PointerEvent event;
        Object it$iv;
        Object obj;
        Ref.LongRef pointer = new Ref.LongRef();
        pointer.element = pointerId;
        while (true) {
            InlineMarker.mark(0);
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default($this$awaitDragOrUp_u2djO51t88, null, continuation, 1, null);
            InlineMarker.mark(1);
            PointerEvent event2 = (PointerEvent) objAwaitPointerEvent$default;
            List<PointerInputChange> changes = event2.getChanges();
            int index$iv$iv = 0;
            int size = changes.size();
            while (true) {
                if (index$iv$iv >= size) {
                    event = event2;
                    it$iv = null;
                    break;
                }
                it$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) it$iv;
                event = event2;
                if (Boolean.valueOf(PointerId.m5723equalsimpl0(it.getId(), pointer.element)).booleanValue()) {
                    break;
                }
                index$iv$iv++;
                event2 = event;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List<PointerInputChange> changes2 = event.getChanges();
                int index$iv$iv2 = 0;
                int size2 = changes2.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        obj = null;
                        break;
                    }
                    Object item$iv$iv = changes2.get(index$iv$iv2);
                    PointerInputChange it2 = (PointerInputChange) item$iv$iv;
                    if (Boolean.valueOf(it2.getPressed()).booleanValue()) {
                        obj = item$iv$iv;
                        break;
                    }
                    index$iv$iv2++;
                }
                PointerInputChange otherDown = (PointerInputChange) obj;
                if (otherDown == null) {
                    return dragEvent;
                }
                pointer.element = otherDown.getId();
            } else if (function1.invoke(dragEvent).booleanValue()) {
                return dragEvent;
            }
        }
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-pn7EDYM, reason: not valid java name */
    private static final Object m768awaitPointerSlopOrCancellationpn7EDYM(AwaitPointerEventScope $this$awaitPointerSlopOrCancellation_u2dpn7EDYM, long pointerId, int pointerType, Orientation orientation, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super PointerInputChange> continuation) {
        Object it$iv;
        Object it$iv2;
        PointerEventPass pointerEventPass = null;
        if (m777isPointerUpDmW0f2w($this$awaitPointerSlopOrCancellation_u2dpn7EDYM.getCurrentEvent(), pointerId)) {
            return null;
        }
        float touchSlop = m778pointerSlopE8SPZFQ($this$awaitPointerSlopOrCancellation_u2dpn7EDYM.getViewConfiguration(), pointerType);
        Ref.LongRef pointer = new Ref.LongRef();
        pointer.element = pointerId;
        TouchSlopDetector touchSlopDetector = new TouchSlopDetector(orientation);
        while (true) {
            InlineMarker.mark(0);
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default($this$awaitPointerSlopOrCancellation_u2dpn7EDYM, pointerEventPass, continuation, 1, pointerEventPass);
            InlineMarker.mark(1);
            PointerEvent event = (PointerEvent) objAwaitPointerEvent$default;
            List<PointerInputChange> changes = event.getChanges();
            int size = changes.size();
            int index$iv$iv = 0;
            while (true) {
                if (index$iv$iv >= size) {
                    it$iv = null;
                    break;
                }
                List<PointerInputChange> list = changes;
                Object item$iv$iv = list.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                if (Boolean.valueOf(PointerId.m5723equalsimpl0(it.getId(), pointer.element)).booleanValue()) {
                    break;
                }
                index$iv$iv++;
                changes = list;
            }
            PointerInputChange dragEvent = (PointerInputChange) it$iv;
            if (dragEvent == null || dragEvent.isConsumed()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(dragEvent)) {
                List<PointerInputChange> changes2 = event.getChanges();
                int index$iv$iv2 = 0;
                int size2 = changes2.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        it$iv2 = null;
                        break;
                    }
                    Object item$iv$iv2 = changes2.get(index$iv$iv2);
                    it$iv2 = item$iv$iv2;
                    PointerInputChange it2 = (PointerInputChange) it$iv2;
                    if (Boolean.valueOf(it2.getPressed()).booleanValue()) {
                        break;
                    }
                    index$iv$iv2++;
                }
                PointerInputChange otherDown = (PointerInputChange) it$iv2;
                if (otherDown == null) {
                    return null;
                }
                pointer.element = otherDown.getId();
            } else {
                Offset postSlopOffset = touchSlopDetector.m857addPointerInputChangeGcwITfU(dragEvent, touchSlop);
                if (postSlopOffset != null) {
                    function2.invoke(dragEvent, postSlopOffset);
                    if (dragEvent.isConsumed()) {
                        return dragEvent;
                    }
                    touchSlopDetector.reset();
                } else {
                    PointerEventPass pointerEventPass2 = PointerEventPass.Final;
                    InlineMarker.mark(0);
                    $this$awaitPointerSlopOrCancellation_u2dpn7EDYM.awaitPointerEvent(pointerEventPass2, continuation);
                    InlineMarker.mark(1);
                    if (dragEvent.isConsumed()) {
                        return null;
                    }
                }
            }
            pointerEventPass = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [androidx.compose.ui.input.pointer.PointerInputChange] */
    /* JADX INFO: renamed from: awaitLongPressOrCancellation-rnUCldI, reason: not valid java name */
    public static final Object m767awaitLongPressOrCancellationrnUCldI(AwaitPointerEventScope $this$awaitLongPressOrCancellation_u2drnUCldI, long pointerId, Continuation<? super PointerInputChange> continuation) {
        DragGestureDetectorKt$awaitLongPressOrCancellation$1 dragGestureDetectorKt$awaitLongPressOrCancellation$1;
        Object it$iv;
        ?? r5;
        Ref.ObjectRef longPress;
        if (continuation instanceof DragGestureDetectorKt$awaitLongPressOrCancellation$1) {
            dragGestureDetectorKt$awaitLongPressOrCancellation$1 = (DragGestureDetectorKt$awaitLongPressOrCancellation$1) continuation;
            if ((dragGestureDetectorKt$awaitLongPressOrCancellation$1.label & Integer.MIN_VALUE) != 0) {
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.label -= Integer.MIN_VALUE;
            } else {
                dragGestureDetectorKt$awaitLongPressOrCancellation$1 = new DragGestureDetectorKt$awaitLongPressOrCancellation$1(continuation);
            }
        } else {
            dragGestureDetectorKt$awaitLongPressOrCancellation$1 = new DragGestureDetectorKt$awaitLongPressOrCancellation$1(continuation);
        }
        Object $result = dragGestureDetectorKt$awaitLongPressOrCancellation$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dragGestureDetectorKt$awaitLongPressOrCancellation$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (m777isPointerUpDmW0f2w($this$awaitLongPressOrCancellation_u2drnUCldI.getCurrentEvent(), pointerId)) {
                    return null;
                }
                List<PointerInputChange> changes = $this$awaitLongPressOrCancellation_u2drnUCldI.getCurrentEvent().getChanges();
                int $i$f$fastFirstOrNull = 0;
                int index$iv$iv = 0;
                int size = changes.size();
                while (true) {
                    if (index$iv$iv < size) {
                        Object item$iv$iv = changes.get(index$iv$iv);
                        it$iv = item$iv$iv;
                        PointerInputChange it = (PointerInputChange) it$iv;
                        List<PointerInputChange> list = changes;
                        int $i$f$fastFirstOrNull2 = $i$f$fastFirstOrNull;
                        if (!PointerId.m5723equalsimpl0(it.getId(), pointerId)) {
                            index$iv$iv++;
                            $i$f$fastFirstOrNull = $i$f$fastFirstOrNull2;
                            changes = list;
                        }
                    } else {
                        it$iv = null;
                    }
                }
                PointerInputChange pointerInputChange = (PointerInputChange) it$iv;
                if (pointerInputChange == null) {
                    return null;
                }
                r5 = pointerInputChange;
                Ref.ObjectRef longPress2 = new Ref.ObjectRef();
                Ref.ObjectRef currentDown = new Ref.ObjectRef();
                currentDown.element = r5;
                long longPressTimeout = $this$awaitLongPressOrCancellation_u2drnUCldI.getViewConfiguration().getLongPressTimeoutMillis();
                try {
                    DragGestureDetectorKt$awaitLongPressOrCancellation$2 dragGestureDetectorKt$awaitLongPressOrCancellation$2 = new DragGestureDetectorKt$awaitLongPressOrCancellation$2(currentDown, longPress2, null);
                    dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$0 = r5;
                    dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$1 = longPress2;
                    dragGestureDetectorKt$awaitLongPressOrCancellation$1.label = 1;
                    if ($this$awaitLongPressOrCancellation_u2drnUCldI.withTimeout(longPressTimeout, dragGestureDetectorKt$awaitLongPressOrCancellation$2, dragGestureDetectorKt$awaitLongPressOrCancellation$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return null;
                } catch (PointerEventTimeoutCancellationException e) {
                    longPress = longPress2;
                }
                break;
            case 1:
                longPress = (Ref.ObjectRef) dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$1;
                r5 = (PointerInputChange) dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    return null;
                } catch (PointerEventTimeoutCancellationException e2) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PointerInputChange pointerInputChange2 = (PointerInputChange) longPress.element;
        return pointerInputChange2 == null ? r5 : pointerInputChange2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPointerUp-DmW0f2w, reason: not valid java name */
    public static final boolean m777isPointerUpDmW0f2w(PointerEvent $this$isPointerUp_u2dDmW0f2w, long pointerId) {
        Object it$iv;
        List<PointerInputChange> changes = $this$isPointerUp_u2dDmW0f2w.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                it$iv = item$iv$iv;
                PointerInputChange it = (PointerInputChange) it$iv;
                if (PointerId.m5723equalsimpl0(it.getId(), pointerId)) {
                    break;
                }
                index$iv$iv++;
            } else {
                it$iv = null;
                break;
            }
        }
        PointerInputChange pointerInputChange = (PointerInputChange) it$iv;
        boolean z = false;
        if (pointerInputChange != null && pointerInputChange.getPressed()) {
            z = true;
        }
        return !z;
    }

    static {
        float arg0$iv = mouseSlop;
        float other$iv = defaultTouchSlop;
        mouseToTouchSlopRatio = arg0$iv / other$iv;
    }

    /* JADX INFO: renamed from: pointerSlop-E8SPZFQ, reason: not valid java name */
    public static final float m778pointerSlopE8SPZFQ(ViewConfiguration $this$pointerSlop_u2dE8SPZFQ, int pointerType) {
        return PointerType.m5813equalsimpl0(pointerType, PointerType.INSTANCE.m5818getMouseT8wyACA()) ? $this$pointerSlop_u2dE8SPZFQ.getTouchSlop() * mouseToTouchSlopRatio : $this$pointerSlop_u2dE8SPZFQ.getTouchSlop();
    }
}
