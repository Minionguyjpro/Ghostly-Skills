package com.startapp.android.publish.common.metaData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.startapp.android.publish.adsCommon.Utils.b;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class BootCompleteListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            g.a(3, "BootCompleteListener - onReceive");
            long elapsedRealtime = SystemClock.elapsedRealtime() + 60000;
            b.a(context);
            b.a(context, Long.valueOf(elapsedRealtime));
            b.a(context, elapsedRealtime);
        } catch (Exception e) {
            f.a(context, d.EXCEPTION, "BootCompleteListener.onReceive - failed to start services", e.getMessage(), "");
        }
    }
}
