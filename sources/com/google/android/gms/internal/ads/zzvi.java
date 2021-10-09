package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvi implements Runnable {
    private final zzvf zzbqc;
    private final zzvw zzbqf;
    private final zzuu zzbqg;

    zzvi(zzvf zzvf, zzvw zzvw, zzuu zzuu) {
        this.zzbqc = zzvf;
        this.zzbqf = zzvw;
        this.zzbqg = zzuu;
    }

    public final void run() {
        this.zzbqc.zza(this.zzbqf, this.zzbqg);
    }
}
