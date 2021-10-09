package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.altbeacon.beacon.client.BeaconDataFactory;
import org.altbeacon.beacon.client.NullBeaconDataFactory;
import org.altbeacon.beacon.distance.DistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;

public class Beacon implements Parcelable, Serializable {
    @Deprecated
    public static final Parcelable.Creator<Beacon> CREATOR = new Parcelable.Creator<Beacon>() {
        public Beacon createFromParcel(Parcel parcel) {
            return new Beacon(parcel);
        }

        public Beacon[] newArray(int i) {
            return new Beacon[i];
        }
    };
    private static final List<Identifier> UNMODIFIABLE_LIST_OF_IDENTIFIER = Collections.unmodifiableList(new ArrayList());
    private static final List<Long> UNMODIFIABLE_LIST_OF_LONG = Collections.unmodifiableList(new ArrayList());
    protected static BeaconDataFactory beaconDataFactory = new NullBeaconDataFactory();
    protected static DistanceCalculator sDistanceCalculator = null;
    protected static boolean sHardwareEqualityEnforced = false;
    protected int mBeaconTypeCode;
    protected String mBluetoothAddress;
    protected String mBluetoothName;
    protected List<Long> mDataFields;
    protected Double mDistance;
    protected List<Long> mExtraDataFields;
    protected List<Identifier> mIdentifiers;
    protected int mManufacturer;
    protected boolean mMultiFrameBeacon;
    private int mPacketCount;
    protected String mParserIdentifier;
    protected int mRssi;
    private int mRssiMeasurementCount;
    private Double mRunningAverageRssi;
    protected int mServiceUuid;
    protected int mTxPower;

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public static void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        sDistanceCalculator = distanceCalculator;
    }

    public static DistanceCalculator getDistanceCalculator() {
        return sDistanceCalculator;
    }

    public static void setHardwareEqualityEnforced(boolean z) {
        sHardwareEqualityEnforced = z;
    }

    public static boolean getHardwareEqualityEnforced() {
        return sHardwareEqualityEnforced;
    }

    @Deprecated
    protected Beacon(Parcel parcel) {
        boolean z = false;
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        int readInt = parcel.readInt();
        this.mIdentifiers = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.mIdentifiers.add(Identifier.parse(parcel.readString()));
        }
        this.mDistance = Double.valueOf(parcel.readDouble());
        this.mRssi = parcel.readInt();
        this.mTxPower = parcel.readInt();
        this.mBluetoothAddress = parcel.readString();
        this.mBeaconTypeCode = parcel.readInt();
        this.mServiceUuid = parcel.readInt();
        int readInt2 = parcel.readInt();
        this.mDataFields = new ArrayList(readInt2);
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.mDataFields.add(Long.valueOf(parcel.readLong()));
        }
        int readInt3 = parcel.readInt();
        this.mExtraDataFields = new ArrayList(readInt3);
        for (int i3 = 0; i3 < readInt3; i3++) {
            this.mExtraDataFields.add(Long.valueOf(parcel.readLong()));
        }
        this.mManufacturer = parcel.readInt();
        this.mBluetoothName = parcel.readString();
        this.mParserIdentifier = parcel.readString();
        this.mMultiFrameBeacon = parcel.readByte() != 0 ? true : z;
        this.mRunningAverageRssi = (Double) parcel.readValue((ClassLoader) null);
        this.mRssiMeasurementCount = parcel.readInt();
        this.mPacketCount = parcel.readInt();
    }

    protected Beacon() {
        this.mRssiMeasurementCount = 0;
        this.mPacketCount = 0;
        this.mRunningAverageRssi = null;
        this.mServiceUuid = -1;
        this.mMultiFrameBeacon = false;
        this.mIdentifiers = new ArrayList(1);
        this.mDataFields = new ArrayList(1);
        this.mExtraDataFields = new ArrayList(1);
    }

    public void setRssiMeasurementCount(int i) {
        this.mRssiMeasurementCount = i;
    }

    public void setPacketCount(int i) {
        this.mPacketCount = i;
    }

    public void setRunningAverageRssi(double d) {
        this.mRunningAverageRssi = Double.valueOf(d);
        this.mDistance = null;
    }

    public void setRssi(int i) {
        this.mRssi = i;
    }

    public int getServiceUuid() {
        return this.mServiceUuid;
    }

    public Identifier getIdentifier(int i) {
        return this.mIdentifiers.get(i);
    }

    public List<Long> getDataFields() {
        if (this.mDataFields.getClass().isInstance(UNMODIFIABLE_LIST_OF_LONG)) {
            return this.mDataFields;
        }
        return Collections.unmodifiableList(this.mDataFields);
    }

    public List<Long> getExtraDataFields() {
        if (this.mExtraDataFields.getClass().isInstance(UNMODIFIABLE_LIST_OF_LONG)) {
            return this.mExtraDataFields;
        }
        return Collections.unmodifiableList(this.mExtraDataFields);
    }

    public void setExtraDataFields(List<Long> list) {
        this.mExtraDataFields = list;
    }

    public double getDistance() {
        if (this.mDistance == null) {
            double d = (double) this.mRssi;
            Double d2 = this.mRunningAverageRssi;
            if (d2 != null) {
                d = d2.doubleValue();
            } else {
                LogManager.d("Beacon", "Not using running average RSSI because it is null", new Object[0]);
            }
            this.mDistance = calculateDistance(this.mTxPower, d);
        }
        return this.mDistance.doubleValue();
    }

    public int getRssi() {
        return this.mRssi;
    }

    public String getBluetoothAddress() {
        return this.mBluetoothAddress;
    }

    public boolean isMultiFrameBeacon() {
        return this.mMultiFrameBeacon;
    }

    public int hashCode() {
        StringBuilder stringBuilder = toStringBuilder();
        if (sHardwareEqualityEnforced) {
            stringBuilder.append(this.mBluetoothAddress);
        }
        return stringBuilder.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Beacon)) {
            return false;
        }
        Beacon beacon = (Beacon) obj;
        if (!this.mIdentifiers.equals(beacon.mIdentifiers)) {
            return false;
        }
        if (sHardwareEqualityEnforced) {
            return getBluetoothAddress().equals(beacon.getBluetoothAddress());
        }
        return true;
    }

    public String toString() {
        return toStringBuilder().toString();
    }

    private StringBuilder toStringBuilder() {
        String str;
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Identifier next : this.mIdentifiers) {
            if (i > 1) {
                sb.append(" ");
            }
            sb.append("id");
            sb.append(i);
            sb.append(": ");
            if (next == null) {
                str = "null";
            } else {
                str = next.toString();
            }
            sb.append(str);
            i++;
        }
        if (this.mParserIdentifier != null) {
            sb.append(" type " + this.mParserIdentifier);
        }
        return sb;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        String str;
        parcel.writeInt(this.mIdentifiers.size());
        for (Identifier next : this.mIdentifiers) {
            if (next == null) {
                str = null;
            } else {
                str = next.toString();
            }
            parcel.writeString(str);
        }
        parcel.writeDouble(getDistance());
        parcel.writeInt(this.mRssi);
        parcel.writeInt(this.mTxPower);
        parcel.writeString(this.mBluetoothAddress);
        parcel.writeInt(this.mBeaconTypeCode);
        parcel.writeInt(this.mServiceUuid);
        parcel.writeInt(this.mDataFields.size());
        for (Long longValue : this.mDataFields) {
            parcel.writeLong(longValue.longValue());
        }
        parcel.writeInt(this.mExtraDataFields.size());
        for (Long longValue2 : this.mExtraDataFields) {
            parcel.writeLong(longValue2.longValue());
        }
        parcel.writeInt(this.mManufacturer);
        parcel.writeString(this.mBluetoothName);
        parcel.writeString(this.mParserIdentifier);
        parcel.writeByte(this.mMultiFrameBeacon ? (byte) 1 : 0);
        parcel.writeValue(this.mRunningAverageRssi);
        parcel.writeInt(this.mRssiMeasurementCount);
        parcel.writeInt(this.mPacketCount);
    }

    public boolean isExtraBeaconData() {
        return this.mIdentifiers.size() == 0 && this.mDataFields.size() != 0;
    }

    protected static Double calculateDistance(int i, double d) {
        if (getDistanceCalculator() != null) {
            return Double.valueOf(getDistanceCalculator().calculateDistance(i, d));
        }
        LogManager.e("Beacon", "Distance calculator not set.  Distance will bet set to -1", new Object[0]);
        return Double.valueOf(-1.0d);
    }
}
