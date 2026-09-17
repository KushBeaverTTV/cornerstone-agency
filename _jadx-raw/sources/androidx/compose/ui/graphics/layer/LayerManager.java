package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.core.os.HandlerCompat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayerManager.android.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\rJ\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u001b\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/graphics/layer/LayerManager;", "", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "(Landroidx/compose/ui/graphics/CanvasHolder;)V", "getCanvasHolder", "()Landroidx/compose/ui/graphics/CanvasHolder;", "handler", "Landroid/os/Handler;", "imageReader", "Landroid/media/ImageReader;", "layerSet", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "persistenceIterationInProgress", "", "postponedReleaseRequests", "Landroidx/collection/MutableObjectList;", "destroy", "", "hasImageReader", "persist", "layer", "persistLayers", "layers", "Landroidx/collection/ScatterSet;", "release", "updateLayerPersistence", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayerManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final boolean isRobolectric;
    private final CanvasHolder canvasHolder;
    private ImageReader imageReader;
    private boolean persistenceIterationInProgress;
    private MutableObjectList<GraphicsLayer> postponedReleaseRequests;
    private final MutableScatterSet<GraphicsLayer> layerSet = ScatterSetKt.mutableScatterSetOf();
    private final Handler handler = HandlerCompat.createAsync(Looper.getMainLooper(), new Handler.Callback() { // from class: androidx.compose.ui.graphics.layer.LayerManager$$ExternalSyntheticLambda1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return LayerManager.handler$lambda$0(this.f$0, message);
        }
    });

    public LayerManager(CanvasHolder canvasHolder) {
        this.canvasHolder = canvasHolder;
    }

    public final CanvasHolder getCanvasHolder() {
        return this.canvasHolder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handler$lambda$0(LayerManager this$0, Message it) {
        this$0.persistLayers(this$0.layerSet);
        return true;
    }

    public final void persist(GraphicsLayer layer) {
        this.layerSet.add(layer);
        if (!this.handler.hasMessages(0)) {
            this.handler.sendMessageAtFrontOfQueue(Message.obtain());
        }
    }

    public final void release(GraphicsLayer layer) {
        if (!this.persistenceIterationInProgress) {
            if (this.layerSet.remove(layer)) {
                layer.discardDisplayList$ui_graphics_release();
            }
        } else {
            MutableObjectList<GraphicsLayer> mutableObjectList = this.postponedReleaseRequests;
            if (mutableObjectList == null) {
                mutableObjectList = new MutableObjectList<>(0, 1, null);
                this.postponedReleaseRequests = mutableObjectList;
            }
            mutableObjectList.add(layer);
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00d2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x00d4 A[LOOP:0: B:16:0x0077->B:29:0x00d4, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:0x00e8 A[EDGE_INSN: B:44:0x00e8->B:32:0x00e8 BREAK  A[LOOP:0: B:16:0x0077->B:29:0x00d4], SYNTHETIC] */
    private final void persistLayers(ScatterSet<GraphicsLayer> layers) {
        boolean shouldPersistLayers = (1 == 0 || !layers.isNotEmpty() || isRobolectric) ? false : true;
        if (shouldPersistLayers) {
            ImageReader $this$persistLayers_u24lambda_u243 = this.imageReader;
            if ($this$persistLayers_u24lambda_u243 == null) {
                $this$persistLayers_u24lambda_u243 = ImageReader.newInstance(1, 1, 1, 3);
                $this$persistLayers_u24lambda_u243.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.compose.ui.graphics.layer.LayerManager$$ExternalSyntheticLambda0
                    @Override // android.media.ImageReader.OnImageAvailableListener
                    public final void onImageAvailable(ImageReader imageReader) {
                        LayerManager.persistLayers$lambda$3$lambda$2(imageReader);
                    }
                }, this.handler);
                this.imageReader = $this$persistLayers_u24lambda_u243;
            }
            Surface surface = $this$persistLayers_u24lambda_u243.getSurface();
            Canvas canvas = LockHardwareCanvasHelper.INSTANCE.lockHardwareCanvas(surface);
            this.persistenceIterationInProgress = true;
            CanvasHolder this_$iv = this.canvasHolder;
            Canvas previousCanvas$iv = this_$iv.getAndroidCanvas().getInternalCanvas();
            this_$iv.getAndroidCanvas().setInternalCanvas(canvas);
            androidx.compose.ui.graphics.Canvas $this$persistLayers_u24lambda_u246 = this_$iv.getAndroidCanvas();
            int i = 0;
            canvas.save();
            canvas.clipRect(0, 0, 1, 1);
            ScatterSet<GraphicsLayer> scatterSet = layers;
            Object[] k$iv = scatterSet.elements;
            long[] m$iv$iv = scatterSet.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    long[] m$iv$iv2 = m$iv$iv;
                    boolean shouldPersistLayers2 = shouldPersistLayers;
                    int i2 = i;
                    ScatterSet<GraphicsLayer> scatterSet2 = scatterSet;
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                        if (i$iv$iv != lastIndex$iv$iv) {
                            break;
                            break;
                        }
                        i$iv$iv++;
                        i = i2;
                        scatterSet = scatterSet2;
                        shouldPersistLayers = shouldPersistLayers2;
                        m$iv$iv = m$iv$iv2;
                    } else {
                        int i3 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                            long value$iv$iv$iv = slot$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                GraphicsLayer layer = (GraphicsLayer) k$iv[index$iv$iv];
                                layer.drawForPersistence$ui_graphics_release($this$persistLayers_u24lambda_u246);
                            }
                            i3 = 8;
                            slot$iv$iv >>= 8;
                        }
                        if (bitCount$iv$iv != i3) {
                            break;
                        }
                        if (i$iv$iv != lastIndex$iv$iv) {
                            break;
                        }
                        i$iv$iv++;
                        i = i2;
                        scatterSet = scatterSet2;
                        shouldPersistLayers = shouldPersistLayers2;
                        m$iv$iv = m$iv$iv2;
                    }
                }
            }
            canvas.restore();
            this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
            this.persistenceIterationInProgress = false;
            MutableObjectList<GraphicsLayer> mutableObjectList = this.postponedReleaseRequests;
            if (mutableObjectList != null && mutableObjectList.isNotEmpty()) {
                MutableObjectList<GraphicsLayer> this_$iv2 = mutableObjectList;
                Object[] content$iv = this_$iv2.content;
                int i4 = this_$iv2._size;
                for (int i$iv = 0; i$iv < i4; i$iv++) {
                    GraphicsLayer it = (GraphicsLayer) content$iv[i$iv];
                    release(it);
                }
                mutableObjectList.clear();
            }
            surface.unlockCanvasAndPost(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void persistLayers$lambda$3$lambda$2(ImageReader it) {
        Image imageAcquireLatestImage;
        if (it == null || (imageAcquireLatestImage = it.acquireLatestImage()) == null) {
            return;
        }
        imageAcquireLatestImage.close();
    }

    public final void destroy() {
        ImageReader imageReader = this.imageReader;
        if (imageReader != null) {
            imageReader.close();
        }
        this.imageReader = null;
    }

    public final boolean hasImageReader() {
        return this.imageReader != null;
    }

    public final void updateLayerPersistence() {
        destroy();
        persistLayers(this.layerSet);
    }

    /* JADX INFO: compiled from: LayerManager.android.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/ui/graphics/layer/LayerManager$Companion;", "", "()V", "isRobolectric", "", "()Z", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRobolectric() {
            return LayerManager.isRobolectric;
        }
    }

    static {
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        isRobolectric = Intrinsics.areEqual(lowerCase, "robolectric");
    }
}
