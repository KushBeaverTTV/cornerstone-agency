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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000201BE\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fBU\b\u0010\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\u0015\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001bJL\u0010!\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\nHÖ\u0001J\t\u0010'\u001a\u00020\u0004HÖ\u0001J%\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0001¢\u0006\u0002\b/R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001c\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u001b¨\u00062"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigSchemaDto;", "", "fields", "", "", "Lai/nous/aurora/data/remote/HermesConfigFieldDto;", "categoryOrder", "", "source", "fieldCount", "", "<init>", "(Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFields", "()Ljava/util/Map;", "getCategoryOrder$annotations", "()V", "getCategoryOrder", "()Ljava/util/List;", "getSource", "()Ljava/lang/String;", "getFieldCount$annotations", "getFieldCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "copy", "(Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;)Lai/nous/aurora/data/remote/HermesConfigSchemaDto;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesConfigSchemaDto {
    private final List<String> categoryOrder;
    private final Integer fieldCount;
    private final Map<String, HermesConfigFieldDto> fields;
    private final String source;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {new LinkedHashMapSerializer(StringSerializer.INSTANCE, HermesConfigFieldDto$$serializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE), null, null};

    public HermesConfigSchemaDto() {
        this((Map) null, (List) null, (String) null, (Integer) null, 15, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HermesConfigSchemaDto copy$default(HermesConfigSchemaDto hermesConfigSchemaDto, Map map, List list, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            map = hermesConfigSchemaDto.fields;
        }
        if ((i & 2) != 0) {
            list = hermesConfigSchemaDto.categoryOrder;
        }
        if ((i & 4) != 0) {
            str = hermesConfigSchemaDto.source;
        }
        if ((i & 8) != 0) {
            num = hermesConfigSchemaDto.fieldCount;
        }
        return hermesConfigSchemaDto.copy(map, list, str, num);
    }



    public final Map<String, HermesConfigFieldDto> component1() {
        return this.fields;
    }

    public final List<String> component2() {
        return this.categoryOrder;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getFieldCount() {
        return this.fieldCount;
    }

    public final HermesConfigSchemaDto copy(Map<String, HermesConfigFieldDto> fields, List<String> categoryOrder, String source, Integer fieldCount) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
        return new HermesConfigSchemaDto(fields, categoryOrder, source, fieldCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesConfigSchemaDto)) {
            return false;
        }
        HermesConfigSchemaDto hermesConfigSchemaDto = (HermesConfigSchemaDto) other;
        return Intrinsics.areEqual(this.fields, hermesConfigSchemaDto.fields) && Intrinsics.areEqual(this.categoryOrder, hermesConfigSchemaDto.categoryOrder) && Intrinsics.areEqual(this.source, hermesConfigSchemaDto.source) && Intrinsics.areEqual(this.fieldCount, hermesConfigSchemaDto.fieldCount);
    }

    public int hashCode() {
        return (((((this.fields.hashCode() * 31) + this.categoryOrder.hashCode()) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31) + (this.fieldCount != null ? this.fieldCount.hashCode() : 0);
    }

    public String toString() {
        return "HermesConfigSchemaDto(fields=" + this.fields + ", categoryOrder=" + this.categoryOrder + ", source=" + this.source + ", fieldCount=" + this.fieldCount + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigSchemaDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesConfigSchemaDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesConfigSchemaDto> serializer() {
            return HermesConfigSchemaDto$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ HermesConfigSchemaDto(int seen0, Map fields, List categoryOrder, String source, Integer fieldCount, SerializationConstructorMarker serializationConstructorMarker) {
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
                if ((seen0 & 4) == 0) {
                    this.source = null;
                } else {
                    this.source = source;
                }
                if ((seen0 & 8) == 0) {
                    this.fieldCount = null;
                } else {
                    this.fieldCount = fieldCount;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(HermesConfigSchemaDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.fields, MapsKt.emptyMap())) {
                    output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.fields);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.categoryOrder, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.categoryOrder);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || self.source != null) {
                    output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.source);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 3) || self.fieldCount != null) {
                    output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.fieldCount);
                }
            }

            public HermesConfigSchemaDto(Map<String, HermesConfigFieldDto> fields, List<String> categoryOrder, String source, Integer fieldCount) {
                Intrinsics.checkNotNullParameter(fields, "fields");
                Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
                this.fields = fields;
                this.categoryOrder = categoryOrder;
                this.source = source;
                this.fieldCount = fieldCount;
            }

            public /* synthetic */ HermesConfigSchemaDto(Map map, List list, String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? MapsKt.emptyMap() : map, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : num);
            }

            public final Map<String, HermesConfigFieldDto> getFields() {
                return this.fields;
            }

            public final List<String> getCategoryOrder() {
                return this.categoryOrder;
            }
}