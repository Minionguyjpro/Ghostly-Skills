package com.appsgeyser.sdk.server.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.analytics.Analytics;
import java.util.ArrayList;

public class NetworkAvailableReceiver extends BroadcastReceiver {
    private final ArrayList<OnNetworkStateChangedListener> listeners = new ArrayList<>();

    public static synchronized NetworkAvailableReceiver createAndRegisterNetworkReceiver(Context context) {
        NetworkAvailableReceiver networkAvailableReceiver;
        synchronized (NetworkAvailableReceiver.class) {
            networkAvailableReceiver = new NetworkAvailableReceiver();
            context.registerReceiver(networkAvailableReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        return networkAvailableReceiver;
    }

    public void onReceive(Context context, Intent intent) {
        notifyAllListeners(context);
    }

    private void notifyAllListeners(Context context) {
        int i = 0;
        if (NetworkManager.isOnline(context)) {
            int size = this.listeners.size();
            while (i < size) {
                this.listeners.get(i).networkIsUp();
                i++;
            }
            return;
        }
        int size2 = this.listeners.size();
        while (i < size2) {
            this.listeners.get(i).networkIsDown();
            i++;
        }
    }

    public void addListener(OnNetworkStateChangedListener onNetworkStateChangedListener) {
        if (!this.listeners.contains(onNetworkStateChangedListener)) {
            this.listeners.add(onNetworkStateChangedListener);
        }
    }

    public OnNetworkStateChangedListener createNetworkAvailableListener(final Context context) {
        return new OnNetworkStateChangedListener(getClass().getSimpleName()) {
            public void networkIsDown() {
            }

            public void networkIsUp() {
                InternalEntryPoint.getInstance().retryParsers(context);
                if (!Analytics.getInstance(context).isUsageAlreadySent()) {
                    Analytics.getInstance(context).activityStarted(context);
                }
            }
        };
    }
}
