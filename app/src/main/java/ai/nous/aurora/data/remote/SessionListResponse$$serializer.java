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
   serializer inside SessionListResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from SessionListResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class SessionListResponse$$serializer implements GeneratedSerializer<SessionListResponse> {
    public static final SessionListResponse$$serializer INSTANCE = new SessionListResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private SessionListResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.SessionListResponse", INSTANCE, 5);
        d.addElement("object", true);
        d.addElement("data", true);
        d.addElement("limit", true);
        d.addElement("offset", true);
        d.addElement("hasMore", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), SessionListResponse.$childSerializers[1], BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(BooleanSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, SessionListResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        SessionListResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public SessionListResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        String object = null;
        List data = null;
        Integer limit = null;
        Integer offset = null;
        Boolean hasMore = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: object = (String) cd.decodeNullableSerializableElement(descriptor, 0, StringSerializer.INSTANCE, object); seen0 |= 1; break;
                case 1: data = (List) cd.decodeSerializableElement(descriptor, 1, SessionListResponse.$childSerializers[1], data); seen0 |= 2; break;
                case 2: limit = (Integer) cd.decodeNullableSerializableElement(descriptor, 2, IntSerializer.INSTANCE, limit); seen0 |= 4; break;
                case 3: offset = (Integer) cd.decodeNullableSerializableElement(descriptor, 3, IntSerializer.INSTANCE, offset); seen0 |= 8; break;
                case 4: hasMore = (Boolean) cd.decodeNullableSerializableElement(descriptor, 4, BooleanSerializer.INSTANCE, hasMore); seen0 |= 16; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new SessionListResponse(seen0, object, data, limit, offset, hasMore, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
