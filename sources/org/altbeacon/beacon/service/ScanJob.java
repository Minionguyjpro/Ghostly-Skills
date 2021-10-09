package org.altbeacon.beacon.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.distance.ModelSpecificDistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.utils.ProcessUtils;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public class ScanJob extends JobService {
    /* access modifiers changed from: private */
    public static final String TAG = ScanJob.class.getSimpleName();
    private static int sOverrideImmediateScanJobId = -1;
    private static int sOverridePeriodicScanJobId = -1;
    private boolean mInitialized = false;
    private ScanHelper mScanHelper;
    /* access modifiers changed from: private */
    public ScanState mScanState;
    /* access modifiers changed from: private */
    public Handler mStopHandler = new Handler();

    public boolean onStartJob(final JobParameters jobParameters) {
        boolean z;
        initialzeScanHelper();
        if (jobParameters.getJobId() == getImmediateScanJobId(this)) {
            String str = TAG;
            LogManager.i(str, "Running immediate scan job: instance is " + this, new Object[0]);
        } else {
            String str2 = TAG;
            LogManager.i(str2, "Running periodic scan job: instance is " + this, new Object[0]);
        }
        List<ScanResult> dumpBackgroundScanResultQueue = ScanJobScheduler.getInstance().dumpBackgroundScanResultQueue();
        LogManager.d(TAG, "Processing %d queued scan resuilts", Integer.valueOf(dumpBackgroundScanResultQueue.size()));
        for (ScanResult next : dumpBackgroundScanResultQueue) {
            ScanRecord scanRecord = next.getScanRecord();
            if (scanRecord != null) {
                this.mScanHelper.processScanResult(next.getDevice(), next.getRssi(), scanRecord.getBytes());
            }
        }
        LogManager.d(TAG, "Done processing queued scan resuilts", new Object[0]);
        if (this.mInitialized) {
            LogManager.d(TAG, "Scanning already started.  Resetting for current parameters", new Object[0]);
            z = restartScanning();
        } else {
            z = startScanning();
        }
        this.mStopHandler.removeCallbacksAndMessages((Object) null);
        if (z) {
            String str3 = TAG;
            LogManager.i(str3, "Scan job running for " + this.mScanState.getScanJobRuntimeMillis() + " millis", new Object[0]);
            this.mStopHandler.postDelayed(new Runnable() {
                public void run() {
                    String access$000 = ScanJob.TAG;
                    LogManager.i(access$000, "Scan job runtime expired: " + ScanJob.this, new Object[0]);
                    ScanJob.this.stopScanning();
                    ScanJob.this.mScanState.save();
                    ScanJob.this.jobFinished(jobParameters, false);
                    ScanJob.this.mStopHandler.post(new Runnable() {
                        public void run() {
                            ScanJob.this.scheduleNextScan();
                        }
                    });
                }
            }, (long) this.mScanState.getScanJobRuntimeMillis());
        } else {
            LogManager.i(TAG, "Scanning not started so Scan job is complete.", new Object[0]);
            jobFinished(jobParameters, false);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void scheduleNextScan() {
        if (!this.mScanState.getBackgroundMode().booleanValue()) {
            LogManager.d(TAG, "In foreground mode, schedule next scan", new Object[0]);
            ScanJobScheduler.getInstance().forceScheduleNextScan(this);
            return;
        }
        startPassiveScanIfNeeded();
    }

    private void startPassiveScanIfNeeded() {
        LogManager.d(TAG, "Checking to see if we need to start a passive scan", new Object[0]);
        boolean z = false;
        for (Region stateOf : new ArrayList(this.mScanState.getMonitoringStatus().regions())) {
            RegionMonitoringState stateOf2 = this.mScanState.getMonitoringStatus().stateOf(stateOf);
            if (stateOf2 != null && stateOf2.getInside()) {
                z = true;
            }
        }
        if (z) {
            LogManager.i(TAG, "We are inside a beacon region.  We will not scan between cycles.", new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.mScanHelper.startAndroidOBackgroundScan(this.mScanState.getBeaconParsers());
        } else {
            LogManager.d(TAG, "This is not Android O.  No scanning between cycles when using ScanJob", new Object[0]);
        }
    }

    public boolean onStopJob(JobParameters jobParameters) {
        if (jobParameters.getJobId() == getPeriodicScanJobId(this)) {
            String str = TAG;
            LogManager.i(str, "onStopJob called for periodic scan " + this, new Object[0]);
        } else {
            String str2 = TAG;
            LogManager.i(str2, "onStopJob called for immediate scan " + this, new Object[0]);
        }
        this.mStopHandler.removeCallbacksAndMessages((Object) null);
        stopScanning();
        startPassiveScanIfNeeded();
        return false;
    }

    /* access modifiers changed from: private */
    public void stopScanning() {
        this.mInitialized = false;
        this.mScanHelper.getCycledScanner().stop();
        this.mScanHelper.getCycledScanner().destroy();
        LogManager.d(TAG, "Scanning stopped", new Object[0]);
    }

    private void initialzeScanHelper() {
        this.mScanHelper = new ScanHelper(this);
        ScanState restore = ScanState.restore(this);
        this.mScanState = restore;
        restore.setLastScanStartTimeMillis(System.currentTimeMillis());
        this.mScanHelper.setMonitoringStatus(this.mScanState.getMonitoringStatus());
        this.mScanHelper.setRangedRegionState(this.mScanState.getRangedRegionState());
        this.mScanHelper.setBeaconParsers(this.mScanState.getBeaconParsers());
        this.mScanHelper.setExtraDataBeaconTracker(this.mScanState.getExtraBeaconDataTracker());
        if (this.mScanHelper.getCycledScanner() == null) {
            this.mScanHelper.createCycledLeScanner(this.mScanState.getBackgroundMode().booleanValue(), (BluetoothCrashResolver) null);
        }
    }

    private boolean restartScanning() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.mScanHelper.stopAndroidOBackgroundScan();
        }
        long longValue = (this.mScanState.getBackgroundMode().booleanValue() ? this.mScanState.getBackgroundScanPeriod() : this.mScanState.getForegroundScanPeriod()).longValue();
        this.mScanHelper.getCycledScanner().setScanPeriods(longValue, (this.mScanState.getBackgroundMode().booleanValue() ? this.mScanState.getBackgroundBetweenScanPeriod() : this.mScanState.getForegroundBetweenScanPeriod()).longValue(), this.mScanState.getBackgroundMode().booleanValue());
        this.mInitialized = true;
        if (longValue <= 0) {
            LogManager.w(TAG, "Starting scan with scan period of zero.  Exiting ScanJob.", new Object[0]);
            this.mScanHelper.getCycledScanner().stop();
            return false;
        } else if (this.mScanHelper.getRangedRegionState().size() > 0 || this.mScanHelper.getMonitoringStatus().regions().size() > 0) {
            this.mScanHelper.getCycledScanner().start();
            return true;
        } else {
            this.mScanHelper.getCycledScanner().stop();
            return false;
        }
    }

    private boolean startScanning() {
        BeaconManager instanceForApplication = BeaconManager.getInstanceForApplication(getApplicationContext());
        instanceForApplication.setScannerInSameProcess(true);
        if (instanceForApplication.isMainProcess()) {
            LogManager.i(TAG, "scanJob version %s is starting up on the main process", "2.13.1");
        } else {
            LogManager.i(TAG, "beaconScanJob library version %s is starting up on a separate process", "2.13.1");
            ProcessUtils processUtils = new ProcessUtils(this);
            String str = TAG;
            LogManager.i(str, "beaconScanJob PID is " + processUtils.getPid() + " with process name " + processUtils.getProcessName(), new Object[0]);
        }
        Beacon.setDistanceCalculator(new ModelSpecificDistanceCalculator(this, BeaconManager.getDistanceModelUpdateUrl()));
        return restartScanning();
    }

    public static int getImmediateScanJobId(Context context) {
        if (sOverrideImmediateScanJobId < 0) {
            return getJobIdFromManifest(context, "immediateScanJobId");
        }
        String str = TAG;
        LogManager.i(str, "Using ImmediateScanJobId from static override: " + sOverrideImmediateScanJobId, new Object[0]);
        return sOverrideImmediateScanJobId;
    }

    public static int getPeriodicScanJobId(Context context) {
        if (sOverrideImmediateScanJobId < 0) {
            return getJobIdFromManifest(context, "periodicScanJobId");
        }
        String str = TAG;
        LogManager.i(str, "Using PeriodicScanJobId from static override: " + sOverridePeriodicScanJobId, new Object[0]);
        return sOverridePeriodicScanJobId;
    }

    private static int getJobIdFromManifest(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, ScanJob.class), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            serviceInfo = null;
        }
        if (serviceInfo == null || serviceInfo.metaData == null || serviceInfo.metaData.get(str) == null) {
            throw new RuntimeException("Cannot get job id from manifest.  Make sure that the " + str + " is configured in the manifest for the ScanJob.");
        }
        int i = serviceInfo.metaData.getInt(str);
        String str2 = TAG;
        LogManager.i(str2, "Using " + str + " from manifest: " + i, new Object[0]);
        return i;
    }
}
