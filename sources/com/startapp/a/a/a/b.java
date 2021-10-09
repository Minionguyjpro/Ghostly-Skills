package com.startapp.a.a.a;

/* compiled from: StartAppSDK */
public class b {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x00d0, code lost:
        r2 = r2 ^ (((long) r0.get(((r19 + r1) - r4) + 3)) << 24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00dc, code lost:
        r2 = r2 ^ (((long) r0.get(((r19 + r1) - r4) + 2)) << 16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00e8, code lost:
        r2 = r2 ^ (((long) r0.get(((r19 + r1) - r4) + 1)) << 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00f4, code lost:
        r4 = -4132994306676758123L;
        r2 = (((long) r0.get((r19 + r1) - r4)) ^ r2) * -4132994306676758123L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0104, code lost:
        r2 = (r2 ^ (r2 >>> 47)) * r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x010e, code lost:
        return (r2 >>> 47) ^ r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x00b6, code lost:
        r2 = r2 ^ (((long) r0.get(((r19 + r1) - r4) + 5)) << 40);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x00c4, code lost:
        r2 = r2 ^ (((long) r0.get(((r19 + r1) - r4) + 4)) << 32);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long a(java.nio.ByteBuffer r18, int r19, int r20, long r21) {
        /*
            r0 = r18
            r1 = r20
            r2 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r2 = r21 & r2
            long r4 = (long) r1
            r6 = -4132994306676758123(0xc6a4a7935bd1e995, double:-2.0946245025644615E32)
            long r4 = r4 * r6
            long r2 = r2 ^ r4
            int r4 = r1 >> 3
            r5 = 0
        L_0x0017:
            r10 = 32
            r11 = 24
            r12 = 16
            r13 = 8
            if (r5 >= r4) goto L_0x009d
            int r15 = r5 << 3
            int r15 = r19 + r15
            int r14 = r15 + 0
            byte r14 = r0.get(r14)
            long r6 = (long) r14
            r16 = 255(0xff, double:1.26E-321)
            long r6 = r6 & r16
            int r14 = r15 + 1
            byte r14 = r0.get(r14)
            long r8 = (long) r14
            long r8 = r8 & r16
            long r8 = r8 << r13
            long r6 = r6 + r8
            int r8 = r15 + 2
            byte r8 = r0.get(r8)
            long r8 = (long) r8
            long r8 = r8 & r16
            long r8 = r8 << r12
            long r6 = r6 + r8
            int r8 = r15 + 3
            byte r8 = r0.get(r8)
            long r8 = (long) r8
            long r8 = r8 & r16
            long r8 = r8 << r11
            long r6 = r6 + r8
            int r8 = r15 + 4
            byte r8 = r0.get(r8)
            long r8 = (long) r8
            long r8 = r8 & r16
            long r8 = r8 << r10
            long r6 = r6 + r8
            int r8 = r15 + 5
            byte r8 = r0.get(r8)
            long r8 = (long) r8
            long r8 = r8 & r16
            r10 = 40
            long r8 = r8 << r10
            long r6 = r6 + r8
            int r8 = r15 + 6
            byte r8 = r0.get(r8)
            long r8 = (long) r8
            long r8 = r8 & r16
            r10 = 48
            long r8 = r8 << r10
            long r6 = r6 + r8
            int r15 = r15 + 7
            byte r8 = r0.get(r15)
            long r8 = (long) r8
            long r8 = r8 & r16
            r10 = 56
            long r8 = r8 << r10
            long r6 = r6 + r8
            r8 = -4132994306676758123(0xc6a4a7935bd1e995, double:-2.0946245025644615E32)
            long r6 = r6 * r8
            r10 = 47
            long r10 = r6 >>> r10
            long r6 = r6 ^ r10
            long r6 = r6 * r8
            long r2 = r2 ^ r6
            long r2 = r2 * r8
            int r5 = r5 + 1
            r6 = -4132994306676758123(0xc6a4a7935bd1e995, double:-2.0946245025644615E32)
            goto L_0x0017
        L_0x009d:
            r4 = r1 & 7
            switch(r4) {
                case 1: goto L_0x00f4;
                case 2: goto L_0x00e8;
                case 3: goto L_0x00dc;
                case 4: goto L_0x00d0;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00b6;
                case 7: goto L_0x00a8;
                default: goto L_0x00a2;
            }
        L_0x00a2:
            r4 = -4132994306676758123(0xc6a4a7935bd1e995, double:-2.0946245025644615E32)
            goto L_0x0104
        L_0x00a8:
            int r5 = r19 + r1
            int r5 = r5 - r4
            int r5 = r5 + 6
            byte r5 = r0.get(r5)
            long r5 = (long) r5
            r7 = 48
            long r5 = r5 << r7
            long r2 = r2 ^ r5
        L_0x00b6:
            int r5 = r19 + r1
            int r5 = r5 - r4
            int r5 = r5 + 5
            byte r5 = r0.get(r5)
            long r5 = (long) r5
            r7 = 40
            long r5 = r5 << r7
            long r2 = r2 ^ r5
        L_0x00c4:
            int r5 = r19 + r1
            int r5 = r5 - r4
            int r5 = r5 + 4
            byte r5 = r0.get(r5)
            long r5 = (long) r5
            long r5 = r5 << r10
            long r2 = r2 ^ r5
        L_0x00d0:
            int r5 = r19 + r1
            int r5 = r5 - r4
            int r5 = r5 + 3
            byte r5 = r0.get(r5)
            long r5 = (long) r5
            long r5 = r5 << r11
            long r2 = r2 ^ r5
        L_0x00dc:
            int r5 = r19 + r1
            int r5 = r5 - r4
            int r5 = r5 + 2
            byte r5 = r0.get(r5)
            long r5 = (long) r5
            long r5 = r5 << r12
            long r2 = r2 ^ r5
        L_0x00e8:
            int r5 = r19 + r1
            int r5 = r5 - r4
            int r5 = r5 + 1
            byte r5 = r0.get(r5)
            long r5 = (long) r5
            long r5 = r5 << r13
            long r2 = r2 ^ r5
        L_0x00f4:
            int r1 = r19 + r1
            int r1 = r1 - r4
            byte r0 = r0.get(r1)
            long r0 = (long) r0
            long r0 = r0 ^ r2
            r4 = -4132994306676758123(0xc6a4a7935bd1e995, double:-2.0946245025644615E32)
            long r2 = r0 * r4
        L_0x0104:
            r0 = 47
            long r6 = r2 >>> r0
            long r2 = r2 ^ r6
            long r2 = r2 * r4
            long r0 = r2 >>> r0
            long r0 = r0 ^ r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.a.a.a.b.a(java.nio.ByteBuffer, int, int, long):long");
    }
}
