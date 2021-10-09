package com.google.android.gms.internal.ads;

final /* synthetic */ class zzuj implements Runnable {
    private final zzuf zzbpk;
    private final String zzzo;

    zzuj(zzuf zzuf, String str) {
        this.zzbpk = zzuf;
        this.zzzo = str;
    }

    public final void run() {
        this.zzbpk.zzbg(this.zzzo);
    }
}
