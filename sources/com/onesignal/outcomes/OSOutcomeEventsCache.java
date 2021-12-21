package com.onesignal.outcomes;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.onesignal.OSLogger;
import com.onesignal.OSSharedPreferences;
import com.onesignal.OneSignalDb;
import com.onesignal.influence.model.OSInfluenceChannel;
import com.onesignal.influence.model.OSInfluenceType;
import com.onesignal.outcomes.model.OSCachedUniqueOutcome;
import com.onesignal.outcomes.model.OSOutcomeEventParams;
import com.onesignal.outcomes.model.OSOutcomeSource;
import com.onesignal.outcomes.model.OSOutcomeSourceBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

class OSOutcomeEventsCache {
    private OneSignalDb dbHelper;
    private OSLogger logger;
    private OSSharedPreferences preferences;

    OSOutcomeEventsCache(OSLogger oSLogger, OneSignalDb oneSignalDb, OSSharedPreferences oSSharedPreferences) {
        this.logger = oSLogger;
        this.dbHelper = oneSignalDb;
        this.preferences = oSSharedPreferences;
    }

    /* access modifiers changed from: package-private */
    public boolean isOutcomesV2ServiceEnabled() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getBool(oSSharedPreferences.getPreferencesName(), this.preferences.getOutcomesV2KeyName(), false);
    }

    /* access modifiers changed from: package-private */
    public Set<String> getUnattributedUniqueOutcomeEventsSentByChannel() {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        return oSSharedPreferences.getStringSet(oSSharedPreferences.getPreferencesName(), "PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT", (Set<String>) null);
    }

    /* access modifiers changed from: package-private */
    public void saveUnattributedUniqueOutcomeEventsSentByChannel(Set<String> set) {
        OSSharedPreferences oSSharedPreferences = this.preferences;
        oSSharedPreferences.saveStringSet(oSSharedPreferences.getPreferencesName(), "PREFS_OS_UNATTRIBUTED_UNIQUE_OUTCOME_EVENTS_SENT", set);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0 = r7.logger;
        r1 = "Error closing transaction! ";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        if (r0 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r7.logger.error("Error closing transaction! ", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r8 = e;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:6:0x0024, B:14:0x0034] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void deleteOldOutcomeEvent(com.onesignal.outcomes.model.OSOutcomeEventParams r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.onesignal.OneSignalDb r0 = r7.dbHelper     // Catch:{ all -> 0x0058 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x0058 }
            r0.beginTransaction()     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.String r1 = "outcome"
            java.lang.String r2 = "timestamp = ?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0033 }
            r4 = 0
            long r5 = r8.getTimestamp()     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.String r8 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0033 }
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x0033 }
            r0.delete(r1, r2, r3)     // Catch:{ SQLiteException -> 0x0033 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteException -> 0x0033 }
            if (r0 == 0) goto L_0x0047
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x0028 }
            goto L_0x0047
        L_0x0028:
            r8 = move-exception
            com.onesignal.OSLogger r0 = r7.logger     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = "Error closing transaction! "
        L_0x002d:
            r0.error(r1, r8)     // Catch:{ all -> 0x0058 }
            goto L_0x0047
        L_0x0031:
            r8 = move-exception
            goto L_0x0049
        L_0x0033:
            r8 = move-exception
            com.onesignal.OSLogger r1 = r7.logger     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "Error deleting old outcome event records! "
            r1.error(r2, r8)     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x0047
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x0041 }
            goto L_0x0047
        L_0x0041:
            r8 = move-exception
            com.onesignal.OSLogger r0 = r7.logger     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = "Error closing transaction! "
            goto L_0x002d
        L_0x0047:
            monitor-exit(r7)
            return
        L_0x0049:
            if (r0 == 0) goto L_0x0057
            r0.endTransaction()     // Catch:{ SQLiteException -> 0x004f }
            goto L_0x0057
        L_0x004f:
            r0 = move-exception
            com.onesignal.OSLogger r1 = r7.logger     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error closing transaction! "
            r1.error(r2, r0)     // Catch:{ all -> 0x0058 }
        L_0x0057:
            throw r8     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r8 = move-exception
            monitor-exit(r7)
            goto L_0x005c
        L_0x005b:
            throw r8
        L_0x005c:
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsCache.deleteOldOutcomeEvent(com.onesignal.outcomes.model.OSOutcomeEventParams):void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveOutcomeEvent(OSOutcomeEventParams oSOutcomeEventParams) {
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        OSInfluenceType oSInfluenceType = OSInfluenceType.UNATTRIBUTED;
        OSInfluenceType oSInfluenceType2 = OSInfluenceType.UNATTRIBUTED;
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            OSOutcomeSource outcomeSource = oSOutcomeEventParams.getOutcomeSource();
            if (outcomeSource.getDirectBody() != null) {
                OSOutcomeSourceBody directBody = outcomeSource.getDirectBody();
                if (directBody.getNotificationIds() != null && directBody.getNotificationIds().length() > 0) {
                    oSInfluenceType = OSInfluenceType.DIRECT;
                    jSONArray = outcomeSource.getDirectBody().getNotificationIds();
                }
                if (directBody.getInAppMessagesIds() != null && directBody.getInAppMessagesIds().length() > 0) {
                    oSInfluenceType2 = OSInfluenceType.DIRECT;
                    jSONArray2 = outcomeSource.getDirectBody().getInAppMessagesIds();
                }
            }
            if (outcomeSource.getIndirectBody() != null) {
                OSOutcomeSourceBody indirectBody = outcomeSource.getIndirectBody();
                if (indirectBody.getNotificationIds() != null && indirectBody.getNotificationIds().length() > 0) {
                    OSInfluenceType oSInfluenceType3 = OSInfluenceType.INDIRECT;
                    oSInfluenceType = oSInfluenceType3;
                    jSONArray = outcomeSource.getIndirectBody().getNotificationIds();
                }
                if (indirectBody.getInAppMessagesIds() != null && indirectBody.getInAppMessagesIds().length() > 0) {
                    oSInfluenceType2 = OSInfluenceType.INDIRECT;
                    jSONArray2 = outcomeSource.getIndirectBody().getInAppMessagesIds();
                }
            }
        }
        SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
        sQLiteDatabaseWithRetries.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("notification_ids", jSONArray.toString());
            contentValues.put("iam_ids", jSONArray2.toString());
            contentValues.put("notification_influence_type", oSInfluenceType.toString().toLowerCase());
            contentValues.put("iam_influence_type", oSInfluenceType2.toString().toLowerCase());
            contentValues.put("name", oSOutcomeEventParams.getOutcomeId());
            contentValues.put("weight", oSOutcomeEventParams.getWeight());
            contentValues.put(AvidJSONUtil.KEY_TIMESTAMP, Long.valueOf(oSOutcomeEventParams.getTimestamp()));
            sQLiteDatabaseWithRetries.insert("outcome", (String) null, contentValues);
            sQLiteDatabaseWithRetries.setTransactionSuccessful();
            sQLiteDatabaseWithRetries.endTransaction();
        } catch (SQLException e) {
            this.logger.error("Error closing transaction! ", e);
        } catch (Throwable th) {
            try {
                sQLiteDatabaseWithRetries.endTransaction();
            } catch (SQLException e2) {
                this.logger.error("Error closing transaction! ", e2);
            }
            throw th;
        }
        return;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x010f A[SYNTHETIC, Splitter:B:54:0x010f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.onesignal.outcomes.model.OSOutcomeEventParams> getAllEventsToSend() {
        /*
            r15 = this;
            monitor-enter(r15)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0119 }
            r0.<init>()     // Catch:{ all -> 0x0119 }
            r1 = 0
            com.onesignal.OneSignalDb r2 = r15.dbHelper     // Catch:{ all -> 0x010c }
            android.database.sqlite.SQLiteDatabase r3 = r2.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x010c }
            java.lang.String r4 = "outcome"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x010c }
            boolean r3 = r2.moveToFirst()     // Catch:{ all -> 0x0109 }
            if (r3 == 0) goto L_0x00fc
        L_0x001f:
            java.lang.String r3 = "notification_influence_type"
            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x0109 }
            java.lang.String r3 = r2.getString(r3)     // Catch:{ all -> 0x0109 }
            com.onesignal.influence.model.OSInfluenceType r3 = com.onesignal.influence.model.OSInfluenceType.fromString(r3)     // Catch:{ all -> 0x0109 }
            java.lang.String r4 = "iam_influence_type"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ all -> 0x0109 }
            java.lang.String r4 = r2.getString(r4)     // Catch:{ all -> 0x0109 }
            com.onesignal.influence.model.OSInfluenceType r4 = com.onesignal.influence.model.OSInfluenceType.fromString(r4)     // Catch:{ all -> 0x0109 }
            java.lang.String r5 = "notification_ids"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ all -> 0x0109 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ all -> 0x0109 }
            if (r5 == 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            java.lang.String r5 = "[]"
        L_0x004a:
            java.lang.String r6 = "iam_ids"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ all -> 0x0109 }
            java.lang.String r6 = r2.getString(r6)     // Catch:{ all -> 0x0109 }
            if (r6 == 0) goto L_0x0057
            goto L_0x0059
        L_0x0057:
            java.lang.String r6 = "[]"
        L_0x0059:
            java.lang.String r7 = "name"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ all -> 0x0109 }
            java.lang.String r9 = r2.getString(r7)     // Catch:{ all -> 0x0109 }
            java.lang.String r7 = "weight"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ all -> 0x0109 }
            float r11 = r2.getFloat(r7)     // Catch:{ all -> 0x0109 }
            java.lang.String r7 = "timestamp"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ all -> 0x0109 }
            long r12 = r2.getLong(r7)     // Catch:{ all -> 0x0109 }
            com.onesignal.outcomes.model.OSOutcomeSourceBody r7 = new com.onesignal.outcomes.model.OSOutcomeSourceBody     // Catch:{ JSONException -> 0x00ee }
            r7.<init>()     // Catch:{ JSONException -> 0x00ee }
            com.onesignal.outcomes.model.OSOutcomeSourceBody r8 = new com.onesignal.outcomes.model.OSOutcomeSourceBody     // Catch:{ JSONException -> 0x00ee }
            r8.<init>()     // Catch:{ JSONException -> 0x00ee }
            int[] r10 = com.onesignal.outcomes.OSOutcomeEventsCache.AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ JSONException -> 0x00ee }
            int r3 = r3.ordinal()     // Catch:{ JSONException -> 0x00ee }
            r3 = r10[r3]     // Catch:{ JSONException -> 0x00ee }
            r10 = 2
            r14 = 1
            if (r3 == r14) goto L_0x009f
            if (r3 == r10) goto L_0x0091
            r3 = r1
            goto L_0x00ac
        L_0x0091:
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00ee }
            r3.<init>(r5)     // Catch:{ JSONException -> 0x00ee }
            r8.setNotificationIds(r3)     // Catch:{ JSONException -> 0x00ee }
            com.onesignal.outcomes.model.OSOutcomeSource r3 = new com.onesignal.outcomes.model.OSOutcomeSource     // Catch:{ JSONException -> 0x00ee }
            r3.<init>(r1, r8)     // Catch:{ JSONException -> 0x00ee }
            goto L_0x00ac
        L_0x009f:
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00ee }
            r3.<init>(r5)     // Catch:{ JSONException -> 0x00ee }
            r7.setNotificationIds(r3)     // Catch:{ JSONException -> 0x00ee }
            com.onesignal.outcomes.model.OSOutcomeSource r3 = new com.onesignal.outcomes.model.OSOutcomeSource     // Catch:{ JSONException -> 0x00ee }
            r3.<init>(r7, r1)     // Catch:{ JSONException -> 0x00ee }
        L_0x00ac:
            int[] r5 = com.onesignal.outcomes.OSOutcomeEventsCache.AnonymousClass1.$SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ JSONException -> 0x00ee }
            int r4 = r4.ordinal()     // Catch:{ JSONException -> 0x00ee }
            r4 = r5[r4]     // Catch:{ JSONException -> 0x00ee }
            if (r4 == r14) goto L_0x00cf
            if (r4 == r10) goto L_0x00ba
        L_0x00b8:
            r10 = r3
            goto L_0x00e4
        L_0x00ba:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00ee }
            r4.<init>(r6)     // Catch:{ JSONException -> 0x00ee }
            r8.setInAppMessagesIds(r4)     // Catch:{ JSONException -> 0x00ee }
            if (r3 != 0) goto L_0x00ca
            com.onesignal.outcomes.model.OSOutcomeSource r3 = new com.onesignal.outcomes.model.OSOutcomeSource     // Catch:{ JSONException -> 0x00ee }
            r3.<init>(r1, r8)     // Catch:{ JSONException -> 0x00ee }
            goto L_0x00b8
        L_0x00ca:
            com.onesignal.outcomes.model.OSOutcomeSource r3 = r3.setIndirectBody(r8)     // Catch:{ JSONException -> 0x00ee }
            goto L_0x00b8
        L_0x00cf:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00ee }
            r4.<init>(r6)     // Catch:{ JSONException -> 0x00ee }
            r7.setInAppMessagesIds(r4)     // Catch:{ JSONException -> 0x00ee }
            if (r3 != 0) goto L_0x00df
            com.onesignal.outcomes.model.OSOutcomeSource r3 = new com.onesignal.outcomes.model.OSOutcomeSource     // Catch:{ JSONException -> 0x00ee }
            r3.<init>(r7, r1)     // Catch:{ JSONException -> 0x00ee }
            goto L_0x00b8
        L_0x00df:
            com.onesignal.outcomes.model.OSOutcomeSource r3 = r3.setDirectBody(r7)     // Catch:{ JSONException -> 0x00ee }
            goto L_0x00b8
        L_0x00e4:
            com.onesignal.outcomes.model.OSOutcomeEventParams r3 = new com.onesignal.outcomes.model.OSOutcomeEventParams     // Catch:{ JSONException -> 0x00ee }
            r8 = r3
            r8.<init>(r9, r10, r11, r12)     // Catch:{ JSONException -> 0x00ee }
            r0.add(r3)     // Catch:{ JSONException -> 0x00ee }
            goto L_0x00f6
        L_0x00ee:
            r3 = move-exception
            com.onesignal.OSLogger r4 = r15.logger     // Catch:{ all -> 0x0109 }
            java.lang.String r5 = "Generating JSONArray from notifications ids outcome:JSON Failed."
            r4.error(r5, r3)     // Catch:{ all -> 0x0109 }
        L_0x00f6:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x0109 }
            if (r3 != 0) goto L_0x001f
        L_0x00fc:
            if (r2 == 0) goto L_0x0107
            boolean r1 = r2.isClosed()     // Catch:{ all -> 0x0119 }
            if (r1 != 0) goto L_0x0107
            r2.close()     // Catch:{ all -> 0x0119 }
        L_0x0107:
            monitor-exit(r15)
            return r0
        L_0x0109:
            r0 = move-exception
            r1 = r2
            goto L_0x010d
        L_0x010c:
            r0 = move-exception
        L_0x010d:
            if (r1 == 0) goto L_0x0118
            boolean r2 = r1.isClosed()     // Catch:{ all -> 0x0119 }
            if (r2 != 0) goto L_0x0118
            r1.close()     // Catch:{ all -> 0x0119 }
        L_0x0118:
            throw r0     // Catch:{ all -> 0x0119 }
        L_0x0119:
            r0 = move-exception
            monitor-exit(r15)
            goto L_0x011d
        L_0x011c:
            throw r0
        L_0x011d:
            goto L_0x011c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsCache.getAllEventsToSend():java.util.List");
    }

    /* renamed from: com.onesignal.outcomes.OSOutcomeEventsCache$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$onesignal$influence$model$OSInfluenceType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.onesignal.influence.model.OSInfluenceType[] r0 = com.onesignal.influence.model.OSInfluenceType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$onesignal$influence$model$OSInfluenceType = r0
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.DIRECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.INDIRECT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.UNATTRIBUTED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$onesignal$influence$model$OSInfluenceType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.onesignal.influence.model.OSInfluenceType r1 = com.onesignal.influence.model.OSInfluenceType.DISABLED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsCache.AnonymousClass1.<clinit>():void");
        }
    }

    private void addIdToListFromChannel(List<OSCachedUniqueOutcome> list, JSONArray jSONArray, OSInfluenceChannel oSInfluenceChannel) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    list.add(new OSCachedUniqueOutcome(jSONArray.getString(i), oSInfluenceChannel));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addIdsToListFromSource(List<OSCachedUniqueOutcome> list, OSOutcomeSourceBody oSOutcomeSourceBody) {
        if (oSOutcomeSourceBody != null) {
            JSONArray inAppMessagesIds = oSOutcomeSourceBody.getInAppMessagesIds();
            JSONArray notificationIds = oSOutcomeSourceBody.getNotificationIds();
            addIdToListFromChannel(list, inAppMessagesIds, OSInfluenceChannel.IAM);
            addIdToListFromChannel(list, notificationIds, OSInfluenceChannel.NOTIFICATION);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void saveUniqueOutcomeEventParams(OSOutcomeEventParams oSOutcomeEventParams) {
        OSLogger oSLogger = this.logger;
        oSLogger.debug("OneSignal saveUniqueOutcomeEventParams: " + oSOutcomeEventParams.toString());
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            String outcomeId = oSOutcomeEventParams.getOutcomeId();
            ArrayList<OSCachedUniqueOutcome> arrayList = new ArrayList<>();
            OSOutcomeSourceBody directBody = oSOutcomeEventParams.getOutcomeSource().getDirectBody();
            OSOutcomeSourceBody indirectBody = oSOutcomeEventParams.getOutcomeSource().getIndirectBody();
            addIdsToListFromSource(arrayList, directBody);
            addIdsToListFromSource(arrayList, indirectBody);
            SQLiteDatabase sQLiteDatabaseWithRetries = this.dbHelper.getSQLiteDatabaseWithRetries();
            sQLiteDatabaseWithRetries.beginTransaction();
            try {
                for (OSCachedUniqueOutcome oSCachedUniqueOutcome : arrayList) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("channel_influence_id", oSCachedUniqueOutcome.getInfluenceId());
                    contentValues.put("channel_type", String.valueOf(oSCachedUniqueOutcome.getChannel()));
                    contentValues.put("name", outcomeId);
                    sQLiteDatabaseWithRetries.insert("cached_unique_outcome", (String) null, contentValues);
                }
                sQLiteDatabaseWithRetries.setTransactionSuccessful();
                sQLiteDatabaseWithRetries.endTransaction();
            } catch (SQLException e) {
                this.logger.error("Error closing transaction! ", e);
            } catch (Throwable th) {
                try {
                    sQLiteDatabaseWithRetries.endTransaction();
                } catch (SQLException e2) {
                    this.logger.error("Error closing transaction! ", e2);
                }
                throw th;
            }
        } else {
            return;
        }
        return;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009b, code lost:
        if (r3.isClosed() == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009d, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ad, code lost:
        if (r3.isClosed() == false) goto L_0x009d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a9 A[SYNTHETIC, Splitter:B:37:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b4 A[SYNTHETIC, Splitter:B:43:0x00b4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<com.onesignal.influence.model.OSInfluence> getNotCachedUniqueInfluencesForOutcome(java.lang.String r22, java.util.List<com.onesignal.influence.model.OSInfluence> r23) {
        /*
            r21 = this;
            r1 = r21
            monitor-enter(r21)
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00be }
            r2.<init>()     // Catch:{ all -> 0x00be }
            com.onesignal.OneSignalDb r0 = r1.dbHelper     // Catch:{ all -> 0x00be }
            android.database.sqlite.SQLiteDatabase r0 = r0.getSQLiteDatabaseWithRetries()     // Catch:{ all -> 0x00be }
            r3 = 0
            java.util.Iterator r12 = r23.iterator()     // Catch:{ JSONException -> 0x00a3 }
        L_0x0013:
            boolean r4 = r12.hasNext()     // Catch:{ JSONException -> 0x00a3 }
            if (r4 == 0) goto L_0x0095
            java.lang.Object r4 = r12.next()     // Catch:{ JSONException -> 0x00a3 }
            r13 = r4
            com.onesignal.influence.model.OSInfluence r13 = (com.onesignal.influence.model.OSInfluence) r13     // Catch:{ JSONException -> 0x00a3 }
            org.json.JSONArray r14 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00a3 }
            r14.<init>()     // Catch:{ JSONException -> 0x00a3 }
            org.json.JSONArray r15 = r13.getIds()     // Catch:{ JSONException -> 0x00a3 }
            if (r15 != 0) goto L_0x002c
            goto L_0x0013
        L_0x002c:
            r11 = 0
            r16 = r3
            r10 = 0
        L_0x0030:
            int r3 = r15.length()     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            if (r10 >= r3) goto L_0x007a
            java.lang.String r9 = r15.getString(r10)     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            com.onesignal.influence.model.OSInfluenceChannel r3 = r13.getInfluenceChannel()     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            java.lang.String[] r5 = new java.lang.String[r11]     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            java.lang.String r6 = "channel_influence_id = ? AND channel_type = ? AND name = ?"
            r4 = 3
            java.lang.String[] r7 = new java.lang.String[r4]     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            r7[r11] = r9     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            r4 = 1
            r7[r4] = r3     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            r3 = 2
            r7[r3] = r22     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            java.lang.String r4 = "cached_unique_outcome"
            r8 = 0
            r17 = 0
            r18 = 0
            java.lang.String r19 = "1"
            r3 = r0
            r20 = r9
            r9 = r17
            r17 = r10
            r10 = r18
            r18 = 0
            r11 = r19
            android.database.Cursor r16 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            int r3 = r16.getCount()     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            if (r3 != 0) goto L_0x0076
            r3 = r20
            r14.put(r3)     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
        L_0x0076:
            int r10 = r17 + 1
            r11 = 0
            goto L_0x0030
        L_0x007a:
            int r3 = r14.length()     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            if (r3 <= 0) goto L_0x008a
            com.onesignal.influence.model.OSInfluence r3 = r13.copy()     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            r3.setIds(r14)     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
            r2.add(r3)     // Catch:{ JSONException -> 0x0091, all -> 0x008d }
        L_0x008a:
            r3 = r16
            goto L_0x0013
        L_0x008d:
            r0 = move-exception
            r3 = r16
            goto L_0x00b2
        L_0x0091:
            r0 = move-exception
            r3 = r16
            goto L_0x00a4
        L_0x0095:
            if (r3 == 0) goto L_0x00b0
            boolean r0 = r3.isClosed()     // Catch:{ all -> 0x00be }
            if (r0 != 0) goto L_0x00b0
        L_0x009d:
            r3.close()     // Catch:{ all -> 0x00be }
            goto L_0x00b0
        L_0x00a1:
            r0 = move-exception
            goto L_0x00b2
        L_0x00a3:
            r0 = move-exception
        L_0x00a4:
            r0.printStackTrace()     // Catch:{ all -> 0x00a1 }
            if (r3 == 0) goto L_0x00b0
            boolean r0 = r3.isClosed()     // Catch:{ all -> 0x00be }
            if (r0 != 0) goto L_0x00b0
            goto L_0x009d
        L_0x00b0:
            monitor-exit(r21)
            return r2
        L_0x00b2:
            if (r3 == 0) goto L_0x00bd
            boolean r2 = r3.isClosed()     // Catch:{ all -> 0x00be }
            if (r2 != 0) goto L_0x00bd
            r3.close()     // Catch:{ all -> 0x00be }
        L_0x00bd:
            throw r0     // Catch:{ all -> 0x00be }
        L_0x00be:
            r0 = move-exception
            monitor-exit(r21)
            goto L_0x00c2
        L_0x00c1:
            throw r0
        L_0x00c2:
            goto L_0x00c1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.outcomes.OSOutcomeEventsCache.getNotCachedUniqueInfluencesForOutcome(java.lang.String, java.util.List):java.util.List");
    }
}
