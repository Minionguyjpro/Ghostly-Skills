package com.google.android.gms.internal.ads;

final /* synthetic */ class zzwa implements Runnable {
    private final zzvz zzbqx;
    private final zzuu zzbqy;

    zzwa(zzvz zzvz, zzuu zzuu) {
        this.zzbqx = zzvz;
        this.zzbqy = zzuu;
    }

    public final void run() {
        zzvz zzvz = this.zzbqx;
        zzuu zzuu = this.zzbqy;
        zzvz.zzbqw.zzbpz.zze(zzuu);
        zzuu.destroy();
    }
}
