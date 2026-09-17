package kotlinx.coroutines.channels;

import ai.nous.aurora.presentation.theme.AuroraMotion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;

/* JADX INFO: compiled from: BroadcastChannel.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u000256B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016J\u0016\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0002J\u0016\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u001b2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001e\u0010\u001e\u001a\u00020\u00152\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0017\u0010'\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0010¢\u0006\u0002\b(J\b\u00103\u001a\u000204H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00060\fj\u0002`\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010!\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030 \u0012\u0006\u0012\u0004\u0018\u00010\u00110\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010)\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0019\u00100\u001a\u0004\u0018\u00018\u00008F¢\u0006\f\u0012\u0004\b1\u0010-\u001a\u0004\b2\u0010/¨\u00067"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl;", "E", "Lkotlinx/coroutines/channels/BufferedChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "capacity", "", "<init>", "(I)V", "getCapacity", "()I", "lock", "Lkotlinx/coroutines/internal/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "subscribers", "", "lastConflatedElement", "", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "removeSubscriber", "", "s", "send", "element", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "registerSelectForSend", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "onSendInternalResult", "Ljava/util/HashMap;", "close", "", "cause", "", "cancelImpl", "cancelImpl$kotlinx_coroutines_core", "isClosedForSend", "()Z", "value", "getValue$annotations", "()V", "getValue", "()Ljava/lang/Object;", "valueOrNull", "getValueOrNull$annotations", "getValueOrNull", "toString", "", "SubscriberBuffered", "SubscriberConflated", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BroadcastChannelImpl<E> extends BufferedChannel<E> implements BroadcastChannel<E> {
    private final int capacity;
    private Object lastConflatedElement;
    private final ReentrantLock lock;
    private final HashMap<SelectInstance<?>, Object> onSendInternalResult;
    private List<? extends BufferedChannel<E>> subscribers;

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.BroadcastChannelImpl$send$1, reason: invalid class name */
    /* JADX INFO: compiled from: BroadcastChannel.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.BroadcastChannelImpl", f = "BroadcastChannel.kt", i = {0, 0}, l = {179}, m = "send", n = {"this", "element"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ BroadcastChannelImpl<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(BroadcastChannelImpl<E> broadcastChannelImpl, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = broadcastChannelImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.send(null, this);
        }
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    public static /* synthetic */ void getValueOrNull$annotations() {
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public BroadcastChannelImpl(int capacity) {
        super(0, null);
        this.capacity = capacity;
        if (!(this.capacity >= 1 || this.capacity == -1)) {
            throw new IllegalArgumentException(("BroadcastChannel capacity must be positive or Channel.CONFLATED, but " + this.capacity + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        this.subscribers = CollectionsKt.emptyList();
        this.lastConflatedElement = BroadcastChannelKt.NO_ELEMENT;
        this.onSendInternalResult = new HashMap<>();
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel<E> openSubscription() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            BufferedChannel s = this.capacity == -1 ? new SubscriberConflated() : new SubscriberBuffered();
            if (!isClosedForSend() || this.lastConflatedElement != BroadcastChannelKt.NO_ELEMENT) {
                if (this.lastConflatedElement != BroadcastChannelKt.NO_ELEMENT) {
                    s.mo8893trySendJP2dKIU(getValue());
                }
                this.subscribers = CollectionsKt.plus((Collection<? extends BufferedChannel>) this.subscribers, s);
                return s;
            }
            s.close(getCloseCause());
            return s;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSubscriber(ReceiveChannel<? extends E> s) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Iterable $this$filter$iv = this.subscribers;
            ArrayList arrayList = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                BufferedChannel it = (BufferedChannel) element$iv$iv;
                if (it != s) {
                    arrayList.add(element$iv$iv);
                }
            }
            this.subscribers = arrayList;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0072  */
    /* JADX WARN: Code duplicated, block: B:25:0x0088 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x0089  */
    /* JADX WARN: Code duplicated, block: B:29:0x0095  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0089 -> B:27:0x008d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public java.lang.Object send(E r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.channels.BroadcastChannelImpl.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            kotlinx.coroutines.channels.BroadcastChannelImpl$send$1 r0 = (kotlinx.coroutines.channels.BroadcastChannelImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.channels.BroadcastChannelImpl$send$1 r0 = new kotlinx.coroutines.channels.BroadcastChannelImpl$send$1
            r0.<init>(r10, r12)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L3f;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L2c:
            r11 = 0
            r3 = 0
            java.lang.Object r4 = r0.L$2
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r0.L$1
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.BroadcastChannelImpl r6 = (kotlinx.coroutines.channels.BroadcastChannelImpl) r6
            kotlin.ResultKt.throwOnFailure(r1)
            r7 = r3
            r3 = r2
            r2 = r1
            goto L8d
        L3f:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r10
            java.util.concurrent.locks.ReentrantLock r4 = r3.lock
            r5 = 0
            r6 = r4
            java.util.concurrent.locks.Lock r6 = (java.util.concurrent.locks.Lock) r6
            r6.lock()
            r4 = 0
            boolean r7 = r3.isClosedForSend()     // Catch: java.lang.Throwable -> Lae
            if (r7 != 0) goto La9
            int r7 = r3.capacity     // Catch: java.lang.Throwable -> Lae
            r8 = -1
            if (r7 != r8) goto L5a
            r3.lastConflatedElement = r11     // Catch: java.lang.Throwable -> Lae
        L5a:
            java.util.List<? extends kotlinx.coroutines.channels.BufferedChannel<E>> r7 = r3.subscribers     // Catch: java.lang.Throwable -> Lae
            r6.unlock()
            r4 = r7
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            r5 = 0
            java.util.Iterator r6 = r4.iterator()
            r4 = r6
            r6 = r3
            r9 = r5
            r5 = r11
            r11 = r9
        L6c:
            boolean r3 = r4.hasNext()
            if (r3 == 0) goto La5
            java.lang.Object r3 = r4.next()
            kotlinx.coroutines.channels.BufferedChannel r3 = (kotlinx.coroutines.channels.BufferedChannel) r3
            r7 = 0
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r4
            r8 = 1
            r0.label = r8
            java.lang.Object r3 = r3.sendBroadcast$kotlinx_coroutines_core(r5, r0)
            if (r3 != r2) goto L89
            return r2
        L89:
            r9 = r2
            r2 = r1
            r1 = r3
            r3 = r9
        L8d:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto La1
            boolean r8 = r6.isClosedForSend()
            if (r8 != 0) goto L9c
            goto La1
        L9c:
            java.lang.Throwable r3 = r6.getSendException()
            throw r3
        La1:
            r1 = r2
            r2 = r3
            goto L6c
        La5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        La9:
            java.lang.Throwable r2 = r3.getSendException()     // Catch: java.lang.Throwable -> Lae
            throw r2     // Catch: java.lang.Throwable -> Lae
        Lae:
            r11 = move-exception
            r6.unlock()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastChannelImpl.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: trySend-JP2dKIU, reason: not valid java name */
    public Object mo8893trySendJP2dKIU(E element) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            if (isClosedForSend()) {
                Object objMo8893trySendJP2dKIU = super.mo8893trySendJP2dKIU(element);
                reentrantLock.unlock();
                return objMo8893trySendJP2dKIU;
            }
            Iterable $this$any$iv = this.subscribers;
            boolean z = false;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                for (Object element$iv : $this$any$iv) {
                    BufferedChannel it = (BufferedChannel) element$iv;
                    if (it.shouldSendSuspend$kotlinx_coroutines_core()) {
                        z = true;
                        break;
                    }
                }
            }
            boolean shouldSuspend = z;
            if (shouldSuspend) {
                Object objM8922failurePtdJZtk = ChannelResult.INSTANCE.m8922failurePtdJZtk();
                reentrantLock.unlock();
                return objM8922failurePtdJZtk;
            }
            if (this.capacity == -1) {
                this.lastConflatedElement = element;
            }
            Iterable $this$forEach$iv = this.subscribers;
            for (Object element$iv2 : $this$forEach$iv) {
                BufferedChannel it2 = (BufferedChannel) element$iv2;
                it2.mo8893trySendJP2dKIU(element);
            }
            Object objM8923successJP2dKIU = ChannelResult.INSTANCE.m8923successJP2dKIU(Unit.INSTANCE);
            reentrantLock.unlock();
            return objM8923successJP2dKIU;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    protected void registerSelectForSend(SelectInstance<?> select, Object element) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Object result = this.onSendInternalResult.remove(select);
            if (result != null) {
                select.selectInRegistrationPhase(result);
                reentrantLock.unlock();
            } else {
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(select.getContext()), null, CoroutineStart.UNDISPATCHED, new AnonymousClass2(this, element, select, null), 1, null);
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.channels.BroadcastChannelImpl$registerSelectForSend$2, reason: invalid class name */
    /* JADX INFO: compiled from: BroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.BroadcastChannelImpl$registerSelectForSend$2", f = "BroadcastChannel.kt", i = {}, l = {AuroraMotion.TAB_MS}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Object $element;
        final /* synthetic */ SelectInstance<?> $select;
        int label;
        final /* synthetic */ BroadcastChannelImpl<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(BroadcastChannelImpl<E> broadcastChannelImpl, Object obj, SelectInstance<?> selectInstance, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = broadcastChannelImpl;
            this.$element = obj;
            this.$select = selectInstance;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$element, this.$select, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:29:0x0064 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:27:0x005e, B:29:0x0064, B:34:0x0073, B:35:0x0078, B:36:0x0079, B:38:0x0081, B:40:0x0088, B:42:0x00a0, B:43:0x00a7, B:39:0x0084), top: B:50:0x005e }] */
        /* JADX WARN: Code duplicated, block: B:31:0x006f  */
        /* JADX WARN: Code duplicated, block: B:33:0x0072  */
        /* JADX WARN: Code duplicated, block: B:34:0x0073 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:27:0x005e, B:29:0x0064, B:34:0x0073, B:35:0x0078, B:36:0x0079, B:38:0x0081, B:40:0x0088, B:42:0x00a0, B:43:0x00a7, B:39:0x0084), top: B:50:0x005e }] */
        /* JADX WARN: Code duplicated, block: B:38:0x0081 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:27:0x005e, B:29:0x0064, B:34:0x0073, B:35:0x0078, B:36:0x0079, B:38:0x0081, B:40:0x0088, B:42:0x00a0, B:43:0x00a7, B:39:0x0084), top: B:50:0x005e }] */
        /* JADX WARN: Code duplicated, block: B:39:0x0084 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:27:0x005e, B:29:0x0064, B:34:0x0073, B:35:0x0078, B:36:0x0079, B:38:0x0081, B:40:0x0088, B:42:0x00a0, B:43:0x00a7, B:39:0x0084), top: B:50:0x005e }] */
        /* JADX WARN: Code duplicated, block: B:42:0x00a0 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:27:0x005e, B:29:0x0064, B:34:0x0073, B:35:0x0078, B:36:0x0079, B:38:0x0081, B:40:0x0088, B:42:0x00a0, B:43:0x00a7, B:39:0x0084), top: B:50:0x005e }] */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            AnonymousClass2 anonymousClass2;
            boolean z;
            BroadcastChannelImpl<E> broadcastChannelImpl;
            SelectInstance<?> selectInstance;
            ReentrantLock reentrantLock;
            Object channel_closed;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    try {
                        BroadcastChannelImpl<E> broadcastChannelImpl2 = this.this$0;
                        Object obj2 = this.$element;
                        this.label = 1;
                        if (broadcastChannelImpl2.send((E) obj2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        anonymousClass2 = this;
                        z = true;
                        ReentrantLock reentrantLock2 = ((BroadcastChannelImpl) anonymousClass2.this$0).lock;
                        broadcastChannelImpl = anonymousClass2.this$0;
                        selectInstance = anonymousClass2.$select;
                        reentrantLock = reentrantLock2;
                        reentrantLock.lock();
                        try {
                            if (DebugKt.getASSERTIONS_ENABLED()) {
                                if (((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult.get(selectInstance) == null) {
                                    throw new AssertionError();
                                }
                            }
                            HashMap map = ((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult;
                            if (z) {
                                channel_closed = Unit.INSTANCE;
                            } else {
                                channel_closed = BufferedChannelKt.getCHANNEL_CLOSED();
                            }
                            map.put(selectInstance, channel_closed);
                            Intrinsics.checkNotNull(selectInstance, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
                            if (((SelectImplementation) selectInstance).trySelectDetailed(broadcastChannelImpl, Unit.INSTANCE) != TrySelectDetailedResult.REREGISTER) {
                                ((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult.remove(selectInstance);
                            }
                            Unit unit = Unit.INSTANCE;
                            reentrantLock.unlock();
                            return Unit.INSTANCE;
                        } catch (Throwable th2) {
                            reentrantLock.unlock();
                            throw th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        anonymousClass2 = this;
                        if (anonymousClass2.this$0.isClosedForSend() || (!(th instanceof ClosedSendChannelException) && anonymousClass2.this$0.getSendException() != th)) {
                            throw th;
                        }
                        z = false;
                    }
                    break;
                case 1:
                    anonymousClass2 = this;
                    try {
                        ResultKt.throwOnFailure(obj);
                        z = true;
                    } catch (Throwable th4) {
                        th = th4;
                        if (anonymousClass2.this$0.isClosedForSend()) {
                            break;
                        }
                        throw th;
                    }
                    ReentrantLock reentrantLock3 = ((BroadcastChannelImpl) anonymousClass2.this$0).lock;
                    broadcastChannelImpl = anonymousClass2.this$0;
                    selectInstance = anonymousClass2.$select;
                    reentrantLock = reentrantLock3;
                    reentrantLock.lock();
                    if (DebugKt.getASSERTIONS_ENABLED()) {
                        if (((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult.get(selectInstance) == null) {
                            throw new AssertionError();
                        }
                    }
                    HashMap map2 = ((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult;
                    if (z) {
                        channel_closed = Unit.INSTANCE;
                    } else {
                        channel_closed = BufferedChannelKt.getCHANNEL_CLOSED();
                    }
                    map2.put(selectInstance, channel_closed);
                    Intrinsics.checkNotNull(selectInstance, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
                    if (((SelectImplementation) selectInstance).trySelectDetailed(broadcastChannelImpl, Unit.INSTANCE) != TrySelectDetailedResult.REREGISTER) {
                        ((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult.remove(selectInstance);
                    }
                    Unit unit2 = Unit.INSTANCE;
                    reentrantLock.unlock();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable cause) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Iterable $this$forEach$iv = this.subscribers;
            for (Object element$iv : $this$forEach$iv) {
                BufferedChannel it = (BufferedChannel) element$iv;
                it.close(cause);
            }
            Iterable $this$forEach$iv2 = this.subscribers;
            Iterable $this$filter$iv = $this$forEach$iv2;
            ArrayList arrayList = new ArrayList();
            for (Object element$iv$iv : $this$filter$iv) {
                BufferedChannel it2 = (BufferedChannel) element$iv$iv;
                if (it2.hasElements$kotlinx_coroutines_core()) {
                    arrayList.add(element$iv$iv);
                }
            }
            this.subscribers = arrayList;
            return super.close(cause);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            Iterable $this$forEach$iv = this.subscribers;
            for (Object element$iv : $this$forEach$iv) {
                BufferedChannel it = (BufferedChannel) element$iv;
                it.cancelImpl$kotlinx_coroutines_core(cause);
            }
            this.lastConflatedElement = BroadcastChannelKt.NO_ELEMENT;
            return super.cancelImpl$kotlinx_coroutines_core(cause);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        ReentrantLock $this$withLock$iv = this.lock;
        ReentrantLock reentrantLock = $this$withLock$iv;
        reentrantLock.lock();
        try {
            return super.isClosedForSend();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: compiled from: BroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberBuffered;", "Lkotlinx/coroutines/channels/BufferedChannel;", "<init>", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "cancelImpl", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class SubscriberBuffered extends BufferedChannel<E> {
        /* JADX WARN: Multi-variable type inference failed */
        public SubscriberBuffered() {
            super(BroadcastChannelImpl.this.getCapacity(), null, 2, 0 == true ? 1 : 0);
        }

        @Override // kotlinx.coroutines.channels.BufferedChannel
        /* JADX INFO: renamed from: cancelImpl, reason: merged with bridge method [inline-methods] */
        public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
            ReentrantLock $this$withLock$iv = ((BroadcastChannelImpl) BroadcastChannelImpl.this).lock;
            BroadcastChannelImpl<E> broadcastChannelImpl = BroadcastChannelImpl.this;
            ReentrantLock reentrantLock = $this$withLock$iv;
            reentrantLock.lock();
            try {
                broadcastChannelImpl.removeSubscriber(this);
                return super.cancelImpl$kotlinx_coroutines_core(cause);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: BroadcastChannel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberConflated;", "Lkotlinx/coroutines/channels/ConflatedBufferedChannel;", "<init>", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "cancelImpl", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class SubscriberConflated extends ConflatedBufferedChannel<E> {
        public SubscriberConflated() {
            super(1, BufferOverflow.DROP_OLDEST, null, 4, null);
        }

        @Override // kotlinx.coroutines.channels.BufferedChannel
        /* JADX INFO: renamed from: cancelImpl, reason: merged with bridge method [inline-methods] */
        public boolean cancelImpl$kotlinx_coroutines_core(Throwable cause) {
            BroadcastChannelImpl.this.removeSubscriber(this);
            return super.cancelImpl$kotlinx_coroutines_core(cause);
        }
    }

    public final E getValue() throws Throwable {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!isClosedForSend()) {
                if (this.lastConflatedElement == BroadcastChannelKt.NO_ELEMENT) {
                    throw new IllegalStateException("No value".toString());
                }
                E e = (E) this.lastConflatedElement;
                reentrantLock.unlock();
                return e;
            }
            Throwable closeCause = getCloseCause();
            if (closeCause == null) {
                throw new IllegalStateException("This broadcast channel is closed");
            }
            throw closeCause;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final E getValueOrNull() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = null;
            if (!isClosedForReceive() && this.lastConflatedElement != BroadcastChannelKt.NO_ELEMENT) {
                e = (E) this.lastConflatedElement;
            }
            return e;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public String toString() {
        return (this.lastConflatedElement != BroadcastChannelKt.NO_ELEMENT ? "CONFLATED_ELEMENT=" + this.lastConflatedElement + "; " : "") + "BROADCAST=<" + super.toString() + ">; SUBSCRIBERS=" + CollectionsKt.joinToString$default(this.subscribers, ";", "<", ">", 0, null, null, 56, null);
    }
}
