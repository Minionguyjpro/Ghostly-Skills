package com.google.android.gms.internal.ads;

final class zzade implements Runnable {
    private final /* synthetic */ zzanf zzccd;
    private final /* synthetic */ String zzcce;

    zzade(zzadb zzadb, zzanf zzanf, String str) {
        this.zzccd = zzanf;
        this.zzcce = str;
    }

    public final void run() {
        this.zzccd.zzcz(this.zzcce);
    }
}
