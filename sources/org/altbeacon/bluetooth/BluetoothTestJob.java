package org.altbeacon.bluetooth;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.HandlerThread;
import org.altbeacon.beacon.logging.LogManager;

public class BluetoothTestJob extends JobService {
    /* access modifiers changed from: private */
    public static final String TAG = BluetoothTestJob.class.getSimpleName();
    private static int sOverrideJobId = -1;
    private Handler mHandler = null;
    private HandlerThread mHandlerThread = null;

    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("BluetoothTestThread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                boolean z;
                LogManager.i(BluetoothTestJob.TAG, "Bluetooth Test Job running", new Object[0]);
                int i = jobParameters.getExtras().getInt("test_type");
                boolean z2 = true;
                if (i == 0) {
                    LogManager.d(BluetoothTestJob.TAG, "No test specified.  Done with job.", new Object[0]);
                    z = true;
                } else {
                    z = false;
                }
                if ((i & 1) == 1) {
                    LogManager.d(BluetoothTestJob.TAG, "Scan test specified.", new Object[0]);
                    if (!BluetoothMedic.getInstance().runScanTest(BluetoothTestJob.this)) {
                        LogManager.d(BluetoothTestJob.TAG, "scan test failed", new Object[0]);
                    }
                    z = true;
                }
                if ((i & 2) == 2) {
                    if (z) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException unused) {
                        }
                    }
                    LogManager.d(BluetoothTestJob.TAG, "Transmit test specified.", new Object[0]);
                    if (!BluetoothMedic.getInstance().runTransmitterTest(BluetoothTestJob.this)) {
                        LogManager.d(BluetoothTestJob.TAG, "transmit test failed", new Object[0]);
                    }
                } else {
                    z2 = z;
                }
                if (!z2) {
                    String access$000 = BluetoothTestJob.TAG;
                    LogManager.w(access$000, "Unknown test type:" + i + "  Exiting.", new Object[0]);
                }
                BluetoothTestJob.this.jobFinished(jobParameters, false);
            }
        });
        return true;
    }
}
