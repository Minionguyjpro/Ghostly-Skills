package com.google.android.gms.internal.ads;

final class zzapc implements Runnable {
    private final /* synthetic */ zzaov zzcxf;

    zzapc(zzaov zzaov) {
        this.zzcxf = zzaov;
    }

    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.onPaused();
            this.zzcxf.zzcxd.zzsy();
        }
    }
}
