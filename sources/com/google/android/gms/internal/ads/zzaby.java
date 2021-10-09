package com.google.android.gms.internal.ads;

final class zzaby implements Runnable {
    private final /* synthetic */ zzaoj zzcaj;
    private final /* synthetic */ String zzcak;
    private final /* synthetic */ zzabv zzcal;

    zzaby(zzabv zzabv, zzaoj zzaoj, String str) {
        this.zzcal = zzabv;
        this.zzcaj = zzaoj;
        this.zzcak = str;
    }

    public final void run() {
        this.zzcaj.set(this.zzcal.zzbzz.zzdv().get(this.zzcak));
    }
}
