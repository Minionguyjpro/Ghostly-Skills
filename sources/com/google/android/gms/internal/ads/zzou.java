package com.google.android.gms.internal.ads;

final class zzou implements Runnable {
    private final /* synthetic */ zzos zzbir;

    zzou(zzos zzos) {
        this.zzbir = zzos;
    }

    public final void run() {
        if (this.zzbir.zzbij != null) {
            this.zzbir.zzbij.zzkq();
            this.zzbir.zzbij.zzkp();
        }
        zzoz unused = this.zzbir.zzbij = null;
    }
}
