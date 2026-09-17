package ai.nous.aurora.data.remote;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;

/* JADX INFO: compiled from: HermesConfigModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\tHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lai/nous/aurora/data/remote/ConfigSetRequest;", "", "path", "", "value", "Lkotlinx/serialization/json/JsonElement;", "<init>", "(Ljava/lang/String;Lkotlinx/serialization/json/JsonElement;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPath", "()Ljava/lang/String;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ConfigSetRequest {
    private final String path;
    private final JsonElement value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ ConfigSetRequest copy$default(ConfigSetRequest configSetRequest, String str, JsonElement jsonElement, int i, Object obj) {
        if ((i & 1) != 0) {
            str = configSetRequest.path;
        }
        if ((i & 2) != 0) {
            jsonElement = configSetRequest.value;
        }
        return configSetRequest.copy(str, jsonElement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final JsonElement getValue() {
        return this.value;
    }

    public final ConfigSetRequest copy(String path, JsonElement value) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new ConfigSetRequest(path, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigSetRequest)) {
            return false;
        }
        ConfigSetRequest configSetRequest = (ConfigSetRequest) other;
        return Intrinsics.areEqual(this.path, configSetRequest.path) && Intrinsics.areEqual(this.value, configSetRequest.value);
    }

    public int hashCode() {
        return (this.path.hashCode() * 31) + (this.value == null ? 0 : this.value.hashCode());
    }

    public String toString() {
        return "ConfigSetRequest(path=" + this.path + ", value=" + this.value + ")";
    }

    /* JADX INFO: compiled from: HermesConfigModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/ConfigSetRequest$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/ConfigSetRequest;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<ConfigSetRequest> serializer() {
            return ConfigSetRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ ConfigSetRequest(int seen0, String path, JsonElement value, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (seen0 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1, ConfigSetRequest$$serializer.INSTANCE.getDescriptor());
        }
        this.path = path;
        if ((seen0 & 2) == 0) {
            this.value = null;
        } else {
            this.value = value;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(ConfigSetRequest self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.path);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.value != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, JsonElementSerializer.INSTANCE, self.value);
        }
    }

    public ConfigSetRequest(String path, JsonElement value) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.path = path;
        this.value = value;
    }

    public /* synthetic */ ConfigSetRequest(String str, JsonElement jsonElement, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : jsonElement);
    }

    public final String getPath() {
        return this.path;
    }

    public final JsonElement getValue() {
        return this.value;
    }
}
