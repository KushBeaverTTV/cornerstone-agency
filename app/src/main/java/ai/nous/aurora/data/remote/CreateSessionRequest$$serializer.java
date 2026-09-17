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
   serializer inside CreateSessionRequest.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from CreateSessionRequest.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class CreateSessionRequest$$serializer implements GeneratedSerializer<CreateSessionRequest> {
    public static final CreateSessionRequest$$serializer INSTANCE = new CreateSessionRequest$$serializer();
    private static final SerialDescriptor descriptor;

    private CreateSessionRequest$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.CreateSessionRequest", INSTANCE, 2);
        d.addElement("title", true);
        d.addElement("model", true);
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
    public void serialize(Encoder encoder, CreateSessionRequest value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        CreateSessionRequest.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public CreateSessionRequest deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String title = null;
        String model = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: title = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, title); seen0 |= 1; break;
                case 1: model = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, model); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new CreateSessionRequest(seen0, title, model, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
