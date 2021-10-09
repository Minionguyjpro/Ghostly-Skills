package com.google.android.gms.internal.ads;

final /* synthetic */ class zzvg implements Runnable {
    private final zzvf zzbqc;
    private final zzci zzbqd;
    private final zzvw zzbqe;

    zzvg(zzvf zzvf, zzci zzci, zzvw zzvw) {
        this.zzbqc = zzvf;
        this.zzbqd = zzci;
        this.zzbqe = zzvw;
    }

    public final void run() {
        this.zzbqc.zza(this.zzbqd, this.zzbqe);
    }
}
