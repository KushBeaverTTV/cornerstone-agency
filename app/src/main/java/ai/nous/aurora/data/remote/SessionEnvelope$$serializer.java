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
   serializer inside SessionEnvelope.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from SessionEnvelope.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class SessionEnvelope$$serializer implements GeneratedSerializer<SessionEnvelope> {
    public static final SessionEnvelope$$serializer INSTANCE = new SessionEnvelope$$serializer();
    private static final SerialDescriptor descriptor;

    private SessionEnvelope$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.SessionEnvelope", INSTANCE, 2);
        d.addElement("object", true);
        d.addElement("session", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(HermesSession$$serializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, SessionEnvelope value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        SessionEnvelope.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public SessionEnvelope deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String object = null;
        HermesSession session = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: object = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, object); seen0 |= 1; break;
                case 1: session = (HermesSession) cd.decodeNullableSerializableElement(descriptor, 1, HermesSession$$serializer.INSTANCE, session); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new SessionEnvelope(seen0, object, session, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
