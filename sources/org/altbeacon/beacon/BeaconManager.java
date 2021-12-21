package org.altbeacon.beacon;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.BeaconService;
import org.altbeacon.beacon.service.MonitoringStatus;
import org.altbeacon.beacon.service.RunningAverageRssiFilter;
import org.altbeacon.beacon.service.ScanJobScheduler;
import org.altbeacon.beacon.service.SettingsData;
import org.altbeacon.beacon.service.StartRMData;
import org.altbeacon.beacon.service.scanner.NonBeaconLeScanCallback;
import org.altbeacon.beacon.simulator.BeaconSimulator;
import org.altbeacon.beacon.utils.ProcessUtils;

public class BeaconManager {
    private static final Object SINGLETON_LOCK = new Object();
    protected static BeaconSimulator beaconSimulator = null;
    protected static String distanceModelUpdateUrl = "http://data.altbeacon.org/android-distance.json";
    protected static Class rssiFilterImplClass = RunningAverageRssiFilter.class;
    private static boolean sAndroidLScanningDisabled = false;
    private static long sExitRegionPeriod = 10000;
    protected static volatile BeaconManager sInstance = null;
    private static boolean sManifestCheckingDisabled = false;
    private long backgroundBetweenScanPeriod;
    private long backgroundScanPeriod;
    private final List<BeaconParser> beaconParsers = new CopyOnWriteArrayList();
    private final ConcurrentMap<Object, Object> consumers = new ConcurrentHashMap();
    protected RangeNotifier dataRequestNotifier = null;
    private long foregroundBetweenScanPeriod;
    private long foregroundScanPeriod;
    private boolean mBackgroundMode;
    private boolean mBackgroundModeUninitialized;
    private final Context mContext;
    private boolean mMainProcess;
    private NonBeaconLeScanCallback mNonBeaconLeScanCallback;
    private boolean mRegionStatePersistenceEnabled;
    private Boolean mScannerInSameProcess;
    private boolean mScheduledScanJobsEnabled;
    protected final Set<MonitorNotifier> monitorNotifiers = new CopyOnWriteArraySet();
    protected final Set<RangeNotifier> rangeNotifiers = new CopyOnWriteArraySet();
    private final ArrayList<Region> rangedRegions = new ArrayList<>();
    private Messenger serviceMessenger = null;

    public static void setRegionExitPeriod(long j) {
        sExitRegionPeriod = j;
        BeaconManager beaconManager = sInstance;
        if (beaconManager != null) {
            beaconManager.applySettings();
        }
    }

    public static long getRegionExitPeriod() {
        return sExitRegionPeriod;
    }

    public static BeaconManager getInstanceForApplication(Context context) {
        BeaconManager beaconManager = sInstance;
        if (beaconManager == null) {
            synchronized (SINGLETON_LOCK) {
                beaconManager = sInstance;
                if (beaconManager == null) {
                    beaconManager = new BeaconManager(context);
                    sInstance = beaconManager;
                }
            }
        }
        return beaconManager;
    }

    protected BeaconManager(Context context) {
        boolean z = true;
        this.mRegionStatePersistenceEnabled = true;
        this.mBackgroundMode = false;
        this.mBackgroundModeUninitialized = true;
        this.mMainProcess = false;
        this.mScannerInSameProcess = null;
        this.mScheduledScanJobsEnabled = false;
        this.foregroundScanPeriod = 1100;
        this.foregroundBetweenScanPeriod = 0;
        this.backgroundScanPeriod = 10000;
        this.backgroundBetweenScanPeriod = 300000;
        this.mContext = context.getApplicationContext();
        checkIfMainProcess();
        if (!sManifestCheckingDisabled) {
            verifyServiceDeclaration();
        }
        this.beaconParsers.add(new AltBeaconParser());
        this.mScheduledScanJobsEnabled = Build.VERSION.SDK_INT < 26 ? false : z;
    }

    public boolean isMainProcess() {
        return this.mMainProcess;
    }

    public boolean isScannerInDifferentProcess() {
        Boolean bool = this.mScannerInSameProcess;
        return bool != null && !bool.booleanValue();
    }

    public void setScannerInSameProcess(boolean z) {
        this.mScannerInSameProcess = Boolean.valueOf(z);
    }

    /* access modifiers changed from: protected */
    public void checkIfMainProcess() {
        ProcessUtils processUtils = new ProcessUtils(this.mContext);
        String processName = processUtils.getProcessName();
        String packageName = processUtils.getPackageName();
        int pid = processUtils.getPid();
        this.mMainProcess = processUtils.isMainProcess();
        LogManager.i("BeaconManager", "BeaconManager started up on pid " + pid + " named '" + processName + "' for application package '" + packageName + "'.  isMainProcess=" + this.mMainProcess, new Object[0]);
    }

    public List<BeaconParser> getBeaconParsers() {
        return this.beaconParsers;
    }

    public boolean isAnyConsumerBound() {
        boolean z;
        synchronized (this.consumers) {
            z = this.consumers.isEmpty() && this.serviceMessenger != null;
        }
        return z;
    }

    public boolean getScheduledScanJobsEnabled() {
        return this.mScheduledScanJobsEnabled;
    }

    public boolean getBackgroundMode() {
        return this.mBackgroundMode;
    }

    public long getBackgroundScanPeriod() {
        return this.backgroundScanPeriod;
    }

