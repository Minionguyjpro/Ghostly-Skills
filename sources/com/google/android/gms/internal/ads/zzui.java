package com.google.android.gms.internal.ads;

final /* synthetic */ class zzui implements Runnable {
    private final zzuf zzbpk;
    private final String zzzo;

    zzui(zzuf zzuf, String str) {
        this.zzbpk = zzuf;
        this.zzzo = str;
    }

    public final void run() {
        this.zzbpk.zzbh(this.zzzo);
    }
}
