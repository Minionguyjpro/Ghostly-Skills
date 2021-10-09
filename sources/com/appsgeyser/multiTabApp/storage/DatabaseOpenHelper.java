package com.appsgeyser.multiTabApp.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.text.SimpleDateFormat;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DatabaseOpenHelper(Context context) {
        super(context, "Storage", (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIRTUAL TABLE history  USING fts3(visitTime DATETIME, title TEXT, url TEXT);");
        sQLiteDatabase.execSQL("create table downloadsList (id integer primary key autoincrement,name text,description text,id_d text,link_d text,file_path text,date integer,status text);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w("StorageDatabase", "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS create table downloadsList (id integer primary key autoincrement,name text,description text,id_d text,link_d text,file_path text,date integer,status text);");
        onCreate(sQLiteDatabase);
    }
}
