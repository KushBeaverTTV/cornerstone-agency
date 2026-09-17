package ai.nous.aurora.data.remote;

import java.util.*;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.*;
import kotlinx.serialization.descriptors.*;
import kotlinx.serialization.encoding.*;
import kotlinx.serialization.internal.*;
import kotlinx.serialization.json.*;

/* Reconstructed by fix_jadx.py: jadx inlined-and-destroyed this generated
   serializer inside MoaConfigDto.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from MoaConfigDto.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class MoaConfigDto$$serializer implements GeneratedSerializer<MoaConfigDto> {
    public static final MoaConfigDto$$serializer INSTANCE = new MoaConfigDto$$serializer();
    private static final SerialDescriptor descriptor;

    private MoaConfigDto$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.MoaConfigDto", INSTANCE, 12);
        d.addElement("ok", true);
        d.addElement("defaultPreset", true);
        d.addElement("activePreset", true);
        d.addElement("presets", true);
        d.addElement("saveTraces", true);
        d.addElement("traceDir", true);
        d.addElement("referenceModels", true);
        d.addElement("aggregator", true);
        d.addElement("maxTokens", true);
        d.addElement("referenceMaxTokens", true);
        d.addElement("fanout", true);
        d.addElement("enabled", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), StringSerializer.INSTANCE, StringSerializer.INSTANCE, MoaConfigDto.$childSerializers[3], BooleanSerializer.INSTANCE, StringSerializer.INSTANCE, MoaConfigDto.$childSerializers[6], BuiltinSerializersKt.getNullable(MoaModelSlotDto$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, MoaConfigDto value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        MoaConfigDto.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public MoaConfigDto deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        Boolean ok = null;
        String defaultPreset = null;
        String activePreset = null;
        Map presets = null;
        boolean saveTraces = false;
        String traceDir = null;
        List referenceModels = null;
        MoaModelSlotDto aggregator = null;
        Integer maxTokens = null;
        Integer referenceMaxTokens = null;
        String fanout = null;
        Boolean enabled = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: ok = (Boolean) cd.decodeNullableSerializableElement(descriptor, 0, BooleanSerializer.INSTANCE, ok); seen0 |= 1; break;
                case 1: defaultPreset = cd.decodeStringElement(descriptor, 1); seen0 |= 2; break;
                case 2: activePreset = cd.decodeStringElement(descriptor, 2); seen0 |= 4; break;
                case 3: presets = (Map) cd.decodeSerializableElement(descriptor, 3, MoaConfigDto.$childSerializers[3], presets); seen0 |= 8; break;
                case 4: saveTraces = cd.decodeBooleanElement(descriptor, 4); seen0 |= 16; break;
                case 5: traceDir = cd.decodeStringElement(descriptor, 5); seen0 |= 32; break;
                case 6: referenceModels = (List) cd.decodeSerializableElement(descriptor, 6, MoaConfigDto.$childSerializers[6], referenceModels); seen0 |= 64; break;
                case 7: aggregator = (MoaModelSlotDto) cd.decodeNullableSerializableElement(descriptor, 7, MoaModelSlotDto$$serializer.INSTANCE, aggregator); seen0 |= 128; break;
                case 8: maxTokens = (Integer) cd.decodeNullableSerializableElement(descriptor, 8, IntSerializer.INSTANCE, maxTokens); seen0 |= 256; break;
                case 9: referenceMaxTokens = (Integer) cd.decodeNullableSerializableElement(descriptor, 9, IntSerializer.INSTANCE, referenceMaxTokens); seen0 |= 512; break;
                case 10: fanout = (String) cd.decodeNullableSerializableElement(descriptor, 10, StringSerializer.INSTANCE, fanout); seen0 |= 1024; break;
                case 11: enabled = (Boolean) cd.decodeNullableSerializableElement(descriptor, 11, BooleanSerializer.INSTANCE, enabled); seen0 |= 2048; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new MoaConfigDto(seen0, ok, defaultPreset, activePreset, presets, saveTraces, traceDir, referenceModels, aggregator, maxTokens, referenceMaxTokens, fanout, enabled, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
