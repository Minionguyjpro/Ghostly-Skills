package com.google.android.gms.internal.ads;

final class zzop implements Runnable {
    private final /* synthetic */ zzoo zzbik;

    zzop(zzoo zzoo) {
        this.zzbik = zzoo;
    }

    public final void run() {
        if (this.zzbik.zzbij != null) {
            this.zzbik.zzbij.zzkq();
            this.zzbik.zzbij.zzkp();
            this.zzbik.zzbij.zzcs();
        }
        zzoz unused = this.zzbik.zzbij = null;
    }
}
