package org.altbeacon.beacon;

import android.bluetooth.BluetoothDevice;

public class AltBeaconParser extends BeaconParser {
    public AltBeaconParser() {
        this.mHardwareAssistManufacturers = new int[]{280};
        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25");
        this.mIdentifier = "altbeacon";
    }

    public Beacon fromScanData(byte[] bArr, int i, BluetoothDevice bluetoothDevice) {
        return fromScanData(bArr, i, bluetoothDevice, new AltBeacon());
    }
}
