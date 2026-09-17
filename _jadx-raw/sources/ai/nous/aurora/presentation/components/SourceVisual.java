package ai.nous.aurora.presentation.components;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SourceBadge.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0013\u0010\rJ\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J.\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lai/nous/aurora/presentation/components/SourceVisual;", "", "icon", "", "tintBg", "Landroidx/compose/ui/graphics/Color;", "label", "", "<init>", "(IJLjava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIcon", "()I", "getTintBg-0d7_KjU", "()J", "J", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component2-0d7_KjU", "component3", "copy", "copy-bw27NRU", "(IJLjava/lang/String;)Lai/nous/aurora/presentation/components/SourceVisual;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class SourceVisual {
    public static final int $stable = 0;
    private final int icon;
    private final String label;
    private final long tintBg;

    public /* synthetic */ SourceVisual(int i, long j, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, j, str);
    }

    /* JADX INFO: renamed from: copy-bw27NRU$default, reason: not valid java name */
    public static /* synthetic */ SourceVisual m116copybw27NRU$default(SourceVisual sourceVisual, int i, long j, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = sourceVisual.icon;
        }
        if ((i2 & 2) != 0) {
            j = sourceVisual.tintBg;
        }
        if ((i2 & 4) != 0) {
            str = sourceVisual.label;
        }
        return sourceVisual.m118copybw27NRU(i, j, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
    public final long getTintBg() {
        return this.tintBg;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: copy-bw27NRU, reason: not valid java name */
    public final SourceVisual m118copybw27NRU(int icon, long tintBg, String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new SourceVisual(icon, tintBg, label, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceVisual)) {
            return false;
        }
        SourceVisual sourceVisual = (SourceVisual) other;
        return this.icon == sourceVisual.icon && Color.m4535equalsimpl0(this.tintBg, sourceVisual.tintBg) && Intrinsics.areEqual(this.label, sourceVisual.label);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.icon) * 31) + Color.m4541hashCodeimpl(this.tintBg)) * 31) + this.label.hashCode();
    }

    public String toString() {
        return "SourceVisual(icon=" + this.icon + ", tintBg=" + Color.m4542toStringimpl(this.tintBg) + ", label=" + this.label + ")";
    }

    private SourceVisual(int icon, long tintBg, String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.icon = icon;
        this.tintBg = tintBg;
        this.label = label;
    }

    public final int getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: getTintBg-0d7_KjU, reason: not valid java name */
    public final long m119getTintBg0d7_KjU() {
        return this.tintBg;
    }

    public final String getLabel() {
        return this.label;
    }
}
