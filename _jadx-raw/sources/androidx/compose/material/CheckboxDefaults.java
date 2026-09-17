package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: compiled from: Checkbox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material/CheckboxDefaults;", "", "()V", "colors", "Landroidx/compose/material/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledColor", "disabledIndeterminateColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/CheckboxColors;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();

    private CheckboxDefaults() {
    }

    /* JADX INFO: renamed from: colors-zjMxDiM, reason: not valid java name */
    public final CheckboxColors m1824colorszjMxDiM(long checkedColor, long uncheckedColor, long checkmarkColor, long disabledColor, long disabledIndeterminateColor, Composer $composer, int $changed, int i) {
        long uncheckedColor2;
        long disabledColor2;
        long disabledIndeterminateColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 469524104, "C(colors)P(0:c#ui.graphics.Color,4:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)226@9439L6,227@9503L6,228@9586L6,229@9647L6,229@9690L8,230@9784L8,232@9833L922:Checkbox.kt#jmzs0o");
        long checkedColor2 = (i & 1) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1848getSecondary0d7_KjU() : checkedColor;
        if ((i & 2) != 0) {
            long jM1845getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            uncheckedColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU) : 0.0f);
        } else {
            uncheckedColor2 = uncheckedColor;
        }
        long checkmarkColor2 = (i & 4) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1850getSurface0d7_KjU() : checkmarkColor;
        if ((i & 8) != 0) {
            long jM1845getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            disabledColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU2) : 0.0f);
        } else {
            disabledColor2 = disabledColor;
        }
        if ((i & 16) != 0) {
            long j = checkedColor2;
            disabledIndeterminateColor2 = Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f);
        } else {
            disabledIndeterminateColor2 = disabledIndeterminateColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(469524104, $changed, -1, "androidx.compose.material.CheckboxDefaults.colors (Checkbox.kt:231)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 654676893, "CC(remember):Checkbox.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(checkedColor2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(uncheckedColor2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(checkmarkColor2)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(disabledColor2)) || ($changed & 3072) == 2048) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(disabledIndeterminateColor2)) || ($changed & 24576) == 16384);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
            long j2 = checkmarkColor2;
            long jM4532copywmQWz5c = Color.m4532copywmQWz5c(j2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j2) : 0.0f, (14 & 2) != 0 ? Color.m4540getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j2) : 0.0f);
            long disabledIndeterminateColor3 = disabledIndeterminateColor2;
            long j3 = checkedColor2;
            long disabledColor3 = disabledColor2;
            long uncheckedColor3 = uncheckedColor2;
            long uncheckedColor4 = checkedColor2;
            value$iv = new DefaultCheckboxColors(checkmarkColor2, jM4532copywmQWz5c, uncheckedColor4, Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : 0.0f, (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f), disabledColor3, Color.m4532copywmQWz5c(disabledColor3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(disabledColor3) : 0.0f, (14 & 2) != 0 ? Color.m4540getRedimpl(disabledColor3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(disabledColor3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(disabledColor3) : 0.0f), disabledIndeterminateColor3, checkedColor2, uncheckedColor3, disabledColor3, disabledIndeterminateColor3, null);
            $composer.updateRememberedValue(value$iv);
        }
        DefaultCheckboxColors defaultCheckboxColors = (DefaultCheckboxColors) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultCheckboxColors;
    }
}
