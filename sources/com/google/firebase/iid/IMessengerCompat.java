package com.google.firebase.iid;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
interface IMessengerCompat extends IInterface {
    void send(Message message) throws RemoteException;

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    public static class Proxy implements IMessengerCompat {
        private final IBinder remote;

        Proxy(IBinder iBinder) {
            this.remote = iBinder;
        }

        public void send(Message message) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
            obtain.writeInt(1);
            message.writeToParcel(obtain, 0);
            try {
                this.remote.transact(1, obtain, (Parcel) null, 1);
            } finally {
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.remote;
        }
    }
}
