package ai.nous.aurora.data.local.db;

import androidx.core.view.MotionEventCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: SessionDao.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H§@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\fJ\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\tJ$\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0097@¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H§@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\tJ\u000e\u0010\u001f\u001a\u00020\u000eH§@¢\u0006\u0002\u0010\u001b¨\u0006 "}, d2 = {"Lai/nous/aurora/data/local/db/SessionDao;", "", "observeSessions", "Lkotlinx/coroutines/flow/Flow;", "", "Lai/nous/aurora/data/local/db/SessionEntity;", "ownerKey", "", "getAll", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "session", "(Lai/nous/aurora/data/local/db/SessionEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "sessions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "deleteById", "deleteForOwner", "replaceForOwner", "rows", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listOwnerless", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "adoptOwnerless", "", "canonicalOwner", "deleteAll", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SessionDao {

    /* JADX INFO: renamed from: ai.nous.aurora.data.local.db.SessionDao$replaceForOwner$1, reason: invalid class name */
    /* JADX INFO: compiled from: SessionDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.local.db.SessionDao$DefaultImpls", f = "SessionDao.kt", i = {0, 0}, l = {MotionEventCompat.AXIS_GENERIC_12, MotionEventCompat.AXIS_GENERIC_13}, m = "replaceForOwner", n = {"$this", "rows"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.replaceForOwner(null, null, null, this);
        }
    }

    Object adoptOwnerless(String str, Continuation continuation);

    Object deleteAll(Continuation continuation);

    Object deleteById(String str, String str2, Continuation continuation);

    Object deleteForOwner(String str, Continuation continuation);

    Object getAll(String str, Continuation continuation);

    Object getById(String str, String str2, Continuation continuation);

    Object insert(SessionEntity sessionEntity, Continuation continuation);

    Object insertAll(List<SessionEntity> list, Continuation continuation);

    Object listOwnerless(Continuation continuation);

    Flow<List<SessionEntity>> observeSessions(String ownerKey);

    Object replaceForOwner(String str, List<SessionEntity> list, Continuation continuation);

    Object update(SessionEntity sessionEntity, Continuation continuation);

    /* JADX INFO: compiled from: SessionDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Code duplicated, block: B:20:0x0058  */
        /* JADX WARN: Code duplicated, block: B:22:0x0066 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        public static Object replaceForOwner(SessionDao $this, String ownerKey, List<SessionEntity> list, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            SessionDao $this2;
            List<SessionEntity> list2;
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
                    anonymousClass1.L$0 = $this;
                    anonymousClass1.L$1 = list;
                    anonymousClass1.label = 1;
                    if ($this.deleteForOwner(ownerKey, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $this2 = $this;
                    list2 = list;
                    if (!list2.isEmpty()) {
                        anonymousClass1.L$0 = null;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.label = 2;
                        if ($this2.insertAll(list2, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 1:
                    list2 = (List) anonymousClass1.L$1;
                    $this2 = (SessionDao) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result);
                    if (!list2.isEmpty()) {
                        anonymousClass1.L$0 = null;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.label = 2;
                        if ($this2.insertAll(list2, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
