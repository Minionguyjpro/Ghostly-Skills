package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzq implements zzatd<Object, Object> {
    private final /* synthetic */ zzzf zzbvj;
    private final /* synthetic */ zzxt zzbvk;

    zzzq(zzzp zzzp, zzzf zzzf, zzxt zzxt) {
        this.zzbvj = zzzf;
        this.zzbvk = zzxt;
    }

    public final void zzau(String str) {
        try {
            this.zzbvj.zzbr(str);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }
}
