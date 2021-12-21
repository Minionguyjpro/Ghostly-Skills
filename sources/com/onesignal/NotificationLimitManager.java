package com.onesignal;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import com.google.android.gms.ads.AdRequest;
import java.util.Map;
import java.util.TreeMap;

class NotificationLimitManager {
    static final String MAX_NUMBER_OF_NOTIFICATIONS_STR = Integer.toString(49);

    private static int getMaxNumberOfNotificationsInt() {
        return 49;
    }

    private static String getMaxNumberOfNotificationsString() {
        return MAX_NUMBER_OF_NOTIFICATIONS_STR;
    }

    static void clearOldestOverLimit(Context context, int i) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                clearOldestOverLimitStandard(context, i);
            } else {
                clearOldestOverLimitFallback(context, i);
            }
        } catch (Throwable unused) {
            clearOldestOverLimitFallback(context, i);
        }
    }

    static void clearOldestOverLimitStandard(Context context, int i) throws Throwable {
        StatusBarNotification[] activeNotifications = OneSignalNotificationManager.getActiveNotifications(context);
        int length = (activeNotifications.length - getMaxNumberOfNotificationsInt()) + i;
        if (length >= 1) {
            TreeMap treeMap = new TreeMap();
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                if (!isGroupSummary(statusBarNotification)) {
                    treeMap.put(Long.valueOf(statusBarNotification.getNotification().when), Integer.valueOf(statusBarNotification.getId()));
                }
            }
            for (Map.Entry value : treeMap.entrySet()) {
                OneSignal.cancelNotification(((Integer) value.getValue()).intValue());
                length--;
                if (length <= 0) {
                    return;
                }
            }
        }
    }

    static void clearOldestOverLimitFallback(Context context, int i) {
        Cursor cursor = null;
        try {
            cursor = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries().query("notification", new String[]{"android_notification_id"}, OneSignalDbHelper.recentUninteractedWithNotificationsWhere().toString(), (String[]) null, (String) null, (String) null, "_id", getMaxNumberOfNotificationsString() + i);
            int count = (cursor.getCount() - getMaxNumberOfNotificationsInt()) + i;
            if (count >= 1) {
                do {
                    if (!cursor.moveToNext()) {
                        break;
                    }
                    OneSignal.cancelNotification(cursor.getInt(cursor.getColumnIndex("android_notification_id")));
                    count--;
                } while (count > 0);
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } else if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    static boolean isGroupSummary(StatusBarNotification statusBarNotification) {
        return (statusBarNotification.getNotification().flags & AdRequest.MAX_CONTENT_URL_LENGTH) != 0;
    }
}
