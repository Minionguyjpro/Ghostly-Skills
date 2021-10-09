package com.onesignal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.onesignal.OneSignal;
import java.util.ArrayList;

class NotificationRestorer {
    static final String[] COLUMNS_FOR_RESTORE = {"android_notification_id", "full_data", "created_time"};
    public static boolean restored;

    static void asyncRestore(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setPriority(10);
                NotificationRestorer.restore(context);
            }
        }, "OS_RESTORE_NOTIFS").start();
    }

    public static void restore(Context context) {
        if (OSUtils.areNotificationsEnabled(context) && !restored) {
            restored = true;
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Restoring notifications");
            OneSignalDbHelper instance = OneSignalDbHelper.getInstance(context);
            StringBuilder recentUninteractedWithNotificationsWhere = OneSignalDbHelper.recentUninteractedWithNotificationsWhere();
            skipVisibleNotifications(context, recentUninteractedWithNotificationsWhere);
            queryAndRestoreNotificationsAndBadgeCount(context, instance, recentUninteractedWithNotificationsWhere);
        }
    }

    private static void queryAndRestoreNotificationsAndBadgeCount(Context context, OneSignalDbHelper oneSignalDbHelper, StringBuilder sb) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
        OneSignal.Log(log_level, "Querying DB for notifs to restore: " + sb.toString());
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabaseWithRetries = oneSignalDbHelper.getSQLiteDatabaseWithRetries();
            cursor = sQLiteDatabaseWithRetries.query("notification", COLUMNS_FOR_RESTORE, sb.toString(), (String[]) null, (String) null, (String) null, "_id DESC", NotificationLimitManager.MAX_NUMBER_OF_NOTIFICATIONS_STR);
            showNotificationsFromCursor(context, cursor, 200);
            BadgeCountUpdater.update(sQLiteDatabaseWithRetries, context);
            if (cursor == null || cursor.isClosed()) {
                return;
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }

    private static void skipVisibleNotifications(Context context, StringBuilder sb) {
        if (Build.VERSION.SDK_INT >= 23) {
            StatusBarNotification[] activeNotifications = OneSignalNotificationManager.getActiveNotifications(context);
            if (activeNotifications.length != 0) {
                ArrayList arrayList = new ArrayList();
                for (StatusBarNotification id : activeNotifications) {
                    arrayList.add(Integer.valueOf(id.getId()));
                }
                sb.append(" AND android_notification_id NOT IN (");
                sb.append(TextUtils.join(",", arrayList));
                sb.append(")");
            }
        }
    }

    static void showNotificationsFromCursor(Context context, Cursor cursor, int i) {
        if (cursor.moveToFirst()) {
            boolean z = NotificationExtenderService.getIntent(context) != null;
            do {
                if (z) {
                    Intent intent = NotificationExtenderService.getIntent(context);
                    addRestoreExtras(intent, cursor);
                    NotificationExtenderService.enqueueWork(context, intent.getComponent(), 2071862121, intent, false);
                } else {
                    RestoreJobService.enqueueWork(context, new ComponentName(context, RestoreJobService.class), 2071862122, addRestoreExtras(new Intent(), cursor), false);
                }
                if (i > 0) {
                    OSUtils.sleep(i);
                }
            } while (cursor.moveToNext());
        }
    }

    private static Intent addRestoreExtras(Intent intent, Cursor cursor) {
        int i = cursor.getInt(cursor.getColumnIndex("android_notification_id"));
        String string = cursor.getString(cursor.getColumnIndex("full_data"));
        intent.putExtra("json_payload", string).putExtra("android_notif_id", i).putExtra("restoring", true).putExtra(AvidJSONUtil.KEY_TIMESTAMP, Long.valueOf(cursor.getLong(cursor.getColumnIndex("created_time"))));
        return intent;
    }

    static void startDelayedRestoreTaskFromReceiver(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleRestoreKickoffJob");
            ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(2071862120, new ComponentName(context, RestoreKickoffJobService.class)).setOverrideDeadline(15000).setMinimumLatency(15000).build());
            return;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "scheduleRestoreKickoffAlarmTask");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(), NotificationRestoreService.class.getName()));
        PendingIntent service = PendingIntent.getService(context, 2071862120, intent, 134217728);
        ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 15000, service);
    }
}
