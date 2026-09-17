package ai.nous.aurora.data.remote;

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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: HermesConfigModels.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB3\b\u0010\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\fJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J#\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lai/nous/aurora/data/remote/CategoriesResponse;", "", "categories", "", "Lai/nous/aurora/data/remote/ConfigCategory;", "totalFields", "", "<init>", "(Ljava/util/List;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCategories", "()Ljava/util/List;", "getTotalFields$annotations", "()V", "getTotalFields", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class CategoriesResponse {
    private final List<ConfigCategory> categories;
    private final int totalFields;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    static final KSerializer[] $childSerializers = {new ArrayListSerializer(ConfigCategory$$serializer.INSTANCE), null};

    /* JADX WARN: Multi-variable type inference failed */
    public CategoriesResponse() {
        this((List) null, 0, 3, (DefaultConstructorMarker) (null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CategoriesResponse copy$default(CategoriesResponse categoriesResponse, List list, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = categoriesResponse.categories;
        }
        if ((i2 & 2) != 0) {
            i = categoriesResponse.totalFields;
        }
        return categoriesResponse.copy(list, i);
    }


    public final List<ConfigCategory> component1() {
        return this.categories;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getTotalFields() {
        return this.totalFields;
    }

    public final CategoriesResponse copy(List<ConfigCategory> categories, int totalFields) {
        Intrinsics.checkNotNullParameter(categories, "categories");
        return new CategoriesResponse(categories, totalFields);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CategoriesResponse)) {
            return false;
        }
        CategoriesResponse categoriesResponse = (CategoriesResponse) other;
        return Intrinsics.areEqual(this.categories, categoriesResponse.categories) && this.totalFields == categoriesResponse.totalFields;
    }

    public int hashCode() {
        return (this.categories.hashCode() * 31) + Integer.hashCode(this.totalFields);
    }

    public String toString() {
        return "CategoriesResponse(categories=" + this.categories + ", totalFields=" + this.totalFields + ")";
    }

    /* JADX INFO: compiled from: HermesConfigModels.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lai/nous/aurora/data/remote/CategoriesResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lai/nous/aurora/data/remote/CategoriesResponse;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<CategoriesResponse> serializer() {
            return CategoriesResponse$$serializer.INSTANCE;
        }
    }
            public /* synthetic */ CategoriesResponse(int seen0, List categories, int totalFields, SerializationConstructorMarker serializationConstructorMarker) {
                if ((seen0 & 1) == 0) {
                    this.categories = CollectionsKt.emptyList();
                } else {
                    this.categories = categories;
                }
                if ((seen0 & 2) == 0) {
                    this.totalFields = 0;
                } else {
                    this.totalFields = totalFields;
                }
            }

            @JvmStatic

            public static final /* synthetic */ void write$Self$app_debug(CategoriesResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
                KSerializer[] kSerializerArr = $childSerializers;
                if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.categories, CollectionsKt.emptyList())) {
                    output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.categories);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || self.totalFields != 0) {
                    output.encodeIntElement(serialDesc, 1, self.totalFields);
                }
            }

            public CategoriesResponse(List<ConfigCategory> categories, int totalFields) {
                Intrinsics.checkNotNullParameter(categories, "categories");
                this.categories = categories;
                this.totalFields = totalFields;
            }

            public /* synthetic */ CategoriesResponse(List list, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? CollectionsKt.emptyList() : list, (i2 & 2) != 0 ? 0 : i);
            }

            public final List<ConfigCategory> getCategories() {
                return this.categories;
            }
}