package org.altbeacon.beacon.service;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.scanner.CycledLeScanCallback;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;
import org.altbeacon.beacon.service.scanner.DistinctPacketDetector;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;
import org.altbeacon.beacon.service.scanner.ScanFilterUtils;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

class ScanHelper {
    /* access modifiers changed from: private */
    public static final String TAG = ScanHelper.class.getSimpleName();
    private BeaconManager mBeaconManager;
    /* access modifiers changed from: private */
    public Set<BeaconParser> mBeaconParsers = new HashSet();
    /* access modifiers changed from: private */
    public Context mContext;
    private final CycledLeScanCallback mCycledLeScanCallback = new CycledLeScanCallback() {
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            ScanHelper.this.processScanResult(bluetoothDevice, i, bArr);
        }

        public void onCycleEnd() {
            ScanHelper.this.mDistinctPacketDetector.clearDetections();
            ScanHelper.this.mMonitoringStatus.updateNewlyOutside();
            ScanHelper.this.processRangeData();
            if (ScanHelper.this.mSimulatedScanData != null) {
                LogManager.w(ScanHelper.TAG, "Simulated scan data is deprecated and will be removed in a future release. Please use the new BeaconSimulator interface instead.", new Object[0]);
                ApplicationInfo applicationInfo = ScanHelper.this.mContext.getApplicationInfo();
                int i = applicationInfo.flags & 2;
                applicationInfo.flags = i;
                if (i != 0) {
                    for (Beacon access$600 : ScanHelper.this.mSimulatedScanData) {
                        ScanHelper.this.processBeaconFromScan(access$600);
                    }
                } else {
                    LogManager.w(ScanHelper.TAG, "Simulated scan data provided, but ignored because we are not running in debug mode.  Please remove simulated scan data for production.", new Object[0]);
                }
            }
            if (BeaconManager.getBeaconSimulator() == null) {
                return;
            }
            if (BeaconManager.getBeaconSimulator().getBeacons() != null) {
                ApplicationInfo applicationInfo2 = ScanHelper.this.mContext.getApplicationInfo();
                int i2 = applicationInfo2.flags & 2;
                applicationInfo2.flags = i2;
                if (i2 != 0) {
                    for (Beacon access$6002 : BeaconManager.getBeaconSimulator().getBeacons()) {
                        ScanHelper.this.processBeaconFromScan(access$6002);
                    }
                    return;
                }
                LogManager.w(ScanHelper.TAG, "Beacon simulations provided, but ignored because we are not running in debug mode.  Please remove beacon simulations for production.", new Object[0]);
                return;
            }
            LogManager.w(ScanHelper.TAG, "getBeacons is returning null. No simulated beacons to report.", new Object[0]);
        }
    };
    /* access modifiers changed from: private */
    public CycledLeScanner mCycledScanner;
    /* access modifiers changed from: private */
    public DistinctPacketDetector mDistinctPacketDetector = new DistinctPacketDetector();
    private ExecutorService mExecutor;
    private ExtraDataBeaconTracker mExtraDataBeaconTracker = new ExtraDataBeaconTracker();
    /* access modifiers changed from: private */
    public MonitoringStatus mMonitoringStatus;
    private final Map<Region, RangeState> mRangedRegionState = new HashMap();
    /* access modifiers changed from: private */
    public List<Beacon> mSimulatedScanData = null;

    ScanHelper(Context context) {
        this.mContext = context;
        this.mBeaconManager = BeaconManager.getInstanceForApplication(context);
        this.mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    }

    /* access modifiers changed from: package-private */
    public CycledLeScanner getCycledScanner() {
        return this.mCycledScanner;
    }

    /* access modifiers changed from: package-private */
    public MonitoringStatus getMonitoringStatus() {
        return this.mMonitoringStatus;
    }

    /* access modifiers changed from: package-private */
    public void setMonitoringStatus(MonitoringStatus monitoringStatus) {
        this.mMonitoringStatus = monitoringStatus;
    }

    /* access modifiers changed from: package-private */
    public Map<Region, RangeState> getRangedRegionState() {
        return this.mRangedRegionState;
    }

    /* access modifiers changed from: package-private */
    public void setRangedRegionState(Map<Region, RangeState> map) {
        synchronized (this.mRangedRegionState) {
            this.mRangedRegionState.clear();
            this.mRangedRegionState.putAll(map);
        }
    }

    /* access modifiers changed from: package-private */
    public void setExtraDataBeaconTracker(ExtraDataBeaconTracker extraDataBeaconTracker) {
        this.mExtraDataBeaconTracker = extraDataBeaconTracker;
    }

    /* access modifiers changed from: package-private */
    public void setBeaconParsers(Set<BeaconParser> set) {
        this.mBeaconParsers = set;
    }

    /* access modifiers changed from: package-private */
    public void setSimulatedScanData(List<Beacon> list) {
        this.mSimulatedScanData = list;
    }

    /* access modifiers changed from: package-private */
    public void createCycledLeScanner(boolean z, BluetoothCrashResolver bluetoothCrashResolver) {
        this.mCycledScanner = CycledLeScanner.createScanner(this.mContext, 1100, 0, z, this.mCycledLeScanCallback, bluetoothCrashResolver);
    }

    /* access modifiers changed from: package-private */
    public void processScanResult(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        try {
            new ScanProcessor(this.mBeaconManager.getNonBeaconLeScanCallback()).executeOnExecutor(this.mExecutor, new ScanData[]{new ScanData(bluetoothDevice, i, bArr)});
        } catch (RejectedExecutionException unused) {
            LogManager.w(TAG, "Ignoring scan result because we cannot keep up.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void reloadParsers() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.mBeaconManager.getBeaconParsers());
        boolean z = true;
        for (BeaconParser next : this.mBeaconManager.getBeaconParsers()) {
            if (next.getExtraDataParsers().size() > 0) {
                z = false;
                hashSet.addAll(next.getExtraDataParsers());
            }
        }
        this.mBeaconParsers = hashSet;
        this.mExtraDataBeaconTracker = new ExtraDataBeaconTracker(z);
    }

    /* access modifiers changed from: package-private */
    public void startAndroidOBackgroundScan(Set<BeaconParser> set) {
        ScanSettings build = new ScanSettings.Builder().setScanMode(0).build();
        List<ScanFilter> createScanFiltersForBeaconParsers = new ScanFilterUtils().createScanFiltersForBeaconParsers(new ArrayList(set));
        try {
            BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter();
            if (adapter == null) {
                LogManager.w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
            } else if (!adapter.isEnabled()) {
                LogManager.w(TAG, "Failed to start background scan on Android O: BluetoothAdapter is not enabled", new Object[0]);
            } else {
                int startScan = adapter.getBluetoothLeScanner().startScan(createScanFiltersForBeaconParsers, build, getScanCallbackIntent());
                if (startScan != 0) {
                    String str = TAG;
                    LogManager.e(str, "Failed to start background scan on Android O.  Code: " + startScan, new Object[0]);
                    return;
                }
                LogManager.d(TAG, "Started passive beacon scan", new Object[0]);
            }
        } catch (SecurityException unused) {
            LogManager.e(TAG, "SecurityException making Android O background scanner", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void stopAndroidOBackgroundScan() {
        try {
            BluetoothAdapter adapter = ((BluetoothManager) this.mContext.getApplicationContext().getSystemService("bluetooth")).getAdapter();
            if (adapter == null) {
                LogManager.w(TAG, "Failed to construct a BluetoothAdapter", new Object[0]);
            } else if (!adapter.isEnabled()) {
                LogManager.w(TAG, "BluetoothAdapter is not enabled", new Object[0]);
            } else {
                BluetoothLeScanner bluetoothLeScanner = adapter.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(getScanCallbackIntent());
                }
            }
        } catch (SecurityException unused) {
            LogManager.e(TAG, "SecurityException stopping Android O background scanner", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public PendingIntent getScanCallbackIntent() {
        Intent intent = new Intent(this.mContext, StartupBroadcastReceiver.class);
        intent.putExtra("o-scan", true);
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728);
    }

    /* access modifiers changed from: private */
    public void processRangeData() {
        synchronized (this.mRangedRegionState) {
            for (Region next : this.mRangedRegionState.keySet()) {
                RangeState rangeState = this.mRangedRegionState.get(next);
                LogManager.d(TAG, "Calling ranging callback", new Object[0]);
                rangeState.getCallback().call(this.mContext, "rangingData", new RangingData(rangeState.finalizeBeacons(), next).toBundle());
            }
        }
    }

    /* access modifiers changed from: private */
    public void processBeaconFromScan(Beacon beacon) {
        if (Stats.getInstance().isEnabled()) {
            Stats.getInstance().log(beacon);
        }
        if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.d(TAG, "beacon detected : %s", beacon.toString());
        }
        Beacon track = this.mExtraDataBeaconTracker.track(beacon);
        if (track != null) {
            this.mMonitoringStatus.updateNewlyInsideInRegionsContaining(track);
            LogManager.d(TAG, "looking for ranging region matches for this beacon", new Object[0]);
            synchronized (this.mRangedRegionState) {
                for (Region next : matchingRegions(track, this.mRangedRegionState.keySet())) {
                    LogManager.d(TAG, "matches ranging region: %s", next);
                    RangeState rangeState = this.mRangedRegionState.get(next);
                    if (rangeState != null) {
                        rangeState.addBeacon(track);
                    }
                }
            }
        } else if (LogManager.isVerboseLoggingEnabled()) {
            LogManager.d(TAG, "not processing detections for GATT extra data beacon", new Object[0]);
        }
    }

    private class ScanData {
        BluetoothDevice device;
        final int rssi;
        byte[] scanRecord;

        ScanData(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            this.device = bluetoothDevice;
            this.rssi = i;
            this.scanRecord = bArr;
        }
    }

    private class ScanProcessor extends AsyncTask<ScanData, Void, Void> {
        final DetectionTracker mDetectionTracker = DetectionTracker.getInstance();
        private final NonBeaconLeScanCallback mNonBeaconLeScanCallback;

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Void... voidArr) {
        }

        ScanProcessor(NonBeaconLeScanCallback nonBeaconLeScanCallback) {
            this.mNonBeaconLeScanCallback = nonBeaconLeScanCallback;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:1:0x000f A[LOOP:0: B:1:0x000f->B:4:0x0025, LOOP_START, PHI: r3 
          PHI: (r3v1 org.altbeacon.beacon.Beacon) = (r3v0 org.altbeacon.beacon.Beacon), (r3v6 org.altbeacon.beacon.Beacon) binds: [B:0:0x0000, B:4:0x0025] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void doInBackground(org.altbeacon.beacon.service.ScanHelper.ScanData... r8) {
            /*
                r7 = this;
                r0 = 0
                r8 = r8[r0]
                org.altbeacon.beacon.service.ScanHelper r1 = org.altbeacon.beacon.service.ScanHelper.this
                java.util.Set r1 = r1.mBeaconParsers
                java.util.Iterator r1 = r1.iterator()
                r2 = 0
                r3 = r2
            L_0x000f:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L_0x0027
                java.lang.Object r3 = r1.next()
                org.altbeacon.beacon.BeaconParser r3 = (org.altbeacon.beacon.BeaconParser) r3
                byte[] r4 = r8.scanRecord
                int r5 = r8.rssi
                android.bluetooth.BluetoothDevice r6 = r8.device
                org.altbeacon.beacon.Beacon r3 = r3.fromScanData(r4, r5, r6)
                if (r3 == 0) goto L_0x000f
            L_0x0027:
                if (r3 == 0) goto L_0x009d
                boolean r1 = org.altbeacon.beacon.logging.LogManager.isVerboseLoggingEnabled()
                if (r1 == 0) goto L_0x0055
                java.lang.String r1 = org.altbeacon.beacon.service.ScanHelper.TAG
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "Beacon packet detected for: "
                r4.append(r5)
                r4.append(r3)
                java.lang.String r5 = " with rssi "
                r4.append(r5)
                int r5 = r3.getRssi()
                r4.append(r5)
                java.lang.String r4 = r4.toString()
                java.lang.Object[] r5 = new java.lang.Object[r0]
                org.altbeacon.beacon.logging.LogManager.d(r1, r4, r5)
            L_0x0055:
                org.altbeacon.beacon.service.DetectionTracker r1 = r7.mDetectionTracker
                r1.recordDetection()
                org.altbeacon.beacon.service.ScanHelper r1 = org.altbeacon.beacon.service.ScanHelper.this
                org.altbeacon.beacon.service.scanner.CycledLeScanner r1 = r1.mCycledScanner
                if (r1 == 0) goto L_0x0097
                org.altbeacon.beacon.service.ScanHelper r1 = org.altbeacon.beacon.service.ScanHelper.this
                org.altbeacon.beacon.service.scanner.CycledLeScanner r1 = r1.mCycledScanner
                boolean r1 = r1.getDistinctPacketsDetectedPerScan()
                if (r1 != 0) goto L_0x0097
                org.altbeacon.beacon.service.ScanHelper r1 = org.altbeacon.beacon.service.ScanHelper.this
                org.altbeacon.beacon.service.scanner.DistinctPacketDetector r1 = r1.mDistinctPacketDetector
                android.bluetooth.BluetoothDevice r4 = r8.device
                java.lang.String r4 = r4.getAddress()
                byte[] r8 = r8.scanRecord
                boolean r8 = r1.isPacketDistinct(r4, r8)
                if (r8 != 0) goto L_0x0097
                java.lang.String r8 = org.altbeacon.beacon.service.ScanHelper.TAG
                java.lang.Object[] r0 = new java.lang.Object[r0]
                java.lang.String r1 = "Non-distinct packets detected in a single scan.  Restarting scans unecessary."
                org.altbeacon.beacon.logging.LogManager.i(r8, r1, r0)
                org.altbeacon.beacon.service.ScanHelper r8 = org.altbeacon.beacon.service.ScanHelper.this
                org.altbeacon.beacon.service.scanner.CycledLeScanner r8 = r8.mCycledScanner
                r0 = 1
                r8.setDistinctPacketsDetectedPerScan(r0)
            L_0x0097:
                org.altbeacon.beacon.service.ScanHelper r8 = org.altbeacon.beacon.service.ScanHelper.this
                r8.processBeaconFromScan(r3)
                goto L_0x00aa
            L_0x009d:
                org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback r0 = r7.mNonBeaconLeScanCallback
                if (r0 == 0) goto L_0x00aa
                android.bluetooth.BluetoothDevice r1 = r8.device
                int r3 = r8.rssi
                byte[] r8 = r8.scanRecord
                r0.onNonBeaconLeScan(r1, r3, r8)
            L_0x00aa:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.ScanHelper.ScanProcessor.doInBackground(org.altbeacon.beacon.service.ScanHelper$ScanData[]):java.lang.Void");
        }
    }

    private List<Region> matchingRegions(Beacon beacon, Collection<Region> collection) {
        ArrayList arrayList = new ArrayList();
        for (Region next : collection) {
            if (next.matchesBeacon(beacon)) {
                arrayList.add(next);
            } else {
                LogManager.d(TAG, "This region (%s) does not match beacon: %s", next, beacon);
            }
        }
        return arrayList;
    }
}
