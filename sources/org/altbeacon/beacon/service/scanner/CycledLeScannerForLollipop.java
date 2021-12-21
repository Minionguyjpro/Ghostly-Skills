package org.altbeacon.beacon.service.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.SystemClock;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.DetectionTracker;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public class CycledLeScannerForLollipop extends CycledLeScanner {
    private ScanCallback leScanCallback;
    private long mBackgroundLScanFirstDetectionTime = 0;
    /* access modifiers changed from: private */
    public long mBackgroundLScanStartTime = 0;
    private final BeaconManager mBeaconManager = BeaconManager.getInstanceForApplication(this.mContext);
    private boolean mMainScanCycleActive = false;
    private BluetoothLeScanner mScanner;

    public CycledLeScannerForLollipop(Context context, long j, long j2, boolean z, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver bluetoothCrashResolver) {
        super(context, j, j2, z, cycledLeScanCallback, bluetoothCrashResolver);
    }

    /* access modifiers changed from: protected */
    public void stopScan() {
        postStopLeScan();
    }

    /* access modifiers changed from: protected */
    public boolean deferScanIfNeeded() {
        long elapsedRealtime = this.mNextScanCycleStartTime - SystemClock.elapsedRealtime();
        boolean z = elapsedRealtime > 0;
        boolean z2 = this.mMainScanCycleActive;
        this.mMainScanCycleActive = !z;
        if (z) {
            long elapsedRealtime2 = SystemClock.elapsedRealtime() - DetectionTracker.getInstance().getLastDetectionTime();
            if (z2) {
                if (elapsedRealtime2 > 10000) {
                    this.mBackgroundLScanStartTime = SystemClock.elapsedRealtime();
                    this.mBackgroundLScanFirstDetectionTime = 0;
                    LogManager.d("CycledLeScannerForLollipop", "This is Android L. Preparing to do a filtered scan for the background.", new Object[0]);
                    if (this.mBetweenScanPeriod > 6000) {
                        startScan();
                    } else {
                        LogManager.d("CycledLeScannerForLollipop", "Suppressing scan between cycles because the between scan cycle is too short.", new Object[0]);
                    }
                } else {
                    LogManager.d("CycledLeScannerForLollipop", "This is Android L, but we last saw a beacon only %s ago, so we will not keep scanning in background.", Long.valueOf(elapsedRealtime2));
                }
            }
            if (this.mBackgroundLScanStartTime > 0 && DetectionTracker.getInstance().getLastDetectionTime() > this.mBackgroundLScanStartTime) {
                if (this.mBackgroundLScanFirstDetectionTime == 0) {
                    this.mBackgroundLScanFirstDetectionTime = DetectionTracker.getInstance().getLastDetectionTime();
                }
                if (SystemClock.elapsedRealtime() - this.mBackgroundLScanFirstDetectionTime >= 10000) {
                    LogManager.d("CycledLeScannerForLollipop", "We've been detecting for a bit.  Stopping Android L background scanning", new Object[0]);
                    stopScan();
                    this.mBackgroundLScanStartTime = 0;
                } else {
                    LogManager.d("CycledLeScannerForLollipop", "Delivering Android L background scanning results", new Object[0]);
                    this.mCycledLeScanCallback.onCycleEnd();
                }
            }
            LogManager.d("CycledLeScannerForLollipop", "Waiting to start full Bluetooth scan for another %s milliseconds", Long.valueOf(elapsedRealtime));
            if (z2 && this.mBackgroundFlag) {
                setWakeUpAlarm();
            }
            Handler handler = this.mHandler;
            AnonymousClass1 r4 = new Runnable() {
                public void run() {
                    CycledLeScannerForLollipop.this.scanLeDevice(true);
                }
            };
            if (elapsedRealtime > 1000) {
                elapsedRealtime = 1000;
            }
            handler.postDelayed(r4, elapsedRealtime);
        } else if (this.mBackgroundLScanStartTime > 0) {
            stopScan();
            this.mBackgroundLScanStartTime = 0;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void startScan() {
        List<ScanFilter> list;
        ScanSettings scanSettings;
        if (!isBluetoothOn()) {
            LogManager.d("CycledLeScannerForLollipop", "Not starting scan because bluetooth is off", new Object[0]);
            return;
        }
        new ArrayList();
        if (!this.mMainScanCycleActive) {
            LogManager.d("CycledLeScannerForLollipop", "starting filtered scan in SCAN_MODE_LOW_POWER", new Object[0]);
            scanSettings = new ScanSettings.Builder().setScanMode(0).build();
            list = new ScanFilterUtils().createScanFiltersForBeaconParsers(this.mBeaconManager.getBeaconParsers());
        } else {
            LogManager.d("CycledLeScannerForLollipop", "starting non-filtered scan in SCAN_MODE_LOW_LATENCY", new Object[0]);
            scanSettings = new ScanSettings.Builder().setScanMode(2).build();
            list = new ScanFilterUtils().createWildcardScanFilters();
        }
        if (scanSettings != null) {
            postStartLeScan(list, scanSettings);
        }
    }

    /* access modifiers changed from: protected */
    public void finishScan() {
        LogManager.d("CycledLeScannerForLollipop", "Stopping scan", new Object[0]);
        stopScan();
        this.mScanningPaused = true;
    }

    private void postStartLeScan(List<ScanFilter> list, ScanSettings scanSettings) {
        final BluetoothLeScanner scanner = getScanner();
        if (scanner != null) {
            final ScanCallback newLeScanCallback = getNewLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages((Object) null);
            final List<ScanFilter> list2 = list;
            final ScanSettings scanSettings2 = scanSettings;
            this.mScanHandler.post(new Runnable() {
                public void run() {
                    try {
                        scanner.startScan(list2, scanSettings2, newLeScanCallback);
                    } catch (IllegalStateException unused) {
                        LogManager.w("CycledLeScannerForLollipop", "Cannot start scan. Bluetooth may be turned off.", new Object[0]);
                    } catch (NullPointerException e) {
                        LogManager.e(e, "CycledLeScannerForLollipop", "Cannot start scan. Unexpected NPE.", new Object[0]);
                    } catch (SecurityException unused2) {
                        LogManager.e("CycledLeScannerForLollipop", "Cannot start scan.  Security Exception", new Object[0]);
                    }
                }
            });
        }
    }

    private void postStopLeScan() {
        if (!isBluetoothOn()) {
            LogManager.d("CycledLeScannerForLollipop", "Not stopping scan because bluetooth is off", new Object[0]);
            return;
        }
        final BluetoothLeScanner scanner = getScanner();
        if (scanner != null) {
            final ScanCallback newLeScanCallback = getNewLeScanCallback();
            this.mScanHandler.removeCallbacksAndMessages((Object) null);
            this.mScanHandler.post(new Runnable() {
                public void run() {
                    try {
                        LogManager.d("CycledLeScannerForLollipop", "Stopping LE scan on scan handler", new Object[0]);
                        scanner.stopScan(newLeScanCallback);
                    } catch (IllegalStateException unused) {
                        LogManager.w("CycledLeScannerForLollipop", "Cannot stop scan. Bluetooth may be turned off.", new Object[0]);
                    } catch (NullPointerException e) {
                        LogManager.e(e, "CycledLeScannerForLollipop", "Cannot stop scan. Unexpected NPE.", new Object[0]);
                    } catch (SecurityException unused2) {
                        LogManager.e("CycledLeScannerForLollipop", "Cannot stop scan.  Security Exception", new Object[0]);
                    }
                }
            });
        }
    }

    private boolean isBluetoothOn() {
        try {
            BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
            if (bluetoothAdapter == null) {
                LogManager.w("CycledLeScannerForLollipop", "Cannot get bluetooth adapter", new Object[0]);
                return false;
            } else if (bluetoothAdapter.getState() == 12) {
                return true;
            } else {
                return false;
            }
        } catch (SecurityException unused) {
            LogManager.w("CycledLeScannerForLollipop", "SecurityException checking if bluetooth is on", new Object[0]);
        }
    }

    private BluetoothLeScanner getScanner() {
        try {
            if (this.mScanner == null) {
                LogManager.d("CycledLeScannerForLollipop", "Making new Android L scanner", new Object[0]);
                if (getBluetoothAdapter() != null) {
                    this.mScanner = getBluetoothAdapter().getBluetoothLeScanner();
                }
                if (this.mScanner == null) {
                    LogManager.w("CycledLeScannerForLollipop", "Failed to make new Android L scanner", new Object[0]);
                }
            }
        } catch (SecurityException unused) {
            LogManager.w("CycledLeScannerForLollipop", "SecurityException making new Android L scanner", new Object[0]);
        }
        return this.mScanner;
    }

    private ScanCallback getNewLeScanCallback() {
        if (this.leScanCallback == null) {
            this.leScanCallback = new ScanCallback() {
                public void onScanResult(int i, ScanResult scanResult) {
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.d("CycledLeScannerForLollipop", "got record", new Object[0]);
                        List<ParcelUuid> serviceUuids = scanResult.getScanRecord().getServiceUuids();
                        if (serviceUuids != null) {
                            for (ParcelUuid parcelUuid : serviceUuids) {
                                LogManager.d("CycledLeScannerForLollipop", "with service uuid: " + parcelUuid, new Object[0]);
                            }
                        }
                    }
                    CycledLeScannerForLollipop.this.mCycledLeScanCallback.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
                    if (CycledLeScannerForLollipop.this.mBackgroundLScanStartTime > 0) {
                        LogManager.d("CycledLeScannerForLollipop", "got a filtered scan result in the background.", new Object[0]);
                    }
                }

                public void onBatchScanResults(List<ScanResult> list) {
                    LogManager.d("CycledLeScannerForLollipop", "got batch records", new Object[0]);
                    for (ScanResult next : list) {
                        CycledLeScannerForLollipop.this.mCycledLeScanCallback.onLeScan(next.getDevice(), next.getRssi(), next.getScanRecord().getBytes());
                    }
                    if (CycledLeScannerForLollipop.this.mBackgroundLScanStartTime > 0) {
                        LogManager.d("CycledLeScannerForLollipop", "got a filtered batch scan result in the background.", new Object[0]);
                    }
                }

                public void onScanFailed(int i) {
                    Intent intent = new Intent("onScanFailed");
                    intent.putExtra("errorCode", i);
                    LocalBroadcastManager.getInstance(CycledLeScannerForLollipop.this.mContext).sendBroadcast(intent);
                    if (i == 1) {
                        LogManager.e("CycledLeScannerForLollipop", "Scan failed: a BLE scan with the same settings is already started by the app", new Object[0]);
                    } else if (i == 2) {
                        LogManager.e("CycledLeScannerForLollipop", "Scan failed: app cannot be registered", new Object[0]);
                    } else if (i == 3) {
                        LogManager.e("CycledLeScannerForLollipop", "Scan failed: internal error", new Object[0]);
                    } else if (i != 4) {
                        LogManager.e("CycledLeScannerForLollipop", "Scan failed with unknown error (errorCode=" + i + ")", new Object[0]);
                    } else {
                        LogManager.e("CycledLeScannerForLollipop", "Scan failed: power optimized scan feature is not supported", new Object[0]);
                    }
                }
            };
        }
        return this.leScanCallback;
    }
}
