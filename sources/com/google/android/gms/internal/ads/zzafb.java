package com.google.android.gms.internal.ads;

final class zzafb implements Runnable {
    private final /* synthetic */ zzafa zzcgj;
    private final /* synthetic */ zzaji zzwg;

    zzafb(zzafa zzafa, zzaji zzaji) {
        this.zzcgj = zzafa;
        this.zzwg = zzaji;
    }

    public final void run() {
        this.zzcgj.zzccf.zza(this.zzwg);
        if (this.zzcgj.zzcgi != null) {
            this.zzcgj.zzcgi.release();
            zzvs unused = this.zzcgj.zzcgi = null;
        }
    }
}
