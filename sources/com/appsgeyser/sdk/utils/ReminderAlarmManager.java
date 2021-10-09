package com.appsgeyser.sdk.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.push.AlarmReceiver;

public class ReminderAlarmManager {
    public static void startReminderAlarm(Context context, int i, boolean z) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PreferencesCoder preferencesCoder = new PreferencesCoder(context);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 994, new Intent(context, AlarmReceiver.class), 0);
        long j = ((long) i) * 86400000;
        if (alarmManager != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (z) {
                alarmManager.cancel(broadcast);
                alarmManager.setRepeating(0, currentTimeMillis + j, 86400000, broadcast);
                preferencesCoder.savePrefLong("appsgeyserSdk_lastReminderSetTiming", currentTimeMillis);
                return;
            }
            alarmManager.setRepeating(0, j + preferencesCoder.getPrefLong("appsgeyserSdk_lastReminderSetTiming", System.currentTimeMillis()), 86400000, broadcast);
        }
    }
}
