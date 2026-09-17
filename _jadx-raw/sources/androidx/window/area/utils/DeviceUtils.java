package androidx.window.area.utils;

import ai.nous.aurora.presentation.theme.AuroraMotion;
import android.util.DisplayMetrics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceUtils.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/area/utils/DeviceUtils;", "", "()V", "deviceList", "", "Landroidx/window/area/utils/DeviceMetrics;", "getRearDisplayMetrics", "Landroid/util/DisplayMetrics;", "manufacturer", "", "model", "getRearDisplayMetrics$window_release", "hasDeviceMetrics", "", "hasDeviceMetrics$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DeviceUtils {
    public static final DeviceUtils INSTANCE = new DeviceUtils();
    private static final List<DeviceMetrics> deviceList;

    private DeviceUtils() {
    }

    static {
        DisplayMetrics $this$deviceList_u24lambda_u240 = new DisplayMetrics();
        $this$deviceList_u24lambda_u240.widthPixels = 1080;
        $this$deviceList_u24lambda_u240.heightPixels = 2092;
        $this$deviceList_u24lambda_u240.density = 2.625f;
        $this$deviceList_u24lambda_u240.densityDpi = AuroraMotion.ELLIPSIS_STEP_MS;
        Unit unit = Unit.INSTANCE;
        deviceList = CollectionsKt.listOf(new DeviceMetrics("google", "pixel fold", $this$deviceList_u24lambda_u240));
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0063  */
    public final boolean hasDeviceMetrics$window_release(String manufacturer, String model) {
        boolean z;
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(model, "model");
        Iterable $this$any$iv = deviceList;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            DeviceMetrics it = (DeviceMetrics) element$iv;
            String manufacturer2 = it.getManufacturer();
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = manufacturer.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (Intrinsics.areEqual(manufacturer2, lowerCase)) {
                String model2 = it.getModel();
                Locale US2 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US2, "US");
                String lowerCase2 = model.toLowerCase(US2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                if (Intrinsics.areEqual(model2, lowerCase2)) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0055  */
    public final DisplayMetrics getRearDisplayMetrics$window_release(String manufacturer, String model) {
        Object element$iv;
        boolean z;
        Intrinsics.checkNotNullParameter(manufacturer, "manufacturer");
        Intrinsics.checkNotNullParameter(model, "model");
        Iterable $this$firstOrNull$iv = deviceList;
        Iterator it = $this$firstOrNull$iv.iterator();
        do {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            DeviceMetrics it2 = (DeviceMetrics) element$iv;
            String manufacturer2 = it2.getManufacturer();
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = manufacturer.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (Intrinsics.areEqual(manufacturer2, lowerCase)) {
                String model2 = it2.getModel();
                Locale US2 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US2, "US");
                String lowerCase2 = model.toLowerCase(US2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                if (Intrinsics.areEqual(model2, lowerCase2)) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
        } while (!z);
        DeviceMetrics deviceMetrics = (DeviceMetrics) element$iv;
        if (deviceMetrics != null) {
            return deviceMetrics.getRearDisplayMetrics();
        }
        return null;
    }
}
