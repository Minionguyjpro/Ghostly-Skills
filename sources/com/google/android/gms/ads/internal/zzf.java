package com.google.android.gms.ads.internal;

final /* synthetic */ class zzf implements Runnable {
    private final zzbl zzwj;

    private zzf(zzbl zzbl) {
        this.zzwj = zzbl;
    }

    static Runnable zza(zzbl zzbl) {
        return new zzf(zzbl);
    }

    public final void run() {
        this.zzwj.resume();
    }
}
