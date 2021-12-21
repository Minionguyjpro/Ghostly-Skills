package com.appnext.base.a.c;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.a.b.d;
import com.appnext.base.a.c.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public final class a extends e<com.appnext.base.a.b.a> {
    private static final String COLUMN_PACKAGE_NAME = "p";
    public static final String dS = "ct";
    private static final String dT = "c";
    private String[] dU = {COLUMN_PACKAGE_NAME, dT};

    public static String ar() {
        return "create table ct ( p text, c integer)";
    }

    public final long a(JSONArray jSONArray) {
        return super.a(dS, jSONArray);
    }

    public final void delete() {
        super.delete(dS);
    }

    public final void q(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(e.a.Equals);
        super.a(dS, new String[]{COLUMN_PACKAGE_NAME}, new String[]{str}, arrayList);
    }

    public final List<com.appnext.base.a.b.a> as() {
        return super.y(dS);
    }

    public final List<com.appnext.base.a.b.a> r(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(e.a.Equals);
        return super.a(dS, new String[]{COLUMN_PACKAGE_NAME}, new String[]{str}, (String) null, arrayList);
    }

    private static ContentValues b(com.appnext.base.a.b.a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PACKAGE_NAME, aVar.getPackageName());
        contentValues.put(dT, aVar.ag());
        return contentValues;
    }

    /* access modifiers changed from: protected */
    public final String[] at() {
        return this.dU;
    }

    protected static com.appnext.base.a.b.a a(Cursor cursor) {
        return new com.appnext.base.a.b.a(cursor.getString(cursor.getColumnIndex(COLUMN_PACKAGE_NAME)), Integer.valueOf(cursor.getInt(cursor.getColumnIndex(dT))));
    }

    public final long a(com.appnext.base.a.b.a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PACKAGE_NAME, aVar.getPackageName());
        contentValues.put(dT, aVar.ag());
        return e.a(dS, contentValues);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ d b(Cursor cursor) {
        return new com.appnext.base.a.b.a(cursor.getString(cursor.getColumnIndex(COLUMN_PACKAGE_NAME)), Integer.valueOf(cursor.getInt(cursor.getColumnIndex(dT))));
    }
}
