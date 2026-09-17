package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;

/* JADX INFO: compiled from: LazyLayoutBeyondBoundsModifierLocal.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001aA\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"unsupportedDirection", "", "lazyLayoutBeyondBoundsModifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "reverseLayout", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;ZLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutBeyondBoundsModifierLocalKt {
    /* JADX WARN: Code duplicated, block: B:15:0x005f  */
    /* JADX WARN: Code duplicated, block: B:25:0x0079  */
    /* JADX WARN: Code duplicated, block: B:35:0x0094  */
    /* JADX WARN: Code duplicated, block: B:45:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:55:0x00ce  */
    public static final Modifier lazyLayoutBeyondBoundsModifier(Modifier $this$lazyLayoutBeyondBoundsModifier, LazyLayoutBeyondBoundsState state, LazyLayoutBeyondBoundsInfo beyondBoundsInfo, boolean reverseLayout, LayoutDirection layoutDirection, Orientation orientation, boolean enabled, Composer $composer, int $changed) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        Modifier modifierThen;
        ComposerKt.sourceInformationMarkerStart($composer, 1331498025, "C(lazyLayoutBeyondBoundsModifier)P(5!1,4,2,3):LazyLayoutBeyondBoundsModifierLocal.kt#wow0x6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1331498025, $changed, -1, "androidx.compose.foundation.lazy.layout.lazyLayoutBeyondBoundsModifier (LazyLayoutBeyondBoundsModifierLocal.kt:51)");
        }
        if (!enabled) {
            $composer.startReplaceGroup(-1890658823);
            $composer.endReplaceGroup();
            modifierThen = $this$lazyLayoutBeyondBoundsModifier;
        } else {
            $composer.startReplaceGroup(-1890632411);
            ComposerKt.sourceInformation($composer, "54@2427L270");
            ComposerKt.sourceInformationMarkerStart($composer, -753724309, "CC(remember):LazyLayoutBeyondBoundsModifierLocal.kt#9igjgp");
            if ((($changed & 112) ^ 48) > 32 && $composer.changed(state)) {
                z = true;
            } else if (($changed & 48) == 32) {
                z = true;
            } else {
                z = false;
            }
            if ((($changed & 896) ^ 384) > 256 && $composer.changed(beyondBoundsInfo)) {
                z2 = true;
            } else if (($changed & 384) == 256) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z6 = z | z2;
            if ((($changed & 7168) ^ 3072) > 2048 && $composer.changed(reverseLayout)) {
                z3 = true;
            } else if (($changed & 3072) == 2048) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z7 = z6 | z3;
            if (((57344 & $changed) ^ 24576) > 16384 && $composer.changed(layoutDirection)) {
                z4 = true;
            } else if (($changed & 24576) == 16384) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z8 = z7 | z4;
            if (((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 || !$composer.changed(orientation)) {
                z5 = ($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072;
            }
            boolean invalid$iv = z8 | z5;
            Object value$iv = $composer.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new LazyLayoutBeyondBoundsModifierLocal(state, beyondBoundsInfo, reverseLayout, layoutDirection, orientation);
                $composer.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            modifierThen = $this$lazyLayoutBeyondBoundsModifier.then((LazyLayoutBeyondBoundsModifierLocal) value$iv);
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return modifierThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void unsupportedDirection() {
        throw new IllegalStateException("Lazy list does not support beyond bounds layout for the specified direction".toString());
    }
}
