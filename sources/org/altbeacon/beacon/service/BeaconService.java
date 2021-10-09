package org.altbeacon.beacon.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.distance.ModelSpecificDistanceCalculator;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.startup.StartupBroadcastReceiver;
import org.altbeacon.beacon.utils.ProcessUtils;
import org.altbeacon.bluetooth.BluetoothCrashResolver;

public class BeaconService extends Service {
    private BluetoothCrashResolver bluetoothCrashResolver;
    private final Handler handler = new Handler();
    final Messenger mMessenger = new Messenger(new IncomingHandler(this));
    private ScanHelper mScanHelper;

    static class IncomingHandler extends Handler {
        private final WeakReference<BeaconService> mService;

        IncomingHandler(BeaconService beaconService) {
            super(Looper.getMainLooper());
            this.mService = new WeakReference<>(beaconService);
        }

        public void handleMessage(Message message) {
            BeaconService beaconService = (BeaconService) this.mService.get();
            if (beaconService != null) {
                StartRMData fromBundle = StartRMData.fromBundle(message.getData());
                if (fromBundle != null) {
                    int i = message.what;
                    if (i == 2) {
                        LogManager.i("BeaconService", "start ranging received", new Object[0]);
                        beaconService.startRangingBeaconsInRegion(fromBundle.getRegionData(), new Callback(fromBundle.getCallbackPackageName()));
                        beaconService.setScanPeriods(fromBundle.getScanPeriod(), fromBundle.getBetweenScanPeriod(), fromBundle.getBackgroundFlag());
                    } else if (i == 3) {
                        LogManager.i("BeaconService", "stop ranging received", new Object[0]);
                        beaconService.stopRangingBeaconsInRegion(fromBundle.getRegionData());
                        beaconService.setScanPeriods(fromBundle.getScanPeriod(), fromBundle.getBetweenScanPeriod(), fromBundle.getBackgroundFlag());
                    } else if (i == 4) {
                        LogManager.i("BeaconService", "start monitoring received", new Object[0]);
                        beaconService.startMonitoringBeaconsInRegion(fromBundle.getRegionData(), new Callback(fromBundle.getCallbackPackageName()));
                        beaconService.setScanPeriods(fromBundle.getScanPeriod(), fromBundle.getBetweenScanPeriod(), fromBundle.getBackgroundFlag());
                    } else if (i == 5) {
                        LogManager.i("BeaconService", "stop monitoring received", new Object[0]);
                        beaconService.stopMonitoringBeaconsInRegion(fromBundle.getRegionData());
                        beaconService.setScanPeriods(fromBundle.getScanPeriod(), fromBundle.getBetweenScanPeriod(), fromBundle.getBackgroundFlag());
                    } else if (i != 6) {
                        super.handleMessage(message);
                    } else {
                        LogManager.i("BeaconService", "set scan intervals received", new Object[0]);
                        beaconService.setScanPeriods(fromBundle.getScanPeriod(), fromBundle.getBetweenScanPeriod(), fromBundle.getBackgroundFlag());
                    }
                } else if (message.what == 7) {
                    LogManager.i("BeaconService", "Received settings update from other process", new Object[0]);
                    SettingsData fromBundle2 = SettingsData.fromBundle(message.getData());
                    if (fromBundle2 != null) {
                        fromBundle2.apply(beaconService);
                    } else {
                        LogManager.w("BeaconService", "Settings data missing", new Object[0]);
                    }
                } else {
                    LogManager.i("BeaconService", "Received unknown message from other process : " + message.what, new Object[0]);
                }
            }
        }
    }

