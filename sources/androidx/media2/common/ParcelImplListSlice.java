package androidx.media2.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.versionedparcelable.ParcelImpl;
import java.util.ArrayList;
import java.util.List;

public class ParcelImplListSlice implements Parcelable {
    public static final Parcelable.Creator<ParcelImplListSlice> CREATOR = new Parcelable.Creator<ParcelImplListSlice>() {
        public ParcelImplListSlice createFromParcel(Parcel parcel) {
            return new ParcelImplListSlice(parcel);
        }

        public ParcelImplListSlice[] newArray(int i) {
            return new ParcelImplListSlice[i];
        }
    };
    final List<ParcelImpl> mList;

    public int describeContents() {
        return 0;
    }

    public ParcelImplListSlice(List<ParcelImpl> list) {
        if (list != null) {
            this.mList = list;
            return;
        }
        throw new NullPointerException("list shouldn't be null");
    }

    ParcelImplListSlice(Parcel parcel) {
        int readInt = parcel.readInt();
        this.mList = new ArrayList(readInt);
        if (readInt > 0) {
            int i = 0;
            while (i < readInt && parcel.readInt() != 0) {
                this.mList.add((ParcelImpl) parcel.readParcelable(ParcelImpl.class.getClassLoader()));
                i++;
            }
            if (i < readInt) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                while (i < readInt) {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    obtain.writeInt(i);
                    try {
                        readStrongBinder.transact(1, obtain, obtain2, 0);
                        while (i < readInt && obtain2.readInt() != 0) {
                            this.mList.add((ParcelImpl) obtain2.readParcelable(ParcelImpl.class.getClassLoader()));
                            i++;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                    } catch (RemoteException e) {
                        Log.w("ParcelImplListSlice", "Failure retrieving array; only received " + i + " of " + readInt, e);
                        return;
                    }
                }
            }
        }
    }

    public List<ParcelImpl> getList() {
        return this.mList;
    }

    public void writeToParcel(Parcel parcel, int i) {
        final int size = this.mList.size();
        parcel.writeInt(size);
        if (size > 0) {
            int i2 = 0;
            while (i2 < size && parcel.dataSize() < 65536) {
                parcel.writeInt(1);
                parcel.writeParcelable(this.mList.get(i2), i);
                i2++;
            }
            if (i2 < size) {
                parcel.writeInt(0);
                parcel.writeStrongBinder(new Binder() {
                    /* access modifiers changed from: protected */
                    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
                        if (i != 1) {
                            return super.onTransact(i, parcel, parcel2, i2);
                        }
                        int readInt = parcel.readInt();
                        while (readInt < size && parcel2.dataSize() < 65536) {
                            parcel2.writeInt(1);
                            parcel2.writeParcelable(ParcelImplListSlice.this.mList.get(readInt), i2);
                            readInt++;
                        }
                        if (readInt < size) {
                            parcel2.writeInt(0);
                        }
                        return true;
                    }
                });
            }
        }
    }
}
