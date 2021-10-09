package com.google.android.gms.internal.ads;

final class zze implements Runnable {
    private final /* synthetic */ zzr zzn;
    private final /* synthetic */ zzd zzo;

    zze(zzd zzd, zzr zzr) {
        this.zzo = zzd;
        this.zzn = zzr;
    }

    public final void run() {
        try {
            this.zzo.zzi.put(this.zzn);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }
}
