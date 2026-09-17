package ai.nous.aurora.data.repository;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\nJ\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lai/nous/aurora/data/repository/SettingsLastGoodCodec;", "", "<init>", "()V", "json", "Lkotlinx/serialization/json/Json;", "encode", "", "schemaSource", "values", "", "Lkotlinx/serialization/json/JsonElement;", "decode", "Lai/nous/aurora/data/repository/SettingsLastGoodPayload;", "raw", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SettingsLastGoodCodec {
    public static final SettingsLastGoodCodec INSTANCE = new SettingsLastGoodCodec();
    private static final Json json = JsonKt.Json$default(null, new Function1() { // from class: ai.nous.aurora.data.repository.SettingsLastGoodCodec$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SettingsLastGoodCodec.json$lambda$0((JsonBuilder) obj);
        }
    }, 1, null);
    public static final int $stable = 8;

    private SettingsLastGoodCodec() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    public final String encode(String schemaSource, Map<String, ? extends JsonElement> values) {
        Intrinsics.checkNotNullParameter(schemaSource, "schemaSource");
        Intrinsics.checkNotNullParameter(values, "values");
        JsonObject root = new JsonObject(MapsKt.mapOf(TuplesKt.to("schemaSource", JsonElementKt.JsonPrimitive(schemaSource)), TuplesKt.to("values", new JsonObject(values))));
        return json.encodeToString(JsonObject.INSTANCE.serializer(), root);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0060  */
    public final SettingsLastGoodPayload decode(String raw) {
        Object objM7388constructorimpl;
        String p0;
        Intrinsics.checkNotNullParameter(raw, "raw");
        try {
            Result.Companion companion = Result.INSTANCE;
            SettingsLastGoodCodec settingsLastGoodCodec = this;
            JsonElement toJsonElement = json.parseToJsonElement(raw);
            JsonObject root = toJsonElement instanceof JsonObject ? (JsonObject) toJsonElement : null;
            if (root == null) {
                return null;
            }
            Object obj = root.get((Object) "values");
            JsonObject values = obj instanceof JsonObject ? (JsonObject) obj : null;
            if (values == null || values.isEmpty()) {
                return null;
            }
            Object obj2 = root.get((Object) "schemaSource");
            JsonPrimitive jsonPrimitive = obj2 instanceof JsonPrimitive ? (JsonPrimitive) obj2 : null;
            if (jsonPrimitive == null || (p0 = JsonElementKt.getContentOrNull(jsonPrimitive)) == null) {
                p0 = "disk-last-good";
            } else {
                if (StringsKt.isBlank(p0)) {
                    p0 = null;
                }
                if (p0 == null) {
                    p0 = "disk-last-good";
                }
            }
            objM7388constructorimpl = Result.m7388constructorimpl(new SettingsLastGoodPayload(p0, values));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7388constructorimpl = Result.m7388constructorimpl(ResultKt.createFailure(th));
        }
        return (SettingsLastGoodPayload) (Result.m7394isFailureimpl(objM7388constructorimpl) ? null : objM7388constructorimpl);
    }
}
