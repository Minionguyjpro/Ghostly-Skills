package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzajo extends zzajx {
    private final /* synthetic */ zzajm zzcpw;

    zzajo(zzajm zzajm) {
        this.zzcpw = zzajm;
    }

    public final void onStop() {
    }

    public final void zzdn() {
        zznm zznm = new zznm(this.zzcpw.mContext, this.zzcpw.zzyf.zzcw);
        synchronized (this.zzcpw.mLock) {
            try {
                zzbv.zzet();
                zznp.zza(this.zzcpw.zzcpn, zznm);
            } catch (IllegalArgumentException e) {
                zzakb.zzc("Cannot config CSI reporter.", e);
            }
        }
    }
}
