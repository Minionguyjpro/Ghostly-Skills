package org.altbeacon.beacon.service;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class ScanState implements Serializable {
    public static int MIN_SCAN_JOB_INTERVAL_MILLIS = 300000;
    private static final String TAG = ScanState.class.getSimpleName();
    private long mBackgroundBetweenScanPeriod;
    private boolean mBackgroundMode;
    private long mBackgroundScanPeriod;
    private Set<BeaconParser> mBeaconParsers = new HashSet();
    private transient Context mContext;
    private ExtraDataBeaconTracker mExtraBeaconDataTracker = new ExtraDataBeaconTracker();
    private long mForegroundBetweenScanPeriod;
    private long mForegroundScanPeriod;
    private long mLastScanStartTimeMillis = 0;
    private transient MonitoringStatus mMonitoringStatus;
    private Map<Region, RangeState> mRangedRegionState = new HashMap();

    public Boolean getBackgroundMode() {
        return Boolean.valueOf(this.mBackgroundMode);
    }

    public Long getBackgroundBetweenScanPeriod() {
        return Long.valueOf(this.mBackgroundBetweenScanPeriod);
    }

    public Long getBackgroundScanPeriod() {
        return Long.valueOf(this.mBackgroundScanPeriod);
    }

    public Long getForegroundBetweenScanPeriod() {
        return Long.valueOf(this.mForegroundBetweenScanPeriod);
    }

    public Long getForegroundScanPeriod() {
        return Long.valueOf(this.mForegroundScanPeriod);
    }

    public ScanState(Context context) {
        this.mContext = context;
    }

    public MonitoringStatus getMonitoringStatus() {
        return this.mMonitoringStatus;
    }

    public Map<Region, RangeState> getRangedRegionState() {
        return this.mRangedRegionState;
    }

    public ExtraDataBeaconTracker getExtraBeaconDataTracker() {
        return this.mExtraBeaconDataTracker;
    }

    public Set<BeaconParser> getBeaconParsers() {
        return this.mBeaconParsers;
    }

    public void setLastScanStartTimeMillis(long j) {
        this.mLastScanStartTimeMillis = j;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:64|(0)|(0)|73|74) */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:2|(5:3|4|5|6|(5:7|8|9|10|(2:12|13)))|14|15|(2:56|57)|58|(1:60)|61|62) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0078, code lost:
        if (r4 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0090, code lost:
        if (r4 != null) goto L_0x001d;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x00f4 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0057 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0061 A[Catch:{ all -> 0x007b }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0073 A[SYNTHETIC, Splitter:B:40:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x008b A[SYNTHETIC, Splitter:B:51:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e8 A[SYNTHETIC, Splitter:B:66:0x00e8] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f1 A[SYNTHETIC, Splitter:B:71:0x00f1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.altbeacon.beacon.service.ScanState restore(android.content.Context r10) {
        /*
            java.lang.Class<org.altbeacon.beacon.service.ScanState> r0 = org.altbeacon.beacon.service.ScanState.class
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            java.lang.String r3 = "android-beacon-library-scan-state"
            java.io.FileInputStream r3 = r10.openFileInput(r3)     // Catch:{ FileNotFoundException -> 0x007e, IOException -> 0x004e, ClassNotFoundException -> 0x004c, ClassCastException -> 0x004a, all -> 0x0046 }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x003d, ClassNotFoundException -> 0x003b, ClassCastException -> 0x0039, all -> 0x0036 }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0042, IOException -> 0x003d, ClassNotFoundException -> 0x003b, ClassCastException -> 0x0039, all -> 0x0036 }
            java.lang.Object r5 = r4.readObject()     // Catch:{ FileNotFoundException -> 0x0034, IOException -> 0x002f, ClassNotFoundException -> 0x002d, ClassCastException -> 0x002b }
            org.altbeacon.beacon.service.ScanState r5 = (org.altbeacon.beacon.service.ScanState) r5     // Catch:{ FileNotFoundException -> 0x0034, IOException -> 0x002f, ClassNotFoundException -> 0x002d, ClassCastException -> 0x002b }
            r5.mContext = r10     // Catch:{ FileNotFoundException -> 0x0044, IOException -> 0x0029, ClassNotFoundException -> 0x0027, ClassCastException -> 0x0025 }
            if (r3 == 0) goto L_0x001d
            r3.close()     // Catch:{ IOException -> 0x001d }
        L_0x001d:
            r4.close()     // Catch:{ IOException -> 0x0022 }
            goto L_0x0093
        L_0x0022:
            goto L_0x0093
        L_0x0025:
            r1 = move-exception
            goto L_0x0053
        L_0x0027:
            r1 = move-exception
            goto L_0x0053
        L_0x0029:
            r1 = move-exception
            goto L_0x0053
        L_0x002b:
            r5 = move-exception
            goto L_0x0030
        L_0x002d:
            r5 = move-exception
            goto L_0x0030
        L_0x002f:
            r5 = move-exception
        L_0x0030:
            r9 = r5
            r5 = r1
            r1 = r9
            goto L_0x0053
        L_0x0034:
            r5 = r1
            goto L_0x0044
        L_0x0036:
            r10 = move-exception
            r4 = r1
            goto L_0x007c
        L_0x0039:
            r4 = move-exception
            goto L_0x003e
        L_0x003b:
            r4 = move-exception
            goto L_0x003e
        L_0x003d:
            r4 = move-exception
        L_0x003e:
            r5 = r1
            r1 = r4
            r4 = r5
            goto L_0x0053
        L_0x0042:
            r4 = r1
            r5 = r4
        L_0x0044:
            r1 = r3
            goto L_0x0080
        L_0x0046:
            r10 = move-exception
            r4 = r1
            goto L_0x00e6
        L_0x004a:
            r3 = move-exception
            goto L_0x004f
        L_0x004c:
            r3 = move-exception
            goto L_0x004f
        L_0x004e:
            r3 = move-exception
        L_0x004f:
            r4 = r1
            r5 = r4
            r1 = r3
            r3 = r5
        L_0x0053:
            boolean r6 = r1 instanceof java.io.InvalidClassException     // Catch:{ all -> 0x007b }
            if (r6 == 0) goto L_0x0061
            java.lang.String r1 = TAG     // Catch:{ all -> 0x007b }
            java.lang.String r6 = "Serialized ScanState has wrong class. Just ignoring saved state..."
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch:{ all -> 0x007b }
            org.altbeacon.beacon.logging.LogManager.d(r1, r6, r7)     // Catch:{ all -> 0x007b }
            goto L_0x0071
        L_0x0061:
            java.lang.String r6 = TAG     // Catch:{ all -> 0x007b }
            java.lang.String r7 = "Deserialization exception"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x007b }
            org.altbeacon.beacon.logging.LogManager.e(r6, r7, r8)     // Catch:{ all -> 0x007b }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x007b }
            java.lang.String r7 = "error: "
            android.util.Log.e(r6, r7, r1)     // Catch:{ all -> 0x007b }
        L_0x0071:
            if (r3 == 0) goto L_0x0078
            r3.close()     // Catch:{ IOException -> 0x0077 }
            goto L_0x0078
        L_0x0077:
        L_0x0078:
            if (r4 == 0) goto L_0x0093
            goto L_0x001d
        L_0x007b:
            r10 = move-exception
        L_0x007c:
            r1 = r3
            goto L_0x00e6
        L_0x007e:
            r4 = r1
            r5 = r4
        L_0x0080:
            java.lang.String r3 = TAG     // Catch:{ all -> 0x00e5 }
            java.lang.String r6 = "Serialized ScanState does not exist.  This may be normal on first run."
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e5 }
            org.altbeacon.beacon.logging.LogManager.w(r3, r6, r7)     // Catch:{ all -> 0x00e5 }
            if (r1 == 0) goto L_0x0090
            r1.close()     // Catch:{ IOException -> 0x008f }
            goto L_0x0090
        L_0x008f:
        L_0x0090:
            if (r4 == 0) goto L_0x0093
            goto L_0x001d
        L_0x0093:
            if (r5 != 0) goto L_0x009a
            org.altbeacon.beacon.service.ScanState r5 = new org.altbeacon.beacon.service.ScanState     // Catch:{ all -> 0x00ec }
            r5.<init>(r10)     // Catch:{ all -> 0x00ec }
        L_0x009a:
            org.altbeacon.beacon.service.ExtraDataBeaconTracker r1 = r5.mExtraBeaconDataTracker     // Catch:{ all -> 0x00ec }
            if (r1 != 0) goto L_0x00a5
            org.altbeacon.beacon.service.ExtraDataBeaconTracker r1 = new org.altbeacon.beacon.service.ExtraDataBeaconTracker     // Catch:{ all -> 0x00ec }
            r1.<init>()     // Catch:{ all -> 0x00ec }
            r5.mExtraBeaconDataTracker = r1     // Catch:{ all -> 0x00ec }
        L_0x00a5:
            org.altbeacon.beacon.service.MonitoringStatus r10 = org.altbeacon.beacon.service.MonitoringStatus.getInstanceForApplication(r10)     // Catch:{ all -> 0x00ec }
            r5.mMonitoringStatus = r10     // Catch:{ all -> 0x00ec }
            java.lang.String r10 = TAG     // Catch:{ all -> 0x00ec }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ec }
            r1.<init>()     // Catch:{ all -> 0x00ec }
            java.lang.String r3 = "Scan state restore regions: monitored="
            r1.append(r3)     // Catch:{ all -> 0x00ec }
            org.altbeacon.beacon.service.MonitoringStatus r3 = r5.getMonitoringStatus()     // Catch:{ all -> 0x00ec }
            java.util.Set r3 = r3.regions()     // Catch:{ all -> 0x00ec }
            int r3 = r3.size()     // Catch:{ all -> 0x00ec }
            r1.append(r3)     // Catch:{ all -> 0x00ec }
            java.lang.String r3 = " ranged="
            r1.append(r3)     // Catch:{ all -> 0x00ec }
            java.util.Map r3 = r5.getRangedRegionState()     // Catch:{ all -> 0x00ec }
            java.util.Set r3 = r3.keySet()     // Catch:{ all -> 0x00ec }
            int r3 = r3.size()     // Catch:{ all -> 0x00ec }
            r1.append(r3)     // Catch:{ all -> 0x00ec }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ec }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00ec }
            org.altbeacon.beacon.logging.LogManager.d(r10, r1, r2)     // Catch:{ all -> 0x00ec }
            monitor-exit(r0)     // Catch:{ all -> 0x00ec }
            return r5
        L_0x00e5:
            r10 = move-exception
        L_0x00e6:
            if (r1 == 0) goto L_0x00ef
            r1.close()     // Catch:{ IOException -> 0x00ee }
            goto L_0x00ef
        L_0x00ec:
            r10 = move-exception
            goto L_0x00f5
        L_0x00ee:
        L_0x00ef:
            if (r4 == 0) goto L_0x00f4
            r4.close()     // Catch:{ IOException -> 0x00f4 }
        L_0x00f4:
            throw r10     // Catch:{ all -> 0x00ec }
        L_0x00f5:
            monitor-exit(r0)     // Catch:{ all -> 0x00ec }
            goto L_0x00f8
        L_0x00f7:
            throw r10
        L_0x00f8:
            goto L_0x00f7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.ScanState.restore(android.content.Context):org.altbeacon.beacon.service.ScanState");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:40|(2:42|43)|(2:47|48)|49|50) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:2|(5:3|4|5|6|(6:7|8|(1:10)|11|(1:13)|(2:15|16)))|17|18|36|37|38) */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b8, code lost:
        if (r4 == null) goto L_0x00bb;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x008a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x00bb */
    /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00d1 */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b3 A[SYNTHETIC, Splitter:B:32:0x00b3] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c5 A[SYNTHETIC, Splitter:B:42:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ce A[SYNTHETIC, Splitter:B:47:0x00ce] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:49:0x00d1=Splitter:B:49:0x00d1, B:36:0x00bb=Splitter:B:36:0x00bb} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void save() {
        /*
            r10 = this;
            java.lang.Class<org.altbeacon.beacon.service.ScanState> r0 = org.altbeacon.beacon.service.ScanState.class
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            android.content.Context r3 = r10.mContext     // Catch:{ IOException -> 0x009d, all -> 0x0098 }
            java.lang.String r4 = "android-beacon-library-scan-state-temp"
            java.io.FileOutputStream r3 = r3.openFileOutput(r4, r2)     // Catch:{ IOException -> 0x009d, all -> 0x0098 }
            java.io.ObjectOutputStream r4 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0093, all -> 0x0090 }
            r4.writeObject(r10)     // Catch:{ IOException -> 0x008e }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x008e }
            android.content.Context r5 = r10.mContext     // Catch:{ IOException -> 0x008e }
            java.io.File r5 = r5.getFilesDir()     // Catch:{ IOException -> 0x008e }
            java.lang.String r6 = "android-beacon-library-scan-state"
            r1.<init>(r5, r6)     // Catch:{ IOException -> 0x008e }
            java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x008e }
            android.content.Context r6 = r10.mContext     // Catch:{ IOException -> 0x008e }
            java.io.File r6 = r6.getFilesDir()     // Catch:{ IOException -> 0x008e }
            java.lang.String r7 = "android-beacon-library-scan-state-temp"
            r5.<init>(r6, r7)     // Catch:{ IOException -> 0x008e }
            java.lang.String r6 = TAG     // Catch:{ IOException -> 0x008e }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008e }
            r7.<init>()     // Catch:{ IOException -> 0x008e }
            java.lang.String r8 = "Temp file is "
            r7.append(r8)     // Catch:{ IOException -> 0x008e }
            java.lang.String r8 = r5.getAbsolutePath()     // Catch:{ IOException -> 0x008e }
            r7.append(r8)     // Catch:{ IOException -> 0x008e }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x008e }
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x008e }
            org.altbeacon.beacon.logging.LogManager.d(r6, r7, r8)     // Catch:{ IOException -> 0x008e }
            java.lang.String r6 = TAG     // Catch:{ IOException -> 0x008e }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x008e }
            r7.<init>()     // Catch:{ IOException -> 0x008e }
            java.lang.String r8 = "Perm file is "
            r7.append(r8)     // Catch:{ IOException -> 0x008e }
            java.lang.String r8 = r1.getAbsolutePath()     // Catch:{ IOException -> 0x008e }
            r7.append(r8)     // Catch:{ IOException -> 0x008e }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x008e }
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x008e }
            org.altbeacon.beacon.logging.LogManager.d(r6, r7, r8)     // Catch:{ IOException -> 0x008e }
            boolean r6 = r1.delete()     // Catch:{ IOException -> 0x008e }
            if (r6 != 0) goto L_0x0076
            java.lang.String r6 = TAG     // Catch:{ IOException -> 0x008e }
            java.lang.String r7 = "Error while saving scan status to file: Cannot delete existing file."
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x008e }
            org.altbeacon.beacon.logging.LogManager.e(r6, r7, r8)     // Catch:{ IOException -> 0x008e }
        L_0x0076:
            boolean r1 = r5.renameTo(r1)     // Catch:{ IOException -> 0x008e }
            if (r1 != 0) goto L_0x0085
            java.lang.String r1 = TAG     // Catch:{ IOException -> 0x008e }
            java.lang.String r5 = "Error while saving scan status to file: Cannot rename temp file."
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x008e }
            org.altbeacon.beacon.logging.LogManager.e(r1, r5, r6)     // Catch:{ IOException -> 0x008e }
        L_0x0085:
            if (r3 == 0) goto L_0x008a
            r3.close()     // Catch:{ IOException -> 0x008a }
        L_0x008a:
            r4.close()     // Catch:{ IOException -> 0x00bb }
            goto L_0x00bb
        L_0x008e:
            r1 = move-exception
            goto L_0x00a1
        L_0x0090:
            r2 = move-exception
            r4 = r1
            goto L_0x009b
        L_0x0093:
            r4 = move-exception
            r9 = r4
            r4 = r1
            r1 = r9
            goto L_0x00a1
        L_0x0098:
            r2 = move-exception
            r3 = r1
            r4 = r3
        L_0x009b:
            r1 = r2
            goto L_0x00c3
        L_0x009d:
            r3 = move-exception
            r4 = r1
            r1 = r3
            r3 = r4
        L_0x00a1:
            java.lang.String r5 = TAG     // Catch:{ all -> 0x00c2 }
            java.lang.String r6 = "Error while saving scan status to file: "
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x00c2 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00c2 }
            r7[r2] = r1     // Catch:{ all -> 0x00c2 }
            org.altbeacon.beacon.logging.LogManager.e(r5, r6, r7)     // Catch:{ all -> 0x00c2 }
            if (r3 == 0) goto L_0x00b8
            r3.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00b8
        L_0x00b7:
        L_0x00b8:
            if (r4 == 0) goto L_0x00bb
            goto L_0x008a
        L_0x00bb:
            org.altbeacon.beacon.service.MonitoringStatus r1 = r10.mMonitoringStatus     // Catch:{ all -> 0x00c9 }
            r1.saveMonitoringStatusIfOn()     // Catch:{ all -> 0x00c9 }
            monitor-exit(r0)     // Catch:{ all -> 0x00c9 }
            return
        L_0x00c2:
            r1 = move-exception
        L_0x00c3:
            if (r3 == 0) goto L_0x00cc
            r3.close()     // Catch:{ IOException -> 0x00cb }
            goto L_0x00cc
        L_0x00c9:
            r1 = move-exception
            goto L_0x00d2
        L_0x00cb:
        L_0x00cc:
            if (r4 == 0) goto L_0x00d1
            r4.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x00d1:
            throw r1     // Catch:{ all -> 0x00c9 }
        L_0x00d2:
            monitor-exit(r0)     // Catch:{ all -> 0x00c9 }
            goto L_0x00d5
        L_0x00d4:
            throw r1
        L_0x00d5:
            goto L_0x00d4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.ScanState.save():void");
    }

    public int getScanJobIntervalMillis() {
        long j;
        long j2;
        if (getBackgroundMode().booleanValue()) {
            j2 = getBackgroundScanPeriod().longValue();
            j = getBackgroundBetweenScanPeriod().longValue();
        } else {
            j2 = getForegroundScanPeriod().longValue();
            j = getForegroundBetweenScanPeriod().longValue();
        }
        long j3 = j2 + j;
        int i = MIN_SCAN_JOB_INTERVAL_MILLIS;
        return j3 > ((long) i) ? (int) j3 : i;
    }

    public int getScanJobRuntimeMillis() {
        long j;
        String str = TAG;
        LogManager.d(str, "ScanState says background mode for ScanJob is " + getBackgroundMode(), new Object[0]);
        if (getBackgroundMode().booleanValue()) {
            j = getBackgroundScanPeriod().longValue();
        } else {
            j = getForegroundScanPeriod().longValue();
        }
        if (!getBackgroundMode().booleanValue()) {
            int i = MIN_SCAN_JOB_INTERVAL_MILLIS;
            if (j < ((long) i)) {
                return i;
            }
        }
        return (int) j;
    }

    public void applyChanges(BeaconManager beaconManager) {
        this.mBeaconParsers = new HashSet(beaconManager.getBeaconParsers());
        this.mForegroundScanPeriod = beaconManager.getForegroundScanPeriod();
        this.mForegroundBetweenScanPeriod = beaconManager.getForegroundBetweenScanPeriod();
        this.mBackgroundScanPeriod = beaconManager.getBackgroundScanPeriod();
        this.mBackgroundBetweenScanPeriod = beaconManager.getBackgroundBetweenScanPeriod();
        this.mBackgroundMode = beaconManager.getBackgroundMode();
        ArrayList arrayList = new ArrayList(this.mMonitoringStatus.regions());
        ArrayList arrayList2 = new ArrayList(this.mRangedRegionState.keySet());
        ArrayList arrayList3 = new ArrayList(beaconManager.getMonitoredRegions());
        ArrayList arrayList4 = new ArrayList(beaconManager.getRangedRegions());
        String str = TAG;
        LogManager.d(str, "ranged regions: old=" + arrayList2.size() + " new=" + arrayList4.size(), new Object[0]);
        String str2 = TAG;
        LogManager.d(str2, "monitored regions: old=" + arrayList.size() + " new=" + arrayList3.size(), new Object[0]);
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            Region region = (Region) it.next();
            if (!arrayList2.contains(region)) {
                String str3 = TAG;
                LogManager.d(str3, "Starting ranging region: " + region, new Object[0]);
                this.mRangedRegionState.put(region, new RangeState(new Callback(this.mContext.getPackageName())));
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            Region region2 = (Region) it2.next();
            if (!arrayList4.contains(region2)) {
                String str4 = TAG;
                LogManager.d(str4, "Stopping ranging region: " + region2, new Object[0]);
                this.mRangedRegionState.remove(region2);
            }
        }
        String str5 = TAG;
        LogManager.d(str5, "Updated state with " + arrayList4.size() + " ranging regions and " + arrayList3.size() + " monitoring regions.", new Object[0]);
        save();
    }
}
