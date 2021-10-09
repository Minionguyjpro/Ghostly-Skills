package com.google.android.gms.internal.ads;

final /* synthetic */ class zzul implements Runnable {
    private final zzuf zzbpk;
    private final String zzzo;

    zzul(zzuf zzuf, String str) {
        this.zzbpk = zzuf;
        this.zzzo = str;
    }

    public final void run() {
        this.zzbpk.zzbf(this.zzzo);
    }
}
