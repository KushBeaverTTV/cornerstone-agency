package ai.nous.aurora.data.repository;

import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.domain.model.AuroraSession;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: SessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010\b\u001a\u00020\tH&J\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u000bH¦@¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH¦@¢\u0006\u0004\b\u000f\u0010\rJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\b\u001a\u00020\tH¦@¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b\u0016\u0010\u0017J2\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\tH¦@¢\u0006\u0004\b\u001a\u0010\u0017J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH¦@¢\u0006\u0002\u0010\u0012J\u000e\u0010\u001d\u001a\u00020\u001cH¦@¢\u0006\u0002\u0010\rJ\u000e\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010\rJ\u000e\u0010 \u001a\u00020\u001cH¦@¢\u0006\u0002\u0010\r¨\u0006!"}, d2 = {"Lai/nous/aurora/data/repository/SessionRepository;", "", "observeSessions", "Lkotlinx/coroutines/flow/Flow;", "", "Lai/nous/aurora/domain/model/AuroraSession;", "observeMessages", "Lai/nous/aurora/domain/model/AuroraMessage;", "sessionId", "", "refreshSessions", "Lkotlin/Result;", "refreshSessions-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSession", "createSession-IoAF18A", "selectSession", "selectSession-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "content", "model", "sendMessage-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retryMessage", "localMessageId", "retryMessage-BWLJW6A", "failPendingMessages", "", "recoverInterruptedMessages", "adoptLegacyOwnerlessRows", "", "clearLocalCache", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SessionRepository {
    Object adoptLegacyOwnerlessRows(Continuation continuation);

    Object clearLocalCache(Continuation continuation);

    /* JADX INFO: renamed from: createSession-IoAF18A */
    Object mo52createSessionIoAF18A(Continuation continuation);

    Object failPendingMessages(String str, Continuation continuation);

    Flow<List<AuroraMessage>> observeMessages(String sessionId);

    Flow<List<AuroraSession>> observeSessions();

    Object recoverInterruptedMessages(Continuation continuation);

    /* JADX INFO: renamed from: refreshSessions-IoAF18A */
    Object mo53refreshSessionsIoAF18A(Continuation continuation);

    /* JADX INFO: renamed from: retryMessage-BWLJW6A */
    Object mo54retryMessageBWLJW6A(String str, String str2, String str3, Continuation continuation);

    /* JADX INFO: renamed from: selectSession-gIAlu-s */
    Object mo55selectSessiongIAlus(String str, Continuation continuation);

    /* JADX INFO: renamed from: sendMessage-BWLJW6A */
    Object mo56sendMessageBWLJW6A(String str, String str2, String str3, Continuation continuation);

    /* JADX INFO: compiled from: SessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX INFO: renamed from: sendMessage-BWLJW6A$default, reason: not valid java name */
        public static /* synthetic */ Object m63sendMessageBWLJW6A$default(SessionRepository sessionRepository, String str, String str2, String str3, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendMessage-BWLJW6A");
            }
            if ((i & 4) != 0) {
                str3 = null;
            }
            return sessionRepository.mo56sendMessageBWLJW6A(str, str2, str3, continuation);
        }

        /* JADX INFO: renamed from: retryMessage-BWLJW6A$default, reason: not valid java name */
        public static /* synthetic */ Object m62retryMessageBWLJW6A$default(SessionRepository sessionRepository, String str, String str2, String str3, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: retryMessage-BWLJW6A");
            }
            if ((i & 4) != 0) {
                str3 = null;
            }
            return sessionRepository.mo54retryMessageBWLJW6A(str, str2, str3, continuation);
        }

        public static Object adoptLegacyOwnerlessRows(SessionRepository $this, Continuation continuation) {
            return Boxing.boxInt(0);
        }
    }
}
