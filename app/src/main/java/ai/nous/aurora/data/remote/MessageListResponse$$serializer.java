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
   serializer inside MessageListResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from MessageListResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class MessageListResponse$$serializer implements GeneratedSerializer<MessageListResponse> {
    public static final MessageListResponse$$serializer INSTANCE = new MessageListResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private MessageListResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.MessageListResponse", INSTANCE, 3);
        d.addElement("object", true);
        d.addElement("sessionId", true);
        d.addElement("data", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), MessageListResponse.$childSerializers[2]};
    }

    @Override
    public void serialize(Encoder encoder, MessageListResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        MessageListResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public MessageListResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String object = null;
        String sessionId = null;
        List data = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: object = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, object); seen0 |= 1; break;
                case 1: sessionId = (String) cd.decodeNullableSerializableElement(descriptor, 1, StringSerializer.INSTANCE, sessionId); seen0 |= 2; break;
                case 2: data = (List) cd.decodeSerializableElement(descriptor, 2, MessageListResponse.$childSerializers[2], data); seen0 |= 4; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new MessageListResponse(seen0, object, sessionId, data, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
