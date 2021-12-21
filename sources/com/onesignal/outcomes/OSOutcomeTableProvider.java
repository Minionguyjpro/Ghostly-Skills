package com.onesignal.outcomes;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.onesignal.influence.model.OSInfluenceChannel;

public class OSOutcomeTableProvider {
    public void upgradeOutcomeTableRevision1To2(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("BEGIN TRANSACTION;");
            sQLiteDatabase.execSQL("CREATE TEMPORARY TABLE outcome_backup(" + "_id,session,notification_ids,name,timestamp" + ");");
            sQLiteDatabase.execSQL("INSERT INTO outcome_backup SELECT " + "_id,session,notification_ids,name,timestamp" + " FROM outcome;");
            sQLiteDatabase.execSQL("DROP TABLE outcome;");
            sQLiteDatabase.execSQL("CREATE TABLE outcome (_id INTEGER PRIMARY KEY,session TEXT,notification_ids TEXT,name TEXT,timestamp TIMESTAMP,weight FLOAT);");
            sQLiteDatabase.execSQL("INSERT INTO outcome (" + "_id,session,notification_ids,name,timestamp" + ", weight) SELECT " + "_id,session,notification_ids,name,timestamp" + ", 0 FROM outcome_backup;");
            sQLiteDatabase.execSQL("DROP TABLE outcome_backup;");
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            sQLiteDatabase.execSQL("COMMIT;");
            throw th;
        }
        sQLiteDatabase.execSQL("COMMIT;");
    }

    public void upgradeOutcomeTableRevision2To3(SQLiteDatabase sQLiteDatabase) {
        String str = "_id,name,timestamp,notification_ids,weight" + "," + "notification_influence_type";
        try {
            sQLiteDatabase.execSQL("BEGIN TRANSACTION;");
            sQLiteDatabase.execSQL("ALTER TABLE outcome RENAME TO " + "outcome_aux" + ";");
            sQLiteDatabase.execSQL("CREATE TABLE outcome (_id INTEGER PRIMARY KEY,notification_influence_type TEXT,iam_influence_type TEXT,notification_ids TEXT,iam_ids TEXT,name TEXT,timestamp TIMESTAMP,weight FLOAT);");
            sQLiteDatabase.execSQL("INSERT INTO outcome(" + str + ") SELECT " + "_id,name,timestamp,notification_ids,weight,session" + " FROM " + "outcome_aux" + ";");
            StringBuilder sb = new StringBuilder();
            sb.append("DROP TABLE ");
            sb.append("outcome_aux");
            sb.append(";");
            sQLiteDatabase.execSQL(sb.toString());
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            sQLiteDatabase.execSQL("COMMIT;");
            throw th;
        }
        sQLiteDatabase.execSQL("COMMIT;");
    }

    public void upgradeCacheOutcomeTableRevision1To2(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("BEGIN TRANSACTION;");
            sQLiteDatabase.execSQL("CREATE TABLE cached_unique_outcome (_id INTEGER PRIMARY KEY,channel_influence_id TEXT,channel_type TEXT,name TEXT);");
            sQLiteDatabase.execSQL("INSERT INTO cached_unique_outcome(" + "_id,name,channel_influence_id" + ") SELECT " + "_id,name,notification_id" + " FROM " + "cached_unique_outcome_notification" + ";");
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE cached_unique_outcome SET channel_type = '");
            sb.append(OSInfluenceChannel.NOTIFICATION.toString());
            sb.append("';");
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.execSQL("DROP TABLE " + "cached_unique_outcome_notification" + ";");
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            sQLiteDatabase.execSQL("COMMIT;");
            throw th;
        }
        sQLiteDatabase.execSQL("COMMIT;");
    }
}
