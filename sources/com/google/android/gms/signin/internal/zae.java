package com.google.android.gms.signin.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.IAccountAccessor;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
public interface zae extends IInterface {
    void zaa(int i) throws RemoteException;

    void zaa(IAccountAccessor iAccountAccessor, int i, boolean z) throws RemoteException;

    void zaa(zak zak, zac zac) throws RemoteException;
}
