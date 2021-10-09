package com.google.android.gms.internal.ads;

final class zzabp implements Runnable {
    private final /* synthetic */ zzajh zzbzl;
    private final /* synthetic */ zzabo zzbzm;

    zzabp(zzabo zzabo, zzajh zzajh) {
        this.zzbzm = zzabo;
        this.zzbzl = zzajh;
    }

    public final void run() {
        this.zzbzm.zzbzd.zzb(this.zzbzl);
    }
}
