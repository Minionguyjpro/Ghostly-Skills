package org.altbeacon.beacon.service;

import android.os.SystemClock;
import java.io.Serializable;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;

public class RangedBeacon implements Serializable {
    public static long maxTrackingAge = 5000;
    private static long sampleExpirationMilliseconds = 20000;
    protected long lastTrackedTimeMillis = 0;
    Beacon mBeacon;
    protected transient RssiFilter mFilter = null;
    private boolean mTracked = true;
    private int packetCount = 0;

    public RangedBeacon(Beacon beacon) {
        updateBeacon(beacon);
    }

    public void updateBeacon(Beacon beacon) {
        this.packetCount++;
        this.mBeacon = beacon;
        addMeasurement(Integer.valueOf(beacon.getRssi()));
    }

    public boolean isTracked() {
        return this.mTracked;
    }

    public void setTracked(boolean z) {
        this.mTracked = z;
    }

    public Beacon getBeacon() {
        return this.mBeacon;
    }

    public void commitMeasurements() {
        if (!getFilter().noMeasurementsAvailable()) {
            double calculateRssi = getFilter().calculateRssi();
            this.mBeacon.setRunningAverageRssi(calculateRssi);
            this.mBeacon.setRssiMeasurementCount(getFilter().getMeasurementCount());
            LogManager.d("RangedBeacon", "calculated new runningAverageRssi: %s", Double.valueOf(calculateRssi));
        } else {
            LogManager.d("RangedBeacon", "No measurements available to calculate running average", new Object[0]);
        }
        this.mBeacon.setPacketCount(this.packetCount);
        this.packetCount = 0;
    }

    public void addMeasurement(Integer num) {
        if (num.intValue() != 127) {
            this.mTracked = true;
            this.lastTrackedTimeMillis = SystemClock.elapsedRealtime();
            getFilter().addMeasurement(num);
        }
    }

    public boolean noMeasurementsAvailable() {
        return getFilter().noMeasurementsAvailable();
    }

    public long getTrackingAge() {
        return SystemClock.elapsedRealtime() - this.lastTrackedTimeMillis;
    }

    public boolean isExpired() {
        return getTrackingAge() > maxTrackingAge;
    }

    private RssiFilter getFilter() {
        if (this.mFilter == null) {
            try {
                this.mFilter = (RssiFilter) BeaconManager.getRssiFilterImplClass().getConstructors()[0].newInstance(new Object[0]);
            } catch (Exception unused) {
                LogManager.e("RangedBeacon", "Could not construct RssiFilterImplClass %s", BeaconManager.getRssiFilterImplClass().getName());
            }
        }
        return this.mFilter;
    }
}
