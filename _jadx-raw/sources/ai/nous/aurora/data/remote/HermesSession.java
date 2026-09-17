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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: GatewayService.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 @2\u00020\u0001:\u0002?@Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fBu\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0013J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010-\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010.\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\"J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jx\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00101J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\tHÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001J%\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u0015R \u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001f\u0012\u0004\b\u001c\u0010\u001a\u001a\u0004\b\u001d\u0010\u001eR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010#\u0012\u0004\b \u0010\u001a\u001a\u0004\b!\u0010\"R \u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010#\u0012\u0004\b$\u0010\u001a\u001a\u0004\b%\u0010\"R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015¨\u0006A"}, d2 = {"Lai/nous/aurora/data/remote/HermesSession;", "", "id", "", "title", "model", "source", "userId", "messageCount", "", "lastActive", "", "startedAt", "preview", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getTitle", "getModel", "getSource", "getUserId$annotations", "()V", "getUserId", "getMessageCount$annotations", "getMessageCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLastActive$annotations", "getLastActive", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getStartedAt$annotations", "getStartedAt", "getPreview", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;)Lai/nous/aurora/data/remote/HermesSession;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesSession {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String id;
    private final Double lastActive;
    private final Integer messageCount;
    private final String model;
    private final String preview;
    private final String source;
    private final Double startedAt;
    private final String title;
    private final String userId;

    @SerialName("last_active")
    public static /* synthetic */ void getLastActive$annotations() {
    }

    @SerialName("message_count")
    public static /* synthetic */ void getMessageCount$annotations() {
    }

    @SerialName("started_at")
    public static /* synthetic */ void getStartedAt$annotations() {
    }

    @SerialName("user_id")
    public static /* synthetic */ void getUserId$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getMessageCount() {
        return this.messageCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Double getLastActive() {
        return this.lastActive;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Double getStartedAt() {
        return this.startedAt;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPreview() {
        return this.preview;
    }

    public final HermesSession copy(String id, String title, String model, String source, String userId, Integer messageCount, Double lastActive, Double startedAt, String preview) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new HermesSession(id, title, model, source, userId, messageCount, lastActive, startedAt, preview);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesSession)) {
            return false;
        }
        HermesSession hermesSession = (HermesSession) other;
        return Intrinsics.areEqual(this.id, hermesSession.id) && Intrinsics.areEqual(this.title, hermesSession.title) && Intrinsics.areEqual(this.model, hermesSession.model) && Intrinsics.areEqual(this.source, hermesSession.source) && Intrinsics.areEqual(this.userId, hermesSession.userId) && Intrinsics.areEqual(this.messageCount, hermesSession.messageCount) && Intrinsics.areEqual((Object) this.lastActive, (Object) hermesSession.lastActive) && Intrinsics.areEqual((Object) this.startedAt, (Object) hermesSession.startedAt) && Intrinsics.areEqual(this.preview, hermesSession.preview);
    }

    public int hashCode() {
        return (((((((((((((((this.id.hashCode() * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31) + (this.messageCount == null ? 0 : this.messageCount.hashCode())) * 31) + (this.lastActive == null ? 0 : this.lastActive.hashCode())) * 31) + (this.startedAt == null ? 0 : this.startedAt.hashCode())) * 31) + (this.preview != null ? this.preview.hashCode() : 0);
    }

    public String toString() {
        return "HermesSession(id=" + this.id + ", title=" + this.title + ", model=" + this.model + ", source=" + this.source + ", userId=" + this.userId + ", messageCount=" + this.messageCount + ", lastActive=" + this.lastActive + ", startedAt=" + this.startedAt + ", preview=" + this.preview + ")";
    }

    /* JADX INFO: compiled from: GatewayService.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesSession$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesSession;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesSession> serializer() {
            return HermesSession$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ HermesSession(int seen0, String id, String title, String model, String source, String userId, Integer messageCount, Double lastActive, Double startedAt, String preview, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (seen0 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, HermesSession$$serializer.INSTANCE.getDescriptor());
        }
        this.id = id;
        if ((seen0 & 2) == 0) {
            this.title = null;
        } else {
            this.title = title;
        }
        if ((seen0 & 4) == 0) {
            this.model = null;
        } else {
            this.model = model;
        }
        if ((seen0 & 8) == 0) {
            this.source = null;
        } else {
            this.source = source;
        }
        if ((seen0 & 16) == 0) {
            this.userId = null;
        } else {
            this.userId = userId;
        }
        if ((seen0 & 32) == 0) {
            this.messageCount = null;
        } else {
            this.messageCount = messageCount;
        }
        if ((seen0 & 64) == 0) {
            this.lastActive = null;
        } else {
            this.lastActive = lastActive;
        }
        if ((seen0 & 128) == 0) {
            this.startedAt = null;
        } else {
            this.startedAt = startedAt;
        }
        if ((seen0 & 256) == 0) {
            this.preview = null;
        } else {
            this.preview = preview;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(HermesSession self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.id);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.title != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.title);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.model != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.model);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.source != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.source);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.userId != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.userId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.messageCount != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.messageCount);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.lastActive != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, DoubleSerializer.INSTANCE, self.lastActive);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.startedAt != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, DoubleSerializer.INSTANCE, self.startedAt);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.preview != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.preview);
        }
    }

    public HermesSession(String id, String title, String model, String source, String userId, Integer messageCount, Double lastActive, Double startedAt, String preview) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.title = title;
        this.model = model;
        this.source = source;
        this.userId = userId;
        this.messageCount = messageCount;
        this.lastActive = lastActive;
        this.startedAt = startedAt;
        this.preview = preview;
    }

    public /* synthetic */ HermesSession(String str, String str2, String str3, String str4, String str5, Integer num, Double d, Double d2, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : d, (i & 128) != 0 ? null : d2, (i & 256) == 0 ? str6 : null);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getSource() {
        return this.source;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final Integer getMessageCount() {
        return this.messageCount;
    }

    public final Double getLastActive() {
        return this.lastActive;
    }

    public final Double getStartedAt() {
        return this.startedAt;
    }

    public final String getPreview() {
        return this.preview;
    }
}
