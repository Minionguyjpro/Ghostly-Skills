package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.onesignal.OneSignal;
import org.json.JSONArray;
import org.json.JSONObject;

class NotificationOpenedProcessor {
    private static final String TAG = NotificationOpenedProcessor.class.getCanonicalName();

    NotificationOpenedProcessor() {
    }

    static void processFromContext(Context context, Intent intent) {
        if (isOneSignalIntent(intent)) {
            OneSignal.setAppContext(context);
            handleDismissFromActionButtonPress(context, intent);
            processIntent(context, intent);
        }
    }

    private static boolean isOneSignalIntent(Intent intent) {
        return intent.hasExtra("onesignalData") || intent.hasExtra("summary") || intent.hasExtra("androidNotificationId");
    }

    private static void handleDismissFromActionButtonPress(Context context, Intent intent) {
        if (intent.getBooleanExtra("action_button", false)) {
            NotificationManagerCompat.from(context).cancel(intent.getIntExtra("androidNotificationId", 0));
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: org.json.JSONObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: org.json.JSONArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: org.json.JSONArray} */
    /* JADX WARNING: type inference failed for: r7v0, types: [org.json.JSONObject] */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0074 A[SYNTHETIC, Splitter:B:27:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void processIntent(android.content.Context r9, android.content.Intent r10) {
        /*
            java.lang.String r0 = "androidNotificationId"
            java.lang.String r1 = "Error closing transaction! "
            java.lang.String r2 = "onesignalData"
            java.lang.String r3 = "summary"
            java.lang.String r3 = r10.getStringExtra(r3)
            java.lang.String r4 = "dismissed"
            r5 = 0
            boolean r4 = r10.getBooleanExtra(r4, r5)
            r6 = 0
            if (r4 != 0) goto L_0x004b
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ all -> 0x0044 }
            java.lang.String r8 = r10.getStringExtra(r2)     // Catch:{ all -> 0x0044 }
            r7.<init>(r8)     // Catch:{ all -> 0x0044 }
            boolean r8 = handleIAMPreviewOpen(r9, r7)     // Catch:{ all -> 0x0042 }
            if (r8 == 0) goto L_0x0026
            return
        L_0x0026:
            int r8 = r10.getIntExtra(r0, r5)     // Catch:{ all -> 0x0042 }
            r7.put(r0, r8)     // Catch:{ all -> 0x0042 }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x0042 }
            r10.putExtra(r2, r0)     // Catch:{ all -> 0x0042 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = r10.getStringExtra(r2)     // Catch:{ all -> 0x0042 }
            r0.<init>(r2)     // Catch:{ all -> 0x0042 }
            org.json.JSONArray r0 = com.onesignal.NotificationBundleProcessor.newJsonArray(r0)     // Catch:{ all -> 0x0042 }
            goto L_0x004d
        L_0x0042:
            r0 = move-exception
            goto L_0x0046
        L_0x0044:
            r0 = move-exception
            r7 = r6
        L_0x0046:
            r0.printStackTrace()
            r0 = r6
            goto L_0x004d
        L_0x004b:
            r0 = r6
            r7 = r0
        L_0x004d:
            com.onesignal.OneSignalDbHelper r2 = com.onesignal.OneSignalDbHelper.getInstance(r9)
            android.database.sqlite.SQLiteDatabase r6 = r2.getSQLiteDatabaseWithRetries()     // Catch:{ Exception -> 0x007a }
            r6.beginTransaction()     // Catch:{ Exception -> 0x007a }
            if (r4 != 0) goto L_0x005f
            if (r3 == 0) goto L_0x005f
            addChildNotifications(r0, r3, r6)     // Catch:{ Exception -> 0x007a }
        L_0x005f:
            markNotificationsConsumed(r9, r10, r6, r4)     // Catch:{ Exception -> 0x007a }
            if (r3 != 0) goto L_0x006f
            java.lang.String r2 = "grp"
            java.lang.String r2 = r10.getStringExtra(r2)     // Catch:{ Exception -> 0x007a }
            if (r2 == 0) goto L_0x006f
            com.onesignal.NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(r9, r6, r2, r4)     // Catch:{ Exception -> 0x007a }
        L_0x006f:
            r6.setTransactionSuccessful()     // Catch:{ Exception -> 0x007a }
            if (r6 == 0) goto L_0x008e
            r6.endTransaction()     // Catch:{ all -> 0x0088 }
            goto L_0x008e
        L_0x0078:
            r9 = move-exception
            goto L_0x009e
        L_0x007a:
            r2 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0078 }
            java.lang.String r8 = "Error processing notification open or dismiss record! "
            com.onesignal.OneSignal.Log(r3, r8, r2)     // Catch:{ all -> 0x0078 }
            if (r6 == 0) goto L_0x008e
            r6.endTransaction()     // Catch:{ all -> 0x0088 }
            goto L_0x008e
        L_0x0088:
            r2 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR
            com.onesignal.OneSignal.Log(r3, r1, r2)
        L_0x008e:
            if (r4 != 0) goto L_0x009d
            java.lang.String r1 = "from_alert"
            boolean r10 = r10.getBooleanExtra(r1, r5)
            java.lang.String r1 = com.onesignal.OSNotificationFormatHelper.getOSNotificationIdFromJson(r7)
            com.onesignal.OneSignal.handleNotificationOpen(r9, r0, r10, r1)
        L_0x009d:
            return
        L_0x009e:
            if (r6 == 0) goto L_0x00aa
            r6.endTransaction()     // Catch:{ all -> 0x00a4 }
            goto L_0x00aa
        L_0x00a4:
            r10 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r0 = com.onesignal.OneSignal.LOG_LEVEL.ERROR
            com.onesignal.OneSignal.Log(r0, r1, r10)
        L_0x00aa:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationOpenedProcessor.processIntent(android.content.Context, android.content.Intent):void");
    }

    static boolean handleIAMPreviewOpen(Context context, JSONObject jSONObject) {
        String inAppPreviewPushUUID = NotificationBundleProcessor.inAppPreviewPushUUID(jSONObject);
        if (inAppPreviewPushUUID == null) {
            return false;
        }
        OneSignal.startOrResumeApp(context);
        OSInAppMessageController.getController().displayPreviewMessage(inAppPreviewPushUUID);
        return true;
    }

    private static void addChildNotifications(JSONArray jSONArray, String str, SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("notification", new String[]{"full_data"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, (String) null, (String) null, (String) null);
        if (query.getCount() > 1) {
            query.moveToFirst();
            do {
                try {
                    jSONArray.put(new JSONObject(query.getString(query.getColumnIndex("full_data"))));
                } catch (Throwable unused) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Could not parse JSON of sub notification in group: " + str);
                }
            } while (query.moveToNext());
        }
        query.close();
    }

    private static void markNotificationsConsumed(Context context, Intent intent, SQLiteDatabase sQLiteDatabase, boolean z) {
        String str;
        String stringExtra = intent.getStringExtra("summary");
        String[] strArr = null;
        if (stringExtra != null) {
            boolean equals = stringExtra.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
            if (equals) {
                str = "group_id IS NULL";
            } else {
                strArr = new String[]{stringExtra};
                str = "group_id = ?";
            }
            if (!z && !OneSignal.getClearGroupSummaryClick()) {
                String valueOf = String.valueOf(OneSignalNotificationManager.getMostRecentNotifIdFromGroup(sQLiteDatabase, stringExtra, equals));
                str = str + " AND android_notification_id = ?";
                strArr = equals ? new String[]{valueOf} : new String[]{stringExtra, valueOf};
            }
        } else {
            str = "android_notification_id = " + intent.getIntExtra("androidNotificationId", 0);
        }
        clearStatusBarNotifications(context, sQLiteDatabase, stringExtra);
        sQLiteDatabase.update("notification", newContentValuesWithConsumed(intent), str, strArr);
        BadgeCountUpdater.update(sQLiteDatabase, context);
    }

    private static void clearStatusBarNotifications(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        if (str != null) {
            NotificationSummaryManager.clearNotificationOnSummaryClick(context, sQLiteDatabase, str);
        } else if (Build.VERSION.SDK_INT >= 23 && OneSignalNotificationManager.getGrouplessNotifsCount(context).intValue() < 1) {
            OneSignalNotificationManager.getNotificationManager(context).cancel(OneSignalNotificationManager.getGrouplessSummaryId());
        }
    }

    private static ContentValues newContentValuesWithConsumed(Intent intent) {
        ContentValues contentValues = new ContentValues();
        if (intent.getBooleanExtra("dismissed", false)) {
            contentValues.put("dismissed", 1);
        } else {
            contentValues.put("opened", 1);
        }
        return contentValues;
    }
}
