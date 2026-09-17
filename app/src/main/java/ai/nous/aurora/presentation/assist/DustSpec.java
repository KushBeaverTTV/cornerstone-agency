package ai.nous.aurora.presentation.assist;

import kotlin.Metadata;

/* JADX INFO: compiled from: AssistOrb.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006 "}, d2 = {"Lai/nous/aurora/presentation/assist/DustSpec;", "", "angle0", "", "radiusFrac", "sizeFrac", "speed", "phase", "alpha", "<init>", "(FFFFFF)V", "getAngle0", "()F", "getRadiusFrac", "getSizeFrac", "getSpeed", "getPhase", "getAlpha", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class DustSpec {
    private final float alpha;
    private final float angle0;
    private final float phase;
    private final float radiusFrac;
    private final float sizeFrac;
    private final float speed;

    public static /* synthetic */ DustSpec copy$default(DustSpec dustSpec, float f, float f2, float f3, float f4, float f5, float f6, int i, Object obj) {
        if ((i & 1) != 0) {
            f = dustSpec.angle0;
        }
        if ((i & 2) != 0) {
            f2 = dustSpec.radiusFrac;
        }
        float f7 = f2;
        if ((i & 4) != 0) {
            f3 = dustSpec.sizeFrac;
        }
        float f8 = f3;
        if ((i & 8) != 0) {
            f4 = dustSpec.speed;
        }
        float f9 = f4;
        if ((i & 16) != 0) {
            f5 = dustSpec.phase;
        }
        float f10 = f5;
        if ((i & 32) != 0) {
            f6 = dustSpec.alpha;
        }
        return dustSpec.copy(f, f7, f8, f9, f10, f6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getAngle0() {
        return this.angle0;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getRadiusFrac() {
        return this.radiusFrac;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getSizeFrac() {
        return this.sizeFrac;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getSpeed() {
        return this.speed;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getPhase() {
        return this.phase;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getAlpha() {
        return this.alpha;
    }

    public final DustSpec copy(float angle0, float radiusFrac, float sizeFrac, float speed, float phase, float alpha) {
        return new DustSpec(angle0, radiusFrac, sizeFrac, speed, phase, alpha);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DustSpec)) {
            return false;
        }
        DustSpec dustSpec = (DustSpec) other;
        return Float.compare(this.angle0, dustSpec.angle0) == 0 && Float.compare(this.radiusFrac, dustSpec.radiusFrac) == 0 && Float.compare(this.sizeFrac, dustSpec.sizeFrac) == 0 && Float.compare(this.speed, dustSpec.speed) == 0 && Float.compare(this.phase, dustSpec.phase) == 0 && Float.compare(this.alpha, dustSpec.alpha) == 0;
    }

    public int hashCode() {
        return (((((((((Float.hashCode(this.angle0) * 31) + Float.hashCode(this.radiusFrac)) * 31) + Float.hashCode(this.sizeFrac)) * 31) + Float.hashCode(this.speed)) * 31) + Float.hashCode(this.phase)) * 31) + Float.hashCode(this.alpha);
    }

    public String toString() {
        return "DustSpec(angle0=" + this.angle0 + ", radiusFrac=" + this.radiusFrac + ", sizeFrac=" + this.sizeFrac + ", speed=" + this.speed + ", phase=" + this.phase + ", alpha=" + this.alpha + ")";
    }

    public DustSpec(float angle0, float radiusFrac, float sizeFrac, float speed, float phase, float alpha) {
        this.angle0 = angle0;
        this.radiusFrac = radiusFrac;
        this.sizeFrac = sizeFrac;
        this.speed = speed;
        this.phase = phase;
        this.alpha = alpha;
    }




}
