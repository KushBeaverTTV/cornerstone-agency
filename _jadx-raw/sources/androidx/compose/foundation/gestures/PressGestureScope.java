package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: TapGestureDetector.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "awaitRelease", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAwaitRelease", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PressGestureScope extends Density {
    Object awaitRelease(Continuation<? super Unit> continuation);

    Object tryAwaitRelease(Continuation<? super Boolean> continuation);

    /* JADX INFO: compiled from: TapGestureDetector.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m821roundToPxR2X_6o(PressGestureScope $this, long $receiver) {
            return PressGestureScope.super.mo719roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m822roundToPx0680j_4(PressGestureScope $this, float $receiver) {
            return PressGestureScope.super.mo720roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m823toDpGaN1DYA(PressGestureScope $this, long $receiver) {
            return PressGestureScope.super.mo721toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m824toDpu2uoSUM(PressGestureScope $this, float $receiver) {
            return PressGestureScope.super.mo722toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m825toDpu2uoSUM(PressGestureScope $this, int $receiver) {
            return PressGestureScope.super.mo723toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m826toDpSizekrfVVM(PressGestureScope $this, long $receiver) {
            return PressGestureScope.super.mo724toDpSizekrfVVM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m827toPxR2X_6o(PressGestureScope $this, long $receiver) {
            return PressGestureScope.super.mo725toPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m828toPx0680j_4(PressGestureScope $this, float $receiver) {
            return PressGestureScope.super.mo726toPx0680j_4($receiver);
        }

        @Deprecated
        public static Rect toRect(PressGestureScope $this, DpRect $receiver) {
            return PressGestureScope.super.toRect($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m829toSizeXkaWNTQ(PressGestureScope $this, long $receiver) {
            return PressGestureScope.super.mo727toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m830toSp0xMU5do(PressGestureScope $this, float $receiver) {
            return PressGestureScope.super.mo728toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m831toSpkPz2Gy4(PressGestureScope $this, float $receiver) {
            return PressGestureScope.super.mo729toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m832toSpkPz2Gy4(PressGestureScope $this, int $receiver) {
            return PressGestureScope.super.mo730toSpkPz2Gy4($receiver);
        }
    }
}
