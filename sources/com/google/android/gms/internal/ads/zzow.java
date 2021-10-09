package com.google.android.gms.internal.ads;

final class zzow implements Runnable {
    private final /* synthetic */ zzov zzbis;

    zzow(zzov zzov) {
        this.zzbis = zzov;
    }

    public final void run() {
        if (this.zzbis.zzbij != null) {
            this.zzbis.zzbij.zzkq();
            this.zzbis.zzbij.zzkp();
            this.zzbis.zzbij.zzcs();
        }
        zzoz unused = this.zzbis.zzbij = null;
    }
}
