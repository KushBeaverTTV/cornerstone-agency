package androidx.compose.material;

import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
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
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JP\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%J×\u0001\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u0002042\u0013\b\u0002\u00105\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+H\u0007¢\u0006\u0002\u00106Já\u0001\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u0002042\u0013\b\u0002\u00105\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+H\u0007¢\u0006\u0002\u00107JÂ\u0001\u00108\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u00109JÌ\u0001\u00108\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0011\u0010)\u001a\r\u0012\u0004\u0012\u00020\u00190*¢\u0006\u0002\b+2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0015\b\u0002\u0010/\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00100\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0019\u0018\u00010*¢\u0006\u0002\b+2\b\b\u0002\u0010!\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u00103\u001a\u000204H\u0007¢\u0006\u0002\u0010:Jä\u0001\u0010;\u001a\u00020 2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=2\b\b\u0002\u0010A\u001a\u00020=2\b\b\u0002\u0010B\u001a\u00020=2\b\b\u0002\u0010C\u001a\u00020=2\b\b\u0002\u0010D\u001a\u00020=2\b\b\u0002\u0010E\u001a\u00020=2\b\b\u0002\u0010F\u001a\u00020=2\b\b\u0002\u0010G\u001a\u00020=2\b\b\u0002\u0010H\u001a\u00020=2\b\b\u0002\u0010I\u001a\u00020=2\b\b\u0002\u0010J\u001a\u00020=2\b\b\u0002\u0010K\u001a\u00020=2\b\b\u0002\u0010L\u001a\u00020=2\b\b\u0002\u0010M\u001a\u00020=2\b\b\u0002\u0010N\u001a\u00020=2\b\b\u0002\u0010O\u001a\u00020=2\b\b\u0002\u0010P\u001a\u00020=2\b\b\u0002\u0010Q\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ:\u0010T\u001a\u0002042\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u00062\b\b\u0002\u0010X\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bY\u0010ZJä\u0001\u0010[\u001a\u00020 2\b\b\u0002\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020=2\b\b\u0002\u0010?\u001a\u00020=2\b\b\u0002\u0010@\u001a\u00020=2\b\b\u0002\u0010A\u001a\u00020=2\b\b\u0002\u0010\\\u001a\u00020=2\b\b\u0002\u0010]\u001a\u00020=2\b\b\u0002\u0010^\u001a\u00020=2\b\b\u0002\u0010_\u001a\u00020=2\b\b\u0002\u0010F\u001a\u00020=2\b\b\u0002\u0010G\u001a\u00020=2\b\b\u0002\u0010H\u001a\u00020=2\b\b\u0002\u0010I\u001a\u00020=2\b\b\u0002\u0010J\u001a\u00020=2\b\b\u0002\u0010K\u001a\u00020=2\b\b\u0002\u0010L\u001a\u00020=2\b\b\u0002\u0010M\u001a\u00020=2\b\b\u0002\u0010N\u001a\u00020=2\b\b\u0002\u0010O\u001a\u00020=2\b\b\u0002\u0010P\u001a\u00020=2\b\b\u0002\u0010Q\u001a\u00020=H\u0007ø\u0001\u0000¢\u0006\u0004\b`\u0010SJ:\u0010a\u001a\u0002042\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u00062\b\b\u0002\u0010X\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bb\u0010ZJ:\u0010c\u001a\u0002042\b\b\u0002\u0010U\u001a\u00020\u00062\b\b\u0002\u0010V\u001a\u00020\u00062\b\b\u0002\u0010W\u001a\u00020\u00062\b\b\u0002\u0010X\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bd\u0010ZJJ\u0010e\u001a\u00020f*\u00020f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010g\u001a\u00020\u00062\b\b\u0002\u0010h\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\bi\u0010jR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\f\u0010\bR\u0019\u0010\r\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0019\u0010\u0015\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0016\u0010\bR\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006k"}, d2 = {"Landroidx/compose/material/TextFieldDefaults;", "", "()V", "BackgroundOpacity", "", "FocusedBorderThickness", "Landroidx/compose/ui/unit/Dp;", "getFocusedBorderThickness-D9Ej5fM", "()F", "F", "IconOpacity", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "OutlinedTextFieldShape", "Landroidx/compose/ui/graphics/Shape;", "getOutlinedTextFieldShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "TextFieldShape", "getTextFieldShape", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "UnfocusedIndicatorLineOpacity", "BorderBox", "", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material/TextFieldColors;", "shape", "focusedBorderThickness", "unfocusedBorderThickness", "BorderBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "OutlinedTextFieldDecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", OutlinedTextFieldKt.BorderId, "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "TextFieldDecorationBox", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "outlinedTextFieldColors", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", "backgroundColor", "cursorColor", "errorCursorColor", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "outlinedTextFieldColors-dx8h9Zs", "(JJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "outlinedTextFieldPadding", "start", "top", "end", "bottom", "outlinedTextFieldPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "textFieldColors", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "textFieldColors-dx8h9Zs", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "indicatorLine", "Landroidx/compose/ui/Modifier;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final float BackgroundOpacity = 0.12f;
    public static final float IconOpacity = 0.54f;
    public static final float UnfocusedIndicatorLineOpacity = 0.42f;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m7016constructorimpl(56);
    private static final float MinWidth = Dp.m7016constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m7016constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m7016constructorimpl(2);

    private TextFieldDefaults() {
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m2068getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m2069getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final Shape getTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1117199624, "C233@8406L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1117199624, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-TextFieldShape> (TextFieldDefaults.kt:233)");
        }
        CornerBasedShape cornerBasedShapeCopy$default = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall(), null, null, CornerSizeKt.getZeroCornerSize(), CornerSizeKt.getZeroCornerSize(), 3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cornerBasedShapeCopy$default;
    }

    public final Shape getOutlinedTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1899109048, "C242@8708L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1899109048, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-OutlinedTextFieldShape> (TextFieldDefaults.kt:242)");
        }
        CornerBasedShape small = MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return small;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2070getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m2067getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m2062indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedBorderThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedBorderThickness;
        }
        return textFieldDefaults.m2071indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m2071indicatorLinegv0btCI(Modifier $this$indicatorLine_u2dgv0btCI, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float focusedIndicatorLineThickness, final float unfocusedIndicatorLineThickness) {
        return ComposedModifierKt.composed($this$indicatorLine_u2dgv0btCI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
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
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                $composer.startReplaceGroup(1398930845);
                ComposerKt.sourceInformation($composer, "C299@11110L217:TextFieldDefaults.kt#jmzs0o");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1398930845, $changed, -1, "androidx.compose.material.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:299)");
                }
                State stroke = TextFieldDefaultsKt.m2078animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, $composer, 0);
                Modifier modifierDrawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, (BorderStroke) stroke.getValue());
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return modifierDrawIndicatorLine;
            }
        });
    }

    /* JADX INFO: renamed from: BorderBox-nbWgWpA, reason: not valid java name */
    public final void m2066BorderBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, final int $changed, final int i) {
        Shape shape2;
        float focusedBorderThickness2;
        float f;
        int $dirty;
        Shape shape3;
        float focusedBorderThickness3;
        float unfocusedBorderThickness2;
        float unfocusedBorderThickness3;
        float unfocusedBorderThickness4;
        Shape shape4;
        Composer $composer2 = $composer.startRestartGroup(943754022);
        ComposerKt.sourceInformation($composer2, "C(BorderBox)P(1,4,3!1,5,2:c#ui.unit.Dp,6:c#ui.unit.Dp)332@12464L22,336@12648L203,344@12860L47:TextFieldDefaults.kt#jmzs0o");
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
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer2.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i2;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i2;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                focusedBorderThickness2 = focusedBorderThickness;
                int i3 = $composer2.changed(focusedBorderThickness2) ? 131072 : 65536;
                $dirty2 |= i3;
            } else {
                focusedBorderThickness2 = focusedBorderThickness;
            }
            $dirty2 |= i3;
        } else {
            focusedBorderThickness2 = focusedBorderThickness;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = unfocusedBorderThickness;
                int i4 = $composer2.changed(f) ? 1048576 : 524288;
                $dirty2 |= i4;
            } else {
                f = unfocusedBorderThickness;
            }
            $dirty2 |= i4;
        } else {
            f = unfocusedBorderThickness;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer2.changed(this) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty2) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            shape4 = shape2;
            unfocusedBorderThickness4 = focusedBorderThickness2;
            unfocusedBorderThickness3 = f;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer2, ($dirty2 >> 21) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    focusedBorderThickness2 = FocusedBorderThickness;
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = UnfocusedBorderThickness;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
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
                    $dirty = $dirty2 & (-3670017);
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                } else {
                    $dirty = $dirty2;
                    shape3 = shape2;
                    focusedBorderThickness3 = focusedBorderThickness2;
                    unfocusedBorderThickness2 = f;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(943754022, $dirty, -1, "androidx.compose.material.TextFieldDefaults.BorderBox (TextFieldDefaults.kt:335)");
            }
            Shape shape5 = shape3;
            State borderStroke = TextFieldDefaultsKt.m2078animateBorderStrokeAsStateNuRrP5Q(enabled, isError, interactionSource, colors, focusedBorderThickness3, unfocusedBorderThickness2, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (458752 & ($dirty >> 3)));
            BoxKt.Box(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke.getValue(), shape5), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedBorderThickness3 = unfocusedBorderThickness2;
            unfocusedBorderThickness4 = focusedBorderThickness3;
            shape4 = shape5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Shape shape6 = shape4;
            final float f2 = unfocusedBorderThickness4;
            final float f3 = unfocusedBorderThickness3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$BorderBox$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    this.$tmp0_rcvr.m2066BorderBoxnbWgWpA(enabled, isError, interactionSource, colors, shape6, f2, f3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2064textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getFirstBaselineOffset();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldBottomPadding();
        }
        return textFieldDefaults.m2075textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2075textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m1030PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2065textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m2076textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2076textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m1030PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m2063outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m2073outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2073outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m1030PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: textFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m2074textFieldColorsdx8h9Zs(long textColor, long disabledTextColor, long backgroundColor, long cursorColor, long errorCursorColor, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long leadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long trailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long textColor2;
        long disabledTextColor2;
        long backgroundColor2;
        long focusedIndicatorColor2;
        long unfocusedIndicatorColor2;
        long disabledIndicatorColor2;
        long leadingIconColor2;
        long disabledLeadingIconColor2;
        long trailingIconColor2;
        long disabledTrailingIconColor2;
        long focusedLabelColor2;
        long unfocusedLabelColor2;
        long disabledLabelColor2;
        long placeholderColor2;
        long disabledPlaceholderColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 231892599, "C(textFieldColors)P(17:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,13:c#ui.graphics.Color,19:c#ui.graphics.Color,2:c#ui.graphics.Color,9:c#ui.graphics.Color,15:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,18:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,20:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,16:c#ui.graphics.Color,5:c#ui.graphics.Color)395@14784L7,395@14815L7,396@14888L8,397@14946L6,398@15039L6,399@15103L6,401@15182L6,401@15223L4,403@15297L6,404@15449L8,405@15511L6,407@15585L6,408@15714L8,411@15843L6,412@15974L8,413@16039L6,415@16114L6,415@16155L4,416@16213L6,416@16248L6,417@16331L8,418@16389L6,419@16451L6,419@16486L6,420@16572L8:TextFieldDefaults.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM4544unboximpl = ((Color) objConsume).m4544unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            textColor2 = Color.m4532copywmQWz5c(jM4544unboximpl, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4544unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m4540getRedimpl(jM4544unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4544unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4544unboximpl) : 0.0f);
        } else {
            textColor2 = textColor;
        }
        if ((i & 2) != 0) {
            long j = textColor2;
            disabledTextColor2 = Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        if ((i & 4) != 0) {
            long jM1845getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            backgroundColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU) : 0.0f);
        } else {
            backgroundColor2 = backgroundColor;
        }
        long cursorColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1846getPrimary0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorCursorColor;
        if ((i & 32) != 0) {
            long jM1846getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1846getPrimary0d7_KjU();
            focusedIndicatorColor2 = Color.m4532copywmQWz5c(jM1846getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1846getPrimary0d7_KjU) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1846getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1846getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1846getPrimary0d7_KjU) : 0.0f);
        } else {
            focusedIndicatorColor2 = focusedIndicatorColor;
        }
        if ((i & 64) != 0) {
            long jM1845getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            unfocusedIndicatorColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU2) : 0.42f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU2) : 0.0f);
        } else {
            unfocusedIndicatorColor2 = unfocusedIndicatorColor;
        }
        if ((i & 128) != 0) {
            long j2 = unfocusedIndicatorColor2;
            disabledIndicatorColor2 = Color.m4532copywmQWz5c(j2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j2) : 0.0f);
        } else {
            disabledIndicatorColor2 = disabledIndicatorColor;
        }
        long errorIndicatorColor2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorIndicatorColor;
        if ((i & 512) != 0) {
            long jM1845getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            leadingIconColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU3) : 0.0f);
        } else {
            leadingIconColor2 = leadingIconColor;
        }
        if ((i & 1024) != 0) {
            long j3 = leadingIconColor2;
            disabledLeadingIconColor2 = Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 2048) != 0 ? leadingIconColor2 : errorLeadingIconColor;
        if ((i & 4096) != 0) {
            long jM1845getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            trailingIconColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU4) : 0.54f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU4) : 0.0f);
        } else {
            trailingIconColor2 = trailingIconColor;
        }
        if ((i & 8192) != 0) {
            long j4 = trailingIconColor2;
            disabledTrailingIconColor2 = Color.m4532copywmQWz5c(j4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j4) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (i & 16384) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorTrailingIconColor;
        if ((32768 & i) != 0) {
            long jM1846getPrimary0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1846getPrimary0d7_KjU();
            focusedLabelColor2 = Color.m4532copywmQWz5c(jM1846getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1846getPrimary0d7_KjU2) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1846getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1846getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1846getPrimary0d7_KjU2) : 0.0f);
        } else {
            focusedLabelColor2 = focusedLabelColor;
        }
        if ((65536 & i) != 0) {
            long jM1845getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            unfocusedLabelColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU5) : 0.0f);
        } else {
            unfocusedLabelColor2 = unfocusedLabelColor;
        }
        if ((131072 & i) != 0) {
            long j5 = unfocusedLabelColor2;
            disabledLabelColor2 = Color.m4532copywmQWz5c(j5, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j5) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j5) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (262144 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorLabelColor;
        if ((524288 & i) != 0) {
            long jM1845getOnSurface0d7_KjU6 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            placeholderColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU6) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU6) : 0.0f);
        } else {
            placeholderColor2 = placeholderColor;
        }
        if ((i & 1048576) != 0) {
            long j6 = placeholderColor2;
            disabledPlaceholderColor2 = Color.m4532copywmQWz5c(j6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j6) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(231892599, $changed, $changed1, "androidx.compose.material.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:422)");
        }
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(textColor2, disabledTextColor2, cursorColor2, errorCursorColor2, focusedIndicatorColor2, unfocusedIndicatorColor2, errorIndicatorColor2, disabledIndicatorColor2, leadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, trailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, backgroundColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, disabledPlaceholderColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldColors;
    }

    /* JADX INFO: renamed from: outlinedTextFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m2072outlinedTextFieldColorsdx8h9Zs(long textColor, long disabledTextColor, long backgroundColor, long cursorColor, long errorCursorColor, long focusedBorderColor, long unfocusedBorderColor, long disabledBorderColor, long errorBorderColor, long leadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long trailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long textColor2;
        long disabledTextColor2;
        long focusedBorderColor2;
        long unfocusedBorderColor2;
        long disabledBorderColor2;
        long leadingIconColor2;
        long disabledLeadingIconColor2;
        long trailingIconColor2;
        long disabledTrailingIconColor2;
        long focusedLabelColor2;
        long unfocusedLabelColor2;
        long disabledLabelColor2;
        long placeholderColor2;
        long disabledPlaceholderColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 1762667317, "C(outlinedTextFieldColors)P(17:c#ui.graphics.Color,6:c#ui.graphics.Color,0:c#ui.graphics.Color,1:c#ui.graphics.Color,9:c#ui.graphics.Color,13:c#ui.graphics.Color,19:c#ui.graphics.Color,2:c#ui.graphics.Color,8:c#ui.graphics.Color,15:c#ui.graphics.Color,4:c#ui.graphics.Color,11:c#ui.graphics.Color,18:c#ui.graphics.Color,7:c#ui.graphics.Color,12:c#ui.graphics.Color,14:c#ui.graphics.Color,20:c#ui.graphics.Color,3:c#ui.graphics.Color,10:c#ui.graphics.Color,16:c#ui.graphics.Color,5:c#ui.graphics.Color)453@18100L7,453@18131L7,454@18204L8,456@18310L6,457@18374L6,459@18450L6,459@18491L4,461@18562L6,461@18605L8,462@18700L8,463@18759L6,465@18833L6,466@18962L8,469@19091L6,470@19222L8,471@19287L6,473@19362L6,473@19403L4,474@19461L6,474@19496L6,475@19579L8,476@19637L6,477@19699L6,477@19734L6,478@19820L8:TextFieldDefaults.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM4544unboximpl = ((Color) objConsume).m4544unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            textColor2 = Color.m4532copywmQWz5c(jM4544unboximpl, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM4544unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m4540getRedimpl(jM4544unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM4544unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM4544unboximpl) : 0.0f);
        } else {
            textColor2 = textColor;
        }
        if ((i & 2) != 0) {
            long j = textColor2;
            disabledTextColor2 = Color.m4532copywmQWz5c(j, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long backgroundColor2 = (i & 4) != 0 ? Color.INSTANCE.m4569getTransparent0d7_KjU() : backgroundColor;
        long cursorColor2 = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1846getPrimary0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorCursorColor;
        if ((i & 32) != 0) {
            long jM1846getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1846getPrimary0d7_KjU();
            focusedBorderColor2 = Color.m4532copywmQWz5c(jM1846getPrimary0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1846getPrimary0d7_KjU) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1846getPrimary0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1846getPrimary0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1846getPrimary0d7_KjU) : 0.0f);
        } else {
            focusedBorderColor2 = focusedBorderColor;
        }
        if ((i & 64) != 0) {
            long jM1845getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            unfocusedBorderColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU) : 0.0f);
        } else {
            unfocusedBorderColor2 = unfocusedBorderColor;
        }
        if ((i & 128) != 0) {
            long j2 = unfocusedBorderColor2;
            disabledBorderColor2 = Color.m4532copywmQWz5c(j2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j2) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j2) : 0.0f);
        } else {
            disabledBorderColor2 = disabledBorderColor;
        }
        long errorBorderColor2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorBorderColor;
        if ((i & 512) != 0) {
            long jM1845getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            leadingIconColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU2) : 0.54f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU2) : 0.0f);
        } else {
            leadingIconColor2 = leadingIconColor;
        }
        if ((i & 1024) != 0) {
            long j3 = leadingIconColor2;
            disabledLeadingIconColor2 = Color.m4532copywmQWz5c(j3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j3) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j3) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long errorLeadingIconColor2 = (i & 2048) != 0 ? leadingIconColor2 : errorLeadingIconColor;
        if ((i & 4096) != 0) {
            long jM1845getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            trailingIconColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU3) : 0.0f);
        } else {
            trailingIconColor2 = trailingIconColor;
        }
        if ((i & 8192) != 0) {
            long j4 = trailingIconColor2;
            disabledTrailingIconColor2 = Color.m4532copywmQWz5c(j4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j4) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j4) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long errorTrailingIconColor2 = (i & 16384) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorTrailingIconColor;
        if ((32768 & i) != 0) {
            long jM1846getPrimary0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1846getPrimary0d7_KjU();
            focusedLabelColor2 = Color.m4532copywmQWz5c(jM1846getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1846getPrimary0d7_KjU2) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1846getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1846getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1846getPrimary0d7_KjU2) : 0.0f);
        } else {
            focusedLabelColor2 = focusedLabelColor;
        }
        if ((65536 & i) != 0) {
            long jM1845getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            unfocusedLabelColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU4) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU4) : 0.0f);
        } else {
            unfocusedLabelColor2 = unfocusedLabelColor;
        }
        if ((131072 & i) != 0) {
            long j5 = unfocusedLabelColor2;
            disabledLabelColor2 = Color.m4532copywmQWz5c(j5, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j5) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j5) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j5) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j5) : 0.0f);
        } else {
            disabledLabelColor2 = disabledLabelColor;
        }
        long errorLabelColor2 = (262144 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1840getError0d7_KjU() : errorLabelColor;
        if ((524288 & i) != 0) {
            long jM1845getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1845getOnSurface0d7_KjU();
            placeholderColor2 = Color.m4532copywmQWz5c(jM1845getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m4536getAlphaimpl(jM1845getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(jM1845getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(jM1845getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(jM1845getOnSurface0d7_KjU5) : 0.0f);
        } else {
            placeholderColor2 = placeholderColor;
        }
        if ((i & 1048576) != 0) {
            long j6 = placeholderColor2;
            disabledPlaceholderColor2 = Color.m4532copywmQWz5c(j6, (14 & 1) != 0 ? Color.m4536getAlphaimpl(j6) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m4540getRedimpl(j6) : 0.0f, (14 & 4) != 0 ? Color.m4539getGreenimpl(j6) : 0.0f, (14 & 8) != 0 ? Color.m4537getBlueimpl(j6) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1762667317, $changed, $changed1, "androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:480)");
        }
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(textColor2, disabledTextColor2, cursorColor2, errorCursorColor2, focusedBorderColor2, unfocusedBorderColor2, errorBorderColor2, disabledBorderColor2, leadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, trailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, backgroundColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, placeholderColor2, disabledPlaceholderColor2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldColors;
    }

    public final void TextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Shape shape2;
        int $dirty1;
        int $dirty;
        Composer $composer2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        int $dirty2;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Composer $composer3 = $composer.startRestartGroup(-1391531252);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)P(12,3,2,10,13,4,5,6,8,7,11,9)573@25758L14,574@25808L17,582@26042L624:TextFieldDefaults.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int i2 = 128;
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 256 : 128;
        }
        int i3 = 1024;
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changed(isError) ? 1048576 : 524288;
        }
        int i5 = i & 128;
        if (i5 != 0) {
            $dirty3 |= 12582912;
            function7 = function3;
        } else if (($changed & 12582912) == 0) {
            function7 = function3;
            $dirty3 |= $composer3.changedInstance(function7) ? 8388608 : 4194304;
        } else {
            function7 = function3;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty3 |= $composer3.changedInstance(function5) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function6) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty4 |= ((i & 2048) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i & 4096) == 0 && $composer3.changed(colors)) {
                i2 = 256;
            }
            $dirty4 |= i2;
        }
        if (($changed1 & 3072) == 0) {
            if ((i & 8192) == 0 && $composer3.changed(contentPadding)) {
                i3 = 2048;
            }
            $dirty4 |= i3;
        }
        if ((i & 16384) != 0) {
            $dirty4 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty4 |= $composer3.changed(this) ? 16384 : 8192;
        }
        int $dirty5 = $dirty3;
        if ((306783379 & $dirty3) == 306783378 && ($dirty4 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            isError2 = isError;
            function9 = function4;
            function10 = function5;
            function11 = function6;
            shape2 = shape;
            colors3 = colors;
            contentPadding3 = contentPadding;
            $dirty2 = $dirty5;
            function8 = function7;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                isError2 = i4 != 0 ? false : isError;
                function8 = i5 != 0 ? null : function7;
                function9 = i6 != 0 ? null : function4;
                function10 = i7 != 0 ? null : function5;
                function11 = i8 != 0 ? null : function6;
                if ((i & 2048) != 0) {
                    shape2 = getTextFieldShape($composer3, ($dirty4 >> 12) & 14);
                    $dirty1 = $dirty4 & (-113);
                } else {
                    shape2 = shape;
                    $dirty1 = $dirty4;
                }
                if ((i & 4096) != 0) {
                    $dirty = $dirty5;
                    $composer2 = $composer3;
                    colors2 = m2074textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, ($dirty1 >> 9) & 112, 2097151);
                    $dirty1 &= -897;
                } else {
                    $dirty = $dirty5;
                    $composer2 = $composer3;
                    colors2 = colors;
                }
                if ((i & 8192) != 0) {
                    contentPadding2 = function8 == null ? m2065textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : m2064textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty4 = $dirty1 & (-7169);
                } else {
                    contentPadding2 = contentPadding;
                    $dirty4 = $dirty1;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty4 &= -113;
                }
                if ((i & 4096) != 0) {
                    $dirty4 &= -897;
                }
                if ((i & 8192) != 0) {
                    isError2 = isError;
                    function9 = function4;
                    function10 = function5;
                    function11 = function6;
                    shape2 = shape;
                    colors2 = colors;
                    $dirty = $dirty5;
                    $dirty4 &= -7169;
                    function8 = function7;
                    $composer2 = $composer3;
                    contentPadding2 = contentPadding;
                } else {
                    isError2 = isError;
                    function9 = function4;
                    function10 = function5;
                    function11 = function6;
                    shape2 = shape;
                    colors2 = colors;
                    contentPadding2 = contentPadding;
                    $dirty = $dirty5;
                    function8 = function7;
                    $composer2 = $composer3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty2 = $dirty;
                ComposerKt.traceEventStart(-1391531252, $dirty2, $dirty4, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:581)");
            } else {
                $dirty2 = $dirty;
            }
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, function2, visualTransformation, function8, function9, function10, function11, singleLine, enabled, isError2, interactionSource, contentPadding2, shape2, colors2, null, $composer2, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896) | (($dirty2 >> 3) & 7168) | (($dirty2 >> 9) & 57344) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty4 << 21) & 29360128) | (($dirty2 << 15) & 234881024) | (($dirty2 << 21) & 1879048192), (($dirty2 >> 18) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty2 >> 12) & 112) | (($dirty4 >> 3) & 896) | (($dirty4 << 6) & 7168) | (($dirty4 << 6) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            contentPadding3 = contentPadding2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z = isError2;
            final Function2<? super Composer, ? super Integer, Unit> function12 = function8;
            final Function2<? super Composer, ? super Integer, Unit> function13 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function10;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function11;
            final Shape shape3 = shape2;
            final TextFieldColors textFieldColors = colors3;
            final PaddingValues paddingValues = contentPadding3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox.1
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

                public final void invoke(Composer composer, int i9) {
                    TextFieldDefaults.this.TextFieldDecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, z, function12, function13, function14, function15, shape3, textFieldColors, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public final void OutlinedTextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function8;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Shape shape2;
        int $dirty1;
        int $dirty;
        Composer $composer2;
        TextFieldColors colors2;
        PaddingValues contentPadding2;
        Composer $composer3;
        ComposableLambda composableLambdaRememberComposableLambda;
        int $dirty2;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Composer $composer4 = $composer.startRestartGroup(-920823490);
        ComposerKt.sourceInformation($composer4, "C(OutlinedTextFieldDecorationBox)P(13,4,3,11,14,5,6,7,9,8,12,10,1,2)669@31256L22,670@31314L25,672@31450L85,676@31552L628:TextFieldDefaults.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer4.changedInstance(function2) ? 32 : 16;
        }
        int i2 = 128;
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer4.changed(enabled) ? 256 : 128;
        }
        int i3 = 1024;
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer4.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty3 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty3 |= $composer4.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer4.changed(interactionSource) ? 131072 : 65536;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty3 |= $composer4.changed(isError) ? 1048576 : 524288;
        }
        int i5 = i & 128;
        if (i5 != 0) {
            $dirty3 |= 12582912;
            function8 = function3;
        } else if (($changed & 12582912) == 0) {
            function8 = function3;
            $dirty3 |= $composer4.changedInstance(function8) ? 8388608 : 4194304;
        } else {
            function8 = function3;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty3 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty3 |= $composer4.changedInstance(function5) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty4 |= $composer4.changedInstance(function6) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty4 |= ((i & 2048) == 0 && $composer4.changed(shape)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i & 4096) == 0 && $composer4.changed(colors)) {
                i2 = 256;
            }
            $dirty4 |= i2;
        }
        if (($changed1 & 3072) == 0) {
            if ((i & 8192) == 0 && $composer4.changed(contentPadding)) {
                i3 = 2048;
            }
            $dirty4 |= i3;
        }
        int i9 = i & 16384;
        if (i9 != 0) {
            $dirty4 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty4 |= $composer4.changedInstance(function7) ? 16384 : 8192;
        }
        if ((i & 32768) != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer4.changed(this) ? 131072 : 65536;
        }
        int $dirty5 = $dirty3;
        if (($dirty3 & 306783379) == 306783378 && (74899 & $dirty4) == 74898 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            isError2 = isError;
            function10 = function4;
            function11 = function5;
            function12 = function6;
            shape2 = shape;
            colors3 = colors;
            contentPadding3 = contentPadding;
            function13 = function7;
            $dirty2 = $dirty5;
            function9 = function8;
            $composer3 = $composer4;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                isError2 = i4 != 0 ? false : isError;
                function9 = i5 != 0 ? null : function8;
                function10 = i6 != 0 ? null : function4;
                function11 = i7 != 0 ? null : function5;
                function12 = i8 != 0 ? null : function6;
                if ((i & 2048) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer4, ($dirty4 >> 15) & 14);
                    $dirty1 = $dirty4 & (-113);
                } else {
                    shape2 = shape;
                    $dirty1 = $dirty4;
                }
                if ((i & 4096) != 0) {
                    $dirty = $dirty5;
                    $composer2 = $composer4;
                    colors2 = m2072outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, ($dirty1 >> 12) & 112, 2097151);
                    $dirty1 &= -897;
                } else {
                    $dirty = $dirty5;
                    $composer2 = $composer4;
                    colors2 = colors;
                }
                if ((i & 8192) != 0) {
                    contentPadding2 = m2063outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty1 &= -7169;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i9 != 0) {
                    final boolean z = isError2;
                    final TextFieldColors textFieldColors = colors2;
                    final Shape shape3 = shape2;
                    $composer3 = $composer2;
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(2147252344, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed2) {
                            ComposerKt.sourceInformation($composer5, "C673@31464L61:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 3) == 2 && $composer5.getSkipping()) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2147252344, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:673)");
                            }
                            TextFieldDefaults.INSTANCE.m2066BorderBoxnbWgWpA(enabled, z, interactionSource, textFieldColors, shape3, 0.0f, 0.0f, $composer5, 12582912, 96);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                } else {
                    $composer3 = $composer2;
                    composableLambdaRememberComposableLambda = function7;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty4 &= -113;
                }
                if ((i & 4096) != 0) {
                    $dirty4 &= -897;
                }
                if ((i & 8192) != 0) {
                    int i10 = $dirty4 & (-7169);
                    isError2 = isError;
                    function10 = function4;
                    function11 = function5;
                    function12 = function6;
                    shape2 = shape;
                    colors2 = colors;
                    composableLambdaRememberComposableLambda = function7;
                    $dirty = $dirty5;
                    $dirty1 = i10;
                    function9 = function8;
                    $composer3 = $composer4;
                    contentPadding2 = contentPadding;
                } else {
                    isError2 = isError;
                    function10 = function4;
                    function11 = function5;
                    function12 = function6;
                    shape2 = shape;
                    colors2 = colors;
                    contentPadding2 = contentPadding;
                    $dirty = $dirty5;
                    $dirty1 = $dirty4;
                    function9 = function8;
                    $composer3 = $composer4;
                    composableLambdaRememberComposableLambda = function7;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty2 = $dirty;
                ComposerKt.traceEventStart(-920823490, $dirty2, $dirty1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:675)");
            } else {
                $dirty2 = $dirty;
            }
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, function2, visualTransformation, function9, function10, function11, function12, singleLine, enabled, isError2, interactionSource, contentPadding2, shape2, colors2, composableLambdaRememberComposableLambda, $composer3, (($dirty2 << 3) & 112) | 6 | (($dirty2 << 3) & 896) | (($dirty2 >> 3) & 7168) | (($dirty2 >> 9) & 57344) | (($dirty2 >> 9) & 458752) | (3670016 & ($dirty2 >> 9)) | (($dirty1 << 21) & 29360128) | (($dirty2 << 15) & 234881024) | (($dirty2 << 21) & 1879048192), (($dirty2 >> 18) & 14) | (($dirty2 >> 12) & 112) | (($dirty1 >> 3) & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (458752 & ($dirty1 << 3)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            contentPadding3 = contentPadding2;
            function13 = composableLambdaRememberComposableLambda;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z2 = isError2;
            final Function2<? super Composer, ? super Integer, Unit> function14 = function9;
            final Function2<? super Composer, ? super Integer, Unit> function15 = function10;
            final Function2<? super Composer, ? super Integer, Unit> function16 = function11;
            final Function2<? super Composer, ? super Integer, Unit> function17 = function12;
            final Shape shape4 = shape2;
            final TextFieldColors textFieldColors2 = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2<? super Composer, ? super Integer, Unit> function18 = function13;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.2
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

                public final void invoke(Composer composer, int i11) {
                    TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, function2, enabled, singleLine, visualTransformation, interactionSource, z2, function14, function15, function16, function17, shape4, textFieldColors2, paddingValues, function18, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `shape` parameter.")
    public final /* synthetic */ void TextFieldDecorationBox(final String value, final Function2 innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, TextFieldColors colors, PaddingValues contentPadding, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        boolean isError2;
        Function2 label2;
        Function2 placeholder2;
        Function2 leadingIcon2;
        Function2 trailingIcon2;
        int $dirty;
        Composer $composer2;
        TextFieldColors colors2;
        int $dirty1;
        PaddingValues contentPadding2;
        TextFieldColors colors3;
        int $dirty2;
        int $dirty3;
        Composer $composer3;
        Composer $composer4 = $composer.startRestartGroup(1171040065);
        ComposerKt.sourceInformation($composer4, "C(TextFieldDecorationBox)P(11,3,2,9,12,4,5,6,8,7,10)714@32930L17,733@33577L14,721@33156L508:TextFieldDefaults.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer4.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer4.changedInstance(innerTextField) ? 32 : 16;
        }
        int i2 = 128;
        if ((i & 4) != 0) {
            $dirty4 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty4 |= $composer4.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty4 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty4 |= $composer4.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty4 |= $composer4.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer4.changed(interactionSource) ? 131072 : 65536;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer4.changed(isError) ? 1048576 : 524288;
        }
        int i4 = i & 128;
        if (i4 != 0) {
            $dirty4 |= 12582912;
            function2 = label;
        } else if (($changed & 12582912) == 0) {
            function2 = label;
            $dirty4 |= $composer4.changedInstance(function2) ? 8388608 : 4194304;
        } else {
            function2 = label;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty4 |= $composer4.changedInstance(placeholder) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty4 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty4 |= $composer4.changedInstance(leadingIcon) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i7 = i & 1024;
        if (i7 != 0) {
            $dirty5 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty5 |= $composer4.changedInstance(trailingIcon) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty5 |= ((i & 2048) == 0 && $composer4.changed(colors)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i & 4096) == 0 && $composer4.changed(contentPadding)) {
                i2 = 256;
            }
            $dirty5 |= i2;
        }
        if ((i & 8192) != 0) {
            $dirty5 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty5 |= $composer4.changed(this) ? 2048 : 1024;
        }
        int $dirty6 = $dirty5;
        if ((306783379 & $dirty4) == 306783378 && ($dirty6 & 1171) == 1170 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            isError2 = isError;
            placeholder2 = placeholder;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            colors3 = colors;
            contentPadding2 = contentPadding;
            label2 = function2;
            $composer3 = $composer4;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                isError2 = i3 != 0 ? false : isError;
                label2 = i4 != 0 ? null : function2;
                placeholder2 = i5 != 0 ? null : placeholder;
                leadingIcon2 = i6 != 0 ? null : leadingIcon;
                trailingIcon2 = i7 != 0 ? null : trailingIcon;
                if ((i & 2048) != 0) {
                    $composer2 = $composer4;
                    $dirty = $dirty4;
                    colors2 = m2074textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, ($dirty6 >> 6) & 112, 2097151);
                    $dirty1 = $dirty6 & (-113);
                } else {
                    $dirty = $dirty4;
                    $composer2 = $composer4;
                    colors2 = colors;
                    $dirty1 = $dirty6;
                }
                if ((i & 4096) != 0) {
                    contentPadding2 = label2 == null ? m2065textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : m2064textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 = $dirty1 & (-897);
                    colors3 = colors2;
                } else {
                    contentPadding2 = contentPadding;
                    colors3 = colors2;
                    $dirty2 = $dirty1;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty6 &= -113;
                }
                if ((i & 4096) != 0) {
                    isError2 = isError;
                    placeholder2 = placeholder;
                    leadingIcon2 = leadingIcon;
                    trailingIcon2 = trailingIcon;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    $dirty = $dirty4;
                    label2 = function2;
                    $composer2 = $composer4;
                    $dirty2 = $dirty6 & (-897);
                } else {
                    isError2 = isError;
                    placeholder2 = placeholder;
                    leadingIcon2 = leadingIcon;
                    trailingIcon2 = trailingIcon;
                    colors3 = colors;
                    contentPadding2 = contentPadding;
                    $dirty = $dirty4;
                    label2 = function2;
                    $composer2 = $composer4;
                    $dirty2 = $dirty6;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty3 = $dirty;
                ComposerKt.traceEventStart(1171040065, $dirty3, $dirty2, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:721)");
            } else {
                $dirty3 = $dirty;
            }
            Composer $composer5 = $composer2;
            $composer3 = $composer5;
            TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError2, label2, placeholder2, leadingIcon2, trailingIcon2, getTextFieldShape($composer5, ($dirty2 >> 9) & 14), colors3, contentPadding2, $composer3, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3) | (29360128 & $dirty3) | (234881024 & $dirty3) | (1879048192 & $dirty3), ($dirty2 & 14) | (($dirty2 << 3) & 896) | (($dirty2 << 3) & 7168) | (57344 & ($dirty2 << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z = isError2;
            final Function2 function3 = label2;
            final Function2 function4 = placeholder2;
            final Function2 function5 = leadingIcon2;
            final Function2 function6 = trailingIcon2;
            final TextFieldColors textFieldColors = colors3;
            final PaddingValues paddingValues = contentPadding2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox.2
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

                public final void invoke(Composer composer, int i8) {
                    TextFieldDefaults.this.TextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z, function3, function4, function5, function6, textFieldColors, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `shape` parameter.")
    public final /* synthetic */ void OutlinedTextFieldDecorationBox(final String value, final Function2 innerTextField, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2 label, Function2 placeholder, Function2 leadingIcon, Function2 trailingIcon, TextFieldColors colors, PaddingValues contentPadding, Function2 border, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Function2 label2;
        Function2 placeholder2;
        Function2 leadingIcon2;
        Function2 trailingIcon2;
        int $dirty;
        Composer $composer2;
        final TextFieldColors colors2;
        int $dirty1;
        PaddingValues contentPadding2;
        Composer $composer3;
        Function2 border2;
        PaddingValues contentPadding3;
        boolean isError2;
        TextFieldColors colors3;
        int $dirty2;
        int $dirty3;
        Composer $composer4;
        Composer $composer5 = $composer.startRestartGroup(-1280721485);
        ComposerKt.sourceInformation($composer5, "C(OutlinedTextFieldDecorationBox)P(12,4,3,10,13,5,6,7,9,8,11,1,2)756@34416L25,758@34552L78,773@35068L22,761@34639L549:TextFieldDefaults.kt#jmzs0o");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer5.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer5.changedInstance(innerTextField) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty4 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty4 |= $composer5.changed(enabled) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty4 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty4 |= $composer5.changed(singleLine) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty4 |= $composer5.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer5.changed(interactionSource) ? 131072 : 65536;
        }
        int i2 = i & 64;
        if (i2 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer5.changed(isError) ? 1048576 : 524288;
        }
        int i3 = i & 128;
        if (i3 != 0) {
            $dirty4 |= 12582912;
            function2 = label;
        } else if (($changed & 12582912) == 0) {
            function2 = label;
            $dirty4 |= $composer5.changedInstance(function2) ? 8388608 : 4194304;
        } else {
            function2 = label;
        }
        int i4 = i & 256;
        if (i4 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty4 |= $composer5.changedInstance(placeholder) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i5 = i & 512;
        if (i5 != 0) {
            $dirty4 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty4 |= $composer5.changedInstance(leadingIcon) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        int i6 = i & 1024;
        if (i6 != 0) {
            $dirty5 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty5 |= $composer5.changedInstance(trailingIcon) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty5 |= ((i & 2048) == 0 && $composer5.changed(colors)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty5 |= ((i & 4096) == 0 && $composer5.changed(contentPadding)) ? 256 : 128;
        }
        int i7 = i & 8192;
        if (i7 != 0) {
            $dirty5 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty5 |= $composer5.changedInstance(border) ? 2048 : 1024;
        }
        if ((i & 16384) != 0) {
            $dirty5 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty5 |= $composer5.changed(this) ? 16384 : 8192;
        }
        int $dirty6 = $dirty5;
        if ((306783379 & $dirty4) == 306783378 && ($dirty6 & 9363) == 9362 && $composer5.getSkipping()) {
            $composer5.skipToGroupEnd();
            isError2 = isError;
            placeholder2 = placeholder;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            colors3 = colors;
            contentPadding3 = contentPadding;
            border2 = border;
            label2 = function2;
            $composer4 = $composer5;
        } else {
            $composer5.startDefaults();
            if (($changed & 1) == 0 || $composer5.getDefaultsInvalid()) {
                boolean isError3 = i2 != 0 ? false : isError;
                label2 = i3 != 0 ? null : function2;
                placeholder2 = i4 != 0 ? null : placeholder;
                leadingIcon2 = i5 != 0 ? null : leadingIcon;
                trailingIcon2 = i6 != 0 ? null : trailingIcon;
                if ((i & 2048) != 0) {
                    $dirty = $dirty4;
                    $composer2 = $composer5;
                    colors2 = m2072outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer2, 0, 0, ($dirty6 >> 9) & 112, 2097151);
                    $dirty1 = $dirty6 & (-113);
                } else {
                    $dirty = $dirty4;
                    $composer2 = $composer5;
                    colors2 = colors;
                    $dirty1 = $dirty6;
                }
                if ((i & 4096) != 0) {
                    contentPadding2 = m2063outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty1 &= -897;
                } else {
                    contentPadding2 = contentPadding;
                }
                if (i7 != 0) {
                    final boolean isError4 = isError3;
                    $composer3 = $composer2;
                    contentPadding3 = contentPadding2;
                    border2 = ComposableLambdaKt.rememberComposableLambda(1261916269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer6, int $changed2) {
                            ComposerKt.sourceInformation($composer6, "C759@34566L54:TextFieldDefaults.kt#jmzs0o");
                            if (($changed2 & 3) == 2 && $composer6.getSkipping()) {
                                $composer6.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1261916269, $changed2, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:759)");
                            }
                            TextFieldDefaults.INSTANCE.m2066BorderBoxnbWgWpA(enabled, isError4, interactionSource, colors2, null, 0.0f, 0.0f, $composer6, 12582912, 112);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    isError2 = isError4;
                    colors3 = colors2;
                    $dirty2 = $dirty1;
                } else {
                    $composer3 = $composer2;
                    border2 = border;
                    contentPadding3 = contentPadding2;
                    isError2 = isError3;
                    colors3 = colors2;
                    $dirty2 = $dirty1;
                }
            } else {
                $composer5.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty6 &= -113;
                }
                if ((i & 4096) != 0) {
                    isError2 = isError;
                    placeholder2 = placeholder;
                    leadingIcon2 = leadingIcon;
                    trailingIcon2 = trailingIcon;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    border2 = border;
                    $dirty = $dirty4;
                    label2 = function2;
                    $composer3 = $composer5;
                    $dirty2 = $dirty6 & (-897);
                } else {
                    isError2 = isError;
                    placeholder2 = placeholder;
                    leadingIcon2 = leadingIcon;
                    trailingIcon2 = trailingIcon;
                    colors3 = colors;
                    contentPadding3 = contentPadding;
                    border2 = border;
                    $dirty = $dirty4;
                    label2 = function2;
                    $composer3 = $composer5;
                    $dirty2 = $dirty6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty3 = $dirty;
                ComposerKt.traceEventStart(-1280721485, $dirty3, $dirty2, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:761)");
            } else {
                $dirty3 = $dirty;
            }
            $composer4 = $composer3;
            OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, isError2, label2, placeholder2, leadingIcon2, trailingIcon2, getOutlinedTextFieldShape($composer3, ($dirty2 >> 12) & 14), colors3, contentPadding3, border2, $composer4, ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3) | (29360128 & $dirty3) | (234881024 & $dirty3) | (1879048192 & $dirty3), ($dirty2 & 14) | (($dirty2 << 3) & 896) | (($dirty2 << 3) & 7168) | (($dirty2 << 3) & 57344) | (458752 & ($dirty2 << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer4.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z = isError2;
            final Function2 function3 = label2;
            final Function2 function4 = placeholder2;
            final Function2 function5 = leadingIcon2;
            final Function2 function6 = trailingIcon2;
            final TextFieldColors textFieldColors = colors3;
            final PaddingValues paddingValues = contentPadding3;
            final Function2 function7 = border2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.4
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

                public final void invoke(Composer composer, int i8) {
                    TextFieldDefaults.this.OutlinedTextFieldDecorationBox(value, innerTextField, enabled, singleLine, visualTransformation, interactionSource, z, function3, function4, function5, function6, textFieldColors, paddingValues, function7, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }
}
