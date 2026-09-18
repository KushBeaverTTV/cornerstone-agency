package ai.nous.aurora.device;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: AssistDeviceActionParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u0007J\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0012\u001a\u00020\u0007J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0014\u001a\u00020\u0007J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u0007*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0007H\u0002J\u001b\u0010!\u001a\u0004\u0018\u00010\t*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\"J\u001b\u0010#\u001a\u0004\u0018\u00010$*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010%J&\u0010&\u001a\u00020\u0007*\u00020\u001f2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\b\b\u0002\u0010(\u001a\u00020\tH\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lai/nous/aurora/device/AssistDeviceActionParser;", "", "<init>", "()V", "json", "Lkotlinx/serialization/json/Json;", "marker", "", "maxActions", "", "parseStructured", "", "Lai/nous/aurora/device/AssistDeviceAction;", "raw", "extractJsonArrayPayload", "payload", "extractJsonArrayPayload$app_debug", "parseReply", "reply", "parseTranscript", "transcript", "parseAction", "element", "Lkotlinx/serialization/json/JsonElement;", "parseLegacyMarker", "text", "parseNaturalAlarm", "Lai/nous/aurora/device/AssistDeviceAction$SetAlarm;", "parseNaturalTimer", "Lai/nous/aurora/device/AssistDeviceAction$SetTimer;", "string", "Lkotlinx/serialization/json/JsonObject;", "key", "int", "(Lkotlinx/serialization/json/JsonObject;Ljava/lang/String;)Ljava/lang/Integer;", "long", "", "(Lkotlinx/serialization/json/JsonObject;Ljava/lang/String;)Ljava/lang/Long;", "safeText", "fallback", "maxLength", "validPhoneNumber", "", "number", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AssistDeviceActionParser {
    private static final String marker = "AURORA_DEVICE_ACTIONS:";
    private static final int maxActions = 3;
    public static final AssistDeviceActionParser INSTANCE = new AssistDeviceActionParser();
    private static final Json json = ai.nous.aurora.compat.Compat.d_JsonKt_Json_4(null, new Function1() { // from class: ai.nous.aurora.device.AssistDeviceActionParser$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return AssistDeviceActionParser.json$lambda$0((JsonBuilder) obj);
        }
    }, 1, null);
    public static final int $stable = 8;

    private AssistDeviceActionParser() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    public final List<AssistDeviceAction> parseStructured(String raw) {
        String jsonText;
        Object objM7388constructorimpl;
        String string = raw != null ? StringsKt.trim((CharSequence) raw).toString() : null;
        if (string == null) {
            string = "";
        }
        String payload = string;
        if (!StringsKt.isBlank(payload) && (jsonText = extractJsonArrayPayload$app_debug(payload)) != null) {
            try {
                Result.Companion companion = Result.Companion;
                AssistDeviceActionParser assistDeviceActionParser = this;
                JsonElement toJsonElement = json.parseToJsonElement(jsonText);
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(toJsonElement instanceof JsonArray ? (JsonArray) toJsonElement : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM7388constructorimpl = ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
            }
            JsonArray array = (JsonArray) (ai.nous.aurora.compat.Compat.m7394isFailureimpl(objM7388constructorimpl) ? null : objM7388constructorimpl);
            if (array == null) {
                return CollectionsKt.emptyList();
            }
            Iterable $this$mapNotNull$iv = CollectionsKt.take(array, 3);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                JsonElement p0 = (JsonElement) element$iv$iv$iv;
                AssistDeviceAction action = parseAction(p0);
                if (action != null) {
                    destination$iv$iv.add(action);
                }
            }
            return (List) destination$iv$iv;
        }
        return CollectionsKt.emptyList();
    }

    public final String extractJsonArrayPayload$app_debug(String payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        String trimmed = StringsKt.trim((CharSequence) payload).toString();
        if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(trimmed, "[", false, 2, (Object) null)) {
            return trimmed;
        }
        int idx = ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) trimmed, marker, 0, true, 2, (Object) null);
        if (idx < 0) {
            return null;
        }
        String strSubstring = trimmed.substring(idx + 22);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        String after = StringsKt.trimStart((CharSequence) strSubstring).toString();
        int start = ai.nous.aurora.compat.Compat.d_StringsKt_indexOf_6((CharSequence) after, AbstractJsonLexerKt.BEGIN_LIST, 0, false, 6, (Object) null);
        if (start < 0) {
            return null;
        }
        int depth = 0;
        boolean inString = false;
        boolean escape = false;
        int length = after.length();
        for (int i = start; i < length; i++) {
            char c = after.charAt(i);
            if (inString) {
                if (escape) {
                    escape = false;
                } else if (c == '\\') {
                    escape = true;
                } else if (c == '\"') {
                    inString = false;
                }
            } else {
                switch (c) {
                    case '\"':
                        inString = true;
                        break;
                    case '[':
                        depth++;
                        break;
                    case ']':
                        depth--;
                        if (depth == 0) {
                            String strSubstring2 = after.substring(start, i + 1);
                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                            return strSubstring2;
                        }
                        break;
                }
            }
        }
        return null;
    }

    public final List<AssistDeviceAction> parseReply(String reply) {
        Intrinsics.checkNotNullParameter(reply, "reply");
        List<AssistDeviceAction> structured = parseStructured(reply);
        if (structured.isEmpty()) {
            structured = null;
        }
        if (structured != null) {
            return structured;
        }
        AssistDeviceAction p0 = parseLegacyMarker(reply);
        List<AssistDeviceAction> listListOf = p0 != null ? CollectionsKt.listOf(p0) : null;
        return listListOf == null ? CollectionsKt.emptyList() : listListOf;
    }

    public final List<AssistDeviceAction> parseTranscript(String transcript) {
        List<AssistDeviceAction> listListOf;
        Intrinsics.checkNotNullParameter(transcript, "transcript");
        AssistDeviceAction.SetAlarm p0 = parseNaturalAlarm(transcript);
        if (p0 != null && (listListOf = CollectionsKt.listOf(p0)) != null) {
            return listListOf;
        }
        AssistDeviceAction.SetTimer p1 = parseNaturalTimer(transcript);
        if (p1 != null) {
            return CollectionsKt.listOf(p1);
        }
        return CollectionsKt.emptyList();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final AssistDeviceAction parseAction(JsonElement element) {
        String strString;
        String strString2;
        String p0;
        String strString3;
        String p1;
        Integer numM68int;
        Integer numM68int2;
        JsonObject obj = element instanceof JsonObject ? (JsonObject) element : null;
        if (obj != null && (strString = string(obj, "type")) != null) {
            String type = strString.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(type, "toLowerCase(...)");
            if (type != null) {
                switch (type.hashCode()) {
                    case -1287315460:
                        if (!type.equals("create_calendar_event")) {
                            return null;
                        }
                        String title = safeText$default(this, obj, "title", "Aurora event", 0, 4, null);
                        Long begin = m69long(obj, "begin_ms");
                        Long end = m69long(obj, "end_ms");
                        if (begin != null && begin.longValue() <= 0) {
                            return null;
                        }
                        if (end == null || (begin != null && end.longValue() >= begin.longValue())) {
                            return new AssistDeviceAction.CreateCalendarEvent(title, begin, end);
                        }
                        return null;
                    case -843234004:
                        if (type.equals("compose_sms") && (strString2 = string(obj, "number")) != null && (p0 = StringsKt.trim((CharSequence) strString2).toString()) != null) {
                            if (!validPhoneNumber(p0)) {
                                p0 = null;
                            }
                            if (p0 != null) {
                                String number = p0;
                                return new AssistDeviceAction.ComposeSms(number, safeText(obj, "message", "", 500));
                            }
                        }
                        return null;
                    case -485094478:
                        if (type.equals("open_dialer") && (strString3 = string(obj, "number")) != null && (p1 = StringsKt.trim((CharSequence) strString3).toString()) != null) {
                            if (!validPhoneNumber(p1)) {
                                p1 = null;
                            }
                            if (p1 != null) {
                                String number2 = p1;
                                return new AssistDeviceAction.OpenDialer(number2);
                            }
                        }
                        return null;
                    case 915228724:
                        if (!type.equals("set_alarm") || (numM68int = m68int(obj, "hour")) == null) {
                            return null;
                        }
                        int hour = numM68int.intValue();
                        Integer numM68int3 = m68int(obj, "minute");
                        if (numM68int3 == null) {
                            return null;
                        }
                        int minute = numM68int3.intValue();
                        if (hour >= 0 && hour < 24) {
                            if (minute >= 0 && minute < 60) {
                                return new AssistDeviceAction.SetAlarm(hour, minute, safeText$default(this, obj, "label", "Cornerstone", 0, 4, null));
                            }
                        }
                        return null;
                    case 932697384:
                        if (!type.equals("set_timer") || (numM68int2 = m68int(obj, "seconds")) == null) {
                            return null;
                        }
                        int seconds = numM68int2.intValue();
                        if (1 <= seconds && seconds < 86401) {
                            return new AssistDeviceAction.SetTimer(seconds, safeText$default(this, obj, "label", "Aurora timer", 0, 4, null));
                        }
                        return null;
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    private final AssistDeviceAction parseLegacyMarker(String text) {
        Integer intOrNull;
        boolean z = false;
        MatchResult alarm = ai.nous.aurora.compat.Compat.d_Regex_find_5(new Regex("(?im)^\\s*AURORA_ACTION:ALARM\\s+hour=(\\d{1,2})\\s+minute=(\\d{1,2})(?:\\s+label=([^\\n]+))?\\s*$"), text, 0, 2, null);
        if (alarm != null) {
            Integer intOrNull2 = StringsKt.toIntOrNull(alarm.getGroupValues().get(1));
            if (intOrNull2 == null) {
                return null;
            }
            int hour = intOrNull2.intValue();
            Integer intOrNull3 = StringsKt.toIntOrNull(alarm.getGroupValues().get(2));
            if (intOrNull3 == null) {
                return null;
            }
            int minute = intOrNull3.intValue();
            if (hour >= 0 && hour < 24) {
                if (minute >= 0 && minute < 60) {
                    z = true;
                }
                if (z) {
                    String str = (String) CollectionsKt.getOrNull(alarm.getGroupValues(), 3);
                    String strTake = StringsKt.take(StringsKt.trim((CharSequence) (str != null ? str : "")).toString(), 80);
                    if (StringsKt.isBlank(strTake)) {
                        strTake = "Cornerstone";
                    }
                    return new AssistDeviceAction.SetAlarm(hour, minute, strTake);
                }
            }
            return null;
        }
        MatchResult timer = ai.nous.aurora.compat.Compat.d_Regex_find_5(new Regex("(?im)^\\s*AURORA_ACTION:TIMER\\s+seconds=(\\d+)(?:\\s+(?:message|label)=([^\\n]+))?\\s*$"), text, 0, 2, null);
        if (timer == null || (intOrNull = StringsKt.toIntOrNull(timer.getGroupValues().get(1))) == null) {
            return null;
        }
        int seconds = intOrNull.intValue();
        if (1 <= seconds && seconds < 86401) {
            z = true;
        }
        if (!z) {
            return null;
        }
        String str2 = (String) CollectionsKt.getOrNull(timer.getGroupValues(), 2);
        String strTake2 = StringsKt.take(StringsKt.trim((CharSequence) (str2 != null ? str2 : "")).toString(), 80);
        if (StringsKt.isBlank(strTake2)) {
            strTake2 = "Aurora timer";
        }
        return new AssistDeviceAction.SetTimer(seconds, strTake2);
    }

    private final AssistDeviceAction.SetAlarm parseNaturalAlarm(String text) {
        Integer intOrNull;
        boolean z = false;
        MatchResult match = ai.nous.aurora.compat.Compat.d_Regex_find_5(new Regex("(?i)\\b(?:set\\s+(?:an\\s+)?alarm(?:\\s+(?:for|at))?|alarm\\s+(?:for|at)|wake\\s+me(?:\\s+(?:at|for))?)\\s+(\\d{1,2})(?::(\\d{2}))?\\s*(a\\.?m\\.?|p\\.?m\\.?)?\\b"), text, 0, 2, null);
        if (match == null || (intOrNull = StringsKt.toIntOrNull(match.getGroupValues().get(1))) == null) {
            return null;
        }
        int hour = intOrNull.intValue();
        String str = match.getGroupValues().get(2);
        if (StringsKt.isBlank(str)) {
            str = "0";
        }
        Integer intOrNull2 = StringsKt.toIntOrNull(str);
        if (intOrNull2 == null) {
            return null;
        }
        int minute = intOrNull2.intValue();
        String lowerCase = match.getGroupValues().get(3).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String meridiem = ai.nous.aurora.compat.Compat.d_StringsKt_replace_6(lowerCase, ".", "", false, 4, (Object) null);
        if (!(minute >= 0 && minute < 60)) {
            return null;
        }
        if (!StringsKt.isBlank(meridiem)) {
            if (1 <= hour && hour < 13) {
                z = true;
            }
            if (!z) {
                return null;
            }
            if (Intrinsics.areEqual(meridiem, "pm") && hour != 12) {
                hour += 12;
            }
            if (Intrinsics.areEqual(meridiem, "am") && hour == 12) {
                hour = 0;
            }
        } else {
            if (hour >= 0 && hour < 24) {
                z = true;
            }
            if (!z) {
                return null;
            }
        }
        return new AssistDeviceAction.SetAlarm(hour, minute, null, 4, null);
    }

    private final AssistDeviceAction.SetTimer parseNaturalTimer(String text) {
        Long longOrNull;
        long multiplier;
        boolean z = false;
        MatchResult match = ai.nous.aurora.compat.Compat.d_Regex_find_5(new Regex("(?i)\\b(?:set\\s+(?:a\\s+)?timer|start\\s+(?:a\\s+)?timer|timer)\\s+(?:for\\s+)?(\\d+)\\s*(seconds?|secs?|minutes?|mins?|hours?|hrs?)\\b"), text, 0, 2, null);
        if (match == null || (longOrNull = StringsKt.toLongOrNull(match.getGroupValues().get(1))) == null) {
            return null;
        }
        long amount = longOrNull.longValue();
        String unit = match.getGroupValues().get(2).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(unit, "toLowerCase(...)");
        if (ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(unit, "hour", false, 2, (Object) null) || ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(unit, "hr", false, 2, (Object) null)) {
            multiplier = 3600;
        } else {
            multiplier = ai.nous.aurora.compat.Compat.d_StringsKt_startsWith_5(unit, "min", false, 2, (Object) null) ? 60L : 1L;
        }
        long seconds = amount * multiplier;
        if (1 <= seconds && seconds < 86401) {
            z = true;
        }
        if (z) {
            return new AssistDeviceAction.SetTimer((int) seconds, null, 2, null);
        }
        return null;
    }

    private final String string(JsonObject $this$string, String key) {
        Object obj = $this$string.get((Object) key);
        JsonPrimitive jsonPrimitive = obj instanceof JsonPrimitive ? (JsonPrimitive) obj : null;
        if (jsonPrimitive != null) {
            return JsonElementKt.getContentOrNull(jsonPrimitive);
        }
        return null;
    }

    /* JADX INFO: renamed from: int, reason: not valid java name */
    private final Integer m68int(JsonObject $this$int, String key) {
        Object obj = $this$int.get((Object) key);
        JsonPrimitive jsonPrimitive = obj instanceof JsonPrimitive ? (JsonPrimitive) obj : null;
        if (jsonPrimitive != null) {
            return JsonElementKt.getIntOrNull(jsonPrimitive);
        }
        return null;
    }

    /* JADX INFO: renamed from: long, reason: not valid java name */
    private final Long m69long(JsonObject $this$long, String key) {
        Object obj = $this$long.get((Object) key);
        JsonPrimitive jsonPrimitive = obj instanceof JsonPrimitive ? (JsonPrimitive) obj : null;
        if (jsonPrimitive != null) {
            return JsonElementKt.getLongOrNull(jsonPrimitive);
        }
        return null;
    }

    static /* synthetic */ String safeText$default(AssistDeviceActionParser assistDeviceActionParser, JsonObject jsonObject, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 80;
        }
        return assistDeviceActionParser.safeText(jsonObject, str, str2, i);
    }

    private final String safeText(JsonObject $this$safeText, String key, String fallback, int maxLength) {
        String string;
        String strTake;
        String strString = string($this$safeText, key);
        if (strString != null && (string = StringsKt.trim((CharSequence) strString).toString()) != null && (strTake = StringsKt.take(string, maxLength)) != null) {
            String str = strTake;
            if (StringsKt.isBlank(str)) {
                str = fallback;
            }
            String str2 = str;
            if (str2 != null) {
                return str2;
            }
        }
        return fallback;
    }

    private final boolean validPhoneNumber(String number) {
        int length = number.length();
        if (3 <= length && length < 41) {
            if (new Regex("[+0-9(). -]+").matches(number)) {
                return true;
            }
        }
        return false;
    }
}
