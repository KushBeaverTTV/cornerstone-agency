package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: classes15.dex */
public class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    private static long load32(byte[] in, int idx) {
        return ((long) ((in[idx] & UByte.MAX_VALUE) | ((in[idx + 1] & UByte.MAX_VALUE) << 8) | ((in[idx + 2] & UByte.MAX_VALUE) << 16) | ((in[idx + 3] & UByte.MAX_VALUE) << 24))) & 4294967295L;
    }

    private static long load26(byte[] in, int idx, int shift) {
        return (load32(in, idx) >> shift) & 67108863;
    }

    private static void toByteArray(byte[] output, long num, int idx) {
        int i = 0;
        while (i < 4) {
            output[idx + i] = (byte) (255 & num);
            i++;
            num >>= 8;
        }
    }

    private static void copyBlockSize(byte[] output, byte[] in, int idx) {
        int copyCount = Math.min(16, in.length - idx);
        System.arraycopy(in, idx, output, 0, copyCount);
        output[copyCount] = 1;
        if (copyCount != 16) {
            Arrays.fill(output, copyCount + 1, output.length, (byte) 0);
        }
    }

    public static byte[] computeMac(final byte[] key, byte[] data) {
        if (key.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long h0 = 0;
        long h1 = 0;
        long h2 = 0;
        long h3 = 0;
        long h4 = 0;
        long d0 = load26(key, 0, 0) & 67108863;
        long r1 = load26(key, 3, 2) & 67108611;
        long r2 = load26(key, 6, 4) & 67092735;
        long r3 = load26(key, 9, 6) & 66076671;
        long r4 = load26(key, 12, 8) & 1048575;
        long s1 = r1 * 5;
        long s2 = r2 * 5;
        long s3 = r3 * 5;
        long s4 = r4 * 5;
        byte[] buf = new byte[17];
        int i = 0;
        while (true) {
            long r0 = d0;
            if (i < data.length) {
                copyBlockSize(buf, data, i);
                long h5 = h0 + load26(buf, 0, 0);
                long h6 = h1 + load26(buf, 3, 2);
                long h7 = h2 + load26(buf, 6, 4);
                long h8 = h3 + load26(buf, 9, 6);
                long h9 = h4 + (load26(buf, 12, 8) | ((long) (buf[16] << 24)));
                long d1 = (h5 * r0) + (h6 * s4) + (h7 * s3) + (h8 * s2) + (h9 * s1);
                long d2 = (h5 * r1) + (h6 * r0) + (h7 * s4) + (h8 * s3) + (h9 * s2);
                long d3 = (h5 * r2) + (h6 * r1) + (h7 * r0) + (h8 * s4) + (h9 * s3);
                long d4 = (h5 * r3) + (h6 * r2) + (h7 * r1) + (h8 * r0) + (h9 * s4);
                long d5 = (h5 * r4) + (h6 * r3) + (h7 * r2) + (h8 * r1) + (h9 * r0);
                long c = d1 >> 26;
                long h10 = d1 & 67108863;
                long d6 = d2 + c;
                long c2 = d6 >> 26;
                long h11 = d6 & 67108863;
                long d7 = d3 + c2;
                long c3 = d7 >> 26;
                h2 = d7 & 67108863;
                long d8 = d4 + c3;
                long c4 = d8 >> 26;
                h3 = d8 & 67108863;
                long d9 = d5 + c4;
                long c5 = d9 >> 26;
                h4 = d9 & 67108863;
                long h12 = h10 + (c5 * 5);
                long c6 = h12 >> 26;
                h0 = h12 & 67108863;
                h1 = h11 + c6;
                i += 16;
                d0 = r0;
            } else {
                long c7 = h1 >> 26;
                long h13 = h2 + c7;
                long c8 = h13 >> 26;
                long h14 = h13 & 67108863;
                long h15 = h3 + c8;
                long c9 = h15 >> 26;
                long h16 = h15 & 67108863;
                long h17 = h4 + c9;
                long c10 = h17 >> 26;
                long h18 = h17 & 67108863;
                long h19 = h0 + (c10 * 5);
                long c11 = h19 >> 26;
                long h20 = h19 & 67108863;
                long h21 = (h1 & 67108863) + c11;
                long g0 = h20 + 5;
                long c12 = g0 >> 26;
                long g1 = h21 + c12;
                long c13 = g1 >> 26;
                long g2 = h14 + c13;
                long c14 = g2 >> 26;
                long g3 = h16 + c14;
                long c15 = g3 >> 26;
                long g4 = g3 & 67108863;
                long g5 = (h18 + c15) - 67108864;
                long mask = g5 >> 63;
                long h22 = h20 & mask;
                long h23 = h21 & mask;
                long h24 = h14 & mask;
                long h25 = h16 & mask;
                long h26 = h18 & mask;
                long mask2 = ~mask;
                long h27 = h23 | (g1 & 67108863 & mask2);
                long h28 = h24 | (g2 & 67108863 & mask2);
                long h29 = h25 | (g4 & mask2);
                long h30 = ((h27 << 26) | h22 | (g0 & 67108863 & mask2)) & 4294967295L;
                long h31 = ((h27 >> 6) | (h28 << 20)) & 4294967295L;
                long h32 = ((h28 >> 12) | (h29 << 14)) & 4294967295L;
                long h33 = ((h29 >> 18) | ((h26 | (g5 & mask2)) << 8)) & 4294967295L;
                long c16 = h30 + load32(key, 16);
                long h34 = c16 & 4294967295L;
                long c17 = load32(key, 20) + h31 + (c16 >> 32);
                long h35 = c17 & 4294967295L;
                long c18 = load32(key, 24) + h32 + (c17 >> 32);
                long h36 = c18 & 4294967295L;
                long c19 = load32(key, 28) + h33 + (c18 >> 32);
                byte[] mac = new byte[16];
                toByteArray(mac, h34, 0);
                toByteArray(mac, h35, 4);
                toByteArray(mac, h36, 8);
                toByteArray(mac, c19 & 4294967295L, 12);
                return mac;
            }
        }
    }

    public static void verifyMac(final byte[] key, byte[] data, byte[] mac) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(key, data), mac)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
