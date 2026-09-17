package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\b\u001a\u00020\t*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\n*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0010\u001a\u00020\n*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0017ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0012J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0017J\u0016\u0010 \u001a\u00020\u0016*\u00020\u0015H\u0017ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0018J\u0019\u0010\"\u001a\u00020\r*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0019\u0010\"\u001a\u00020\r*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010%R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "(J)F", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(F)J", "(I)J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: compiled from: Density.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m7004toDpGaN1DYA(Density $this, long $receiver) {
            return Density.super.mo721toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m7011toSp0xMU5do(Density $this, float $receiver) {
            return Density.super.mo728toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m7009toPx0680j_4(Density $this, float $receiver) {
            return Density.super.mo726toPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m7003roundToPx0680j_4(Density $this, float $receiver) {
            return Density.super.mo720roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m7008toPxR2X_6o(Density $this, long $receiver) {
            return Density.super.mo725toPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m7002roundToPxR2X_6o(Density $this, long $receiver) {
            return Density.super.mo719roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m7006toDpu2uoSUM(Density $this, int $receiver) {
            return Density.super.mo723toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m7013toSpkPz2Gy4(Density $this, int $receiver) {
            return Density.super.mo730toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m7005toDpu2uoSUM(Density $this, float $receiver) {
            return Density.super.mo722toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m7012toSpkPz2Gy4(Density $this, float $receiver) {
            return Density.super.mo729toSpkPz2Gy4($receiver);
        }

        @Deprecated
        public static Rect toRect(Density $this, DpRect $receiver) {
            return Density.super.toRect($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m7010toSizeXkaWNTQ(Density $this, long $receiver) {
            return Density.super.mo727toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m7007toDpSizekrfVVM(Density $this, long $receiver) {
            return Density.super.mo724toDpSizekrfVVM($receiver);
        }
    }

    /* JADX INFO: renamed from: toPx-0680j_4 */
    default float mo726toPx0680j_4(float $this$toPx_u2d0680j_4) {
        return getDensity() * $this$toPx_u2d0680j_4;
    }

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    default int mo720roundToPx0680j_4(float $this$roundToPx_u2d0680j_4) {
        float px = mo726toPx0680j_4($this$roundToPx_u2d0680j_4);
        if (Float.isInfinite(px)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(px);
    }

    /* JADX INFO: renamed from: toPx--R2X_6o */
    default float mo725toPxR2X_6o(long $this$toPx_u2d_u2dR2X_6o) {
        if (TextUnitType.m7237equalsimpl0(TextUnit.m7208getTypeUIouoOA($this$toPx_u2d_u2dR2X_6o), TextUnitType.INSTANCE.m7242getSpUIouoOA())) {
            return mo726toPx0680j_4(mo721toDpGaN1DYA($this$toPx_u2d_u2dR2X_6o));
        }
        throw new IllegalStateException("Only Sp can convert to Px".toString());
    }

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    default int mo719roundToPxR2X_6o(long $this$roundToPx_u2d_u2dR2X_6o) {
        float $this$fastRoundToInt$iv = mo725toPxR2X_6o($this$roundToPx_u2d_u2dR2X_6o);
        return Math.round($this$fastRoundToInt$iv);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo723toDpu2uoSUM(int $this$toDp_u2du2uoSUM) {
        float $this$dp$iv = $this$toDp_u2du2uoSUM / getDensity();
        return Dp.m7016constructorimpl($this$dp$iv);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo730toSpkPz2Gy4(int $this$toSp_u2dkPz2Gy4) {
        return mo728toSp0xMU5do(mo723toDpu2uoSUM($this$toSp_u2dkPz2Gy4));
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo722toDpu2uoSUM(float $this$toDp_u2du2uoSUM) {
        float $this$dp$iv = $this$toDp_u2du2uoSUM / getDensity();
        return Dp.m7016constructorimpl($this$dp$iv);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo729toSpkPz2Gy4(float $this$toSp_u2dkPz2Gy4) {
        return mo728toSp0xMU5do(mo722toDpu2uoSUM($this$toSp_u2dkPz2Gy4));
    }

    default Rect toRect(DpRect $this$toRect) {
        return new Rect(mo726toPx0680j_4($this$toRect.m7099getLeftD9Ej5fM()), mo726toPx0680j_4($this$toRect.m7101getTopD9Ej5fM()), mo726toPx0680j_4($this$toRect.m7100getRightD9Ej5fM()), mo726toPx0680j_4($this$toRect.m7098getBottomD9Ej5fM()));
    }

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    default long mo727toSizeXkaWNTQ(long $this$toSize_u2dXkaWNTQ) {
        if ($this$toSize_u2dXkaWNTQ != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            return SizeKt.Size(mo726toPx0680j_4(DpSize.m7114getWidthD9Ej5fM($this$toSize_u2dXkaWNTQ)), mo726toPx0680j_4(DpSize.m7112getHeightD9Ej5fM($this$toSize_u2dXkaWNTQ)));
        }
        return Size.INSTANCE.m4370getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    default long mo724toDpSizekrfVVM(long $this$toDpSize_u2dk_u2drfVVM) {
        if ($this$toDpSize_u2dk_u2drfVVM != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            return DpKt.m7038DpSizeYgX7TsA(mo722toDpu2uoSUM(Size.m4362getWidthimpl($this$toDpSize_u2dk_u2drfVVM)), mo722toDpu2uoSUM(Size.m4359getHeightimpl($this$toDpSize_u2dk_u2drfVVM)));
        }
        return DpSize.INSTANCE.m7123getUnspecifiedMYxV2XQ();
    }
}
