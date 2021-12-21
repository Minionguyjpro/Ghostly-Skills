package com.google.android.gms.internal.ads;

final class zzabu implements Runnable {
    private final /* synthetic */ zzajh zzaam;
    private final /* synthetic */ zzabt zzbzw;

    zzabu(zzabt zzabt, zzajh zzajh) {
        this.zzbzw = zzabt;
        this.zzaam = zzajh;
    }

    public final void run() {
        this.zzbzw.zzbzd.zzb(this.zzaam);
    }
}
