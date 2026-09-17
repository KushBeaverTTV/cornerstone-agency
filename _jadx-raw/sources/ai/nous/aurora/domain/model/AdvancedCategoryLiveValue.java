package ai.nous.aurora.domain.model;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: AdvancedCategoryLiveValue.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\tJ\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J0\u0010\u000e\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lai/nous/aurora/domain/model/AdvancedCategoryLiveValue;", "", "<init>", "()V", "FORBIDDEN_EDIT", "", "liveSummary", "id", "values", "", "purposeFallback", "isForbiddenEditLabel", "", "label", "displaySummary", "v", "Lkotlin/Function1;", "onOff", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AdvancedCategoryLiveValue {
    public static final int $stable = 0;
    public static final String FORBIDDEN_EDIT = "Edit";
    public static final AdvancedCategoryLiveValue INSTANCE = new AdvancedCategoryLiveValue();

    private AdvancedCategoryLiveValue() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String liveSummary$lambda$0(Map $values, String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        String str = (String) $values.get(path);
        String string = str != null ? StringsKt.trim((CharSequence) str).toString() : null;
        return string == null ? "" : string;
    }

    /* JADX WARN: Code duplicated, block: B:158:0x0378  */
    /* JADX WARN: Code duplicated, block: B:160:0x0397  */
    /* JADX WARN: Code duplicated, block: B:162:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:179:0x040f  */
    /* JADX WARN: Code duplicated, block: B:182:0x0418  */
    /* JADX WARN: Code duplicated, block: B:185:0x042a  */
    /* JADX WARN: Code duplicated, block: B:187:0x044a  */
    /* JADX WARN: Code duplicated, block: B:189:0x0465  */
    /* JADX WARN: Code duplicated, block: B:192:0x0473  */
    /* JADX WARN: Code duplicated, block: B:197:0x047a A[LOOP:0: B:183:0x0424->B:197:0x047a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:201:0x0486  */
    /* JADX WARN: Code duplicated, block: B:204:0x0490  */
    /* JADX WARN: Code duplicated, block: B:212:0x0481 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:213:0x0480 A[SYNTHETIC] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String liveSummary(String id, final Map<String, String> values) {
        String strInvoke;
        String strInvoke2;
        String strInvoke3;
        String en;
        Iterator it;
        Object element$iv;
        String path;
        String value;
        char c;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(values, "values");
        Function1<? super String, String> function1 = new Function1() { // from class: ai.nous.aurora.domain.model.AdvancedCategoryLiveValue$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AdvancedCategoryLiveValue.liveSummary$lambda$0(values, (String) obj);
            }
        };
        Function1<? super String, String> function2 = new Function1() { // from class: ai.nous.aurora.domain.model.AdvancedCategoryLiveValue$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AdvancedCategoryLiveValue.liveSummary$lambda$1((String) obj);
            }
        };
        String lowerCase = id.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        boolean z3 = false;
        char c2 = 2;
        String strInvoke4 = "";
        switch (lowerCase.hashCode()) {
            case -1360467711:
                if (!lowerCase.equals("telegram")) {
                    Iterable $this$firstOrNull$iv = values.entrySet();
                    it = $this$firstOrNull$iv.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry = (Map.Entry) element$iv;
                            path = (String) entry.getKey();
                            value = (String) entry.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = c2;
                                z = z3;
                            } else {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null) || Intrinsics.areEqual(StringsKt.substringBefore$default(path, '.', (String) null, 2, (Object) null), id)) {
                                    z2 = true;
                                }
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                    }
                    Map.Entry entry2 = (Map.Entry) element$iv;
                    strInvoke = entry2 != null ? (String) entry2.getValue() : null;
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    strInvoke2 = function1.invoke(id + ".status");
                    if (StringsKt.isBlank(strInvoke2)) {
                        strInvoke3 = function1.invoke(id + ".enabled");
                        if (StringsKt.isBlank(strInvoke3)) {
                            strInvoke3 = function1.invoke("platforms." + id + ".enabled");
                        }
                        en = strInvoke3;
                        if (!StringsKt.equals(en, "true", true) || Intrinsics.areEqual(en, "1") || StringsKt.equals(en, DebugKt.DEBUG_PROPERTY_VALUE_ON, true)) {
                            strInvoke4 = "configured";
                        } else if (StringsKt.equals(en, "false", true) || Intrinsics.areEqual(en, "0") || StringsKt.equals(en, DebugKt.DEBUG_PROPERTY_VALUE_OFF, true)) {
                            strInvoke4 = "disabled";
                        }
                        strInvoke2 = strInvoke4;
                    }
                    strInvoke4 = strInvoke2;
                }
                break;
            case -1181248900:
                if (!lowerCase.equals("terminal")) {
                    Iterable $this$firstOrNull$iv2 = values.entrySet();
                    it = $this$firstOrNull$iv2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry3 = (Map.Entry) element$iv;
                            path = (String) entry3.getKey();
                            value = (String) entry3.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry4 = (Map.Entry) element$iv;
                    if (entry4 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke5 = function1.invoke("terminal.backend");
                    if (StringsKt.isBlank(strInvoke5)) {
                        strInvoke5 = function1.invoke("terminal.cwd");
                    }
                    strInvoke4 = strInvoke5;
                }
                break;
            case -1077756671:
                if (!lowerCase.equals("memory")) {
                    Iterable $this$firstOrNull$iv3 = values.entrySet();
                    it = $this$firstOrNull$iv3.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry5 = (Map.Entry) element$iv;
                            path = (String) entry5.getKey();
                            value = (String) entry5.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry6 = (Map.Entry) element$iv;
                    if (entry6 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke6 = function1.invoke("memory.provider");
                    if (StringsKt.isBlank(strInvoke6)) {
                        String strInvoke7 = function1.invoke("memory.memory_enabled");
                        String it2 = strInvoke7;
                        if (StringsKt.isBlank(it2)) {
                            strInvoke7 = null;
                        }
                        String str = strInvoke7;
                        strInvoke = str != null ? function2.invoke(str) : null;
                        strInvoke6 = strInvoke != null ? strInvoke : "";
                    }
                    strInvoke4 = strInvoke6;
                }
                break;
            case -189118908:
                if (!lowerCase.equals("gateway")) {
                    Iterable $this$firstOrNull$iv4 = values.entrySet();
                    it = $this$firstOrNull$iv4.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry7 = (Map.Entry) element$iv;
                            path = (String) entry7.getKey();
                            value = (String) entry7.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry8 = (Map.Entry) element$iv;
                    if (entry8 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke8 = function1.invoke("gateway.host");
                    if (StringsKt.isBlank(strInvoke8)) {
                        strInvoke8 = function1.invoke("gateway.port");
                    }
                    strInvoke4 = strInvoke8;
                }
                break;
            case -80148248:
                if (!lowerCase.equals("general")) {
                    Iterable $this$firstOrNull$iv5 = values.entrySet();
                    it = $this$firstOrNull$iv5.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry9 = (Map.Entry) element$iv;
                            path = (String) entry9.getKey();
                            value = (String) entry9.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry10 = (Map.Entry) element$iv;
                    if (entry10 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke9 = function1.invoke("model.provider");
                    if (StringsKt.isBlank(strInvoke9)) {
                        strInvoke9 = function1.invoke("model.default");
                    }
                    strInvoke4 = strInvoke9;
                }
                break;
            case 114227:
                if (!lowerCase.equals("stt")) {
                    Iterable $this$firstOrNull$iv6 = values.entrySet();
                    it = $this$firstOrNull$iv6.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry11 = (Map.Entry) element$iv;
                            path = (String) entry11.getKey();
                            value = (String) entry11.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry12 = (Map.Entry) element$iv;
                    if (entry12 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    strInvoke4 = function1.invoke("stt.provider");
                }
                break;
            case 115187:
                if (!lowerCase.equals("tts")) {
                    Iterable $this$firstOrNull$iv7 = values.entrySet();
                    it = $this$firstOrNull$iv7.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry13 = (Map.Entry) element$iv;
                            path = (String) entry13.getKey();
                            value = (String) entry13.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry14 = (Map.Entry) element$iv;
                    if (entry14 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    strInvoke4 = function1.invoke("tts.provider");
                }
                break;
            case 3062414:
                if (!lowerCase.equals("cron")) {
                    Iterable $this$firstOrNull$iv8 = values.entrySet();
                    it = $this$firstOrNull$iv8.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry15 = (Map.Entry) element$iv;
                            path = (String) entry15.getKey();
                            value = (String) entry15.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry16 = (Map.Entry) element$iv;
                    if (entry16 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke10 = function1.invoke("cron.enabled");
                    String it3 = strInvoke10;
                    if (StringsKt.isBlank(it3)) {
                        strInvoke10 = null;
                    }
                    String str2 = strInvoke10;
                    strInvoke = str2 != null ? function2.invoke(str2) : null;
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                }
                break;
            case 92750597:
                if (!lowerCase.equals("agent")) {
                    Iterable $this$firstOrNull$iv9 = values.entrySet();
                    it = $this$firstOrNull$iv9.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry17 = (Map.Entry) element$iv;
                            path = (String) entry17.getKey();
                            value = (String) entry17.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry18 = (Map.Entry) element$iv;
                    if (entry18 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String[] strArr = new String[2];
                    String strInvoke11 = function1.invoke("model.default");
                    if (StringsKt.isBlank(strInvoke11)) {
                        strInvoke11 = null;
                    }
                    strArr[0] = strInvoke11;
                    String strInvoke12 = function1.invoke("agent.max_turns");
                    String it4 = strInvoke12;
                    if (StringsKt.isBlank(it4)) {
                        strInvoke12 = null;
                    }
                    String it5 = strInvoke12;
                    strArr[1] = it5 != null ? it5 + " turns" : null;
                    strInvoke4 = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) strArr), " · ", null, null, 0, null, null, 62, null);
                }
                break;
            case 150940456:
                if (!lowerCase.equals("browser")) {
                    Iterable $this$firstOrNull$iv10 = values.entrySet();
                    it = $this$firstOrNull$iv10.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry19 = (Map.Entry) element$iv;
                            path = (String) entry19.getKey();
                            value = (String) entry19.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry110 = (Map.Entry) element$iv;
                    if (entry110 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke13 = function1.invoke("browser.cloud_provider");
                    if (StringsKt.isBlank(strInvoke13)) {
                        strInvoke13 = function1.invoke("browser.use_gateway");
                    }
                    strInvoke4 = strInvoke13;
                }
                break;
            case 342281055:
                if (!lowerCase.equals("logging")) {
                    Iterable $this$firstOrNull$iv11 = values.entrySet();
                    it = $this$firstOrNull$iv11.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry111 = (Map.Entry) element$iv;
                            path = (String) entry111.getKey();
                            value = (String) entry111.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry112 = (Map.Entry) element$iv;
                    if (entry112 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke14 = function1.invoke("logging.level");
                    if (StringsKt.isBlank(strInvoke14)) {
                        strInvoke14 = function1.invoke("logging.file");
                    }
                    String strInvoke15 = strInvoke14;
                    if (StringsKt.isBlank(strInvoke15)) {
                        strInvoke15 = function1.invoke("logging.path");
                    }
                    strInvoke4 = strInvoke15;
                }
                break;
            case 756240582:
                if (!lowerCase.equals("auxiliary")) {
                    Iterable $this$firstOrNull$iv12 = values.entrySet();
                    it = $this$firstOrNull$iv12.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry113 = (Map.Entry) element$iv;
                            path = (String) entry113.getKey();
                            value = (String) entry113.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry114 = (Map.Entry) element$iv;
                    if (entry114 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke16 = function1.invoke("auxiliary.vision_model");
                    if (StringsKt.isBlank(strInvoke16)) {
                        strInvoke16 = function1.invoke("auxiliary.web_extract_model");
                    }
                    String str3 = strInvoke16;
                    if (StringsKt.isBlank(str3)) {
                        String strInvoke17 = function1.invoke("auxiliary.moa_enabled");
                        String it6 = strInvoke17;
                        if (StringsKt.isBlank(it6)) {
                            strInvoke17 = null;
                        }
                        String it7 = strInvoke17;
                        strInvoke = it7 != null ? "moa " + ((Object) function2.invoke(it7)) : null;
                        str3 = strInvoke != null ? strInvoke : "";
                    }
                    strInvoke4 = str3;
                }
                break;
            case 949122880:
                if (!lowerCase.equals("security")) {
                    Iterable $this$firstOrNull$iv13 = values.entrySet();
                    it = $this$firstOrNull$iv13.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry115 = (Map.Entry) element$iv;
                            path = (String) entry115.getKey();
                            value = (String) entry115.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry116 = (Map.Entry) element$iv;
                    if (entry116 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke18 = function1.invoke("security.allow_private_urls");
                    if (StringsKt.isBlank(strInvoke18)) {
                        strInvoke18 = function1.invoke("security.allow_private_networks");
                    }
                    String str4 = strInvoke18;
                    if (!StringsKt.isBlank(str4)) {
                        strInvoke4 = "private urls " + ((Object) function2.invoke(str4));
                    } else if (!StringsKt.isBlank(function1.invoke("security.api_server_key_set"))) {
                        strInvoke4 = "keys set";
                    }
                }
                break;
            case 1389669672:
                if (!lowerCase.equals("delegation")) {
                    Iterable $this$firstOrNull$iv14 = values.entrySet();
                    it = $this$firstOrNull$iv14.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry117 = (Map.Entry) element$iv;
                            path = (String) entry117.getKey();
                            value = (String) entry117.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry118 = (Map.Entry) element$iv;
                    if (entry118 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke19 = function1.invoke("delegation.model");
                    if (StringsKt.isBlank(strInvoke19)) {
                        strInvoke19 = function1.invoke("delegation.provider");
                    }
                    strInvoke4 = strInvoke19;
                }
                break;
            case 1431984486:
                if (!lowerCase.equals("compression")) {
                    Iterable $this$firstOrNull$iv15 = values.entrySet();
                    it = $this$firstOrNull$iv15.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry119 = (Map.Entry) element$iv;
                            path = (String) entry119.getKey();
                            value = (String) entry119.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry1110 = (Map.Entry) element$iv;
                    if (entry1110 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    String strInvoke20 = function1.invoke("compression.enabled");
                    String it8 = strInvoke20;
                    if (StringsKt.isBlank(it8)) {
                        strInvoke20 = null;
                    }
                    String str5 = strInvoke20;
                    strInvoke = str5 != null ? function2.invoke(str5) : null;
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                }
                break;
            case 1671380268:
                if (!lowerCase.equals("discord")) {
                    Iterable $this$firstOrNull$iv16 = values.entrySet();
                    it = $this$firstOrNull$iv16.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry1111 = (Map.Entry) element$iv;
                            path = (String) entry1111.getKey();
                            value = (String) entry1111.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry1112 = (Map.Entry) element$iv;
                    if (entry1112 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    strInvoke2 = function1.invoke(id + ".status");
                    if (StringsKt.isBlank(strInvoke2)) {
                        strInvoke3 = function1.invoke(id + ".enabled");
                        if (StringsKt.isBlank(strInvoke3)) {
                            strInvoke3 = function1.invoke("platforms." + id + ".enabled");
                        }
                        en = strInvoke3;
                        if (StringsKt.equals(en, "true", true)) {
                            strInvoke4 = "configured";
                        } else {
                            strInvoke4 = "configured";
                        }
                        strInvoke2 = strInvoke4;
                    }
                    strInvoke4 = strInvoke2;
                }
                break;
            case 1671764162:
                if (!lowerCase.equals("display")) {
                    Iterable $this$firstOrNull$iv17 = values.entrySet();
                    it = $this$firstOrNull$iv17.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            element$iv = it.next();
                            Map.Entry entry1113 = (Map.Entry) element$iv;
                            path = (String) entry1113.getKey();
                            value = (String) entry1113.getValue();
                            if (StringsKt.isBlank(value)) {
                                c = 2;
                                z = false;
                                if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                                }
                                z2 = true;
                                if (z2) {
                                    z3 = z;
                                    c2 = c;
                                }
                            } else {
                                c = c2;
                                z = z3;
                            }
                            z2 = z;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            element$iv = null;
                        }
                        break;
                    }
                    Map.Entry entry1114 = (Map.Entry) element$iv;
                    if (entry1114 != null) {
                    }
                    if (strInvoke != null) {
                        strInvoke4 = strInvoke;
                    }
                } else {
                    strInvoke4 = displaySummary(function1, function2);
                }
                break;
            default:
                Iterable $this$firstOrNull$iv18 = values.entrySet();
                it = $this$firstOrNull$iv18.iterator();
                while (true) {
                    if (it.hasNext()) {
                        element$iv = it.next();
                        Map.Entry entry1115 = (Map.Entry) element$iv;
                        path = (String) entry1115.getKey();
                        value = (String) entry1115.getValue();
                        if (StringsKt.isBlank(value)) {
                            c = 2;
                            z = false;
                            if (!StringsKt.startsWith$default(path, id + ".", false, 2, (Object) null)) {
                            }
                            z2 = true;
                            if (z2) {
                                z3 = z;
                                c2 = c;
                            }
                        } else {
                            c = c2;
                            z = z3;
                        }
                        z2 = z;
                        if (z2) {
                            z3 = z;
                            c2 = c;
                        }
                    } else {
                        element$iv = null;
                    }
                    break;
                }
                Map.Entry entry1116 = (Map.Entry) element$iv;
                if (entry1116 != null) {
                }
                if (strInvoke != null) {
                    strInvoke4 = strInvoke;
                }
                break;
        }
        String live = StringsKt.trim((CharSequence) strInvoke4).toString();
        return (StringsKt.isBlank(live) || StringsKt.equals(live, FORBIDDEN_EDIT, true)) ? purposeFallback(id) : live;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String liveSummary$lambda$1(String raw) {
        Intrinsics.checkNotNullParameter(raw, "raw");
        String lowerCase = raw.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case 48:
                return !lowerCase.equals("0") ? raw : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
            case 49:
                if (!lowerCase.equals("1")) {
                    return raw;
                }
                break;
            case 3521:
                return !lowerCase.equals("no") ? raw : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
            case 3551:
                if (!lowerCase.equals(DebugKt.DEBUG_PROPERTY_VALUE_ON)) {
                    return raw;
                }
                break;
            case 109935:
                return !lowerCase.equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF) ? raw : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
            case 119527:
                if (!lowerCase.equals("yes")) {
                    return raw;
                }
                break;
            case 3569038:
                if (!lowerCase.equals("true")) {
                    return raw;
                }
                break;
            case 97196323:
                return !lowerCase.equals("false") ? raw : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
            default:
                return raw;
        }
        return DebugKt.DEBUG_PROPERTY_VALUE_ON;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String purposeFallback(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        String lowerCase = id.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case -1360467711:
                if (lowerCase.equals("telegram")) {
                    return "Bot · platforms";
                }
                break;
            case -1181248900:
                if (lowerCase.equals("terminal")) {
                    return "Backend · cwd · env";
                }
                break;
            case -1077756671:
                if (lowerCase.equals("memory")) {
                    return "Provider · enable";
                }
                break;
            case -189118908:
                if (lowerCase.equals("gateway")) {
                    return "Host API";
                }
                break;
            case -80148248:
                if (lowerCase.equals("general")) {
                    return "Model · provider";
                }
                break;
            case 114227:
                if (lowerCase.equals("stt")) {
                    return "Speech in";
                }
                break;
            case 115187:
                if (lowerCase.equals("tts")) {
                    return "Speech out";
                }
                break;
            case 3062414:
                if (lowerCase.equals("cron")) {
                    return "Scheduled jobs";
                }
                break;
            case 92750597:
                if (lowerCase.equals("agent")) {
                    return "Turns · guidance · tools";
                }
                break;
            case 112386354:
                if (lowerCase.equals("voice")) {
                    return "CLI voice loop";
                }
                break;
            case 150940456:
                if (lowerCase.equals("browser")) {
                    return "Cloud · gateway";
                }
                break;
            case 342281055:
                if (lowerCase.equals("logging")) {
                    return "Level · file";
                }
                break;
            case 756240582:
                if (lowerCase.equals("auxiliary")) {
                    return "Vision · extract";
                }
                break;
            case 949122880:
                if (lowerCase.equals("security")) {
                    return "Allowlists · privacy";
                }
                break;
            case 1389669672:
                if (lowerCase.equals("delegation")) {
                    return "Subagent model";
                }
                break;
            case 1431984486:
                if (lowerCase.equals("compression")) {
                    return "Context compression";
                }
                break;
            case 1671380268:
                if (lowerCase.equals("discord")) {
                    return "Bot · platforms";
                }
                break;
            case 1671764162:
                if (lowerCase.equals("display")) {
                    return "Skin · resume · streaming";
                }
                break;
        }
        String strReplace$default = StringsKt.replace$default(id, '_', ' ', false, 4, (Object) null);
        if (!(strReplace$default.length() > 0)) {
            return strReplace$default;
        }
        StringBuilder sb = new StringBuilder();
        char it = strReplace$default.charAt(0);
        String strValueOf = String.valueOf(it);
        Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
        String upperCase = strValueOf.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        StringBuilder sbAppend = sb.append((Object) upperCase);
        String strSubstring = strReplace$default.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }

    public final boolean isForbiddenEditLabel(String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        return StringsKt.equals(StringsKt.trim((CharSequence) label).toString(), FORBIDDEN_EDIT, true);
    }

    private final String displaySummary(Function1<? super String, String> v, Function1<? super String, String> onOff) {
        String personality = v.invoke("display.personality");
        String skin = v.invoke("display.skin");
        String theme = v.invoke("display.theme");
        String resume = v.invoke("display.resume_display");
        String iface = v.invoke("display.interface");
        String strInvoke = v.invoke("display.streaming");
        String it = strInvoke;
        boolean zIsBlank = StringsKt.isBlank(it);
        String reasoning = null;
        if (zIsBlank) {
            strInvoke = null;
        }
        String str = strInvoke;
        String streaming = str != null ? onOff.invoke(str) : null;
        String strInvoke2 = v.invoke("display.show_reasoning");
        String it2 = strInvoke2;
        if (StringsKt.isBlank(it2)) {
            strInvoke2 = null;
        }
        String it3 = strInvoke2;
        if (it3 != null && Intrinsics.areEqual(onOff.invoke(it3), DebugKt.DEBUG_PROPERTY_VALUE_ON)) {
            reasoning = "reasoning";
        }
        List $this$displaySummary_u24lambda_u2428 = CollectionsKt.createListBuilder();
        if (!StringsKt.isBlank(personality)) {
            $this$displaySummary_u24lambda_u2428.add(personality);
        }
        if (!StringsKt.isBlank(skin) && !StringsKt.equals(skin, "default", true)) {
            $this$displaySummary_u24lambda_u2428.add(skin);
        } else if (!StringsKt.isBlank(skin) && StringsKt.isBlank(personality)) {
            $this$displaySummary_u24lambda_u2428.add(skin);
        }
        if (!StringsKt.isBlank(theme)) {
            $this$displaySummary_u24lambda_u2428.add(theme);
        }
        if (!StringsKt.isBlank(resume)) {
            $this$displaySummary_u24lambda_u2428.add("resume " + resume);
        }
        if (!StringsKt.isBlank(iface) && StringsKt.isBlank(personality) && StringsKt.isBlank(skin)) {
            $this$displaySummary_u24lambda_u2428.add(iface);
        }
        if (Intrinsics.areEqual(streaming, DebugKt.DEBUG_PROPERTY_VALUE_ON)) {
            $this$displaySummary_u24lambda_u2428.add("stream");
        }
        if (reasoning != null && $this$displaySummary_u24lambda_u2428.size() < 3) {
            $this$displaySummary_u24lambda_u2428.add(reasoning);
        }
        List parts = CollectionsKt.build($this$displaySummary_u24lambda_u2428);
        return CollectionsKt.joinToString$default(CollectionsKt.take(parts, 3), " · ", null, null, 0, null, null, 62, null);
    }
}
