package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public abstract class zab {
    public final int zaa;

    public zab(int i) {
        this.zaa = i;
    }

    public abstract void zaa(Status status);

    public abstract void zaa(GoogleApiManager.zaa<?> zaa2) throws DeadObjectException;

    public abstract void zaa(zav zav, boolean z);

    public abstract void zaa(Exception exc);

    /* access modifiers changed from: private */
    public static Status zab(RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage());
    }
}
