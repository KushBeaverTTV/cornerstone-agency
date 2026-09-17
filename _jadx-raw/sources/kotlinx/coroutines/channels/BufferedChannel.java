package kotlinx.coroutines.channels;

import androidx.autofill.HintConstants;
import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectClause2Impl;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: BufferedChannel.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0004ï\u0001ð\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\"\b\u0002\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0006¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u0010#J4\u0010%\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u0011H\u0082@¢\u0006\u0002\u0010)J\"\u0010*\u001a\u00020\b*\u00020+2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u0004H\u0002J#\u0010,\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u00002\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0.H\u0002¢\u0006\u0002\u0010/J\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020\b012\u0006\u0010\"\u001a\u00028\u0000H\u0016¢\u0006\u0004\b2\u00103J\u0018\u00104\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00028\u0000H\u0090@¢\u0006\u0004\b5\u0010#Jê\u0001\u00106\u001a\u0002H7\"\u0004\b\u0001\u001072\u0006\u0010\"\u001a\u00028\u00002\b\u00108\u001a\u0004\u0018\u0001092\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H70;2<\u0010<\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0004\u0012\u0002H70=2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H70;2h\b\u0002\u0010C\u001ab\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H70DH\u0082\b¢\u0006\u0002\u0010EJ\u001d\u0010F\u001a\b\u0012\u0004\u0012\u00020\b012\u0006\u0010\"\u001a\u00028\u0000H\u0004¢\u0006\u0004\bG\u00103JX\u0010H\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u00112\u0006\u00108\u001a\u00020+2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\b0;2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0;H\u0082\b¢\u0006\u0002\u0010IJE\u0010J\u001a\u00020\u00042\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010K\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010LJE\u0010M\u001a\u00020\u00042\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010K\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010LJ\u0010\u0010N\u001a\u00020\u001a2\u0006\u0010O\u001a\u00020\u0011H\u0003J\u0010\u0010P\u001a\u00020\u001a2\u0006\u0010Q\u001a\u00020\u0011H\u0002J\r\u0010N\u001a\u00020\u001aH\u0010¢\u0006\u0002\bRJ\u0019\u0010S\u001a\u00020\u001a*\u0002092\u0006\u0010\"\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010TJ\b\u0010U\u001a\u00020\bH\u0014J\b\u0010V\u001a\u00020\bH\u0014J\u000e\u0010W\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010XJ;\u0010Y\u001a\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0011H\u0082@¢\u0006\u0002\u0010[J\"\u0010\\\u001a\u00020\b*\u00020+2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0016\u0010]\u001a\u00020\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000.H\u0002J\u0016\u0010^\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0096@¢\u0006\u0004\b_\u0010XJ4\u0010`\u001a\b\u0012\u0004\u0012\u00028\u0000012\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u0011H\u0082@¢\u0006\u0004\ba\u0010[J\u001c\u0010b\u001a\u00020\b2\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000010.H\u0002J\u0015\u0010c\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0016¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\b2\u0006\u0010g\u001a\u00020\u0011H\u0004J÷\u0001\u0010h\u001a\u0002H7\"\u0004\b\u0001\u001072\b\u00108\u001a\u0004\u0018\u0001092!\u0010i\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H70\u00072Q\u0010<\u001aM\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(Z\u0012\u0004\u0012\u0002H70j2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H70;2S\b\u0002\u0010C\u001aM\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u001e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(@\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(Z\u0012\u0004\u0012\u0002H70jH\u0082\b¢\u0006\u0002\u0010kJ`\u0010l\u001a\u00020\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00112\u0006\u00108\u001a\u00020+2!\u0010i\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\b0\u00072\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0;H\u0082\bJ2\u0010m\u001a\u0004\u0018\u0001092\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u000109H\u0002J2\u0010n\u001a\u0004\u0018\u0001092\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00112\b\u00108\u001a\u0004\u0018\u000109H\u0002J\"\u0010o\u001a\u00020\u001a*\u0002092\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u0004H\u0002J\b\u0010p\u001a\u00020\bH\u0002J&\u0010q\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u0011H\u0002J&\u0010s\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u0011H\u0002J\u0012\u0010t\u001a\u00020\b2\b\b\u0002\u0010u\u001a\u00020\u0011H\u0002J\u0015\u0010v\u001a\u00020\b2\u0006\u0010w\u001a\u00020\u0011H\u0000¢\u0006\u0002\bxJ \u0010\u007f\u001a\u00020\b2\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u00012\b\u0010\"\u001a\u0004\u0018\u000109H\u0014J%\u0010\u0082\u0001\u001a\u00020\b2\u0006\u0010\"\u001a\u00028\u00002\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u0001H\u0002¢\u0006\u0003\u0010\u0083\u0001J!\u0010\u0084\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J\"\u0010\u0092\u0001\u001a\u00020\b2\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u00012\t\u0010\u0085\u0001\u001a\u0004\u0018\u000109H\u0002J\u0017\u0010\u0093\u0001\u001a\u00020\b2\f\u0010\u0080\u0001\u001a\u0007\u0012\u0002\b\u00030\u0081\u0001H\u0002J!\u0010\u0094\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J!\u0010\u0095\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J!\u0010\u0096\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0085\u0001\u001a\u0004\u0018\u0001092\t\u0010\u0086\u0001\u001a\u0004\u0018\u000109H\u0002J\u0011\u0010\u009f\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000 \u0001H\u0096\u0002J\t\u0010ª\u0001\u001a\u00020\bH\u0014J\u0015\u0010«\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J\u0013\u0010\u00ad\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u0001J\u0007\u0010\u00ad\u0001\u001a\u00020\bJ \u0010\u00ad\u0001\u001a\u00020\b2\u0011\u0010¬\u0001\u001a\f\u0018\u00010¯\u0001j\u0005\u0018\u0001`®\u0001¢\u0006\u0003\u0010°\u0001J\u001b\u0010±\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0010¢\u0006\u0003\b²\u0001J\u001e\u0010³\u0001\u001a\u00020\u001a2\n\u0010¬\u0001\u001a\u0005\u0018\u00010\u009b\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\u001aH\u0014J\t\u0010´\u0001\u001a\u00020\bH\u0002J1\u0010µ\u0001\u001a\u00020\b2&\u0010¶\u0001\u001a!\u0012\u0017\u0012\u0015\u0018\u00010\u009b\u0001¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(¬\u0001\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\t\u0010·\u0001\u001a\u00020\bH\u0002J\t\u0010¸\u0001\u001a\u00020\bH\u0002J\t\u0010¹\u0001\u001a\u00020\bH\u0002J\t\u0010º\u0001\u001a\u00020\bH\u0002J\u0018\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0007\u0010½\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010¾\u0001\u001a\u00020\b2\u0007\u0010½\u0001\u001a\u00020\u0011H\u0002J\u000f\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\u0018\u0010À\u0001\u001a\u00020\u00112\r\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\u0018\u0010Â\u0001\u001a\u00020\b2\r\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J \u0010Ã\u0001\u001a\u00020\b2\r\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010Ä\u0001\u001a\u00020\b*\u00020+H\u0002J\r\u0010Å\u0001\u001a\u00020\b*\u00020+H\u0002J\u0016\u0010Æ\u0001\u001a\u00020\b*\u00020+2\u0007\u0010Ç\u0001\u001a\u00020\u001aH\u0002J\u001b\u0010Ï\u0001\u001a\u00020\u001a2\u0007\u0010Ð\u0001\u001a\u00020\u00112\u0007\u0010Ì\u0001\u001a\u00020\u001aH\u0002J\u000f\u0010Ó\u0001\u001a\u00020\u001aH\u0000¢\u0006\u0003\bÔ\u0001J'\u0010Õ\u0001\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u0011H\u0002J)\u0010Ö\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001e2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J)\u0010Ù\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001e2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J2\u0010Ú\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001e2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\u0007\u0010Û\u0001\u001a\u00020\u0011H\u0002J!\u0010Ü\u0001\u001a\u00020\b2\u0007\u0010×\u0001\u001a\u00020\u00112\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH\u0002J\u0012\u0010Ý\u0001\u001a\u00020\b2\u0007\u0010Þ\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010ß\u0001\u001a\u00020\b2\u0007\u0010Þ\u0001\u001a\u00020\u0011H\u0002J\n\u0010à\u0001\u001a\u00030á\u0001H\u0016J\u0010\u0010â\u0001\u001a\u00030á\u0001H\u0000¢\u0006\u0003\bã\u0001J\u0007\u0010ä\u0001\u001a\u00020\bJJ\u0010å\u0001\u001a#\u0012\u0005\u0012\u00030\u009b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000001\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0æ\u0001*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\u0006H\u0002¢\u0006\u0003\u0010ç\u0001J4\u0010è\u0001\u001a\u00020\b2\b\u0010¬\u0001\u001a\u00030\u009b\u00012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u0000012\b\u0010é\u0001\u001a\u00030\u009c\u0001H\u0002¢\u0006\u0006\bê\u0001\u0010ë\u0001JM\u0010ì\u0001\u001a\u001e\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0006\u0012\u0004\u0018\u000109\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0j*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\u00062\u0006\u0010\"\u001a\u00028\u0000H\u0002¢\u0006\u0003\u0010í\u0001JD\u0010ì\u0001\u001a\u001d\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0æ\u0001*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\u0006H\u0002¢\u0006\u0003\u0010ç\u0001J+\u0010î\u0001\u001a\u00020\b2\b\u0010¬\u0001\u001a\u00030\u009b\u00012\u0006\u0010\"\u001a\u00028\u00002\b\u0010é\u0001\u001a\u00030\u009c\u0001H\u0002¢\u0006\u0003\u0010ë\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00068\u0000X\u0081\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\t\u0010\f\u001a\u00020\rX\u0082\u0004R\t\u0010\u000e\u001a\u00020\rX\u0082\u0004R\t\u0010\u000f\u001a\u00020\rX\u0082\u0004R\u0014\u0010\u0010\u001a\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\t\u0010\u0018\u001a\u00020\rX\u0082\u0004R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u0015\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0082\u0004R\u0015\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0082\u0004R\u0015\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e0\u001dX\u0082\u0004R,\u0010y\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000z8VX\u0096\u0004¢\u0006\f\u0012\u0004\b{\u0010|\u001a\u0004\b}\u0010~R%\u0010\u0087\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0088\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\b\u0089\u0001\u0010|\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R+\u0010\u008c\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000010\u0088\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\b\u008d\u0001\u0010|\u001a\u0006\b\u008e\u0001\u0010\u008b\u0001R'\u0010\u008f\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0088\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\b\u0090\u0001\u0010|\u001a\u0006\b\u0091\u0001\u0010\u008b\u0001R\u008f\u0001\u0010\u0097\u0001\u001ax\u0012\u0019\u0012\u0017\u0012\u0002\b\u00030\u0081\u0001¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u0080\u0001\u0012\u0016\u0012\u0014\u0018\u000109¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u0099\u0001\u0012\u0016\u0012\u0014\u0018\u000109¢\u0006\r\b>\u0012\t\b?\u0012\u0005\b\b(\u009a\u0001\u0012 \u0012\u001e\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0006\u0012\u0004\u0018\u000109\u0012\u0005\u0012\u00030\u009c\u0001\u0012\u0004\u0012\u00020\b0j\u0018\u00010jj\u0005\u0018\u0001`\u0098\u0001X\u0082\u0004¢\u0006\f\n\u0003\u0010\u009e\u0001\u0012\u0005\b\u009d\u0001\u0010|R\u0012\u0010¡\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\u001dX\u0082\u0004R\u001a\u0010¢\u0001\u001a\u0005\u0018\u00010\u009b\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u0018\u0010¥\u0001\u001a\u00030\u009b\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b¦\u0001\u0010¤\u0001R\u0018\u0010§\u0001\u001a\u00030\u009b\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b¨\u0001\u0010¤\u0001R\u0012\u0010©\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\u001dX\u0082\u0004R\u0016\u0010»\u0001\u001a\u00020\u001a8TX\u0094\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010\u001bR\u001d\u0010È\u0001\u001a\u00020\u001a8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÉ\u0001\u0010|\u001a\u0005\bÈ\u0001\u0010\u001bR\u001b\u0010Ê\u0001\u001a\u00020\u001a*\u00020\u00118BX\u0082\u0004¢\u0006\b\u001a\u0006\bÊ\u0001\u0010Ë\u0001R\u001d\u0010Ì\u0001\u001a\u00020\u001a8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÍ\u0001\u0010|\u001a\u0005\bÌ\u0001\u0010\u001bR\u001b\u0010Î\u0001\u001a\u00020\u001a*\u00020\u00118BX\u0082\u0004¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ë\u0001R\u001d\u0010Ñ\u0001\u001a\u00020\u001a8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÒ\u0001\u0010|\u001a\u0005\bÑ\u0001\u0010\u001b¨\u0006ñ\u0001"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel;", "E", "Lkotlinx/coroutines/channels/Channel;", "capacity", "", "onUndeliveredElement", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "Lkotlin/Function1;", "", "<init>", "(ILkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", "sendersAndCloseStatus", "Lkotlinx/atomicfu/AtomicLong;", "receivers", "bufferEnd", "sendersCounter", "", "getSendersCounter$kotlinx_coroutines_core", "()J", "receiversCounter", "getReceiversCounter$kotlinx_coroutines_core", "bufferEndCounter", "getBufferEndCounter", "completedExpandBuffersAndPauseFlag", "isRendezvousOrUnlimited", "", "()Z", "sendSegment", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/channels/ChannelSegment;", "receiveSegment", "bufferEndSegment", "send", "element", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedSend", "sendOnNoWaiterSuspend", "segment", "index", "s", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareSenderForSuspension", "Lkotlinx/coroutines/Waiter;", "onClosedSendOnNoWaiterSuspend", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "sendBroadcast", "sendBroadcast$kotlinx_coroutines_core", "sendImpl", "R", "waiter", "", "onRendezvousOrBuffered", "Lkotlin/Function0;", "onSuspend", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "segm", "i", "onClosed", "onNoWaiterSuspend", "Lkotlin/Function4;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "trySendDropOldest", "trySendDropOldest-JP2dKIU", "sendImplOnNoWaiter", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlinx/coroutines/Waiter;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "updateCellSend", "closed", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLjava/lang/Object;Z)I", "updateCellSendSlow", "shouldSendSuspend", "curSendersAndCloseStatus", "bufferOrRendezvousSend", "curSenders", "shouldSendSuspend$kotlinx_coroutines_core", "tryResumeReceiver", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "onReceiveEnqueued", "onReceiveDequeued", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOnNoWaiterSuspend", "r", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareReceiverForSuspension", "onClosedReceiveOnNoWaiterSuspend", "receiveCatching", "receiveCatching-JP2dKIU", "receiveCatchingOnNoWaiterSuspend", "receiveCatchingOnNoWaiterSuspend-GKJJFZk", "onClosedReceiveCatchingOnNoWaiterSuspend", "tryReceive", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "dropFirstElementUntilTheSpecifiedCellIsInTheBuffer", "globalCellIndex", "receiveImpl", "onElementRetrieved", "Lkotlin/Function3;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "receiveImplOnNoWaiter", "updateCellReceive", "updateCellReceiveSlow", "tryResumeSender", "expandBuffer", "updateCellExpandBuffer", "b", "updateCellExpandBufferSlow", "incCompletedExpandBufferAttempts", "nAttempts", "waitExpandBufferCompletion", "globalIndex", "waitExpandBufferCompletion$kotlinx_coroutines_core", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend$annotations", "()V", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "registerSelectForSend", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "onClosedSelectOnSend", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)V", "processResultSelectSend", "ignoredParam", "selectResult", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive$annotations", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "getOnReceiveCatching$annotations", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "registerSelectForReceive", "onClosedSelectOnReceive", "processResultSelectReceive", "processResultSelectReceiveOrNull", "processResultSelectReceiveCatching", "onUndeliveredElementReceiveCancellationConstructor", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "param", "internalResult", "", "Lkotlin/coroutines/CoroutineContext;", "getOnUndeliveredElementReceiveCancellationConstructor$annotations", "Lkotlin/jvm/functions/Function3;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "_closeCause", "closeCause", "getCloseCause", "()Ljava/lang/Throwable;", "sendException", "getSendException", "receiveException", "getReceiveException", "closeHandler", "onClosedIdempotent", "close", "cause", "cancel", "Lkotlinx/coroutines/CancellationException;", "Ljava/util/concurrent/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "closeOrCancelImpl", "invokeCloseHandler", "invokeOnClose", "handler", "markClosed", "markCancelled", "markCancellationStarted", "completeCloseOrCancel", "isConflatedDropOldest", "completeClose", "sendersCur", "completeCancel", "closeLinkedList", "markAllEmptyCellsAsClosed", "lastSegment", "removeUnprocessedElements", "cancelSuspendedReceiveRequests", "resumeReceiverOnClosedChannel", "resumeSenderOnCancelledChannel", "resumeWaiterOnClosedChannel", "receiver", "isClosedForSend", "isClosedForSend$annotations", "isClosedForSend0", "(J)Z", "isClosedForReceive", "isClosedForReceive$annotations", "isClosedForReceive0", "isClosed", "sendersAndCloseStatusCur", "isEmpty", "isEmpty$annotations", "hasElements", "hasElements$kotlinx_coroutines_core", "isCellNonEmpty", "findSegmentSend", "id", "startFrom", "findSegmentReceive", "findSegmentBufferEnd", "currentBufferEndCounter", "moveSegmentBufferEndToSpecifiedOrLast", "updateSendersCounterIfLower", "value", "updateReceiversCounterIfLower", "toString", "", "toStringDebug", "toStringDebug$kotlinx_coroutines_core", "checkSegmentStructureInvariants", "bindCancellationFunResult", "Lkotlin/reflect/KFunction3;", "(Lkotlin/jvm/functions/Function1;)Lkotlin/reflect/KFunction;", "onCancellationChannelResultImplDoNotCall", "context", "onCancellationChannelResultImplDoNotCall-5_sEAP8", "(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "bindCancellationFun", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Lkotlin/jvm/functions/Function3;", "onCancellationImplDoNotCall", "SendBroadcast", "BufferedChannelIterator", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class BufferedChannel<E> implements Channel<E> {
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private final int capacity;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;
    public final Function1<E, Unit> onUndeliveredElement;
    private final Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> onUndeliveredElementReceiveCancellationConstructor;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;
    private static final /* synthetic */ AtomicLongFieldUpdater sendersAndCloseStatus$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater receivers$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater bufferEnd$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd$volatile");
    private static final /* synthetic */ AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater sendSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater receiveSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater bufferEndSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closeCause$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause$volatile");
    private static final /* synthetic */ AtomicReferenceFieldUpdater closeHandler$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler$volatile");

    private final /* synthetic */ Object getAndUpdate$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, ? extends Object> function1) {
        Object obj2;
        do {
            obj2 = atomicReferenceFieldUpdater.get(obj);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, obj, obj2, function1.invoke(obj2)));
        return obj2;
    }

    private final /* synthetic */ long getBufferEnd$volatile() {
        return this.bufferEnd$volatile;
    }

    private final /* synthetic */ Object getBufferEndSegment$volatile() {
        return this.bufferEndSegment$volatile;
    }

    private final /* synthetic */ Object getCloseHandler$volatile() {
        return this.closeHandler$volatile;
    }

    private final /* synthetic */ long getCompletedExpandBuffersAndPauseFlag$volatile() {
        return this.completedExpandBuffersAndPauseFlag$volatile;
    }

    public static /* synthetic */ void getOnReceive$annotations() {
    }

    public static /* synthetic */ void getOnReceiveCatching$annotations() {
    }

    public static /* synthetic */ void getOnReceiveOrNull$annotations() {
    }

    public static /* synthetic */ void getOnSend$annotations() {
    }

    private static /* synthetic */ void getOnUndeliveredElementReceiveCancellationConstructor$annotations() {
    }

    private final /* synthetic */ Object getReceiveSegment$volatile() {
        return this.receiveSegment$volatile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater getReceiveSegment$volatile$FU() {
        return receiveSegment$volatile$FU;
    }

    private final /* synthetic */ long getReceivers$volatile() {
        return this.receivers$volatile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ AtomicLongFieldUpdater getReceivers$volatile$FU() {
        return receivers$volatile$FU;
    }

    private final /* synthetic */ Object getSendSegment$volatile() {
        return this.sendSegment$volatile;
    }

    private final /* synthetic */ long getSendersAndCloseStatus$volatile() {
        return this.sendersAndCloseStatus$volatile;
    }

    private final /* synthetic */ Object get_closeCause$volatile() {
        return this._closeCause$volatile;
    }

    public static /* synthetic */ void isClosedForReceive$annotations() {
    }

    public static /* synthetic */ void isClosedForSend$annotations() {
    }

    public static /* synthetic */ void isEmpty$annotations() {
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicLongFieldUpdater atomicLongFieldUpdater, Function1<? super Long, Unit> function1) {
        while (true) {
            function1.invoke(Long.valueOf(atomicLongFieldUpdater.get(obj)));
        }
    }

    private final /* synthetic */ void loop$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(atomicReferenceFieldUpdater.get(obj));
        }
    }

    private final /* synthetic */ void setBufferEnd$volatile(long j) {
        this.bufferEnd$volatile = j;
    }

    private final /* synthetic */ void setBufferEndSegment$volatile(Object obj) {
        this.bufferEndSegment$volatile = obj;
    }

    private final /* synthetic */ void setCloseHandler$volatile(Object obj) {
        this.closeHandler$volatile = obj;
    }

    private final /* synthetic */ void setCompletedExpandBuffersAndPauseFlag$volatile(long j) {
        this.completedExpandBuffersAndPauseFlag$volatile = j;
    }

    private final /* synthetic */ void setReceiveSegment$volatile(Object obj) {
        this.receiveSegment$volatile = obj;
    }

    private final /* synthetic */ void setReceivers$volatile(long j) {
        this.receivers$volatile = j;
    }

    private final /* synthetic */ void setSendSegment$volatile(Object obj) {
        this.sendSegment$volatile = obj;
    }

    private final /* synthetic */ void setSendersAndCloseStatus$volatile(long j) {
        this.sendersAndCloseStatus$volatile = j;
    }

    private final /* synthetic */ void set_closeCause$volatile(Object obj) {
        this._closeCause$volatile = obj;
    }

    private final /* synthetic */ void update$atomicfu(Object obj, AtomicLongFieldUpdater atomicLongFieldUpdater, Function1<? super Long, Long> function1) {
        long j;
        do {
            j = atomicLongFieldUpdater.get(obj);
        } while (!atomicLongFieldUpdater.compareAndSet(obj, j, function1.invoke(Long.valueOf(j)).longValue()));
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation<? super E> continuation) {
        return receive$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public Object mo8900receiveCatchingJP2dKIU(Continuation<? super ChannelResult<? extends E>> continuation) {
        return m8898receiveCatchingJP2dKIU$suspendImpl(this, continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(E e, Continuation<? super Unit> continuation) {
        return send$suspendImpl(this, e, continuation);
    }

    public Object sendBroadcast$kotlinx_coroutines_core(E e, Continuation<? super Boolean> continuation) {
        return sendBroadcast$suspendImpl(this, e, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BufferedChannel(int capacity, Function1<? super E, Unit> function1) {
        ChannelSegment channelSegment;
        this.capacity = capacity;
        this.onUndeliveredElement = function1;
        if (this.capacity >= 0) {
            this.bufferEnd$volatile = BufferedChannelKt.initialBufferEnd(this.capacity);
            this.completedExpandBuffersAndPauseFlag$volatile = getBufferEndCounter();
            ChannelSegment firstSegment = new ChannelSegment(0L, null, this, 3);
            this.sendSegment$volatile = firstSegment;
            this.receiveSegment$volatile = firstSegment;
            if (isRendezvousOrUnlimited()) {
                channelSegment = BufferedChannelKt.NULL_SEGMENT;
                Intrinsics.checkNotNull(channelSegment, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
            } else {
                channelSegment = firstSegment;
            }
            this.bufferEndSegment$volatile = channelSegment;
            this.onUndeliveredElementReceiveCancellationConstructor = this.onUndeliveredElement != null ? new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return BufferedChannel.onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56(this.f$0, (SelectInstance) obj, obj2, obj3);
                }
            } : null;
            this._closeCause$volatile = BufferedChannelKt.NO_CLOSE_CAUSE;
            return;
        }
        throw new IllegalArgumentException(("Invalid channel capacity: " + this.capacity + ", should be >=0").toString());
    }

    public /* synthetic */ BufferedChannel(int i, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return Channel.DefaultImpls.offer(this, e);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public E poll() {
        return (E) Channel.DefaultImpls.poll(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public Object receiveOrNull(Continuation<? super E> continuation) {
        return Channel.DefaultImpls.receiveOrNull(this, continuation);
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        long $this$sendersCounter$iv = sendersAndCloseStatus$volatile$FU.get(this);
        return $this$sendersCounter$iv & 1152921504606846975L;
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return receivers$volatile$FU.get(this);
    }

    private final long getBufferEndCounter() {
        return bufferEnd$volatile$FU.get(this);
    }

    private final boolean isRendezvousOrUnlimited() {
        long it = getBufferEndCounter();
        return it == 0 || it == Long.MAX_VALUE;
    }

    static /* synthetic */ <E> Object send$suspendImpl(BufferedChannel<E> bufferedChannel, E e, Continuation<? super Unit> continuation) throws Throwable {
        ChannelSegment<E> channelSegment;
        ChannelSegment<E> channelSegment2 = (ChannelSegment) sendSegment$volatile$FU.get(bufferedChannel);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(bufferedChannel);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = bufferedChannel.isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i$iv = (int) ($this$sendersCounter$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (channelSegment2.id != id$iv) {
                ChannelSegment<E> channelSegmentFindSegmentSend = bufferedChannel.findSegmentSend(id$iv, channelSegment2);
                if (channelSegmentFindSegmentSend != null) {
                    channelSegment = channelSegmentFindSegmentSend;
                } else if (closed$iv) {
                    Object objOnClosedSend = bufferedChannel.onClosedSend(e, continuation);
                    if (objOnClosedSend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return objOnClosedSend;
                    }
                }
            } else {
                channelSegment = channelSegment2;
            }
            switch (bufferedChannel.updateCellSend(channelSegment, i$iv, e, $this$sendersCounter$iv$iv, null, closed$iv)) {
                case 0:
                    channelSegment.cleanPrev();
                    break;
                case 1:
                    break;
                case 2:
                    if (closed$iv) {
                        channelSegment.onSlotCleaned();
                        Object objOnClosedSend2 = bufferedChannel.onClosedSend(e, continuation);
                        if (objOnClosedSend2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return objOnClosedSend2;
                        }
                    } else if (DebugKt.getASSERTIONS_ENABLED()) {
                        throw new AssertionError();
                    }
                case 3:
                    Object objSendOnNoWaiterSuspend = bufferedChannel.sendOnNoWaiterSuspend(channelSegment, i$iv, e, $this$sendersCounter$iv$iv, continuation);
                    if (objSendOnNoWaiterSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return objSendOnNoWaiterSuspend;
                    }
                    break;
                case 4:
                    if ($this$sendersCounter$iv$iv < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        channelSegment.cleanPrev();
                    }
                    Object objOnClosedSend3 = bufferedChannel.onClosedSend(e, continuation);
                    if (objOnClosedSend3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return objOnClosedSend3;
                    }
                    break;
                case 5:
                    channelSegment.cleanPrev();
                    channelSegment2 = channelSegment;
                    break;
                default:
                    channelSegment2 = channelSegment;
                    break;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object onClosedSend(E e, Continuation<? super Unit> continuation) {
        Throwable thRecoverFromStackFrame;
        UndeliveredElementException it;
        UndeliveredElementException undeliveredElementExceptionRecoverFromStackFrame;
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl continuation2 = cancellable$iv;
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, e, null, 2, null)) != null) {
            ExceptionsKt.addSuppressed(it, getSendException());
            CancellableContinuationImpl $this$resumeWithStackTrace$iv = continuation2;
            Result.Companion companion = Result.INSTANCE;
            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv instanceof CoroutineStackFrame)) {
                undeliveredElementExceptionRecoverFromStackFrame = StackTraceRecoveryKt.recoverFromStackFrame(it, $this$resumeWithStackTrace$iv);
            } else {
                undeliveredElementExceptionRecoverFromStackFrame = it;
            }
            $this$resumeWithStackTrace$iv.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(undeliveredElementExceptionRecoverFromStackFrame)));
        } else {
            CancellableContinuationImpl $this$resumeWithStackTrace$iv2 = continuation2;
            Throwable exception$iv = getSendException();
            Result.Companion companion2 = Result.INSTANCE;
            if (DebugKt.getRECOVER_STACK_TRACES() && ($this$resumeWithStackTrace$iv2 instanceof CoroutineStackFrame)) {
                thRecoverFromStackFrame = StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, $this$resumeWithStackTrace$iv2);
            } else {
                thRecoverFromStackFrame = exception$iv;
            }
            $this$resumeWithStackTrace$iv2.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(thRecoverFromStackFrame)));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:65:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:68:0x01cc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:69:0x01cd  */
    public final Object sendOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, E e, long s, Continuation<? super Unit> continuation) throws Throwable {
        ChannelSegment segment$iv$iv;
        Object result;
        Continuation<? super Unit> continuation2 = continuation;
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
        try {
            try {
                try {
                    try {
                        switch (updateCellSend(channelSegment, index, e, s, cancellable$iv, false)) {
                            case 0:
                                channelSegment.cleanPrev();
                                Result.Companion companion = Result.INSTANCE;
                                cancellable$iv.resumeWith(Result.m7388constructorimpl(Unit.INSTANCE));
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(continuation);
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return result;
                                }
                                return Unit.INSTANCE;
                            case 1:
                                Result.Companion companion2 = Result.INSTANCE;
                                cancellable$iv.resumeWith(Result.m7388constructorimpl(Unit.INSTANCE));
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(continuation);
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return result;
                                }
                                return Unit.INSTANCE;
                            case 2:
                                prepareSenderForSuspension(cancellable$iv, channelSegment, index);
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(continuation);
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return result;
                                }
                                return Unit.INSTANCE;
                            case 3:
                            default:
                                throw new IllegalStateException("unexpected".toString());
                            case 4:
                                if (s < getReceiversCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                result = cancellable$iv.getResult();
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    DebugProbesKt.probeCoroutineSuspended(continuation);
                                }
                                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                    return result;
                                }
                                return Unit.INSTANCE;
                            case 5:
                                try {
                                    channelSegment.cleanPrev();
                                    ChannelSegment segment$iv$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
                                    while (true) {
                                        long $this$sendersCounter$iv$iv$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
                                        long $this$sendersCounter$iv$iv$iv2 = $this$sendersCounter$iv$iv$iv & 1152921504606846975L;
                                        boolean closed$iv$iv = isClosedForSend0($this$sendersCounter$iv$iv$iv);
                                        long id$iv$iv = $this$sendersCounter$iv$iv$iv2 / ((long) BufferedChannelKt.SEGMENT_SIZE);
                                        int i$iv$iv = (int) ($this$sendersCounter$iv$iv$iv2 % ((long) BufferedChannelKt.SEGMENT_SIZE));
                                        if (segment$iv$iv2.id != id$iv$iv) {
                                            try {
                                                ChannelSegment segment$iv$iv3 = findSegmentSend(id$iv$iv, segment$iv$iv2);
                                                if (segment$iv$iv3 != null) {
                                                    segment$iv$iv = segment$iv$iv3;
                                                } else if (closed$iv$iv) {
                                                    onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                }
                                            } catch (Throwable th) {
                                                e$iv = th;
                                                cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                                throw e$iv;
                                            }
                                        } else {
                                            segment$iv$iv = segment$iv$iv2;
                                        }
                                        ChannelSegment segment$iv$iv4 = segment$iv$iv;
                                        Continuation<? super Unit> continuation3 = continuation2;
                                        switch (updateCellSend(segment$iv$iv, i$iv$iv, e, $this$sendersCounter$iv$iv$iv2, cancellable$iv, closed$iv$iv)) {
                                            case 0:
                                                segment$iv$iv4.cleanPrev();
                                                Result.Companion companion3 = Result.INSTANCE;
                                                cancellable$iv.resumeWith(Result.m7388constructorimpl(Unit.INSTANCE));
                                                break;
                                            case 1:
                                                Result.Companion companion4 = Result.INSTANCE;
                                                cancellable$iv.resumeWith(Result.m7388constructorimpl(Unit.INSTANCE));
                                                break;
                                            case 2:
                                                if (!closed$iv$iv) {
                                                    CancellableContinuationImpl cancellableContinuationImpl = cancellable$iv instanceof Waiter ? cancellable$iv : null;
                                                    if (cancellableContinuationImpl != null) {
                                                        prepareSenderForSuspension(cancellableContinuationImpl, segment$iv$iv4, i$iv$iv);
                                                    }
                                                } else {
                                                    segment$iv$iv4.onSlotCleaned();
                                                    onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                }
                                                break;
                                            case 3:
                                                throw new IllegalStateException("unexpected".toString());
                                            case 4:
                                                if ($this$sendersCounter$iv$iv$iv2 < getReceiversCounter$kotlinx_coroutines_core()) {
                                                    segment$iv$iv4.cleanPrev();
                                                }
                                                onClosedSendOnNoWaiterSuspend(e, cancellable$iv);
                                                break;
                                            case 5:
                                                segment$iv$iv4.cleanPrev();
                                                segment$iv$iv2 = segment$iv$iv4;
                                                continuation2 = continuation3;
                                                break;
                                            default:
                                                segment$iv$iv2 = segment$iv$iv4;
                                                continuation2 = continuation3;
                                                break;
                                        }
                                    }
                                    result = cancellable$iv.getResult();
                                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                        DebugProbesKt.probeCoroutineSuspended(continuation);
                                    }
                                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                        return result;
                                    }
                                    return Unit.INSTANCE;
                                } catch (Throwable th2) {
                                    e$iv = th2;
                                }
                                break;
                        }
                    } catch (Throwable th3) {
                        e$iv = th3;
                    }
                } catch (Throwable th4) {
                    e$iv = th4;
                }
            } catch (Throwable th5) {
                e$iv = th5;
            }
        } catch (Throwable th6) {
            e$iv = th6;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareSenderForSuspension(Waiter $this$prepareSenderForSuspension, ChannelSegment<E> channelSegment, int index) {
        $this$prepareSenderForSuspension.invokeOnCancellation(channelSegment, BufferedChannelKt.SEGMENT_SIZE + index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedSendOnNoWaiterSuspend(E element, CancellableContinuation<? super Unit> cont) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, element, cont.getContext());
        }
        CancellableContinuation<? super Unit> cancellableContinuation = cont;
        Throwable exception$iv = getSendException();
        if (DebugKt.getRECOVER_STACK_TRACES() && (cont instanceof CoroutineStackFrame)) {
            exception$iv = StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) cont);
        }
        Result.Companion companion = Result.INSTANCE;
        cancellableContinuation.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(exception$iv)));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo8893trySendJP2dKIU(E element) {
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2;
        if (!shouldSendSuspend(sendersAndCloseStatus$volatile$FU.get(this))) {
            Object waiter$iv = BufferedChannelKt.INTERRUPTED_SEND;
            int $i$f$sendImpl = 0;
            ChannelSegment segment$iv3 = (ChannelSegment) sendSegment$volatile$FU.get(this);
            while (true) {
                long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
                long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
                boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
                long id$iv = $this$sendersCounter$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                int i$iv = (int) ($this$sendersCounter$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                if (segment$iv3.id != id$iv) {
                    ChannelSegment segment$iv4 = findSegmentSend(id$iv, segment$iv3);
                    if (segment$iv4 != null) {
                        segment$iv = segment$iv4;
                    } else if (closed$iv) {
                        return ChannelResult.INSTANCE.m8921closedJP2dKIU(getSendException());
                    }
                } else {
                    segment$iv = segment$iv3;
                }
                ChannelSegment segment$iv5 = segment$iv;
                int $i$f$sendImpl2 = $i$f$sendImpl;
                switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                    case 0:
                        segment$iv5.cleanPrev();
                        return ChannelResult.INSTANCE.m8923successJP2dKIU(Unit.INSTANCE);
                    case 1:
                        return ChannelResult.INSTANCE.m8923successJP2dKIU(Unit.INSTANCE);
                    case 2:
                        if (closed$iv) {
                            segment$iv5.onSlotCleaned();
                            return ChannelResult.INSTANCE.m8921closedJP2dKIU(getSendException());
                        }
                        Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                        if (waiter != null) {
                            segment$iv2 = segment$iv5;
                            prepareSenderForSuspension(waiter, segment$iv2, i$iv);
                        } else {
                            segment$iv2 = segment$iv5;
                        }
                        ChannelSegment segm = segment$iv2;
                        segm.onSlotCleaned();
                        return ChannelResult.INSTANCE.m8922failurePtdJZtk();
                    case 3:
                        throw new IllegalStateException("unexpected".toString());
                    case 4:
                        if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                            segment$iv5.cleanPrev();
                        }
                        return ChannelResult.INSTANCE.m8921closedJP2dKIU(getSendException());
                    case 5:
                        segment$iv5.cleanPrev();
                    default:
                        segment$iv3 = segment$iv5;
                        $i$f$sendImpl = $i$f$sendImpl2;
                        break;
                }
            }
        } else {
            return ChannelResult.INSTANCE.m8922failurePtdJZtk();
        }
    }

    static /* synthetic */ <E> Object sendBroadcast$suspendImpl(BufferedChannel<E> bufferedChannel, E e, Continuation<? super Boolean> continuation) {
        ChannelSegment segment$iv;
        int $i$f$suspendCancellableCoroutine = 0;
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        if (!(bufferedChannel.onUndeliveredElement == null)) {
            throw new IllegalStateException("the `onUndeliveredElement` feature is unsupported for `sendBroadcast(e)`".toString());
        }
        Object waiter$iv = new SendBroadcast(cont);
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(bufferedChannel);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(bufferedChannel);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = bufferedChannel.isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            cancellable$iv = cancellable$iv;
            int i$iv = (int) ($this$sendersCounter$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = bufferedChannel.findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    Result.Companion companion = Result.INSTANCE;
                    cont.resumeWith(Result.m7388constructorimpl(Boxing.boxBoolean(false)));
                }
            } else {
                segment$iv = segment$iv2;
            }
            int $i$f$suspendCancellableCoroutine2 = $i$f$suspendCancellableCoroutine;
            Object waiter$iv2 = waiter$iv;
            switch (bufferedChannel.updateCellSend(segment$iv, i$iv, e, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv.cleanPrev();
                    Result.Companion companion2 = Result.INSTANCE;
                    cont.resumeWith(Result.m7388constructorimpl(Boxing.boxBoolean(true)));
                    break;
                case 1:
                    Result.Companion companion3 = Result.INSTANCE;
                    cont.resumeWith(Result.m7388constructorimpl(Boxing.boxBoolean(true)));
                    break;
                case 2:
                    if (closed$iv) {
                        segment$iv.onSlotCleaned();
                        Result.Companion companion4 = Result.INSTANCE;
                        cont.resumeWith(Result.m7388constructorimpl(Boxing.boxBoolean(false)));
                    } else {
                        SendBroadcast sendBroadcast = waiter$iv2 instanceof Waiter ? (Waiter) waiter$iv2 : null;
                        if (sendBroadcast != null) {
                            bufferedChannel.prepareSenderForSuspension(sendBroadcast, segment$iv, i$iv);
                        }
                    }
                    break;
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < bufferedChannel.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv.cleanPrev();
                    }
                    Result.Companion companion5 = Result.INSTANCE;
                    cont.resumeWith(Result.m7388constructorimpl(Boxing.boxBoolean(false)));
                    break;
                case 5:
                    segment$iv.cleanPrev();
                default:
                    waiter$iv = waiter$iv2;
                    segment$iv2 = segment$iv;
                    $i$f$suspendCancellableCoroutine = $i$f$suspendCancellableCoroutine2;
                    break;
            }
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: compiled from: BufferedChannel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$SendBroadcast;", "Lkotlinx/coroutines/Waiter;", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "<init>", "(Lkotlinx/coroutines/CancellableContinuation;)V", "getCont", "()Lkotlinx/coroutines/CancellableContinuation;", "invokeOnCancellation", "", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class SendBroadcast implements Waiter {
        private final /* synthetic */ CancellableContinuationImpl<Boolean> $$delegate_0;
        private final CancellableContinuation<Boolean> cont;

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> segment, int index) {
            this.$$delegate_0.invokeOnCancellation(segment, index);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SendBroadcast(CancellableContinuation<? super Boolean> cancellableContinuation) {
            Intrinsics.checkNotNull(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlin.Boolean>");
            this.$$delegate_0 = (CancellableContinuationImpl) cancellableContinuation;
            this.cont = cancellableContinuation;
        }

        public final CancellableContinuation<Boolean> getCont() {
            return this.cont;
        }
    }

    static /* synthetic */ Object sendImpl$default(BufferedChannel $this, Object element, Object waiter, Function0 onRendezvousOrBuffered, Function2 onSuspend, Function0 onClosed, Function4 onNoWaiterSuspend, int i, Object obj) {
        Function4 onNoWaiterSuspend2;
        ChannelSegment segment;
        int i2;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendImpl");
        }
        if ((i & 32) == 0) {
            onNoWaiterSuspend2 = onNoWaiterSuspend;
        } else {
            onNoWaiterSuspend2 = new Function4() { // from class: kotlinx.coroutines.channels.BufferedChannel.sendImpl.1
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3, Object p4) {
                    return invoke((ChannelSegment<Object>) p1, ((Number) p2).intValue(), p3, ((Number) p4).longValue());
                }

                public final Void invoke(ChannelSegment<E> channelSegment, int i3, E e, long j) {
                    throw new IllegalStateException("unexpected".toString());
                }
            };
        }
        ChannelSegment segment2 = (ChannelSegment) sendSegment$volatile$FU.get($this);
        while (true) {
            long sendersAndCloseStatusCur = sendersAndCloseStatus$volatile$FU.getAndIncrement($this);
            long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
            boolean closed = $this.isClosedForSend0(sendersAndCloseStatusCur);
            long id = $this$sendersCounter$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i3 = (int) ($this$sendersCounter$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment2.id != id) {
                ChannelSegment segment3 = $this.findSegmentSend(id, segment2);
                if (segment3 != null) {
                    segment = segment3;
                } else if (closed) {
                    return onClosed.invoke();
                }
            } else {
                segment = segment2;
            }
            ChannelSegment segment4 = segment;
            switch ($this.updateCellSend(segment, i3, element, $this$sendersCounter$iv, waiter, closed)) {
                case 0:
                    segment4.cleanPrev();
                    return onRendezvousOrBuffered.invoke();
                case 1:
                    return onRendezvousOrBuffered.invoke();
                case 2:
                    if (closed) {
                        segment4.onSlotCleaned();
                        return onClosed.invoke();
                    }
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        i2 = i3;
                        $this.prepareSenderForSuspension(waiter2, segment4, i2);
                    } else {
                        i2 = i3;
                    }
                    return onSuspend.invoke(segment4, Integer.valueOf(i2));
                case 3:
                    return onNoWaiterSuspend2.invoke(segment4, Integer.valueOf(i3), element, Long.valueOf($this$sendersCounter$iv));
                case 4:
                    if ($this$sendersCounter$iv < $this.getReceiversCounter$kotlinx_coroutines_core()) {
                        segment4.cleanPrev();
                    }
                    return onClosed.invoke();
                case 5:
                    segment4.cleanPrev();
                    break;
            }
            segment2 = segment4;
        }
    }

    private final <R> R sendImpl(E element, Object waiter, Function0<? extends R> onRendezvousOrBuffered, Function2<? super ChannelSegment<E>, ? super Integer, ? extends R> onSuspend, Function0<? extends R> onClosed, Function4<? super ChannelSegment<E>, ? super Integer, ? super E, ? super Long, ? extends R> onNoWaiterSuspend) {
        ChannelSegment segment;
        int i;
        ChannelSegment segment2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
            boolean closed = isClosedForSend0(sendersAndCloseStatusCur);
            long id = $this$sendersCounter$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i2 = (int) ($this$sendersCounter$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment2.id != id) {
                ChannelSegment segment3 = findSegmentSend(id, segment2);
                if (segment3 != null) {
                    segment = segment3;
                } else if (closed) {
                    return onClosed.invoke();
                }
            } else {
                segment = segment2;
            }
            ChannelSegment segment4 = segment;
            switch (updateCellSend(segment, i2, element, $this$sendersCounter$iv, waiter, closed)) {
                case 0:
                    segment4.cleanPrev();
                    return onRendezvousOrBuffered.invoke();
                case 1:
                    return onRendezvousOrBuffered.invoke();
                case 2:
                    if (closed) {
                        segment4.onSlotCleaned();
                        return onClosed.invoke();
                    }
                    Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                    if (waiter2 != null) {
                        i = i2;
                        prepareSenderForSuspension(waiter2, segment4, i);
                    } else {
                        i = i2;
                    }
                    return onSuspend.invoke(segment4, Integer.valueOf(i));
                case 3:
                    return onNoWaiterSuspend.invoke(segment4, Integer.valueOf(i2), element, Long.valueOf($this$sendersCounter$iv));
                case 4:
                    if ($this$sendersCounter$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment4.cleanPrev();
                    }
                    return onClosed.invoke();
                case 5:
                    segment4.cleanPrev();
                    break;
            }
            segment2 = segment4;
        }
    }

    /* JADX INFO: renamed from: trySendDropOldest-JP2dKIU, reason: not valid java name */
    protected final Object m8902trySendDropOldestJP2dKIU(E element) {
        ChannelSegment segment$iv;
        ChannelSegment segment$iv2;
        Object waiter$iv = BufferedChannelKt.BUFFERED;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv3 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i$iv = (int) ($this$sendersCounter$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment$iv3.id != id$iv) {
                ChannelSegment segment$iv4 = findSegmentSend(id$iv, segment$iv3);
                if (segment$iv4 != null) {
                    segment$iv = segment$iv4;
                } else if (closed$iv) {
                    return ChannelResult.INSTANCE.m8921closedJP2dKIU(getSendException());
                }
            } else {
                segment$iv = segment$iv3;
            }
            ChannelSegment segment$iv5 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, waiter$iv, closed$iv)) {
                case 0:
                    segment$iv5.cleanPrev();
                    return ChannelResult.INSTANCE.m8923successJP2dKIU(Unit.INSTANCE);
                case 1:
                    return ChannelResult.INSTANCE.m8923successJP2dKIU(Unit.INSTANCE);
                case 2:
                    if (closed$iv) {
                        segment$iv5.onSlotCleaned();
                        return ChannelResult.INSTANCE.m8921closedJP2dKIU(getSendException());
                    }
                    Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                    if (waiter != null) {
                        segment$iv2 = segment$iv5;
                        prepareSenderForSuspension(waiter, segment$iv2, i$iv);
                    } else {
                        segment$iv2 = segment$iv5;
                    }
                    ChannelSegment segm = segment$iv2;
                    dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((segm.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) i$iv));
                    return ChannelResult.INSTANCE.m8923successJP2dKIU(Unit.INSTANCE);
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv5.cleanPrev();
                    }
                    return ChannelResult.INSTANCE.m8921closedJP2dKIU(getSendException());
                case 5:
                    segment$iv5.cleanPrev();
                default:
                    segment$iv3 = segment$iv5;
                    $i$f$sendImpl = $i$f$sendImpl2;
                    break;
            }
        }
    }

    private final void sendImplOnNoWaiter(ChannelSegment<E> segment, int index, E element, long s, Waiter waiter, Function0<Unit> onRendezvousOrBuffered, Function0<Unit> onClosed) {
        ChannelSegment segment$iv;
        Unit unitInvoke;
        int $i$f$sendImplOnNoWaiter = 0;
        switch (updateCellSend(segment, index, element, s, waiter, false)) {
            case 0:
                segment.cleanPrev();
                onRendezvousOrBuffered.invoke();
                return;
            case 1:
                onRendezvousOrBuffered.invoke();
                return;
            case 2:
                prepareSenderForSuspension(waiter, segment, index);
                return;
            case 3:
            default:
                throw new IllegalStateException("unexpected".toString());
            case 4:
                if (s < getReceiversCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                onClosed.invoke();
                return;
            case 5:
                segment.cleanPrev();
                ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
                while (true) {
                    long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
                    long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
                    boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
                    long id$iv = $this$sendersCounter$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                    int i$iv = (int) ($this$sendersCounter$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                    if (segment$iv2.id != id$iv) {
                        ChannelSegment segment$iv3 = findSegmentSend(id$iv, segment$iv2);
                        if (segment$iv3 != null) {
                            segment$iv = segment$iv3;
                        } else if (closed$iv) {
                            unitInvoke = onClosed.invoke();
                        }
                    } else {
                        segment$iv = segment$iv2;
                    }
                    ChannelSegment segment$iv4 = segment$iv;
                    int $i$f$sendImplOnNoWaiter2 = $i$f$sendImplOnNoWaiter;
                    switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, waiter, closed$iv)) {
                        case 0:
                            segment$iv4.cleanPrev();
                            unitInvoke = onRendezvousOrBuffered.invoke();
                            break;
                        case 1:
                            unitInvoke = onRendezvousOrBuffered.invoke();
                            break;
                        case 2:
                            if (!closed$iv) {
                                Waiter waiter2 = waiter instanceof Waiter ? waiter : null;
                                if (waiter2 != null) {
                                    prepareSenderForSuspension(waiter2, segment$iv4, i$iv);
                                }
                                unitInvoke = Unit.INSTANCE;
                            } else {
                                segment$iv4.onSlotCleaned();
                                unitInvoke = onClosed.invoke();
                            }
                            break;
                        case 3:
                            throw new IllegalStateException("unexpected".toString());
                        case 4:
                            if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                                segment$iv4.cleanPrev();
                            }
                            unitInvoke = onClosed.invoke();
                            break;
                        case 5:
                            segment$iv4.cleanPrev();
                        default:
                            segment$iv2 = segment$iv4;
                            $i$f$sendImplOnNoWaiter = $i$f$sendImplOnNoWaiter2;
                            break;
                    }
                }
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int updateCellSend(ChannelSegment<E> segment, int index, E element, long s, Object waiter, boolean closed) {
        segment.storeElement$kotlinx_coroutines_core(index, element);
        if (closed) {
            return updateCellSendSlow(segment, index, element, s, waiter, closed);
        }
        Object state = segment.getState$kotlinx_coroutines_core(index);
        if (state == null) {
            if (bufferOrRendezvousSend(s)) {
                if (segment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else {
                if (waiter == null) {
                    return 3;
                }
                if (segment.casState$kotlinx_coroutines_core(index, null, waiter)) {
                    return 2;
                }
            }
        } else if (state instanceof Waiter) {
            segment.cleanElement$kotlinx_coroutines_core(index);
            if (tryResumeReceiver(state, element)) {
                segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.DONE_RCV);
                onReceiveDequeued();
                return 0;
            }
            if (segment.getAndSetState$kotlinx_coroutines_core(index, BufferedChannelKt.INTERRUPTED_RCV) != BufferedChannelKt.INTERRUPTED_RCV) {
                segment.onCancelledRequest(index, true);
            }
            return 5;
        }
        return updateCellSendSlow(segment, index, element, s, waiter, closed);
    }

    private final int updateCellSendSlow(ChannelSegment<E> segment, int index, E element, long s, Object waiter, boolean closed) {
        while (true) {
            Object state = segment.getState$kotlinx_coroutines_core(index);
            if (state != null) {
                if (state != BufferedChannelKt.IN_BUFFER) {
                    if (state != BufferedChannelKt.INTERRUPTED_RCV) {
                        if (state == BufferedChannelKt.POISONED) {
                            segment.cleanElement$kotlinx_coroutines_core(index);
                            return 5;
                        }
                        if (state == BufferedChannelKt.getCHANNEL_CLOSED()) {
                            segment.cleanElement$kotlinx_coroutines_core(index);
                            completeCloseOrCancel();
                            return 4;
                        }
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if ((((state instanceof Waiter) || (state instanceof WaiterEB)) ? 1 : 0) == 0) {
                                throw new AssertionError();
                            }
                        }
                        segment.cleanElement$kotlinx_coroutines_core(index);
                        Object receiver = state instanceof WaiterEB ? ((WaiterEB) state).waiter : state;
                        if (tryResumeReceiver(receiver, element)) {
                            segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.DONE_RCV);
                            onReceiveDequeued();
                            return 0;
                        }
                        if (segment.getAndSetState$kotlinx_coroutines_core(index, BufferedChannelKt.INTERRUPTED_RCV) != BufferedChannelKt.INTERRUPTED_RCV) {
                            segment.onCancelledRequest(index, true);
                        }
                        return 5;
                    }
                    segment.cleanElement$kotlinx_coroutines_core(index);
                    return 5;
                }
                if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (bufferOrRendezvousSend(s) && !closed) {
                if (segment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.BUFFERED)) {
                    return 1;
                }
            } else if (closed) {
                if (segment.casState$kotlinx_coroutines_core(index, null, BufferedChannelKt.INTERRUPTED_SEND)) {
                    segment.onCancelledRequest(index, false);
                    return 4;
                }
            } else {
                if (waiter == null) {
                    return 3;
                }
                if (segment.casState$kotlinx_coroutines_core(index, null, waiter)) {
                    return 2;
                }
            }
        }
    }

    private final boolean shouldSendSuspend(long curSendersAndCloseStatus) {
        if (isClosedForSend0(curSendersAndCloseStatus)) {
            return false;
        }
        long $this$sendersCounter$iv = curSendersAndCloseStatus & 1152921504606846975L;
        return !bufferOrRendezvousSend($this$sendersCounter$iv);
    }

    private final boolean bufferOrRendezvousSend(long curSenders) {
        return curSenders < getBufferEndCounter() || curSenders < getReceiversCounter$kotlinx_coroutines_core() + ((long) this.capacity);
    }

    public boolean shouldSendSuspend$kotlinx_coroutines_core() {
        return shouldSendSuspend(sendersAndCloseStatus$volatile$FU.get(this));
    }

    private final boolean tryResumeReceiver(Object $this$tryResumeReceiver, E e) {
        if ($this$tryResumeReceiver instanceof SelectInstance) {
            return ((SelectInstance) $this$tryResumeReceiver).trySelect(this, e);
        }
        if ($this$tryResumeReceiver instanceof ReceiveCatching) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = ((ReceiveCatching) $this$tryResumeReceiver).cont;
            ChannelResult channelResultM8908boximpl = ChannelResult.m8908boximpl(ChannelResult.INSTANCE.m8923successJP2dKIU(e));
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            return BufferedChannelKt.tryResume0(cancellableContinuationImpl, channelResultM8908boximpl, (Function3) (function1 != null ? bindCancellationFunResult(function1) : null));
        }
        if ($this$tryResumeReceiver instanceof BufferedChannelIterator) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.channels.BufferedChannel.BufferedChannelIterator<E of kotlinx.coroutines.channels.BufferedChannel>");
            return ((BufferedChannelIterator) $this$tryResumeReceiver).tryResumeHasNext(e);
        }
        if ($this$tryResumeReceiver instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeReceiver, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) $this$tryResumeReceiver;
            Function1<E, Unit> function2 = this.onUndeliveredElement;
            return BufferedChannelKt.tryResume0(cancellableContinuation, e, (Function3) (function2 != null ? bindCancellationFun(function2) : null));
        }
        throw new IllegalStateException(("Unexpected receiver type: " + $this$tryResumeReceiver).toString());
    }

    protected void onReceiveEnqueued() {
    }

    protected void onReceiveDequeued() {
    }

    static /* synthetic */ <E> Object receive$suspendImpl(BufferedChannel<E> bufferedChannel, Continuation<? super E> continuation) throws Throwable {
        ChannelSegment<E> channelSegment;
        ChannelSegment<E> channelSegment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(bufferedChannel);
        while (!bufferedChannel.isClosedForReceive()) {
            long r$iv = getReceivers$volatile$FU().getAndIncrement(bufferedChannel);
            long id$iv = r$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i$iv = (int) (r$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (channelSegment2.id != id$iv) {
                ChannelSegment<E> channelSegmentFindSegmentReceive = bufferedChannel.findSegmentReceive(id$iv, channelSegment2);
                if (channelSegmentFindSegmentReceive == null) {
                    continue;
                } else {
                    channelSegment = channelSegmentFindSegmentReceive;
                }
            } else {
                channelSegment = channelSegment2;
            }
            Object updCellResult$iv = bufferedChannel.updateCellReceive(channelSegment, i$iv, r$iv, null);
            if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                if (updCellResult$iv != BufferedChannelKt.FAILED) {
                    if (updCellResult$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        return bufferedChannel.receiveOnNoWaiterSuspend(channelSegment, i$iv, r$iv, continuation);
                    }
                    channelSegment.cleanPrev();
                    return updCellResult$iv;
                }
                if (r$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
                channelSegment2 = channelSegment;
            } else {
                throw new IllegalStateException("unexpected".toString());
            }
        }
        throw StackTraceRecoveryKt.recoverStackTrace(bufferedChannel.getReceiveException());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v20, types: [kotlinx.coroutines.Waiter] */
    public final Object receiveOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, long r, Continuation<? super E> continuation) throws Throwable {
        ChannelSegment segment$iv$iv;
        int $i$f$suspendCancellableCoroutineReusable = 0;
        Continuation<? super E> continuation2 = continuation;
        int i = 0;
        CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
        try {
            Object updCellResult$iv = updateCellReceive(channelSegment, index, r, cancellable$iv);
            try {
                if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                    try {
                        if (updCellResult$iv != BufferedChannelKt.FAILED) {
                            channelSegment.cleanPrev();
                            Function1<E, Unit> function1 = this.onUndeliveredElement;
                            cancellable$iv.resume(updCellResult$iv, (Function3<? super Throwable, ? super Object, ? super CoroutineContext, Unit>) (function1 != null ? bindCancellationFun(function1) : null));
                        } else {
                            if (r < getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment.cleanPrev();
                            }
                            ChannelSegment segment$iv$iv2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
                            while (true) {
                                if (isClosedForReceive()) {
                                    onClosedReceiveOnNoWaiterSuspend(cancellable$iv);
                                    break;
                                }
                                long r$iv$iv = getReceivers$volatile$FU().getAndIncrement(this);
                                long id$iv$iv = r$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                                $i$f$suspendCancellableCoroutineReusable = $i$f$suspendCancellableCoroutineReusable;
                                continuation2 = continuation2;
                                try {
                                    int i$iv$iv = (int) (r$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                                    i = i;
                                    if (segment$iv$iv2.id != id$iv$iv) {
                                        segment$iv$iv = findSegmentReceive(id$iv$iv, segment$iv$iv2);
                                        if (segment$iv$iv == null) {
                                        }
                                    } else {
                                        segment$iv$iv = segment$iv$iv2;
                                    }
                                    Object updCellResult$iv$iv = updateCellReceive(segment$iv$iv, i$iv$iv, r$iv$iv, cancellable$iv);
                                    if (updCellResult$iv$iv != BufferedChannelKt.SUSPEND) {
                                        if (updCellResult$iv$iv == BufferedChannelKt.FAILED) {
                                            if (r$iv$iv < getSendersCounter$kotlinx_coroutines_core()) {
                                                segment$iv$iv.cleanPrev();
                                            }
                                            segment$iv$iv2 = segment$iv$iv;
                                        } else {
                                            if (updCellResult$iv$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                                throw new IllegalStateException("unexpected".toString());
                                            }
                                            segment$iv$iv.cleanPrev();
                                            Function1<E, Unit> function2 = this.onUndeliveredElement;
                                            cancellable$iv.resume(updCellResult$iv$iv, (Function3<? super Throwable, ? super Object, ? super CoroutineContext, Unit>) (function2 != null ? bindCancellationFun(function2) : null));
                                        }
                                    } else {
                                        ?? r7 = cancellable$iv instanceof Waiter ? cancellable$iv : null;
                                        if (r7 != 0) {
                                            prepareReceiverForSuspension(r7, segment$iv$iv, i$iv$iv);
                                        }
                                    }
                                    break;
                                } catch (Throwable th) {
                                    e$iv = th;
                                    cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                    throw e$iv;
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        e$iv = th2;
                    }
                } else {
                    try {
                        prepareReceiverForSuspension(cancellable$iv, channelSegment, index);
                    } catch (Throwable th3) {
                        e$iv = th3;
                        cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                        throw e$iv;
                    }
                }
                Object result = cancellable$iv.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                return result;
            } catch (Throwable th4) {
                e$iv = th4;
            }
        } catch (Throwable th5) {
            e$iv = th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareReceiverForSuspension(Waiter $this$prepareReceiverForSuspension, ChannelSegment<E> channelSegment, int index) {
        onReceiveEnqueued();
        $this$prepareReceiverForSuspension.invokeOnCancellation(channelSegment, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveOnNoWaiterSuspend(CancellableContinuation<? super E> cont) {
        Result.Companion companion = Result.INSTANCE;
        cont.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(getReceiveException())));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: receiveCatching-JP2dKIU$suspendImpl, reason: not valid java name */
    static /* synthetic */ <E> Object m8898receiveCatchingJP2dKIU$suspendImpl(BufferedChannel<E> bufferedChannel, Continuation<? super ChannelResult<? extends E>> continuation) {
        BufferedChannel$receiveCatching$1 bufferedChannel$receiveCatching$1;
        Object objM8899receiveCatchingOnNoWaiterSuspendGKJJFZk;
        int $i$f$receiveImpl;
        if (continuation instanceof BufferedChannel$receiveCatching$1) {
            bufferedChannel$receiveCatching$1 = (BufferedChannel$receiveCatching$1) continuation;
            if ((bufferedChannel$receiveCatching$1.label & Integer.MIN_VALUE) != 0) {
                bufferedChannel$receiveCatching$1.label -= Integer.MIN_VALUE;
            } else {
                bufferedChannel$receiveCatching$1 = new BufferedChannel$receiveCatching$1(bufferedChannel, continuation);
            }
        } else {
            bufferedChannel$receiveCatching$1 = new BufferedChannel$receiveCatching$1(bufferedChannel, continuation);
        }
        Object $result = bufferedChannel$receiveCatching$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (bufferedChannel$receiveCatching$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ChannelSegment<E> segment$iv = (ChannelSegment) getReceiveSegment$volatile$FU().get(bufferedChannel);
                while (!bufferedChannel.isClosedForReceive()) {
                    long r$iv = getReceivers$volatile$FU().getAndIncrement(bufferedChannel);
                    long id$iv = r$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                    int i$iv = (int) (r$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                    if (segment$iv.id != id$iv) {
                        ChannelSegment<E> channelSegmentFindSegmentReceive = bufferedChannel.findSegmentReceive(id$iv, segment$iv);
                        if (channelSegmentFindSegmentReceive == null) {
                            continue;
                        } else {
                            segment$iv = channelSegmentFindSegmentReceive;
                        }
                    }
                    Object updCellResult$iv = bufferedChannel.updateCellReceive(segment$iv, i$iv, r$iv, null);
                    if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                        if (updCellResult$iv != BufferedChannelKt.FAILED) {
                            if (updCellResult$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                ChannelSegment<E> segm = segment$iv;
                                bufferedChannel$receiveCatching$1.label = 1;
                                objM8899receiveCatchingOnNoWaiterSuspendGKJJFZk = bufferedChannel.m8899receiveCatchingOnNoWaiterSuspendGKJJFZk(segm, i$iv, r$iv, bufferedChannel$receiveCatching$1);
                                if (objM8899receiveCatchingOnNoWaiterSuspendGKJJFZk == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $i$f$receiveImpl = 0;
                            } else {
                                segment$iv.cleanPrev();
                                return ChannelResult.INSTANCE.m8923successJP2dKIU(updCellResult$iv);
                            }
                            break;
                        } else if (r$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                            segment$iv.cleanPrev();
                        }
                    } else {
                        throw new IllegalStateException("unexpected".toString());
                    }
                }
                return ChannelResult.INSTANCE.m8921closedJP2dKIU(bufferedChannel.getCloseCause());
            case 1:
                $i$f$receiveImpl = 0;
                ResultKt.throwOnFailure($result);
                objM8899receiveCatchingOnNoWaiterSuspendGKJJFZk = ((ChannelResult) $result).getHolder();
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return objM8899receiveCatchingOnNoWaiterSuspendGKJJFZk;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk, reason: not valid java name */
    public final Object m8899receiveCatchingOnNoWaiterSuspendGKJJFZk(ChannelSegment<E> channelSegment, int i, long j, Continuation<? super ChannelResult<? extends E>> continuation) {
        BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 bufferedChannel$receiveCatchingOnNoWaiterSuspend$1;
        Object result;
        if (continuation instanceof BufferedChannel$receiveCatchingOnNoWaiterSuspend$1) {
            bufferedChannel$receiveCatchingOnNoWaiterSuspend$1 = (BufferedChannel$receiveCatchingOnNoWaiterSuspend$1) continuation;
            if ((bufferedChannel$receiveCatchingOnNoWaiterSuspend$1.label & Integer.MIN_VALUE) != 0) {
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$1.label -= Integer.MIN_VALUE;
            } else {
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$1 = new BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(this, continuation);
            }
        } else {
            bufferedChannel$receiveCatchingOnNoWaiterSuspend$1 = new BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(this, continuation);
        }
        BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 bufferedChannel$receiveCatchingOnNoWaiterSuspend$2 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$1;
        Object obj = bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.L$0 = this;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.L$1 = channelSegment;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.I$0 = i;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.J$0 = j;
                bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.label = 1;
                CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(bufferedChannel$receiveCatchingOnNoWaiterSuspend$2));
                try {
                    Intrinsics.checkNotNull(orCreateCancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuationImpl<kotlinx.coroutines.channels.ChannelResult<E of kotlinx.coroutines.channels.BufferedChannel.receiveCatchingOnNoWaiterSuspend_GKJJFZk$lambda$38>>");
                    ReceiveCatching receiveCatching = new ReceiveCatching(orCreateCancellableContinuation);
                    Object objUpdateCellReceive = updateCellReceive(channelSegment, i, j, receiveCatching);
                    if (objUpdateCellReceive != BufferedChannelKt.SUSPEND) {
                        if (objUpdateCellReceive == BufferedChannelKt.FAILED) {
                            if (j < getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment.cleanPrev();
                            }
                            ChannelSegment channelSegment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
                            while (true) {
                                if (isClosedForReceive()) {
                                    onClosedReceiveCatchingOnNoWaiterSuspend(orCreateCancellableContinuation);
                                } else {
                                    long andIncrement = getReceivers$volatile$FU().getAndIncrement(this);
                                    long j2 = andIncrement / ((long) BufferedChannelKt.SEGMENT_SIZE);
                                    int i2 = (int) (andIncrement % ((long) BufferedChannelKt.SEGMENT_SIZE));
                                    if (channelSegment2.id != j2) {
                                        ChannelSegment channelSegmentFindSegmentReceive = findSegmentReceive(j2, channelSegment2);
                                        if (channelSegmentFindSegmentReceive != null) {
                                            channelSegment2 = channelSegmentFindSegmentReceive;
                                        }
                                    }
                                    Object objUpdateCellReceive2 = updateCellReceive(channelSegment2, i2, andIncrement, receiveCatching);
                                    if (objUpdateCellReceive2 != BufferedChannelKt.SUSPEND) {
                                        if (objUpdateCellReceive2 != BufferedChannelKt.FAILED) {
                                            if (objUpdateCellReceive2 == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                                throw new IllegalStateException("unexpected".toString());
                                            }
                                            channelSegment2.cleanPrev();
                                            ChannelResult channelResultM8908boximpl = ChannelResult.m8908boximpl(ChannelResult.INSTANCE.m8923successJP2dKIU(objUpdateCellReceive2));
                                            Function1<E, Unit> function1 = this.onUndeliveredElement;
                                            orCreateCancellableContinuation.resume(channelResultM8908boximpl, (Function3<? super Throwable, ? super ChannelResult, ? super CoroutineContext, Unit>) (function1 != null ? bindCancellationFunResult(function1) : null));
                                        } else if (andIncrement < getSendersCounter$kotlinx_coroutines_core()) {
                                            channelSegment2.cleanPrev();
                                        }
                                    } else {
                                        ReceiveCatching receiveCatching2 = receiveCatching instanceof Waiter ? receiveCatching : null;
                                        if (receiveCatching2 != null) {
                                            prepareReceiverForSuspension(receiveCatching2, channelSegment2, i2);
                                        }
                                    }
                                }
                            }
                        } else {
                            channelSegment.cleanPrev();
                            ChannelResult channelResultM8908boximpl2 = ChannelResult.m8908boximpl(ChannelResult.INSTANCE.m8923successJP2dKIU(objUpdateCellReceive));
                            Function1<E, Unit> function2 = this.onUndeliveredElement;
                            orCreateCancellableContinuation.resume(channelResultM8908boximpl2, (Function3<? super Throwable, ? super ChannelResult, ? super CoroutineContext, Unit>) (function2 != null ? bindCancellationFunResult(function2) : null));
                        }
                    } else {
                        prepareReceiverForSuspension(receiveCatching, channelSegment, i);
                    }
                    result = orCreateCancellableContinuation.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(bufferedChannel$receiveCatchingOnNoWaiterSuspend$2);
                    }
                    if (result == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (Throwable th) {
                    orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                    throw th;
                }
                break;
            case 1:
                long j3 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.J$0;
                int i3 = bufferedChannel$receiveCatchingOnNoWaiterSuspend$2.I$0;
                ResultKt.throwOnFailure(obj);
                result = obj;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return ((ChannelResult) result).getHolder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosedReceiveCatchingOnNoWaiterSuspend(CancellableContinuation<? super ChannelResult<? extends E>> cont) {
        Result.Companion companion = Result.INSTANCE;
        cont.resumeWith(Result.m7388constructorimpl(ChannelResult.m8908boximpl(ChannelResult.INSTANCE.m8921closedJP2dKIU(getCloseCause()))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public Object mo8901tryReceivePtdJZtk() {
        ChannelSegment segment$iv;
        long r = receivers$volatile$FU.get(this);
        long sendersAndCloseStatusCur = sendersAndCloseStatus$volatile$FU.get(this);
        if (isClosedForReceive0(sendersAndCloseStatusCur)) {
            return ChannelResult.INSTANCE.m8921closedJP2dKIU(getCloseCause());
        }
        long $this$sendersCounter$iv = sendersAndCloseStatusCur & 1152921504606846975L;
        if (r >= $this$sendersCounter$iv) {
            return ChannelResult.INSTANCE.m8922failurePtdJZtk();
        }
        Object waiter$iv = BufferedChannelKt.INTERRUPTED_RCV;
        ChannelSegment segment$iv2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        while (!isClosedForReceive()) {
            long r$iv = getReceivers$volatile$FU().getAndIncrement(this);
            long id$iv = r$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i$iv = (int) (r$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = findSegmentReceive(id$iv, segment$iv2);
                if (segment$iv3 == null) {
                    continue;
                } else {
                    segment$iv = segment$iv3;
                }
            } else {
                segment$iv = segment$iv2;
            }
            long r2 = r;
            ChannelSegment segment$iv4 = segment$iv;
            Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, waiter$iv);
            if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                if (updCellResult$iv != BufferedChannelKt.FAILED) {
                    if (updCellResult$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                    segment$iv4.cleanPrev();
                    return ChannelResult.INSTANCE.m8923successJP2dKIU(updCellResult$iv);
                }
                if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv4.cleanPrev();
                }
                segment$iv2 = segment$iv4;
                r = r2;
            } else {
                Waiter waiter = waiter$iv instanceof Waiter ? (Waiter) waiter$iv : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, segment$iv4, i$iv);
                }
                waitExpandBufferCompletion$kotlinx_coroutines_core(r$iv);
                segment$iv4.onSlotCleaned();
                return ChannelResult.INSTANCE.m8922failurePtdJZtk();
            }
        }
        return ChannelResult.INSTANCE.m8921closedJP2dKIU(getCloseCause());
    }

    protected final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long globalCellIndex) {
        UndeliveredElementException it;
        if (DebugKt.getASSERTIONS_ENABLED() && !isConflatedDropOldest()) {
            throw new AssertionError();
        }
        ChannelSegment<E> channelSegment = (ChannelSegment) receiveSegment$volatile$FU.get(this);
        while (true) {
            long r = receivers$volatile$FU.get(this);
            if (globalCellIndex < Math.max(((long) this.capacity) + r, getBufferEndCounter())) {
                return;
            }
            if (receivers$volatile$FU.compareAndSet(this, r, r + 1)) {
                long id = r / ((long) BufferedChannelKt.SEGMENT_SIZE);
                int i = (int) (r % ((long) BufferedChannelKt.SEGMENT_SIZE));
                if (channelSegment.id != id) {
                    ChannelSegment<E> channelSegmentFindSegmentReceive = findSegmentReceive(id, channelSegment);
                    if (channelSegmentFindSegmentReceive == null) {
                        continue;
                    } else {
                        channelSegment = channelSegmentFindSegmentReceive;
                    }
                }
                Object updCellResult = updateCellReceive(channelSegment, i, r, null);
                if (updCellResult != BufferedChannelKt.FAILED) {
                    channelSegment.cleanPrev();
                    Function1<E, Unit> function1 = this.onUndeliveredElement;
                    if (function1 != null && (it = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(function1, updCellResult, null, 2, null)) != null) {
                        throw it;
                    }
                } else if (r < getSendersCounter$kotlinx_coroutines_core()) {
                    channelSegment.cleanPrev();
                }
            }
        }
    }

    static /* synthetic */ Object receiveImpl$default(BufferedChannel $this, Object waiter, Function1 onElementRetrieved, Function3 onSuspend, Function0 onClosed, Function3 onNoWaiterSuspend, int i, Object obj) {
        Function3 onNoWaiterSuspend2;
        ChannelSegment segment;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: receiveImpl");
        }
        if ((i & 16) == 0) {
            onNoWaiterSuspend2 = onNoWaiterSuspend;
        } else {
            onNoWaiterSuspend2 = new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel.receiveImpl.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2, Object p3) {
                    return invoke((ChannelSegment) p1, ((Number) p2).intValue(), ((Number) p3).longValue());
                }

                public final Void invoke(ChannelSegment<E> channelSegment, int i2, long j) {
                    throw new IllegalStateException("unexpected".toString());
                }
            };
        }
        ChannelSegment segment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get($this);
        while (!$this.isClosedForReceive()) {
            long r = getReceivers$volatile$FU().getAndIncrement($this);
            long id = r / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i2 = (int) (r % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment2.id != id) {
                ChannelSegment segment3 = $this.findSegmentReceive(id, segment2);
                if (segment3 == null) {
                    continue;
                } else {
                    segment = segment3;
                }
            } else {
                segment = segment2;
            }
            Object updCellResult = $this.updateCellReceive(segment, i2, r, waiter);
            if (updCellResult != BufferedChannelKt.SUSPEND) {
                if (updCellResult != BufferedChannelKt.FAILED) {
                    if (updCellResult == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        return onNoWaiterSuspend2.invoke(segment, Integer.valueOf(i2), Long.valueOf(r));
                    }
                    segment.cleanPrev();
                    return onElementRetrieved.invoke(updCellResult);
                }
                if (r < $this.getSendersCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                segment2 = segment;
            } else {
                Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                if (waiter2 != null) {
                    $this.prepareReceiverForSuspension(waiter2, segment, i2);
                }
                return onSuspend.invoke(segment, Integer.valueOf(i2), Long.valueOf(r));
            }
        }
        return onClosed.invoke();
    }

    private final <R> R receiveImpl(Object waiter, Function1<? super E, ? extends R> onElementRetrieved, Function3<? super ChannelSegment<E>, ? super Integer, ? super Long, ? extends R> onSuspend, Function0<? extends R> onClosed, Function3<? super ChannelSegment<E>, ? super Integer, ? super Long, ? extends R> onNoWaiterSuspend) {
        ChannelSegment segment;
        ChannelSegment segment2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        while (!isClosedForReceive()) {
            long r = getReceivers$volatile$FU().getAndIncrement(this);
            long id = r / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i = (int) (r % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment2.id != id) {
                ChannelSegment segment3 = findSegmentReceive(id, segment2);
                if (segment3 == null) {
                    continue;
                } else {
                    segment = segment3;
                }
            } else {
                segment = segment2;
            }
            Object updCellResult = updateCellReceive(segment, i, r, waiter);
            if (updCellResult != BufferedChannelKt.SUSPEND) {
                if (updCellResult != BufferedChannelKt.FAILED) {
                    if (updCellResult == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        return onNoWaiterSuspend.invoke(segment, Integer.valueOf(i), Long.valueOf(r));
                    }
                    segment.cleanPrev();
                    return onElementRetrieved.invoke(updCellResult);
                }
                if (r < getSendersCounter$kotlinx_coroutines_core()) {
                    segment.cleanPrev();
                }
                segment2 = segment;
            } else {
                Waiter waiter2 = waiter instanceof Waiter ? (Waiter) waiter : null;
                if (waiter2 != null) {
                    prepareReceiverForSuspension(waiter2, segment, i);
                }
                return onSuspend.invoke(segment, Integer.valueOf(i), Long.valueOf(r));
            }
        }
        return onClosed.invoke();
    }

    private final void receiveImplOnNoWaiter(ChannelSegment<E> segment, int index, long r, Waiter waiter, Function1<? super E, Unit> onElementRetrieved, Function0<Unit> onClosed) {
        ChannelSegment segment$iv;
        int $i$f$receiveImplOnNoWaiter = 0;
        Object updCellResult = updateCellReceive(segment, index, r, waiter);
        if (updCellResult != BufferedChannelKt.SUSPEND) {
            if (updCellResult != BufferedChannelKt.FAILED) {
                segment.cleanPrev();
                onElementRetrieved.invoke(updCellResult);
                return;
            }
            if (r < getSendersCounter$kotlinx_coroutines_core()) {
                segment.cleanPrev();
            }
            ChannelSegment segment$iv2 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
            while (!isClosedForReceive()) {
                long r$iv = getReceivers$volatile$FU().getAndIncrement(this);
                long id$iv = r$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                int i$iv = (int) (r$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                if (segment$iv2.id != id$iv) {
                    ChannelSegment segment$iv3 = findSegmentReceive(id$iv, segment$iv2);
                    if (segment$iv3 == null) {
                        continue;
                    } else {
                        segment$iv = segment$iv3;
                    }
                } else {
                    segment$iv = segment$iv2;
                }
                int $i$f$receiveImplOnNoWaiter2 = $i$f$receiveImplOnNoWaiter;
                ChannelSegment segment$iv4 = segment$iv;
                Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, waiter);
                if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                    if (updCellResult$iv != BufferedChannelKt.FAILED) {
                        if (updCellResult$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                            throw new IllegalStateException("unexpected".toString());
                        }
                        segment$iv4.cleanPrev();
                        onElementRetrieved.invoke(updCellResult$iv);
                        return;
                    }
                    if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                        segment$iv4.cleanPrev();
                    }
                    segment$iv2 = segment$iv4;
                    $i$f$receiveImplOnNoWaiter = $i$f$receiveImplOnNoWaiter2;
                } else {
                    Waiter waiter2 = waiter instanceof Waiter ? waiter : null;
                    if (waiter2 != null) {
                        prepareReceiverForSuspension(waiter2, segment$iv4, i$iv);
                    }
                    Unit unit = Unit.INSTANCE;
                    return;
                }
            }
            onClosed.invoke();
            return;
        }
        prepareReceiverForSuspension(waiter, segment, index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object updateCellReceive(ChannelSegment<E> segment, int index, long r, Object waiter) {
        Object state = segment.getState$kotlinx_coroutines_core(index);
        if (state == null) {
            long $this$sendersCounter$iv = sendersAndCloseStatus$volatile$FU.get(this);
            long senders = $this$sendersCounter$iv & 1152921504606846975L;
            if (r >= senders) {
                if (waiter == null) {
                    return BufferedChannelKt.SUSPEND_NO_WAITER;
                }
                if (segment.casState$kotlinx_coroutines_core(index, state, waiter)) {
                    expandBuffer();
                    return BufferedChannelKt.SUSPEND;
                }
            }
        } else if (state == BufferedChannelKt.BUFFERED && segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.DONE_RCV)) {
            expandBuffer();
            return segment.retrieveElement$kotlinx_coroutines_core(index);
        }
        return updateCellReceiveSlow(segment, index, r, waiter);
    }

    private final Object updateCellReceiveSlow(ChannelSegment<E> segment, int index, long r, Object waiter) {
        while (true) {
            Object state = segment.getState$kotlinx_coroutines_core(index);
            if (state == null || state == BufferedChannelKt.IN_BUFFER) {
                long $this$sendersCounter$iv = sendersAndCloseStatus$volatile$FU.get(this);
                long senders = $this$sendersCounter$iv & 1152921504606846975L;
                if (r < senders) {
                    if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.POISONED)) {
                        expandBuffer();
                        return BufferedChannelKt.FAILED;
                    }
                } else {
                    if (waiter == null) {
                        return BufferedChannelKt.SUSPEND_NO_WAITER;
                    }
                    if (segment.casState$kotlinx_coroutines_core(index, state, waiter)) {
                        expandBuffer();
                        return BufferedChannelKt.SUSPEND;
                    }
                }
            } else if (state == BufferedChannelKt.BUFFERED) {
                if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.DONE_RCV)) {
                    expandBuffer();
                    return segment.retrieveElement$kotlinx_coroutines_core(index);
                }
            } else {
                if (state != BufferedChannelKt.INTERRUPTED_SEND && state != BufferedChannelKt.POISONED) {
                    if (state != BufferedChannelKt.getCHANNEL_CLOSED()) {
                        if (state != BufferedChannelKt.RESUMING_BY_EB && segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.RESUMING_BY_RCV)) {
                            boolean helpExpandBuffer = state instanceof WaiterEB;
                            Object sender = state instanceof WaiterEB ? ((WaiterEB) state).waiter : state;
                            if (tryResumeSender(sender, segment, index)) {
                                segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.DONE_RCV);
                                expandBuffer();
                                return segment.retrieveElement$kotlinx_coroutines_core(index);
                            }
                            segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.INTERRUPTED_SEND);
                            segment.onCancelledRequest(index, false);
                            if (helpExpandBuffer) {
                                expandBuffer();
                            }
                            return BufferedChannelKt.FAILED;
                        }
                    } else {
                        expandBuffer();
                        return BufferedChannelKt.FAILED;
                    }
                }
                return BufferedChannelKt.FAILED;
            }
        }
    }

    private final boolean tryResumeSender(Object $this$tryResumeSender, ChannelSegment<E> channelSegment, int index) {
        if ($this$tryResumeSender instanceof CancellableContinuation) {
            Intrinsics.checkNotNull($this$tryResumeSender, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return BufferedChannelKt.tryResume0$default((CancellableContinuation) $this$tryResumeSender, Unit.INSTANCE, null, 2, null);
        }
        if ($this$tryResumeSender instanceof SelectInstance) {
            Intrinsics.checkNotNull($this$tryResumeSender, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
            TrySelectDetailedResult trySelectResult = ((SelectImplementation) $this$tryResumeSender).trySelectDetailed(this, Unit.INSTANCE);
            if (trySelectResult == TrySelectDetailedResult.REREGISTER) {
                channelSegment.cleanElement$kotlinx_coroutines_core(index);
            }
            return trySelectResult == TrySelectDetailedResult.SUCCESSFUL;
        }
        if ($this$tryResumeSender instanceof SendBroadcast) {
            return BufferedChannelKt.tryResume0$default(((SendBroadcast) $this$tryResumeSender).getCont(), true, null, 2, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + $this$tryResumeSender).toString());
    }

    private final void expandBuffer() {
        if (isRendezvousOrUnlimited()) {
            return;
        }
        ChannelSegment<E> channelSegment = (ChannelSegment) bufferEndSegment$volatile$FU.get(this);
        while (true) {
            long b = bufferEnd$volatile$FU.getAndIncrement(this);
            long id = b / ((long) BufferedChannelKt.SEGMENT_SIZE);
            long s = getSendersCounter$kotlinx_coroutines_core();
            if (s <= b) {
                if (channelSegment.id < id && channelSegment.getNext() != 0) {
                    moveSegmentBufferEndToSpecifiedOrLast(id, channelSegment);
                }
                incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
                return;
            }
            if (channelSegment.id != id) {
                ChannelSegment<E> channelSegmentFindSegmentBufferEnd = findSegmentBufferEnd(id, channelSegment, b);
                if (channelSegmentFindSegmentBufferEnd == null) {
                    continue;
                } else {
                    channelSegment = channelSegmentFindSegmentBufferEnd;
                }
            }
            int i = (int) (b % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (updateCellExpandBuffer(channelSegment, i, b)) {
                incCompletedExpandBufferAttempts$default(this, r4, 1, null);
                return;
            }
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
        }
    }

    private final boolean updateCellExpandBuffer(ChannelSegment<E> segment, int index, long b) {
        Object state = segment.getState$kotlinx_coroutines_core(index);
        if ((state instanceof Waiter) && b >= receivers$volatile$FU.get(this) && segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.RESUMING_BY_EB)) {
            if (!tryResumeSender(state, segment, index)) {
                segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.INTERRUPTED_SEND);
                segment.onCancelledRequest(index, false);
                return false;
            }
            segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.BUFFERED);
            return true;
        }
        return updateCellExpandBufferSlow(segment, index, b);
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment<E> segment, int index, long b) {
        while (true) {
            Object state = segment.getState$kotlinx_coroutines_core(index);
            if (state instanceof Waiter) {
                if (b >= receivers$volatile$FU.get(this)) {
                    if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.RESUMING_BY_EB)) {
                        if (!tryResumeSender(state, segment, index)) {
                            segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.INTERRUPTED_SEND);
                            segment.onCancelledRequest(index, false);
                            return false;
                        }
                        segment.setState$kotlinx_coroutines_core(index, BufferedChannelKt.BUFFERED);
                        return true;
                    }
                } else if (segment.casState$kotlinx_coroutines_core(index, state, new WaiterEB((Waiter) state))) {
                    return true;
                }
            } else {
                if (state == BufferedChannelKt.INTERRUPTED_SEND) {
                    return false;
                }
                if (state == null) {
                    if (segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.IN_BUFFER)) {
                        return true;
                    }
                } else {
                    if (state == BufferedChannelKt.BUFFERED || state == BufferedChannelKt.POISONED || state == BufferedChannelKt.DONE_RCV || state == BufferedChannelKt.INTERRUPTED_RCV || state == BufferedChannelKt.getCHANNEL_CLOSED()) {
                        return true;
                    }
                    if (state != BufferedChannelKt.RESUMING_BY_RCV) {
                        throw new IllegalStateException(("Unexpected cell state: " + state).toString());
                    }
                }
            }
        }
    }

    static /* synthetic */ void incCompletedExpandBufferAttempts$default(BufferedChannel bufferedChannel, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        }
        if ((i & 1) != 0) {
            j = 1;
        }
        bufferedChannel.incCompletedExpandBufferAttempts(j);
    }

    private final void incCompletedExpandBufferAttempts(long nAttempts) {
        long $this$ebPauseExpandBuffers$iv;
        long it = completedExpandBuffersAndPauseFlag$volatile$FU.addAndGet(this, nAttempts);
        if ((it & 4611686018427387904L) != 0) {
            do {
                $this$ebPauseExpandBuffers$iv = completedExpandBuffersAndPauseFlag$volatile$FU.get(this);
            } while (($this$ebPauseExpandBuffers$iv & 4611686018427387904L) != 0);
        }
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long globalIndex) {
        long ebCompleted;
        if (isRendezvousOrUnlimited()) {
            return;
        }
        while (getBufferEndCounter() <= globalIndex) {
        }
        int i = BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
        boolean z = false;
        int i2 = 0;
        while (true) {
            long j = DurationKt.MAX_MILLIS;
            if (i2 >= i) {
                AtomicLongFieldUpdater handler$atomicfu$iv = completedExpandBuffersAndPauseFlag$volatile$FU;
                while (true) {
                    long it = handler$atomicfu$iv.get(this);
                    long $this$ebCompletedCounter$iv = it & j;
                    if (handler$atomicfu$iv.compareAndSet(this, it, BufferedChannelKt.constructEBCompletedAndPauseFlag($this$ebCompletedCounter$iv, true))) {
                        break;
                    }
                    z = false;
                    j = DurationKt.MAX_MILLIS;
                }
                while (true) {
                    long b = getBufferEndCounter();
                    long ebCompletedAndBit = completedExpandBuffersAndPauseFlag$volatile$FU.get(this);
                    long $this$ebCompletedCounter$iv2 = ebCompletedAndBit & j;
                    ebCompleted = $this$ebCompletedCounter$iv2;
                    boolean pauseExpandBuffers = (4611686018427387904L & ebCompletedAndBit) != 0 ? true : z;
                    if (b == ebCompleted && b == getBufferEndCounter()) {
                        break;
                    }
                    if (!pauseExpandBuffers) {
                        completedExpandBuffersAndPauseFlag$volatile$FU.compareAndSet(this, ebCompletedAndBit, BufferedChannelKt.constructEBCompletedAndPauseFlag(ebCompleted, true));
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    } else {
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    }
                }
                AtomicLongFieldUpdater handler$atomicfu$iv2 = completedExpandBuffersAndPauseFlag$volatile$FU;
                while (true) {
                    long it2 = handler$atomicfu$iv2.get(this);
                    long jConstructEBCompletedAndPauseFlag = BufferedChannelKt.constructEBCompletedAndPauseFlag(it2 & j, z);
                    long ebCompleted2 = ebCompleted;
                    if (!handler$atomicfu$iv2.compareAndSet(this, it2, jConstructEBCompletedAndPauseFlag)) {
                        ebCompleted = ebCompleted2;
                        z = false;
                        j = DurationKt.MAX_MILLIS;
                    } else {
                        return;
                    }
                }
            } else {
                long b2 = getBufferEndCounter();
                long $this$ebCompletedCounter$iv3 = completedExpandBuffersAndPauseFlag$volatile$FU.get(this);
                if (b2 == (DurationKt.MAX_MILLIS & $this$ebCompletedCounter$iv3) && b2 == getBufferEndCounter()) {
                    return;
                } else {
                    i2++;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<E, BufferedChannel<E>> getOnSend() {
        BufferedChannel$onSend$1 bufferedChannel$onSend$1 = BufferedChannel$onSend$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onSend$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onSend$1, 3);
        BufferedChannel$onSend$2 bufferedChannel$onSend$2 = BufferedChannel$onSend$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onSend$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause2Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onSend$2, 3), null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void registerSelectForSend(SelectInstance<?> select, Object element) {
        ChannelSegment segment$iv;
        int $i$f$sendImpl = 0;
        ChannelSegment segment$iv2 = (ChannelSegment) sendSegment$volatile$FU.get(this);
        while (true) {
            long sendersAndCloseStatusCur$iv = sendersAndCloseStatus$volatile$FU.getAndIncrement(this);
            long $this$sendersCounter$iv$iv = sendersAndCloseStatusCur$iv & 1152921504606846975L;
            boolean closed$iv = isClosedForSend0(sendersAndCloseStatusCur$iv);
            long id$iv = $this$sendersCounter$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i$iv = (int) ($this$sendersCounter$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment$iv2.id != id$iv) {
                ChannelSegment segment$iv3 = findSegmentSend(id$iv, segment$iv2);
                if (segment$iv3 != null) {
                    segment$iv = segment$iv3;
                } else if (closed$iv) {
                    onClosedSelectOnSend(element, select);
                    return;
                }
            } else {
                segment$iv = segment$iv2;
            }
            ChannelSegment segment$iv4 = segment$iv;
            int $i$f$sendImpl2 = $i$f$sendImpl;
            switch (updateCellSend(segment$iv, i$iv, element, $this$sendersCounter$iv$iv, select, closed$iv)) {
                case 0:
                    segment$iv4.cleanPrev();
                    select.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                case 1:
                    select.selectInRegistrationPhase(Unit.INSTANCE);
                    return;
                case 2:
                    if (closed$iv) {
                        segment$iv4.onSlotCleaned();
                        onClosedSelectOnSend(element, select);
                        return;
                    } else {
                        Waiter waiter = select instanceof Waiter ? (Waiter) select : null;
                        if (waiter != null) {
                            prepareSenderForSuspension(waiter, segment$iv4, i$iv);
                        }
                        return;
                    }
                case 3:
                    throw new IllegalStateException("unexpected".toString());
                case 4:
                    if ($this$sendersCounter$iv$iv < getReceiversCounter$kotlinx_coroutines_core()) {
                        segment$iv4.cleanPrev();
                    }
                    onClosedSelectOnSend(element, select);
                    return;
                case 5:
                    segment$iv4.cleanPrev();
                default:
                    segment$iv2 = segment$iv4;
                    $i$f$sendImpl = $i$f$sendImpl2;
                    break;
            }
        }
    }

    private final void onClosedSelectOnSend(E element, SelectInstance<?> select) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        if (function1 != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function1, element, select.getContext());
        }
        select.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectSend(Object ignoredParam, Object selectResult) throws Throwable {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw getSendException();
        }
        return this;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceive() {
        BufferedChannel$onReceive$1 bufferedChannel$onReceive$1 = BufferedChannel$onReceive$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceive$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceive$1, 3);
        BufferedChannel$onReceive$2 bufferedChannel$onReceive$2 = BufferedChannel$onReceive$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceive$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceive$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        BufferedChannel$onReceiveCatching$1 bufferedChannel$onReceiveCatching$1 = BufferedChannel$onReceiveCatching$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveCatching$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveCatching$1, 3);
        BufferedChannel$onReceiveCatching$2 bufferedChannel$onReceiveCatching$2 = BufferedChannel$onReceiveCatching$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveCatching$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveCatching$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceiveOrNull() {
        BufferedChannel$onReceiveOrNull$1 bufferedChannel$onReceiveOrNull$1 = BufferedChannel$onReceiveOrNull$1.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveOrNull$1, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"select\")] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \"param\")] kotlin.Any?, kotlin.Unit>");
        Function3 function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveOrNull$1, 3);
        BufferedChannel$onReceiveOrNull$2 bufferedChannel$onReceiveOrNull$2 = BufferedChannel$onReceiveOrNull$2.INSTANCE;
        Intrinsics.checkNotNull(bufferedChannel$onReceiveOrNull$2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \"clauseObject\")] kotlin.Any, @[ParameterName(name = \"param\")] kotlin.Any?, @[ParameterName(name = \"clauseResult\")] kotlin.Any?, kotlin.Any?>");
        return new SelectClause1Impl(this, function3, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(bufferedChannel$onReceiveOrNull$2, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerSelectForReceive(SelectInstance<?> select, Object ignoredParam) {
        ChannelSegment segment$iv = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        while (!isClosedForReceive()) {
            long r$iv = getReceivers$volatile$FU().getAndIncrement(this);
            long id$iv = r$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
            int i$iv = (int) (r$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (segment$iv.id != id$iv) {
                ChannelSegment channelSegmentFindSegmentReceive = findSegmentReceive(id$iv, segment$iv);
                if (channelSegmentFindSegmentReceive == null) {
                    continue;
                } else {
                    segment$iv = channelSegmentFindSegmentReceive;
                }
            }
            Object updCellResult$iv = updateCellReceive(segment$iv, i$iv, r$iv, select);
            if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                if (updCellResult$iv != BufferedChannelKt.FAILED) {
                    if (updCellResult$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                        throw new IllegalStateException("unexpected".toString());
                    }
                    segment$iv.cleanPrev();
                    select.selectInRegistrationPhase(updCellResult$iv);
                    return;
                }
                if (r$iv < getSendersCounter$kotlinx_coroutines_core()) {
                    segment$iv.cleanPrev();
                }
            } else {
                Waiter waiter = select instanceof Waiter ? (Waiter) select : null;
                if (waiter != null) {
                    prepareReceiverForSuspension(waiter, segment$iv, i$iv);
                }
                return;
            }
        }
        onClosedSelectOnReceive(select);
    }

    private final void onClosedSelectOnReceive(SelectInstance<?> select) {
        select.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceive(Object ignoredParam, Object selectResult) throws Throwable {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            throw getReceiveException();
        }
        return selectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceiveOrNull(Object ignoredParam, Object selectResult) throws Throwable {
        if (selectResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
            if (getCloseCause() == null) {
                return null;
            }
            throw getReceiveException();
        }
        return selectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processResultSelectReceiveCatching(Object ignoredParam, Object selectResult) {
        return ChannelResult.m8908boximpl(selectResult == BufferedChannelKt.getCHANNEL_CLOSED() ? ChannelResult.INSTANCE.m8921closedJP2dKIU(getCloseCause()) : ChannelResult.INSTANCE.m8923successJP2dKIU(selectResult));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function3 onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56(final BufferedChannel this$0, final SelectInstance select, Object obj, final Object element) {
        return new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                return BufferedChannel.onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55(element, this$0, select, (Throwable) obj2, obj3, (CoroutineContext) obj4);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onUndeliveredElementReceiveCancellationConstructor$lambda$57$lambda$56$lambda$55(Object $element, BufferedChannel this$0, SelectInstance $select, Throwable th, Object obj, CoroutineContext coroutineContext) {
        if ($element != BufferedChannelKt.getCHANNEL_CLOSED()) {
            OnUndeliveredElementKt.callUndeliveredElement(this$0.onUndeliveredElement, $element, $select.getContext());
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator<E> iterator() {
        return new BufferedChannelIterator();
    }

    /* JADX INFO: compiled from: BufferedChannel.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\n\u001a\u00020\tH\u0096B¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\tH\u0002J,\u0010\r\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u000e\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00028\u0000¢\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator;", "Lkotlinx/coroutines/channels/ChannelIterator;", "Lkotlinx/coroutines/Waiter;", "<init>", "(Lkotlinx/coroutines/channels/BufferedChannel;)V", "receiveResult", "", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "", "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedHasNext", "hasNextOnNoWaiterSuspend", "segment", "Lkotlinx/coroutines/channels/ChannelSegment;", "index", "", "r", "", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnCancellation", "", "Lkotlinx/coroutines/internal/Segment;", "onClosedHasNextNoWaiterSuspend", "next", "()Ljava/lang/Object;", "tryResumeHasNext", "element", "(Ljava/lang/Object;)Z", "tryResumeHasNextOnClosedChannel", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class BufferedChannelIterator implements ChannelIterator<E>, Waiter {
        private CancellableContinuationImpl<? super Boolean> continuation;
        private Object receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;

        public BufferedChannelIterator() {
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public /* synthetic */ Object next(Continuation $completion) {
            return ChannelIterator.DefaultImpls.next(this, $completion);
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation<? super Boolean> continuation) throws Throwable {
            ChannelSegment<E> channelSegment;
            boolean zOnClosedHasNext = true;
            if (this.receiveResult == BufferedChannelKt.NO_RECEIVE_RESULT || this.receiveResult == BufferedChannelKt.getCHANNEL_CLOSED()) {
                BufferedChannel<E> bufferedChannel = BufferedChannel.this;
                ChannelSegment<E> channelSegment2 = (ChannelSegment) BufferedChannel.getReceiveSegment$volatile$FU().get(bufferedChannel);
                while (!bufferedChannel.isClosedForReceive()) {
                    long r$iv = BufferedChannel.getReceivers$volatile$FU().getAndIncrement(bufferedChannel);
                    long id$iv = r$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                    int i$iv = (int) (r$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                    if (channelSegment2.id != id$iv) {
                        ChannelSegment<E> channelSegmentFindSegmentReceive = bufferedChannel.findSegmentReceive(id$iv, channelSegment2);
                        if (channelSegmentFindSegmentReceive == null) {
                            continue;
                        } else {
                            channelSegment = channelSegmentFindSegmentReceive;
                        }
                    } else {
                        channelSegment = channelSegment2;
                    }
                    Object updCellResult$iv = bufferedChannel.updateCellReceive(channelSegment, i$iv, r$iv, null);
                    if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                        if (updCellResult$iv != BufferedChannelKt.FAILED) {
                            if (updCellResult$iv != BufferedChannelKt.SUSPEND_NO_WAITER) {
                                channelSegment.cleanPrev();
                                this.receiveResult = updCellResult$iv;
                            } else {
                                return hasNextOnNoWaiterSuspend(channelSegment, i$iv, r$iv, continuation);
                            }
                        } else {
                            if (r$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                                channelSegment.cleanPrev();
                            }
                            channelSegment2 = channelSegment;
                        }
                    } else {
                        throw new IllegalStateException("unreachable".toString());
                    }
                }
                zOnClosedHasNext = onClosedHasNext();
            }
            return Boxing.boxBoolean(zOnClosedHasNext);
        }

        private final boolean onClosedHasNext() throws Throwable {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                return false;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(cause);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object hasNextOnNoWaiterSuspend(ChannelSegment<E> channelSegment, int index, long r, Continuation<? super Boolean> continuation) throws Throwable {
            ChannelSegment segment$iv$iv;
            BufferedChannel<E> bufferedChannel = BufferedChannel.this;
            int $i$f$suspendCancellableCoroutineReusable = 0;
            Continuation<? super Boolean> continuation2 = continuation;
            int i = 0;
            CancellableContinuationImpl cancellable$iv = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation2));
            int i2 = 0;
            try {
                this.continuation = cancellable$iv;
                Object updCellResult$iv = bufferedChannel.updateCellReceive(channelSegment, index, r, this);
                try {
                    if (updCellResult$iv != BufferedChannelKt.SUSPEND) {
                        try {
                            if (updCellResult$iv != BufferedChannelKt.FAILED) {
                                channelSegment.cleanPrev();
                                this.receiveResult = updCellResult$iv;
                                this.continuation = null;
                                Boolean boolBoxBoolean = Boxing.boxBoolean(true);
                                Function1<E, Unit> function1 = bufferedChannel.onUndeliveredElement;
                                cancellable$iv.resume(boolBoxBoolean, (Function3<? super Throwable, ? super Boolean, ? super CoroutineContext, Unit>) (function1 != null ? bufferedChannel.bindCancellationFun(function1, updCellResult$iv) : null));
                            } else {
                                if (r < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                                    channelSegment.cleanPrev();
                                }
                                ChannelSegment segment$iv$iv2 = (ChannelSegment) BufferedChannel.getReceiveSegment$volatile$FU().get(bufferedChannel);
                                while (true) {
                                    if (bufferedChannel.isClosedForReceive()) {
                                        onClosedHasNextNoWaiterSuspend();
                                        break;
                                    }
                                    long r$iv$iv = BufferedChannel.getReceivers$volatile$FU().getAndIncrement(bufferedChannel);
                                    long id$iv$iv = r$iv$iv / ((long) BufferedChannelKt.SEGMENT_SIZE);
                                    $i$f$suspendCancellableCoroutineReusable = $i$f$suspendCancellableCoroutineReusable;
                                    continuation2 = continuation2;
                                    try {
                                        int i$iv$iv = (int) (r$iv$iv % ((long) BufferedChannelKt.SEGMENT_SIZE));
                                        i = i;
                                        if (segment$iv$iv2.id != id$iv$iv) {
                                            segment$iv$iv = bufferedChannel.findSegmentReceive(id$iv$iv, segment$iv$iv2);
                                            if (segment$iv$iv == null) {
                                            }
                                        } else {
                                            segment$iv$iv = segment$iv$iv2;
                                        }
                                        int i3 = i2;
                                        Object updCellResult$iv2 = updCellResult$iv;
                                        Object updCellResult$iv$iv = bufferedChannel.updateCellReceive(segment$iv$iv, i$iv$iv, r$iv$iv, this);
                                        if (updCellResult$iv$iv != BufferedChannelKt.SUSPEND) {
                                            if (updCellResult$iv$iv == BufferedChannelKt.FAILED) {
                                                if (r$iv$iv < bufferedChannel.getSendersCounter$kotlinx_coroutines_core()) {
                                                    segment$iv$iv.cleanPrev();
                                                }
                                                segment$iv$iv2 = segment$iv$iv;
                                                i2 = i3;
                                                updCellResult$iv = updCellResult$iv2;
                                            } else {
                                                if (updCellResult$iv$iv == BufferedChannelKt.SUSPEND_NO_WAITER) {
                                                    throw new IllegalStateException("unexpected".toString());
                                                }
                                                segment$iv$iv.cleanPrev();
                                                this.receiveResult = updCellResult$iv$iv;
                                                this.continuation = null;
                                                Boolean boolBoxBoolean2 = Boxing.boxBoolean(true);
                                                Function1<E, Unit> function2 = bufferedChannel.onUndeliveredElement;
                                                cancellable$iv.resume(boolBoxBoolean2, (Function3<? super Throwable, ? super Boolean, ? super CoroutineContext, Unit>) (function2 != null ? bufferedChannel.bindCancellationFun(function2, updCellResult$iv$iv) : null));
                                            }
                                        } else {
                                            BufferedChannelIterator bufferedChannelIterator = this instanceof Waiter ? this : null;
                                            if (bufferedChannelIterator != null) {
                                                bufferedChannel.prepareReceiverForSuspension(bufferedChannelIterator, segment$iv$iv, i$iv$iv);
                                            }
                                        }
                                        break;
                                    } catch (Throwable th) {
                                        e$iv = th;
                                        cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                                        throw e$iv;
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                        }
                    } else {
                        try {
                            bufferedChannel.prepareReceiverForSuspension(this, channelSegment, index);
                        } catch (Throwable th3) {
                            e$iv = th3;
                            cancellable$iv.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                            throw e$iv;
                        }
                    }
                    Object result = cancellable$iv.getResult();
                    if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(continuation);
                    }
                    return result;
                } catch (Throwable th4) {
                    e$iv = th4;
                }
            } catch (Throwable th5) {
                e$iv = th5;
            }
        }

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> segment, int index) {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.invokeOnCancellation(segment, index);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void onClosedHasNextNoWaiterSuspend() {
            Throwable thRecoverFromStackFrame;
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m7388constructorimpl(false));
                return;
            }
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl2 = cancellableContinuationImpl;
            if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuationImpl instanceof CoroutineStackFrame)) {
                thRecoverFromStackFrame = StackTraceRecoveryKt.recoverFromStackFrame(cause, cancellableContinuationImpl);
            } else {
                thRecoverFromStackFrame = cause;
            }
            Result.Companion companion2 = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(thRecoverFromStackFrame)));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() throws Throwable {
            E e = (E) this.receiveResult;
            if (!(e != BufferedChannelKt.NO_RECEIVE_RESULT)) {
                throw new IllegalStateException("`hasNext()` has not been invoked".toString());
            }
            this.receiveResult = BufferedChannelKt.NO_RECEIVE_RESULT;
            if (e == BufferedChannelKt.getCHANNEL_CLOSED()) {
                throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.this.getReceiveException());
            }
            return e;
        }

        public final boolean tryResumeHasNext(E element) {
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            Function3 function3BindCancellationFun = null;
            this.continuation = null;
            this.receiveResult = element;
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl2 = cancellableContinuationImpl;
            Function1<E, Unit> function1 = BufferedChannel.this.onUndeliveredElement;
            if (function1 != null) {
                function3BindCancellationFun = BufferedChannel.this.bindCancellationFun(function1, element);
            }
            return BufferedChannelKt.tryResume0(cancellableContinuationImpl2, true, function3BindCancellationFun);
        }

        public final void tryResumeHasNextOnClosedChannel() {
            Throwable thRecoverFromStackFrame;
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl = this.continuation;
            Intrinsics.checkNotNull(cancellableContinuationImpl);
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable cause = BufferedChannel.this.getCloseCause();
            if (cause == null) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m7388constructorimpl(false));
                return;
            }
            CancellableContinuationImpl<? super Boolean> cancellableContinuationImpl2 = cancellableContinuationImpl;
            if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuationImpl instanceof CoroutineStackFrame)) {
                thRecoverFromStackFrame = StackTraceRecoveryKt.recoverFromStackFrame(cause, cancellableContinuationImpl);
            } else {
                thRecoverFromStackFrame = cause;
            }
            Result.Companion companion2 = Result.INSTANCE;
            cancellableContinuationImpl2.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(thRecoverFromStackFrame)));
        }
    }

    protected final Throwable getCloseCause() {
        return (Throwable) _closeCause$volatile$FU.get(this);
    }

    protected final Throwable getSendException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : closeCause;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable getReceiveException() {
        Throwable closeCause = getCloseCause();
        return closeCause == null ? new ClosedReceiveChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : closeCause;
    }

    protected void onClosedIdempotent() {
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        return closeOrCancelImpl(cause, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final boolean cancel(Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel() {
        cancelImpl$kotlinx_coroutines_core(null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cause) {
        cancelImpl$kotlinx_coroutines_core(cause);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
        return closeOrCancelImpl(cause == null ? new CancellationException("Channel was cancelled") : cause, true);
    }

    protected boolean closeOrCancelImpl(Throwable cause, boolean cancel) {
        if (cancel) {
            markCancellationStarted();
        }
        boolean closedByThisOperation = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closeCause$volatile$FU, this, BufferedChannelKt.NO_CLOSE_CAUSE, cause);
        if (cancel) {
            markCancelled();
        } else {
            markClosed();
        }
        completeCloseOrCancel();
        onClosedIdempotent();
        if (closedByThisOperation) {
            invokeCloseHandler();
        }
        return closedByThisOperation;
    }

    private final void invokeCloseHandler() {
        Object it;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = closeHandler$volatile$FU;
        do {
            it = handler$atomicfu$iv.get(this);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, it, it == null ? BufferedChannelKt.CLOSE_HANDLER_CLOSED : BufferedChannelKt.CLOSE_HANDLER_INVOKED));
        if (it != null) {
            ((Function1) it).invoke(getCloseCause());
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closeHandler$volatile$FU, this, null, handler)) {
            AtomicReferenceFieldUpdater handler$atomicfu$iv = closeHandler$volatile$FU;
            do {
                Object cur = handler$atomicfu$iv.get(this);
                if (cur != BufferedChannelKt.CLOSE_HANDLER_CLOSED) {
                    if (cur != BufferedChannelKt.CLOSE_HANDLER_INVOKED) {
                        throw new IllegalStateException(("Another handler is already registered: " + cur).toString());
                    }
                    throw new IllegalStateException("Another handler was already registered and successfully invoked".toString());
                }
            } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(closeHandler$volatile$FU, this, BufferedChannelKt.CLOSE_HANDLER_CLOSED, BufferedChannelKt.CLOSE_HANDLER_INVOKED));
            handler.invoke(getCloseCause());
        }
    }

    private final void markClosed() {
        long cur;
        long jConstructSendersAndCloseStatus;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            int $i$f$getSendersCloseStatus = (int) (cur >> 60);
            switch ($i$f$getSendersCloseStatus) {
                case 0:
                    jConstructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & cur, 2);
                    break;
                case 1:
                    jConstructSendersAndCloseStatus = BufferedChannelKt.constructSendersAndCloseStatus(1152921504606846975L & cur, 3);
                    break;
                default:
                    return;
            }
        } while (!handler$atomicfu$iv.compareAndSet(this, cur, jConstructSendersAndCloseStatus));
    }

    private final void markCancelled() {
        long cur;
        long $this$sendersCounter$iv;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            $this$sendersCounter$iv = cur & 1152921504606846975L;
        } while (!handler$atomicfu$iv.compareAndSet(this, cur, BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, 3)));
    }

    private final void markCancellationStarted() {
        long cur;
        long $this$sendersCounter$iv;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            int $i$f$getSendersCloseStatus = (int) (cur >> 60);
            if ($i$f$getSendersCloseStatus == 0) {
                $this$sendersCounter$iv = cur & 1152921504606846975L;
            } else {
                return;
            }
        } while (!handler$atomicfu$iv.compareAndSet(this, cur, BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, 1)));
    }

    private final void completeCloseOrCancel() {
        isClosedForSend();
    }

    protected boolean isConflatedDropOldest() {
        return false;
    }

    private final ChannelSegment<E> completeClose(long sendersCur) {
        ChannelSegment<E> channelSegmentCloseLinkedList = closeLinkedList();
        if (isConflatedDropOldest()) {
            long lastBufferedCellGlobalIndex = markAllEmptyCellsAsClosed(channelSegmentCloseLinkedList);
            if (lastBufferedCellGlobalIndex != -1) {
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(lastBufferedCellGlobalIndex);
            }
        }
        cancelSuspendedReceiveRequests(channelSegmentCloseLinkedList, sendersCur);
        return channelSegmentCloseLinkedList;
    }

    private final void completeCancel(long sendersCur) {
        removeUnprocessedElements(completeClose(sendersCur));
    }

    private final ChannelSegment<E> closeLinkedList() {
        Object lastSegment = bufferEndSegment$volatile$FU.get(this);
        ChannelSegment it = (ChannelSegment) sendSegment$volatile$FU.get(this);
        if (it.id > ((ChannelSegment) lastSegment).id) {
            lastSegment = it;
        }
        ChannelSegment it2 = (ChannelSegment) receiveSegment$volatile$FU.get(this);
        if (it2.id > ((ChannelSegment) lastSegment).id) {
            lastSegment = it2;
        }
        return (ChannelSegment) ConcurrentLinkedListKt.close((ConcurrentLinkedListNode) lastSegment);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long markAllEmptyCellsAsClosed(ChannelSegment<E> lastSegment) {
        ChannelSegment<E> channelSegment = lastSegment;
        while (true) {
            int index = BufferedChannelKt.SEGMENT_SIZE;
            while (true) {
                index--;
                if (-1 < index) {
                    long globalIndex = (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) index);
                    if (globalIndex >= getReceiversCounter$kotlinx_coroutines_core()) {
                        while (true) {
                            Object state = channelSegment.getState$kotlinx_coroutines_core(index);
                            if (state == null || state == BufferedChannelKt.IN_BUFFER) {
                                if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                    channelSegment.onSlotCleaned();
                                    break;
                                }
                            } else {
                                if (state != BufferedChannelKt.BUFFERED) {
                                    break;
                                }
                                return globalIndex;
                            }
                        }
                    } else {
                        return -1L;
                    }
                }
            }
            ChannelSegment<E> channelSegment2 = (ChannelSegment) channelSegment.getPrev();
            if (channelSegment2 == null) {
                return -1L;
            }
            channelSegment = channelSegment2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void removeUnprocessedElements(ChannelSegment<E> lastSegment) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        UndeliveredElementException undeliveredElementException = null;
        Object suspendedSenders = InlineList.m8950constructorimpl$default(null, 1, null);
        ChannelSegment<E> channelSegment = lastSegment;
        loop0: while (true) {
            for (int index = BufferedChannelKt.SEGMENT_SIZE - 1; -1 < index; index--) {
                long globalIndex = (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) index);
                while (true) {
                    Object state = channelSegment.getState$kotlinx_coroutines_core(index);
                    if (state == BufferedChannelKt.DONE_RCV) {
                        break loop0;
                    }
                    if (state != BufferedChannelKt.BUFFERED) {
                        if (state == BufferedChannelKt.IN_BUFFER || state == null) {
                            if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                channelSegment.onSlotCleaned();
                                break;
                            }
                        } else if (!(state instanceof Waiter) && !(state instanceof WaiterEB)) {
                            if (state != BufferedChannelKt.RESUMING_BY_EB && state != BufferedChannelKt.RESUMING_BY_RCV) {
                                if (state != BufferedChannelKt.RESUMING_BY_EB) {
                                    break;
                                }
                            } else {
                                break loop0;
                            }
                        } else {
                            if (globalIndex < getReceiversCounter$kotlinx_coroutines_core()) {
                                break loop0;
                            }
                            Waiter sender = state instanceof WaiterEB ? ((WaiterEB) state).waiter : (Waiter) state;
                            if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                if (function1 != null) {
                                    Object element = channelSegment.getElement$kotlinx_coroutines_core(index);
                                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, element, undeliveredElementException);
                                }
                                suspendedSenders = InlineList.m8955plusFjFbRPM(suspendedSenders, sender);
                                channelSegment.cleanElement$kotlinx_coroutines_core(index);
                                channelSegment.onSlotCleaned();
                                break;
                            }
                        }
                    } else {
                        if (globalIndex < getReceiversCounter$kotlinx_coroutines_core()) {
                            break loop0;
                        }
                        if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            if (function1 != null) {
                                Object element2 = channelSegment.getElement$kotlinx_coroutines_core(index);
                                undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, element2, undeliveredElementException);
                            }
                            channelSegment.cleanElement$kotlinx_coroutines_core(index);
                            channelSegment.onSlotCleaned();
                            break;
                        }
                    }
                }
            }
            ChannelSegment<E> channelSegment2 = (ChannelSegment) channelSegment.getPrev();
            if (channelSegment2 == null) {
                break;
            } else {
                channelSegment = channelSegment2;
            }
        }
        if (suspendedSenders != null) {
            if (!(suspendedSenders instanceof ArrayList)) {
                Waiter it = (Waiter) suspendedSenders;
                resumeSenderOnCancelledChannel(it);
            } else {
                Intrinsics.checkNotNull(suspendedSenders, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
                ArrayList list$iv = (ArrayList) suspendedSenders;
                for (int i$iv = list$iv.size() - 1; -1 < i$iv; i$iv--) {
                    Waiter it2 = (Waiter) list$iv.get(i$iv);
                    resumeSenderOnCancelledChannel(it2);
                }
            }
        }
        if (undeliveredElementException != null) {
            UndeliveredElementException it3 = undeliveredElementException;
            throw it3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void cancelSuspendedReceiveRequests(ChannelSegment<E> lastSegment, long sendersCounter) {
        Object suspendedReceivers = InlineList.m8950constructorimpl$default(null, 1, null);
        loop0: for (ChannelSegment<E> channelSegment = lastSegment; channelSegment != null; channelSegment = (ChannelSegment) channelSegment.getPrev()) {
            for (int index = BufferedChannelKt.SEGMENT_SIZE - 1; -1 < index; index--) {
                if ((channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) index) < sendersCounter) {
                    break loop0;
                }
                while (true) {
                    Object state = channelSegment.getState$kotlinx_coroutines_core(index);
                    if (state == null || state == BufferedChannelKt.IN_BUFFER) {
                        if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            channelSegment.onSlotCleaned();
                            break;
                        }
                    } else if (state instanceof WaiterEB) {
                        if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            suspendedReceivers = InlineList.m8955plusFjFbRPM(suspendedReceivers, ((WaiterEB) state).waiter);
                            channelSegment.onCancelledRequest(index, true);
                            break;
                        }
                    } else {
                        if (!(state instanceof Waiter)) {
                            break;
                        }
                        if (channelSegment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                            suspendedReceivers = InlineList.m8955plusFjFbRPM(suspendedReceivers, state);
                            channelSegment.onCancelledRequest(index, true);
                            break;
                        }
                    }
                }
            }
        }
        if (suspendedReceivers == null) {
            return;
        }
        if (!(suspendedReceivers instanceof ArrayList)) {
            Waiter it = (Waiter) suspendedReceivers;
            resumeReceiverOnClosedChannel(it);
            return;
        }
        Intrinsics.checkNotNull(suspendedReceivers, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
        ArrayList list$iv = (ArrayList) suspendedReceivers;
        for (int i$iv = list$iv.size() - 1; -1 < i$iv; i$iv--) {
            Waiter it2 = (Waiter) list$iv.get(i$iv);
            resumeReceiverOnClosedChannel(it2);
        }
    }

    private final void resumeReceiverOnClosedChannel(Waiter $this$resumeReceiverOnClosedChannel) {
        resumeWaiterOnClosedChannel($this$resumeReceiverOnClosedChannel, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter $this$resumeSenderOnCancelledChannel) {
        resumeWaiterOnClosedChannel($this$resumeSenderOnCancelledChannel, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter $this$resumeWaiterOnClosedChannel, boolean receiver) {
        if (!($this$resumeWaiterOnClosedChannel instanceof SendBroadcast)) {
            if (!($this$resumeWaiterOnClosedChannel instanceof CancellableContinuation)) {
                if (!($this$resumeWaiterOnClosedChannel instanceof ReceiveCatching)) {
                    if (!($this$resumeWaiterOnClosedChannel instanceof BufferedChannelIterator)) {
                        if (!($this$resumeWaiterOnClosedChannel instanceof SelectInstance)) {
                            throw new IllegalStateException(("Unexpected waiter: " + $this$resumeWaiterOnClosedChannel).toString());
                        }
                        ((SelectInstance) $this$resumeWaiterOnClosedChannel).trySelect(this, BufferedChannelKt.getCHANNEL_CLOSED());
                        return;
                    }
                    ((BufferedChannelIterator) $this$resumeWaiterOnClosedChannel).tryResumeHasNextOnClosedChannel();
                    return;
                }
                CancellableContinuationImpl<ChannelResult<? extends E>> cancellableContinuationImpl = ((ReceiveCatching) $this$resumeWaiterOnClosedChannel).cont;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m7388constructorimpl(ChannelResult.m8908boximpl(ChannelResult.INSTANCE.m8921closedJP2dKIU(getCloseCause()))));
                return;
            }
            Continuation continuation = (Continuation) $this$resumeWaiterOnClosedChannel;
            Result.Companion companion2 = Result.INSTANCE;
            continuation.resumeWith(Result.m7388constructorimpl(ResultKt.createFailure(receiver ? getReceiveException() : getSendException())));
            return;
        }
        CancellableContinuation<Boolean> cont = ((SendBroadcast) $this$resumeWaiterOnClosedChannel).getCont();
        Result.Companion companion3 = Result.INSTANCE;
        cont.resumeWith(Result.m7388constructorimpl(false));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return isClosedForSend0(sendersAndCloseStatus$volatile$FU.get(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isClosedForSend0(long $this$isClosedForSend0) {
        return isClosed($this$isClosedForSend0, false);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return isClosedForReceive0(sendersAndCloseStatus$volatile$FU.get(this));
    }

    private final boolean isClosedForReceive0(long $this$isClosedForReceive0) {
        return isClosed($this$isClosedForReceive0, true);
    }

    private final boolean isClosed(long sendersAndCloseStatusCur, boolean isClosedForReceive) {
        switch ((int) (sendersAndCloseStatusCur >> 60)) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                completeClose(sendersAndCloseStatusCur & 1152921504606846975L);
                return (isClosedForReceive && hasElements$kotlinx_coroutines_core()) ? false : true;
            case 3:
                completeCancel(sendersAndCloseStatusCur & 1152921504606846975L);
                return true;
            default:
                throw new IllegalStateException(("unexpected close status: " + ((int) (sendersAndCloseStatusCur >> 60))).toString());
        }
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        if (isClosedForReceive() || hasElements$kotlinx_coroutines_core()) {
            return false;
        }
        return !isClosedForReceive();
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
        while (true) {
            ChannelSegment<E> channelSegment = (ChannelSegment) receiveSegment$volatile$FU.get(this);
            long r = getReceiversCounter$kotlinx_coroutines_core();
            long s = getSendersCounter$kotlinx_coroutines_core();
            if (s <= r) {
                return false;
            }
            long id = r / ((long) BufferedChannelKt.SEGMENT_SIZE);
            if (channelSegment.id != id) {
                ChannelSegment<E> channelSegmentFindSegmentReceive = findSegmentReceive(id, channelSegment);
                if (channelSegmentFindSegmentReceive != null) {
                    channelSegment = channelSegmentFindSegmentReceive;
                } else if (((ChannelSegment) receiveSegment$volatile$FU.get(this)).id < id) {
                    return false;
                }
            }
            channelSegment.cleanPrev();
            int i = (int) (r % ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (isCellNonEmpty(channelSegment, i, r)) {
                return true;
            }
            receivers$volatile$FU.compareAndSet(this, r, r + 1);
        }
    }

    private final boolean isCellNonEmpty(ChannelSegment<E> segment, int index, long globalIndex) {
        Object state;
        do {
            state = segment.getState$kotlinx_coroutines_core(index);
            if (state != null && state != BufferedChannelKt.IN_BUFFER) {
                if (state == BufferedChannelKt.BUFFERED) {
                    return true;
                }
                if (state != BufferedChannelKt.INTERRUPTED_SEND && state != BufferedChannelKt.getCHANNEL_CLOSED() && state != BufferedChannelKt.DONE_RCV && state != BufferedChannelKt.POISONED) {
                    if (state == BufferedChannelKt.RESUMING_BY_EB) {
                        return true;
                    }
                    return state != BufferedChannelKt.RESUMING_BY_RCV && globalIndex == getReceiversCounter$kotlinx_coroutines_core();
                }
                return false;
            }
        } while (!segment.casState$kotlinx_coroutines_core(index, state, BufferedChannelKt.POISONED));
        expandBuffer();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> findSegmentSend(long id, ChannelSegment<E> startFrom) {
        Object s$iv;
        boolean z;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = sendSegment$volatile$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(startFrom, id, createNewSegment$iv);
            if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m8965getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                    break;
                }
                if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv.remove();
                }
            }
        } while (!z);
        if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            if (startFrom.id * ((long) BufferedChannelKt.SEGMENT_SIZE) >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            startFrom.cleanPrev();
            return null;
        }
        ChannelSegment<E> channelSegment = (ChannelSegment) SegmentOrClosed.m8965getSegmentimpl(s$iv);
        if (channelSegment.id > id) {
            updateSendersCounterIfLower(channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE) >= getReceiversCounter$kotlinx_coroutines_core()) {
                return null;
            }
            channelSegment.cleanPrev();
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(channelSegment.id == id)) {
                throw new AssertionError();
            }
        }
        return channelSegment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ChannelSegment<E> findSegmentReceive(long id, ChannelSegment<E> startFrom) {
        Object s$iv;
        boolean z;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = receiveSegment$volatile$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(startFrom, id, createNewSegment$iv);
            if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m8965getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                    break;
                }
                if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv.remove();
                }
            }
        } while (!z);
        if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            if (startFrom.id * ((long) BufferedChannelKt.SEGMENT_SIZE) < getSendersCounter$kotlinx_coroutines_core()) {
                startFrom.cleanPrev();
            }
            return null;
        }
        ChannelSegment<E> channelSegment = (ChannelSegment) SegmentOrClosed.m8965getSegmentimpl(s$iv);
        if (!isRendezvousOrUnlimited() && id <= getBufferEndCounter() / ((long) BufferedChannelKt.SEGMENT_SIZE)) {
            AtomicReferenceFieldUpdater handler$atomicfu$iv2 = bufferEndSegment$volatile$FU;
            while (true) {
                Segment cur$iv = (Segment) handler$atomicfu$iv2.get(this);
                if (cur$iv.id >= channelSegment.id || !channelSegment.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv2, this, cur$iv, channelSegment)) {
                    if (!cur$iv.decPointers$kotlinx_coroutines_core()) {
                        break;
                    }
                    cur$iv.remove();
                    break;
                }
                if (channelSegment.decPointers$kotlinx_coroutines_core()) {
                    channelSegment.remove();
                }
            }
        }
        if (channelSegment.id > id) {
            updateReceiversCounterIfLower(channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE));
            if (channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE) < getSendersCounter$kotlinx_coroutines_core()) {
                channelSegment.cleanPrev();
            }
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(channelSegment.id == id)) {
                throw new AssertionError();
            }
        }
        return channelSegment;
    }

    private final ChannelSegment<E> findSegmentBufferEnd(long id, ChannelSegment<E> startFrom, long currentBufferEndCounter) {
        Object s$iv;
        boolean z;
        AtomicReferenceFieldUpdater handler$atomicfu$iv = bufferEndSegment$volatile$FU;
        Function2 createNewSegment$iv = (Function2) BufferedChannelKt.createSegmentFunction();
        do {
            s$iv = ConcurrentLinkedListKt.findSegmentInternal(startFrom, id, createNewSegment$iv);
            if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
                break;
            }
            Segment to$iv$iv = SegmentOrClosed.m8965getSegmentimpl(s$iv);
            while (true) {
                Segment cur$iv$iv = (Segment) handler$atomicfu$iv.get(this);
                if (cur$iv$iv.id >= to$iv$iv.id) {
                    z = true;
                    break;
                }
                if (!to$iv$iv.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                }
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv$iv, to$iv$iv)) {
                    if (cur$iv$iv.decPointers$kotlinx_coroutines_core()) {
                        cur$iv$iv.remove();
                    }
                    z = true;
                    break;
                }
                if (to$iv$iv.decPointers$kotlinx_coroutines_core()) {
                    to$iv$iv.remove();
                }
            }
        } while (!z);
        if (SegmentOrClosed.m8967isClosedimpl(s$iv)) {
            completeCloseOrCancel();
            moveSegmentBufferEndToSpecifiedOrLast(id, startFrom);
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        ChannelSegment<E> channelSegment = (ChannelSegment) SegmentOrClosed.m8965getSegmentimpl(s$iv);
        if (channelSegment.id > id) {
            if (bufferEnd$volatile$FU.compareAndSet(this, currentBufferEndCounter + 1, channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE))) {
                incCompletedExpandBufferAttempts((channelSegment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) - currentBufferEndCounter);
                return null;
            }
            incCompletedExpandBufferAttempts$default(this, 0L, 1, null);
            return null;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(channelSegment.id == id)) {
                throw new AssertionError();
            }
        }
        return channelSegment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void moveSegmentBufferEndToSpecifiedOrLast(long id, ChannelSegment<E> startFrom) {
        boolean z;
        ChannelSegment<E> channelSegment;
        ChannelSegment<E> channelSegment2;
        ChannelSegment<E> channelSegment3 = startFrom;
        while (channelSegment3.id < id && (channelSegment2 = (ChannelSegment) channelSegment3.getNext()) != null) {
            channelSegment3 = channelSegment2;
        }
        while (true) {
            if (!channelSegment3.isRemoved() || (channelSegment = (ChannelSegment) channelSegment3.getNext()) == null) {
                AtomicReferenceFieldUpdater handler$atomicfu$iv = bufferEndSegment$volatile$FU;
                while (true) {
                    Segment cur$iv = (Segment) handler$atomicfu$iv.get(this);
                    z = true;
                    if (cur$iv.id >= channelSegment3.id) {
                        break;
                    }
                    if (!channelSegment3.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(handler$atomicfu$iv, this, cur$iv, channelSegment3)) {
                        if (!cur$iv.decPointers$kotlinx_coroutines_core()) {
                            break;
                        }
                        cur$iv.remove();
                        break;
                    } else if (channelSegment3.decPointers$kotlinx_coroutines_core()) {
                        channelSegment3.remove();
                    }
                }
                if (z) {
                    return;
                }
            } else {
                channelSegment3 = channelSegment;
            }
        }
    }

    private final void updateSendersCounterIfLower(long value) {
        long cur;
        long update;
        AtomicLongFieldUpdater handler$atomicfu$iv = sendersAndCloseStatus$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            long $this$sendersCounter$iv = cur & 1152921504606846975L;
            if ($this$sendersCounter$iv >= value) {
                return;
            } else {
                update = BufferedChannelKt.constructSendersAndCloseStatus($this$sendersCounter$iv, (int) (cur >> 60));
            }
        } while (!sendersAndCloseStatus$volatile$FU.compareAndSet(this, cur, update));
    }

    private final void updateReceiversCounterIfLower(long value) {
        long cur;
        AtomicLongFieldUpdater handler$atomicfu$iv = receivers$volatile$FU;
        do {
            cur = handler$atomicfu$iv.get(this);
            if (cur >= value) {
                return;
            }
        } while (!receivers$volatile$FU.compareAndSet(this, cur, value));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        long $this$sendersCloseStatus$iv = sendersAndCloseStatus$volatile$FU.get(this);
        switch ((int) ($this$sendersCloseStatus$iv >> 60)) {
            case 2:
                sb.append("closed,");
                break;
            case 3:
                sb.append("cancelled,");
                break;
        }
        sb.append("capacity=" + this.capacity + AbstractJsonLexerKt.COMMA);
        sb.append("data=[");
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$volatile$FU.get(this), sendSegment$volatile$FU.get(this), bufferEndSegment$volatile$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            if (it != BufferedChannelKt.NULL_SEGMENT) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList $this$minBy$iv = arrayList;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = iterator$iv.next();
        if (iterator$iv.hasNext()) {
            ChannelSegment it2 = (ChannelSegment) minElem$iv;
            long minValue$iv = it2.id;
            do {
                Object e$iv = iterator$iv.next();
                ChannelSegment it3 = (ChannelSegment) e$iv;
                long v$iv = it3.id;
                if (minValue$iv > v$iv) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            } while (iterator$iv.hasNext());
        }
        ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
        long r = getReceiversCounter$kotlinx_coroutines_core();
        long s = getSendersCounter$kotlinx_coroutines_core();
        ChannelSegment segment = firstSegment;
        while (true) {
            int i = 0;
            int i2 = BufferedChannelKt.SEGMENT_SIZE;
            while (true) {
                if (i < i2) {
                    ChannelSegment firstSegment2 = firstSegment;
                    long globalCellIndex = (segment.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) i);
                    if (globalCellIndex < s || globalCellIndex < r) {
                        Object cellState = segment.getState$kotlinx_coroutines_core(i);
                        Object element = segment.getElement$kotlinx_coroutines_core(i);
                        if (cellState instanceof CancellableContinuation) {
                            string = (globalCellIndex >= r || globalCellIndex < s) ? (globalCellIndex >= s || globalCellIndex < r) ? "cont" : "send" : "receive";
                        } else if (cellState instanceof SelectInstance) {
                            string = (globalCellIndex >= r || globalCellIndex < s) ? (globalCellIndex >= s || globalCellIndex < r) ? "select" : "onSend" : "onReceive";
                        } else if (cellState instanceof ReceiveCatching) {
                            string = "receiveCatching";
                        } else if (cellState instanceof SendBroadcast) {
                            string = "sendBroadcast";
                        } else if (cellState instanceof WaiterEB) {
                            string = "EB(" + cellState + ')';
                        } else if (Intrinsics.areEqual(cellState, BufferedChannelKt.RESUMING_BY_RCV) || Intrinsics.areEqual(cellState, BufferedChannelKt.RESUMING_BY_EB)) {
                            string = "resuming_sender";
                        } else {
                            if (cellState != null && !Intrinsics.areEqual(cellState, BufferedChannelKt.IN_BUFFER) && !Intrinsics.areEqual(cellState, BufferedChannelKt.DONE_RCV) && !Intrinsics.areEqual(cellState, BufferedChannelKt.POISONED) && !Intrinsics.areEqual(cellState, BufferedChannelKt.INTERRUPTED_RCV) && !Intrinsics.areEqual(cellState, BufferedChannelKt.INTERRUPTED_SEND) && !Intrinsics.areEqual(cellState, BufferedChannelKt.getCHANNEL_CLOSED())) {
                                string = cellState.toString();
                            }
                            i++;
                            firstSegment = firstSegment2;
                        }
                        String cellStateString = string;
                        if (element != null) {
                            sb.append('(' + cellStateString + AbstractJsonLexerKt.COMMA + element + "),");
                        } else {
                            sb.append(cellStateString + AbstractJsonLexerKt.COMMA);
                        }
                        i++;
                        firstSegment = firstSegment2;
                    }
                } else {
                    ChannelSegment firstSegment3 = firstSegment;
                    ChannelSegment channelSegment = (ChannelSegment) segment.getNext();
                    if (channelSegment != null) {
                        segment = channelSegment;
                        firstSegment = firstSegment3;
                    }
                }
            }
        }
        if (StringsKt.last(sb) == ',') {
            Intrinsics.checkNotNullExpressionValue(sb.deleteCharAt(sb.length() - 1), "deleteCharAt(...)");
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toStringDebug$kotlinx_coroutines_core() {
        String cellStateString;
        StringBuilder sb = new StringBuilder();
        StringBuilder sbAppend = new StringBuilder().append("S=").append(getSendersCounter$kotlinx_coroutines_core()).append(",R=").append(getReceiversCounter$kotlinx_coroutines_core()).append(",B=").append(getBufferEndCounter()).append(",B'=").append(completedExpandBuffersAndPauseFlag$volatile$FU.get(this)).append(",C=");
        long $this$sendersCloseStatus$iv = sendersAndCloseStatus$volatile$FU.get(this);
        sb.append(sbAppend.append((int) ($this$sendersCloseStatus$iv >> 60)).append(AbstractJsonLexerKt.COMMA).toString());
        long $this$sendersCloseStatus$iv2 = sendersAndCloseStatus$volatile$FU.get(this);
        int $i$f$getSendersCloseStatus = (int) ($this$sendersCloseStatus$iv2 >> 60);
        switch ($i$f$getSendersCloseStatus) {
            case 1:
                sb.append("CANCELLATION_STARTED,");
                break;
            case 2:
                sb.append("CLOSED,");
                break;
            case 3:
                sb.append("CANCELLED,");
                break;
        }
        sb.append("SEND_SEGM=" + DebugStringsKt.getHexAddress(sendSegment$volatile$FU.get(this)) + ",RCV_SEGM=" + DebugStringsKt.getHexAddress(receiveSegment$volatile$FU.get(this)));
        if (!isRendezvousOrUnlimited()) {
            sb.append(",EB_SEGM=" + DebugStringsKt.getHexAddress(bufferEndSegment$volatile$FU.get(this)));
        }
        sb.append("  ");
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$volatile$FU.get(this), sendSegment$volatile$FU.get(this), bufferEndSegment$volatile$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            if (it != BufferedChannelKt.NULL_SEGMENT) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList $this$minBy$iv = arrayList;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = iterator$iv.next();
        if (iterator$iv.hasNext()) {
            ChannelSegment it2 = (ChannelSegment) minElem$iv;
            long minValue$iv = it2.id;
            do {
                Object e$iv = iterator$iv.next();
                ChannelSegment it3 = (ChannelSegment) e$iv;
                long v$iv = it3.id;
                if (minValue$iv > v$iv) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            } while (iterator$iv.hasNext());
        }
        ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
        ChannelSegment channelSegment = firstSegment;
        while (true) {
            StringBuilder sbAppend2 = new StringBuilder().append(DebugStringsKt.getHexAddress(channelSegment)).append("=[").append(channelSegment.isRemoved() ? "*" : "").append(channelSegment.id).append(",prev=");
            ChannelSegment channelSegment2 = (ChannelSegment) channelSegment.getPrev();
            sb.append(sbAppend2.append(channelSegment2 != null ? DebugStringsKt.getHexAddress(channelSegment2) : null).append(AbstractJsonLexerKt.COMMA).toString());
            int i = BufferedChannelKt.SEGMENT_SIZE;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2;
                Object cellState = channelSegment.getState$kotlinx_coroutines_core(i3);
                Object element = channelSegment.getElement$kotlinx_coroutines_core(i3);
                if (cellState instanceof CancellableContinuation) {
                    cellStateString = "cont";
                } else if (cellState instanceof SelectInstance) {
                    cellStateString = "select";
                } else if (cellState instanceof ReceiveCatching) {
                    cellStateString = "receiveCatching";
                } else if (cellState instanceof SendBroadcast) {
                    cellStateString = "send(broadcast)";
                } else {
                    cellStateString = cellState instanceof WaiterEB ? "EB(" + cellState + ')' : String.valueOf(cellState);
                }
                sb.append(AbstractJsonLexerKt.BEGIN_LIST + i3 + "]=(" + cellStateString + AbstractJsonLexerKt.COMMA + element + "),");
            }
            StringBuilder sbAppend3 = new StringBuilder().append("next=");
            ChannelSegment channelSegment3 = (ChannelSegment) channelSegment.getNext();
            sb.append(sbAppend3.append(channelSegment3 != null ? DebugStringsKt.getHexAddress(channelSegment3) : null).append("]  ").toString());
            ChannelSegment channelSegment4 = (ChannelSegment) channelSegment.getNext();
            if (channelSegment4 == null) {
                return sb.toString();
            }
            channelSegment = channelSegment4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:51:0x0123  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void checkSegmentStructureInvariants() {
        boolean z;
        if (isRendezvousOrUnlimited()) {
            if (!(bufferEndSegment$volatile$FU.get(this) == BufferedChannelKt.NULL_SEGMENT)) {
                throw new IllegalStateException(("bufferEndSegment must be NULL_SEGMENT for rendezvous and unlimited channels; they do not manipulate it.\nChannel state: " + this).toString());
            }
        } else if (!(((ChannelSegment) receiveSegment$volatile$FU.get(this)).id <= ((ChannelSegment) bufferEndSegment$volatile$FU.get(this)).id)) {
            throw new IllegalStateException(("bufferEndSegment should not have lower id than receiveSegment.\nChannel state: " + this).toString());
        }
        Iterable $this$filter$iv = CollectionsKt.listOf((Object[]) new ChannelSegment[]{receiveSegment$volatile$FU.get(this), sendSegment$volatile$FU.get(this), bufferEndSegment$volatile$FU.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            ChannelSegment it = (ChannelSegment) element$iv$iv;
            if (it != BufferedChannelKt.NULL_SEGMENT) {
                arrayList.add(element$iv$iv);
            }
        }
        ArrayList destination$iv$iv = arrayList;
        ArrayList $this$minBy$iv = destination$iv$iv;
        Iterator iterator$iv = $this$minBy$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new NoSuchElementException();
        }
        Object minElem$iv = iterator$iv.next();
        if (iterator$iv.hasNext()) {
            ChannelSegment it2 = (ChannelSegment) minElem$iv;
            long minValue$iv = it2.id;
            do {
                Object e$iv = iterator$iv.next();
                ChannelSegment it3 = (ChannelSegment) e$iv;
                long v$iv = it3.id;
                if (minValue$iv > v$iv) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            } while (iterator$iv.hasNext());
        }
        ChannelSegment firstSegment = (ChannelSegment) minElem$iv;
        if (!(firstSegment.getPrev() == 0)) {
            throw new IllegalStateException(("All processed segments should be unreachable from the data structure, but the `prev` link of the leftmost segment is non-null.\nChannel state: " + this).toString());
        }
        ChannelSegment segment = firstSegment;
        while (segment.getNext() != 0) {
            S next = segment.getNext();
            Intrinsics.checkNotNull(next);
            if (((ChannelSegment) next).getPrev() != 0) {
                S next2 = segment.getNext();
                Intrinsics.checkNotNull(next2);
                if (((ChannelSegment) next2).getPrev() == segment) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = true;
            }
            if (!z) {
                throw new IllegalStateException(("The `segment.next.prev === segment` invariant is violated.\nChannel state: " + this).toString());
            }
            int interruptedOrClosedCells = 0;
            int i = BufferedChannelKt.SEGMENT_SIZE;
            for (int i2 = 0; i2 < i; i2++) {
                Object state = segment.getState$kotlinx_coroutines_core(i2);
                if (!Intrinsics.areEqual(state, BufferedChannelKt.BUFFERED) && !(state instanceof Waiter)) {
                    if (Intrinsics.areEqual(state, BufferedChannelKt.INTERRUPTED_RCV) || Intrinsics.areEqual(state, BufferedChannelKt.INTERRUPTED_SEND) || Intrinsics.areEqual(state, BufferedChannelKt.getCHANNEL_CLOSED())) {
                        if (!(segment.getElement$kotlinx_coroutines_core(i2) == null)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        interruptedOrClosedCells++;
                    } else {
                        if (!Intrinsics.areEqual(state, BufferedChannelKt.POISONED) && !Intrinsics.areEqual(state, BufferedChannelKt.DONE_RCV)) {
                            throw new IllegalStateException(("Unexpected segment cell state: " + state + ".\nChannel state: " + this).toString());
                        }
                        if (!(segment.getElement$kotlinx_coroutines_core(i2) == null)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                    }
                }
            }
            int i3 = BufferedChannelKt.SEGMENT_SIZE;
            if (interruptedOrClosedCells == i3) {
                if (!(segment == receiveSegment$volatile$FU.get(this) || segment == sendSegment$volatile$FU.get(this) || segment == bufferEndSegment$volatile$FU.get(this))) {
                    throw new IllegalStateException(("Logically removed segment is reachable.\nChannel state: " + this).toString());
                }
            }
            S next3 = segment.getNext();
            Intrinsics.checkNotNull(next3);
            segment = (ChannelSegment) next3;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.BufferedChannel$bindCancellationFunResult$1, reason: invalid class name */
    /* JADX INFO: compiled from: BufferedChannel.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<Throwable, ChannelResult<? extends E>, CoroutineContext, Unit> {
        AnonymousClass1(Object obj) {
            super(3, obj, BufferedChannel.class, "onCancellationChannelResultImplDoNotCall", "onCancellationChannelResultImplDoNotCall-5_sEAP8(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th, Object p2, CoroutineContext coroutineContext) {
            m8903invoke5_sEAP8(th, ((ChannelResult) p2).getHolder(), coroutineContext);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke-5_sEAP8, reason: not valid java name */
        public final void m8903invoke5_sEAP8(Throwable p0, Object p1, CoroutineContext p2) {
            ((BufferedChannel) this.receiver).m8897onCancellationChannelResultImplDoNotCall5_sEAP8(p0, p1, p2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KFunction<Unit> bindCancellationFunResult(Function1<? super E, Unit> function1) {
        return new AnonymousClass1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCancellationChannelResultImplDoNotCall-5_sEAP8, reason: not valid java name */
    public final void m8897onCancellationChannelResultImplDoNotCall5_sEAP8(Throwable cause, Object element, CoroutineContext context) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        Intrinsics.checkNotNull(function1);
        Object objM8913getOrNullimpl = ChannelResult.m8913getOrNullimpl(element);
        Intrinsics.checkNotNull(objM8913getOrNullimpl);
        OnUndeliveredElementKt.callUndeliveredElement(function1, objM8913getOrNullimpl, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Function3<Throwable, Object, CoroutineContext, Unit> bindCancellationFun(final Function1<? super E, Unit> function1, final E e) {
        return new Function3() { // from class: kotlinx.coroutines.channels.BufferedChannel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return BufferedChannel.bindCancellationFun$lambda$89(function1, e, (Throwable) obj, obj2, (CoroutineContext) obj3);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindCancellationFun$lambda$89(Function1 $this_bindCancellationFun, Object $element, Throwable th, Object obj, CoroutineContext context) {
        OnUndeliveredElementKt.callUndeliveredElement($this_bindCancellationFun, $element, context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.BufferedChannel$bindCancellationFun$2, reason: invalid class name */
    /* JADX INFO: compiled from: BufferedChannel.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function3<Throwable, E, CoroutineContext, Unit> {
        AnonymousClass2(Object obj) {
            super(3, obj, BufferedChannel.class, "onCancellationImplDoNotCall", "onCancellationImplDoNotCall(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th, Object p2, CoroutineContext coroutineContext) {
            invoke2(th, p2, coroutineContext);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable p0, E e, CoroutineContext p2) {
            ((BufferedChannel) this.receiver).onCancellationImplDoNotCall(p0, e, p2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KFunction<Unit> bindCancellationFun(Function1<? super E, Unit> function1) {
        return new AnonymousClass2(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCancellationImplDoNotCall(Throwable cause, E element, CoroutineContext context) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        Intrinsics.checkNotNull(function1);
        OnUndeliveredElementKt.callUndeliveredElement(function1, element, context);
    }
}
