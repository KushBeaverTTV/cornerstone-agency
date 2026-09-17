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
   serializer inside ConfigResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from ConfigResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class ConfigResponse$$serializer implements GeneratedSerializer<ConfigResponse> {
    public static final ConfigResponse$$serializer INSTANCE = new ConfigResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private ConfigResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.ConfigResponse", INSTANCE, 4);
        d.addElement("object", true);
        d.addElement("path", true);
        d.addElement("redacted", true);
        d.addElement("config", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE), BuiltinSerializersKt.getNullable(JsonObjectSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, ConfigResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        ConfigResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public ConfigResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String object = null;
        String path = null;
        Boolean redacted = null;
        JsonObject config = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: object = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, object); seen0 |= 1; break;
                case 1: path = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, path); seen0 |= 2; break;
                case 2: redacted = (Boolean) cd.decodeNullableSerializableElement(descriptor, 2, BooleanSerializer.INSTANCE, redacted); seen0 |= 4; break;
                case 3: config = (JsonObject) cd.decodeNullableSerializableElement(descriptor, 3, JsonObjectSerializer.INSTANCE, config); seen0 |= 8; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new ConfigResponse(seen0, object, path, redacted, config, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
