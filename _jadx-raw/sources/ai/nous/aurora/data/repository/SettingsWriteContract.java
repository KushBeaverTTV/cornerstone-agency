package ai.nous.aurora.data.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0018\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\f0\u000b¨\u0006\r"}, d2 = {"Lai/nous/aurora/data/repository/SettingsWriteContract;", "", "<init>", "()V", "matches", "", "values", "", "", "Lkotlinx/serialization/json/JsonElement;", "expected", "", "Lkotlin/Pair;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SettingsWriteContract {
    public static final int $stable = 0;
    public static final SettingsWriteContract INSTANCE = new SettingsWriteContract();

    private SettingsWriteContract() {
    }

    public final boolean matches(Map<String, ? extends JsonElement> values, List<? extends Pair<String, ? extends JsonElement>> expected) {
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(expected, "expected");
        List<? extends Pair<String, ? extends JsonElement>> $this$all$iv = expected;
        if (($this$all$iv instanceof Collection) && $this$all$iv.isEmpty()) {
            return true;
        }
        for (Object element$iv : $this$all$iv) {
            Pair pair = (Pair) element$iv;
            String path = (String) pair.component1();
            JsonElement value = (JsonElement) pair.component2();
            if (!Intrinsics.areEqual(values.get(path), value)) {
                return false;
            }
        }
        return true;
    }
}
