package org.altbeacon.beacon.service;

import java.io.Serializable;
import java.util.HashMap;
import org.altbeacon.beacon.Beacon;

public class ExtraDataBeaconTracker implements Serializable {
    private final HashMap<String, HashMap<Integer, Beacon>> mBeaconsByKey;
    private final boolean matchBeaconsByServiceUUID;

    public ExtraDataBeaconTracker() {
        this(true);
    }

    public ExtraDataBeaconTracker(boolean z) {
        this.mBeaconsByKey = new HashMap<>();
        this.matchBeaconsByServiceUUID = z;
    }

    public synchronized Beacon track(Beacon beacon) {
        if (beacon.isMultiFrameBeacon() || beacon.getServiceUuid() != -1) {
            beacon = trackGattBeacon(beacon);
        }
        return beacon;
    }

    private Beacon trackGattBeacon(Beacon beacon) {
        if (beacon.isExtraBeaconData()) {
            updateTrackedBeacons(beacon);
            return null;
        }
        String beaconKey = getBeaconKey(beacon);
        HashMap hashMap = this.mBeaconsByKey.get(beaconKey);
        if (hashMap == null) {
            hashMap = new HashMap();
        } else {
            beacon.setExtraDataFields(((Beacon) hashMap.values().iterator().next()).getExtraDataFields());
        }
        hashMap.put(Integer.valueOf(beacon.hashCode()), beacon);
        this.mBeaconsByKey.put(beaconKey, hashMap);
        return beacon;
    }

    private void updateTrackedBeacons(Beacon beacon) {
        HashMap hashMap = this.mBeaconsByKey.get(getBeaconKey(beacon));
        if (hashMap != null) {
            for (Beacon beacon2 : hashMap.values()) {
                beacon2.setRssi(beacon.getRssi());
                beacon2.setExtraDataFields(beacon.getDataFields());
            }
        }
    }

    private String getBeaconKey(Beacon beacon) {
        if (!this.matchBeaconsByServiceUUID) {
            return beacon.getBluetoothAddress();
        }
        return beacon.getBluetoothAddress() + beacon.getServiceUuid();
    }
}
