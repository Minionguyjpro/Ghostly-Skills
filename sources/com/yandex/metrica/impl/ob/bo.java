package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.Closeable;

public class bo extends SQLiteOpenHelper implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    protected final bs f802a;

    public bo(Context context, String str, bs bsVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, bm.b);
        this.f802a = bsVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f802a.b(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f802a.a(sQLiteDatabase, i, i2);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        this.f802a.a(sQLiteDatabase);
    }
}
