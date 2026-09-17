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
   serializer inside SessionChatResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from SessionChatResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class SessionChatResponse$$serializer implements GeneratedSerializer<SessionChatResponse> {
    public static final SessionChatResponse$$serializer INSTANCE = new SessionChatResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private SessionChatResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.SessionChatResponse", INSTANCE, 3);
        d.addElement("object", true);
        d.addElement("sessionId", true);
        d.addElement("message", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(HermesMessage$$serializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, SessionChatResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        SessionChatResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public SessionChatResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String object = null;
        String sessionId = null;
        HermesMessage message = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: object = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, object); seen0 |= 1; break;
                case 1: sessionId = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, sessionId); seen0 |= 2; break;
                case 2: message = (HermesMessage) cd.decodeNullableSerializableElement(descriptor, 2, HermesMessage$$serializer.INSTANCE, message); seen0 |= 4; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new SessionChatResponse(seen0, object, sessionId, message, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
