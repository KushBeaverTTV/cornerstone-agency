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
   serializer inside MoaEncodeResponse.Companion.serializer() ("Anonymous inner class
   unlimited recursion"). Rebuilt from MoaEncodeResponse.write$Self + the serialization
   ctor. See PORTING_NOTES.md. */
public final class MoaEncodeResponse$$serializer implements GeneratedSerializer<MoaEncodeResponse> {
    public static final MoaEncodeResponse$$serializer INSTANCE = new MoaEncodeResponse$$serializer();
    private static final SerialDescriptor descriptor;

    private MoaEncodeResponse$$serializer() {
    }

    static {
        PluginGeneratedSerialDescriptor d = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.MoaEncodeResponse", INSTANCE, 3);
        d.addElement("ok", true);
        d.addElement("message", true);
        d.addElement("onePromptOnly", true);
        descriptor = d;
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BooleanSerializer.INSTANCE, StringSerializer.INSTANCE, BooleanSerializer.INSTANCE};
    }

    @Override
    public void serialize(Encoder encoder, MoaEncodeResponse value) {
        CompositeEncoder ce = encoder.beginStructure(descriptor);
        MoaEncodeResponse.write$Self$app_debug(value, ce, descriptor);
        ce.endStructure(descriptor);
    }

    @Override
    public MoaEncodeResponse deserialize(Decoder decoder) {
        CompositeDecoder cd = decoder.beginStructure(descriptor);
        int seen0 = 0;
        boolean ok = false;
        String message = null;
        boolean onePromptOnly = false;
        boolean done = false;
        while (!done) {
            int idx = cd.decodeElementIndex(descriptor);
            switch (idx) {
                case -1:
                    done = true;
                    break;
                case 0: ok = cd.decodeBooleanElement(descriptor, 0); seen0 |= 1; break;
                case 1: message = cd.decodeStringElement(descriptor, 1); seen0 |= 2; break;
                case 2: onePromptOnly = cd.decodeBooleanElement(descriptor, 2); seen0 |= 4; break;
                default:
                    throw new UnknownFieldException(idx);
            }
        }
        cd.endStructure(descriptor);
        return new MoaEncodeResponse(seen0, ok, message, onePromptOnly, (SerializationConstructorMarker) null);
    }

    @Override
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
