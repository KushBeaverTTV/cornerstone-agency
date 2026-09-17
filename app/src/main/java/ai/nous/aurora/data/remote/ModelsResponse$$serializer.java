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
   serializer inside ModelsResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from ModelsResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class ModelsResponse$$serializer implements GeneratedSerializer<ModelsResponse> {
    public static final ModelsResponse$$serializer INSTANCE = new ModelsResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private ModelsResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.ModelsResponse", INSTANCE, 1);
        d.addElement("data", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{ModelsResponse.$childSerializers[0]};
    }

    @Override
    public void serialize(Encoder encoder, ModelsResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        ModelsResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public ModelsResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        List data = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: data = (List) cd.decodeSerializableElement(descriptor, 0, ModelsResponse.$childSerializers[0], data); seen0 |= 1; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new ModelsResponse(seen0, data, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
