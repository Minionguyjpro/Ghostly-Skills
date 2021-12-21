package com.yandex.metrica.impl.ob;

import android.database.sqlite.SQLiteDatabase;

public class bx implements bv {

    /* renamed from: a  reason: collision with root package name */
    private final bo f810a;

    public void a(SQLiteDatabase sQLiteDatabase) {
    }

    public bx(bo boVar) {
        this.f810a = boVar;
    }

    public SQLiteDatabase a() {
        return this.f810a.getWritableDatabase();
    }
}
