package com.startapp.a.a.e;

/* compiled from: StartAppSDK */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final c f14a = new c();

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0052 A[SYNTHETIC, Splitter:B:30:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(com.startapp.a.a.a.c r12) {
        /*
            r11 = this;
            int r0 = r12.b()
            int r1 = r12.c()
            r2 = 0
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0047 }
            r3.<init>()     // Catch:{ Exception -> 0x0047 }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            r4 = 0
            r5 = 0
        L_0x0015:
            if (r5 >= r1) goto L_0x0031
            long[] r6 = r12.a((int) r5)     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            r7 = 0
        L_0x001c:
            r8 = 4096(0x1000, float:5.74E-42)
            if (r7 >= r8) goto L_0x002e
            int r8 = r0 + -1
            if (r0 <= 0) goto L_0x002d
            r9 = r6[r7]     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            r2.writeLong(r9)     // Catch:{ Exception -> 0x0042, all -> 0x003f }
            int r7 = r7 + 1
            r0 = r8
            goto L_0x001c
        L_0x002d:
            r0 = r8
        L_0x002e:
            int r5 = r5 + 1
            goto L_0x0015
        L_0x0031:
            r3.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            com.startapp.a.a.e.c r12 = r11.f14a
            byte[] r0 = r3.toByteArray()
            java.lang.String r12 = r12.a((byte[]) r0)
            return r12
        L_0x003f:
            r12 = move-exception
            r2 = r3
            goto L_0x0050
        L_0x0042:
            r12 = move-exception
            r2 = r3
            goto L_0x0048
        L_0x0045:
            r12 = move-exception
            goto L_0x0050
        L_0x0047:
            r12 = move-exception
        L_0x0048:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "problem serializing bitSet"
            r0.<init>(r1, r12)     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0050:
            if (r2 == 0) goto L_0x0055
            r2.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0055:
            goto L_0x0057
        L_0x0056:
            throw r12
        L_0x0057:
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.a.a.e.b.a(com.startapp.a.a.a.c):java.lang.String");
    }
}
