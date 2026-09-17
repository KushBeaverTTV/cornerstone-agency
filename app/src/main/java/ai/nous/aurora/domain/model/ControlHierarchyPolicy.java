package ai.nous.aurora.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ControlHierarchyPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nJ \u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\nJ\u001e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nJ\u0016\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0015¨\u0006\u001e"}, d2 = {"Lai/nous/aurora/domain/model/ControlHierarchyPolicy;", "", "<init>", "()V", "homeDestinations", "", "Lai/nous/aurora/domain/model/ControlHierarchyPolicy$Destination;", "dailyDestinations", "proDestinations", "proSectionLabel", "", "proGateSubtitle", "dailySectionLabel", "friendlyModelLine", "provider", "model", "fallbackLabel", "moaHomeSummary", "defaultPreset", "activePreset", "referenceCount", "", "connectionSubtitle", "online", "", "gateway", "accountSubtitle", "sessionCount", "Tier", "Destination", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ControlHierarchyPolicy {
    public static final int $stable = 0;
    public static final ControlHierarchyPolicy INSTANCE = new ControlHierarchyPolicy();

    private ControlHierarchyPolicy() {
    }

    /* JADX INFO: compiled from: ControlHierarchyPolicy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/domain/model/ControlHierarchyPolicy$Tier;", "", "<init>", "(Ljava/lang/String;I)V", "Daily", "Pro", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Tier {
        Daily,
        Pro;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Tier> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: ControlHierarchyPolicy.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lai/nous/aurora/domain/model/ControlHierarchyPolicy$Destination;", "", "id", "", "tier", "Lai/nous/aurora/domain/model/ControlHierarchyPolicy$Tier;", "label", "summary", "<init>", "(Ljava/lang/String;Lai/nous/aurora/domain/model/ControlHierarchyPolicy$Tier;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTier", "()Lai/nous/aurora/domain/model/ControlHierarchyPolicy$Tier;", "getLabel", "getSummary", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Destination {
        public static final int $stable = 0;
        private final String id;
        private final String label;
        private final String summary;
        private final Tier tier;

        public static /* synthetic */ Destination copy$default(Destination destination, String str, Tier tier, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = destination.id;
            }
            if ((i & 2) != 0) {
                tier = destination.tier;
            }
            if ((i & 4) != 0) {
                str2 = destination.label;
            }
            if ((i & 8) != 0) {
                str3 = destination.summary;
            }
            return destination.copy(str, tier, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Tier getTier() {
            return this.tier;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSummary() {
            return this.summary;
        }

        public final Destination copy(String id, Tier tier, String label, String summary) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(tier, "tier");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(summary, "summary");
            return new Destination(id, tier, label, summary);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Destination)) {
                return false;
            }
            Destination destination = (Destination) other;
            return Intrinsics.areEqual(this.id, destination.id) && this.tier == destination.tier && Intrinsics.areEqual(this.label, destination.label) && Intrinsics.areEqual(this.summary, destination.summary);
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.tier.hashCode()) * 31) + this.label.hashCode()) * 31) + this.summary.hashCode();
        }

        public String toString() {
            return "Destination(id=" + this.id + ", tier=" + this.tier + ", label=" + this.label + ", summary=" + this.summary + ")";
        }

        public Destination(String id, Tier tier, String label, String summary) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(tier, "tier");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(summary, "summary");
            this.id = id;
            this.tier = tier;
            this.label = label;
            this.summary = summary;
        }






    }

    public final List<Destination> homeDestinations() {
        return CollectionsKt.listOf(new Destination[]{new Destination("model", Tier.Daily, "Model", "Current friendly model"), new Destination("assistant", Tier.Daily, "Assistant", "Talk/Assistant access"), new Destination("account", Tier.Daily, "Account", "Account and sessions"), new Destination("moa", Tier.Pro, "Mixture of Agents", "Ensemble of independent models"), new Destination("advanced", Tier.Pro, "Advanced Config", "Complete desktop-parity options")});
    }

    public final List<Destination> dailyDestinations() {
        Iterable $this$filter$iv = homeDestinations();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Destination it = (Destination) element$iv$iv;
            if (it.getTier() == Tier.Daily) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<Destination> proDestinations() {
        Iterable $this$filter$iv = homeDestinations();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            Destination it = (Destination) element$iv$iv;
            if (it.getTier() == Tier.Pro) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final String proSectionLabel() {
        return "Power tools";
    }

    public final String proGateSubtitle() {
        return "Advanced features for Pro setups";
    }

    public final String dailySectionLabel() {
        return "Daily";
    }

    public static /* synthetic */ String friendlyModelLine$default(ControlHierarchyPolicy controlHierarchyPolicy, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "";
        }
        return controlHierarchyPolicy.friendlyModelLine(str, str2, str3);
    }

    public final String friendlyModelLine(String provider, String model, String fallbackLabel) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(fallbackLabel, "fallbackLabel");
        String p = ComposerChipPolicy.INSTANCE.friendlyProvider(provider);
        String m = ComposerChipPolicy.INSTANCE.friendlyModel(model);
        if (StringsKt.isBlank(model)) {
            m = null;
        }
        String str = m;
        if (!(str == null || StringsKt.isBlank(str)) && !StringsKt.isBlank(p)) {
            return m + " · " + p;
        }
        String str2 = m;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            return m;
        }
        if (StringsKt.isBlank(p)) {
            return !StringsKt.isBlank(fallbackLabel) ? fallbackLabel : "Choose model";
        }
        return p;
    }

    public final String moaHomeSummary(String defaultPreset, String activePreset, int referenceCount) {
        Intrinsics.checkNotNullParameter(defaultPreset, "defaultPreset");
        Intrinsics.checkNotNullParameter(activePreset, "activePreset");
        String string = StringsKt.trim((CharSequence) defaultPreset).toString();
        if (StringsKt.isBlank(string)) {
            string = "default";
        }
        String preset = string;
        String active = StringsKt.trim((CharSequence) activePreset).toString();
        String refs = null;
        if (!((StringsKt.isBlank(active) || Intrinsics.areEqual(active, preset)) ? false : true)) {
            active = null;
        }
        if (referenceCount > 0) {
            refs = referenceCount + " reference" + (referenceCount == 1 ? "" : "s");
        }
        StringBuilder $this$moaHomeSummary_u24lambda_u246 = new StringBuilder();
        $this$moaHomeSummary_u24lambda_u246.append(preset);
        if (active != null) {
            $this$moaHomeSummary_u24lambda_u246.append(" · active " + active);
        }
        if (refs != null) {
            String it = refs;
            $this$moaHomeSummary_u24lambda_u246.append(" · " + it);
        }
        $this$moaHomeSummary_u24lambda_u246.append(" · ensemble of independent models");
        String string2 = $this$moaHomeSummary_u24lambda_u246.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        return string2;
    }

    public final String connectionSubtitle(boolean online, String gateway) {
        Intrinsics.checkNotNullParameter(gateway, "gateway");
        if (online) {
            return "Connected · live Hermes configuration";
        }
        return !StringsKt.isBlank(gateway) ? "Offline · last-good snapshot" : "No host";
    }

    public final String accountSubtitle(String gateway, int sessionCount) {
        StringBuilder sbAppend;
        String str;
        Intrinsics.checkNotNullParameter(gateway, "gateway");
        String sessions = sessionCount + " session" + (sessionCount == 1 ? "" : "s");
        if (StringsKt.isBlank(gateway)) {
            sbAppend = new StringBuilder().append(sessions);
            str = " · add connection";
        } else {
            sbAppend = new StringBuilder().append(sessions);
            str = " · connection details";
        }
        return sbAppend.append(str).toString();
    }
}
