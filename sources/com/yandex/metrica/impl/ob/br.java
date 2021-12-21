package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import java.util.ArrayList;
import java.util.List;

public class br {

    /* renamed from: a  reason: collision with root package name */
    private final bv f806a;
    private String b;

    public br(bo boVar, String str) {
        this.f806a = new bx(boVar);
        this.b = str;
    }

    public List<cw> a() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        try {
            sQLiteDatabase = this.f806a.a();
            try {
                cursor = sQLiteDatabase.query(this.b, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            ArrayList arrayList = new ArrayList();
                            do {
                                arrayList.add(new cw(cursor.getString(cursor.getColumnIndex("name")), cursor.getLong(cursor.getColumnIndex("granted")) == 1));
                            } while (cursor.moveToNext());
                            this.f806a.a(sQLiteDatabase);
                            bk.a(cursor);
                            return arrayList;
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th2) {
                        th = th2;
                        this.f806a.a(sQLiteDatabase);
                        bk.a(cursor);
                        throw th;
                    }
                }
            } catch (Exception unused2) {
                cursor = null;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                cursor = null;
                th = th4;
                this.f806a.a(sQLiteDatabase);
                bk.a(cursor);
                throw th;
            }
        } catch (Exception unused3) {
            sQLiteDatabase = null;
            cursor = null;
        } catch (Throwable th5) {
            cursor = null;
            th = th5;
            sQLiteDatabase = null;
            this.f806a.a(sQLiteDatabase);
            bk.a(cursor);
            throw th;
        }
        this.f806a.a(sQLiteDatabase);
        bk.a(cursor);
        return null;
    }

    public void a(List<cw> list) {
        SQLiteDatabase a2 = this.f806a.a();
        try {
            a2.beginTransaction();
            a2.execSQL("delete from permissions");
            for (cw next : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", next.b());
                contentValues.put("granted", Integer.valueOf(next.a() ? 1 : 0));
                a2.insert("permissions", (String) null, contentValues);
            }
            a2.setTransactionSuccessful();
        } catch (SQLException unused) {
        } catch (Throwable th) {
            a2.endTransaction();
            this.f806a.a(a2);
            throw th;
        }
        a2.endTransaction();
        this.f806a.a(a2);
    }
}
