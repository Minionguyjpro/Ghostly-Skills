package com.google.android.gms.internal.ads;

final class zzapb implements Runnable {
    private final /* synthetic */ zzaov zzcxf;
    private final /* synthetic */ int zzcxi;
    private final /* synthetic */ int zzcxj;

    zzapb(zzaov zzaov, int i, int i2) {
        this.zzcxf = zzaov;
        this.zzcxi = i;
        this.zzcxj = i2;
    }

    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.zzf(this.zzcxi, this.zzcxj);
        }
    }
}
