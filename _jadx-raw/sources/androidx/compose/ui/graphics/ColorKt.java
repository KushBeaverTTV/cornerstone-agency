package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: Color.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a9\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a\u0017\u0010\u000f\u001a\u00020\u00072\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010\u001a\u001a5\u0010\u000f\u001a\u00020\u00072\b\b\u0001\u0010\u0010\u001a\u00020\u00192\b\b\u0001\u0010\u0012\u001a\u00020\u00192\b\b\u0001\u0010\u0013\u001a\u00020\u00192\b\b\u0003\u0010\u0014\u001a\u00020\u0019H\u0007¢\u0006\u0002\u0010\u001b\u001a\u0015\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001d\u001a9\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\u0010\u0017\u001a1\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0011H\u0082\b\u001a,\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\b\b\u0001\u0010(\u001a\u00020\u0011H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u001e\u0010+\u001a\u00020\u0007*\u00020\u00072\u0006\u0010,\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u0016\u0010/\u001a\u000200*\u00020\u0007H\u0003ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a\u0016\u00103\u001a\u00020\u0011*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a%\u00106\u001a\u00020\u0007*\u00020\u00072\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000708H\u0086\bø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a\u0016\u0010;\u001a\u00020\u0019*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=\"\u0018\u0010\u0000\u001a\u00020\u00018\u0000X\u0081T¢\u0006\n\n\u0002\u0010\u0004\u0012\u0004\b\u0002\u0010\u0003\"\u001f\u0010\u0005\u001a\u00020\u0006*\u00020\u00078Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u001f\u0010\f\u001a\u00020\u0006*\u00020\u00078Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006>"}, d2 = {"UnspecifiedColor", "Lkotlin/ULong;", "getUnspecifiedColor$annotations", "()V", "J", "isSpecified", "", "Landroidx/compose/ui/graphics/Color;", "isSpecified-8_81llA$annotations", "(J)V", "isSpecified-8_81llA", "(J)Z", "isUnspecified", "isUnspecified-8_81llA$annotations", "isUnspecified-8_81llA", "Color", "red", "", "green", "blue", "alpha", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "color", "", "(I)J", "(IIII)J", "", "(J)J", "UncheckedColor", "compositeComponent", "fgC", "bgC", "fgA", "bgA", "a", "lerp", "start", "stop", "fraction", "lerp-jxsXWHM", "(JJF)J", "compositeOver", "background", "compositeOver--OWjLjI", "(JJ)J", "getComponents", "", "getComponents-8_81llA", "(J)[F", "luminance", "luminance-8_81llA", "(J)F", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-DxMtmZc", "(JLkotlin/jvm/functions/Function0;)J", "toArgb", "toArgb-8_81llA", "(J)I", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorKt {
    public static final long UnspecifiedColor = 16;

    public static /* synthetic */ void getUnspecifiedColor$annotations() {
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m4582isSpecified8_81llA$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m4584isUnspecified8_81llA$annotations(long j) {
    }

    public static /* synthetic */ long Color$default(float f, float f2, float f3, float f4, ColorSpace colorSpace, int i, Object obj) {
        if ((i & 8) != 0) {
            f4 = 1.0f;
        }
        if ((i & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return Color(f, f2, f3, f4, colorSpace);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:104:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:106:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:107:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:109:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:111:0x01de  */
    /* JADX WARN: Code duplicated, block: B:112:0x01e1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:113:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:115:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:117:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:119:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:121:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:125:0x021b  */
    /* JADX WARN: Code duplicated, block: B:128:0x0222  */
    /* JADX WARN: Code duplicated, block: B:72:0x0145  */
    /* JADX WARN: Code duplicated, block: B:75:0x014c  */
    /* JADX WARN: Code duplicated, block: B:78:0x015f  */
    /* JADX WARN: Code duplicated, block: B:80:0x0163  */
    /* JADX WARN: Code duplicated, block: B:81:0x0166  */
    /* JADX WARN: Code duplicated, block: B:83:0x016b  */
    /* JADX WARN: Code duplicated, block: B:85:0x0171  */
    /* JADX WARN: Code duplicated, block: B:86:0x0174 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:87:0x0176 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x0178  */
    /* JADX WARN: Code duplicated, block: B:90:0x0182  */
    /* JADX WARN: Code duplicated, block: B:92:0x0187  */
    /* JADX WARN: Code duplicated, block: B:94:0x018e  */
    /* JADX WARN: Code duplicated, block: B:98:0x01b2  */
    public static final long Color(float red, float green, float blue, float alpha, ColorSpace colorSpace) {
        short s;
        float minimumValue$iv;
        float maximumValue$iv;
        float $this$fastCoerceAtLeast$iv$iv;
        int s$iv;
        int e$iv;
        int m$iv;
        int outE$iv;
        int outM$iv;
        int e$iv2;
        short s2;
        int m$iv2;
        float minimumValue$iv2;
        float maximumValue$iv2;
        float $this$fastCoerceAtLeast$iv$iv2;
        int s$iv2;
        int e$iv3;
        int m$iv3;
        int outE$iv2;
        int outM$iv2;
        int e$iv4;
        short s3;
        int m$iv4;
        float $this$fastCoerceAtLeast$iv$iv3;
        int i;
        int i2;
        if (colorSpace.getIsSrgb()) {
            float $this$fastCoerceAtLeast$iv$iv4 = alpha;
            if ($this$fastCoerceAtLeast$iv$iv4 < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv4 = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv4 > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv4 = 1.0f;
            }
            int i3 = ((int) (($this$fastCoerceAtLeast$iv$iv4 * 255.0f) + 0.5f)) << 24;
            float $this$fastCoerceAtLeast$iv$iv5 = red;
            if ($this$fastCoerceAtLeast$iv$iv5 < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv5 = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv5 > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv5 = 1.0f;
            }
            int i4 = (((int) (($this$fastCoerceAtLeast$iv$iv5 * 255.0f) + 0.5f)) << 16) | i3;
            float $this$fastCoerceAtLeast$iv$iv6 = green;
            if ($this$fastCoerceAtLeast$iv$iv6 < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv6 = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv6 > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv6 = 1.0f;
            }
            int i5 = i4 | (((int) (($this$fastCoerceAtLeast$iv$iv6 * 255.0f) + 0.5f)) << 8);
            float $this$fastCoerceAtLeast$iv$iv7 = blue;
            if ($this$fastCoerceAtLeast$iv$iv7 < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv7 = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv7 > 1.0f) {
                $this$fastCoerceAtLeast$iv$iv7 = 1.0f;
            }
            int argb = ((int) (($this$fastCoerceAtLeast$iv$iv7 * 255.0f) + 0.5f)) | i5;
            return Color.m4530constructorimpl(ULong.m7562constructorimpl(ULong.m7562constructorimpl(argb) << 32));
        }
        int argb2 = colorSpace.getComponentCount();
        boolean value$iv = argb2 == 3;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Color only works with ColorSpaces with 3 components");
        }
        int id = colorSpace.getId();
        boolean value$iv2 = id != -1;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("Unknown color space, please use a color space in ColorSpaces");
        }
        float minimumValue$iv3 = colorSpace.getMinValue(0);
        float maximumValue$iv3 = colorSpace.getMaxValue(0);
        float $this$fastCoerceAtLeast$iv$iv8 = red;
        if ($this$fastCoerceAtLeast$iv$iv8 < minimumValue$iv3) {
            $this$fastCoerceAtLeast$iv$iv8 = minimumValue$iv3;
        }
        if ($this$fastCoerceAtLeast$iv$iv8 > maximumValue$iv3) {
            $this$fastCoerceAtLeast$iv$iv8 = maximumValue$iv3;
        }
        int bits$iv = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv8);
        int s$iv3 = bits$iv >>> 31;
        int e$iv5 = (bits$iv >>> 23) & 255;
        int m$iv5 = bits$iv & 8388607;
        int outE$iv3 = 0;
        int outM$iv3 = 0;
        if (e$iv5 == 255) {
            outE$iv3 = 31;
            outM$iv3 = m$iv5 != 0 ? 512 : 0;
        } else {
            int e$iv6 = (e$iv5 - 127) + 15;
            if (e$iv6 >= 31) {
                outE$iv3 = 49;
            } else {
                if (e$iv6 > 0) {
                    outE$iv3 = e$iv6;
                    outM$iv3 = m$iv5 >> 13;
                    if ((m$iv5 & 4096) != 0) {
                        int out$iv = (outE$iv3 << 10) | outM$iv3;
                        s = (short) ((out$iv + 1) | (s$iv3 << 15));
                    }
                    short r = s;
                    minimumValue$iv = colorSpace.getMinValue(1);
                    maximumValue$iv = colorSpace.getMaxValue(1);
                    $this$fastCoerceAtLeast$iv$iv = green;
                    if ($this$fastCoerceAtLeast$iv$iv < minimumValue$iv) {
                        $this$fastCoerceAtLeast$iv$iv = minimumValue$iv;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv) {
                        $this$fastCoerceAtLeast$iv$iv = maximumValue$iv;
                    }
                    int bits$iv2 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv);
                    s$iv = bits$iv2 >>> 31;
                    e$iv = (bits$iv2 >>> 23) & 255;
                    m$iv = bits$iv2 & 8388607;
                    outE$iv = 0;
                    outM$iv = 0;
                    if (e$iv == 255) {
                        outE$iv = 31;
                        if (m$iv != 0) {
                            i2 = 512;
                        } else {
                            i2 = 0;
                        }
                        outM$iv = i2;
                    } else {
                        e$iv2 = (e$iv - 127) + 15;
                        if (e$iv2 >= 31) {
                            outE$iv = 49;
                        } else {
                            if (e$iv2 <= 0) {
                                outE$iv = e$iv2;
                                outM$iv = m$iv >> 13;
                                if ((m$iv & 4096) != 0) {
                                    int out$iv2 = (outE$iv << 10) | outM$iv;
                                    s2 = (short) ((out$iv2 + 1) | (s$iv << 15));
                                }
                                short g = s2;
                                minimumValue$iv2 = colorSpace.getMinValue(2);
                                maximumValue$iv2 = colorSpace.getMaxValue(2);
                                $this$fastCoerceAtLeast$iv$iv2 = blue;
                                if ($this$fastCoerceAtLeast$iv$iv2 < minimumValue$iv2) {
                                    $this$fastCoerceAtLeast$iv$iv2 = minimumValue$iv2;
                                }
                                if ($this$fastCoerceAtLeast$iv$iv2 > maximumValue$iv2) {
                                    $this$fastCoerceAtLeast$iv$iv2 = maximumValue$iv2;
                                }
                                int bits$iv3 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv2);
                                s$iv2 = bits$iv3 >>> 31;
                                e$iv3 = (bits$iv3 >>> 23) & 255;
                                m$iv3 = bits$iv3 & 8388607;
                                outE$iv2 = 0;
                                outM$iv2 = 0;
                                if (e$iv3 == 255) {
                                    outE$iv2 = 31;
                                    if (m$iv3 != 0) {
                                        i = 512;
                                    } else {
                                        i = 0;
                                    }
                                    outM$iv2 = i;
                                } else {
                                    e$iv4 = (e$iv3 - 127) + 15;
                                    if (e$iv4 >= 31) {
                                        outE$iv2 = 49;
                                    } else {
                                        if (e$iv4 <= 0) {
                                            outE$iv2 = e$iv4;
                                            outM$iv2 = m$iv3 >> 13;
                                            if ((m$iv3 & 4096) != 0) {
                                                int out$iv3 = (outE$iv2 << 10) | outM$iv2;
                                                s3 = (short) ((s$iv2 << 15) | (out$iv3 + 1));
                                            }
                                            short b = s3;
                                            $this$fastCoerceAtLeast$iv$iv3 = alpha;
                                            if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                                                $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                                            }
                                            if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                                                $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                                            }
                                            int a = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                                            return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b)) << 16) | ((((long) a) & 1023) << 6) | (((long) id) & 63)));
                                        }
                                        if (e$iv4 >= -10) {
                                            m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                            if ((m$iv4 & 4096) != 0) {
                                                m$iv4 += 8192;
                                            }
                                            outM$iv2 = m$iv4 >> 13;
                                        }
                                    }
                                }
                                int out$iv4 = s$iv2 << 15;
                                s3 = (short) (out$iv4 | (outE$iv2 << 10) | outM$iv2);
                                short b2 = s3;
                                $this$fastCoerceAtLeast$iv$iv3 = alpha;
                                if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                                    $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                                }
                                if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                                    $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                                }
                                int a2 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                                return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b2)) << 16) | ((((long) a2) & 1023) << 6) | (((long) id) & 63)));
                            }
                            if (e$iv2 >= -10) {
                                m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                                if ((m$iv2 & 4096) != 0) {
                                    m$iv2 += 8192;
                                }
                                outM$iv = m$iv2 >> 13;
                            }
                        }
                    }
                    int out$iv5 = s$iv << 15;
                    s2 = (short) (out$iv5 | (outE$iv << 10) | outM$iv);
                    short g2 = s2;
                    minimumValue$iv2 = colorSpace.getMinValue(2);
                    maximumValue$iv2 = colorSpace.getMaxValue(2);
                    $this$fastCoerceAtLeast$iv$iv2 = blue;
                    if ($this$fastCoerceAtLeast$iv$iv2 < minimumValue$iv2) {
                        $this$fastCoerceAtLeast$iv$iv2 = minimumValue$iv2;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv2 > maximumValue$iv2) {
                        $this$fastCoerceAtLeast$iv$iv2 = maximumValue$iv2;
                    }
                    int bits$iv4 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv2);
                    s$iv2 = bits$iv4 >>> 31;
                    e$iv3 = (bits$iv4 >>> 23) & 255;
                    m$iv3 = bits$iv4 & 8388607;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        if (m$iv3 != 0) {
                            i = 512;
                        } else {
                            i = 0;
                        }
                        outM$iv2 = i;
                    } else {
                        e$iv4 = (e$iv3 - 127) + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv6 = (outE$iv2 << 10) | outM$iv2;
                                    s3 = (short) ((s$iv2 << 15) | (out$iv6 + 1));
                                }
                                short b3 = s3;
                                $this$fastCoerceAtLeast$iv$iv3 = alpha;
                                if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                                    $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                                }
                                if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                                    $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                                }
                                int a3 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                                return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b3)) << 16) | ((((long) a3) & 1023) << 6) | (((long) id) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv7 = s$iv2 << 15;
                    s3 = (short) (out$iv7 | (outE$iv2 << 10) | outM$iv2);
                    short b4 = s3;
                    $this$fastCoerceAtLeast$iv$iv3 = alpha;
                    if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                        $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                        $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                    }
                    int a4 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                    return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b4)) << 16) | ((((long) a4) & 1023) << 6) | (((long) id) & 63)));
                }
                if (e$iv6 >= -10) {
                    int m$iv6 = (m$iv5 | 8388608) >> (1 - e$iv6);
                    if ((m$iv6 & 4096) != 0) {
                        m$iv6 += 8192;
                    }
                    outM$iv3 = m$iv6 >> 13;
                }
            }
        }
        int out$iv8 = s$iv3 << 15;
        s = (short) (out$iv8 | (outE$iv3 << 10) | outM$iv3);
        short r2 = s;
        minimumValue$iv = colorSpace.getMinValue(1);
        maximumValue$iv = colorSpace.getMaxValue(1);
        $this$fastCoerceAtLeast$iv$iv = green;
        if ($this$fastCoerceAtLeast$iv$iv < minimumValue$iv) {
            $this$fastCoerceAtLeast$iv$iv = minimumValue$iv;
        }
        if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv) {
            $this$fastCoerceAtLeast$iv$iv = maximumValue$iv;
        }
        int bits$iv5 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv);
        s$iv = bits$iv5 >>> 31;
        e$iv = (bits$iv5 >>> 23) & 255;
        m$iv = bits$iv5 & 8388607;
        outE$iv = 0;
        outM$iv = 0;
        if (e$iv == 255) {
            outE$iv = 31;
            if (m$iv != 0) {
                i2 = 512;
            } else {
                i2 = 0;
            }
            outM$iv = i2;
        } else {
            e$iv2 = (e$iv - 127) + 15;
            if (e$iv2 >= 31) {
                outE$iv = 49;
            } else {
                if (e$iv2 <= 0) {
                    outE$iv = e$iv2;
                    outM$iv = m$iv >> 13;
                    if ((m$iv & 4096) != 0) {
                        int out$iv9 = (outE$iv << 10) | outM$iv;
                        s2 = (short) ((out$iv9 + 1) | (s$iv << 15));
                    }
                    short g3 = s2;
                    minimumValue$iv2 = colorSpace.getMinValue(2);
                    maximumValue$iv2 = colorSpace.getMaxValue(2);
                    $this$fastCoerceAtLeast$iv$iv2 = blue;
                    if ($this$fastCoerceAtLeast$iv$iv2 < minimumValue$iv2) {
                        $this$fastCoerceAtLeast$iv$iv2 = minimumValue$iv2;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv2 > maximumValue$iv2) {
                        $this$fastCoerceAtLeast$iv$iv2 = maximumValue$iv2;
                    }
                    int bits$iv6 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv2);
                    s$iv2 = bits$iv6 >>> 31;
                    e$iv3 = (bits$iv6 >>> 23) & 255;
                    m$iv3 = bits$iv6 & 8388607;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        if (m$iv3 != 0) {
                            i = 512;
                        } else {
                            i = 0;
                        }
                        outM$iv2 = i;
                    } else {
                        e$iv4 = (e$iv3 - 127) + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv10 = (outE$iv2 << 10) | outM$iv2;
                                    s3 = (short) ((s$iv2 << 15) | (out$iv10 + 1));
                                }
                                short b5 = s3;
                                $this$fastCoerceAtLeast$iv$iv3 = alpha;
                                if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                                    $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                                }
                                if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                                    $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                                }
                                int a5 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                                return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g3) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b5)) << 16) | ((((long) a5) & 1023) << 6) | (((long) id) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv11 = s$iv2 << 15;
                    s3 = (short) (out$iv11 | (outE$iv2 << 10) | outM$iv2);
                    short b6 = s3;
                    $this$fastCoerceAtLeast$iv$iv3 = alpha;
                    if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                        $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                        $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                    }
                    int a6 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                    return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g3) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b6)) << 16) | ((((long) a6) & 1023) << 6) | (((long) id) & 63)));
                }
                if (e$iv2 >= -10) {
                    m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                    if ((m$iv2 & 4096) != 0) {
                        m$iv2 += 8192;
                    }
                    outM$iv = m$iv2 >> 13;
                }
            }
        }
        int out$iv12 = s$iv << 15;
        s2 = (short) (out$iv12 | (outE$iv << 10) | outM$iv);
        short g4 = s2;
        minimumValue$iv2 = colorSpace.getMinValue(2);
        maximumValue$iv2 = colorSpace.getMaxValue(2);
        $this$fastCoerceAtLeast$iv$iv2 = blue;
        if ($this$fastCoerceAtLeast$iv$iv2 < minimumValue$iv2) {
            $this$fastCoerceAtLeast$iv$iv2 = minimumValue$iv2;
        }
        if ($this$fastCoerceAtLeast$iv$iv2 > maximumValue$iv2) {
            $this$fastCoerceAtLeast$iv$iv2 = maximumValue$iv2;
        }
        int bits$iv7 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv2);
        s$iv2 = bits$iv7 >>> 31;
        e$iv3 = (bits$iv7 >>> 23) & 255;
        m$iv3 = bits$iv7 & 8388607;
        outE$iv2 = 0;
        outM$iv2 = 0;
        if (e$iv3 == 255) {
            outE$iv2 = 31;
            if (m$iv3 != 0) {
                i = 512;
            } else {
                i = 0;
            }
            outM$iv2 = i;
        } else {
            e$iv4 = (e$iv3 - 127) + 15;
            if (e$iv4 >= 31) {
                outE$iv2 = 49;
            } else {
                if (e$iv4 <= 0) {
                    outE$iv2 = e$iv4;
                    outM$iv2 = m$iv3 >> 13;
                    if ((m$iv3 & 4096) != 0) {
                        int out$iv13 = (outE$iv2 << 10) | outM$iv2;
                        s3 = (short) ((s$iv2 << 15) | (out$iv13 + 1));
                    }
                    short b7 = s3;
                    $this$fastCoerceAtLeast$iv$iv3 = alpha;
                    if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
                        $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
                        $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
                    }
                    int a7 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
                    return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g4) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b7)) << 16) | ((((long) a7) & 1023) << 6) | (((long) id) & 63)));
                }
                if (e$iv4 >= -10) {
                    m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                    if ((m$iv4 & 4096) != 0) {
                        m$iv4 += 8192;
                    }
                    outM$iv2 = m$iv4 >> 13;
                }
            }
        }
        int out$iv14 = s$iv2 << 15;
        s3 = (short) (out$iv14 | (outE$iv2 << 10) | outM$iv2);
        short b8 = s3;
        $this$fastCoerceAtLeast$iv$iv3 = alpha;
        if ($this$fastCoerceAtLeast$iv$iv3 < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv3 > 1.0f) {
            $this$fastCoerceAtLeast$iv$iv3 = 1.0f;
        }
        int a8 = (int) (($this$fastCoerceAtLeast$iv$iv3 * 1023.0f) + 0.5f);
        return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) r2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g4) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) b8)) << 16) | ((((long) a8) & 1023) << 6) | (((long) id) & 63)));
    }

    public static /* synthetic */ long UncheckedColor$default(float f, float f2, float f3, float f4, ColorSpace colorSpace, int i, Object obj) {
        if ((i & 8) != 0) {
            f4 = 1.0f;
        }
        if ((i & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return UncheckedColor(f, f2, f3, f4, colorSpace);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:30:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:31:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:35:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:36:0x00bd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x00bf A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:38:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:40:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:44:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:48:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:50:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:51:0x0101  */
    /* JADX WARN: Code duplicated, block: B:53:0x0105  */
    /* JADX WARN: Code duplicated, block: B:55:0x010b  */
    /* JADX WARN: Code duplicated, block: B:56:0x010e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:57:0x0110 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x0112  */
    /* JADX WARN: Code duplicated, block: B:60:0x011c  */
    /* JADX WARN: Code duplicated, block: B:62:0x0121  */
    /* JADX WARN: Code duplicated, block: B:64:0x0128  */
    public static final long UncheckedColor(float red, float green, float blue, float alpha, ColorSpace colorSpace) {
        int i;
        int s$iv;
        int e$iv;
        int m$iv;
        int outE$iv;
        int outM$iv;
        int e$iv2;
        int i2;
        int m$iv2;
        int s$iv2;
        int e$iv3;
        int m$iv3;
        int outE$iv2;
        int outM$iv2;
        int e$iv4;
        int i3;
        int m$iv4;
        int i4;
        int i5;
        if (colorSpace.getIsSrgb()) {
            int argb = ((int) ((255.0f * blue) + 0.5f)) | (((int) ((red * 255.0f) + 0.5f)) << 16) | (((int) ((alpha * 255.0f) + 0.5f)) << 24) | (((int) ((green * 255.0f) + 0.5f)) << 8);
            return Color.m4530constructorimpl(ULong.m7562constructorimpl(ULong.m7562constructorimpl(argb) << 32));
        }
        int bits$iv = Float.floatToRawIntBits(red);
        int s$iv3 = bits$iv >>> 31;
        int e$iv5 = (bits$iv >>> 23) & 255;
        int m$iv5 = bits$iv & 8388607;
        int outE$iv3 = 0;
        int outM$iv3 = 0;
        if (e$iv5 == 255) {
            outE$iv3 = 31;
            outM$iv3 = m$iv5 != 0 ? 512 : 0;
        } else {
            int e$iv6 = (e$iv5 - 127) + 15;
            if (e$iv6 >= 31) {
                outE$iv3 = 49;
            } else {
                if (e$iv6 > 0) {
                    outE$iv3 = e$iv6;
                    outM$iv3 = m$iv5 >> 13;
                    if ((m$iv5 & 4096) != 0) {
                        int out$iv = (outE$iv3 << 10) | outM$iv3;
                        i = (short) ((out$iv + 1) | (s$iv3 << 15));
                    }
                    int $i$f$floatToHalf = i;
                    int bits$iv2 = Float.floatToRawIntBits(green);
                    s$iv = bits$iv2 >>> 31;
                    e$iv = (bits$iv2 >>> 23) & 255;
                    m$iv = bits$iv2 & 8388607;
                    outE$iv = 0;
                    outM$iv = 0;
                    if (e$iv == 255) {
                        outE$iv = 31;
                        if (m$iv != 0) {
                            i5 = 512;
                        } else {
                            i5 = 0;
                        }
                        outM$iv = i5;
                    } else {
                        e$iv2 = (e$iv - 127) + 15;
                        if (e$iv2 >= 31) {
                            outE$iv = 49;
                        } else {
                            if (e$iv2 <= 0) {
                                outE$iv = e$iv2;
                                outM$iv = m$iv >> 13;
                                if ((m$iv & 4096) != 0) {
                                    int out$iv2 = (outE$iv << 10) | outM$iv;
                                    i2 = (short) ((out$iv2 + 1) | (s$iv << 15));
                                }
                                int $i$f$floatToHalf2 = i2;
                                int bits$iv3 = Float.floatToRawIntBits(blue);
                                s$iv2 = bits$iv3 >>> 31;
                                e$iv3 = (bits$iv3 >>> 23) & 255;
                                m$iv3 = bits$iv3 & 8388607;
                                outE$iv2 = 0;
                                outM$iv2 = 0;
                                if (e$iv3 == 255) {
                                    outE$iv2 = 31;
                                    if (m$iv3 != 0) {
                                        i4 = 512;
                                    } else {
                                        i4 = 0;
                                    }
                                    outM$iv2 = i4;
                                } else {
                                    e$iv4 = (e$iv3 - 127) + 15;
                                    if (e$iv4 >= 31) {
                                        outE$iv2 = 49;
                                    } else {
                                        if (e$iv4 <= 0) {
                                            outE$iv2 = e$iv4;
                                            outM$iv2 = m$iv3 >> 13;
                                            if ((m$iv3 & 4096) != 0) {
                                                int out$iv3 = (outE$iv2 << 10) | outM$iv2;
                                                i3 = (short) ((s$iv2 << 15) | (out$iv3 + 1));
                                            }
                                            int $i$f$floatToHalf3 = i3;
                                            int a = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                                            int id = colorSpace.getId();
                                            return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf3)) << 16) | ((((long) a) & 1023) << 6) | (((long) id) & 63)));
                                        }
                                        if (e$iv4 >= -10) {
                                            m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                            if ((m$iv4 & 4096) != 0) {
                                                m$iv4 += 8192;
                                            }
                                            outM$iv2 = m$iv4 >> 13;
                                        }
                                    }
                                }
                                int out$iv4 = s$iv2 << 15;
                                i3 = (short) (out$iv4 | (outE$iv2 << 10) | outM$iv2);
                                int $i$f$floatToHalf4 = i3;
                                int a2 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                                int id2 = colorSpace.getId();
                                return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf4)) << 16) | ((((long) a2) & 1023) << 6) | (((long) id2) & 63)));
                            }
                            if (e$iv2 >= -10) {
                                m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                                if ((m$iv2 & 4096) != 0) {
                                    m$iv2 += 8192;
                                }
                                outM$iv = m$iv2 >> 13;
                            }
                        }
                    }
                    i2 = (short) ((s$iv << 15) | (outE$iv << 10) | outM$iv);
                    int $i$f$floatToHalf5 = i2;
                    int bits$iv4 = Float.floatToRawIntBits(blue);
                    s$iv2 = bits$iv4 >>> 31;
                    e$iv3 = (bits$iv4 >>> 23) & 255;
                    m$iv3 = bits$iv4 & 8388607;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        if (m$iv3 != 0) {
                            i4 = 512;
                        } else {
                            i4 = 0;
                        }
                        outM$iv2 = i4;
                    } else {
                        e$iv4 = (e$iv3 - 127) + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv5 = (outE$iv2 << 10) | outM$iv2;
                                    i3 = (short) ((s$iv2 << 15) | (out$iv5 + 1));
                                }
                                int $i$f$floatToHalf6 = i3;
                                int a3 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                                int id3 = colorSpace.getId();
                                return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf5) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf6)) << 16) | ((((long) a3) & 1023) << 6) | (((long) id3) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv6 = s$iv2 << 15;
                    i3 = (short) (out$iv6 | (outE$iv2 << 10) | outM$iv2);
                    int $i$f$floatToHalf7 = i3;
                    int a4 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                    int id4 = colorSpace.getId();
                    return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf5) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf7)) << 16) | ((((long) a4) & 1023) << 6) | (((long) id4) & 63)));
                }
                if (e$iv6 >= -10) {
                    int m$iv6 = (m$iv5 | 8388608) >> (1 - e$iv6);
                    if ((m$iv6 & 4096) != 0) {
                        m$iv6 += 8192;
                    }
                    outM$iv3 = m$iv6 >> 13;
                }
            }
        }
        i = (short) ((s$iv3 << 15) | (outE$iv3 << 10) | outM$iv3);
        int $i$f$floatToHalf8 = i;
        int bits$iv5 = Float.floatToRawIntBits(green);
        s$iv = bits$iv5 >>> 31;
        e$iv = (bits$iv5 >>> 23) & 255;
        m$iv = bits$iv5 & 8388607;
        outE$iv = 0;
        outM$iv = 0;
        if (e$iv == 255) {
            outE$iv = 31;
            if (m$iv != 0) {
                i5 = 512;
            } else {
                i5 = 0;
            }
            outM$iv = i5;
        } else {
            e$iv2 = (e$iv - 127) + 15;
            if (e$iv2 >= 31) {
                outE$iv = 49;
            } else {
                if (e$iv2 <= 0) {
                    outE$iv = e$iv2;
                    outM$iv = m$iv >> 13;
                    if ((m$iv & 4096) != 0) {
                        int out$iv7 = (outE$iv << 10) | outM$iv;
                        i2 = (short) ((out$iv7 + 1) | (s$iv << 15));
                    }
                    int $i$f$floatToHalf9 = i2;
                    int bits$iv6 = Float.floatToRawIntBits(blue);
                    s$iv2 = bits$iv6 >>> 31;
                    e$iv3 = (bits$iv6 >>> 23) & 255;
                    m$iv3 = bits$iv6 & 8388607;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        if (m$iv3 != 0) {
                            i4 = 512;
                        } else {
                            i4 = 0;
                        }
                        outM$iv2 = i4;
                    } else {
                        e$iv4 = (e$iv3 - 127) + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv8 = (outE$iv2 << 10) | outM$iv2;
                                    i3 = (short) ((s$iv2 << 15) | (out$iv8 + 1));
                                }
                                int $i$f$floatToHalf10 = i3;
                                int a5 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                                int id5 = colorSpace.getId();
                                return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf8) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf9) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf10)) << 16) | ((((long) a5) & 1023) << 6) | (((long) id5) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv9 = s$iv2 << 15;
                    i3 = (short) (out$iv9 | (outE$iv2 << 10) | outM$iv2);
                    int $i$f$floatToHalf11 = i3;
                    int a6 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                    int id6 = colorSpace.getId();
                    return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf8) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf9) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf11)) << 16) | ((((long) a6) & 1023) << 6) | (((long) id6) & 63)));
                }
                if (e$iv2 >= -10) {
                    m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                    if ((m$iv2 & 4096) != 0) {
                        m$iv2 += 8192;
                    }
                    outM$iv = m$iv2 >> 13;
                }
            }
        }
        i2 = (short) ((s$iv << 15) | (outE$iv << 10) | outM$iv);
        int $i$f$floatToHalf12 = i2;
        int bits$iv7 = Float.floatToRawIntBits(blue);
        s$iv2 = bits$iv7 >>> 31;
        e$iv3 = (bits$iv7 >>> 23) & 255;
        m$iv3 = bits$iv7 & 8388607;
        outE$iv2 = 0;
        outM$iv2 = 0;
        if (e$iv3 == 255) {
            outE$iv2 = 31;
            if (m$iv3 != 0) {
                i4 = 512;
            } else {
                i4 = 0;
            }
            outM$iv2 = i4;
        } else {
            e$iv4 = (e$iv3 - 127) + 15;
            if (e$iv4 >= 31) {
                outE$iv2 = 49;
            } else {
                if (e$iv4 <= 0) {
                    outE$iv2 = e$iv4;
                    outM$iv2 = m$iv3 >> 13;
                    if ((m$iv3 & 4096) != 0) {
                        int out$iv10 = (outE$iv2 << 10) | outM$iv2;
                        i3 = (short) ((s$iv2 << 15) | (out$iv10 + 1));
                    }
                    int $i$f$floatToHalf13 = i3;
                    int a7 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
                    int id7 = colorSpace.getId();
                    return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf8) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf12) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf13)) << 16) | ((((long) a7) & 1023) << 6) | (((long) id7) & 63)));
                }
                if (e$iv4 >= -10) {
                    m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                    if ((m$iv4 & 4096) != 0) {
                        m$iv4 += 8192;
                    }
                    outM$iv2 = m$iv4 >> 13;
                }
            }
        }
        int out$iv11 = s$iv2 << 15;
        i3 = (short) (out$iv11 | (outE$iv2 << 10) | outM$iv2);
        int $i$f$floatToHalf14 = i3;
        int a8 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + 0.5f);
        int id8 = colorSpace.getId();
        return Color.m4530constructorimpl(ULong.m7562constructorimpl(((((long) $i$f$floatToHalf8) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) $i$f$floatToHalf12) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((WebSocketProtocol.PAYLOAD_SHORT_MAX & ((long) $i$f$floatToHalf14)) << 16) | ((((long) a8) & 1023) << 6) | (((long) id8) & 63)));
    }

    public static final long Color(int color) {
        return Color.m4530constructorimpl(ULong.m7562constructorimpl(ULong.m7562constructorimpl(color) << 32));
    }

    public static final long Color(long color) {
        return Color.m4530constructorimpl(ULong.m7562constructorimpl(color << 32));
    }

    public static /* synthetic */ long Color$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 255;
        }
        return Color(i, i2, i3, i4);
    }

    public static final long Color(int red, int green, int blue, int alpha) {
        int color = ((alpha & 255) << 24) | ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
        return Color(color);
    }

    /* JADX INFO: renamed from: lerp-jxsXWHM, reason: not valid java name */
    public static final long m4585lerpjxsXWHM(long start, long stop, float fraction) {
        ColorSpace colorSpace = ColorSpaces.INSTANCE.getOklab();
        long startColor = Color.m4531convertvNxB06k(start, colorSpace);
        long endColor = Color.m4531convertvNxB06k(stop, colorSpace);
        float startAlpha = Color.m4536getAlphaimpl(startColor);
        float startL = Color.m4540getRedimpl(startColor);
        float startA = Color.m4539getGreenimpl(startColor);
        float startB = Color.m4537getBlueimpl(startColor);
        float endAlpha = Color.m4536getAlphaimpl(endColor);
        float endL = Color.m4540getRedimpl(endColor);
        float endA = Color.m4539getGreenimpl(endColor);
        float endB = Color.m4537getBlueimpl(endColor);
        float $this$fastCoerceAtLeast$iv$iv = fraction;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            $this$fastCoerceAtLeast$iv$iv = 1.0f;
        }
        float minimumValue$iv = $this$fastCoerceAtLeast$iv$iv;
        long interpolated = UncheckedColor(MathHelpersKt.lerp(startL, endL, minimumValue$iv), MathHelpersKt.lerp(startA, endA, minimumValue$iv), MathHelpersKt.lerp(startB, endB, minimumValue$iv), MathHelpersKt.lerp(startAlpha, endAlpha, minimumValue$iv), colorSpace);
        return Color.m4531convertvNxB06k(interpolated, Color.m4538getColorSpaceimpl(stop));
    }

    /* JADX INFO: renamed from: compositeOver--OWjLjI, reason: not valid java name */
    public static final long m4579compositeOverOWjLjI(long $this$compositeOver_u2d_u2dOWjLjI, long background) {
        float f;
        float f2;
        long fg = Color.m4531convertvNxB06k($this$compositeOver_u2d_u2dOWjLjI, Color.m4538getColorSpaceimpl(background));
        float bgA = Color.m4536getAlphaimpl(background);
        float fgA = Color.m4536getAlphaimpl(fg);
        float a = ((1.0f - fgA) * bgA) + fgA;
        float fgC$iv = Color.m4540getRedimpl(fg);
        float bgC$iv = Color.m4540getRedimpl(background);
        float f3 = 0.0f;
        if (!(a == 0.0f)) {
            f = ((fgC$iv * fgA) + ((bgC$iv * bgA) * (1.0f - fgA))) / a;
        } else {
            f = 0.0f;
        }
        float fgC$iv2 = f;
        float fgC$iv3 = Color.m4539getGreenimpl(fg);
        float bgC$iv2 = Color.m4539getGreenimpl(background);
        if (!(a == 0.0f)) {
            f2 = ((fgC$iv3 * fgA) + ((bgC$iv2 * bgA) * (1.0f - fgA))) / a;
        } else {
            f2 = 0.0f;
        }
        float fgC$iv4 = f2;
        float fgC$iv5 = Color.m4537getBlueimpl(fg);
        float bgC$iv3 = Color.m4537getBlueimpl(background);
        if (!(a == 0.0f)) {
            f3 = ((fgC$iv5 * fgA) + ((bgC$iv3 * bgA) * (1.0f - fgA))) / a;
        }
        float b = f3;
        return UncheckedColor(fgC$iv2, fgC$iv4, b, a, Color.m4538getColorSpaceimpl(background));
    }

    private static final float compositeComponent(float fgC, float bgC, float fgA, float bgA, float a) {
        if (a == 0.0f) {
            return 0.0f;
        }
        return ((fgC * fgA) + ((bgC * bgA) * (1.0f - fgA))) / a;
    }

    /* JADX INFO: renamed from: getComponents-8_81llA, reason: not valid java name */
    private static final float[] m4580getComponents8_81llA(long $this$getComponents_u2d8_81llA) {
        return new float[]{Color.m4540getRedimpl($this$getComponents_u2d8_81llA), Color.m4539getGreenimpl($this$getComponents_u2d8_81llA), Color.m4537getBlueimpl($this$getComponents_u2d8_81llA), Color.m4536getAlphaimpl($this$getComponents_u2d8_81llA)};
    }

    /* JADX INFO: renamed from: luminance-8_81llA, reason: not valid java name */
    public static final float m4586luminance8_81llA(long $this$luminance_u2d8_81llA) {
        ColorSpace colorSpace = Color.m4538getColorSpaceimpl($this$luminance_u2d8_81llA);
        boolean value$iv = ColorModel.m4941equalsimpl0(colorSpace.getModel(), ColorModel.INSTANCE.m4948getRgbxdoWZVw());
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) ColorModel.m4944toStringimpl(colorSpace.getModel())));
        }
        Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
        DoubleFunction eotf = ((Rgb) colorSpace).getEotfFunc();
        double r = eotf.invoke(Color.m4540getRedimpl($this$luminance_u2d8_81llA));
        double g = eotf.invoke(Color.m4539getGreenimpl($this$luminance_u2d8_81llA));
        double b = eotf.invoke(Color.m4537getBlueimpl($this$luminance_u2d8_81llA));
        float $this$fastCoerceIn$iv = (float) ((0.2126d * r) + (0.7152d * g) + (0.0722d * b));
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    /* JADX INFO: renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m4588toArgb8_81llA(long $this$toArgb_u2d8_81llA) {
        return (int) ULong.m7562constructorimpl(Color.m4531convertvNxB06k($this$toArgb_u2d8_81llA, ColorSpaces.INSTANCE.getSrgb()) >>> 32);
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA, reason: not valid java name */
    public static final boolean m4581isSpecified8_81llA(long $this$isSpecified) {
        return $this$isSpecified != 16;
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA, reason: not valid java name */
    public static final boolean m4583isUnspecified8_81llA(long $this$isUnspecified) {
        return $this$isUnspecified == 16;
    }

    /* JADX INFO: renamed from: takeOrElse-DxMtmZc, reason: not valid java name */
    public static final long m4587takeOrElseDxMtmZc(long $this$takeOrElse_u2dDxMtmZc, Function0<Color> function0) {
        return ($this$takeOrElse_u2dDxMtmZc > 16L ? 1 : ($this$takeOrElse_u2dDxMtmZc == 16L ? 0 : -1)) != 0 ? $this$takeOrElse_u2dDxMtmZc : function0.invoke().m4544unboximpl();
    }
}
