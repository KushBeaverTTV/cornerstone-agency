package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.core.util.Consumer;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.WorkerExceptionInfo;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.WorkerExceptionUtilsKt;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\tJ&\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runWorker", "delegate", "Landroidx/work/ListenableWorker;", "workConstraintsTracker", "Landroidx/work/impl/constraints/WorkConstraintsTracker;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "(Landroidx/work/ListenableWorker;Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupAndRunConstraintTrackingWork", "ConstraintUnsatisfiedException", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ConstraintTrackingWorker extends CoroutineWorker {
    private final WorkerParameters workerParameters;

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$1, reason: invalid class name */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker", f = "ConstraintTrackingWorker.kt", i = {}, l = {125}, m = "runWorker", n = {}, s = {})
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
            return ConstraintTrackingWorker.this.runWorker(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker", f = "ConstraintTrackingWorker.kt", i = {0, 0}, l = {97}, m = "setupAndRunConstraintTrackingWork", n = {"this", "delegate"}, s = {"L$0", "L$1"})
    static final class C08011 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C08011(Continuation<? super C08011> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConstraintTrackingWorker.this.setupAndRunConstraintTrackingWork(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker(Context appContext, WorkerParameters workerParameters) {
        super(appContext, workerParameters);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParameters");
        this.workerParameters = workerParameters;
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker$doWork$2", f = "ConstraintTrackingWorker.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ConstraintTrackingWorker.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object obj = ConstraintTrackingWorker.this.setupAndRunConstraintTrackingWork(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return obj;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // androidx.work.CoroutineWorker
    public Object doWork(Continuation<? super ListenableWorker.Result> continuation) {
        Executor backgroundExecutor = getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(backgroundExecutor, "backgroundExecutor");
        return BuildersKt.withContext(ExecutorsKt.from(backgroundExecutor), new AnonymousClass2(null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.work.ListenableWorker] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v8 */
    public final Object setupAndRunConstraintTrackingWork(Continuation<? super ListenableWorker.Result> continuation) {
        C08011 c08011;
        int stopReason;
        ConstraintTrackingWorker constraintTrackingWorker;
        Object objWithContext;
        if (continuation instanceof C08011) {
            c08011 = (C08011) continuation;
            if ((c08011.label & Integer.MIN_VALUE) != 0) {
                c08011.label -= Integer.MIN_VALUE;
            } else {
                c08011 = new C08011(continuation);
            }
        } else {
            c08011 = new C08011(continuation);
        }
        C08011 c08012 = c08011;
        Object obj = c08012.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r5 = c08012.label;
        try {
            switch (r5) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    constraintTrackingWorker = this;
                    String string = constraintTrackingWorker.getInputData().getString(ConstraintTrackingWorkerKt.ARGUMENT_CLASS_NAME);
                    String str = string;
                    if (!(str == null || str.length() == 0)) {
                        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(constraintTrackingWorker.getApplicationContext());
                        Intrinsics.checkNotNullExpressionValue(workManagerImpl, "getInstance(applicationContext)");
                        WorkSpecDao workSpecDao = workManagerImpl.getWorkDatabase().workSpecDao();
                        String string2 = constraintTrackingWorker.getId().toString();
                        Intrinsics.checkNotNullExpressionValue(string2, "id.toString()");
                        WorkSpec workSpec = workSpecDao.getWorkSpec(string2);
                        if (workSpec == null) {
                            ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                            Intrinsics.checkNotNullExpressionValue(resultFailure, "failure()");
                            return resultFailure;
                        }
                        Trackers trackers = workManagerImpl.getTrackers();
                        Intrinsics.checkNotNullExpressionValue(trackers, "workManagerImpl.trackers");
                        WorkConstraintsTracker workConstraintsTracker = new WorkConstraintsTracker(trackers);
                        if (!workConstraintsTracker.areAllConstraintsMet(workSpec)) {
                            Logger.get().debug(ConstraintTrackingWorkerKt.TAG, "Constraints not met for delegate " + string + ". Requesting retry.");
                            ListenableWorker.Result resultRetry = ListenableWorker.Result.retry();
                            Intrinsics.checkNotNullExpressionValue(resultRetry, "retry()");
                            return resultRetry;
                        }
                        Logger.get().debug(ConstraintTrackingWorkerKt.TAG, "Constraints met for delegate " + string);
                        try {
                            WorkerFactory workerFactory = constraintTrackingWorker.getWorkerFactory();
                            Context applicationContext = constraintTrackingWorker.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                            ListenableWorker listenableWorkerCreateWorkerWithDefaultFallback = workerFactory.createWorkerWithDefaultFallback(applicationContext, string, constraintTrackingWorker.workerParameters);
                            Executor mainThreadExecutor = constraintTrackingWorker.workerParameters.getTaskExecutor().getMainThreadExecutor();
                            Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "workerParameters.taskExecutor.mainThreadExecutor");
                            CoroutineDispatcher coroutineDispatcherFrom = ExecutorsKt.from(mainThreadExecutor);
                            AnonymousClass5 anonymousClass5 = constraintTrackingWorker.new AnonymousClass5(listenableWorkerCreateWorkerWithDefaultFallback, workConstraintsTracker, workSpec, null);
                            c08012.L$0 = constraintTrackingWorker;
                            c08012.L$1 = listenableWorkerCreateWorkerWithDefaultFallback;
                            c08012.label = 1;
                            objWithContext = BuildersKt.withContext(coroutineDispatcherFrom, anonymousClass5, c08012);
                            r5 = listenableWorkerCreateWorkerWithDefaultFallback;
                            if (objWithContext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } catch (Throwable th) {
                            Logger.get().debug(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
                            Consumer<WorkerExceptionInfo> workerInitializationExceptionHandler = workManagerImpl.getConfiguration().getWorkerInitializationExceptionHandler();
                            if (workerInitializationExceptionHandler != null) {
                                WorkerExceptionUtilsKt.safeAccept(workerInitializationExceptionHandler, new WorkerExceptionInfo(string, constraintTrackingWorker.workerParameters, th), ConstraintTrackingWorkerKt.TAG);
                            }
                            ListenableWorker.Result resultFailure2 = ListenableWorker.Result.failure();
                            Intrinsics.checkNotNullExpressionValue(resultFailure2, "failure()");
                            return resultFailure2;
                        }
                    } else {
                        Logger.get().error(ConstraintTrackingWorkerKt.TAG, "No worker to delegate to.");
                        ListenableWorker.Result resultFailure3 = ListenableWorker.Result.failure();
                        Intrinsics.checkNotNullExpressionValue(resultFailure3, "failure()");
                        return resultFailure3;
                    }
                    break;
                case 1:
                    ListenableWorker listenableWorker = (ListenableWorker) c08012.L$1;
                    constraintTrackingWorker = (ConstraintTrackingWorker) c08012.L$0;
                    ResultKt.throwOnFailure(obj);
                    objWithContext = obj;
                    r5 = listenableWorker;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return (ListenableWorker.Result) objWithContext;
        } catch (CancellationException e) {
            if (constraintTrackingWorker.isStopped() || (e instanceof ConstraintUnsatisfiedException)) {
                if (Build.VERSION.SDK_INT < 31) {
                    stopReason = WorkInfo.STOP_REASON_UNKNOWN;
                } else if (constraintTrackingWorker.isStopped()) {
                    stopReason = constraintTrackingWorker.getStopReason();
                } else {
                    if (!(e instanceof ConstraintUnsatisfiedException)) {
                        throw new IllegalStateException("Unreachable");
                    }
                    stopReason = ((ConstraintUnsatisfiedException) e).getStopReason();
                }
                r5.stop(stopReason);
            }
            if (e instanceof ConstraintUnsatisfiedException) {
                ListenableWorker.Result resultRetry2 = ListenableWorker.Result.retry();
                Intrinsics.checkNotNullExpressionValue(resultRetry2, "{\n            // there a…throw cancelled\n        }");
                return resultRetry2;
            }
            throw e;
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$5, reason: invalid class name */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$5", f = "ConstraintTrackingWorker.kt", i = {}, l = {98}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        final /* synthetic */ ListenableWorker $delegate;
        final /* synthetic */ WorkConstraintsTracker $workConstraintsTracker;
        final /* synthetic */ WorkSpec $workSpec;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(ListenableWorker listenableWorker, WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$delegate = listenableWorker;
            this.$workConstraintsTracker = workConstraintsTracker;
            this.$workSpec = workSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ConstraintTrackingWorker.this.new AnonymousClass5(this.$delegate, this.$workConstraintsTracker, this.$workSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objRunWorker = ConstraintTrackingWorker.this.runWorker(this.$delegate, this.$workConstraintsTracker, this.$workSpec, this);
                    if (objRunWorker == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objRunWorker;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object runWorker(ListenableWorker delegate, WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, Continuation<? super ListenableWorker.Result> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objCoroutineScope;
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
        Object $result = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                C08002 c08002 = new C08002(delegate, workConstraintsTracker, workSpec, null);
                anonymousClass1.label = 1;
                objCoroutineScope = CoroutineScopeKt.coroutineScope(c08002, anonymousClass1);
                if (objCoroutineScope == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                objCoroutineScope = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intrinsics.checkNotNullExpressionValue(objCoroutineScope, "delegate: ListenableWork….cancel()\n        }\n    }");
        return objCoroutineScope;
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2", f = "ConstraintTrackingWorker.kt", i = {0, 0, 0}, l = {134}, m = "invokeSuspend", n = {"atomicReason", "future", "constraintTrackingJob"}, s = {"L$0", "L$1", "L$2"})
    static final class C08002 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        final /* synthetic */ ListenableWorker $delegate;
        final /* synthetic */ WorkConstraintsTracker $workConstraintsTracker;
        final /* synthetic */ WorkSpec $workSpec;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08002(ListenableWorker listenableWorker, WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, Continuation<? super C08002> continuation) {
            super(2, continuation);
            this.$delegate = listenableWorker;
            this.$workConstraintsTracker = workConstraintsTracker;
            this.$workSpec = workSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08002 c08002 = new C08002(this.$delegate, this.$workConstraintsTracker, this.$workSpec, continuation);
            c08002.L$0 = obj;
            return c08002;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((C08002) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:33:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:34:0x00ff  */
        /* JADX WARN: Code duplicated, block: B:37:0x0106 A[ADDED_TO_REGION] */
        /* JADX WARN: Not initialized variable reg: 7, insn: 0x0115: INVOKE 
  (r7 I:kotlinx.coroutines.Job A[D('constraintTrackingJob' kotlinx.coroutines.Job)])
  (r6 I:java.util.concurrent.CancellationException)
  (r5 I:int)
  (r6 I:java.lang.Object)
 STATIC call: kotlinx.coroutines.Job.DefaultImpls.cancel$default(kotlinx.coroutines.Job, java.util.concurrent.CancellationException, int, java.lang.Object):void A[MD:(kotlinx.coroutines.Job, java.util.concurrent.CancellationException, int, java.lang.Object):void (m)], block:B:43:0x0115 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Job constraintTrackingJob;
            ListenableFuture<ListenableWorker.Result> listenableFuture;
            Job constraintTrackingJob2;
            ListenableFuture<ListenableWorker.Result> listenableFuture2;
            AtomicInteger atomicReason;
            Object $result2;
            Object $result3;
            boolean constraintFailed;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        AtomicInteger atomicReason2 = new AtomicInteger(-256);
                        ListenableFuture<ListenableWorker.Result> listenableFutureStartWork = this.$delegate.startWork();
                        Intrinsics.checkNotNullExpressionValue(listenableFutureStartWork, "delegate.startWork()");
                        listenableFuture = listenableFutureStartWork;
                        constraintTrackingJob2 = BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(this.$workConstraintsTracker, this.$workSpec, atomicReason2, listenableFutureStartWork, null), 3, null);
                        try {
                            this.L$0 = atomicReason2;
                            this.L$1 = listenableFuture;
                            this.L$2 = constraintTrackingJob2;
                            this.label = 1;
                            Object objAwait = ListenableFutureKt.await(listenableFuture, this);
                            if (objAwait == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            atomicReason = atomicReason2;
                            $result2 = $result;
                            $result3 = objAwait;
                            try {
                                Object $result4 = (ListenableWorker.Result) $result3;
                                Job.DefaultImpls.cancel$default(constraintTrackingJob2, (CancellationException) null, 1, (Object) null);
                                return $result4;
                            } catch (CancellationException e) {
                                cancellation = e;
                                listenableFuture2 = listenableFuture;
                                String tag$iv = ConstraintTrackingWorkerKt.TAG;
                                Logger.get().debug(tag$iv, "Delegated worker " + this.$delegate.getClass() + " was cancelled", cancellation);
                                if (atomicReason.get() != -256) {
                                    constraintFailed = true;
                                } else {
                                    constraintFailed = false;
                                }
                                if (listenableFuture2.isCancelled()) {
                                }
                                throw cancellation;
                            } catch (Throwable th) {
                                throwable = th;
                                String tag$iv2 = ConstraintTrackingWorkerKt.TAG;
                                Logger.get().debug(tag$iv2, "Delegated worker " + this.$delegate.getClass() + " threw exception in startWork.", throwable);
                                throw throwable;
                            }
                        } catch (CancellationException e2) {
                            cancellation = e2;
                            listenableFuture2 = listenableFuture;
                            atomicReason = atomicReason2;
                            String tag$iv3 = ConstraintTrackingWorkerKt.TAG;
                            Logger.get().debug(tag$iv3, "Delegated worker " + this.$delegate.getClass() + " was cancelled", cancellation);
                            if (atomicReason.get() != -256) {
                                constraintFailed = true;
                            } else {
                                constraintFailed = false;
                            }
                            if (listenableFuture2.isCancelled()) {
                            }
                            throw cancellation;
                        } catch (Throwable th2) {
                            throwable = th2;
                            String tag$iv4 = ConstraintTrackingWorkerKt.TAG;
                            Logger.get().debug(tag$iv4, "Delegated worker " + this.$delegate.getClass() + " threw exception in startWork.", throwable);
                            throw throwable;
                        }
                    case 1:
                        $result3 = $result;
                        Job constraintTrackingJob3 = (Job) this.L$2;
                        listenableFuture2 = (ListenableFuture) this.L$1;
                        atomicReason = (AtomicInteger) this.L$0;
                        try {
                            ResultKt.throwOnFailure($result3);
                            listenableFuture = listenableFuture2;
                            constraintTrackingJob2 = constraintTrackingJob3;
                            $result2 = $result3;
                            Object $result5 = (ListenableWorker.Result) $result3;
                            Job.DefaultImpls.cancel$default(constraintTrackingJob2, (CancellationException) null, 1, (Object) null);
                            return $result5;
                        } catch (CancellationException e3) {
                            cancellation = e3;
                            String tag$iv5 = ConstraintTrackingWorkerKt.TAG;
                            Logger.get().debug(tag$iv5, "Delegated worker " + this.$delegate.getClass() + " was cancelled", cancellation);
                            if (atomicReason.get() != -256) {
                                constraintFailed = true;
                            } else {
                                constraintFailed = false;
                            }
                            if (listenableFuture2.isCancelled() || !constraintFailed) {
                                throw cancellation;
                            }
                            throw new ConstraintUnsatisfiedException(atomicReason.get());
                        } catch (Throwable th3) {
                            throwable = th3;
                            String tag$iv6 = ConstraintTrackingWorkerKt.TAG;
                            Logger.get().debug(tag$iv6, "Delegated worker " + this.$delegate.getClass() + " threw exception in startWork.", throwable);
                            throw throwable;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable cancellation) {
                Job.DefaultImpls.cancel$default(constraintTrackingJob, (CancellationException) null, 1, (Object) null);
                throw cancellation;
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker$ConstraintUnsatisfiedException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "stopReason", "", "(I)V", "getStopReason", "()I", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class ConstraintUnsatisfiedException extends CancellationException {
        private final int stopReason;

        public ConstraintUnsatisfiedException(int stopReason) {
            this.stopReason = stopReason;
        }

        public final int getStopReason() {
            return this.stopReason;
        }
    }
}
