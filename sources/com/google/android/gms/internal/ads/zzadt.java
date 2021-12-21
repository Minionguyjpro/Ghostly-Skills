package com.google.android.gms.internal.ads;

final class zzadt implements Runnable {
    private final /* synthetic */ zzadk zzccn;
    private final /* synthetic */ zzaol zzcco;

    zzadt(zzadk zzadk, zzaol zzaol) {
        this.zzccn = zzadk;
        this.zzcco = zzaol;
    }

    public final void run() {
        synchronized (this.zzccn.zzbzh) {
            this.zzccn.zzccj = this.zzccn.zza(this.zzccn.zzccg.zzacr, this.zzcco);
            if (this.zzccn.zzccj == null) {
                this.zzccn.zzc(0, "Could not start the ad request service.");
                zzakk.zzcrm.removeCallbacks(this.zzccn.zzbzg);
            }
        }
    }
}
