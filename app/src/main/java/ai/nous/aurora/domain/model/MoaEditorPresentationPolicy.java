package ai.nous.aurora.domain.model;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoaEditorPresentationPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001+B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u0015\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0010J\u001e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0005J\u0015\u0010\u001d\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"J&\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fJ&\u0010(\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lai/nous/aurora/domain/model/MoaEditorPresentationPolicy;", "", "<init>", "()V", "MIN_ACTION_HEIGHT_DP", "", "sectionOrder", "", "Lai/nous/aurora/domain/model/MoaEditorPresentationPolicy$Section;", "progressiveSections", "", "isProgressiveSection", "", "section", "defaultExpanded", "sectionLabel", "", "sectionSubtitle", "fieldLabel", "path", "fieldSubtitle", "fanoutSubtitle", "fanout", "presetSubtitle", "defaultPreset", "activePreset", "selectedName", "maxTokensSubtitle", "tokens", "referenceMaxTokensSubtitle", "(Ljava/lang/Integer;)Ljava/lang/String;", "temperatureSubtitle", "temperature", "", "(Ljava/lang/Double;)Ljava/lang/String;", "saveButtonLabel", "dirty", "saving", "lastSaved", "canSave", "saveButtonEnabled", "bridgeOnline", "remoteAvailable", "Section", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MoaEditorPresentationPolicy {
    public static final int MIN_ACTION_HEIGHT_DP = 48;
    public static final MoaEditorPresentationPolicy INSTANCE = new MoaEditorPresentationPolicy();
    private static final Set<Section> progressiveSections = SetsKt.setOf(new Section[]{Section.RunBehavior, Section.Diagnostics});
    public static final int $stable = 8;

    /* JADX INFO: compiled from: MoaEditorPresentationPolicy.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Section.values().length];
            try {
                iArr[Section.Purpose.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Section.Preset.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Section.References.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Section.Aggregator.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Section.RunBehavior.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Section.Diagnostics.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private MoaEditorPresentationPolicy() {
    }

    /* JADX INFO: compiled from: MoaEditorPresentationPolicy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lai/nous/aurora/domain/model/MoaEditorPresentationPolicy$Section;", "", "<init>", "(Ljava/lang/String;I)V", "Purpose", "Preset", "References", "Aggregator", "RunBehavior", "Diagnostics", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Section {
        Purpose,
        Preset,
        References,
        Aggregator,
        RunBehavior,
        Diagnostics;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Section> getEntries() {
            return $ENTRIES;
        }
    }

    public final List<Section> sectionOrder() {
        return CollectionsKt.toList(Section.getEntries());
    }

    public final boolean isProgressiveSection(Section section) {
        Intrinsics.checkNotNullParameter(section, "section");
        return progressiveSections.contains(section);
    }

    public final boolean defaultExpanded(Section section) {
        Intrinsics.checkNotNullParameter(section, "section");
        switch (WhenMappings.$EnumSwitchMapping$0[section.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
            case 5:
            case 6:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String sectionLabel(Section section) {
        Intrinsics.checkNotNullParameter(section, "section");
        switch (WhenMappings.$EnumSwitchMapping$0[section.ordinal()]) {
            case 1:
                return "What MoA does";
            case 2:
                return "Preset";
            case 3:
                return "Reference agents";
            case 4:
                return "Aggregator";
            case 5:
                return "Run behavior";
            case 6:
                return "Diagnostics";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String sectionSubtitle(Section section) {
        Intrinsics.checkNotNullParameter(section, "section");
        switch (WhenMappings.$EnumSwitchMapping$0[section.ordinal()]) {
            case 1:
                return "Combine independent agents, then synthesize one answer";
            case 2:
                return "Saved configuration you can switch between";
            case 3:
                return "Agents that vote before aggregation";
            case 4:
                return "Model that turns votes into the final reply";
            case 5:
                return "When to run, fan-out, and token limits";
            case 6:
                return "Traces and saved runs";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String fieldLabel(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        switch (path.hashCode()) {
            case -1281824933:
                if (path.equals("fanout")) {
                    return "Fan-out";
                }
                break;
            case 392657406:
                if (path.equals("aggregator_temperature")) {
                    return "Aggregator temperature";
                }
                break;
            case 724597395:
                if (path.equals("trace_dir")) {
                    return "Trace directory";
                }
                break;
            case 868760784:
                if (path.equals("save_traces")) {
                    return "Save traces";
                }
                break;
            case 978447945:
                if (path.equals("reference_max_tokens")) {
                    return "Reference max tokens";
                }
                break;
            case 1349567701:
                if (path.equals("max_tokens")) {
                    return "Max output tokens";
                }
                break;
            case 1701890688:
                if (path.equals("reference_temperature")) {
                    return "Reference temperature";
                }
                break;
        }
        String strReplace$default = ai.nous.aurora.compat.Compat.d_StringsKt_replace_6(path, "_", " ", false, 4, (Object) null);
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:33:0x0061 A[ORIG_RETURN, RETURN] */
    public final String fieldSubtitle(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        switch (path) {
            case "fanout":
                return "When to ask the reference agents";
            case "aggregator_temperature":
                return "Creativity of the final answer";
            case "trace_dir":
                return "Where run traces are stored";
            case "save_traces":
                return "Keep a record of each MoA run";
            case "reference_max_tokens":
                return "Per-reference token ceiling";
            case "max_tokens":
                return "Longest reply the aggregator may return";
            case "reference_temperature":
                return "Creativity of reference agents";
            default:
                return "";
        }
    }

    public final String fanoutSubtitle(String fanout) {
        return Intrinsics.areEqual(fanout, "user_turn") ? "Once per user turn" : "Every tool iteration";
    }

    public final String presetSubtitle(String defaultPreset, String activePreset, String selectedName) {
        Intrinsics.checkNotNullParameter(defaultPreset, "defaultPreset");
        Intrinsics.checkNotNullParameter(activePreset, "activePreset");
        Intrinsics.checkNotNullParameter(selectedName, "selectedName");
        String string = StringsKt.trim((CharSequence) defaultPreset).toString();
        if (StringsKt.isBlank(string)) {
            string = "default";
        }
        String str = string;
        String string2 = StringsKt.trim((CharSequence) activePreset).toString();
        if (StringsKt.isBlank(string2)) {
            string2 = str;
        }
        String active = string2;
        StringBuilder $this$presetSubtitle_u24lambda_u243 = new StringBuilder();
        $this$presetSubtitle_u24lambda_u243.append("Default: " + str);
        if (!Intrinsics.areEqual(active, str)) {
            $this$presetSubtitle_u24lambda_u243.append(" · Active: " + active);
        }
        if (!StringsKt.isBlank(selectedName) && !Intrinsics.areEqual(selectedName, active)) {
            $this$presetSubtitle_u24lambda_u243.append(" · Selected: " + selectedName);
        }
        String string3 = $this$presetSubtitle_u24lambda_u243.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        return string3;
    }

    public final String maxTokensSubtitle(int tokens) {
        return tokens + " tokens";
    }

    public final String referenceMaxTokensSubtitle(Integer tokens) {
        if (tokens != null) {
            int it = tokens.intValue();
            String str = it + " tokens";
            if (str != null) {
                return str;
            }
        }
        return "Follow max output";
    }

    public final String temperatureSubtitle(Double temperature) {
        String string;
        return (temperature == null || (string = temperature.toString()) == null) ? "Default" : string;
    }

    public final String saveButtonLabel(boolean dirty, boolean saving, boolean lastSaved, boolean canSave) {
        if (saving) {
            return "Saving…";
        }
        if (dirty && canSave) {
            return "Save changes";
        }
        return (!lastSaved || dirty) ? "No changes" : "Saved";
    }

    public final boolean saveButtonEnabled(boolean dirty, boolean saving, boolean bridgeOnline, boolean remoteAvailable) {
        return bridgeOnline && remoteAvailable && dirty && !saving;
    }
}
