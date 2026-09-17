package ai.nous.aurora.data.remote;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 S2\u00020\u0001:\u0002RSB\u009d\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0015\u0010\u0016B£\u0001\b\u0010\u0012\u0006\u0010\u0017\u001a\u00020\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b\u0015\u0010\u001aJ\u0010\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u00109\u001a\u00020\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\u0015\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00102J\u0010\u0010A\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00102J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001cJ¤\u0001\u0010D\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010EJ\u0013\u0010F\u001a\u00020\u00032\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010H\u001a\u00020\u0011HÖ\u0001J\t\u0010I\u001a\u00020\u0005HÖ\u0001J%\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00002\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0001¢\u0006\u0002\bQR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001c\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001f\u001a\u0004\b'\u0010(R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001f\u001a\u0004\b*\u0010!R\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001f\u001a\u0004\b,\u0010-R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R \u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00103\u0012\u0004\b0\u0010\u001f\u001a\u0004\b1\u00102R \u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00103\u0012\u0004\b4\u0010\u001f\u001a\u0004\b5\u00102R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010!R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b7\u0010\u001c¨\u0006T"}, d2 = {"Lai/nous/aurora/data/remote/MoaConfigDto;", "", "ok", "", "defaultPreset", "", "activePreset", "presets", "", "Lai/nous/aurora/data/remote/MoaPresetDto;", "saveTraces", "traceDir", "referenceModels", "", "Lai/nous/aurora/data/remote/MoaModelSlotDto;", "aggregator", "maxTokens", "", "referenceMaxTokens", "fanout", "enabled", "<init>", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;ZLjava/lang/String;Ljava/util/List;Lai/nous/aurora/data/remote/MoaModelSlotDto;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;ZLjava/lang/String;Ljava/util/List;Lai/nous/aurora/data/remote/MoaModelSlotDto;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOk", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDefaultPreset$annotations", "()V", "getDefaultPreset", "()Ljava/lang/String;", "getActivePreset$annotations", "getActivePreset", "getPresets", "()Ljava/util/Map;", "getSaveTraces$annotations", "getSaveTraces", "()Z", "getTraceDir$annotations", "getTraceDir", "getReferenceModels$annotations", "getReferenceModels", "()Ljava/util/List;", "getAggregator", "()Lai/nous/aurora/data/remote/MoaModelSlotDto;", "getMaxTokens$annotations", "getMaxTokens", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getReferenceMaxTokens$annotations", "getReferenceMaxTokens", "getFanout", "getEnabled", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;ZLjava/lang/String;Ljava/util/List;Lai/nous/aurora/data/remote/MoaModelSlotDto;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lai/nous/aurora/data/remote/MoaConfigDto;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class MoaConfigDto {
    private final String activePreset;
    private final MoaModelSlotDto aggregator;
    private final String defaultPreset;
    private final Boolean enabled;
    private final String fanout;
    private final Integer maxTokens;
    private final Boolean ok;
    private final Map<String, MoaPresetDto> presets;
    private final Integer referenceMaxTokens;
    private final List<MoaModelSlotDto> referenceModels;
    private final boolean saveTraces;
    private final String traceDir;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, MoaPresetDto$$serializer.INSTANCE), null, null, new ArrayListSerializer(MoaModelSlotDto$$serializer.INSTANCE), null, null, null, null, null};

    public MoaConfigDto() {
        this((Boolean) null, (String) null, (String) null, (Map) null, false, (String) null, (List) null, (MoaModelSlotDto) null, (Integer) null, (Integer) null, (String) null, (Boolean) null, 4095, (DefaultConstructorMarker) null);
    }

    @SerialName("active_preset")
    public static /* synthetic */ void getActivePreset$annotations() {
    }

    @SerialName("default_preset")
    public static /* synthetic */ void getDefaultPreset$annotations() {
    }

    @SerialName("max_tokens")
    public static /* synthetic */ void getMaxTokens$annotations() {
    }

    @SerialName("reference_max_tokens")
    public static /* synthetic */ void getReferenceMaxTokens$annotations() {
    }

    @SerialName("reference_models")
    public static /* synthetic */ void getReferenceModels$annotations() {
    }

    @SerialName("save_traces")
    public static /* synthetic */ void getSaveTraces$annotations() {
    }

    @SerialName("trace_dir")
    public static /* synthetic */ void getTraceDir$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Boolean getOk() {
        return this.ok;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getReferenceMaxTokens() {
        return this.referenceMaxTokens;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getFanout() {
        return this.fanout;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDefaultPreset() {
        return this.defaultPreset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getActivePreset() {
        return this.activePreset;
    }

    public final Map<String, MoaPresetDto> component4() {
        return this.presets;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getSaveTraces() {
        return this.saveTraces;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTraceDir() {
        return this.traceDir;
    }

    public final List<MoaModelSlotDto> component7() {
        return this.referenceModels;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final MoaModelSlotDto getAggregator() {
        return this.aggregator;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getMaxTokens() {
        return this.maxTokens;
    }

    public final MoaConfigDto copy(Boolean ok, String defaultPreset, String activePreset, Map<String, MoaPresetDto> presets, boolean saveTraces, String traceDir, List<MoaModelSlotDto> referenceModels, MoaModelSlotDto aggregator, Integer maxTokens, Integer referenceMaxTokens, String fanout, Boolean enabled) {
        Intrinsics.checkNotNullParameter(defaultPreset, "defaultPreset");
        Intrinsics.checkNotNullParameter(activePreset, "activePreset");
        Intrinsics.checkNotNullParameter(presets, "presets");
        Intrinsics.checkNotNullParameter(traceDir, "traceDir");
        Intrinsics.checkNotNullParameter(referenceModels, "referenceModels");
        return new MoaConfigDto(ok, defaultPreset, activePreset, presets, saveTraces, traceDir, referenceModels, aggregator, maxTokens, referenceMaxTokens, fanout, enabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoaConfigDto)) {
            return false;
        }
        MoaConfigDto moaConfigDto = (MoaConfigDto) other;
        return Intrinsics.areEqual(this.ok, moaConfigDto.ok) && Intrinsics.areEqual(this.defaultPreset, moaConfigDto.defaultPreset) && Intrinsics.areEqual(this.activePreset, moaConfigDto.activePreset) && Intrinsics.areEqual(this.presets, moaConfigDto.presets) && this.saveTraces == moaConfigDto.saveTraces && Intrinsics.areEqual(this.traceDir, moaConfigDto.traceDir) && Intrinsics.areEqual(this.referenceModels, moaConfigDto.referenceModels) && Intrinsics.areEqual(this.aggregator, moaConfigDto.aggregator) && Intrinsics.areEqual(this.maxTokens, moaConfigDto.maxTokens) && Intrinsics.areEqual(this.referenceMaxTokens, moaConfigDto.referenceMaxTokens) && Intrinsics.areEqual(this.fanout, moaConfigDto.fanout) && Intrinsics.areEqual(this.enabled, moaConfigDto.enabled);
    }

    public int hashCode() {
        return ((((((((((((((((((((((this.ok == null ? 0 : this.ok.hashCode()) * 31) + this.defaultPreset.hashCode()) * 31) + this.activePreset.hashCode()) * 31) + this.presets.hashCode()) * 31) + Boolean.hashCode(this.saveTraces)) * 31) + this.traceDir.hashCode()) * 31) + this.referenceModels.hashCode()) * 31) + (this.aggregator == null ? 0 : this.aggregator.hashCode())) * 31) + (this.maxTokens == null ? 0 : this.maxTokens.hashCode())) * 31) + (this.referenceMaxTokens == null ? 0 : this.referenceMaxTokens.hashCode())) * 31) + (this.fanout == null ? 0 : this.fanout.hashCode())) * 31) + (this.enabled != null ? this.enabled.hashCode() : 0);
    }

    public String toString() {
        return "MoaConfigDto(ok=" + this.ok + ", defaultPreset=" + this.defaultPreset + ", activePreset=" + this.activePreset + ", presets=" + this.presets + ", saveTraces=" + this.saveTraces + ", traceDir=" + this.traceDir + ", referenceModels=" + this.referenceModels + ", aggregator=" + this.aggregator + ", maxTokens=" + this.maxTokens + ", referenceMaxTokens=" + this.referenceMaxTokens + ", fanout=" + this.fanout + ", enabled=" + this.enabled + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/MoaConfigDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/MoaConfigDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<MoaConfigDto> serializer() {
            return new GeneratedSerializer<MoaConfigDto>() { // from class: ai.nous.aurora.data.remote.MoaConfigDto$$serializer
                private static final SerialDescriptor descriptor;
                public static final int $stable = 8;

                @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
                public final SerialDescriptor getDescriptor() {
                    return descriptor;
                }

                static {
                    PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("ai.nous.aurora.data.remote.MoaConfigDto", 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0004: RETURN 
                          (wrap ai.nous.aurora.data.remote.MoaConfigDto$$serializer:0x0002: SGET  A[WRAPPED] (LINE:133) ai.nous.aurora.data.remote.MoaConfigDto$$serializer.INSTANCE ai.nous.aurora.data.remote.MoaConfigDto$$serializer)
                         (LINE:133) in method: ai.nous.aurora.data.remote.MoaConfigDto.Companion.serializer():kotlinx.serialization.KSerializer<ai.nous.aurora.data.remote.MoaConfigDto>, file: classes10.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: CONSTRUCTOR (r0v2 'pluginGeneratedSerialDescriptor' kotlinx.serialization.internal.PluginGeneratedSerialDescriptor) = 
                          ("ai.nous.aurora.data.remote.MoaConfigDto")
                          (wrap ai.nous.aurora.data.remote.MoaConfigDto$$serializer:0x000d: SGET  A[WRAPPED] ai.nous.aurora.data.remote.MoaConfigDto$$serializer.INSTANCE ai.nous.aurora.data.remote.MoaConfigDto$$serializer)
                          (12 int)
                         A[DECLARE_VAR, MD:(java.lang.String, kotlinx.serialization.internal.GeneratedSerializer<?>, int):void (m)] (LINE:133) call: kotlinx.serialization.internal.PluginGeneratedSerialDescriptor.<init>(java.lang.String, kotlinx.serialization.internal.GeneratedSerializer, int):void type: CONSTRUCTOR in method: ai.nous.aurora.data.remote.MoaConfigDto$$serializer.<clinit>():void, file: classes10.dex
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
                        Caused by: jadx.core.utils.exceptions.CodegenException: Anonymous inner class unlimited recursion detected. Convert class to inner: ai.nous.aurora.data.remote.MoaConfigDto$$serializer
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
                        ai.nous.aurora.data.remote.MoaConfigDto$$serializer r0 = ai.nous.aurora.data.remote.MoaConfigDto$$serializer.INSTANCE
                        kotlinx.serialization.KSerializer r0 = (kotlinx.serialization.KSerializer) r0
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: ai.nous.aurora.data.remote.MoaConfigDto.Companion.serializer():kotlinx.serialization.KSerializer");
                }
            }

            public /* synthetic */ MoaConfigDto(int seen0, Boolean ok, String defaultPreset, String activePreset, Map presets, boolean saveTraces, String traceDir, List referenceModels, MoaModelSlotDto aggregator, Integer maxTokens, Integer referenceMaxTokens, String fanout, Boolean enabled, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.ok = null;
                } else {
                    this.ok = ok;
                }
                if ((seen0 & 2) == 0) {
                    this.defaultPreset = "default";
                } else {
                    this.defaultPreset = defaultPreset;
                }
                if ((seen0 & 4) == 0) {
                    this.activePreset = "";
                } else {
                    this.activePreset = activePreset;
                }
                if ((seen0 & 8) == 0) {
                    this.presets = MapsKt.emptyMap();
                } else {
                    this.presets = presets;
                }
                if ((seen0 & 16) == 0) {
                    this.saveTraces = false;
                } else {
                    this.saveTraces = saveTraces;
                }
                if ((seen0 & 32) == 0) {
                    this.traceDir = "";
                } else {
                    this.traceDir = traceDir;
                }
                if ((seen0 & 64) == 0) {
                    this.referenceModels = CollectionsKt.emptyList();
                } else {
                    this.referenceModels = referenceModels;
                }
                if ((seen0 & 128) == 0) {
                    this.aggregator = null;
                } else {
                    this.aggregator = aggregator;
                }
                if ((seen0 & 256) == 0) {
                    this.maxTokens = null;
                } else {
                    this.maxTokens = maxTokens;
                }
                if ((seen0 & 512) == 0) {
                    this.referenceMaxTokens = null;
                } else {
                    this.referenceMaxTokens = referenceMaxTokens;
                }
                if ((seen0 & 1024) == 0) {
                    this.fanout = null;
                } else {
                    this.fanout = fanout;
                }
                if ((seen0 & 2048) == 0) {
                    this.enabled = null;
                } else {
                    this.enabled = enabled;
                }
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$app_debug(MoaConfigDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer<Object>[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || self.ok != null) {
                    output.encodeNullableSerializableElement(serialDesc, 0, BooleanSerializer.INSTANCE, self.ok);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.defaultPreset, "default")) {
                    output.encodeStringElement(serialDesc, 1, self.defaultPreset);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.activePreset, "")) {
                    output.encodeStringElement(serialDesc, 2, self.activePreset);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.presets, MapsKt.emptyMap())) {
                    output.encodeSerializableElement(serialDesc, 3, kSerializerArr[3], self.presets);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 4) || self.saveTraces) {
                    output.encodeBooleanElement(serialDesc, 4, self.saveTraces);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.traceDir, "")) {
                    output.encodeStringElement(serialDesc, 5, self.traceDir);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 6) || !Intrinsics.areEqual(self.referenceModels, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 6, kSerializerArr[6], self.referenceModels);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 7) || self.aggregator != null) {
                    output.encodeNullableSerializableElement(serialDesc, 7, MoaModelSlotDto$$serializer.INSTANCE, self.aggregator);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 8) || self.maxTokens != null) {
                    output.encodeNullableSerializableElement(serialDesc, 8, IntSerializer.INSTANCE, self.maxTokens);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 9) || self.referenceMaxTokens != null) {
                    output.encodeNullableSerializableElement(serialDesc, 9, IntSerializer.INSTANCE, self.referenceMaxTokens);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 10) || self.fanout != null) {
                    output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.fanout);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 11) || self.enabled != null) {
                    output.encodeNullableSerializableElement(serialDesc, 11, BooleanSerializer.INSTANCE, self.enabled);
                }
            }

            public MoaConfigDto(Boolean ok, String defaultPreset, String activePreset, Map<String, MoaPresetDto> presets, boolean saveTraces, String traceDir, List<MoaModelSlotDto> referenceModels, MoaModelSlotDto aggregator, Integer maxTokens, Integer referenceMaxTokens, String fanout, Boolean enabled) {
                Intrinsics.checkNotNullParameter(defaultPreset, "defaultPreset");
                Intrinsics.checkNotNullParameter(activePreset, "activePreset");
                Intrinsics.checkNotNullParameter(presets, "presets");
                Intrinsics.checkNotNullParameter(traceDir, "traceDir");
                Intrinsics.checkNotNullParameter(referenceModels, "referenceModels");
                this.ok = ok;
                this.defaultPreset = defaultPreset;
                this.activePreset = activePreset;
                this.presets = presets;
                this.saveTraces = saveTraces;
                this.traceDir = traceDir;
                this.referenceModels = referenceModels;
                this.aggregator = aggregator;
                this.maxTokens = maxTokens;
                this.referenceMaxTokens = referenceMaxTokens;
                this.fanout = fanout;
                this.enabled = enabled;
            }

            public /* synthetic */ MoaConfigDto(Boolean bool, String str, String str2, Map map, boolean z, String str3, List list, MoaModelSlotDto moaModelSlotDto, Integer num, Integer num2, String str4, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? "default" : str, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? MapsKt.emptyMap() : map, (i & 16) != 0 ? false : z, (i & 32) == 0 ? str3 : "", (i & 64) != 0 ? CollectionsKt.emptyList() : list, (i & 128) != 0 ? null : moaModelSlotDto, (i & 256) != 0 ? null : num, (i & 512) != 0 ? null : num2, (i & 1024) != 0 ? null : str4, (i & 2048) == 0 ? bool2 : null);
            }

            public final Boolean getOk() {
                return this.ok;
            }

            public final String getDefaultPreset() {
                return this.defaultPreset;
            }

            public final String getActivePreset() {
                return this.activePreset;
            }

            public final Map<String, MoaPresetDto> getPresets() {
                return this.presets;
            }

            public final boolean getSaveTraces() {
                return this.saveTraces;
            }

            public final String getTraceDir() {
                return this.traceDir;
            }

            public final List<MoaModelSlotDto> getReferenceModels() {
                return this.referenceModels;
            }

            public final MoaModelSlotDto getAggregator() {
                return this.aggregator;
            }

            public final Integer getMaxTokens() {
                return this.maxTokens;
            }

            public final Integer getReferenceMaxTokens() {
                return this.referenceMaxTokens;
            }

            public final String getFanout() {
                return this.fanout;
            }

            public final Boolean getEnabled() {
                return this.enabled;
            }
        }
