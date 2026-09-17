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

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\nHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u000f¨\u0006'"}, d2 = {"Lai/nous/aurora/data/remote/MoaEncodeResponse;", "", "ok", "", "message", "", "onePromptOnly", "<init>", "(ZLjava/lang/String;Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/String;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOk", "()Z", "getMessage", "()Ljava/lang/String;", "getOnePromptOnly$annotations", "()V", "getOnePromptOnly", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class MoaEncodeResponse {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String message;
    private final boolean ok;
    private final boolean onePromptOnly;

    public MoaEncodeResponse() {
        this(false, (String) null, false, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ MoaEncodeResponse copy$default(MoaEncodeResponse moaEncodeResponse, boolean z, String str, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = moaEncodeResponse.ok;
        }
        if ((i & 2) != 0) {
            str = moaEncodeResponse.message;
        }
        if ((i & 4) != 0) {
            z2 = moaEncodeResponse.onePromptOnly;
        }
        return moaEncodeResponse.copy(z, str, z2);
    }


    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getOk() {
        return this.ok;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getOnePromptOnly() {
        return this.onePromptOnly;
    }

    public final MoaEncodeResponse copy(boolean ok, String message, boolean onePromptOnly) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new MoaEncodeResponse(ok, message, onePromptOnly);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoaEncodeResponse)) {
            return false;
        }
        MoaEncodeResponse moaEncodeResponse = (MoaEncodeResponse) other;
        return this.ok == moaEncodeResponse.ok && Intrinsics.areEqual(this.message, moaEncodeResponse.message) && this.onePromptOnly == moaEncodeResponse.onePromptOnly;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.ok) * 31) + this.message.hashCode()) * 31) + Boolean.hashCode(this.onePromptOnly);
    }

    public String toString() {
        return "MoaEncodeResponse(ok=" + this.ok + ", message=" + this.message + ", onePromptOnly=" + this.onePromptOnly + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/MoaEncodeResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/MoaEncodeResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<MoaEncodeResponse> serializer() {
            return MoaEncodeResponse$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ MoaEncodeResponse(int seen0, boolean ok, String message, boolean onePromptOnly, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.ok = false;
                } else {
                    this.ok = ok;
                }
                if ((seen0 & 2) == 0) {
                    this.message = "";
                } else {
                    this.message = message;
                }
                if ((seen0 & 4) == 0) {
                    this.onePromptOnly = true;
                } else {
                    this.onePromptOnly = onePromptOnly;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(MoaEncodeResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.ok) {
                    output.encodeBooleanElement(serialDesc, 0, self.ok);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.message, "")) {
                    output.encodeStringElement(serialDesc, 1, self.message);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || !self.onePromptOnly) {
                    output.encodeBooleanElement(serialDesc, 2, self.onePromptOnly);
                }
            }

            public MoaEncodeResponse(boolean ok, String message, boolean onePromptOnly) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.ok = ok;
                this.message = message;
                this.onePromptOnly = onePromptOnly;
            }

            public /* synthetic */ MoaEncodeResponse(boolean z, String str, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? true : z2);
            }
}