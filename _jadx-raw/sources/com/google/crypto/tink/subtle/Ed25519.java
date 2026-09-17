package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: classes15.dex */
final class Ed25519 {
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SECRET_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;
    private static final CachedXYT CACHED_NEUTRAL = new CachedXYT(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private static final PartialXYZT NEUTRAL = new PartialXYZT(new XYZ(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    static final byte[] GROUP_ORDER = {-19, -45, -11, 92, 26, 99, 18, 88, -42, -100, -9, -94, -34, -7, -34, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};

    private static class XYZ {
        final long[] x;
        final long[] y;
        final long[] z;

        XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        XYZ(long[] x, long[] y, long[] z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        XYZ(XYZ xyz) {
            this.x = Arrays.copyOf(xyz.x, 10);
            this.y = Arrays.copyOf(xyz.y, 10);
            this.z = Arrays.copyOf(xyz.z, 10);
        }

        XYZ(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        static XYZ fromPartialXYZT(XYZ out, PartialXYZT in) {
            Field25519.mult(out.x, in.xyz.x, in.t);
            Field25519.mult(out.y, in.xyz.y, in.xyz.z);
            Field25519.mult(out.z, in.xyz.z, in.t);
            return out;
        }

        byte[] toBytes() {
            long[] recip = new long[10];
            long[] x = new long[10];
            long[] y = new long[10];
            Field25519.inverse(recip, this.z);
            Field25519.mult(x, this.x, recip);
            Field25519.mult(y, this.y, recip);
            byte[] s = Field25519.contract(y);
            s[31] = (byte) (s[31] ^ (Ed25519.getLsb(x) << 7));
            return s;
        }

        boolean isOnCurve() {
            long[] x2 = new long[10];
            Field25519.square(x2, this.x);
            long[] y2 = new long[10];
            Field25519.square(y2, this.y);
            long[] z2 = new long[10];
            Field25519.square(z2, this.z);
            long[] z4 = new long[10];
            Field25519.square(z4, z2);
            long[] lhs = new long[10];
            Field25519.sub(lhs, y2, x2);
            Field25519.mult(lhs, lhs, z2);
            long[] rhs = new long[10];
            Field25519.mult(rhs, x2, y2);
            Field25519.mult(rhs, rhs, Ed25519Constants.D);
            Field25519.sum(rhs, z4);
            Field25519.reduce(rhs, rhs);
            return Bytes.equal(Field25519.contract(lhs), Field25519.contract(rhs));
        }
    }

    private static class XYZT {
        final long[] t;
        final XYZ xyz;

        XYZT() {
            this(new XYZ(), new long[10]);
        }

        XYZT(XYZ xyz, long[] t) {
            this.xyz = xyz;
            this.t = t;
        }

        XYZT(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromPartialXYZT(XYZT out, PartialXYZT in) {
            Field25519.mult(out.xyz.x, in.xyz.x, in.t);
            Field25519.mult(out.xyz.y, in.xyz.y, in.xyz.z);
            Field25519.mult(out.xyz.z, in.xyz.z, in.t);
            Field25519.mult(out.t, in.xyz.x, in.xyz.y);
            return out;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromBytesNegateVarTime(byte[] s) throws GeneralSecurityException {
            long[] x = new long[10];
            long[] y = Field25519.expand(s);
            long[] z = new long[10];
            z[0] = 1;
            long[] t = new long[10];
            long[] u = new long[10];
            long[] v = new long[10];
            long[] vxx = new long[10];
            long[] check = new long[10];
            Field25519.square(u, y);
            Field25519.mult(v, u, Ed25519Constants.D);
            Field25519.sub(u, u, z);
            Field25519.sum(v, v, z);
            long[] v3 = new long[10];
            Field25519.square(v3, v);
            Field25519.mult(v3, v3, v);
            Field25519.square(x, v3);
            Field25519.mult(x, x, v);
            Field25519.mult(x, x, u);
            Ed25519.pow2252m3(x, x);
            Field25519.mult(x, x, v3);
            Field25519.mult(x, x, u);
            Field25519.square(vxx, x);
            Field25519.mult(vxx, vxx, v);
            Field25519.sub(check, vxx, u);
            if (Ed25519.isNonZeroVarTime(check)) {
                Field25519.sum(check, vxx, u);
                if (Ed25519.isNonZeroVarTime(check)) {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
                Field25519.mult(x, x, Ed25519Constants.SQRTM1);
            }
            if (Ed25519.isNonZeroVarTime(x) || ((s[31] & UByte.MAX_VALUE) >> 7) == 0) {
                if (Ed25519.getLsb(x) == ((s[31] & UByte.MAX_VALUE) >> 7)) {
                    Ed25519.neg(x, x);
                }
                Field25519.mult(t, x, y);
                return new XYZT(new XYZ(x, y, z), t);
            }
            throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        }
    }

    private static class PartialXYZT {
        final long[] t;
        final XYZ xyz;

        PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        PartialXYZT(XYZ xyz, long[] t) {
            this.xyz = xyz;
            this.t = t;
        }

        PartialXYZT(PartialXYZT other) {
            this.xyz = new XYZ(other.xyz);
            this.t = Arrays.copyOf(other.t, 10);
        }
    }

    static class CachedXYT {
        final long[] t2d;
        final long[] yMinusX;
        final long[] yPlusX;

        CachedXYT() {
            this(new long[10], new long[10], new long[10]);
        }

        CachedXYT(long[] yPlusX, long[] yMinusX, long[] t2d) {
            this.yPlusX = yPlusX;
            this.yMinusX = yMinusX;
            this.t2d = t2d;
        }

        CachedXYT(CachedXYT other) {
            this.yPlusX = Arrays.copyOf(other.yPlusX, 10);
            this.yMinusX = Arrays.copyOf(other.yMinusX, 10);
            this.t2d = Arrays.copyOf(other.t2d, 10);
        }

        void multByZ(long[] output, long[] in) {
            System.arraycopy(in, 0, output, 0, 10);
        }

        void copyConditional(CachedXYT other, int icopy) {
            Curve25519.copyConditional(this.yPlusX, other.yPlusX, icopy);
            Curve25519.copyConditional(this.yMinusX, other.yMinusX, icopy);
            Curve25519.copyConditional(this.t2d, other.t2d, icopy);
        }
    }

    private static class CachedXYZT extends CachedXYT {
        private final long[] z;

        CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        CachedXYZT(XYZT xyzt) {
            this();
            Field25519.sum(this.yPlusX, xyzt.xyz.y, xyzt.xyz.x);
            Field25519.sub(this.yMinusX, xyzt.xyz.y, xyzt.xyz.x);
            System.arraycopy(xyzt.xyz.z, 0, this.z, 0, 10);
            Field25519.mult(this.t2d, xyzt.t, Ed25519Constants.D2);
        }

        CachedXYZT(long[] yPlusX, long[] yMinusX, long[] z, long[] t2d) {
            super(yPlusX, yMinusX, t2d);
            this.z = z;
        }

        @Override // com.google.crypto.tink.subtle.Ed25519.CachedXYT
        public void multByZ(long[] output, long[] in) {
            Field25519.mult(output, in, this.z);
        }
    }

    private static void add(PartialXYZT partialXYZT, XYZT extended, CachedXYT cached) {
        long[] t = new long[10];
        Field25519.sum(partialXYZT.xyz.x, extended.xyz.y, extended.xyz.x);
        Field25519.sub(partialXYZT.xyz.y, extended.xyz.y, extended.xyz.x);
        Field25519.mult(partialXYZT.xyz.y, partialXYZT.xyz.y, cached.yMinusX);
        Field25519.mult(partialXYZT.xyz.z, partialXYZT.xyz.x, cached.yPlusX);
        Field25519.mult(partialXYZT.t, extended.t, cached.t2d);
        cached.multByZ(partialXYZT.xyz.x, extended.xyz.z);
        Field25519.sum(t, partialXYZT.xyz.x, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.z, t, partialXYZT.t);
        Field25519.sub(partialXYZT.t, t, partialXYZT.t);
    }

    private static void sub(PartialXYZT partialXYZT, XYZT extended, CachedXYT cached) {
        long[] t = new long[10];
        Field25519.sum(partialXYZT.xyz.x, extended.xyz.y, extended.xyz.x);
        Field25519.sub(partialXYZT.xyz.y, extended.xyz.y, extended.xyz.x);
        Field25519.mult(partialXYZT.xyz.y, partialXYZT.xyz.y, cached.yPlusX);
        Field25519.mult(partialXYZT.xyz.z, partialXYZT.xyz.x, cached.yMinusX);
        Field25519.mult(partialXYZT.t, extended.t, cached.t2d);
        cached.multByZ(partialXYZT.xyz.x, extended.xyz.z);
        Field25519.sum(t, partialXYZT.xyz.x, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sub(partialXYZT.xyz.z, t, partialXYZT.t);
        Field25519.sum(partialXYZT.t, t, partialXYZT.t);
    }

    private static void doubleXYZ(PartialXYZT partialXYZT, XYZ p) {
        long[] t0 = new long[10];
        Field25519.square(partialXYZT.xyz.x, p.x);
        Field25519.square(partialXYZT.xyz.z, p.y);
        Field25519.square(partialXYZT.t, p.z);
        Field25519.sum(partialXYZT.t, partialXYZT.t, partialXYZT.t);
        Field25519.sum(partialXYZT.xyz.y, p.x, p.y);
        Field25519.square(t0, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.z, partialXYZT.xyz.z, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, t0, partialXYZT.xyz.y);
        Field25519.sub(partialXYZT.t, partialXYZT.t, partialXYZT.xyz.z);
    }

    private static void doubleXYZT(PartialXYZT partialXYZT, XYZT p) {
        doubleXYZ(partialXYZT, p.xyz);
    }

    private static int eq(int a, int b) {
        int r = (~(a ^ b)) & 255;
        int r2 = r & (r << 4);
        int r3 = r2 & (r2 << 2);
        return ((r3 & (r3 << 1)) >> 7) & 1;
    }

    private static void select(CachedXYT t, int pos, byte b) {
        int bnegative = (b & UByte.MAX_VALUE) >> 7;
        int babs = b - (((-bnegative) & b) << 1);
        t.copyConditional(Ed25519Constants.B_TABLE[pos][0], eq(babs, 1));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][1], eq(babs, 2));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][2], eq(babs, 3));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][3], eq(babs, 4));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][4], eq(babs, 5));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][5], eq(babs, 6));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][6], eq(babs, 7));
        t.copyConditional(Ed25519Constants.B_TABLE[pos][7], eq(babs, 8));
        long[] yPlusX = Arrays.copyOf(t.yMinusX, 10);
        long[] yMinusX = Arrays.copyOf(t.yPlusX, 10);
        long[] t2d = Arrays.copyOf(t.t2d, 10);
        neg(t2d, t2d);
        CachedXYT minust = new CachedXYT(yPlusX, yMinusX, t2d);
        t.copyConditional(minust, bnegative);
    }

    private static XYZ scalarMultWithBase(byte[] a) {
        byte[] e = new byte[64];
        for (int i = 0; i < 32; i++) {
            e[(i * 2) + 0] = (byte) (((a[i] & UByte.MAX_VALUE) >> 0) & 15);
            e[(i * 2) + 1] = (byte) (((a[i] & UByte.MAX_VALUE) >> 4) & 15);
        }
        int carry = 0;
        for (int i2 = 0; i2 < e.length - 1; i2++) {
            e[i2] = (byte) (e[i2] + carry);
            int carry2 = e[i2] + 8;
            carry = carry2 >> 4;
            int carry3 = e[i2];
            e[i2] = (byte) (carry3 - (carry << 4));
        }
        int i3 = e.length;
        int i4 = i3 - 1;
        e[i4] = (byte) (e[i4] + carry);
        PartialXYZT ret = new PartialXYZT(NEUTRAL);
        XYZT xyzt = new XYZT();
        for (int i5 = 1; i5 < e.length; i5 += 2) {
            CachedXYT t = new CachedXYT(CACHED_NEUTRAL);
            select(t, i5 / 2, e[i5]);
            add(ret, XYZT.fromPartialXYZT(xyzt, ret), t);
        }
        XYZ xyz = new XYZ();
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        doubleXYZ(ret, XYZ.fromPartialXYZT(xyz, ret));
        for (int i6 = 0; i6 < e.length; i6 += 2) {
            CachedXYT t2 = new CachedXYT(CACHED_NEUTRAL);
            select(t2, i6 / 2, e[i6]);
            add(ret, XYZT.fromPartialXYZT(xyzt, ret), t2);
        }
        XYZ result = new XYZ(ret);
        if (!result.isOnCurve()) {
            throw new IllegalStateException("arithmetic error in scalar multiplication");
        }
        return result;
    }

    static byte[] scalarMultWithBaseToBytes(byte[] a) {
        return scalarMultWithBase(a).toBytes();
    }

    private static byte[] slide(byte[] a) {
        byte[] r = new byte[256];
        for (int i = 0; i < 256; i++) {
            r[i] = (byte) (1 & ((a[i >> 3] & UByte.MAX_VALUE) >> (i & 7)));
        }
        for (int i2 = 0; i2 < 256; i2++) {
            if (r[i2] != 0) {
                for (int b = 1; b <= 6 && i2 + b < 256; b++) {
                    if (r[i2 + b] != 0) {
                        if (r[i2] + (r[i2 + b] << b) <= 15) {
                            r[i2] = (byte) (r[i2] + (r[i2 + b] << b));
                            r[i2 + b] = 0;
                        } else {
                            if (r[i2] - (r[i2 + b] << b) < -15) {
                                break;
                            }
                            r[i2] = (byte) (r[i2] - (r[i2 + b] << b));
                            for (int k = i2 + b; k < 256; k++) {
                                if (r[k] == 0) {
                                    r[k] = 1;
                                    break;
                                }
                                r[k] = 0;
                            }
                        }
                    }
                }
            }
        }
        return r;
    }

    private static XYZ doubleScalarMultVarTime(byte[] a, XYZT pointA, byte[] b) {
        CachedXYZT[] pointAArray = new CachedXYZT[8];
        pointAArray[0] = new CachedXYZT(pointA);
        PartialXYZT t = new PartialXYZT();
        doubleXYZT(t, pointA);
        XYZT doubleA = new XYZT(t);
        for (int i = 1; i < pointAArray.length; i++) {
            add(t, doubleA, pointAArray[i - 1]);
            pointAArray[i] = new CachedXYZT(new XYZT(t));
        }
        byte[] aSlide = slide(a);
        byte[] bSlide = slide(b);
        PartialXYZT t2 = new PartialXYZT(NEUTRAL);
        XYZT u = new XYZT();
        int i2 = 255;
        while (i2 >= 0 && aSlide[i2] == 0 && bSlide[i2] == 0) {
            i2--;
        }
        while (i2 >= 0) {
            doubleXYZ(t2, new XYZ(t2));
            if (aSlide[i2] > 0) {
                add(t2, XYZT.fromPartialXYZT(u, t2), pointAArray[aSlide[i2] / 2]);
            } else if (aSlide[i2] < 0) {
                sub(t2, XYZT.fromPartialXYZT(u, t2), pointAArray[(-aSlide[i2]) / 2]);
            }
            if (bSlide[i2] > 0) {
                add(t2, XYZT.fromPartialXYZT(u, t2), Ed25519Constants.B2[bSlide[i2] / 2]);
            } else if (bSlide[i2] < 0) {
                sub(t2, XYZT.fromPartialXYZT(u, t2), Ed25519Constants.B2[(-bSlide[i2]) / 2]);
            }
            i2--;
        }
        return new XYZ(t2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNonZeroVarTime(long[] in) {
        long[] inCopy = new long[in.length + 1];
        System.arraycopy(in, 0, inCopy, 0, in.length);
        Field25519.reduceCoefficients(inCopy);
        byte[] bytes = Field25519.contract(inCopy);
        for (byte b : bytes) {
            if (b != 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLsb(long[] in) {
        return Field25519.contract(in)[0] & 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void neg(long[] out, long[] in) {
        for (int i = 0; i < in.length; i++) {
            out[i] = -in[i];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void pow2252m3(long[] out, long[] in) {
        long[] t0 = new long[10];
        long[] t1 = new long[10];
        long[] t2 = new long[10];
        Field25519.square(t0, in);
        Field25519.square(t1, t0);
        for (int i = 1; i < 2; i++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t1, in, t1);
        Field25519.mult(t0, t0, t1);
        Field25519.square(t0, t0);
        Field25519.mult(t0, t1, t0);
        Field25519.square(t1, t0);
        for (int i2 = 1; i2 < 5; i2++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t0, t1, t0);
        Field25519.square(t1, t0);
        for (int i3 = 1; i3 < 10; i3++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t1, t1, t0);
        Field25519.square(t2, t1);
        for (int i4 = 1; i4 < 20; i4++) {
            Field25519.square(t2, t2);
        }
        Field25519.mult(t1, t2, t1);
        Field25519.square(t1, t1);
        for (int i5 = 1; i5 < 10; i5++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t0, t1, t0);
        Field25519.square(t1, t0);
        for (int i6 = 1; i6 < 50; i6++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t1, t1, t0);
        Field25519.square(t2, t1);
        for (int i7 = 1; i7 < 100; i7++) {
            Field25519.square(t2, t2);
        }
        Field25519.mult(t1, t2, t1);
        Field25519.square(t1, t1);
        for (int i8 = 1; i8 < 50; i8++) {
            Field25519.square(t1, t1);
        }
        Field25519.mult(t0, t1, t0);
        Field25519.square(t0, t0);
        for (int i9 = 1; i9 < 2; i9++) {
            Field25519.square(t0, t0);
        }
        Field25519.mult(out, t0, in);
    }

    private static long load3(byte[] in, int idx) {
        long result = ((long) in[idx]) & 255;
        return result | (((long) (in[idx + 1] & UByte.MAX_VALUE)) << 8) | (((long) (in[idx + 2] & UByte.MAX_VALUE)) << 16);
    }

    private static long load4(byte[] in, int idx) {
        long result = load3(in, idx);
        return result | (((long) (in[idx + 3] & UByte.MAX_VALUE)) << 24);
    }

    private static void reduce(byte[] s) {
        long s0 = load3(s, 0) & 2097151;
        long s1 = (load4(s, 2) >> 5) & 2097151;
        long s2 = (load3(s, 5) >> 2) & 2097151;
        long s3 = (load4(s, 7) >> 7) & 2097151;
        long s4 = (load4(s, 10) >> 4) & 2097151;
        long s5 = (load3(s, 13) >> 1) & 2097151;
        long s6 = (load4(s, 15) >> 6) & 2097151;
        long s7 = (load3(s, 18) >> 3) & 2097151;
        long s8 = load3(s, 21) & 2097151;
        long s9 = (load4(s, 23) >> 5) & 2097151;
        long s10 = (load3(s, 26) >> 2) & 2097151;
        long s11 = (load4(s, 28) >> 7) & 2097151;
        long s12 = (load4(s, 31) >> 4) & 2097151;
        long s13 = (load3(s, 34) >> 1) & 2097151;
        long s14 = (load4(s, 36) >> 6) & 2097151;
        long s15 = (load3(s, 39) >> 3) & 2097151;
        long s16 = load3(s, 42) & 2097151;
        long s17 = (load4(s, 44) >> 5) & 2097151;
        long s18 = (load3(s, 47) >> 2) & 2097151;
        long s19 = (load4(s, 49) >> 7) & 2097151;
        long s20 = (load4(s, 52) >> 4) & 2097151;
        long s21 = (load3(s, 55) >> 1) & 2097151;
        long s22 = (load4(s, 57) >> 6) & 2097151;
        long s23 = load4(s, 60) >> 3;
        long s110 = s16 - (s23 * 683901);
        long s111 = ((s14 - (s23 * 997805)) + (s22 * 136657)) - (s21 * 683901);
        long s112 = ((((s12 + (s23 * 470296)) + (s22 * 654183)) - (s21 * 997805)) + (s20 * 136657)) - (s19 * 683901);
        long s24 = s6 + (s18 * 666643);
        long s25 = s8 + (s20 * 666643) + (s19 * 470296) + (s18 * 654183);
        long s113 = ((((s10 + (s22 * 666643)) + (s21 * 470296)) + (s20 * 654183)) - (s19 * 997805)) + (s18 * 136657);
        long carry6 = (s24 + 1048576) >> 21;
        long s26 = s7 + (s19 * 666643) + (s18 * 470296) + carry6;
        long carry8 = (s25 + 1048576) >> 21;
        long s27 = ((((s9 + (s21 * 666643)) + (s20 * 470296)) + (s19 * 654183)) - (s18 * 997805)) + carry8;
        long carry10 = (s113 + 1048576) >> 21;
        long s114 = ((((((s11 + (s23 * 666643)) + (s22 * 470296)) + (s21 * 654183)) - (s20 * 997805)) + (s19 * 136657)) - (s18 * 683901)) + carry10;
        long carry12 = (s112 + 1048576) >> 21;
        long s115 = ((((s13 + (s23 * 654183)) - (s22 * 997805)) + (s21 * 136657)) - (s20 * 683901)) + carry12;
        long carry14 = (s111 + 1048576) >> 21;
        long s116 = ((s15 + (s23 * 136657)) - (s22 * 683901)) + carry14;
        long carry16 = (s110 + 1048576) >> 21;
        long s117 = s17 + carry16;
        long carry7 = (s26 + 1048576) >> 21;
        long carry9 = (s27 + 1048576) >> 21;
        long carry11 = (s114 + 1048576) >> 21;
        long s118 = (s112 - (carry12 << 21)) + carry11;
        long carry13 = (s115 + 1048576) >> 21;
        long s119 = (s111 - (carry14 << 21)) + carry13;
        long s120 = s115 - (carry13 << 21);
        long carry15 = (s116 + 1048576) >> 21;
        long s121 = (s110 - (carry16 << 21)) + carry15;
        long s122 = s116 - (carry15 << 21);
        long s123 = ((s113 - (carry10 << 21)) + carry9) - (s117 * 683901);
        long s28 = ((((s25 - (carry8 << 21)) + carry7) - (s117 * 997805)) + (s121 * 136657)) - (s122 * 683901);
        long s29 = (((((s24 - (carry6 << 21)) + (s117 * 470296)) + (s121 * 654183)) - (s122 * 997805)) + (s119 * 136657)) - (s120 * 683901);
        long s30 = s0 + (s118 * 666643);
        long s31 = s2 + (s119 * 666643) + (s120 * 470296) + (s118 * 654183);
        long s32 = ((((s4 + (s121 * 666643)) + (s122 * 470296)) + (s119 * 654183)) - (s120 * 997805)) + (s118 * 136657);
        long carry0 = (s30 + 1048576) >> 21;
        long s33 = s1 + (s120 * 666643) + (s118 * 470296) + carry0;
        long carry2 = (s31 + 1048576) >> 21;
        long s34 = ((((s3 + (s122 * 666643)) + (s119 * 470296)) + (s120 * 654183)) - (s118 * 997805)) + carry2;
        long carry4 = (s32 + 1048576) >> 21;
        long s35 = ((((((s5 + (s117 * 666643)) + (s121 * 470296)) + (s122 * 654183)) - (s119 * 997805)) + (s120 * 136657)) - (s118 * 683901)) + carry4;
        long carry17 = (s29 + 1048576) >> 21;
        long s36 = (((((s26 - (carry7 << 21)) + (s117 * 654183)) - (s121 * 997805)) + (s122 * 136657)) - (s119 * 683901)) + carry17;
        long carry18 = (s28 + 1048576) >> 21;
        long s37 = (((s27 - (carry9 << 21)) + (s117 * 136657)) - (s121 * 683901)) + carry18;
        long carry19 = (s123 + 1048576) >> 21;
        long s124 = (s114 - (carry11 << 21)) + carry19;
        long carry1 = (s33 + 1048576) >> 21;
        long carry3 = (s34 + 1048576) >> 21;
        long carry5 = (s35 + 1048576) >> 21;
        long carry20 = (s36 + 1048576) >> 21;
        long s38 = (s28 - (carry18 << 21)) + carry20;
        long carry21 = (s37 + 1048576) >> 21;
        long s125 = (s123 - (carry19 << 21)) + carry21;
        long carry110 = (s124 + 1048576) >> 21;
        long s126 = 0 + carry110;
        long s39 = (s30 - (carry0 << 21)) + (s126 * 666643);
        long carry22 = s39 >> 21;
        long s40 = (s33 - (carry1 << 21)) + (s126 * 470296) + carry22;
        long carry23 = s40 >> 21;
        long s41 = (s31 - (carry2 << 21)) + carry1 + (s126 * 654183) + carry23;
        long carry24 = s41 >> 21;
        long s42 = ((s34 - (carry3 << 21)) - (s126 * 997805)) + carry24;
        long carry25 = s42 >> 21;
        long s43 = (s32 - (carry4 << 21)) + carry3 + (s126 * 136657) + carry25;
        long carry26 = s43 >> 21;
        long s44 = ((s35 - (carry5 << 21)) - (s126 * 683901)) + carry26;
        long carry27 = s44 >> 21;
        long s45 = (s29 - (carry17 << 21)) + carry5 + carry27;
        long carry28 = s45 >> 21;
        long s46 = (s36 - (carry20 << 21)) + carry28;
        long carry29 = s46 >> 21;
        long s47 = s38 + carry29;
        long carry30 = s47 >> 21;
        long s48 = (s37 - (carry21 << 21)) + carry30;
        long carry31 = s48 >> 21;
        long s127 = s125 + carry31;
        long carry111 = s127 >> 21;
        long s128 = (s124 - (carry110 << 21)) + carry111;
        long carry112 = s128 >> 21;
        long s129 = 0 + carry112;
        long s49 = (s39 - (carry22 << 21)) + (666643 * s129);
        long carry32 = s49 >> 21;
        long s50 = (s40 - (carry23 << 21)) + (470296 * s129) + carry32;
        long s51 = s49 - (carry32 << 21);
        long carry33 = s50 >> 21;
        long s52 = (s41 - (carry24 << 21)) + (654183 * s129) + carry33;
        long s53 = s50 - (carry33 << 21);
        long carry34 = s52 >> 21;
        long s54 = ((s42 - (carry25 << 21)) - (997805 * s129)) + carry34;
        long s55 = s52 - (carry34 << 21);
        long carry35 = s54 >> 21;
        long s56 = (s43 - (carry26 << 21)) + (136657 * s129) + carry35;
        long s57 = s54 - (carry35 << 21);
        long carry36 = s56 >> 21;
        long s58 = ((s44 - (carry27 << 21)) - (683901 * s129)) + carry36;
        long s59 = s56 - (carry36 << 21);
        long carry37 = s58 >> 21;
        long s60 = (s45 - (carry28 << 21)) + carry37;
        long s61 = s58 - (carry37 << 21);
        long carry38 = s60 >> 21;
        long s62 = (s46 - (carry29 << 21)) + carry38;
        long s63 = s60 - (carry38 << 21);
        long carry39 = s62 >> 21;
        long s64 = (s47 - (carry30 << 21)) + carry39;
        long s65 = s62 - (carry39 << 21);
        long carry40 = s64 >> 21;
        long s66 = (s48 - (carry31 << 21)) + carry40;
        long s67 = s64 - (carry40 << 21);
        long s68 = s66 >> 21;
        long s130 = (s127 - (carry111 << 21)) + s68;
        long s69 = s66 - (s68 << 21);
        long carry113 = s130 >> 21;
        long s131 = (s128 - (carry112 << 21)) + carry113;
        long s132 = s130 - (carry113 << 21);
        s[0] = (byte) s51;
        s[1] = (byte) (s51 >> 8);
        s[2] = (byte) ((s51 >> 16) | (s53 << 5));
        s[3] = (byte) (s53 >> 3);
        s[4] = (byte) (s53 >> 11);
        s[5] = (byte) ((s53 >> 19) | (s55 << 2));
        s[6] = (byte) (s55 >> 6);
        s[7] = (byte) ((s55 >> 14) | (s57 << 7));
        s[8] = (byte) (s57 >> 1);
        s[9] = (byte) (s57 >> 9);
        s[10] = (byte) ((s57 >> 17) | (s59 << 4));
        s[11] = (byte) (s59 >> 4);
        s[12] = (byte) (s59 >> 12);
        s[13] = (byte) ((s59 >> 20) | (s61 << 1));
        s[14] = (byte) (s61 >> 7);
        s[15] = (byte) ((s61 >> 15) | (s63 << 6));
        s[16] = (byte) (s63 >> 2);
        s[17] = (byte) (s63 >> 10);
        s[18] = (byte) ((s63 >> 18) | (s65 << 3));
        s[19] = (byte) (s65 >> 5);
        s[20] = (byte) (s65 >> 13);
        s[21] = (byte) s67;
        s[22] = (byte) (s67 >> 8);
        s[23] = (byte) ((s67 >> 16) | (s69 << 5));
        s[24] = (byte) (s69 >> 3);
        s[25] = (byte) (s69 >> 11);
        s[26] = (byte) ((s69 >> 19) | (s132 << 2));
        s[27] = (byte) (s132 >> 6);
        s[28] = (byte) ((s132 >> 14) | (s131 << 7));
        s[29] = (byte) (s131 >> 1);
        s[30] = (byte) (s131 >> 9);
        s[31] = (byte) (s131 >> 17);
    }

    private static void mulAdd(byte[] s, byte[] a, byte[] b, byte[] c) {
        long a0 = load3(a, 0) & 2097151;
        long a1 = (load4(a, 2) >> 5) & 2097151;
        long a2 = (load3(a, 5) >> 2) & 2097151;
        long a3 = (load4(a, 7) >> 7) & 2097151;
        long a4 = (load4(a, 10) >> 4) & 2097151;
        long a5 = (load3(a, 13) >> 1) & 2097151;
        long a6 = (load4(a, 15) >> 6) & 2097151;
        long a7 = (load3(a, 18) >> 3) & 2097151;
        long a8 = load3(a, 21) & 2097151;
        long a9 = (load4(a, 23) >> 5) & 2097151;
        long a10 = (load3(a, 26) >> 2) & 2097151;
        long a11 = load4(a, 28) >> 7;
        long b0 = load3(b, 0) & 2097151;
        long b1 = (load4(b, 2) >> 5) & 2097151;
        long b2 = (load3(b, 5) >> 2) & 2097151;
        long b3 = (load4(b, 7) >> 7) & 2097151;
        long b4 = (load4(b, 10) >> 4) & 2097151;
        long b5 = (load3(b, 13) >> 1) & 2097151;
        long b6 = (load4(b, 15) >> 6) & 2097151;
        long b7 = (load3(b, 18) >> 3) & 2097151;
        long b8 = load3(b, 21) & 2097151;
        long b9 = (load4(b, 23) >> 5) & 2097151;
        long b10 = (load3(b, 26) >> 2) & 2097151;
        long b11 = load4(b, 28) >> 7;
        long c0 = load3(c, 0) & 2097151;
        long c1 = (load4(c, 2) >> 5) & 2097151;
        long c2 = (load3(c, 5) >> 2) & 2097151;
        long c3 = (load4(c, 7) >> 7) & 2097151;
        long c4 = (load4(c, 10) >> 4) & 2097151;
        long c5 = (load3(c, 13) >> 1) & 2097151;
        long c6 = (load4(c, 15) >> 6) & 2097151;
        long c7 = (load3(c, 18) >> 3) & 2097151;
        long c8 = load3(c, 21) & 2097151;
        long c9 = (load4(c, 23) >> 5) & 2097151;
        long c10 = (load3(c, 26) >> 2) & 2097151;
        long c11 = load4(c, 28) >> 7;
        long s0 = c0 + (a0 * b0);
        long s2 = c2 + (a0 * b2) + (a1 * b1) + (a2 * b0);
        long s4 = c4 + (a0 * b4) + (a1 * b3) + (a2 * b2) + (a3 * b1) + (a4 * b0);
        long s6 = c6 + (a0 * b6) + (a1 * b5) + (a2 * b4) + (a3 * b3) + (a4 * b2) + (a5 * b1) + (a6 * b0);
        long s8 = c8 + (a0 * b8) + (a1 * b7) + (a2 * b6) + (a3 * b5) + (a4 * b4) + (a5 * b3) + (a6 * b2) + (a7 * b1) + (a8 * b0);
        long s10 = c10 + (a0 * b10) + (a1 * b9) + (a2 * b8) + (a3 * b7) + (a4 * b6) + (a5 * b5) + (a6 * b4) + (a7 * b3) + (a8 * b2) + (a9 * b1) + (a10 * b0);
        long s12 = (a1 * b11) + (a2 * b10) + (a3 * b9) + (a4 * b8) + (a5 * b7) + (a6 * b6) + (a7 * b5) + (a8 * b4) + (a9 * b3) + (a10 * b2) + (a11 * b1);
        long s14 = (a3 * b11) + (a4 * b10) + (a5 * b9) + (a6 * b8) + (a7 * b7) + (a8 * b6) + (a9 * b5) + (a10 * b4) + (a11 * b3);
        long s16 = (a5 * b11) + (a6 * b10) + (a7 * b9) + (a8 * b8) + (a9 * b7) + (a10 * b6) + (a11 * b5);
        long s18 = (a7 * b11) + (a8 * b10) + (a9 * b9) + (a10 * b8) + (a11 * b7);
        long s20 = (a9 * b11) + (a10 * b10) + (a11 * b9);
        long s22 = a11 * b11;
        long carry0 = (s0 + 1048576) >> 21;
        long s1 = c1 + (a0 * b1) + (a1 * b0) + carry0;
        long carry2 = (s2 + 1048576) >> 21;
        long s3 = c3 + (a0 * b3) + (a1 * b2) + (a2 * b1) + (a3 * b0) + carry2;
        long carry4 = (s4 + 1048576) >> 21;
        long s5 = c5 + (a0 * b5) + (a1 * b4) + (a2 * b3) + (a3 * b2) + (a4 * b1) + (a5 * b0) + carry4;
        long carry6 = (s6 + 1048576) >> 21;
        long s7 = c7 + (a0 * b7) + (a1 * b6) + (a2 * b5) + (a3 * b4) + (a4 * b3) + (a5 * b2) + (a6 * b1) + (a7 * b0) + carry6;
        long carry8 = (s8 + 1048576) >> 21;
        long s9 = c9 + (a0 * b9) + (a1 * b8) + (a2 * b7) + (a3 * b6) + (a4 * b5) + (a5 * b4) + (a6 * b3) + (a7 * b2) + (a8 * b1) + (a9 * b0) + carry8;
        long carry10 = (s10 + 1048576) >> 21;
        long s11 = c11 + (a0 * b11) + (a1 * b10) + (a2 * b9) + (a3 * b8) + (a4 * b7) + (a5 * b6) + (a6 * b5) + (a7 * b4) + (a8 * b3) + (a9 * b2) + (a10 * b1) + (a11 * b0) + carry10;
        long carry12 = (s12 + 1048576) >> 21;
        long s13 = (a2 * b11) + (a3 * b10) + (a4 * b9) + (a5 * b8) + (a6 * b7) + (a7 * b6) + (a8 * b5) + (a9 * b4) + (a10 * b3) + (a11 * b2) + carry12;
        long carry14 = (s14 + 1048576) >> 21;
        long s15 = (a4 * b11) + (a5 * b10) + (a6 * b9) + (a7 * b8) + (a8 * b7) + (a9 * b6) + (a10 * b5) + (a11 * b4) + carry14;
        long carry16 = (s16 + 1048576) >> 21;
        long s17 = (a6 * b11) + (a7 * b10) + (a8 * b9) + (a9 * b8) + (a10 * b7) + (a11 * b6) + carry16;
        long carry18 = (s18 + 1048576) >> 21;
        long s19 = (a8 * b11) + (a9 * b10) + (a10 * b9) + (a11 * b8) + carry18;
        long carry20 = (s20 + 1048576) >> 21;
        long s21 = (a10 * b11) + (a11 * b10) + carry20;
        long carry22 = (s22 + 1048576) >> 21;
        long s23 = 0 + carry22;
        long carry1 = (s1 + 1048576) >> 21;
        long carry3 = (s3 + 1048576) >> 21;
        long carry5 = (s5 + 1048576) >> 21;
        long carry7 = (s7 + 1048576) >> 21;
        long carry9 = (s9 + 1048576) >> 21;
        long carry11 = (s11 + 1048576) >> 21;
        long carry13 = (s13 + 1048576) >> 21;
        long carry15 = (s15 + 1048576) >> 21;
        long carry17 = (s17 + 1048576) >> 21;
        long s110 = (s18 - (carry18 << 21)) + carry17;
        long carry19 = (s19 + 1048576) >> 21;
        long s24 = (s20 - (carry20 << 21)) + carry19;
        long s111 = s19 - (carry19 << 21);
        long carry21 = (s21 + 1048576) >> 21;
        long s25 = (s22 - (carry22 << 21)) + carry21;
        long s26 = s21 - (carry21 << 21);
        long s112 = ((s16 - (carry16 << 21)) + carry15) - (s23 * 683901);
        long s113 = ((((s14 - (carry14 << 21)) + carry13) - (s23 * 997805)) + (s25 * 136657)) - (s26 * 683901);
        long s114 = ((((((s12 - (carry12 << 21)) + carry11) + (s23 * 470296)) + (s25 * 654183)) - (s26 * 997805)) + (s24 * 136657)) - (s111 * 683901);
        long s27 = (s6 - (carry6 << 21)) + carry5 + (s110 * 666643);
        long s28 = (s8 - (carry8 << 21)) + carry7 + (s24 * 666643) + (s111 * 470296) + (s110 * 654183);
        long s115 = ((((((s10 - (carry10 << 21)) + carry9) + (s25 * 666643)) + (s26 * 470296)) + (s24 * 654183)) - (s111 * 997805)) + (s110 * 136657);
        long carry23 = (s27 + 1048576) >> 21;
        long s29 = (s7 - (carry7 << 21)) + (s111 * 666643) + (s110 * 470296) + carry23;
        long carry24 = (s28 + 1048576) >> 21;
        long s30 = (((((s9 - (carry9 << 21)) + (s26 * 666643)) + (s24 * 470296)) + (s111 * 654183)) - (s110 * 997805)) + carry24;
        long carry110 = (s115 + 1048576) >> 21;
        long s116 = (((((((s11 - (carry11 << 21)) + (s23 * 666643)) + (s25 * 470296)) + (s26 * 654183)) - (s24 * 997805)) + (s111 * 136657)) - (s110 * 683901)) + carry110;
        long carry111 = (s114 + 1048576) >> 21;
        long s117 = (((((s13 - (carry13 << 21)) + (s23 * 654183)) - (s25 * 997805)) + (s26 * 136657)) - (s24 * 683901)) + carry111;
        long carry112 = (s113 + 1048576) >> 21;
        long s118 = (((s15 - (carry15 << 21)) + (s23 * 136657)) - (s25 * 683901)) + carry112;
        long carry113 = (s112 + 1048576) >> 21;
        long s119 = (s17 - (carry17 << 21)) + carry113;
        long carry25 = (s29 + 1048576) >> 21;
        long carry26 = (s30 + 1048576) >> 21;
        long carry114 = (s116 + 1048576) >> 21;
        long s120 = (s114 - (carry111 << 21)) + carry114;
        long carry115 = (s117 + 1048576) >> 21;
        long s121 = (s113 - (carry112 << 21)) + carry115;
        long s122 = s117 - (carry115 << 21);
        long carry116 = (s118 + 1048576) >> 21;
        long s123 = (s112 - (carry113 << 21)) + carry116;
        long s124 = s118 - (carry116 << 21);
        long s125 = ((s115 - (carry110 << 21)) + carry26) - (s119 * 683901);
        long s31 = ((((s28 - (carry24 << 21)) + carry25) - (s119 * 997805)) + (s123 * 136657)) - (s124 * 683901);
        long s32 = (((((s27 - (carry23 << 21)) + (s119 * 470296)) + (s123 * 654183)) - (s124 * 997805)) + (s121 * 136657)) - (s122 * 683901);
        long s33 = (s0 - (carry0 << 21)) + (s120 * 666643);
        long s34 = (s2 - (carry2 << 21)) + carry1 + (s121 * 666643) + (s122 * 470296) + (s120 * 654183);
        long s35 = ((((((s4 - (carry4 << 21)) + carry3) + (s123 * 666643)) + (s124 * 470296)) + (s121 * 654183)) - (s122 * 997805)) + (s120 * 136657);
        long carry27 = (s33 + 1048576) >> 21;
        long s36 = (s1 - (carry1 << 21)) + (s122 * 666643) + (s120 * 470296) + carry27;
        long carry28 = (s34 + 1048576) >> 21;
        long s37 = (((((s3 - (carry3 << 21)) + (s124 * 666643)) + (s121 * 470296)) + (s122 * 654183)) - (s120 * 997805)) + carry28;
        long carry29 = (s35 + 1048576) >> 21;
        long s38 = (((((((s5 - (carry5 << 21)) + (s119 * 666643)) + (s123 * 470296)) + (s124 * 654183)) - (s121 * 997805)) + (s122 * 136657)) - (s120 * 683901)) + carry29;
        long carry30 = (s32 + 1048576) >> 21;
        long s39 = (((((s29 - (carry25 << 21)) + (s119 * 654183)) - (s123 * 997805)) + (s124 * 136657)) - (s121 * 683901)) + carry30;
        long carry31 = (s31 + 1048576) >> 21;
        long s40 = (((s30 - (carry26 << 21)) + (s119 * 136657)) - (s123 * 683901)) + carry31;
        long carry117 = (s125 + 1048576) >> 21;
        long s126 = (s116 - (carry114 << 21)) + carry117;
        long carry32 = (s36 + 1048576) >> 21;
        long carry33 = (s37 + 1048576) >> 21;
        long carry34 = (s38 + 1048576) >> 21;
        long s41 = (s32 - (carry30 << 21)) + carry34;
        long carry35 = (s39 + 1048576) >> 21;
        long s42 = (s31 - (carry31 << 21)) + carry35;
        long carry36 = (s40 + 1048576) >> 21;
        long s127 = (s125 - (carry117 << 21)) + carry36;
        long carry118 = (s126 + 1048576) >> 21;
        long s128 = 0 + carry118;
        long s43 = (s33 - (carry27 << 21)) + (s128 * 666643);
        long s44 = (s34 - (carry28 << 21)) + carry32 + (s128 * 654183);
        long s45 = (s35 - (carry29 << 21)) + carry33 + (s128 * 136657);
        long carry37 = s43 >> 21;
        long s46 = (s36 - (carry32 << 21)) + (s128 * 470296) + carry37;
        long carry38 = s46 >> 21;
        long s47 = s44 + carry38;
        long carry39 = s47 >> 21;
        long s48 = ((s37 - (carry33 << 21)) - (s128 * 997805)) + carry39;
        long carry40 = s48 >> 21;
        long s49 = s45 + carry40;
        long carry41 = s49 >> 21;
        long s50 = ((s38 - (carry34 << 21)) - (s128 * 683901)) + carry41;
        long carry42 = s50 >> 21;
        long s51 = s41 + carry42;
        long carry43 = s51 >> 21;
        long s52 = (s39 - (carry35 << 21)) + carry43;
        long carry44 = s52 >> 21;
        long s53 = s42 + carry44;
        long carry45 = s53 >> 21;
        long s54 = (s40 - (carry36 << 21)) + carry45;
        long carry46 = s54 >> 21;
        long s129 = s127 + carry46;
        long carry119 = s129 >> 21;
        long s130 = (s126 - (carry118 << 21)) + carry119;
        long carry120 = s130 >> 21;
        long s131 = 0 + carry120;
        long s55 = (s43 - (carry37 << 21)) + (666643 * s131);
        long carry47 = s55 >> 21;
        long s56 = (s46 - (carry38 << 21)) + (470296 * s131) + carry47;
        long s57 = s55 - (carry47 << 21);
        long s58 = s56 >> 21;
        long s59 = (s47 - (carry39 << 21)) + (654183 * s131) + s58;
        long s60 = s56 - (s58 << 21);
        long carry48 = s59 >> 21;
        long s61 = ((s48 - (carry40 << 21)) - (997805 * s131)) + carry48;
        long s62 = s59 - (carry48 << 21);
        long carry49 = s61 >> 21;
        long s63 = (s49 - (carry41 << 21)) + (136657 * s131) + carry49;
        long s64 = s61 - (carry49 << 21);
        long carry50 = s63 >> 21;
        long s65 = ((s50 - (carry42 << 21)) - (683901 * s131)) + carry50;
        long s66 = s63 - (carry50 << 21);
        long carry51 = s65 >> 21;
        long s67 = (s51 - (carry43 << 21)) + carry51;
        long s68 = s65 - (carry51 << 21);
        long carry52 = s67 >> 21;
        long s69 = (s52 - (carry44 << 21)) + carry52;
        long s70 = s67 - (carry52 << 21);
        long carry53 = s69 >> 21;
        long s71 = (s53 - (carry45 << 21)) + carry53;
        long s72 = s69 - (carry53 << 21);
        long carry54 = s71 >> 21;
        long s73 = (s54 - (carry46 << 21)) + carry54;
        long s74 = s71 - (carry54 << 21);
        long s75 = s73 >> 21;
        long s132 = (s129 - (carry119 << 21)) + s75;
        long s76 = s73 - (s75 << 21);
        long carry121 = s132 >> 21;
        long s133 = (s130 - (carry120 << 21)) + carry121;
        long s134 = s132 - (carry121 << 21);
        s[0] = (byte) s57;
        s[1] = (byte) (s57 >> 8);
        s[2] = (byte) ((s57 >> 16) | (s60 << 5));
        s[3] = (byte) (s60 >> 3);
        s[4] = (byte) (s60 >> 11);
        s[5] = (byte) ((s60 >> 19) | (s62 << 2));
        s[6] = (byte) (s62 >> 6);
        s[7] = (byte) ((s62 >> 14) | (s64 << 7));
        s[8] = (byte) (s64 >> 1);
        s[9] = (byte) (s64 >> 9);
        s[10] = (byte) ((s64 >> 17) | (s66 << 4));
        s[11] = (byte) (s66 >> 4);
        s[12] = (byte) (s66 >> 12);
        s[13] = (byte) ((s66 >> 20) | (s68 << 1));
        s[14] = (byte) (s68 >> 7);
        s[15] = (byte) ((s68 >> 15) | (s70 << 6));
        s[16] = (byte) (s70 >> 2);
        s[17] = (byte) (s70 >> 10);
        s[18] = (byte) ((s70 >> 18) | (s72 << 3));
        s[19] = (byte) (s72 >> 5);
        s[20] = (byte) (s72 >> 13);
        s[21] = (byte) s74;
        s[22] = (byte) (s74 >> 8);
        s[23] = (byte) ((s74 >> 16) | (s76 << 5));
        s[24] = (byte) (s76 >> 3);
        s[25] = (byte) (s76 >> 11);
        s[26] = (byte) ((s76 >> 19) | (s134 << 2));
        s[27] = (byte) (s134 >> 6);
        s[28] = (byte) ((s134 >> 14) | (s133 << 7));
        s[29] = (byte) (s133 >> 1);
        s[30] = (byte) (s133 >> 9);
        s[31] = (byte) (s133 >> 17);
    }

    static byte[] getHashedScalar(final byte[] privateKey) throws GeneralSecurityException {
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        digest.update(privateKey, 0, 32);
        byte[] h = digest.digest();
        h[0] = (byte) (h[0] & 248);
        h[31] = (byte) (h[31] & 127);
        h[31] = (byte) (h[31] | 64);
        return h;
    }

    static byte[] sign(final byte[] message, final byte[] publicKey, final byte[] hashedPrivateKey) throws GeneralSecurityException {
        byte[] messageCopy = Arrays.copyOfRange(message, 0, message.length);
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        digest.update(hashedPrivateKey, 32, 32);
        digest.update(messageCopy);
        byte[] r = digest.digest();
        reduce(r);
        byte[] rB = Arrays.copyOfRange(scalarMultWithBase(r).toBytes(), 0, 32);
        digest.reset();
        digest.update(rB);
        digest.update(publicKey);
        digest.update(messageCopy);
        byte[] hram = digest.digest();
        reduce(hram);
        byte[] s = new byte[32];
        mulAdd(s, hram, hashedPrivateKey, r);
        return Bytes.concat(rB, s);
    }

    private static boolean isSmallerThanGroupOrder(byte[] s) {
        for (int j = 31; j >= 0; j--) {
            int a = s[j] & 255;
            int b = GROUP_ORDER[j] & 255;
            if (a != b) {
                return a < b;
            }
        }
        return false;
    }

    static boolean verify(final byte[] message, final byte[] signature, final byte[] publicKey) throws GeneralSecurityException {
        if (signature.length != 64) {
            return false;
        }
        byte[] s = Arrays.copyOfRange(signature, 32, 64);
        if (!isSmallerThanGroupOrder(s)) {
            return false;
        }
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        digest.update(signature, 0, 32);
        digest.update(publicKey);
        digest.update(message);
        byte[] h = digest.digest();
        reduce(h);
        XYZT negPublicKey = XYZT.fromBytesNegateVarTime(publicKey);
        XYZ xyz = doubleScalarMultVarTime(h, negPublicKey, s);
        byte[] expectedR = xyz.toBytes();
        for (int i = 0; i < 32; i++) {
            if (expectedR[i] != signature[i]) {
                return false;
            }
        }
        return true;
    }

    private Ed25519() {
    }
}
