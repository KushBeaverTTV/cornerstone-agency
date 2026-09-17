package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.WindowManager;
import androidx.core.view.WindowInsetsCompat;
import androidx.window.core.Bounds;
import androidx.window.layout.util.ActivityCompatHelperApi24;
import androidx.window.layout.util.ContextCompatHelper;
import androidx.window.layout.util.ContextCompatHelperApi30;
import androidx.window.layout.util.DisplayCompatHelperApi28;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowMetricsCalculatorCompat.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0002\b\u001aJ\u0015\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!H\u0003J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0015\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b%J\u0018\u0010&\u001a\u00020'2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006)"}, d2 = {"Landroidx/window/layout/WindowMetricsCalculatorCompat;", "Landroidx/window/layout/WindowMetricsCalculator;", "()V", "TAG", "", "insetsTypeMasks", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getInsetsTypeMasks$window_release", "()Ljava/util/ArrayList;", "computeCurrentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "computeMaximumWindowMetrics", "computeWindowBoundsIceCreamSandwich", "Landroid/graphics/Rect;", "computeWindowBoundsIceCreamSandwich$window_release", "computeWindowBoundsN", "computeWindowBoundsN$window_release", "computeWindowBoundsP", "computeWindowBoundsP$window_release", "computeWindowBoundsQ", "computeWindowBoundsQ$window_release", "computeWindowInsetsCompat", "Landroidx/core/view/WindowInsetsCompat;", "computeWindowInsetsCompat$window_release", "getCutoutForDisplay", "Landroid/view/DisplayCutout;", "display", "Landroid/view/Display;", "getNavigationBarHeight", "getRealSizeForDisplay", "Landroid/graphics/Point;", "getRealSizeForDisplay$window_release", "getRectSizeFromDisplay", "", "bounds", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    public static final WindowMetricsCalculatorCompat INSTANCE = new WindowMetricsCalculatorCompat();
    private static final String TAG;
    private static final ArrayList<Integer> insetsTypeMasks;

    private WindowMetricsCalculatorCompat() {
    }

    static {
        String simpleName = WindowMetricsCalculatorCompat.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "WindowMetricsCalculatorC…at::class.java.simpleName");
        TAG = simpleName;
        insetsTypeMasks = CollectionsKt.arrayListOf(Integer.valueOf(WindowInsetsCompat.Type.statusBars()), Integer.valueOf(WindowInsetsCompat.Type.navigationBars()), Integer.valueOf(WindowInsetsCompat.Type.captionBar()), Integer.valueOf(WindowInsetsCompat.Type.ime()), Integer.valueOf(WindowInsetsCompat.Type.systemGestures()), Integer.valueOf(WindowInsetsCompat.Type.mandatorySystemGestures()), Integer.valueOf(WindowInsetsCompat.Type.tappableElement()), Integer.valueOf(WindowInsetsCompat.Type.displayCutout()));
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeCurrentWindowMetrics(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 30) {
            return ContextCompatHelperApi30.INSTANCE.currentWindowMetrics(context);
        }
        Context unwrappedContext = ContextCompatHelper.INSTANCE.unwrapUiContext$window_release(context);
        if (unwrappedContext instanceof Activity) {
            return computeCurrentWindowMetrics((Activity) unwrappedContext);
        }
        if (unwrappedContext instanceof InputMethodService) {
            Object systemService = context.getSystemService("window");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            WindowManager wm = (WindowManager) systemService;
            Display defaultDisplay = wm.getDefaultDisplay();
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "wm.defaultDisplay");
            Point displaySize = getRealSizeForDisplay$window_release(defaultDisplay);
            Rect imeBounds = new Rect(0, 0, displaySize.x, displaySize.y);
            return new WindowMetrics(imeBounds, null, 2, null);
        }
        throw new IllegalArgumentException(context + " is not a UiContext");
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeCurrentWindowMetrics(Activity activity) throws Exception {
        Rect bounds;
        WindowInsetsCompat windowInsetsCompat;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Build.VERSION.SDK_INT >= 30) {
            bounds = ContextCompatHelperApi30.INSTANCE.currentWindowBounds(activity);
        } else if (Build.VERSION.SDK_INT >= 29) {
            bounds = computeWindowBoundsQ$window_release(activity);
        } else if (Build.VERSION.SDK_INT >= 28) {
            bounds = computeWindowBoundsP$window_release(activity);
        } else {
            bounds = computeWindowBoundsN$window_release(activity);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            windowInsetsCompat = computeWindowInsetsCompat$window_release(activity);
        } else {
            windowInsetsCompat = new WindowInsetsCompat.Builder().build();
            Intrinsics.checkNotNullExpressionValue(windowInsetsCompat, "{\n            WindowInse…ilder().build()\n        }");
        }
        return new WindowMetrics(new Bounds(bounds), windowInsetsCompat);
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeMaximumWindowMetrics(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return computeMaximumWindowMetrics((Context) activity);
    }

    @Override // androidx.window.layout.WindowMetricsCalculator
    public WindowMetrics computeMaximumWindowMetrics(Context context) throws Exception {
        Rect bounds;
        WindowInsetsCompat windowInsetsCompat;
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 30) {
            bounds = ContextCompatHelperApi30.INSTANCE.maximumWindowBounds(context);
        } else {
            Object systemService = context.getSystemService("window");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            WindowManager wm = (WindowManager) systemService;
            Display display = wm.getDefaultDisplay();
            Intrinsics.checkNotNullExpressionValue(display, "display");
            Point displaySize = getRealSizeForDisplay$window_release(display);
            bounds = new Rect(0, 0, displaySize.x, displaySize.y);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            windowInsetsCompat = computeWindowInsetsCompat$window_release(context);
        } else {
            windowInsetsCompat = new WindowInsetsCompat.Builder().build();
            Intrinsics.checkNotNullExpressionValue(windowInsetsCompat, "{\n            WindowInse…ilder().build()\n        }");
        }
        return new WindowMetrics(new Bounds(bounds), windowInsetsCompat);
    }

    public final Rect computeWindowBoundsQ$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Configuration config = activity.getResources().getConfiguration();
        try {
            Field windowConfigField = Configuration.class.getDeclaredField("windowConfiguration");
            windowConfigField.setAccessible(true);
            Object windowConfig = windowConfigField.get(config);
            Method getBoundsMethod = windowConfig.getClass().getDeclaredMethod("getBounds", new Class[0]);
            Object objInvoke = getBoundsMethod.invoke(windowConfig, new Object[0]);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type android.graphics.Rect");
            Rect bounds = new Rect((Rect) objInvoke);
            return bounds;
        } catch (IllegalAccessException e) {
            Log.w(TAG, e);
            Rect bounds2 = computeWindowBoundsP$window_release(activity);
            return bounds2;
        } catch (NoSuchFieldException e2) {
            Log.w(TAG, e2);
            Rect bounds3 = computeWindowBoundsP$window_release(activity);
            return bounds3;
        } catch (NoSuchMethodException e3) {
            Log.w(TAG, e3);
            Rect bounds4 = computeWindowBoundsP$window_release(activity);
            return bounds4;
        } catch (InvocationTargetException e4) {
            Log.w(TAG, e4);
            Rect bounds5 = computeWindowBoundsP$window_release(activity);
            return bounds5;
        }
    }

    public final Rect computeWindowBoundsP$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Rect bounds = new Rect();
        Configuration config = activity.getResources().getConfiguration();
        try {
            Field windowConfigField = Configuration.class.getDeclaredField("windowConfiguration");
            windowConfigField.setAccessible(true);
            Object windowConfig = windowConfigField.get(config);
            if (ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
                Method getAppBounds = windowConfig.getClass().getDeclaredMethod("getBounds", new Class[0]);
                Object objInvoke = getAppBounds.invoke(windowConfig, new Object[0]);
                Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type android.graphics.Rect");
                bounds.set((Rect) objInvoke);
            } else {
                Method getAppBounds2 = windowConfig.getClass().getDeclaredMethod("getAppBounds", new Class[0]);
                Object objInvoke2 = getAppBounds2.invoke(windowConfig, new Object[0]);
                Intrinsics.checkNotNull(objInvoke2, "null cannot be cast to non-null type android.graphics.Rect");
                bounds.set((Rect) objInvoke2);
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, e);
            getRectSizeFromDisplay(activity, bounds);
        } catch (NoSuchFieldException e2) {
            Log.w(TAG, e2);
            getRectSizeFromDisplay(activity, bounds);
        } catch (NoSuchMethodException e3) {
            Log.w(TAG, e3);
            getRectSizeFromDisplay(activity, bounds);
        } catch (InvocationTargetException e4) {
            Log.w(TAG, e4);
            getRectSizeFromDisplay(activity, bounds);
        }
        WindowManager platformWindowManager = activity.getWindowManager();
        Display currentDisplay = platformWindowManager.getDefaultDisplay();
        Point realDisplaySize = new Point();
        currentDisplay.getRealSize(realDisplaySize);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            int navigationBarHeight = getNavigationBarHeight(activity);
            if (bounds.bottom + navigationBarHeight == realDisplaySize.y) {
                bounds.bottom += navigationBarHeight;
            } else if (bounds.right + navigationBarHeight == realDisplaySize.x) {
                bounds.right += navigationBarHeight;
            } else if (bounds.left == navigationBarHeight) {
                bounds.left = 0;
            }
        }
        if ((bounds.width() < realDisplaySize.x || bounds.height() < realDisplaySize.y) && !ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            Intrinsics.checkNotNullExpressionValue(currentDisplay, "currentDisplay");
            DisplayCutout displayCutout = getCutoutForDisplay(currentDisplay);
            if (displayCutout != null) {
                if (bounds.left == DisplayCompatHelperApi28.INSTANCE.safeInsetLeft(displayCutout)) {
                    bounds.left = 0;
                }
                if (realDisplaySize.x - bounds.right == DisplayCompatHelperApi28.INSTANCE.safeInsetRight(displayCutout)) {
                    bounds.right += DisplayCompatHelperApi28.INSTANCE.safeInsetRight(displayCutout);
                }
                if (bounds.top == DisplayCompatHelperApi28.INSTANCE.safeInsetTop(displayCutout)) {
                    bounds.top = 0;
                }
                if (realDisplaySize.y - bounds.bottom == DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(displayCutout)) {
                    bounds.bottom += DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(displayCutout);
                }
            }
        }
        return bounds;
    }

    private final void getRectSizeFromDisplay(Activity activity, Rect bounds) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(bounds);
    }

    public final Rect computeWindowBoundsN$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Rect bounds = new Rect();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(bounds);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "defaultDisplay");
            Point realDisplaySize = getRealSizeForDisplay$window_release(defaultDisplay);
            int navigationBarHeight = getNavigationBarHeight(activity);
            if (bounds.bottom + navigationBarHeight == realDisplaySize.y) {
                bounds.bottom += navigationBarHeight;
            } else if (bounds.right + navigationBarHeight == realDisplaySize.x) {
                bounds.right += navigationBarHeight;
            }
        }
        return bounds;
    }

    public final Rect computeWindowBoundsIceCreamSandwich$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Intrinsics.checkNotNullExpressionValue(defaultDisplay, "defaultDisplay");
        Point realDisplaySize = getRealSizeForDisplay$window_release(defaultDisplay);
        Rect bounds = new Rect();
        if (realDisplaySize.x == 0 || realDisplaySize.y == 0) {
            defaultDisplay.getRectSize(bounds);
        } else {
            bounds.right = realDisplaySize.x;
            bounds.bottom = realDisplaySize.y;
        }
        return bounds;
    }

    public final Point getRealSizeForDisplay$window_release(Display display) {
        Intrinsics.checkNotNullParameter(display, "display");
        Point size = new Point();
        display.getRealSize(size);
        return size;
    }

    private final int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    private final DisplayCutout getCutoutForDisplay(Display display) {
        try {
            Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor(new Class[0]);
            constructor.setAccessible(true);
            Object displayInfo = constructor.newInstance(new Object[0]);
            Method getDisplayInfoMethod = display.getClass().getDeclaredMethod("getDisplayInfo", displayInfo.getClass());
            getDisplayInfoMethod.setAccessible(true);
            getDisplayInfoMethod.invoke(display, displayInfo);
            Field displayCutoutField = displayInfo.getClass().getDeclaredField("displayCutout");
            displayCutoutField.setAccessible(true);
            Object cutout = displayCutoutField.get(displayInfo);
            if (!(cutout instanceof DisplayCutout)) {
                return null;
            }
            DisplayCutout displayCutout = (DisplayCutout) cutout;
            return displayCutout;
        } catch (ClassNotFoundException e) {
            Log.w(TAG, e);
            return null;
        } catch (IllegalAccessException e2) {
            Log.w(TAG, e2);
            return null;
        } catch (InstantiationException e3) {
            Log.w(TAG, e3);
            return null;
        } catch (NoSuchFieldException e4) {
            Log.w(TAG, e4);
            return null;
        } catch (NoSuchMethodException e5) {
            Log.w(TAG, e5);
            return null;
        } catch (InvocationTargetException e6) {
            Log.w(TAG, e6);
            return null;
        }
    }

    public final ArrayList<Integer> getInsetsTypeMasks$window_release() {
        return insetsTypeMasks;
    }

    public final WindowInsetsCompat computeWindowInsetsCompat$window_release(Context context) throws Exception {
        Intrinsics.checkNotNullParameter(context, "context");
        int build = Build.VERSION.SDK_INT;
        if (build >= 30) {
            WindowInsetsCompat windowInsetsCompat = ContextCompatHelperApi30.INSTANCE.currentWindowInsets(context);
            return windowInsetsCompat;
        }
        throw new Exception("Incompatible SDK version");
    }
}
