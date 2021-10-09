package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvj implements Runnable {
    private final zzuu zzbqh;

    private zzvj(zzuu zzuu) {
        this.zzbqh = zzuu;
    }

    static Runnable zza(zzuu zzuu) {
        return new zzvj(zzuu);
    }

    public final void run() {
        this.zzbqh.destroy();
    }
}
