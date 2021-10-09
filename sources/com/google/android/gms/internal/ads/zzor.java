package com.google.android.gms.internal.ads;

final class zzor implements Runnable {
    private final /* synthetic */ zzoq zzbin;

    zzor(zzoq zzoq) {
        this.zzbin = zzoq;
    }

    public final void run() {
        if (this.zzbin.zzbij != null) {
            this.zzbin.zzbij.zzkq();
            this.zzbin.zzbij.zzkp();
            this.zzbin.zzbij.zzcs();
        }
        zzoz unused = this.zzbin.zzbij = null;
    }
}
