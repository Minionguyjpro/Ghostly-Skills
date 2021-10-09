package com.appsgeyser.sdk.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.google.gson.JsonSyntaxException;

public class PushStarterReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String prefString;
        if (intent != null && context != null) {
            String action = intent.getAction();
            if ((action.equals("android.intent.action.BOOT_COMPLETED") || action.equals("android.intent.action.QUICKBOOT_POWERON") || action.equals("android.intent.action.REBOOT") || action.equals("com.htc.intent.action.QUICKBOOT_POWERON")) && (prefString = new PreferencesCoder(context).getPrefString("ServerResponse", (String) null)) != null) {
                ConfigPhp parseFromJson = ConfigPhp.parseFromJson(prefString);
                if (parseFromJson.isPushNotificationsActive()) {
                    try {
                        OneSignalCreator.init(context);
                    } catch (JsonSyntaxException unused) {
                        Log.d("OneSignalStarterRcv", "unexpected JsonSyntaxException");
                    }
                }
                if (parseFromJson.isInactivityReminderEnabled()) {
                    Intent intent2 = new Intent(context, AlarmService.class);
                    intent2.putExtra("days_inactivity", parseFromJson.getInactivityDaysPeriod());
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.startForegroundService(intent2);
                    } else {
                        context.startService(intent2);
                    }
                }
            }
        }
    }
}
