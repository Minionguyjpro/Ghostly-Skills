package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztd implements zzts {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzbny;

    zztd(zztc zztc, String str, String str2) {
        this.val$name = str;
        this.zzbny = str2;
    }

    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzboe != null) {
            zztt.zzboe.onAppEvent(this.val$name, this.zzbny);
        }
    }
}
