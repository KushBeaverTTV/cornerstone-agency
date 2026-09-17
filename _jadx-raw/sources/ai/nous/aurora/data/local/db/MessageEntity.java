package ai.nous.aurora.data.local.db;

import ai.nous.aurora.domain.model.OutboxExactlyOnce;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageEntity.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J[\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006'"}, d2 = {"Lai/nous/aurora/data/local/db/MessageEntity;", "", "id", "", "sessionId", "ownerKey", "role", "content", "timestamp", "", "deliveryStatus", "clientMsgId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getSessionId", "getOwnerKey", "getRole", "getContent", "getTimestamp", "()J", "getDeliveryStatus", "getClientMsgId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class MessageEntity {
    public static final int $stable = 0;
    private final String clientMsgId;
    private final String content;
    private final String deliveryStatus;
    private final String id;
    private final String ownerKey;
    private final String role;
    private final String sessionId;
    private final long timestamp;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOwnerKey() {
        return this.ownerKey;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getClientMsgId() {
        return this.clientMsgId;
    }

    public final MessageEntity copy(String id, String sessionId, String ownerKey, String role, String content, long timestamp, String deliveryStatus, String clientMsgId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(ownerKey, "ownerKey");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(deliveryStatus, "deliveryStatus");
        return new MessageEntity(id, sessionId, ownerKey, role, content, timestamp, deliveryStatus, clientMsgId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessageEntity)) {
            return false;
        }
        MessageEntity messageEntity = (MessageEntity) other;
        return Intrinsics.areEqual(this.id, messageEntity.id) && Intrinsics.areEqual(this.sessionId, messageEntity.sessionId) && Intrinsics.areEqual(this.ownerKey, messageEntity.ownerKey) && Intrinsics.areEqual(this.role, messageEntity.role) && Intrinsics.areEqual(this.content, messageEntity.content) && this.timestamp == messageEntity.timestamp && Intrinsics.areEqual(this.deliveryStatus, messageEntity.deliveryStatus) && Intrinsics.areEqual(this.clientMsgId, messageEntity.clientMsgId);
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.sessionId.hashCode()) * 31) + this.ownerKey.hashCode()) * 31) + this.role.hashCode()) * 31) + this.content.hashCode()) * 31) + Long.hashCode(this.timestamp)) * 31) + this.deliveryStatus.hashCode()) * 31) + (this.clientMsgId == null ? 0 : this.clientMsgId.hashCode());
    }

    public String toString() {
        return "MessageEntity(id=" + this.id + ", sessionId=" + this.sessionId + ", ownerKey=" + this.ownerKey + ", role=" + this.role + ", content=" + this.content + ", timestamp=" + this.timestamp + ", deliveryStatus=" + this.deliveryStatus + ", clientMsgId=" + this.clientMsgId + ")";
    }

    public MessageEntity(String id, String sessionId, String ownerKey, String role, String content, long timestamp, String deliveryStatus, String clientMsgId) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(ownerKey, "ownerKey");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(deliveryStatus, "deliveryStatus");
        this.id = id;
        this.sessionId = sessionId;
        this.ownerKey = ownerKey;
        this.role = role;
        this.content = content;
        this.timestamp = timestamp;
        this.deliveryStatus = deliveryStatus;
        this.clientMsgId = clientMsgId;
    }

    public /* synthetic */ MessageEntity(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "" : str3, str4, str5, (i & 32) != 0 ? System.currentTimeMillis() : j, (i & 64) != 0 ? OutboxExactlyOnce.STATUS_SENT : str6, (i & 128) != 0 ? null : str7);
    }

    public final String getId() {
        return this.id;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getOwnerKey() {
        return this.ownerKey;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getContent() {
        return this.content;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public final String getClientMsgId() {
        return this.clientMsgId;
    }
}
