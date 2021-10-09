package com.google.android.gms.internal.ads;

final class zzapa implements Runnable {
    private final /* synthetic */ zzaov zzcxf;

    zzapa(zzaov zzaov) {
        this.zzcxf = zzaov;
    }

    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.zzsu();
        }
    }
}
