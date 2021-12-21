package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztn implements zzts {
    private final /* synthetic */ zzagu zzboa;

    zztn(zzti zzti, zzagu zzagu) {
        this.zzboa = zzagu;
    }

    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzboh != null) {
            zztt.zzboh.zza(this.zzboa);
        }
    }
}
