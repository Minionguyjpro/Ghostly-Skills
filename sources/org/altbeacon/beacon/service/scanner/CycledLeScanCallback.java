package org.altbeacon.beacon.service.scanner;

import android.bluetooth.BluetoothDevice;

public interface CycledLeScanCallback {
    void onCycleEnd();

    void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
