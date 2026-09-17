package androidx.compose.foundation.text;

import androidx.compose.foundation.text.modifiers.SelectableTextAnnotatedStringElement;
import androidx.compose.foundation.text.modifiers.SelectionController;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNodeKt;
import androidx.compose.foundation.text.modifiers.TextStringSimpleElement;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.termux.terminal.KeyHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BasicText.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0084\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001az\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001ad\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001an\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010 \u001az\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010!\u001a°\u0001\u0010\"\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010#\u001a\u00020\u000e2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0001\u0018\u00010\tH\u0003ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a@\u0010,\u001a\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020/\u0012\f\u0012\n\u0012\u0004\u0012\u000201\u0018\u0001000.\u0018\u00010-2\f\u00102\u001a\b\u0012\u0004\u0012\u0002030-2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e00H\u0002\u001a\u001e\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u000207062\b\u00108\u001a\u0004\u0018\u000109H\u0002\u001aÂ\u0001\u0010:\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%2\u0014\u0010;\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0<\u0018\u00010-2\u001c\u0010>\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010?0-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u0001\u0018\u00010\tH\u0002ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006B²\u0006\n\u0010C\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"BasicText", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "inlineContent", "", "", "Landroidx/compose/foundation/text/InlineTextContent;", "BasicText-VhcvRP8", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "BasicText-RWo7tUw", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILjava/util/Map;Landroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/runtime/Composer;II)V", "BasicText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILjava/util/Map;Landroidx/compose/runtime/Composer;II)V", "BasicText-BpD7jsM", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZILandroidx/compose/runtime/Composer;II)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/runtime/Composer;II)V", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/graphics/ColorProducer;Landroidx/compose/runtime/Composer;II)V", "LayoutWithLinksAndInlineContent", "hasInlineContent", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "selectionController", "Landroidx/compose/foundation/text/modifiers/SelectionController;", "onShowTranslation", "Landroidx/compose/foundation/text/modifiers/TextAnnotatedStringNode$TextSubstitutionValue;", "LayoutWithLinksAndInlineContent-vOo2fZY", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/AnnotatedString;Lkotlin/jvm/functions/Function1;ZLjava/util/Map;Landroidx/compose/ui/text/TextStyle;IZIILandroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "measureWithTextRangeMeasureConstraints", "", "Lkotlin/Pair;", "Landroidx/compose/ui/layout/Placeable;", "Lkotlin/Function0;", "Landroidx/compose/ui/unit/IntOffset;", "measurables", "Landroidx/compose/ui/layout/Measurable;", "shouldMeasureLinks", "selectionIdSaver", "Landroidx/compose/runtime/saveable/Saver;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "textModifier", "placeholders", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "onPlaceholderLayout", "Landroidx/compose/ui/geometry/Rect;", "textModifier-cFh6CEA", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function1;IZIILandroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/text/modifiers/SelectionController;Landroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function1;)Landroidx/compose/ui/Modifier;", "foundation_release", "displayedText"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicTextKt {
    /* JADX WARN: Code duplicated, block: B:178:0x04a2  */
    /* JADX INFO: renamed from: BasicText-VhcvRP8, reason: not valid java name */
    public static final void m1330BasicTextVhcvRP8(final String text, Modifier modifier, TextStyle style, Function1<? super TextLayoutResult, Unit> function1, int overflow, boolean softWrap, int maxLines, int minLines, ColorProducer color, Composer $composer, final int $changed, final int i) {
        int i2;
        boolean z;
        SelectionController selectionController;
        Modifier finalModifier;
        int minLines2;
        Function0<ComposeUiNode> function0;
        Modifier modifier2;
        ColorProducer color2;
        TextStyle style2;
        Function1<? super TextLayoutResult, Unit> function2;
        int overflow2;
        boolean softWrap2;
        int maxLines2;
        Object value$iv;
        Object value$iv2;
        Composer $composer2 = $composer.startRestartGroup(-1186827822);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(8,3,7,4,5:c#ui.text.style.TextOverflow,6,1,2)101@5004L7,151@6858L41:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(style) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 24576) == 0) {
            i2 = overflow;
            $dirty |= $composer2.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = softWrap;
        } else if ((196608 & $changed) == 0) {
            z = softWrap;
            $dirty |= $composer2.changed(z) ? 131072 : 65536;
        } else {
            z = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(minLines) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(color) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty & 38347923) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            style2 = style;
            function2 = function1;
            maxLines2 = maxLines;
            minLines2 = minLines;
            color2 = color;
            softWrap2 = z;
            overflow2 = i2;
        } else {
            Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier;
            TextStyle style3 = i4 != 0 ? TextStyle.INSTANCE.getDefault() : style;
            Function1<? super TextLayoutResult, Unit> function3 = i5 != 0 ? null : function1;
            int overflow3 = i6 != 0 ? TextOverflow.INSTANCE.m6932getClipgIe3tQ8() : i2;
            boolean softWrap3 = i7 != 0 ? true : z;
            int maxLines3 = i8 != 0 ? Integer.MAX_VALUE : maxLines;
            int minLines3 = i9 != 0 ? 1 : minLines;
            ColorProducer color3 = i10 != 0 ? null : color;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1186827822, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:96)");
            }
            HeightInLinesModifierKt.validateMinMaxLines(minLines3, maxLines3);
            ProvidableCompositionLocal<SelectionRegistrar> localSelectionRegistrar = SelectionRegistrarKt.getLocalSelectionRegistrar();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localSelectionRegistrar);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final SelectionRegistrar selectionRegistrar = (SelectionRegistrar) objConsume;
            if (selectionRegistrar != null) {
                $composer2.startReplaceGroup(-1589202404);
                ComposerKt.sourceInformation($composer2, "103@5140L7,105@5286L69,105@5203L152,108@5364L234");
                ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localTextSelectionColors);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                long backgroundSelectionColor = ((SelectionColors) objConsume2).getSelectionBackgroundColor();
                Object[] objArr = {selectionRegistrar};
                Saver<Long, Long> saverSelectionIdSaver = selectionIdSaver(selectionRegistrar);
                ComposerKt.sourceInformationMarkerStart($composer2, -328352935, "CC(remember):BasicText.kt#9igjgp");
                boolean invalid$iv = $composer2.changedInstance(selectionRegistrar);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function0) new Function0<Long>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$selectionController$selectableId$1$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Long invoke() {
                            return Long.valueOf(selectionRegistrar.nextSelectableId());
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                long selectableId = ((Number) RememberSaveableKt.m4120rememberSaveable(objArr, (Saver) saverSelectionIdSaver, (String) null, (Function0) value$iv, $composer2, 0, 4)).longValue();
                ComposerKt.sourceInformationMarkerStart($composer2, -328350274, "CC(remember):BasicText.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(selectableId) | $composer2.changed(selectionRegistrar) | $composer2.changed(backgroundSelectionColor);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new SelectionController(selectableId, selectionRegistrar, backgroundSelectionColor, null, 8, null);
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv2;
                }
                selectionController = (SelectionController) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1588686502);
                $composer2.endReplaceGroup();
                selectionController = null;
            }
            if (selectionController == null && function3 == null) {
                $composer2.startReplaceGroup(-1587866335);
                ComposerKt.sourceInformation($composer2, "143@6671L7");
                Modifier modifierM4695graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m4695graphicsLayerAp8cVGQ$default(modifier3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131071, null);
                ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localFontFamilyResolver);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                finalModifier = modifierM4695graphicsLayerAp8cVGQ$default.then(new TextStringSimpleElement(text, style3, (FontFamily.Resolver) objConsume3, overflow3, softWrap3, maxLines3, minLines3, color3, null));
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1588564052);
                ComposerKt.sourceInformation($composer2, "130@6178L7");
                Modifier modifierM4695graphicsLayerAp8cVGQ$default2 = GraphicsLayerModifierKt.m4695graphicsLayerAp8cVGQ$default(modifier3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131071, null);
                AnnotatedString annotatedString = new AnnotatedString(text, null, null, 6, null);
                ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver2 = CompositionLocalsKt.getLocalFontFamilyResolver();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer2.consume(localFontFamilyResolver2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                finalModifier = m1333textModifiercFh6CEA(modifierM4695graphicsLayerAp8cVGQ$default2, annotatedString, style3, function3, overflow3, softWrap3, maxLines3, minLines3, (FontFamily.Resolver) objConsume4, null, null, selectionController, color3, null);
                $composer2.endReplaceGroup();
            }
            MeasurePolicy measurePolicy$iv = EmptyMeasurePolicy.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 544976794, "CC(Layout)P(1)125@4862L23,128@5013L385:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, finalModifier);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            minLines2 = minLines3;
            ComposerKt.sourceInformationMarkerStart($composer2, 1405779621, "CC(ReusableComposeNode):Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u241$iv = Updater.m4027constructorimpl($composer2);
            Updater.m4034setimpl($this$Layout_u24lambda_u241$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl($this$Layout_u24lambda_u241$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4034setimpl($this$Layout_u24lambda_u241$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u241$iv.getInserting()) {
                modifier2 = modifier3;
            } else {
                modifier2 = modifier3;
                if (!Intrinsics.areEqual($this$Layout_u24lambda_u241$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                color2 = color3;
                style2 = style3;
                function2 = function3;
                overflow2 = overflow3;
                softWrap2 = softWrap3;
                maxLines2 = maxLines3;
            }
            $this$Layout_u24lambda_u241$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u241$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color3;
            style2 = style3;
            function2 = function3;
            overflow2 = overflow3;
            softWrap2 = softWrap3;
            maxLines2 = maxLines3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final TextStyle textStyle = style2;
            final Function1<? super TextLayoutResult, Unit> function4 = function2;
            final int i11 = overflow2;
            final boolean z2 = softWrap2;
            final int i12 = maxLines2;
            final int i13 = minLines2;
            final ColorProducer colorProducer = color2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$1
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

                public final void invoke(Composer composer, int i14) {
                    BasicTextKt.m1330BasicTextVhcvRP8(text, modifier4, textStyle, function4, i11, z2, i12, i13, colorProducer, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: BasicText-RWo7tUw, reason: not valid java name */
    public static final void m1328BasicTextRWo7tUw(final AnnotatedString text, Modifier modifier, TextStyle style, Function1<? super TextLayoutResult, Unit> function1, int overflow, boolean softWrap, int maxLines, int minLines, Map<String, InlineTextContent> map, ColorProducer color, Composer $composer, final int $changed, final int i) {
        Function1<? super TextLayoutResult, Unit> function2;
        int i2;
        Modifier modifier2;
        TextStyle style2;
        Function1<? super TextLayoutResult, Unit> function3;
        int overflow2;
        boolean softWrap2;
        Map<String, InlineTextContent> mapEmptyMap;
        ColorProducer color2;
        SelectionController selectionController;
        int minLines2;
        int maxLines2;
        Composer $composer2;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        Object value$iv4;
        Composer $composer3 = $composer.startRestartGroup(-1064305212);
        ComposerKt.sourceInformation($composer3, "C(BasicText)P(9,4,8,5,6:c#ui.text.style.TextOverflow,7,2,3,1)196@9257L7:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(style) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            function2 = function1;
        } else if (($changed & 3072) == 0) {
            function2 = function1;
            $dirty |= $composer3.changedInstance(function2) ? 2048 : 1024;
        } else {
            function2 = function1;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 24576) == 0) {
            i2 = overflow;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changed(softWrap) ? 131072 : 65536;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changed(minLines) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(map) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i11 = i & 512;
        if (i11 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(color) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if (($dirty & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier2 = modifier;
            style2 = style;
            softWrap2 = softWrap;
            maxLines2 = maxLines;
            minLines2 = minLines;
            mapEmptyMap = map;
            color2 = color;
            function3 = function2;
            overflow2 = i2;
            $composer2 = $composer3;
        } else {
            modifier2 = i3 != 0 ? Modifier.INSTANCE : modifier;
            style2 = i4 != 0 ? TextStyle.INSTANCE.getDefault() : style;
            function3 = i5 != 0 ? null : function2;
            overflow2 = i6 != 0 ? TextOverflow.INSTANCE.m6932getClipgIe3tQ8() : i2;
            softWrap2 = i7 != 0 ? true : softWrap;
            int maxLines3 = i8 != 0 ? Integer.MAX_VALUE : maxLines;
            int minLines3 = i9 != 0 ? 1 : minLines;
            mapEmptyMap = i10 != 0 ? MapsKt.emptyMap() : map;
            color2 = i11 != 0 ? null : color;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1064305212, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:191)");
            }
            HeightInLinesModifierKt.validateMinMaxLines(minLines3, maxLines3);
            ProvidableCompositionLocal<SelectionRegistrar> localSelectionRegistrar = SelectionRegistrarKt.getLocalSelectionRegistrar();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localSelectionRegistrar);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final SelectionRegistrar selectionRegistrar = (SelectionRegistrar) objConsume;
            if (selectionRegistrar != null) {
                $composer3.startReplaceGroup(-1584983428);
                ComposerKt.sourceInformation($composer3, "198@9393L7,200@9539L69,200@9456L152,203@9617L234");
                ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localTextSelectionColors);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                long backgroundSelectionColor = ((SelectionColors) objConsume2).getSelectionBackgroundColor();
                Object[] objArr = {selectionRegistrar};
                Saver<Long, Long> saverSelectionIdSaver = selectionIdSaver(selectionRegistrar);
                ComposerKt.sourceInformationMarkerStart($composer3, -328216839, "CC(remember):BasicText.kt#9igjgp");
                boolean invalid$iv = $composer3.changedInstance(selectionRegistrar);
                Object it$iv = $composer3.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function0) new Function0<Long>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$selectionController$selectableId$2$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Long invoke() {
                            return Long.valueOf(selectionRegistrar.nextSelectableId());
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                long selectableId = ((Number) RememberSaveableKt.m4120rememberSaveable(objArr, (Saver) saverSelectionIdSaver, (String) null, (Function0) value$iv3, $composer3, 0, 4)).longValue();
                ComposerKt.sourceInformationMarkerStart($composer3, -328214178, "CC(remember):BasicText.kt#9igjgp");
                boolean invalid$iv2 = $composer3.changed(selectableId) | $composer3.changed(selectionRegistrar) | $composer3.changed(backgroundSelectionColor);
                Object it$iv2 = $composer3.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = new SelectionController(selectableId, selectionRegistrar, backgroundSelectionColor, null, 8, null);
                    $composer3.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                selectionController = (SelectionController) value$iv4;
            } else {
                $dirty = $dirty;
                $composer3.startReplaceGroup(-1584467526);
                $composer3.endReplaceGroup();
                selectionController = null;
            }
            boolean hasInlineContent = AnnotatedStringResolveInlineContentKt.hasInlineContent(text);
            boolean hasLinks = TextAnnotatedStringNodeKt.hasLinks(text);
            if (hasInlineContent || hasLinks) {
                minLines2 = minLines3;
                maxLines2 = maxLines3;
                $composer2 = $composer3;
                int $dirty2 = $dirty;
                $composer2.startReplaceGroup(-1583391888);
                ComposerKt.sourceInformation($composer2, "241@11076L39,254@11574L7,257@11697L256,243@11125L838");
                ComposerKt.sourceInformationMarkerStart($composer2, -328167685, "CC(remember):BasicText.kt#9igjgp");
                boolean invalid$iv3 = ($dirty2 & 14) == 4;
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(text, null, 2, null);
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv3;
                }
                final MutableState displayedText$delegate = (MutableState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AnnotatedString annotatedStringBasicText_RWo7tUw$lambda$5 = BasicText_RWo7tUw$lambda$5(displayedText$delegate);
                ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer2.consume(localFontFamilyResolver);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                FontFamily.Resolver resolver = (FontFamily.Resolver) objConsume3;
                ComposerKt.sourceInformationMarkerStart($composer2, -328147596, "CC(remember):BasicText.kt#9igjgp");
                boolean invalid$iv4 = $composer2.changed(displayedText$delegate);
                Object it$iv4 = $composer2.rememberedValue();
                if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new Function1<TextAnnotatedStringNode.TextSubstitutionValue, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TextAnnotatedStringNode.TextSubstitutionValue textSubstitutionValue) {
                            invoke2(textSubstitutionValue);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TextAnnotatedStringNode.TextSubstitutionValue substitutionValue) {
                            AnnotatedString original;
                            MutableState<AnnotatedString> mutableState = displayedText$delegate;
                            if (substitutionValue.isShowingSubstitution()) {
                                original = substitutionValue.getSubstitution();
                            } else {
                                original = substitutionValue.getOriginal();
                            }
                            mutableState.setValue(original);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                m1331LayoutWithLinksAndInlineContentvOo2fZY(modifier2, annotatedStringBasicText_RWo7tUw$lambda$5, function3, hasInlineContent, mapEmptyMap, style2, overflow2, softWrap2, maxLines2, minLines2, resolver, selectionController, color2, (Function1) value$iv2, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 896) | (($dirty2 >> 12) & 57344) | (($dirty2 << 9) & 458752) | (($dirty2 << 6) & 3670016) | (($dirty2 << 6) & 29360128) | (($dirty2 << 6) & 234881024) | (($dirty2 << 6) & 1879048192), ($dirty2 >> 21) & 896, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1584294453);
                ComposerKt.sourceInformation($composer3, "229@10603L7,217@10089L814");
                Modifier modifierM4695graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m4695graphicsLayerAp8cVGQ$default(modifier2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131071, null);
                ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver2 = CompositionLocalsKt.getLocalFontFamilyResolver();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localFontFamilyResolver2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                minLines2 = minLines3;
                maxLines2 = maxLines3;
                Modifier modifier$iv = m1333textModifiercFh6CEA(modifierM4695graphicsLayerAp8cVGQ$default, text, style2, function3, overflow2, softWrap2, maxLines3, minLines3, (FontFamily.Resolver) objConsume4, null, null, selectionController, color2, null);
                MeasurePolicy measurePolicy$iv = EmptyMeasurePolicy.INSTANCE;
                $composer2 = $composer3;
                ComposerKt.sourceInformationMarkerStart($composer2, 544976794, "CC(Layout)P(1)125@4862L23,128@5013L385:Layout.kt#80mrfh");
                int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart($composer2, 1405779621, "CC(ReusableComposeNode):Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u241$iv = Updater.m4027constructorimpl($composer2);
                Updater.m4034setimpl($this$Layout_u24lambda_u241$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4034setimpl($this$Layout_u24lambda_u241$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4034setimpl($this$Layout_u24lambda_u241$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u241$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u241$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                    $this$Layout_u24lambda_u241$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                    $this$Layout_u24lambda_u241$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                }
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            final TextStyle textStyle = style2;
            final Function1<? super TextLayoutResult, Unit> function4 = function3;
            final int i12 = overflow2;
            final boolean z = softWrap2;
            final int i13 = maxLines2;
            final int i14 = minLines2;
            final Map<String, InlineTextContent> map2 = mapEmptyMap;
            final ColorProducer colorProducer = color2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$3
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

                public final void invoke(Composer composer, int i15) {
                    BasicTextKt.m1328BasicTextRWo7tUw(text, modifier3, textStyle, function4, i12, z, i13, i14, map2, colorProducer, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final AnnotatedString BasicText_RWo7tUw$lambda$5(MutableState<AnnotatedString> mutableState) {
        MutableState<AnnotatedString> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: BasicText-BpD7jsM, reason: not valid java name */
    public static final /* synthetic */ void m1327BasicTextBpD7jsM(final String text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, Composer $composer, final int $changed, final int i) {
        TextStyle textStyle;
        Function1 onTextLayout2;
        int i2;
        boolean z;
        Modifier modifier2;
        TextStyle style2;
        int overflow2;
        boolean softWrap2;
        int maxLines2;
        Function1 onTextLayout3;
        Composer $composer2 = $composer.startRestartGroup(1022429478);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(6,1,5,2,3:c#ui.text.style.TextOverflow,4)279@12355L234:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            textStyle = style;
        } else if (($changed & 384) == 0) {
            textStyle = style;
            $dirty |= $composer2.changed(textStyle) ? 256 : 128;
        } else {
            textStyle = style;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 24576) == 0) {
            i2 = overflow;
            $dirty |= $composer2.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = softWrap;
        } else if ((196608 & $changed) == 0) {
            z = softWrap;
            $dirty |= $composer2.changed(z) ? 131072 : 65536;
        } else {
            z = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            maxLines2 = maxLines;
            onTextLayout3 = onTextLayout2;
            softWrap2 = z;
            overflow2 = i2;
            style2 = textStyle;
        } else {
            Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier;
            TextStyle style3 = i4 != 0 ? TextStyle.INSTANCE.getDefault() : textStyle;
            if (i5 != 0) {
                onTextLayout2 = null;
            }
            int overflow3 = i6 != 0 ? TextOverflow.INSTANCE.m6932getClipgIe3tQ8() : i2;
            boolean softWrap3 = i7 != 0 ? true : z;
            int maxLines3 = i8 != 0 ? Integer.MAX_VALUE : maxLines;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1022429478, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:278)");
            }
            m1330BasicTextVhcvRP8(text, modifier3, style3, (Function1<? super TextLayoutResult, Unit>) onTextLayout2, overflow3, softWrap3, maxLines3, 1, (ColorProducer) null, $composer2, ($dirty & 14) | 12582912 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            style2 = style3;
            overflow2 = overflow3;
            softWrap2 = softWrap3;
            maxLines2 = maxLines3;
            onTextLayout3 = onTextLayout2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final TextStyle textStyle2 = style2;
            final Function1 function1 = onTextLayout3;
            final int i9 = overflow2;
            final boolean z2 = softWrap2;
            final int i10 = maxLines2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$4
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
                    BasicTextKt.m1327BasicTextBpD7jsM(text, modifier4, textStyle2, function1, i9, z2, i10, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: BasicText-4YKlhWE, reason: not valid java name */
    public static final /* synthetic */ void m1325BasicText4YKlhWE(final AnnotatedString text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, Map inlineContent, Composer $composer, final int $changed, final int i) {
        Function1 onTextLayout2;
        int i2;
        boolean z;
        Map inlineContent2;
        Modifier modifier2;
        TextStyle style2;
        int overflow2;
        boolean softWrap2;
        int maxLines2;
        Function1 onTextLayout3;
        Composer $composer2 = $composer.startRestartGroup(-648605928);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(7,2,6,3,4:c#ui.text.style.TextOverflow,5,1)303@13046L273:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(style) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 24576) == 0) {
            i2 = overflow;
            $dirty |= $composer2.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = softWrap;
        } else if ((196608 & $changed) == 0) {
            z = softWrap;
            $dirty |= $composer2.changed(z) ? 131072 : 65536;
        } else {
            z = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(inlineContent) ? 8388608 : 4194304;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            maxLines2 = maxLines;
            inlineContent2 = inlineContent;
            onTextLayout3 = onTextLayout2;
            softWrap2 = z;
            overflow2 = i2;
            style2 = style;
        } else {
            Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier;
            TextStyle style3 = i4 != 0 ? TextStyle.INSTANCE.getDefault() : style;
            if (i5 != 0) {
                onTextLayout2 = null;
            }
            int overflow3 = i6 != 0 ? TextOverflow.INSTANCE.m6932getClipgIe3tQ8() : i2;
            boolean softWrap3 = i7 != 0 ? true : z;
            int maxLines3 = i8 != 0 ? Integer.MAX_VALUE : maxLines;
            Map inlineContent3 = i9 != 0 ? MapsKt.emptyMap() : inlineContent;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-648605928, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:302)");
            }
            m1328BasicTextRWo7tUw(text, modifier3, style3, onTextLayout2, overflow3, softWrap3, maxLines3, 1, inlineContent3, null, $composer2, ($dirty & 14) | 12582912 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (($dirty << 3) & 234881024), 512);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            inlineContent2 = inlineContent3;
            modifier2 = modifier3;
            style2 = style3;
            overflow2 = overflow3;
            softWrap2 = softWrap3;
            maxLines2 = maxLines3;
            onTextLayout3 = onTextLayout2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final TextStyle textStyle = style2;
            final Function1 function1 = onTextLayout3;
            final int i10 = overflow2;
            final boolean z2 = softWrap2;
            final int i11 = maxLines2;
            final Map map = inlineContent2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$5
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
                    BasicTextKt.m1325BasicText4YKlhWE(text, modifier4, textStyle, function1, i10, z2, i11, map, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compat")
    /* JADX INFO: renamed from: BasicText-4YKlhWE, reason: not valid java name */
    public static final /* synthetic */ void m1326BasicText4YKlhWE(final String text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, int minLines, Composer $composer, final int $changed, final int i) {
        Function1 onTextLayout2;
        int i2;
        boolean z;
        Modifier.Companion modifier2;
        TextStyle style2;
        int overflow2;
        boolean softWrap2;
        int maxLines2;
        int minLines2;
        int minLines3;
        Modifier modifier3;
        TextStyle style3;
        int overflow3;
        boolean softWrap3;
        int maxLines3;
        Function1 onTextLayout3;
        Composer $composer2 = $composer.startRestartGroup(1542716361);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(7,2,6,3,4:c#ui.text.style.TextOverflow,5)327@13717L86:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(style) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            onTextLayout2 = onTextLayout;
        } else if (($changed & 3072) == 0) {
            onTextLayout2 = onTextLayout;
            $dirty |= $composer2.changedInstance(onTextLayout2) ? 2048 : 1024;
        } else {
            onTextLayout2 = onTextLayout;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 24576) == 0) {
            i2 = overflow;
            $dirty |= $composer2.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = softWrap;
        } else if ((196608 & $changed) == 0) {
            z = softWrap;
            $dirty |= $composer2.changed(z) ? 131072 : 65536;
        } else {
            z = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(minLines) ? 8388608 : 4194304;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier;
            maxLines3 = maxLines;
            minLines3 = minLines;
            onTextLayout3 = onTextLayout2;
            softWrap3 = z;
            overflow3 = i2;
            style3 = style;
        } else {
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (i4 == 0) {
                style2 = style;
            } else {
                style2 = TextStyle.INSTANCE.getDefault();
            }
            if (i5 != 0) {
                onTextLayout2 = null;
            }
            if (i6 == 0) {
                overflow2 = i2;
            } else {
                overflow2 = TextOverflow.INSTANCE.m6932getClipgIe3tQ8();
            }
            if (i7 == 0) {
                softWrap2 = z;
            } else {
                softWrap2 = true;
            }
            if (i8 == 0) {
                maxLines2 = maxLines;
            } else {
                maxLines2 = Integer.MAX_VALUE;
            }
            if (i9 == 0) {
                minLines2 = minLines;
            } else {
                minLines2 = 1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1542716361, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:327)");
            }
            m1330BasicTextVhcvRP8(text, modifier2, style2, (Function1<? super TextLayoutResult, Unit>) onTextLayout2, overflow2, softWrap2, maxLines2, minLines2, (ColorProducer) null, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty), 256);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            minLines3 = minLines2;
            modifier3 = modifier2;
            style3 = style2;
            overflow3 = overflow2;
            softWrap3 = softWrap2;
            maxLines3 = maxLines2;
            onTextLayout3 = onTextLayout2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final TextStyle textStyle = style3;
            final Function1 function1 = onTextLayout3;
            final int i10 = overflow3;
            final boolean z2 = softWrap3;
            final int i11 = maxLines3;
            final int i12 = minLines3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$6
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

                public final void invoke(Composer composer, int i13) {
                    BasicTextKt.m1326BasicText4YKlhWE(text, modifier4, textStyle, function1, i10, z2, i11, i12, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compat")
    /* JADX INFO: renamed from: BasicText-VhcvRP8, reason: not valid java name */
    public static final /* synthetic */ void m1329BasicTextVhcvRP8(final AnnotatedString text, Modifier modifier, TextStyle style, Function1 onTextLayout, int overflow, boolean softWrap, int maxLines, int minLines, Map inlineContent, Composer $composer, final int $changed, final int i) {
        int i2;
        boolean z;
        int minLines2;
        Modifier modifier2;
        Map inlineContent2;
        TextStyle style2;
        Function1 onTextLayout2;
        int overflow2;
        boolean softWrap2;
        int maxLines2;
        Composer $composer2 = $composer.startRestartGroup(851408699);
        ComposerKt.sourceInformation($composer2, "C(BasicText)P(8,3,7,4,5:c#ui.text.style.TextOverflow,6,1,2)341@14269L240:BasicText.kt#423gt5");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(text) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(style) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(onTextLayout) ? 2048 : 1024;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = overflow;
        } else if (($changed & 24576) == 0) {
            i2 = overflow;
            $dirty |= $composer2.changed(i2) ? 16384 : 8192;
        } else {
            i2 = overflow;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = softWrap;
        } else if ((196608 & $changed) == 0) {
            z = softWrap;
            $dirty |= $composer2.changed(z) ? 131072 : 65536;
        } else {
            z = softWrap;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(maxLines) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(minLines) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(inlineContent) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($dirty & 38347923) == 38347922 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            style2 = style;
            maxLines2 = maxLines;
            minLines2 = minLines;
            inlineContent2 = inlineContent;
            overflow2 = i2;
            softWrap2 = z;
            modifier2 = modifier;
            onTextLayout2 = onTextLayout;
        } else {
            Modifier.Companion modifier3 = i3 != 0 ? Modifier.INSTANCE : modifier;
            TextStyle style3 = i4 != 0 ? TextStyle.INSTANCE.getDefault() : style;
            Function1 onTextLayout3 = i5 != 0 ? null : onTextLayout;
            int overflow3 = i6 != 0 ? TextOverflow.INSTANCE.m6932getClipgIe3tQ8() : i2;
            boolean softWrap3 = i7 != 0 ? true : z;
            int maxLines3 = i8 != 0 ? Integer.MAX_VALUE : maxLines;
            int minLines3 = i9 != 0 ? 1 : minLines;
            Map inlineContent3 = i10 != 0 ? MapsKt.emptyMap() : inlineContent;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(851408699, $dirty, -1, "androidx.compose.foundation.text.BasicText (BasicText.kt:341)");
            }
            m1328BasicTextRWo7tUw(text, modifier3, style3, onTextLayout3, overflow3, softWrap3, maxLines3, minLines3, inlineContent3, null, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty), 512);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            minLines2 = minLines3;
            modifier2 = modifier3;
            inlineContent2 = inlineContent3;
            style2 = style3;
            onTextLayout2 = onTextLayout3;
            overflow2 = overflow3;
            softWrap2 = softWrap3;
            maxLines2 = maxLines3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final TextStyle textStyle = style2;
            final Function1 function1 = onTextLayout2;
            final int i11 = overflow2;
            final boolean z2 = softWrap2;
            final int i12 = maxLines2;
            final int i13 = minLines2;
            final Map map = inlineContent2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$BasicText$7
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

                public final void invoke(Composer composer, int i14) {
                    BasicTextKt.m1329BasicTextVhcvRP8(text, modifier4, textStyle, function1, i11, z2, i12, i13, map, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final Saver<Long, Long> selectionIdSaver(final SelectionRegistrar selectionRegistrar) {
        return SaverKt.Saver(new Function2<SaverScope, Long, Long>() { // from class: androidx.compose.foundation.text.BasicTextKt.selectionIdSaver.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Long invoke(SaverScope saverScope, Long l) {
                return invoke(saverScope, l.longValue());
            }

            public final Long invoke(SaverScope $this$Saver, long it) {
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, it)) {
                    return Long.valueOf(it);
                }
                return null;
            }
        }, new Function1<Long, Long>() { // from class: androidx.compose.foundation.text.BasicTextKt.selectionIdSaver.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Long invoke(Long l) {
                return invoke(l.longValue());
            }

            public final Long invoke(long it) {
                return Long.valueOf(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Pair<Placeable, Function0<IntOffset>>> measureWithTextRangeMeasureConstraints(List<? extends Measurable> list, Function0<Boolean> function0) {
        if (function0.invoke().booleanValue()) {
            TextRangeLayoutMeasureScope textRangeLayoutMeasureScope = new TextRangeLayoutMeasureScope();
            List<? extends Measurable> list2 = list;
            int $i$f$fastMapIndexedNotNull = 0;
            ArrayList target$iv = new ArrayList(list2.size());
            List<? extends Measurable> list3 = list2;
            int $i$f$fastForEachIndexed = 0;
            int index$iv$iv = 0;
            int size = list3.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = list3.get(index$iv$iv);
                Measurable measurable = (Measurable) item$iv$iv;
                Object parentData = measurable.getParentData();
                Intrinsics.checkNotNull(parentData, "null cannot be cast to non-null type androidx.compose.foundation.text.TextRangeLayoutModifier");
                TextRangeScopeMeasurePolicy rangeMeasurePolicy = ((TextRangeLayoutModifier) parentData).getMeasurePolicy();
                TextRangeLayoutMeasureResult rangeMeasureResult = rangeMeasurePolicy.measure(textRangeLayoutMeasureScope);
                TextRangeLayoutMeasureScope textRangeLayoutMeasureScope2 = textRangeLayoutMeasureScope;
                Constraints.Companion companion = Constraints.INSTANCE;
                List<? extends Measurable> list4 = list2;
                int width = rangeMeasureResult.getWidth();
                int $i$f$fastMapIndexedNotNull2 = $i$f$fastMapIndexedNotNull;
                int $i$f$fastMapIndexedNotNull3 = rangeMeasureResult.getWidth();
                List<? extends Measurable> list5 = list3;
                int height = rangeMeasureResult.getHeight();
                int $i$f$fastForEachIndexed2 = $i$f$fastForEachIndexed;
                int $i$f$fastForEachIndexed3 = rangeMeasureResult.getHeight();
                Placeable placeable = measurable.mo5870measureBRTryo0(companion.m6978fitPrioritizingWidthZbe2FdA(width, $i$f$fastMapIndexedNotNull3, height, $i$f$fastForEachIndexed3));
                target$iv.add(new Pair(placeable, rangeMeasureResult.getPlace()));
                index$iv$iv++;
                textRangeLayoutMeasureScope = textRangeLayoutMeasureScope2;
                list2 = list4;
                $i$f$fastMapIndexedNotNull = $i$f$fastMapIndexedNotNull2;
                list3 = list5;
                $i$f$fastForEachIndexed = $i$f$fastForEachIndexed2;
            }
            return target$iv;
        }
        return null;
    }

    /* JADX INFO: renamed from: textModifier-cFh6CEA, reason: not valid java name */
    private static final Modifier m1333textModifiercFh6CEA(Modifier $this$textModifier_u2dcFh6CEA, AnnotatedString text, TextStyle style, Function1<? super TextLayoutResult, Unit> function1, int overflow, boolean softWrap, int maxLines, int minLines, FontFamily.Resolver fontFamilyResolver, List<AnnotatedString.Range<Placeholder>> list, Function1<? super List<Rect>, Unit> function2, SelectionController selectionController, ColorProducer color, Function1<? super TextAnnotatedStringNode.TextSubstitutionValue, Unit> function3) {
        if (selectionController == null) {
            TextAnnotatedStringElement staticTextModifier = new TextAnnotatedStringElement(text, style, fontFamilyResolver, function1, overflow, softWrap, maxLines, minLines, list, function2, null, color, function3, null);
            return $this$textModifier_u2dcFh6CEA.then(Modifier.INSTANCE).then(staticTextModifier);
        }
        SelectableTextAnnotatedStringElement selectableTextModifier = new SelectableTextAnnotatedStringElement(text, style, fontFamilyResolver, function1, overflow, softWrap, maxLines, minLines, list, function2, selectionController, color, null);
        return $this$textModifier_u2dcFh6CEA.then(selectionController.getModifier()).then(selectableTextModifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: LayoutWithLinksAndInlineContent-vOo2fZY, reason: not valid java name */
    public static final void m1331LayoutWithLinksAndInlineContentvOo2fZY(final Modifier modifier, final AnnotatedString annotatedString, final Function1<? super TextLayoutResult, Unit> function1, final boolean z, Map<String, InlineTextContent> map, final TextStyle textStyle, final int i, final boolean z2, final int i2, final int i3, final FontFamily.Resolver resolver, final SelectionController selectionController, final ColorProducer colorProducer, final Function1<? super TextAnnotatedStringNode.TextSubstitutionValue, Unit> function2, Composer composer, final int i4, final int i5, final int i6) {
        Map<String, InlineTextContent> map2;
        Map<String, InlineTextContent> mapEmptyMap;
        final TextLinkScope textLinkScope;
        Object obj;
        Function0 function0;
        final MutableState mutableState;
        Function1 function3;
        Object obj2;
        Object obj3;
        Object obj4;
        TextMeasurePolicy textMeasurePolicy;
        Function0<ComposeUiNode> function4;
        Object obj5;
        Object obj6;
        Object objMutableStateOf$default;
        Object obj7;
        Composer composerStartRestartGroup = composer.startRestartGroup(645129368);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LayoutWithLinksAndInlineContent)P(6,13,8,2,3,12,9:c#ui.text.style.TextOverflow,11,4,5,1,10)578@22283L117,565@21843L1459:BasicText.kt#423gt5");
        int i7 = i4;
        int i8 = i5;
        if ((i6 & 1) != 0) {
            i7 |= 6;
        } else if ((i4 & 6) == 0) {
            i7 |= composerStartRestartGroup.changed(modifier) ? 4 : 2;
        }
        if ((i6 & 2) != 0) {
            i7 |= 48;
        } else if ((i4 & 48) == 0) {
            i7 |= composerStartRestartGroup.changed(annotatedString) ? 32 : 16;
        }
        if ((i6 & 4) != 0) {
            i7 |= 384;
        } else if ((i4 & 384) == 0) {
            i7 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i6 & 8) != 0) {
            i7 |= 3072;
        } else if ((i4 & 3072) == 0) {
            i7 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        int i9 = i6 & 16;
        if (i9 != 0) {
            i7 |= 24576;
            map2 = map;
        } else if ((i4 & 24576) == 0) {
            map2 = map;
            i7 |= composerStartRestartGroup.changedInstance(map2) ? 16384 : 8192;
        } else {
            map2 = map;
        }
        if ((i6 & 32) != 0) {
            i7 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i4 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i7 |= composerStartRestartGroup.changed(textStyle) ? 131072 : 65536;
        }
        if ((i6 & 64) != 0) {
            i7 |= 1572864;
        } else if ((i4 & 1572864) == 0) {
            i7 |= composerStartRestartGroup.changed(i) ? 1048576 : 524288;
        }
        if ((i6 & 128) != 0) {
            i7 |= 12582912;
        } else if ((12582912 & i4) == 0) {
            i7 |= composerStartRestartGroup.changed(z2) ? 8388608 : 4194304;
        }
        if ((i6 & 256) != 0) {
            i7 |= 100663296;
        } else if ((100663296 & i4) == 0) {
            i7 |= composerStartRestartGroup.changed(i2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i6 & 512) != 0) {
            i7 |= 805306368;
        } else if ((805306368 & i4) == 0) {
            i7 |= composerStartRestartGroup.changed(i3) ? KeyHandler.KEYMOD_SHIFT : KeyHandler.KEYMOD_NUM_LOCK;
        }
        if ((i6 & 1024) != 0) {
            i8 |= 6;
        } else if ((i5 & 6) == 0) {
            i8 |= composerStartRestartGroup.changedInstance(resolver) ? 4 : 2;
        }
        if ((i6 & 2048) != 0) {
            i8 |= 48;
        } else if ((i5 & 48) == 0) {
            i8 |= composerStartRestartGroup.changedInstance(selectionController) ? 32 : 16;
        }
        if ((i6 & 4096) != 0) {
            i8 |= 384;
        } else if ((i5 & 384) == 0) {
            i8 |= composerStartRestartGroup.changedInstance(colorProducer) ? 256 : 128;
        }
        if ((i6 & 8192) != 0) {
            i8 |= 3072;
        } else if ((i5 & 3072) == 0) {
            i8 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if ((i7 & 306783379) == 306783378 && (i8 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i7 = i7;
            i8 = i8;
            mapEmptyMap = map2;
        } else {
            mapEmptyMap = i9 != 0 ? MapsKt.emptyMap() : map2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(645129368, i7, i8, "androidx.compose.foundation.text.LayoutWithLinksAndInlineContent (BasicText.kt:536)");
            }
            if (TextAnnotatedStringNodeKt.hasLinks(annotatedString)) {
                composerStartRestartGroup.startReplaceGroup(-619323167);
                ComposerKt.sourceInformation(composerStartRestartGroup, "539@20954L38");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297072527, "CC(remember):BasicText.kt#9igjgp");
                boolean z3 = (i7 & 112) == 32;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new TextLinkScope(annotatedString);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
                textLinkScope = (TextLinkScope) objRememberedValue;
            } else {
                composerStartRestartGroup.startReplaceGroup(-619265198);
                composerStartRestartGroup.endReplaceGroup();
                textLinkScope = null;
            }
            if (TextAnnotatedStringNodeKt.hasLinks(annotatedString)) {
                composerStartRestartGroup.startReplaceGroup(-619074547);
                ComposerKt.sourceInformation(composerStartRestartGroup, "545@21203L90");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297064507, "CC(remember):BasicText.kt#9igjgp");
                boolean zChanged = ((i7 & 112) == 32) | composerStartRestartGroup.changed(textLinkScope);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    obj7 = (Function0) new Function0<AnnotatedString>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$styledText$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final AnnotatedString invoke() {
                            AnnotatedString annotatedStringApplyAnnotators$foundation_release;
                            TextLinkScope textLinkScope2 = textLinkScope;
                            return (textLinkScope2 == null || (annotatedStringApplyAnnotators$foundation_release = textLinkScope2.applyAnnotators$foundation_release()) == null) ? annotatedString : annotatedStringApplyAnnotators$foundation_release;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj7);
                } else {
                    obj7 = objRememberedValue2;
                }
                function0 = (Function0) obj7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-618966357);
                ComposerKt.sourceInformation(composerStartRestartGroup, "548@21307L8");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297061261, "CC(remember):BasicText.kt#9igjgp");
                boolean z4 = (i7 & 112) == 32;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z4 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    obj = (Function0) new Function0<AnnotatedString>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$styledText$2$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final AnnotatedString invoke() {
                            return annotatedString;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj);
                } else {
                    obj = objRememberedValue3;
                }
                function0 = (Function0) obj;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            }
            Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> pairResolveInlineContent = z ? AnnotatedStringResolveInlineContentKt.resolveInlineContent(annotatedString, mapEmptyMap) : new Pair<>(null, null);
            List<AnnotatedString.Range<Placeholder>> listComponent1 = pairResolveInlineContent.component1();
            List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> listComponent2 = pairResolveInlineContent.component2();
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-618671702);
                ComposerKt.sourceInformation(composerStartRestartGroup, "558@21610L61");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297051512, "CC(remember):BasicText.kt#9igjgp");
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                } else {
                    objMutableStateOf$default = objRememberedValue4;
                }
                mutableState = (MutableState) objMutableStateOf$default;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-618591630);
                composerStartRestartGroup.endReplaceGroup();
                mutableState = null;
            }
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-618506565);
                ComposerKt.sourceInformation(composerStartRestartGroup, "562@21777L44");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297046185, "CC(remember):BasicText.kt#9igjgp");
                boolean zChanged2 = composerStartRestartGroup.changed(mutableState);
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    obj6 = (Function1) new Function1<List<? extends Rect>, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$onPlaceholderLayout$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends Rect> list) {
                            invoke2((List<Rect>) list);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(List<Rect> list) {
                            MutableState<List<Rect>> mutableState2 = mutableState;
                            if (mutableState2 == null) {
                                return;
                            }
                            mutableState2.setValue(list);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj6);
                } else {
                    obj6 = objRememberedValue5;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
                function3 = (Function1) obj6;
            } else {
                composerStartRestartGroup.startReplaceGroup(-618442830);
                composerStartRestartGroup.endReplaceGroup();
                function3 = null;
            }
            Modifier modifierM4695graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m4695graphicsLayerAp8cVGQ$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131071, null);
            AnnotatedString annotatedString2 = (AnnotatedString) function0.invoke();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297029920, "CC(remember):BasicText.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(textLinkScope) | ((i7 & 896) == 256);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                obj2 = (Function1) new Function1<TextLayoutResult, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TextLayoutResult textLayoutResult) {
                        invoke2(textLayoutResult);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(TextLayoutResult it) {
                        TextLinkScope textLinkScope2 = textLinkScope;
                        if (textLinkScope2 != null) {
                            textLinkScope2.setTextLayoutResult(it);
                        }
                        Function1<TextLayoutResult, Unit> function5 = function1;
                        if (function5 != null) {
                            function5.invoke(it);
                        }
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj2);
            } else {
                obj2 = objRememberedValue6;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1333textModifiercFh6CEA = m1333textModifiercFh6CEA(modifierM4695graphicsLayerAp8cVGQ$default, annotatedString2, textStyle, (Function1) obj2, i, z2, i2, i3, resolver, listComponent1, function3, selectionController, colorProducer, function2);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-617202116);
                ComposerKt.sourceInformation(composerStartRestartGroup, "599@23147L55,600@23233L39");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297002334, "CC(remember):BasicText.kt#9igjgp");
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(textLinkScope);
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    obj3 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$4$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            TextLinkScope it = textLinkScope;
                            return Boolean.valueOf(it != null ? it.getShouldMeasureLinks().invoke().booleanValue() : false);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj3);
                } else {
                    obj3 = objRememberedValue7;
                }
                Function0 function5 = (Function0) obj3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -296999598, "CC(remember):BasicText.kt#9igjgp");
                boolean zChanged3 = composerStartRestartGroup.changed(mutableState);
                Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (zChanged3 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    obj4 = (Function0) new Function0<List<? extends Rect>>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final List<? extends Rect> invoke() {
                            MutableState<List<Rect>> mutableState2 = mutableState;
                            if (mutableState2 != null) {
                                return mutableState2.getValue();
                            }
                            return null;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj4);
                } else {
                    obj4 = objRememberedValue8;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextMeasurePolicy textMeasurePolicy2 = new TextMeasurePolicy(function5, (Function0) obj4);
                composerStartRestartGroup.endReplaceGroup();
                textMeasurePolicy = textMeasurePolicy2;
            } else {
                composerStartRestartGroup.startReplaceGroup(-617362851);
                ComposerKt.sourceInformation(composerStartRestartGroup, "595@22992L55");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -297007294, "CC(remember):BasicText.kt#9igjgp");
                boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(textLinkScope);
                Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    obj5 = (Function0) new Function0<Boolean>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$3$1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final Boolean invoke() {
                            TextLinkScope it = textLinkScope;
                            return Boolean.valueOf(it != null ? it.getShouldMeasureLinks().invoke().booleanValue() : false);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj5);
                } else {
                    obj5 = objRememberedValue9;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                LinksTextMeasurePolicy linksTextMeasurePolicy = new LinksTextMeasurePolicy((Function0) obj5);
                composerStartRestartGroup.endReplaceGroup();
                textMeasurePolicy = linksTextMeasurePolicy;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1333textModifiercFh6CEA);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                function4 = constructor;
                composerStartRestartGroup.createNode(function4);
            } else {
                function4 = constructor;
                composerStartRestartGroup.useNode();
            }
            Composer composerM4027constructorimpl = Updater.m4027constructorimpl(composerStartRestartGroup);
            Updater.m4034setimpl(composerM4027constructorimpl, textMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4034setimpl(composerM4027constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4027constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4027constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4027constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4027constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4034setimpl(composerM4027constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -515487512, "C:BasicText.kt#423gt5");
            if (textLinkScope == null) {
                composerStartRestartGroup.startReplaceGroup(-515480539);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(537560924);
                ComposerKt.sourceInformation(composerStartRestartGroup, "567@21894L18");
                textLinkScope.LinksComposables(composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
                Unit unit = Unit.INSTANCE;
            }
            if (listComponent2 == null) {
                composerStartRestartGroup.startReplaceGroup(-515428893);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-515428892);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*569@21966L48");
                AnnotatedStringResolveInlineContentKt.InlineChildren(annotatedString, listComponent2, composerStartRestartGroup, (i7 >> 3) & 14);
                Unit unit2 = Unit.INSTANCE;
                composerStartRestartGroup.endReplaceGroup();
                Unit unit3 = Unit.INSTANCE;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Map<String, InlineTextContent> map3 = mapEmptyMap;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.BasicTextKt$LayoutWithLinksAndInlineContent$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i12) {
                    BasicTextKt.m1331LayoutWithLinksAndInlineContentvOo2fZY(modifier, annotatedString, function1, z, map3, textStyle, i, z2, i2, i3, resolver, selectionController, colorProducer, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
                }
            });
        }
    }
}
