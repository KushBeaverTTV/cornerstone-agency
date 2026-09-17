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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: GatewayService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rBS\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\f\u0010\u0011J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u0010#\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001dJN\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\bHÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0019\u0010\u0017R \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001e\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u00064"}, d2 = {"Lai/nous/aurora/data/remote/SessionListResponse;", "", "object", "", "data", "", "Lai/nous/aurora/data/remote/HermesSession;", "limit", "", "offset", "hasMore", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getObject", "()Ljava/lang/String;", "getData", "()Ljava/util/List;", "getLimit", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOffset", "getHasMore$annotations", "()V", "getHasMore", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)Lai/nous/aurora/data/remote/SessionListResponse;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class SessionListResponse {
    private final List<HermesSession> data;
    private final Boolean hasMore;
    private final Integer limit;
    private final String object;
    private final Integer offset;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {null, new ArrayListSerializer(HermesSession$$serializer.INSTANCE), null, null, null};

    public SessionListResponse() {
        this((String) null, (List) null, (Integer) null, (Integer) null, (Boolean) null, 31, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SessionListResponse copy$default(SessionListResponse sessionListResponse, String str, List list, Integer num, Integer num2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sessionListResponse.object;
        }
        if ((i & 2) != 0) {
            list = sessionListResponse.data;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            num = sessionListResponse.limit;
        }
        Integer num3 = num;
        if ((i & 8) != 0) {
            num2 = sessionListResponse.offset;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            bool = sessionListResponse.hasMore;
        }
        return sessionListResponse.copy(str, list2, num3, num4, bool);
    }


    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getObject() {
        return this.object;
    }

    public final List<HermesSession> component2() {
        return this.data;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getLimit() {
        return this.limit;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getHasMore() {
        return this.hasMore;
    }

    public final SessionListResponse copy(String object, List<HermesSession> data, Integer limit, Integer offset, Boolean hasMore) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new SessionListResponse(object, data, limit, offset, hasMore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionListResponse)) {
            return false;
        }
        SessionListResponse sessionListResponse = (SessionListResponse) other;
        return Intrinsics.areEqual(this.object, sessionListResponse.object) && Intrinsics.areEqual(this.data, sessionListResponse.data) && Intrinsics.areEqual(this.limit, sessionListResponse.limit) && Intrinsics.areEqual(this.offset, sessionListResponse.offset) && Intrinsics.areEqual(this.hasMore, sessionListResponse.hasMore);
    }

    public int hashCode() {
        return ((((((((this.object == null ? 0 : this.object.hashCode()) * 31) + this.data.hashCode()) * 31) + (this.limit == null ? 0 : this.limit.hashCode())) * 31) + (this.offset == null ? 0 : this.offset.hashCode())) * 31) + (this.hasMore != null ? this.hasMore.hashCode() : 0);
    }

    public String toString() {
        return "SessionListResponse(object=" + this.object + ", data=" + this.data + ", limit=" + this.limit + ", offset=" + this.offset + ", hasMore=" + this.hasMore + ")";
    }

    /* JADX INFO: compiled from: GatewayService.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/SessionListResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/SessionListResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<SessionListResponse> serializer() {
            return SessionListResponse$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ SessionListResponse(int seen0, String object, List data, Integer limit, Integer offset, Boolean hasMore, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.object = null;
                } else {
                    this.object = object;
                }
                if ((seen0 & 2) == 0) {
                    this.data = CollectionsKt.emptyList();
                } else {
                    this.data = data;
                }
                if ((seen0 & 4) == 0) {
                    this.limit = null;
                } else {
                    this.limit = limit;
                }
                if ((seen0 & 8) == 0) {
                    this.offset = null;
                } else {
                    this.offset = offset;
                }
                if ((seen0 & 16) == 0) {
                    this.hasMore = null;
                } else {
                    this.hasMore = hasMore;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(SessionListResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.object != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.object);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.data, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.data);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || self.limit != null) {
                    output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.limit);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 3) || self.offset != null) {
                    output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.offset);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 4) || self.hasMore != null) {
                    output.encodeNullableSerializableElement(serialDesc, 4, BooleanSerializer.INSTANCE, self.hasMore);
                }
            }

            public SessionListResponse(String object, List<HermesSession> data, Integer limit, Integer offset, Boolean hasMore) {
                Intrinsics.checkNotNullParameter(data, "data");
                this.object = object;
                this.data = data;
                this.limit = limit;
                this.offset = offset;
                this.hasMore = hasMore;
            }

            public /* synthetic */ SessionListResponse(String str, List list, Integer num, Integer num2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : bool);
            }

            public final List<HermesSession> getData() {
                return this.data;
            }
}