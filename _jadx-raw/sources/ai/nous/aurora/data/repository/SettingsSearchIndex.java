package ai.nous.aurora.data.repository;

import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsSearchIndex.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006Ja\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006J\u0018\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lai/nous/aurora/data/repository/SettingsSearchIndex;", "", "<init>", "()V", "MOA_ALIASES", "", "", "ASSIST_ALIASES", "SECRET_FRAGMENTS", "isSecretPath", "", "path", "search", "Lai/nous/aurora/data/repository/SettingsSearchIndex$Hit;", "query", "fields", "Lai/nous/aurora/data/repository/HermesSettingField;", "values", "", "categoryOrder", "moaDefaultPreset", "moaReferenceCount", "", "(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;)Ljava/util/List;", "categoryLabel", "id", "humanize", "description", "Hit", "Kind", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SettingsSearchIndex {
    public static final SettingsSearchIndex INSTANCE = new SettingsSearchIndex();
    private static final List<String> MOA_ALIASES = CollectionsKt.listOf((Object[]) new String[]{"moa", "/moa", "mixture", "mixture of agents", "mixture-of-agents", "mixtureofagents", "reference models", "reference model", "aggregator", "moa preset", "powerhouse"});
    private static final List<String> ASSIST_ALIASES = CollectionsKt.listOf((Object[]) new String[]{"assist", "assistant", "voice", "voice assistant", "dictation", "microphone", "mic", "orb", "power", "power button", "power-button", "auto-listen", "autolisten", "speak replies", "aurora assist"});
    private static final List<String> SECRET_FRAGMENTS = CollectionsKt.listOf((Object[]) new String[]{"api_key", "apikey", "token", HintConstants.AUTOFILL_HINT_PASSWORD, "secret", "credential", "private_key", "authorization"});
    public static final int $stable = 8;

    /* JADX INFO: compiled from: SettingsSearchIndex.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\tHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006%"}, d2 = {"Lai/nous/aurora/data/repository/SettingsSearchIndex$Hit;", "", "id", "", "label", "subtitle", "kind", "Lai/nous/aurora/data/repository/SettingsSearchIndex$Kind;", "score", "", "path", "categoryId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lai/nous/aurora/data/repository/SettingsSearchIndex$Kind;ILjava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLabel", "getSubtitle", "getKind", "()Lai/nous/aurora/data/repository/SettingsSearchIndex$Kind;", "getScore", "()I", "getPath", "getCategoryId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Hit {
        public static final int $stable = 0;
        private final String categoryId;
        private final String id;
        private final Kind kind;
        private final String label;
        private final String path;
        private final int score;
        private final String subtitle;

        public static /* synthetic */ Hit copy$default(Hit hit, String str, String str2, String str3, Kind kind, int i, String str4, String str5, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = hit.id;
            }
            if ((i2 & 2) != 0) {
                str2 = hit.label;
            }
            String str6 = str2;
            if ((i2 & 4) != 0) {
                str3 = hit.subtitle;
            }
            String str7 = str3;
            if ((i2 & 8) != 0) {
                kind = hit.kind;
            }
            Kind kind2 = kind;
            if ((i2 & 16) != 0) {
                i = hit.score;
            }
            int i3 = i;
            if ((i2 & 32) != 0) {
                str4 = hit.path;
            }
            String str8 = str4;
            if ((i2 & 64) != 0) {
                str5 = hit.categoryId;
            }
            return hit.copy(str, str6, str7, kind2, i3, str8, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSubtitle() {
            return this.subtitle;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Kind getKind() {
            return this.kind;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCategoryId() {
            return this.categoryId;
        }

        public final Hit copy(String id, String label, String subtitle, Kind kind, int score, String path, String categoryId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Intrinsics.checkNotNullParameter(kind, "kind");
            return new Hit(id, label, subtitle, kind, score, path, categoryId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Hit)) {
                return false;
            }
            Hit hit = (Hit) other;
            return Intrinsics.areEqual(this.id, hit.id) && Intrinsics.areEqual(this.label, hit.label) && Intrinsics.areEqual(this.subtitle, hit.subtitle) && this.kind == hit.kind && this.score == hit.score && Intrinsics.areEqual(this.path, hit.path) && Intrinsics.areEqual(this.categoryId, hit.categoryId);
        }

        public int hashCode() {
            return (((((((((((this.id.hashCode() * 31) + this.label.hashCode()) * 31) + this.subtitle.hashCode()) * 31) + this.kind.hashCode()) * 31) + Integer.hashCode(this.score)) * 31) + (this.path == null ? 0 : this.path.hashCode())) * 31) + (this.categoryId != null ? this.categoryId.hashCode() : 0);
        }

        public String toString() {
            return "Hit(id=" + this.id + ", label=" + this.label + ", subtitle=" + this.subtitle + ", kind=" + this.kind + ", score=" + this.score + ", path=" + this.path + ", categoryId=" + this.categoryId + ")";
        }

        public Hit(String id, String label, String subtitle, Kind kind, int score, String path, String categoryId) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Intrinsics.checkNotNullParameter(kind, "kind");
            this.id = id;
            this.label = label;
            this.subtitle = subtitle;
            this.kind = kind;
            this.score = score;
            this.path = path;
            this.categoryId = categoryId;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ Hit(String str, String str2, String str3, Kind kind, int i, String str4, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            String str6;
            String str7;
            if ((i2 & 32) == 0) {
                str6 = str4;
            } else {
                str6 = null;
            }
            if ((i2 & 64) == 0) {
                str7 = str5;
            } else {
                str7 = null;
            }
            this(str, str2, str3, kind, i, str6, str7);
        }

        public final String getId() {
            return this.id;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final Kind getKind() {
            return this.kind;
        }

        public final int getScore() {
            return this.score;
        }

        public final String getPath() {
            return this.path;
        }

        public final String getCategoryId() {
            return this.categoryId;
        }
    }

    private SettingsSearchIndex() {
    }

    /* JADX INFO: compiled from: SettingsSearchIndex.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lai/nous/aurora/data/repository/SettingsSearchIndex$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "MoaEditor", "Assistant", "Category", "Field", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Kind {
        MoaEditor,
        Assistant,
        Category,
        Field;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    public final boolean isSecretPath(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        String p = path.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(p, "toLowerCase(...)");
        Iterable $this$any$iv = SECRET_FRAGMENTS;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains$default((CharSequence) p, (CharSequence) it, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ List search$default(SettingsSearchIndex settingsSearchIndex, String str, List list, Map map, List list2, String str2, Integer num, int i, Object obj) {
        String str3;
        Integer num2;
        if ((i & 16) == 0) {
            str3 = str2;
        } else {
            str3 = null;
        }
        if ((i & 32) == 0) {
            num2 = num;
        } else {
            num2 = null;
        }
        return settingsSearchIndex.search(str, list, map, list2, str3, num2);
    }

    /* JADX WARN: Code duplicated, block: B:205:0x04fb  */
    /* JADX WARN: Code duplicated, block: B:208:0x0500  */
    /* JADX WARN: Code duplicated, block: B:214:0x050e  */
    /* JADX WARN: Code duplicated, block: B:217:0x0512  */
    /* JADX WARN: Code duplicated, block: B:260:0x0169 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:42:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:46:0x00db  */
    /* JADX WARN: Code duplicated, block: B:72:0x016b A[LOOP:7: B:58:0x0138->B:72:0x016b, LOOP_END] */
    public final List<Hit> search(String query, List<HermesSettingField> fields, Map<String, String> values, List<String> categoryOrder, String moaDefaultPreset, Integer moaReferenceCount) {
        boolean moaAlias;
        String preset;
        String refs;
        boolean assistAlias;
        Iterable $this$any$iv;
        boolean moaAlias2;
        boolean z;
        Iterable $this$forEach$iv;
        int $i$f$forEach;
        String shown;
        String str;
        boolean z2;
        int i;
        List categories;
        Iterable $this$forEach$iv2;
        int $i$f$forEach2;
        Iterator it;
        boolean assistAlias2;
        Map<String, String> values2 = values;
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(values2, "values");
        Intrinsics.checkNotNullParameter(categoryOrder, "categoryOrder");
        String q = StringsKt.trim((CharSequence) query).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(q, "toLowerCase(...)");
        if (q.length() < 2) {
            return CollectionsKt.emptyList();
        }
        Iterable hits = (List) new ArrayList();
        Iterable $this$any$iv2 = MOA_ALIASES;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it2 = $this$any$iv2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    moaAlias = false;
                    break;
                }
                Object element$iv = it2.next();
                String it3 = (String) element$iv;
                if (Intrinsics.areEqual(it3, q) || StringsKt.startsWith$default(it3, q, false, 2, (Object) null) || StringsKt.startsWith$default(q, it3, false, 2, (Object) null)) {
                    moaAlias = true;
                    break;
                }
            }
        } else {
            moaAlias = false;
        }
        boolean moaStrong = moaAlias && q.length() >= 3;
        if (moaStrong || Intrinsics.areEqual(q, "agent")) {
            if (moaDefaultPreset == null) {
                preset = "default";
            } else {
                preset = !StringsKt.isBlank(moaDefaultPreset) ? moaDefaultPreset : null;
                if (preset == null) {
                    preset = "default";
                }
            }
            if (moaReferenceCount != null) {
                refs = moaReferenceCount.intValue() + " references";
                if (refs == null) {
                    refs = "presets";
                }
            } else {
                refs = "presets";
            }
            ((Collection) hits).add(new Hit("dest:moa", "Mixture of Agents", "Default for /moa · " + preset + " · " + refs, Kind.MoaEditor, moaStrong ? 10000 : 100, null, null, 96, null));
        }
        Iterable $this$any$iv3 = ASSIST_ALIASES;
        if (($this$any$iv3 instanceof Collection) && ((Collection) $this$any$iv3).isEmpty()) {
            assistAlias = false;
        } else {
            Iterator it4 = $this$any$iv3.iterator();
            while (true) {
                if (!it4.hasNext()) {
                    assistAlias = false;
                    break;
                }
                Object element$iv2 = it4.next();
                String it5 = (String) element$iv2;
                if (Intrinsics.areEqual(it5, q)) {
                    $this$any$iv = $this$any$iv3;
                    moaAlias2 = moaAlias;
                } else {
                    $this$any$iv = $this$any$iv3;
                    moaAlias2 = moaAlias;
                    if (!StringsKt.startsWith$default(it5, q, false, 2, (Object) null) && !StringsKt.startsWith$default(q, it5, false, 2, (Object) null)) {
                        z = false;
                    }
                    if (z) {
                        assistAlias = true;
                        break;
                    }
                    $this$any$iv3 = $this$any$iv;
                    moaAlias = moaAlias2;
                }
                z = true;
                if (z) {
                    assistAlias = true;
                    break;
                }
                $this$any$iv3 = $this$any$iv;
                moaAlias = moaAlias2;
            }
        }
        boolean assistStrong = assistAlias && q.length() >= 3;
        if (assistStrong) {
            ((Collection) hits).add(new Hit("dest:assistant", "Assistant", "Power-button Assist · orb · auto-listen · speak", Kind.Assistant, 9500, null, null, 96, null));
        }
        if (moaStrong && (Intrinsics.areEqual(q, "moa") || Intrinsics.areEqual(q, "/moa") || StringsKt.contains$default((CharSequence) q, (CharSequence) "mixture", false, 2, (Object) null) || Intrinsics.areEqual(q, "aggregator") || StringsKt.contains$default((CharSequence) q, (CharSequence) "reference", false, 2, (Object) null))) {
            final Comparator comparator = new Comparator() { // from class: ai.nous.aurora.data.repository.SettingsSearchIndex$search$$inlined$compareByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    SettingsSearchIndex.Hit it6 = (SettingsSearchIndex.Hit) t2;
                    Integer numValueOf = Integer.valueOf(it6.getScore());
                    SettingsSearchIndex.Hit it7 = (SettingsSearchIndex.Hit) t;
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(it7.getScore()));
                }
            };
            Iterable $this$distinctBy$iv = CollectionsKt.sortedWith(hits, new Comparator() { // from class: ai.nous.aurora.data.repository.SettingsSearchIndex$search$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator.compare(t, t2);
                    if (previousCompare != 0) {
                        return previousCompare;
                    }
                    SettingsSearchIndex.Hit it6 = (SettingsSearchIndex.Hit) t;
                    String lowerCase = it6.getLabel().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    SettingsSearchIndex.Hit it7 = (SettingsSearchIndex.Hit) t2;
                    String lowerCase2 = it7.getLabel().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                    return ComparisonsKt.compareValues(lowerCase, lowerCase2);
                }
            });
            HashSet set$iv = new HashSet();
            ArrayList list$iv = new ArrayList();
            for (Object e$iv : $this$distinctBy$iv) {
                if (set$iv.add(((Hit) e$iv).getId())) {
                    list$iv.add(e$iv);
                }
            }
            ArrayList $this$distinctBy$iv2 = list$iv;
            return CollectionsKt.take($this$distinctBy$iv2, 40);
        }
        if (assistStrong) {
            Iterable $this$any$iv4 = hits;
            if (($this$any$iv4 instanceof Collection) && ((Collection) $this$any$iv4).isEmpty()) {
                assistAlias2 = false;
            } else {
                Iterator it6 = $this$any$iv4.iterator();
                while (true) {
                    if (!it6.hasNext()) {
                        assistAlias2 = false;
                        break;
                    }
                    Object element$iv3 = it6.next();
                    boolean assistAlias3 = assistAlias;
                    boolean assistStrong2 = assistStrong;
                    if (((Hit) element$iv3).getKind() == Kind.Assistant) {
                        assistAlias2 = true;
                        break;
                    }
                    assistAlias = assistAlias3;
                    assistStrong = assistStrong2;
                }
            }
            if (assistAlias2 && (Intrinsics.areEqual(q, "assist") || Intrinsics.areEqual(q, "assistant") || Intrinsics.areEqual(q, "voice") || Intrinsics.areEqual(q, "dictation") || Intrinsics.areEqual(q, "microphone") || Intrinsics.areEqual(q, "mic") || Intrinsics.areEqual(q, "orb") || StringsKt.contains$default((CharSequence) q, (CharSequence) "power", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "listen", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) q, (CharSequence) "speak", false, 2, (Object) null))) {
                Iterable $this$filter$iv = hits;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    Iterable $this$filter$iv2 = $this$filter$iv;
                    if (((Hit) element$iv$iv).getKind() == Kind.Assistant) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                    $this$filter$iv = $this$filter$iv2;
                }
                final Comparator comparator2 = new Comparator() { // from class: ai.nous.aurora.data.repository.SettingsSearchIndex$search$$inlined$compareByDescending$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        SettingsSearchIndex.Hit it7 = (SettingsSearchIndex.Hit) t2;
                        Integer numValueOf = Integer.valueOf(it7.getScore());
                        SettingsSearchIndex.Hit it8 = (SettingsSearchIndex.Hit) t;
                        return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(it8.getScore()));
                    }
                };
                return CollectionsKt.take(CollectionsKt.sortedWith((List) destination$iv$iv, new Comparator() { // from class: ai.nous.aurora.data.repository.SettingsSearchIndex$search$$inlined$thenBy$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        int previousCompare = comparator2.compare(t, t2);
                        if (previousCompare != 0) {
                            return previousCompare;
                        }
                        SettingsSearchIndex.Hit it7 = (SettingsSearchIndex.Hit) t;
                        String lowerCase = it7.getLabel().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        SettingsSearchIndex.Hit it8 = (SettingsSearchIndex.Hit) t2;
                        String lowerCase2 = it8.getLabel().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                        return ComparisonsKt.compareValues(lowerCase, lowerCase2);
                    }
                }), 5);
            }
        }
        List<String> list = categoryOrder;
        List<HermesSettingField> $this$map$iv = fields;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv2.add(((HermesSettingField) item$iv$iv).getCategory());
        }
        List categories2 = CollectionsKt.distinct(CollectionsKt.plus((Collection) list, destination$iv$iv2));
        List $this$forEach$iv3 = categories2;
        int $i$f$forEach3 = 0;
        Iterator it7 = $this$forEach$iv3.iterator();
        while (it7.hasNext()) {
            Object element$iv4 = it7.next();
            String id = (String) element$iv4;
            if (moaStrong && StringsKt.equals(id, "moa", true)) {
                categories = categories2;
                $this$forEach$iv2 = $this$forEach$iv3;
                $i$f$forEach2 = $i$f$forEach3;
                it = it7;
            } else {
                String label = INSTANCE.categoryLabel(id);
                categories = categories2;
                $this$forEach$iv2 = $this$forEach$iv3;
                String hay = (id + " " + label).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(hay, "toLowerCase(...)");
                $i$f$forEach2 = $i$f$forEach3;
                it = it7;
                if (StringsKt.contains$default((CharSequence) hay, (CharSequence) q, false, 2, (Object) null)) {
                    ArrayList arrayList = (Collection) hits;
                    String str2 = "cat:" + id;
                    Kind kind = Kind.Category;
                    String lowerCase = id.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    arrayList.add(new Hit(str2, label, "Configuration category", kind, Intrinsics.areEqual(lowerCase, q) ? 500 : 200, null, id, 32, null));
                }
            }
            categories2 = categories;
            $this$forEach$iv3 = $this$forEach$iv2;
            $i$f$forEach3 = $i$f$forEach2;
            it7 = it;
        }
        List<HermesSettingField> $this$forEach$iv4 = fields;
        int $i$f$forEach4 = 0;
        for (Object element$iv5 : $this$forEach$iv4) {
            HermesSettingField field = (HermesSettingField) element$iv5;
            if (moaStrong && StringsKt.equals(field.getCategory(), "moa", true)) {
                $this$forEach$iv = $this$forEach$iv4;
                $i$f$forEach = $i$f$forEach4;
            } else {
                StringBuilder $this$search_u24lambda_u2415_u24lambda_u2414 = new StringBuilder();
                $this$forEach$iv = $this$forEach$iv4;
                $this$search_u24lambda_u2415_u24lambda_u2414.append(field.getPath());
                $this$search_u24lambda_u2415_u24lambda_u2414.append(' ');
                $this$search_u24lambda_u2415_u24lambda_u2414.append(field.getDescription());
                $this$search_u24lambda_u2415_u24lambda_u2414.append(' ');
                $this$search_u24lambda_u2415_u24lambda_u2414.append(field.getCategory());
                String string = $this$search_u24lambda_u2415_u24lambda_u2414.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                String hay2 = string.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(hay2, "toLowerCase(...)");
                $i$f$forEach = $i$f$forEach4;
                if (StringsKt.contains$default((CharSequence) hay2, (CharSequence) q, false, 2, (Object) null)) {
                    boolean secret = field.isSecret() || INSTANCE.isSecretPath(field.getPath());
                    String raw = values2.get(field.getPath());
                    if (secret) {
                        String str3 = raw;
                        if (str3 == null || StringsKt.isBlank(str3)) {
                            shown = "Unset";
                            if (!secret) {
                                str = raw;
                                if (str != null) {
                                    z2 = true;
                                } else {
                                    z2 = true;
                                }
                                if (!z2) {
                                    shown = StringsKt.take(raw, 80);
                                }
                            }
                        } else {
                            shown = "Set";
                        }
                    } else {
                        shown = "Unset";
                        if (!secret) {
                            str = raw;
                            if (str != null || StringsKt.isBlank(str)) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2) {
                                shown = StringsKt.take(raw, 80);
                            }
                        }
                    }
                    ArrayList arrayList2 = (Collection) hits;
                    String str4 = "field:" + field.getPath();
                    String strHumanize = INSTANCE.humanize(field.getPath(), field.getDescription());
                    String str5 = field.getPath() + " · " + shown;
                    Kind kind2 = Kind.Field;
                    String lowerCase2 = field.getPath().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                    if (Intrinsics.areEqual(lowerCase2, q)) {
                        i = 400;
                    } else {
                        String lowerCase3 = field.getPath().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                        i = StringsKt.startsWith$default(lowerCase3, q, false, 2, (Object) null) ? 300 : 150;
                    }
                    arrayList2.add(new Hit(str4, strHumanize, str5, kind2, i, field.getPath(), field.getCategory()));
                }
            }
            values2 = values;
            $i$f$forEach4 = $i$f$forEach;
            $this$forEach$iv4 = $this$forEach$iv;
        }
        final Comparator comparator3 = new Comparator() { // from class: ai.nous.aurora.data.repository.SettingsSearchIndex$search$$inlined$compareByDescending$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                SettingsSearchIndex.Hit it8 = (SettingsSearchIndex.Hit) t2;
                Integer numValueOf = Integer.valueOf(it8.getScore());
                SettingsSearchIndex.Hit it9 = (SettingsSearchIndex.Hit) t;
                return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(it9.getScore()));
            }
        };
        Iterable $this$distinctBy$iv3 = CollectionsKt.sortedWith(hits, new Comparator() { // from class: ai.nous.aurora.data.repository.SettingsSearchIndex$search$$inlined$thenBy$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator3.compare(t, t2);
                if (previousCompare != 0) {
                    return previousCompare;
                }
                SettingsSearchIndex.Hit it8 = (SettingsSearchIndex.Hit) t;
                String lowerCase4 = it8.getLabel().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                SettingsSearchIndex.Hit it9 = (SettingsSearchIndex.Hit) t2;
                String lowerCase5 = it9.getLabel().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase4, lowerCase5);
            }
        });
        HashSet set$iv2 = new HashSet();
        ArrayList list$iv2 = new ArrayList();
        for (Object e$iv2 : $this$distinctBy$iv3) {
            if (set$iv2.add(((Hit) e$iv2).getId())) {
                list$iv2.add(e$iv2);
            }
        }
        ArrayList $this$distinctBy$iv4 = list$iv2;
        return CollectionsKt.take($this$distinctBy$iv4, 40);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String categoryLabel(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        String lowerCase = id.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case 107465:
                if (lowerCase.equals("lsp")) {
                    return "Language Servers";
                }
                break;
            case 108287:
                if (lowerCase.equals("moa")) {
                    return "Mixture of Agents";
                }
                break;
            case 114227:
                if (lowerCase.equals("stt")) {
                    return "Speech to Text";
                }
                break;
            case 115187:
                if (lowerCase.equals("tts")) {
                    return "Text to Speech";
                }
                break;
            case 485976911:
                if (lowerCase.equals("x_search")) {
                    return "X Search";
                }
                break;
        }
        String strReplace$default = StringsKt.replace$default(id, '_', ' ', false, 4, (Object) null);
        if (!(strReplace$default.length() > 0)) {
            return strReplace$default;
        }
        StringBuilder sb = new StringBuilder();
        char it = strReplace$default.charAt(0);
        StringBuilder sbAppend = sb.append((Object) CharsKt.titlecase(it));
        String strSubstring = strReplace$default.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }

    private final String humanize(String path, String description) {
        if (!StringsKt.isBlank(description) && !StringsKt.contains$default((CharSequence) description, '.', false, 2, (Object) null) && description.length() < 60) {
            return description;
        }
        String strReplace$default = StringsKt.replace$default(StringsKt.substringAfterLast$default(path, '.', (String) null, 2, (Object) null), '_', ' ', false, 4, (Object) null);
        if (!(strReplace$default.length() > 0)) {
            return strReplace$default;
        }
        StringBuilder sb = new StringBuilder();
        char it = strReplace$default.charAt(0);
        StringBuilder sbAppend = sb.append((Object) CharsKt.titlecase(it));
        String strSubstring = strReplace$default.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return sbAppend.append(strSubstring).toString();
    }
}
