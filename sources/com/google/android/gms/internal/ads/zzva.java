package com.google.android.gms.internal.ads;

final class zzva implements Runnable {
    private final /* synthetic */ String zzbpt;
    private final /* synthetic */ zzuw zzbpu;

    zzva(zzuw zzuw, String str) {
        this.zzbpu = zzuw;
        this.zzbpt = str;
    }

    public final void run() {
        this.zzbpu.zzbnd.loadData(this.zzbpt, "text/html", "UTF-8");
    }
}
