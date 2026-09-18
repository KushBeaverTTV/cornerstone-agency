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
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: GatewayService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000201BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nBM\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\fHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001J%\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0001¢\u0006\u0002\b/R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0013R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0013¨\u00062"}, d2 = {"Lai/nous/aurora/data/remote/HermesMessage;", "", "id", "", "role", "content", "timestamp", "", "clientMsgId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getRole", "getContent$annotations", "getContent", "getTimestamp", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getClientMsgId$annotations", "getClientMsgId", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)Lai/nous/aurora/data/remote/HermesMessage;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesMessage {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String clientMsgId;
    private final String content;
    private final String id;
    private final String role;
    private final Double timestamp;

    public HermesMessage() {
        this((String) null, (String) null, (String) null, (Double) null, (String) null, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ HermesMessage copy$default(HermesMessage hermesMessage, String str, String str2, String str3, Double d, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hermesMessage.id;
        }
        if ((i & 2) != 0) {
            str2 = hermesMessage.role;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = hermesMessage.content;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            d = hermesMessage.timestamp;
        }
        Double d2 = d;
        if ((i & 16) != 0) {
            str4 = hermesMessage.clientMsgId;
        }
        return hermesMessage.copy(str, str5, str6, d2, str4);
    }


    @Serializable(with = FlexibleStringSerializer.class)


    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Double getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getClientMsgId() {
        return this.clientMsgId;
    }

    public final HermesMessage copy(String id, String role, String content, Double timestamp, String clientMsgId) {
        return new HermesMessage(id, role, content, timestamp, clientMsgId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesMessage)) {
            return false;
        }
        HermesMessage hermesMessage = (HermesMessage) other;
        return Intrinsics.areEqual(this.id, hermesMessage.id) && Intrinsics.areEqual(this.role, hermesMessage.role) && Intrinsics.areEqual(this.content, hermesMessage.content) && Intrinsics.areEqual((Object) this.timestamp, (Object) hermesMessage.timestamp) && Intrinsics.areEqual(this.clientMsgId, hermesMessage.clientMsgId);
    }

    public int hashCode() {
        return ((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.role == null ? 0 : this.role.hashCode())) * 31) + (this.content == null ? 0 : this.content.hashCode())) * 31) + (this.timestamp == null ? 0 : this.timestamp.hashCode())) * 31) + (this.clientMsgId != null ? this.clientMsgId.hashCode() : 0);
    }

    public String toString() {
        return "HermesMessage(id=" + this.id + ", role=" + this.role + ", content=" + this.content + ", timestamp=" + this.timestamp + ", clientMsgId=" + this.clientMsgId + ")";
    }

    /* JADX INFO: compiled from: GatewayService.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesMessage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesMessage;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesMessage> serializer() {
            return HermesMessage$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ HermesMessage(int seen0, String id, String role, String content, Double timestamp, String clientMsgId, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen0 & 1) == 0) {
            this.id = null;
        } else {
            this.id = id;
        }
        if ((seen0 & 2) == 0) {
            this.role = null;
        } else {
            this.role = role;
        }
        if ((seen0 & 4) == 0) {
            this.content = null;
        } else {
            this.content = content;
        }
        if ((seen0 & 8) == 0) {
            this.timestamp = null;
        } else {
            this.timestamp = timestamp;
        }
        if ((seen0 & 16) == 0) {
            this.clientMsgId = null;
        } else {
            this.clientMsgId = clientMsgId;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(HermesMessage self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, FlexibleStringSerializer.INSTANCE, self.id);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.role != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.role);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.content != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, FlexibleStringSerializer.INSTANCE, self.content);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.timestamp != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, DoubleSerializer.INSTANCE, self.timestamp);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.clientMsgId != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.clientMsgId);
        }
    }

    public HermesMessage(String id, String role, String content, Double timestamp, String clientMsgId) {
        this.id = id;
        this.role = role;
        this.content = content;
        this.timestamp = timestamp;
        this.clientMsgId = clientMsgId;
    }

    public /* synthetic */ HermesMessage(String str, String str2, String str3, Double d, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : d, (i & 16) != 0 ? null : str4);
    }




}
