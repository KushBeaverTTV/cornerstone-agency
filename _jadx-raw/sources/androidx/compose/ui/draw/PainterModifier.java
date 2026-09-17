package androidx.compose.ui.draw;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: androidx.compose.ui.draw.PainterNode, reason: from toString */
/* JADX INFO: compiled from: PainterModifier.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u001a\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u001a\u00102\u001a\u0002032\u0006\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00101J\b\u00106\u001a\u000207H\u0016J\f\u00108\u001a\u000209*\u00020:H\u0016J\u0016\u0010;\u001a\u00020\u0007*\u00020.H\u0002ø\u0001\u0000¢\u0006\u0004\b<\u0010=J\u0016\u0010>\u001a\u00020\u0007*\u00020.H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010=J\u001c\u0010@\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020AH\u0016J\u001c\u0010F\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020AH\u0016J&\u0010H\u001a\u00020I*\u00020J2\u0006\u0010C\u001a\u00020K2\u0006\u00104\u001a\u000203H\u0016ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ\u001c\u0010N\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020AH\u0016J\u001c\u0010O\u001a\u00020A*\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010G\u001a\u00020AH\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010'\"\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010'\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006P"}, d2 = {"Landroidx/compose/ui/draw/PainterNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "sizeToIntrinsics", "", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/painter/Painter;ZLandroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "getAlpha", "()F", "setAlpha", "(F)V", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getContentScale", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale", "(Landroidx/compose/ui/layout/ContentScale;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "getSizeToIntrinsics", "setSizeToIntrinsics", "(Z)V", "useIntrinsicSize", "getUseIntrinsicSize", "calculateScaledSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "modifyConstraints", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "toString", "", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "hasSpecifiedAndFiniteHeight", "hasSpecifiedAndFiniteHeight-uvyYCjk", "(J)Z", "hasSpecifiedAndFiniteWidth", "hasSpecifiedAndFiniteWidth-uvyYCjk", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PainterModifier extends Modifier.Node implements LayoutModifierNode, DrawModifierNode {
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;
    private boolean sizeToIntrinsics;

    public /* synthetic */ PainterModifier(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(painter, z, (i & 4) != 0 ? Alignment.INSTANCE.getCenter() : alignment, (i & 8) != 0 ? ContentScale.INSTANCE.getInside() : contentScale, (i & 16) != 0 ? 1.0f : f, (i & 32) != 0 ? null : colorFilter);
    }

    public final Painter getPainter() {
        return this.painter;
    }

    public final void setPainter(Painter painter) {
        this.painter = painter;
    }

    public final boolean getSizeToIntrinsics() {
        return this.sizeToIntrinsics;
    }

    public final void setSizeToIntrinsics(boolean z) {
        this.sizeToIntrinsics = z;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final void setContentScale(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public PainterModifier(Painter painter, boolean sizeToIntrinsics, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter) {
        this.painter = painter;
        this.sizeToIntrinsics = sizeToIntrinsics;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = alpha;
        this.colorFilter = colorFilter;
    }

    private final boolean getUseIntrinsicSize() {
        if (!this.sizeToIntrinsics) {
            return false;
        }
        long $this$isSpecified$iv = this.painter.getIntrinsicSize();
        return (($this$isSpecified$iv > InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : ($this$isSpecified$iv == InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? 1 : 0) != 0;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo444measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo5870measureBRTryo0(m4188modifyConstraintsZezNO4M(constraints));
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.draw.PainterNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        if (getUseIntrinsicSize()) {
            long constraints = m4188modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, height, 7, null));
            int layoutWidth = measurable.minIntrinsicWidth(height);
            return Math.max(Constraints.m6971getMinWidthimpl(constraints), layoutWidth);
        }
        return measurable.minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        if (getUseIntrinsicSize()) {
            long constraints = m4188modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, height, 7, null));
            int layoutWidth = measurable.maxIntrinsicWidth(height);
            return Math.max(Constraints.m6971getMinWidthimpl(constraints), layoutWidth);
        }
        return measurable.maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        if (getUseIntrinsicSize()) {
            long constraints = m4188modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, width, 0, 0, 13, null));
            int layoutHeight = measurable.minIntrinsicHeight(width);
            return Math.max(Constraints.m6970getMinHeightimpl(constraints), layoutHeight);
        }
        return measurable.minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        if (getUseIntrinsicSize()) {
            long constraints = m4188modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, width, 0, 0, 13, null));
            int layoutHeight = measurable.maxIntrinsicHeight(width);
            return Math.max(Constraints.m6970getMinHeightimpl(constraints), layoutHeight);
        }
        return measurable.maxIntrinsicHeight(width);
    }

    /* JADX INFO: renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m4185calculateScaledSizeE7KxVPU(long dstSize) {
        float srcWidth;
        float srcHeight;
        if (!getUseIntrinsicSize()) {
            return dstSize;
        }
        if (!m4187hasSpecifiedAndFiniteWidthuvyYCjk(this.painter.getIntrinsicSize())) {
            srcWidth = Size.m4362getWidthimpl(dstSize);
        } else {
            srcWidth = Size.m4362getWidthimpl(this.painter.getIntrinsicSize());
        }
        if (!m4186hasSpecifiedAndFiniteHeightuvyYCjk(this.painter.getIntrinsicSize())) {
            srcHeight = Size.m4359getHeightimpl(dstSize);
        } else {
            srcHeight = Size.m4359getHeightimpl(this.painter.getIntrinsicSize());
        }
        long srcSize = SizeKt.Size(srcWidth, srcHeight);
        if (!(Size.m4362getWidthimpl(dstSize) == 0.0f)) {
            if (!(Size.m4359getHeightimpl(dstSize) == 0.0f)) {
                return ScaleFactorKt.m5974timesUQTWf7w(srcSize, this.contentScale.mo5861computeScaleFactorH7hwNQA(srcSize, dstSize));
            }
        }
        return Size.INSTANCE.m4371getZeroNHjbRc();
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m4188modifyConstraintsZezNO4M(long constraints) {
        int iM6971getMinWidthimpl;
        int iM6970getMinHeightimpl;
        boolean hasBoundedDimens = Constraints.m6965getHasBoundedWidthimpl(constraints) && Constraints.m6964getHasBoundedHeightimpl(constraints);
        boolean hasFixedDimens = Constraints.m6967getHasFixedWidthimpl(constraints) && Constraints.m6966getHasFixedHeightimpl(constraints);
        if ((!getUseIntrinsicSize() && hasBoundedDimens) || hasFixedDimens) {
            return Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : Constraints.m6969getMaxWidthimpl(constraints), (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : Constraints.m6968getMaxHeightimpl(constraints), (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
        }
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (m4187hasSpecifiedAndFiniteWidthuvyYCjk(intrinsicSize)) {
            float $this$fastRoundToInt$iv = Size.m4362getWidthimpl(intrinsicSize);
            iM6971getMinWidthimpl = Math.round($this$fastRoundToInt$iv);
        } else {
            iM6971getMinWidthimpl = Constraints.m6971getMinWidthimpl(constraints);
        }
        int intrinsicWidth = iM6971getMinWidthimpl;
        if (m4186hasSpecifiedAndFiniteHeightuvyYCjk(intrinsicSize)) {
            float $this$fastRoundToInt$iv2 = Size.m4359getHeightimpl(intrinsicSize);
            iM6970getMinHeightimpl = Math.round($this$fastRoundToInt$iv2);
        } else {
            iM6970getMinHeightimpl = Constraints.m6970getMinHeightimpl(constraints);
        }
        int intrinsicHeight = iM6970getMinHeightimpl;
        int constrainedWidth = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, intrinsicWidth);
        int constrainedHeight = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, intrinsicHeight);
        long scaledSize = m4185calculateScaledSizeE7KxVPU(SizeKt.Size(constrainedWidth, constrainedHeight));
        float $this$fastRoundToInt$iv3 = Size.m4362getWidthimpl(scaledSize);
        int minWidth = ConstraintsKt.m6986constrainWidthK40F9xA(constraints, Math.round($this$fastRoundToInt$iv3));
        float $this$fastRoundToInt$iv4 = Size.m4359getHeightimpl(scaledSize);
        int minHeight = ConstraintsKt.m6985constrainHeightK40F9xA(constraints, Math.round($this$fastRoundToInt$iv4));
        return Constraints.m6959copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m6971getMinWidthimpl(constraints) : minWidth, (11 & 2) != 0 ? Constraints.m6969getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m6970getMinHeightimpl(constraints) : minHeight, (11 & 8) != 0 ? Constraints.m6968getMaxHeightimpl(constraints) : 0);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0068  */
    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) throws Throwable {
        float fM4362getWidthimpl;
        float fM4359getHeightimpl;
        long scaledSize;
        float dy;
        float dx;
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (m4187hasSpecifiedAndFiniteWidthuvyYCjk(intrinsicSize)) {
            fM4362getWidthimpl = Size.m4362getWidthimpl(intrinsicSize);
        } else {
            fM4362getWidthimpl = Size.m4362getWidthimpl($this$draw.mo5090getSizeNHjbRc());
        }
        float srcWidth = fM4362getWidthimpl;
        if (m4186hasSpecifiedAndFiniteHeightuvyYCjk(intrinsicSize)) {
            fM4359getHeightimpl = Size.m4359getHeightimpl(intrinsicSize);
        } else {
            fM4359getHeightimpl = Size.m4359getHeightimpl($this$draw.mo5090getSizeNHjbRc());
        }
        float srcHeight = fM4359getHeightimpl;
        long srcSize = SizeKt.Size(srcWidth, srcHeight);
        if (Size.m4362getWidthimpl($this$draw.mo5090getSizeNHjbRc()) == 0.0f) {
            scaledSize = Size.INSTANCE.m4371getZeroNHjbRc();
        } else {
            if (!(Size.m4359getHeightimpl($this$draw.mo5090getSizeNHjbRc()) == 0.0f)) {
                scaledSize = ScaleFactorKt.m5974timesUQTWf7w(srcSize, this.contentScale.mo5861computeScaleFactorH7hwNQA(srcSize, $this$draw.mo5090getSizeNHjbRc()));
            } else {
                scaledSize = Size.INSTANCE.m4371getZeroNHjbRc();
            }
        }
        Alignment alignment = this.alignment;
        float $this$fastRoundToInt$iv = Size.m4362getWidthimpl(scaledSize);
        int iRound = Math.round($this$fastRoundToInt$iv);
        float $this$fastRoundToInt$iv2 = Size.m4359getHeightimpl(scaledSize);
        long jIntSize = IntSizeKt.IntSize(iRound, Math.round($this$fastRoundToInt$iv2));
        float $this$fastRoundToInt$iv3 = Size.m4362getWidthimpl($this$draw.mo5090getSizeNHjbRc());
        int iRound2 = Math.round($this$fastRoundToInt$iv3);
        float $this$fastRoundToInt$iv4 = Size.m4359getHeightimpl($this$draw.mo5090getSizeNHjbRc());
        long alignedPosition = alignment.mo4139alignKFBX0sM(jIntSize, IntSizeKt.IntSize(iRound2, Math.round($this$fastRoundToInt$iv4)), $this$draw.getLayoutDirection());
        float dx2 = IntOffset.m7144getXimpl(alignedPosition);
        float dy2 = IntOffset.m7145getYimpl(alignedPosition);
        ContentDrawScope $this$translate$iv = $this$draw;
        $this$translate$iv.getDrawContext().getTransform().translate(dx2, dy2);
        try {
            Painter $this$draw_u24lambda_u241_u24lambda_u240 = this.painter;
            try {
                dy = dy2;
                dx = dx2;
                try {
                    $this$draw_u24lambda_u241_u24lambda_u240.m5217drawx_KDEd0($this$translate$iv, scaledSize, this.alpha, this.colorFilter);
                    $this$translate$iv.getDrawContext().getTransform().translate(-dx, -dy);
                    $this$draw.drawContent();
                } catch (Throwable th) {
                    th = th;
                    $this$translate$iv.getDrawContext().getTransform().translate(-dx, -dy);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                dy = dy2;
                dx = dx2;
            }
        } catch (Throwable th3) {
            th = th3;
            dy = dy2;
            dx = dx2;
        }
    }

    /* JADX INFO: renamed from: hasSpecifiedAndFiniteWidth-uvyYCjk, reason: not valid java name */
    private final boolean m4187hasSpecifiedAndFiniteWidthuvyYCjk(long $this$hasSpecifiedAndFiniteWidth_u2duvyYCjk) {
        if (Size.m4358equalsimpl0($this$hasSpecifiedAndFiniteWidth_u2duvyYCjk, Size.INSTANCE.m4370getUnspecifiedNHjbRc())) {
            return false;
        }
        float fM4362getWidthimpl = Size.m4362getWidthimpl($this$hasSpecifiedAndFiniteWidth_u2duvyYCjk);
        return !Float.isInfinite(fM4362getWidthimpl) && !Float.isNaN(fM4362getWidthimpl);
    }

    /* JADX INFO: renamed from: hasSpecifiedAndFiniteHeight-uvyYCjk, reason: not valid java name */
    private final boolean m4186hasSpecifiedAndFiniteHeightuvyYCjk(long $this$hasSpecifiedAndFiniteHeight_u2duvyYCjk) {
        if (Size.m4358equalsimpl0($this$hasSpecifiedAndFiniteHeight_u2duvyYCjk, Size.INSTANCE.m4370getUnspecifiedNHjbRc())) {
            return false;
        }
        float fM4359getHeightimpl = Size.m4359getHeightimpl($this$hasSpecifiedAndFiniteHeight_u2duvyYCjk);
        return !Float.isInfinite(fM4359getHeightimpl) && !Float.isNaN(fM4359getHeightimpl);
    }

    public String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.sizeToIntrinsics + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }
}
