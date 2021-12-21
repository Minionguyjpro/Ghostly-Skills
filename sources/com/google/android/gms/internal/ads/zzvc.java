package com.google.android.gms.internal.ads;

final class zzvc implements Runnable {
    private final /* synthetic */ zzuw zzbpu;
    private final /* synthetic */ String zzbpv;

    zzvc(zzuw zzuw, String str) {
        this.zzbpu = zzuw;
        this.zzbpv = str;
    }

    public final void run() {
        this.zzbpu.zzbnd.loadUrl(this.zzbpv);
    }
}
