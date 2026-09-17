package ai.nous.aurora.data.remote;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J#\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\nHÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006%"}, d2 = {"Lai/nous/aurora/data/remote/HermesModelOptionsDto;", "", "providers", "", "Lai/nous/aurora/data/remote/HermesModelProviderDto;", "current", "Lai/nous/aurora/data/remote/HermesCurrentModelDto;", "<init>", "(Ljava/util/List;Lai/nous/aurora/data/remote/HermesCurrentModelDto;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lai/nous/aurora/data/remote/HermesCurrentModelDto;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getProviders", "()Ljava/util/List;", "getCurrent", "()Lai/nous/aurora/data/remote/HermesCurrentModelDto;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesModelOptionsDto {
    private final HermesCurrentModelDto current;
    private final List<HermesModelProviderDto> providers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {new ArrayListSerializer(HermesModelProviderDto$$serializer.INSTANCE), null};

    /* JADX WARN: Multi-variable type inference failed */
    public HermesModelOptionsDto() {
        this((List) null, (HermesCurrentModelDto) (null), 3, (DefaultConstructorMarker) (null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HermesModelOptionsDto copy$default(HermesModelOptionsDto hermesModelOptionsDto, List list, HermesCurrentModelDto hermesCurrentModelDto, int i, Object obj) {
        if ((i & 1) != 0) {
            list = hermesModelOptionsDto.providers;
        }
        if ((i & 2) != 0) {
            hermesCurrentModelDto = hermesModelOptionsDto.current;
        }
        return hermesModelOptionsDto.copy(list, hermesCurrentModelDto);
    }

    public final List<HermesModelProviderDto> component1() {
        return this.providers;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final HermesCurrentModelDto getCurrent() {
        return this.current;
    }

    public final HermesModelOptionsDto copy(List<HermesModelProviderDto> providers, HermesCurrentModelDto current) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(current, "current");
        return new HermesModelOptionsDto(providers, current);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesModelOptionsDto)) {
            return false;
        }
        HermesModelOptionsDto hermesModelOptionsDto = (HermesModelOptionsDto) other;
        return Intrinsics.areEqual(this.providers, hermesModelOptionsDto.providers) && Intrinsics.areEqual(this.current, hermesModelOptionsDto.current);
    }

    public int hashCode() {
        return (this.providers.hashCode() * 31) + this.current.hashCode();
    }

    public String toString() {
        return "HermesModelOptionsDto(providers=" + this.providers + ", current=" + this.current + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesModelOptionsDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesModelOptionsDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesModelOptionsDto> serializer() {
            return HermesModelOptionsDto$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ HermesModelOptionsDto(int seen0, List providers, HermesCurrentModelDto current, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.providers = CollectionsKt.emptyList();
                } else {
                    this.providers = providers;
                }
                if ((seen0 & 2) == 0) {
                    this.current = new HermesCurrentModelDto((String) null, (String) null, false, 7, (DefaultConstructorMarker) null);
                } else {
                    this.current = current;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(HermesModelOptionsDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.providers, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.providers);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.current, new HermesCurrentModelDto((String) null, (String) null, false, 7, (DefaultConstructorMarker) null))) {
                    output.encodeSerializableElement(serialDesc, 1, HermesCurrentModelDto$$serializer.INSTANCE, self.current);
                }
            }

            public HermesModelOptionsDto(List<HermesModelProviderDto> providers, HermesCurrentModelDto current) {
                Intrinsics.checkNotNullParameter(providers, "providers");
                Intrinsics.checkNotNullParameter(current, "current");
                this.providers = providers;
                this.current = current;
            }

            public /* synthetic */ HermesModelOptionsDto(List list, HermesCurrentModelDto hermesCurrentModelDto, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? new HermesCurrentModelDto((String) null, (String) null, false, 7, (DefaultConstructorMarker) null) : hermesCurrentModelDto);
            }

            public final List<HermesModelProviderDto> getProviders() {
                return this.providers;
            }
}