package org.altbeacon.beacon.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import org.altbeacon.beacon.Region;

public class StartRMData implements Parcelable, Serializable {
    public static final Parcelable.Creator<StartRMData> CREATOR = new Parcelable.Creator<StartRMData>() {
        public StartRMData createFromParcel(Parcel parcel) {
            return new StartRMData(parcel);
        }

        public StartRMData[] newArray(int i) {
            return new StartRMData[i];
        }
    };
    private boolean mBackgroundFlag;
    private long mBetweenScanPeriod;
    private String mCallbackPackageName;
    private Region mRegion;
    private long mScanPeriod;

    public int describeContents() {
        return 0;
    }

    private StartRMData() {
    }

    public StartRMData(long j, long j2, boolean z) {
        this.mScanPeriod = j;
        this.mBetweenScanPeriod = j2;
        this.mBackgroundFlag = z;
    }

    public StartRMData(Region region, String str, long j, long j2, boolean z) {
        this.mScanPeriod = j;
        this.mBetweenScanPeriod = j2;
        this.mRegion = region;
        this.mCallbackPackageName = str;
        this.mBackgroundFlag = z;
    }

    public long getScanPeriod() {
        return this.mScanPeriod;
    }

    public long getBetweenScanPeriod() {
        return this.mBetweenScanPeriod;
    }

    public Region getRegionData() {
        return this.mRegion;
    }

    public String getCallbackPackageName() {
        return this.mCallbackPackageName;
    }

    public boolean getBackgroundFlag() {
        return this.mBackgroundFlag;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRegion, i);
        parcel.writeString(this.mCallbackPackageName);
        parcel.writeLong(this.mScanPeriod);
        parcel.writeLong(this.mBetweenScanPeriod);
        parcel.writeByte(this.mBackgroundFlag ? (byte) 1 : 0);
    }

    private StartRMData(Parcel parcel) {
        this.mRegion = (Region) parcel.readParcelable(StartRMData.class.getClassLoader());
        this.mCallbackPackageName = parcel.readString();
        this.mScanPeriod = parcel.readLong();
        this.mBetweenScanPeriod = parcel.readLong();
        this.mBackgroundFlag = parcel.readByte() != 0;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong("scanPeriod", this.mScanPeriod);
        bundle.putLong("betweenScanPeriod", this.mBetweenScanPeriod);
        bundle.putBoolean("backgroundFlag", this.mBackgroundFlag);
        bundle.putString("callbackPackageName", this.mCallbackPackageName);
        Region region = this.mRegion;
        if (region != null) {
            bundle.putSerializable("region", region);
        }
        return bundle;
    }

    public static StartRMData fromBundle(Bundle bundle) {
        boolean z;
        bundle.setClassLoader(Region.class.getClassLoader());
        StartRMData startRMData = new StartRMData();
        boolean z2 = true;
        if (bundle.containsKey("region")) {
            startRMData.mRegion = (Region) bundle.getSerializable("region");
            z = true;
        } else {
            z = false;
        }
        if (bundle.containsKey("scanPeriod")) {
            startRMData.mScanPeriod = ((Long) bundle.get("scanPeriod")).longValue();
        } else {
            z2 = z;
        }
        if (bundle.containsKey("betweenScanPeriod")) {
            startRMData.mBetweenScanPeriod = ((Long) bundle.get("betweenScanPeriod")).longValue();
        }
        if (bundle.containsKey("backgroundFlag")) {
            startRMData.mBackgroundFlag = ((Boolean) bundle.get("backgroundFlag")).booleanValue();
        }
        if (bundle.containsKey("callbackPackageName")) {
            startRMData.mCallbackPackageName = (String) bundle.get("callbackPackageName");
        }
        if (z2) {
            return startRMData;
        }
        return null;
    }
}
