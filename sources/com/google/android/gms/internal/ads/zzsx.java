package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzsx implements zzts {
    zzsx(zzsu zzsu) {
    }

    public final void zzb(zztt zztt) throws RemoteException {
        if (zztt.zzxs != null) {
            zztt.zzxs.onAdLeftApplication();
        }
    }
}
