package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;

public class AltBeacon extends Beacon {
    public static final Parcelable.Creator<AltBeacon> CREATOR = new Parcelable.Creator<AltBeacon>() {
        public AltBeacon createFromParcel(Parcel parcel) {
            return new AltBeacon(parcel);
        }

        public AltBeacon[] newArray(int i) {
            return new AltBeacon[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    protected AltBeacon() {
    }

    protected AltBeacon(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
