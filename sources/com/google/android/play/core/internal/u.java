package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class u extends k implements v {
    public u() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel) throws RemoteException {
        switch (i) {
            case 2:
                b(parcel.readInt(), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int readInt = parcel.readInt();
                Bundle bundle = (Bundle) l.a(parcel, Bundle.CREATOR);
                h(readInt);
                return true;
            case 4:
                int readInt2 = parcel.readInt();
                Bundle bundle2 = (Bundle) l.a(parcel, Bundle.CREATOR);
                j(readInt2);
                return true;
            case 5:
                c(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 6:
                Bundle bundle3 = (Bundle) l.a(parcel, Bundle.CREATOR);
                k((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                g((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 8:
                Bundle bundle4 = (Bundle) l.a(parcel, Bundle.CREATOR);
                l((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 10:
                Bundle bundle5 = (Bundle) l.a(parcel, Bundle.CREATOR);
                m((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 11:
                d((Bundle) l.a(parcel, Bundle.CREATOR), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 12:
                e((Bundle) l.a(parcel, Bundle.CREATOR), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                f((Bundle) l.a(parcel, Bundle.CREATOR), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 14:
                Bundle bundle6 = (Bundle) l.a(parcel, Bundle.CREATOR);
                Bundle bundle7 = (Bundle) l.a(parcel, Bundle.CREATOR);
                n();
                return true;
            case 15:
                Bundle bundle8 = (Bundle) l.a(parcel, Bundle.CREATOR);
                i();
                return true;
            default:
                return false;
        }
    }
}
