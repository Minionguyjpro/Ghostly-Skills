package com.onesignal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import com.onesignal.OneSignal;
import com.onesignal.outcomes.OSOutcomeTableProvider;
import java.util.ArrayList;

class OneSignalDbHelper extends SQLiteOpenHelper implements OneSignalDb {
    protected static final String[] SQL_INDEX_ENTRIES = {"CREATE INDEX notification_notification_id_idx ON notification(notification_id); ", "CREATE INDEX notification_android_notification_id_idx ON notification(android_notification_id); ", "CREATE INDEX notification_group_id_idx ON notification(group_id); ", "CREATE INDEX notification_collapse_id_idx ON notification(collapse_id); ", "CREATE INDEX notification_created_time_idx ON notification(created_time); ", "CREATE INDEX notification_expire_time_idx ON notification(expire_time); "};
    private static OSOutcomeTableProvider outcomeTableProvider = new OSOutcomeTableProvider();
    private static OneSignalDbHelper sInstance;

    private static int getDbVersion() {
        return 8;
    }

    OneSignalDbHelper(Context context) {
        super(context, "OneSignal.db", (SQLiteDatabase.CursorFactory) null, getDbVersion());
    }

    public static synchronized OneSignalDbHelper getInstance(Context context) {
        OneSignalDbHelper oneSignalDbHelper;
        synchronized (OneSignalDbHelper.class) {
            if (sInstance == null) {
                sInstance = new OneSignalDbHelper(context.getApplicationContext());
            }
            oneSignalDbHelper = sInstance;
        }
        return oneSignalDbHelper;
    }

    public synchronized SQLiteDatabase getSQLiteDatabaseWithRetries() {
        int i = 0;
        while (true) {
            try {
            } catch (SQLiteCantOpenDatabaseException | SQLiteDatabaseLockedException e) {
                i++;
                if (i < 5) {
                    SystemClock.sleep((long) (i * 400));
                } else {
                    throw e;
                }
            }
        }
        return getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,collapse_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')),expire_time TIMESTAMP);");
        sQLiteDatabase.execSQL("CREATE TABLE outcome (_id INTEGER PRIMARY KEY,notification_influence_type TEXT,iam_influence_type TEXT,notification_ids TEXT,iam_ids TEXT,name TEXT,timestamp TIMESTAMP,weight FLOAT);");
        sQLiteDatabase.execSQL("CREATE TABLE cached_unique_outcome (_id INTEGER PRIMARY KEY,channel_influence_id TEXT,channel_type TEXT,name TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE in_app_message (_id INTEGER PRIMARY KEY,display_quantity INTEGER,last_display INTEGER,message_id TEXT,displayed_in_session INTEGER,click_ids TEXT);");
        for (String execSQL : SQL_INDEX_ENTRIES) {
            sQLiteDatabase.execSQL(execSQL);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "OneSignal Database onUpgrade from: " + i + " to: " + i2);
        try {
            internalOnUpgrade(sQLiteDatabase, i);
        } catch (SQLiteException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error in upgrade, migration may have already run! Skipping!", e);
        }
    }

    private synchronized void internalOnUpgrade(SQLiteDatabase sQLiteDatabase, int i) {
        if (i < 2) {
            try {
                upgradeToV2(sQLiteDatabase);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (i < 3) {
            upgradeToV3(sQLiteDatabase);
        }
        if (i < 4) {
            upgradeToV4(sQLiteDatabase);
        }
        if (i < 5) {
            upgradeToV5(sQLiteDatabase);
        }
        if (i == 5) {
            upgradeFromV5ToV6(sQLiteDatabase);
        }
        if (i < 7) {
            upgradeToV7(sQLiteDatabase);
        }
        if (i < 8) {
            upgradeToV8(sQLiteDatabase);
        }
    }

    private static void upgradeToV2(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "ALTER TABLE notification ADD COLUMN collapse_id TEXT;");
        safeExecSQL(sQLiteDatabase, "CREATE INDEX notification_group_id_idx ON notification(group_id); ");
    }

    private static void upgradeToV3(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "ALTER TABLE notification ADD COLUMN expire_time TIMESTAMP;");
        safeExecSQL(sQLiteDatabase, "UPDATE notification SET expire_time = created_time + 259200;");
        safeExecSQL(sQLiteDatabase, "CREATE INDEX notification_expire_time_idx ON notification(expire_time); ");
    }

    private static void upgradeToV4(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "CREATE TABLE outcome (_id INTEGER PRIMARY KEY,notification_ids TEXT,name TEXT,session TEXT,params TEXT,timestamp TIMESTAMP);");
    }

    private static void upgradeToV5(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "CREATE TABLE cached_unique_outcome_notification (_id INTEGER PRIMARY KEY,notification_id TEXT,name TEXT);");
        upgradeFromV5ToV6(sQLiteDatabase);
    }

    private static void upgradeFromV5ToV6(SQLiteDatabase sQLiteDatabase) {
        outcomeTableProvider.upgradeOutcomeTableRevision1To2(sQLiteDatabase);
    }

    private static void upgradeToV7(SQLiteDatabase sQLiteDatabase) {
        safeExecSQL(sQLiteDatabase, "CREATE TABLE in_app_message (_id INTEGER PRIMARY KEY,display_quantity INTEGER,last_display INTEGER,message_id TEXT,displayed_in_session INTEGER,click_ids TEXT);");
    }

    private synchronized void upgradeToV8(SQLiteDatabase sQLiteDatabase) {
        outcomeTableProvider.upgradeOutcomeTableRevision2To3(sQLiteDatabase);
        outcomeTableProvider.upgradeCacheOutcomeTableRevision1To2(sQLiteDatabase);
    }

    private static void safeExecSQL(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: finally extract failed */
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "SDK version rolled back! Clearing OneSignal.db as it could be in an unexpected state.");
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", (String[]) null);
        try {
            ArrayList<String> arrayList = new ArrayList<>(rawQuery.getCount());
            while (rawQuery.moveToNext()) {
                arrayList.add(rawQuery.getString(0));
            }
            for (String str : arrayList) {
                if (!str.startsWith("sqlite_")) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
            }
            rawQuery.close();
            onCreate(sQLiteDatabase);
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
    }

    static StringBuilder recentUninteractedWithNotificationsWhere() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        StringBuilder sb = new StringBuilder("created_time > " + (currentTimeMillis - 604800) + " AND " + "dismissed" + " = 0 AND " + "opened" + " = 0 AND " + "is_summary" + " = 0");
        if (OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_RESTORE_TTL_FILTER", true)) {
            sb.append(" AND expire_time > " + currentTimeMillis);
        }
        return sb;
    }
}
