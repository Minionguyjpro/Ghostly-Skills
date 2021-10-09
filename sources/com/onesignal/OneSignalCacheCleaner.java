package com.onesignal;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.onesignal.OneSignal;
import com.onesignal.influence.model.OSInfluenceChannel;

class OneSignalCacheCleaner {
    static void cleanOldCachedData(Context context) {
        OneSignalDbHelper instance = OneSignalDbHelper.getInstance(context);
        cleanNotificationCache(instance.getSQLiteDatabaseWithRetries());
        cleanCachedInAppMessages(instance);
    }

    static synchronized void cleanNotificationCache(final SQLiteDatabase sQLiteDatabase) {
        synchronized (OneSignalCacheCleaner.class) {
            new Thread(new Runnable() {
                public void run() {
                    Thread.currentThread().setPriority(10);
                    sQLiteDatabase.beginTransaction();
                    try {
                        OneSignalCacheCleaner.cleanCachedNotifications(sQLiteDatabase);
                        OneSignalCacheCleaner.cleanCachedUniqueOutcomeEventNotifications(sQLiteDatabase);
                        sQLiteDatabase.setTransactionSuccessful();
                    } finally {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (SQLException e) {
                            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", e);
                        }
                    }
                }
            }, "OS_DELETE_CACHED_NOTIFICATIONS_THREAD").start();
        }
    }

    static synchronized void cleanCachedInAppMessages(final OneSignalDbHelper oneSignalDbHelper) {
        synchronized (OneSignalCacheCleaner.class) {
            new Thread(new Runnable() {
                public void run() {
                    Thread.currentThread().setPriority(10);
                    OSInAppMessageController.getController().getInAppMessageRepository(oneSignalDbHelper).cleanCachedInAppMessages();
                }
            }, "OS_DELETE_CACHED_REDISPLAYED_IAMS_THREAD").start();
        }
    }

    /* access modifiers changed from: private */
    public static void cleanCachedNotifications(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("notification", "created_time < ?", new String[]{String.valueOf((System.currentTimeMillis() / 1000) - 604800)});
    }

    /* access modifiers changed from: private */
    public static void cleanCachedUniqueOutcomeEventNotifications(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("cached_unique_outcome", "NOT EXISTS(SELECT NULL FROM notification n WHERE n.notification_id = channel_influence_id AND channel_type = \"" + OSInfluenceChannel.NOTIFICATION.toString().toLowerCase() + "\")", (String[]) null);
    }
}
