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
   serializer inside HermesConfigSchemaDto.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from HermesConfigSchemaDto.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class HermesConfigSchemaDto$$serializer implements GeneratedSerializer<HermesConfigSchemaDto> {
    public static final HermesConfigSchemaDto$$serializer INSTANCE = new HermesConfigSchemaDto$$serializer();
    private static final SerialDescriptor descriptor;

    private HermesConfigSchemaDto$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.HermesConfigSchemaDto", INSTANCE, 4);
        d.addElement("fields", true);
        d.addElement("categoryOrder", true);
        d.addElement("source", true);
        d.addElement("fieldCount", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{HermesConfigSchemaDto.$childSerializers[0], HermesConfigSchemaDto.$childSerializers[1], BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE)};
    }

    @Override
    public void serialize(Encoder encoder, HermesConfigSchemaDto value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        HermesConfigSchemaDto.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public HermesConfigSchemaDto deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        Map fields = null;
        List categoryOrder = null;
        String source = null;
        Integer fieldCount = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: fields = (Map) cd.decodeSerializableElement(descriptor, 0, HermesConfigSchemaDto.$childSerializers[0], fields); seen0 |= 1; break;
                case 1: categoryOrder = (List) cd.decodeSerializableElement(descriptor, 1, HermesConfigSchemaDto.$childSerializers[1], categoryOrder); seen0 |= 2; break;
                case 2: source = (String) cd.decodeNullableSerializableElement(descriptor, 2, StringSerializer.INSTANCE, source); seen0 |= 4; break;
                case 3: fieldCount = (Integer) cd.decodeNullableSerializableElement(descriptor, 3, IntSerializer.INSTANCE, fieldCount); seen0 |= 8; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new HermesConfigSchemaDto(seen0, fields, categoryOrder, source, fieldCount, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
