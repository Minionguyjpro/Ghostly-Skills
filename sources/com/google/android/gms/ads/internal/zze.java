package com.google.android.gms.ads.internal;

final /* synthetic */ class zze implements Runnable {
    private final zzbl zzwj;

    private zze(zzbl zzbl) {
        this.zzwj = zzbl;
    }

    static Runnable zza(zzbl zzbl) {
        return new zze(zzbl);
    }

    public final void run() {
        this.zzwj.pause();
    }
}
