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
   serializer inside HermesModelOptionsDto.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from HermesModelOptionsDto.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class HermesModelOptionsDto$$serializer implements GeneratedSerializer<HermesModelOptionsDto> {
    public static final HermesModelOptionsDto$$serializer INSTANCE = new HermesModelOptionsDto$$serializer();
    private static final SerialDescriptor descriptor;

    private HermesModelOptionsDto$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.HermesModelOptionsDto", INSTANCE, 2);
        d.addElement("providers", true);
        d.addElement("current", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{HermesModelOptionsDto.$childSerializers[0], HermesCurrentModelDto$$serializer.INSTANCE};
    }

    @Override
    public void serialize(Encoder encoder, HermesModelOptionsDto value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        HermesModelOptionsDto.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public HermesModelOptionsDto deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        List providers = null;
        HermesCurrentModelDto current = null;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: providers = (List) cd.decodeSerializableElement(descriptor, 0, HermesModelOptionsDto.$childSerializers[0], providers); seen0 |= 1; break;
                case 1: current = (HermesCurrentModelDto) cd.decodeSerializableElement(descriptor, 1, HermesCurrentModelDto$$serializer.INSTANCE, current); seen0 |= 2; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new HermesModelOptionsDto(seen0, providers, current, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
