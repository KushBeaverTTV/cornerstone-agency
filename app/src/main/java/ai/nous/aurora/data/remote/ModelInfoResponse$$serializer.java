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
   serializer inside ModelInfoResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from ModelInfoResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class ModelInfoResponse$$serializer implements GeneratedSerializer<ModelInfoResponse> {
    public static final ModelInfoResponse$$serializer INSTANCE = new ModelInfoResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private ModelInfoResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.ModelInfoResponse", INSTANCE, 2);
        d.addElement("model", true);
        d.addElement("provider", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, ModelInfoResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        ModelInfoResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public ModelInfoResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String model = null;
        String provider = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: model = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, model); seen0 |= 1; break;
                case 1: provider = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, provider); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new ModelInfoResponse(seen0, model, provider, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