    public void onCreate() {
        BluetoothCrashResolver bluetoothCrashResolver2 = new BluetoothCrashResolver(this);
        this.bluetoothCrashResolver = bluetoothCrashResolver2;
        bluetoothCrashResolver2.start();
        ScanHelper scanHelper = new ScanHelper(this);
        this.mScanHelper = scanHelper;
        if (scanHelper.getCycledScanner() == null) {
            this.mScanHelper.createCycledLeScanner(false, this.bluetoothCrashResolver);
        }
        this.mScanHelper.setMonitoringStatus(MonitoringStatus.getInstanceForApplication(this));
        this.mScanHelper.setRangedRegionState(new HashMap());
        this.mScanHelper.setBeaconParsers(new HashSet());
        this.mScanHelper.setExtraDataBeaconTracker(new ExtraDataBeaconTracker());
        BeaconManager instanceForApplication = BeaconManager.getInstanceForApplication(getApplicationContext());
        instanceForApplication.setScannerInSameProcess(true);
        if (instanceForApplication.isMainProcess()) {
            LogManager.i("BeaconService", "beaconService version %s is starting up on the main process", "2.13.1");
        } else {
            LogManager.i("BeaconService", "beaconService version %s is starting up on a separate process", "2.13.1");
            ProcessUtils processUtils = new ProcessUtils(this);
            LogManager.i("BeaconService", "beaconService PID is " + processUtils.getPid() + " with process name " + processUtils.getProcessName(), new Object[0]);
        }
        try {
            ServiceInfo serviceInfo = getPackageManager().getServiceInfo(new ComponentName(this, BeaconService.class), 128);
            if (!(serviceInfo == null || serviceInfo.metaData == null || serviceInfo.metaData.get("longScanForcingEnabled") == null || !serviceInfo.metaData.get("longScanForcingEnabled").toString().equals("true"))) {
                LogManager.i("BeaconService", "longScanForcingEnabled to keep scans going on Android N for > 30 minutes", new Object[0]);
                this.mScanHelper.getCycledScanner().setLongScanForcingEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.mScanHelper.reloadParsers();
        Beacon.setDistanceCalculator(new ModelSpecificDistanceCalculator(this, BeaconManager.getDistanceModelUpdateUrl()));
        try {
            this.mScanHelper.setSimulatedScanData((List) Class.forName("org.altbeacon.beacon.SimulatedScanData").getField("beacons").get((Object) null));
        } catch (ClassNotFoundException unused2) {
            LogManager.d("BeaconService", "No org.altbeacon.beacon.SimulatedScanData class exists.", new Object[0]);
        } catch (Exception e) {
            LogManager.e(e, "BeaconService", "Cannot get simulated Scan data.  Make sure your org.altbeacon.beacon.SimulatedScanData class defines a field with the signature 'public static List<Beacon> beacons'", new Object[0]);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String str;
        if (intent == null) {
            str = "starting with null intent";
        } else {
            str = "starting with intent " + intent.toString();
        }
        LogManager.i("BeaconService", str, new Object[0]);
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        LogManager.i("BeaconService", "binding", new Object[0]);
        return this.mMessenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        LogManager.i("BeaconService", "unbinding", new Object[0]);
        return false;
    }

    public void onDestroy() {
        LogManager.e("BeaconService", "onDestroy()", new Object[0]);
        if (Build.VERSION.SDK_INT < 18) {
            LogManager.w("BeaconService", "Not supported prior to API 18.", new Object[0]);
            return;
        }
        this.bluetoothCrashResolver.stop();
        LogManager.i("BeaconService", "onDestroy called.  stopping scanning", new Object[0]);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.mScanHelper.getCycledScanner().stop();
        this.mScanHelper.getCycledScanner().destroy();
        this.mScanHelper.getMonitoringStatus().stopStatusPreservation();
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        LogManager.d("BeaconService", "task removed", new Object[0]);
        if (Build.VERSION.RELEASE.contains("4.4.1") || Build.VERSION.RELEASE.contains("4.4.2") || Build.VERSION.RELEASE.contains("4.4.3")) {
            ((AlarmManager) getApplicationContext().getSystemService("alarm")).set(0, System.currentTimeMillis() + 1000, getRestartIntent());
            LogManager.d("BeaconService", "Setting a wakeup alarm to go off due to Android 4.4.2 service restarting bug.", new Object[0]);
        }
    }

    private PendingIntent getRestartIntent() {
        return PendingIntent.getBroadcast(getApplicationContext(), 1, new Intent(getApplicationContext(), StartupBroadcastReceiver.class), 1073741824);
    }

    public void startRangingBeaconsInRegion(Region region, Callback callback) {
        synchronized (this.mScanHelper.getRangedRegionState()) {
            if (this.mScanHelper.getRangedRegionState().containsKey(region)) {
                LogManager.i("BeaconService", "Already ranging that region -- will replace existing region.", new Object[0]);
                this.mScanHelper.getRangedRegionState().remove(region);
            }
            this.mScanHelper.getRangedRegionState().put(region, new RangeState(callback));
            LogManager.d("BeaconService", "Currently ranging %s regions.", Integer.valueOf(this.mScanHelper.getRangedRegionState().size()));
        }
        this.mScanHelper.getCycledScanner().start();
    }

    public void stopRangingBeaconsInRegion(Region region) {
        int size;
        synchronized (this.mScanHelper.getRangedRegionState()) {
            this.mScanHelper.getRangedRegionState().remove(region);
            size = this.mScanHelper.getRangedRegionState().size();
            LogManager.d("BeaconService", "Currently ranging %s regions.", Integer.valueOf(this.mScanHelper.getRangedRegionState().size()));
        }
        if (size == 0 && this.mScanHelper.getMonitoringStatus().regionsCount() == 0) {
            this.mScanHelper.getCycledScanner().stop();
        }
    }

    public void startMonitoringBeaconsInRegion(Region region, Callback callback) {
        LogManager.d("BeaconService", "startMonitoring called", new Object[0]);
        this.mScanHelper.getMonitoringStatus().addRegion(region, callback);
        LogManager.d("BeaconService", "Currently monitoring %s regions.", Integer.valueOf(this.mScanHelper.getMonitoringStatus().regionsCount()));
        this.mScanHelper.getCycledScanner().start();
    }

    public void stopMonitoringBeaconsInRegion(Region region) {
        LogManager.d("BeaconService", "stopMonitoring called", new Object[0]);
        this.mScanHelper.getMonitoringStatus().removeRegion(region);
        LogManager.d("BeaconService", "Currently monitoring %s regions.", Integer.valueOf(this.mScanHelper.getMonitoringStatus().regionsCount()));
        if (this.mScanHelper.getMonitoringStatus().regionsCount() == 0 && this.mScanHelper.getRangedRegionState().size() == 0) {
            this.mScanHelper.getCycledScanner().stop();
        }
    }

    public void setScanPeriods(long j, long j2, boolean z) {
        this.mScanHelper.getCycledScanner().setScanPeriods(j, j2, z);
    }

    public void reloadParsers() {
        this.mScanHelper.reloadParsers();
    }
}
