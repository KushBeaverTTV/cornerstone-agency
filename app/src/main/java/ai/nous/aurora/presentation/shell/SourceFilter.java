package ai.nous.aurora.presentation.shell;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InnerShell.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lai/nous/aurora/presentation/shell/SourceFilter;", "", "key", "", "label", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "getLabel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class SourceFilter {
    private final String key;
    private final String label;

    public static /* synthetic */ SourceFilter copy$default(SourceFilter sourceFilter, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sourceFilter.key;
        }
        if ((i & 2) != 0) {
            str2 = sourceFilter.label;
        }
        return sourceFilter.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    public final SourceFilter copy(String key, String label) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(label, "label");
        return new SourceFilter(key, label);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceFilter)) {
            return false;
        }
        SourceFilter sourceFilter = (SourceFilter) other;
        return Intrinsics.areEqual(this.key, sourceFilter.key) && Intrinsics.areEqual(this.label, sourceFilter.label);
    }

    public int hashCode() {
        return (this.key.hashCode() * 31) + this.label.hashCode();
    }

    public String toString() {
        return "SourceFilter(key=" + this.key + ", label=" + this.label + ")";
    }

    public SourceFilter(String key, String label) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(label, "label");
        this.key = key;
        this.label = label;
    }


}
