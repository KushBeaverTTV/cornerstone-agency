package ai.nous.aurora.presentation.assist;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AssistOrb.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0010\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0012\u0010\nJ\u0010\u0010\u0013\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0014\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0016\u0010\nJ8\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\n¨\u0006!"}, d2 = {"Lai/nous/aurora/presentation/assist/AssistOrbPalette;", "", "core", "Landroidx/compose/ui/graphics/Color;", "glow", "accent", "deep", "<init>", "(JJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCore-0d7_KjU", "()J", "J", "getGlow-0d7_KjU", "getAccent-0d7_KjU", "getDeep-0d7_KjU", "component1", "component1-0d7_KjU", "component2", "component2-0d7_KjU", "component3", "component3-0d7_KjU", "component4", "component4-0d7_KjU", "copy", "copy-jRlVdoo", "(JJJJ)Lai/nous/aurora/presentation/assist/AssistOrbPalette;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AssistOrbPalette {
    public static final int $stable = 0;
    private final long accent;
    private final long core;
    private final long deep;
    private final long glow;

    public /* synthetic */ AssistOrbPalette(long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4);
    }

    /* JADX INFO: renamed from: component1-0d7_KjU, reason: not valid java name and from getter */
    public final long getCore() {
        return this.core;
    }

    /* JADX INFO: renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
    public final long getGlow() {
        return this.glow;
    }

    /* JADX INFO: renamed from: component3-0d7_KjU, reason: not valid java name and from getter */
    public final long getAccent() {
        return this.accent;
    }

    /* JADX INFO: renamed from: component4-0d7_KjU, reason: not valid java name and from getter */
    public final long getDeep() {
        return this.deep;
    }

    /* JADX INFO: renamed from: copy-jRlVdoo, reason: not valid java name */
    public final AssistOrbPalette m96copyjRlVdoo(long core, long glow, long accent, long deep) {
        return new AssistOrbPalette(core, glow, accent, deep, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssistOrbPalette)) {
            return false;
        }
        AssistOrbPalette assistOrbPalette = (AssistOrbPalette) other;
        return ai.nous.aurora.compat.Compat.m4535equalsimpl0(this.core, assistOrbPalette.core) && ai.nous.aurora.compat.Compat.m4535equalsimpl0(this.glow, assistOrbPalette.glow) && ai.nous.aurora.compat.Compat.m4535equalsimpl0(this.accent, assistOrbPalette.accent) && ai.nous.aurora.compat.Compat.m4535equalsimpl0(this.deep, assistOrbPalette.deep);
    }

    public int hashCode() {
        return (((((ai.nous.aurora.compat.Compat.m4541hashCodeimpl(this.core) * 31) + ai.nous.aurora.compat.Compat.m4541hashCodeimpl(this.glow)) * 31) + ai.nous.aurora.compat.Compat.m4541hashCodeimpl(this.accent)) * 31) + ai.nous.aurora.compat.Compat.m4541hashCodeimpl(this.deep);
    }

    public String toString() {
        return "AssistOrbPalette(core=" + ai.nous.aurora.compat.Compat.m4542toStringimpl(this.core) + ", glow=" + ai.nous.aurora.compat.Compat.m4542toStringimpl(this.glow) + ", accent=" + ai.nous.aurora.compat.Compat.m4542toStringimpl(this.accent) + ", deep=" + ai.nous.aurora.compat.Compat.m4542toStringimpl(this.deep) + ")";
    }

    private AssistOrbPalette(long core, long glow, long accent, long deep) {
        this.core = core;
        this.glow = glow;
        this.accent = accent;
        this.deep = deep;
    }

    /* JADX INFO: renamed from: getCore-0d7_KjU, reason: not valid java name */
    public final long m98getCore0d7_KjU() {
        return this.core;
    }

    /* JADX INFO: renamed from: getGlow-0d7_KjU, reason: not valid java name */
    public final long m100getGlow0d7_KjU() {
        return this.glow;
    }

    /* JADX INFO: renamed from: getAccent-0d7_KjU, reason: not valid java name */
    public final long m97getAccent0d7_KjU() {
        return this.accent;
    }

    /* JADX INFO: renamed from: getDeep-0d7_KjU, reason: not valid java name */
    public final long m99getDeep0d7_KjU() {
        return this.deep;
    }
}
