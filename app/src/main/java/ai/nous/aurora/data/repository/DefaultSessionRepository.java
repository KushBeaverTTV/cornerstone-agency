package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.local.db.AuroraDatabase;
import ai.nous.aurora.data.local.db.MessageDao;
import ai.nous.aurora.data.local.db.MessageEntity;
import ai.nous.aurora.data.local.db.SessionDao;
import ai.nous.aurora.data.local.db.SessionEntity;
import ai.nous.aurora.data.remote.GatewayClient;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import ai.nous.aurora.domain.model.AuroraMessage;
import ai.nous.aurora.domain.model.AuroraSession;
import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import ai.nous.aurora.domain.model.SessionPresentation;
import ai.nous.aurora.domain.model.SessionSyncPolicy;
import androidx.room.RoomDatabaseKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: DefaultSessionRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0017\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J\u000e\u0010\u001c\u001a\u00020\u001dH\u0096@¢\u0006\u0002\u0010\u0018J\u000e\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010\u0018J\u0014\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0!H\u0016J\u001c\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\"0!2\u0006\u0010&\u001a\u00020\u0010H\u0016J\u001c\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0(H\u0096@¢\u0006\u0004\b)\u0010\u0018J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020#0(H\u0096@¢\u0006\u0004\b+\u0010\u0018J\u001e\u0010,\u001a\b\u0012\u0004\u0012\u00020#0(2\u0006\u0010&\u001a\u00020\u0010H\u0096@¢\u0006\u0004\b-\u0010.J,\u0010/\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\f\u00100\u001a\b\u0012\u0004\u0012\u00020%0\"H\u0082@¢\u0006\u0002\u00101J0\u00102\u001a\b\u0012\u0004\u0012\u00020%0(2\u0006\u0010&\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b5\u00106J0\u00107\u001a\b\u0012\u0004\u0012\u00020%0(2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u00010\u0010H\u0096@¢\u0006\u0004\b8\u00106J\u0016\u00109\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010.J\u000e\u0010:\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010\u0018JH\u0010;\u001a\b\u0012\u0004\u0012\u00020%0(2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u0010H\u0082@¢\u0006\u0004\b>\u0010?J\u000e\u0010@\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010\u0018J\f\u0010A\u001a\u00020#*\u00020BH\u0002J\f\u0010A\u001a\u00020%*\u00020CH\u0002J\u0014\u0010D\u001a\u00020B*\u00020#2\u0006\u0010\u001a\u001a\u00020\u0010H\u0002J&\u0010D\u001a\u00020C*\u00020%2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\b\b\u0002\u0010E\u001a\u00020FH\u0002J\f\u0010G\u001a\u00020F*\u00020FH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lai/nous/aurora/data/repository/DefaultSessionRepository;", "Lai/nous/aurora/data/repository/SessionRepository;", "database", "Lai/nous/aurora/data/local/db/AuroraDatabase;", "gatewayClient", "Lai/nous/aurora/data/remote/GatewayClient;", "credentialStore", "Lai/nous/aurora/data/storage/AuroraCredentialStore;", "<init>", "(Lai/nous/aurora/data/local/db/AuroraDatabase;Lai/nous/aurora/data/remote/GatewayClient;Lai/nous/aurora/data/storage/AuroraCredentialStore;)V", "sessions", "Lai/nous/aurora/data/local/db/SessionDao;", "messages", "Lai/nous/aurora/data/local/db/MessageDao;", "deliveryLocks", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlinx/coroutines/sync/Mutex;", "refreshMutex", "refreshGeneration", "Ljava/util/concurrent/atomic/AtomicLong;", "legacyAdopted", "Ljava/util/concurrent/atomic/AtomicBoolean;", "ownerKey", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockFor", "owner", "localMessageId", "adoptLegacyOwnerlessRows", "", "ensureLegacyAdopted", "", "observeSessions", "Lkotlinx/coroutines/flow/Flow;", "", "Lai/nous/aurora/domain/model/AuroraSession;", "observeMessages", "Lai/nous/aurora/domain/model/AuroraMessage;", "sessionId", "refreshSessions", "Lkotlin/Result;", "refreshSessions-IoAF18A", "createSession", "createSession-IoAF18A", "selectSession", "selectSession-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reconcileAmbiguousOutbox", "remote", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "content", "model", "sendMessage-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retryMessage", "retryMessage-BWLJW6A", "failPendingMessages", "recoverInterruptedMessages", "deliverPending", "pendingId", "clientMsgId", "deliverPending-bMdYcbs", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearLocalCache", "toModel", "Lai/nous/aurora/data/local/db/SessionEntity;", "Lai/nous/aurora/data/local/db/MessageEntity;", "toEntity", "orderHint", "", "ifZeroNow", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultSessionRepository implements SessionRepository {
    public static final int $stable = 8;
    public final AuroraCredentialStore credentialStore;
    public final AuroraDatabase database;
    public final ConcurrentHashMap<String, Mutex> deliveryLocks;
    public final GatewayClient gatewayClient;
    public final AtomicBoolean legacyAdopted;
    public final MessageDao messages;
    public final AtomicLong refreshGeneration;
    public final Mutex refreshMutex;
    public final SessionDao sessions;

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$adoptLegacyOwnerlessRows$1, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 1}, l = {59, LockFreeTaskQueueCore.CLOSED_SHIFT}, m = "adoptLegacyOwnerlessRows", n = {"this", "this"}, s = {"L$0", "L$0"})
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.adoptLegacyOwnerlessRows(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$clearLocalCache$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 1, 1}, l = {378, 379, 380}, m = "clearLocalCache", n = {"this", "this", "owner"}, s = {"L$0", "L$0", "L$1"})
    final class C02151 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02151(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.clearLocalCache(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$ensureLegacyAdopted$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {}, l = {70}, m = "ensureLegacyAdopted", n = {}, s = {})
    final class C02161 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02161(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.ensureLegacyAdopted(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$failPendingMessages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 0}, l = {249, 251}, m = "failPendingMessages", n = {"this", "sessionId"}, s = {"L$0", "L$1"})
    final class C02171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C02171(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.failPendingMessages(null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$ownerKey$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {}, l = {40}, m = "ownerKey", n = {}, s = {})
    final class C02181 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02181(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.ownerKey(this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$reconcileAmbiguousOutbox$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 0, 0, 1, 1, 1}, l = {171, 184}, m = "reconcileAmbiguousOutbox", n = {"this", "owner", "remote", "this", "owner", "remote"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
    final class C02191 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C02191(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.reconcileAmbiguousOutbox(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$recoverInterruptedMessages$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository", f = "DefaultSessionRepository.kt", i = {0, 1}, l = {255, 256, 257}, m = "recoverInterruptedMessages", n = {"this", "this"}, s = {"L$0", "L$0"})
    final class C02201 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02201(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultSessionRepository.this.recoverInterruptedMessages(this);
        }
    }

    public DefaultSessionRepository(AuroraDatabase database, GatewayClient gatewayClient, AuroraCredentialStore credentialStore) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(gatewayClient, "gatewayClient");
        Intrinsics.checkNotNullParameter(credentialStore, "credentialStore");
        this.database = database;
        this.gatewayClient = gatewayClient;
        this.credentialStore = credentialStore;
        this.sessions = this.database.sessionDao();
        this.messages = this.database.messageDao();
        this.deliveryLocks = new ConcurrentHashMap<>();
        this.refreshMutex = ai.nous.aurora.compat.Compat.d_MutexKt_Mutex_3(false, 1, null);
        this.refreshGeneration = new AtomicLong(0L);
        this.legacyAdopted = new AtomicBoolean(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object ownerKey(Continuation continuation) {
        C02181 c02181;
        Object gatewayAddress;
        if (continuation instanceof C02181) {
            c02181 = (C02181) continuation;
            if ((c02181.label & Integer.MIN_VALUE) != 0) {
                c02181.label -= Integer.MIN_VALUE;
            } else {
                c02181 = new C02181(continuation);
            }
        } else {
            c02181 = new C02181(continuation);
        }
        Object $result = c02181.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02181.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AuroraCredentialStore auroraCredentialStore = this.credentialStore;
                c02181.label = 1;
                gatewayAddress = auroraCredentialStore.getGatewayAddress(c02181);
                if (gatewayAddress == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                gatewayAddress = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String lowerCase = StringsKt.trim((CharSequence) gatewayAddress).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String host = StringsKt.trimEnd(StringsKt.removePrefix(StringsKt.removePrefix(lowerCase, (CharSequence) "https://"), (CharSequence) "http://"), '/');
        String str = host;
        return StringsKt.isBlank(str) ? "default" : str;
    }

    private final Mutex lockFor(String owner, String localMessageId) {
        Mutex mutexPutIfAbsent;
        String key = OutboxExactlyOnce.INSTANCE.deliveryKey(owner, localMessageId);
        ConcurrentMap<String, Mutex> $this$getOrPut$iv = this.deliveryLocks;
        Mutex mutexMutex$default = $this$getOrPut$iv.get(key);
        if (mutexMutex$default == null && (mutexPutIfAbsent = $this$getOrPut$iv.putIfAbsent(key, (mutexMutex$default = ai.nous.aurora.compat.Compat.d_MutexKt_Mutex_3(false, 1, null)))) != null) {
            mutexMutex$default = mutexPutIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(mutexMutex$default, "getOrPut(...)");
        return mutexMutex$default;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x005a  */
    /* JADX WARN: Code duplicated, block: B:21:0x0060  */
    /* JADX WARN: Code duplicated, block: B:23:0x0077 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0078  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    public Object adoptLegacyOwnerlessRows(Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        DefaultSessionRepository defaultSessionRepository;
        Object objOwnerKey;
        String owner;
        Object objWithTransaction;
        DefaultSessionRepository defaultSessionRepository2;
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
                defaultSessionRepository = this;
                anonymousClass1.L$0 = defaultSessionRepository;
                anonymousClass1.label = 1;
                objOwnerKey = defaultSessionRepository.ownerKey(anonymousClass1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                owner = (String) objOwnerKey;
                if (!SessionSyncPolicy.INSTANCE.shouldAdopt("", owner)) {
                    return Boxing.boxInt(0);
                }
                AuroraDatabase auroraDatabase = defaultSessionRepository.database;
                Object AnonymousClass2 = null;
                AnonymousClass2 anonymousClass2 = defaultSessionRepository.new AnonymousClass2(owner, null);
                anonymousClass1.L$0 = defaultSessionRepository;
                anonymousClass1.label = 2;
                objWithTransaction = RoomDatabaseKt.withTransaction(auroraDatabase, anonymousClass2, anonymousClass1);
                if (objWithTransaction == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                ((Number) objWithTransaction).intValue();
                defaultSessionRepository2.legacyAdopted.set(true);
                return objWithTransaction;
            case 1:
                defaultSessionRepository = (DefaultSessionRepository) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                objOwnerKey = $result;
                owner = (String) objOwnerKey;
                if (!SessionSyncPolicy.INSTANCE.shouldAdopt("", owner)) {
                    return Boxing.boxInt(0);
                }
                AuroraDatabase auroraDatabase2 = defaultSessionRepository.database;
                AnonymousClass2 anonymousClass3 = defaultSessionRepository.new AnonymousClass2(owner, null);
                anonymousClass1.L$0 = defaultSessionRepository;
                anonymousClass1.label = 2;
                objWithTransaction = RoomDatabaseKt.withTransaction(auroraDatabase2, anonymousClass3, anonymousClass1);
                if (objWithTransaction == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                ((Number) objWithTransaction).intValue();
                defaultSessionRepository2.legacyAdopted.set(true);
                return objWithTransaction;
            case 2:
                defaultSessionRepository2 = (DefaultSessionRepository) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                objWithTransaction = $result;
                ((Number) objWithTransaction).intValue();
                defaultSessionRepository2.legacyAdopted.set(true);
                return objWithTransaction;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.DefaultSessionRepository$adoptLegacyOwnerlessRows$2, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultSessionRepository.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.DefaultSessionRepository$adoptLegacyOwnerlessRows$2", f = "DefaultSessionRepository.kt", i = {1}, l = {62, 63}, m = "invokeSuspend", n = {"s"}, s = {"I$0"})
    final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation, Object> {
        final /* synthetic */ String $owner;
        int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation continuation) {
            super(1, continuation);
            this.$owner = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Continuation<?> continuation) {
            return DefaultSessionRepository.this.new AnonymousClass2(this.$owner, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation continuation) {
            return ((AnonymousClass2)(Object) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0054 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x0055  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            int s;
            Object objAdoptOwnerless;
            int s2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objAdoptOwnerless2 = DefaultSessionRepository.this.sessions.adoptOwnerless(this.$owner, this);
                    if (objAdoptOwnerless2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objAdoptOwnerless2;
                    s = ((Number) $result).intValue();
                    this.I$0 = s;
                    this.label = 2;
                    objAdoptOwnerless = DefaultSessionRepository.this.messages.adoptOwnerless(this.$owner, this);
                    if (objAdoptOwnerless == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    s2 = s;
                    $result = objAdoptOwnerless;
                    int m = ((Number) $result).intValue();
                    return Boxing.boxInt(s2 + m);
                case 1:
                    ResultKt.throwOnFailure($result);
                    s = ((Number) $result).intValue();
                    this.I$0 = s;
                    this.label = 2;
                    objAdoptOwnerless = DefaultSessionRepository.this.messages.adoptOwnerless(this.$owner, this);
                    if (objAdoptOwnerless == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    s2 = s;
                    $result = objAdoptOwnerless;
                    int m2 = ((Number) $result).intValue();
                    return Boxing.boxInt(s2 + m2);
                case 2:
                    s2 = this.I$0;
                    ResultKt.throwOnFailure($result);
                    int m3 = ((Number) $result).intValue();
                    return Boxing.boxInt(s2 + m3);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object ensureLegacyAdopted(Continuation continuation) {
        C02161 c02161;
        Object objAdoptLegacyOwnerlessRows;
        if (continuation instanceof C02161) {
            c02161 = (C02161) continuation;
            if ((c02161.label & Integer.MIN_VALUE) != 0) {
                c02161.label -= Integer.MIN_VALUE;
            } else {
                c02161 = new C02161(continuation);
            }
        } else {
            c02161 = new C02161(continuation);
        }
        Object $result = c02161.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c02161.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (this.legacyAdopted.get()) {
                        return Unit.INSTANCE;
                    }
                    Result.Companion companion = Result.Companion;
                    DefaultSessionRepository $this$ensureLegacyAdopted_u24lambda_u243 = this;
                    c02161.label = 1;
                    objAdoptLegacyOwnerlessRows = $this$ensureLegacyAdopted_u24lambda_u243.adoptLegacyOwnerlessRows(c02161);
                    if (objAdoptLegacyOwnerlessRows == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objAdoptLegacyOwnerlessRows = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) objAdoptLegacyOwnerlessRows).intValue()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    @Override // ai.nous.aurora.data.repository.SessionRepository
    public Flow<List<AuroraSession>> observeSessions() {
        Flow $this$flatMapLatest$iv = this.credentialStore.getOwnerGenerationFlow();
        return FlowKt.transformLatest($this$flatMapLatest$iv, new DefaultSessionRepository$observeSessions$$inlined$flatMapLatest$1(null, this));
    }

    @Override // ai.nous.aurora.data.repository.SessionRepository
    public Flow<List<AuroraMessage>> observeMessages(String sessionId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Flow $this$flatMapLatest$iv = this.credentialStore.getOwnerGenerationFlow();
        return FlowKt.transformLatest($this$flatMapLatest$iv, new DefaultSessionRepository$observeMessages$$inlined$flatMapLatest$1(null, this, sessionId));
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00e2 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:39:0x0101 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x0102  */
    /* JADX WARN: Code duplicated, block: B:44:0x011c A[Catch: all -> 0x0170, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0170, blocks: (B:63:0x01a2, B:44:0x011c), top: B:100:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0130 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:47:0x0131  */
    /* JADX WARN: Code duplicated, block: B:51:0x014e A[Catch: all -> 0x0097, LOOP:2: B:49:0x0148->B:51:0x014e, LOOP_END, TryCatch #1 {all -> 0x0097, blocks: (B:13:0x003f, B:16:0x0052, B:67:0x01ba, B:68:0x01cf, B:70:0x01d5, B:71:0x01e5, B:24:0x0090, B:48:0x0133, B:49:0x0148, B:51:0x014e, B:52:0x0162), top: B:100:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0176 A[Catch: all -> 0x026d, TRY_ENTER, TryCatch #3 {all -> 0x026d, blocks: (B:59:0x018a, B:61:0x0190, B:73:0x01f2, B:74:0x020a, B:76:0x0210, B:42:0x010e, B:56:0x0176), top: B:102:0x010e }] */
    /* JADX WARN: Code duplicated, block: B:58:0x0189 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:61:0x0190 A[Catch: all -> 0x026d, TRY_LEAVE, TryCatch #3 {all -> 0x026d, blocks: (B:59:0x018a, B:61:0x0190, B:73:0x01f2, B:74:0x020a, B:76:0x0210, B:42:0x010e, B:56:0x0176), top: B:102:0x010e }] */
    /* JADX WARN: Code duplicated, block: B:63:0x01a2 A[Catch: all -> 0x0170, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0170, blocks: (B:63:0x01a2, B:44:0x011c), top: B:100:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x01b6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:66:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:70:0x01d5 A[Catch: all -> 0x0097, LOOP:0: B:68:0x01cf->B:70:0x01d5, LOOP_END, TryCatch #1 {all -> 0x0097, blocks: (B:13:0x003f, B:16:0x0052, B:67:0x01ba, B:68:0x01cf, B:70:0x01d5, B:71:0x01e5, B:24:0x0090, B:48:0x0133, B:49:0x0148, B:51:0x014e, B:52:0x0162), top: B:100:0x0028 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x01f2 A[Catch: all -> 0x026d, TRY_ENTER, TryCatch #3 {all -> 0x026d, blocks: (B:59:0x018a, B:61:0x0190, B:73:0x01f2, B:74:0x020a, B:76:0x0210, B:42:0x010e, B:56:0x0176), top: B:102:0x010e }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0210 A[Catch: all -> 0x026d, TRY_LEAVE, TryCatch #3 {all -> 0x026d, blocks: (B:59:0x018a, B:61:0x0190, B:73:0x01f2, B:74:0x020a, B:76:0x0210, B:42:0x010e, B:56:0x0176), top: B:102:0x010e }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:81:0x0243 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:82:0x0244  */
    /* JADX WARN: Code duplicated, block: B:88:0x0251  */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0173: MOVE (r9 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY] A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)]), block:B:55:0x0171 */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    /* JADX INFO: renamed from: refreshSessions-IoAF18A, reason: not valid java name */
    public Object mo53refreshSessionsIoAF18A(Continuation continuation) throws Throwable {
        DefaultSessionRepository$refreshSessions$1 defaultSessionRepository$refreshSessions$1;
        Mutex $this$withLock_u24default$iv;
        Mutex $this$withLock_u24default$iv2;
        Object owner$iv;
        DefaultSessionRepository defaultSessionRepository;
        Object objOwnerKey;
        String owner;
        long gen;
        Mutex $this$withLock_u24default$iv3;
        DefaultSessionRepository defaultSessionRepository2;
        String owner2;
        int i;
        Object objM44getSessionsIoAF18A;
        Object all;
        int i2;
        Collection destination$iv$iv;
        Object objM7388constructorimpl;
        Throwable err;
        List remote;
        SessionDao sessionDao;
        Collection destination$iv$iv2;
        int i3;
        Object all2;
        DefaultSessionRepository defaultSessionRepository3;
        int i4;
        Collection destination$iv$iv3;
        Object objM7388constructorimpl2;
        if (continuation instanceof DefaultSessionRepository$refreshSessions$1) {
            defaultSessionRepository$refreshSessions$1 = (DefaultSessionRepository$refreshSessions$1) continuation;
            if ((defaultSessionRepository$refreshSessions$1.label & Integer.MIN_VALUE) != 0) {
                defaultSessionRepository$refreshSessions$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSessionRepository$refreshSessions$1 = new DefaultSessionRepository$refreshSessions$1(this, continuation);
            }
        } else {
            defaultSessionRepository$refreshSessions$1 = new DefaultSessionRepository$refreshSessions$1(this, continuation);
        }
        DefaultSessionRepository$refreshSessions$1 defaultSessionRepository$refreshSessions$2 = defaultSessionRepository$refreshSessions$1;
        Object $result = defaultSessionRepository$refreshSessions$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            try {
                switch (defaultSessionRepository$refreshSessions$2.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        defaultSessionRepository = this;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository;
                        defaultSessionRepository$refreshSessions$2.label = 1;
                        if (defaultSessionRepository.ensureLegacyAdopted(defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository;
                        defaultSessionRepository$refreshSessions$2.label = 2;
                        objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$refreshSessions$2);
                        if (objOwnerKey == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        owner = (String) objOwnerKey;
                        gen = defaultSessionRepository.refreshGeneration.incrementAndGet();
                        $this$withLock_u24default$iv3 = defaultSessionRepository.refreshMutex;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository;
                        defaultSessionRepository$refreshSessions$2.L$1 = owner;
                        defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                        defaultSessionRepository$refreshSessions$2.J$0 = gen;
                        defaultSessionRepository$refreshSessions$2.label = 3;
                        if ($this$withLock_u24default$iv3.lock(null, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSessionRepository2 = defaultSessionRepository;
                        owner2 = owner;
                        owner$iv = null;
                        i = 0;
                        try {
                            if (!SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                                Result.Companion companion = Result.Companion;
                                SessionDao sessionDao2 = defaultSessionRepository2.sessions;
                                defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 4;
                                all = sessionDao2.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                                if (all == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                i2 = 0;
                                Iterable $this$map$iv = (Iterable) all;
                                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                                for (Object item$iv$iv : $this$map$iv) {
                                    SessionEntity it = (SessionEntity) item$iv$iv;
                                    destination$iv$iv.add(defaultSessionRepository2.toModel(it));
                                }
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv);
                                $this$withLock_u24default$iv2.unlock(owner$iv);
                                return objM7388constructorimpl;
                            }
                            GatewayClient gatewayClient = defaultSessionRepository2.gatewayClient;
                            defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                            defaultSessionRepository$refreshSessions$2.L$1 = owner2;
                            defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                            defaultSessionRepository$refreshSessions$2.J$0 = gen;
                            defaultSessionRepository$refreshSessions$2.label = 5;
                            objM44getSessionsIoAF18A = gatewayClient.m44getSessionsIoAF18A(defaultSessionRepository$refreshSessions$2);
                            if (objM44getSessionsIoAF18A == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM44getSessionsIoAF18A);
                            try {
                                if (err == null) {
                                    remote = (List) objM44getSessionsIoAF18A;
                                    if (SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                                        sessionDao = defaultSessionRepository2.sessions;
                                        List $this$map$iv2 = remote;
                                        destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                                        Object item$iv$iv2 = null;
                                        for (Object item$iv$iv2 : $this$map$iv2) {
                                            AuroraSession it2 = (AuroraSession) item$iv$iv2;
                                            Object $result2 = $result;
                                            destination$iv$iv2.add(defaultSessionRepository2.toEntity(it2, owner2));
                                            $result = $result2;
                                            break;
                                        }
                                        defaultSessionRepository$refreshSessions$2.L$0 = $this$withLock_u24default$iv3;
                                        defaultSessionRepository$refreshSessions$2.L$1 = remote;
                                        defaultSessionRepository$refreshSessions$2.L$2 = null;
                                        defaultSessionRepository$refreshSessions$2.label = 7;
                                        if (sessionDao.replaceForOwner(owner2, (List) destination$iv$iv2, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                        i3 = i;
                                        try {
                                            Result.Companion companion2 = Result.Companion;
                                            objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(remote);
                                            objM7388constructorimpl = objM7388constructorimpl2;
                                        } catch (Throwable th) {
                                            $this$withLock_u24default$iv2.unlock(owner$iv);
                                            throw th;
                                        }
                                    } else {
                                        Result.Companion companion3 = Result.Companion;
                                        SessionDao sessionDao3 = defaultSessionRepository2.sessions;
                                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                                        defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                                        defaultSessionRepository$refreshSessions$2.L$2 = null;
                                        defaultSessionRepository$refreshSessions$2.label = 6;
                                        all2 = sessionDao3.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                                        if (all2 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                        defaultSessionRepository3 = defaultSessionRepository2;
                                        i4 = i;
                                        Iterable $this$map$iv3 = (Iterable) all2;
                                        destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
                                        Object item$iv$iv3 = null;
                                        for (Object item$iv$iv3 : $this$map$iv3) {
                                            SessionEntity it3 = (SessionEntity) item$iv$iv3;
                                            destination$iv$iv3.add(defaultSessionRepository3.toModel(it3));
                                        }
                                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv3);
                                        objM7388constructorimpl = objM7388constructorimpl2;
                                    }
                                } else {
                                    Result.Companion companion4 = Result.Companion;
                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(err));
                                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                }
                                $this$withLock_u24default$iv2.unlock(owner$iv);
                                return objM7388constructorimpl;
                            } catch (Throwable th2) {
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                $this$withLock_u24default$iv2.unlock(owner$iv);
                                throw th2;
                            }
                        } catch (Throwable th3) {
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                            $this$withLock_u24default$iv2.unlock(owner$iv);
                            throw th3;
                        }
                    case 1:
                        defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$refreshSessions$2.L$0;
                        ResultKt.throwOnFailure($result);
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository;
                        defaultSessionRepository$refreshSessions$2.label = 2;
                        objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$refreshSessions$2);
                        if (objOwnerKey == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        owner = (String) objOwnerKey;
                        gen = defaultSessionRepository.refreshGeneration.incrementAndGet();
                        $this$withLock_u24default$iv3 = defaultSessionRepository.refreshMutex;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository;
                        defaultSessionRepository$refreshSessions$2.L$1 = owner;
                        defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                        defaultSessionRepository$refreshSessions$2.J$0 = gen;
                        defaultSessionRepository$refreshSessions$2.label = 3;
                        if ($this$withLock_u24default$iv3.lock(null, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSessionRepository2 = defaultSessionRepository;
                        owner2 = owner;
                        owner$iv = null;
                        i = 0;
                        if (!SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                            Result.Companion companion5 = Result.Companion;
                            SessionDao sessionDao4 = defaultSessionRepository2.sessions;
                            defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                            defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                            defaultSessionRepository$refreshSessions$2.L$2 = null;
                            defaultSessionRepository$refreshSessions$2.label = 4;
                            all = sessionDao4.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                            if (all == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                            i2 = 0;
                            Iterable $this$map$iv4 = (Iterable) all;
                            destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv4, 10));
                            java.util.Iterator<Object> r12 = $this$map$iv4.iterator();
                            while (r12.hasNext()) {
                                Object item$iv$iv = r12.next();
                                SessionEntity it4 = (SessionEntity) item$iv$iv;
                                destination$iv$iv.add(defaultSessionRepository2.toModel(it4));
                            }
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv);
                            $this$withLock_u24default$iv2.unlock(owner$iv);
                            return objM7388constructorimpl;
                        }
                        GatewayClient gatewayClient2 = defaultSessionRepository2.gatewayClient;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                        defaultSessionRepository$refreshSessions$2.L$1 = owner2;
                        defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                        defaultSessionRepository$refreshSessions$2.J$0 = gen;
                        defaultSessionRepository$refreshSessions$2.label = 5;
                        objM44getSessionsIoAF18A = gatewayClient2.m44getSessionsIoAF18A(defaultSessionRepository$refreshSessions$2);
                        if (objM44getSessionsIoAF18A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM44getSessionsIoAF18A);
                        if (err == null) {
                            remote = (List) objM44getSessionsIoAF18A;
                            if (SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                                Result.Companion companion6 = Result.Companion;
                                SessionDao sessionDao5 = defaultSessionRepository2.sessions;
                                defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 6;
                                all2 = sessionDao5.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                                if (all2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                defaultSessionRepository3 = defaultSessionRepository2;
                                i4 = i;
                                Iterable $this$map$iv5 = (Iterable) all2;
                                destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv5, 10));
                                java.util.Iterator<Object> r12U1 = $this$map$iv5.iterator();
                                while (r12U1.hasNext()) {
                                    Object item$iv$iv3 = r12U1.next();
                                    SessionEntity it5 = (SessionEntity) item$iv$iv3;
                                    destination$iv$iv3.add(defaultSessionRepository3.toModel(it5));
                                }
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv3);
                                objM7388constructorimpl = objM7388constructorimpl2;
                            } else {
                                sessionDao = defaultSessionRepository2.sessions;
                                List $this$map$iv6 = remote;
                                destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv6, 10));
                                java.util.Iterator<Object> r16 = $this$map$iv6.iterator();
                                while (r16.hasNext()) {
                                    Object item$iv$iv2 = r16.next();
                                    AuroraSession it6 = (AuroraSession) item$iv$iv2;
                                    Object $result3 = $result;
                                    destination$iv$iv2.add(defaultSessionRepository2.toEntity(it6, owner2));
                                    $result = $result3;
                                    break;
                                }
                                defaultSessionRepository$refreshSessions$2.L$0 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$1 = remote;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 7;
                                if (sessionDao.replaceForOwner(owner2, (List) destination$iv$iv2, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                i3 = i;
                                Result.Companion companion7 = Result.Companion;
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(remote);
                                objM7388constructorimpl = objM7388constructorimpl2;
                            }
                        } else {
                            Result.Companion companion8 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(err));
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv);
                        return objM7388constructorimpl;
                    case 2:
                        defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$refreshSessions$2.L$0;
                        ResultKt.throwOnFailure($result);
                        objOwnerKey = $result;
                        owner = (String) objOwnerKey;
                        gen = defaultSessionRepository.refreshGeneration.incrementAndGet();
                        $this$withLock_u24default$iv3 = defaultSessionRepository.refreshMutex;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository;
                        defaultSessionRepository$refreshSessions$2.L$1 = owner;
                        defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                        defaultSessionRepository$refreshSessions$2.J$0 = gen;
                        defaultSessionRepository$refreshSessions$2.label = 3;
                        if ($this$withLock_u24default$iv3.lock(null, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultSessionRepository2 = defaultSessionRepository;
                        owner2 = owner;
                        owner$iv = null;
                        i = 0;
                        if (!SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                            Result.Companion companion9 = Result.Companion;
                            SessionDao sessionDao6 = defaultSessionRepository2.sessions;
                            defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                            defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                            defaultSessionRepository$refreshSessions$2.L$2 = null;
                            defaultSessionRepository$refreshSessions$2.label = 4;
                            all = sessionDao6.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                            if (all == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                            i2 = 0;
                            Iterable $this$map$iv7 = (Iterable) all;
                            destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv7, 10));
                            java.util.Iterator<Object> r12 = $this$map$iv7.iterator();
                            while (r12.hasNext()) {
                                Object item$iv$iv = r12.next();
                                SessionEntity it7 = (SessionEntity) item$iv$iv;
                                destination$iv$iv.add(defaultSessionRepository2.toModel(it7));
                            }
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv);
                            $this$withLock_u24default$iv2.unlock(owner$iv);
                            return objM7388constructorimpl;
                        }
                        GatewayClient gatewayClient3 = defaultSessionRepository2.gatewayClient;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                        defaultSessionRepository$refreshSessions$2.L$1 = owner2;
                        defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                        defaultSessionRepository$refreshSessions$2.J$0 = gen;
                        defaultSessionRepository$refreshSessions$2.label = 5;
                        objM44getSessionsIoAF18A = gatewayClient3.m44getSessionsIoAF18A(defaultSessionRepository$refreshSessions$2);
                        if (objM44getSessionsIoAF18A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM44getSessionsIoAF18A);
                        if (err == null) {
                            remote = (List) objM44getSessionsIoAF18A;
                            if (SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                                Result.Companion companion10 = Result.Companion;
                                SessionDao sessionDao7 = defaultSessionRepository2.sessions;
                                defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 6;
                                all2 = sessionDao7.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                                if (all2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                defaultSessionRepository3 = defaultSessionRepository2;
                                i4 = i;
                                Iterable $this$map$iv8 = (Iterable) all2;
                                destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv8, 10));
                                java.util.Iterator<Object> r12U2 = $this$map$iv8.iterator();
                                while (r12U2.hasNext()) {
                                    Object item$iv$iv3 = r12U2.next();
                                    SessionEntity it8 = (SessionEntity) item$iv$iv3;
                                    destination$iv$iv3.add(defaultSessionRepository3.toModel(it8));
                                }
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv3);
                                objM7388constructorimpl = objM7388constructorimpl2;
                            } else {
                                sessionDao = defaultSessionRepository2.sessions;
                                List $this$map$iv9 = remote;
                                destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv9, 10));
                                java.util.Iterator<Object> r16U3 = $this$map$iv9.iterator();
                                while (r16U3.hasNext()) {
                                    Object item$iv$iv2 = r16U3.next();
                                    AuroraSession it9 = (AuroraSession) item$iv$iv2;
                                    Object $result4 = $result;
                                    destination$iv$iv2.add(defaultSessionRepository2.toEntity(it9, owner2));
                                    $result = $result4;
                                    break;
                                }
                                defaultSessionRepository$refreshSessions$2.L$0 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$1 = remote;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 7;
                                if (sessionDao.replaceForOwner(owner2, (List) destination$iv$iv2, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                i3 = i;
                                Result.Companion companion11 = Result.Companion;
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(remote);
                                objM7388constructorimpl = objM7388constructorimpl2;
                            }
                        } else {
                            Result.Companion companion12 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(err));
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv);
                        return objM7388constructorimpl;
                    case 3:
                        long gen2 = defaultSessionRepository$refreshSessions$2.J$0;
                        $this$withLock_u24default$iv3 = (Mutex) defaultSessionRepository$refreshSessions$2.L$2;
                        owner2 = (String) defaultSessionRepository$refreshSessions$2.L$1;
                        defaultSessionRepository2 = (DefaultSessionRepository) defaultSessionRepository$refreshSessions$2.L$0;
                        ResultKt.throwOnFailure($result);
                        owner$iv = null;
                        gen = gen2;
                        i = 0;
                        if (!SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                            Result.Companion companion13 = Result.Companion;
                            SessionDao sessionDao8 = defaultSessionRepository2.sessions;
                            defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                            defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                            defaultSessionRepository$refreshSessions$2.L$2 = null;
                            defaultSessionRepository$refreshSessions$2.label = 4;
                            all = sessionDao8.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                            if (all == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                            i2 = 0;
                            Iterable $this$map$iv10 = (Iterable) all;
                            destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv10, 10));
                            java.util.Iterator<Object> r12U4 = $this$map$iv10.iterator();
                            while (r12U4.hasNext()) {
                                Object item$iv$iv = r12U4.next();
                                SessionEntity it10 = (SessionEntity) item$iv$iv;
                                destination$iv$iv.add(defaultSessionRepository2.toModel(it10));
                            }
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv);
                            $this$withLock_u24default$iv2.unlock(owner$iv);
                            return objM7388constructorimpl;
                        }
                        GatewayClient gatewayClient4 = defaultSessionRepository2.gatewayClient;
                        defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                        defaultSessionRepository$refreshSessions$2.L$1 = owner2;
                        defaultSessionRepository$refreshSessions$2.L$2 = $this$withLock_u24default$iv3;
                        defaultSessionRepository$refreshSessions$2.J$0 = gen;
                        defaultSessionRepository$refreshSessions$2.label = 5;
                        objM44getSessionsIoAF18A = gatewayClient4.m44getSessionsIoAF18A(defaultSessionRepository$refreshSessions$2);
                        if (objM44getSessionsIoAF18A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM44getSessionsIoAF18A);
                        if (err == null) {
                            remote = (List) objM44getSessionsIoAF18A;
                            if (SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                                Result.Companion companion14 = Result.Companion;
                                SessionDao sessionDao9 = defaultSessionRepository2.sessions;
                                defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 6;
                                all2 = sessionDao9.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                                if (all2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                defaultSessionRepository3 = defaultSessionRepository2;
                                i4 = i;
                                Iterable $this$map$iv11 = (Iterable) all2;
                                destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv11, 10));
                                java.util.Iterator<Object> r12U5 = $this$map$iv11.iterator();
                                while (r12U5.hasNext()) {
                                    Object item$iv$iv3 = r12U5.next();
                                    SessionEntity it11 = (SessionEntity) item$iv$iv3;
                                    destination$iv$iv3.add(defaultSessionRepository3.toModel(it11));
                                }
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv3);
                                objM7388constructorimpl = objM7388constructorimpl2;
                            } else {
                                sessionDao = defaultSessionRepository2.sessions;
                                List $this$map$iv12 = remote;
                                destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv12, 10));
                                java.util.Iterator<Object> r16U6 = $this$map$iv12.iterator();
                                while (r16U6.hasNext()) {
                                    Object item$iv$iv2 = r16U6.next();
                                    AuroraSession it12 = (AuroraSession) item$iv$iv2;
                                    Object $result5 = $result;
                                    destination$iv$iv2.add(defaultSessionRepository2.toEntity(it12, owner2));
                                    $result = $result5;
                                    break;
                                }
                                defaultSessionRepository$refreshSessions$2.L$0 = $this$withLock_u24default$iv3;
                                defaultSessionRepository$refreshSessions$2.L$1 = remote;
                                defaultSessionRepository$refreshSessions$2.L$2 = null;
                                defaultSessionRepository$refreshSessions$2.label = 7;
                                if (sessionDao.replaceForOwner(owner2, (List) destination$iv$iv2, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                i3 = i;
                                Result.Companion companion15 = Result.Companion;
                                objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(remote);
                                objM7388constructorimpl = objM7388constructorimpl2;
                            }
                        } else {
                            Result.Companion companion16 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(err));
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                        }
                        $this$withLock_u24default$iv2.unlock(owner$iv);
                        return objM7388constructorimpl;
                    case 4:
                        i2 = 0;
                        owner$iv = null;
                        Mutex $this$withLock_u24default$iv4 = (Mutex) defaultSessionRepository$refreshSessions$2.L$1;
                        $this$withLock_u24default$iv2 = $this$withLock_u24default$iv4;
                        DefaultSessionRepository defaultSessionRepository4 = (DefaultSessionRepository) defaultSessionRepository$refreshSessions$2.L$0;
                        ResultKt.throwOnFailure($result);
                        defaultSessionRepository2 = defaultSessionRepository4;
                        all = $result;
                        Iterable $this$map$iv13 = (Iterable) all;
                        destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv13, 10));
                        java.util.Iterator<Object> r12U7 = $this$map$iv13.iterator();
                        while (r12U7.hasNext()) {
                            Object item$iv$iv = r12U7.next();
                            SessionEntity it13 = (SessionEntity) item$iv$iv;
                            destination$iv$iv.add(defaultSessionRepository2.toModel(it13));
                        }
                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv);
                        $this$withLock_u24default$iv2.unlock(owner$iv);
                        return objM7388constructorimpl;
                    case 5:
                        gen = defaultSessionRepository$refreshSessions$2.J$0;
                        Mutex $this$withLock_u24default$iv5 = (Mutex) defaultSessionRepository$refreshSessions$2.L$2;
                        String owner3 = (String) defaultSessionRepository$refreshSessions$2.L$1;
                        DefaultSessionRepository defaultSessionRepository5 = (DefaultSessionRepository) defaultSessionRepository$refreshSessions$2.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            objM44getSessionsIoAF18A = ai.nous.aurora.compat.Compat.resultUnbox($result);
                            i = 0;
                            owner$iv = null;
                            $this$withLock_u24default$iv3 = $this$withLock_u24default$iv5;
                            owner2 = owner3;
                            defaultSessionRepository2 = defaultSessionRepository5;
                            err = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(objM44getSessionsIoAF18A);
                            if (err == null) {
                                remote = (List) objM44getSessionsIoAF18A;
                                if (SessionSyncPolicy.INSTANCE.shouldApplyRefresh(gen, defaultSessionRepository2.refreshGeneration.get())) {
                                    Result.Companion companion17 = Result.Companion;
                                    SessionDao sessionDao10 = defaultSessionRepository2.sessions;
                                    defaultSessionRepository$refreshSessions$2.L$0 = defaultSessionRepository2;
                                    defaultSessionRepository$refreshSessions$2.L$1 = $this$withLock_u24default$iv3;
                                    defaultSessionRepository$refreshSessions$2.L$2 = null;
                                    defaultSessionRepository$refreshSessions$2.label = 6;
                                    all2 = sessionDao10.getAll(owner2, defaultSessionRepository$refreshSessions$2);
                                    if (all2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                    defaultSessionRepository3 = defaultSessionRepository2;
                                    i4 = i;
                                    Iterable $this$map$iv14 = (Iterable) all2;
                                    destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv14, 10));
                                    java.util.Iterator<Object> r12U8 = $this$map$iv14.iterator();
                                    while (r12U8.hasNext()) {
    Object item$iv$iv3 = r12U8.next();

                                        SessionEntity it14 = (SessionEntity) item$iv$iv3;
                                        destination$iv$iv3.add(defaultSessionRepository3.toModel(it14));
                                    }
                                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv3);
                                    objM7388constructorimpl = objM7388constructorimpl2;
                                } else {
                                    sessionDao = defaultSessionRepository2.sessions;
                                    List $this$map$iv15 = remote;
                                    destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv15, 10));
                                    java.util.Iterator<Object> r16U9 = $this$map$iv15.iterator();
                                    while (r16U9.hasNext()) {
                                        Object item$iv$iv2 = r16U9.next();
                                        AuroraSession it15 = (AuroraSession) item$iv$iv2;
                                        Object $result6 = $result;
                                        destination$iv$iv2.add(defaultSessionRepository2.toEntity(it15, owner2));
                                        $result = $result6;
                                        break;
                                    }
                                    defaultSessionRepository$refreshSessions$2.L$0 = $this$withLock_u24default$iv3;
                                    defaultSessionRepository$refreshSessions$2.L$1 = remote;
                                    defaultSessionRepository$refreshSessions$2.L$2 = null;
                                    defaultSessionRepository$refreshSessions$2.label = 7;
                                    if (sessionDao.replaceForOwner(owner2, (List) destination$iv$iv2, defaultSessionRepository$refreshSessions$2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                    i3 = i;
                                    Result.Companion companion18 = Result.Companion;
                                    objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(remote);
                                    objM7388constructorimpl = objM7388constructorimpl2;
                                }
                            } else {
                                Result.Companion companion19 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(err));
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                            }
                            $this$withLock_u24default$iv2.unlock(owner$iv);
                            return objM7388constructorimpl;
                        } catch (Throwable th4) {
                            owner$iv = null;
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv5;
                            $this$withLock_u24default$iv2.unlock(owner$iv);
                            throw th4;
                        }
                    case 6:
                        i4 = 0;
                        owner$iv = null;
                        $this$withLock_u24default$iv2 = (Mutex) defaultSessionRepository$refreshSessions$2.L$1;
                        defaultSessionRepository3 = (DefaultSessionRepository) defaultSessionRepository$refreshSessions$2.L$0;
                        ResultKt.throwOnFailure($result);
                        all2 = $result;
                        Iterable $this$map$iv16 = (Iterable) all2;
                        destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv16, 10));
                        java.util.Iterator<Object> r12U10 = $this$map$iv16.iterator();
                        while (r12U10.hasNext()) {
    Object item$iv$iv3 = r12U10.next();

                            SessionEntity it16 = (SessionEntity) item$iv$iv3;
                            destination$iv$iv3.add(defaultSessionRepository3.toModel(it16));
                        }
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl((List) destination$iv$iv3);
                        objM7388constructorimpl = objM7388constructorimpl2;
                        $this$withLock_u24default$iv2.unlock(owner$iv);
                        return objM7388constructorimpl;
                    case 7:
                        i3 = 0;
                        remote = (List) defaultSessionRepository$refreshSessions$2.L$1;
                        owner$iv = null;
                        $this$withLock_u24default$iv2 = (Mutex) defaultSessionRepository$refreshSessions$2.L$0;
                        ResultKt.throwOnFailure($result);
                        Result.Companion companion110 = Result.Companion;
                        objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(remote);
                        objM7388constructorimpl = objM7388constructorimpl2;
                        $this$withLock_u24default$iv2.unlock(owner$iv);
                        return objM7388constructorimpl;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Throwable th5) {
                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
            }
        } catch (Throwable th6) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0078 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x008a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x008b  */
    /* JADX WARN: Code duplicated, block: B:28:0x0095  */
    /* JADX WARN: Code duplicated, block: B:30:0x00ac A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:33:0x00af A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    /* JADX INFO: renamed from: createSession-IoAF18A, reason: not valid java name */
    public Object mo52createSessionIoAF18A(Continuation continuation) {
        DefaultSessionRepository$createSession$1 defaultSessionRepository$createSession$1;
        DefaultSessionRepository defaultSessionRepository;
        Object objOwnerKey;
        String owner;
        Object objM42createSessiongIAlus;
        DefaultSessionRepository defaultSessionRepository2;
        Object value;
        SessionDao sessionDao;
        SessionEntity entity;
        if (continuation instanceof DefaultSessionRepository$createSession$1) {
            defaultSessionRepository$createSession$1 = (DefaultSessionRepository$createSession$1) continuation;
            if ((defaultSessionRepository$createSession$1.label & Integer.MIN_VALUE) != 0) {
                defaultSessionRepository$createSession$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSessionRepository$createSession$1 = new DefaultSessionRepository$createSession$1(this, continuation);
            }
        } else {
            defaultSessionRepository$createSession$1 = new DefaultSessionRepository$createSession$1(this, continuation);
        }
        Object $result = defaultSessionRepository$createSession$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSessionRepository$createSession$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                defaultSessionRepository$createSession$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$createSession$1.label = 1;
                if (defaultSessionRepository.ensureLegacyAdopted(defaultSessionRepository$createSession$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository$createSession$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$createSession$1.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$createSession$1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                owner = (String) objOwnerKey;
                GatewayClient gatewayClient = defaultSessionRepository.gatewayClient;
                defaultSessionRepository$createSession$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$createSession$1.L$1 = owner;
                defaultSessionRepository$createSession$1.label = 3;
                objM42createSessiongIAlus = gatewayClient.m42createSessiongIAlus(null, defaultSessionRepository$createSession$1);
                if (objM42createSessiongIAlus == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                value = objM42createSessiongIAlus;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return value;
                }
                AuroraSession created = (AuroraSession) value;
                sessionDao = defaultSessionRepository2.sessions;
                entity = defaultSessionRepository2.toEntity(created, owner);
                defaultSessionRepository$createSession$1.L$0 = value;
                defaultSessionRepository$createSession$1.L$1 = null;
                defaultSessionRepository$createSession$1.label = 4;
                if (sessionDao.insert(entity, defaultSessionRepository$createSession$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return value;
            case 1:
                defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$createSession$1.L$0;
                ResultKt.throwOnFailure($result);
                defaultSessionRepository$createSession$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$createSession$1.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$createSession$1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                owner = (String) objOwnerKey;
                GatewayClient gatewayClient2 = defaultSessionRepository.gatewayClient;
                defaultSessionRepository$createSession$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$createSession$1.L$1 = owner;
                defaultSessionRepository$createSession$1.label = 3;
                objM42createSessiongIAlus = gatewayClient2.m42createSessiongIAlus(null, defaultSessionRepository$createSession$1);
                if (objM42createSessiongIAlus == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                value = objM42createSessiongIAlus;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return value;
                }
                AuroraSession created2 = (AuroraSession) value;
                sessionDao = defaultSessionRepository2.sessions;
                entity = defaultSessionRepository2.toEntity(created2, owner);
                defaultSessionRepository$createSession$1.L$0 = value;
                defaultSessionRepository$createSession$1.L$1 = null;
                defaultSessionRepository$createSession$1.label = 4;
                if (sessionDao.insert(entity, defaultSessionRepository$createSession$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return value;
            case 2:
                defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$createSession$1.L$0;
                ResultKt.throwOnFailure($result);
                objOwnerKey = $result;
                owner = (String) objOwnerKey;
                GatewayClient gatewayClient3 = defaultSessionRepository.gatewayClient;
                defaultSessionRepository$createSession$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$createSession$1.L$1 = owner;
                defaultSessionRepository$createSession$1.label = 3;
                objM42createSessiongIAlus = gatewayClient3.m42createSessiongIAlus(null, defaultSessionRepository$createSession$1);
                if (objM42createSessiongIAlus == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                value = objM42createSessiongIAlus;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return value;
                }
                AuroraSession created3 = (AuroraSession) value;
                sessionDao = defaultSessionRepository2.sessions;
                entity = defaultSessionRepository2.toEntity(created3, owner);
                defaultSessionRepository$createSession$1.L$0 = value;
                defaultSessionRepository$createSession$1.L$1 = null;
                defaultSessionRepository$createSession$1.label = 4;
                if (sessionDao.insert(entity, defaultSessionRepository$createSession$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return value;
            case 3:
                String owner2 = (String) defaultSessionRepository$createSession$1.L$1;
                DefaultSessionRepository defaultSessionRepository3 = (DefaultSessionRepository) defaultSessionRepository$createSession$1.L$0;
                ResultKt.throwOnFailure($result);
                owner = owner2;
                value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                defaultSessionRepository2 = defaultSessionRepository3;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return value;
                }
                AuroraSession created4 = (AuroraSession) value;
                sessionDao = defaultSessionRepository2.sessions;
                entity = defaultSessionRepository2.toEntity(created4, owner);
                defaultSessionRepository$createSession$1.L$0 = value;
                defaultSessionRepository$createSession$1.L$1 = null;
                defaultSessionRepository$createSession$1.label = 4;
                if (sessionDao.insert(entity, defaultSessionRepository$createSession$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return value;
            case 4:
                Object obj = defaultSessionRepository$createSession$1.L$0;
                ResultKt.throwOnFailure($result);
                return obj;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x02ed  */
    /* JADX WARN: Code duplicated, block: B:106:0x030a A[Catch: all -> 0x0334, TryCatch #0 {all -> 0x0334, blocks: (B:103:0x02f5, B:104:0x0304, B:106:0x030a, B:110:0x031f, B:112:0x0323, B:113:0x0328, B:114:0x0333), top: B:119:0x02f5 }] */
    /* JADX WARN: Code duplicated, block: B:112:0x0323 A[Catch: all -> 0x0334, TryCatch #0 {all -> 0x0334, blocks: (B:103:0x02f5, B:104:0x0304, B:106:0x030a, B:110:0x031f, B:112:0x0323, B:113:0x0328, B:114:0x0333), top: B:119:0x02f5 }] */
    /* JADX WARN: Code duplicated, block: B:113:0x0328 A[Catch: all -> 0x0334, TryCatch #0 {all -> 0x0334, blocks: (B:103:0x02f5, B:104:0x0304, B:106:0x030a, B:110:0x031f, B:112:0x0323, B:113:0x0328, B:114:0x0333), top: B:119:0x02f5 }] */
    /* JADX WARN: Code duplicated, block: B:117:0x0340  */
    /* JADX WARN: Code duplicated, block: B:119:0x02f5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:120:0x031e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:121:0x031c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:122:? A[LOOP:0: B:104:0x0304->B:122:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:123:0x022e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:128:0x0189 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0115 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x0116  */
    /* JADX WARN: Code duplicated, block: B:30:0x012e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x012f  */
    /* JADX WARN: Code duplicated, block: B:34:0x013c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0153 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:37:0x0154  */
    /* JADX WARN: Code duplicated, block: B:41:0x017e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0186  */
    /* JADX WARN: Code duplicated, block: B:47:0x01da A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:48:0x01db  */
    /* JADX WARN: Code duplicated, block: B:51:0x01f5 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:55:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:60:0x0218  */
    /* JADX WARN: Code duplicated, block: B:66:0x0234  */
    /* JADX WARN: Code duplicated, block: B:67:0x0239  */
    /* JADX WARN: Code duplicated, block: B:70:0x023f  */
    /* JADX WARN: Code duplicated, block: B:75:0x024a  */
    /* JADX WARN: Code duplicated, block: B:77:0x0288 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:78:0x0289  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:81:0x0298  */
    /* JADX WARN: Code duplicated, block: B:84:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:86:0x02af  */
    /* JADX WARN: Code duplicated, block: B:88:0x02c6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:89:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:92:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:93:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:96:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:97:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:99:0x02ec A[RETURN] */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    /* JADX INFO: renamed from: selectSession-gIAlu-s, reason: not valid java name */
    public Object mo55selectSessiongIAlus(String str, Continuation continuation) throws Throwable {
        DefaultSessionRepository$selectSession$1 defaultSessionRepository$selectSession$1;
        DefaultSessionRepository defaultSessionRepository;
        String sessionId;
        Object objOwnerKey;
        String owner;
        DefaultSessionRepository defaultSessionRepository2;
        String owner2;
        Object msgs;
        DefaultSessionRepository defaultSessionRepository3;
        String owner3;
        DefaultSessionRepository defaultSessionRepository4;
        List<AuroraMessage> list;
        String owner4;
        String sessionId2;
        DefaultSessionRepository defaultSessionRepository5;
        Object obj;
        List<AuroraMessage> list2;
        String owner5;
        MessageDao messageDao;
        Collection destination$iv$iv;
        int index$iv$iv;
        DefaultSessionRepository defaultSessionRepository6;
        String owner6;
        String sessionId3;
        DefaultSessionRepository defaultSessionRepository7;
        String sessionId4;
        String owner7;
        Object byId;
        Object obj2;
        String sessionId5;
        SessionEntity existing;
        Iterator it;
        boolean z;
        Object element$iv;
        AuroraMessage auroraMessage;
        String firstUser;
        String str2;
        SessionEntity named;
        SessionDao sessionDao;
        String owner8;
        DefaultSessionRepository defaultSessionRepository8;
        Throwable it2;
        Object byId2;
        String sessionId6;
        SessionEntity sessionEntity;
        AuroraSession model;
        AuroraSession local;
        Object obj3;
        Object objMo53refreshSessionsIoAF18A;
        String sessionId7;
        Object value;
        Iterator it3;
        Object obj4;
        AuroraSession auroraSession;
        Object element$iv2;
        if (continuation instanceof DefaultSessionRepository$selectSession$1) {
            defaultSessionRepository$selectSession$1 = (DefaultSessionRepository$selectSession$1) continuation;
            if ((defaultSessionRepository$selectSession$1.label & Integer.MIN_VALUE) != 0) {
                defaultSessionRepository$selectSession$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSessionRepository$selectSession$1 = new DefaultSessionRepository$selectSession$1(this, continuation);
            }
        } else {
            defaultSessionRepository$selectSession$1 = new DefaultSessionRepository$selectSession$1(this, continuation);
        }
        DefaultSessionRepository$selectSession$1 defaultSessionRepository$selectSession$2 = defaultSessionRepository$selectSession$1;
        Object $result = defaultSessionRepository$selectSession$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSessionRepository$selectSession$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                sessionId = str;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository;
                defaultSessionRepository$selectSession$2.L$1 = sessionId;
                defaultSessionRepository$selectSession$2.label = 1;
                if (defaultSessionRepository.ensureLegacyAdopted(defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository;
                defaultSessionRepository$selectSession$2.L$1 = sessionId;
                defaultSessionRepository$selectSession$2.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$selectSession$2);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                DefaultSessionRepository defaultSessionRepository9 = defaultSessionRepository;
                owner = sessionId;
                defaultSessionRepository2 = defaultSessionRepository9;
                owner2 = (String) objOwnerKey;
                GatewayClient gatewayClient = defaultSessionRepository2.gatewayClient;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository2;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = owner2;
                defaultSessionRepository$selectSession$2.label = 3;
                msgs = gatewayClient.m43getMessagesgIAlus(owner, defaultSessionRepository$selectSession$2);
                if (msgs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                owner3 = owner2;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(msgs)) {
                    list = (List) msgs;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository3;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = owner3;
                    defaultSessionRepository$selectSession$2.L$3 = msgs;
                    defaultSessionRepository$selectSession$2.L$4 = list;
                    defaultSessionRepository$selectSession$2.label = 4;
                    if (defaultSessionRepository3.reconcileAmbiguousOutbox(owner, owner3, list, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    owner4 = owner3;
                    sessionId2 = owner;
                    defaultSessionRepository5 = defaultSessionRepository3;
                    obj = msgs;
                    list2 = list;
                    owner5 = null;
                    messageDao = defaultSessionRepository5.messages;
                    Iterable $this$mapIndexed$iv = list2;
                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
                    index$iv$iv = 0;
                    for (Object item$iv$iv : $this$mapIndexed$iv) {
                        int index$iv$iv2 = index$iv$iv + 1;
                        if (index$iv$iv < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        AuroraMessage m = (AuroraMessage) item$iv$iv;
                        destination$iv$iv = destination$iv$iv;
                        destination$iv$iv.add(defaultSessionRepository5.toEntity(m, sessionId2, owner4, index$iv$iv));
                        index$iv$iv = index$iv$iv2;
                        $result = $result;
                    }
                    defaultSessionRepository6 = defaultSessionRepository5;
                    owner6 = owner4;
                    sessionId3 = sessionId2;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository6;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId3;
                    defaultSessionRepository$selectSession$2.L$2 = owner6;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 5;
                    if (messageDao.replaceForSession(sessionId3, owner6, (List) destination$iv$iv, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSessionRepository7 = defaultSessionRepository6;
                    sessionId4 = sessionId3;
                    owner7 = owner6;
                    SessionDao sessionDao2 = defaultSessionRepository7.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId4;
                    defaultSessionRepository$selectSession$2.L$2 = owner7;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 6;
                    byId = sessionDao2.getById(sessionId4, owner7, defaultSessionRepository$selectSession$2);
                    if (byId == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj;
                    sessionId5 = sessionId4;
                    existing = (SessionEntity) byId;
                    if (existing != null && SessionPresentation.INSTANCE.isGenericTitle(existing.getTitle())) {
                        Iterable $this$firstOrNull$iv = list2;
                        it = $this$firstOrNull$iv.iterator();
                        do {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                z = true;
                            } else {
                                z = true;
                                element$iv = null;
                            }
                            auroraMessage = (AuroraMessage) element$iv;
                            if (auroraMessage != null) {
                                firstUser = auroraMessage.getContent();
                            } else {
                                firstUser = null;
                            }
                            str2 = firstUser;
                            if (str2 != null && !StringsKt.isBlank(str2)) {
                                z = false;
                            }
                            if (!z) {
                                named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                                sessionDao = defaultSessionRepository7.sessions;
                                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                                defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                                defaultSessionRepository$selectSession$2.L$2 = owner7;
                                defaultSessionRepository$selectSession$2.L$3 = obj2;
                                defaultSessionRepository$selectSession$2.L$4 = null;
                                defaultSessionRepository$selectSession$2.label = 7;
                                if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                owner8 = owner7;
                                defaultSessionRepository8 = defaultSessionRepository7;
                                owner3 = owner8;
                                owner = sessionId5;
                                DefaultSessionRepository defaultSessionRepository10 = defaultSessionRepository8;
                                msgs = obj2;
                                defaultSessionRepository4 = defaultSessionRepository10;
                            }
                        } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository11 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository11;
                        }
                    }
                    owner = sessionId5;
                    owner3 = owner7;
                    msgs = obj2;
                    defaultSessionRepository4 = defaultSessionRepository7;
                } else {
                    defaultSessionRepository4 = defaultSessionRepository3;
                }
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao3 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao3.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion2 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                try {
                    Result.Companion companion3 = Result.Companion;
                    Iterable list3 = (List) value;
                    Iterable $this$firstOrNull$iv2 = list3;
                    it3 = $this$firstOrNull$iv2.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            element$iv2 = it3.next();
                            if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                obj4 = element$iv2;
                            }
                        } else {
                            obj4 = obj3;
                        }
                    }
                    auroraSession = (AuroraSession) obj4;
                    if (auroraSession != null) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                    }
                    throw new IllegalStateException("Session not found".toString());
                } catch (Throwable th) {
                    Result.Companion companion4 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
                }
            case 1:
                sessionId = (String) defaultSessionRepository$selectSession$2.L$1;
                defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository;
                defaultSessionRepository$selectSession$2.L$1 = sessionId;
                defaultSessionRepository$selectSession$2.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$selectSession$2);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                DefaultSessionRepository defaultSessionRepository12 = defaultSessionRepository;
                owner = sessionId;
                defaultSessionRepository2 = defaultSessionRepository12;
                owner2 = (String) objOwnerKey;
                GatewayClient gatewayClient2 = defaultSessionRepository2.gatewayClient;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository2;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = owner2;
                defaultSessionRepository$selectSession$2.label = 3;
                msgs = gatewayClient2.m43getMessagesgIAlus(owner, defaultSessionRepository$selectSession$2);
                if (msgs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                owner3 = owner2;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(msgs)) {
                    list = (List) msgs;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository3;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = owner3;
                    defaultSessionRepository$selectSession$2.L$3 = msgs;
                    defaultSessionRepository$selectSession$2.L$4 = list;
                    defaultSessionRepository$selectSession$2.label = 4;
                    if (defaultSessionRepository3.reconcileAmbiguousOutbox(owner, owner3, list, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    owner4 = owner3;
                    sessionId2 = owner;
                    defaultSessionRepository5 = defaultSessionRepository3;
                    obj = msgs;
                    list2 = list;
                    owner5 = null;
                    messageDao = defaultSessionRepository5.messages;
                    Iterable $this$mapIndexed$iv2 = list2;
                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv2, 10));
                    index$iv$iv = 0;
                    java.util.Iterator<Object> r18U11 = $this$mapIndexed$iv2.iterator();
                    while (r18U11.hasNext()) {
    Object item$iv$iv = r18U11.next();

                        int index$iv$iv3 = index$iv$iv + 1;
                        if (index$iv$iv < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        AuroraMessage m2 = (AuroraMessage) item$iv$iv;
                        destination$iv$iv = destination$iv$iv;
                        destination$iv$iv.add(defaultSessionRepository5.toEntity(m2, sessionId2, owner4, index$iv$iv));
                        index$iv$iv = index$iv$iv3;
                        $result = $result;
                    }
                    defaultSessionRepository6 = defaultSessionRepository5;
                    owner6 = owner4;
                    sessionId3 = sessionId2;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository6;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId3;
                    defaultSessionRepository$selectSession$2.L$2 = owner6;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 5;
                    if (messageDao.replaceForSession(sessionId3, owner6, (List) destination$iv$iv, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSessionRepository7 = defaultSessionRepository6;
                    sessionId4 = sessionId3;
                    owner7 = owner6;
                    SessionDao sessionDao4 = defaultSessionRepository7.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId4;
                    defaultSessionRepository$selectSession$2.L$2 = owner7;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 6;
                    byId = sessionDao4.getById(sessionId4, owner7, defaultSessionRepository$selectSession$2);
                    if (byId == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj;
                    sessionId5 = sessionId4;
                    existing = (SessionEntity) byId;
                    if (existing != null) {
                        Iterable $this$firstOrNull$iv3 = list2;
                        it = $this$firstOrNull$iv3.iterator();
                        do {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                z = true;
                            } else {
                                z = true;
                                element$iv = null;
                            }
                            auroraMessage = (AuroraMessage) element$iv;
                            if (auroraMessage != null) {
                                firstUser = auroraMessage.getContent();
                            } else {
                                firstUser = null;
                            }
                            str2 = firstUser;
                            if (str2 != null) {
                                z = false;
                            }
                            if (!z) {
                                named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                                sessionDao = defaultSessionRepository7.sessions;
                                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                                defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                                defaultSessionRepository$selectSession$2.L$2 = owner7;
                                defaultSessionRepository$selectSession$2.L$3 = obj2;
                                defaultSessionRepository$selectSession$2.L$4 = null;
                                defaultSessionRepository$selectSession$2.label = 7;
                                if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                owner8 = owner7;
                                defaultSessionRepository8 = defaultSessionRepository7;
                                owner3 = owner8;
                                owner = sessionId5;
                                DefaultSessionRepository defaultSessionRepository13 = defaultSessionRepository8;
                                msgs = obj2;
                                defaultSessionRepository4 = defaultSessionRepository13;
                            }
                        } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository14 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository14;
                        }
                    }
                    owner = sessionId5;
                    owner3 = owner7;
                    msgs = obj2;
                    defaultSessionRepository4 = defaultSessionRepository7;
                } else {
                    defaultSessionRepository4 = defaultSessionRepository3;
                }
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion5 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao5 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao5.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion6 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion7 = Result.Companion;
                Iterable list4 = (List) value;
                Iterable $this$firstOrNull$iv4 = list4;
                it3 = $this$firstOrNull$iv4.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 2:
                String sessionId8 = (String) defaultSessionRepository$selectSession$2.L$1;
                DefaultSessionRepository defaultSessionRepository15 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                objOwnerKey = $result;
                owner = sessionId8;
                defaultSessionRepository2 = defaultSessionRepository15;
                owner2 = (String) objOwnerKey;
                GatewayClient gatewayClient3 = defaultSessionRepository2.gatewayClient;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository2;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = owner2;
                defaultSessionRepository$selectSession$2.label = 3;
                msgs = gatewayClient3.m43getMessagesgIAlus(owner, defaultSessionRepository$selectSession$2);
                if (msgs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                owner3 = owner2;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(msgs)) {
                    list = (List) msgs;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository3;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = owner3;
                    defaultSessionRepository$selectSession$2.L$3 = msgs;
                    defaultSessionRepository$selectSession$2.L$4 = list;
                    defaultSessionRepository$selectSession$2.label = 4;
                    if (defaultSessionRepository3.reconcileAmbiguousOutbox(owner, owner3, list, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    owner4 = owner3;
                    sessionId2 = owner;
                    defaultSessionRepository5 = defaultSessionRepository3;
                    obj = msgs;
                    list2 = list;
                    owner5 = null;
                    messageDao = defaultSessionRepository5.messages;
                    Iterable $this$mapIndexed$iv3 = list2;
                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv3, 10));
                    index$iv$iv = 0;
                    java.util.Iterator<Object> r18 = $this$mapIndexed$iv3.iterator();
                    java.util.Iterator<Object> r18U12 = $this$mapIndexed$iv3.iterator();
                    while (r18U12.hasNext()) {
    Object item$iv$iv = r18U12.next();

                        int index$iv$iv4 = index$iv$iv + 1;
                        if (index$iv$iv < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        AuroraMessage m3 = (AuroraMessage) item$iv$iv;
                        destination$iv$iv = destination$iv$iv;
                        destination$iv$iv.add(defaultSessionRepository5.toEntity(m3, sessionId2, owner4, index$iv$iv));
                        index$iv$iv = index$iv$iv4;
                        $result = $result;
                    }
                    defaultSessionRepository6 = defaultSessionRepository5;
                    owner6 = owner4;
                    sessionId3 = sessionId2;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository6;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId3;
                    defaultSessionRepository$selectSession$2.L$2 = owner6;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 5;
                    if (messageDao.replaceForSession(sessionId3, owner6, (List) destination$iv$iv, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSessionRepository7 = defaultSessionRepository6;
                    sessionId4 = sessionId3;
                    owner7 = owner6;
                    SessionDao sessionDao6 = defaultSessionRepository7.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId4;
                    defaultSessionRepository$selectSession$2.L$2 = owner7;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 6;
                    byId = sessionDao6.getById(sessionId4, owner7, defaultSessionRepository$selectSession$2);
                    if (byId == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj;
                    sessionId5 = sessionId4;
                    existing = (SessionEntity) byId;
                    if (existing != null) {
                        Iterable $this$firstOrNull$iv5 = list2;
                        it = $this$firstOrNull$iv5.iterator();
                        do {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                z = true;
                            } else {
                                z = true;
                                element$iv = null;
                            }
                            auroraMessage = (AuroraMessage) element$iv;
                            if (auroraMessage != null) {
                                firstUser = auroraMessage.getContent();
                            } else {
                                firstUser = null;
                            }
                            str2 = firstUser;
                            if (str2 != null) {
                                z = false;
                            }
                            if (!z) {
                                named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                                sessionDao = defaultSessionRepository7.sessions;
                                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                                defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                                defaultSessionRepository$selectSession$2.L$2 = owner7;
                                defaultSessionRepository$selectSession$2.L$3 = obj2;
                                defaultSessionRepository$selectSession$2.L$4 = null;
                                defaultSessionRepository$selectSession$2.label = 7;
                                if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                owner8 = owner7;
                                defaultSessionRepository8 = defaultSessionRepository7;
                                owner3 = owner8;
                                owner = sessionId5;
                                DefaultSessionRepository defaultSessionRepository16 = defaultSessionRepository8;
                                msgs = obj2;
                                defaultSessionRepository4 = defaultSessionRepository16;
                            }
                        } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository17 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository17;
                        }
                    }
                    owner = sessionId5;
                    owner3 = owner7;
                    msgs = obj2;
                    defaultSessionRepository4 = defaultSessionRepository7;
                } else {
                    defaultSessionRepository4 = defaultSessionRepository3;
                }
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion8 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao7 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao7.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion9 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion10 = Result.Companion;
                Iterable list5 = (List) value;
                Iterable $this$firstOrNull$iv6 = list5;
                it3 = $this$firstOrNull$iv6.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 3:
                owner3 = (String) defaultSessionRepository$selectSession$2.L$2;
                owner = (String) defaultSessionRepository$selectSession$2.L$1;
                defaultSessionRepository3 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                msgs = ai.nous.aurora.compat.Compat.resultUnbox($result);
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(msgs)) {
                    list = (List) msgs;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository3;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = owner3;
                    defaultSessionRepository$selectSession$2.L$3 = msgs;
                    defaultSessionRepository$selectSession$2.L$4 = list;
                    defaultSessionRepository$selectSession$2.label = 4;
                    if (defaultSessionRepository3.reconcileAmbiguousOutbox(owner, owner3, list, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    owner4 = owner3;
                    sessionId2 = owner;
                    defaultSessionRepository5 = defaultSessionRepository3;
                    obj = msgs;
                    list2 = list;
                    owner5 = null;
                    messageDao = defaultSessionRepository5.messages;
                    Iterable $this$mapIndexed$iv4 = list2;
                    destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv4, 10));
                    index$iv$iv = 0;
                    java.util.Iterator<Object> r18U13 = $this$mapIndexed$iv4.iterator();
                    while (r18U13.hasNext()) {
    Object item$iv$iv = r18U13.next();

                        int index$iv$iv5 = index$iv$iv + 1;
                        if (index$iv$iv < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        AuroraMessage m4 = (AuroraMessage) item$iv$iv;
                        destination$iv$iv = destination$iv$iv;
                        destination$iv$iv.add(defaultSessionRepository5.toEntity(m4, sessionId2, owner4, index$iv$iv));
                        index$iv$iv = index$iv$iv5;
                        $result = $result;
                    }
                    defaultSessionRepository6 = defaultSessionRepository5;
                    owner6 = owner4;
                    sessionId3 = sessionId2;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository6;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId3;
                    defaultSessionRepository$selectSession$2.L$2 = owner6;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 5;
                    if (messageDao.replaceForSession(sessionId3, owner6, (List) destination$iv$iv, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    defaultSessionRepository7 = defaultSessionRepository6;
                    sessionId4 = sessionId3;
                    owner7 = owner6;
                    SessionDao sessionDao8 = defaultSessionRepository7.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                    defaultSessionRepository$selectSession$2.L$1 = sessionId4;
                    defaultSessionRepository$selectSession$2.L$2 = owner7;
                    defaultSessionRepository$selectSession$2.L$3 = obj;
                    defaultSessionRepository$selectSession$2.L$4 = list2;
                    defaultSessionRepository$selectSession$2.label = 6;
                    byId = sessionDao8.getById(sessionId4, owner7, defaultSessionRepository$selectSession$2);
                    if (byId == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = obj;
                    sessionId5 = sessionId4;
                    existing = (SessionEntity) byId;
                    if (existing != null) {
                        Iterable $this$firstOrNull$iv7 = list2;
                        it = $this$firstOrNull$iv7.iterator();
                        do {
                            if (it.hasNext()) {
                                element$iv = it.next();
                                z = true;
                            } else {
                                z = true;
                                element$iv = null;
                            }
                            auroraMessage = (AuroraMessage) element$iv;
                            if (auroraMessage != null) {
                                firstUser = auroraMessage.getContent();
                            } else {
                                firstUser = null;
                            }
                            str2 = firstUser;
                            if (str2 != null) {
                                z = false;
                            }
                            if (!z) {
                                named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                                sessionDao = defaultSessionRepository7.sessions;
                                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                                defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                                defaultSessionRepository$selectSession$2.L$2 = owner7;
                                defaultSessionRepository$selectSession$2.L$3 = obj2;
                                defaultSessionRepository$selectSession$2.L$4 = null;
                                defaultSessionRepository$selectSession$2.label = 7;
                                if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                owner8 = owner7;
                                defaultSessionRepository8 = defaultSessionRepository7;
                                owner3 = owner8;
                                owner = sessionId5;
                                DefaultSessionRepository defaultSessionRepository18 = defaultSessionRepository8;
                                msgs = obj2;
                                defaultSessionRepository4 = defaultSessionRepository18;
                            }
                        } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository19 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository19;
                        }
                    }
                    owner = sessionId5;
                    owner3 = owner7;
                    msgs = obj2;
                    defaultSessionRepository4 = defaultSessionRepository7;
                } else {
                    defaultSessionRepository4 = defaultSessionRepository3;
                }
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion11 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao9 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao9.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion12 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion13 = Result.Companion;
                Iterable list6 = (List) value;
                Iterable $this$firstOrNull$iv8 = list6;
                it3 = $this$firstOrNull$iv8.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 4:
                owner5 = null;
                list2 = (List) defaultSessionRepository$selectSession$2.L$4;
                obj = defaultSessionRepository$selectSession$2.L$3;
                String owner9 = (String) defaultSessionRepository$selectSession$2.L$2;
                String sessionId9 = (String) defaultSessionRepository$selectSession$2.L$1;
                DefaultSessionRepository defaultSessionRepository20 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                owner4 = owner9;
                sessionId2 = sessionId9;
                defaultSessionRepository5 = defaultSessionRepository20;
                messageDao = defaultSessionRepository5.messages;
                Iterable $this$mapIndexed$iv5 = list2;
                destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv5, 10));
                index$iv$iv = 0;
                java.util.Iterator<Object> r18U14 = $this$mapIndexed$iv5.iterator();
                while (r18U14.hasNext()) {
    Object item$iv$iv = r18U14.next();

                    int index$iv$iv6 = index$iv$iv + 1;
                    if (index$iv$iv < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    AuroraMessage m5 = (AuroraMessage) item$iv$iv;
                    destination$iv$iv = destination$iv$iv;
                    destination$iv$iv.add(defaultSessionRepository5.toEntity(m5, sessionId2, owner4, index$iv$iv));
                    index$iv$iv = index$iv$iv6;
                    $result = $result;
                }
                defaultSessionRepository6 = defaultSessionRepository5;
                owner6 = owner4;
                sessionId3 = sessionId2;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository6;
                defaultSessionRepository$selectSession$2.L$1 = sessionId3;
                defaultSessionRepository$selectSession$2.L$2 = owner6;
                defaultSessionRepository$selectSession$2.L$3 = obj;
                defaultSessionRepository$selectSession$2.L$4 = list2;
                defaultSessionRepository$selectSession$2.label = 5;
                if (messageDao.replaceForSession(sessionId3, owner6, (List) destination$iv$iv, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository7 = defaultSessionRepository6;
                sessionId4 = sessionId3;
                owner7 = owner6;
                SessionDao sessionDao10 = defaultSessionRepository7.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                defaultSessionRepository$selectSession$2.L$1 = sessionId4;
                defaultSessionRepository$selectSession$2.L$2 = owner7;
                defaultSessionRepository$selectSession$2.L$3 = obj;
                defaultSessionRepository$selectSession$2.L$4 = list2;
                defaultSessionRepository$selectSession$2.label = 6;
                byId = sessionDao10.getById(sessionId4, owner7, defaultSessionRepository$selectSession$2);
                if (byId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = obj;
                sessionId5 = sessionId4;
                existing = (SessionEntity) byId;
                if (existing != null) {
                    Iterable $this$firstOrNull$iv9 = list2;
                    it = $this$firstOrNull$iv9.iterator();
                    do {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            z = true;
                        } else {
                            z = true;
                            element$iv = null;
                        }
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository110 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository110;
                        }
                        it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                        if (it2 != null) {
                            Result.Companion companion14 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                        }
                        SessionDao sessionDao11 = defaultSessionRepository4.sessions;
                        defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                        defaultSessionRepository$selectSession$2.L$1 = owner;
                        defaultSessionRepository$selectSession$2.L$2 = null;
                        defaultSessionRepository$selectSession$2.L$3 = null;
                        defaultSessionRepository$selectSession$2.L$4 = null;
                        defaultSessionRepository$selectSession$2.label = 8;
                        byId2 = sessionDao11.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                        if (byId2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionId6 = owner;
                        sessionEntity = (SessionEntity) byId2;
                        if (sessionEntity != null) {
                            model = defaultSessionRepository4.toModel(sessionEntity);
                        } else {
                            model = null;
                        }
                        local = model;
                        if (local != null) {
                            Result.Companion companion15 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                        }
                        defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                        obj3 = null;
                        defaultSessionRepository$selectSession$2.L$1 = null;
                        defaultSessionRepository$selectSession$2.label = 9;
                        objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                        if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionId7 = sessionId6;
                        value = objMo53refreshSessionsIoAF18A;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                        }
                        Result.Companion companion16 = Result.Companion;
                        Iterable list7 = (List) value;
                        Iterable $this$firstOrNull$iv10 = list7;
                        it3 = $this$firstOrNull$iv10.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                element$iv2 = it3.next();
                                if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                    obj4 = element$iv2;
                                }
                            } else {
                                obj4 = obj3;
                            }
                        }
                        auroraSession = (AuroraSession) obj4;
                        if (auroraSession != null) {
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                        }
                        throw new IllegalStateException("Session not found".toString());
                    } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                    auroraMessage = (AuroraMessage) element$iv;
                    if (auroraMessage != null) {
                        firstUser = auroraMessage.getContent();
                    } else {
                        firstUser = null;
                    }
                    str2 = firstUser;
                    if (str2 != null) {
                        z = false;
                    }
                    if (!z) {
                        named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                        sessionDao = defaultSessionRepository7.sessions;
                        defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                        defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                        defaultSessionRepository$selectSession$2.L$2 = owner7;
                        defaultSessionRepository$selectSession$2.L$3 = obj2;
                        defaultSessionRepository$selectSession$2.L$4 = null;
                        defaultSessionRepository$selectSession$2.label = 7;
                        if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        owner8 = owner7;
                        defaultSessionRepository8 = defaultSessionRepository7;
                        owner3 = owner8;
                        owner = sessionId5;
                        DefaultSessionRepository defaultSessionRepository111 = defaultSessionRepository8;
                        msgs = obj2;
                        defaultSessionRepository4 = defaultSessionRepository111;
                    }
                    it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                    if (it2 != null) {
                        Result.Companion companion17 = Result.Companion;
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                    }
                    SessionDao sessionDao12 = defaultSessionRepository4.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = null;
                    defaultSessionRepository$selectSession$2.L$3 = null;
                    defaultSessionRepository$selectSession$2.L$4 = null;
                    defaultSessionRepository$selectSession$2.label = 8;
                    byId2 = sessionDao12.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                    if (byId2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionId6 = owner;
                    sessionEntity = (SessionEntity) byId2;
                    if (sessionEntity != null) {
                        model = defaultSessionRepository4.toModel(sessionEntity);
                    } else {
                        model = null;
                    }
                    local = model;
                    if (local != null) {
                        Result.Companion companion18 = Result.Companion;
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                    }
                    defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                    obj3 = null;
                    defaultSessionRepository$selectSession$2.L$1 = null;
                    defaultSessionRepository$selectSession$2.label = 9;
                    objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                    if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionId7 = sessionId6;
                    value = objMo53refreshSessionsIoAF18A;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                    }
                    Result.Companion companion19 = Result.Companion;
                    Iterable list8 = (List) value;
                    Iterable $this$firstOrNull$iv11 = list8;
                    it3 = $this$firstOrNull$iv11.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            element$iv2 = it3.next();
                            if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                obj4 = element$iv2;
                            }
                        } else {
                            obj4 = obj3;
                        }
                    }
                    auroraSession = (AuroraSession) obj4;
                    if (auroraSession != null) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                    }
                    throw new IllegalStateException("Session not found".toString());
                }
                owner = sessionId5;
                owner3 = owner7;
                msgs = obj2;
                defaultSessionRepository4 = defaultSessionRepository7;
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion110 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao13 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao13.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion111 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion112 = Result.Companion;
                Iterable list9 = (List) value;
                Iterable $this$firstOrNull$iv12 = list9;
                it3 = $this$firstOrNull$iv12.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 5:
                list2 = (List) defaultSessionRepository$selectSession$2.L$4;
                obj = defaultSessionRepository$selectSession$2.L$3;
                owner7 = (String) defaultSessionRepository$selectSession$2.L$2;
                sessionId4 = (String) defaultSessionRepository$selectSession$2.L$1;
                defaultSessionRepository7 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                SessionDao sessionDao14 = defaultSessionRepository7.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                defaultSessionRepository$selectSession$2.L$1 = sessionId4;
                defaultSessionRepository$selectSession$2.L$2 = owner7;
                defaultSessionRepository$selectSession$2.L$3 = obj;
                defaultSessionRepository$selectSession$2.L$4 = list2;
                defaultSessionRepository$selectSession$2.label = 6;
                byId = sessionDao14.getById(sessionId4, owner7, defaultSessionRepository$selectSession$2);
                if (byId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = obj;
                sessionId5 = sessionId4;
                existing = (SessionEntity) byId;
                if (existing != null) {
                    Iterable $this$firstOrNull$iv13 = list2;
                    it = $this$firstOrNull$iv13.iterator();
                    do {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            z = true;
                        } else {
                            z = true;
                            element$iv = null;
                        }
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository112 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository112;
                        }
                        it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                        if (it2 != null) {
                            Result.Companion companion113 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                        }
                        SessionDao sessionDao15 = defaultSessionRepository4.sessions;
                        defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                        defaultSessionRepository$selectSession$2.L$1 = owner;
                        defaultSessionRepository$selectSession$2.L$2 = null;
                        defaultSessionRepository$selectSession$2.L$3 = null;
                        defaultSessionRepository$selectSession$2.L$4 = null;
                        defaultSessionRepository$selectSession$2.label = 8;
                        byId2 = sessionDao15.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                        if (byId2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionId6 = owner;
                        sessionEntity = (SessionEntity) byId2;
                        if (sessionEntity != null) {
                            model = defaultSessionRepository4.toModel(sessionEntity);
                        } else {
                            model = null;
                        }
                        local = model;
                        if (local != null) {
                            Result.Companion companion114 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                        }
                        defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                        obj3 = null;
                        defaultSessionRepository$selectSession$2.L$1 = null;
                        defaultSessionRepository$selectSession$2.label = 9;
                        objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                        if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionId7 = sessionId6;
                        value = objMo53refreshSessionsIoAF18A;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                        }
                        Result.Companion companion115 = Result.Companion;
                        Iterable list10 = (List) value;
                        Iterable $this$firstOrNull$iv14 = list10;
                        it3 = $this$firstOrNull$iv14.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                element$iv2 = it3.next();
                                if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                    obj4 = element$iv2;
                                }
                            } else {
                                obj4 = obj3;
                            }
                        }
                        auroraSession = (AuroraSession) obj4;
                        if (auroraSession != null) {
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                        }
                        throw new IllegalStateException("Session not found".toString());
                    } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                    auroraMessage = (AuroraMessage) element$iv;
                    if (auroraMessage != null) {
                        firstUser = auroraMessage.getContent();
                    } else {
                        firstUser = null;
                    }
                    str2 = firstUser;
                    if (str2 != null) {
                        z = false;
                    }
                    if (!z) {
                        named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                        sessionDao = defaultSessionRepository7.sessions;
                        defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                        defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                        defaultSessionRepository$selectSession$2.L$2 = owner7;
                        defaultSessionRepository$selectSession$2.L$3 = obj2;
                        defaultSessionRepository$selectSession$2.L$4 = null;
                        defaultSessionRepository$selectSession$2.label = 7;
                        if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        owner8 = owner7;
                        defaultSessionRepository8 = defaultSessionRepository7;
                        owner3 = owner8;
                        owner = sessionId5;
                        DefaultSessionRepository defaultSessionRepository113 = defaultSessionRepository8;
                        msgs = obj2;
                        defaultSessionRepository4 = defaultSessionRepository113;
                    }
                    it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                    if (it2 != null) {
                        Result.Companion companion116 = Result.Companion;
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                    }
                    SessionDao sessionDao16 = defaultSessionRepository4.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = null;
                    defaultSessionRepository$selectSession$2.L$3 = null;
                    defaultSessionRepository$selectSession$2.L$4 = null;
                    defaultSessionRepository$selectSession$2.label = 8;
                    byId2 = sessionDao16.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                    if (byId2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionId6 = owner;
                    sessionEntity = (SessionEntity) byId2;
                    if (sessionEntity != null) {
                        model = defaultSessionRepository4.toModel(sessionEntity);
                    } else {
                        model = null;
                    }
                    local = model;
                    if (local != null) {
                        Result.Companion companion117 = Result.Companion;
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                    }
                    defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                    obj3 = null;
                    defaultSessionRepository$selectSession$2.L$1 = null;
                    defaultSessionRepository$selectSession$2.label = 9;
                    objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                    if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionId7 = sessionId6;
                    value = objMo53refreshSessionsIoAF18A;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                    }
                    Result.Companion companion118 = Result.Companion;
                    Iterable list11 = (List) value;
                    Iterable $this$firstOrNull$iv15 = list11;
                    it3 = $this$firstOrNull$iv15.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            element$iv2 = it3.next();
                            if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                obj4 = element$iv2;
                            }
                        } else {
                            obj4 = obj3;
                        }
                    }
                    auroraSession = (AuroraSession) obj4;
                    if (auroraSession != null) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                    }
                    throw new IllegalStateException("Session not found".toString());
                }
                owner = sessionId5;
                owner3 = owner7;
                msgs = obj2;
                defaultSessionRepository4 = defaultSessionRepository7;
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion119 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao17 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao17.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion1110 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion1111 = Result.Companion;
                Iterable list12 = (List) value;
                Iterable $this$firstOrNull$iv16 = list12;
                it3 = $this$firstOrNull$iv16.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 6:
                list2 = (List) defaultSessionRepository$selectSession$2.L$4;
                Object obj5 = defaultSessionRepository$selectSession$2.L$3;
                owner7 = (String) defaultSessionRepository$selectSession$2.L$2;
                String sessionId10 = (String) defaultSessionRepository$selectSession$2.L$1;
                defaultSessionRepository7 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                byId = $result;
                obj2 = obj5;
                sessionId5 = sessionId10;
                existing = (SessionEntity) byId;
                if (existing != null) {
                    Iterable $this$firstOrNull$iv17 = list2;
                    it = $this$firstOrNull$iv17.iterator();
                    do {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            z = true;
                        } else {
                            z = true;
                            element$iv = null;
                        }
                        auroraMessage = (AuroraMessage) element$iv;
                        if (auroraMessage != null) {
                            firstUser = auroraMessage.getContent();
                        } else {
                            firstUser = null;
                        }
                        str2 = firstUser;
                        if (str2 != null) {
                            z = false;
                        }
                        if (!z) {
                            named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                            sessionDao = defaultSessionRepository7.sessions;
                            defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                            defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                            defaultSessionRepository$selectSession$2.L$2 = owner7;
                            defaultSessionRepository$selectSession$2.L$3 = obj2;
                            defaultSessionRepository$selectSession$2.L$4 = null;
                            defaultSessionRepository$selectSession$2.label = 7;
                            if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            owner8 = owner7;
                            defaultSessionRepository8 = defaultSessionRepository7;
                            owner3 = owner8;
                            owner = sessionId5;
                            DefaultSessionRepository defaultSessionRepository114 = defaultSessionRepository8;
                            msgs = obj2;
                            defaultSessionRepository4 = defaultSessionRepository114;
                        }
                        it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                        if (it2 != null) {
                            Result.Companion companion1112 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                        }
                        SessionDao sessionDao18 = defaultSessionRepository4.sessions;
                        defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                        defaultSessionRepository$selectSession$2.L$1 = owner;
                        defaultSessionRepository$selectSession$2.L$2 = null;
                        defaultSessionRepository$selectSession$2.L$3 = null;
                        defaultSessionRepository$selectSession$2.L$4 = null;
                        defaultSessionRepository$selectSession$2.label = 8;
                        byId2 = sessionDao18.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                        if (byId2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionId6 = owner;
                        sessionEntity = (SessionEntity) byId2;
                        if (sessionEntity != null) {
                            model = defaultSessionRepository4.toModel(sessionEntity);
                        } else {
                            model = null;
                        }
                        local = model;
                        if (local != null) {
                            Result.Companion companion1113 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                        }
                        defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                        obj3 = null;
                        defaultSessionRepository$selectSession$2.L$1 = null;
                        defaultSessionRepository$selectSession$2.label = 9;
                        objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                        if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sessionId7 = sessionId6;
                        value = objMo53refreshSessionsIoAF18A;
                        if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                        }
                        Result.Companion companion1114 = Result.Companion;
                        Iterable list13 = (List) value;
                        Iterable $this$firstOrNull$iv18 = list13;
                        it3 = $this$firstOrNull$iv18.iterator();
                        while (true) {
                            if (it3.hasNext()) {
                                element$iv2 = it3.next();
                                if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                    obj4 = element$iv2;
                                }
                            } else {
                                obj4 = obj3;
                            }
                        }
                        auroraSession = (AuroraSession) obj4;
                        if (auroraSession != null) {
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                        }
                        throw new IllegalStateException("Session not found".toString());
                    } while (!StringsKt.equals(((AuroraMessage) element$iv).getRole(), "user", true));
                    auroraMessage = (AuroraMessage) element$iv;
                    if (auroraMessage != null) {
                        firstUser = auroraMessage.getContent();
                    } else {
                        firstUser = null;
                    }
                    str2 = firstUser;
                    if (str2 != null) {
                        z = false;
                    }
                    if (!z) {
                        named = existing.copy((91 & 1) != 0 ? existing.id : null, (91 & 2) != 0 ? existing.ownerKey : null, (91 & 4) != 0 ? existing.title : SessionPresentation.INSTANCE.titleFromFirstMessage(firstUser), (91 & 8) != 0 ? existing.source : null, (91 & 16) != 0 ? existing.model : null, (91 & 32) != 0 ? existing.messageCount : list2.size(), (91 & 64) != 0 ? existing.lastActiveMs : 0L, (91 & 128) != 0 ? existing.preview : StringsKt.take(firstUser, 160));
                        sessionDao = defaultSessionRepository7.sessions;
                        defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository7;
                        defaultSessionRepository$selectSession$2.L$1 = sessionId5;
                        defaultSessionRepository$selectSession$2.L$2 = owner7;
                        defaultSessionRepository$selectSession$2.L$3 = obj2;
                        defaultSessionRepository$selectSession$2.L$4 = null;
                        defaultSessionRepository$selectSession$2.label = 7;
                        if (sessionDao.insert(named, defaultSessionRepository$selectSession$2) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        owner8 = owner7;
                        defaultSessionRepository8 = defaultSessionRepository7;
                        owner3 = owner8;
                        owner = sessionId5;
                        DefaultSessionRepository defaultSessionRepository115 = defaultSessionRepository8;
                        msgs = obj2;
                        defaultSessionRepository4 = defaultSessionRepository115;
                    }
                    it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                    if (it2 != null) {
                        Result.Companion companion1115 = Result.Companion;
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                    }
                    SessionDao sessionDao19 = defaultSessionRepository4.sessions;
                    defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                    defaultSessionRepository$selectSession$2.L$1 = owner;
                    defaultSessionRepository$selectSession$2.L$2 = null;
                    defaultSessionRepository$selectSession$2.L$3 = null;
                    defaultSessionRepository$selectSession$2.L$4 = null;
                    defaultSessionRepository$selectSession$2.label = 8;
                    byId2 = sessionDao19.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                    if (byId2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionId6 = owner;
                    sessionEntity = (SessionEntity) byId2;
                    if (sessionEntity != null) {
                        model = defaultSessionRepository4.toModel(sessionEntity);
                    } else {
                        model = null;
                    }
                    local = model;
                    if (local != null) {
                        Result.Companion companion1116 = Result.Companion;
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                    }
                    defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                    obj3 = null;
                    defaultSessionRepository$selectSession$2.L$1 = null;
                    defaultSessionRepository$selectSession$2.label = 9;
                    objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                    if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sessionId7 = sessionId6;
                    value = objMo53refreshSessionsIoAF18A;
                    if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                    }
                    Result.Companion companion1117 = Result.Companion;
                    Iterable list14 = (List) value;
                    Iterable $this$firstOrNull$iv19 = list14;
                    it3 = $this$firstOrNull$iv19.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            element$iv2 = it3.next();
                            if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                                obj4 = element$iv2;
                            }
                        } else {
                            obj4 = obj3;
                        }
                    }
                    auroraSession = (AuroraSession) obj4;
                    if (auroraSession != null) {
                        return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                    }
                    throw new IllegalStateException("Session not found".toString());
                }
                owner = sessionId5;
                owner3 = owner7;
                msgs = obj2;
                defaultSessionRepository4 = defaultSessionRepository7;
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion1118 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao110 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao110.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion1119 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion11110 = Result.Companion;
                Iterable list15 = (List) value;
                Iterable $this$firstOrNull$iv110 = list15;
                it3 = $this$firstOrNull$iv110.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 7:
                obj2 = defaultSessionRepository$selectSession$2.L$3;
                owner8 = (String) defaultSessionRepository$selectSession$2.L$2;
                sessionId5 = (String) defaultSessionRepository$selectSession$2.L$1;
                defaultSessionRepository8 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                owner3 = owner8;
                owner = sessionId5;
                DefaultSessionRepository defaultSessionRepository116 = defaultSessionRepository8;
                msgs = obj2;
                defaultSessionRepository4 = defaultSessionRepository116;
                it2 = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(msgs);
                if (it2 != null) {
                    Result.Companion companion11111 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(it2));
                }
                SessionDao sessionDao111 = defaultSessionRepository4.sessions;
                defaultSessionRepository$selectSession$2.L$0 = defaultSessionRepository4;
                defaultSessionRepository$selectSession$2.L$1 = owner;
                defaultSessionRepository$selectSession$2.L$2 = null;
                defaultSessionRepository$selectSession$2.L$3 = null;
                defaultSessionRepository$selectSession$2.L$4 = null;
                defaultSessionRepository$selectSession$2.label = 8;
                byId2 = sessionDao111.getById(owner, owner3, defaultSessionRepository$selectSession$2);
                if (byId2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId6 = owner;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion11112 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion11113 = Result.Companion;
                Iterable list16 = (List) value;
                Iterable $this$firstOrNull$iv111 = list16;
                it3 = $this$firstOrNull$iv111.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 8:
                sessionId6 = (String) defaultSessionRepository$selectSession$2.L$1;
                defaultSessionRepository4 = (DefaultSessionRepository) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                byId2 = $result;
                sessionEntity = (SessionEntity) byId2;
                if (sessionEntity != null) {
                    model = defaultSessionRepository4.toModel(sessionEntity);
                } else {
                    model = null;
                }
                local = model;
                if (local != null) {
                    Result.Companion companion11114 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(local);
                }
                defaultSessionRepository$selectSession$2.L$0 = sessionId6;
                obj3 = null;
                defaultSessionRepository$selectSession$2.L$1 = null;
                defaultSessionRepository$selectSession$2.label = 9;
                objMo53refreshSessionsIoAF18A = defaultSessionRepository4.mo53refreshSessionsIoAF18A(defaultSessionRepository$selectSession$2);
                if (objMo53refreshSessionsIoAF18A == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId7 = sessionId6;
                value = objMo53refreshSessionsIoAF18A;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion11115 = Result.Companion;
                Iterable list17 = (List) value;
                Iterable $this$firstOrNull$iv112 = list17;
                it3 = $this$firstOrNull$iv112.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            case 9:
                sessionId7 = (String) defaultSessionRepository$selectSession$2.L$0;
                ResultKt.throwOnFailure($result);
                value = ai.nous.aurora.compat.Compat.resultUnbox($result);
                obj3 = null;
                if (ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value)) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(value);
                }
                Result.Companion companion11116 = Result.Companion;
                Iterable list18 = (List) value;
                Iterable $this$firstOrNull$iv113 = list18;
                it3 = $this$firstOrNull$iv113.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        element$iv2 = it3.next();
                        if (Intrinsics.areEqual(((AuroraSession) element$iv2).getId(), sessionId7)) {
                            obj4 = element$iv2;
                        }
                    } else {
                        obj4 = obj3;
                    }
                }
                auroraSession = (AuroraSession) obj4;
                if (auroraSession != null) {
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(auroraSession);
                }
                throw new IllegalStateException("Session not found".toString());
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x008f  */
    /* JADX WARN: Code duplicated, block: B:22:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:27:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:29:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:30:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:40:0x00fa A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x0111 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:0x00bb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:45:0x00bb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x00a6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object reconcileAmbiguousOutbox(String sessionId, String str, List<AuroraMessage> list, Continuation continuation) {
        C02191 c02191;
        DefaultSessionRepository defaultSessionRepository;
        String owner;
        List<AuroraMessage> list2;
        Object objOutboxForSession;
        Collection destination$iv$iv;
        String owner2;
        DefaultSessionRepository defaultSessionRepository2;
        Iterator it;
        MessageEntity it2;
        MessageEntity row;
        AuroraMessage match;
        boolean z;
        String next;
        MessageDao messageDao;
        String id;
        if (continuation instanceof C02191) {
            c02191 = (C02191) continuation;
            if ((c02191.label & Integer.MIN_VALUE) != 0) {
                c02191.label -= Integer.MIN_VALUE;
            } else {
                c02191 = new C02191(continuation);
            }
        } else {
            c02191 = new C02191(continuation);
        }
        Object $result = c02191.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02191.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                owner = str;
                list2 = list;
                MessageDao messageDao2 = defaultSessionRepository.messages;
                c02191.L$0 = defaultSessionRepository;
                c02191.L$1 = owner;
                c02191.L$2 = list2;
                c02191.label = 1;
                objOutboxForSession = messageDao2.outboxForSession(sessionId, owner, c02191);
                if (objOutboxForSession == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Iterable outbox = (List) objOutboxForSession;
                Iterable $this$filterTo$iv$iv = outbox;
                destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filterTo$iv$iv) {
                    it2 = (MessageEntity) element$iv$iv;
                    if (Intrinsics.areEqual(it2.getDeliveryStatus(), OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$forEach$iv = (List) destination$iv$iv;
                owner2 = owner;
                defaultSessionRepository2 = defaultSessionRepository;
                it = $this$forEach$iv.iterator();
                while (it.hasNext()) {
                    Object element$iv = it.next();
                    row = (MessageEntity) element$iv;
                    OutboxExactlyOnce outboxExactlyOnce = OutboxExactlyOnce.INSTANCE;
                    String content = row.getContent();
                    Function1 function1 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSessionRepository.reconcileAmbiguousOutbox$lambda$26$lambda$24((AuroraMessage) obj);
                        }
                    };
                    match = (AuroraMessage) outboxExactlyOnce.findRemoteContentMatch(list2, content, function1, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSessionRepository.reconcileAmbiguousOutbox$lambda$26$lambda$25((AuroraMessage) obj);
                        }
                    }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function1, obj));
                        }
                    } : null, (16 & 32) != 0 ? null : null, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : null);
                    OutboxExactlyOnce outboxExactlyOnce2 = OutboxExactlyOnce.INSTANCE;
                    if (match != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    next = outboxExactlyOnce2.reconcileAmbiguousAgainstRemote(true, z);
                    if (next != null) {
                        messageDao = defaultSessionRepository2.messages;
                        id = row.getId();
                        c02191.L$0 = defaultSessionRepository2;
                        c02191.L$1 = owner2;
                        c02191.L$2 = list2;
                        c02191.L$3 = it;
                        c02191.label = 2;
                        if (messageDao.setDeliveryStatus(id, owner2, next, c02191) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                List<AuroraMessage> list3 = (List) c02191.L$2;
                owner = (String) c02191.L$1;
                defaultSessionRepository = (DefaultSessionRepository) c02191.L$0;
                ResultKt.throwOnFailure($result);
                list2 = list3;
                objOutboxForSession = $result;
                Iterable outbox2 = (List) objOutboxForSession;
                Iterable $this$filterTo$iv$iv2 = outbox2;
                destination$iv$iv = new ArrayList();
                java.util.Iterator<Object> r13U15 = $this$filterTo$iv$iv2.iterator();
                while (r13U15.hasNext()) {
    element$iv$iv = r13U15.next();

                    it2 = (MessageEntity) element$iv$iv;
                    if (Intrinsics.areEqual(it2.getDeliveryStatus(), OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$forEach$iv2 = (List) destination$iv$iv;
                owner2 = owner;
                defaultSessionRepository2 = defaultSessionRepository;
                it = $this$forEach$iv2.iterator();
                while (it.hasNext()) {
                    Object element$iv2 = it.next();
                    row = (MessageEntity) element$iv2;
                    OutboxExactlyOnce outboxExactlyOnce3 = OutboxExactlyOnce.INSTANCE;
                    String content2 = row.getContent();
                    Function1 function2 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSessionRepository.reconcileAmbiguousOutbox$lambda$26$lambda$24((AuroraMessage) obj);
                        }
                    };
                    match = (AuroraMessage) outboxExactlyOnce3.findRemoteContentMatch(list2, content2, function2, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSessionRepository.reconcileAmbiguousOutbox$lambda$26$lambda$25((AuroraMessage) obj);
                        }
                    }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function2, obj));
                        }
                    } : null, (16 & 32) != 0 ? null : null, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : null);
                    OutboxExactlyOnce outboxExactlyOnce4 = OutboxExactlyOnce.INSTANCE;
                    if (match != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    next = outboxExactlyOnce4.reconcileAmbiguousAgainstRemote(true, z);
                    if (next != null) {
                        messageDao = defaultSessionRepository2.messages;
                        id = row.getId();
                        c02191.L$0 = defaultSessionRepository2;
                        c02191.L$1 = owner2;
                        c02191.L$2 = list2;
                        c02191.L$3 = it;
                        c02191.label = 2;
                        if (messageDao.setDeliveryStatus(id, owner2, next, c02191) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            case 2:
                it = (Iterator) c02191.L$3;
                list2 = (List) c02191.L$2;
                owner2 = (String) c02191.L$1;
                defaultSessionRepository2 = (DefaultSessionRepository) c02191.L$0;
                ResultKt.throwOnFailure($result);
                while (it.hasNext()) {
                    Object element$iv3 = it.next();
                    row = (MessageEntity) element$iv3;
                    OutboxExactlyOnce outboxExactlyOnce5 = OutboxExactlyOnce.INSTANCE;
                    String content3 = row.getContent();
                    Function1 function3 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSessionRepository.reconcileAmbiguousOutbox$lambda$26$lambda$24((AuroraMessage) obj);
                        }
                    };
                    match = (AuroraMessage) outboxExactlyOnce5.findRemoteContentMatch(list2, content3, function3, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultSessionRepository.reconcileAmbiguousOutbox$lambda$26$lambda$25((AuroraMessage) obj);
                        }
                    }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function3, obj));
                        }
                    } : null, (16 & 32) != 0 ? null : null, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : null);
                    OutboxExactlyOnce outboxExactlyOnce6 = OutboxExactlyOnce.INSTANCE;
                    if (match != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    next = outboxExactlyOnce6.reconcileAmbiguousAgainstRemote(true, z);
                    if (next != null) {
                        messageDao = defaultSessionRepository2.messages;
                        id = row.getId();
                        c02191.L$0 = defaultSessionRepository2;
                        c02191.L$1 = owner2;
                        c02191.L$2 = list2;
                        c02191.L$3 = it;
                        c02191.label = 2;
                        if (messageDao.setDeliveryStatus(id, owner2, next, c02191) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String reconcileAmbiguousOutbox$lambda$26$lambda$24(AuroraMessage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getRole();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String reconcileAmbiguousOutbox$lambda$26$lambda$25(AuroraMessage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getContent();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0126 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x0127  */
    /* JADX WARN: Code duplicated, block: B:28:0x0177 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x0178  */
    /* JADX WARN: Code duplicated, block: B:32:0x0197 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:33:0x0198  */
    /* JADX WARN: Code duplicated, block: B:36:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:38:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:40:0x01ef A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:42:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:44:0x022a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:48:0x0259 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:50:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    /* JADX INFO: renamed from: sendMessage-BWLJW6A, reason: not valid java name */
    public Object mo56sendMessageBWLJW6A(String str, String str2, String str3, Continuation continuation) throws Throwable {
        DefaultSessionRepository$sendMessage$1 defaultSessionRepository$sendMessage$1;
        DefaultSessionRepository defaultSessionRepository;
        String content;
        String sessionId;
        String model;
        Object objOwnerKey;
        String sessionId2;
        DefaultSessionRepository defaultSessionRepository2;
        String model2;
        String owner;
        String clientId;
        String pendingId;
        MessageDao messageDao;
        MessageEntity messageEntity;
        String content2;
        String clientId2;
        String pendingId2;
        Object byId;
        DefaultSessionRepository defaultSessionRepository3;
        String sessionId3;
        String content3;
        String model3;
        String owner2;
        String clientId3;
        String pendingId3;
        SessionEntity row;
        SessionDao sessionDao;
        SessionEntity sessionEntityCopy;
        SessionDao sessionDao2;
        SessionEntity sessionEntityCopy2;
        Object objM51deliverPendingbMdYcbs;
        if (continuation instanceof DefaultSessionRepository$sendMessage$1) {
            defaultSessionRepository$sendMessage$1 = (DefaultSessionRepository$sendMessage$1) continuation;
            if ((defaultSessionRepository$sendMessage$1.label & Integer.MIN_VALUE) != 0) {
                defaultSessionRepository$sendMessage$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSessionRepository$sendMessage$1 = new DefaultSessionRepository$sendMessage$1(this, continuation);
            }
        } else {
            defaultSessionRepository$sendMessage$1 = new DefaultSessionRepository$sendMessage$1(this, continuation);
        }
        Object $result = defaultSessionRepository$sendMessage$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSessionRepository$sendMessage$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                content = str2;
                sessionId = str;
                model = str3;
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId;
                defaultSessionRepository$sendMessage$1.L$2 = content;
                defaultSessionRepository$sendMessage$1.L$3 = model;
                defaultSessionRepository$sendMessage$1.label = 1;
                if (defaultSessionRepository.ensureLegacyAdopted(defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId;
                defaultSessionRepository$sendMessage$1.L$2 = content;
                defaultSessionRepository$sendMessage$1.L$3 = model;
                defaultSessionRepository$sendMessage$1.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$sendMessage$1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId2 = sessionId;
                defaultSessionRepository2 = defaultSessionRepository;
                model2 = model;
                owner = (String) objOwnerKey;
                clientId = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(clientId, "toString(...)");
                pendingId = OutboxExactlyOnce.INSTANCE.localIdForClient(clientId);
                messageDao = defaultSessionRepository2.messages;
                messageEntity = new MessageEntity(pendingId, sessionId2, owner, "user", content, System.currentTimeMillis(), OutboxExactlyOnce.STATUS_PENDING, clientId);
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = content;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = clientId;
                defaultSessionRepository$sendMessage$1.L$6 = pendingId;
                defaultSessionRepository$sendMessage$1.label = 3;
                if (messageDao.insert(messageEntity, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                String str4 = content;
                content2 = clientId;
                clientId2 = pendingId;
                pendingId2 = str4;
                SessionDao sessionDao3 = defaultSessionRepository2.sessions;
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = pendingId2;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = content2;
                defaultSessionRepository$sendMessage$1.L$6 = clientId2;
                defaultSessionRepository$sendMessage$1.label = 4;
                byId = sessionDao3.getById(sessionId2, owner, defaultSessionRepository$sendMessage$1);
                if (byId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                sessionId3 = sessionId2;
                content3 = pendingId2;
                model3 = model2;
                owner2 = owner;
                clientId3 = content2;
                pendingId3 = clientId2;
                row = (SessionEntity) byId;
                if (row != null) {
                    if (SessionPresentation.INSTANCE.isGenericTitle(row.getTitle())) {
                        sessionDao2 = defaultSessionRepository3.sessions;
                        sessionEntityCopy2 = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : SessionPresentation.INSTANCE.titleFromFirstMessage(content3), (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 5;
                        if (sessionDao2.insert(sessionEntityCopy2, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        sessionDao = defaultSessionRepository3.sessions;
                        sessionEntityCopy = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : null, (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 6;
                        if (sessionDao.insert(sessionEntityCopy, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                String content4 = content3;
                String sessionId4 = sessionId3;
                DefaultSessionRepository defaultSessionRepository4 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository4.m51deliverPendingbMdYcbs(sessionId4, pendingId3, content4, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 1:
                model = (String) defaultSessionRepository$sendMessage$1.L$3;
                content = (String) defaultSessionRepository$sendMessage$1.L$2;
                sessionId = (String) defaultSessionRepository$sendMessage$1.L$1;
                defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$sendMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId;
                defaultSessionRepository$sendMessage$1.L$2 = content;
                defaultSessionRepository$sendMessage$1.L$3 = model;
                defaultSessionRepository$sendMessage$1.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$sendMessage$1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sessionId2 = sessionId;
                defaultSessionRepository2 = defaultSessionRepository;
                model2 = model;
                owner = (String) objOwnerKey;
                clientId = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(clientId, "toString(...)");
                pendingId = OutboxExactlyOnce.INSTANCE.localIdForClient(clientId);
                messageDao = defaultSessionRepository2.messages;
                messageEntity = new MessageEntity(pendingId, sessionId2, owner, "user", content, System.currentTimeMillis(), OutboxExactlyOnce.STATUS_PENDING, clientId);
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = content;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = clientId;
                defaultSessionRepository$sendMessage$1.L$6 = pendingId;
                defaultSessionRepository$sendMessage$1.label = 3;
                if (messageDao.insert(messageEntity, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                String str5 = content;
                content2 = clientId;
                clientId2 = pendingId;
                pendingId2 = str5;
                SessionDao sessionDao4 = defaultSessionRepository2.sessions;
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = pendingId2;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = content2;
                defaultSessionRepository$sendMessage$1.L$6 = clientId2;
                defaultSessionRepository$sendMessage$1.label = 4;
                byId = sessionDao4.getById(sessionId2, owner, defaultSessionRepository$sendMessage$1);
                if (byId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                sessionId3 = sessionId2;
                content3 = pendingId2;
                model3 = model2;
                owner2 = owner;
                clientId3 = content2;
                pendingId3 = clientId2;
                row = (SessionEntity) byId;
                if (row != null) {
                    if (SessionPresentation.INSTANCE.isGenericTitle(row.getTitle())) {
                        sessionDao2 = defaultSessionRepository3.sessions;
                        sessionEntityCopy2 = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : SessionPresentation.INSTANCE.titleFromFirstMessage(content3), (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 5;
                        if (sessionDao2.insert(sessionEntityCopy2, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        sessionDao = defaultSessionRepository3.sessions;
                        sessionEntityCopy = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : null, (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 6;
                        if (sessionDao.insert(sessionEntityCopy, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                String content5 = content3;
                String sessionId5 = sessionId3;
                DefaultSessionRepository defaultSessionRepository5 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository5.m51deliverPendingbMdYcbs(sessionId5, pendingId3, content5, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 2:
                String model4 = (String) defaultSessionRepository$sendMessage$1.L$3;
                content = (String) defaultSessionRepository$sendMessage$1.L$2;
                String sessionId6 = (String) defaultSessionRepository$sendMessage$1.L$1;
                DefaultSessionRepository defaultSessionRepository6 = (DefaultSessionRepository) defaultSessionRepository$sendMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                sessionId2 = sessionId6;
                defaultSessionRepository2 = defaultSessionRepository6;
                objOwnerKey = $result;
                model2 = model4;
                owner = (String) objOwnerKey;
                clientId = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(clientId, "toString(...)");
                pendingId = OutboxExactlyOnce.INSTANCE.localIdForClient(clientId);
                messageDao = defaultSessionRepository2.messages;
                messageEntity = new MessageEntity(pendingId, sessionId2, owner, "user", content, System.currentTimeMillis(), OutboxExactlyOnce.STATUS_PENDING, clientId);
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = content;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = clientId;
                defaultSessionRepository$sendMessage$1.L$6 = pendingId;
                defaultSessionRepository$sendMessage$1.label = 3;
                if (messageDao.insert(messageEntity, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                String str6 = content;
                content2 = clientId;
                clientId2 = pendingId;
                pendingId2 = str6;
                SessionDao sessionDao5 = defaultSessionRepository2.sessions;
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = pendingId2;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = content2;
                defaultSessionRepository$sendMessage$1.L$6 = clientId2;
                defaultSessionRepository$sendMessage$1.label = 4;
                byId = sessionDao5.getById(sessionId2, owner, defaultSessionRepository$sendMessage$1);
                if (byId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                sessionId3 = sessionId2;
                content3 = pendingId2;
                model3 = model2;
                owner2 = owner;
                clientId3 = content2;
                pendingId3 = clientId2;
                row = (SessionEntity) byId;
                if (row != null) {
                    if (SessionPresentation.INSTANCE.isGenericTitle(row.getTitle())) {
                        sessionDao2 = defaultSessionRepository3.sessions;
                        sessionEntityCopy2 = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : SessionPresentation.INSTANCE.titleFromFirstMessage(content3), (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 5;
                        if (sessionDao2.insert(sessionEntityCopy2, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        sessionDao = defaultSessionRepository3.sessions;
                        sessionEntityCopy = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : null, (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 6;
                        if (sessionDao.insert(sessionEntityCopy, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                String content6 = content3;
                String sessionId7 = sessionId3;
                DefaultSessionRepository defaultSessionRepository7 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository7.m51deliverPendingbMdYcbs(sessionId7, pendingId3, content6, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 3:
                clientId2 = (String) defaultSessionRepository$sendMessage$1.L$6;
                content2 = (String) defaultSessionRepository$sendMessage$1.L$5;
                owner = (String) defaultSessionRepository$sendMessage$1.L$4;
                model2 = (String) defaultSessionRepository$sendMessage$1.L$3;
                pendingId2 = (String) defaultSessionRepository$sendMessage$1.L$2;
                sessionId2 = (String) defaultSessionRepository$sendMessage$1.L$1;
                defaultSessionRepository2 = (DefaultSessionRepository) defaultSessionRepository$sendMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                SessionDao sessionDao6 = defaultSessionRepository2.sessions;
                defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$sendMessage$1.L$1 = sessionId2;
                defaultSessionRepository$sendMessage$1.L$2 = pendingId2;
                defaultSessionRepository$sendMessage$1.L$3 = model2;
                defaultSessionRepository$sendMessage$1.L$4 = owner;
                defaultSessionRepository$sendMessage$1.L$5 = content2;
                defaultSessionRepository$sendMessage$1.L$6 = clientId2;
                defaultSessionRepository$sendMessage$1.label = 4;
                byId = sessionDao6.getById(sessionId2, owner, defaultSessionRepository$sendMessage$1);
                if (byId == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                sessionId3 = sessionId2;
                content3 = pendingId2;
                model3 = model2;
                owner2 = owner;
                clientId3 = content2;
                pendingId3 = clientId2;
                row = (SessionEntity) byId;
                if (row != null) {
                    if (SessionPresentation.INSTANCE.isGenericTitle(row.getTitle())) {
                        sessionDao2 = defaultSessionRepository3.sessions;
                        sessionEntityCopy2 = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : SessionPresentation.INSTANCE.titleFromFirstMessage(content3), (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 5;
                        if (sessionDao2.insert(sessionEntityCopy2, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        sessionDao = defaultSessionRepository3.sessions;
                        sessionEntityCopy = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : null, (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 6;
                        if (sessionDao.insert(sessionEntityCopy, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                String content7 = content3;
                String sessionId8 = sessionId3;
                DefaultSessionRepository defaultSessionRepository8 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository8.m51deliverPendingbMdYcbs(sessionId8, pendingId3, content7, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 4:
                String pendingId4 = (String) defaultSessionRepository$sendMessage$1.L$6;
                String clientId4 = (String) defaultSessionRepository$sendMessage$1.L$5;
                String owner3 = (String) defaultSessionRepository$sendMessage$1.L$4;
                String model5 = (String) defaultSessionRepository$sendMessage$1.L$3;
                String content8 = (String) defaultSessionRepository$sendMessage$1.L$2;
                String sessionId9 = (String) defaultSessionRepository$sendMessage$1.L$1;
                DefaultSessionRepository defaultSessionRepository9 = (DefaultSessionRepository) defaultSessionRepository$sendMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                defaultSessionRepository3 = defaultSessionRepository9;
                byId = $result;
                sessionId3 = sessionId9;
                content3 = content8;
                model3 = model5;
                owner2 = owner3;
                clientId3 = clientId4;
                pendingId3 = pendingId4;
                row = (SessionEntity) byId;
                if (row != null) {
                    if (SessionPresentation.INSTANCE.isGenericTitle(row.getTitle())) {
                        sessionDao2 = defaultSessionRepository3.sessions;
                        sessionEntityCopy2 = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : SessionPresentation.INSTANCE.titleFromFirstMessage(content3), (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 5;
                        if (sessionDao2.insert(sessionEntityCopy2, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        sessionDao = defaultSessionRepository3.sessions;
                        sessionEntityCopy = row.copy((91 & 1) != 0 ? row.id : null, (91 & 2) != 0 ? row.ownerKey : null, (91 & 4) != 0 ? row.title : null, (91 & 8) != 0 ? row.source : null, (91 & 16) != 0 ? row.model : null, (91 & 32) != 0 ? row.messageCount : 0, (91 & 64) != 0 ? row.lastActiveMs : System.currentTimeMillis(), (91 & 128) != 0 ? row.preview : StringsKt.take(content3, 160));
                        defaultSessionRepository$sendMessage$1.L$0 = defaultSessionRepository3;
                        defaultSessionRepository$sendMessage$1.L$1 = sessionId3;
                        defaultSessionRepository$sendMessage$1.L$2 = content3;
                        defaultSessionRepository$sendMessage$1.L$3 = model3;
                        defaultSessionRepository$sendMessage$1.L$4 = owner2;
                        defaultSessionRepository$sendMessage$1.L$5 = clientId3;
                        defaultSessionRepository$sendMessage$1.L$6 = pendingId3;
                        defaultSessionRepository$sendMessage$1.label = 6;
                        if (sessionDao.insert(sessionEntityCopy, defaultSessionRepository$sendMessage$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                String content9 = content3;
                String sessionId10 = sessionId3;
                DefaultSessionRepository defaultSessionRepository10 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository10.m51deliverPendingbMdYcbs(sessionId10, pendingId3, content9, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 5:
                pendingId3 = (String) defaultSessionRepository$sendMessage$1.L$6;
                clientId3 = (String) defaultSessionRepository$sendMessage$1.L$5;
                owner2 = (String) defaultSessionRepository$sendMessage$1.L$4;
                model3 = (String) defaultSessionRepository$sendMessage$1.L$3;
                content3 = (String) defaultSessionRepository$sendMessage$1.L$2;
                sessionId3 = (String) defaultSessionRepository$sendMessage$1.L$1;
                defaultSessionRepository3 = (DefaultSessionRepository) defaultSessionRepository$sendMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                String content10 = content3;
                String sessionId11 = sessionId3;
                DefaultSessionRepository defaultSessionRepository11 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository11.m51deliverPendingbMdYcbs(sessionId11, pendingId3, content10, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 6:
                pendingId3 = (String) defaultSessionRepository$sendMessage$1.L$6;
                clientId3 = (String) defaultSessionRepository$sendMessage$1.L$5;
                owner2 = (String) defaultSessionRepository$sendMessage$1.L$4;
                model3 = (String) defaultSessionRepository$sendMessage$1.L$3;
                content3 = (String) defaultSessionRepository$sendMessage$1.L$2;
                sessionId3 = (String) defaultSessionRepository$sendMessage$1.L$1;
                defaultSessionRepository3 = (DefaultSessionRepository) defaultSessionRepository$sendMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                String content11 = content3;
                String sessionId12 = sessionId3;
                DefaultSessionRepository defaultSessionRepository12 = defaultSessionRepository3;
                defaultSessionRepository$sendMessage$1.L$0 = null;
                defaultSessionRepository$sendMessage$1.L$1 = null;
                defaultSessionRepository$sendMessage$1.L$2 = null;
                defaultSessionRepository$sendMessage$1.L$3 = null;
                defaultSessionRepository$sendMessage$1.L$4 = null;
                defaultSessionRepository$sendMessage$1.L$5 = null;
                defaultSessionRepository$sendMessage$1.L$6 = null;
                defaultSessionRepository$sendMessage$1.label = 7;
                objM51deliverPendingbMdYcbs = defaultSessionRepository12.m51deliverPendingbMdYcbs(sessionId12, pendingId3, content11, model3, owner2, clientId3, defaultSessionRepository$sendMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 7:
                ResultKt.throwOnFailure($result);
                return ai.nous.aurora.compat.Compat.resultUnbox($result);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00bf A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:26:0x00de A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x00df  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:34:0x0109  */
    /* JADX WARN: Code duplicated, block: B:36:0x0114  */
    /* JADX WARN: Code duplicated, block: B:38:0x0133 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x0148  */
    /* JADX WARN: Code duplicated, block: B:43:0x014e  */
    /* JADX WARN: Code duplicated, block: B:46:0x015a  */
    /* JADX WARN: Code duplicated, block: B:49:0x015e  */
    /* JADX WARN: Code duplicated, block: B:52:0x0162  */
    /* JADX WARN: Code duplicated, block: B:53:0x0164  */
    /* JADX WARN: Code duplicated, block: B:56:0x0192 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:58:0x0194  */
    /* JADX WARN: Code duplicated, block: B:60:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    /* JADX INFO: renamed from: retryMessage-BWLJW6A, reason: not valid java name */
    public Object mo54retryMessageBWLJW6A(String str, String str2, String str3, Continuation continuation) throws Throwable {
        DefaultSessionRepository$retryMessage$1 defaultSessionRepository$retryMessage$1;
        DefaultSessionRepository defaultSessionRepository;
        String localMessageId;
        String sessionId;
        String model;
        Object objOwnerKey;
        String localMessageId2;
        DefaultSessionRepository defaultSessionRepository2;
        String sessionId2;
        String localMessageId3;
        String owner;
        Object objClaimForRetry;
        DefaultSessionRepository defaultSessionRepository3;
        String model2;
        String sessionId3;
        String owner2;
        MessageEntity claimed;
        String it;
        String clientId;
        Object objM51deliverPendingbMdYcbs;
        MessageDao messageDao;
        String id;
        String strStatusAfterPreNetworkRejection;
        if (continuation instanceof DefaultSessionRepository$retryMessage$1) {
            defaultSessionRepository$retryMessage$1 = (DefaultSessionRepository$retryMessage$1) continuation;
            if ((defaultSessionRepository$retryMessage$1.label & Integer.MIN_VALUE) != 0) {
                defaultSessionRepository$retryMessage$1.label -= Integer.MIN_VALUE;
            } else {
                defaultSessionRepository$retryMessage$1 = new DefaultSessionRepository$retryMessage$1(this, continuation);
            }
        } else {
            defaultSessionRepository$retryMessage$1 = new DefaultSessionRepository$retryMessage$1(this, continuation);
        }
        Object $result = defaultSessionRepository$retryMessage$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (defaultSessionRepository$retryMessage$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                localMessageId = str2;
                sessionId = str;
                model = str3;
                defaultSessionRepository$retryMessage$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$retryMessage$1.L$1 = sessionId;
                defaultSessionRepository$retryMessage$1.L$2 = localMessageId;
                defaultSessionRepository$retryMessage$1.L$3 = model;
                defaultSessionRepository$retryMessage$1.label = 1;
                if (defaultSessionRepository.ensureLegacyAdopted(defaultSessionRepository$retryMessage$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository$retryMessage$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$retryMessage$1.L$1 = sessionId;
                defaultSessionRepository$retryMessage$1.L$2 = localMessageId;
                defaultSessionRepository$retryMessage$1.L$3 = model;
                defaultSessionRepository$retryMessage$1.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$retryMessage$1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                String str4 = localMessageId;
                localMessageId2 = model;
                defaultSessionRepository2 = defaultSessionRepository;
                sessionId2 = sessionId;
                localMessageId3 = str4;
                owner = (String) objOwnerKey;
                MessageDao messageDao2 = defaultSessionRepository2.messages;
                defaultSessionRepository$retryMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$retryMessage$1.L$1 = sessionId2;
                defaultSessionRepository$retryMessage$1.L$2 = localMessageId3;
                defaultSessionRepository$retryMessage$1.L$3 = localMessageId2;
                defaultSessionRepository$retryMessage$1.L$4 = owner;
                defaultSessionRepository$retryMessage$1.label = 3;
                objClaimForRetry = messageDao2.claimForRetry(localMessageId3, sessionId2, owner, defaultSessionRepository$retryMessage$1);
                if (objClaimForRetry == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                model2 = localMessageId2;
                sessionId3 = sessionId2;
                owner2 = owner;
                claimed = (MessageEntity) objClaimForRetry;
                if (claimed == null) {
                    Result.Companion companion = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("No failed message to retry")));
                }
                if (OutboxExactlyOnce.INSTANCE.retryKeepsSameBubble(localMessageId3, claimed.getId())) {
                    throw new IllegalStateException("Retry must reuse local bubble id".toString());
                }
                if (!Intrinsics.areEqual(claimed.getSessionId(), sessionId3)) {
                    messageDao = defaultSessionRepository3.messages;
                    id = claimed.getId();
                    strStatusAfterPreNetworkRejection = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                    defaultSessionRepository$retryMessage$1.L$0 = null;
                    defaultSessionRepository$retryMessage$1.L$1 = null;
                    defaultSessionRepository$retryMessage$1.L$2 = null;
                    defaultSessionRepository$retryMessage$1.L$3 = null;
                    defaultSessionRepository$retryMessage$1.L$4 = null;
                    defaultSessionRepository$retryMessage$1.label = 4;
                    if (messageDao.setDeliveryStatus(id, owner2, strStatusAfterPreNetworkRejection, defaultSessionRepository$retryMessage$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Result.Companion companion2 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message belongs to a different session")));
                }
                it = claimed.getClientMsgId();
                if (it == null) {
                    clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                } else {
                    if (!(StringsKt.isBlank(it) ? false : true)) {
                        it = null;
                    }
                    if (it == null) {
                        clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                    } else {
                        clientId = it;
                    }
                }
                String id2 = claimed.getId();
                String content = claimed.getContent();
                defaultSessionRepository$retryMessage$1.L$0 = null;
                defaultSessionRepository$retryMessage$1.L$1 = null;
                defaultSessionRepository$retryMessage$1.L$2 = null;
                defaultSessionRepository$retryMessage$1.L$3 = null;
                defaultSessionRepository$retryMessage$1.L$4 = null;
                defaultSessionRepository$retryMessage$1.label = 5;
                objM51deliverPendingbMdYcbs = defaultSessionRepository3.m51deliverPendingbMdYcbs(sessionId3, id2, content, model2, owner2, clientId, defaultSessionRepository$retryMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 1:
                model = (String) defaultSessionRepository$retryMessage$1.L$3;
                localMessageId = (String) defaultSessionRepository$retryMessage$1.L$2;
                sessionId = (String) defaultSessionRepository$retryMessage$1.L$1;
                defaultSessionRepository = (DefaultSessionRepository) defaultSessionRepository$retryMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                defaultSessionRepository$retryMessage$1.L$0 = defaultSessionRepository;
                defaultSessionRepository$retryMessage$1.L$1 = sessionId;
                defaultSessionRepository$retryMessage$1.L$2 = localMessageId;
                defaultSessionRepository$retryMessage$1.L$3 = model;
                defaultSessionRepository$retryMessage$1.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(defaultSessionRepository$retryMessage$1);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                String str5 = localMessageId;
                localMessageId2 = model;
                defaultSessionRepository2 = defaultSessionRepository;
                sessionId2 = sessionId;
                localMessageId3 = str5;
                owner = (String) objOwnerKey;
                MessageDao messageDao3 = defaultSessionRepository2.messages;
                defaultSessionRepository$retryMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$retryMessage$1.L$1 = sessionId2;
                defaultSessionRepository$retryMessage$1.L$2 = localMessageId3;
                defaultSessionRepository$retryMessage$1.L$3 = localMessageId2;
                defaultSessionRepository$retryMessage$1.L$4 = owner;
                defaultSessionRepository$retryMessage$1.label = 3;
                objClaimForRetry = messageDao3.claimForRetry(localMessageId3, sessionId2, owner, defaultSessionRepository$retryMessage$1);
                if (objClaimForRetry == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                model2 = localMessageId2;
                sessionId3 = sessionId2;
                owner2 = owner;
                claimed = (MessageEntity) objClaimForRetry;
                if (claimed == null) {
                    Result.Companion companion3 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("No failed message to retry")));
                }
                if (OutboxExactlyOnce.INSTANCE.retryKeepsSameBubble(localMessageId3, claimed.getId())) {
                    throw new IllegalStateException("Retry must reuse local bubble id".toString());
                }
                if (!Intrinsics.areEqual(claimed.getSessionId(), sessionId3)) {
                    messageDao = defaultSessionRepository3.messages;
                    id = claimed.getId();
                    strStatusAfterPreNetworkRejection = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                    defaultSessionRepository$retryMessage$1.L$0 = null;
                    defaultSessionRepository$retryMessage$1.L$1 = null;
                    defaultSessionRepository$retryMessage$1.L$2 = null;
                    defaultSessionRepository$retryMessage$1.L$3 = null;
                    defaultSessionRepository$retryMessage$1.L$4 = null;
                    defaultSessionRepository$retryMessage$1.label = 4;
                    if (messageDao.setDeliveryStatus(id, owner2, strStatusAfterPreNetworkRejection, defaultSessionRepository$retryMessage$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Result.Companion companion4 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message belongs to a different session")));
                }
                it = claimed.getClientMsgId();
                if (it == null) {
                    clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                } else {
                    if (!(StringsKt.isBlank(it) ? false : true)) {
                        it = null;
                    }
                    if (it == null) {
                        clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                    } else {
                        clientId = it;
                    }
                }
                String id3 = claimed.getId();
                String content2 = claimed.getContent();
                defaultSessionRepository$retryMessage$1.L$0 = null;
                defaultSessionRepository$retryMessage$1.L$1 = null;
                defaultSessionRepository$retryMessage$1.L$2 = null;
                defaultSessionRepository$retryMessage$1.L$3 = null;
                defaultSessionRepository$retryMessage$1.L$4 = null;
                defaultSessionRepository$retryMessage$1.label = 5;
                objM51deliverPendingbMdYcbs = defaultSessionRepository3.m51deliverPendingbMdYcbs(sessionId3, id3, content2, model2, owner2, clientId, defaultSessionRepository$retryMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 2:
                String model3 = (String) defaultSessionRepository$retryMessage$1.L$3;
                String localMessageId4 = (String) defaultSessionRepository$retryMessage$1.L$2;
                String sessionId4 = (String) defaultSessionRepository$retryMessage$1.L$1;
                DefaultSessionRepository defaultSessionRepository4 = (DefaultSessionRepository) defaultSessionRepository$retryMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                objOwnerKey = $result;
                localMessageId2 = model3;
                defaultSessionRepository2 = defaultSessionRepository4;
                sessionId2 = sessionId4;
                localMessageId3 = localMessageId4;
                owner = (String) objOwnerKey;
                MessageDao messageDao4 = defaultSessionRepository2.messages;
                defaultSessionRepository$retryMessage$1.L$0 = defaultSessionRepository2;
                defaultSessionRepository$retryMessage$1.L$1 = sessionId2;
                defaultSessionRepository$retryMessage$1.L$2 = localMessageId3;
                defaultSessionRepository$retryMessage$1.L$3 = localMessageId2;
                defaultSessionRepository$retryMessage$1.L$4 = owner;
                defaultSessionRepository$retryMessage$1.label = 3;
                objClaimForRetry = messageDao4.claimForRetry(localMessageId3, sessionId2, owner, defaultSessionRepository$retryMessage$1);
                if (objClaimForRetry == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository3 = defaultSessionRepository2;
                model2 = localMessageId2;
                sessionId3 = sessionId2;
                owner2 = owner;
                claimed = (MessageEntity) objClaimForRetry;
                if (claimed == null) {
                    Result.Companion companion5 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("No failed message to retry")));
                }
                if (OutboxExactlyOnce.INSTANCE.retryKeepsSameBubble(localMessageId3, claimed.getId())) {
                    throw new IllegalStateException("Retry must reuse local bubble id".toString());
                }
                if (!Intrinsics.areEqual(claimed.getSessionId(), sessionId3)) {
                    messageDao = defaultSessionRepository3.messages;
                    id = claimed.getId();
                    strStatusAfterPreNetworkRejection = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                    defaultSessionRepository$retryMessage$1.L$0 = null;
                    defaultSessionRepository$retryMessage$1.L$1 = null;
                    defaultSessionRepository$retryMessage$1.L$2 = null;
                    defaultSessionRepository$retryMessage$1.L$3 = null;
                    defaultSessionRepository$retryMessage$1.L$4 = null;
                    defaultSessionRepository$retryMessage$1.label = 4;
                    if (messageDao.setDeliveryStatus(id, owner2, strStatusAfterPreNetworkRejection, defaultSessionRepository$retryMessage$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Result.Companion companion6 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message belongs to a different session")));
                }
                it = claimed.getClientMsgId();
                if (it == null) {
                    clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                } else {
                    if (!(StringsKt.isBlank(it) ? false : true)) {
                        it = null;
                    }
                    if (it == null) {
                        clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                    } else {
                        clientId = it;
                    }
                }
                String id4 = claimed.getId();
                String content3 = claimed.getContent();
                defaultSessionRepository$retryMessage$1.L$0 = null;
                defaultSessionRepository$retryMessage$1.L$1 = null;
                defaultSessionRepository$retryMessage$1.L$2 = null;
                defaultSessionRepository$retryMessage$1.L$3 = null;
                defaultSessionRepository$retryMessage$1.L$4 = null;
                defaultSessionRepository$retryMessage$1.label = 5;
                objM51deliverPendingbMdYcbs = defaultSessionRepository3.m51deliverPendingbMdYcbs(sessionId3, id4, content3, model2, owner2, clientId, defaultSessionRepository$retryMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 3:
                String owner3 = (String) defaultSessionRepository$retryMessage$1.L$4;
                String model4 = (String) defaultSessionRepository$retryMessage$1.L$3;
                localMessageId3 = (String) defaultSessionRepository$retryMessage$1.L$2;
                String sessionId5 = (String) defaultSessionRepository$retryMessage$1.L$1;
                DefaultSessionRepository defaultSessionRepository5 = (DefaultSessionRepository) defaultSessionRepository$retryMessage$1.L$0;
                ResultKt.throwOnFailure($result);
                owner2 = owner3;
                model2 = model4;
                sessionId3 = sessionId5;
                defaultSessionRepository3 = defaultSessionRepository5;
                objClaimForRetry = $result;
                claimed = (MessageEntity) objClaimForRetry;
                if (claimed == null) {
                    Result.Companion companion7 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("No failed message to retry")));
                }
                if (OutboxExactlyOnce.INSTANCE.retryKeepsSameBubble(localMessageId3, claimed.getId())) {
                    throw new IllegalStateException("Retry must reuse local bubble id".toString());
                }
                if (!Intrinsics.areEqual(claimed.getSessionId(), sessionId3)) {
                    messageDao = defaultSessionRepository3.messages;
                    id = claimed.getId();
                    strStatusAfterPreNetworkRejection = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                    defaultSessionRepository$retryMessage$1.L$0 = null;
                    defaultSessionRepository$retryMessage$1.L$1 = null;
                    defaultSessionRepository$retryMessage$1.L$2 = null;
                    defaultSessionRepository$retryMessage$1.L$3 = null;
                    defaultSessionRepository$retryMessage$1.L$4 = null;
                    defaultSessionRepository$retryMessage$1.label = 4;
                    if (messageDao.setDeliveryStatus(id, owner2, strStatusAfterPreNetworkRejection, defaultSessionRepository$retryMessage$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Result.Companion companion8 = Result.Companion;
                    return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message belongs to a different session")));
                }
                it = claimed.getClientMsgId();
                if (it == null) {
                    clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                } else {
                    if (!(StringsKt.isBlank(it) ? false : true)) {
                        it = null;
                    }
                    if (it == null) {
                        clientId = StringsKt.removePrefix(localMessageId3, (CharSequence) "local-");
                    } else {
                        clientId = it;
                    }
                }
                String id5 = claimed.getId();
                String content4 = claimed.getContent();
                defaultSessionRepository$retryMessage$1.L$0 = null;
                defaultSessionRepository$retryMessage$1.L$1 = null;
                defaultSessionRepository$retryMessage$1.L$2 = null;
                defaultSessionRepository$retryMessage$1.L$3 = null;
                defaultSessionRepository$retryMessage$1.L$4 = null;
                defaultSessionRepository$retryMessage$1.label = 5;
                objM51deliverPendingbMdYcbs = defaultSessionRepository3.m51deliverPendingbMdYcbs(sessionId3, id5, content4, model2, owner2, clientId, defaultSessionRepository$retryMessage$1);
                if (objM51deliverPendingbMdYcbs == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return objM51deliverPendingbMdYcbs;
            case 4:
                ResultKt.throwOnFailure($result);
                Result.Companion companion9 = Result.Companion;
                return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message belongs to a different session")));
            case 5:
                ResultKt.throwOnFailure($result);
                return ai.nous.aurora.compat.Compat.resultUnbox($result);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x006b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x006c  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    public Object failPendingMessages(String sessionId, Continuation continuation) {
        C02171 c02171;
        DefaultSessionRepository defaultSessionRepository;
        Object objOwnerKey;
        Object objFailAllPending;
        if (continuation instanceof C02171) {
            c02171 = (C02171) continuation;
            if ((c02171.label & Integer.MIN_VALUE) != 0) {
                c02171.label -= Integer.MIN_VALUE;
            } else {
                c02171 = new C02171(continuation);
            }
        } else {
            c02171 = new C02171(continuation);
        }
        Object $result = c02171.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (c02171.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    defaultSessionRepository = this;
                    c02171.L$0 = defaultSessionRepository;
                    c02171.L$1 = sessionId;
                    c02171.label = 1;
                    objOwnerKey = defaultSessionRepository.ownerKey(c02171);
                    if (objOwnerKey == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    String owner = (String) objOwnerKey;
                    Result.Companion companion = Result.Companion;
                    DefaultSessionRepository $this$failPendingMessages_u24lambda_u2430 = defaultSessionRepository;
                    MessageDao messageDao = $this$failPendingMessages_u24lambda_u2430.messages;
                    c02171.L$0 = null;
                    c02171.L$1 = null;
                    c02171.label = 2;
                    objFailAllPending = messageDao.failAllPending(sessionId, owner, c02171);
                    if (objFailAllPending == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) objFailAllPending).intValue()));
                    return Unit.INSTANCE;
                case 1:
                    sessionId = (String) c02171.L$1;
                    defaultSessionRepository = (DefaultSessionRepository) c02171.L$0;
                    ResultKt.throwOnFailure($result);
                    objOwnerKey = $result;
                    String owner2 = (String) objOwnerKey;
                    Result.Companion companion2 = Result.Companion;
                    DefaultSessionRepository $this$failPendingMessages_u24lambda_u2431 = defaultSessionRepository;
                    MessageDao messageDao2 = $this$failPendingMessages_u24lambda_u2431.messages;
                    c02171.L$0 = null;
                    c02171.L$1 = null;
                    c02171.label = 2;
                    objFailAllPending = messageDao2.failAllPending(sessionId, owner2, c02171);
                    if (objFailAllPending == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) objFailAllPending).intValue()));
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    objFailAllPending = $result;
                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) objFailAllPending).intValue()));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            Result.Companion companion3 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x005c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x006d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    public Object recoverInterruptedMessages(Continuation continuation) {
        C02201 c02201;
        DefaultSessionRepository defaultSessionRepository;
        Object objOwnerKey;
        String owner;
        MessageDao messageDao;
        if (continuation instanceof C02201) {
            c02201 = (C02201) continuation;
            if ((c02201.label & Integer.MIN_VALUE) != 0) {
                c02201.label -= Integer.MIN_VALUE;
            } else {
                c02201 = new C02201(continuation);
            }
        } else {
            c02201 = new C02201(continuation);
        }
        Object $result = c02201.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02201.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                c02201.L$0 = defaultSessionRepository;
                c02201.label = 1;
                if (defaultSessionRepository.ensureLegacyAdopted(c02201) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                c02201.L$0 = defaultSessionRepository;
                c02201.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(c02201);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                owner = (String) objOwnerKey;
                messageDao = defaultSessionRepository.messages;
                c02201.L$0 = null;
                c02201.label = 3;
                if (messageDao.failInterruptedForOwner(owner, c02201) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                defaultSessionRepository = (DefaultSessionRepository) c02201.L$0;
                ResultKt.throwOnFailure($result);
                c02201.L$0 = defaultSessionRepository;
                c02201.label = 2;
                objOwnerKey = defaultSessionRepository.ownerKey(c02201);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                owner = (String) objOwnerKey;
                messageDao = defaultSessionRepository.messages;
                c02201.L$0 = null;
                c02201.label = 3;
                if (messageDao.failInterruptedForOwner(owner, c02201) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                defaultSessionRepository = (DefaultSessionRepository) c02201.L$0;
                ResultKt.throwOnFailure($result);
                objOwnerKey = $result;
                owner = (String) objOwnerKey;
                messageDao = defaultSessionRepository.messages;
                c02201.L$0 = null;
                c02201.label = 3;
                if (messageDao.failInterruptedForOwner(owner, c02201) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:104:0x03eb A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:105:0x03ec  */
    /* JADX WARN: Code duplicated, block: B:113:0x042b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:114:0x042c  */
    /* JADX WARN: Code duplicated, block: B:122:0x0479 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:123:0x047a  */
    /* JADX WARN: Code duplicated, block: B:127:0x0486 A[Catch: all -> 0x0504, TRY_LEAVE, TryCatch #13 {all -> 0x0504, blocks: (B:138:0x04e6, B:137:0x04dd, B:125:0x0480, B:127:0x0486), top: B:259:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:132:0x04d0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:133:0x04d1  */
    /* JADX WARN: Code duplicated, block: B:144:0x0511  */
    /* JADX WARN: Code duplicated, block: B:149:0x0559 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:150:0x055a  */
    /* JADX WARN: Code duplicated, block: B:163:0x05ba A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:164:0x05bb  */
    /* JADX WARN: Code duplicated, block: B:167:0x05d1 A[Catch: all -> 0x070d, TRY_LEAVE, TryCatch #22 {all -> 0x070d, blocks: (B:165:0x05cb, B:167:0x05d1), top: B:292:0x05cb }] */
    /* JADX WARN: Code duplicated, block: B:169:0x0601  */
    /* JADX WARN: Code duplicated, block: B:170:0x0605  */
    /* JADX WARN: Code duplicated, block: B:174:0x0614 A[Catch: all -> 0x06e1, TRY_LEAVE, TryCatch #0 {all -> 0x06e1, blocks: (B:172:0x060e, B:174:0x0614), top: B:251:0x060e }] */
    /* JADX WARN: Code duplicated, block: B:176:0x0636 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:177:0x0637  */
    /* JADX WARN: Code duplicated, block: B:184:0x0671 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:185:0x0672  */
    /* JADX WARN: Code duplicated, block: B:199:0x06c4  */
    /* JADX WARN: Code duplicated, block: B:202:0x06da  */
    /* JADX WARN: Code duplicated, block: B:206:0x06ec  */
    /* JADX WARN: Code duplicated, block: B:219:0x0754 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:222:0x0759 A[Catch: all -> 0x0797, TryCatch #28 {all -> 0x0797, blocks: (B:220:0x0755, B:222:0x0759, B:224:0x075f, B:226:0x0765, B:233:0x079f, B:217:0x0731, B:216:0x0723), top: B:304:0x0723 }] */
    /* JADX WARN: Code duplicated, block: B:223:0x075e  */
    /* JADX WARN: Code duplicated, block: B:226:0x0765 A[Catch: all -> 0x0797, TRY_LEAVE, TryCatch #28 {all -> 0x0797, blocks: (B:220:0x0755, B:222:0x0759, B:224:0x075f, B:226:0x0765, B:233:0x079f, B:217:0x0731, B:216:0x0723), top: B:304:0x0723 }] */
    /* JADX WARN: Code duplicated, block: B:229:0x0794  */
    /* JADX WARN: Code duplicated, block: B:232:0x079a  */
    /* JADX WARN: Code duplicated, block: B:276:0x033d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:284:0x03ff A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:300:0x064a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:308:0x0594 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:312:0x04b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:87:0x0369 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:88:0x036a  */
    /* JADX WARN: Code duplicated, block: B:99:0x03b7  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v51, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r1v60 */
    /* JADX WARN: Type inference failed for: r1v64 */
    /* JADX WARN: Type inference failed for: r27v3 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v74 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v12, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v22, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v49 */
    /* JADX WARN: Type inference failed for: r4v51 */
    /* JADX WARN: Type inference failed for: r4v52 */
    /* JADX WARN: Type inference failed for: r4v55 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v65 */
    /* JADX WARN: Type inference failed for: r4v68 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v71 */
    /* JADX WARN: Type inference failed for: r4v74 */
    /* JADX WARN: Type inference failed for: r4v75 */
    /* JADX WARN: Type inference failed for: r4v76, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v77 */
    /* JADX WARN: Type inference failed for: r4v78 */
    /* JADX WARN: Type inference failed for: r4v87 */
    /* JADX WARN: Type inference failed for: r4v88, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r4v91 */
    /* JADX WARN: Type inference failed for: r4v92 */
    /* JADX WARN: Type inference failed for: r4v93 */
    /* JADX WARN: Type inference failed for: r4v94 */
    /* JADX WARN: Type inference failed for: r4v95 */
    /* JADX WARN: Type inference failed for: r4v96 */
    /* JADX WARN: Type inference failed for: r4v97 */
    /* JADX WARN: Type inference failed for: r4v98 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v23, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v36, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r5v39 */
    /* JADX WARN: Type inference failed for: r5v43, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v58 */
    /* JADX WARN: Type inference failed for: r5v67 */
    /* JADX WARN: Type inference failed for: r5v70 */
    /* JADX WARN: Type inference failed for: r5v72 */
    /* JADX WARN: Type inference failed for: r5v80 */
    /* JADX WARN: Type inference failed for: r5v81 */
    /* JADX WARN: Type inference failed for: r5v82 */
    /* JADX WARN: Type inference failed for: r5v83 */
    /* JADX WARN: Type inference failed for: r5v84 */
    /* JADX WARN: Type inference failed for: r5v85 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v49, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v54 */
    /* JADX WARN: Type inference failed for: r6v94 */
    /* JADX WARN: Type inference failed for: r6v95 */
    /* JADX WARN: Type inference failed for: r6v96 */
    /* JADX WARN: Type inference failed for: r6v97 */
    /* JADX WARN: Type inference failed for: r6v98 */
    /* JADX WARN: Type inference failed for: r8v58 */
    /* JADX WARN: Type inference failed for: r8v59 */
    /* JADX WARN: Type inference failed for: r8v60 */
    /* JADX WARN: Type inference failed for: r8v64 */
    /* JADX WARN: Type inference failed for: r8v72 */
    /* JADX WARN: Type inference failed for: r8v74 */
    /* JADX WARN: Type inference failed for: r8v76, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r8v82 */
    /* JADX WARN: Type inference failed for: r8v83 */
    /* JADX WARN: Type inference failed for: r8v84 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v58 */
    /* JADX WARN: Type inference failed for: r9v63, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v98 */
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
    /* JADX INFO: renamed from: deliverPending-bMdYcbs, reason: not valid java name */
    public final Object m51deliverPendingbMdYcbs(String str, String str2, String str3, String str4, String str5, String str6, Continuation continuation) throws Throwable {
        DefaultSessionRepository$deliverPending$1 defaultSessionRepository$deliverPending$1;
        DefaultSessionRepository defaultSessionRepository;
        Object r4;
        String str7;
        DefaultSessionRepository defaultSessionRepository2;
        String str8;
        String str9;
        Ref.BooleanRef booleanRef;
        Object objMarkSending;
        String str10;
        DefaultSessionRepository defaultSessionRepository3;
        Object r9;
        String str11;
        String str12;
        String str13;
        String str14;
        Object objUpdateStatusIf;
        String str15;
        Object r5;
        DefaultSessionRepository defaultSessionRepository4;
        String str16;
        Object deliveryStatus;
        DefaultSessionRepository defaultSessionRepository5;
        Object r3;
        DefaultSessionRepository defaultSessionRepository6;
        String str17;
        String str18;
        String str19;
        String str20;
        Object r19;
        Object objM46sendMessagehUnOzRk;
        String str21;
        String str22;
        String str23;
        Ref.BooleanRef booleanRef2;
        Object value;
        DefaultSessionRepository defaultSessionRepository7;
        String str24;
        Object r6;
        String str25;
        DefaultSessionRepository defaultSessionRepository8;
        Throwable thM7391exceptionOrNullimpl;
        AuroraMessage auroraMessage;
        DefaultSessionRepository defaultSessionRepository9;
        Throwable th = null;
        AuroraMessage auroraMessage2;
        Object deliveryStatus2;
        DefaultSessionRepository defaultSessionRepository10;
        String str26;
        AuroraMessage auroraMessage3;
        Object r7;
        String str27;
        String str28;
        Throwable th2 = null;
        int i;
        String strClassifyTransportFailure$default;
        String str29;
        int i2;
        String str30;
        Object r8;
        String str31;
        Object deliveryStatus3;
        String str32;
        Object r10;
        String str33;
        Object r11;
        DefaultSessionRepository defaultSessionRepository11;
        String str34;
        Object r12;
        Object objMo55selectSessiongIAlus;
        String str35;
        String str36;
        Object value2;
        Object r1;
        AuroraMessage auroraMessage4;
        DefaultSessionRepository defaultSessionRepository12;
        MessageDao messageDao;
        String str37;
        Object r13;
        DefaultSessionRepository defaultSessionRepository13;
        String str38;
        Object r2;
        Object objM7388constructorimpl;
        DefaultSessionRepository defaultSessionRepository14;
        String str39;
        String str40;
        Object r14;
        String str41;
        String str42;
        DefaultSessionRepository defaultSessionRepository15;
        int i3;
        Object objM43getMessagesgIAlus;
        String str43;
        String str44;
        DefaultSessionRepository defaultSessionRepository16;
        Object value3;
        String str45;
        DefaultSessionRepository defaultSessionRepository17;
        Throwable th3 = null;
        Object r15;
        String str46;
        Object r16;
        String str47;
        DefaultSessionRepository defaultSessionRepository18;
        int i4;
        AuroraMessage auroraMessage5;
        int i5;
        boolean z;
        String strReconcileAmbiguousAgainstRemote;
        MessageDao messageDao2;
        DefaultSessionRepository defaultSessionRepository19;
        String str48;
        Object obj;
        Object r17;
        String str49;
        String str50;
        String str51;
        int i6;
        Throwable th4 = null;
        Object r18;
        String str52;
        String str53;
        String str54;
        int i7;
        String str55;
        String str56;
        Object objMo55selectSessiongIAlus2;
        Object r20;
        String str57;
        DefaultSessionRepository defaultSessionRepository20;
        int i8;
        Throwable th5 = null;
        String str58;
        String str59;
        DefaultSessionRepository defaultSessionRepository21;
        String str60;
        Object r21;
        Object byId;
        DefaultSessionRepository defaultSessionRepository22;
        String str61;
        Object r22;
        String str62;
        DefaultSessionRepository defaultSessionRepository23;
        Object r23;
        DefaultSessionRepository defaultSessionRepository24;
        String str63;
        Object r24;
        DefaultSessionRepository defaultSessionRepository25;
        DefaultSessionRepository defaultSessionRepository26;
        String str64;
        Object r25;
        MessageEntity messageEntity;
        String deliveryStatus4;
        if (continuation instanceof DefaultSessionRepository$deliverPending$1) {
            defaultSessionRepository$deliverPending$1 = (DefaultSessionRepository$deliverPending$1) continuation;
            if ((defaultSessionRepository$deliverPending$1.label & Integer.MIN_VALUE) != 0) {
                defaultSessionRepository$deliverPending$1.label -= Integer.MIN_VALUE;
                defaultSessionRepository = this;
            } else {
                defaultSessionRepository = this;
                defaultSessionRepository$deliverPending$1 = new DefaultSessionRepository$deliverPending$1(defaultSessionRepository, continuation);
            }
        } else {
            defaultSessionRepository = this;
            defaultSessionRepository$deliverPending$1 = new DefaultSessionRepository$deliverPending$1(defaultSessionRepository, continuation);
        }
        DefaultSessionRepository$deliverPending$1 defaultSessionRepository$deliverPending$2 = defaultSessionRepository$deliverPending$1;
        Object obj2 = defaultSessionRepository$deliverPending$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            try {
                try {
                    switch (defaultSessionRepository$deliverPending$2.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj2);
                            defaultSessionRepository2 = this;
                            str9 = str3;
                            str7 = str5;
                            Mutex mutexLockFor = defaultSessionRepository2.lockFor(str7, str2);
                            if (!ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_tryLock_4(mutexLockFor, null, 1, null)) {
                                Result.Companion companion = Result.Companion;
                                MessageDao messageDao3 = defaultSessionRepository2.messages;
                                String strStatusAfterPreNetworkRejection = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                                defaultSessionRepository$deliverPending$2.label = 1;
                                objUpdateStatusIf = messageDao3.updateStatusIf(str2, str, str7, OutboxExactlyOnce.STATUS_PENDING, strStatusAfterPreNetworkRejection, defaultSessionRepository$deliverPending$2);
                                if (objUpdateStatusIf == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) objUpdateStatusIf).intValue()));
                                Result.Companion companion2 = Result.Companion;
                                return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message already delivering")));
                            }
                            booleanRef = new Ref.BooleanRef();
                            try {
                                MessageDao messageDao4 = defaultSessionRepository2.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$deliverPending$2.L$1 = str;
                                defaultSessionRepository$deliverPending$2.L$2 = str2;
                                defaultSessionRepository$deliverPending$2.L$3 = str9;
                                defaultSessionRepository$deliverPending$2.L$4 = str4;
                                defaultSessionRepository$deliverPending$2.L$5 = str7;
                                defaultSessionRepository$deliverPending$2.L$6 = str6;
                                defaultSessionRepository$deliverPending$2.L$7 = mutexLockFor;
                                defaultSessionRepository$deliverPending$2.L$8 = booleanRef;
                                defaultSessionRepository$deliverPending$2.label = 2;
                                objMarkSending = messageDao4.markSending(str2, str, str7, defaultSessionRepository$deliverPending$2);
                                if (objMarkSending == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str10 = str6;
                                defaultSessionRepository3 = defaultSessionRepository2;
                                r9 = mutexLockFor;
                                str11 = str7;
                                str12 = str4;
                                str13 = str2;
                                str14 = str;
                                try {
                                    if (!((Boolean) objMarkSending).booleanValue()) {
                                        try {
                                            Result.Companion companion3 = Result.Companion;
                                            MessageDao messageDao5 = defaultSessionRepository3.messages;
                                            String strStatusAfterPreNetworkRejection2 = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository3;
                                            defaultSessionRepository$deliverPending$2.L$1 = str13;
                                            defaultSessionRepository$deliverPending$2.L$2 = str11;
                                            defaultSessionRepository$deliverPending$2.L$3 = r9;
                                            defaultSessionRepository$deliverPending$2.L$4 = null;
                                            defaultSessionRepository$deliverPending$2.L$5 = null;
                                            defaultSessionRepository$deliverPending$2.L$6 = null;
                                            defaultSessionRepository$deliverPending$2.L$7 = null;
                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                            defaultSessionRepository$deliverPending$2.label = 3;
                                            deliveryStatus = messageDao5.setDeliveryStatus(str13, str11, strStatusAfterPreNetworkRejection2, defaultSessionRepository$deliverPending$2);
                                            if (deliveryStatus == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            defaultSessionRepository5 = defaultSessionRepository3;
                                            r3 = r9;
                                            str25 = str11;
                                            try {
                                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus).intValue()));
                                                r6 = r3;
                                                defaultSessionRepository7 = defaultSessionRepository5;
                                                str24 = str25;
                                                str16 = str13;
                                            } catch (Throwable th6) {
                                                r5 = r3;
                                                defaultSessionRepository4 = defaultSessionRepository5;
                                                str15 = str25;
                                                str16 = str13;
                                                Result.Companion companion4 = Result.Companion;
                                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th6));
                                                r6 = r5;
                                                str24 = str15;
                                                defaultSessionRepository7 = defaultSessionRepository4;
                                            }
                                            Result.Companion companion5 = Result.Companion;
                                            Object objM7388constructorimpl2 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Lost delivery claim before network")));
                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r6, null, 1, null);
                                            defaultSessionRepository7.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str24, str16), r6);
                                            return objM7388constructorimpl2;
                                        } catch (Throwable th7) {
                                            str15 = str11;
                                            r5 = r9;
                                            defaultSessionRepository4 = defaultSessionRepository3;
                                            str16 = str13;
                                            Result.Companion companion6 = Result.Companion;
                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th7));
                                            r6 = r5;
                                            str24 = str15;
                                            defaultSessionRepository7 = defaultSessionRepository4;
                                            Result.Companion companion7 = Result.Companion;
                                            Object objM7388constructorimpl3 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Lost delivery claim before network")));
                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r6, null, 1, null);
                                            defaultSessionRepository7.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str24, str16), r6);
                                            return objM7388constructorimpl3;
                                        }
                                    }
                                    booleanRef.element = true;
                                    GatewayClient gatewayClient = defaultSessionRepository3.gatewayClient;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository3;
                                    defaultSessionRepository$deliverPending$2.L$1 = str14;
                                    defaultSessionRepository$deliverPending$2.L$2 = str13;
                                    defaultSessionRepository$deliverPending$2.L$3 = str9;
                                    defaultSessionRepository$deliverPending$2.L$4 = str11;
                                    defaultSessionRepository$deliverPending$2.L$5 = str10;
                                    defaultSessionRepository$deliverPending$2.L$6 = r9;
                                    defaultSessionRepository$deliverPending$2.L$7 = booleanRef;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.label = 4;
                                    defaultSessionRepository6 = defaultSessionRepository3;
                                    str17 = str14;
                                    str18 = str13;
                                    str19 = str9;
                                    str20 = str11;
                                    r19 = r9;
                                    try {
                                        objM46sendMessagehUnOzRk = gatewayClient.m46sendMessagehUnOzRk(str14, str9, str12, str10, str11, defaultSessionRepository$deliverPending$2);
                                        if (objM46sendMessagehUnOzRk == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        str21 = str10;
                                        str8 = str18;
                                        str22 = str19;
                                        str7 = str20;
                                        r4 = r19;
                                        str23 = str17;
                                        booleanRef2 = booleanRef;
                                        value = objM46sendMessagehUnOzRk;
                                        defaultSessionRepository8 = defaultSessionRepository6;
                                        try {
                                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                                            if (thM7391exceptionOrNullimpl == null) {
                                                try {
                                                    auroraMessage = (AuroraMessage) value;
                                                    try {
                                                        Result.Companion companion8 = Result.Companion;
                                                        MessageDao messageDao6 = defaultSessionRepository8.messages;
                                                        String strStatusAfterNetworkAck = OutboxExactlyOnce.INSTANCE.statusAfterNetworkAck();
                                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository8;
                                                        defaultSessionRepository$deliverPending$2.L$1 = str23;
                                                        defaultSessionRepository$deliverPending$2.L$2 = str8;
                                                        defaultSessionRepository$deliverPending$2.L$3 = str7;
                                                        defaultSessionRepository$deliverPending$2.L$4 = str21;
                                                        defaultSessionRepository$deliverPending$2.L$5 = r4;
                                                        defaultSessionRepository$deliverPending$2.L$6 = auroraMessage;
                                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                                        defaultSessionRepository$deliverPending$2.label = 5;
                                                        deliveryStatus2 = messageDao6.setDeliveryStatus(str8, str7, strStatusAfterNetworkAck, defaultSessionRepository$deliverPending$2);
                                                        if (deliveryStatus2 == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                        defaultSessionRepository10 = defaultSessionRepository8;
                                                        str26 = str23;
                                                        Object r27 = r4;
                                                        auroraMessage3 = auroraMessage;
                                                        r7 = r27;
                                                        String str65 = str21;
                                                        str27 = str7;
                                                        str28 = str65;
                                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus2).intValue()));
                                                        auroraMessage2 = auroraMessage3;
                                                        r12 = r7;
                                                        str23 = str26;
                                                        defaultSessionRepository11 = defaultSessionRepository10;
                                                        String str66 = str27;
                                                        str21 = str28;
                                                        str34 = str66;
                                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository11;
                                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                        defaultSessionRepository$deliverPending$2.L$2 = str34;
                                                        defaultSessionRepository$deliverPending$2.L$3 = str21;
                                                        defaultSessionRepository$deliverPending$2.L$4 = r12;
                                                        defaultSessionRepository$deliverPending$2.L$5 = auroraMessage2;
                                                        defaultSessionRepository$deliverPending$2.L$6 = null;
                                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                                        defaultSessionRepository$deliverPending$2.label = 6;
                                                        objMo55selectSessiongIAlus = defaultSessionRepository11.mo55selectSessiongIAlus(str23, defaultSessionRepository$deliverPending$2);
                                                        if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                        str35 = str34;
                                                        str36 = str21;
                                                        value2 = objMo55selectSessiongIAlus;
                                                        r1 = r12;
                                                        auroraMessage4 = auroraMessage2;
                                                        defaultSessionRepository12 = defaultSessionRepository11;
                                                        if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(value2)) {
                                                            Result.Companion companion9 = Result.Companion;
                                                            Object objM7388constructorimpl4 = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r1, null, 1, null);
                                                            defaultSessionRepository12.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str35, str8), r1);
                                                            return objM7388constructorimpl4;
                                                        }
                                                        try {
                                                            Result.Companion companion10 = Result.Companion;
                                                            messageDao = defaultSessionRepository12.messages;
                                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository12;
                                                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                            defaultSessionRepository$deliverPending$2.L$2 = str35;
                                                            defaultSessionRepository$deliverPending$2.L$3 = str36;
                                                            defaultSessionRepository$deliverPending$2.L$4 = r1;
                                                            defaultSessionRepository$deliverPending$2.L$5 = auroraMessage4;
                                                            defaultSessionRepository$deliverPending$2.label = 7;
                                                            if (messageDao.deleteById(str8, str35, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                            str37 = str35;
                                                            r13 = r1;
                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                                                            r2 = r13;
                                                            str38 = str37;
                                                            defaultSessionRepository13 = defaultSessionRepository12;
                                                            Result.Companion companion11 = Result.Companion;
                                                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                                            r24 = r2;
                                                            str63 = str38;
                                                            defaultSessionRepository24 = defaultSessionRepository13;
                                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                                            return objM7388constructorimpl;
                                                        } catch (Throwable th8) {
                                                            Result.Companion companion12 = Result.Companion;
                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th8));
                                                            r2 = r1;
                                                            str38 = str35;
                                                            defaultSessionRepository13 = defaultSessionRepository12;
                                                        }
                                                    } catch (Throwable th9) {
                                                        defaultSessionRepository9 = defaultSessionRepository8;
                                                        auroraMessage2 = auroraMessage;
                                                        r11 = r4;
                                                        str33 = str7;
                                                        Result.Companion companion13 = Result.Companion;
                                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th9));
                                                        r12 = r11;
                                                        str34 = str33;
                                                        defaultSessionRepository11 = defaultSessionRepository9;
                                                    }
                                                } catch (Throwable th10) {
                                                    defaultSessionRepository2 = defaultSessionRepository8;
                                                }
                                            } else {
                                                th2 = thM7391exceptionOrNullimpl;
                                                i = 0;
                                                strClassifyTransportFailure$default = OutboxExactlyOnce.classifyTransportFailure$default(OutboxExactlyOnce.INSTANCE, th2.getMessage(), booleanRef2.element, null, 4, null);
                                                try {
                                                    Result.Companion companion14 = Result.Companion;
                                                    MessageDao messageDao7 = defaultSessionRepository8.messages;
                                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository8;
                                                    defaultSessionRepository$deliverPending$2.L$1 = str23;
                                                    defaultSessionRepository$deliverPending$2.L$2 = str8;
                                                    defaultSessionRepository$deliverPending$2.L$3 = str22;
                                                    defaultSessionRepository$deliverPending$2.L$4 = str7;
                                                    defaultSessionRepository$deliverPending$2.L$5 = str21;
                                                    defaultSessionRepository$deliverPending$2.L$6 = r4;
                                                    defaultSessionRepository$deliverPending$2.L$7 = th2;
                                                    defaultSessionRepository$deliverPending$2.L$8 = strClassifyTransportFailure$default;
                                                    defaultSessionRepository$deliverPending$2.label = 8;
                                                    deliveryStatus3 = messageDao7.setDeliveryStatus(str8, str7, strClassifyTransportFailure$default, defaultSessionRepository$deliverPending$2);
                                                    if (deliveryStatus3 == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    str29 = str8;
                                                    str32 = str7;
                                                    r10 = r4;
                                                    str31 = strClassifyTransportFailure$default;
                                                    defaultSessionRepository18 = defaultSessionRepository8;
                                                    try {
                                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus3).intValue()));
                                                        i2 = i;
                                                        String str67 = str22;
                                                        defaultSessionRepository2 = defaultSessionRepository18;
                                                        str39 = str67;
                                                        r14 = r10;
                                                        str40 = str32;
                                                    } catch (Throwable th11) {
                                                        i2 = i;
                                                        defaultSessionRepository14 = defaultSessionRepository18;
                                                        r8 = r10;
                                                        str30 = str32;
                                                        try {
                                                            Result.Companion companion15 = Result.Companion;
                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th11));
                                                            String str68 = str22;
                                                            defaultSessionRepository2 = defaultSessionRepository14;
                                                            str39 = str68;
                                                            r14 = r8;
                                                            str40 = str30;
                                                        } catch (Throwable th12) {
                                                            defaultSessionRepository2 = defaultSessionRepository14;
                                                            r4 = r8;
                                                            str7 = str30;
                                                            str8 = str29;
                                                        }
                                                    }
                                                    try {
                                                        if (Intrinsics.areEqual(str31, OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                                                            try {
                                                                Result.Companion companion16 = Result.Companion;
                                                                defaultSessionRepository15 = defaultSessionRepository2;
                                                                i3 = 0;
                                                                GatewayClient gatewayClient2 = defaultSessionRepository15.gatewayClient;
                                                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                                                defaultSessionRepository$deliverPending$2.L$1 = str23;
                                                                defaultSessionRepository$deliverPending$2.L$2 = str29;
                                                                defaultSessionRepository$deliverPending$2.L$3 = str39;
                                                                defaultSessionRepository$deliverPending$2.L$4 = str40;
                                                                defaultSessionRepository$deliverPending$2.L$5 = str21;
                                                                defaultSessionRepository$deliverPending$2.L$6 = r14;
                                                                defaultSessionRepository$deliverPending$2.L$7 = th2;
                                                                defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository15;
                                                                defaultSessionRepository$deliverPending$2.label = 9;
                                                                objM43getMessagesgIAlus = gatewayClient2.m43getMessagesgIAlus(str23, defaultSessionRepository$deliverPending$2);
                                                                if (objM43getMessagesgIAlus == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                                str43 = str39;
                                                                str44 = str21;
                                                                defaultSessionRepository16 = defaultSessionRepository15;
                                                                DefaultSessionRepository defaultSessionRepository27 = defaultSessionRepository2;
                                                                value3 = objM43getMessagesgIAlus;
                                                                str45 = str23;
                                                                defaultSessionRepository17 = defaultSessionRepository27;
                                                                String str69 = str40;
                                                                th3 = th2;
                                                                r15 = r14;
                                                                str46 = str69;
                                                                try {
                                                                    try {
                                                                        if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                                                            i4 = 0;
                                                                            OutboxExactlyOnce outboxExactlyOnce = OutboxExactlyOnce.INSTANCE;
                                                                            Function1 function1 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                                                                @Override // kotlin.jvm.functions.Function1
                                                                                public final Object invoke(Object obj3) {
                                                                                    return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                                                                }
                                                                            };
                                                                            auroraMessage5 = (AuroraMessage) outboxExactlyOnce.findRemoteContentMatch((List) value3, str43, function1, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                                                                @Override // kotlin.jvm.functions.Function1
                                                                                public final Object invoke(Object obj3) {
                                                                                    return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                                                                }
                                                                            }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                                                                @Override // kotlin.jvm.functions.Function1
                                                                                public final Object invoke(Object obj3) {
                                                                                    return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function1, obj3));
                                                                                }
                                                                            } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                                                                @Override // kotlin.jvm.functions.Function1
                                                                                public final Object invoke(Object obj3) {
                                                                                    return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                                                                }
                                                                            });
                                                                            i5 = i3;
                                                                            OutboxExactlyOnce outboxExactlyOnce2 = OutboxExactlyOnce.INSTANCE;
                                                                            if (auroraMessage5 != null) {
                                                                                z = true;
                                                                            } else {
                                                                                z = false;
                                                                            }
                                                                            try {
                                                                                strReconcileAmbiguousAgainstRemote = outboxExactlyOnce2.reconcileAmbiguousAgainstRemote(true, z);
                                                                                if (strReconcileAmbiguousAgainstRemote != null) {
                                                                                    messageDao2 = defaultSessionRepository16.messages;
                                                                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                                                                    defaultSessionRepository$deliverPending$2.L$1 = str45;
                                                                                    defaultSessionRepository$deliverPending$2.L$2 = str29;
                                                                                    defaultSessionRepository$deliverPending$2.L$3 = str43;
                                                                                    defaultSessionRepository$deliverPending$2.L$4 = str46;
                                                                                    defaultSessionRepository$deliverPending$2.L$5 = str44;
                                                                                    defaultSessionRepository$deliverPending$2.L$6 = r15;
                                                                                    defaultSessionRepository$deliverPending$2.L$7 = th3;
                                                                                    defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                                                                    defaultSessionRepository$deliverPending$2.L$9 = value3;
                                                                                    defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                                                                    defaultSessionRepository$deliverPending$2.label = 10;
                                                                                    if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                                                                        return coroutine_suspended;
                                                                                    }
                                                                                    defaultSessionRepository19 = defaultSessionRepository17;
                                                                                    str48 = str46;
                                                                                    obj = value3;
                                                                                    r17 = r15;
                                                                                    str49 = strReconcileAmbiguousAgainstRemote;
                                                                                    str50 = str29;
                                                                                    str42 = str44;
                                                                                    str51 = str43;
                                                                                    i6 = i5;
                                                                                    try {
                                                                                        if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                                                                            try {
                                                                                                Result.Companion companion17 = Result.Companion;
                                                                                                DefaultSessionRepository defaultSessionRepository28 = defaultSessionRepository16;
                                                                                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                                                                                defaultSessionRepository$deliverPending$2.L$1 = str50;
                                                                                                defaultSessionRepository$deliverPending$2.L$2 = str51;
                                                                                                defaultSessionRepository$deliverPending$2.L$3 = str48;
                                                                                                defaultSessionRepository$deliverPending$2.L$4 = str42;
                                                                                                defaultSessionRepository$deliverPending$2.L$5 = r17;
                                                                                                defaultSessionRepository$deliverPending$2.L$6 = th3;
                                                                                                defaultSessionRepository$deliverPending$2.L$7 = obj;
                                                                                                str56 = str51;
                                                                                                try {
                                                                                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.label = 11;
                                                                                                    objMo55selectSessiongIAlus2 = defaultSessionRepository28.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                                                                                    if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                                                                        return coroutine_suspended;
                                                                                                    }
                                                                                                    th4 = th3;
                                                                                                    r18 = r17;
                                                                                                    str52 = str42;
                                                                                                    str53 = str48;
                                                                                                    str54 = str56;
                                                                                                    i7 = i4;
                                                                                                    str55 = str50;
                                                                                                    try {
                                                                                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                                                                                        r23 = r18;
                                                                                                        defaultSessionRepository23 = defaultSessionRepository19;
                                                                                                        str62 = str53;
                                                                                                    } catch (Throwable th13) {
                                                                                                        try {
                                                                                                            Result.Companion companion18 = Result.Companion;
                                                                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th13));
                                                                                                            r23 = r18;
                                                                                                            defaultSessionRepository23 = defaultSessionRepository19;
                                                                                                            str62 = str53;
                                                                                                        } catch (Throwable th14) {
                                                                                                            th2 = th4;
                                                                                                            r4 = r18;
                                                                                                            str7 = str53;
                                                                                                            str8 = str55;
                                                                                                            str42 = str52;
                                                                                                            defaultSessionRepository2 = defaultSessionRepository19;
                                                                                                            str41 = str54;
                                                                                                        }
                                                                                                    }
                                                                                                    str44 = str52;
                                                                                                    str43 = str54;
                                                                                                    value3 = obj;
                                                                                                    r20 = r23;
                                                                                                    defaultSessionRepository20 = defaultSessionRepository23;
                                                                                                    str57 = str62;
                                                                                                    str29 = str55;
                                                                                                    th3 = th4;
                                                                                                } catch (Throwable th15) {
                                                                                                    th4 = th3;
                                                                                                    r18 = r17;
                                                                                                    str52 = str42;
                                                                                                    str53 = str48;
                                                                                                    str54 = str56;
                                                                                                    i7 = i4;
                                                                                                    str55 = str50;
                                                                                                    Result.Companion companion19 = Result.Companion;
                                                                                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th15));
                                                                                                    r23 = r18;
                                                                                                    defaultSessionRepository23 = defaultSessionRepository19;
                                                                                                    str62 = str53;
                                                                                                    str44 = str52;
                                                                                                    str43 = str54;
                                                                                                    value3 = obj;
                                                                                                    r20 = r23;
                                                                                                    defaultSessionRepository20 = defaultSessionRepository23;
                                                                                                    str57 = str62;
                                                                                                    str29 = str55;
                                                                                                    th3 = th4;
                                                                                                    str41 = str43;
                                                                                                    r22 = r20;
                                                                                                    str61 = str57;
                                                                                                    defaultSessionRepository22 = defaultSessionRepository20;
                                                                                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                                                                                    i8 = i2;
                                                                                                    r21 = r22;
                                                                                                    th5 = th3;
                                                                                                    str8 = str29;
                                                                                                    defaultSessionRepository21 = defaultSessionRepository22;
                                                                                                    str59 = str41;
                                                                                                    str58 = str44;
                                                                                                    str60 = str61;
                                                                                                    MessageDao messageDao8 = defaultSessionRepository21.messages;
                                                                                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                                                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                                                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                                                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                                                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                                                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                                                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                                                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                                                                                    defaultSessionRepository$deliverPending$2.label = 12;
                                                                                                    byId = messageDao8.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                                                                    r25 = r21;
                                                                                                    str64 = str60;
                                                                                                    defaultSessionRepository26 = defaultSessionRepository21;
                                                                                                    if (byId == coroutine_suspended) {
                                                                                                        return coroutine_suspended;
                                                                                                    }
                                                                                                    messageEntity = (MessageEntity) byId;
                                                                                                    if (messageEntity != null) {
                                                                                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                                                                    } else {
                                                                                                        deliveryStatus4 = null;
                                                                                                    }
                                                                                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                                                                        Result.Companion companion20 = Result.Companion;
                                                                                                        Object objM7388constructorimpl5 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                                                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                                                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                                                                        return objM7388constructorimpl5;
                                                                                                    }
                                                                                                    th2 = th5;
                                                                                                    r16 = r25;
                                                                                                    str47 = str64;
                                                                                                    defaultSessionRepository25 = defaultSessionRepository26;
                                                                                                    Result.Companion companion21 = Result.Companion;
                                                                                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                                                                                    r24 = r16;
                                                                                                    str63 = str47;
                                                                                                    defaultSessionRepository24 = defaultSessionRepository25;
                                                                                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                                                                                    defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                                                                                    return objM7388constructorimpl;
                                                                                                }
                                                                                            } catch (Throwable th16) {
                                                                                                th4 = th3;
                                                                                                r18 = r17;
                                                                                                str52 = str42;
                                                                                                str53 = str48;
                                                                                                str54 = str51;
                                                                                                i7 = i4;
                                                                                                str55 = str50;
                                                                                            }
                                                                                        } else {
                                                                                            String str70 = str51;
                                                                                            r20 = r17;
                                                                                            str44 = str42;
                                                                                            str29 = str50;
                                                                                            value3 = obj;
                                                                                            str57 = str48;
                                                                                            str43 = str70;
                                                                                            defaultSessionRepository20 = defaultSessionRepository19;
                                                                                        }
                                                                                    } catch (Throwable th17) {
                                                                                        str41 = str51;
                                                                                        th2 = th3;
                                                                                        r4 = r17;
                                                                                        defaultSessionRepository2 = defaultSessionRepository19;
                                                                                        str7 = str48;
                                                                                        str8 = str50;
                                                                                    }
                                                                                } else {
                                                                                    r20 = r15;
                                                                                    str57 = str46;
                                                                                    defaultSessionRepository20 = defaultSessionRepository17;
                                                                                }
                                                                                str41 = str43;
                                                                                r22 = r20;
                                                                                str61 = str57;
                                                                                defaultSessionRepository22 = defaultSessionRepository20;
                                                                                try {
                                                                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                                                                    i8 = i2;
                                                                                    r21 = r22;
                                                                                    th5 = th3;
                                                                                    str8 = str29;
                                                                                    defaultSessionRepository21 = defaultSessionRepository22;
                                                                                    str59 = str41;
                                                                                    str58 = str44;
                                                                                    str60 = str61;
                                                                                } catch (Throwable th18) {
                                                                                    r4 = r22;
                                                                                    th2 = th3;
                                                                                    str8 = str29;
                                                                                    defaultSessionRepository2 = defaultSessionRepository22;
                                                                                    str42 = str44;
                                                                                    str7 = str61;
                                                                                    Result.Companion companion22 = Result.Companion;
                                                                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th18));
                                                                                    i8 = i2;
                                                                                    th5 = th2;
                                                                                    str58 = str42;
                                                                                    str59 = str41;
                                                                                    r21 = r4;
                                                                                    str60 = str7;
                                                                                    defaultSessionRepository21 = defaultSessionRepository2;
                                                                                }
                                                                            } catch (Throwable th19) {
                                                                                str41 = str43;
                                                                                r4 = r15;
                                                                                th2 = th3;
                                                                                str8 = str29;
                                                                                defaultSessionRepository2 = defaultSessionRepository17;
                                                                                str42 = str44;
                                                                                str7 = str46;
                                                                            }
                                                                            MessageDao messageDao9 = defaultSessionRepository21.messages;
                                                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                                            defaultSessionRepository$deliverPending$2.L$7 = null;
                                                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                                                            defaultSessionRepository$deliverPending$2.L$9 = null;
                                                                            defaultSessionRepository$deliverPending$2.L$10 = null;
                                                                            defaultSessionRepository$deliverPending$2.label = 12;
                                                                            byId = messageDao9.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                                            r25 = r21;
                                                                            str64 = str60;
                                                                            defaultSessionRepository26 = defaultSessionRepository21;
                                                                            if (byId == coroutine_suspended) {
                                                                                return coroutine_suspended;
                                                                            }
                                                                            messageEntity = (MessageEntity) byId;
                                                                            if (messageEntity != null) {
                                                                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                                            } else {
                                                                                deliveryStatus4 = null;
                                                                            }
                                                                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                                                Result.Companion companion23 = Result.Companion;
                                                                                Object objM7388constructorimpl6 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                                                return objM7388constructorimpl6;
                                                                            }
                                                                            th2 = th5;
                                                                            r16 = r25;
                                                                            str47 = str64;
                                                                            defaultSessionRepository25 = defaultSessionRepository26;
                                                                        } else {
                                                                            str41 = str43;
                                                                            r22 = r15;
                                                                            str61 = str46;
                                                                            defaultSessionRepository22 = defaultSessionRepository17;
                                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                                                            i8 = i2;
                                                                            r21 = r22;
                                                                            th5 = th3;
                                                                            str8 = str29;
                                                                            defaultSessionRepository21 = defaultSessionRepository22;
                                                                            str59 = str41;
                                                                            str58 = str44;
                                                                            str60 = str61;
                                                                            MessageDao messageDao10 = defaultSessionRepository21.messages;
                                                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                                            defaultSessionRepository$deliverPending$2.L$7 = null;
                                                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                                                            defaultSessionRepository$deliverPending$2.L$9 = null;
                                                                            defaultSessionRepository$deliverPending$2.L$10 = null;
                                                                            defaultSessionRepository$deliverPending$2.label = 12;
                                                                            byId = messageDao10.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                                            r25 = r21;
                                                                            str64 = str60;
                                                                            defaultSessionRepository26 = defaultSessionRepository21;
                                                                            if (byId == coroutine_suspended) {
                                                                                return coroutine_suspended;
                                                                            }
                                                                            messageEntity = (MessageEntity) byId;
                                                                            if (messageEntity != null) {
                                                                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                                            } else {
                                                                                deliveryStatus4 = null;
                                                                            }
                                                                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                                                Result.Companion companion24 = Result.Companion;
                                                                                Object objM7388constructorimpl7 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                                                return objM7388constructorimpl7;
                                                                            }
                                                                            th2 = th5;
                                                                            r16 = r25;
                                                                            str47 = str64;
                                                                            defaultSessionRepository25 = defaultSessionRepository26;
                                                                        }
                                                                        Result.Companion companion25 = Result.Companion;
                                                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                                                                        i8 = i2;
                                                                        th5 = th2;
                                                                        str58 = str42;
                                                                        str59 = str41;
                                                                        r21 = r4;
                                                                        str60 = str7;
                                                                        defaultSessionRepository21 = defaultSessionRepository2;
                                                                        MessageDao messageDao11 = defaultSessionRepository21.messages;
                                                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                                        defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                                        defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                                        defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                                        defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                                        defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                                                        defaultSessionRepository$deliverPending$2.label = 12;
                                                                        byId = messageDao11.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                                        r25 = r21;
                                                                        str64 = str60;
                                                                        defaultSessionRepository26 = defaultSessionRepository21;
                                                                        if (byId == coroutine_suspended) {
                                                                            return coroutine_suspended;
                                                                        }
                                                                        messageEntity = (MessageEntity) byId;
                                                                        if (messageEntity != null) {
                                                                            deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                                        } else {
                                                                            deliveryStatus4 = null;
                                                                        }
                                                                        if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                                            Result.Companion companion26 = Result.Companion;
                                                                            Object objM7388constructorimpl8 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                                            defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                                            return objM7388constructorimpl8;
                                                                        }
                                                                        th2 = th5;
                                                                        r16 = r25;
                                                                        str47 = str64;
                                                                        defaultSessionRepository25 = defaultSessionRepository26;
                                                                    } catch (Throwable th20) {
                                                                    }
                                                                } catch (Throwable th21) {
                                                                    str41 = str43;
                                                                    r4 = r15;
                                                                    th2 = th3;
                                                                    str8 = str29;
                                                                    defaultSessionRepository2 = defaultSessionRepository17;
                                                                    str42 = str44;
                                                                    str7 = str46;
                                                                }
                                                            } catch (Throwable th22) {
                                                                str41 = str39;
                                                                r4 = r14;
                                                                str7 = str40;
                                                                str8 = str29;
                                                                str42 = str21;
                                                            }
                                                            break;
                                                        } else {
                                                            r16 = r14;
                                                            str47 = str40;
                                                            str8 = str29;
                                                            defaultSessionRepository25 = defaultSessionRepository2;
                                                        }
                                                        Result.Companion companion27 = Result.Companion;
                                                        objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                                        r24 = r16;
                                                        str63 = str47;
                                                        defaultSessionRepository24 = defaultSessionRepository25;
                                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                                        defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                                        return objM7388constructorimpl;
                                                    } catch (Throwable th23) {
                                                        r4 = r14;
                                                        str7 = str40;
                                                        str8 = str29;
                                                    }
                                                } catch (Throwable th24) {
                                                    str29 = str8;
                                                    i2 = 0;
                                                    str30 = str7;
                                                    r8 = r4;
                                                    str31 = strClassifyTransportFailure$default;
                                                    defaultSessionRepository14 = defaultSessionRepository8;
                                                    Result.Companion companion110 = Result.Companion;
                                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th24));
                                                    String str610 = str22;
                                                    defaultSessionRepository2 = defaultSessionRepository14;
                                                    str39 = str610;
                                                    r14 = r8;
                                                    str40 = str30;
                                                    if (Intrinsics.areEqual(str31, OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                                                        Result.Companion companion111 = Result.Companion;
                                                        defaultSessionRepository15 = defaultSessionRepository2;
                                                        i3 = 0;
                                                        GatewayClient gatewayClient3 = defaultSessionRepository15.gatewayClient;
                                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                                        defaultSessionRepository$deliverPending$2.L$1 = str23;
                                                        defaultSessionRepository$deliverPending$2.L$2 = str29;
                                                        defaultSessionRepository$deliverPending$2.L$3 = str39;
                                                        defaultSessionRepository$deliverPending$2.L$4 = str40;
                                                        defaultSessionRepository$deliverPending$2.L$5 = str21;
                                                        defaultSessionRepository$deliverPending$2.L$6 = r14;
                                                        defaultSessionRepository$deliverPending$2.L$7 = th2;
                                                        defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository15;
                                                        defaultSessionRepository$deliverPending$2.label = 9;
                                                        objM43getMessagesgIAlus = gatewayClient3.m43getMessagesgIAlus(str23, defaultSessionRepository$deliverPending$2);
                                                        if (objM43getMessagesgIAlus == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                        str43 = str39;
                                                        str44 = str21;
                                                        defaultSessionRepository16 = defaultSessionRepository15;
                                                        DefaultSessionRepository defaultSessionRepository29 = defaultSessionRepository2;
                                                        value3 = objM43getMessagesgIAlus;
                                                        str45 = str23;
                                                        defaultSessionRepository17 = defaultSessionRepository29;
                                                        String str611 = str40;
                                                        th3 = th2;
                                                        r15 = r14;
                                                        str46 = str611;
                                                        if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                                            i4 = 0;
                                                            OutboxExactlyOnce outboxExactlyOnce3 = OutboxExactlyOnce.INSTANCE;
                                                            Function1 function2 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                                                @Override // kotlin.jvm.functions.Function1
                                                                public final Object invoke(Object obj3) {
                                                                    return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                                                }
                                                            };
                                                            auroraMessage5 = (AuroraMessage) outboxExactlyOnce3.findRemoteContentMatch((List) value3, str43, function2, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                                                @Override // kotlin.jvm.functions.Function1
                                                                public final Object invoke(Object obj3) {
                                                                    return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                                                }
                                                            }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                                                @Override // kotlin.jvm.functions.Function1
                                                                public final Object invoke(Object obj3) {
                                                                    return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function2, obj3));
                                                                }
                                                            } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                                                @Override // kotlin.jvm.functions.Function1
                                                                public final Object invoke(Object obj3) {
                                                                    return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                                                }
                                                            });
                                                            i5 = i3;
                                                            OutboxExactlyOnce outboxExactlyOnce4 = OutboxExactlyOnce.INSTANCE;
                                                            if (auroraMessage5 != null) {
                                                                z = true;
                                                            } else {
                                                                z = false;
                                                            }
                                                            strReconcileAmbiguousAgainstRemote = outboxExactlyOnce4.reconcileAmbiguousAgainstRemote(true, z);
                                                            if (strReconcileAmbiguousAgainstRemote != null) {
                                                                messageDao2 = defaultSessionRepository16.messages;
                                                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                                                defaultSessionRepository$deliverPending$2.L$1 = str45;
                                                                defaultSessionRepository$deliverPending$2.L$2 = str29;
                                                                defaultSessionRepository$deliverPending$2.L$3 = str43;
                                                                defaultSessionRepository$deliverPending$2.L$4 = str46;
                                                                defaultSessionRepository$deliverPending$2.L$5 = str44;
                                                                defaultSessionRepository$deliverPending$2.L$6 = r15;
                                                                defaultSessionRepository$deliverPending$2.L$7 = th3;
                                                                defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                                                defaultSessionRepository$deliverPending$2.L$9 = value3;
                                                                defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                                                defaultSessionRepository$deliverPending$2.label = 10;
                                                                if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                                                    return coroutine_suspended;
                                                                }
                                                                defaultSessionRepository19 = defaultSessionRepository17;
                                                                str48 = str46;
                                                                obj = value3;
                                                                r17 = r15;
                                                                str49 = strReconcileAmbiguousAgainstRemote;
                                                                str50 = str29;
                                                                str42 = str44;
                                                                str51 = str43;
                                                                i6 = i5;
                                                                if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                                                    Result.Companion companion112 = Result.Companion;
                                                                    DefaultSessionRepository defaultSessionRepository210 = defaultSessionRepository16;
                                                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                                                    defaultSessionRepository$deliverPending$2.L$1 = str50;
                                                                    defaultSessionRepository$deliverPending$2.L$2 = str51;
                                                                    defaultSessionRepository$deliverPending$2.L$3 = str48;
                                                                    defaultSessionRepository$deliverPending$2.L$4 = str42;
                                                                    defaultSessionRepository$deliverPending$2.L$5 = r17;
                                                                    defaultSessionRepository$deliverPending$2.L$6 = th3;
                                                                    defaultSessionRepository$deliverPending$2.L$7 = obj;
                                                                    str56 = str51;
                                                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                                                    defaultSessionRepository$deliverPending$2.label = 11;
                                                                    objMo55selectSessiongIAlus2 = defaultSessionRepository210.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                                                    if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                                        return coroutine_suspended;
                                                                    }
                                                                    th4 = th3;
                                                                    r18 = r17;
                                                                    str52 = str42;
                                                                    str53 = str48;
                                                                    str54 = str56;
                                                                    i7 = i4;
                                                                    str55 = str50;
                                                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                                                    r23 = r18;
                                                                    defaultSessionRepository23 = defaultSessionRepository19;
                                                                    str62 = str53;
                                                                    str44 = str52;
                                                                    str43 = str54;
                                                                    value3 = obj;
                                                                    r20 = r23;
                                                                    defaultSessionRepository20 = defaultSessionRepository23;
                                                                    str57 = str62;
                                                                    str29 = str55;
                                                                    th3 = th4;
                                                                } else {
                                                                    String str71 = str51;
                                                                    r20 = r17;
                                                                    str44 = str42;
                                                                    str29 = str50;
                                                                    value3 = obj;
                                                                    str57 = str48;
                                                                    str43 = str71;
                                                                    defaultSessionRepository20 = defaultSessionRepository19;
                                                                }
                                                            } else {
                                                                r20 = r15;
                                                                str57 = str46;
                                                                defaultSessionRepository20 = defaultSessionRepository17;
                                                            }
                                                            str41 = str43;
                                                            r22 = r20;
                                                            str61 = str57;
                                                            defaultSessionRepository22 = defaultSessionRepository20;
                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                                            i8 = i2;
                                                            r21 = r22;
                                                            th5 = th3;
                                                            str8 = str29;
                                                            defaultSessionRepository21 = defaultSessionRepository22;
                                                            str59 = str41;
                                                            str58 = str44;
                                                            str60 = str61;
                                                            MessageDao messageDao12 = defaultSessionRepository21.messages;
                                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                            defaultSessionRepository$deliverPending$2.L$7 = null;
                                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                                            defaultSessionRepository$deliverPending$2.L$9 = null;
                                                            defaultSessionRepository$deliverPending$2.L$10 = null;
                                                            defaultSessionRepository$deliverPending$2.label = 12;
                                                            byId = messageDao12.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                            r25 = r21;
                                                            str64 = str60;
                                                            defaultSessionRepository26 = defaultSessionRepository21;
                                                            if (byId == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                            messageEntity = (MessageEntity) byId;
                                                            if (messageEntity != null) {
                                                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                            } else {
                                                                deliveryStatus4 = null;
                                                            }
                                                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                                Result.Companion companion28 = Result.Companion;
                                                                Object objM7388constructorimpl9 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                                return objM7388constructorimpl9;
                                                            }
                                                            th2 = th5;
                                                            r16 = r25;
                                                            str47 = str64;
                                                            defaultSessionRepository25 = defaultSessionRepository26;
                                                        } else {
                                                            str41 = str43;
                                                            r22 = r15;
                                                            str61 = str46;
                                                            defaultSessionRepository22 = defaultSessionRepository17;
                                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                                            i8 = i2;
                                                            r21 = r22;
                                                            th5 = th3;
                                                            str8 = str29;
                                                            defaultSessionRepository21 = defaultSessionRepository22;
                                                            str59 = str41;
                                                            str58 = str44;
                                                            str60 = str61;
                                                            MessageDao messageDao13 = defaultSessionRepository21.messages;
                                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                            defaultSessionRepository$deliverPending$2.L$7 = null;
                                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                                            defaultSessionRepository$deliverPending$2.L$9 = null;
                                                            defaultSessionRepository$deliverPending$2.L$10 = null;
                                                            defaultSessionRepository$deliverPending$2.label = 12;
                                                            byId = messageDao13.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                            r25 = r21;
                                                            str64 = str60;
                                                            defaultSessionRepository26 = defaultSessionRepository21;
                                                            if (byId == coroutine_suspended) {
                                                                return coroutine_suspended;
                                                            }
                                                            messageEntity = (MessageEntity) byId;
                                                            if (messageEntity != null) {
                                                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                            } else {
                                                                deliveryStatus4 = null;
                                                            }
                                                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                                Result.Companion companion29 = Result.Companion;
                                                                Object objM7388constructorimpl10 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                                return objM7388constructorimpl10;
                                                            }
                                                            th2 = th5;
                                                            r16 = r25;
                                                            str47 = str64;
                                                            defaultSessionRepository25 = defaultSessionRepository26;
                                                        }
                                                        Result.Companion companion210 = Result.Companion;
                                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th24));
                                                        i8 = i2;
                                                        th5 = th2;
                                                        str58 = str42;
                                                        str59 = str41;
                                                        r21 = r4;
                                                        str60 = str7;
                                                        defaultSessionRepository21 = defaultSessionRepository2;
                                                        MessageDao messageDao14 = defaultSessionRepository21.messages;
                                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                                        defaultSessionRepository$deliverPending$2.L$2 = str59;
                                                        defaultSessionRepository$deliverPending$2.L$3 = str60;
                                                        defaultSessionRepository$deliverPending$2.L$4 = str58;
                                                        defaultSessionRepository$deliverPending$2.L$5 = r21;
                                                        defaultSessionRepository$deliverPending$2.L$6 = th5;
                                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                                        defaultSessionRepository$deliverPending$2.label = 12;
                                                        byId = messageDao14.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                                        r25 = r21;
                                                        str64 = str60;
                                                        defaultSessionRepository26 = defaultSessionRepository21;
                                                        if (byId == coroutine_suspended) {
                                                            return coroutine_suspended;
                                                        }
                                                        messageEntity = (MessageEntity) byId;
                                                        if (messageEntity != null) {
                                                            deliveryStatus4 = messageEntity.getDeliveryStatus();
                                                        } else {
                                                            deliveryStatus4 = null;
                                                        }
                                                        if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                                            Result.Companion companion211 = Result.Companion;
                                                            Object objM7388constructorimpl11 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                                            defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                                            return objM7388constructorimpl11;
                                                        }
                                                        th2 = th5;
                                                        r16 = r25;
                                                        str47 = str64;
                                                        defaultSessionRepository25 = defaultSessionRepository26;
                                                    } else {
                                                        r16 = r14;
                                                        str47 = str40;
                                                        str8 = str29;
                                                        defaultSessionRepository25 = defaultSessionRepository2;
                                                    }
                                                    Result.Companion companion212 = Result.Companion;
                                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                                    r24 = r16;
                                                    str63 = str47;
                                                    defaultSessionRepository24 = defaultSessionRepository25;
                                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                                    defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                                    return objM7388constructorimpl;
                                                }
                                            }
                                        } catch (Throwable th25) {
                                            defaultSessionRepository2 = defaultSessionRepository8;
                                        }
                                    } catch (Throwable th26) {
                                        defaultSessionRepository2 = defaultSessionRepository6;
                                        str8 = str18;
                                        str7 = str20;
                                        r4 = r19;
                                    }
                                } catch (Throwable th27) {
                                    String str72 = str11;
                                    Object r110 = r9;
                                    defaultSessionRepository2 = defaultSessionRepository3;
                                    str8 = str13;
                                    str7 = str72;
                                    r4 = r110;
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r4, null, 1, null);
                                defaultSessionRepository2.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str7, str8), r4);
                                throw th27;
                                }
                            } catch (Throwable th28) {
                                str8 = str2;
                                r4 = mutexLockFor;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure(obj2);
                            objUpdateStatusIf = obj2;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) objUpdateStatusIf).intValue()));
                            Result.Companion companion30 = Result.Companion;
                            return ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Message already delivering")));
                        case 2:
                            Ref.BooleanRef booleanRef3 = (Ref.BooleanRef) defaultSessionRepository$deliverPending$2.L$8;
                            r4 = (Mutex) defaultSessionRepository$deliverPending$2.L$7;
                            String str73 = (String) defaultSessionRepository$deliverPending$2.L$6;
                            str7 = (String) defaultSessionRepository$deliverPending$2.L$5;
                            String str74 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            str9 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str8 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            String str75 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository30 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                str10 = str73;
                                defaultSessionRepository3 = defaultSessionRepository30;
                                booleanRef = booleanRef3;
                                objMarkSending = obj2;
                                r9 = r4;
                                str14 = str75;
                                str12 = str74;
                                str13 = str8;
                                str11 = str7;
                                if (!((Boolean) objMarkSending).booleanValue()) {
                                    Result.Companion companion31 = Result.Companion;
                                    MessageDao messageDao15 = defaultSessionRepository3.messages;
                                    String strStatusAfterPreNetworkRejection3 = OutboxExactlyOnce.INSTANCE.statusAfterPreNetworkRejection();
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository3;
                                    defaultSessionRepository$deliverPending$2.L$1 = str13;
                                    defaultSessionRepository$deliverPending$2.L$2 = str11;
                                    defaultSessionRepository$deliverPending$2.L$3 = r9;
                                    defaultSessionRepository$deliverPending$2.L$4 = null;
                                    defaultSessionRepository$deliverPending$2.L$5 = null;
                                    defaultSessionRepository$deliverPending$2.L$6 = null;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.label = 3;
                                    deliveryStatus = messageDao15.setDeliveryStatus(str13, str11, strStatusAfterPreNetworkRejection3, defaultSessionRepository$deliverPending$2);
                                    if (deliveryStatus == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    defaultSessionRepository5 = defaultSessionRepository3;
                                    r3 = r9;
                                    str25 = str11;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus).intValue()));
                                    r6 = r3;
                                    defaultSessionRepository7 = defaultSessionRepository5;
                                    str24 = str25;
                                    str16 = str13;
                                    Result.Companion companion32 = Result.Companion;
                                    Object objM7388constructorimpl12 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Lost delivery claim before network")));
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r6, null, 1, null);
                                    defaultSessionRepository7.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str24, str16), r6);
                                    return objM7388constructorimpl12;
                                }
                                booleanRef.element = true;
                                GatewayClient gatewayClient4 = defaultSessionRepository3.gatewayClient;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository3;
                                defaultSessionRepository$deliverPending$2.L$1 = str14;
                                defaultSessionRepository$deliverPending$2.L$2 = str13;
                                defaultSessionRepository$deliverPending$2.L$3 = str9;
                                defaultSessionRepository$deliverPending$2.L$4 = str11;
                                defaultSessionRepository$deliverPending$2.L$5 = str10;
                                defaultSessionRepository$deliverPending$2.L$6 = r9;
                                defaultSessionRepository$deliverPending$2.L$7 = booleanRef;
                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                defaultSessionRepository$deliverPending$2.label = 4;
                                defaultSessionRepository6 = defaultSessionRepository3;
                                str17 = str14;
                                str18 = str13;
                                str19 = str9;
                                str20 = str11;
                                r19 = r9;
                                objM46sendMessagehUnOzRk = gatewayClient4.m46sendMessagehUnOzRk(str14, str9, str12, str10, str11, defaultSessionRepository$deliverPending$2);
                                if (objM46sendMessagehUnOzRk == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str21 = str10;
                                str8 = str18;
                                str22 = str19;
                                str7 = str20;
                                r4 = r19;
                                str23 = str17;
                                booleanRef2 = booleanRef;
                                value = objM46sendMessagehUnOzRk;
                                defaultSessionRepository8 = defaultSessionRepository6;
                                thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                                if (thM7391exceptionOrNullimpl == null) {
                                    auroraMessage = (AuroraMessage) value;
                                    Result.Companion companion33 = Result.Companion;
                                    MessageDao messageDao16 = defaultSessionRepository8.messages;
                                    String strStatusAfterNetworkAck2 = OutboxExactlyOnce.INSTANCE.statusAfterNetworkAck();
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository8;
                                    defaultSessionRepository$deliverPending$2.L$1 = str23;
                                    defaultSessionRepository$deliverPending$2.L$2 = str8;
                                    defaultSessionRepository$deliverPending$2.L$3 = str7;
                                    defaultSessionRepository$deliverPending$2.L$4 = str21;
                                    defaultSessionRepository$deliverPending$2.L$5 = r4;
                                    defaultSessionRepository$deliverPending$2.L$6 = auroraMessage;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.label = 5;
                                    deliveryStatus2 = messageDao16.setDeliveryStatus(str8, str7, strStatusAfterNetworkAck2, defaultSessionRepository$deliverPending$2);
                                    if (deliveryStatus2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    defaultSessionRepository10 = defaultSessionRepository8;
                                    str26 = str23;
                                    Object r28 = r4;
                                    auroraMessage3 = auroraMessage;
                                    r7 = r28;
                                    String str612 = str21;
                                    str27 = str7;
                                    str28 = str612;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus2).intValue()));
                                    auroraMessage2 = auroraMessage3;
                                    r12 = r7;
                                    str23 = str26;
                                    defaultSessionRepository11 = defaultSessionRepository10;
                                    String str613 = str27;
                                    str21 = str28;
                                    str34 = str613;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository11;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str34;
                                    defaultSessionRepository$deliverPending$2.L$3 = str21;
                                    defaultSessionRepository$deliverPending$2.L$4 = r12;
                                    defaultSessionRepository$deliverPending$2.L$5 = auroraMessage2;
                                    defaultSessionRepository$deliverPending$2.L$6 = null;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.label = 6;
                                    objMo55selectSessiongIAlus = defaultSessionRepository11.mo55selectSessiongIAlus(str23, defaultSessionRepository$deliverPending$2);
                                    if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    str35 = str34;
                                    str36 = str21;
                                    value2 = objMo55selectSessiongIAlus;
                                    r1 = r12;
                                    auroraMessage4 = auroraMessage2;
                                    defaultSessionRepository12 = defaultSessionRepository11;
                                    if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(value2)) {
                                        Result.Companion companion34 = Result.Companion;
                                        Object objM7388constructorimpl13 = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r1, null, 1, null);
                                        defaultSessionRepository12.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str35, str8), r1);
                                        return objM7388constructorimpl13;
                                    }
                                    Result.Companion companion113 = Result.Companion;
                                    messageDao = defaultSessionRepository12.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository12;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str35;
                                    defaultSessionRepository$deliverPending$2.L$3 = str36;
                                    defaultSessionRepository$deliverPending$2.L$4 = r1;
                                    defaultSessionRepository$deliverPending$2.L$5 = auroraMessage4;
                                    defaultSessionRepository$deliverPending$2.label = 7;
                                    if (messageDao.deleteById(str8, str35, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    str37 = str35;
                                    r13 = r1;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                                    r2 = r13;
                                    str38 = str37;
                                    defaultSessionRepository13 = defaultSessionRepository12;
                                    Result.Companion companion114 = Result.Companion;
                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                    r24 = r2;
                                    str63 = str38;
                                    defaultSessionRepository24 = defaultSessionRepository13;
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                    defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                    return objM7388constructorimpl;
                                }
                                th2 = thM7391exceptionOrNullimpl;
                                i = 0;
                                strClassifyTransportFailure$default = OutboxExactlyOnce.classifyTransportFailure$default(OutboxExactlyOnce.INSTANCE, th2.getMessage(), booleanRef2.element, null, 4, null);
                                Result.Companion companion115 = Result.Companion;
                                MessageDao messageDao17 = defaultSessionRepository8.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository8;
                                defaultSessionRepository$deliverPending$2.L$1 = str23;
                                defaultSessionRepository$deliverPending$2.L$2 = str8;
                                defaultSessionRepository$deliverPending$2.L$3 = str22;
                                defaultSessionRepository$deliverPending$2.L$4 = str7;
                                defaultSessionRepository$deliverPending$2.L$5 = str21;
                                defaultSessionRepository$deliverPending$2.L$6 = r4;
                                defaultSessionRepository$deliverPending$2.L$7 = th2;
                                defaultSessionRepository$deliverPending$2.L$8 = strClassifyTransportFailure$default;
                                defaultSessionRepository$deliverPending$2.label = 8;
                                deliveryStatus3 = messageDao17.setDeliveryStatus(str8, str7, strClassifyTransportFailure$default, defaultSessionRepository$deliverPending$2);
                                if (deliveryStatus3 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str29 = str8;
                                str32 = str7;
                                r10 = r4;
                                str31 = strClassifyTransportFailure$default;
                                defaultSessionRepository18 = defaultSessionRepository8;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus3).intValue()));
                                i2 = i;
                                String str614 = str22;
                                defaultSessionRepository2 = defaultSessionRepository18;
                                str39 = str614;
                                r14 = r10;
                                str40 = str32;
                                if (Intrinsics.areEqual(str31, OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                                    Result.Companion companion116 = Result.Companion;
                                    defaultSessionRepository15 = defaultSessionRepository2;
                                    i3 = 0;
                                    GatewayClient gatewayClient5 = defaultSessionRepository15.gatewayClient;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                    defaultSessionRepository$deliverPending$2.L$1 = str23;
                                    defaultSessionRepository$deliverPending$2.L$2 = str29;
                                    defaultSessionRepository$deliverPending$2.L$3 = str39;
                                    defaultSessionRepository$deliverPending$2.L$4 = str40;
                                    defaultSessionRepository$deliverPending$2.L$5 = str21;
                                    defaultSessionRepository$deliverPending$2.L$6 = r14;
                                    defaultSessionRepository$deliverPending$2.L$7 = th2;
                                    defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository15;
                                    defaultSessionRepository$deliverPending$2.label = 9;
                                    objM43getMessagesgIAlus = gatewayClient5.m43getMessagesgIAlus(str23, defaultSessionRepository$deliverPending$2);
                                    if (objM43getMessagesgIAlus == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    str43 = str39;
                                    str44 = str21;
                                    defaultSessionRepository16 = defaultSessionRepository15;
                                    DefaultSessionRepository defaultSessionRepository211 = defaultSessionRepository2;
                                    value3 = objM43getMessagesgIAlus;
                                    str45 = str23;
                                    defaultSessionRepository17 = defaultSessionRepository211;
                                    String str615 = str40;
                                    th3 = th2;
                                    r15 = r14;
                                    str46 = str615;
                                    if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                        i4 = 0;
                                        OutboxExactlyOnce outboxExactlyOnce5 = OutboxExactlyOnce.INSTANCE;
                                        Function1 function3 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                            }
                                        };
                                        auroraMessage5 = (AuroraMessage) outboxExactlyOnce5.findRemoteContentMatch((List) value3, str43, function3, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                            }
                                        }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function3, obj3));
                                            }
                                        } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                            }
                                        });
                                        i5 = i3;
                                        OutboxExactlyOnce outboxExactlyOnce6 = OutboxExactlyOnce.INSTANCE;
                                        if (auroraMessage5 != null) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                        strReconcileAmbiguousAgainstRemote = outboxExactlyOnce6.reconcileAmbiguousAgainstRemote(true, z);
                                        if (strReconcileAmbiguousAgainstRemote != null) {
                                            messageDao2 = defaultSessionRepository16.messages;
                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                            defaultSessionRepository$deliverPending$2.L$1 = str45;
                                            defaultSessionRepository$deliverPending$2.L$2 = str29;
                                            defaultSessionRepository$deliverPending$2.L$3 = str43;
                                            defaultSessionRepository$deliverPending$2.L$4 = str46;
                                            defaultSessionRepository$deliverPending$2.L$5 = str44;
                                            defaultSessionRepository$deliverPending$2.L$6 = r15;
                                            defaultSessionRepository$deliverPending$2.L$7 = th3;
                                            defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                            defaultSessionRepository$deliverPending$2.L$9 = value3;
                                            defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                            defaultSessionRepository$deliverPending$2.label = 10;
                                            if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            defaultSessionRepository19 = defaultSessionRepository17;
                                            str48 = str46;
                                            obj = value3;
                                            r17 = r15;
                                            str49 = strReconcileAmbiguousAgainstRemote;
                                            str50 = str29;
                                            str42 = str44;
                                            str51 = str43;
                                            i6 = i5;
                                            if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                                Result.Companion companion117 = Result.Companion;
                                                DefaultSessionRepository defaultSessionRepository212 = defaultSessionRepository16;
                                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                                defaultSessionRepository$deliverPending$2.L$1 = str50;
                                                defaultSessionRepository$deliverPending$2.L$2 = str51;
                                                defaultSessionRepository$deliverPending$2.L$3 = str48;
                                                defaultSessionRepository$deliverPending$2.L$4 = str42;
                                                defaultSessionRepository$deliverPending$2.L$5 = r17;
                                                defaultSessionRepository$deliverPending$2.L$6 = th3;
                                                defaultSessionRepository$deliverPending$2.L$7 = obj;
                                                str56 = str51;
                                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                                defaultSessionRepository$deliverPending$2.L$9 = null;
                                                defaultSessionRepository$deliverPending$2.L$10 = null;
                                                defaultSessionRepository$deliverPending$2.label = 11;
                                                objMo55selectSessiongIAlus2 = defaultSessionRepository212.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                                if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                th4 = th3;
                                                r18 = r17;
                                                str52 = str42;
                                                str53 = str48;
                                                str54 = str56;
                                                i7 = i4;
                                                str55 = str50;
                                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                                r23 = r18;
                                                defaultSessionRepository23 = defaultSessionRepository19;
                                                str62 = str53;
                                                str44 = str52;
                                                str43 = str54;
                                                value3 = obj;
                                                r20 = r23;
                                                defaultSessionRepository20 = defaultSessionRepository23;
                                                str57 = str62;
                                                str29 = str55;
                                                th3 = th4;
                                            } else {
                                                String str76 = str51;
                                                r20 = r17;
                                                str44 = str42;
                                                str29 = str50;
                                                value3 = obj;
                                                str57 = str48;
                                                str43 = str76;
                                                defaultSessionRepository20 = defaultSessionRepository19;
                                            }
                                        } else {
                                            r20 = r15;
                                            str57 = str46;
                                            defaultSessionRepository20 = defaultSessionRepository17;
                                        }
                                        str41 = str43;
                                        r22 = r20;
                                        str61 = str57;
                                        defaultSessionRepository22 = defaultSessionRepository20;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                        i8 = i2;
                                        r21 = r22;
                                        th5 = th3;
                                        str8 = str29;
                                        defaultSessionRepository21 = defaultSessionRepository22;
                                        str59 = str41;
                                        str58 = str44;
                                        str60 = str61;
                                        MessageDao messageDao18 = defaultSessionRepository21.messages;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                        defaultSessionRepository$deliverPending$2.L$2 = str59;
                                        defaultSessionRepository$deliverPending$2.L$3 = str60;
                                        defaultSessionRepository$deliverPending$2.L$4 = str58;
                                        defaultSessionRepository$deliverPending$2.L$5 = r21;
                                        defaultSessionRepository$deliverPending$2.L$6 = th5;
                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                        defaultSessionRepository$deliverPending$2.label = 12;
                                        byId = messageDao18.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                        r25 = r21;
                                        str64 = str60;
                                        defaultSessionRepository26 = defaultSessionRepository21;
                                        if (byId == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        messageEntity = (MessageEntity) byId;
                                        if (messageEntity != null) {
                                            deliveryStatus4 = messageEntity.getDeliveryStatus();
                                        } else {
                                            deliveryStatus4 = null;
                                        }
                                        if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                            Result.Companion companion213 = Result.Companion;
                                            Object objM7388constructorimpl14 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                            defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                            return objM7388constructorimpl14;
                                        }
                                        th2 = th5;
                                        r16 = r25;
                                        str47 = str64;
                                        defaultSessionRepository25 = defaultSessionRepository26;
                                    } else {
                                        str41 = str43;
                                        r22 = r15;
                                        str61 = str46;
                                        defaultSessionRepository22 = defaultSessionRepository17;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                        i8 = i2;
                                        r21 = r22;
                                        th5 = th3;
                                        str8 = str29;
                                        defaultSessionRepository21 = defaultSessionRepository22;
                                        str59 = str41;
                                        str58 = str44;
                                        str60 = str61;
                                        MessageDao messageDao19 = defaultSessionRepository21.messages;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                        defaultSessionRepository$deliverPending$2.L$2 = str59;
                                        defaultSessionRepository$deliverPending$2.L$3 = str60;
                                        defaultSessionRepository$deliverPending$2.L$4 = str58;
                                        defaultSessionRepository$deliverPending$2.L$5 = r21;
                                        defaultSessionRepository$deliverPending$2.L$6 = th5;
                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                        defaultSessionRepository$deliverPending$2.label = 12;
                                        byId = messageDao19.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                        r25 = r21;
                                        str64 = str60;
                                        defaultSessionRepository26 = defaultSessionRepository21;
                                        if (byId == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        messageEntity = (MessageEntity) byId;
                                        if (messageEntity != null) {
                                            deliveryStatus4 = messageEntity.getDeliveryStatus();
                                        } else {
                                            deliveryStatus4 = null;
                                        }
                                        if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                            Result.Companion companion214 = Result.Companion;
                                            Object objM7388constructorimpl15 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                            defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                            return objM7388constructorimpl15;
                                        }
                                        th2 = th5;
                                        r16 = r25;
                                        str47 = str64;
                                        defaultSessionRepository25 = defaultSessionRepository26;
                                    }
                                    Result.Companion companion215 = Result.Companion;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                                    i8 = i2;
                                    th5 = th2;
                                    str58 = str42;
                                    str59 = str41;
                                    r21 = r4;
                                    str60 = str7;
                                    defaultSessionRepository21 = defaultSessionRepository2;
                                    MessageDao messageDao110 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao110.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion216 = Result.Companion;
                                        Object objM7388constructorimpl16 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl16;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                } else {
                                    r16 = r14;
                                    str47 = str40;
                                    str8 = str29;
                                    defaultSessionRepository25 = defaultSessionRepository2;
                                }
                                Result.Companion companion217 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                r24 = r16;
                                str63 = str47;
                                defaultSessionRepository24 = defaultSessionRepository25;
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                return objM7388constructorimpl;
                            } catch (Throwable th29) {
                                defaultSessionRepository2 = defaultSessionRepository30;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r4, null, 1, null);
                            defaultSessionRepository2.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str7, str8), r4);
                            throw th29;
                            }
                        case 3:
                            Mutex mutex = (Mutex) defaultSessionRepository$deliverPending$2.L$3;
                            String str77 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str13 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository31 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                str25 = str77;
                                deliveryStatus = obj2;
                                r3 = mutex;
                                defaultSessionRepository5 = defaultSessionRepository31;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus).intValue()));
                                r6 = r3;
                                defaultSessionRepository7 = defaultSessionRepository5;
                                str24 = str25;
                                str16 = str13;
                            } catch (Throwable th30) {
                                str16 = str13;
                                defaultSessionRepository4 = defaultSessionRepository31;
                                str15 = str77;
                                r5 = mutex;
                                Result.Companion companion35 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th30));
                                r6 = r5;
                                str24 = str15;
                                defaultSessionRepository7 = defaultSessionRepository4;
                                Result.Companion companion36 = Result.Companion;
                                Object objM7388constructorimpl17 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Lost delivery claim before network")));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r6, null, 1, null);
                                defaultSessionRepository7.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str24, str16), r6);
                                return objM7388constructorimpl17;
                            }
                            Result.Companion companion37 = Result.Companion;
                            Object objM7388constructorimpl18 = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(new IllegalStateException("Lost delivery claim before network")));
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r6, null, 1, null);
                            defaultSessionRepository7.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str24, str16), r6);
                            return objM7388constructorimpl18;
                        case 4:
                            booleanRef2 = (Ref.BooleanRef) defaultSessionRepository$deliverPending$2.L$7;
                            Mutex mutex2 = (Mutex) defaultSessionRepository$deliverPending$2.L$6;
                            String str78 = (String) defaultSessionRepository$deliverPending$2.L$5;
                            String str79 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            String str80 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str8 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            String str81 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository32 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            ResultKt.throwOnFailure(obj2);
                            value = ai.nous.aurora.compat.Compat.resultUnbox(obj2);
                            str23 = str81;
                            defaultSessionRepository8 = defaultSessionRepository32;
                            str21 = str78;
                            str22 = str80;
                            r4 = mutex2;
                            str7 = str79;
                            thM7391exceptionOrNullimpl = ai.nous.aurora.compat.Compat.m7391exceptionOrNullimpl(value);
                            if (thM7391exceptionOrNullimpl == null) {
                                auroraMessage = (AuroraMessage) value;
                                Result.Companion companion38 = Result.Companion;
                                MessageDao messageDao111 = defaultSessionRepository8.messages;
                                String strStatusAfterNetworkAck3 = OutboxExactlyOnce.INSTANCE.statusAfterNetworkAck();
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository8;
                                defaultSessionRepository$deliverPending$2.L$1 = str23;
                                defaultSessionRepository$deliverPending$2.L$2 = str8;
                                defaultSessionRepository$deliverPending$2.L$3 = str7;
                                defaultSessionRepository$deliverPending$2.L$4 = str21;
                                defaultSessionRepository$deliverPending$2.L$5 = r4;
                                defaultSessionRepository$deliverPending$2.L$6 = auroraMessage;
                                defaultSessionRepository$deliverPending$2.L$7 = null;
                                defaultSessionRepository$deliverPending$2.label = 5;
                                deliveryStatus2 = messageDao111.setDeliveryStatus(str8, str7, strStatusAfterNetworkAck3, defaultSessionRepository$deliverPending$2);
                                if (deliveryStatus2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                defaultSessionRepository10 = defaultSessionRepository8;
                                str26 = str23;
                                Object r29 = r4;
                                auroraMessage3 = auroraMessage;
                                r7 = r29;
                                String str616 = str21;
                                str27 = str7;
                                str28 = str616;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus2).intValue()));
                                auroraMessage2 = auroraMessage3;
                                r12 = r7;
                                str23 = str26;
                                defaultSessionRepository11 = defaultSessionRepository10;
                                String str617 = str27;
                                str21 = str28;
                                str34 = str617;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository11;
                                defaultSessionRepository$deliverPending$2.L$1 = str8;
                                defaultSessionRepository$deliverPending$2.L$2 = str34;
                                defaultSessionRepository$deliverPending$2.L$3 = str21;
                                defaultSessionRepository$deliverPending$2.L$4 = r12;
                                defaultSessionRepository$deliverPending$2.L$5 = auroraMessage2;
                                defaultSessionRepository$deliverPending$2.L$6 = null;
                                defaultSessionRepository$deliverPending$2.L$7 = null;
                                defaultSessionRepository$deliverPending$2.label = 6;
                                objMo55selectSessiongIAlus = defaultSessionRepository11.mo55selectSessiongIAlus(str23, defaultSessionRepository$deliverPending$2);
                                if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str35 = str34;
                                str36 = str21;
                                value2 = objMo55selectSessiongIAlus;
                                r1 = r12;
                                auroraMessage4 = auroraMessage2;
                                defaultSessionRepository12 = defaultSessionRepository11;
                                if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(value2)) {
                                    Result.Companion companion39 = Result.Companion;
                                    Object objM7388constructorimpl19 = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r1, null, 1, null);
                                    defaultSessionRepository12.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str35, str8), r1);
                                    return objM7388constructorimpl19;
                                }
                                Result.Companion companion118 = Result.Companion;
                                messageDao = defaultSessionRepository12.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository12;
                                defaultSessionRepository$deliverPending$2.L$1 = str8;
                                defaultSessionRepository$deliverPending$2.L$2 = str35;
                                defaultSessionRepository$deliverPending$2.L$3 = str36;
                                defaultSessionRepository$deliverPending$2.L$4 = r1;
                                defaultSessionRepository$deliverPending$2.L$5 = auroraMessage4;
                                defaultSessionRepository$deliverPending$2.label = 7;
                                if (messageDao.deleteById(str8, str35, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str37 = str35;
                                r13 = r1;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                                r2 = r13;
                                str38 = str37;
                                defaultSessionRepository13 = defaultSessionRepository12;
                                Result.Companion companion119 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                r24 = r2;
                                str63 = str38;
                                defaultSessionRepository24 = defaultSessionRepository13;
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                return objM7388constructorimpl;
                            }
                            th2 = thM7391exceptionOrNullimpl;
                            i = 0;
                            strClassifyTransportFailure$default = OutboxExactlyOnce.classifyTransportFailure$default(OutboxExactlyOnce.INSTANCE, th2.getMessage(), booleanRef2.element, null, 4, null);
                            Result.Companion companion1110 = Result.Companion;
                            MessageDao messageDao112 = defaultSessionRepository8.messages;
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository8;
                            defaultSessionRepository$deliverPending$2.L$1 = str23;
                            defaultSessionRepository$deliverPending$2.L$2 = str8;
                            defaultSessionRepository$deliverPending$2.L$3 = str22;
                            defaultSessionRepository$deliverPending$2.L$4 = str7;
                            defaultSessionRepository$deliverPending$2.L$5 = str21;
                            defaultSessionRepository$deliverPending$2.L$6 = r4;
                            defaultSessionRepository$deliverPending$2.L$7 = th2;
                            defaultSessionRepository$deliverPending$2.L$8 = strClassifyTransportFailure$default;
                            defaultSessionRepository$deliverPending$2.label = 8;
                            deliveryStatus3 = messageDao112.setDeliveryStatus(str8, str7, strClassifyTransportFailure$default, defaultSessionRepository$deliverPending$2);
                            if (deliveryStatus3 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str29 = str8;
                            str32 = str7;
                            r10 = r4;
                            str31 = strClassifyTransportFailure$default;
                            defaultSessionRepository18 = defaultSessionRepository8;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus3).intValue()));
                            i2 = i;
                            String str618 = str22;
                            defaultSessionRepository2 = defaultSessionRepository18;
                            str39 = str618;
                            r14 = r10;
                            str40 = str32;
                            if (Intrinsics.areEqual(str31, OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                                Result.Companion companion1111 = Result.Companion;
                                defaultSessionRepository15 = defaultSessionRepository2;
                                i3 = 0;
                                GatewayClient gatewayClient6 = defaultSessionRepository15.gatewayClient;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$deliverPending$2.L$1 = str23;
                                defaultSessionRepository$deliverPending$2.L$2 = str29;
                                defaultSessionRepository$deliverPending$2.L$3 = str39;
                                defaultSessionRepository$deliverPending$2.L$4 = str40;
                                defaultSessionRepository$deliverPending$2.L$5 = str21;
                                defaultSessionRepository$deliverPending$2.L$6 = r14;
                                defaultSessionRepository$deliverPending$2.L$7 = th2;
                                defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository15;
                                defaultSessionRepository$deliverPending$2.label = 9;
                                objM43getMessagesgIAlus = gatewayClient6.m43getMessagesgIAlus(str23, defaultSessionRepository$deliverPending$2);
                                if (objM43getMessagesgIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str43 = str39;
                                str44 = str21;
                                defaultSessionRepository16 = defaultSessionRepository15;
                                DefaultSessionRepository defaultSessionRepository213 = defaultSessionRepository2;
                                value3 = objM43getMessagesgIAlus;
                                str45 = str23;
                                defaultSessionRepository17 = defaultSessionRepository213;
                                String str619 = str40;
                                th3 = th2;
                                r15 = r14;
                                str46 = str619;
                                if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                    i4 = 0;
                                    OutboxExactlyOnce outboxExactlyOnce7 = OutboxExactlyOnce.INSTANCE;
                                    Function1 function4 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                        }
                                    };
                                    auroraMessage5 = (AuroraMessage) outboxExactlyOnce7.findRemoteContentMatch((List) value3, str43, function4, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                        }
                                    }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function4, obj3));
                                        }
                                    } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                        }
                                    });
                                    i5 = i3;
                                    OutboxExactlyOnce outboxExactlyOnce8 = OutboxExactlyOnce.INSTANCE;
                                    if (auroraMessage5 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    strReconcileAmbiguousAgainstRemote = outboxExactlyOnce8.reconcileAmbiguousAgainstRemote(true, z);
                                    if (strReconcileAmbiguousAgainstRemote != null) {
                                        messageDao2 = defaultSessionRepository16.messages;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                        defaultSessionRepository$deliverPending$2.L$1 = str45;
                                        defaultSessionRepository$deliverPending$2.L$2 = str29;
                                        defaultSessionRepository$deliverPending$2.L$3 = str43;
                                        defaultSessionRepository$deliverPending$2.L$4 = str46;
                                        defaultSessionRepository$deliverPending$2.L$5 = str44;
                                        defaultSessionRepository$deliverPending$2.L$6 = r15;
                                        defaultSessionRepository$deliverPending$2.L$7 = th3;
                                        defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                        defaultSessionRepository$deliverPending$2.L$9 = value3;
                                        defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                        defaultSessionRepository$deliverPending$2.label = 10;
                                        if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        defaultSessionRepository19 = defaultSessionRepository17;
                                        str48 = str46;
                                        obj = value3;
                                        r17 = r15;
                                        str49 = strReconcileAmbiguousAgainstRemote;
                                        str50 = str29;
                                        str42 = str44;
                                        str51 = str43;
                                        i6 = i5;
                                        if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                            Result.Companion companion1112 = Result.Companion;
                                            DefaultSessionRepository defaultSessionRepository214 = defaultSessionRepository16;
                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                            defaultSessionRepository$deliverPending$2.L$1 = str50;
                                            defaultSessionRepository$deliverPending$2.L$2 = str51;
                                            defaultSessionRepository$deliverPending$2.L$3 = str48;
                                            defaultSessionRepository$deliverPending$2.L$4 = str42;
                                            defaultSessionRepository$deliverPending$2.L$5 = r17;
                                            defaultSessionRepository$deliverPending$2.L$6 = th3;
                                            defaultSessionRepository$deliverPending$2.L$7 = obj;
                                            str56 = str51;
                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                            defaultSessionRepository$deliverPending$2.L$9 = null;
                                            defaultSessionRepository$deliverPending$2.L$10 = null;
                                            defaultSessionRepository$deliverPending$2.label = 11;
                                            objMo55selectSessiongIAlus2 = defaultSessionRepository214.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                            if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            th4 = th3;
                                            r18 = r17;
                                            str52 = str42;
                                            str53 = str48;
                                            str54 = str56;
                                            i7 = i4;
                                            str55 = str50;
                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                            r23 = r18;
                                            defaultSessionRepository23 = defaultSessionRepository19;
                                            str62 = str53;
                                            str44 = str52;
                                            str43 = str54;
                                            value3 = obj;
                                            r20 = r23;
                                            defaultSessionRepository20 = defaultSessionRepository23;
                                            str57 = str62;
                                            str29 = str55;
                                            th3 = th4;
                                        } else {
                                            String str710 = str51;
                                            r20 = r17;
                                            str44 = str42;
                                            str29 = str50;
                                            value3 = obj;
                                            str57 = str48;
                                            str43 = str710;
                                            defaultSessionRepository20 = defaultSessionRepository19;
                                        }
                                    } else {
                                        r20 = r15;
                                        str57 = str46;
                                        defaultSessionRepository20 = defaultSessionRepository17;
                                    }
                                    str41 = str43;
                                    r22 = r20;
                                    str61 = str57;
                                    defaultSessionRepository22 = defaultSessionRepository20;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                    i8 = i2;
                                    r21 = r22;
                                    th5 = th3;
                                    str8 = str29;
                                    defaultSessionRepository21 = defaultSessionRepository22;
                                    str59 = str41;
                                    str58 = str44;
                                    str60 = str61;
                                    MessageDao messageDao113 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao113.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion218 = Result.Companion;
                                        Object objM7388constructorimpl110 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl110;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                } else {
                                    str41 = str43;
                                    r22 = r15;
                                    str61 = str46;
                                    defaultSessionRepository22 = defaultSessionRepository17;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                    i8 = i2;
                                    r21 = r22;
                                    th5 = th3;
                                    str8 = str29;
                                    defaultSessionRepository21 = defaultSessionRepository22;
                                    str59 = str41;
                                    str58 = str44;
                                    str60 = str61;
                                    MessageDao messageDao114 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao114.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion219 = Result.Companion;
                                        Object objM7388constructorimpl111 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl111;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                }
                                Result.Companion companion2110 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                                i8 = i2;
                                th5 = th2;
                                str58 = str42;
                                str59 = str41;
                                r21 = r4;
                                str60 = str7;
                                defaultSessionRepository21 = defaultSessionRepository2;
                                MessageDao messageDao115 = defaultSessionRepository21.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                defaultSessionRepository$deliverPending$2.L$1 = str8;
                                defaultSessionRepository$deliverPending$2.L$2 = str59;
                                defaultSessionRepository$deliverPending$2.L$3 = str60;
                                defaultSessionRepository$deliverPending$2.L$4 = str58;
                                defaultSessionRepository$deliverPending$2.L$5 = r21;
                                defaultSessionRepository$deliverPending$2.L$6 = th5;
                                defaultSessionRepository$deliverPending$2.L$7 = null;
                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                defaultSessionRepository$deliverPending$2.L$9 = null;
                                defaultSessionRepository$deliverPending$2.L$10 = null;
                                defaultSessionRepository$deliverPending$2.label = 12;
                                byId = messageDao115.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                r25 = r21;
                                str64 = str60;
                                defaultSessionRepository26 = defaultSessionRepository21;
                                if (byId == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                messageEntity = (MessageEntity) byId;
                                if (messageEntity != null) {
                                    deliveryStatus4 = messageEntity.getDeliveryStatus();
                                } else {
                                    deliveryStatus4 = null;
                                }
                                if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                    Result.Companion companion2111 = Result.Companion;
                                    Object objM7388constructorimpl112 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                    defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                    return objM7388constructorimpl112;
                                }
                                th2 = th5;
                                r16 = r25;
                                str47 = str64;
                                defaultSessionRepository25 = defaultSessionRepository26;
                            } else {
                                r16 = r14;
                                str47 = str40;
                                str8 = str29;
                                defaultSessionRepository25 = defaultSessionRepository2;
                            }
                            Result.Companion companion2112 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            r24 = r16;
                            str63 = str47;
                            defaultSessionRepository24 = defaultSessionRepository25;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r4, null, 1, null);
                            defaultSessionRepository2.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str7, str8), r4);
                            throw th5;
                        case 5:
                            auroraMessage3 = (AuroraMessage) defaultSessionRepository$deliverPending$2.L$6;
                            r7 = (Mutex) defaultSessionRepository$deliverPending$2.L$5;
                            str28 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            str27 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str8 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str26 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            defaultSessionRepository10 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                deliveryStatus2 = obj2;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus2).intValue()));
                                auroraMessage2 = auroraMessage3;
                                r12 = r7;
                                str23 = str26;
                                defaultSessionRepository11 = defaultSessionRepository10;
                                String str6110 = str27;
                                str21 = str28;
                                str34 = str6110;
                            } catch (Throwable th31) {
                                auroraMessage2 = auroraMessage3;
                                r11 = r7;
                                str23 = str26;
                                defaultSessionRepository9 = defaultSessionRepository10;
                                String str82 = str27;
                                str21 = str28;
                                str33 = str82;
                                Result.Companion companion120 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th31));
                                r12 = r11;
                                str34 = str33;
                                defaultSessionRepository11 = defaultSessionRepository9;
                            }
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository11;
                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                            defaultSessionRepository$deliverPending$2.L$2 = str34;
                            defaultSessionRepository$deliverPending$2.L$3 = str21;
                            defaultSessionRepository$deliverPending$2.L$4 = r12;
                            defaultSessionRepository$deliverPending$2.L$5 = auroraMessage2;
                            defaultSessionRepository$deliverPending$2.L$6 = null;
                            defaultSessionRepository$deliverPending$2.L$7 = null;
                            defaultSessionRepository$deliverPending$2.label = 6;
                            objMo55selectSessiongIAlus = defaultSessionRepository11.mo55selectSessiongIAlus(str23, defaultSessionRepository$deliverPending$2);
                            if (objMo55selectSessiongIAlus == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str35 = str34;
                            str36 = str21;
                            value2 = objMo55selectSessiongIAlus;
                            r1 = r12;
                            auroraMessage4 = auroraMessage2;
                            defaultSessionRepository12 = defaultSessionRepository11;
                            if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(value2)) {
                                Result.Companion companion310 = Result.Companion;
                                Object objM7388constructorimpl113 = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r1, null, 1, null);
                                defaultSessionRepository12.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str35, str8), r1);
                                return objM7388constructorimpl113;
                            }
                            Result.Companion companion1113 = Result.Companion;
                            messageDao = defaultSessionRepository12.messages;
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository12;
                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                            defaultSessionRepository$deliverPending$2.L$2 = str35;
                            defaultSessionRepository$deliverPending$2.L$3 = str36;
                            defaultSessionRepository$deliverPending$2.L$4 = r1;
                            defaultSessionRepository$deliverPending$2.L$5 = auroraMessage4;
                            defaultSessionRepository$deliverPending$2.label = 7;
                            if (messageDao.deleteById(str8, str35, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str37 = str35;
                            r13 = r1;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                            r2 = r13;
                            str38 = str37;
                            defaultSessionRepository13 = defaultSessionRepository12;
                            Result.Companion companion1114 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                            r24 = r2;
                            str63 = str38;
                            defaultSessionRepository24 = defaultSessionRepository13;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 6:
                            AuroraMessage auroraMessage6 = (AuroraMessage) defaultSessionRepository$deliverPending$2.L$5;
                            Mutex mutex3 = (Mutex) defaultSessionRepository$deliverPending$2.L$4;
                            String str83 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            String str84 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str8 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository33 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            ResultKt.throwOnFailure(obj2);
                            value2 = ai.nous.aurora.compat.Compat.resultUnbox(obj2);
                            r1 = mutex3;
                            str35 = str84;
                            auroraMessage4 = auroraMessage6;
                            str36 = str83;
                            defaultSessionRepository12 = defaultSessionRepository33;
                            if (ai.nous.aurora.compat.Compat.m7394isFailureimpl(value2)) {
                                Result.Companion companion311 = Result.Companion;
                                Object objM7388constructorimpl114 = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r1, null, 1, null);
                                defaultSessionRepository12.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str35, str8), r1);
                                return objM7388constructorimpl114;
                            }
                            Result.Companion companion1115 = Result.Companion;
                            messageDao = defaultSessionRepository12.messages;
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository12;
                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                            defaultSessionRepository$deliverPending$2.L$2 = str35;
                            defaultSessionRepository$deliverPending$2.L$3 = str36;
                            defaultSessionRepository$deliverPending$2.L$4 = r1;
                            defaultSessionRepository$deliverPending$2.L$5 = auroraMessage4;
                            defaultSessionRepository$deliverPending$2.label = 7;
                            if (messageDao.deleteById(str8, str35, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str37 = str35;
                            r13 = r1;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                            r2 = r13;
                            str38 = str37;
                            defaultSessionRepository13 = defaultSessionRepository12;
                            Result.Companion companion1116 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                            r24 = r2;
                            str63 = str38;
                            defaultSessionRepository24 = defaultSessionRepository13;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 7:
                            auroraMessage4 = (AuroraMessage) defaultSessionRepository$deliverPending$2.L$5;
                            r13 = (Mutex) defaultSessionRepository$deliverPending$2.L$4;
                            str36 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str37 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str8 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            defaultSessionRepository12 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Unit.INSTANCE);
                                r2 = r13;
                                str38 = str37;
                                defaultSessionRepository13 = defaultSessionRepository12;
                            } catch (Throwable th32) {
                                r1 = r13;
                                str35 = str37;
                                Result.Companion companion121 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th32));
                                r2 = r1;
                                str38 = str35;
                                defaultSessionRepository13 = defaultSessionRepository12;
                            }
                            Result.Companion companion1117 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(AuroraMessage.copy$default(auroraMessage4, null, null, null, OutboxExactlyOnce.STATUS_SENT, str36, 7, null));
                            r24 = r2;
                            str63 = str38;
                            defaultSessionRepository24 = defaultSessionRepository13;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 8:
                            i2 = 0;
                            str31 = (String) defaultSessionRepository$deliverPending$2.L$8;
                            th2 = (Throwable) defaultSessionRepository$deliverPending$2.L$7;
                            Mutex mutex4 = (Mutex) defaultSessionRepository$deliverPending$2.L$6;
                            str21 = (String) defaultSessionRepository$deliverPending$2.L$5;
                            String str85 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            str22 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str29 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str23 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository34 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                deliveryStatus3 = obj2;
                                defaultSessionRepository18 = defaultSessionRepository34;
                                i = 0;
                                r10 = mutex4;
                                str32 = str85;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(Boxing.boxInt(((Number) deliveryStatus3).intValue()));
                                i2 = i;
                                String str6111 = str22;
                                defaultSessionRepository2 = defaultSessionRepository18;
                                str39 = str6111;
                                r14 = r10;
                                str40 = str32;
                            } catch (Throwable th33) {
                                defaultSessionRepository14 = defaultSessionRepository34;
                                r8 = mutex4;
                                str30 = str85;
                                Result.Companion companion1118 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th33));
                                String str6112 = str22;
                                defaultSessionRepository2 = defaultSessionRepository14;
                                str39 = str6112;
                                r14 = r8;
                                str40 = str30;
                                if (Intrinsics.areEqual(str31, OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                                    Result.Companion companion1119 = Result.Companion;
                                    defaultSessionRepository15 = defaultSessionRepository2;
                                    i3 = 0;
                                    GatewayClient gatewayClient7 = defaultSessionRepository15.gatewayClient;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                    defaultSessionRepository$deliverPending$2.L$1 = str23;
                                    defaultSessionRepository$deliverPending$2.L$2 = str29;
                                    defaultSessionRepository$deliverPending$2.L$3 = str39;
                                    defaultSessionRepository$deliverPending$2.L$4 = str40;
                                    defaultSessionRepository$deliverPending$2.L$5 = str21;
                                    defaultSessionRepository$deliverPending$2.L$6 = r14;
                                    defaultSessionRepository$deliverPending$2.L$7 = th2;
                                    defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository15;
                                    defaultSessionRepository$deliverPending$2.label = 9;
                                    objM43getMessagesgIAlus = gatewayClient7.m43getMessagesgIAlus(str23, defaultSessionRepository$deliverPending$2);
                                    if (objM43getMessagesgIAlus == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    str43 = str39;
                                    str44 = str21;
                                    defaultSessionRepository16 = defaultSessionRepository15;
                                    DefaultSessionRepository defaultSessionRepository215 = defaultSessionRepository2;
                                    value3 = objM43getMessagesgIAlus;
                                    str45 = str23;
                                    defaultSessionRepository17 = defaultSessionRepository215;
                                    String str6113 = str40;
                                    th3 = th2;
                                    r15 = r14;
                                    str46 = str6113;
                                    if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                        i4 = 0;
                                        OutboxExactlyOnce outboxExactlyOnce9 = OutboxExactlyOnce.INSTANCE;
                                        Function1 function5 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                            }
                                        };
                                        auroraMessage5 = (AuroraMessage) outboxExactlyOnce9.findRemoteContentMatch((List) value3, str43, function5, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                            }
                                        }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function5, obj3));
                                            }
                                        } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                            }
                                        });
                                        i5 = i3;
                                        OutboxExactlyOnce outboxExactlyOnce10 = OutboxExactlyOnce.INSTANCE;
                                        if (auroraMessage5 != null) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                        strReconcileAmbiguousAgainstRemote = outboxExactlyOnce10.reconcileAmbiguousAgainstRemote(true, z);
                                        if (strReconcileAmbiguousAgainstRemote != null) {
                                            messageDao2 = defaultSessionRepository16.messages;
                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                            defaultSessionRepository$deliverPending$2.L$1 = str45;
                                            defaultSessionRepository$deliverPending$2.L$2 = str29;
                                            defaultSessionRepository$deliverPending$2.L$3 = str43;
                                            defaultSessionRepository$deliverPending$2.L$4 = str46;
                                            defaultSessionRepository$deliverPending$2.L$5 = str44;
                                            defaultSessionRepository$deliverPending$2.L$6 = r15;
                                            defaultSessionRepository$deliverPending$2.L$7 = th3;
                                            defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                            defaultSessionRepository$deliverPending$2.L$9 = value3;
                                            defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                            defaultSessionRepository$deliverPending$2.label = 10;
                                            if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            defaultSessionRepository19 = defaultSessionRepository17;
                                            str48 = str46;
                                            obj = value3;
                                            r17 = r15;
                                            str49 = strReconcileAmbiguousAgainstRemote;
                                            str50 = str29;
                                            str42 = str44;
                                            str51 = str43;
                                            i6 = i5;
                                            if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                                Result.Companion companion11110 = Result.Companion;
                                                DefaultSessionRepository defaultSessionRepository216 = defaultSessionRepository16;
                                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                                defaultSessionRepository$deliverPending$2.L$1 = str50;
                                                defaultSessionRepository$deliverPending$2.L$2 = str51;
                                                defaultSessionRepository$deliverPending$2.L$3 = str48;
                                                defaultSessionRepository$deliverPending$2.L$4 = str42;
                                                defaultSessionRepository$deliverPending$2.L$5 = r17;
                                                defaultSessionRepository$deliverPending$2.L$6 = th3;
                                                defaultSessionRepository$deliverPending$2.L$7 = obj;
                                                str56 = str51;
                                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                                defaultSessionRepository$deliverPending$2.L$9 = null;
                                                defaultSessionRepository$deliverPending$2.L$10 = null;
                                                defaultSessionRepository$deliverPending$2.label = 11;
                                                objMo55selectSessiongIAlus2 = defaultSessionRepository216.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                                if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                th4 = th3;
                                                r18 = r17;
                                                str52 = str42;
                                                str53 = str48;
                                                str54 = str56;
                                                i7 = i4;
                                                str55 = str50;
                                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                                r23 = r18;
                                                defaultSessionRepository23 = defaultSessionRepository19;
                                                str62 = str53;
                                                str44 = str52;
                                                str43 = str54;
                                                value3 = obj;
                                                r20 = r23;
                                                defaultSessionRepository20 = defaultSessionRepository23;
                                                str57 = str62;
                                                str29 = str55;
                                                th3 = th4;
                                            } else {
                                                String str711 = str51;
                                                r20 = r17;
                                                str44 = str42;
                                                str29 = str50;
                                                value3 = obj;
                                                str57 = str48;
                                                str43 = str711;
                                                defaultSessionRepository20 = defaultSessionRepository19;
                                            }
                                        } else {
                                            r20 = r15;
                                            str57 = str46;
                                            defaultSessionRepository20 = defaultSessionRepository17;
                                        }
                                        str41 = str43;
                                        r22 = r20;
                                        str61 = str57;
                                        defaultSessionRepository22 = defaultSessionRepository20;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                        i8 = i2;
                                        r21 = r22;
                                        th5 = th3;
                                        str8 = str29;
                                        defaultSessionRepository21 = defaultSessionRepository22;
                                        str59 = str41;
                                        str58 = str44;
                                        str60 = str61;
                                        MessageDao messageDao116 = defaultSessionRepository21.messages;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                        defaultSessionRepository$deliverPending$2.L$2 = str59;
                                        defaultSessionRepository$deliverPending$2.L$3 = str60;
                                        defaultSessionRepository$deliverPending$2.L$4 = str58;
                                        defaultSessionRepository$deliverPending$2.L$5 = r21;
                                        defaultSessionRepository$deliverPending$2.L$6 = th5;
                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                        defaultSessionRepository$deliverPending$2.label = 12;
                                        byId = messageDao116.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                        r25 = r21;
                                        str64 = str60;
                                        defaultSessionRepository26 = defaultSessionRepository21;
                                        if (byId == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        messageEntity = (MessageEntity) byId;
                                        if (messageEntity != null) {
                                            deliveryStatus4 = messageEntity.getDeliveryStatus();
                                        } else {
                                            deliveryStatus4 = null;
                                        }
                                        if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                            Result.Companion companion2113 = Result.Companion;
                                            Object objM7388constructorimpl115 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                            defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                            return objM7388constructorimpl115;
                                        }
                                        th2 = th5;
                                        r16 = r25;
                                        str47 = str64;
                                        defaultSessionRepository25 = defaultSessionRepository26;
                                    } else {
                                        str41 = str43;
                                        r22 = r15;
                                        str61 = str46;
                                        defaultSessionRepository22 = defaultSessionRepository17;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                        i8 = i2;
                                        r21 = r22;
                                        th5 = th3;
                                        str8 = str29;
                                        defaultSessionRepository21 = defaultSessionRepository22;
                                        str59 = str41;
                                        str58 = str44;
                                        str60 = str61;
                                        MessageDao messageDao117 = defaultSessionRepository21.messages;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                        defaultSessionRepository$deliverPending$2.L$1 = str8;
                                        defaultSessionRepository$deliverPending$2.L$2 = str59;
                                        defaultSessionRepository$deliverPending$2.L$3 = str60;
                                        defaultSessionRepository$deliverPending$2.L$4 = str58;
                                        defaultSessionRepository$deliverPending$2.L$5 = r21;
                                        defaultSessionRepository$deliverPending$2.L$6 = th5;
                                        defaultSessionRepository$deliverPending$2.L$7 = null;
                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                        defaultSessionRepository$deliverPending$2.label = 12;
                                        byId = messageDao117.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                        r25 = r21;
                                        str64 = str60;
                                        defaultSessionRepository26 = defaultSessionRepository21;
                                        if (byId == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        messageEntity = (MessageEntity) byId;
                                        if (messageEntity != null) {
                                            deliveryStatus4 = messageEntity.getDeliveryStatus();
                                        } else {
                                            deliveryStatus4 = null;
                                        }
                                        if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                            Result.Companion companion2114 = Result.Companion;
                                            Object objM7388constructorimpl116 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                            defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                            return objM7388constructorimpl116;
                                        }
                                        th2 = th5;
                                        r16 = r25;
                                        str47 = str64;
                                        defaultSessionRepository25 = defaultSessionRepository26;
                                    }
                                    Result.Companion companion2115 = Result.Companion;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th33));
                                    i8 = i2;
                                    th5 = th2;
                                    str58 = str42;
                                    str59 = str41;
                                    r21 = r4;
                                    str60 = str7;
                                    defaultSessionRepository21 = defaultSessionRepository2;
                                    MessageDao messageDao118 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao118.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion2116 = Result.Companion;
                                        Object objM7388constructorimpl117 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl117;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                } else {
                                    r16 = r14;
                                    str47 = str40;
                                    str8 = str29;
                                    defaultSessionRepository25 = defaultSessionRepository2;
                                }
                                Result.Companion companion2117 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                r24 = r16;
                                str63 = str47;
                                defaultSessionRepository24 = defaultSessionRepository25;
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                return objM7388constructorimpl;
                            }
                            if (Intrinsics.areEqual(str31, OutboxExactlyOnce.STATUS_AMBIGUOUS)) {
                                Result.Companion companion11111 = Result.Companion;
                                defaultSessionRepository15 = defaultSessionRepository2;
                                i3 = 0;
                                GatewayClient gatewayClient8 = defaultSessionRepository15.gatewayClient;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository2;
                                defaultSessionRepository$deliverPending$2.L$1 = str23;
                                defaultSessionRepository$deliverPending$2.L$2 = str29;
                                defaultSessionRepository$deliverPending$2.L$3 = str39;
                                defaultSessionRepository$deliverPending$2.L$4 = str40;
                                defaultSessionRepository$deliverPending$2.L$5 = str21;
                                defaultSessionRepository$deliverPending$2.L$6 = r14;
                                defaultSessionRepository$deliverPending$2.L$7 = th2;
                                defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository15;
                                defaultSessionRepository$deliverPending$2.label = 9;
                                objM43getMessagesgIAlus = gatewayClient8.m43getMessagesgIAlus(str23, defaultSessionRepository$deliverPending$2);
                                if (objM43getMessagesgIAlus == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                str43 = str39;
                                str44 = str21;
                                defaultSessionRepository16 = defaultSessionRepository15;
                                DefaultSessionRepository defaultSessionRepository217 = defaultSessionRepository2;
                                value3 = objM43getMessagesgIAlus;
                                str45 = str23;
                                defaultSessionRepository17 = defaultSessionRepository217;
                                String str6114 = str40;
                                th3 = th2;
                                r15 = r14;
                                str46 = str6114;
                                if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                    i4 = 0;
                                    OutboxExactlyOnce outboxExactlyOnce11 = OutboxExactlyOnce.INSTANCE;
                                    Function1 function6 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                        }
                                    };
                                    auroraMessage5 = (AuroraMessage) outboxExactlyOnce11.findRemoteContentMatch((List) value3, str43, function6, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                        }
                                    }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function6, obj3));
                                        }
                                    } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj3) {
                                            return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                        }
                                    });
                                    i5 = i3;
                                    OutboxExactlyOnce outboxExactlyOnce12 = OutboxExactlyOnce.INSTANCE;
                                    if (auroraMessage5 != null) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    strReconcileAmbiguousAgainstRemote = outboxExactlyOnce12.reconcileAmbiguousAgainstRemote(true, z);
                                    if (strReconcileAmbiguousAgainstRemote != null) {
                                        messageDao2 = defaultSessionRepository16.messages;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                        defaultSessionRepository$deliverPending$2.L$1 = str45;
                                        defaultSessionRepository$deliverPending$2.L$2 = str29;
                                        defaultSessionRepository$deliverPending$2.L$3 = str43;
                                        defaultSessionRepository$deliverPending$2.L$4 = str46;
                                        defaultSessionRepository$deliverPending$2.L$5 = str44;
                                        defaultSessionRepository$deliverPending$2.L$6 = r15;
                                        defaultSessionRepository$deliverPending$2.L$7 = th3;
                                        defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                        defaultSessionRepository$deliverPending$2.L$9 = value3;
                                        defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                        defaultSessionRepository$deliverPending$2.label = 10;
                                        if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        defaultSessionRepository19 = defaultSessionRepository17;
                                        str48 = str46;
                                        obj = value3;
                                        r17 = r15;
                                        str49 = strReconcileAmbiguousAgainstRemote;
                                        str50 = str29;
                                        str42 = str44;
                                        str51 = str43;
                                        i6 = i5;
                                        if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                            Result.Companion companion11112 = Result.Companion;
                                            DefaultSessionRepository defaultSessionRepository218 = defaultSessionRepository16;
                                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                            defaultSessionRepository$deliverPending$2.L$1 = str50;
                                            defaultSessionRepository$deliverPending$2.L$2 = str51;
                                            defaultSessionRepository$deliverPending$2.L$3 = str48;
                                            defaultSessionRepository$deliverPending$2.L$4 = str42;
                                            defaultSessionRepository$deliverPending$2.L$5 = r17;
                                            defaultSessionRepository$deliverPending$2.L$6 = th3;
                                            defaultSessionRepository$deliverPending$2.L$7 = obj;
                                            str56 = str51;
                                            defaultSessionRepository$deliverPending$2.L$8 = null;
                                            defaultSessionRepository$deliverPending$2.L$9 = null;
                                            defaultSessionRepository$deliverPending$2.L$10 = null;
                                            defaultSessionRepository$deliverPending$2.label = 11;
                                            objMo55selectSessiongIAlus2 = defaultSessionRepository218.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                            if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            th4 = th3;
                                            r18 = r17;
                                            str52 = str42;
                                            str53 = str48;
                                            str54 = str56;
                                            i7 = i4;
                                            str55 = str50;
                                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                            r23 = r18;
                                            defaultSessionRepository23 = defaultSessionRepository19;
                                            str62 = str53;
                                            str44 = str52;
                                            str43 = str54;
                                            value3 = obj;
                                            r20 = r23;
                                            defaultSessionRepository20 = defaultSessionRepository23;
                                            str57 = str62;
                                            str29 = str55;
                                            th3 = th4;
                                        } else {
                                            String str712 = str51;
                                            r20 = r17;
                                            str44 = str42;
                                            str29 = str50;
                                            value3 = obj;
                                            str57 = str48;
                                            str43 = str712;
                                            defaultSessionRepository20 = defaultSessionRepository19;
                                        }
                                    } else {
                                        r20 = r15;
                                        str57 = str46;
                                        defaultSessionRepository20 = defaultSessionRepository17;
                                    }
                                    str41 = str43;
                                    r22 = r20;
                                    str61 = str57;
                                    defaultSessionRepository22 = defaultSessionRepository20;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                    i8 = i2;
                                    r21 = r22;
                                    th5 = th3;
                                    str8 = str29;
                                    defaultSessionRepository21 = defaultSessionRepository22;
                                    str59 = str41;
                                    str58 = str44;
                                    str60 = str61;
                                    MessageDao messageDao119 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao119.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion2118 = Result.Companion;
                                        Object objM7388constructorimpl118 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl118;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                } else {
                                    str41 = str43;
                                    r22 = r15;
                                    str61 = str46;
                                    defaultSessionRepository22 = defaultSessionRepository17;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                    i8 = i2;
                                    r21 = r22;
                                    th5 = th3;
                                    str8 = str29;
                                    defaultSessionRepository21 = defaultSessionRepository22;
                                    str59 = str41;
                                    str58 = str44;
                                    str60 = str61;
                                    MessageDao messageDao1110 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao1110.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion2119 = Result.Companion;
                                        Object objM7388constructorimpl119 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl119;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                }
                                Result.Companion companion21110 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                                i8 = i2;
                                th5 = th2;
                                str58 = str42;
                                str59 = str41;
                                r21 = r4;
                                str60 = str7;
                                defaultSessionRepository21 = defaultSessionRepository2;
                                MessageDao messageDao1111 = defaultSessionRepository21.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                defaultSessionRepository$deliverPending$2.L$1 = str8;
                                defaultSessionRepository$deliverPending$2.L$2 = str59;
                                defaultSessionRepository$deliverPending$2.L$3 = str60;
                                defaultSessionRepository$deliverPending$2.L$4 = str58;
                                defaultSessionRepository$deliverPending$2.L$5 = r21;
                                defaultSessionRepository$deliverPending$2.L$6 = th5;
                                defaultSessionRepository$deliverPending$2.L$7 = null;
                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                defaultSessionRepository$deliverPending$2.L$9 = null;
                                defaultSessionRepository$deliverPending$2.L$10 = null;
                                defaultSessionRepository$deliverPending$2.label = 12;
                                byId = messageDao1111.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                r25 = r21;
                                str64 = str60;
                                defaultSessionRepository26 = defaultSessionRepository21;
                                if (byId == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                messageEntity = (MessageEntity) byId;
                                if (messageEntity != null) {
                                    deliveryStatus4 = messageEntity.getDeliveryStatus();
                                } else {
                                    deliveryStatus4 = null;
                                }
                                if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                    Result.Companion companion21111 = Result.Companion;
                                    Object objM7388constructorimpl1110 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                    defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                    return objM7388constructorimpl1110;
                                }
                                th2 = th5;
                                r16 = r25;
                                str47 = str64;
                                defaultSessionRepository25 = defaultSessionRepository26;
                            } else {
                                r16 = r14;
                                str47 = str40;
                                str8 = str29;
                                defaultSessionRepository25 = defaultSessionRepository2;
                            }
                            Result.Companion companion21112 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            r24 = r16;
                            str63 = str47;
                            defaultSessionRepository24 = defaultSessionRepository25;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 9:
                            i2 = 0;
                            DefaultSessionRepository defaultSessionRepository35 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$8;
                            th3 = (Throwable) defaultSessionRepository$deliverPending$2.L$7;
                            Mutex mutex5 = (Mutex) defaultSessionRepository$deliverPending$2.L$6;
                            str42 = (String) defaultSessionRepository$deliverPending$2.L$5;
                            String str86 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            str41 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            String str87 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            String str88 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository36 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                i3 = 0;
                                str45 = str88;
                                str44 = str42;
                                value3 = ai.nous.aurora.compat.Compat.resultUnbox(obj2);
                                str29 = str87;
                                defaultSessionRepository16 = defaultSessionRepository35;
                                str43 = str41;
                                r15 = mutex5;
                                str46 = str86;
                                defaultSessionRepository17 = defaultSessionRepository36;
                                if (!ai.nous.aurora.compat.Compat.m7395isSuccessimpl(value3)) {
                                    str41 = str43;
                                    r22 = r15;
                                    str61 = str46;
                                    defaultSessionRepository22 = defaultSessionRepository17;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                    i8 = i2;
                                    r21 = r22;
                                    th5 = th3;
                                    str8 = str29;
                                    defaultSessionRepository21 = defaultSessionRepository22;
                                    str59 = str41;
                                    str58 = str44;
                                    str60 = str61;
                                    MessageDao messageDao1112 = defaultSessionRepository21.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                    defaultSessionRepository$deliverPending$2.L$1 = str8;
                                    defaultSessionRepository$deliverPending$2.L$2 = str59;
                                    defaultSessionRepository$deliverPending$2.L$3 = str60;
                                    defaultSessionRepository$deliverPending$2.L$4 = str58;
                                    defaultSessionRepository$deliverPending$2.L$5 = r21;
                                    defaultSessionRepository$deliverPending$2.L$6 = th5;
                                    defaultSessionRepository$deliverPending$2.L$7 = null;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 12;
                                    byId = messageDao1112.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                    r25 = r21;
                                    str64 = str60;
                                    defaultSessionRepository26 = defaultSessionRepository21;
                                    if (byId == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    messageEntity = (MessageEntity) byId;
                                    if (messageEntity != null) {
                                        deliveryStatus4 = messageEntity.getDeliveryStatus();
                                    } else {
                                        deliveryStatus4 = null;
                                    }
                                    if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion21113 = Result.Companion;
                                        Object objM7388constructorimpl1111 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                        ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                        defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                        return objM7388constructorimpl1111;
                                    }
                                    th2 = th5;
                                    r16 = r25;
                                    str47 = str64;
                                    defaultSessionRepository25 = defaultSessionRepository26;
                                    Result.Companion companion21114 = Result.Companion;
                                    objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                    r24 = r16;
                                    str63 = str47;
                                    defaultSessionRepository24 = defaultSessionRepository25;
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                    defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                    return objM7388constructorimpl;
                                }
                                i4 = 0;
                                OutboxExactlyOnce outboxExactlyOnce13 = OutboxExactlyOnce.INSTANCE;
                                Function1 function7 = new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37((AuroraMessage) obj3);
                                    }
                                };
                                auroraMessage5 = (AuroraMessage) outboxExactlyOnce13.findRemoteContentMatch((List) value3, str43, function7, new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38((AuroraMessage) obj3);
                                    }
                                }, (16 & 16) != 0 ? new Function1() { // from class: ai.nous.aurora.domain.model.OutboxExactlyOnce$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return Boolean.valueOf(OutboxExactlyOnce.findRemoteContentMatch$lambda$4(function7, obj3));
                                    }
                                } : null, (16 & 32) != 0 ? null : str44, (16 & 64) != 0 ? OutboxExactlyOnce.AnonymousClass2.INSTANCE : new Function1() { // from class: ai.nous.aurora.data.repository.DefaultSessionRepository$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return DefaultSessionRepository.deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39((AuroraMessage) obj3);
                                    }
                                });
                                i5 = i3;
                                OutboxExactlyOnce outboxExactlyOnce14 = OutboxExactlyOnce.INSTANCE;
                                if (auroraMessage5 != null) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                strReconcileAmbiguousAgainstRemote = outboxExactlyOnce14.reconcileAmbiguousAgainstRemote(true, z);
                                if (strReconcileAmbiguousAgainstRemote != null) {
                                    messageDao2 = defaultSessionRepository16.messages;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository17;
                                    defaultSessionRepository$deliverPending$2.L$1 = str45;
                                    defaultSessionRepository$deliverPending$2.L$2 = str29;
                                    defaultSessionRepository$deliverPending$2.L$3 = str43;
                                    defaultSessionRepository$deliverPending$2.L$4 = str46;
                                    defaultSessionRepository$deliverPending$2.L$5 = str44;
                                    defaultSessionRepository$deliverPending$2.L$6 = r15;
                                    defaultSessionRepository$deliverPending$2.L$7 = th3;
                                    defaultSessionRepository$deliverPending$2.L$8 = defaultSessionRepository16;
                                    defaultSessionRepository$deliverPending$2.L$9 = value3;
                                    defaultSessionRepository$deliverPending$2.L$10 = strReconcileAmbiguousAgainstRemote;
                                    defaultSessionRepository$deliverPending$2.label = 10;
                                    if (messageDao2.setDeliveryStatus(str29, str46, strReconcileAmbiguousAgainstRemote, defaultSessionRepository$deliverPending$2) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    defaultSessionRepository19 = defaultSessionRepository17;
                                    str48 = str46;
                                    obj = value3;
                                    r17 = r15;
                                    str49 = strReconcileAmbiguousAgainstRemote;
                                    str50 = str29;
                                    str42 = str44;
                                    str51 = str43;
                                    i6 = i5;
                                    if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                        Result.Companion companion11113 = Result.Companion;
                                        DefaultSessionRepository defaultSessionRepository219 = defaultSessionRepository16;
                                        defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                        defaultSessionRepository$deliverPending$2.L$1 = str50;
                                        defaultSessionRepository$deliverPending$2.L$2 = str51;
                                        defaultSessionRepository$deliverPending$2.L$3 = str48;
                                        defaultSessionRepository$deliverPending$2.L$4 = str42;
                                        defaultSessionRepository$deliverPending$2.L$5 = r17;
                                        defaultSessionRepository$deliverPending$2.L$6 = th3;
                                        defaultSessionRepository$deliverPending$2.L$7 = obj;
                                        str56 = str51;
                                        defaultSessionRepository$deliverPending$2.L$8 = null;
                                        defaultSessionRepository$deliverPending$2.L$9 = null;
                                        defaultSessionRepository$deliverPending$2.L$10 = null;
                                        defaultSessionRepository$deliverPending$2.label = 11;
                                        objMo55selectSessiongIAlus2 = defaultSessionRepository219.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                        if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        th4 = th3;
                                        r18 = r17;
                                        str52 = str42;
                                        str53 = str48;
                                        str54 = str56;
                                        i7 = i4;
                                        str55 = str50;
                                        ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                        r23 = r18;
                                        defaultSessionRepository23 = defaultSessionRepository19;
                                        str62 = str53;
                                        str44 = str52;
                                        str43 = str54;
                                        value3 = obj;
                                        r20 = r23;
                                        defaultSessionRepository20 = defaultSessionRepository23;
                                        str57 = str62;
                                        str29 = str55;
                                        th3 = th4;
                                    } else {
                                        String str713 = str51;
                                        r20 = r17;
                                        str44 = str42;
                                        str29 = str50;
                                        value3 = obj;
                                        str57 = str48;
                                        str43 = str713;
                                        defaultSessionRepository20 = defaultSessionRepository19;
                                    }
                                } else {
                                    r20 = r15;
                                    str57 = str46;
                                    defaultSessionRepository20 = defaultSessionRepository17;
                                }
                                str41 = str43;
                                r22 = r20;
                                str61 = str57;
                                defaultSessionRepository22 = defaultSessionRepository20;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                i8 = i2;
                                r21 = r22;
                                th5 = th3;
                                str8 = str29;
                                defaultSessionRepository21 = defaultSessionRepository22;
                                str59 = str41;
                                str58 = str44;
                                str60 = str61;
                                MessageDao messageDao1113 = defaultSessionRepository21.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                defaultSessionRepository$deliverPending$2.L$1 = str8;
                                defaultSessionRepository$deliverPending$2.L$2 = str59;
                                defaultSessionRepository$deliverPending$2.L$3 = str60;
                                defaultSessionRepository$deliverPending$2.L$4 = str58;
                                defaultSessionRepository$deliverPending$2.L$5 = r21;
                                defaultSessionRepository$deliverPending$2.L$6 = th5;
                                defaultSessionRepository$deliverPending$2.L$7 = null;
                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                defaultSessionRepository$deliverPending$2.L$9 = null;
                                defaultSessionRepository$deliverPending$2.L$10 = null;
                                defaultSessionRepository$deliverPending$2.label = 12;
                                byId = messageDao1113.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                r25 = r21;
                                str64 = str60;
                                defaultSessionRepository26 = defaultSessionRepository21;
                                if (byId == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                messageEntity = (MessageEntity) byId;
                                if (messageEntity != null) {
                                    deliveryStatus4 = messageEntity.getDeliveryStatus();
                                } else {
                                    deliveryStatus4 = null;
                                }
                                if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                    Result.Companion companion21115 = Result.Companion;
                                    Object objM7388constructorimpl1112 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                    defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                    return objM7388constructorimpl1112;
                                }
                                th2 = th5;
                                r16 = r25;
                                str47 = str64;
                                defaultSessionRepository25 = defaultSessionRepository26;
                                Result.Companion companion21116 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                r24 = r16;
                                str63 = str47;
                                defaultSessionRepository24 = defaultSessionRepository25;
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                return objM7388constructorimpl;
                            } catch (Throwable th34) {
                                r4 = mutex5;
                                th2 = th3;
                                defaultSessionRepository2 = defaultSessionRepository36;
                                str8 = str87;
                                str7 = str86;
                            }
                            Result.Companion companion21117 = Result.Companion;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th5));
                            i8 = i2;
                            th5 = th2;
                            str58 = str42;
                            str59 = str41;
                            r21 = r4;
                            str60 = str7;
                            defaultSessionRepository21 = defaultSessionRepository2;
                            MessageDao messageDao1114 = defaultSessionRepository21.messages;
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                            defaultSessionRepository$deliverPending$2.L$7 = null;
                            defaultSessionRepository$deliverPending$2.L$8 = null;
                            defaultSessionRepository$deliverPending$2.L$9 = null;
                            defaultSessionRepository$deliverPending$2.L$10 = null;
                            defaultSessionRepository$deliverPending$2.label = 12;
                            byId = messageDao1114.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                            r25 = r21;
                            str64 = str60;
                            defaultSessionRepository26 = defaultSessionRepository21;
                            if (byId == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            messageEntity = (MessageEntity) byId;
                            if (messageEntity != null) {
                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                            } else {
                                deliveryStatus4 = null;
                            }
                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                Result.Companion companion21118 = Result.Companion;
                                Object objM7388constructorimpl1113 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                return objM7388constructorimpl1113;
                            }
                            th2 = th5;
                            r16 = r25;
                            str47 = str64;
                            defaultSessionRepository25 = defaultSessionRepository26;
                            Result.Companion companion21119 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            r24 = r16;
                            str63 = str47;
                            defaultSessionRepository24 = defaultSessionRepository25;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 10:
                            i2 = 0;
                            str49 = (String) defaultSessionRepository$deliverPending$2.L$10;
                            obj = defaultSessionRepository$deliverPending$2.L$9;
                            defaultSessionRepository16 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$8;
                            th3 = (Throwable) defaultSessionRepository$deliverPending$2.L$7;
                            Mutex mutex6 = (Mutex) defaultSessionRepository$deliverPending$2.L$6;
                            str42 = (String) defaultSessionRepository$deliverPending$2.L$5;
                            String str89 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            str41 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            String str90 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            String str91 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository37 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                str45 = str91;
                                i4 = 0;
                                str51 = str41;
                                str50 = str90;
                                i6 = 0;
                                defaultSessionRepository19 = defaultSessionRepository37;
                                r17 = mutex6;
                                str48 = str89;
                                if (Intrinsics.areEqual(str49, OutboxExactlyOnce.STATUS_SENT)) {
                                    Result.Companion companion11114 = Result.Companion;
                                    DefaultSessionRepository defaultSessionRepository2110 = defaultSessionRepository16;
                                    defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository19;
                                    defaultSessionRepository$deliverPending$2.L$1 = str50;
                                    defaultSessionRepository$deliverPending$2.L$2 = str51;
                                    defaultSessionRepository$deliverPending$2.L$3 = str48;
                                    defaultSessionRepository$deliverPending$2.L$4 = str42;
                                    defaultSessionRepository$deliverPending$2.L$5 = r17;
                                    defaultSessionRepository$deliverPending$2.L$6 = th3;
                                    defaultSessionRepository$deliverPending$2.L$7 = obj;
                                    str56 = str51;
                                    defaultSessionRepository$deliverPending$2.L$8 = null;
                                    defaultSessionRepository$deliverPending$2.L$9 = null;
                                    defaultSessionRepository$deliverPending$2.L$10 = null;
                                    defaultSessionRepository$deliverPending$2.label = 11;
                                    objMo55selectSessiongIAlus2 = defaultSessionRepository2110.mo55selectSessiongIAlus(str45, defaultSessionRepository$deliverPending$2);
                                    if (objMo55selectSessiongIAlus2 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    th4 = th3;
                                    r18 = r17;
                                    str52 = str42;
                                    str53 = str48;
                                    str54 = str56;
                                    i7 = i4;
                                    str55 = str50;
                                    ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                    r23 = r18;
                                    defaultSessionRepository23 = defaultSessionRepository19;
                                    str62 = str53;
                                    str44 = str52;
                                    str43 = str54;
                                    value3 = obj;
                                    r20 = r23;
                                    defaultSessionRepository20 = defaultSessionRepository23;
                                    str57 = str62;
                                    str29 = str55;
                                    th3 = th4;
                                } else {
                                    String str714 = str51;
                                    r20 = r17;
                                    str44 = str42;
                                    str29 = str50;
                                    value3 = obj;
                                    str57 = str48;
                                    str43 = str714;
                                    defaultSessionRepository20 = defaultSessionRepository19;
                                }
                                str41 = str43;
                                r22 = r20;
                                str61 = str57;
                                defaultSessionRepository22 = defaultSessionRepository20;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                i8 = i2;
                                r21 = r22;
                                th5 = th3;
                                str8 = str29;
                                defaultSessionRepository21 = defaultSessionRepository22;
                                str59 = str41;
                                str58 = str44;
                                str60 = str61;
                            } catch (Throwable th35) {
                                th2 = th3;
                                r4 = mutex6;
                                str7 = str89;
                                str8 = str90;
                                defaultSessionRepository2 = defaultSessionRepository37;
                                break;
                            }
                            MessageDao messageDao1115 = defaultSessionRepository21.messages;
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                            defaultSessionRepository$deliverPending$2.L$7 = null;
                            defaultSessionRepository$deliverPending$2.L$8 = null;
                            defaultSessionRepository$deliverPending$2.L$9 = null;
                            defaultSessionRepository$deliverPending$2.L$10 = null;
                            defaultSessionRepository$deliverPending$2.label = 12;
                            byId = messageDao1115.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                            r25 = r21;
                            str64 = str60;
                            defaultSessionRepository26 = defaultSessionRepository21;
                            if (byId == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            messageEntity = (MessageEntity) byId;
                            if (messageEntity != null) {
                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                            } else {
                                deliveryStatus4 = null;
                            }
                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                Result.Companion companion211110 = Result.Companion;
                                Object objM7388constructorimpl1114 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                return objM7388constructorimpl1114;
                            }
                            th2 = th5;
                            r16 = r25;
                            str47 = str64;
                            defaultSessionRepository25 = defaultSessionRepository26;
                            Result.Companion companion211111 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            r24 = r16;
                            str63 = str47;
                            defaultSessionRepository24 = defaultSessionRepository25;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 11:
                            i2 = 0;
                            i6 = 0;
                            i7 = 0;
                            obj = defaultSessionRepository$deliverPending$2.L$7;
                            th4 = (Throwable) defaultSessionRepository$deliverPending$2.L$6;
                            r18 = (Mutex) defaultSessionRepository$deliverPending$2.L$5;
                            str52 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            str53 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str54 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str55 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository38 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            try {
                                ResultKt.throwOnFailure(obj2);
                                objMo55selectSessiongIAlus2 = ai.nous.aurora.compat.Compat.resultUnbox(obj2);
                                defaultSessionRepository19 = defaultSessionRepository38;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(objMo55selectSessiongIAlus2));
                                r23 = r18;
                                defaultSessionRepository23 = defaultSessionRepository19;
                                str62 = str53;
                                break;
                            } catch (Throwable th36) {
                                defaultSessionRepository19 = defaultSessionRepository38;
                                Result.Companion companion122 = Result.Companion;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th36));
                                r23 = r18;
                                defaultSessionRepository23 = defaultSessionRepository19;
                                str62 = str53;
                                str44 = str52;
                                str43 = str54;
                                value3 = obj;
                                r20 = r23;
                                defaultSessionRepository20 = defaultSessionRepository23;
                                str57 = str62;
                                str29 = str55;
                                th3 = th4;
                                str41 = str43;
                                r22 = r20;
                                str61 = str57;
                                defaultSessionRepository22 = defaultSessionRepository20;
                                ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                                i8 = i2;
                                r21 = r22;
                                th5 = th3;
                                str8 = str29;
                                defaultSessionRepository21 = defaultSessionRepository22;
                                str59 = str41;
                                str58 = str44;
                                str60 = str61;
                                MessageDao messageDao1116 = defaultSessionRepository21.messages;
                                defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                                defaultSessionRepository$deliverPending$2.L$1 = str8;
                                defaultSessionRepository$deliverPending$2.L$2 = str59;
                                defaultSessionRepository$deliverPending$2.L$3 = str60;
                                defaultSessionRepository$deliverPending$2.L$4 = str58;
                                defaultSessionRepository$deliverPending$2.L$5 = r21;
                                defaultSessionRepository$deliverPending$2.L$6 = th5;
                                defaultSessionRepository$deliverPending$2.L$7 = null;
                                defaultSessionRepository$deliverPending$2.L$8 = null;
                                defaultSessionRepository$deliverPending$2.L$9 = null;
                                defaultSessionRepository$deliverPending$2.L$10 = null;
                                defaultSessionRepository$deliverPending$2.label = 12;
                                byId = messageDao1116.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                                r25 = r21;
                                str64 = str60;
                                defaultSessionRepository26 = defaultSessionRepository21;
                                if (byId == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                messageEntity = (MessageEntity) byId;
                                if (messageEntity != null) {
                                    deliveryStatus4 = messageEntity.getDeliveryStatus();
                                } else {
                                    deliveryStatus4 = null;
                                }
                                if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                    Result.Companion companion211112 = Result.Companion;
                                    Object objM7388constructorimpl1115 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                    ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                    defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                    return objM7388constructorimpl1115;
                                }
                                th2 = th5;
                                r16 = r25;
                                str47 = str64;
                                defaultSessionRepository25 = defaultSessionRepository26;
                                Result.Companion companion211113 = Result.Companion;
                                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                                r24 = r16;
                                str63 = str47;
                                defaultSessionRepository24 = defaultSessionRepository25;
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                                defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                                return objM7388constructorimpl;
                            }
                            str44 = str52;
                            str43 = str54;
                            value3 = obj;
                            r20 = r23;
                            defaultSessionRepository20 = defaultSessionRepository23;
                            str57 = str62;
                            str29 = str55;
                            th3 = th4;
                            str41 = str43;
                            r22 = r20;
                            str61 = str57;
                            defaultSessionRepository22 = defaultSessionRepository20;
                            ai.nous.aurora.compat.Compat.m7388constructorimpl(ai.nous.aurora.compat.Compat.m7387boximpl(value3));
                            i8 = i2;
                            r21 = r22;
                            th5 = th3;
                            str8 = str29;
                            defaultSessionRepository21 = defaultSessionRepository22;
                            str59 = str41;
                            str58 = str44;
                            str60 = str61;
                            MessageDao messageDao1117 = defaultSessionRepository21.messages;
                            defaultSessionRepository$deliverPending$2.L$0 = defaultSessionRepository21;
                            defaultSessionRepository$deliverPending$2.L$1 = str8;
                            defaultSessionRepository$deliverPending$2.L$2 = str59;
                            defaultSessionRepository$deliverPending$2.L$3 = str60;
                            defaultSessionRepository$deliverPending$2.L$4 = str58;
                            defaultSessionRepository$deliverPending$2.L$5 = r21;
                            defaultSessionRepository$deliverPending$2.L$6 = th5;
                            defaultSessionRepository$deliverPending$2.L$7 = null;
                            defaultSessionRepository$deliverPending$2.L$8 = null;
                            defaultSessionRepository$deliverPending$2.L$9 = null;
                            defaultSessionRepository$deliverPending$2.L$10 = null;
                            defaultSessionRepository$deliverPending$2.label = 12;
                            byId = messageDao1117.getById(str8, str60, defaultSessionRepository$deliverPending$2);
                            r25 = r21;
                            str64 = str60;
                            defaultSessionRepository26 = defaultSessionRepository21;
                            if (byId == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            messageEntity = (MessageEntity) byId;
                            if (messageEntity != null) {
                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                            } else {
                                deliveryStatus4 = null;
                            }
                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                Result.Companion companion211114 = Result.Companion;
                                Object objM7388constructorimpl1116 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                return objM7388constructorimpl1116;
                            }
                            th2 = th5;
                            r16 = r25;
                            str47 = str64;
                            defaultSessionRepository25 = defaultSessionRepository26;
                            Result.Companion companion211115 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            r24 = r16;
                            str63 = str47;
                            defaultSessionRepository24 = defaultSessionRepository25;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        case 12:
                            i8 = 0;
                            th5 = (Throwable) defaultSessionRepository$deliverPending$2.L$6;
                            Mutex mutex7 = (Mutex) defaultSessionRepository$deliverPending$2.L$5;
                            str58 = (String) defaultSessionRepository$deliverPending$2.L$4;
                            String str92 = (String) defaultSessionRepository$deliverPending$2.L$3;
                            str59 = (String) defaultSessionRepository$deliverPending$2.L$2;
                            str8 = (String) defaultSessionRepository$deliverPending$2.L$1;
                            DefaultSessionRepository defaultSessionRepository39 = (DefaultSessionRepository) defaultSessionRepository$deliverPending$2.L$0;
                            ResultKt.throwOnFailure(obj2);
                            byId = obj2;
                            r25 = mutex7;
                            str64 = str92;
                            defaultSessionRepository26 = defaultSessionRepository39;
                            messageEntity = (MessageEntity) byId;
                            if (messageEntity != null) {
                                deliveryStatus4 = messageEntity.getDeliveryStatus();
                            } else {
                                deliveryStatus4 = null;
                            }
                            if (Intrinsics.areEqual(deliveryStatus4, OutboxExactlyOnce.STATUS_SENT)) {
                                Result.Companion companion211116 = Result.Companion;
                                Object objM7388constructorimpl1117 = ai.nous.aurora.compat.Compat.m7388constructorimpl(new AuroraMessage(str8, "user", str59, OutboxExactlyOnce.STATUS_SENT, str58));
                                ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r25, null, 1, null);
                                defaultSessionRepository26.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str64, str8), r25);
                                return objM7388constructorimpl1117;
                            }
                            th2 = th5;
                            r16 = r25;
                            str47 = str64;
                            defaultSessionRepository25 = defaultSessionRepository26;
                            Result.Companion companion211117 = Result.Companion;
                            objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th2));
                            r24 = r16;
                            str63 = str47;
                            defaultSessionRepository24 = defaultSessionRepository25;
                            ai.nous.aurora.compat.Compat.d_Mutex_DefaultImpls_unlock_4(r24, null, 1, null);
                            defaultSessionRepository24.deliveryLocks.remove(OutboxExactlyOnce.INSTANCE.deliveryKey(str63, str8), r24);
                            return objM7388constructorimpl;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                } catch (Throwable th37) {
                }
            } catch (Throwable th38) {
                r4 = continuation;
                str7 = defaultSessionRepository;
            }
        } catch (Throwable th39) {
            Result.Companion companion40 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th39));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$37(AuroraMessage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getRole();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$38(AuroraMessage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getContent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String deliverPending_bMdYcbs$lambda$43$lambda$42$lambda$41$lambda$39(AuroraMessage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getClientMsgId();
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0066 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x0067  */
    /* JADX WARN: Code duplicated, block: B:24:0x007a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // ai.nous.aurora.data.repository.SessionRepository
    public Object clearLocalCache(Continuation continuation) {
        C02151 c02151;
        DefaultSessionRepository defaultSessionRepository;
        Object objOwnerKey;
        String owner;
        MessageDao messageDao;
        DefaultSessionRepository defaultSessionRepository2;
        String owner2;
        SessionDao sessionDao;
        if (continuation instanceof C02151) {
            c02151 = (C02151) continuation;
            if ((c02151.label & Integer.MIN_VALUE) != 0) {
                c02151.label -= Integer.MIN_VALUE;
            } else {
                c02151 = new C02151(continuation);
            }
        } else {
            c02151 = new C02151(continuation);
        }
        Object $result = c02151.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02151.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                defaultSessionRepository = this;
                c02151.L$0 = defaultSessionRepository;
                c02151.label = 1;
                objOwnerKey = defaultSessionRepository.ownerKey(c02151);
                if (objOwnerKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
                owner = (String) objOwnerKey;
                messageDao = defaultSessionRepository.messages;
                c02151.L$0 = defaultSessionRepository;
                c02151.L$1 = owner;
                c02151.label = 2;
                if (messageDao.deleteForOwner(owner, c02151) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                owner2 = owner;
                sessionDao = defaultSessionRepository2.sessions;
                c02151.L$0 = null;
                c02151.L$1 = null;
                c02151.label = 3;
                if (sessionDao.deleteForOwner(owner2, c02151) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                defaultSessionRepository = (DefaultSessionRepository) c02151.L$0;
                ResultKt.throwOnFailure($result);
                objOwnerKey = $result;
                owner = (String) objOwnerKey;
                messageDao = defaultSessionRepository.messages;
                c02151.L$0 = defaultSessionRepository;
                c02151.L$1 = owner;
                c02151.label = 2;
                if (messageDao.deleteForOwner(owner, c02151) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                defaultSessionRepository2 = defaultSessionRepository;
                owner2 = owner;
                sessionDao = defaultSessionRepository2.sessions;
                c02151.L$0 = null;
                c02151.L$1 = null;
                c02151.label = 3;
                if (sessionDao.deleteForOwner(owner2, c02151) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 2:
                owner2 = (String) c02151.L$1;
                defaultSessionRepository2 = (DefaultSessionRepository) c02151.L$0;
                ResultKt.throwOnFailure($result);
                sessionDao = defaultSessionRepository2.sessions;
                c02151.L$0 = null;
                c02151.L$1 = null;
                c02151.label = 3;
                if (sessionDao.deleteForOwner(owner2, c02151) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 3:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuroraSession toModel(SessionEntity $this$toModel) {
        return new AuroraSession($this$toModel.getId(), $this$toModel.getTitle(), $this$toModel.getSource(), $this$toModel.getModel(), $this$toModel.getMessageCount(), $this$toModel.getLastActiveMs(), $this$toModel.getPreview(), null, 128, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuroraMessage toModel(MessageEntity $this$toModel) {
        return new AuroraMessage($this$toModel.getId(), $this$toModel.getRole(), $this$toModel.getContent(), $this$toModel.getDeliveryStatus(), $this$toModel.getClientMsgId());
    }

    private final SessionEntity toEntity(AuroraSession $this$toEntity, String owner) {
        return new SessionEntity($this$toEntity.getId(), owner, $this$toEntity.getTitle(), $this$toEntity.getSource(), $this$toEntity.getModel(), $this$toEntity.getMessageCount(), ifZeroNow($this$toEntity.getLastActiveMs()), $this$toEntity.getPreview());
    }

    private final MessageEntity toEntity(AuroraMessage $this$toEntity, String sessionId, String owner, long orderHint) {
        String id = $this$toEntity.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(id, "toString(...)");
        }
        String str = id;
        String role = $this$toEntity.getRole();
        String content = $this$toEntity.getContent();
        long jCurrentTimeMillis = System.currentTimeMillis() + orderHint;
        String deliveryStatus = $this$toEntity.getDeliveryStatus();
        if (StringsKt.isBlank(deliveryStatus)) {
            deliveryStatus = OutboxExactlyOnce.STATUS_SENT;
        }
        return new MessageEntity(str, sessionId, owner, role, content, jCurrentTimeMillis, deliveryStatus, $this$toEntity.getClientMsgId());
    }

    static /* synthetic */ MessageEntity toEntity$default(DefaultSessionRepository defaultSessionRepository, AuroraMessage auroraMessage, String str, String str2, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 0;
        }
        return defaultSessionRepository.toEntity(auroraMessage, str, str2, j);
    }

    private final long ifZeroNow(long $this$ifZeroNow) {
        return $this$ifZeroNow <= 0 ? System.currentTimeMillis() : $this$ifZeroNow;
    }
}
