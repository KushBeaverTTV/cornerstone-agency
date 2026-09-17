package androidx.compose.ui.graphics.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RectF;
import android.os.Build;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidGraphicsLayer.android.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 º\u00012\u00020\u0001:\u0002º\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0088\u0001\u001a\u00020,2\u0007\u0010\u0089\u0001\u001a\u00020\u0000H\u0002J\t\u0010\u008a\u0001\u001a\u00020,H\u0002J\t\u0010\u008b\u0001\u001a\u00020,H\u0002J\u000f\u0010\u008c\u0001\u001a\u00020,H\u0000¢\u0006\u0003\b\u008d\u0001J$\u0010\u008e\u0001\u001a\u00020,2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0003\b\u0092\u0001J\u0019\u0010\u0093\u0001\u001a\u00020,2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0000¢\u0006\u0003\b\u0094\u0001J\u000f\u0010\u0095\u0001\u001a\u00020,H\u0001¢\u0006\u0003\b\u0096\u0001J\t\u0010\u0097\u0001\u001a\u00020\u0015H\u0002J\t\u0010\u0098\u0001\u001a\u00020QH\u0002J\t\u0010\u0099\u0001\u001a\u00020,H\u0002J\t\u0010\u009a\u0001\u001a\u00020,H\u0002JE\u0010\u009b\u0001\u001a\u00020,2\u0006\u00108\u001a\u0002092\u0006\u0010D\u001a\u00020E2\u0006\u0010u\u001a\u00020t2\u0018\u0010\u009c\u0001\u001a\u0013\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*¢\u0006\u0002\b-ø\u0001\u0000¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001J\t\u0010\u009f\u0001\u001a\u00020,H\u0002J\t\u0010 \u0001\u001a\u00020,H\u0002J\u000f\u0010¡\u0001\u001a\u00020,H\u0000¢\u0006\u0003\b¢\u0001J\t\u0010£\u0001\u001a\u00020,H\u0002J5\u0010¤\u0001\u001a\u0003H¥\u0001\"\u0005\b\u0000\u0010¥\u00012\u001b\u0010\u009c\u0001\u001a\u0016\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020i\u0012\u0005\u0012\u0003H¥\u00010¦\u0001H\u0082\b¢\u0006\u0003\u0010§\u0001J\u0010\u0010¨\u0001\u001a\u00020,2\u0007\u0010©\u0001\u001a\u00020KJ%\u0010ª\u0001\u001a\u00020,2\u0006\u0010~\u001a\u00020}2\u0006\u0010u\u001a\u00020tH\u0002ø\u0001\u0000¢\u0006\u0006\b«\u0001\u0010¬\u0001J'\u0010\u00ad\u0001\u001a\u00020,2\b\b\u0002\u0010~\u001a\u00020R2\b\b\u0002\u0010u\u001a\u00020iø\u0001\u0000¢\u0006\u0006\b®\u0001\u0010¬\u0001J2\u0010¯\u0001\u001a\u00020,2\b\b\u0002\u0010~\u001a\u00020R2\b\b\u0002\u0010u\u001a\u00020i2\t\b\u0002\u0010°\u0001\u001a\u00020\bø\u0001\u0000¢\u0006\u0006\b±\u0001\u0010²\u0001J\u0011\u0010³\u0001\u001a\u00030´\u0001H\u0086@¢\u0006\u0003\u0010µ\u0001J\u0013\u0010¶\u0001\u001a\u00020,2\b\u0010·\u0001\u001a\u00030¸\u0001H\u0002J\u0014\u0010¹\u0001\u001a\u0004\u0018\u00010\u00152\u0007\u0010©\u0001\u001a\u00020KH\u0002R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00168F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u000b\"\u0004\b\u001e\u0010\rR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\"\u001a\u00020!2\u0006\u0010\u0007\u001a\u00020!8F@FX\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001f\u0010)\u001a\u0013\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*¢\u0006\u0002\b-X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010/\u001a\u0004\u0018\u00010.2\b\u0010\u0007\u001a\u0004\u0018\u00010.8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R*\u00105\u001a\u0002042\u0006\u0010\u0007\u001a\u0002048F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b6\u0010\u0019\"\u0004\b7\u0010\u001bR\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010:\u001a\u0013\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*¢\u0006\u0002\b-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010@\u001a\u00020!2\u0006\u0010?\u001a\u00020!@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b@\u0010&R\u0011\u0010A\u001a\u00020B8F¢\u0006\u0006\u001a\u0004\bC\u0010\u0011R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010F\u001a\u00020>8F¢\u0006\u0006\u001a\u0004\bG\u0010HR\u000e\u0010I\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010L\u001a\u00020B8F¢\u0006\u0006\u001a\u0004\bM\u0010\u0011R\u000e\u0010N\u001a\u00020OX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010P\u001a\u0004\u0018\u00010QX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010S\u001a\u00020R2\u0006\u0010\u0007\u001a\u00020R@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010V\u001a\u0004\bT\u0010\u0011\"\u0004\bU\u0010\u0013R(\u0010X\u001a\u0004\u0018\u00010W2\b\u0010\u0007\u001a\u0004\u0018\u00010W8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R$\u0010]\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b^\u0010\u000b\"\u0004\b_\u0010\rR$\u0010`\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\ba\u0010\u000b\"\u0004\bb\u0010\rR$\u0010c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bd\u0010\u000b\"\u0004\be\u0010\rR\u0010\u0010f\u001a\u0004\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010h\u001a\u00020iX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010VR\u0016\u0010j\u001a\u00020RX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010VR$\u0010k\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bl\u0010\u000b\"\u0004\bm\u0010\rR$\u0010n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bo\u0010\u000b\"\u0004\bp\u0010\rR$\u0010q\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\br\u0010\u000b\"\u0004\bs\u0010\rR,\u0010u\u001a\u00020t2\u0006\u0010\u0007\u001a\u00020t@BX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010V\u001a\u0004\bv\u0010\u0011\"\u0004\bw\u0010\u0013R\u0010\u0010x\u001a\u0004\u0018\u00010yX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010z\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8F@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b{\u0010\u0011\"\u0004\b|\u0010\u0013R-\u0010~\u001a\u00020}2\u0006\u0010\u0007\u001a\u00020}@FX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0011\n\u0002\u0010V\u001a\u0004\b\u007f\u0010\u0011\"\u0005\b\u0080\u0001\u0010\u0013R'\u0010\u0081\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0082\u0001\u0010\u000b\"\u0005\b\u0083\u0001\u0010\rR'\u0010\u0084\u0001\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u0085\u0001\u0010\u000b\"\u0005\b\u0086\u0001\u0010\rR\u000f\u0010\u0087\u0001\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006»\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "", "impl", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "layerManager", "Landroidx/compose/ui/graphics/layer/LayerManager;", "(Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;Landroidx/compose/ui/graphics/layer/LayerManager;)V", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "androidOutline", "Landroid/graphics/Outline;", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "cameraDistance", "getCameraDistance", "setCameraDistance", "childDependenciesTracker", "Landroidx/compose/ui/graphics/layer/ChildLayerDependenciesTracker;", "", "clip", "getClip$annotations", "()V", "getClip", "()Z", "setClip", "(Z)V", "clipDrawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "setCompositingStrategy-Wpw9cng", "density", "Landroidx/compose/ui/unit/Density;", "drawBlock", "getImpl$ui_graphics_release", "()Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "internalOutline", "Landroidx/compose/ui/graphics/Outline;", "<set-?>", "isReleased", "layerId", "", "getLayerId", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "outline", "getOutline", "()Landroidx/compose/ui/graphics/Outline;", "outlineDirty", "outlinePath", "Landroidx/compose/ui/graphics/Path;", "ownerViewId", "getOwnerViewId", "parentLayerUsages", "", "pathBounds", "Landroid/graphics/RectF;", "Landroidx/compose/ui/geometry/Offset;", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "J", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "roundRectClipPath", "roundRectCornerRadius", "roundRectOutlineSize", "Landroidx/compose/ui/geometry/Size;", "roundRectOutlineTopLeft", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "Landroidx/compose/ui/unit/IntSize;", "size", "getSize-YbymL2g", "setSize-ozmzZPI", "softwareLayerPaint", "Landroidx/compose/ui/graphics/Paint;", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "Landroidx/compose/ui/unit/IntOffset;", "topLeft", "getTopLeft-nOcc-ac", "setTopLeft--gyyYBs", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "usePathForClip", "addSubLayer", "graphicsLayer", "configureOutlineAndClip", "discardContentIfReleasedAndHaveNoParentLayerUsages", "discardDisplayList", "discardDisplayList$ui_graphics_release", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "parentLayer", "draw$ui_graphics_release", "drawForPersistence", "drawForPersistence$ui_graphics_release", "emulateTrimMemory", "emulateTrimMemory$ui_graphics_release", "obtainAndroidOutline", "obtainPathBounds", "onAddedToParentLayer", "onRemovedFromParentLayer", "record", "block", "record-mL-hObY", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;JLkotlin/jvm/functions/Function1;)V", "recordInternal", "recreateDisplayListIfNeeded", "release", "release$ui_graphics_release", "resetOutlineParams", "resolveOutlinePosition", "T", "Lkotlin/Function2;", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "setPathOutline", "path", "setPosition", "setPosition-VbeCjmY", "(JJ)V", "setRectOutline", "setRectOutline-tz77jQw", "setRoundRectOutline", "cornerRadius", "setRoundRectOutline-TNW_H78", "(JJF)V", "toImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformCanvas", "androidCanvas", "Landroid/graphics/Canvas;", "updatePathOutline", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GraphicsLayer {
    private static final LayerSnapshotImpl SnapshotImpl;
    private Outline androidOutline;
    private boolean clip;
    private final GraphicsLayerImpl impl;
    private androidx.compose.ui.graphics.Outline internalOutline;
    private boolean isReleased;
    private final LayerManager layerManager;
    private Path outlinePath;
    private int parentLayerUsages;
    private RectF pathBounds;
    private long pivotOffset;
    private Path roundRectClipPath;
    private float roundRectCornerRadius;
    private long size;
    private Paint softwareLayerPaint;
    private long topLeft;
    private boolean usePathForClip;
    private Density density = DrawContextKt.getDefaultDensity();
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private Function1<? super DrawScope, Unit> drawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$drawBlock$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
            invoke2(drawScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DrawScope $this$null) {
        }
    };
    private final Function1<DrawScope, Unit> clipDrawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$clipDrawBlock$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
            invoke2(drawScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DrawScope $this$null) {
            Path path = this.this$0.outlinePath;
            if (!this.this$0.usePathForClip || !this.this$0.getClip() || path == null) {
                this.this$0.drawBlock.invoke($this$null);
                return;
            }
            Function1 block$iv = this.this$0.drawBlock;
            int clipOp$iv = ClipOp.INSTANCE.m4523getIntersectrtfAjoo();
            DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$null.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5011getSizeNHjbRc();
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$clipPath_KD09W0M_u24lambda_u245$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                $this$clipPath_KD09W0M_u24lambda_u245$iv.mo5013clipPathmtrdDE(path, clipOp$iv);
                block$iv.invoke($this$null);
            } finally {
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5012setSizeuvyYCjk(previousSize$iv$iv);
            }
        }
    };
    private boolean outlineDirty = true;
    private long roundRectOutlineTopLeft = Offset.INSTANCE.m4309getZeroF1C5BW0();
    private long roundRectOutlineSize = Size.INSTANCE.m4370getUnspecifiedNHjbRc();
    private final ChildLayerDependenciesTracker childDependenciesTracker = new ChildLayerDependenciesTracker();

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidGraphicsLayer.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.graphics.layer.GraphicsLayer", f = "AndroidGraphicsLayer.android.kt", i = {}, l = {864}, m = "toImageBitmap", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GraphicsLayer.this.toImageBitmap(this);
        }
    }

    public static /* synthetic */ void getClip$annotations() {
    }

    public GraphicsLayer(GraphicsLayerImpl impl, LayerManager layerManager) {
        this.impl = impl;
        this.layerManager = layerManager;
        this.impl.setClip(false);
        this.topLeft = IntOffset.INSTANCE.m7154getZeronOccac();
        this.size = IntSize.INSTANCE.m7191getZeroYbymL2g();
        this.pivotOffset = Offset.INSTANCE.m4308getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: getImpl$ui_graphics_release, reason: from getter */
    public final GraphicsLayerImpl getImpl() {
        return this.impl;
    }

    /* JADX INFO: renamed from: isReleased, reason: from getter */
    public final boolean getIsReleased() {
        return this.isReleased;
    }

    /* JADX INFO: renamed from: getCompositingStrategy-ke2Ky5w, reason: not valid java name */
    public final int m5179getCompositingStrategyke2Ky5w() {
        return this.impl.getCompositingStrategy();
    }

    /* JADX INFO: renamed from: setCompositingStrategy-Wpw9cng, reason: not valid java name */
    public final void m5187setCompositingStrategyWpw9cng(int value) {
        if (!CompositingStrategy.m5166equalsimpl0(this.impl.getCompositingStrategy(), value)) {
            this.impl.mo5200setCompositingStrategyWpw9cng(value);
        }
    }

    /* JADX INFO: renamed from: getTopLeft-nOcc-ac, reason: not valid java name and from getter */
    public final long getTopLeft() {
        return this.topLeft;
    }

    /* JADX INFO: renamed from: setTopLeft--gyyYBs, reason: not valid java name */
    public final void m5192setTopLeftgyyYBs(long value) {
        if (!IntOffset.m7143equalsimpl0(this.topLeft, value)) {
            this.topLeft = value;
            m5173setPositionVbeCjmY(value, this.size);
        }
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: setSize-ozmzZPI, reason: not valid java name */
    private final void m5176setSizeozmzZPI(long value) {
        if (!IntSize.m7184equalsimpl0(this.size, value)) {
            this.size = value;
            m5173setPositionVbeCjmY(this.topLeft, value);
            long $this$isUnspecified$iv = this.roundRectOutlineSize;
            if ($this$isUnspecified$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
                this.outlineDirty = true;
                configureOutlineAndClip();
            }
        }
    }

    public final float getAlpha() {
        return this.impl.getAlpha();
    }

    public final void setAlpha(float value) {
        if (!(this.impl.getAlpha() == value)) {
            this.impl.setAlpha(value);
        }
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m5178getBlendMode0nO6VwU() {
        return this.impl.getBlendMode();
    }

    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m5186setBlendModes9anfk8(int value) {
        if (!BlendMode.m4445equalsimpl0(this.impl.getBlendMode(), value)) {
            this.impl.mo5199setBlendModes9anfk8(value);
        }
    }

    public final ColorFilter getColorFilter() {
        return this.impl.getColorFilter();
    }

    public final void setColorFilter(ColorFilter value) {
        if (!Intrinsics.areEqual(this.impl.getColorFilter(), value)) {
            this.impl.setColorFilter(value);
        }
    }

    /* JADX INFO: renamed from: getPivotOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getPivotOffset() {
        return this.pivotOffset;
    }

    /* JADX INFO: renamed from: setPivotOffset-k-4lQ0M, reason: not valid java name */
    public final void m5188setPivotOffsetk4lQ0M(long value) {
        if (!Offset.m4290equalsimpl0(this.pivotOffset, value)) {
            this.pivotOffset = value;
            this.impl.mo5202setPivotOffsetk4lQ0M(value);
        }
    }

    public final float getScaleX() {
        return this.impl.getScaleX();
    }

    public final void setScaleX(float value) {
        if (!(this.impl.getScaleX() == value)) {
            this.impl.setScaleX(value);
        }
    }

    public final float getScaleY() {
        return this.impl.getScaleY();
    }

    public final void setScaleY(float value) {
        if (!(this.impl.getScaleY() == value)) {
            this.impl.setScaleY(value);
        }
    }

    public final float getTranslationX() {
        return this.impl.getTranslationX();
    }

    public final void setTranslationX(float value) {
        if (!(this.impl.getTranslationX() == value)) {
            this.impl.setTranslationX(value);
        }
    }

    public final float getTranslationY() {
        return this.impl.getTranslationY();
    }

    public final void setTranslationY(float value) {
        if (!(this.impl.getTranslationY() == value)) {
            this.impl.setTranslationY(value);
        }
    }

    public final float getShadowElevation() {
        return this.impl.getShadowElevation();
    }

    public final void setShadowElevation(float value) {
        if (!(this.impl.getShadowElevation() == value)) {
            this.impl.setShadowElevation(value);
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final float getRotationX() {
        return this.impl.getRotationX();
    }

    public final void setRotationX(float value) {
        if (!(this.impl.getRotationX() == value)) {
            this.impl.setRotationX(value);
        }
    }

    public final float getRotationY() {
        return this.impl.getRotationY();
    }

    public final void setRotationY(float value) {
        if (!(this.impl.getRotationY() == value)) {
            this.impl.setRotationY(value);
        }
    }

    public final float getRotationZ() {
        return this.impl.getRotationZ();
    }

    public final void setRotationZ(float value) {
        if (!(this.impl.getRotationZ() == value)) {
            this.impl.setRotationZ(value);
        }
    }

    public final float getCameraDistance() {
        return this.impl.getCameraDistance();
    }

    public final void setCameraDistance(float value) {
        if (!(this.impl.getCameraDistance() == value)) {
            this.impl.setCameraDistance(value);
        }
    }

    public final boolean getClip() {
        return this.clip;
    }

    public final void setClip(boolean value) {
        if (this.clip != value) {
            this.clip = value;
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final RenderEffect getRenderEffect() {
        return this.impl.getRenderEffect();
    }

    public final void setRenderEffect(RenderEffect value) {
        if (!Intrinsics.areEqual(this.impl.getRenderEffect(), value)) {
            this.impl.setRenderEffect(value);
        }
    }

    /* JADX INFO: renamed from: setPosition-VbeCjmY, reason: not valid java name */
    private final void m5173setPositionVbeCjmY(long topLeft, long size) {
        this.impl.mo5203setPositionH0pRuoY(IntOffset.m7144getXimpl(topLeft), IntOffset.m7145getYimpl(topLeft), size);
    }

    /* JADX INFO: renamed from: record-mL-hObY, reason: not valid java name */
    public final void m5184recordmLhObY(Density density, LayoutDirection layoutDirection, long size, Function1<? super DrawScope, Unit> block) {
        m5176setSizeozmzZPI(size);
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.drawBlock = block;
        this.impl.setInvalidated(true);
        recordInternal();
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00c5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x00c7 A[LOOP:0: B:20:0x0075->B:33:0x00c7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x00d3 A[EDGE_INSN: B:42:0x00d3->B:36:0x00d3 BREAK  A[LOOP:0: B:20:0x0075->B:33:0x00c7], SYNTHETIC] */
    private final void recordInternal() {
        ChildLayerDependenciesTracker this_$iv = this.childDependenciesTracker;
        this_$iv.oldDependency = this_$iv.dependency;
        MutableScatterSet currentSet$iv = this_$iv.dependenciesSet;
        if (currentSet$iv != null && currentSet$iv.isNotEmpty()) {
            MutableScatterSet it$iv = this_$iv.oldDependenciesSet;
            if (it$iv == null) {
                it$iv = ScatterSetKt.mutableScatterSetOf();
                this_$iv.oldDependenciesSet = it$iv;
            }
            it$iv.addAll(currentSet$iv);
            currentSet$iv.clear();
        }
        this_$iv.trackingInProgress = true;
        this.impl.record(this.density, this.layoutDirection, this, this.clipDrawBlock);
        this_$iv.trackingInProgress = false;
        GraphicsLayer it = this_$iv.oldDependency;
        if (it != null) {
            it.onRemovedFromParentLayer();
        }
        MutableScatterSet oldSet$iv = this_$iv.oldDependenciesSet;
        if (oldSet$iv != null && oldSet$iv.isNotEmpty()) {
            MutableScatterSet this_$iv$iv = oldSet$iv;
            Object[] k$iv$iv = this_$iv$iv.elements;
            long[] m$iv$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 <= lastIndex$iv$iv$iv) {
                while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    ChildLayerDependenciesTracker this_$iv2 = this_$iv;
                    if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                            break;
                            break;
                        } else {
                            i$iv$iv$iv++;
                            this_$iv = this_$iv2;
                        }
                    } else {
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                ((GraphicsLayer) k$iv$iv[index$iv$iv$iv]).onRemovedFromParentLayer();
                            }
                            slot$iv$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv$iv != 8) {
                            break;
                        }
                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                            break;
                        }
                        i$iv$iv$iv++;
                        this_$iv = this_$iv2;
                    }
                }
            }
            oldSet$iv.clear();
        }
    }

    private final void addSubLayer(GraphicsLayer graphicsLayer) {
        if (this.childDependenciesTracker.onDependencyAdded(graphicsLayer)) {
            graphicsLayer.onAddedToParentLayer();
        }
    }

    private final void transformCanvas(Canvas androidCanvas) {
        float left = IntOffset.m7144getXimpl(this.topLeft);
        float top = IntOffset.m7145getYimpl(this.topLeft);
        float right = IntOffset.m7144getXimpl(this.topLeft) + IntSize.m7186getWidthimpl(this.size);
        float bottom = IntOffset.m7145getYimpl(this.topLeft) + IntSize.m7185getHeightimpl(this.size);
        float layerAlpha = getAlpha();
        ColorFilter layerColorFilter = getColorFilter();
        int layerBlendMode = m5178getBlendMode0nO6VwU();
        boolean useSaveLayer = layerAlpha < 1.0f || !BlendMode.m4445equalsimpl0(layerBlendMode, BlendMode.INSTANCE.m4476getSrcOver0nO6VwU()) || layerColorFilter != null || CompositingStrategy.m5166equalsimpl0(m5179getCompositingStrategyke2Ky5w(), CompositingStrategy.INSTANCE.m5172getOffscreenke2Ky5w());
        if (useSaveLayer) {
            Paint it = this.softwareLayerPaint;
            if (it == null) {
                it = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = it;
            }
            Paint it2 = it;
            it2.setAlpha(layerAlpha);
            it2.mo4412setBlendModes9anfk8(layerBlendMode);
            it2.setColorFilter(layerColorFilter);
            Paint paint = it;
            androidCanvas.saveLayer(left, top, right, bottom, paint.getInternalPaint());
        } else {
            androidCanvas.save();
        }
        androidCanvas.translate(left, top);
        androidCanvas.concat(this.impl.calculateMatrix());
    }

    public final void drawForPersistence$ui_graphics_release(androidx.compose.ui.graphics.Canvas canvas) {
        if (AndroidCanvas_androidKt.getNativeCanvas(canvas).isHardwareAccelerated()) {
            recreateDisplayListIfNeeded();
            this.impl.draw(canvas);
        }
    }

    private final void recreateDisplayListIfNeeded() {
        if (!this.impl.getHasDisplayList()) {
            try {
                recordInternal();
            } catch (Throwable th) {
            }
        }
    }

    public final void draw$ui_graphics_release(androidx.compose.ui.graphics.Canvas canvas, GraphicsLayer parentLayer) {
        if (this.isReleased) {
            return;
        }
        configureOutlineAndClip();
        recreateDisplayListIfNeeded();
        boolean useZ = getShadowElevation() > 0.0f;
        if (useZ) {
            canvas.enableZ();
        }
        Canvas androidCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        boolean softwareRendered = !androidCanvas.isHardwareAccelerated();
        if (softwareRendered) {
            androidCanvas.save();
            transformCanvas(androidCanvas);
        }
        boolean willClipPath = softwareRendered && this.clip;
        if (willClipPath) {
            canvas.save();
            androidx.compose.ui.graphics.Outline tmpOutline = getOutline();
            if (tmpOutline instanceof androidx.compose.ui.graphics.Outline.Rectangle) {
                androidx.compose.ui.graphics.Canvas.m4507clipRectmtrdDE$default(canvas, tmpOutline.getRect(), 0, 2, null);
            } else if (tmpOutline instanceof androidx.compose.ui.graphics.Outline.Rounded) {
                Path rRectPath = this.roundRectClipPath;
                if (rRectPath != null) {
                    rRectPath.rewind();
                } else {
                    rRectPath = AndroidPath_androidKt.Path();
                    this.roundRectClipPath = rRectPath;
                }
                Path.addRoundRect$default(rRectPath, ((androidx.compose.ui.graphics.Outline.Rounded) tmpOutline).getRoundRect(), null, 2, null);
                androidx.compose.ui.graphics.Canvas.m4505clipPathmtrdDE$default(canvas, rRectPath, 0, 2, null);
            } else if (tmpOutline instanceof androidx.compose.ui.graphics.Outline.Generic) {
                androidx.compose.ui.graphics.Canvas.m4505clipPathmtrdDE$default(canvas, ((androidx.compose.ui.graphics.Outline.Generic) tmpOutline).getPath(), 0, 2, null);
            }
        }
        if (parentLayer != null) {
            parentLayer.addSubLayer(this);
        }
        this.impl.draw(canvas);
        if (willClipPath) {
            canvas.restore();
        }
        if (useZ) {
            canvas.disableZ();
        }
        if (softwareRendered) {
            androidCanvas.restore();
        }
    }

    private final void onAddedToParentLayer() {
        this.parentLayerUsages++;
    }

    private final void onRemovedFromParentLayer() {
        this.parentLayerUsages--;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    private final RectF obtainPathBounds() {
        RectF rectF = this.pathBounds;
        if (rectF != null) {
            return rectF;
        }
        RectF it = new RectF();
        this.pathBounds = it;
        return it;
    }

    private final void configureOutlineAndClip() {
        long outlineSize$iv;
        if (this.outlineDirty) {
            boolean outlineIsNeeded = this.clip || getShadowElevation() > 0.0f;
            Outline $this$configureOutlineAndClip_u24lambda_u247 = null;
            if (!outlineIsNeeded) {
                this.impl.setClip(false);
                this.impl.mo5201setOutlineO0kMr_c(null, IntSize.INSTANCE.m7191getZeroYbymL2g());
            } else {
                Path tmpPath = this.outlinePath;
                if (tmpPath == null) {
                    this.impl.setClip(this.clip);
                    Size.INSTANCE.m4371getZeroNHjbRc();
                    Outline $this$configureOutlineAndClip_u24lambda_u249 = obtainAndroidOutline();
                    long layerSize$iv = IntSizeKt.m7198toSizeozmzZPI(this.size);
                    long rRectTopLeft$iv = this.roundRectOutlineTopLeft;
                    long rRectSize$iv = this.roundRectOutlineSize;
                    if (rRectSize$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
                        outlineSize$iv = layerSize$iv;
                    } else {
                        outlineSize$iv = rRectSize$iv;
                    }
                    long outlineSize = outlineSize$iv;
                    float $this$fastRoundToInt$iv = Offset.m4293getXimpl(rRectTopLeft$iv);
                    int left = Math.round($this$fastRoundToInt$iv);
                    float $this$fastRoundToInt$iv2 = Offset.m4294getYimpl(rRectTopLeft$iv);
                    int top = Math.round($this$fastRoundToInt$iv2);
                    float $this$fastRoundToInt$iv3 = Offset.m4293getXimpl(rRectTopLeft$iv) + Size.m4362getWidthimpl(outlineSize);
                    int right = Math.round($this$fastRoundToInt$iv3);
                    float $this$fastRoundToInt$iv4 = Offset.m4294getYimpl(rRectTopLeft$iv) + Size.m4359getHeightimpl(outlineSize);
                    int bottom = Math.round($this$fastRoundToInt$iv4);
                    $this$configureOutlineAndClip_u24lambda_u249.setRoundRect(left, top, right, bottom, this.roundRectCornerRadius);
                    $this$configureOutlineAndClip_u24lambda_u249.setAlpha(getAlpha());
                    this.impl.mo5201setOutlineO0kMr_c($this$configureOutlineAndClip_u24lambda_u249, IntSizeKt.m7194roundToIntSizeuvyYCjk(outlineSize));
                } else {
                    RectF bounds = obtainPathBounds();
                    if (tmpPath instanceof AndroidPath) {
                        ((AndroidPath) tmpPath).getInternalPath().computeBounds(bounds, false);
                        Outline outlineUpdatePathOutline = updatePathOutline(tmpPath);
                        if (outlineUpdatePathOutline != null) {
                            $this$configureOutlineAndClip_u24lambda_u247 = outlineUpdatePathOutline;
                            $this$configureOutlineAndClip_u24lambda_u247.setAlpha(getAlpha());
                        }
                        GraphicsLayerImpl graphicsLayerImpl = this.impl;
                        float $this$fastRoundToInt$iv5 = bounds.width();
                        int iRound = Math.round($this$fastRoundToInt$iv5);
                        float $this$fastRoundToInt$iv6 = bounds.height();
                        graphicsLayerImpl.mo5201setOutlineO0kMr_c($this$configureOutlineAndClip_u24lambda_u247, IntSizeKt.IntSize(iRound, Math.round($this$fastRoundToInt$iv6)));
                        if (!this.usePathForClip || !this.clip) {
                            this.impl.setClip(this.clip);
                        } else {
                            this.impl.setClip(false);
                            this.impl.discardDisplayList();
                        }
                    } else {
                        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
                    }
                }
            }
        }
        this.outlineDirty = false;
    }

    private final <T> T resolveOutlinePosition(Function2<? super Offset, ? super Size, ? extends T> block) {
        long outlineSize;
        long layerSize = IntSizeKt.m7198toSizeozmzZPI(this.size);
        long rRectTopLeft = this.roundRectOutlineTopLeft;
        long rRectSize = this.roundRectOutlineSize;
        if (rRectSize == InlineClassHelperKt.UnspecifiedPackedFloats) {
            outlineSize = layerSize;
        } else {
            outlineSize = rRectSize;
        }
        return block.invoke(Offset.m4282boximpl(rRectTopLeft), Size.m4350boximpl(outlineSize));
    }

    private final Outline updatePathOutline(Path path) {
        Outline resultOutline;
        if (Build.VERSION.SDK_INT > 28 || path.isConvex()) {
            resultOutline = obtainAndroidOutline();
            if (Build.VERSION.SDK_INT >= 30) {
                OutlineVerificationHelper.INSTANCE.setPath(resultOutline, path);
            } else if (path instanceof AndroidPath) {
                resultOutline.setConvexPath(((AndroidPath) path).getInternalPath());
            } else {
                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
            }
            this.usePathForClip = !resultOutline.canClip();
        } else {
            Outline outline = this.androidOutline;
            if (outline != null) {
                outline.setEmpty();
            }
            resultOutline = null;
            this.usePathForClip = true;
            this.impl.setInvalidated(true);
        }
        this.outlinePath = path;
        return resultOutline;
    }

    private final Outline obtainAndroidOutline() {
        Outline outline = this.androidOutline;
        if (outline != null) {
            return outline;
        }
        Outline it = new Outline();
        this.androidOutline = it;
        return it;
    }

    public final void release$ui_graphics_release() {
        if (!this.isReleased) {
            this.isReleased = true;
            discardContentIfReleasedAndHaveNoParentLayerUsages();
        }
    }

    private final void discardContentIfReleasedAndHaveNoParentLayerUsages() {
        if (this.isReleased && this.parentLayerUsages == 0) {
            if (this.layerManager != null) {
                this.layerManager.release(this);
            } else {
                discardDisplayList$ui_graphics_release();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0086 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0088 A[LOOP:0: B:9:0x0031->B:22:0x0088, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x009a A[EDGE_INSN: B:30:0x009a->B:25:0x009a BREAK  A[LOOP:0: B:9:0x0031->B:22:0x0088], SYNTHETIC] */
    public final void discardDisplayList$ui_graphics_release() {
        ChildLayerDependenciesTracker this_$iv = this.childDependenciesTracker;
        int $i$f$removeDependencies = 0;
        GraphicsLayer it$iv = this_$iv.dependency;
        if (it$iv != null) {
            it$iv.onRemovedFromParentLayer();
            this_$iv.dependency = null;
        }
        MutableScatterSet it$iv2 = this_$iv.dependenciesSet;
        if (it$iv2 != null) {
            int i = 0;
            MutableScatterSet this_$iv$iv = it$iv2;
            Object[] k$iv$iv = this_$iv$iv.elements;
            long[] m$iv$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            if (0 <= lastIndex$iv$iv$iv) {
                while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    ChildLayerDependenciesTracker this_$iv2 = this_$iv;
                    int $i$f$removeDependencies2 = $i$f$removeDependencies;
                    int i2 = i;
                    ScatterSet this_$iv$iv2 = this_$iv$iv;
                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                            break;
                            break;
                        }
                        i$iv$iv$iv++;
                        i = i2;
                        this_$iv$iv = this_$iv$iv2;
                        this_$iv = this_$iv2;
                        $i$f$removeDependencies = $i$f$removeDependencies2;
                    } else {
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        for (int j$iv$iv$iv = 0; j$iv$iv$iv < bitCount$iv$iv$iv; j$iv$iv$iv++) {
                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                            if (value$iv$iv$iv$iv < 128) {
                                int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                GraphicsLayer it = (GraphicsLayer) k$iv$iv[index$iv$iv$iv];
                                it.onRemovedFromParentLayer();
                            }
                            slot$iv$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv$iv != 8) {
                            break;
                        }
                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                            break;
                        }
                        i$iv$iv$iv++;
                        i = i2;
                        this_$iv$iv = this_$iv$iv2;
                        this_$iv = this_$iv2;
                        $i$f$removeDependencies = $i$f$removeDependencies2;
                    }
                }
            }
            it$iv2.clear();
        }
        this.impl.discardDisplayList();
    }

    public final void emulateTrimMemory$ui_graphics_release() {
        this.impl.discardDisplayList();
    }

    public final long getLayerId() {
        return this.impl.getLayerId();
    }

    public final long getOwnerViewId() {
        return this.impl.getOwnerId();
    }

    public final androidx.compose.ui.graphics.Outline getOutline() {
        long outlineSize$iv;
        androidx.compose.ui.graphics.Outline tmpOutline = this.internalOutline;
        Path tmpPath = this.outlinePath;
        if (tmpOutline != null) {
            return tmpOutline;
        }
        if (tmpPath != null) {
            androidx.compose.ui.graphics.Outline.Generic it = new androidx.compose.ui.graphics.Outline.Generic(tmpPath);
            this.internalOutline = it;
            return it;
        }
        long layerSize$iv = IntSizeKt.m7198toSizeozmzZPI(this.size);
        long rRectTopLeft$iv = this.roundRectOutlineTopLeft;
        long rRectSize$iv = this.roundRectOutlineSize;
        if (rRectSize$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
            outlineSize$iv = layerSize$iv;
        } else {
            outlineSize$iv = rRectSize$iv;
        }
        long outlineSize = outlineSize$iv;
        float left = Offset.m4293getXimpl(rRectTopLeft$iv);
        float top = Offset.m4294getYimpl(rRectTopLeft$iv);
        float right = left + Size.m4362getWidthimpl(outlineSize);
        float bottom = top + Size.m4359getHeightimpl(outlineSize);
        float cornerRadius = this.roundRectCornerRadius;
        androidx.compose.ui.graphics.Outline.Rectangle it2 = cornerRadius > 0.0f ? new androidx.compose.ui.graphics.Outline.Rounded(RoundRectKt.m4347RoundRectgG7oq9Y(left, top, right, bottom, CornerRadiusKt.CornerRadius$default(cornerRadius, 0.0f, 2, null))) : new androidx.compose.ui.graphics.Outline.Rectangle(new Rect(left, top, right, bottom));
        this.internalOutline = it2;
        return it2;
    }

    private final void resetOutlineParams() {
        this.internalOutline = null;
        this.outlinePath = null;
        this.roundRectOutlineSize = Size.INSTANCE.m4370getUnspecifiedNHjbRc();
        this.roundRectOutlineTopLeft = Offset.INSTANCE.m4309getZeroF1C5BW0();
        this.roundRectCornerRadius = 0.0f;
        this.outlineDirty = true;
        this.usePathForClip = false;
    }

    public final void setPathOutline(Path path) {
        resetOutlineParams();
        this.outlinePath = path;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setRoundRectOutline-TNW_H78, reason: not valid java name */
    public final void m5190setRoundRectOutlineTNW_H78(long topLeft, long size, float cornerRadius) {
        if (Offset.m4290equalsimpl0(this.roundRectOutlineTopLeft, topLeft) && Size.m4358equalsimpl0(this.roundRectOutlineSize, size)) {
            if ((this.roundRectCornerRadius == cornerRadius) && this.outlinePath == null) {
                return;
            }
        }
        resetOutlineParams();
        this.roundRectOutlineTopLeft = topLeft;
        this.roundRectOutlineSize = size;
        this.roundRectCornerRadius = cornerRadius;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw$default, reason: not valid java name */
    public static /* synthetic */ void m5174setRectOutlinetz77jQw$default(GraphicsLayer graphicsLayer, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m4309getZeroF1C5BW0();
        }
        if ((i & 2) != 0) {
            j2 = Size.INSTANCE.m4370getUnspecifiedNHjbRc();
        }
        graphicsLayer.m5189setRectOutlinetz77jQw(j, j2);
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw, reason: not valid java name */
    public final void m5189setRectOutlinetz77jQw(long topLeft, long size) {
        m5190setRoundRectOutlineTNW_H78(topLeft, size, 0.0f);
    }

    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
    public final long m5177getAmbientShadowColor0d7_KjU() {
        return this.impl.getAmbientShadowColor();
    }

    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    public final void m5185setAmbientShadowColor8_81llA(long value) {
        if (!Color.m4535equalsimpl0(value, this.impl.getAmbientShadowColor())) {
            this.impl.mo5198setAmbientShadowColor8_81llA(value);
        }
    }

    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
    public final long m5182getSpotShadowColor0d7_KjU() {
        return this.impl.getSpotShadowColor();
    }

    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    public final void m5191setSpotShadowColor8_81llA(long value) {
        if (!Color.m4535equalsimpl0(value, this.impl.getSpotShadowColor())) {
            this.impl.mo5204setSpotShadowColor8_81llA(value);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object toImageBitmap(Continuation<? super ImageBitmap> continuation) {
        AnonymousClass1 anonymousClass1;
        Object bitmap;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                LayerSnapshotImpl layerSnapshotImpl = SnapshotImpl;
                anonymousClass2.label = 1;
                bitmap = layerSnapshotImpl.toBitmap(this, anonymousClass2);
                if (bitmap == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                bitmap = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return AndroidImageBitmap_androidKt.asImageBitmap((Bitmap) bitmap);
    }

    static {
        LayerSnapshotV21 layerSnapshotV21;
        if (LayerManager.INSTANCE.isRobolectric()) {
            layerSnapshotV21 = LayerSnapshotV21.INSTANCE;
        } else if (Build.VERSION.SDK_INT >= 28) {
            layerSnapshotV21 = LayerSnapshotV28.INSTANCE;
        } else if (SurfaceUtils.INSTANCE.isLockHardwareCanvasAvailable()) {
            layerSnapshotV21 = LayerSnapshotV22.INSTANCE;
        } else {
            layerSnapshotV21 = LayerSnapshotV21.INSTANCE;
        }
        SnapshotImpl = layerSnapshotV21;
    }
}
