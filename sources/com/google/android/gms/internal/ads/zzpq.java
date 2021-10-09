package com.google.android.gms.internal.ads;

final class zzpq implements Runnable {
    private final /* synthetic */ zzpd zzbki;
    private final /* synthetic */ zzpp zzbkj;

    zzpq(zzpp zzpp, zzpd zzpd) {
        this.zzbkj = zzpp;
        this.zzbki = zzpd;
    }

    public final void run() {
        this.zzbkj.zza(this.zzbki);
    }
}
