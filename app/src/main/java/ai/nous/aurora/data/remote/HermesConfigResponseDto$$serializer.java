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
   serializer inside HermesConfigResponseDto.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from HermesConfigResponseDto.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class HermesConfigResponseDto$$serializer implements GeneratedSerializer<HermesConfigResponseDto> {
    public static final HermesConfigResponseDto$$serializer INSTANCE = new HermesConfigResponseDto$$serializer();
    private static final SerialDescriptor descriptor;

    private HermesConfigResponseDto$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.HermesConfigResponseDto", INSTANCE, 8);
        d.addElement("object", true);
        d.addElement("path", true);
        d.addElement("redacted", true);
        d.addElement("resolved", true);
        d.addElement("config", true);
        d.addElement("flat", true);
        d.addElement("flatCount", true);
        d.addElement("resolutionError", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, JsonObjectSerializer.INSTANCE, HermesConfigResponseDto.$childSerializers[5], IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, HermesConfigResponseDto value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        HermesConfigResponseDto.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public HermesConfigResponseDto deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String object = null;
        String path = null;
        boolean redacted = false;
        boolean resolved = false;
        JsonObject config = null;
        Map flat = null;
        int flatCount = 0;
        String resolutionError = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: object = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, object); seen0 |= 1; break;
                case 1: path = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, path); seen0 |= 2; break;
                case 2: redacted = cd.decodeBooleanElement(descriptor, 2); seen0 |= 4; break;
                case 3: resolved = cd.decodeBooleanElement(descriptor, 3); seen0 |= 8; break;
                case 4: config = (JsonObject) cd.decodeSerializableElement(descriptor, 4, JsonObjectSerializer.INSTANCE, config); seen0 |= 16; break;
                case 5: flat = (Map) cd.decodeSerializableElement(descriptor, 5, HermesConfigResponseDto.$childSerializers[5], flat); seen0 |= 32; break;
                case 6: flatCount = cd.decodeIntElement(descriptor, 6); seen0 |= 64; break;
                case 7: resolutionError = (String) cd.decodeNullableSerializableElement(descriptor, 7, StringSerializer.INSTANCE, resolutionError); seen0 |= 128; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new HermesConfigResponseDto(seen0, object, path, redacted, resolved, config, flat, flatCount, resolutionError, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
