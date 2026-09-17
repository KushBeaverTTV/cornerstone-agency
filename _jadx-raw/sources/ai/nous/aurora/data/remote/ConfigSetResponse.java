package ai.nous.aurora.data.remote;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;

/* JADX INFO: compiled from: HermesConfigModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB7\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lai/nous/aurora/data/remote/ConfigSetResponse;", "", "ok", "", "path", "", "value", "Lkotlinx/serialization/json/JsonElement;", "<init>", "(ZLjava/lang/String;Lkotlinx/serialization/json/JsonElement;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/String;Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOk", "()Z", "getPath", "()Ljava/lang/String;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ConfigSetResponse {
    private final boolean ok;
    private final String path;
    private final JsonElement value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public ConfigSetResponse() {
        this(false, (String) null, (JsonElement) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ ConfigSetResponse copy$default(ConfigSetResponse configSetResponse, boolean z, String str, JsonElement jsonElement, int i, Object obj) {
        if ((i & 1) != 0) {
            z = configSetResponse.ok;
        }
        if ((i & 2) != 0) {
            str = configSetResponse.path;
        }
        if ((i & 4) != 0) {
            jsonElement = configSetResponse.value;
        }
        return configSetResponse.copy(z, str, jsonElement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getOk() {
        return this.ok;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final JsonElement getValue() {
        return this.value;
    }

    public final ConfigSetResponse copy(boolean ok, String path, JsonElement value) {
        return new ConfigSetResponse(ok, path, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigSetResponse)) {
            return false;
        }
        ConfigSetResponse configSetResponse = (ConfigSetResponse) other;
        return this.ok == configSetResponse.ok && Intrinsics.areEqual(this.path, configSetResponse.path) && Intrinsics.areEqual(this.value, configSetResponse.value);
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.ok) * 31) + (this.path == null ? 0 : this.path.hashCode())) * 31) + (this.value != null ? this.value.hashCode() : 0);
    }

    public String toString() {
        return "ConfigSetResponse(ok=" + this.ok + ", path=" + this.path + ", value=" + this.value + ")";
    }

    /* JADX INFO: compiled from: HermesConfigModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/ConfigSetResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/ConfigSetResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<ConfigSetResponse> serializer() {
            return new GeneratedSerializer<ConfigSetResponse>() { // from class: ai.nous.aurora.data.remote.ConfigSetResponse$$serializer
                private static final SerialDescriptor descriptor;
                public static final int $stable = 8;

                @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
                public final SerialDescriptor getDescriptor() {
                    return descriptor;
                }

                static {
                    PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.ConfigSetResponse", 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: RETURN 
                          (wrap ai.nous.aurora.data.remote.ConfigSetResponse$$serializer:0x0002: SGET  A[WRAPPED] (LINE:50) ai.nous.aurora.data.remote.ConfigSetResponse$$serializer.INSTANCE ai.nous.aurora.data.remote.ConfigSetResponse$$serializer)
                         (LINE:50) in method: ai.nous.aurora.data.remote.ConfigSetResponse.Companion.serializer():kotlinx.serialization.KSerializer<ai.nous.aurora.data.remote.ConfigSetResponse>, file: classes10.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Method generation error
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:348)
                        	... 5 more
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0014: CONSTRUCTOR (r0v2 'pluginGeneratedSerialDescriptor' kotlinx.serialization.internal.PluginGeneratedSerialDescriptor) = 
                          ("ai.nous.aurora.data.remote.ConfigSetResponse")
                          (wrap ai.nous.aurora.data.remote.ConfigSetResponse$$serializer:0x000d: SGET  A[WRAPPED] ai.nous.aurora.data.remote.ConfigSetResponse$$serializer.INSTANCE ai.nous.aurora.data.remote.ConfigSetResponse$$serializer)
                          (3 int)
                         A[DECLARE_VAR, MD:(java.lang.String, kotlinx.serialization.internal.GeneratedSerializer<?>, int):void (m)] (LINE:50) call: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor.<init>(java.lang.String, kotlinx.serialization.internal.GeneratedSerializer, int):void type: CONSTRUCTOR in method: ai.nous.aurora.data.remote.ConfigSetResponse$$serializer.<clinit>():void, file: classes10.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                        	... 5 more
                        Caused by: jadx.core.utils.exceptions.CodegenException: Anonymous inner class unlimited recursion detected. Convert class to inner: ai.nous.aurora.data.remote.ConfigSetResponse$$serializer
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:813)
                        	at jadx.core.codegen.InsnGen.staticField(InsnGen.java:225)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:492)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:782)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 15 more
                        */
                    /*
                        this = this;
                        ai.nous.aurora.data.remote.ConfigSetResponse$$serializer r0 = ai.nous.aurora.data.remote.ConfigSetResponse$$serializer.INSTANCE
                        kotlinx.serialization.KSerializer r0 = (kotlinx.serialization.KSerializer) r0
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.data.remote.ConfigSetResponse.Companion.serializer():kotlinx.serialization.KSerializer");
                }
            }

            public /* synthetic */ ConfigSetResponse(int seen0, boolean ok, String path, JsonElement value, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.ok = false;
                } else {
                    this.ok = ok;
                }
                if ((seen0 & 2) == 0) {
                    this.path = null;
                } else {
                    this.path = path;
                }
                if ((seen0 & 4) == 0) {
                    this.value = null;
                } else {
                    this.value = value;
                }
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app_debug(ConfigSetResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.ok) {
                    output.encodeBooleanElement(serialDesc, 0, self.ok);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.path != null) {
                    output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.path);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || self.value != null) {
                    output.encodeNullableSerializableElement(serialDesc, 2, JsonElementSerializer.INSTANCE, self.value);
                }
            }

            public ConfigSetResponse(boolean ok, String path, JsonElement value) {
                this.ok = ok;
                this.path = path;
                this.value = value;
            }

            public /* synthetic */ ConfigSetResponse(boolean z, String str, JsonElement jsonElement, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : jsonElement);
            }

            public final boolean getOk() {
                return this.ok;
            }

            public final String getPath() {
                return this.path;
            }

            public final JsonElement getValue() {
                return this.value;
            }
        }
