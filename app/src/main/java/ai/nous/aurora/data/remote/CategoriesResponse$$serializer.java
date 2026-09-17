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
   serializer inside CategoriesResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from CategoriesResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class CategoriesResponse$$serializer implements GeneratedSerializer<CategoriesResponse> {
    public static final CategoriesResponse$$serializer INSTANCE = new CategoriesResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private CategoriesResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.CategoriesResponse", INSTANCE, 2);
        d.addElement("categories", true);
        d.addElement("totalFields", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{CategoriesResponse.$childSerializers[0], IntSerializer.INSTANCE};
    }

    @Override
    public void serialize(Encoder encoder, CategoriesResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        CategoriesResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public CategoriesResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        List categories = null;
        int totalFields = 0;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: categories = (List) cd.decodeSerializableElement(descriptor, 0, CategoriesResponse.$childSerializers[0], categories); seen0 |= 1; break;
                case 1: totalFields = cd.decodeIntElement(descriptor, 1); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new CategoriesResponse(seen0, categories, totalFields, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
