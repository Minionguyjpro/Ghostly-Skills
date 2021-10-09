package com.google.android.gms.internal.ads;

final class zzxj implements Runnable {
    private final /* synthetic */ zzxh zzbuk;
    private final /* synthetic */ zzanz zzbul;

    zzxj(zzxh zzxh, zzanz zzanz) {
        this.zzbuk = zzxh;
        this.zzbul = zzanz;
    }

    public final void run() {
        for (zzanz zzanz : this.zzbuk.zzbug.keySet()) {
            if (zzanz != this.zzbul) {
                ((zzxb) this.zzbuk.zzbug.get(zzanz)).cancel();
            }
        }
    }
}
