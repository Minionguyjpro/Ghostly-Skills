package com.google.android.gms.internal.ads;

final /* synthetic */ class zzapj implements Runnable {
    private final zzapg zzcyc;

    private zzapj(zzapg zzapg) {
        this.zzcyc = zzapg;
    }

    static Runnable zza(zzapg zzapg) {
        return new zzapj(zzapg);
    }

    public final void run() {
        this.zzcyc.stop();
    }
}
