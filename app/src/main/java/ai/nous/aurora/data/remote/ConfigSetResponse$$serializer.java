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
   serializer inside ConfigSetResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from ConfigSetResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class ConfigSetResponse$$serializer implements GeneratedSerializer<ConfigSetResponse> {
    public static final ConfigSetResponse$$serializer INSTANCE = new ConfigSetResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private ConfigSetResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.ConfigSetResponse", INSTANCE, 3);
        d.addElement("ok", true);
        d.addElement("path", true);
        d.addElement("value", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BooleanSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(JsonElementSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, ConfigSetResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        ConfigSetResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public ConfigSetResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        boolean ok = false;
        String path = null;
        JsonElement value = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: ok = cd.decodeBooleanElement(descriptor, 0); seen0 |= 1; break;
                case 1: path = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, path); seen0 |= 2; break;
                case 2: value = (JsonElement) cd.decodeNullableSerializableElement(descriptor, 2, JsonElementSerializer.INSTANCE, value); seen0 |= 4; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new ConfigSetResponse(seen0, ok, path, value, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
