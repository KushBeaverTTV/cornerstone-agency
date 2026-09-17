package ai.nous.aurora.data.remote;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: GatewayService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\nHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006("}, d2 = {"Lai/nous/aurora/data/remote/SessionChatResponse;", "", "object", "", "sessionId", "message", "Lai/nous/aurora/data/remote/HermesMessage;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lai/nous/aurora/data/remote/HermesMessage;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lai/nous/aurora/data/remote/HermesMessage;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getObject", "()Ljava/lang/String;", "getSessionId$annotations", "()V", "getSessionId", "getMessage", "()Lai/nous/aurora/data/remote/HermesMessage;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class SessionChatResponse {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HermesMessage message;
    private final String object;
    private final String sessionId;

    public SessionChatResponse() {
        this((String) null, (String) null, (HermesMessage) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ SessionChatResponse copy$default(SessionChatResponse sessionChatResponse, String str, String str2, HermesMessage hermesMessage, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sessionChatResponse.object;
        }
        if ((i & 2) != 0) {
            str2 = sessionChatResponse.sessionId;
        }
        if ((i & 4) != 0) {
            hermesMessage = sessionChatResponse.message;
        }
        return sessionChatResponse.copy(str, str2, hermesMessage);
    }


    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getObject() {
        return this.object;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final HermesMessage getMessage() {
        return this.message;
    }

    public final SessionChatResponse copy(String object, String sessionId, HermesMessage message) {
        return new SessionChatResponse(object, sessionId, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionChatResponse)) {
            return false;
        }
        SessionChatResponse sessionChatResponse = (SessionChatResponse) other;
        return Intrinsics.areEqual(this.object, sessionChatResponse.object) && Intrinsics.areEqual(this.sessionId, sessionChatResponse.sessionId) && Intrinsics.areEqual(this.message, sessionChatResponse.message);
    }

    public int hashCode() {
        return ((((this.object == null ? 0 : this.object.hashCode()) * 31) + (this.sessionId == null ? 0 : this.sessionId.hashCode())) * 31) + (this.message != null ? this.message.hashCode() : 0);
    }

    public String toString() {
        return "SessionChatResponse(object=" + this.object + ", sessionId=" + this.sessionId + ", message=" + this.message + ")";
    }

    /* JADX INFO: compiled from: GatewayService.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/SessionChatResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/SessionChatResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<SessionChatResponse> serializer() {
            return SessionChatResponse$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ SessionChatResponse(int seen0, String object, String sessionId, HermesMessage message, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.object = null;
                } else {
                    this.object = object;
                }
                if ((seen0 & 2) == 0) {
                    this.sessionId = null;
                } else {
                    this.sessionId = sessionId;
                }
                if ((seen0 & 4) == 0) {
                    this.message = null;
                } else {
                    this.message = message;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(SessionChatResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.object != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.object);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.sessionId != null) {
                    output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.sessionId);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || self.message != null) {
                    output.encodeNullableSerializableElement(serialDesc, 2, HermesMessage$$serializer.INSTANCE, self.message);
                }
            }

            public SessionChatResponse(String object, String sessionId, HermesMessage message) {
                this.object = object;
                this.sessionId = sessionId;
                this.message = message;
            }

            public /* synthetic */ SessionChatResponse(String str, String str2, HermesMessage hermesMessage, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : hermesMessage);
            }
}