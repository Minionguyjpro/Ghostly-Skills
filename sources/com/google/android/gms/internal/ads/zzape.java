package com.google.android.gms.internal.ads;

final class zzape implements Runnable {
    private final /* synthetic */ zzaov zzcxf;

    zzape(zzaov zzaov) {
        this.zzcxf = zzaov;
    }

    public final void run() {
        if (this.zzcxf.zzcxd != null) {
            this.zzcxf.zzcxd.onPaused();
        }
    }
}
