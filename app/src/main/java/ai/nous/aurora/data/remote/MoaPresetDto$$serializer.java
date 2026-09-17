package ai.nous.aurora.data.remote;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"ai/nous/aurora/data/remote/MoaPresetDto.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lai/nous/aurora/data/remote/MoaPresetDto;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
public /* synthetic */ class MoaPresetDto$$serializer implements GeneratedSerializer<MoaPresetDto> {
    private static final SerialDescriptor descriptor;
    public static final MoaPresetDto$$serializer INSTANCE = new MoaPresetDto$$serializer();
    public static final int $stable = 8;

    private MoaPresetDto$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.MoaPresetDto", INSTANCE, 8);
        pluginGeneratedSerialDescriptor.addElement("enabled", true);
        pluginGeneratedSerialDescriptor.addElement("reference_models", true);
        pluginGeneratedSerialDescriptor.addElement("aggregator", true);
        pluginGeneratedSerialDescriptor.addElement("reference_temperature", true);
        pluginGeneratedSerialDescriptor.addElement("aggregator_temperature", true);
        pluginGeneratedSerialDescriptor.addElement("max_tokens", true);
        pluginGeneratedSerialDescriptor.addElement("reference_max_tokens", true);
        pluginGeneratedSerialDescriptor.addElement("fanout", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BooleanSerializer.INSTANCE, MoaPresetDto.$childSerializers[1], MoaModelSlotDto$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final MoaPresetDto deserialize(Decoder decoder) {
        MoaModelSlotDto moaModelSlotDto;
        Integer num;
        String str;
        int i;
        Double d;
        boolean z;
        Double d2;
        List list;
        int i2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        KSerializer[] kSerializerArr = MoaPresetDto.$childSerializers;
        int i3 = 7;
        int i4 = 6;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            boolean zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 0);
            List list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, kSerializerArr[1], null);
            MoaModelSlotDto moaModelSlotDto2 = (MoaModelSlotDto) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, MoaModelSlotDto$$serializer.INSTANCE, null);
            Double d3 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, DoubleSerializer.INSTANCE, null);
            Double d4 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, DoubleSerializer.INSTANCE, null);
            int iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
            Integer num2 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 6, IntSerializer.INSTANCE, null);
            str = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, null);
            num = num2;
            i2 = iDecodeIntElement;
            d2 = d3;
            d = d4;
            moaModelSlotDto = moaModelSlotDto2;
            i = 255;
            list = list2;
            z = zDecodeBooleanElement;
        } else {
            boolean z2 = true;
            boolean zDecodeBooleanElement2 = false;
            int i5 = 0;
            Integer num3 = null;
            String str2 = null;
            Double d5 = null;
            Double d6 = null;
            List list3 = null;
            moaModelSlotDto = null;
            int iDecodeIntElement2 = 0;
            while (z2) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z2 = false;
                        i3 = 7;
                        i4 = 6;
                        break;
                    case 0:
                        zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(serialDescriptor, 0);
                        i5 |= 1;
                        i3 = 7;
                        i4 = 6;
                        break;
                    case 1:
                        list3 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, kSerializerArr[1], list3);
                        i5 |= 2;
                        i3 = 7;
                        i4 = 6;
                        break;
                    case 2:
                        moaModelSlotDto = (MoaModelSlotDto) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, MoaModelSlotDto$$serializer.INSTANCE, moaModelSlotDto);
                        i5 |= 4;
                        i3 = 7;
                        i4 = 6;
                        break;
                    case 3:
                        d6 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, DoubleSerializer.INSTANCE, d6);
                        i5 |= 8;
                        i3 = 7;
                        i4 = 6;
                        break;
                    case 4:
                        d5 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, DoubleSerializer.INSTANCE, d5);
                        i5 |= 16;
                        i3 = 7;
                        i4 = 6;
                        break;
                    case 5:
                        iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
                        i5 |= 32;
                        break;
                    case 6:
                        num3 = (Integer) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i4, IntSerializer.INSTANCE, num3);
                        i5 |= 64;
                        break;
                    case 7:
                        str2 = (String) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, i3, StringSerializer.INSTANCE, str2);
                        i5 |= 128;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            num = num3;
            str = str2;
            i = i5;
            d = d5;
            z = zDecodeBooleanElement2;
            int i6 = iDecodeIntElement2;
            d2 = d6;
            list = list3;
            i2 = i6;
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new MoaPresetDto(i, z, list, moaModelSlotDto, d2, d, i2, num, str, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, MoaPresetDto value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        MoaPresetDto.write$Self$app_debug(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
