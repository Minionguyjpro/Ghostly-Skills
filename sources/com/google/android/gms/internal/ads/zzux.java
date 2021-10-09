package com.google.android.gms.internal.ads;

final /* synthetic */ class zzux implements Runnable {
    private final zzuw zzbpr;
    private final String zzzo;

    zzux(zzuw zzuw, String str) {
        this.zzbpr = zzuw;
        this.zzzo = str;
    }

    public final void run() {
        this.zzbpr.zzbi(this.zzzo);
    }
}
