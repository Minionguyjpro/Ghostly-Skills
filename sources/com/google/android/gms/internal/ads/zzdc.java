package com.google.android.gms.internal.ads;

final class zzdc implements Runnable {
    private final /* synthetic */ zzcz zzsl;
    private final /* synthetic */ int zzsm;
    private final /* synthetic */ boolean zzsn;

    zzdc(zzcz zzcz, int i, boolean z) {
        this.zzsl = zzcz;
        this.zzsm = i;
        this.zzsn = z;
    }

    public final void run() {
        zzba zzb = this.zzsl.zzb(this.zzsm, this.zzsn);
        zzba unused = this.zzsl.zzsb = zzb;
        if (zzcz.zza(this.zzsm, zzb)) {
            this.zzsl.zza(this.zzsm + 1, this.zzsn);
        }
    }
}
