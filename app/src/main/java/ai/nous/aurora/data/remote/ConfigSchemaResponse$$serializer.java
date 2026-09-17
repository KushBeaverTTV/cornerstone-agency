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
   serializer inside ConfigSchemaResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from ConfigSchemaResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class ConfigSchemaResponse$$serializer implements GeneratedSerializer<ConfigSchemaResponse> {
    public static final ConfigSchemaResponse$$serializer INSTANCE = new ConfigSchemaResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private ConfigSchemaResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.ConfigSchemaResponse", INSTANCE, 2);
        d.addElement("fields", true);
        d.addElement("categoryOrder", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{ConfigSchemaResponse.$childSerializers[0], ConfigSchemaResponse.$childSerializers[1]};
    }

    @Override
    public void serialize(Encoder encoder, ConfigSchemaResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        ConfigSchemaResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public ConfigSchemaResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        Map fields = null;
        List categoryOrder = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: fields = (Map) cd.decodeSerializableElement(descriptor, 0, ConfigSchemaResponse.$childSerializers[0], fields); seen0 |= 1; break;
                case 1: categoryOrder = (List) cd.decodeSerializableElement(descriptor, 1, ConfigSchemaResponse.$childSerializers[1], categoryOrder); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new ConfigSchemaResponse(seen0, fields, categoryOrder, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
