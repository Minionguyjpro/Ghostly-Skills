package com.appsgeyser.multiTabApp.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookmarksManager extends SQLiteOpenHelper {
    private final String BOOKMARKS_PAGE_PREFIX = "BookmarksPage";
    private String BOOKMARKS_TABLE_CREATE;
    private String BOOKMARKS_TABLE_NAME;
    private final String DATABASE_NAME;
    private Context _context;
    private String _name;

    public BookmarksManager(String str, Context context) {
        super(context, "Bookmarks" + str, (SQLiteDatabase.CursorFactory) null, 1);
        this._context = context;
        this.DATABASE_NAME = "Bookmarks" + str;
        this.BOOKMARKS_TABLE_NAME = "BookmarksPage" + str;
        this.BOOKMARKS_TABLE_CREATE = "CREATE TABLE " + this.BOOKMARKS_TABLE_NAME + " (" + "id" + " integer primary key autoincrement, " + "name" + " TEXT, " + "url" + " TEXT);";
        this._name = str;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.BOOKMARKS_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + this.BOOKMARKS_TABLE_NAME);
        onCreate(sQLiteDatabase);
    }

    public void addBookmark(String str, String str2) {
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put("url", str2);
        writableDatabase.insert(this.BOOKMARKS_TABLE_NAME, (String) null, contentValues);
    }

    public void removeBookmark(Integer num) {
        super.getWritableDatabase().delete(this.BOOKMARKS_TABLE_NAME, "id=?", new String[]{num.toString()});
    }

    public Cursor getBookmarks() {
        return getBookmarks(0, (Integer) null);
    }

    public Cursor getBookmarks(Integer num, Integer num2) {
        String str;
        SQLiteDatabase readableDatabase = super.getReadableDatabase();
        String str2 = "";
        if (!(num2 == null || num2.intValue() == 0)) {
            str2 = str2 + " limit " + num2;
        }
        if (num == null || num.intValue() <= 0) {
            str = str2;
        } else {
            str = str2 + " offset " + num;
        }
        return readableDatabase.query(this.BOOKMARKS_TABLE_NAME, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null, str);
    }
}
