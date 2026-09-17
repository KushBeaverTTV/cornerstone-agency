package ai.nous.aurora.data.remote;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 .2\u00020\u0001:\u0002-.BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBS\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0018JP\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\rHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001J%\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018¨\u0006/"}, d2 = {"Lai/nous/aurora/data/remote/ConfigFieldMeta;", "", "type", "", "description", "category", "options", "", "sensitive", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getType", "()Ljava/lang/String;", "getDescription", "getCategory", "getOptions", "()Ljava/util/List;", "getSensitive", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)Lai/nous/aurora/data/remote/ConfigFieldMeta;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ConfigFieldMeta {
    private final String category;
    private final String description;
    private final List<String> options;
    private final Boolean sensitive;
    private final String type;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE), null};

    public ConfigFieldMeta() {
        this((String) null, (String) null, (String) null, (List) null, (Boolean) null, 31, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConfigFieldMeta copy$default(ConfigFieldMeta configFieldMeta, String str, String str2, String str3, List list, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = configFieldMeta.type;
        }
        if ((i & 2) != 0) {
            str2 = configFieldMeta.description;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = configFieldMeta.category;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            list = configFieldMeta.options;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            bool = configFieldMeta.sensitive;
        }
        return configFieldMeta.copy(str, str4, str5, list2, bool);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    public final List<String> component4() {
        return this.options;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getSensitive() {
        return this.sensitive;
    }

    public final ConfigFieldMeta copy(String type, String description, String category, List<String> options, Boolean sensitive) {
        return new ConfigFieldMeta(type, description, category, options, sensitive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigFieldMeta)) {
            return false;
        }
        ConfigFieldMeta configFieldMeta = (ConfigFieldMeta) other;
        return Intrinsics.areEqual(this.type, configFieldMeta.type) && Intrinsics.areEqual(this.description, configFieldMeta.description) && Intrinsics.areEqual(this.category, configFieldMeta.category) && Intrinsics.areEqual(this.options, configFieldMeta.options) && Intrinsics.areEqual(this.sensitive, configFieldMeta.sensitive);
    }

    public int hashCode() {
        return ((((((((this.type == null ? 0 : this.type.hashCode()) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.category == null ? 0 : this.category.hashCode())) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31) + (this.sensitive != null ? this.sensitive.hashCode() : 0);
    }

    public String toString() {
        return "ConfigFieldMeta(type=" + this.type + ", description=" + this.description + ", category=" + this.category + ", options=" + this.options + ", sensitive=" + this.sensitive + ")";
    }

    /* JADX INFO: compiled from: HermesConfigModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/ConfigFieldMeta$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/ConfigFieldMeta;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<ConfigFieldMeta> serializer() {
            return ConfigFieldMeta$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ ConfigFieldMeta(int seen0, String type, String description, String category, List options, Boolean sensitive, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen0 & 1) == 0) {
            this.type = null;
        } else {
            this.type = type;
        }
        if ((seen0 & 2) == 0) {
            this.description = null;
        } else {
            this.description = description;
        }
        if ((seen0 & 4) == 0) {
            this.category = null;
        } else {
            this.category = category;
        }
        if ((seen0 & 8) == 0) {
            this.options = null;
        } else {
            this.options = options;
        }
        if ((seen0 & 16) == 0) {
            this.sensitive = null;
        } else {
            this.sensitive = sensitive;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(ConfigFieldMeta self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.type != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.type);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.description != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.description);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.category != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.category);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.options != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, kSerializerArr[3], self.options);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.sensitive != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, BooleanSerializer.INSTANCE, self.sensitive);
        }
    }

    public ConfigFieldMeta(String type, String description, String category, List<String> list, Boolean sensitive) {
        this.type = type;
        this.description = description;
        this.category = category;
        this.options = list;
        this.sensitive = sensitive;
    }

    public /* synthetic */ ConfigFieldMeta(String str, String str2, String str3, List list, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : bool);
    }

    public final String getType() {
        return this.type;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCategory() {
        return this.category;
    }

    public final List<String> getOptions() {
        return this.options;
    }

    public final Boolean getSensitive() {
        return this.sensitive;
    }
}
