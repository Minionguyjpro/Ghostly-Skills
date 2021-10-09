package com.google.android.gms.internal.ads;

final /* synthetic */ class zzaow implements Runnable {
    private final int zzcsi;
    private final zzaov zzcxe;

    zzaow(zzaov zzaov, int i) {
        this.zzcxe = zzaov;
        this.zzcsi = i;
    }

    public final void run() {
        this.zzcxe.zzah(this.zzcsi);
    }
}
