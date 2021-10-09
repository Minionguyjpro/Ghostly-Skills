package org.altbeacon.beacon.service;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class MonitoringStatus {
    private static final Object SINGLETON_LOCK = new Object();
    private static final String TAG = MonitoringStatus.class.getSimpleName();
    private static volatile MonitoringStatus sInstance;
    private Context mContext;
    private Map<Region, RegionMonitoringState> mRegionsStatesMap;
    private boolean mStatePreservationIsOn = true;

    public static MonitoringStatus getInstanceForApplication(Context context) {
        MonitoringStatus monitoringStatus = sInstance;
        if (monitoringStatus == null) {
            synchronized (SINGLETON_LOCK) {
                monitoringStatus = sInstance;
                if (monitoringStatus == null) {
                    monitoringStatus = new MonitoringStatus(context.getApplicationContext());
                    sInstance = monitoringStatus;
                }
            }
        }
        return monitoringStatus;
    }

    public MonitoringStatus(Context context) {
        this.mContext = context;
    }

    public synchronized void addRegion(Region region, Callback callback) {
        addLocalRegion(region, callback);
        saveMonitoringStatusIfOn();
    }

    public synchronized void removeRegion(Region region) {
        removeLocalRegion(region);
        saveMonitoringStatusIfOn();
    }

    public synchronized Set<Region> regions() {
        return getRegionsStateMap().keySet();
    }

    public synchronized int regionsCount() {
        return regions().size();
    }

    public synchronized RegionMonitoringState stateOf(Region region) {
        return getRegionsStateMap().get(region);
    }

    public synchronized void updateNewlyOutside() {
        boolean z = false;
        for (Region next : regions()) {
            RegionMonitoringState stateOf = stateOf(next);
            if (stateOf.markOutsideIfExpired()) {
                LogManager.d(TAG, "found a monitor that expired: %s", next);
                stateOf.getCallback().call(this.mContext, "monitoringData", new MonitoringData(stateOf.getInside(), next).toBundle());
                z = true;
            }
        }
        if (z) {
            saveMonitoringStatusIfOn();
        } else {
            updateMonitoringStatusTime(System.currentTimeMillis());
        }
    }

    public synchronized void updateNewlyInsideInRegionsContaining(Beacon beacon) {
        boolean z = false;
        for (Region next : regionsMatchingTo(beacon)) {
            RegionMonitoringState regionMonitoringState = getRegionsStateMap().get(next);
            if (regionMonitoringState != null && regionMonitoringState.markInside()) {
                z = true;
                regionMonitoringState.getCallback().call(this.mContext, "monitoringData", new MonitoringData(regionMonitoringState.getInside(), next).toBundle());
            }
        }
        if (z) {
            saveMonitoringStatusIfOn();
        } else {
            updateMonitoringStatusTime(System.currentTimeMillis());
        }
    }

    private Map<Region, RegionMonitoringState> getRegionsStateMap() {
        if (this.mRegionsStatesMap == null) {
            restoreOrInitializeMonitoringStatus();
        }
        return this.mRegionsStatesMap;
    }

    private void restoreOrInitializeMonitoringStatus() {
        long currentTimeMillis = System.currentTimeMillis() - getLastMonitoringStatusUpdateTime();
        this.mRegionsStatesMap = new ConcurrentHashMap();
        if (!this.mStatePreservationIsOn) {
            LogManager.d(TAG, "Not restoring monitoring state because persistence is disabled", new Object[0]);
        } else if (currentTimeMillis > 900000) {
            String str = TAG;
            LogManager.d(str, "Not restoring monitoring state because it was recorded too many milliseconds ago: " + currentTimeMillis, new Object[0]);
        } else {
            restoreMonitoringStatus();
            LogManager.d(TAG, "Done restoring monitoring status", new Object[0]);
        }
    }

    private List<Region> regionsMatchingTo(Beacon beacon) {
        ArrayList arrayList = new ArrayList();
        for (Region next : regions()) {
            if (next.matchesBeacon(beacon)) {
                arrayList.add(next);
            } else {
                LogManager.d(TAG, "This region (%s) does not match beacon: %s", next, beacon);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:6|7|8|(8:9|10|11|12|(2:15|13)|50|16|(2:18|19))|20|21|54) */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0066 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0090 A[SYNTHETIC, Splitter:B:35:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009c A[SYNTHETIC, Splitter:B:42:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a3 A[SYNTHETIC, Splitter:B:46:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveMonitoringStatusIfOn() {
        /*
            r9 = this;
            boolean r0 = r9.mStatePreservationIsOn
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.String r0 = TAG
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "saveMonitoringStatusIfOn()"
            org.altbeacon.beacon.logging.LogManager.d(r0, r3, r2)
            java.util.Map r0 = r9.getRegionsStateMap()
            int r0 = r0.size()
            r2 = 50
            java.lang.String r3 = "org.altbeacon.beacon.service.monitoring_status_state"
            if (r0 <= r2) goto L_0x002d
            java.lang.String r0 = TAG
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Too many regions being monitored.  Will not persist region state"
            org.altbeacon.beacon.logging.LogManager.w(r0, r2, r1)
            android.content.Context r0 = r9.mContext
            r0.deleteFile(r3)
            goto L_0x0098
        L_0x002d:
            r0 = 0
            android.content.Context r2 = r9.mContext     // Catch:{ IOException -> 0x0079, all -> 0x0074 }
            java.io.FileOutputStream r2 = r2.openFileOutput(r3, r1)     // Catch:{ IOException -> 0x0079, all -> 0x0074 }
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x006f, all -> 0x006c }
            r3.<init>(r2)     // Catch:{ IOException -> 0x006f, all -> 0x006c }
            java.util.Map r0 = r9.getRegionsStateMap()     // Catch:{ IOException -> 0x006a }
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ IOException -> 0x006a }
            r4.<init>()     // Catch:{ IOException -> 0x006a }
            java.util.Set r5 = r0.keySet()     // Catch:{ IOException -> 0x006a }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x006a }
        L_0x004a:
            boolean r6 = r5.hasNext()     // Catch:{ IOException -> 0x006a }
            if (r6 == 0) goto L_0x005e
            java.lang.Object r6 = r5.next()     // Catch:{ IOException -> 0x006a }
            org.altbeacon.beacon.Region r6 = (org.altbeacon.beacon.Region) r6     // Catch:{ IOException -> 0x006a }
            java.lang.Object r7 = r0.get(r6)     // Catch:{ IOException -> 0x006a }
            r4.put(r6, r7)     // Catch:{ IOException -> 0x006a }
            goto L_0x004a
        L_0x005e:
            r3.writeObject(r4)     // Catch:{ IOException -> 0x006a }
            if (r2 == 0) goto L_0x0066
            r2.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            r3.close()     // Catch:{ IOException -> 0x0098 }
            goto L_0x0098
        L_0x006a:
            r0 = move-exception
            goto L_0x007d
        L_0x006c:
            r1 = move-exception
            r3 = r0
            goto L_0x0077
        L_0x006f:
            r3 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x007d
        L_0x0074:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L_0x0077:
            r0 = r1
            goto L_0x009a
        L_0x0079:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
        L_0x007d:
            java.lang.String r4 = TAG     // Catch:{ all -> 0x0099 }
            java.lang.String r5 = "Error while saving monitored region states to file "
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x0099 }
            r6[r1] = r0     // Catch:{ all -> 0x0099 }
            org.altbeacon.beacon.logging.LogManager.e(r4, r5, r6)     // Catch:{ all -> 0x0099 }
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ all -> 0x0099 }
            r0.printStackTrace(r1)     // Catch:{ all -> 0x0099 }
            if (r2 == 0) goto L_0x0095
            r2.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0095
        L_0x0094:
        L_0x0095:
            if (r3 == 0) goto L_0x0098
            goto L_0x0066
        L_0x0098:
            return
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            if (r2 == 0) goto L_0x00a1
            r2.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00a1
        L_0x00a0:
        L_0x00a1:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x00a6 }
        L_0x00a6:
            goto L_0x00a8
        L_0x00a7:
            throw r0
        L_0x00a8:
            goto L_0x00a7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.MonitoringStatus.saveMonitoringStatusIfOn():void");
    }

    /* access modifiers changed from: protected */
    public void updateMonitoringStatusTime(long j) {
        this.mContext.getFileStreamPath("org.altbeacon.beacon.service.monitoring_status_state").setLastModified(j);
    }

    /* access modifiers changed from: protected */
    public long getLastMonitoringStatusUpdateTime() {
        return this.mContext.getFileStreamPath("org.altbeacon.beacon.service.monitoring_status_state").lastModified();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x00a3 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ca A[Catch:{ all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d4 A[Catch:{ all -> 0x00ef }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6 A[SYNTHETIC, Splitter:B:38:0x00e6] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f2 A[SYNTHETIC, Splitter:B:45:0x00f2] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9 A[SYNTHETIC, Splitter:B:49:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void restoreMonitoringStatus() {
        /*
            r10 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r10.mContext     // Catch:{ IOException -> 0x00c2, ClassNotFoundException -> 0x00c0, ClassCastException -> 0x00be, all -> 0x00b9 }
            java.lang.String r3 = "org.altbeacon.beacon.service.monitoring_status_state"
            java.io.FileInputStream r2 = r2.openFileInput(r3)     // Catch:{ IOException -> 0x00c2, ClassNotFoundException -> 0x00c0, ClassCastException -> 0x00be, all -> 0x00b9 }
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x00b4, ClassNotFoundException -> 0x00b2, ClassCastException -> 0x00b0, all -> 0x00ad }
            r3.<init>(r2)     // Catch:{ IOException -> 0x00b4, ClassNotFoundException -> 0x00b2, ClassCastException -> 0x00b0, all -> 0x00ad }
            java.lang.Object r0 = r3.readObject()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r4 = TAG     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r5.<init>()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r6 = "Restored region monitoring state for "
            r5.append(r6)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            int r6 = r0.size()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r5.append(r6)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r6 = " regions."
            r5.append(r6)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            org.altbeacon.beacon.logging.LogManager.d(r4, r5, r6)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.util.Set r4 = r0.keySet()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
        L_0x003e:
            boolean r5 = r4.hasNext()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            if (r5 == 0) goto L_0x007b
            java.lang.Object r5 = r4.next()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            org.altbeacon.beacon.Region r5 = (org.altbeacon.beacon.Region) r5     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r6 = TAG     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r7.<init>()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r8 = "Region  "
            r7.append(r8)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r7.append(r5)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r8 = " uniqueId: "
            r7.append(r8)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r8 = r5.getUniqueId()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r7.append(r8)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r8 = " state: "
            r7.append(r8)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.Object r5 = r0.get(r5)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r7.append(r5)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.String r5 = r7.toString()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            org.altbeacon.beacon.logging.LogManager.d(r6, r5, r7)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            goto L_0x003e
        L_0x007b:
            java.util.Collection r4 = r0.values()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
        L_0x0083:
            boolean r5 = r4.hasNext()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            if (r5 == 0) goto L_0x0099
            java.lang.Object r5 = r4.next()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            org.altbeacon.beacon.service.RegionMonitoringState r5 = (org.altbeacon.beacon.service.RegionMonitoringState) r5     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            boolean r6 = r5.getInside()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            if (r6 == 0) goto L_0x0083
            r5.markInside()     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            goto L_0x0083
        L_0x0099:
            java.util.Map<org.altbeacon.beacon.Region, org.altbeacon.beacon.service.RegionMonitoringState> r4 = r10.mRegionsStatesMap     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            r4.putAll(r0)     // Catch:{ IOException -> 0x00ab, ClassNotFoundException -> 0x00a9, ClassCastException -> 0x00a7 }
            if (r2 == 0) goto L_0x00a3
            r2.close()     // Catch:{ IOException -> 0x00a3 }
        L_0x00a3:
            r3.close()     // Catch:{ IOException -> 0x00ee }
            goto L_0x00ee
        L_0x00a7:
            r0 = move-exception
            goto L_0x00c6
        L_0x00a9:
            r0 = move-exception
            goto L_0x00c6
        L_0x00ab:
            r0 = move-exception
            goto L_0x00c6
        L_0x00ad:
            r1 = move-exception
            r3 = r0
            goto L_0x00bc
        L_0x00b0:
            r3 = move-exception
            goto L_0x00b5
        L_0x00b2:
            r3 = move-exception
            goto L_0x00b5
        L_0x00b4:
            r3 = move-exception
        L_0x00b5:
            r9 = r3
            r3 = r0
            r0 = r9
            goto L_0x00c6
        L_0x00b9:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L_0x00bc:
            r0 = r1
            goto L_0x00f0
        L_0x00be:
            r2 = move-exception
            goto L_0x00c3
        L_0x00c0:
            r2 = move-exception
            goto L_0x00c3
        L_0x00c2:
            r2 = move-exception
        L_0x00c3:
            r3 = r0
            r0 = r2
            r2 = r3
        L_0x00c6:
            boolean r4 = r0 instanceof java.io.InvalidClassException     // Catch:{ all -> 0x00ef }
            if (r4 == 0) goto L_0x00d4
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ef }
            java.lang.String r4 = "Serialized Monitoring State has wrong class. Just ignoring saved state..."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00ef }
            org.altbeacon.beacon.logging.LogManager.d(r0, r4, r1)     // Catch:{ all -> 0x00ef }
            goto L_0x00e4
        L_0x00d4:
            java.lang.String r4 = TAG     // Catch:{ all -> 0x00ef }
            java.lang.String r5 = "Deserialization exception, message: %s"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00ef }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00ef }
            r6[r1] = r0     // Catch:{ all -> 0x00ef }
            org.altbeacon.beacon.logging.LogManager.e(r4, r5, r6)     // Catch:{ all -> 0x00ef }
        L_0x00e4:
            if (r2 == 0) goto L_0x00eb
            r2.close()     // Catch:{ IOException -> 0x00ea }
            goto L_0x00eb
        L_0x00ea:
        L_0x00eb:
            if (r3 == 0) goto L_0x00ee
            goto L_0x00a3
        L_0x00ee:
            return
        L_0x00ef:
            r0 = move-exception
        L_0x00f0:
            if (r2 == 0) goto L_0x00f7
            r2.close()     // Catch:{ IOException -> 0x00f6 }
            goto L_0x00f7
        L_0x00f6:
        L_0x00f7:
            if (r3 == 0) goto L_0x00fc
            r3.close()     // Catch:{ IOException -> 0x00fc }
        L_0x00fc:
            goto L_0x00fe
        L_0x00fd:
            throw r0
        L_0x00fe:
            goto L_0x00fd
        */
        throw new UnsupportedOperationException("Method not decompiled: org.altbeacon.beacon.service.MonitoringStatus.restoreMonitoringStatus():void");
    }

    public synchronized void stopStatusPreservation() {
        this.mContext.deleteFile("org.altbeacon.beacon.service.monitoring_status_state");
        this.mStatePreservationIsOn = false;
    }

    public synchronized void startStatusPreservation() {
        if (!this.mStatePreservationIsOn) {
            this.mStatePreservationIsOn = true;
            saveMonitoringStatusIfOn();
        }
    }

    public boolean isStatePreservationOn() {
        return this.mStatePreservationIsOn;
    }

    public void updateLocalState(Region region, Integer num) {
        RegionMonitoringState regionMonitoringState = getRegionsStateMap().get(region);
        if (regionMonitoringState == null) {
            regionMonitoringState = addLocalRegion(region);
        }
        if (num != null) {
            if (num.intValue() == 0) {
                regionMonitoringState.markOutside();
            }
            if (num.intValue() == 1) {
                regionMonitoringState.markInside();
            }
        }
    }

    public void removeLocalRegion(Region region) {
        getRegionsStateMap().remove(region);
    }

    public RegionMonitoringState addLocalRegion(Region region) {
        return addLocalRegion(region, new Callback((String) null));
    }

    private RegionMonitoringState addLocalRegion(Region region, Callback callback) {
        if (getRegionsStateMap().containsKey(region)) {
            Iterator<Region> it = getRegionsStateMap().keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Region next = it.next();
                if (next.equals(region)) {
                    if (next.hasSameIdentifiers(region)) {
                        return getRegionsStateMap().get(next);
                    }
                    String str = TAG;
                    LogManager.d(str, "Replacing region with unique identifier " + region.getUniqueId(), new Object[0]);
                    String str2 = TAG;
                    LogManager.d(str2, "Old definition: " + next, new Object[0]);
                    String str3 = TAG;
                    LogManager.d(str3, "New definition: " + region, new Object[0]);
                    LogManager.d(TAG, "clearing state", new Object[0]);
                    getRegionsStateMap().remove(region);
                }
            }
        }
        RegionMonitoringState regionMonitoringState = new RegionMonitoringState(callback);
        getRegionsStateMap().put(region, regionMonitoringState);
        return regionMonitoringState;
    }
}
