package androidx.compose.ui.graphics.layer;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;

/* JADX INFO: compiled from: GraphicsLayer.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\b\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"DefaultCameraDistance", "", "drawLayer", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "setOutline", "outline", "Landroidx/compose/ui/graphics/Outline;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GraphicsLayerKt {
    public static final float DefaultCameraDistance = 8.0f;

    public static final void drawLayer(DrawScope $this$drawLayer, GraphicsLayer graphicsLayer) {
        Canvas canvas = $this$drawLayer.getDrawContext().getCanvas();
        graphicsLayer.draw$ui_graphics_release(canvas, $this$drawLayer.getDrawContext().getGraphicsLayer());
    }

    public static final void setOutline(GraphicsLayer $this$setOutline, Outline outline) {
        if (outline instanceof Outline.Rectangle) {
            $this$setOutline.m5189setRectOutlinetz77jQw(OffsetKt.Offset(((Outline.Rectangle) outline).getRect().getLeft(), ((Outline.Rectangle) outline).getRect().getTop()), SizeKt.Size(((Outline.Rectangle) outline).getRect().getWidth(), ((Outline.Rectangle) outline).getRect().getHeight()));
            return;
        }
        if (!(outline instanceof Outline.Generic)) {
            if (outline instanceof Outline.Rounded) {
                if (((Outline.Rounded) outline).getRoundRectPath() != null) {
                    $this$setOutline.setPathOutline(((Outline.Rounded) outline).getRoundRectPath());
                    return;
                } else {
                    RoundRect rr = ((Outline.Rounded) outline).getRoundRect();
                    $this$setOutline.m5190setRoundRectOutlineTNW_H78(OffsetKt.Offset(rr.getLeft(), rr.getTop()), SizeKt.Size(rr.getWidth(), rr.getHeight()), CornerRadius.m4268getXimpl(rr.m4341getBottomLeftCornerRadiuskKHJgLs()));
                    return;
                }
            }
            return;
        }
        $this$setOutline.setPathOutline(((Outline.Generic) outline).getPath());
    }
}
