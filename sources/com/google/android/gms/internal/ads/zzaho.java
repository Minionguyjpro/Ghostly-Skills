package com.google.android.gms.internal.ads;

final class zzaho implements Runnable {
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzjj zzyh;

    zzaho(zzahn zzahn, zzjj zzjj, zzxq zzxq) {
        this.zzclv = zzahn;
        this.zzyh = zzjj;
        this.zzclu = zzxq;
    }

    public final void run() {
        this.zzclv.zza(this.zzyh, this.zzclu);
    }
}
