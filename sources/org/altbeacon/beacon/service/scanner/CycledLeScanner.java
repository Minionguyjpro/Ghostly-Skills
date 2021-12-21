package org.altbeacon.beacon.service.scanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import java.util.Date;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public abstract class CycledLeScanner {
    protected boolean mBackgroundFlag = false;
    protected long mBetweenScanPeriod;
    private BluetoothAdapter mBluetoothAdapter;
    protected final BluetoothCrashResolver mBluetoothCrashResolver;
    protected final Context mContext;
    private long mCurrentScanStartTime = 0;
    protected final CycledLeScanCallback mCycledLeScanCallback;
    private volatile boolean mDistinctPacketsDetectedPerScan = false;
    protected final Handler mHandler = new Handler(Looper.getMainLooper());
    private long mLastScanCycleEndTime = 0;
    private long mLastScanCycleStartTime = 0;
    private boolean mLongScanForcingEnabled = false;
    protected long mNextScanCycleStartTime = 0;
    protected boolean mRestartNeeded = false;
    private long mScanCycleStopTime = 0;
    private boolean mScanCyclerStarted = false;
    protected final Handler mScanHandler;
    private long mScanPeriod;
    /* access modifiers changed from: private */
    public final HandlerThread mScanThread;
    private boolean mScanning;
    private boolean mScanningEnabled = false;
    private boolean mScanningLeftOn = false;
    protected boolean mScanningPaused;
    private PendingIntent mWakeUpOperation = null;

    /* access modifiers changed from: protected */
    public abstract boolean deferScanIfNeeded();

    /* access modifiers changed from: protected */
    public abstract void finishScan();

    /* access modifiers changed from: protected */
    public abstract void startScan();

    /* access modifiers changed from: protected */
    public abstract void stopScan();

    protected CycledLeScanner(Context context, long j, long j2, boolean z, CycledLeScanCallback cycledLeScanCallback, BluetoothCrashResolver bluetoothCrashResolver) {
        this.mScanPeriod = j;
        this.mBetweenScanPeriod = j2;
        this.mContext = context;
        this.mCycledLeScanCallback = cycledLeScanCallback;
        this.mBluetoothCrashResolver = bluetoothCrashResolver;
        this.mBackgroundFlag = z;
        HandlerThread handlerThread = new HandlerThread("CycledLeScannerThread");
        this.mScanThread = handlerThread;
        handlerThread.start();
        this.mScanHandler = new Handler(this.mScanThread.getLooper());
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.altbeacon.beacon.service.scanner.CycledLeScanner createScanner(android.content.Context r14, long r15, long r17, boolean r19, org.altbeacon.beacon.service.scanner.CycledLeScanCallback r20, org.altbeacon.bluetooth.BluetoothCrashResolver r21) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "CycledLeScanner"
            r2 = 0
            r3 = 18
            if (r0 >= r3) goto L_0x0012
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r2 = "Not supported prior to API 18."
            org.altbeacon.beacon.logging.LogManager.w(r1, r2, r0)
            r0 = 0
            return r0
        L_0x0012:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            r4 = 1
            if (r0 >= r3) goto L_0x0022
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r3 = "This is pre Android 5.0.  We are using old scanning APIs"
            org.altbeacon.beacon.logging.LogManager.i(r1, r3, r0)
        L_0x0020:
            r4 = 0
            goto L_0x0047
        L_0x0022:
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            if (r0 >= r3) goto L_0x003e
            boolean r0 = org.altbeacon.beacon.BeaconManager.isAndroidLScanningDisabled()
            if (r0 == 0) goto L_0x0036
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r3 = "This is Android 5.0, but L scanning is disabled. We are using old scanning APIs"
            org.altbeacon.beacon.logging.LogManager.i(r1, r3, r0)
            goto L_0x0020
        L_0x0036:
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r3 = "This is Android 5.0.  We are using new scanning APIs"
            org.altbeacon.beacon.logging.LogManager.i(r1, r3, r0)
            goto L_0x0047
        L_0x003e:
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r3 = "Using Android O scanner"
            org.altbeacon.beacon.logging.LogManager.i(r1, r3, r0)
            r2 = 1
            goto L_0x0020
        L_0x0047:
            if (r2 == 0) goto L_0x005a
            org.altbeacon.beacon.service.scanner.CycledLeScannerForAndroidO r0 = new org.altbeacon.beacon.service.scanner.CycledLeScannerForAndroidO
            r5 = r0
            r6 = r14
            r7 = r15
            r9 = r17
            r11 = r19
            r12 = r20
            r13 = r21
            r5.<init>(r6, r7, r9, r11, r12, r13)
            return r0
        L_0x005a:
            if (r4 == 0) goto L_0x006d
            org.altbeacon.beacon.service.scanner.CycledLeScannerForLollipop r0 = new org.altbeacon.beacon.service.scanner.CycledLeScannerForLollipop
            r1 = r0
            r2 = r14
            r3 = r15
            r5 = r17
            r7 = r19
            r8 = r20
            r9 = r21
            r1.<init>(r2, r3, r5, r7, r8, r9)
            return r0
        L_0x006d:
            org.altbeacon.beacon.service.scanner.CycledLeScannerForJellyBeanMr2 r0 = new org.altbeacon.beacon.service.scanner.CycledLeScannerForJellyBeanMr2
            r1 = r0
            r2 = r14
            r3 = r15
            r5 = r17
            r7 = r19
            r8 = r20
            r9 = r21
            r1.<init>(r2, r3, r5, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.scanner.CycledLeScanner.createScanner(android.content.Context, long, long, boolean, org.altbeacon.beacon.service.scanner.CycledLeScanCallback, org.altbeacon.bluetooth.BluetoothCrashResolver):org.altbeacon.beacon.service.scanner.CycledLeScanner");
    }

    public void setLongScanForcingEnabled(boolean z) {
        this.mLongScanForcingEnabled = z;
    }

    public void setScanPeriods(long j, long j2, boolean z) {
        LogManager.d("CycledLeScanner", "Set scan periods called with %s, %s Background mode must have changed.", Long.valueOf(j), Long.valueOf(j2));
        if (this.mBackgroundFlag != z) {
            this.mRestartNeeded = true;
        }
        this.mBackgroundFlag = z;
        this.mScanPeriod = j;
        this.mBetweenScanPeriod = j2;
        if (z) {
            LogManager.d("CycledLeScanner", "We are in the background.  Setting wakeup alarm", new Object[0]);
            setWakeUpAlarm();
        } else {
            LogManager.d("CycledLeScanner", "We are not in the background.  Cancelling wakeup alarm", new Object[0]);
            cancelWakeUpAlarm();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = this.mNextScanCycleStartTime;
        if (j3 > elapsedRealtime) {
            long j4 = this.mLastScanCycleEndTime + j2;
            if (j4 < j3) {
                this.mNextScanCycleStartTime = j4;
                LogManager.i("CycledLeScanner", "Adjusted nextScanStartTime to be %s", new Date((this.mNextScanCycleStartTime - SystemClock.elapsedRealtime()) + System.currentTimeMillis()));
            }
        }
        long j5 = this.mScanCycleStopTime;
        if (j5 > elapsedRealtime) {
            long j6 = this.mLastScanCycleStartTime + j;
            if (j6 < j5) {
                this.mScanCycleStopTime = j6;
                LogManager.i("CycledLeScanner", "Adjusted scanStopTime to be %s", Long.valueOf(j6));
            }
        }
    }

    public void start() {
        LogManager.d("CycledLeScanner", "start called", new Object[0]);
        this.mScanningEnabled = true;
        if (!this.mScanCyclerStarted) {
            scanLeDevice(true);
        } else {
            LogManager.d("CycledLeScanner", "scanning already started", new Object[0]);
        }
    }

    public void stop() {
        LogManager.d("CycledLeScanner", "stop called", new Object[0]);
        this.mScanningEnabled = false;
        if (this.mScanCyclerStarted) {
            scanLeDevice(false);
            if (this.mScanningLeftOn) {
                LogManager.d("CycledLeScanner", "Stopping scanning previously left on.", new Object[0]);
                this.mScanningLeftOn = false;
                try {
                    LogManager.d("CycledLeScanner", "stopping bluetooth le scan", new Object[0]);
                    finishScan();
                } catch (Exception e) {
                    LogManager.w(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                }
            }
        } else {
            LogManager.d("CycledLeScanner", "scanning already stopped", new Object[0]);
        }
    }

    public boolean getDistinctPacketsDetectedPerScan() {
        return this.mDistinctPacketsDetectedPerScan;
    }

    public void setDistinctPacketsDetectedPerScan(boolean z) {
        this.mDistinctPacketsDetectedPerScan = z;
    }

    public void destroy() {
        LogManager.d("CycledLeScanner", "Destroying", new Object[0]);
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mScanHandler.post(new Runnable() {
            public void run() {
                LogManager.d("CycledLeScanner", "Quitting scan thread", new Object[0]);
                CycledLeScanner.this.mScanThread.quit();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void scanLeDevice(Boolean bool) {
        try {
            this.mScanCyclerStarted = true;
            if (getBluetoothAdapter() == null) {
                LogManager.e("CycledLeScanner", "No Bluetooth adapter.  beaconService cannot scan.", new Object[0]);
            }
            if (!this.mScanningEnabled || !bool.booleanValue()) {
                LogManager.d("CycledLeScanner", "disabling scan", new Object[0]);
                this.mScanning = false;
                this.mScanCyclerStarted = false;
                stopScan();
                this.mCurrentScanStartTime = 0;
                this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                this.mHandler.removeCallbacksAndMessages((Object) null);
                finishScanCycle();
            } else if (!deferScanIfNeeded()) {
                LogManager.d("CycledLeScanner", "starting a new scan cycle", new Object[0]);
                if (this.mScanning && !this.mScanningPaused) {
                    if (!this.mRestartNeeded) {
                        LogManager.d("CycledLeScanner", "We are already scanning and have been for " + (SystemClock.elapsedRealtime() - this.mCurrentScanStartTime) + " millis", new Object[0]);
                        this.mScanCycleStopTime = SystemClock.elapsedRealtime() + this.mScanPeriod;
                        scheduleScanCycleStop();
                        LogManager.d("CycledLeScanner", "Scan started", new Object[0]);
                    }
                }
                this.mScanning = true;
                this.mScanningPaused = false;
                try {
                    if (getBluetoothAdapter() != null) {
                        if (getBluetoothAdapter().isEnabled()) {
                            if (this.mBluetoothCrashResolver != null && this.mBluetoothCrashResolver.isRecoveryInProgress()) {
                                LogManager.w("CycledLeScanner", "Skipping scan because crash recovery is in progress.", new Object[0]);
                            } else if (this.mScanningEnabled) {
                                if (this.mRestartNeeded) {
                                    this.mRestartNeeded = false;
                                    LogManager.d("CycledLeScanner", "restarting a bluetooth le scan", new Object[0]);
                                } else {
                                    LogManager.d("CycledLeScanner", "starting a new bluetooth le scan", new Object[0]);
                                }
                                try {
                                    if (Build.VERSION.SDK_INT < 23 || checkLocationPermission()) {
                                        this.mCurrentScanStartTime = SystemClock.elapsedRealtime();
                                        startScan();
                                    }
                                } catch (Exception e) {
                                    LogManager.e(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                                }
                            } else {
                                LogManager.d("CycledLeScanner", "Scanning unnecessary - no monitoring or ranging active.", new Object[0]);
                            }
                            this.mLastScanCycleStartTime = SystemClock.elapsedRealtime();
                        } else {
                            LogManager.d("CycledLeScanner", "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                        }
                    }
                } catch (Exception e2) {
                    LogManager.e(e2, "CycledLeScanner", "Exception starting Bluetooth scan.  Perhaps Bluetooth is disabled or unavailable?", new Object[0]);
                }
                this.mScanCycleStopTime = SystemClock.elapsedRealtime() + this.mScanPeriod;
                scheduleScanCycleStop();
                LogManager.d("CycledLeScanner", "Scan started", new Object[0]);
            }
        } catch (SecurityException unused) {
            LogManager.w("CycledLeScanner", "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void scheduleScanCycleStop() {
        long elapsedRealtime = this.mScanCycleStopTime - SystemClock.elapsedRealtime();
        if (!this.mScanningEnabled || elapsedRealtime <= 0) {
            finishScanCycle();
            return;
        }
        LogManager.d("CycledLeScanner", "Waiting to stop scan cycle for another %s milliseconds", Long.valueOf(elapsedRealtime));
        if (this.mBackgroundFlag) {
            setWakeUpAlarm();
        }
        Handler handler = this.mHandler;
        AnonymousClass2 r3 = new Runnable() {
            public void run() {
                CycledLeScanner.this.scheduleScanCycleStop();
            }
        };
        if (elapsedRealtime > 1000) {
            elapsedRealtime = 1000;
        }
        handler.postDelayed(r3, elapsedRealtime);
    }

    private void finishScanCycle() {
        LogManager.d("CycledLeScanner", "Done with scan cycle", new Object[0]);
        try {
            this.mCycledLeScanCallback.onCycleEnd();
            if (this.mScanning) {
                if (getBluetoothAdapter() != null) {
                    if (getBluetoothAdapter().isEnabled()) {
                        if (this.mDistinctPacketsDetectedPerScan && this.mBetweenScanPeriod == 0) {
                            if (!mustStopScanToPreventAndroidNScanTimeout()) {
                                LogManager.d("CycledLeScanner", "Not stopping scanning.  Device capable of multiple indistinct detections per scan.", new Object[0]);
                                this.mScanningLeftOn = true;
                                this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                            }
                        }
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (Build.VERSION.SDK_INT < 24 || this.mBetweenScanPeriod + this.mScanPeriod >= 6000 || elapsedRealtime - this.mLastScanCycleStartTime >= 6000) {
                            try {
                                LogManager.d("CycledLeScanner", "stopping bluetooth le scan", new Object[0]);
                                finishScan();
                                this.mScanningLeftOn = false;
                            } catch (Exception e) {
                                LogManager.w(e, "CycledLeScanner", "Internal Android exception scanning for beacons", new Object[0]);
                            }
                            this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                        } else {
                            LogManager.d("CycledLeScanner", "Not stopping scan because this is Android N and we keep scanning for a minimum of 6 seconds at a time. We will stop in " + (6000 - (elapsedRealtime - this.mLastScanCycleStartTime)) + " millisconds.", new Object[0]);
                            this.mScanningLeftOn = true;
                            this.mLastScanCycleEndTime = SystemClock.elapsedRealtime();
                        }
                    } else {
                        LogManager.d("CycledLeScanner", "Bluetooth is disabled.  Cannot scan for beacons.", new Object[0]);
                        this.mRestartNeeded = true;
                    }
                }
                this.mNextScanCycleStartTime = getNextScanStartTime();
                if (this.mScanningEnabled) {
                    scanLeDevice(true);
                }
            }
            if (!this.mScanningEnabled) {
                LogManager.d("CycledLeScanner", "Scanning disabled. ", new Object[0]);
                this.mScanCyclerStarted = false;
                cancelWakeUpAlarm();
            }
        } catch (SecurityException unused) {
            LogManager.w("CycledLeScanner", "SecurityException working accessing bluetooth.", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public BluetoothAdapter getBluetoothAdapter() {
        try {
            if (this.mBluetoothAdapter == null) {
                BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter();
                this.mBluetoothAdapter = adapter;
                if (adapter == null) {
                    LogManager.w("CycledLeScanner", "Failed to construct a BluetoothAdapter", new Object[0]);
                }
            }
        } catch (SecurityException unused) {
            LogManager.e("CycledLeScanner", "Cannot consruct bluetooth adapter.  Security Exception", new Object[0]);
        }
        return this.mBluetoothAdapter;
    }

    /* access modifiers changed from: protected */
    public void setWakeUpAlarm() {
        long j = this.mBetweenScanPeriod;
        if (300000 >= j) {
            j = 300000;
        }
        long j2 = this.mScanPeriod;
        if (j < j2) {
            j = j2;
        }
        ((AlarmManager) this.mContext.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + j, getWakeUpOperation());
        LogManager.d("CycledLeScanner", "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(j), getWakeUpOperation());
    }

    /* access modifiers changed from: protected */
    public PendingIntent getWakeUpOperation() {
        if (this.mWakeUpOperation == null) {
            Intent intent = new Intent(this.mContext, StartupBroadcastReceiver.class);
            intent.putExtra("wakeup", true);
            this.mWakeUpOperation = PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728);
        }
        return this.mWakeUpOperation;
    }

    /* access modifiers changed from: protected */
    public void cancelWakeUpAlarm() {
        LogManager.d("CycledLeScanner", "cancel wakeup alarm: %s", this.mWakeUpOperation);
        ((AlarmManager) this.mContext.getSystemService("alarm")).set(2, Long.MAX_VALUE, getWakeUpOperation());
        LogManager.d("CycledLeScanner", "Set a wakeup alarm to go off in %s ms: %s", Long.valueOf(Long.MAX_VALUE - SystemClock.elapsedRealtime()), getWakeUpOperation());
    }

    private long getNextScanStartTime() {
        long j = this.mBetweenScanPeriod;
        if (j == 0) {
            return SystemClock.elapsedRealtime();
        }
        long elapsedRealtime = j - (SystemClock.elapsedRealtime() % (this.mScanPeriod + j));
        LogManager.d("CycledLeScanner", "Normalizing between scan period from %s to %s", Long.valueOf(this.mBetweenScanPeriod), Long.valueOf(elapsedRealtime));
        return SystemClock.elapsedRealtime() + elapsedRealtime;
    }

    private boolean checkLocationPermission() {
        return checkPermission("android.permission.ACCESS_COARSE_LOCATION") || checkPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean checkPermission(String str) {
        return this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean mustStopScanToPreventAndroidNScanTimeout() {
        /*
            r9 = this;
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r2 = r9.mBetweenScanPeriod
            long r0 = r0 + r2
            long r2 = r9.mScanPeriod
            long r0 = r0 + r2
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 1
            r4 = 0
            r5 = 24
            if (r2 < r5) goto L_0x0024
            long r5 = r9.mCurrentScanStartTime
            r7 = 0
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x0024
            long r0 = r0 - r5
            r5 = 1800000(0x1b7740, double:8.89318E-318)
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x0024
            r0 = 1
            goto L_0x0025
        L_0x0024:
            r0 = 0
        L_0x0025:
            if (r0 == 0) goto L_0x0043
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r1 = "CycledLeScanner"
            java.lang.String r2 = "The next scan cycle would go over the Android N max duration."
            org.altbeacon.beacon.logging.LogManager.d(r1, r2, r0)
            boolean r0 = r9.mLongScanForcingEnabled
            if (r0 == 0) goto L_0x003c
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Stopping scan to prevent Android N scan timeout."
            org.altbeacon.beacon.logging.LogManager.d(r1, r2, r0)
            return r3
        L_0x003c:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Allowing a long running scan to be stopped by the OS.  To prevent this, set longScanForcingEnabled in the AndroidBeaconLibrary."
            org.altbeacon.beacon.logging.LogManager.w(r1, r2, r0)
        L_0x0043:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.scanner.CycledLeScanner.mustStopScanToPreventAndroidNScanTimeout():boolean");
    }
}
