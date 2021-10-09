package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzr implements zzatd<zzate, Object> {
    private final /* synthetic */ zzxt zzbvk;
    private final /* synthetic */ zzzh zzbvl;
    private final /* synthetic */ zzzp zzbvm;

    zzzr(zzzp zzzp, zzzh zzzh, zzxt zzxt) {
        this.zzbvm = zzzp;
        this.zzbvl = zzzh;
        this.zzbvk = zzxt;
    }

    public final void zzau(String str) {
        try {
            this.zzbvl.zzbr(str);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }
}
