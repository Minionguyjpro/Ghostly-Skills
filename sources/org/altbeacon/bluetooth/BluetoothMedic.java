package org.altbeacon.bluetooth;

import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothMedic {
    /* access modifiers changed from: private */
    public static final String TAG = BluetoothMedic.class.getSimpleName();
    private static BluetoothMedic sInstance;
    /* access modifiers changed from: private */
    public BluetoothAdapter mAdapter;
    /* access modifiers changed from: private */
    public BroadcastReceiver mBluetoothEventReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LogManager.d(BluetoothMedic.TAG, "Broadcast notification received.", new Object[0]);
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (action.equalsIgnoreCase("onScanFailed")) {
                if (intent.getIntExtra("errorCode", -1) == 2) {
                    BluetoothMedic.this.sendNotification(context, "scan failed", "Power cycling bluetooth");
                    LogManager.d(BluetoothMedic.TAG, "Detected a SCAN_FAILED_APPLICATION_REGISTRATION_FAILED.  We need to cycle bluetooth to recover", new Object[0]);
                    if (!BluetoothMedic.this.cycleBluetoothIfNotTooSoon()) {
                        BluetoothMedic.this.sendNotification(context, "scan failed", "Cannot power cycle bluetooth again");
                    }
                }
            } else if (!action.equalsIgnoreCase("onStartFailed")) {
                LogManager.d(BluetoothMedic.TAG, "Unknown event.", new Object[0]);
            } else if (intent.getIntExtra("errorCode", -1) == 4) {
                BluetoothMedic.this.sendNotification(context, "advertising failed", "Expected failure.  Power cycling.");
                if (!BluetoothMedic.this.cycleBluetoothIfNotTooSoon()) {
                    BluetoothMedic.this.sendNotification(context, "advertising failed", "Cannot power cycle bluetooth again");
                }
            }
        }
    };
    private Handler mHandler = new Handler();
    private long mLastBluetoothPowerCycleTime = 0;
    /* access modifiers changed from: private */
    public LocalBroadcastManager mLocalBroadcastManager;
    private int mNotificationIcon = 0;
    private boolean mNotificationsEnabled = false;
    /* access modifiers changed from: private */
    public Boolean mScanTestResult = null;
    private int mTestType = 0;
    /* access modifiers changed from: private */
    public Boolean mTransmitterTestResult = null;

    public static BluetoothMedic getInstance() {
        if (sInstance == null) {
            sInstance = new BluetoothMedic();
        }
        return sInstance;
    }

    private BluetoothMedic() {
    }

    private void initializeWithContext(Context context) {
        if (this.mAdapter == null || this.mLocalBroadcastManager == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
            if (bluetoothManager != null) {
                this.mAdapter = bluetoothManager.getAdapter();
                this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
                return;
            }
            throw new NullPointerException("Cannot get BluetoothManager");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.stopScan(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean runScanTest(final android.content.Context r10) {
        /*
            r9 = this;
            r9.initializeWithContext(r10)
            r0 = 0
            r9.mScanTestResult = r0
            java.lang.String r0 = TAG
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "Starting scan test"
            org.altbeacon.beacon.logging.LogManager.i(r0, r3, r2)
            long r2 = java.lang.System.currentTimeMillis()
            android.bluetooth.BluetoothAdapter r0 = r9.mAdapter
            if (r0 == 0) goto L_0x005d
            android.bluetooth.le.BluetoothLeScanner r0 = r0.getBluetoothLeScanner()
            org.altbeacon.bluetooth.BluetoothMedic$2 r4 = new org.altbeacon.bluetooth.BluetoothMedic$2
            r4.<init>(r0, r10)
            if (r0 == 0) goto L_0x0054
            r0.startScan(r4)
        L_0x0026:
            java.lang.Boolean r10 = r9.mScanTestResult
            if (r10 != 0) goto L_0x004e
            java.lang.String r10 = TAG
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.String r6 = "Waiting for scan test to complete..."
            org.altbeacon.beacon.logging.LogManager.d(r10, r6, r5)
            r5 = 1000(0x3e8, double:4.94E-321)
            java.lang.Thread.sleep(r5)     // Catch:{ InterruptedException -> 0x0039 }
            goto L_0x003a
        L_0x0039:
        L_0x003a:
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r2
            r7 = 5000(0x1388, double:2.4703E-320)
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 <= 0) goto L_0x0026
            java.lang.String r10 = TAG
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "Timeout running scan test"
            org.altbeacon.beacon.logging.LogManager.d(r10, r3, r2)
        L_0x004e:
            r0.stopScan(r4)     // Catch:{ IllegalStateException -> 0x0052 }
            goto L_0x005d
        L_0x0052:
            goto L_0x005d
        L_0x0054:
            java.lang.String r10 = TAG
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.String r2 = "Cannot get scanner"
            org.altbeacon.beacon.logging.LogManager.d(r10, r2, r0)
        L_0x005d:
            java.lang.String r10 = TAG
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.String r2 = "scan test complete"
            org.altbeacon.beacon.logging.LogManager.d(r10, r2, r0)
            java.lang.Boolean r10 = r9.mScanTestResult
            if (r10 == 0) goto L_0x0070
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x0071
        L_0x0070:
            r1 = 1
        L_0x0071:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.bluetooth.BluetoothMedic.runScanTest(android.content.Context):boolean");
    }

    public boolean runTransmitterTest(final Context context) {
        initializeWithContext(context);
        this.mTransmitterTestResult = null;
        long currentTimeMillis = System.currentTimeMillis();
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            final BluetoothLeAdvertiser bluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
            if (bluetoothLeAdvertiser != null) {
                AdvertiseSettings build = new AdvertiseSettings.Builder().setAdvertiseMode(0).build();
                AdvertiseData build2 = new AdvertiseData.Builder().addManufacturerData(0, new byte[]{0}).build();
                LogManager.i(TAG, "Starting transmitter test", new Object[0]);
                bluetoothLeAdvertiser.startAdvertising(build, build2, new AdvertiseCallback() {
                    public void onStartSuccess(AdvertiseSettings advertiseSettings) {
                        super.onStartSuccess(advertiseSettings);
                        LogManager.i(BluetoothMedic.TAG, "Transmitter test succeeded", new Object[0]);
                        bluetoothLeAdvertiser.stopAdvertising(this);
                        Boolean unused = BluetoothMedic.this.mTransmitterTestResult = true;
                    }

                    public void onStartFailure(int i) {
                        super.onStartFailure(i);
                        Intent intent = new Intent("onStartFailed");
                        intent.putExtra("errorCode", i);
                        String access$000 = BluetoothMedic.TAG;
                        LogManager.d(access$000, "Sending onStartFailure broadcast with " + BluetoothMedic.this.mLocalBroadcastManager, new Object[0]);
                        if (BluetoothMedic.this.mLocalBroadcastManager != null) {
                            BluetoothMedic.this.mLocalBroadcastManager.sendBroadcast(intent);
                        }
                        if (i == 4) {
                            Boolean unused = BluetoothMedic.this.mTransmitterTestResult = false;
                            LogManager.w(BluetoothMedic.TAG, "Transmitter test failed in a way we consider a test failure", new Object[0]);
                            BluetoothMedic.this.sendNotification(context, "transmitter failed", "bluetooth not ok");
                            return;
                        }
                        Boolean unused2 = BluetoothMedic.this.mTransmitterTestResult = true;
                        LogManager.i(BluetoothMedic.TAG, "Transmitter test failed, but not in a way we consider a test failure", new Object[0]);
                    }
                });
            } else {
                LogManager.d(TAG, "Cannot get advertiser", new Object[0]);
            }
            while (true) {
                if (this.mTransmitterTestResult != null) {
                    break;
                }
                LogManager.d(TAG, "Waiting for transmitter test to complete...", new Object[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException unused) {
                }
                if (System.currentTimeMillis() - currentTimeMillis > 5000) {
                    LogManager.d(TAG, "Timeout running transmitter test", new Object[0]);
                    break;
                }
            }
        }
        LogManager.d(TAG, "transmitter test complete", new Object[0]);
        Boolean bool = this.mTransmitterTestResult;
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean cycleBluetoothIfNotTooSoon() {
        long currentTimeMillis = System.currentTimeMillis() - this.mLastBluetoothPowerCycleTime;
        if (currentTimeMillis < 60000) {
            String str = TAG;
            LogManager.d(str, "Not cycling bluetooth because we just did so " + currentTimeMillis + " milliseconds ago.", new Object[0]);
            return false;
        }
        this.mLastBluetoothPowerCycleTime = System.currentTimeMillis();
        LogManager.d(TAG, "Power cycling bluetooth", new Object[0]);
        cycleBluetooth();
        return true;
    }

    private void cycleBluetooth() {
        LogManager.d(TAG, "Power cycling bluetooth", new Object[0]);
        LogManager.d(TAG, "Turning Bluetooth off.", new Object[0]);
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.disable();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    LogManager.d(BluetoothMedic.TAG, "Turning Bluetooth back on.", new Object[0]);
                    if (BluetoothMedic.this.mAdapter != null) {
                        BluetoothMedic.this.mAdapter.enable();
                    }
                }
            }, 1000);
            return;
        }
        LogManager.w(TAG, "Cannot cycle bluetooth.  Manager is null.", new Object[0]);
    }

    /* access modifiers changed from: private */
    public void sendNotification(Context context, String str, String str2) {
        initializeWithContext(context);
        if (this.mNotificationsEnabled) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "err");
            NotificationCompat.Builder contentText = builder.setContentTitle("BluetoothMedic: " + str).setSmallIcon(this.mNotificationIcon).setVibrate(new long[]{200, 100, 200}).setContentText(str2);
            TaskStackBuilder create = TaskStackBuilder.create(context);
            create.addNextIntent(new Intent("NoOperation"));
            contentText.setContentIntent(create.getPendingIntent(0, 134217728));
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(1, contentText.build());
            }
        }
    }
}
