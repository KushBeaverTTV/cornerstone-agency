package ai.nous.aurora.data.remote;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 <2\u00020\u0001:\u0002;<Bi\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011Bq\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0010\u0010\u0015J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\u0015\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\t\u0010,\u001a\u00020\u000eHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jk\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010/\u001a\u00020\u00062\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\u000eHÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001J%\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0001¢\u0006\u0002\b:R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010!\u001a\u0004\b%\u0010\u0017¨\u0006="}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigResponseDto;", "", "object", "", "path", "redacted", "", "resolved", "config", "Lkotlinx/serialization/json/JsonObject;", "flat", "", "Lkotlinx/serialization/json/JsonElement;", "flatCount", "", "resolutionError", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLkotlinx/serialization/json/JsonObject;Ljava/util/Map;ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ZZLkotlinx/serialization/json/JsonObject;Ljava/util/Map;ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getObject", "()Ljava/lang/String;", "getPath", "getRedacted", "()Z", "getResolved", "getConfig", "()Lkotlinx/serialization/json/JsonObject;", "getFlat", "()Ljava/util/Map;", "getFlatCount$annotations", "()V", "getFlatCount", "()I", "getResolutionError$annotations", "getResolutionError", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesConfigResponseDto {
    private final JsonObject config;
    private final Map<String, JsonElement> flat;
    private final int flatCount;
    private final String object;
    private final String path;
    private final boolean redacted;
    private final String resolutionError;
    private final boolean resolved;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, JsonElementSerializer.INSTANCE), null, null};

    public HermesConfigResponseDto() {
        this((String) null, (String) null, false, false, (JsonObject) null, (Map) null, 0, (String) null, 255, (DefaultConstructorMarker) null);
    }

    @SerialName("flat_count")
    public static /* synthetic */ void getFlatCount$annotations() {
    }

    @SerialName("resolution_error")
    public static /* synthetic */ void getResolutionError$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getObject() {
        return this.object;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getRedacted() {
        return this.redacted;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getResolved() {
        return this.resolved;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final JsonObject getConfig() {
        return this.config;
    }

    public final Map<String, JsonElement> component6() {
        return this.flat;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getFlatCount() {
        return this.flatCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getResolutionError() {
        return this.resolutionError;
    }

    public final HermesConfigResponseDto copy(String object, String path, boolean redacted, boolean resolved, JsonObject config, Map<String, ? extends JsonElement> flat, int flatCount, String resolutionError) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(flat, "flat");
        return new HermesConfigResponseDto(object, path, redacted, resolved, config, flat, flatCount, resolutionError);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesConfigResponseDto)) {
            return false;
        }
        HermesConfigResponseDto hermesConfigResponseDto = (HermesConfigResponseDto) other;
        return Intrinsics.areEqual(this.object, hermesConfigResponseDto.object) && Intrinsics.areEqual(this.path, hermesConfigResponseDto.path) && this.redacted == hermesConfigResponseDto.redacted && this.resolved == hermesConfigResponseDto.resolved && Intrinsics.areEqual(this.config, hermesConfigResponseDto.config) && Intrinsics.areEqual(this.flat, hermesConfigResponseDto.flat) && this.flatCount == hermesConfigResponseDto.flatCount && Intrinsics.areEqual(this.resolutionError, hermesConfigResponseDto.resolutionError);
    }

    public int hashCode() {
        return ((((((((((((((this.object == null ? 0 : this.object.hashCode()) * 31) + (this.path == null ? 0 : this.path.hashCode())) * 31) + Boolean.hashCode(this.redacted)) * 31) + Boolean.hashCode(this.resolved)) * 31) + this.config.hashCode()) * 31) + this.flat.hashCode()) * 31) + Integer.hashCode(this.flatCount)) * 31) + (this.resolutionError != null ? this.resolutionError.hashCode() : 0);
    }

    public String toString() {
        return "HermesConfigResponseDto(object=" + this.object + ", path=" + this.path + ", redacted=" + this.redacted + ", resolved=" + this.resolved + ", config=" + this.config + ", flat=" + this.flat + ", flatCount=" + this.flatCount + ", resolutionError=" + this.resolutionError + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigResponseDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesConfigResponseDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesConfigResponseDto> serializer() {
            return new GeneratedSerializer<HermesConfigResponseDto>() { // from class: ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer
                private static final SerialDescriptor descriptor;
                public static final int $stable = 8;

                @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
                public final SerialDescriptor getDescriptor() {
                    return descriptor;
                }

                static {
                    PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.HermesConfigResponseDto", 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: RETURN 
                          (wrap ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer:0x0002: SGET  A[WRAPPED] (LINE:62) ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer.INSTANCE ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer)
                         (LINE:62) in method: ai.nous.aurora.data.remote.HermesConfigResponseDto.Companion.serializer():kotlinx.serialization.KSerializer<ai.nous.aurora.data.remote.HermesConfigResponseDto>, file: classes10.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: CONSTRUCTOR (r1v0 'pluginGeneratedSerialDescriptor' kotlinx.serialization.internal.PluginGeneratedSerialDescriptor) = 
                          ("ai.nous.aurora.data.remote.HermesConfigResponseDto")
                          (wrap ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer:0x000d: SGET  A[WRAPPED] ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer.INSTANCE ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer)
                          (8 int)
                         A[DECLARE_VAR, MD:(java.lang.String, kotlinx.serialization.internal.GeneratedSerializer<?>, int):void (m)] (LINE:62) call: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor.<init>(java.lang.String, kotlinx.serialization.internal.GeneratedSerializer, int):void type: CONSTRUCTOR in method: ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer.<clinit>():void, file: classes10.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Anonymous inner class unlimited recursion detected. Convert class to inner: ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer
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
                        ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer r0 = ai.nous.aurora.data.remote.HermesConfigResponseDto$$serializer.INSTANCE
                        kotlinx.serialization.KSerializer r0 = (kotlinx.serialization.KSerializer) r0
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.data.remote.HermesConfigResponseDto.Companion.serializer():kotlinx.serialization.KSerializer");
                }
            }

            public /* synthetic */ HermesConfigResponseDto(int seen0, String object, String path, boolean redacted, boolean resolved, JsonObject config, Map flat, int flatCount, String resolutionError, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.object = null;
                } else {
                    this.object = object;
                }
                if ((seen0 & 2) == 0) {
                    this.path = null;
                } else {
                    this.path = path;
                }
                if ((seen0 & 4) == 0) {
                    this.redacted = true;
                } else {
                    this.redacted = redacted;
                }
                if ((seen0 & 8) == 0) {
                    this.resolved = true;
                } else {
                    this.resolved = resolved;
                }
                if ((seen0 & 16) == 0) {
                    this.config = new JsonObject(MapsKt.emptyMap());
                } else {
                    this.config = config;
                }
                if ((seen0 & 32) == 0) {
                    this.flat = MapsKt.emptyMap();
                } else {
                    this.flat = flat;
                }
                if ((seen0 & 64) == 0) {
                    this.flatCount = 0;
                } else {
                    this.flatCount = flatCount;
                }
                if ((seen0 & 128) == 0) {
                    this.resolutionError = null;
                } else {
                    this.resolutionError = resolutionError;
                }
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app_debug(HermesConfigResponseDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer<Object>[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.object != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.object);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.path != null) {
                    output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.path);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || !self.redacted) {
                    output.encodeBooleanElement(serialDesc, 2, self.redacted);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 3) || !self.resolved) {
                    output.encodeBooleanElement(serialDesc, 3, self.resolved);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.config, new JsonObject(MapsKt.emptyMap()))) {
                    output.encodeSerializableElement(serialDesc, 4, JsonObjectSerializer.INSTANCE, self.config);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.flat, MapsKt.emptyMap())) {
                    output.encodeSerializableElement(serialDesc, 5, kSerializerArr[5], self.flat);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 6) || self.flatCount != 0) {
                    output.encodeIntElement(serialDesc, 6, self.flatCount);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 7) || self.resolutionError != null) {
                    output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.resolutionError);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public HermesConfigResponseDto(String object, String path, boolean redacted, boolean resolved, JsonObject config, Map<String, ? extends JsonElement> flat, int flatCount, String resolutionError) {
                Intrinsics.checkNotNullParameter(config, "config");
                Intrinsics.checkNotNullParameter(flat, "flat");
                this.object = object;
                this.path = path;
                this.redacted = redacted;
                this.resolved = resolved;
                this.config = config;
                this.flat = flat;
                this.flatCount = flatCount;
                this.resolutionError = resolutionError;
            }

            public /* synthetic */ HermesConfigResponseDto(String str, String str2, boolean z, boolean z2, JsonObject jsonObject, Map map, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? true : z, (i2 & 8) == 0 ? z2 : true, (i2 & 16) != 0 ? new JsonObject(MapsKt.emptyMap()) : jsonObject, (i2 & 32) != 0 ? MapsKt.emptyMap() : map, (i2 & 64) != 0 ? 0 : i, (i2 & 128) == 0 ? str3 : null);
            }

            public final String getObject() {
                return this.object;
            }

            public final String getPath() {
                return this.path;
            }

            public final boolean getRedacted() {
                return this.redacted;
            }

            public final boolean getResolved() {
                return this.resolved;
            }

            public final JsonObject getConfig() {
                return this.config;
            }

            public final Map<String, JsonElement> getFlat() {
                return this.flat;
            }

            public final int getFlatCount() {
                return this.flatCount;
            }

            public final String getResolutionError() {
                return this.resolutionError;
            }
        }
