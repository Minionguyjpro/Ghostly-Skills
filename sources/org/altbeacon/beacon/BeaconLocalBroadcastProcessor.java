package org.altbeacon.beacon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import org.altbeacon.beacon.logging.LogManager;

public class BeaconLocalBroadcastProcessor {
    static int registerCallCount;
    private Context mContext;
    private BroadcastReceiver mLocalBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            new IntentHandler().convertIntentsToCallbacks(context, intent);
        }
    };
    int registerCallCountForInstnace = 0;

    private BeaconLocalBroadcastProcessor() {
    }

    public BeaconLocalBroadcastProcessor(Context context) {
        this.mContext = context;
    }

    public void register() {
        registerCallCount++;
        this.registerCallCountForInstnace++;
        LogManager.d("BeaconLocalBroadcastProcessor", "Register calls: global=" + registerCallCount + " instance=" + this.registerCallCountForInstnace, new Object[0]);
        unregister();
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mLocalBroadcastReceiver, new IntentFilter("org.altbeacon.beacon.range_notification"));
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mLocalBroadcastReceiver, new IntentFilter("org.altbeacon.beacon.monitor_notification"));
    }

    public void unregister() {
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mLocalBroadcastReceiver);
    }
}
