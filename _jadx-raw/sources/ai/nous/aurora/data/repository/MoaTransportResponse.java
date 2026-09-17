package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.MoaConfigDto;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0081\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Lai/nous/aurora/data/repository/MoaTransportResponse;", "", "successful", "", "code", "", "body", "Lai/nous/aurora/data/remote/MoaConfigDto;", "detail", "", "<init>", "(ZILai/nous/aurora/data/remote/MoaConfigDto;Ljava/lang/String;)V", "getSuccessful", "()Z", "getCode", "()I", "getBody", "()Lai/nous/aurora/data/remote/MoaConfigDto;", "getDetail", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class MoaTransportResponse {
    public static final int $stable = 8;
    private final MoaConfigDto body;
    private final int code;
    private final String detail;
    private final boolean successful;

    public static /* synthetic */ MoaTransportResponse copy$default(MoaTransportResponse moaTransportResponse, boolean z, int i, MoaConfigDto moaConfigDto, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = moaTransportResponse.successful;
        }
        if ((i2 & 2) != 0) {
            i = moaTransportResponse.code;
        }
        if ((i2 & 4) != 0) {
            moaConfigDto = moaTransportResponse.body;
        }
        if ((i2 & 8) != 0) {
            str = moaTransportResponse.detail;
        }
        return moaTransportResponse.copy(z, i, moaConfigDto, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getSuccessful() {
        return this.successful;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MoaConfigDto getBody() {
        return this.body;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    public final MoaTransportResponse copy(boolean successful, int code, MoaConfigDto body, String detail) {
        return new MoaTransportResponse(successful, code, body, detail);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoaTransportResponse)) {
            return false;
        }
        MoaTransportResponse moaTransportResponse = (MoaTransportResponse) other;
        return this.successful == moaTransportResponse.successful && this.code == moaTransportResponse.code && Intrinsics.areEqual(this.body, moaTransportResponse.body) && Intrinsics.areEqual(this.detail, moaTransportResponse.detail);
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.successful) * 31) + Integer.hashCode(this.code)) * 31) + (this.body == null ? 0 : this.body.hashCode())) * 31) + (this.detail != null ? this.detail.hashCode() : 0);
    }

    public String toString() {
        return "MoaTransportResponse(successful=" + this.successful + ", code=" + this.code + ", body=" + this.body + ", detail=" + this.detail + ")";
    }

    public MoaTransportResponse(boolean successful, int code, MoaConfigDto body, String detail) {
        this.successful = successful;
        this.code = code;
        this.body = body;
        this.detail = detail;
    }

    public /* synthetic */ MoaTransportResponse(boolean z, int i, MoaConfigDto moaConfigDto, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, i, (i2 & 4) != 0 ? null : moaConfigDto, (i2 & 8) != 0 ? null : str);
    }

    public final boolean getSuccessful() {
        return this.successful;
    }

    public final int getCode() {
        return this.code;
    }

    public final MoaConfigDto getBody() {
        return this.body;
    }

    public final String getDetail() {
        return this.detail;
    }
}
