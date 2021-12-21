package com.appsgeyser.sdk.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.appsgeyser.sdk.utils.ReminderAlarmManager;

public class AlarmService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        ReminderAlarmManager.startReminderAlarm(this, intent.getIntExtra("days_inactivity", 1), false);
        return 2;
    }
}
