package com.onesignal;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.onesignal.OneSignal;
import java.util.Set;

class OSInAppMessageRepository {
    private final OneSignalDbHelper dbHelper;

    OSInAppMessageRepository(OneSignalDbHelper oneSignalDbHelper) {
        this.dbHelper = oneSignalDbHelper;
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveInAppMessage(OSInAppMessage oSInAppMessage) {
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        sQLiteDatabaseWithRetries.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("message_id", oSInAppMessage.messageId);
            contentValues.put("display_quantity", Integer.valueOf(oSInAppMessage.getRedisplayStats().getDisplayQuantity()));
            contentValues.put("last_display", Long.valueOf(oSInAppMessage.getRedisplayStats().getLastDisplayTime()));
            contentValues.put("click_ids", oSInAppMessage.getClickedClickIds().toString());
            contentValues.put("displayed_in_session", Boolean.valueOf(oSInAppMessage.isDisplayedInSession()));
            if (sQLiteDatabaseWithRetries.update("in_app_message", contentValues, "message_id = ?", new String[]{oSInAppMessage.messageId}) == 0) {
                sQLiteDatabaseWithRetries.insert("in_app_message", (String) null, contentValues);
            }
            sQLiteDatabaseWithRetries.setTransactionSuccessful();
            sQLiteDatabaseWithRetries.endTransaction();
        } catch (SQLException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", e);
        } catch (Throwable th) {
            try {
                sQLiteDatabaseWithRetries.endTransaction();
            } catch (SQLException e2) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error closing transaction! ", e2);
            }
            throw th;
        }
        return;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0078, code lost:
        if (r1.isClosed() == false) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008e, code lost:
        if (r1.isClosed() == false) goto L_0x007a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.onesignal.OSInAppMessage> getCachedInAppMessages() {
        /*
            r11 = this;
            monitor-enter(r11)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x009f }
            r0.<init>()     // Catch:{ all -> 0x009f }
            r1 = 0
            com.onesignal.OneSignalDbHelper r2 = r11.dbHelper     // Catch:{ JSONException -> 0x0080 }
            android.database.sqlite.SQLiteDatabase r3 = r2.getSQLiteDatabaseWithRetries()     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r4 = "in_app_message"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ JSONException -> 0x0080 }
            boolean r2 = r1.moveToFirst()     // Catch:{ JSONException -> 0x0080 }
            if (r2 == 0) goto L_0x0072
        L_0x001f:
            java.lang.String r2 = "message_id"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r3 = "click_ids"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r3 = r1.getString(r3)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r4 = "display_quantity"
            int r4 = r1.getColumnIndex(r4)     // Catch:{ JSONException -> 0x0080 }
            int r4 = r1.getInt(r4)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r5 = "last_display"
            int r5 = r1.getColumnIndex(r5)     // Catch:{ JSONException -> 0x0080 }
            long r5 = r1.getLong(r5)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r7 = "displayed_in_session"
            int r7 = r1.getColumnIndex(r7)     // Catch:{ JSONException -> 0x0080 }
            int r7 = r1.getInt(r7)     // Catch:{ JSONException -> 0x0080 }
            r8 = 1
            if (r7 != r8) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r8 = 0
        L_0x0056:
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0080 }
            r7.<init>(r3)     // Catch:{ JSONException -> 0x0080 }
            java.util.Set r3 = com.onesignal.OSUtils.newStringSetFromJSONArray(r7)     // Catch:{ JSONException -> 0x0080 }
            com.onesignal.OSInAppMessage r7 = new com.onesignal.OSInAppMessage     // Catch:{ JSONException -> 0x0080 }
            com.onesignal.OSInAppMessageRedisplayStats r9 = new com.onesignal.OSInAppMessageRedisplayStats     // Catch:{ JSONException -> 0x0080 }
            r9.<init>(r4, r5)     // Catch:{ JSONException -> 0x0080 }
            r7.<init>(r2, r3, r8, r9)     // Catch:{ JSONException -> 0x0080 }
            r0.add(r7)     // Catch:{ JSONException -> 0x0080 }
            boolean r2 = r1.moveToNext()     // Catch:{ JSONException -> 0x0080 }
            if (r2 != 0) goto L_0x001f
        L_0x0072:
            if (r1 == 0) goto L_0x0091
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x009f }
            if (r2 != 0) goto L_0x0091
        L_0x007a:
            r1.close()     // Catch:{ all -> 0x009f }
            goto L_0x0091
        L_0x007e:
            r0 = move-exception
            goto L_0x0093
        L_0x0080:
            r2 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x007e }
            java.lang.String r4 = "Generating JSONArray from iam click ids:JSON Failed."
            com.onesignal.OneSignal.Log(r3, r4, r2)     // Catch:{ all -> 0x007e }
            if (r1 == 0) goto L_0x0091
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x009f }
            if (r2 != 0) goto L_0x0091
            goto L_0x007a
        L_0x0091:
            monitor-exit(r11)
            return r0
        L_0x0093:
            if (r1 == 0) goto L_0x009e
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x009f }
            if (r2 != 0) goto L_0x009e
            r1.close()     // Catch:{ all -> 0x009f }
        L_0x009e:
            throw r0     // Catch:{ all -> 0x009f }
        L_0x009f:
            r0 = move-exception
            monitor-exit(r11)
            goto L_0x00a3
        L_0x00a2:
            throw r0
        L_0x00a3:
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSInAppMessageRepository.getCachedInAppMessages():java.util.List");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0079, code lost:
        if (r13.isClosed() == false) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0092, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void cleanCachedInAppMessages() {
        /*
            r14 = this;
            monitor-enter(r14)
            com.onesignal.OneSignalDbHelper r0 = r14.dbHelper     // Catch:{ all -> 0x00db }
            android.database.sqlite.SQLiteDatabase r0 = r0.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x00db }
            java.lang.String r1 = "message_id"
            java.lang.String r2 = "click_ids"
            java.lang.String[] r3 = new java.lang.String[]{r1, r2}     // Catch:{ all -> 0x00db }
            java.lang.String r9 = "last_display < ?"
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00db }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r4
            r4 = 15552000(0xed4e00, double:7.683709E-317)
            long r1 = r1 - r4
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00db }
            r2 = 1
            java.lang.String[] r10 = new java.lang.String[r2]     // Catch:{ all -> 0x00db }
            r2 = 0
            r10[r2] = r1     // Catch:{ all -> 0x00db }
            java.util.Set r11 = com.onesignal.OSUtils.newConcurrentSet()     // Catch:{ all -> 0x00db }
            java.util.Set r12 = com.onesignal.OSUtils.newConcurrentSet()     // Catch:{ all -> 0x00db }
            r13 = 0
            java.lang.String r2 = "in_app_message"
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            r4 = r9
            r5 = r10
            android.database.Cursor r13 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x0095 }
            if (r13 == 0) goto L_0x007f
            int r1 = r13.getCount()     // Catch:{ JSONException -> 0x0095 }
            if (r1 != 0) goto L_0x0044
            goto L_0x007f
        L_0x0044:
            boolean r1 = r13.moveToFirst()     // Catch:{ JSONException -> 0x0095 }
            if (r1 == 0) goto L_0x0073
        L_0x004a:
            java.lang.String r1 = "message_id"
            int r1 = r13.getColumnIndex(r1)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r1 = r13.getString(r1)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r2 = "click_ids"
            int r2 = r13.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r2 = r13.getString(r2)     // Catch:{ JSONException -> 0x0095 }
            r11.add(r1)     // Catch:{ JSONException -> 0x0095 }
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0095 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x0095 }
            java.util.Set r1 = com.onesignal.OSUtils.newStringSetFromJSONArray(r1)     // Catch:{ JSONException -> 0x0095 }
            r12.addAll(r1)     // Catch:{ JSONException -> 0x0095 }
            boolean r1 = r13.moveToNext()     // Catch:{ JSONException -> 0x0095 }
            if (r1 != 0) goto L_0x004a
        L_0x0073:
            if (r13 == 0) goto L_0x00a2
            boolean r1 = r13.isClosed()     // Catch:{ all -> 0x00db }
            if (r1 != 0) goto L_0x00a2
        L_0x007b:
            r13.close()     // Catch:{ all -> 0x00db }
            goto L_0x00a2
        L_0x007f:
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.DEBUG     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r2 = "Attempted to clean 6 month old IAM data, but none exists!"
            com.onesignal.OneSignal.onesignalLog(r1, r2)     // Catch:{ JSONException -> 0x0095 }
            if (r13 == 0) goto L_0x0091
            boolean r0 = r13.isClosed()     // Catch:{ all -> 0x00db }
            if (r0 != 0) goto L_0x0091
            r13.close()     // Catch:{ all -> 0x00db }
        L_0x0091:
            monitor-exit(r14)
            return
        L_0x0093:
            r0 = move-exception
            goto L_0x00cf
        L_0x0095:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0093 }
            if (r13 == 0) goto L_0x00a2
            boolean r1 = r13.isClosed()     // Catch:{ all -> 0x00db }
            if (r1 != 0) goto L_0x00a2
            goto L_0x007b
        L_0x00a2:
            r0.beginTransaction()     // Catch:{ all -> 0x00db }
            java.lang.String r1 = "in_app_message"
            r0.delete(r1, r9, r10)     // Catch:{ all -> 0x00c1 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x00c1 }
            r0.endTransaction()     // Catch:{ SQLException -> 0x00b1 }
            goto L_0x00b9
        L_0x00b1:
            r0 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x00db }
            java.lang.String r2 = "Error closing transaction! "
            com.onesignal.OneSignal.Log(r1, r2, r0)     // Catch:{ all -> 0x00db }
        L_0x00b9:
            r14.cleanInAppMessageIds(r11)     // Catch:{ all -> 0x00db }
            r14.cleanInAppMessageClickedClickIds(r12)     // Catch:{ all -> 0x00db }
            monitor-exit(r14)
            return
        L_0x00c1:
            r1 = move-exception
            r0.endTransaction()     // Catch:{ SQLException -> 0x00c6 }
            goto L_0x00ce
        L_0x00c6:
            r0 = move-exception
            com.onesignal.OneSignal$LOG_LEVEL r2 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch:{ all -> 0x00db }
            java.lang.String r3 = "Error closing transaction! "
            com.onesignal.OneSignal.Log(r2, r3, r0)     // Catch:{ all -> 0x00db }
        L_0x00ce:
            throw r1     // Catch:{ all -> 0x00db }
        L_0x00cf:
            if (r13 == 0) goto L_0x00da
            boolean r1 = r13.isClosed()     // Catch:{ all -> 0x00db }
            if (r1 != 0) goto L_0x00da
            r13.close()     // Catch:{ all -> 0x00db }
        L_0x00da:
            throw r0     // Catch:{ all -> 0x00db }
        L_0x00db:
            r0 = move-exception
            monitor-exit(r14)
            goto L_0x00df
        L_0x00de:
            throw r0
        L_0x00df:
            goto L_0x00de
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.OSInAppMessageRepository.cleanCachedInAppMessages():void");
    }

    private void cleanInAppMessageIds(Set<String> set) {
        if (set != null && set.size() > 0) {
            Set<String> stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_DISPLAYED_IAMS", (Set<String>) null);
            Set<String> stringSet2 = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_IMPRESSIONED_IAMS", (Set<String>) null);
            if (stringSet != null && stringSet.size() > 0) {
                stringSet.removeAll(set);
                OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_DISPLAYED_IAMS", stringSet);
            }
            if (stringSet2 != null && stringSet2.size() > 0) {
                stringSet2.removeAll(set);
                OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_IMPRESSIONED_IAMS", stringSet2);
            }
        }
    }

    private void cleanInAppMessageClickedClickIds(Set<String> set) {
        Set<String> stringSet;
        if (set != null && set.size() > 0 && (stringSet = OneSignalPrefs.getStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_CLICKED_CLICK_IDS_IAMS", (Set<String>) null)) != null && stringSet.size() > 0) {
            stringSet.removeAll(set);
            OneSignalPrefs.saveStringSet(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_OS_CLICKED_CLICK_IDS_IAMS", stringSet);
        }
    }
}
