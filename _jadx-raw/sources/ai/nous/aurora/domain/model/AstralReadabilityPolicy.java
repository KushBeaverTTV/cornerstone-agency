package ai.nous.aurora.domain.model;

import ai.nous.aurora.presentation.theme.ThemeTokens;
import androidx.compose.ui.graphics.Color;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AstralReadabilityPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0015\u0010\u0011\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\u000fJ\u0015\u0010\u0013\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0014\u0010\u000fJ\u0015\u0010\u0015\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u000fJ\u0015\u0010\u0017\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0018\u0010\u000fJ\u001d\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lai/nous/aurora/domain/model/AstralReadabilityPolicy;", "", "<init>", "()V", "MIN_READING_PLANE_ALPHA", "", "ASTRAL_ELEVATED_BORDER_ALPHA", "elevatedListRowStops", "", "Lai/nous/aurora/domain/model/AstralReadabilityPolicy$GradientStop;", "tokens", "Lai/nous/aurora/presentation/theme/ThemeTokens;", "elevatedListRowBorder", "Landroidx/compose/ui/graphics/Color;", "elevatedListRowBorder-vNxB06k", "(Lai/nous/aurora/presentation/theme/ThemeTokens;)J", "assistantBubbleStops", "assistantBubbleBorder", "assistantBubbleBorder-vNxB06k", "loadingRowBackground", "loadingRowBackground-vNxB06k", "loadingRowBorder", "loadingRowBorder-vNxB06k", "filterChipUnselectedBackground", "filterChipUnselectedBackground-vNxB06k", "safeReadingSurface", "color", "astral", "", "safeReadingSurface-5vOe2sY", "(JZ)J", "GradientStop", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AstralReadabilityPolicy {
    public static final int $stable = 0;
    public static final float ASTRAL_ELEVATED_BORDER_ALPHA = 0.85f;
    public static final AstralReadabilityPolicy INSTANCE = new AstralReadabilityPolicy();
    public static final float MIN_READING_PLANE_ALPHA = 0.92f;

    private AstralReadabilityPolicy() {
    }

    /* JADX INFO: compiled from: AstralReadabilityPolicy.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000bJ$\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lai/nous/aurora/domain/model/AstralReadabilityPolicy$GradientStop;", "", "offset", "", "color", "Landroidx/compose/ui/graphics/Color;", "<init>", "(FJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getOffset", "()F", "getColor-0d7_KjU", "()J", "J", "component1", "component2", "component2-0d7_KjU", "copy", "copy-4WTKRHQ", "(FJ)Lai/nous/aurora/domain/model/AstralReadabilityPolicy$GradientStop;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class GradientStop {
        public static final int $stable = 0;
        private final long color;
        private final float offset;

        public /* synthetic */ GradientStop(float f, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(f, j);
        }

        /* JADX INFO: renamed from: copy-4WTKRHQ$default, reason: not valid java name */
        public static /* synthetic */ GradientStop m78copy4WTKRHQ$default(GradientStop gradientStop, float f, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                f = gradientStop.offset;
            }
            if ((i & 2) != 0) {
                j = gradientStop.color;
            }
            return gradientStop.m80copy4WTKRHQ(f, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getOffset() {
            return this.offset;
        }

        /* JADX INFO: renamed from: component2-0d7_KjU, reason: not valid java name and from getter */
        public final long getColor() {
            return this.color;
        }

        /* JADX INFO: renamed from: copy-4WTKRHQ, reason: not valid java name */
        public final GradientStop m80copy4WTKRHQ(float offset, long color) {
            return new GradientStop(offset, color, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GradientStop)) {
                return false;
            }
            GradientStop gradientStop = (GradientStop) other;
            return Float.compare(this.offset, gradientStop.offset) == 0 && Color.m4535equalsimpl0(this.color, gradientStop.color);
        }

        public int hashCode() {
            return (Float.hashCode(this.offset) * 31) + Color.m4541hashCodeimpl(this.color);
        }

        public String toString() {
            return "GradientStop(offset=" + this.offset + ", color=" + Color.m4542toStringimpl(this.color) + ")";
        }

        private GradientStop(float offset, long color) {
            this.offset = offset;
            this.color = color;
        }

        /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
        public final long m81getColor0d7_KjU() {
            return this.color;
        }

        public final float getOffset() {
            return this.offset;
        }
    }

    public final List<GradientStop> elevatedListRowStops(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        float f = 1.0f;
        float f2 = 0.18f;
        float f3 = 0.0f;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (tokens.getAstral()) {
            return CollectionsKt.listOf((Object[]) new GradientStop[]{new GradientStop(f3, tokens.m340getSurfaceHighlight0d7_KjU(), defaultConstructorMarker), new GradientStop(f2, tokens.m322getGlassSurface0d7_KjU(), defaultConstructorMarker), new GradientStop(f, tokens.m339getSurface0d7_KjU(), defaultConstructorMarker)});
        }
        long jM340getSurfaceHighlight0d7_KjU = tokens.m340getSurfaceHighlight0d7_KjU();
        return CollectionsKt.listOf((Object[]) new GradientStop[]{new GradientStop(f3, Color.m4532copywmQWz5c(jM340getSurfaceHighlight0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM340getSurfaceHighlight0d7_KjU) : 0.55f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM340getSurfaceHighlight0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM340getSurfaceHighlight0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM340getSurfaceHighlight0d7_KjU) : 0.0f), defaultConstructorMarker), new GradientStop(f2, tokens.m322getGlassSurface0d7_KjU(), defaultConstructorMarker), new GradientStop(f, tokens.m339getSurface0d7_KjU(), defaultConstructorMarker)});
    }

    /* JADX INFO: renamed from: elevatedListRowBorder-vNxB06k, reason: not valid java name */
    public final long m73elevatedListRowBordervNxB06k(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        if (tokens.getAstral()) {
            long jM321getGlassHairline0d7_KjU = tokens.m321getGlassHairline0d7_KjU();
            return Color.m4532copywmQWz5c(jM321getGlassHairline0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM321getGlassHairline0d7_KjU) : 0.85f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM321getGlassHairline0d7_KjU) : 0.0f);
        }
        long jM321getGlassHairline0d7_KjU2 = tokens.m321getGlassHairline0d7_KjU();
        return Color.m4532copywmQWz5c(jM321getGlassHairline0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM321getGlassHairline0d7_KjU2) : 0.8f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM321getGlassHairline0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM321getGlassHairline0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM321getGlassHairline0d7_KjU2) : 0.0f);
    }

    public final List<GradientStop> assistantBubbleStops(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        float f = 1.0f;
        float f2 = 0.0f;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (tokens.getAstral()) {
            return CollectionsKt.listOf((Object[]) new GradientStop[]{new GradientStop(f2, tokens.m322getGlassSurface0d7_KjU(), defaultConstructorMarker), new GradientStop(0.55f, tokens.m339getSurface0d7_KjU(), defaultConstructorMarker), new GradientStop(f, tokens.m339getSurface0d7_KjU(), defaultConstructorMarker)});
        }
        return CollectionsKt.listOf((Object[]) new GradientStop[]{new GradientStop(f2, tokens.m322getGlassSurface0d7_KjU(), defaultConstructorMarker), new GradientStop(f, tokens.m339getSurface0d7_KjU(), defaultConstructorMarker)});
    }

    /* JADX INFO: renamed from: assistantBubbleBorder-vNxB06k, reason: not valid java name */
    public final long m72assistantBubbleBordervNxB06k(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        if (tokens.getAstral()) {
            long jM321getGlassHairline0d7_KjU = tokens.m321getGlassHairline0d7_KjU();
            return Color.m4532copywmQWz5c(jM321getGlassHairline0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM321getGlassHairline0d7_KjU) : 0.85f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM321getGlassHairline0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM321getGlassHairline0d7_KjU) : 0.0f);
        }
        long jM321getGlassHairline0d7_KjU2 = tokens.m321getGlassHairline0d7_KjU();
        return Color.m4532copywmQWz5c(jM321getGlassHairline0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM321getGlassHairline0d7_KjU2) : 0.6f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM321getGlassHairline0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM321getGlassHairline0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM321getGlassHairline0d7_KjU2) : 0.0f);
    }

    /* JADX INFO: renamed from: loadingRowBackground-vNxB06k, reason: not valid java name */
    public final long m75loadingRowBackgroundvNxB06k(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        if (tokens.getAstral()) {
            return tokens.m339getSurface0d7_KjU();
        }
        long jM312getAmber0d7_KjU = tokens.m312getAmber0d7_KjU();
        return Color.m4532copywmQWz5c(jM312getAmber0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM312getAmber0d7_KjU) : 0.08f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM312getAmber0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM312getAmber0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM312getAmber0d7_KjU) : 0.0f);
    }

    /* JADX INFO: renamed from: loadingRowBorder-vNxB06k, reason: not valid java name */
    public final long m76loadingRowBordervNxB06k(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        if (tokens.getAstral()) {
            long jM312getAmber0d7_KjU = tokens.m312getAmber0d7_KjU();
            return Color.m4532copywmQWz5c(jM312getAmber0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM312getAmber0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM312getAmber0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM312getAmber0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM312getAmber0d7_KjU) : 0.0f);
        }
        long jM312getAmber0d7_KjU2 = tokens.m312getAmber0d7_KjU();
        return Color.m4532copywmQWz5c(jM312getAmber0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM312getAmber0d7_KjU2) : 0.25f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM312getAmber0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM312getAmber0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM312getAmber0d7_KjU2) : 0.0f);
    }

    /* JADX INFO: renamed from: filterChipUnselectedBackground-vNxB06k, reason: not valid java name */
    public final long m74filterChipUnselectedBackgroundvNxB06k(ThemeTokens tokens) {
        Intrinsics.checkNotNullParameter(tokens, "tokens");
        if (tokens.getAstral()) {
            return tokens.m341getSurfaceRaised0d7_KjU();
        }
        long jM341getSurfaceRaised0d7_KjU = tokens.m341getSurfaceRaised0d7_KjU();
        return Color.m4532copywmQWz5c(jM341getSurfaceRaised0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM341getSurfaceRaised0d7_KjU) : 0.8f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM341getSurfaceRaised0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM341getSurfaceRaised0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM341getSurfaceRaised0d7_KjU) : 0.0f);
    }

    /* JADX INFO: renamed from: safeReadingSurface-5vOe2sY, reason: not valid java name */
    public final long m77safeReadingSurface5vOe2sY(long color, boolean astral) {
        if (!astral) {
            return color;
        }
        return Color.m4532copywmQWz5c(color, (14 & 1) != 0 ? Color.m4536getAlphaimpl(color) : Math.max(Color.m4536getAlphaimpl(color), 0.92f), (14 & 2) != 0 ? Color.m4540getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(color) : 0.0f);
    }
}
