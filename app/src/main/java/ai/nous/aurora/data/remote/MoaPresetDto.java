package ai.nous.aurora.data.remote;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 D2\u00020\u0001:\u0002CDBe\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011Bm\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0010\u0010\u0015J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00101\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010 J\t\u00102\u001a\u00020\fHÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010)J\u000b\u00104\u001a\u0004\u0018\u00010\u000fHÆ\u0003Jl\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\u00032\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\fHÖ\u0001J\t\u0010:\u001a\u00020\u000fHÖ\u0001J%\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0001¢\u0006\u0002\bBR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR \u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010!\u0012\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001f\u0010 R \u0010\n\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010!\u0012\u0004\b\"\u0010\u0019\u001a\u0004\b#\u0010 R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0019\u001a\u0004\b%\u0010&R \u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010*\u0012\u0004\b'\u0010\u0019\u001a\u0004\b(\u0010)R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006E"}, d2 = {"Lai/nous/aurora/data/remote/MoaPresetDto;", "", "enabled", "", "referenceModels", "", "Lai/nous/aurora/data/remote/MoaModelSlotDto;", "aggregator", "referenceTemperature", "", "aggregatorTemperature", "maxTokens", "", "referenceMaxTokens", "fanout", "", "<init>", "(ZLjava/util/List;Lai/nous/aurora/data/remote/MoaModelSlotDto;Ljava/lang/Double;Ljava/lang/Double;ILjava/lang/Integer;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/util/List;Lai/nous/aurora/data/remote/MoaModelSlotDto;Ljava/lang/Double;Ljava/lang/Double;ILjava/lang/Integer;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEnabled", "()Z", "getReferenceModels$annotations", "()V", "getReferenceModels", "()Ljava/util/List;", "getAggregator", "()Lai/nous/aurora/data/remote/MoaModelSlotDto;", "getReferenceTemperature$annotations", "getReferenceTemperature", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getAggregatorTemperature$annotations", "getAggregatorTemperature", "getMaxTokens$annotations", "getMaxTokens", "()I", "getReferenceMaxTokens$annotations", "getReferenceMaxTokens", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFanout", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(ZLjava/util/List;Lai/nous/aurora/data/remote/MoaModelSlotDto;Ljava/lang/Double;Ljava/lang/Double;ILjava/lang/Integer;Ljava/lang/String;)Lai/nous/aurora/data/remote/MoaPresetDto;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class MoaPresetDto {
    public final MoaModelSlotDto aggregator;
    public final Double aggregatorTemperature;
    public final boolean enabled;
    public final String fanout;
    public final int maxTokens;
    public final Integer referenceMaxTokens;
    public final List<MoaModelSlotDto> referenceModels;
    public final Double referenceTemperature;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {null, new ArrayListSerializer(MoaModelSlotDto$$serializer.INSTANCE), null, null, null, null, null, null};

    public MoaPresetDto() {
        this(false, (List) null, (MoaModelSlotDto) null, (Double) null, (Double) null, 0, (Integer) null, (String) null, 255, (DefaultConstructorMarker) null);
    }






    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    public final List<MoaModelSlotDto> component2() {
        return this.referenceModels;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MoaModelSlotDto getAggregator() {
        return this.aggregator;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Double getReferenceTemperature() {
        return this.referenceTemperature;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Double getAggregatorTemperature() {
        return this.aggregatorTemperature;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getMaxTokens() {
        return this.maxTokens;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getReferenceMaxTokens() {
        return this.referenceMaxTokens;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFanout() {
        return this.fanout;
    }

    public final MoaPresetDto copy(boolean enabled, List<MoaModelSlotDto> referenceModels, MoaModelSlotDto aggregator, Double referenceTemperature, Double aggregatorTemperature, int maxTokens, Integer referenceMaxTokens, String fanout) {
        Intrinsics.checkNotNullParameter(referenceModels, "referenceModels");
        Intrinsics.checkNotNullParameter(aggregator, "aggregator");
        return new MoaPresetDto(enabled, referenceModels, aggregator, referenceTemperature, aggregatorTemperature, maxTokens, referenceMaxTokens, fanout);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoaPresetDto)) {
            return false;
        }
        MoaPresetDto moaPresetDto = (MoaPresetDto) other;
        return this.enabled == moaPresetDto.enabled && Intrinsics.areEqual(this.referenceModels, moaPresetDto.referenceModels) && Intrinsics.areEqual(this.aggregator, moaPresetDto.aggregator) && Intrinsics.areEqual((Object) this.referenceTemperature, (Object) moaPresetDto.referenceTemperature) && Intrinsics.areEqual((Object) this.aggregatorTemperature, (Object) moaPresetDto.aggregatorTemperature) && this.maxTokens == moaPresetDto.maxTokens && Intrinsics.areEqual(this.referenceMaxTokens, moaPresetDto.referenceMaxTokens) && Intrinsics.areEqual(this.fanout, moaPresetDto.fanout);
    }

    public int hashCode() {
        return (((((((((((((Boolean.hashCode(this.enabled) * 31) + this.referenceModels.hashCode()) * 31) + this.aggregator.hashCode()) * 31) + (this.referenceTemperature == null ? 0 : this.referenceTemperature.hashCode())) * 31) + (this.aggregatorTemperature == null ? 0 : this.aggregatorTemperature.hashCode())) * 31) + Integer.hashCode(this.maxTokens)) * 31) + (this.referenceMaxTokens == null ? 0 : this.referenceMaxTokens.hashCode())) * 31) + (this.fanout != null ? this.fanout.hashCode() : 0);
    }

    public String toString() {
        return "MoaPresetDto(enabled=" + this.enabled + ", referenceModels=" + this.referenceModels + ", aggregator=" + this.aggregator + ", referenceTemperature=" + this.referenceTemperature + ", aggregatorTemperature=" + this.aggregatorTemperature + ", maxTokens=" + this.maxTokens + ", referenceMaxTokens=" + this.referenceMaxTokens + ", fanout=" + this.fanout + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/MoaPresetDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/MoaPresetDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<MoaPresetDto> serializer() {
            return MoaPresetDto$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ MoaPresetDto(int seen0, boolean enabled, List referenceModels, MoaModelSlotDto aggregator, Double referenceTemperature, Double aggregatorTemperature, int maxTokens, Integer referenceMaxTokens, String fanout, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen0 & 1) == 0) {
            this.enabled = true;
        } else {
            this.enabled = enabled;
        }
        if ((seen0 & 2) == 0) {
            this.referenceModels = CollectionsKt.emptyList();
        } else {
            this.referenceModels = referenceModels;
        }
        if ((seen0 & 4) == 0) {
            this.aggregator = new MoaModelSlotDto((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
        } else {
            this.aggregator = aggregator;
        }
        if ((seen0 & 8) == 0) {
            this.referenceTemperature = null;
        } else {
            this.referenceTemperature = referenceTemperature;
        }
        if ((seen0 & 16) == 0) {
            this.aggregatorTemperature = null;
        } else {
            this.aggregatorTemperature = aggregatorTemperature;
        }
        if ((seen0 & 32) == 0) {
            this.maxTokens = 4096;
        } else {
            this.maxTokens = maxTokens;
        }
        if ((seen0 & 64) == 0) {
            this.referenceMaxTokens = null;
        } else {
            this.referenceMaxTokens = referenceMaxTokens;
        }
        if ((seen0 & 128) == 0) {
            this.fanout = "per_iteration";
        } else {
            this.fanout = fanout;
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0042  */
    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(MoaPresetDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z;
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !self.enabled) {
            output.encodeBooleanElement(serialDesc, 0, self.enabled);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.referenceModels, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.referenceModels);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2)) {
            z = true;
        } else {
            if (Intrinsics.areEqual(self.aggregator, new MoaModelSlotDto((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                z = false;
            } else {
                z = true;
            }
        }
        if (z) {
            output.encodeSerializableElement(serialDesc, 2, MoaModelSlotDto$$serializer.INSTANCE, self.aggregator);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.referenceTemperature != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, DoubleSerializer.INSTANCE, self.referenceTemperature);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.aggregatorTemperature != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, DoubleSerializer.INSTANCE, self.aggregatorTemperature);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.maxTokens != 4096) {
            output.encodeIntElement(serialDesc, 5, self.maxTokens);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.referenceMaxTokens != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.referenceMaxTokens);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || !Intrinsics.areEqual(self.fanout, "per_iteration")) {
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.fanout);
        }
    }

    public MoaPresetDto(boolean enabled, List<MoaModelSlotDto> referenceModels, MoaModelSlotDto aggregator, Double referenceTemperature, Double aggregatorTemperature, int maxTokens, Integer referenceMaxTokens, String fanout) {
        Intrinsics.checkNotNullParameter(referenceModels, "referenceModels");
        Intrinsics.checkNotNullParameter(aggregator, "aggregator");
        this.enabled = enabled;
        this.referenceModels = referenceModels;
        this.aggregator = aggregator;
        this.referenceTemperature = referenceTemperature;
        this.aggregatorTemperature = aggregatorTemperature;
        this.maxTokens = maxTokens;
        this.referenceMaxTokens = referenceMaxTokens;
        this.fanout = fanout;
    }

    public /* synthetic */ MoaPresetDto(boolean z, List list, MoaModelSlotDto moaModelSlotDto, Double d, Double d2, int i, Integer num, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? true : z, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list, (i2 & 4) != 0 ? new MoaModelSlotDto((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : moaModelSlotDto, (i2 & 8) != 0 ? null : d, (i2 & 16) != 0 ? null : d2, (i2 & 32) != 0 ? 4096 : i, (i2 & 64) == 0 ? num : null, (i2 & 128) != 0 ? "per_iteration" : str);
    }



    public final List<MoaModelSlotDto> getReferenceModels() {
        return this.referenceModels;
    }


}
