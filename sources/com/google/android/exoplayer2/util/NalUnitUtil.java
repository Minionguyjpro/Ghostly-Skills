package com.google.android.exoplayer2.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    private static int[] scratchEscapePositions = new int[10];
    private static final Object scratchEscapePositionsLock = new Object();

    public static final class SpsData {
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z, boolean z2, int i7, int i8, int i9, boolean z3) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.width = i5;
            this.height = i6;
            this.pixelWidthAspectRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i7;
            this.picOrderCountType = i8;
            this.picOrderCntLsbLength = i9;
            this.deltaPicOrderAlwaysZeroFlag = z3;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        int i2;
        synchronized (scratchEscapePositionsLock) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                try {
                    i3 = findNextUnescapeIndex(bArr, i3, i);
                    if (i3 < i) {
                        if (scratchEscapePositions.length <= i4) {
                            scratchEscapePositions = Arrays.copyOf(scratchEscapePositions, scratchEscapePositions.length * 2);
                        }
                        scratchEscapePositions[i4] = i3;
                        i3 += 3;
                        i4++;
                    }
                } finally {
                }
            }
            i2 = i - i4;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = scratchEscapePositions[i7] - i6;
                System.arraycopy(bArr, i6, bArr, i5, i8);
                int i9 = i5 + i8;
                int i10 = i9 + 1;
                bArr[i9] = 0;
                i5 = i10 + 1;
                bArr[i10] = 0;
                i6 += i8 + 3;
            }
            System.arraycopy(bArr, i6, bArr, i5, i2 - i5);
        }
        return i2;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                byte b = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (b == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (b == 0) {
                    i2++;
                }
                if (b != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean isNalUnitSei(String str, byte b) {
        if ("video/avc".equals(str) && (b & 31) == 6) {
            return true;
        }
        if (!"video/hevc".equals(str) || ((b & 126) >> 1) != 39) {
            return false;
        }
        return true;
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0149  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.util.NalUnitUtil.SpsData parseSpsNalUnit(byte[] r21, int r22, int r23) {
        /*
            com.google.android.exoplayer2.util.ParsableNalUnitBitArray r0 = new com.google.android.exoplayer2.util.ParsableNalUnitBitArray
            r1 = r21
            r2 = r22
            r3 = r23
            r0.<init>(r1, r2, r3)
            r1 = 8
            r0.skipBits(r1)
            int r3 = r0.readBits(r1)
            int r4 = r0.readBits(r1)
            int r5 = r0.readBits(r1)
            int r6 = r0.readUnsignedExpGolombCodedInt()
            r2 = 3
            r9 = 1
            r10 = 100
            if (r3 == r10) goto L_0x004e
            r10 = 110(0x6e, float:1.54E-43)
            if (r3 == r10) goto L_0x004e
            r10 = 122(0x7a, float:1.71E-43)
            if (r3 == r10) goto L_0x004e
            r10 = 244(0xf4, float:3.42E-43)
            if (r3 == r10) goto L_0x004e
            r10 = 44
            if (r3 == r10) goto L_0x004e
            r10 = 83
            if (r3 == r10) goto L_0x004e
            r10 = 86
            if (r3 == r10) goto L_0x004e
            r10 = 118(0x76, float:1.65E-43)
            if (r3 == r10) goto L_0x004e
            r10 = 128(0x80, float:1.794E-43)
            if (r3 == r10) goto L_0x004e
            r10 = 138(0x8a, float:1.93E-43)
            if (r3 != r10) goto L_0x004b
            goto L_0x004e
        L_0x004b:
            r10 = 1
            r11 = 0
            goto L_0x0087
        L_0x004e:
            int r10 = r0.readUnsignedExpGolombCodedInt()
            if (r10 != r2) goto L_0x0059
            boolean r11 = r0.readBit()
            goto L_0x005a
        L_0x0059:
            r11 = 0
        L_0x005a:
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            r0.skipBit()
            boolean r12 = r0.readBit()
            if (r12 == 0) goto L_0x0087
            if (r10 == r2) goto L_0x006e
            r12 = 8
            goto L_0x0070
        L_0x006e:
            r12 = 12
        L_0x0070:
            r13 = 0
        L_0x0071:
            if (r13 >= r12) goto L_0x0087
            boolean r14 = r0.readBit()
            if (r14 == 0) goto L_0x0084
            r14 = 6
            if (r13 >= r14) goto L_0x007f
            r14 = 16
            goto L_0x0081
        L_0x007f:
            r14 = 64
        L_0x0081:
            skipScalingList(r0, r14)
        L_0x0084:
            int r13 = r13 + 1
            goto L_0x0071
        L_0x0087:
            int r12 = r0.readUnsignedExpGolombCodedInt()
            int r12 = r12 + 4
            int r13 = r0.readUnsignedExpGolombCodedInt()
            if (r13 != 0) goto L_0x009a
            int r14 = r0.readUnsignedExpGolombCodedInt()
            int r14 = r14 + 4
            goto L_0x00bb
        L_0x009a:
            if (r13 != r9) goto L_0x00ba
            boolean r14 = r0.readBit()
            r0.readSignedExpGolombCodedInt()
            r0.readSignedExpGolombCodedInt()
            int r15 = r0.readUnsignedExpGolombCodedInt()
            long r1 = (long) r15
            r15 = 0
        L_0x00ac:
            long r7 = (long) r15
            int r17 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r17 >= 0) goto L_0x00b7
            r0.readUnsignedExpGolombCodedInt()
            int r15 = r15 + 1
            goto L_0x00ac
        L_0x00b7:
            r15 = r14
            r14 = 0
            goto L_0x00bc
        L_0x00ba:
            r14 = 0
        L_0x00bb:
            r15 = 0
        L_0x00bc:
            r0.readUnsignedExpGolombCodedInt()
            r0.skipBit()
            int r1 = r0.readUnsignedExpGolombCodedInt()
            int r1 = r1 + r9
            int r2 = r0.readUnsignedExpGolombCodedInt()
            int r2 = r2 + r9
            boolean r16 = r0.readBit()
            int r7 = 2 - r16
            int r7 = r7 * r2
            if (r16 != 0) goto L_0x00d9
            r0.skipBit()
        L_0x00d9:
            r0.skipBit()
            r2 = 16
            int r1 = r1 * 16
            int r7 = r7 * 16
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x011c
            int r2 = r0.readUnsignedExpGolombCodedInt()
            int r8 = r0.readUnsignedExpGolombCodedInt()
            int r17 = r0.readUnsignedExpGolombCodedInt()
            int r18 = r0.readUnsignedExpGolombCodedInt()
            if (r10 != 0) goto L_0x00fd
            int r10 = 2 - r16
            goto L_0x0112
        L_0x00fd:
            r19 = 2
            r9 = 3
            if (r10 != r9) goto L_0x0106
            r9 = 1
            r20 = 1
            goto L_0x0109
        L_0x0106:
            r9 = 1
            r20 = 2
        L_0x0109:
            if (r10 != r9) goto L_0x010c
            r9 = 2
        L_0x010c:
            int r10 = 2 - r16
            int r10 = r10 * r9
            r9 = r20
        L_0x0112:
            int r2 = r2 + r8
            int r2 = r2 * r9
            int r1 = r1 - r2
            int r17 = r17 + r18
            int r17 = r17 * r10
            int r7 = r7 - r17
        L_0x011c:
            r8 = r7
            r7 = r1
            r1 = 1065353216(0x3f800000, float:1.0)
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0168
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0168
            r2 = 8
            int r2 = r0.readBits(r2)
            r9 = 255(0xff, float:3.57E-43)
            if (r2 != r9) goto L_0x0149
            r9 = 16
            int r2 = r0.readBits(r9)
            int r0 = r0.readBits(r9)
            if (r2 == 0) goto L_0x0147
            if (r0 == 0) goto L_0x0147
            float r1 = (float) r2
            float r0 = (float) r0
            float r1 = r1 / r0
        L_0x0147:
            r9 = r1
            goto L_0x016a
        L_0x0149:
            float[] r0 = ASPECT_RATIO_IDC_VALUES
            int r9 = r0.length
            if (r2 >= r9) goto L_0x0152
            r0 = r0[r2]
            r9 = r0
            goto L_0x016a
        L_0x0152:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r9 = "Unexpected aspect_ratio_idc value: "
            r0.append(r9)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "NalUnitUtil"
            com.google.android.exoplayer2.util.Log.w(r2, r0)
        L_0x0168:
            r9 = 1065353216(0x3f800000, float:1.0)
        L_0x016a:
            com.google.android.exoplayer2.util.NalUnitUtil$SpsData r0 = new com.google.android.exoplayer2.util.NalUnitUtil$SpsData
            r2 = r0
            r10 = r11
            r11 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.parseSpsNalUnit(byte[], int, int):com.google.android.exoplayer2.util.NalUnitUtil$SpsData");
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        parsableNalUnitBitArray.skipBits(8);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0097, code lost:
        r8 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int findNalUnit(byte[] r7, int r8, int r9, boolean[] r10) {
        /*
            int r0 = r9 - r8
            r1 = 0
            r2 = 1
            if (r0 < 0) goto L_0x0008
            r3 = 1
            goto L_0x0009
        L_0x0008:
            r3 = 0
        L_0x0009:
            com.google.android.exoplayer2.util.Assertions.checkState(r3)
            if (r0 != 0) goto L_0x000f
            return r9
        L_0x000f:
            r3 = 2
            if (r10 == 0) goto L_0x0040
            boolean r4 = r10[r1]
            if (r4 == 0) goto L_0x001c
            clearPrefixFlags(r10)
            int r8 = r8 + -3
            return r8
        L_0x001c:
            if (r0 <= r2) goto L_0x002b
            boolean r4 = r10[r2]
            if (r4 == 0) goto L_0x002b
            byte r4 = r7[r8]
            if (r4 != r2) goto L_0x002b
            clearPrefixFlags(r10)
            int r8 = r8 - r3
            return r8
        L_0x002b:
            if (r0 <= r3) goto L_0x0040
            boolean r4 = r10[r3]
            if (r4 == 0) goto L_0x0040
            byte r4 = r7[r8]
            if (r4 != 0) goto L_0x0040
            int r4 = r8 + 1
            byte r4 = r7[r4]
            if (r4 != r2) goto L_0x0040
            clearPrefixFlags(r10)
            int r8 = r8 - r2
            return r8
        L_0x0040:
            int r4 = r9 + -1
            int r8 = r8 + r3
        L_0x0043:
            if (r8 >= r4) goto L_0x0067
            byte r5 = r7[r8]
            r5 = r5 & 254(0xfe, float:3.56E-43)
            if (r5 == 0) goto L_0x004c
            goto L_0x0064
        L_0x004c:
            int r5 = r8 + -2
            byte r6 = r7[r5]
            if (r6 != 0) goto L_0x0062
            int r6 = r8 + -1
            byte r6 = r7[r6]
            if (r6 != 0) goto L_0x0062
            byte r6 = r7[r8]
            if (r6 != r2) goto L_0x0062
            if (r10 == 0) goto L_0x0061
            clearPrefixFlags(r10)
        L_0x0061:
            return r5
        L_0x0062:
            int r8 = r8 + -2
        L_0x0064:
            int r8 = r8 + 3
            goto L_0x0043
        L_0x0067:
            if (r10 == 0) goto L_0x00bb
            if (r0 <= r3) goto L_0x007e
            int r8 = r9 + -3
            byte r8 = r7[r8]
            if (r8 != 0) goto L_0x007c
            int r8 = r9 + -2
            byte r8 = r7[r8]
            if (r8 != 0) goto L_0x007c
            byte r8 = r7[r4]
            if (r8 != r2) goto L_0x007c
            goto L_0x0097
        L_0x007c:
            r8 = 0
            goto L_0x0098
        L_0x007e:
            if (r0 != r3) goto L_0x008f
            boolean r8 = r10[r3]
            if (r8 == 0) goto L_0x007c
            int r8 = r9 + -2
            byte r8 = r7[r8]
            if (r8 != 0) goto L_0x007c
            byte r8 = r7[r4]
            if (r8 != r2) goto L_0x007c
            goto L_0x0097
        L_0x008f:
            boolean r8 = r10[r2]
            if (r8 == 0) goto L_0x007c
            byte r8 = r7[r4]
            if (r8 != r2) goto L_0x007c
        L_0x0097:
            r8 = 1
        L_0x0098:
            r10[r1] = r8
            if (r0 <= r2) goto L_0x00a7
            int r8 = r9 + -2
            byte r8 = r7[r8]
            if (r8 != 0) goto L_0x00b1
            byte r8 = r7[r4]
            if (r8 != 0) goto L_0x00b1
            goto L_0x00af
        L_0x00a7:
            boolean r8 = r10[r3]
            if (r8 == 0) goto L_0x00b1
            byte r8 = r7[r4]
            if (r8 != 0) goto L_0x00b1
        L_0x00af:
            r8 = 1
            goto L_0x00b2
        L_0x00b1:
            r8 = 0
        L_0x00b2:
            r10[r2] = r8
            byte r7 = r7[r4]
            if (r7 != 0) goto L_0x00b9
            r1 = 1
        L_0x00b9:
            r10[r3] = r1
        L_0x00bb:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.findNalUnit(byte[], int, int, boolean[]):int");
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int i2 = 8;
        int i3 = 8;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 != 0) {
                i2 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i3) + 256) % 256;
            }
            if (i2 != 0) {
                i3 = i2;
            }
        }
    }
}
