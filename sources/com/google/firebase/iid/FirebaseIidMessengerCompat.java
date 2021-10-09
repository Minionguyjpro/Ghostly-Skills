package com.google.firebase.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.firebase.iid.IMessengerCompat;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public class FirebaseIidMessengerCompat implements Parcelable {
    public static final Parcelable.Creator<FirebaseIidMessengerCompat> CREATOR = new Parcelable.Creator<FirebaseIidMessengerCompat>() {
        public FirebaseIidMessengerCompat createFromParcel(Parcel parcel) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                return new FirebaseIidMessengerCompat(readStrongBinder);
            }
            return null;
        }

        public FirebaseIidMessengerCompat[] newArray(int i) {
            return new FirebaseIidMessengerCompat[i];
        }
    };
    Messenger messenger;
    IMessengerCompat messengerCompat;

    /* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
    public static final class HandleOldParcelNameClassLoader extends ClassLoader {
        /* access modifiers changed from: protected */
        public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
                return super.loadClass(str, z);
            }
            if (!FirebaseInstanceId.isDebugLogEnabled()) {
                return FirebaseIidMessengerCompat.class;
            }
            Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
            return FirebaseIidMessengerCompat.class;
        }
    }

    public int describeContents() {
        return 0;
    }

    public FirebaseIidMessengerCompat(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.messenger = new Messenger(iBinder);
        } else {
            this.messengerCompat = new IMessengerCompat.Proxy(iBinder);
        }
    }

    public void send(Message message) throws RemoteException {
        Messenger messenger2 = this.messenger;
        if (messenger2 != null) {
            messenger2.send(message);
        } else {
            this.messengerCompat.send(message);
        }
    }

    public IBinder getBinder() {
        Messenger messenger2 = this.messenger;
        return messenger2 != null ? messenger2.getBinder() : this.messengerCompat.asBinder();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return getBinder().equals(((FirebaseIidMessengerCompat) obj).getBinder());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Messenger messenger2 = this.messenger;
        if (messenger2 != null) {
            parcel.writeStrongBinder(messenger2.getBinder());
        } else {
            parcel.writeStrongBinder(this.messengerCompat.asBinder());
        }
    }
}
