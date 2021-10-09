package com.appnext.base.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class b extends SQLiteOpenHelper {
    private static final String dx = "appnext_dbs472";
    private static final int dy = 12;
    private static volatile b dz;

    public static b c(Context context) {
        if (dz == null) {
            synchronized (b.class) {
                if (dz == null) {
                    dz = new b(context.getApplicationContext());
                }
            }
        }
        return dz;
    }

    private b(Context context) {
        super(context, dx, (SQLiteDatabase.CursorFactory) null, 12);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table ct ( p text, c integer)");
            sQLiteDatabase.execSQL(com.appnext.base.a.c.b.ar());
            sQLiteDatabase.execSQL("create table config_table ( key text primary key, status text not null default 'off', sample text not null default '1', sample_type text not null default '',cycle text not null default '1', cycle_type text not null default 'once', service_key text not null default '', data text not null default '')");
        } catch (Throwable unused) {
        }
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ct");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collected_data_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS config_table");
            onCreate(sQLiteDatabase);
        } catch (Throwable unused) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ct");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collected_data_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS config_table");
            onCreate(sQLiteDatabase);
        } catch (Throwable unused) {
        }
    }
}
