package com.mopub.mobileads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {
    private final long mBroadcastIdentifier;
    private Context mContext;

    public abstract IntentFilter getIntentFilter();

    public BaseBroadcastReceiver(long j) {
        this.mBroadcastIdentifier = j;
    }

    public static void broadcastAction(Context context, long j, String str) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Preconditions.checkNotNull(str, "action cannot be null");
        Intent intent = new Intent(str);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
    }

    public void register(BroadcastReceiver broadcastReceiver, Context context) {
        this.mContext = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, getIntentFilter());
    }

    public void unregister(BroadcastReceiver broadcastReceiver) {
        Context context = this.mContext;
        if (context != null && broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
            this.mContext = null;
        }
    }

    public boolean shouldConsumeBroadcast(Intent intent) {
        Preconditions.checkNotNull(intent, "intent cannot be null");
        return this.mBroadcastIdentifier == intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1);
    }
}
