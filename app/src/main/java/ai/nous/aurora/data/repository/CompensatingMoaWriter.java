package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.MoaConfigDto;
import androidx.core.app.NotificationCompat;
import androidx.core.location.LocationRequestCompat;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tJ&\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0015"}, d2 = {"Lai/nous/aurora/data/repository/CompensatingMoaWriter;", "", "<init>", "()V", "saveAndVerify", "Lai/nous/aurora/data/remote/MoaConfigDto;", NotificationCompat.CATEGORY_TRANSPORT, "Lai/nous/aurora/data/repository/MoaWriteTransport;", "requested", "(Lai/nous/aurora/data/repository/MoaWriteTransport;Lai/nous/aurora/data/remote/MoaConfigDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreThenFail", "", "prior", "reason", "", "(Lai/nous/aurora/data/repository/MoaWriteTransport;Lai/nous/aurora/data/remote/MoaConfigDto;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requireBody", "response", "Lai/nous/aurora/data/repository/MoaTransportResponse;", "label", "httpFailure", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CompensatingMoaWriter {
    public static final int $stable = 0;
    public static final CompensatingMoaWriter INSTANCE = new CompensatingMoaWriter();

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.CompensatingMoaWriter$restoreThenFail$1, reason: invalid class name */
    /* JADX INFO: compiled from: SettingsDataContracts.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.CompensatingMoaWriter", f = "SettingsDataContracts.kt", i = {0, 0, 0, 0, 1, 1, 1}, l = {119, WebSocketProtocol.PAYLOAD_SHORT}, m = "restoreThenFail", n = {"this", NotificationCompat.CATEGORY_TRANSPORT, "prior", "reason", "this", "prior", "reason"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CompensatingMoaWriter.this.restoreThenFail(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ai.nous.aurora.data.repository.CompensatingMoaWriter$saveAndVerify$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SettingsDataContracts.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "ai.nous.aurora.data.repository.CompensatingMoaWriter", f = "SettingsDataContracts.kt", i = {0, 0, 0, 1, 1, 1, 1, 3, 3, 3, 3}, l = {94, 95, 101, LocationRequestCompat.QUALITY_LOW_POWER, 109}, m = "saveAndVerify", n = {"this", NotificationCompat.CATEGORY_TRANSPORT, "requested", "this", NotificationCompat.CATEGORY_TRANSPORT, "requested", "prior", "this", NotificationCompat.CATEGORY_TRANSPORT, "requested", "prior"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    final class C02141 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C02141(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CompensatingMoaWriter.this.saveAndVerify(null, null, this);
        }
    }

    private CompensatingMoaWriter() {
    }

    /* JADX WARN: Code duplicated, block: B:23:0x00ac A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:28:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:43:0x0101 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:48:0x011b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:51:0x0122  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object saveAndVerify(MoaWriteTransport transport, MoaConfigDto requested, Continuation continuation) {
        C02141 c02141;
        Object obj;
        CompensatingMoaWriter compensatingMoaWriter;
        MoaWriteTransport transport2;
        CompensatingMoaWriter compensatingMoaWriter2;
        MoaConfigDto prior;
        Object objPut;
        MoaTransportResponse saved;
        MoaConfigDto responseBody;
        Object obj2;
        MoaTransportResponse readback;
        if (continuation instanceof C02141) {
            c02141 = (C02141) continuation;
            if ((c02141.label & Integer.MIN_VALUE) != 0) {
                c02141.label -= Integer.MIN_VALUE;
            } else {
                c02141 = new C02141(continuation);
            }
        } else {
            c02141 = new C02141(continuation);
        }
        Object $result = c02141.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c02141.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                c02141.L$0 = this;
                c02141.L$1 = transport;
                c02141.L$2 = requested;
                c02141.L$3 = this;
                c02141.label = 1;
                obj = transport.get(c02141);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                compensatingMoaWriter = this;
                transport2 = transport;
                compensatingMoaWriter2 = compensatingMoaWriter;
                prior = compensatingMoaWriter2.requireBody((MoaTransportResponse) obj, "MoA pre-write snapshot");
                c02141.L$0 = compensatingMoaWriter;
                c02141.L$1 = transport2;
                c02141.L$2 = requested;
                c02141.L$3 = prior;
                c02141.label = 2;
                objPut = transport2.put(requested, c02141);
                if (objPut == coroutine_suspended) {
                    return coroutine_suspended;
                }
                saved = (MoaTransportResponse) objPut;
                if (!saved.getSuccessful()) {
                    throw new IllegalStateException(compensatingMoaWriter.httpFailure("MoA save", saved).toString());
                }
                responseBody = saved.getBody();
                if (responseBody != null || !MoaWriteContract.INSTANCE.matches(requested, responseBody)) {
                    c02141.L$0 = null;
                    c02141.L$1 = null;
                    c02141.L$2 = null;
                    c02141.L$3 = null;
                    c02141.label = 3;
                    if (compensatingMoaWriter.restoreThenFail(transport2, prior, "MoA PUT response mismatch", c02141) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    throw new KotlinNothingValueException();
                }
                c02141.L$0 = compensatingMoaWriter;
                c02141.L$1 = transport2;
                c02141.L$2 = requested;
                c02141.L$3 = prior;
                c02141.label = 4;
                obj2 = transport2.get(c02141);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                readback = (MoaTransportResponse) obj2;
                MoaConfigDto readbackBody = readback.getBody();
                if (!readback.getSuccessful() && readbackBody != null && MoaWriteContract.INSTANCE.matches(requested, readbackBody)) {
                    return readbackBody;
                }
                c02141.L$0 = null;
                c02141.L$1 = null;
                c02141.L$2 = null;
                c02141.L$3 = null;
                c02141.label = 5;
                if (compensatingMoaWriter.restoreThenFail(transport2, prior, "MoA GET readback mismatch", c02141) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw new KotlinNothingValueException();
            case 1:
                compensatingMoaWriter2 = (CompensatingMoaWriter) c02141.L$3;
                requested = (MoaConfigDto) c02141.L$2;
                transport2 = (MoaWriteTransport) c02141.L$1;
                CompensatingMoaWriter compensatingMoaWriter3 = (CompensatingMoaWriter) c02141.L$0;
                ResultKt.throwOnFailure($result);
                compensatingMoaWriter = compensatingMoaWriter3;
                obj = $result;
                prior = compensatingMoaWriter2.requireBody((MoaTransportResponse) obj, "MoA pre-write snapshot");
                c02141.L$0 = compensatingMoaWriter;
                c02141.L$1 = transport2;
                c02141.L$2 = requested;
                c02141.L$3 = prior;
                c02141.label = 2;
                objPut = transport2.put(requested, c02141);
                if (objPut == coroutine_suspended) {
                    return coroutine_suspended;
                }
                saved = (MoaTransportResponse) objPut;
                if (!saved.getSuccessful()) {
                    throw new IllegalStateException(compensatingMoaWriter.httpFailure("MoA save", saved).toString());
                }
                responseBody = saved.getBody();
                if (responseBody != null) {
                    break;
                }
                c02141.L$0 = null;
                c02141.L$1 = null;
                c02141.L$2 = null;
                c02141.L$3 = null;
                c02141.label = 3;
                if (compensatingMoaWriter.restoreThenFail(transport2, prior, "MoA PUT response mismatch", c02141) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw new KotlinNothingValueException();
            case 2:
                prior = (MoaConfigDto) c02141.L$3;
                requested = (MoaConfigDto) c02141.L$2;
                transport2 = (MoaWriteTransport) c02141.L$1;
                CompensatingMoaWriter compensatingMoaWriter4 = (CompensatingMoaWriter) c02141.L$0;
                ResultKt.throwOnFailure($result);
                compensatingMoaWriter = compensatingMoaWriter4;
                objPut = $result;
                saved = (MoaTransportResponse) objPut;
                if (!saved.getSuccessful()) {
                    throw new IllegalStateException(compensatingMoaWriter.httpFailure("MoA save", saved).toString());
                }
                responseBody = saved.getBody();
                if (responseBody != null) {
                    break;
                }
                c02141.L$0 = null;
                c02141.L$1 = null;
                c02141.L$2 = null;
                c02141.L$3 = null;
                c02141.label = 3;
                if (compensatingMoaWriter.restoreThenFail(transport2, prior, "MoA PUT response mismatch", c02141) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw new KotlinNothingValueException();
            case 3:
                ResultKt.throwOnFailure($result);
                throw new KotlinNothingValueException();
            case 4:
                prior = (MoaConfigDto) c02141.L$3;
                requested = (MoaConfigDto) c02141.L$2;
                transport2 = (MoaWriteTransport) c02141.L$1;
                CompensatingMoaWriter compensatingMoaWriter5 = (CompensatingMoaWriter) c02141.L$0;
                ResultKt.throwOnFailure($result);
                compensatingMoaWriter = compensatingMoaWriter5;
                obj2 = $result;
                readback = (MoaTransportResponse) obj2;
                MoaConfigDto readbackBody2 = readback.getBody();
                if (!readback.getSuccessful()) {
                }
                c02141.L$0 = null;
                c02141.L$1 = null;
                c02141.L$2 = null;
                c02141.L$3 = null;
                c02141.label = 5;
                if (compensatingMoaWriter.restoreThenFail(transport2, prior, "MoA GET readback mismatch", c02141) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw new KotlinNothingValueException();
            case 5:
                ResultKt.throwOnFailure($result);
                throw new KotlinNothingValueException();
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object restoreThenFail(MoaWriteTransport transport, MoaConfigDto prior, String reason, Continuation<?> continuation) {
        AnonymousClass1 anonymousClass1;
        CompensatingMoaWriter compensatingMoaWriter;
        Object objPut;
        MoaWriteTransport transport2;
        String reason2;
        MoaTransportResponse restored;
        Object obj;
        MoaTransportResponse verified;
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
                compensatingMoaWriter = this;
                anonymousClass1.L$0 = compensatingMoaWriter;
                anonymousClass1.L$1 = transport;
                anonymousClass1.L$2 = prior;
                anonymousClass1.L$3 = reason;
                anonymousClass1.label = 1;
                objPut = transport.put(prior, anonymousClass1);
                if (objPut == coroutine_suspended) {
                    return coroutine_suspended;
                }
                transport2 = transport;
                reason2 = reason;
                restored = (MoaTransportResponse) objPut;
                MoaConfigDto restoredBody = restored.getBody();
                if (restored.getSuccessful() || restoredBody == null || !MoaWriteContract.INSTANCE.matches(prior, restoredBody)) {
                    throw new IllegalStateException((reason2 + "; compensating PUT failed (" + compensatingMoaWriter.httpFailure("restore", restored) + ")").toString());
                }
                anonymousClass1.L$0 = compensatingMoaWriter;
                anonymousClass1.L$1 = prior;
                anonymousClass1.L$2 = reason2;
                anonymousClass1.L$3 = null;
                anonymousClass1.label = 2;
                obj = transport2.get(anonymousClass1);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                verified = (MoaTransportResponse) obj;
                MoaConfigDto verifiedBody = verified.getBody();
                if (!verified.getSuccessful() && verifiedBody != null && MoaWriteContract.INSTANCE.matches(prior, verifiedBody)) {
                    throw new IllegalStateException((reason2 + "; prior host snapshot restored").toString());
                }
                throw new IllegalStateException((reason2 + "; compensating PUT could not be verified (" + compensatingMoaWriter.httpFailure("restore readback", verified) + ")").toString());
            case 1:
                reason2 = (String) anonymousClass1.L$3;
                prior = (MoaConfigDto) anonymousClass1.L$2;
                transport2 = (MoaWriteTransport) anonymousClass1.L$1;
                compensatingMoaWriter = (CompensatingMoaWriter) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                objPut = $result;
                restored = (MoaTransportResponse) objPut;
                MoaConfigDto restoredBody2 = restored.getBody();
                if (restored.getSuccessful()) {
                }
                throw new IllegalStateException((reason2 + "; compensating PUT failed (" + compensatingMoaWriter.httpFailure("restore", restored) + ")").toString());
            case 2:
                reason2 = (String) anonymousClass1.L$2;
                prior = (MoaConfigDto) anonymousClass1.L$1;
                CompensatingMoaWriter compensatingMoaWriter2 = (CompensatingMoaWriter) anonymousClass1.L$0;
                ResultKt.throwOnFailure($result);
                compensatingMoaWriter = compensatingMoaWriter2;
                obj = $result;
                verified = (MoaTransportResponse) obj;
                MoaConfigDto verifiedBody2 = verified.getBody();
                if (!verified.getSuccessful()) {
                }
                throw new IllegalStateException((reason2 + "; compensating PUT could not be verified (" + compensatingMoaWriter.httpFailure("restore readback", verified) + ")").toString());
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final MoaConfigDto requireBody(MoaTransportResponse response, String label) {
        MoaConfigDto body = response.getBody();
        if (!response.getSuccessful() || body == null) {
            throw new IllegalStateException(httpFailure(label, response).toString());
        }
        return body;
    }

    private final String httpFailure(String label, MoaTransportResponse response) {
        int code = response.getCode();
        String it = response.getDetail();
        String it2 = it != null ? ": " + it : null;
        if (it2 == null) {
            it2 = "";
        }
        return label + " HTTP " + code + it2;
    }
}
