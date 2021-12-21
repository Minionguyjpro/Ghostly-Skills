package com.onesignal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.onesignal.OneSignal;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.ShortcutBadger;

class BadgeCountUpdater {
    private static int badgesEnabled = -1;

    private static boolean areBadgeSettingsEnabled(Context context) {
        int i = badgesEnabled;
        if (i != -1) {
            return i == 1;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                badgesEnabled = "DISABLE".equals(bundle.getString("com.onesignal.BadgeCount")) ? 0 : 1;
            } else {
                badgesEnabled = 1;
            }
        } catch (PackageManager.NameNotFoundException e) {
            badgesEnabled = 0;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error reading meta-data tag 'com.onesignal.BadgeCount'. Disabling badge setting.", e);
        }
        if (badgesEnabled == 1) {
            return true;
        }
        return false;
    }

    private static boolean areBadgesEnabled(Context context) {
        return areBadgeSettingsEnabled(context) && OSUtils.areNotificationsEnabled(context);
    }

    static void update(SQLiteDatabase sQLiteDatabase, Context context) {
        if (areBadgesEnabled(context)) {
            if (Build.VERSION.SDK_INT >= 23) {
                updateStandard(context);
            } else {
                updateFallback(sQLiteDatabase, context);
            }
        }
    }

    private static void updateStandard(Context context) {
        int i = 0;
        for (StatusBarNotification isGroupSummary : OneSignalNotificationManager.getActiveNotifications(context)) {
            if (!NotificationLimitManager.isGroupSummary(isGroupSummary)) {
                i++;
            }
        }
        updateCount(i, context);
    }

    private static void updateFallback(SQLiteDatabase sQLiteDatabase, Context context) {
        Cursor query = sQLiteDatabase.query("notification", (String[]) null, OneSignalDbHelper.recentUninteractedWithNotificationsWhere().toString(), (String[]) null, (String) null, (String) null, (String) null, NotificationLimitManager.MAX_NUMBER_OF_NOTIFICATIONS_STR);
        int count = query.getCount();
        query.close();
        updateCount(count, context);
    }

    static void updateCount(int i, Context context) {
        if (areBadgeSettingsEnabled(context)) {
            try {
                ShortcutBadger.applyCountOrThrow(context, i);
            } catch (ShortcutBadgeException unused) {
            }
        }
    }
}
