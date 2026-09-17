package ai.nous.aurora.data.remote;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B-\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0004\b\b\u0010\tBA\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J/\u0010\u0017\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0004HÖ\u0001J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lai/nous/aurora/data/remote/ConfigSchemaResponse;", "", "fields", "", "", "Lai/nous/aurora/data/remote/ConfigFieldMeta;", "categoryOrder", "", "<init>", "(Ljava/util/Map;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFields", "()Ljava/util/Map;", "getCategoryOrder$annotations", "()V", "getCategoryOrder", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ConfigSchemaResponse {
    private final List<String> categoryOrder;
    private final Map<String, ConfigFieldMeta> fields;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {new LinkedHashMapSerializer(StringSerializer.INSTANCE, ConfigFieldMeta$$serializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public ConfigSchemaResponse() {
        this((Map) null, (List) (null), 3, (DefaultConstructorMarker) (null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConfigSchemaResponse copy$default(ConfigSchemaResponse configSchemaResponse, Map map, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            map = configSchemaResponse.fields;
        }
        if ((i & 2) != 0) {
            list = configSchemaResponse.categoryOrder;
        }
        return configSchemaResponse.copy(map, list);
    }


    public final Map<String, ConfigFieldMeta> component1() {
        return this.fields;
    }

    public final List<String> component2() {
        return this.categoryOrder;
    }

    public final ConfigSchemaResponse copy(Map<String, ConfigFieldMeta> fields, List<String> categoryOrder) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
        return new ConfigSchemaResponse(fields, categoryOrder);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigSchemaResponse)) {
            return false;
        }
        ConfigSchemaResponse configSchemaResponse = (ConfigSchemaResponse) other;
        return Intrinsics.areEqual(this.fields, configSchemaResponse.fields) && Intrinsics.areEqual(this.categoryOrder, configSchemaResponse.categoryOrder);
    }

    public int hashCode() {
        return (this.fields.hashCode() * 31) + this.categoryOrder.hashCode();
    }

    public String toString() {
        return "ConfigSchemaResponse(fields=" + this.fields + ", categoryOrder=" + this.categoryOrder + ")";
    }

    /* JADX INFO: compiled from: HermesConfigModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/ConfigSchemaResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/ConfigSchemaResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<ConfigSchemaResponse> serializer() {
            return ConfigSchemaResponse$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ ConfigSchemaResponse(int seen0, Map fields, List categoryOrder, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.fields = MapsKt.emptyMap();
                } else {
                    this.fields = fields;
                }
                if ((seen0 & 2) == 0) {
                    this.categoryOrder = CollectionsKt.emptyList();
                } else {
                    this.categoryOrder = categoryOrder;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(ConfigSchemaResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.fields, MapsKt.emptyMap())) {
                    output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.fields);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.categoryOrder, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.categoryOrder);
                }
            }

            public ConfigSchemaResponse(Map<String, ConfigFieldMeta> fields, List<String> categoryOrder) {
                Intrinsics.checkNotNullParameter(fields, "fields");
                Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
                this.fields = fields;
                this.categoryOrder = categoryOrder;
            }

            public /* synthetic */ ConfigSchemaResponse(Map map, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? MapsKt.emptyMap() : map, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
            }

            public final Map<String, ConfigFieldMeta> getFields() {
                return this.fields;
            }

            public final List<String> getCategoryOrder() {
                return this.categoryOrder;
            }
        }