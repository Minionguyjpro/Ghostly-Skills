package com.appsgeyser.sdk.push;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.appsgeyser.sdk.R;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String str;
        String str2 = "";
        String prefString = new PreferencesCoder(context).getPrefString("ServerResponse", (String) null);
        if (prefString != null) {
            String inactivityReminderText = ConfigPhp.parseFromJson(prefString).getInactivityReminderText();
            PackageManager packageManager = context.getPackageManager();
            try {
                str = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 128));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                str = str2;
            }
            PendingIntent activity = PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), 1073741824);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Appsgeyser_channel_id");
            NotificationCompat.Builder contentIntent = builder.setAutoCancel(true).setDefaults(-1).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_os_notification_fallback_white_24dp).setContentTitle(str).setContentIntent(activity);
            if (inactivityReminderText != null) {
                str2 = inactivityReminderText;
            }
            contentIntent.setContentText(str2).setDefaults(5).setPriority(1);
            createNotificationChannel(context, str);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(1, builder.build());
            }
        }
    }

    private void createNotificationChannel(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("Appsgeyser_channel_id", str, 3);
            notificationChannel.setDescription("");
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
