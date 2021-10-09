package com.google.android.gms.internal.ads;

public class zzbcb {
    private static final zzbbb zzdph = zzbbb.zzacr();
    private zzbah zzdvk;
    private volatile zzbcu zzdvl;
    private volatile zzbah zzdvm;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzbcu zzk(com.google.android.gms.internal.ads.zzbcu r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.ads.zzbcu r0 = r1.zzdvl
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzbcu r0 = r1.zzdvl     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.zzdvl = r2     // Catch:{ zzbbu -> 0x0012 }
            com.google.android.gms.internal.ads.zzbah r0 = com.google.android.gms.internal.ads.zzbah.zzdpq     // Catch:{ zzbbu -> 0x0012 }
            r1.zzdvm = r0     // Catch:{ zzbbu -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.zzdvl = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.ads.zzbah r2 = com.google.android.gms.internal.ads.zzbah.zzdpq     // Catch:{ all -> 0x0019 }
            r1.zzdvm = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.ads.zzbcu r2 = r1.zzdvl
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcb.zzk(com.google.android.gms.internal.ads.zzbcu):com.google.android.gms.internal.ads.zzbcu");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbcb)) {
            return false;
        }
        zzbcb zzbcb = (zzbcb) obj;
        zzbcu zzbcu = this.zzdvl;
        zzbcu zzbcu2 = zzbcb.zzdvl;
        return (zzbcu == null && zzbcu2 == null) ? zzaav().equals(zzbcb.zzaav()) : (zzbcu == null || zzbcu2 == null) ? zzbcu != null ? zzbcu.equals(zzbcb.zzk(zzbcu.zzadg())) : zzk(zzbcu2.zzadg()).equals(zzbcu2) : zzbcu.equals(zzbcu2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzbah zzaav() {
        if (this.zzdvm != null) {
            return this.zzdvm;
        }
        synchronized (this) {
            if (this.zzdvm != null) {
                zzbah zzbah = this.zzdvm;
                return zzbah;
            }
            this.zzdvm = this.zzdvl == null ? zzbah.zzdpq : this.zzdvl.zzaav();
            zzbah zzbah2 = this.zzdvm;
            return zzbah2;
        }
    }

    public final int zzacw() {
        if (this.zzdvm != null) {
            return this.zzdvm.size();
        }
        if (this.zzdvl != null) {
            return this.zzdvl.zzacw();
        }
        return 0;
    }

    public final zzbcu zzl(zzbcu zzbcu) {
        zzbcu zzbcu2 = this.zzdvl;
        this.zzdvk = null;
        this.zzdvm = null;
        this.zzdvl = zzbcu;
        return zzbcu2;
    }
}
