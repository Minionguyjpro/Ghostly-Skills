package org.altbeacon.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemClock;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashSet;
import java.util.Set;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothCrashResolver {
    private Context context = null;
    private int detectedCrashCount = 0;
    /* access modifiers changed from: private */
    public boolean discoveryStartConfirmed = false;
    private final Set<String> distinctBluetoothAddresses = new HashSet();
    private long lastBluetoothCrashDetectionTime = 0;
    /* access modifiers changed from: private */
    public long lastBluetoothOffTime = 0;
    /* access modifiers changed from: private */
    public long lastBluetoothTurningOnTime = 0;
    private boolean lastRecoverySucceeded = false;
    private long lastStateSaveTime = 0;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                if (BluetoothCrashResolver.this.recoveryInProgress) {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery finished", new Object[0]);
                    BluetoothCrashResolver.this.finishRecovery();
                } else {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery finished (external)", new Object[0]);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                if (BluetoothCrashResolver.this.recoveryInProgress) {
                    boolean unused = BluetoothCrashResolver.this.discoveryStartConfirmed = true;
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery started", new Object[0]);
                } else {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth discovery started (external)", new Object[0]);
                }
            }
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", RecyclerView.UNDEFINED_DURATION);
                if (intExtra != Integer.MIN_VALUE) {
                    switch (intExtra) {
                        case 10:
                            LogManager.d("BluetoothCrashResolver", "Bluetooth state is OFF", new Object[0]);
                            long unused2 = BluetoothCrashResolver.this.lastBluetoothOffTime = SystemClock.elapsedRealtime();
                            return;
                        case 11:
                            long unused3 = BluetoothCrashResolver.this.lastBluetoothTurningOnTime = SystemClock.elapsedRealtime();
                            LogManager.d("BluetoothCrashResolver", "Bluetooth state is TURNING_ON", new Object[0]);
                            return;
                        case 12:
                            LogManager.d("BluetoothCrashResolver", "Bluetooth state is ON", new Object[0]);
                            LogManager.d("BluetoothCrashResolver", "Bluetooth was turned off for %s milliseconds", Long.valueOf(BluetoothCrashResolver.this.lastBluetoothTurningOnTime - BluetoothCrashResolver.this.lastBluetoothOffTime));
                            if (BluetoothCrashResolver.this.lastBluetoothTurningOnTime - BluetoothCrashResolver.this.lastBluetoothOffTime < 600) {
                                BluetoothCrashResolver.this.crashDetected();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else {
                    LogManager.d("BluetoothCrashResolver", "Bluetooth state is ERROR", new Object[0]);
                }
            }
        }
    };
    private int recoveryAttemptCount = 0;
    /* access modifiers changed from: private */
    public boolean recoveryInProgress = false;
    private UpdateNotifier updateNotifier;

    public interface UpdateNotifier {
        void dataUpdated();
    }

    private int getCrashRiskDeviceCount() {
        return 1590;
    }

    public BluetoothCrashResolver(Context context2) {
        this.context = context2.getApplicationContext();
        LogManager.d("BluetoothCrashResolver", "constructed", new Object[0]);
        loadState();
    }

    public void start() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.context.registerReceiver(this.receiver, intentFilter);
        LogManager.d("BluetoothCrashResolver", "started listening for BluetoothAdapter events", new Object[0]);
    }

    public void stop() {
        this.context.unregisterReceiver(this.receiver);
        LogManager.d("BluetoothCrashResolver", "stopped listening for BluetoothAdapter events", new Object[0]);
        saveState();
    }

    public void notifyScannedDevice(BluetoothDevice bluetoothDevice, BluetoothAdapter.LeScanCallback leScanCallback) {
        int size = this.distinctBluetoothAddresses.size();
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.add(bluetoothDevice.getAddress());
        }
        int size2 = this.distinctBluetoothAddresses.size();
        if (size != size2 && size2 % 100 == 0) {
            LogManager.d("BluetoothCrashResolver", "Distinct Bluetooth devices seen: %s", Integer.valueOf(this.distinctBluetoothAddresses.size()));
        }
        if (this.distinctBluetoothAddresses.size() > getCrashRiskDeviceCount() && !this.recoveryInProgress) {
            LogManager.w("BluetoothCrashResolver", "Large number of Bluetooth devices detected: %s Proactively attempting to clear out address list to prevent a crash", Integer.valueOf(this.distinctBluetoothAddresses.size()));
            LogManager.w("BluetoothCrashResolver", "Stopping LE Scan", new Object[0]);
            BluetoothAdapter.getDefaultAdapter().stopLeScan(leScanCallback);
            startRecovery();
            processStateChange();
        }
    }

    public void crashDetected() {
        if (Build.VERSION.SDK_INT < 18) {
            LogManager.d("BluetoothCrashResolver", "Ignoring crashes before API 18, because BLE is unsupported.", new Object[0]);
            return;
        }
        LogManager.w("BluetoothCrashResolver", "BluetoothService crash detected", new Object[0]);
        if (this.distinctBluetoothAddresses.size() > 0) {
            LogManager.d("BluetoothCrashResolver", "Distinct Bluetooth devices seen at crash: %s", Integer.valueOf(this.distinctBluetoothAddresses.size()));
        }
        this.lastBluetoothCrashDetectionTime = SystemClock.elapsedRealtime();
        this.detectedCrashCount++;
        if (this.recoveryInProgress) {
            LogManager.d("BluetoothCrashResolver", "Ignoring Bluetooth crash because recovery is already in progress.", new Object[0]);
        } else {
            startRecovery();
        }
        processStateChange();
    }

    public boolean isRecoveryInProgress() {
        return this.recoveryInProgress;
    }

    private void processStateChange() {
        UpdateNotifier updateNotifier2 = this.updateNotifier;
        if (updateNotifier2 != null) {
            updateNotifier2.dataUpdated();
        }
        if (SystemClock.elapsedRealtime() - this.lastStateSaveTime > 60000) {
            saveState();
        }
    }

    private void startRecovery() {
        this.recoveryAttemptCount++;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        LogManager.d("BluetoothCrashResolver", "about to check if discovery is active", new Object[0]);
        if (!defaultAdapter.isDiscovering()) {
            LogManager.w("BluetoothCrashResolver", "Recovery attempt started", new Object[0]);
            this.recoveryInProgress = true;
            this.discoveryStartConfirmed = false;
            LogManager.d("BluetoothCrashResolver", "about to command discovery", new Object[0]);
            if (!defaultAdapter.startDiscovery()) {
                LogManager.w("BluetoothCrashResolver", "Can't start discovery.  Is Bluetooth turned on?", new Object[0]);
            }
            LogManager.d("BluetoothCrashResolver", "startDiscovery commanded.  isDiscovering()=%s", Boolean.valueOf(defaultAdapter.isDiscovering()));
            LogManager.d("BluetoothCrashResolver", "We will be cancelling this discovery in %s milliseconds.", 5000);
            cancelDiscovery();
            return;
        }
        LogManager.w("BluetoothCrashResolver", "Already discovering.  Recovery attempt abandoned.", new Object[0]);
    }

    /* access modifiers changed from: private */
    public void finishRecovery() {
        LogManager.w("BluetoothCrashResolver", "Recovery attempt finished", new Object[0]);
        synchronized (this.distinctBluetoothAddresses) {
            this.distinctBluetoothAddresses.clear();
        }
        this.recoveryInProgress = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00bb A[SYNTHETIC, Splitter:B:39:0x00bb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveState() {
        /*
            r7 = this;
            long r0 = android.os.SystemClock.elapsedRealtime()
            r7.lastStateSaveTime = r0
            r0 = 1
            r1 = 0
            r2 = 0
            android.content.Context r3 = r7.context     // Catch:{ IOException -> 0x0091 }
            java.lang.String r4 = "BluetoothCrashResolverState.txt"
            java.io.FileOutputStream r3 = r3.openFileOutput(r4, r1)     // Catch:{ IOException -> 0x0091 }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x0091 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0091 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.<init>()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            long r5 = r7.lastBluetoothCrashDetectionTime     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.append(r5)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r4.write(r2)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.<init>()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r3 = r7.detectedCrashCount     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.append(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r4.write(r2)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.<init>()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            int r3 = r7.recoveryAttemptCount     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r2.append(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            r4.write(r2)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            boolean r2 = r7.lastRecoverySucceeded     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            if (r2 == 0) goto L_0x005f
            java.lang.String r2 = "1\n"
            goto L_0x0061
        L_0x005f:
            java.lang.String r2 = "0\n"
        L_0x0061:
            r4.write(r2)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.util.Set<java.lang.String> r2 = r7.distinctBluetoothAddresses     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            monitor-enter(r2)     // Catch:{ IOException -> 0x008d, all -> 0x008a }
            java.util.Set<java.lang.String> r3 = r7.distinctBluetoothAddresses     // Catch:{ all -> 0x0087 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0087 }
        L_0x006d:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x0087 }
            if (r5 == 0) goto L_0x0082
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0087 }
            r4.write(r5)     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = "\n"
            r4.write(r5)     // Catch:{ all -> 0x0087 }
            goto L_0x006d
        L_0x0082:
            monitor-exit(r2)     // Catch:{ all -> 0x0087 }
            r4.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x00a3
        L_0x0087:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0087 }
            throw r3     // Catch:{ IOException -> 0x008d, all -> 0x008a }
        L_0x008a:
            r0 = move-exception
            r2 = r4
            goto L_0x00b9
        L_0x008d:
            r2 = r4
            goto L_0x0091
        L_0x008f:
            r0 = move-exception
            goto L_0x00b9
        L_0x0091:
            java.lang.String r3 = "BluetoothCrashResolver"
            java.lang.String r4 = "Can't write macs to %s"
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ all -> 0x008f }
            java.lang.String r6 = "BluetoothCrashResolverState.txt"
            r5[r1] = r6     // Catch:{ all -> 0x008f }
            org.altbeacon.beacon.logging.LogManager.w(r3, r4, r5)     // Catch:{ all -> 0x008f }
            if (r2 == 0) goto L_0x00a3
            r2.close()     // Catch:{ IOException -> 0x00a3 }
        L_0x00a3:
            java.lang.String r2 = "BluetoothCrashResolver"
            java.lang.String r3 = "Wrote %s Bluetooth addresses"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.util.Set<java.lang.String> r4 = r7.distinctBluetoothAddresses
            int r4 = r4.size()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0[r1] = r4
            org.altbeacon.beacon.logging.LogManager.d(r2, r3, r0)
            return
        L_0x00b9:
            if (r2 == 0) goto L_0x00be
            r2.close()     // Catch:{ IOException -> 0x00be }
        L_0x00be:
            goto L_0x00c0
        L_0x00bf:
            throw r0
        L_0x00c0:
            goto L_0x00bf
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.bluetooth.BluetoothCrashResolver.saveState():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006f, code lost:
        if (r4 != null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007e, code lost:
        if (r4 != null) goto L_0x0071;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0097 A[SYNTHETIC, Splitter:B:46:0x0097] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadState() {
        /*
            r8 = this;
            java.lang.String r0 = "BluetoothCrashResolver"
            java.lang.String r1 = "BluetoothCrashResolverState.txt"
            r2 = 0
            r3 = 1
            r4 = 0
            android.content.Context r5 = r8.context     // Catch:{ IOException -> 0x0075, NumberFormatException -> 0x0066 }
            java.io.FileInputStream r5 = r5.openFileInput(r1)     // Catch:{ IOException -> 0x0075, NumberFormatException -> 0x0066 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0075, NumberFormatException -> 0x0066 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0075, NumberFormatException -> 0x0066 }
            r7.<init>(r5)     // Catch:{ IOException -> 0x0075, NumberFormatException -> 0x0066 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x0075, NumberFormatException -> 0x0066 }
            java.lang.String r4 = r6.readLine()     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            if (r4 == 0) goto L_0x0023
            long r4 = java.lang.Long.parseLong(r4)     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            r8.lastBluetoothCrashDetectionTime = r4     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
        L_0x0023:
            java.lang.String r4 = r6.readLine()     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            if (r4 == 0) goto L_0x002f
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            r8.detectedCrashCount = r4     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
        L_0x002f:
            java.lang.String r4 = r6.readLine()     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            if (r4 == 0) goto L_0x003b
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            r8.recoveryAttemptCount = r4     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
        L_0x003b:
            java.lang.String r4 = r6.readLine()     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            if (r4 == 0) goto L_0x004d
            r8.lastRecoverySucceeded = r2     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            java.lang.String r5 = "1"
            boolean r4 = r4.equals(r5)     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            if (r4 == 0) goto L_0x004d
            r8.lastRecoverySucceeded = r3     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
        L_0x004d:
            java.lang.String r4 = r6.readLine()     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            if (r4 == 0) goto L_0x0059
            java.util.Set<java.lang.String> r5 = r8.distinctBluetoothAddresses     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            r5.add(r4)     // Catch:{ IOException -> 0x0062, NumberFormatException -> 0x0060, all -> 0x005d }
            goto L_0x004d
        L_0x0059:
            r6.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0081
        L_0x005d:
            r0 = move-exception
            r4 = r6
            goto L_0x0095
        L_0x0060:
            r4 = r6
            goto L_0x0066
        L_0x0062:
            r4 = r6
            goto L_0x0075
        L_0x0064:
            r0 = move-exception
            goto L_0x0095
        L_0x0066:
            java.lang.String r5 = "Can't parse file %s"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x0064 }
            r6[r2] = r1     // Catch:{ all -> 0x0064 }
            org.altbeacon.beacon.logging.LogManager.w(r0, r5, r6)     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0081
        L_0x0071:
            r4.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0081
        L_0x0075:
            java.lang.String r5 = "Can't read macs from %s"
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x0064 }
            r6[r2] = r1     // Catch:{ all -> 0x0064 }
            org.altbeacon.beacon.logging.LogManager.w(r0, r5, r6)     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0081
            goto L_0x0071
        L_0x0081:
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.util.Set<java.lang.String> r3 = r8.distinctBluetoothAddresses
            int r3 = r3.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "Read %s Bluetooth addresses"
            org.altbeacon.beacon.logging.LogManager.d(r0, r2, r1)
            return
        L_0x0095:
            if (r4 == 0) goto L_0x009a
            r4.close()     // Catch:{ IOException -> 0x009a }
        L_0x009a:
            goto L_0x009c
        L_0x009b:
            throw r0
        L_0x009c:
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.bluetooth.BluetoothCrashResolver.loadState():void");
    }

    private void cancelDiscovery() {
        try {
            Thread.sleep(5000);
            if (!this.discoveryStartConfirmed) {
                LogManager.w("BluetoothCrashResolver", "BluetoothAdapter.ACTION_DISCOVERY_STARTED never received.  Recovery may fail.", new Object[0]);
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.isDiscovering()) {
                LogManager.d("BluetoothCrashResolver", "Cancelling discovery", new Object[0]);
                defaultAdapter.cancelDiscovery();
                return;
            }
            LogManager.d("BluetoothCrashResolver", "Discovery not running.  Won't cancel it", new Object[0]);
        } catch (InterruptedException unused) {
            LogManager.d("BluetoothCrashResolver", "DiscoveryCanceller sleep interrupted.", new Object[0]);
        }
    }
}
