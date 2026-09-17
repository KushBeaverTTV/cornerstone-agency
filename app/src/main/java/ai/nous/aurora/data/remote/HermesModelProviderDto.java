package ai.nous.aurora.data.remote;

import androidx.autofill.HintConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fBY\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u000b\u0010\u0011J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003JK\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0013\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u000eHÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u001c\u0010\n\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\n\u0010\u0018¨\u00061"}, d2 = {"Lai/nous/aurora/data/remote/HermesModelProviderDto;", "", "slug", "", HintConstants.AUTOFILL_HINT_NAME, "models", "", "authenticated", "", "source", "isCurrent", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSlug", "()Ljava/lang/String;", "getName", "getModels", "()Ljava/util/List;", "getAuthenticated", "()Z", "getSource", "isCurrent$annotations", "()V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesModelProviderDto {
    private final boolean authenticated;
    private final boolean isCurrent;
    private final List<String> models;
    private final String name;
    private final String slug;
    private final String source;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {null, null, new ArrayListSerializer(StringSerializer.INSTANCE), null, null, null};

    public HermesModelProviderDto() {
        this((String) null, (String) null, (List) null, false, (String) null, false, 63, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HermesModelProviderDto copy$default(HermesModelProviderDto hermesModelProviderDto, String str, String str2, List list, boolean z, String str3, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hermesModelProviderDto.slug;
        }
        if ((i & 2) != 0) {
            str2 = hermesModelProviderDto.name;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            list = hermesModelProviderDto.models;
        }
        List list2 = list;
        if ((i & 8) != 0) {
            z = hermesModelProviderDto.authenticated;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            str3 = hermesModelProviderDto.source;
        }
        String str5 = str3;
        if ((i & 32) != 0) {
            z2 = hermesModelProviderDto.isCurrent;
        }
        return hermesModelProviderDto.copy(str, str4, list2, z3, str5, z2);
    }


    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSlug() {
        return this.slug;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component3() {
        return this.models;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getAuthenticated() {
        return this.authenticated;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsCurrent() {
        return this.isCurrent;
    }

    public final HermesModelProviderDto copy(String slug, String name, List<String> models, boolean authenticated, String source, boolean isCurrent) {
        Intrinsics.checkNotNullParameter(slug, "slug");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(source, "source");
        return new HermesModelProviderDto(slug, name, models, authenticated, source, isCurrent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesModelProviderDto)) {
            return false;
        }
        HermesModelProviderDto hermesModelProviderDto = (HermesModelProviderDto) other;
        return Intrinsics.areEqual(this.slug, hermesModelProviderDto.slug) && Intrinsics.areEqual(this.name, hermesModelProviderDto.name) && Intrinsics.areEqual(this.models, hermesModelProviderDto.models) && this.authenticated == hermesModelProviderDto.authenticated && Intrinsics.areEqual(this.source, hermesModelProviderDto.source) && this.isCurrent == hermesModelProviderDto.isCurrent;
    }

    public int hashCode() {
        return (((((((((this.slug.hashCode() * 31) + this.name.hashCode()) * 31) + this.models.hashCode()) * 31) + Boolean.hashCode(this.authenticated)) * 31) + this.source.hashCode()) * 31) + Boolean.hashCode(this.isCurrent);
    }

    public String toString() {
        return "HermesModelProviderDto(slug=" + this.slug + ", name=" + this.name + ", models=" + this.models + ", authenticated=" + this.authenticated + ", source=" + this.source + ", isCurrent=" + this.isCurrent + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesModelProviderDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesModelProviderDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesModelProviderDto> serializer() {
            return HermesModelProviderDto$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ HermesModelProviderDto(int seen0, String slug, String name, List models, boolean authenticated, String source, boolean isCurrent, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen0 & 1) == 0) {
            this.slug = "";
        } else {
            this.slug = slug;
        }
        if ((seen0 & 2) == 0) {
            this.name = "";
        } else {
            this.name = name;
        }
        if ((seen0 & 4) == 0) {
            this.models = CollectionsKt.emptyList();
        } else {
            this.models = models;
        }
        if ((seen0 & 8) == 0) {
            this.authenticated = true;
        } else {
            this.authenticated = authenticated;
        }
        if ((seen0 & 16) == 0) {
            this.source = "hermes";
        } else {
            this.source = source;
        }
        if ((seen0 & 32) == 0) {
            this.isCurrent = false;
        } else {
            this.isCurrent = isCurrent;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(HermesModelProviderDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.slug, "")) {
            output.encodeStringElement(serialDesc, 0, self.slug);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 1, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.models, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.models);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !self.authenticated) {
            output.encodeBooleanElement(serialDesc, 3, self.authenticated);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.source, "hermes")) {
            output.encodeStringElement(serialDesc, 4, self.source);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.isCurrent) {
            output.encodeBooleanElement(serialDesc, 5, self.isCurrent);
        }
    }

    public HermesModelProviderDto(String slug, String name, List<String> models, boolean authenticated, String source, boolean isCurrent) {
        Intrinsics.checkNotNullParameter(slug, "slug");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(models, "models");
        Intrinsics.checkNotNullParameter(source, "source");
        this.slug = slug;
        this.name = name;
        this.models = models;
        this.authenticated = authenticated;
        this.source = source;
        this.isCurrent = isCurrent;
    }

    public /* synthetic */ HermesModelProviderDto(String str, String str2, List list, boolean z, String str3, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) == 0 ? str2 : "", (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? true : z, (i & 16) != 0 ? "hermes" : str3, (i & 32) != 0 ? false : z2);
    }





    public final List<String> getModels() {
        return this.models;
    }





    public final boolean isCurrent() {
        return this.isCurrent;
    }
}
