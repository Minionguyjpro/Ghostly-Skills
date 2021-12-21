package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgz extends zzgq {
    private MessageDigest zzaje;
    private final int zzajh;
    private final int zzaji;

    public zzgz(int i) {
        int i2 = i / 8;
        this.zzajh = i % 8 > 0 ? i2 + 1 : i2;
        this.zzaji = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzx(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.mLock
            monitor-enter(r0)
            java.security.MessageDigest r1 = r9.zzhg()     // Catch:{ all -> 0x0067 }
            r9.zzaje = r1     // Catch:{ all -> 0x0067 }
            r2 = 0
            if (r1 != 0) goto L_0x0010
            byte[] r10 = new byte[r2]     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return r10
        L_0x0010:
            r1.reset()     // Catch:{ all -> 0x0067 }
            java.security.MessageDigest r1 = r9.zzaje     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "UTF-8"
            java.nio.charset.Charset r3 = java.nio.charset.Charset.forName(r3)     // Catch:{ all -> 0x0067 }
            byte[] r10 = r10.getBytes(r3)     // Catch:{ all -> 0x0067 }
            r1.update(r10)     // Catch:{ all -> 0x0067 }
            java.security.MessageDigest r10 = r9.zzaje     // Catch:{ all -> 0x0067 }
            byte[] r10 = r10.digest()     // Catch:{ all -> 0x0067 }
            int r1 = r10.length     // Catch:{ all -> 0x0067 }
            int r3 = r9.zzajh     // Catch:{ all -> 0x0067 }
            if (r1 <= r3) goto L_0x0030
            int r1 = r9.zzajh     // Catch:{ all -> 0x0067 }
            goto L_0x0031
        L_0x0030:
            int r1 = r10.length     // Catch:{ all -> 0x0067 }
        L_0x0031:
            byte[] r3 = new byte[r1]     // Catch:{ all -> 0x0067 }
            java.lang.System.arraycopy(r10, r2, r3, r2, r1)     // Catch:{ all -> 0x0067 }
            int r10 = r9.zzaji     // Catch:{ all -> 0x0067 }
            r4 = 8
            int r10 = r10 % r4
            if (r10 <= 0) goto L_0x0065
            r5 = 0
        L_0x003f:
            if (r2 >= r1) goto L_0x004d
            if (r2 <= 0) goto L_0x0044
            long r5 = r5 << r4
        L_0x0044:
            byte r10 = r3[r2]     // Catch:{ all -> 0x0067 }
            r10 = r10 & 255(0xff, float:3.57E-43)
            long r7 = (long) r10     // Catch:{ all -> 0x0067 }
            long r5 = r5 + r7
            int r2 = r2 + 1
            goto L_0x003f
        L_0x004d:
            int r10 = r9.zzaji     // Catch:{ all -> 0x0067 }
            int r10 = r10 % r4
            int r10 = 8 - r10
            long r1 = r5 >>> r10
            int r10 = r9.zzajh     // Catch:{ all -> 0x0067 }
            int r10 = r10 + -1
        L_0x0058:
            if (r10 < 0) goto L_0x0065
            r5 = 255(0xff, double:1.26E-321)
            long r5 = r5 & r1
            int r6 = (int) r5     // Catch:{ all -> 0x0067 }
            byte r5 = (byte) r6     // Catch:{ all -> 0x0067 }
            r3[r10] = r5     // Catch:{ all -> 0x0067 }
            long r1 = r1 >>> r4
            int r10 = r10 + -1
            goto L_0x0058
        L_0x0065:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return r3
        L_0x0067:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            goto L_0x006b
        L_0x006a:
            throw r10
        L_0x006b:
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgz.zzx(java.lang.String):byte[]");
    }
}
