package ai.nous.aurora.data.remote;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: GatewayService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(B/\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tB?\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J%\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\b&R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006)"}, d2 = {"Lai/nous/aurora/data/remote/MessageListResponse;", "", "object", "", "sessionId", "data", "", "Lai/nous/aurora/data/remote/HermesMessage;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getObject", "()Ljava/lang/String;", "getSessionId$annotations", "()V", "getSessionId", "getData", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class MessageListResponse {
    private final List<HermesMessage> data;
    private final String object;
    private final String sessionId;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {null, null, new ArrayListSerializer(HermesMessage$$serializer.INSTANCE)};

    public MessageListResponse() {
        this((String) null, (String) null, (List) null, 7, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MessageListResponse copy$default(MessageListResponse messageListResponse, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = messageListResponse.object;
        }
        if ((i & 2) != 0) {
            str2 = messageListResponse.sessionId;
        }
        if ((i & 4) != 0) {
            list = messageListResponse.data;
        }
        return messageListResponse.copy(str, str2, list);
    }


    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getObject() {
        return this.object;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    public final List<HermesMessage> component3() {
        return this.data;
    }

    public final MessageListResponse copy(String object, String sessionId, List<HermesMessage> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new MessageListResponse(object, sessionId, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessageListResponse)) {
            return false;
        }
        MessageListResponse messageListResponse = (MessageListResponse) other;
        return Intrinsics.areEqual(this.object, messageListResponse.object) && Intrinsics.areEqual(this.sessionId, messageListResponse.sessionId) && Intrinsics.areEqual(this.data, messageListResponse.data);
    }

    public int hashCode() {
        return ((((this.object == null ? 0 : this.object.hashCode()) * 31) + (this.sessionId != null ? this.sessionId.hashCode() : 0)) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "MessageListResponse(object=" + this.object + ", sessionId=" + this.sessionId + ", data=" + this.data + ")";
    }

    /* JADX INFO: compiled from: GatewayService.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/MessageListResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/MessageListResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<MessageListResponse> serializer() {
            return MessageListResponse$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ MessageListResponse(int seen0, String object, String sessionId, List data, SerializationConstructorMarker serializationConstructorMarker) {
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
                    this.data = CollectionsKt.emptyList();
                } else {
                    this.data = data;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(MessageListResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.object != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.object);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.sessionId != null) {
                    output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.sessionId);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.data, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.data);
                }
            }

            public MessageListResponse(String object, String sessionId, List<HermesMessage> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                this.object = object;
                this.sessionId = sessionId;
                this.data = data;
            }

            public /* synthetic */ MessageListResponse(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
            }

            public final List<HermesMessage> getData() {
                return this.data;
            }
        }