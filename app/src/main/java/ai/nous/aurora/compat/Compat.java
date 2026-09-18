/*
 * Decompiler bridge: JADX emitted calls to Kotlin/Compose methods under
 * sanitized names (mNNNN/moNNNN prefixes, hyphens stripped), and to Kotlin
 * synthetic $default stubs that javac cannot see. Every shim has the JVM
 * signature the call sites expect and forwards to the real method via
 * reflection (resolved once and cached; $default shims pick the overload by
 * runtime argument types).
 *
 * Compile/porting bridge only -- the long-term fix is rewriting the UI layer
 * in Kotlin/Compose and deleting this file. See PORTING_NOTES.md.
 */
package ai.nous.aurora.compat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class Compat {
    private Compat() {}

    private static final Map<Class<?>, Class<?>> WRAP = new HashMap<>();
    static {
        WRAP.put(boolean.class, Boolean.class); WRAP.put(byte.class, Byte.class);
        WRAP.put(char.class, Character.class); WRAP.put(short.class, Short.class);
        WRAP.put(int.class, Integer.class); WRAP.put(long.class, Long.class);
        WRAP.put(float.class, Float.class); WRAP.put(double.class, Double.class);
        WRAP.put(void.class, Void.class);
    }

    private static Method findIn(Class<?> c, String name, Class<?>... types) {
        for (Class<?> k = c; k != null; k = k.getSuperclass()) {
            try {
                Method m = k.getDeclaredMethod(name, types);
                m.setAccessible(true);
                return m;
            } catch (NoSuchMethodException ignored) {}
        }
        return null;
    }

    private static Method find(String cls, String name, Class<?>... types) {
        try {
            Class<?> c = Class.forName(cls);
            Method m = findIn(c, name, types);
            if (m != null) return m;
            // Fallback: same name + arity (hash drift across library versions).
            for (Class<?> k = c; k != null; k = k.getSuperclass()) {
                for (Method m2 : k.getDeclaredMethods()) {
                    if (m2.getName().equals(name) && m2.getParameterCount() == types.length) {
                        m2.setAccessible(true);
                        return m2;
                    }
                }
            }
            throw new IllegalStateException("no method " + cls + "." + name + " arity=" + types.length);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("no class " + cls, e);
        }
    }

    // Lazy resolver for $default shims: picks the overload whose declared
    // parameter types are assignable from the actual runtime arguments.
    private static Method flex(String cls, String name, int arity, Object... args) {
        try {
            for (Class<?> c = Class.forName(cls); c != null; c = c.getSuperclass()) {
                for (Method m : c.getDeclaredMethods()) {
                    if (!m.getName().equals(name) || m.getParameterCount() != arity) continue;
                    if (matches(m.getParameterTypes(), args)) {
                        m.setAccessible(true);
                        return m;
                    }
                }
            }
            throw new IllegalStateException("no overload " + cls + "." + name + "/" + arity);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("no class " + cls, e);
        }
    }

    private static boolean matches(Class<?>[] pts, Object[] args) {
        for (int i = 0; i < pts.length; i++) {
            Object a = args[i];
            Class<?> p = pts[i];
            if (p.isPrimitive()) {
                if (a == null || !WRAP.get(p).isInstance(a)) return false;
            } else if (a != null && !p.isInstance(a)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Throwable, R> R sneaky(Throwable t) throws T {
        throw (T) t;
    }

    /* Re-throw any throwable without declaring it (preserves identity,
       so CancellationException still propagates as-is). Use for `throw th`
       sites where precise-rethrow analysis fails due to checked exceptions. */
    public static RuntimeException sneakyThrow(Throwable t) {
        return Compat.<RuntimeException, RuntimeException>sneaky(t);
    }

    private static Object call(Method m, Object recv, Object... args) {
        try {
            return m.invoke(recv, args);
        } catch (InvocationTargetException e) {
            return Compat.<RuntimeException, Object>sneaky(e.getCause());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    private static final Method M_m7388constructorimpl = find("kotlin.Result", "constructor-impl", java.lang.Object.class);
    public static java.lang.Object m7388constructorimpl(java.lang.Object a0) {
        return (java.lang.Object) call(M_m7388constructorimpl, null, a0);
    }
    private static final Method M_m7387boximpl = find("kotlin.Result", "box-impl", java.lang.Object.class);
    public static kotlin.Result m7387boximpl(java.lang.Object a0) {
        return (kotlin.Result) call(M_m7387boximpl, null, a0);
    }
    private static final Method M_m7391exceptionOrNullimpl = find("kotlin.Result", "exceptionOrNull-impl", java.lang.Object.class);
    public static java.lang.Throwable m7391exceptionOrNullimpl(java.lang.Object a0) {
        return (java.lang.Throwable) call(M_m7391exceptionOrNullimpl, null, a0);
    }
    private static final Method M_m7394isFailureimpl = find("kotlin.Result", "isFailure-impl", java.lang.Object.class);
    public static boolean m7394isFailureimpl(java.lang.Object a0) {
        return (Boolean) call(M_m7394isFailureimpl, null, a0);
    }
    private static final Method M_m7395isSuccessimpl = find("kotlin.Result", "isSuccess-impl", java.lang.Object.class);
    public static boolean m7395isSuccessimpl(java.lang.Object a0) {
        return (Boolean) call(M_m7395isSuccessimpl, null, a0);
    }
    private static final Method M_m7016constructorimpl = find("androidx.compose.ui.unit.Dp", "constructor-impl", float.class);
    public static float m7016constructorimpl(float a0) {
        return (Float) call(M_m7016constructorimpl, null, a0);
    }
    private static final Method M_m7014boximpl = find("androidx.compose.ui.unit.Dp", "box-impl", float.class);
    public static androidx.compose.ui.unit.Dp m7014boximpl(float a0) {
        return (androidx.compose.ui.unit.Dp) call(M_m7014boximpl, null, a0);
    }
    private static final Method M_m7030unboximpl = find("androidx.compose.ui.unit.Dp", "unbox-impl");
    public static float m7030unboximpl(androidx.compose.ui.unit.Dp recv) {
        return (Float) call(M_m7030unboximpl, recv);
    }
    private static final Method M_m7015compareTo0680j_4 = find("androidx.compose.ui.unit.Dp", "compareTo-0680j_4", float.class, float.class);
    public static int m7015compareTo0680j_4(float a0, float a1) {
        return (Integer) call(M_m7015compareTo0680j_4, null, a0, a1);
    }
    private static final Method M_m4536getAlphaimpl = find("androidx.compose.ui.graphics.Color", "getAlpha-impl", long.class);
    public static float m4536getAlphaimpl(long a0) {
        return (Float) call(M_m4536getAlphaimpl, null, a0);
    }
    private static final Method M_m4540getRedimpl = find("androidx.compose.ui.graphics.Color", "getRed-impl", long.class);
    public static float m4540getRedimpl(long a0) {
        return (Float) call(M_m4540getRedimpl, null, a0);
    }
    private static final Method M_m4539getGreenimpl = find("androidx.compose.ui.graphics.Color", "getGreen-impl", long.class);
    public static float m4539getGreenimpl(long a0) {
        return (Float) call(M_m4539getGreenimpl, null, a0);
    }
    private static final Method M_m4537getBlueimpl = find("androidx.compose.ui.graphics.Color", "getBlue-impl", long.class);
    public static float m4537getBlueimpl(long a0) {
        return (Float) call(M_m4537getBlueimpl, null, a0);
    }
    private static final Method M_m4532copywmQWz5c = find("androidx.compose.ui.graphics.Color", "copy-wmQWz5c", long.class, float.class, float.class, float.class, float.class);
    public static long m4532copywmQWz5c(long a0, float a1, float a2, float a3, float a4) {
        return (Long) call(M_m4532copywmQWz5c, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m4524boximpl = find("androidx.compose.ui.graphics.Color", "box-impl", long.class);
    public static androidx.compose.ui.graphics.Color m4524boximpl(long a0) {
        return (androidx.compose.ui.graphics.Color) call(M_m4524boximpl, null, a0);
    }
    private static final Method M_m4544unboximpl = find("androidx.compose.ui.graphics.Color", "unbox-impl");
    public static long m4544unboximpl(androidx.compose.ui.graphics.Color recv) {
        return (Long) call(M_m4544unboximpl, recv);
    }
    private static final Method M_m4542toStringimpl = find("androidx.compose.ui.graphics.Color", "toString-impl", long.class);
    public static java.lang.String m4542toStringimpl(long a0) {
        return (java.lang.String) call(M_m4542toStringimpl, null, a0);
    }
    private static final Method M_m4541hashCodeimpl = find("androidx.compose.ui.graphics.Color", "hashCode-impl", long.class);
    public static int m4541hashCodeimpl(long a0) {
        return (Integer) call(M_m4541hashCodeimpl, null, a0);
    }
    private static final Method M_m4535equalsimpl0 = find("androidx.compose.ui.graphics.Color", "equals-impl0", long.class, long.class);
    public static boolean m4535equalsimpl0(long a0, long a1) {
        return (Boolean) call(M_m4535equalsimpl0, null, a0, a1);
    }
    private static final Method M_m4569getTransparent0d7_KjU = find("androidx.compose.ui.graphics.Color.Companion", "getTransparent-0d7_KjU");
    public static long m4569getTransparent0d7_KjU(androidx.compose.ui.graphics.Color.Companion recv) {
        return (Long) call(M_m4569getTransparent0d7_KjU, recv);
    }
    private static final Method M_m4560getBlack0d7_KjU = find("androidx.compose.ui.graphics.Color.Companion", "getBlack-0d7_KjU");
    public static long m4560getBlack0d7_KjU(androidx.compose.ui.graphics.Color.Companion recv) {
        return (Long) call(M_m4560getBlack0d7_KjU, recv);
    }
    private static final Method M_m4571getWhite0d7_KjU = find("androidx.compose.ui.graphics.Color.Companion", "getWhite-0d7_KjU");
    public static long m4571getWhite0d7_KjU(androidx.compose.ui.graphics.Color.Companion recv) {
        return (Long) call(M_m4571getWhite0d7_KjU, recv);
    }
    private static final Method M_m4293getXimpl = find("androidx.compose.ui.geometry.Offset", "getX-impl", long.class);
    public static float m4293getXimpl(long a0) {
        return (Float) call(M_m4293getXimpl, null, a0);
    }
    private static final Method M_m4294getYimpl = find("androidx.compose.ui.geometry.Offset", "getY-impl", long.class);
    public static float m4294getYimpl(long a0) {
        return (Float) call(M_m4294getYimpl, null, a0);
    }
    private static final Method M_m4307getInfiniteF1C5BW0 = find("androidx.compose.ui.geometry.Offset.Companion", "getInfinite-F1C5BW0");
    public static long m4307getInfiniteF1C5BW0(androidx.compose.ui.geometry.Offset.Companion recv) {
        return (Long) call(M_m4307getInfiniteF1C5BW0, recv);
    }
    private static final Method M_m4308getUnspecifiedF1C5BW0 = find("androidx.compose.ui.geometry.Offset.Companion", "getUnspecified-F1C5BW0");
    public static long m4308getUnspecifiedF1C5BW0(androidx.compose.ui.geometry.Offset.Companion recv) {
        return (Long) call(M_m4308getUnspecifiedF1C5BW0, recv);
    }
    private static final Method M_m4309getZeroF1C5BW0 = find("androidx.compose.ui.geometry.Offset.Companion", "getZero-F1C5BW0");
    public static long m4309getZeroF1C5BW0(androidx.compose.ui.geometry.Offset.Companion recv) {
        return (Long) call(M_m4309getZeroF1C5BW0, recv);
    }
    private static final Method M_m4282boximpl = find("androidx.compose.ui.geometry.Offset", "box-impl", long.class);
    public static androidx.compose.ui.geometry.Offset m4282boximpl(long a0) {
        return (androidx.compose.ui.geometry.Offset) call(M_m4282boximpl, null, a0);
    }
    private static final Method M_m4362getWidthimpl = find("androidx.compose.ui.geometry.Size", "getWidth-impl", long.class);
    public static float m4362getWidthimpl(long a0) {
        return (Float) call(M_m4362getWidthimpl, null, a0);
    }
    private static final Method M_m4359getHeightimpl = find("androidx.compose.ui.geometry.Size", "getHeight-impl", long.class);
    public static float m4359getHeightimpl(long a0) {
        return (Float) call(M_m4359getHeightimpl, null, a0);
    }
    private static final Method M_m4361getMinDimensionimpl = find("androidx.compose.ui.geometry.Size", "getMinDimension-impl", long.class);
    public static float m4361getMinDimensionimpl(long a0) {
        return (Float) call(M_m4361getMinDimensionimpl, null, a0);
    }
    private static final Method M_m4027constructorimpl = find("androidx.compose.runtime.Updater", "constructor-impl", androidx.compose.runtime.Composer.class);
    public static androidx.compose.runtime.Composer m4027constructorimpl(androidx.compose.runtime.Composer a0) {
        return (androidx.compose.runtime.Composer) call(M_m4027constructorimpl, null, a0);
    }
    private static final Method M_m4034setimpl = find("androidx.compose.runtime.Updater", "set-impl", androidx.compose.runtime.Composer.class, java.lang.Object.class, kotlin.jvm.functions.Function2.class);
    public static void m4034setimpl(androidx.compose.runtime.Composer a0, java.lang.Object a1, kotlin.jvm.functions.Function2 a2) {
        call(M_m4034setimpl, null, a0, a1, a2);
    }
    private static final Method M_m1065height3ABfNKs = find("androidx.compose.foundation.layout.SizeKt", "height-3ABfNKs", androidx.compose.ui.Modifier.class, float.class);
    public static androidx.compose.ui.Modifier m1065height3ABfNKs(androidx.compose.ui.Modifier a0, float a1) {
        return (androidx.compose.ui.Modifier) call(M_m1065height3ABfNKs, null, a0, a1);
    }
    private static final Method M_m1084width3ABfNKs = find("androidx.compose.foundation.layout.SizeKt", "width-3ABfNKs", androidx.compose.ui.Modifier.class, float.class);
    public static androidx.compose.ui.Modifier m1084width3ABfNKs(androidx.compose.ui.Modifier a0, float a1) {
        return (androidx.compose.ui.Modifier) call(M_m1084width3ABfNKs, null, a0, a1);
    }
    private static final Method M_m1079size3ABfNKs = find("androidx.compose.foundation.layout.SizeKt", "size-3ABfNKs", androidx.compose.ui.Modifier.class, float.class);
    public static androidx.compose.ui.Modifier m1079size3ABfNKs(androidx.compose.ui.Modifier a0, float a1) {
        return (androidx.compose.ui.Modifier) call(M_m1079size3ABfNKs, null, a0, a1);
    }
    private static final Method M_m1081sizeVpY3zN4 = find("androidx.compose.foundation.layout.SizeKt", "size-VpY3zN4", androidx.compose.ui.Modifier.class, float.class, float.class);
    public static androidx.compose.ui.Modifier m1081sizeVpY3zN4(androidx.compose.ui.Modifier a0, float a1, float a2) {
        return (androidx.compose.ui.Modifier) call(M_m1081sizeVpY3zN4, null, a0, a1, a2);
    }
    private static final Method M_m1067heightInVpY3zN4_default = find("androidx.compose.foundation.layout.SizeKt", "heightIn-VpY3zN4$default", androidx.compose.ui.Modifier.class, float.class, float.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m1067heightInVpY3zN4$default(androidx.compose.ui.Modifier a0, float a1, float a2, int a3, java.lang.Object a4) {
        return (androidx.compose.ui.Modifier) call(M_m1067heightInVpY3zN4_default, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m1086widthInVpY3zN4_default = find("androidx.compose.foundation.layout.SizeKt", "widthIn-VpY3zN4$default", androidx.compose.ui.Modifier.class, float.class, float.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m1086widthInVpY3zN4$default(androidx.compose.ui.Modifier a0, float a1, float a2, int a3, java.lang.Object a4) {
        return (androidx.compose.ui.Modifier) call(M_m1086widthInVpY3zN4_default, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m1035paddingVpY3zN4 = find("androidx.compose.foundation.layout.PaddingKt", "padding-VpY3zN4", androidx.compose.ui.Modifier.class, float.class, float.class);
    public static androidx.compose.ui.Modifier m1035paddingVpY3zN4(androidx.compose.ui.Modifier a0, float a1, float a2) {
        return (androidx.compose.ui.Modifier) call(M_m1035paddingVpY3zN4, null, a0, a1, a2);
    }
    private static final Method M_m1036paddingVpY3zN4_default = find("androidx.compose.foundation.layout.PaddingKt", "padding-VpY3zN4$default", androidx.compose.ui.Modifier.class, float.class, float.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m1036paddingVpY3zN4$default(androidx.compose.ui.Modifier a0, float a1, float a2, int a3, java.lang.Object a4) {
        return (androidx.compose.ui.Modifier) call(M_m1036paddingVpY3zN4_default, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m1037paddingqDBjuR0 = find("androidx.compose.foundation.layout.PaddingKt", "padding-qDBjuR0", androidx.compose.ui.Modifier.class, float.class, float.class, float.class, float.class);
    public static androidx.compose.ui.Modifier m1037paddingqDBjuR0(androidx.compose.ui.Modifier a0, float a1, float a2, float a3, float a4) {
        return (androidx.compose.ui.Modifier) call(M_m1037paddingqDBjuR0, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m1038paddingqDBjuR0_default = find("androidx.compose.foundation.layout.PaddingKt", "padding-qDBjuR0$default", androidx.compose.ui.Modifier.class, float.class, float.class, float.class, float.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m1038paddingqDBjuR0$default(androidx.compose.ui.Modifier a0, float a1, float a2, float a3, float a4, int a5, java.lang.Object a6) {
        return (androidx.compose.ui.Modifier) call(M_m1038paddingqDBjuR0_default, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m1034padding3ABfNKs = find("androidx.compose.foundation.layout.PaddingKt", "padding-3ABfNKs", androidx.compose.ui.Modifier.class, float.class);
    public static androidx.compose.ui.Modifier m1034padding3ABfNKs(androidx.compose.ui.Modifier a0, float a1) {
        return (androidx.compose.ui.Modifier) call(M_m1034padding3ABfNKs, null, a0, a1);
    }
    private static final Method M_m1027PaddingValues0680j_4 = find("androidx.compose.foundation.layout.PaddingKt", "PaddingValues-0680j_4", float.class);
    public static androidx.compose.foundation.layout.PaddingValues m1027PaddingValues0680j_4(float a0) {
        return (androidx.compose.foundation.layout.PaddingValues) call(M_m1027PaddingValues0680j_4, null, a0);
    }
    private static final Method M_m1031PaddingValuesa9UjIt4_default = find("androidx.compose.foundation.layout.PaddingKt", "PaddingValues-a9UjIt4$default", float.class, float.class, float.class, float.class, int.class, java.lang.Object.class);
    public static androidx.compose.foundation.layout.PaddingValues m1031PaddingValuesa9UjIt4$default(float a0, float a1, float a2, float a3, int a4, java.lang.Object a5) {
        return (androidx.compose.foundation.layout.PaddingValues) call(M_m1031PaddingValuesa9UjIt4_default, null, a0, a1, a2, a3, a4, a5);
    }
    private static final Method M_m600borderxT4_qwU = find("androidx.compose.foundation.BorderKt", "border-xT4_qwU", androidx.compose.ui.Modifier.class, float.class, long.class, androidx.compose.ui.graphics.Shape.class);
    public static androidx.compose.ui.Modifier m600borderxT4_qwU(androidx.compose.ui.Modifier a0, float a1, long a2, androidx.compose.ui.graphics.Shape a3) {
        return (androidx.compose.ui.Modifier) call(M_m600borderxT4_qwU, null, a0, a1, a2, a3);
    }
    private static final Method M_m589backgroundbw27NRU_default = find("androidx.compose.foundation.BackgroundKt", "background-bw27NRU$default", androidx.compose.ui.Modifier.class, long.class, androidx.compose.ui.graphics.Shape.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m589backgroundbw27NRU$default(androidx.compose.ui.Modifier a0, long a1, androidx.compose.ui.graphics.Shape a2, int a3, java.lang.Object a4) {
        return (androidx.compose.ui.Modifier) call(M_m589backgroundbw27NRU_default, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m622clickableXHw0xAI_default = find("androidx.compose.foundation.ClickableKt", "clickable-XHw0xAI$default", androidx.compose.ui.Modifier.class, boolean.class, java.lang.String.class, androidx.compose.ui.semantics.Role.class, kotlin.jvm.functions.Function0.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m622clickableXHw0xAI$default(androidx.compose.ui.Modifier a0, boolean a1, java.lang.String a2, androidx.compose.ui.semantics.Role a3, kotlin.jvm.functions.Function0 a4, int a5, java.lang.Object a6) {
        return (androidx.compose.ui.Modifier) call(M_m622clickableXHw0xAI_default, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m619clickableO2vRcR0 = find("androidx.compose.foundation.ClickableKt", "clickable-O2vRcR0", androidx.compose.ui.Modifier.class, androidx.compose.foundation.interaction.MutableInteractionSource.class, androidx.compose.foundation.Indication.class, boolean.class, java.lang.String.class, androidx.compose.ui.semantics.Role.class, kotlin.jvm.functions.Function0.class);
    public static androidx.compose.ui.Modifier m619clickableO2vRcR0(androidx.compose.ui.Modifier a0, androidx.compose.foundation.interaction.MutableInteractionSource a1, androidx.compose.foundation.Indication a2, boolean a3, java.lang.String a4, androidx.compose.ui.semantics.Role a5, kotlin.jvm.functions.Function0 a6) {
        return (androidx.compose.ui.Modifier) call(M_m619clickableO2vRcR0, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m1317RoundedCornerShape0680j_4 = find("androidx.compose.foundation.shape.RoundedCornerShapeKt", "RoundedCornerShape-0680j_4", float.class);
    public static androidx.compose.foundation.shape.RoundedCornerShape m1317RoundedCornerShape0680j_4(float a0) {
        return (androidx.compose.foundation.shape.RoundedCornerShape) call(M_m1317RoundedCornerShape0680j_4, null, a0);
    }
    private static final Method M_m1318RoundedCornerShapea9UjIt4 = find("androidx.compose.foundation.shape.RoundedCornerShapeKt", "RoundedCornerShape-a9UjIt4", float.class, float.class, float.class, float.class);
    public static androidx.compose.foundation.shape.RoundedCornerShape m1318RoundedCornerShapea9UjIt4(float a0, float a1, float a2, float a3) {
        return (androidx.compose.foundation.shape.RoundedCornerShape) call(M_m1318RoundedCornerShapea9UjIt4, null, a0, a1, a2, a3);
    }
    private static final Method M_m1319RoundedCornerShapea9UjIt4_default = find("androidx.compose.foundation.shape.RoundedCornerShapeKt", "RoundedCornerShape-a9UjIt4$default", float.class, float.class, float.class, float.class, int.class, java.lang.Object.class);
    public static androidx.compose.foundation.shape.RoundedCornerShape m1319RoundedCornerShapea9UjIt4$default(float a0, float a1, float a2, float a3, int a4, java.lang.Object a5) {
        return (androidx.compose.foundation.shape.RoundedCornerShape) call(M_m1319RoundedCornerShapea9UjIt4_default, null, a0, a1, a2, a3, a4, a5);
    }
    private static final Method M_m4198shadows4CzXII_default = find("androidx.compose.ui.draw.ShadowKt", "shadow-s4CzXII$default", androidx.compose.ui.Modifier.class, float.class, androidx.compose.ui.graphics.Shape.class, boolean.class, long.class, long.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m4198shadows4CzXII$default(androidx.compose.ui.Modifier a0, float a1, androidx.compose.ui.graphics.Shape a2, boolean a3, long a4, long a5, int a6, java.lang.Object a7) {
        return (androidx.compose.ui.Modifier) call(M_m4198shadows4CzXII_default, null, a0, a1, a2, a3, a4, a5, a6, a7);
    }
    private static final Method M_m4695graphicsLayerAp8cVGQ_default = find("androidx.compose.ui.graphics.GraphicsLayerModifierKt", "graphicsLayer-Ap8cVGQ$default", androidx.compose.ui.Modifier.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, long.class, androidx.compose.ui.graphics.Shape.class, boolean.class, androidx.compose.ui.graphics.RenderEffect.class, long.class, long.class, int.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.Modifier m4695graphicsLayerAp8cVGQ$default(androidx.compose.ui.Modifier a0, float a1, float a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9, float a10, long a11, androidx.compose.ui.graphics.Shape a12, boolean a13, androidx.compose.ui.graphics.RenderEffect a14, long a15, long a16, int a17, int a18, java.lang.Object a19) {
        return (androidx.compose.ui.Modifier) call(M_m4695graphicsLayerAp8cVGQ_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19);
    }
    private static final Method M_m914spacedBy0680j_4 = find("androidx.compose.foundation.layout.Arrangement", "spacedBy-0680j_4", float.class);
    public static androidx.compose.foundation.layout.Arrangement.HorizontalOrVertical m914spacedBy0680j_4(androidx.compose.foundation.layout.Arrangement recv, float a0) {
        return (androidx.compose.foundation.layout.Arrangement.HorizontalOrVertical) call(M_m914spacedBy0680j_4, recv, a0);
    }
    private static final Method M_m2949colorsV1nXRL4 = find("androidx.compose.material3.SwitchDefaults", "colors-V1nXRL4", long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, androidx.compose.runtime.Composer.class, int.class, int.class, int.class);
    public static androidx.compose.material3.SwitchColors m2949colorsV1nXRL4(androidx.compose.material3.SwitchDefaults recv, long a0, long a1, long a2, long a3, long a4, long a5, long a6, long a7, long a8, long a9, long a10, long a11, long a12, long a13, long a14, long a15, androidx.compose.runtime.Composer a16, int a17, int a18, int a19) {
        return (androidx.compose.material3.SwitchColors) call(M_m2949colorsV1nXRL4, recv, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19);
    }
    private static final Method M_m2183buttonColorsro_MJ88 = find("androidx.compose.material3.ButtonDefaults", "buttonColors-ro_MJ88", long.class, long.class, long.class, long.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static androidx.compose.material3.ButtonColors m2183buttonColorsro_MJ88(androidx.compose.material3.ButtonDefaults recv, long a0, long a1, long a2, long a3, androidx.compose.runtime.Composer a4, int a5, int a6) {
        return (androidx.compose.material3.ButtonColors) call(M_m2183buttonColorsro_MJ88, recv, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m2717colors0hiis_0 = find("androidx.compose.material3.OutlinedTextFieldDefaults", "colors-0hiis_0", long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, androidx.compose.foundation.text.selection.TextSelectionColors.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, androidx.compose.runtime.Composer.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class);
    public static androidx.compose.material3.TextFieldColors m2717colors0hiis_0(androidx.compose.material3.OutlinedTextFieldDefaults recv, long a0, long a1, long a2, long a3, long a4, long a5, long a6, long a7, long a8, long a9, androidx.compose.foundation.text.selection.TextSelectionColors a10, long a11, long a12, long a13, long a14, long a15, long a16, long a17, long a18, long a19, long a20, long a21, long a22, long a23, long a24, long a25, long a26, long a27, long a28, long a29, long a30, long a31, long a32, long a33, long a34, long a35, long a36, long a37, long a38, long a39, long a40, long a41, long a42, androidx.compose.runtime.Composer a43, int a44, int a45, int a46, int a47, int a48, int a49, int a50) {
        return (androidx.compose.material3.TextFieldColors) call(M_m2717colors0hiis_0, recv, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38, a39, a40, a41, a42, a43, a44, a45, a46, a47, a48, a49, a50);
    }
    private static final Method M_m2306darkColorSchemeCXl9yA_default = find("androidx.compose.material3.ColorSchemeKt", "darkColorScheme-C-Xl9yA$default", long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, long.class, int.class, int.class, java.lang.Object.class);
    public static androidx.compose.material3.ColorScheme m2306darkColorSchemeCXl9yA$default(long a0, long a1, long a2, long a3, long a4, long a5, long a6, long a7, long a8, long a9, long a10, long a11, long a12, long a13, long a14, long a15, long a16, long a17, long a18, long a19, long a20, long a21, long a22, long a23, long a24, long a25, long a26, long a27, long a28, long a29, long a30, long a31, long a32, long a33, long a34, long a35, int a36, int a37, java.lang.Object a38) {
        return (androidx.compose.material3.ColorScheme) call(M_m2306darkColorSchemeCXl9yA_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38);
    }
    private static final Method M_m4491verticalGradient8A3gB4_default = find("androidx.compose.ui.graphics.Brush.Companion", "verticalGradient-8A-3gB4$default", androidx.compose.ui.graphics.Brush.Companion.class, java.util.List.class, float.class, float.class, int.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.graphics.Brush m4491verticalGradient8A3gB4$default(androidx.compose.ui.graphics.Brush.Companion a0, java.util.List a1, float a2, float a3, int a4, int a5, java.lang.Object a6) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4491verticalGradient8A3gB4_default, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m4492verticalGradient8A3gB4_default = find("androidx.compose.ui.graphics.Brush.Companion", "verticalGradient-8A-3gB4$default", androidx.compose.ui.graphics.Brush.Companion.class, kotlin.Pair[].class, float.class, float.class, int.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.graphics.Brush m4492verticalGradient8A3gB4$default(androidx.compose.ui.graphics.Brush.Companion a0, kotlin.Pair[] a1, float a2, float a3, int a4, int a5, java.lang.Object a6) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4492verticalGradient8A3gB4_default, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m4483horizontalGradient8A3gB4_default = find("androidx.compose.ui.graphics.Brush.Companion", "horizontalGradient-8A-3gB4$default", androidx.compose.ui.graphics.Brush.Companion.class, java.util.List.class, float.class, float.class, int.class, int.class, java.lang.Object.class);
    public static androidx.compose.ui.graphics.Brush m4483horizontalGradient8A3gB4$default(androidx.compose.ui.graphics.Brush.Companion a0, java.util.List a1, float a2, float a3, int a4, int a5, java.lang.Object a6) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4483horizontalGradient8A3gB4_default, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m4496linearGradientmHitzGk = find("androidx.compose.ui.graphics.Brush.Companion", "linearGradient-mHitzGk", kotlin.Pair[].class, long.class, long.class, int.class);
    public static androidx.compose.ui.graphics.Brush m4496linearGradientmHitzGk(androidx.compose.ui.graphics.Brush.Companion recv, kotlin.Pair[] a0, long a1, long a2, int a3) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4496linearGradientmHitzGk, recv, a0, a1, a2, a3);
    }
    private static final Method M_m4497radialGradientP_VxKs = find("androidx.compose.ui.graphics.Brush.Companion", "radialGradient-P_Vx-Ks", java.util.List.class, long.class, float.class, int.class);
    public static androidx.compose.ui.graphics.Brush m4497radialGradientP_VxKs(androidx.compose.ui.graphics.Brush.Companion recv, java.util.List a0, long a1, float a2, int a3) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4497radialGradientP_VxKs, recv, a0, a1, a2, a3);
    }
    private static final Method M_m4498radialGradientP_VxKs = find("androidx.compose.ui.graphics.Brush.Companion", "radialGradient-P_Vx-Ks", kotlin.Pair[].class, long.class, float.class, int.class);
    public static androidx.compose.ui.graphics.Brush m4498radialGradientP_VxKs(androidx.compose.ui.graphics.Brush.Companion recv, kotlin.Pair[] a0, long a1, float a2, int a3) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4498radialGradientP_VxKs, recv, a0, a1, a2, a3);
    }
    private static final Method M_m4499sweepGradientUv8p0NA = find("androidx.compose.ui.graphics.Brush.Companion", "sweepGradient-Uv8p0NA", java.util.List.class, long.class);
    public static androidx.compose.ui.graphics.Brush m4499sweepGradientUv8p0NA(androidx.compose.ui.graphics.Brush.Companion recv, java.util.List a0, long a1) {
        return (androidx.compose.ui.graphics.Brush) call(M_m4499sweepGradientUv8p0NA, recv, a0, a1);
    }
    private static final Method M_m3067Text4IGK_g = find("androidx.compose.material3.TextKt", "Text--4IGK_g", java.lang.String.class, androidx.compose.ui.Modifier.class, long.class, long.class, androidx.compose.ui.text.font.FontStyle.class, androidx.compose.ui.text.font.FontWeight.class, androidx.compose.ui.text.font.FontFamily.class, long.class, androidx.compose.ui.text.style.TextDecoration.class, androidx.compose.ui.text.style.TextAlign.class, long.class, int.class, boolean.class, int.class, int.class, kotlin.jvm.functions.Function1.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.runtime.Composer.class, int.class, int.class, int.class);
    public static void m3067Text4IGK_g(java.lang.String a0, androidx.compose.ui.Modifier a1, long a2, long a3, androidx.compose.ui.text.font.FontStyle a4, androidx.compose.ui.text.font.FontWeight a5, androidx.compose.ui.text.font.FontFamily a6, long a7, androidx.compose.ui.text.style.TextDecoration a8, androidx.compose.ui.text.style.TextAlign a9, long a10, int a11, boolean a12, int a13, int a14, kotlin.jvm.functions.Function1 a15, androidx.compose.ui.text.TextStyle a16, androidx.compose.runtime.Composer a17, int a18, int a19, int a20) {
        call(M_m3067Text4IGK_g, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20);
    }
    private static final Method M_m3068TextIbK3jfQ = find("androidx.compose.material3.TextKt", "Text-IbK3jfQ", androidx.compose.ui.text.AnnotatedString.class, androidx.compose.ui.Modifier.class, long.class, long.class, androidx.compose.ui.text.font.FontStyle.class, androidx.compose.ui.text.font.FontWeight.class, androidx.compose.ui.text.font.FontFamily.class, long.class, androidx.compose.ui.text.style.TextDecoration.class, androidx.compose.ui.text.style.TextAlign.class, long.class, int.class, boolean.class, int.class, int.class, java.util.Map.class, kotlin.jvm.functions.Function1.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.runtime.Composer.class, int.class, int.class, int.class);
    public static void m3068TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString a0, androidx.compose.ui.Modifier a1, long a2, long a3, androidx.compose.ui.text.font.FontStyle a4, androidx.compose.ui.text.font.FontWeight a5, androidx.compose.ui.text.font.FontFamily a6, long a7, androidx.compose.ui.text.style.TextDecoration a8, androidx.compose.ui.text.style.TextAlign a9, long a10, int a11, boolean a12, int a13, int a14, java.util.Map a15, kotlin.jvm.functions.Function1 a16, androidx.compose.ui.text.TextStyle a17, androidx.compose.runtime.Composer a18, int a19, int a20, int a21) {
        call(M_m3068TextIbK3jfQ, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21);
    }
    private static final Method M_m2524Iconww6aTOc = find("androidx.compose.material3.IconKt", "Icon-ww6aTOc", androidx.compose.ui.graphics.vector.ImageVector.class, java.lang.String.class, androidx.compose.ui.Modifier.class, long.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static void m2524Iconww6aTOc(androidx.compose.ui.graphics.vector.ImageVector a0, java.lang.String a1, androidx.compose.ui.Modifier a2, long a3, androidx.compose.runtime.Composer a4, int a5, int a6) {
        call(M_m2524Iconww6aTOc, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m2523Iconww6aTOc = find("androidx.compose.material3.IconKt", "Icon-ww6aTOc", androidx.compose.ui.graphics.painter.Painter.class, java.lang.String.class, androidx.compose.ui.Modifier.class, long.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static void m2523Iconww6aTOc(androidx.compose.ui.graphics.painter.Painter a0, java.lang.String a1, androidx.compose.ui.Modifier a2, long a3, androidx.compose.runtime.Composer a4, int a5, int a6) {
        call(M_m2523Iconww6aTOc, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m2446HorizontalDivider9IZ8Weo = find("androidx.compose.material3.DividerKt", "HorizontalDivider-9IZ8Weo", androidx.compose.ui.Modifier.class, float.class, long.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static void m2446HorizontalDivider9IZ8Weo(androidx.compose.ui.Modifier a0, float a1, long a2, androidx.compose.runtime.Composer a3, int a4, int a5) {
        call(M_m2446HorizontalDivider9IZ8Weo, null, a0, a1, a2, a3, a4, a5);
    }
    private static final Method M_m2741CircularProgressIndicatorLxG7B9w = find("androidx.compose.material3.ProgressIndicatorKt", "CircularProgressIndicator-LxG7B9w", androidx.compose.ui.Modifier.class, long.class, float.class, long.class, int.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static void m2741CircularProgressIndicatorLxG7B9w(androidx.compose.ui.Modifier a0, long a1, float a2, long a3, int a4, androidx.compose.runtime.Composer a5, int a6, int a7) {
        call(M_m2741CircularProgressIndicatorLxG7B9w, null, a0, a1, a2, a3, a4, a5, a6, a7);
    }
    private static final Method M_m2750LinearProgressIndicatorrIrjwxo = find("androidx.compose.material3.ProgressIndicatorKt", "LinearProgressIndicator-rIrjwxo", androidx.compose.ui.Modifier.class, long.class, long.class, int.class, float.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static void m2750LinearProgressIndicatorrIrjwxo(androidx.compose.ui.Modifier a0, long a1, long a2, int a3, float a4, androidx.compose.runtime.Composer a5, int a6, int a7) {
        call(M_m2750LinearProgressIndicatorrIrjwxo, null, a0, a1, a2, a3, a4, a5, a6, a7);
    }
    private static final Method M_m2135AlertDialogOix01E0 = find("androidx.compose.material3.AndroidAlertDialog_androidKt", "AlertDialog-Oix01E0", kotlin.jvm.functions.Function0.class, kotlin.jvm.functions.Function2.class, androidx.compose.ui.Modifier.class, kotlin.jvm.functions.Function2.class, kotlin.jvm.functions.Function2.class, kotlin.jvm.functions.Function2.class, kotlin.jvm.functions.Function2.class, androidx.compose.ui.graphics.Shape.class, long.class, long.class, long.class, long.class, float.class, androidx.compose.ui.window.DialogProperties.class, androidx.compose.runtime.Composer.class, int.class, int.class, int.class);
    public static void m2135AlertDialogOix01E0(kotlin.jvm.functions.Function0 a0, kotlin.jvm.functions.Function2 a1, androidx.compose.ui.Modifier a2, kotlin.jvm.functions.Function2 a3, kotlin.jvm.functions.Function2 a4, kotlin.jvm.functions.Function2 a5, kotlin.jvm.functions.Function2 a6, androidx.compose.ui.graphics.Shape a7, long a8, long a9, long a10, long a11, float a12, androidx.compose.ui.window.DialogProperties a13, androidx.compose.runtime.Composer a14, int a15, int a16, int a17) {
        call(M_m2135AlertDialogOix01E0, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17);
    }
    private static final Method M_m2138DropdownMenuIlH_yew = find("androidx.compose.material3.AndroidMenu_androidKt", "DropdownMenu-IlH_yew", boolean.class, kotlin.jvm.functions.Function0.class, androidx.compose.ui.Modifier.class, long.class, androidx.compose.foundation.ScrollState.class, androidx.compose.ui.window.PopupProperties.class, androidx.compose.ui.graphics.Shape.class, long.class, float.class, float.class, androidx.compose.foundation.BorderStroke.class, kotlin.jvm.functions.Function3.class, androidx.compose.runtime.Composer.class, int.class, int.class, int.class);
    public static void m2138DropdownMenuIlH_yew(boolean a0, kotlin.jvm.functions.Function0 a1, androidx.compose.ui.Modifier a2, long a3, androidx.compose.foundation.ScrollState a4, androidx.compose.ui.window.PopupProperties a5, androidx.compose.ui.graphics.Shape a6, long a7, float a8, float a9, androidx.compose.foundation.BorderStroke a10, kotlin.jvm.functions.Function3 a11, androidx.compose.runtime.Composer a12, int a13, int a14, int a15) {
        call(M_m2138DropdownMenuIlH_yew, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
    }
    private static final Method M_m467animateColorAsStateeuL9pac = find("androidx.compose.animation.SingleValueAnimationKt", "animateColorAsState-euL9pac", long.class, androidx.compose.animation.core.AnimationSpec.class, java.lang.String.class, kotlin.jvm.functions.Function1.class, androidx.compose.runtime.Composer.class, int.class, int.class);
    public static androidx.compose.runtime.State m467animateColorAsStateeuL9pac(long a0, androidx.compose.animation.core.AnimationSpec a1, java.lang.String a2, kotlin.jvm.functions.Function1 a3, androidx.compose.runtime.Composer a4, int a5, int a6) {
        return (androidx.compose.runtime.State) call(M_m467animateColorAsStateeuL9pac, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static final Method M_m492infiniteRepeatable9IiC70o_default = find("androidx.compose.animation.core.AnimationSpecKt", "infiniteRepeatable-9IiC70o$default", androidx.compose.animation.core.DurationBasedAnimationSpec.class, androidx.compose.animation.core.RepeatMode.class, long.class, int.class, java.lang.Object.class);
    public static androidx.compose.animation.core.InfiniteRepeatableSpec m492infiniteRepeatable9IiC70o$default(androidx.compose.animation.core.DurationBasedAnimationSpec a0, androidx.compose.animation.core.RepeatMode a1, long a2, int a3, java.lang.Object a4) {
        return (androidx.compose.animation.core.InfiniteRepeatableSpec) call(M_m492infiniteRepeatable9IiC70o_default, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m427scaleInL8ZKhE_default = find("androidx.compose.animation.EnterExitTransitionKt", "scaleIn-L8ZKh-E$default", androidx.compose.animation.core.FiniteAnimationSpec.class, float.class, long.class, int.class, java.lang.Object.class);
    public static androidx.compose.animation.EnterTransition m427scaleInL8ZKhE$default(androidx.compose.animation.core.FiniteAnimationSpec a0, float a1, long a2, int a3, java.lang.Object a4) {
        return (androidx.compose.animation.EnterTransition) call(M_m427scaleInL8ZKhE_default, null, a0, a1, a2, a3, a4);
    }
    private static final Method M_m6308setRolekuIjeqM = find("androidx.compose.ui.semantics.SemanticsPropertiesKt", "setRole-kuIjeqM", androidx.compose.ui.semantics.SemanticsPropertyReceiver.class, int.class);
    public static void m6308setRolekuIjeqM(androidx.compose.ui.semantics.SemanticsPropertyReceiver a0, int a1) {
        call(M_m6308setRolekuIjeqM, null, a0, a1);
    }
    private static final Method M_m6307setLiveRegionhR3wRGc = find("androidx.compose.ui.semantics.SemanticsPropertiesKt", "setLiveRegion-hR3wRGc", androidx.compose.ui.semantics.SemanticsPropertyReceiver.class, int.class);
    public static void m6307setLiveRegionhR3wRGc(androidx.compose.ui.semantics.SemanticsPropertyReceiver a0, int a1) {
        call(M_m6307setLiveRegionhR3wRGc, null, a0, a1);
    }
    private static final Method M_m6933getEllipsisgIe3tQ8 = find("androidx.compose.ui.text.style.TextOverflow$Companion", "getEllipsis-gIe3tQ8");
    public static int m6933getEllipsisgIe3tQ8(androidx.compose.ui.text.style.TextOverflow.Companion recv) {
        return (Integer) call(M_m6933getEllipsisgIe3tQ8, recv);
    }
    private static final Method M_m6883getCentere0LSkKk = find("androidx.compose.ui.text.style.TextAlign$Companion", "getCenter-e0LSkKk");
    public static int m6883getCentere0LSkKk(androidx.compose.ui.text.style.TextAlign.Companion recv) {
        return (Integer) call(M_m6883getCentere0LSkKk, recv);
    }
    private static final Method M_m6876boximpl = find("androidx.compose.ui.text.style.TextAlign", "box-impl", int.class);
    public static androidx.compose.ui.text.style.TextAlign m6876boximpl(int a0) {
        return (androidx.compose.ui.text.style.TextAlign) call(M_m6876boximpl, null, a0);
    }
    private static final Method M_m6595getItalic_LCdwA = find("androidx.compose.ui.text.font.FontStyle$Companion", "getItalic-_-LCdwA");
    public static int m6595getItalic_LCdwA(androidx.compose.ui.text.font.FontStyle.Companion recv) {
        return (Integer) call(M_m6595getItalic_LCdwA, recv);
    }
    private static final Method M_m6586boximpl = find("androidx.compose.ui.text.font.FontStyle", "box-impl", int.class);
    public static androidx.compose.ui.text.font.FontStyle m6586boximpl(int a0) {
        return (androidx.compose.ui.text.font.FontStyle) call(M_m6586boximpl, null, a0);
    }
    private static final Method M_m6691getSentencesIUNYP9k = find("androidx.compose.ui.text.input.KeyboardCapitalization$Companion", "getSentences-IUNYP9k");
    public static int m6691getSentencesIUNYP9k(androidx.compose.ui.text.input.KeyboardCapitalization.Companion recv) {
        return (Integer) call(M_m6691getSentencesIUNYP9k, recv);
    }
    private static final Method M_m4907getClamp3opZhB0 = find("androidx.compose.ui.graphics.TileMode$Companion", "getClamp-3opZhB0");
    public static int m4907getClamp3opZhB0(androidx.compose.ui.graphics.TileMode.Companion recv) {
        return (Integer) call(M_m4907getClamp3opZhB0, recv);
    }
    private static final Method M_m4888getRoundKaPHkGw = find("androidx.compose.ui.graphics.StrokeCap.Companion", "getRound-KaPHkGw");
    public static int m4888getRoundKaPHkGw(androidx.compose.ui.graphics.StrokeCap.Companion recv) {
        return (Integer) call(M_m4888getRoundKaPHkGw, recv);
    }
    private static final Method M_m6289getButtono7Vup1c = find("androidx.compose.ui.semantics.Role$Companion", "getButton-o7Vup1c");
    public static int m6289getButtono7Vup1c(androidx.compose.ui.semantics.Role.Companion recv) {
        return (Integer) call(M_m6289getButtono7Vup1c, recv);
    }
    private static final Method M_m6282boximpl = find("androidx.compose.ui.semantics.Role", "box-impl", int.class);
    public static androidx.compose.ui.semantics.Role m6282boximpl(int a0) {
        return (androidx.compose.ui.semantics.Role) call(M_m6282boximpl, null, a0);
    }
    private static final Method M_m6281getPolite0phEisY = find("androidx.compose.ui.semantics.LiveRegionMode$Companion", "getPolite-0phEisY");
    public static int m6281getPolite0phEisY(androidx.compose.ui.semantics.LiveRegionMode.Companion recv) {
        return (Integer) call(M_m6281getPolite0phEisY, recv);
    }
    private static final Method M_mo5090getSizeNHjbRc = find("androidx.compose.ui.graphics.drawscope.DrawScope", "getSize-NH-jbRc");
    public static long mo5090getSizeNHjbRc(androidx.compose.ui.graphics.drawscope.DrawScope recv) {
        return (Long) call(M_mo5090getSizeNHjbRc, recv);
    }
    private static final Method M_mo5011getSizeNHjbRc = find("androidx.compose.ui.graphics.drawscope.DrawContext", "getSize-NH-jbRc");
    public static long mo5011getSizeNHjbRc(androidx.compose.ui.graphics.drawscope.DrawContext recv) {
        return (Long) call(M_mo5011getSizeNHjbRc, recv);
    }
    private static final Method M_mo5017rotateUv8p0NA = find("androidx.compose.ui.graphics.drawscope.DrawTransform", "rotate-Uv8p0NA", float.class, long.class);
    public static void mo5017rotateUv8p0NA(androidx.compose.ui.graphics.drawscope.DrawTransform recv, float a0, long a1) {
        call(M_mo5017rotateUv8p0NA, recv, a0, a1);
    }
    // STUB: androidx.compose.ui.graphics.drawscope.DrawTransform.setSize-uvyYCjk missing from current library version
    private static final Method M_mo5012setSizeuvyYCjk = find("androidx.compose.ui.graphics.drawscope.DrawContext", "setSize-uvyYCjk", long.class);
    public static void mo5012setSizeuvyYCjk(androidx.compose.ui.graphics.drawscope.DrawContext recv, long a0) {
        call(M_mo5012setSizeuvyYCjk, recv, a0);
    }
    private static final Method M_mo942getMaxHeightD9Ej5fM = find("androidx.compose.foundation.layout.BoxWithConstraintsScope", "getMaxHeight-D9Ej5fM");
    public static float mo942getMaxHeightD9Ej5fM(androidx.compose.foundation.layout.BoxWithConstraintsScope recv) {
        return (Float) call(M_mo942getMaxHeightD9Ej5fM, recv);
    }
    private static final Method M_mo943getMaxWidthD9Ej5fM = find("androidx.compose.foundation.layout.BoxWithConstraintsScope", "getMaxWidth-D9Ej5fM");
    public static float mo943getMaxWidthD9Ej5fM(androidx.compose.foundation.layout.BoxWithConstraintsScope recv) {
        return (Float) call(M_mo943getMaxWidthD9Ej5fM, recv);
    }
    private static final Method M_m5070drawCircleV9BoPsw_default = find("androidx.compose.ui.graphics.drawscope.DrawScope", "drawCircle-V9BoPsw$default", androidx.compose.ui.graphics.drawscope.DrawScope.class, androidx.compose.ui.graphics.Brush.class, float.class, long.class, float.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, androidx.compose.ui.graphics.ColorFilter.class, int.class, int.class, java.lang.Object.class);
    public static void m5070drawCircleV9BoPsw$default(androidx.compose.ui.graphics.drawscope.DrawScope a0, androidx.compose.ui.graphics.Brush a1, float a2, long a3, float a4, androidx.compose.ui.graphics.drawscope.DrawStyle a5, androidx.compose.ui.graphics.ColorFilter a6, int a7, int a8, java.lang.Object a9) {
        call(M_m5070drawCircleV9BoPsw_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }
    private static final Method M_m5071drawCircleVaOC9Bg_default = find("androidx.compose.ui.graphics.drawscope.DrawScope", "drawCircle-VaOC9Bg$default", androidx.compose.ui.graphics.drawscope.DrawScope.class, long.class, float.class, long.class, float.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, androidx.compose.ui.graphics.ColorFilter.class, int.class, int.class, java.lang.Object.class);
    public static void m5071drawCircleVaOC9Bg$default(androidx.compose.ui.graphics.drawscope.DrawScope a0, long a1, float a2, long a3, float a4, androidx.compose.ui.graphics.drawscope.DrawStyle a5, androidx.compose.ui.graphics.ColorFilter a6, int a7, int a8, java.lang.Object a9) {
        call(M_m5071drawCircleVaOC9Bg_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9);
    }
    private static final Method M_m5076drawLineNGM6Ib0_default = find("androidx.compose.ui.graphics.drawscope.DrawScope", "drawLine-NGM6Ib0$default", androidx.compose.ui.graphics.drawscope.DrawScope.class, long.class, long.class, long.class, float.class, int.class, androidx.compose.ui.graphics.PathEffect.class, float.class, androidx.compose.ui.graphics.ColorFilter.class, int.class, int.class, java.lang.Object.class);
    public static void m5076drawLineNGM6Ib0$default(androidx.compose.ui.graphics.drawscope.DrawScope a0, long a1, long a2, long a3, float a4, int a5, androidx.compose.ui.graphics.PathEffect a6, float a7, androidx.compose.ui.graphics.ColorFilter a8, int a9, int a10, java.lang.Object a11) {
        call(M_m5076drawLineNGM6Ib0_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
    }
    private static final Method M_m5069drawArcyD3GUKo_default = find("androidx.compose.ui.graphics.drawscope.DrawScope", "drawArc-yD3GUKo$default", androidx.compose.ui.graphics.drawscope.DrawScope.class, long.class, float.class, float.class, boolean.class, long.class, long.class, float.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, androidx.compose.ui.graphics.ColorFilter.class, int.class, int.class, java.lang.Object.class);
    public static void m5069drawArcyD3GUKo$default(androidx.compose.ui.graphics.drawscope.DrawScope a0, long a1, float a2, float a3, boolean a4, long a5, long a6, float a7, androidx.compose.ui.graphics.drawscope.DrawStyle a8, androidx.compose.ui.graphics.ColorFilter a9, int a10, int a11, java.lang.Object a12) {
        call(M_m5069drawArcyD3GUKo_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
    }
    private static final Method M_m5068drawArcillE91I_default = find("androidx.compose.ui.graphics.drawscope.DrawScope", "drawArc-illE91I$default", androidx.compose.ui.graphics.drawscope.DrawScope.class, androidx.compose.ui.graphics.Brush.class, float.class, float.class, boolean.class, long.class, long.class, float.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, androidx.compose.ui.graphics.ColorFilter.class, int.class, int.class, java.lang.Object.class);
    public static void m5068drawArcillE91I$default(androidx.compose.ui.graphics.drawscope.DrawScope a0, androidx.compose.ui.graphics.Brush a1, float a2, float a3, boolean a4, long a5, long a6, float a7, androidx.compose.ui.graphics.drawscope.DrawStyle a8, androidx.compose.ui.graphics.ColorFilter a9, int a10, int a11, java.lang.Object a12) {
        call(M_m5068drawArcillE91I_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
    }
    private static final Method M_m5080drawPathLG529CI_default = find("androidx.compose.ui.graphics.drawscope.DrawScope", "drawPath-LG529CI$default", androidx.compose.ui.graphics.drawscope.DrawScope.class, androidx.compose.ui.graphics.Path.class, long.class, float.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, androidx.compose.ui.graphics.ColorFilter.class, int.class, int.class, java.lang.Object.class);
    public static void m5080drawPathLG529CI$default(androidx.compose.ui.graphics.drawscope.DrawScope a0, androidx.compose.ui.graphics.Path a1, long a2, float a3, androidx.compose.ui.graphics.drawscope.DrawStyle a4, androidx.compose.ui.graphics.ColorFilter a5, int a6, int a7, java.lang.Object a8) {
        call(M_m5080drawPathLG529CI_default, null, a0, a1, a2, a3, a4, a5, a6, a7, a8);
    }
    private static Method C_d_ComponentActivityKt_setContent_5;
    public static void d_ComponentActivityKt_setContent_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_ComponentActivityKt_setContent_5 == null) C_d_ComponentActivityKt_setContent_5 = flex("androidx.activity.compose.ComponentActivityKt", "setContent$default", 5, a0, a1, a2, a3, a4);
        call(C_d_ComponentActivityKt_setContent_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_EnterExitTransitionKt_expandVertically_6;
    public static androidx.compose.animation.EnterTransition d_EnterExitTransitionKt_expandVertically_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_EnterExitTransitionKt_expandVertically_6 == null) C_d_EnterExitTransitionKt_expandVertically_6 = flex("androidx.compose.animation.EnterExitTransitionKt", "expandVertically$default", 6, a0, a1, a2, a3, a4, a5);
        return (androidx.compose.animation.EnterTransition) call(C_d_EnterExitTransitionKt_expandVertically_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_EnterExitTransitionKt_fadeIn_4;
    public static androidx.compose.animation.EnterTransition d_EnterExitTransitionKt_fadeIn_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_EnterExitTransitionKt_fadeIn_4 == null) C_d_EnterExitTransitionKt_fadeIn_4 = flex("androidx.compose.animation.EnterExitTransitionKt", "fadeIn$default", 4, a0, a1, a2, a3);
        return (androidx.compose.animation.EnterTransition) call(C_d_EnterExitTransitionKt_fadeIn_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_EnterExitTransitionKt_fadeOut_4;
    public static androidx.compose.animation.ExitTransition d_EnterExitTransitionKt_fadeOut_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_EnterExitTransitionKt_fadeOut_4 == null) C_d_EnterExitTransitionKt_fadeOut_4 = flex("androidx.compose.animation.EnterExitTransitionKt", "fadeOut$default", 4, a0, a1, a2, a3);
        return (androidx.compose.animation.ExitTransition) call(C_d_EnterExitTransitionKt_fadeOut_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_AnimatableKt_Animatable_4;
    public static androidx.compose.animation.core.Animatable d_AnimatableKt_Animatable_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_AnimatableKt_Animatable_4 == null) C_d_AnimatableKt_Animatable_4 = flex("androidx.compose.animation.core.AnimatableKt", "Animatable$default", 4, a0, a1, a2, a3);
        return (androidx.compose.animation.core.Animatable) call(C_d_AnimatableKt_Animatable_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_AnimationSpecKt_spring_5;
    public static androidx.compose.animation.core.SpringSpec d_AnimationSpecKt_spring_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_AnimationSpecKt_spring_5 == null) C_d_AnimationSpecKt_spring_5 = flex("androidx.compose.animation.core.AnimationSpecKt", "spring$default", 5, a0, a1, a2, a3, a4);
        return (androidx.compose.animation.core.SpringSpec) call(C_d_AnimationSpecKt_spring_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_AnimationSpecKt_tween_5;
    public static androidx.compose.animation.core.TweenSpec d_AnimationSpecKt_tween_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_AnimationSpecKt_tween_5 == null) C_d_AnimationSpecKt_tween_5 = flex("androidx.compose.animation.core.AnimationSpecKt", "tween$default", 5, a0, a1, a2, a3, a4);
        return (androidx.compose.animation.core.TweenSpec) call(C_d_AnimationSpecKt_tween_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_BackgroundKt_background_6;
    public static androidx.compose.ui.Modifier d_BackgroundKt_background_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_BackgroundKt_background_6 == null) C_d_BackgroundKt_background_6 = flex("androidx.compose.foundation.BackgroundKt", "background$default", 6, a0, a1, a2, a3, a4, a5);
        return (androidx.compose.ui.Modifier) call(C_d_BackgroundKt_background_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_ScrollKt_verticalScroll_7;
    public static androidx.compose.ui.Modifier d_ScrollKt_verticalScroll_7(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5, java.lang.Object a6) {
        if (C_d_ScrollKt_verticalScroll_7 == null) C_d_ScrollKt_verticalScroll_7 = flex("androidx.compose.foundation.ScrollKt", "verticalScroll$default", 7, a0, a1, a2, a3, a4, a5, a6);
        return (androidx.compose.ui.Modifier) call(C_d_ScrollKt_verticalScroll_7, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static Method C_d_ColumnScope_weight_6;
    public static androidx.compose.ui.Modifier d_ColumnScope_weight_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_ColumnScope_weight_6 == null) C_d_ColumnScope_weight_6 = flex("androidx.compose.foundation.layout.ColumnScope", "weight$default", 6, a0, a1, a2, a3, a4, a5);
        return (androidx.compose.ui.Modifier) call(C_d_ColumnScope_weight_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_RowScope_weight_6;
    public static androidx.compose.ui.Modifier d_RowScope_weight_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_RowScope_weight_6 == null) C_d_RowScope_weight_6 = flex("androidx.compose.foundation.layout.RowScope", "weight$default", 6, a0, a1, a2, a3, a4, a5);
        return (androidx.compose.ui.Modifier) call(C_d_RowScope_weight_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_SizeKt_fillMaxHeight_4;
    public static androidx.compose.ui.Modifier d_SizeKt_fillMaxHeight_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_SizeKt_fillMaxHeight_4 == null) C_d_SizeKt_fillMaxHeight_4 = flex("androidx.compose.foundation.layout.SizeKt", "fillMaxHeight$default", 4, a0, a1, a2, a3);
        return (androidx.compose.ui.Modifier) call(C_d_SizeKt_fillMaxHeight_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_SizeKt_fillMaxSize_4;
    public static androidx.compose.ui.Modifier d_SizeKt_fillMaxSize_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_SizeKt_fillMaxSize_4 == null) C_d_SizeKt_fillMaxSize_4 = flex("androidx.compose.foundation.layout.SizeKt", "fillMaxSize$default", 4, a0, a1, a2, a3);
        return (androidx.compose.ui.Modifier) call(C_d_SizeKt_fillMaxSize_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_SizeKt_fillMaxWidth_4;
    public static androidx.compose.ui.Modifier d_SizeKt_fillMaxWidth_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_SizeKt_fillMaxWidth_4 == null) C_d_SizeKt_fillMaxWidth_4 = flex("androidx.compose.foundation.layout.SizeKt", "fillMaxWidth$default", 4, a0, a1, a2, a3);
        return (androidx.compose.ui.Modifier) call(C_d_SizeKt_fillMaxWidth_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_SizeKt_wrapContentHeight_5;
    public static androidx.compose.ui.Modifier d_SizeKt_wrapContentHeight_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_SizeKt_wrapContentHeight_5 == null) C_d_SizeKt_wrapContentHeight_5 = flex("androidx.compose.foundation.layout.SizeKt", "wrapContentHeight$default", 5, a0, a1, a2, a3, a4);
        return (androidx.compose.ui.Modifier) call(C_d_SizeKt_wrapContentHeight_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_LazyListScope_item_6;
    public static void d_LazyListScope_item_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_LazyListScope_item_6 == null) C_d_LazyListScope_item_6 = flex("androidx.compose.foundation.lazy.LazyListScope", "item$default", 6, a0, a1, a2, a3, a4, a5);
        call(C_d_LazyListScope_item_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_LazyListScope_items_7;
    public static void d_LazyListScope_items_7(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5, java.lang.Object a6) {
        if (C_d_LazyListScope_items_7 == null) C_d_LazyListScope_items_7 = flex("androidx.compose.foundation.lazy.LazyListScope", "items$default", 7, a0, a1, a2, a3, a4, a5, a6);
        call(C_d_LazyListScope_items_7, null, a0, a1, a2, a3, a4, a5, a6);
    }
    private static Method C_d_LazyListState_scrollToItem_6;
    public static java.lang.Object d_LazyListState_scrollToItem_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_LazyListState_scrollToItem_6 == null) C_d_LazyListState_scrollToItem_6 = flex("androidx.compose.foundation.lazy.LazyListState", "scrollToItem$default", 6, a0, a1, a2, a3, a4, a5);
        return (java.lang.Object) call(C_d_LazyListState_scrollToItem_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_SnapshotStateKt_mutableStateOf_4;
    public static androidx.compose.runtime.MutableState d_SnapshotStateKt_mutableStateOf_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_SnapshotStateKt_mutableStateOf_4 == null) C_d_SnapshotStateKt_mutableStateOf_4 = flex("androidx.compose.runtime.SnapshotStateKt", "mutableStateOf$default", 4, a0, a1, a2, a3);
        return (androidx.compose.runtime.MutableState) call(C_d_SnapshotStateKt_mutableStateOf_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_SemanticsModifierKt_semantics_5;
    public static androidx.compose.ui.Modifier d_SemanticsModifierKt_semantics_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_SemanticsModifierKt_semantics_5 == null) C_d_SemanticsModifierKt_semantics_5 = flex("androidx.compose.ui.semantics.SemanticsModifierKt", "semantics$default", 5, a0, a1, a2, a3, a4);
        return (androidx.compose.ui.Modifier) call(C_d_SemanticsModifierKt_semantics_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_CollectionsKt_joinToString_9;
    public static java.lang.String d_CollectionsKt_joinToString_9(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5, java.lang.Object a6, java.lang.Object a7, java.lang.Object a8) {
        if (C_d_CollectionsKt_joinToString_9 == null) C_d_CollectionsKt_joinToString_9 = flex("kotlin.collections.CollectionsKt", "joinToString$default", 9, a0, a1, a2, a3, a4, a5, a6, a7, a8);
        return (java.lang.String) call(C_d_CollectionsKt_joinToString_9, null, a0, a1, a2, a3, a4, a5, a6, a7, a8);
    }
    private static Method C_d_FilesKt_readText_4;
    public static java.lang.String d_FilesKt_readText_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_FilesKt_readText_4 == null) C_d_FilesKt_readText_4 = flex("kotlin.io.FilesKt", "readText$default", 4, a0, a1, a2, a3);
        return (java.lang.String) call(C_d_FilesKt_readText_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_FilesKt_writeText_5;
    public static void d_FilesKt_writeText_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_FilesKt_writeText_5 == null) C_d_FilesKt_writeText_5 = flex("kotlin.io.FilesKt", "writeText$default", 5, a0, a1, a2, a3, a4);
        call(C_d_FilesKt_writeText_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_Regex_find_5;
    public static kotlin.text.MatchResult d_Regex_find_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_Regex_find_5 == null) C_d_Regex_find_5 = flex("kotlin.text.Regex", "find$default", 5, a0, a1, a2, a3, a4);
        return (kotlin.text.MatchResult) call(C_d_Regex_find_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_StringsKt_contains_5;
    public static boolean d_StringsKt_contains_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_StringsKt_contains_5 == null) C_d_StringsKt_contains_5 = flex("kotlin.text.StringsKt", "contains$default", 5, a0, a1, a2, a3, a4);
        return (Boolean) call(C_d_StringsKt_contains_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_StringsKt_endsWith_5;
    public static boolean d_StringsKt_endsWith_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_StringsKt_endsWith_5 == null) C_d_StringsKt_endsWith_5 = flex("kotlin.text.StringsKt", "endsWith$default", 5, a0, a1, a2, a3, a4);
        return (Boolean) call(C_d_StringsKt_endsWith_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_StringsKt_indexOf_6;
    public static int d_StringsKt_indexOf_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_StringsKt_indexOf_6 == null) C_d_StringsKt_indexOf_6 = flex("kotlin.text.StringsKt", "indexOf$default", 6, a0, a1, a2, a3, a4, a5);
        return (Integer) call(C_d_StringsKt_indexOf_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_StringsKt_replace_6;
    public static java.lang.String d_StringsKt_replace_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_StringsKt_replace_6 == null) C_d_StringsKt_replace_6 = flex("kotlin.text.StringsKt", "replace$default", 6, a0, a1, a2, a3, a4, a5);
        return (java.lang.String) call(C_d_StringsKt_replace_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_StringsKt_split_6;
    public static java.util.List d_StringsKt_split_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_StringsKt_split_6 == null) C_d_StringsKt_split_6 = flex("kotlin.text.StringsKt", "split$default", 6, a0, a1, a2, a3, a4, a5);
        return (java.util.List) call(C_d_StringsKt_split_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_StringsKt_startsWith_5;
    public static boolean d_StringsKt_startsWith_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_StringsKt_startsWith_5 == null) C_d_StringsKt_startsWith_5 = flex("kotlin.text.StringsKt", "startsWith$default", 5, a0, a1, a2, a3, a4);
        return (Boolean) call(C_d_StringsKt_startsWith_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_StringsKt_startsWith_6;
    public static boolean d_StringsKt_startsWith_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_StringsKt_startsWith_6 == null) C_d_StringsKt_startsWith_6 = flex("kotlin.text.StringsKt", "startsWith$default", 6, a0, a1, a2, a3, a4, a5);
        return (Boolean) call(C_d_StringsKt_startsWith_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_StringsKt_substringAfterLast_5;
    public static java.lang.String d_StringsKt_substringAfterLast_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_StringsKt_substringAfterLast_5 == null) C_d_StringsKt_substringAfterLast_5 = flex("kotlin.text.StringsKt", "substringAfterLast$default", 5, a0, a1, a2, a3, a4);
        return (java.lang.String) call(C_d_StringsKt_substringAfterLast_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_StringsKt_substringBefore_5;
    public static java.lang.String d_StringsKt_substringBefore_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_StringsKt_substringBefore_5 == null) C_d_StringsKt_substringBefore_5 = flex("kotlin.text.StringsKt", "substringBefore$default", 5, a0, a1, a2, a3, a4);
        return (java.lang.String) call(C_d_StringsKt_substringBefore_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_BuildersKt_async_6;
    public static kotlinx.coroutines.Deferred d_BuildersKt_async_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_BuildersKt_async_6 == null) C_d_BuildersKt_async_6 = flex("kotlinx.coroutines.BuildersKt", "async$default", 6, a0, a1, a2, a3, a4, a5);
        return (kotlinx.coroutines.Deferred) call(C_d_BuildersKt_async_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_BuildersKt_launch_6;
    public static kotlinx.coroutines.Job d_BuildersKt_launch_6(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4, java.lang.Object a5) {
        if (C_d_BuildersKt_launch_6 == null) C_d_BuildersKt_launch_6 = flex("kotlinx.coroutines.BuildersKt", "launch$default", 6, a0, a1, a2, a3, a4, a5);
        return (kotlinx.coroutines.Job) call(C_d_BuildersKt_launch_6, null, a0, a1, a2, a3, a4, a5);
    }
    private static Method C_d_Job_DefaultImpls_cancel_4;
    public static void d_Job_DefaultImpls_cancel_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_Job_DefaultImpls_cancel_4 == null) C_d_Job_DefaultImpls_cancel_4 = flex("kotlinx.coroutines.Job$DefaultImpls", "cancel$default", 4, a0, a1, a2, a3);
        call(C_d_Job_DefaultImpls_cancel_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_SupervisorKt_SupervisorJob_3;
    public static kotlinx.coroutines.CompletableJob d_SupervisorKt_SupervisorJob_3(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2) {
        if (C_d_SupervisorKt_SupervisorJob_3 == null) C_d_SupervisorKt_SupervisorJob_3 = flex("kotlinx.coroutines.SupervisorKt", "SupervisorJob$default", 3, a0, a1, a2);
        return (kotlinx.coroutines.CompletableJob) call(C_d_SupervisorKt_SupervisorJob_3, null, a0, a1, a2);
    }
    private static Method C_d_SharedFlowKt_MutableSharedFlow_5;
    public static kotlinx.coroutines.flow.MutableSharedFlow d_SharedFlowKt_MutableSharedFlow_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_SharedFlowKt_MutableSharedFlow_5 == null) C_d_SharedFlowKt_MutableSharedFlow_5 = flex("kotlinx.coroutines.flow.SharedFlowKt", "MutableSharedFlow$default", 5, a0, a1, a2, a3, a4);
        return (kotlinx.coroutines.flow.MutableSharedFlow) call(C_d_SharedFlowKt_MutableSharedFlow_5, null, a0, a1, a2, a3, a4);
    }
    private static Method C_d_Mutex_DefaultImpls_tryLock_4;
    public static boolean d_Mutex_DefaultImpls_tryLock_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_Mutex_DefaultImpls_tryLock_4 == null) C_d_Mutex_DefaultImpls_tryLock_4 = flex("kotlinx.coroutines.sync.Mutex$DefaultImpls", "tryLock$default", 4, a0, a1, a2, a3);
        return (Boolean) call(C_d_Mutex_DefaultImpls_tryLock_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_Mutex_DefaultImpls_unlock_4;
    public static void d_Mutex_DefaultImpls_unlock_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_Mutex_DefaultImpls_unlock_4 == null) C_d_Mutex_DefaultImpls_unlock_4 = flex("kotlinx.coroutines.sync.Mutex$DefaultImpls", "unlock$default", 4, a0, a1, a2, a3);
        call(C_d_Mutex_DefaultImpls_unlock_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_MutexKt_Mutex_3;
    public static kotlinx.coroutines.sync.Mutex d_MutexKt_Mutex_3(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2) {
        if (C_d_MutexKt_Mutex_3 == null) C_d_MutexKt_Mutex_3 = flex("kotlinx.coroutines.sync.MutexKt", "Mutex$default", 3, a0, a1, a2);
        return (kotlinx.coroutines.sync.Mutex) call(C_d_MutexKt_Mutex_3, null, a0, a1, a2);
    }
    private static Method C_d_JsonKt_Json_4;
    public static kotlinx.serialization.json.Json d_JsonKt_Json_4(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3) {
        if (C_d_JsonKt_Json_4 == null) C_d_JsonKt_Json_4 = flex("kotlinx.serialization.json.JsonKt", "Json$default", 4, a0, a1, a2, a3);
        return (kotlinx.serialization.json.Json) call(C_d_JsonKt_Json_4, null, a0, a1, a2, a3);
    }
    private static Method C_d_EdgeToEdge_enable_5;
    public static void d_EdgeToEdge_enable_5(java.lang.Object a0, java.lang.Object a1, java.lang.Object a2, java.lang.Object a3, java.lang.Object a4) {
        if (C_d_EdgeToEdge_enable_5 == null) C_d_EdgeToEdge_enable_5 = flex("androidx.activity.EdgeToEdge", "enable$default", 5, a0, a1, a2, a3, a4);
        call(C_d_EdgeToEdge_enable_5, null, a0, a1, a2, a3, a4);
    }

    /* kotlin.Result.unbox-impl — jadx rendered calls as ((Result) x).getValue() */
    private static Method C_resultUnbox;
    public static Object resultUnbox(Object r) {
        try {
            if (C_resultUnbox == null) {
                C_resultUnbox = kotlin.Result.class.getDeclaredMethod("unbox-impl", Object.class);
                C_resultUnbox.setAccessible(true);
            }
            return C_resultUnbox.invoke(null, r);
        } catch (Throwable t) {
            return r;
        }
    }

    /* Synthetic-ctor bridges — kotlinc emits ACC_SYNTHETIC constructors for
       default-arg overloads, which javac cannot call directly. These forward
       via reflection with the exact original (args, mask, null) so semantics
       (which params were defaulted) are preserved. */
    private static java.lang.reflect.Constructor<?> ctorOf(String cls, Class<?>... types) {
        try {
            return Class.forName(cls).getConstructor(types);
        } catch (Exception e) {
            throw new IllegalStateException("no ctor " + cls, e);
        }
    }

    private static Object construct(java.lang.reflect.Constructor<?> c, Object... args) {
        try {
            return c.newInstance(args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static java.lang.reflect.Constructor<?> C_TextStyle26;
    public static androidx.compose.ui.text.TextStyle newTextStyle(long c0, long c1, androidx.compose.ui.text.font.FontWeight c2, androidx.compose.ui.text.font.FontStyle c3, androidx.compose.ui.text.font.FontSynthesis c4, androidx.compose.ui.text.font.FontFamily c5, String c6, long c7, androidx.compose.ui.text.style.BaselineShift c8, androidx.compose.ui.text.style.TextGeometricTransform c9, androidx.compose.ui.text.intl.LocaleList c10, long c11, androidx.compose.ui.text.style.TextDecoration c12, androidx.compose.ui.graphics.Shadow c13, androidx.compose.ui.graphics.drawscope.DrawStyle c14, int c15, int c16, long c17, androidx.compose.ui.text.style.TextIndent c18, androidx.compose.ui.text.PlatformTextStyle c19, androidx.compose.ui.text.style.LineHeightStyle c20, int c21, int c22, androidx.compose.ui.text.style.TextMotion c23, int mask) {
        if (C_TextStyle26 == null) C_TextStyle26 = ctorOf("androidx.compose.ui.text.TextStyle", long.class, long.class, androidx.compose.ui.text.font.FontWeight.class, androidx.compose.ui.text.font.FontStyle.class, androidx.compose.ui.text.font.FontSynthesis.class, androidx.compose.ui.text.font.FontFamily.class, String.class, long.class, androidx.compose.ui.text.style.BaselineShift.class, androidx.compose.ui.text.style.TextGeometricTransform.class, androidx.compose.ui.text.intl.LocaleList.class, long.class, androidx.compose.ui.text.style.TextDecoration.class, androidx.compose.ui.graphics.Shadow.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, int.class, int.class, long.class, androidx.compose.ui.text.style.TextIndent.class, androidx.compose.ui.text.PlatformTextStyle.class, androidx.compose.ui.text.style.LineHeightStyle.class, int.class, int.class, androidx.compose.ui.text.style.TextMotion.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.ui.text.TextStyle) construct(C_TextStyle26, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, mask, null);
    }

    private static java.lang.reflect.Constructor<?> C_SpanStyle18;
    public static androidx.compose.ui.text.SpanStyle newSpanStyle(long c0, long c1, androidx.compose.ui.text.font.FontWeight c2, androidx.compose.ui.text.font.FontStyle c3, androidx.compose.ui.text.font.FontSynthesis c4, androidx.compose.ui.text.font.FontFamily c5, String c6, long c7, androidx.compose.ui.text.style.BaselineShift c8, androidx.compose.ui.text.style.TextGeometricTransform c9, androidx.compose.ui.text.intl.LocaleList c10, long c11, androidx.compose.ui.text.style.TextDecoration c12, androidx.compose.ui.graphics.Shadow c13, androidx.compose.ui.text.PlatformSpanStyle c14, androidx.compose.ui.graphics.drawscope.DrawStyle c15, int mask) {
        if (C_SpanStyle18 == null) C_SpanStyle18 = ctorOf("androidx.compose.ui.text.SpanStyle", long.class, long.class, androidx.compose.ui.text.font.FontWeight.class, androidx.compose.ui.text.font.FontStyle.class, androidx.compose.ui.text.font.FontSynthesis.class, androidx.compose.ui.text.font.FontFamily.class, String.class, long.class, androidx.compose.ui.text.style.BaselineShift.class, androidx.compose.ui.text.style.TextGeometricTransform.class, androidx.compose.ui.text.intl.LocaleList.class, long.class, androidx.compose.ui.text.style.TextDecoration.class, androidx.compose.ui.graphics.Shadow.class, androidx.compose.ui.text.PlatformSpanStyle.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.ui.text.SpanStyle) construct(C_SpanStyle18, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, mask, null);
    }

    private static java.lang.reflect.Constructor<?> C_Stroke7;
    public static androidx.compose.ui.graphics.drawscope.Stroke newStroke(float w, float m, int cap, int join, androidx.compose.ui.graphics.PathEffect pe, int mask) {
        if (C_Stroke7 == null) C_Stroke7 = ctorOf("androidx.compose.ui.graphics.drawscope.Stroke", float.class, float.class, int.class, int.class, androidx.compose.ui.graphics.PathEffect.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.ui.graphics.drawscope.Stroke) construct(C_Stroke7, w, m, cap, join, pe, mask, null);
    }

    private static java.lang.reflect.Constructor<?> C_TextStyleBrush27;
    public static androidx.compose.ui.text.TextStyle newTextStyleBrush(androidx.compose.ui.graphics.Brush c0, float c1, long c2, androidx.compose.ui.text.font.FontWeight c3, androidx.compose.ui.text.font.FontStyle c4, androidx.compose.ui.text.font.FontSynthesis c5, androidx.compose.ui.text.font.FontFamily c6, String c7, long c8, androidx.compose.ui.text.style.BaselineShift c9, androidx.compose.ui.text.style.TextGeometricTransform c10, androidx.compose.ui.text.intl.LocaleList c11, long c12, androidx.compose.ui.text.style.TextDecoration c13, androidx.compose.ui.graphics.Shadow c14, androidx.compose.ui.graphics.drawscope.DrawStyle c15, int c16, int c17, long c18, androidx.compose.ui.text.style.TextIndent c19, androidx.compose.ui.text.PlatformTextStyle c20, androidx.compose.ui.text.style.LineHeightStyle c21, int c22, int c23, androidx.compose.ui.text.style.TextMotion c24, int mask) {
        if (C_TextStyleBrush27 == null) C_TextStyleBrush27 = ctorOf("androidx.compose.ui.text.TextStyle", androidx.compose.ui.graphics.Brush.class, float.class, long.class, androidx.compose.ui.text.font.FontWeight.class, androidx.compose.ui.text.font.FontStyle.class, androidx.compose.ui.text.font.FontSynthesis.class, androidx.compose.ui.text.font.FontFamily.class, String.class, long.class, androidx.compose.ui.text.style.BaselineShift.class, androidx.compose.ui.text.style.TextGeometricTransform.class, androidx.compose.ui.text.intl.LocaleList.class, long.class, androidx.compose.ui.text.style.TextDecoration.class, androidx.compose.ui.graphics.Shadow.class, androidx.compose.ui.graphics.drawscope.DrawStyle.class, int.class, int.class, long.class, androidx.compose.ui.text.style.TextIndent.class, androidx.compose.ui.text.PlatformTextStyle.class, androidx.compose.ui.text.style.LineHeightStyle.class, int.class, int.class, androidx.compose.ui.text.style.TextMotion.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.ui.text.TextStyle) construct(C_TextStyleBrush27, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, mask, null);
    }

    private static java.lang.reflect.Constructor<?> C_Typography32;
    public static androidx.compose.material3.Typography newTypography(androidx.compose.ui.text.TextStyle c0, androidx.compose.ui.text.TextStyle c1, androidx.compose.ui.text.TextStyle c2, androidx.compose.ui.text.TextStyle c3, androidx.compose.ui.text.TextStyle c4, androidx.compose.ui.text.TextStyle c5, androidx.compose.ui.text.TextStyle c6, androidx.compose.ui.text.TextStyle c7, androidx.compose.ui.text.TextStyle c8, androidx.compose.ui.text.TextStyle c9, androidx.compose.ui.text.TextStyle c10, androidx.compose.ui.text.TextStyle c11, androidx.compose.ui.text.TextStyle c12, androidx.compose.ui.text.TextStyle c13, androidx.compose.ui.text.TextStyle c14, int mask) {
        if (C_Typography32 == null) C_Typography32 = ctorOf("androidx.compose.material3.Typography", androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, androidx.compose.ui.text.TextStyle.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.material3.Typography) construct(C_Typography32, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, mask | 1073709056, null);
    }

    private static java.lang.reflect.Constructor<?> C_KeyboardOptions9;
    public static androidx.compose.foundation.text.KeyboardOptions newKeyboardOptions(int c0, Boolean c1, int c2, int c3, androidx.compose.ui.text.input.PlatformImeOptions c4, Boolean c5, androidx.compose.ui.text.intl.LocaleList c6, int mask) {
        if (C_KeyboardOptions9 == null) C_KeyboardOptions9 = ctorOf("androidx.compose.foundation.text.KeyboardOptions", int.class, Boolean.class, int.class, int.class, androidx.compose.ui.text.input.PlatformImeOptions.class, Boolean.class, androidx.compose.ui.text.intl.LocaleList.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.foundation.text.KeyboardOptions) construct(C_KeyboardOptions9, c0, c1, c2, c3, c4, c5, c6, mask, null);
    }

    private static java.lang.reflect.Constructor<?> C_Shadow5;
    public static androidx.compose.ui.graphics.Shadow newShadow(long color, long offset, float blur, int mask) {
        if (C_Shadow5 == null) C_Shadow5 = ctorOf("androidx.compose.ui.graphics.Shadow", long.class, long.class, float.class, int.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.ui.graphics.Shadow) construct(C_Shadow5, color, offset, blur, mask, null);
    }

    private static Method C_d_ArraysKt_copyInto_7;
    public static byte[] d_ArraysKt_copyInto_7(byte[] a0, byte[] a1, int a2, int a3, int a4, int a5, Object a6) {
        if (C_d_ArraysKt_copyInto_7 == null) C_d_ArraysKt_copyInto_7 = flex("kotlin.collections.ArraysKt", "copyInto$default", 7, a0, a1, a2, a3, a4, a5, a6);
        return (byte[]) call(C_d_ArraysKt_copyInto_7, null, a0, a1, a2, a3, a4, a5, a6);
    }

    private static java.lang.reflect.Constructor<?> C_SolidColor2;
    public static androidx.compose.ui.graphics.Brush solidColorBrush(long color) {
        if (C_SolidColor2 == null) C_SolidColor2 = ctorOf("androidx.compose.ui.graphics.SolidColor", long.class, kotlin.jvm.internal.DefaultConstructorMarker.class);
        return (androidx.compose.ui.graphics.Brush) construct(C_SolidColor2, color, null);
    }
}