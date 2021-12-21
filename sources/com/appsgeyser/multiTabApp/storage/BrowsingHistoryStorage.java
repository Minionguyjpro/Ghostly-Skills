package com.appsgeyser.multiTabApp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import com.google.android.gms.plus.PlusShare;
import java.util.Date;
import java.util.Vector;

public class BrowsingHistoryStorage {
    private DatabaseOpenHelper _dbHelper;

    public Cursor loadWeeklyHistory() {
        Date date = new Date();
        return loadHistory(new Date(date.getTime() - 604800000), date);
    }

    public BrowsingHistoryStorage(Context context) {
        this._dbHelper = new DatabaseOpenHelper(context);
    }

    public Cursor loadHistory(Date date, Date date2) {
        Vector vector = new Vector();
        String str = "";
        if (date != null) {
            str = str + "visitTime>= ?";
            vector.add(DatabaseOpenHelper.SQL_DATE_FORMAT.format(date));
        }
        if (date2 != null) {
            if (date != null) {
                str = str + " AND ";
            }
            str = str + "visitTime<= ?";
            vector.add(DatabaseOpenHelper.SQL_DATE_FORMAT.format(date2));
        }
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        String[] strArr2 = {"rowId _id", PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "url", "visitTime"};
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("history");
        Cursor query = sQLiteQueryBuilder.query(this._dbHelper.getReadableDatabase(), strArr2, str, strArr, (String) null, (String) null, (String) null);
        if (query == null) {
            return null;
        }
        if (query.moveToFirst()) {
            return query;
        }
        query.close();
        return null;
    }

    public Cursor getHistoryItemsGroupedByUrl(String str) {
        String[] strArr;
        String str2;
        String[] strArr2 = {"rowId _id", PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "url"};
        loadHistory(new Date("2014/01/01"), new Date("2016/09/09"));
        if (str != null) {
            str2 = "url MATCH ?";
            strArr = new String[]{str + "*"};
        } else {
            str2 = null;
            strArr = null;
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("history");
        return sQLiteQueryBuilder.query(this._dbHelper.getReadableDatabase(), strArr2, str2, strArr, "url", (String) null, (String) null, "6");
    }

    public void addHistoryItem(String str, String str2, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str);
        contentValues.put("url", str2);
        contentValues.put("visitTime", DatabaseOpenHelper.SQL_DATE_FORMAT.format(date));
        this._dbHelper.getWritableDatabase().insert("history", (String) null, contentValues);
    }

    public int removeHistoryItemById(long j) {
        try {
            SQLiteDatabase writableDatabase = this._dbHelper.getWritableDatabase();
            return writableDatabase.delete("history", "rowId=" + Long.toString(j), (String[]) null);
        } catch (Exception e) {
            Log.e("removeHistoryItemById", "" + e);
            return -1;
        }
    }

    public int removeHistoryAllItem() {
        try {
            return this._dbHelper.getWritableDatabase().delete("history", (String) null, (String[]) null);
        } catch (Exception e) {
            Log.e("removeHistoryAllItem", "" + e);
            return -1;
        }
    }
}
