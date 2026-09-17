package ai.nous.aurora.data.local.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionEntity.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J_\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\tHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006("}, d2 = {"Lai/nous/aurora/data/local/db/SessionEntity;", "", "id", "", "ownerKey", "title", "source", "model", "messageCount", "", "lastActiveMs", "", "preview", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getOwnerKey", "getTitle", "getSource", "getModel", "getMessageCount", "()I", "getLastActiveMs", "()J", "getPreview", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SessionEntity {
    public static final int $stable = 0;
    public final String id;
    public final long lastActiveMs;
    public final int messageCount;
    public final String model;
    public final String ownerKey;
    public final String preview;
    public final String source;
    public final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getOwnerKey() {
        return this.ownerKey;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getMessageCount() {
        return this.messageCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getLastActiveMs() {
        return this.lastActiveMs;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPreview() {
        return this.preview;
    }

    public final SessionEntity copy(String id, String ownerKey, String title, String source, String model, int messageCount, long lastActiveMs, String preview) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(ownerKey, "ownerKey");
        Intrinsics.checkNotNullParameter(title, "title");
        return new SessionEntity(id, ownerKey, title, source, model, messageCount, lastActiveMs, preview);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionEntity)) {
            return false;
        }
        SessionEntity sessionEntity = (SessionEntity) other;
        return Intrinsics.areEqual(this.id, sessionEntity.id) && Intrinsics.areEqual(this.ownerKey, sessionEntity.ownerKey) && Intrinsics.areEqual(this.title, sessionEntity.title) && Intrinsics.areEqual(this.source, sessionEntity.source) && Intrinsics.areEqual(this.model, sessionEntity.model) && this.messageCount == sessionEntity.messageCount && this.lastActiveMs == sessionEntity.lastActiveMs && Intrinsics.areEqual(this.preview, sessionEntity.preview);
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.ownerKey.hashCode()) * 31) + this.title.hashCode()) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31) + Integer.hashCode(this.messageCount)) * 31) + Long.hashCode(this.lastActiveMs)) * 31) + (this.preview != null ? this.preview.hashCode() : 0);
    }

    public String toString() {
        return "SessionEntity(id=" + this.id + ", ownerKey=" + this.ownerKey + ", title=" + this.title + ", source=" + this.source + ", model=" + this.model + ", messageCount=" + this.messageCount + ", lastActiveMs=" + this.lastActiveMs + ", preview=" + this.preview + ")";
    }

    public SessionEntity(String id, String ownerKey, String title, String source, String model, int messageCount, long lastActiveMs, String preview) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(ownerKey, "ownerKey");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.ownerKey = ownerKey;
        this.title = title;
        this.source = source;
        this.model = model;
        this.messageCount = messageCount;
        this.lastActiveMs = lastActiveMs;
        this.preview = preview;
    }

    public /* synthetic */ SessionEntity(String str, String str2, String str3, String str4, String str5, int i, long j, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) == 0 ? str3 : "", (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? 0 : i, (i2 & 64) != 0 ? 0L : j, (i2 & 128) == 0 ? str6 : null);
    }




}
