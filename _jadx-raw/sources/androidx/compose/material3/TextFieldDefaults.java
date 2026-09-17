package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.internal.TextFieldType;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.PropertyReference0Impl;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b7\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\\\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010-\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100J7\u00101\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010\u001c\u001a\u00020\u0015H\u0007¢\u0006\u0002\u00102J¦\u0002\u00103\u001a\u00020$2\u0006\u00104\u001a\u0002052\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020$07¢\u0006\u0002\b82\u0006\u0010%\u001a\u00020&2\u0006\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020;2\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010'\u001a\u00020&2\u0015\b\u0002\u0010<\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010=\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010?\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010@\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010A\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\u0015\b\u0002\u0010B\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u000107¢\u0006\u0002\b82\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010C\u001a\u00020D2\u0013\b\u0002\u0010E\u001a\r\u0012\u0004\u0012\u00020$07¢\u0006\u0002\b8H\u0007¢\u0006\u0002\u0010FJ\r\u0010,\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010GJÂ\u0003\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010H\u001a\u00020I2\b\b\u0002\u0010J\u001a\u00020I2\b\b\u0002\u0010K\u001a\u00020I2\b\b\u0002\u0010L\u001a\u00020I2\b\b\u0002\u0010M\u001a\u00020I2\b\b\u0002\u0010N\u001a\u00020I2\b\b\u0002\u0010O\u001a\u00020I2\b\b\u0002\u0010P\u001a\u00020I2\b\b\u0002\u0010Q\u001a\u00020I2\b\b\u0002\u0010R\u001a\u00020I2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010T2\b\b\u0002\u0010U\u001a\u00020I2\b\b\u0002\u0010V\u001a\u00020I2\b\b\u0002\u0010W\u001a\u00020I2\b\b\u0002\u0010X\u001a\u00020I2\b\b\u0002\u0010Y\u001a\u00020I2\b\b\u0002\u0010Z\u001a\u00020I2\b\b\u0002\u0010[\u001a\u00020I2\b\b\u0002\u0010\\\u001a\u00020I2\b\b\u0002\u0010]\u001a\u00020I2\b\b\u0002\u0010^\u001a\u00020I2\b\b\u0002\u0010_\u001a\u00020I2\b\b\u0002\u0010`\u001a\u00020I2\b\b\u0002\u0010a\u001a\u00020I2\b\b\u0002\u0010b\u001a\u00020I2\b\b\u0002\u0010c\u001a\u00020I2\b\b\u0002\u0010d\u001a\u00020I2\b\b\u0002\u0010e\u001a\u00020I2\b\b\u0002\u0010f\u001a\u00020I2\b\b\u0002\u0010g\u001a\u00020I2\b\b\u0002\u0010h\u001a\u00020I2\b\b\u0002\u0010i\u001a\u00020I2\b\b\u0002\u0010j\u001a\u00020I2\b\b\u0002\u0010k\u001a\u00020I2\b\b\u0002\u0010l\u001a\u00020I2\b\b\u0002\u0010m\u001a\u00020I2\b\b\u0002\u0010n\u001a\u00020I2\b\b\u0002\u0010o\u001a\u00020I2\b\b\u0002\u0010p\u001a\u00020I2\b\b\u0002\u0010q\u001a\u00020I2\b\b\u0002\u0010r\u001a\u00020I2\b\b\u0002\u0010s\u001a\u00020I2\b\b\u0002\u0010t\u001a\u00020IH\u0007ø\u0001\u0000¢\u0006\u0004\bu\u0010vJ8\u0010w\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b|\u0010}J8\u0010~\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u007f\u0010}J<\u0010\u0080\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0005\b\u0081\u0001\u0010}J<\u0010\u0082\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0005\b\u0083\u0001\u0010}J<\u0010\u0084\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0005\b\u0085\u0001\u0010}J<\u0010\u0086\u0001\u001a\u00020D2\b\b\u0002\u0010x\u001a\u00020\u00042\b\b\u0002\u0010z\u001a\u00020\u00042\b\b\u0002\u0010y\u001a\u00020\u00042\b\b\u0002\u0010{\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0005\b\u0087\u0001\u0010}JM\u0010\u0088\u0001\u001a\u00020+*\u00020+2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0006\u0010,\u001a\u00020\u001f2\b\b\u0002\u0010-\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R$\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0007R\u0019\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0013\u0010\u0007R\u001a\u0010\u0014\u001a\u00020\u00158GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u00158GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u001c\u001a\u00020\u00158G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0018R\u0018\u0010\u001e\u001a\u00020\u001f*\u00020 8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u008b\u0001"}, d2 = {"Landroidx/compose/material3/TextFieldDefaults;", "", "()V", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM$annotations", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "FocusedIndicatorThickness", "getFocusedIndicatorThickness-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM$annotations", "getUnfocusedBorderThickness-D9Ej5fM", "UnfocusedIndicatorThickness", "getUnfocusedIndicatorThickness-D9Ej5fM", "filledShape", "Landroidx/compose/ui/graphics/Shape;", "getFilledShape$annotations", "getFilledShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "outlinedShape", "getOutlinedShape$annotations", "getOutlinedShape", "shape", "getShape", "defaultTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultTextFieldColors", "(Landroidx/compose/material3/ColorScheme;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", TextFieldImplKt.ContainerId, "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "modifier", "Landroidx/compose/ui/Modifier;", "colors", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "Container-4EFweAY", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "ContainerBox", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "DecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "container", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "contentPaddingWithLabel", "start", "end", "top", "bottom", "contentPaddingWithLabel-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "contentPaddingWithoutLabel", "contentPaddingWithoutLabel-a9UjIt4", "outlinedTextFieldPadding", "outlinedTextFieldPadding-a9UjIt4", "supportingTextPadding", "supportingTextPadding-a9UjIt4$material3_release", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "indicatorLine", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m7016constructorimpl(56);
    private static final float MinWidth = Dp.m7016constructorimpl(280);
    private static final float UnfocusedIndicatorThickness = Dp.m7016constructorimpl(1);
    private static final float FocusedIndicatorThickness = Dp.m7016constructorimpl(2);
    private static final float UnfocusedBorderThickness = UnfocusedIndicatorThickness;
    private static final float FocusedBorderThickness = FocusedIndicatorThickness;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.shape", imports = {}))
    public static /* synthetic */ void getFilledShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.FocusedIndicatorThickness` and `OutlinedTextFieldDefaults.FocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.FocusedIndicatorThickness", imports = {}))
    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m3040getFocusedBorderThicknessD9Ej5fM$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.shape", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    public static /* synthetic */ void getOutlinedShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.UnfocusedIndicatorThickness` and `OutlinedTextFieldDefaults.UnfocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.UnfocusedIndicatorThickness", imports = {}))
    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m3041getUnfocusedBorderThicknessD9Ej5fM$annotations() {
    }

    private TextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1941327459, "C60@2756L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1941327459, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-shape> (TextFieldDefaults.kt:60)");
        }
        Shape value = ShapesKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m3053getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m3054getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m3056getUnfocusedIndicatorThicknessD9Ej5fM() {
        return UnfocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m3052getFocusedIndicatorThicknessD9Ej5fM() {
        return FocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: Container-4EFweAY, reason: not valid java name */
    public final void m3047Container4EFweAY(final boolean enabled, final boolean isError, final InteractionSource interactionSource, Modifier modifier, TextFieldColors colors, Shape shape, float focusedIndicatorLineThickness, float unfocusedIndicatorLineThickness, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TextFieldColors colors2;
        Shape shape2;
        float focusedIndicatorLineThickness2;
        float f;
        Modifier.Companion modifier3;
        float focusedIndicatorLineThickness3;
        float unfocusedIndicatorLineThickness2;
        TextFieldColors colors3;
        Shape shape3;
        int $dirty;
        TextFieldColors colors4;
        Modifier modifier4;
        float unfocusedIndicatorLineThickness3;
        float unfocusedIndicatorLineThickness4;
        Shape shape4;
        Composer $composer2 = $composer.startRestartGroup(-818661242);
        ComposerKt.sourceInformation($composer2, "C(Container)P(1,4,3,5!1,6,2:c#ui.unit.Dp,7:c#ui.unit.Dp)105@4681L8,106@4732L5,110@4933L25,112@5006L198,116@5213L496:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i3 = $composer2.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i3;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i3;
        } else {
            colors2 = colors;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i4 = $composer2.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                focusedIndicatorLineThickness2 = focusedIndicatorLineThickness;
                int i5 = $composer2.changed(focusedIndicatorLineThickness2) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                focusedIndicatorLineThickness2 = focusedIndicatorLineThickness;
            }
            $dirty2 |= i5;
        } else {
            focusedIndicatorLineThickness2 = focusedIndicatorLineThickness;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                f = unfocusedIndicatorLineThickness;
                int i6 = $composer2.changed(f) ? 8388608 : 4194304;
                $dirty2 |= i6;
            } else {
                f = unfocusedIndicatorLineThickness;
            }
            $dirty2 |= i6;
        } else {
            f = unfocusedIndicatorLineThickness;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer2.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & $dirty2) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            colors4 = colors2;
            shape4 = shape2;
            unfocusedIndicatorLineThickness4 = focusedIndicatorLineThickness2;
            unfocusedIndicatorLineThickness3 = f;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 16) != 0) {
                    TextFieldColors colors5 = colors($composer2, ($dirty2 >> 24) & 14);
                    $dirty2 &= -57345;
                    colors2 = colors5;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                    shape2 = INSTANCE.getShape($composer2, 6);
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                    focusedIndicatorLineThickness2 = FocusedIndicatorThickness;
                }
                if ((i & 128) != 0) {
                    unfocusedIndicatorLineThickness2 = UnfocusedIndicatorThickness;
                    colors3 = colors2;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    shape3 = shape2;
                    $dirty = $dirty2 & (-29360129);
                } else {
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    unfocusedIndicatorLineThickness2 = f;
                    colors3 = colors2;
                    shape3 = shape2;
                    $dirty = $dirty2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    unfocusedIndicatorLineThickness2 = f;
                    colors3 = colors2;
                    shape3 = shape2;
                    $dirty = (-29360129) & $dirty2;
                    modifier3 = modifier2;
                } else {
                    modifier3 = modifier2;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    unfocusedIndicatorLineThickness2 = f;
                    colors3 = colors2;
                    shape3 = shape2;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-818661242, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.Container (TextFieldDefaults.kt:109)");
            }
            boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer2, ($dirty >> 6) & 14).getValue().booleanValue();
            colors4 = colors3;
            Shape shape5 = shape3;
            final State<Color> stateM467animateColorAsStateeuL9pac = SingleValueAnimationKt.m467animateColorAsStateeuL9pac(colors3.m2984containerColorXeAY9LY$material3_release(enabled, isError, focused), AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, $composer2, 48, 12);
            modifier4 = modifier3;
            BoxKt.Box(m3057indicatorLinegv0btCI(TextFieldImplKt.textFieldBackground(modifier3, new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(stateM467animateColorAsStateeuL9pac) { // from class: androidx.compose.material3.TextFieldDefaults$Container$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            }), shape5), enabled, isError, interactionSource, colors4, focusedIndicatorLineThickness3, unfocusedIndicatorLineThickness2), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedIndicatorLineThickness3 = unfocusedIndicatorLineThickness2;
            unfocusedIndicatorLineThickness4 = focusedIndicatorLineThickness3;
            shape4 = shape5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier4;
            final TextFieldColors textFieldColors = colors4;
            final Shape shape6 = shape4;
            final float f2 = unfocusedIndicatorLineThickness4;
            final float f3 = unfocusedIndicatorLineThickness3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$Container$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    this.$tmp0_rcvr.m3047Container4EFweAY(enabled, isError, interactionSource, modifier5, textFieldColors, shape6, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3042indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedIndicatorThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedIndicatorThickness;
        }
        return textFieldDefaults.m3057indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m3057indicatorLinegv0btCI(Modifier $this$indicatorLine_u2dgv0btCI, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float focusedIndicatorLineThickness, final float unfocusedIndicatorLineThickness) {
        return ComposedModifierKt.composed($this$indicatorLine_u2dgv0btCI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("indicatorLine");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("isError", Boolean.valueOf(isError));
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("colors", colors);
                $this$null.getProperties().set("focusedIndicatorLineThickness", Dp.m7014boximpl(focusedIndicatorLineThickness));
                $this$null.getProperties().set("unfocusedIndicatorLineThickness", Dp.m7014boximpl(unfocusedIndicatorLineThickness));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                $composer.startReplaceGroup(-891038934);
                ComposerKt.sourceInformation($composer, "C169@7666L25,171@7739L263:TextFieldDefaults.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-891038934, $changed, -1, "androidx.compose.material3.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:169)");
                }
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer, 0).getValue().booleanValue();
                Modifier modifierDrawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, TextFieldImplKt.m3356animateBorderStrokeAsStateNuRrP5Q(enabled, isError, focused, colors, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, $composer, 0));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return modifierDrawIndicatorLine;
            }
        });
    }

    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Function2<? super Composer, ? super Integer, Unit> function9, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function10, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Shape shape2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        TextFieldColors colors3;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function17;
        PaddingValues contentPadding3;
        int $dirty1;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function18;
        Function2<? super Composer, ? super Integer, Unit> function19;
        Function2<? super Composer, ? super Integer, Unit> function20;
        TextFieldColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> function24;
        boolean isError3;
        Composer $composer2 = $composer.startRestartGroup(289640444);
        ComposerKt.sourceInformation($composer2, "C(DecorationBox)P(16,4,3,12,17,5,6,7,9,8,15,10,13,14,11!1,2)256@13034L5,257@13075L8,264@13325L428,277@13770L707:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(singleLine) ? 2048 : 1024;
        }
        int i2 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 131072 : 65536;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(isError) ? 1048576 : 524288;
        }
        int i4 = i & 128;
        if (i4 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changedInstance(function5) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i7 = i & 1024;
        if (i7 != 0) {
            $dirty2 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function6) ? 4 : 2;
        }
        int i8 = i & 2048;
        if (i8 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function7) ? 32 : 16;
        }
        int i9 = i & 4096;
        if (i9 != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(function8) ? 256 : 128;
        }
        int i10 = i & 8192;
        if (i10 != 0) {
            $dirty2 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty2 |= $composer2.changedInstance(function9) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer2.changed(shape)) {
                i2 = 16384;
            }
            $dirty2 |= i2;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= ((i & 32768) == 0 && $composer2.changed(colors)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty2 |= ((i & 65536) == 0 && $composer2.changed(contentPadding)) ? 1048576 : 524288;
        }
        int i11 = i & 131072;
        if (i11 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty2 |= $composer2.changedInstance(function10) ? 8388608 : 4194304;
        }
        if ((i & 262144) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty2 |= $composer2.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty & 306783379) == 306783378 && (38347923 & $dirty2) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            isError3 = isError;
            function22 = function3;
            function24 = function4;
            function18 = function5;
            function19 = function6;
            function20 = function7;
            function21 = function8;
            function23 = function9;
            shape4 = shape;
            colors4 = colors;
            contentPadding3 = contentPadding;
            function2RememberComposableLambda = function10;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                isError2 = i3 != 0 ? false : isError;
                function11 = i4 != 0 ? null : function3;
                function12 = i5 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function25 = i6 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function26 = i7 != 0 ? null : function6;
                Function2<? super Composer, ? super Integer, Unit> function27 = i8 != 0 ? null : function7;
                function13 = i9 != 0 ? null : function8;
                function14 = i10 != 0 ? null : function9;
                if ((i & 16384) != 0) {
                    shape2 = INSTANCE.getShape($composer2, 6);
                    $dirty2 &= -57345;
                } else {
                    shape2 = shape;
                }
                if ((32768 & i) != 0) {
                    colors2 = colors($composer2, ($dirty2 >> 24) & 14);
                    $dirty2 &= -458753;
                } else {
                    colors2 = colors;
                }
                if ((i & 65536) != 0) {
                    contentPadding2 = function11 == null ? m3039contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : m3038contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -3670017;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i11 != 0) {
                    int $dirty3 = $dirty2;
                    final boolean z = isError2;
                    final TextFieldColors textFieldColors = colors2;
                    final Shape shape5 = shape2;
                    Function2<Composer, Integer, Unit> function28 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C265@13339L404:TextFieldDefaults.kt#uh7d8r");
                            if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-435523791, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:265)");
                                }
                                TextFieldDefaults.INSTANCE.m3047Container4EFweAY(enabled, z, interactionSource, Modifier.INSTANCE, textFieldColors, shape5, TextFieldDefaults.INSTANCE.m3052getFocusedIndicatorThicknessD9Ej5fM(), TextFieldDefaults.INSTANCE.m3056getUnfocusedIndicatorThicknessD9Ej5fM(), $composer3, 114822144, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer3.skipToGroupEnd();
                        }
                    };
                    Function2<? super Composer, ? super Integer, Unit> function29 = function27;
                    colors3 = colors2;
                    function15 = function26;
                    function16 = function29;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-435523791, true, function28, $composer2, 54);
                    shape3 = shape2;
                    function17 = function25;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty3;
                } else {
                    int $dirty4 = $dirty2;
                    Function2<? super Composer, ? super Integer, Unit> function30 = function27;
                    colors3 = colors2;
                    function15 = function26;
                    function16 = function30;
                    function2RememberComposableLambda = function10;
                    shape3 = shape2;
                    function17 = function25;
                    contentPadding3 = contentPadding2;
                    $dirty1 = $dirty4;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 16384) != 0) {
                    $dirty2 &= -57345;
                }
                if ((32768 & i) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty2 &= -3670017;
                }
                isError2 = isError;
                function11 = function3;
                function12 = function4;
                function15 = function6;
                function16 = function7;
                function13 = function8;
                function14 = function9;
                shape3 = shape;
                colors3 = colors;
                contentPadding3 = contentPadding;
                function2RememberComposableLambda = function10;
                $dirty1 = $dirty2;
                function17 = function5;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(289640444, $dirty, $dirty1, "androidx.compose.material3.TextFieldDefaults.DecorationBox (TextFieldDefaults.kt:276)");
            }
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, function2, visualTransformation, function11, function12, function17, function15, function16, function13, function14, singleLine, enabled, isError2, interactionSource, contentPadding3, colors3, function2RememberComposableLambda, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024) | (($dirty1 << 21) & 1879048192), (($dirty1 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | (($dirty1 >> 3) & 458752) | (($dirty1 << 3) & 3670016) | (29360128 & $dirty1), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape4 = shape3;
            function18 = function17;
            function19 = function15;
            function20 = function16;
            colors4 = colors3;
            function21 = function13;
            function22 = function11;
            function23 = function14;
            function24 = function12;
            isError3 = isError2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z2 = isError3;
            final Function2<? super Composer, ? super Integer, Unit> function31 = function22;
            final Function2<? super Composer, ? super Integer, Unit> function32 = function24;
            final Function2<? super Composer, ? super Integer, Unit> function33 = function18;
            final Function2<? super Composer, ? super Integer, Unit> function34 = function19;
            final Function2<? super Composer, ? super Integer, Unit> function35 = function20;
            final Function2<? super Composer, ? super Integer, Unit> function36 = function21;
            final Function2<? super Composer, ? super Integer, Unit> function37 = function23;
            final Shape shape6 = shape4;
            final TextFieldColors textFieldColors2 = colors4;
            final PaddingValues paddingValues = contentPadding3;
            final Function2<? super Composer, ? super Integer, Unit> function38 = function2RememberComposableLambda;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i12) {
                    TextFieldDefaults.this.DecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, z2, function31, function32, function33, function34, function35, function36, function37, shape6, textFieldColors2, paddingValues, function38, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3038contentPaddingWithLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m3049contentPaddingWithLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3049contentPaddingWithLabela9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m1030PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3039contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m3050contentPaddingWithoutLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3050contentPaddingWithoutLabela9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m1030PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3044supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getSupportingTopPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = Dp.m7016constructorimpl(0);
        }
        return textFieldDefaults.m3059supportingTextPaddinga9UjIt4$material3_release(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release, reason: not valid java name */
    public final PaddingValues m3059supportingTextPaddinga9UjIt4$material3_release(float start, float top, float end, float bottom) {
        return PaddingKt.m1030PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 831731228, "C(colors)336@16080L11,336@16092L22:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(831731228, $changed, -1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:336)");
        }
        TextFieldColors defaultTextFieldColors = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldColors;
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m3048colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart($composer, 1513344955, "C(colors)P(30:c#ui.graphics.Color,41:c#ui.graphics.Color,9:c#ui.graphics.Color,20:c#ui.graphics.Color,22:c#ui.graphics.Color,33:c#ui.graphics.Color,1:c#ui.graphics.Color,11:c#ui.graphics.Color,0:c#ui.graphics.Color,12:c#ui.graphics.Color,32,23:c#ui.graphics.Color,34:c#ui.graphics.Color,2:c#ui.graphics.Color,13:c#ui.graphics.Color,25:c#ui.graphics.Color,36:c#ui.graphics.Color,4:c#ui.graphics.Color,15:c#ui.graphics.Color,31:c#ui.graphics.Color,42:c#ui.graphics.Color,10:c#ui.graphics.Color,21:c#ui.graphics.Color,24:c#ui.graphics.Color,35:c#ui.graphics.Color,3:c#ui.graphics.Color,14:c#ui.graphics.Color,26:c#ui.graphics.Color,37:c#ui.graphics.Color,5:c#ui.graphics.Color,16:c#ui.graphics.Color,29:c#ui.graphics.Color,40:c#ui.graphics.Color,8:c#ui.graphics.Color,19:c#ui.graphics.Color,27:c#ui.graphics.Color,38:c#ui.graphics.Color,6:c#ui.graphics.Color,17:c#ui.graphics.Color,28:c#ui.graphics.Color,39:c#ui.graphics.Color,7:c#ui.graphics.Color,18:c#ui.graphics.Color)438@22922L11,438@22934L22:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorCursorColor;
        SelectionColors selectionColors2 = (i & 1024) != 0 ? null : selectionColors;
        long focusedIndicatorColor2 = (i & 2048) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 4096) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedIndicatorColor;
        long disabledIndicatorColor2 = (i & 8192) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledIndicatorColor;
        long errorIndicatorColor2 = (i & 16384) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorIndicatorColor;
        long focusedLeadingIconColor2 = (32768 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (65536 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedLeadingIconColor;
        long disabledLeadingIconColor2 = (131072 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledLeadingIconColor;
        long errorLeadingIconColor2 = (262144 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (524288 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (1048576 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedTrailingIconColor;
        long disabledTrailingIconColor2 = (2097152 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledTrailingIconColor;
        long errorTrailingIconColor2 = (4194304 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorTrailingIconColor;
        long focusedLabelColor2 = (8388608 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedLabelColor;
        long unfocusedLabelColor2 = (16777216 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedLabelColor;
        long disabledLabelColor2 = (33554432 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledLabelColor;
        long errorLabelColor2 = (67108864 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorLabelColor;
        long focusedPlaceholderColor2 = (134217728 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (268435456 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedPlaceholderColor;
        long disabledPlaceholderColor2 = (536870912 & i) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledPlaceholderColor;
        long errorPlaceholderColor2 = (i & KeyHandler.KEYMOD_CTRL) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (i2 & 1) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i2 & 2) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedSupportingTextColor;
        long disabledSupportingTextColor2 = (i2 & 4) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledSupportingTextColor;
        long errorSupportingTextColor2 = (i2 & 8) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 16) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 32) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedPrefixColor;
        long disabledPrefixColor2 = (i2 & 64) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledPrefixColor;
        long errorPrefixColor2 = (i2 & 128) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 256) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 512) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : unfocusedSuffixColor;
        long disabledSuffixColor2 = (i2 & 1024) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : disabledSuffixColor;
        long errorSuffixColor2 = (i2 & 2048) != 0 ? Color.INSTANCE.m4570getUnspecified0d7_KjU() : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1513344955, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:438)");
        }
        TextFieldColors textFieldColorsM2985copyejIjP34 = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme($composer, 6), $composer, ($changed4 >> 6) & 112).m2985copyejIjP34(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsM2985copyejIjP34;
    }

    public final TextFieldColors getDefaultTextFieldColors(ColorScheme $this$defaultTextFieldColors, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1341970309, "C:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1341970309, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-defaultTextFieldColors> (TextFieldDefaults.kt:486)");
        }
        TextFieldColors it = $this$defaultTextFieldColors.getDefaultTextFieldColorsCached();
        $composer.startReplaceGroup(27085453);
        ComposerKt.sourceInformation($composer, "*501@26788L7");
        if (it == null) {
            long jFromToken = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusInputColor());
            long jFromToken2 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputColor());
            long jFromToken3 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledInputColor());
            long jM4532copywmQWz5c = Color.m4532copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken3) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken3) : 0.0f);
            long jFromToken4 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorInputColor());
            long jFromToken5 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long jFromToken6 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long jFromToken7 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long jFromToken8 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
            long jFromToken9 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getCaretColor());
            long jFromToken10 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor());
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jFromToken11 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor());
            long jFromToken12 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor());
            long jFromToken13 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor());
            long jM4532copywmQWz5c2 = Color.m4532copywmQWz5c(jFromToken13, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken13) : FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken13) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken13) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken13) : 0.0f);
            long jFromToken14 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor());
            long jFromToken15 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor());
            long jFromToken16 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getLeadingIconColor());
            long jFromToken17 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor());
            long jM4532copywmQWz5c3 = Color.m4532copywmQWz5c(jFromToken17, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken17) : FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken17) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken17) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken17) : 0.0f);
            long jFromToken18 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor());
            long jFromToken19 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor());
            long jFromToken20 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getTrailingIconColor());
            long jFromToken21 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor());
            long jM4532copywmQWz5c4 = Color.m4532copywmQWz5c(jFromToken21, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken21) : FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken21) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken21) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken21) : 0.0f);
            long jFromToken22 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor());
            long jFromToken23 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusLabelColor());
            long jFromToken24 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getLabelColor());
            long jFromToken25 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledLabelColor());
            long jM4532copywmQWz5c5 = Color.m4532copywmQWz5c(jFromToken25, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken25) : FilledTextFieldTokens.INSTANCE.getDisabledLabelOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken25) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken25) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken25) : 0.0f);
            long jFromToken26 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorLabelColor());
            long jFromToken27 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long jFromToken28 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long jFromToken29 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledInputColor());
            long jM4532copywmQWz5c6 = Color.m4532copywmQWz5c(jFromToken29, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken29) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken29) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken29) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken29) : 0.0f);
            long jFromToken30 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
            long jFromToken31 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusSupportingColor());
            long jFromToken32 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getSupportingColor());
            long jFromToken33 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor());
            long jM4532copywmQWz5c7 = Color.m4532copywmQWz5c(jFromToken33, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken33) : FilledTextFieldTokens.INSTANCE.getDisabledSupportingOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken33) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken33) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken33) : 0.0f);
            long jFromToken34 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorSupportingColor());
            long jFromToken35 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jFromToken36 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jFromToken37 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jM4532copywmQWz5c8 = Color.m4532copywmQWz5c(jFromToken37, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken37) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken37) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken37) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken37) : 0.0f);
            long jFromToken38 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
            long jFromToken39 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
            long jFromToken40 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
            long jFromToken41 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
            it = new TextFieldColors(jFromToken, jFromToken2, jM4532copywmQWz5c, jFromToken4, jFromToken5, jFromToken6, jFromToken7, jFromToken8, jFromToken9, jFromToken10, (SelectionColors) objConsume, jFromToken11, jFromToken12, jM4532copywmQWz5c2, jFromToken14, jFromToken15, jFromToken16, jM4532copywmQWz5c3, jFromToken18, jFromToken19, jFromToken20, jM4532copywmQWz5c4, jFromToken22, jFromToken23, jFromToken24, jM4532copywmQWz5c5, jFromToken26, jFromToken27, jFromToken28, jM4532copywmQWz5c6, jFromToken30, jFromToken31, jFromToken32, jM4532copywmQWz5c7, jFromToken34, jFromToken35, jFromToken36, jM4532copywmQWz5c8, jFromToken38, jFromToken39, jFromToken40, Color.m4532copywmQWz5c(jFromToken41, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jFromToken41) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m4540getRedimpl(jFromToken41) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jFromToken41) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jFromToken41) : 0.0f), ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor()), null);
            $this$defaultTextFieldColors.setDefaultTextFieldColorsCached$material3_release(it);
        }
        $composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return it;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to TextFieldDefaults.Container", replaceWith = @ReplaceWith(expression = "Container(\n    enabled = enabled,\n    isError = isError,\n    interactionSource = interactionSource,\n    colors = colors,\n    shape = shape,\n)", imports = {}))
    public final void ContainerBox(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        Shape shape3;
        Composer $composer2 = $composer.startRestartGroup(918564008);
        ComposerKt.sourceInformation($composer2, "C(ContainerBox)P(1,3,2)590@32311L5,592@32334L368:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty |= i2;
            } else {
                shape2 = shape;
            }
            $dirty |= i2;
        } else {
            shape2 = shape;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(this) ? 131072 : 65536;
        }
        if ((74899 & $dirty) == 74898 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape3 = shape2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
            } else if ((i & 16) != 0) {
                shape2 = INSTANCE.getShape($composer2, 6);
                $dirty &= -57345;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(918564008, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.ContainerBox (TextFieldDefaults.kt:592)");
            }
            m3047Container4EFweAY(enabled, isError, interactionSource, Modifier.INSTANCE, colors, shape2, FocusedIndicatorThickness, UnfocusedIndicatorThickness, $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896) | (($dirty << 3) & 57344) | (458752 & ($dirty << 3)) | (($dirty << 9) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Shape shape4 = shape3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.ContainerBox.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    TextFieldDefaults.this.ContainerBox(enabled, isError, interactionSource, colors, shape4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -584749279, "C613@33100L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-584749279, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-outlinedShape> (TextFieldDefaults.kt:613)");
        }
        Shape shape = OutlinedTextFieldDefaults.INSTANCE.getShape($composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shape;
    }

    public final Shape getFilledShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 611926497, "C621@33346L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611926497, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-filledShape> (TextFieldDefaults.kt:621)");
        }
        Shape shape = getShape($composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shape;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m3055getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m3051getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3045textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m3060textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3060textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return m3049contentPaddingWithLabela9UjIt4(start, end, top, bottom);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3046textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m3061textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithoutLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithoutLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3061textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return m3050contentPaddingWithoutLabela9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3043outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m3058outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.contentPadding`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.contentPadding(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3058outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return OutlinedTextFieldDefaults.INSTANCE.m2718contentPaddinga9UjIt4(start, top, end, bottom);
    }
}
