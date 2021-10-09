package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;

class NotificationSummaryManager {
    static void updatePossibleDependentSummaryOnDismiss(Context context, SQLiteDatabase sQLiteDatabase, int i) {
        Cursor query = sQLiteDatabase.query("notification", new String[]{"group_id"}, "android_notification_id = " + i, (String[]) null, (String) null, (String) null, (String) null);
        if (query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("group_id"));
            query.close();
            if (string != null) {
                updateSummaryNotificationAfterChildRemoved(context, sQLiteDatabase, string, true);
                return;
            }
            return;
        }
        query.close();
    }

    static void updateSummaryNotificationAfterChildRemoved(Context context, SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        try {
            Cursor internalUpdateSummaryNotificationAfterChildRemoved = internalUpdateSummaryNotificationAfterChildRemoved(context, sQLiteDatabase, str, z);
            if (internalUpdateSummaryNotificationAfterChildRemoved != null && !internalUpdateSummaryNotificationAfterChildRemoved.isClosed()) {
                internalUpdateSummaryNotificationAfterChildRemoved.close();
            }
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error running updateSummaryNotificationAfterChildRemoved!", th);
        }
    }

    private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(Context context, SQLiteDatabase sQLiteDatabase, String str, boolean z) {
        Cursor query = sQLiteDatabase.query("notification", new String[]{"android_notification_id", "created_time"}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, (String) null, (String) null, "_id DESC");
        int count = query.getCount();
        if (count == 0) {
            query.close();
            Integer summaryNotificationId = getSummaryNotificationId(sQLiteDatabase, str);
            if (summaryNotificationId == null) {
                return query;
            }
            OneSignalNotificationManager.getNotificationManager(context).cancel(summaryNotificationId.intValue());
            ContentValues contentValues = new ContentValues();
            contentValues.put(z ? "dismissed" : "opened", 1);
            sQLiteDatabase.update("notification", contentValues, "android_notification_id = " + summaryNotificationId, (String[]) null);
            return query;
        } else if (count == 1) {
            query.close();
            if (getSummaryNotificationId(sQLiteDatabase, str) == null) {
                return query;
            }
            restoreSummary(context, str);
            return query;
        } else {
            try {
                query.moveToFirst();
                Long valueOf = Long.valueOf(query.getLong(query.getColumnIndex("created_time")));
                query.close();
                if (getSummaryNotificationId(sQLiteDatabase, str) == null) {
                    return query;
                }
                NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(context);
                notificationGenerationJob.restoring = true;
                notificationGenerationJob.shownTimeStamp = valueOf;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("grp", str);
                notificationGenerationJob.jsonPayload = jSONObject;
                GenerateNotification.updateSummaryNotification(notificationGenerationJob);
                return query;
            } catch (JSONException unused) {
            }
        }
    }

    private static void restoreSummary(Context context, String str) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            cursor = OneSignalDbHelper.getInstance(context).getSQLiteDatabaseWithRetries().query("notification", NotificationRestorer.COLUMNS_FOR_RESTORE, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", strArr, (String) null, (String) null, (String) null);
            NotificationRestorer.showNotificationsFromCursor(context, cursor, 0);
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

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: type inference failed for: r9v4 */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Integer getSummaryNotificationId(android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "android_notification_id"
            java.lang.String r4 = "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1"
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]
            r1 = 0
            r5[r1] = r12
            r9 = 0
            java.lang.String r2 = "notification"
            java.lang.String[] r3 = new java.lang.String[]{r0}     // Catch:{ all -> 0x004e }
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x004e }
            boolean r1 = r11.moveToFirst()     // Catch:{ all -> 0x0049 }
            if (r1 != 0) goto L_0x002e
            r11.close()     // Catch:{ all -> 0x0049 }
            if (r11 == 0) goto L_0x002d
            boolean r12 = r11.isClosed()
            if (r12 != 0) goto L_0x002d
            r11.close()
        L_0x002d:
            return r9
        L_0x002e:
            int r0 = r11.getColumnIndex(r0)     // Catch:{ all -> 0x0049 }
            int r0 = r11.getInt(r0)     // Catch:{ all -> 0x0049 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0049 }
            r11.close()     // Catch:{ all -> 0x0049 }
            if (r11 == 0) goto L_0x0072
            boolean r12 = r11.isClosed()
            if (r12 != 0) goto L_0x0072
            r11.close()
            goto L_0x0072
        L_0x0049:
            r0 = move-exception
            r10 = r9
            r9 = r11
            r11 = r10
            goto L_0x0050
        L_0x004e:
            r0 = move-exception
            r11 = r9
        L_0x0050:
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x0073 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0073 }
            r2.<init>()     // Catch:{ all -> 0x0073 }
            java.lang.String r3 = "Error getting android notification id for summary notification group: "
            r2.append(r3)     // Catch:{ all -> 0x0073 }
            r2.append(r12)     // Catch:{ all -> 0x0073 }
            java.lang.String r12 = r2.toString()     // Catch:{ all -> 0x0073 }
            com.onesignal.OneSignal.Log(r1, r12, r0)     // Catch:{ all -> 0x0073 }
            if (r9 == 0) goto L_0x0071
            boolean r12 = r9.isClosed()
            if (r12 != 0) goto L_0x0071
            r9.close()
        L_0x0071:
            r9 = r11
        L_0x0072:
            return r9
        L_0x0073:
            r11 = move-exception
            if (r9 == 0) goto L_0x007f
            boolean r12 = r9.isClosed()
            if (r12 != 0) goto L_0x007f
            r9.close()
        L_0x007f:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationSummaryManager.getSummaryNotificationId(android.database.sqlite.SQLiteDatabase, java.lang.String):java.lang.Integer");
    }

    static void clearNotificationOnSummaryClick(Context context, SQLiteDatabase sQLiteDatabase, String str) {
        Integer summaryNotificationId = getSummaryNotificationId(sQLiteDatabase, str);
        boolean equals = str.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        Integer mostRecentNotifIdFromGroup = OneSignalNotificationManager.getMostRecentNotifIdFromGroup(sQLiteDatabase, str, equals);
        if (mostRecentNotifIdFromGroup == null) {
            return;
        }
        if (OneSignal.getClearGroupSummaryClick()) {
            if (equals) {
                summaryNotificationId = Integer.valueOf(OneSignalNotificationManager.getGrouplessSummaryId());
            }
            if (summaryNotificationId != null) {
                notificationManager.cancel(summaryNotificationId.intValue());
                return;
            }
            return;
        }
        OneSignal.cancelNotification(mostRecentNotifIdFromGroup.intValue());
    }
}
