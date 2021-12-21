package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public final class r extends j implements t {
    r(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    public final void d(String str, List<Bundle> list, Bundle bundle, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        l.b(a2, bundle);
        l.c(a2, vVar);
        b(14, a2);
    }

    public final void e(String str, Bundle bundle, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        l.b(a2, bundle);
        l.c(a2, vVar);
        b(5, a2);
    }

    public final void f(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        l.b(a2, bundle);
        l.b(a2, bundle2);
        l.c(a2, vVar);
        b(6, a2);
    }

    public final void g(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        l.b(a2, bundle);
        l.b(a2, bundle2);
        l.c(a2, vVar);
        b(7, a2);
    }

    public final void h(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        l.b(a2, bundle);
        l.b(a2, bundle2);
        l.c(a2, vVar);
        b(9, a2);
    }

    public final void i(String str, Bundle bundle, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        l.b(a2, bundle);
        l.c(a2, vVar);
        b(10, a2);
    }

    public final void j(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        l.b(a2, bundle);
        l.b(a2, bundle2);
        l.c(a2, vVar);
        b(11, a2);
    }
}
