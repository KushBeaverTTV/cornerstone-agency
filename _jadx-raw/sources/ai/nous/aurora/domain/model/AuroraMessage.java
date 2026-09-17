package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AuroraMessage.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J?\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0014\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0016\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0017\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0012¨\u0006$"}, d2 = {"Lai/nous/aurora/domain/model/AuroraMessage;", "", "id", "", "role", "content", "deliveryStatus", "clientMsgId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getRole", "getContent", "getDeliveryStatus", "getClientMsgId", "isPending", "", "()Z", "isFailed", "isAmbiguous", "isOutbox", "isLocalBubble", "canRetry", "getCanRetry", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuroraMessage {
    public static final int $stable = 0;
    private final String clientMsgId;
    private final String content;
    private final String deliveryStatus;
    private final String id;
    private final String role;

    public static /* synthetic */ AuroraMessage copy$default(AuroraMessage auroraMessage, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = auroraMessage.id;
        }
        if ((i & 2) != 0) {
            str2 = auroraMessage.role;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = auroraMessage.content;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = auroraMessage.deliveryStatus;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = auroraMessage.clientMsgId;
        }
        return auroraMessage.copy(str, str6, str7, str8, str5);
    }

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
    public final String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getClientMsgId() {
        return this.clientMsgId;
    }

    public final AuroraMessage copy(String id, String role, String content, String deliveryStatus, String clientMsgId) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(deliveryStatus, "deliveryStatus");
        return new AuroraMessage(id, role, content, deliveryStatus, clientMsgId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuroraMessage)) {
            return false;
        }
        AuroraMessage auroraMessage = (AuroraMessage) other;
        return Intrinsics.areEqual(this.id, auroraMessage.id) && Intrinsics.areEqual(this.role, auroraMessage.role) && Intrinsics.areEqual(this.content, auroraMessage.content) && Intrinsics.areEqual(this.deliveryStatus, auroraMessage.deliveryStatus) && Intrinsics.areEqual(this.clientMsgId, auroraMessage.clientMsgId);
    }

    public int hashCode() {
        return ((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + this.role.hashCode()) * 31) + this.content.hashCode()) * 31) + this.deliveryStatus.hashCode()) * 31) + (this.clientMsgId != null ? this.clientMsgId.hashCode() : 0);
    }

    public String toString() {
        return "AuroraMessage(id=" + this.id + ", role=" + this.role + ", content=" + this.content + ", deliveryStatus=" + this.deliveryStatus + ", clientMsgId=" + this.clientMsgId + ")";
    }

    public AuroraMessage(String id, String role, String content, String deliveryStatus, String clientMsgId) {
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(deliveryStatus, "deliveryStatus");
        this.id = id;
        this.role = role;
        this.content = content;
        this.deliveryStatus = deliveryStatus;
        this.clientMsgId = clientMsgId;
    }

    public /* synthetic */ AuroraMessage(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, str3, (i & 8) != 0 ? OutboxExactlyOnce.STATUS_SENT : str4, (i & 16) != 0 ? null : str5);
    }

    public final String getId() {
        return this.id;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public final String getClientMsgId() {
        return this.clientMsgId;
    }

    public final boolean isPending() {
        return Intrinsics.areEqual(this.deliveryStatus, OutboxExactlyOnce.STATUS_PENDING) || Intrinsics.areEqual(this.deliveryStatus, OutboxExactlyOnce.STATUS_SENDING);
    }

    public final boolean isFailed() {
        return Intrinsics.areEqual(this.deliveryStatus, OutboxExactlyOnce.STATUS_FAILED);
    }

    public final boolean isAmbiguous() {
        return Intrinsics.areEqual(this.deliveryStatus, OutboxExactlyOnce.STATUS_AMBIGUOUS);
    }

    public final boolean isOutbox() {
        return OutboxExactlyOnce.INSTANCE.isOutboxStatus(this.deliveryStatus);
    }

    public final boolean isLocalBubble() {
        String str = this.id;
        return str != null && StringsKt.startsWith$default(str, "local-", false, 2, (Object) null);
    }

    public final boolean getCanRetry() {
        return OutboxExactlyOnce.INSTANCE.retryActionAllowed(this.deliveryStatus);
    }
}
