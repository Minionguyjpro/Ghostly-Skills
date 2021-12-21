package org.altbeacon.beacon.service.scanner;

import android.bluetooth.BluetoothDevice;

public interface NonBeaconLeScanCallback {
    void onNonBeaconLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
