package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzim extends zzbfc<zzim> {
    private Integer zzamf = null;
    private Integer zzanx = null;

    public zzim() {
        this.zzebk = null;
        this.zzebt = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0073, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzo */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzim zza(com.google.android.gms.internal.ads.zzbez r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzabk()
            if (r0 == 0) goto L_0x007b
            r1 = 8
            r2 = 2
            if (r0 == r1) goto L_0x0048
            r1 = 16
            if (r0 == r1) goto L_0x0016
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x0016:
            int r1 = r7.getPosition()
            int r3 = r7.zzacc()     // Catch:{ IllegalArgumentException -> 0x0074 }
            if (r3 < 0) goto L_0x0023
            if (r3 > r2) goto L_0x0023
            goto L_0x0028
        L_0x0023:
            r2 = 4
            if (r3 < r2) goto L_0x002f
            if (r3 > r2) goto L_0x002f
        L_0x0028:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r6.zzanx = r2     // Catch:{ IllegalArgumentException -> 0x0074 }
            goto L_0x0000
        L_0x002f:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0074 }
            r4 = 51
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = " is not a valid enum CellularNetworkType"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0074 }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x0074 }
        L_0x0048:
            int r1 = r7.getPosition()
            int r3 = r7.zzacc()     // Catch:{ IllegalArgumentException -> 0x0074 }
            if (r3 < 0) goto L_0x005b
            if (r3 > r2) goto L_0x005b
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r6.zzamf = r2     // Catch:{ IllegalArgumentException -> 0x0074 }
            goto L_0x0000
        L_0x005b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0074 }
            r4 = 43
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = " is not a valid enum NetworkType"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0074 }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x0074 }
        L_0x0074:
            r7.zzdc(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x007b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzim.zza(com.google.android.gms.internal.ads.zzbez):com.google.android.gms.internal.ads.zzim");
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        Integer num = this.zzamf;
        if (num != null) {
            zzbfa.zzm(1, num.intValue());
        }
        Integer num2 = this.zzanx;
        if (num2 != null) {
            zzbfa.zzm(2, num2.intValue());
        }
        super.zza(zzbfa);
    }

    /* access modifiers changed from: protected */
    public final int zzr() {
        int zzr = super.zzr();
        Integer num = this.zzamf;
        if (num != null) {
            zzr += zzbfa.zzq(1, num.intValue());
        }
        Integer num2 = this.zzanx;
        return num2 != null ? zzr + zzbfa.zzq(2, num2.intValue()) : zzr;
    }
}
