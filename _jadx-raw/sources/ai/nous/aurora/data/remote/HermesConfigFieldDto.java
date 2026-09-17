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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: HermesConfigApi.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBQ\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003JA\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\rHÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigFieldDto;", "", "type", "", "description", "category", "options", "", "secret", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getType", "()Ljava/lang/String;", "getDescription", "getCategory", "getOptions", "()Ljava/util/List;", "getSecret", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class HermesConfigFieldDto {
    private final String category;
    private final String description;
    private final List<String> options;
    private final boolean secret;
    private final String type;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE), null};

    public HermesConfigFieldDto() {
        this((String) null, (String) null, (String) null, (List) null, false, 31, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HermesConfigFieldDto copy$default(HermesConfigFieldDto hermesConfigFieldDto, String str, String str2, String str3, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hermesConfigFieldDto.type;
        }
        if ((i & 2) != 0) {
            str2 = hermesConfigFieldDto.description;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = hermesConfigFieldDto.category;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            list = hermesConfigFieldDto.options;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            z = hermesConfigFieldDto.secret;
        }
        return hermesConfigFieldDto.copy(str, str4, str5, list2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCategory() {
        return this.category;
    }

    public final List<String> component4() {
        return this.options;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getSecret() {
        return this.secret;
    }

    public final HermesConfigFieldDto copy(String type, String description, String category, List<String> options, boolean secret) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(options, "options");
        return new HermesConfigFieldDto(type, description, category, options, secret);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HermesConfigFieldDto)) {
            return false;
        }
        HermesConfigFieldDto hermesConfigFieldDto = (HermesConfigFieldDto) other;
        return Intrinsics.areEqual(this.type, hermesConfigFieldDto.type) && Intrinsics.areEqual(this.description, hermesConfigFieldDto.description) && Intrinsics.areEqual(this.category, hermesConfigFieldDto.category) && Intrinsics.areEqual(this.options, hermesConfigFieldDto.options) && this.secret == hermesConfigFieldDto.secret;
    }

    public int hashCode() {
        return (((((((this.type.hashCode() * 31) + this.description.hashCode()) * 31) + this.category.hashCode()) * 31) + this.options.hashCode()) * 31) + Boolean.hashCode(this.secret);
    }

    public String toString() {
        return "HermesConfigFieldDto(type=" + this.type + ", description=" + this.description + ", category=" + this.category + ", options=" + this.options + ", secret=" + this.secret + ")";
    }

    /* JADX INFO: compiled from: HermesConfigApi.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/HermesConfigFieldDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/HermesConfigFieldDto;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<HermesConfigFieldDto> serializer() {
            return HermesConfigFieldDto$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ HermesConfigFieldDto(int seen0, String type, String description, String category, List options, boolean secret, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen0 & 1) == 0) {
            this.type = "string";
        } else {
            this.type = type;
        }
        if ((seen0 & 2) == 0) {
            this.description = "";
        } else {
            this.description = description;
        }
        if ((seen0 & 4) == 0) {
            this.category = "general";
        } else {
            this.category = category;
        }
        if ((seen0 & 8) == 0) {
            this.options = CollectionsKt.emptyList();
        } else {
            this.options = options;
        }
        if ((seen0 & 16) == 0) {
            this.secret = false;
        } else {
            this.secret = secret;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(HermesConfigFieldDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.type, "string")) {
            output.encodeStringElement(serialDesc, 0, self.type);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.description, "")) {
            output.encodeStringElement(serialDesc, 1, self.description);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.category, "general")) {
            output.encodeStringElement(serialDesc, 2, self.category);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.options, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 3, kSerializerArr[3], self.options);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.secret) {
            output.encodeBooleanElement(serialDesc, 4, self.secret);
        }
    }

    public HermesConfigFieldDto(String type, String description, String category, List<String> options, boolean secret) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(options, "options");
        this.type = type;
        this.description = description;
        this.category = category;
        this.options = options;
        this.secret = secret;
    }

    public /* synthetic */ HermesConfigFieldDto(String str, String str2, String str3, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "string" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "general" : str3, (i & 8) != 0 ? CollectionsKt.emptyList() : list, (i & 16) != 0 ? false : z);
    }

    public final String getType() {
        return this.type;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCategory() {
        return this.category;
    }

    public final List<String> getOptions() {
        return this.options;
    }

    public final boolean getSecret() {
        return this.secret;
    }
}
