package androidx.compose.foundation.text;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.LinkInteractionListener;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u001e\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u001fJ>\u0010 \u001a\u00020\t2\u0016\u0010!\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\"\"\u0004\u0018\u00010\u00012\u0017\u0010#\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0003¢\u0006\u0002\u0010$J\r\u0010%\u001a\u00020\u0003H\u0000¢\u0006\u0002\b&J\u0018\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u001c\u0010,\u001a\u0004\u0018\u00010-2\u0010\u0010.\u001a\f\u0012\u0004\u0012\u00020)0/j\u0002`0H\u0002J\u001c\u00101\u001a\u0004\u0018\u0001022\u0010\u0010.\u001a\f\u0012\u0004\u0012\u00020)0/j\u0002`0H\u0002J\u001a\u00103\u001a\u0004\u0018\u000104*\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u000104H\u0002J\u001c\u00106\u001a\u000207*\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u000209H\u0002R%\u0010\u0005\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u0004R/\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00168F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006;"}, d2 = {"Landroidx/compose/foundation/text/TextLinkScope;", "", "initialText", "Landroidx/compose/ui/text/AnnotatedString;", "(Landroidx/compose/ui/text/AnnotatedString;)V", "annotators", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/TextAnnotatorScope;", "", "Lkotlin/ExtensionFunctionType;", "getInitialText$foundation_release", "()Landroidx/compose/ui/text/AnnotatedString;", "shouldMeasureLinks", "Lkotlin/Function0;", "", "getShouldMeasureLinks", "()Lkotlin/jvm/functions/Function0;", "text", "getText$foundation_release", "setText$foundation_release", "<set-?>", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "getTextLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "setTextLayoutResult", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "textLayoutResult$delegate", "Landroidx/compose/runtime/MutableState;", "LinksComposables", "(Landroidx/compose/runtime/Composer;I)V", "StyleAnnotation", "keys", "", "block", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "applyAnnotators", "applyAnnotators$foundation_release", "handleLink", "link", "Landroidx/compose/ui/text/LinkAnnotation;", "uriHandler", "Landroidx/compose/ui/platform/UriHandler;", "pathForRangeInRangeCoordinates", "Landroidx/compose/ui/graphics/Path;", "range", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/foundation/text/LinkRange;", "shapeForRange", "Landroidx/compose/ui/graphics/Shape;", "mergeOrUse", "Landroidx/compose/ui/text/SpanStyle;", "other", "textRange", "Landroidx/compose/ui/Modifier;", "start", "", "end", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextLinkScope {
    public static final int $stable = 8;
    private final SnapshotStateList<Function1<TextAnnotatorScope, Unit>> annotators;
    private final AnnotatedString initialText;
    private AnnotatedString text;

    /* JADX INFO: renamed from: textLayoutResult$delegate, reason: from kotlin metadata */
    private final MutableState textLayoutResult = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    public TextLinkScope(AnnotatedString initialText) {
        SpanStyle style;
        this.initialText = initialText;
        AnnotatedString.Builder builder = new AnnotatedString.Builder(this.initialText);
        List<AnnotatedString.Range<LinkAnnotation>> linkAnnotations = this.initialText.getLinkAnnotations(0, this.initialText.length());
        int size = linkAnnotations.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = linkAnnotations.get(index$iv);
            AnnotatedString.Range<LinkAnnotation> range = (AnnotatedString.Range) item$iv;
            TextLinkStyles styles = range.getItem().getStyles();
            if (styles != null && (style = styles.getStyle()) != null) {
                builder.addStyle(style, range.getStart(), range.getEnd());
            }
        }
        this.text = builder.toAnnotatedString();
        this.annotators = SnapshotStateKt.mutableStateListOf();
    }

    /* JADX INFO: renamed from: getInitialText$foundation_release, reason: from getter */
    public final AnnotatedString getInitialText() {
        return this.initialText;
    }

    public final TextLayoutResult getTextLayoutResult() {
        State $this$getValue$iv = this.textLayoutResult;
        return (TextLayoutResult) $this$getValue$iv.getValue();
    }

    public final void setTextLayoutResult(TextLayoutResult textLayoutResult) {
        MutableState $this$setValue$iv = this.textLayoutResult;
        $this$setValue$iv.setValue(textLayoutResult);
    }

    /* JADX INFO: renamed from: getText$foundation_release, reason: from getter */
    public final AnnotatedString getText() {
        return this.text;
    }

    public final void setText$foundation_release(AnnotatedString annotatedString) {
        this.text = annotatedString;
    }

    public final Function0<Boolean> getShouldMeasureLinks() {
        return new Function0<Boolean>() { // from class: androidx.compose.foundation.text.TextLinkScope$shouldMeasureLinks$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                TextLayoutInput layoutInput;
                AnnotatedString text = this.this$0.getText();
                TextLayoutResult textLayoutResult = this.this$0.getTextLayoutResult();
                return Boolean.valueOf(Intrinsics.areEqual(text, (textLayoutResult == null || (layoutInput = textLayoutResult.getLayoutInput()) == null) ? null : layoutInput.getText()));
            }
        };
    }

    private final Modifier textRange(Modifier $this$textRange, final int start, final int end) {
        return $this$textRange.then(new TextRangeLayoutModifier(new TextRangeScopeMeasurePolicy() { // from class: androidx.compose.foundation.text.TextLinkScope$$ExternalSyntheticLambda0
            @Override // androidx.compose.foundation.text.TextRangeScopeMeasurePolicy
            public final TextRangeLayoutMeasureResult measure(TextRangeLayoutMeasureScope textRangeLayoutMeasureScope) {
                return TextLinkScope.textRange$lambda$3(this.f$0, start, end, textRangeLayoutMeasureScope);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextRangeLayoutMeasureResult textRange$lambda$3(TextLinkScope this$0, int $start, int $end, TextRangeLayoutMeasureScope $this$$receiver) {
        TextLayoutResult layoutResult = this$0.getTextLayoutResult();
        if (layoutResult == null) {
            return $this$$receiver.layout(0, 0, new Function0<IntOffset>() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$layoutResult$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ IntOffset invoke() {
                    return IntOffset.m7135boximpl(m1451invokenOccac());
                }

                /* JADX INFO: renamed from: invoke-nOcc-ac, reason: not valid java name */
                public final long m1451invokenOccac() {
                    return IntOffset.INSTANCE.m7154getZeronOccac();
                }
            });
        }
        final IntRect bounds = IntRectKt.roundToIntRect(layoutResult.getPathForRange($start, $end).getBounds());
        return $this$$receiver.layout(bounds.getWidth(), bounds.getHeight(), new Function0<IntOffset>() { // from class: androidx.compose.foundation.text.TextLinkScope$textRange$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ IntOffset invoke() {
                return IntOffset.m7135boximpl(m1450invokenOccac());
            }

            /* JADX INFO: renamed from: invoke-nOcc-ac, reason: not valid java name */
            public final long m1450invokenOccac() {
                return bounds.m7172getTopLeftnOccac();
            }
        });
    }

    private final Shape shapeForRange(AnnotatedString.Range<LinkAnnotation> range) {
        final Path it = pathForRangeInRangeCoordinates(range);
        return it != null ? new Shape() { // from class: androidx.compose.foundation.text.TextLinkScope$shapeForRange$1$1
            @Override // androidx.compose.ui.graphics.Shape
            /* JADX INFO: renamed from: createOutline-Pq9zytI */
            public Outline mo637createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                return new Outline.Generic(it);
            }
        } : null;
    }

    private final Path pathForRangeInRangeCoordinates(AnnotatedString.Range<LinkAnnotation> range) {
        TextLayoutResult it;
        float xOffset;
        if (!getShouldMeasureLinks().invoke().booleanValue() || (it = getTextLayoutResult()) == null) {
            return null;
        }
        Path path = it.getPathForRange(range.getStart(), range.getEnd());
        Rect firstCharBoundingBox = it.getBoundingBox(range.getStart());
        Rect lastCharBoundingBox = it.getBoundingBox(range.getEnd() - 1);
        int rangeStartLine = it.getLineForOffset(range.getStart());
        int rangeEndLine = it.getLineForOffset(range.getEnd());
        if (rangeStartLine == rangeEndLine) {
            xOffset = Math.min(lastCharBoundingBox.getLeft(), firstCharBoundingBox.getLeft());
        } else {
            xOffset = 0.0f;
        }
        float yOffset = firstCharBoundingBox.getTop();
        path.mo4429translatek4lQ0M(Offset.m4302unaryMinusF1C5BW0(OffsetKt.Offset(xOffset, yOffset)));
        return path;
    }

    public final void LinksComposables(Composer $composer, final int $changed) {
        Modifier clipModifier;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        SpanStyle spanStyle;
        Object value$iv4;
        Object value$iv5;
        Composer $composer2 = $composer.startRestartGroup(1154651354);
        ComposerKt.sourceInformation($composer2, "C(LinksComposables)154@6764L7:TextLinkScope.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(this) ? 4 : 2;
        }
        if (($dirty & 3) == 2 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1154651354, $dirty, -1, "androidx.compose.foundation.text.TextLinkScope.LinksComposables (TextLinkScope.kt:153)");
            }
            ProvidableCompositionLocal<UriHandler> localUriHandler = CompositionLocalsKt.getLocalUriHandler();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localUriHandler);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final UriHandler uriHandler = (UriHandler) objConsume;
            List<AnnotatedString.Range<LinkAnnotation>> linkAnnotations = this.text.getLinkAnnotations(0, this.text.length());
            List<AnnotatedString.Range<LinkAnnotation>> list = linkAnnotations;
            int $i$f$fastForEach = 0;
            int index$iv = 0;
            int size = list.size();
            while (index$iv < size) {
                AnnotatedString.Range<LinkAnnotation> item$iv = list.get(index$iv);
                final AnnotatedString.Range<LinkAnnotation> range = item$iv;
                if (range.getStart() != range.getEnd()) {
                    $composer2.startReplaceGroup(1383677450);
                    ComposerKt.sourceInformation($composer2, "161@7083L39,173@7698L38,163@7140L640");
                    Shape shape = shapeForRange(range);
                    if (shape == null || (clipModifier = ClipKt.clip(Modifier.INSTANCE, shape)) == null) {
                        clipModifier = Modifier.INSTANCE;
                    }
                    ComposerKt.sourceInformationMarkerStart($composer2, -371004613, "CC(remember):TextLinkScope.kt#9igjgp");
                    Object it$iv = $composer2.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    MutableInteractionSource interactionSource = (MutableInteractionSource) value$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(PointerIconKt.pointerHoverIcon$default(HoverableKt.hoverable$default(textRange(clipModifier, range.getStart(), range.getEnd()), interactionSource, false, 2, null), PointerIcon.INSTANCE.getHand(), false, 2, null), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.invisibleToUser($this$semantics);
                        }
                    }, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, -370984934, "CC(remember):TextLinkScope.kt#9igjgp");
                    boolean invalid$iv = $composer2.changedInstance(this) | $composer2.changed(range) | $composer2.changedInstance(uriHandler);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                this.this$0.handleLink(range.getItem(), uriHandler);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    BoxKt.Box(ClickableKt.m623combinedClickableXVZzFYc(modifierSemantics$default, interactionSource, null, (252 & 4) != 0, (252 & 8) != 0 ? null : null, (252 & 16) != 0 ? null : null, (252 & 32) != 0 ? null : null, (252 & 64) != 0 ? null : null, (252 & 128) != 0 ? null : null, (Function0) value$iv2), $composer2, 0);
                    if (TextLinkScopeKt.isNullOrEmpty(range.getItem().getStyles())) {
                        $composer2.startReplaceGroup(1386435086);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(1384566902);
                        ComposerKt.sourceInformation($composer2, "178@7884L49,179@7988L110,179@7954L144,191@8539L1225,183@8120L1644");
                        ComposerKt.sourceInformationMarkerStart($composer2, -370978971, "CC(remember):TextLinkScope.kt#9igjgp");
                        Object it$iv3 = $composer2.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            value$iv3 = new LinkStateInteractionSourceObserver();
                            $composer2.updateRememberedValue(value$iv3);
                        } else {
                            value$iv3 = it$iv3;
                        }
                        final LinkStateInteractionSourceObserver linkStateObserver = (LinkStateInteractionSourceObserver) value$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerStart($composer2, -370975582, "CC(remember):TextLinkScope.kt#9igjgp");
                        Object it$iv4 = $composer2.rememberedValue();
                        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                            spanStyle = null;
                            value$iv4 = (Function2) new TextLinkScope$LinksComposables$1$3$1(linkStateObserver, interactionSource, null);
                            $composer2.updateRememberedValue(value$iv4);
                        } else {
                            spanStyle = null;
                            value$iv4 = it$iv4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv4, $composer2, 6);
                        Boolean boolValueOf = Boolean.valueOf(linkStateObserver.isHovered());
                        Boolean boolValueOf2 = Boolean.valueOf(linkStateObserver.isFocused());
                        Boolean boolValueOf3 = Boolean.valueOf(linkStateObserver.isPressed());
                        TextLinkStyles styles = range.getItem().getStyles();
                        SpanStyle style = styles != null ? styles.getStyle() : spanStyle;
                        TextLinkStyles styles2 = range.getItem().getStyles();
                        SpanStyle focusedStyle = styles2 != null ? styles2.getFocusedStyle() : spanStyle;
                        TextLinkStyles styles3 = range.getItem().getStyles();
                        SpanStyle hoveredStyle = styles3 != null ? styles3.getHoveredStyle() : spanStyle;
                        TextLinkStyles styles4 = range.getItem().getStyles();
                        Object[] objArr = {boolValueOf, boolValueOf2, boolValueOf3, style, focusedStyle, hoveredStyle, styles4 != null ? styles4.getPressedStyle() : spanStyle};
                        ComposerKt.sourceInformationMarkerStart($composer2, -370956835, "CC(remember):TextLinkScope.kt#9igjgp");
                        boolean invalid$iv2 = $composer2.changedInstance(this) | $composer2.changed(range);
                        Object it$iv5 = $composer2.rememberedValue();
                        if (invalid$iv2 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                            value$iv5 = (Function1) new Function1<TextAnnotatorScope, Unit>() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(TextAnnotatorScope textAnnotatorScope) {
                                    invoke2(textAnnotatorScope);
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(TextAnnotatorScope $this$StyleAnnotation) {
                                    TextLinkStyles styles5;
                                    TextLinkStyles styles6;
                                    TextLinkStyles styles7;
                                    TextLinkScope textLinkScope = this.this$0;
                                    TextLinkScope textLinkScope2 = this.this$0;
                                    TextLinkScope textLinkScope3 = this.this$0;
                                    TextLinkStyles styles8 = range.getItem().getStyles();
                                    SpanStyle pressedStyle = null;
                                    SpanStyle style2 = styles8 != null ? styles8.getStyle() : null;
                                    SpanStyle focusedStyle2 = (!linkStateObserver.isFocused() || (styles7 = range.getItem().getStyles()) == null) ? null : styles7.getFocusedStyle();
                                    SpanStyle spanStyleMergeOrUse = textLinkScope3.mergeOrUse(style2, focusedStyle2);
                                    SpanStyle hoveredStyle2 = (!linkStateObserver.isHovered() || (styles6 = range.getItem().getStyles()) == null) ? null : styles6.getHoveredStyle();
                                    SpanStyle spanStyleMergeOrUse2 = textLinkScope2.mergeOrUse(spanStyleMergeOrUse, hoveredStyle2);
                                    if (linkStateObserver.isPressed() && (styles5 = range.getItem().getStyles()) != null) {
                                        pressedStyle = styles5.getPressedStyle();
                                    }
                                    SpanStyle mergedStyle = textLinkScope.mergeOrUse(spanStyleMergeOrUse2, pressedStyle);
                                    if (mergedStyle != null) {
                                        AnnotatedString.Range<LinkAnnotation> range2 = range;
                                        $this$StyleAnnotation.replaceStyle(mergedStyle, range2.getStart(), range2.getEnd());
                                    }
                                }
                            };
                            $composer2.updateRememberedValue(value$iv5);
                        } else {
                            value$iv5 = it$iv5;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        StyleAnnotation(objArr, (Function1) value$iv5, $composer2, ($dirty << 6) & 896);
                        $composer2.endReplaceGroup();
                    }
                    $composer2.endReplaceGroup();
                } else {
                    uriHandler = uriHandler;
                    $composer2.startReplaceGroup(1386448974);
                    $composer2.endReplaceGroup();
                }
                index$iv++;
                $i$f$fastForEach = $i$f$fastForEach;
                linkAnnotations = linkAnnotations;
                size = size;
                list = list;
                uriHandler = uriHandler;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.TextLinkScope.LinksComposables.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TextLinkScope.this.LinksComposables(composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SpanStyle mergeOrUse(SpanStyle $this$mergeOrUse, SpanStyle other) {
        SpanStyle spanStyleMerge;
        return ($this$mergeOrUse == null || (spanStyleMerge = $this$mergeOrUse.merge(other)) == null) ? other : spanStyleMerge;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleLink(LinkAnnotation link, UriHandler uriHandler) {
        LinkInteractionListener linkInteractionListener;
        Unit unit;
        if (!(link instanceof LinkAnnotation.Url)) {
            if (!(link instanceof LinkAnnotation.Clickable) || (linkInteractionListener = link.getLinkInteractionListener()) == null) {
                return;
            }
            linkInteractionListener.onClick(link);
            return;
        }
        LinkInteractionListener linkInteractionListener2 = link.getLinkInteractionListener();
        if (linkInteractionListener2 != null) {
            linkInteractionListener2.onClick(link);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            try {
                uriHandler.openUri(((LinkAnnotation.Url) link).getUrl());
            } catch (IllegalArgumentException e) {
            }
        }
    }

    public final AnnotatedString applyAnnotators$foundation_release() {
        AnnotatedString styledText;
        if (this.annotators.isEmpty()) {
            styledText = this.text;
        } else {
            AnnotatedString.Builder $this$applyAnnotators_u24lambda_u2414 = new AnnotatedString.Builder(0, 1, null);
            $this$applyAnnotators_u24lambda_u2414.append(this.initialText);
            TextAnnotatorScope scope = new TextAnnotatorScope($this$applyAnnotators_u24lambda_u2414);
            List $this$fastForEach$iv = this.annotators;
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                ((Function1) item$iv).invoke(scope);
            }
            styledText = $this$applyAnnotators_u24lambda_u2414.toAnnotatedString();
        }
        this.text = styledText;
        return styledText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void StyleAnnotation(final Object[] keys, final Function1<? super TextAnnotatorScope, Unit> function1, Composer $composer, final int $changed) {
        Object value$iv;
        Composer $composer2 = $composer.startRestartGroup(-2083052099);
        ComposerKt.sourceInformation($composer2, "C(StyleAnnotation)P(1)254@11281L117,254@11250L148:TextLinkScope.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(this) ? 256 : 128;
        }
        $composer2.startMovableGroup(-416686647, Integer.valueOf(keys.length));
        for (Object value : keys) {
            $dirty |= $composer2.changedInstance(value) ? 4 : 0;
        }
        $composer2.endMovableGroup();
        if (($dirty & 14) == 0) {
            $dirty |= 2;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2083052099, $dirty, -1, "androidx.compose.foundation.text.TextLinkScope.StyleAnnotation (TextLinkScope.kt:253)");
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(function1);
            spreadBuilder.addSpread(keys);
            Object[] array = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
            ComposerKt.sourceInformationMarkerStart($composer2, -416683411, "CC(remember):TextLinkScope.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(this) | (($dirty & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
                        this.this$0.annotators.add(function1);
                        final TextLinkScope textLinkScope = this.this$0;
                        final Function1<TextAnnotatorScope, Unit> function2 = function1;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public void dispose() {
                                textLinkScope.annotators.remove(function2);
                            }
                        };
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(array, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.TextLinkScope.StyleAnnotation.2
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

                public final void invoke(Composer composer, int i) {
                    TextLinkScope.this.StyleAnnotation(Arrays.copyOf(keys, keys.length), function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
