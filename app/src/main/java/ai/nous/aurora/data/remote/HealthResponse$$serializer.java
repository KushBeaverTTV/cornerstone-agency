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
   serializer inside HealthResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from HealthResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class HealthResponse$$serializer implements GeneratedSerializer<HealthResponse> {
    public static final HealthResponse$$serializer INSTANCE = new HealthResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private HealthResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.HealthResponse", INSTANCE, 2);
        d.addElement("status", true);
        d.addElement("ok", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, HealthResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        HealthResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public HealthResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String status = null;
        Boolean ok = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: status = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, status); seen0 |= 1; break;
                case 1: ok = (Boolean) cd.decodeNullableSerializableElement(descriptor, 1, BooleanSerializer.INSTANCE, ok); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new HealthResponse(seen0, status, ok, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
