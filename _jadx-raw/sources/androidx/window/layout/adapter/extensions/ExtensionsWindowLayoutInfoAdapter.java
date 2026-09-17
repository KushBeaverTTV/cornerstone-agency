package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import androidx.window.core.Bounds;
import androidx.window.extensions.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculatorCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtensionsWindowLayoutInfoAdapter.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u001f\u0010\u0003\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\tJ\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionsWindowLayoutInfoAdapter;", "", "()V", "translate", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "info", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "translate$window_release", "Landroidx/window/layout/FoldingFeature;", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "oemFeature", "Landroidx/window/extensions/layout/FoldingFeature;", "validBounds", "", "bounds", "Landroidx/window/core/Bounds;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExtensionsWindowLayoutInfoAdapter {
    public static final ExtensionsWindowLayoutInfoAdapter INSTANCE = new ExtensionsWindowLayoutInfoAdapter();

    private ExtensionsWindowLayoutInfoAdapter() {
    }

    public final FoldingFeature translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.FoldingFeature oemFeature) {
        HardwareFoldingFeature.Type type;
        FoldingFeature.State state;
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(oemFeature, "oemFeature");
        switch (oemFeature.getType()) {
            case 1:
                type = HardwareFoldingFeature.Type.INSTANCE.getFOLD();
                break;
            case 2:
                type = HardwareFoldingFeature.Type.INSTANCE.getHINGE();
                break;
            default:
                return null;
        }
        switch (oemFeature.getState()) {
            case 1:
                state = FoldingFeature.State.FLAT;
                break;
            case 2:
                state = FoldingFeature.State.HALF_OPENED;
                break;
            default:
                return null;
        }
        Rect bounds = oemFeature.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "oemFeature.bounds");
        Bounds bounds2 = new Bounds(bounds);
        if (!validBounds(windowMetrics, bounds2)) {
            return null;
        }
        Rect bounds3 = oemFeature.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds3, "oemFeature.bounds");
        return new HardwareFoldingFeature(new Bounds(bounds3), type, state);
    }

    public final WindowLayoutInfo translate$window_release(Context context, androidx.window.extensions.layout.WindowLayoutInfo info) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(info, "info");
        if (Build.VERSION.SDK_INT >= 30) {
            return translate$window_release(WindowMetricsCalculatorCompat.INSTANCE.computeCurrentWindowMetrics(context), info);
        }
        if (Build.VERSION.SDK_INT >= 29 && (context instanceof Activity)) {
            return translate$window_release(WindowMetricsCalculatorCompat.INSTANCE.computeCurrentWindowMetrics((Activity) context), info);
        }
        throw new UnsupportedOperationException("Display Features are only supported after Q. Display features for non-Activity contexts are not expected to be reported on devices running Q.");
    }

    public final WindowLayoutInfo translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.WindowLayoutInfo info) {
        FoldingFeature foldingFeatureTranslate$window_release;
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(info, "info");
        Iterable displayFeatures = info.getDisplayFeatures();
        Intrinsics.checkNotNullExpressionValue(displayFeatures, "info.displayFeatures");
        Iterable $this$mapNotNull$iv = displayFeatures;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            androidx.window.extensions.layout.FoldingFeature feature = (DisplayFeature) element$iv$iv$iv;
            if (feature instanceof androidx.window.extensions.layout.FoldingFeature) {
                ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(feature, "feature");
                foldingFeatureTranslate$window_release = extensionsWindowLayoutInfoAdapter.translate$window_release(windowMetrics, feature);
            } else {
                foldingFeatureTranslate$window_release = null;
            }
            if (foldingFeatureTranslate$window_release != null) {
                destination$iv$iv.add(foldingFeatureTranslate$window_release);
            }
            $this$mapNotNull$iv = $this$mapNotNull$iv;
        }
        List features = (List) destination$iv$iv;
        return new WindowLayoutInfo(features);
    }

    private final boolean validBounds(WindowMetrics windowMetrics, Bounds bounds) {
        Rect windowBounds = windowMetrics.getBounds();
        if (bounds.isZero()) {
            return false;
        }
        if (bounds.getWidth() != windowBounds.width() && bounds.getHeight() != windowBounds.height()) {
            return false;
        }
        if (bounds.getWidth() >= windowBounds.width() || bounds.getHeight() >= windowBounds.height()) {
            return (bounds.getWidth() == windowBounds.width() && bounds.getHeight() == windowBounds.height()) ? false : true;
        }
        return false;
    }
}
