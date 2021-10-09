package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvn implements Runnable {
    private final zzuu zzbqh;

    private zzvn(zzuu zzuu) {
        this.zzbqh = zzuu;
    }

    static Runnable zza(zzuu zzuu) {
        return new zzvn(zzuu);
    }

    public final void run() {
        this.zzbqh.destroy();
    }
}