    public long getBackgroundBetweenScanPeriod() {
        return this.backgroundBetweenScanPeriod;
    }

    public long getForegroundScanPeriod() {
        return this.foregroundScanPeriod;
    }

    public long getForegroundBetweenScanPeriod() {
        return this.foregroundBetweenScanPeriod;
    }

    public boolean isRegionStatePersistenceEnabled() {
        return this.mRegionStatePersistenceEnabled;
    }

    public void applySettings() {
        if (!determineIfCalledFromSeparateScannerProcess()) {
            if (!isAnyConsumerBound()) {
                LogManager.d("BeaconManager", "Not synchronizing settings to service, as it has not started up yet", new Object[0]);
            } else if (isScannerInDifferentProcess()) {
                LogManager.d("BeaconManager", "Synchronizing settings to service", new Object[0]);
                syncSettingsToService();
            } else {
                LogManager.d("BeaconManager", "Not synchronizing settings to service, as it is in the same process", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void syncSettingsToService() {
        if (this.mScheduledScanJobsEnabled) {
            ScanJobScheduler.getInstance().applySettingsToScheduledJob(this.mContext, this);
            return;
        }
        try {
            applyChangesToServices(7, (Region) null);
        } catch (RemoteException e) {
            LogManager.e("BeaconManager", "Failed to sync settings to service", e);
        }
    }

    private void applyChangesToServices(int i, Region region) throws RemoteException {
        if (this.mScheduledScanJobsEnabled) {
            ScanJobScheduler.getInstance().applySettingsToScheduledJob(this.mContext, this);
        } else if (this.serviceMessenger != null) {
            Message obtain = Message.obtain((Handler) null, i, 0, 0);
            if (i == 6) {
                obtain.setData(new StartRMData(getScanPeriod(), getBetweenScanPeriod(), this.mBackgroundMode).toBundle());
            } else if (i == 7) {
                obtain.setData(new SettingsData().collect(this.mContext).toBundle());
            } else {
                obtain.setData(new StartRMData(region, callbackPackageName(), getScanPeriod(), getBetweenScanPeriod(), this.mBackgroundMode).toBundle());
            }
            this.serviceMessenger.send(obtain);
        } else {
            throw new RemoteException("The BeaconManager is not bound to the service.  Call beaconManager.bind(BeaconConsumer consumer) and wait for a callback to onBeaconServiceConnect()");
        }
    }

    private String callbackPackageName() {
        String packageName = this.mContext.getPackageName();
        LogManager.d("BeaconManager", "callback packageName: %s", packageName);
        return packageName;
    }

    public Set<MonitorNotifier> getMonitoringNotifiers() {
        return Collections.unmodifiableSet(this.monitorNotifiers);
    }

    public Set<RangeNotifier> getRangingNotifiers() {
        return Collections.unmodifiableSet(this.rangeNotifiers);
    }

    public Collection<Region> getMonitoredRegions() {
        return MonitoringStatus.getInstanceForApplication(this.mContext).regions();
    }

    public Collection<Region> getRangedRegions() {
        ArrayList arrayList;
        synchronized (this.rangedRegions) {
            arrayList = new ArrayList(this.rangedRegions);
        }
        return arrayList;
    }

    public static String getDistanceModelUpdateUrl() {
        return distanceModelUpdateUrl;
    }

    public static Class getRssiFilterImplClass() {
        return rssiFilterImplClass;
    }

    public static BeaconSimulator getBeaconSimulator() {
        return beaconSimulator;
    }

    /* access modifiers changed from: protected */
    public RangeNotifier getDataRequestNotifier() {
        return this.dataRequestNotifier;
    }

    public NonBeaconLeScanCallback getNonBeaconLeScanCallback() {
        return this.mNonBeaconLeScanCallback;
    }

    private long getScanPeriod() {
        if (this.mBackgroundMode) {
            return this.backgroundScanPeriod;
        }
        return this.foregroundScanPeriod;
    }

    private long getBetweenScanPeriod() {
        if (this.mBackgroundMode) {
            return this.backgroundBetweenScanPeriod;
        }
        return this.foregroundBetweenScanPeriod;
    }

    private void verifyServiceDeclaration() {
        List<ResolveInfo> queryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent(this.mContext, BeaconService.class), 65536);
        if (queryIntentServices != null && queryIntentServices.isEmpty()) {
            throw new ServiceNotDeclaredException();
        }
    }

    public class ServiceNotDeclaredException extends RuntimeException {
        public ServiceNotDeclaredException() {
            super("The BeaconService is not properly declared in AndroidManifest.xml.  If using Eclipse, please verify that your project.properties has manifestmerger.enabled=true");
        }
    }

    public static boolean isAndroidLScanningDisabled() {
        return sAndroidLScanningDisabled;
    }

    public static void setAndroidLScanningDisabled(boolean z) {
        sAndroidLScanningDisabled = z;
        BeaconManager beaconManager = sInstance;
        if (beaconManager != null) {
            beaconManager.applySettings();
        }
    }

    private boolean determineIfCalledFromSeparateScannerProcess() {
        if (!isScannerInDifferentProcess() || isMainProcess()) {
            return false;
        }
        LogManager.w("BeaconManager", "Ranging/Monitoring may not be controlled from a separate BeaconScanner process.  To remove this warning, please wrap this call in: if (beaconManager.isMainProcess())", new Object[0]);
        return true;
    }
}
