package com.appnext.base.a.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.appnext.base.a.b.d;
import com.appnext.base.a.c.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c extends e<com.appnext.base.a.b.c> {
    private static final String COLUMN_STATUS = "status";
    public static final String dS = "config_table";
    public static final String dV = "key";
    private static final String dW = "sample";
    private static final String dX = "sample_type";
    private static final String dY = "cycle";
    private static final String dZ = "cycle_type";
    private static final String ea = "service_key";
    private static final String eb = "data";
    private String[] dU = {"key", "status", "sample", "sample_type", "cycle", "cycle_type", "service_key", "data"};

    public static String ar() {
        return "create table config_table ( key text primary key, status text not null default 'off', sample text not null default '1', sample_type text not null default '',cycle text not null default '1', cycle_type text not null default 'once', service_key text not null default '', data text not null default '')";
    }

    public final long b(JSONArray jSONArray) {
        return super.a(dS, jSONArray);
    }

    public final long a(JSONObject jSONObject) {
        return super.a(dS, jSONObject);
    }

    public final long a(com.appnext.base.a.b.c cVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", cVar.ak());
        contentValues.put("sample", cVar.al());
        contentValues.put("sample_type", cVar.am());
        contentValues.put("cycle", cVar.an());
        contentValues.put("cycle_type", cVar.ao());
        contentValues.put("key", cVar.getKey());
        contentValues.put("service_key", cVar.ap());
        JSONObject aq = cVar.aq();
        if (aq != null) {
            contentValues.put("data", aq.toString());
        }
        return e.b(dS, contentValues);
    }

    public final void delete() {
        super.delete(dS);
    }

    public final void s(String str) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(e.a.Equals);
            super.a(dS, new String[]{"key"}, new String[]{str}, arrayList);
        }
    }

    public final List<com.appnext.base.a.b.c> as() {
        return super.y(dS);
    }

    public final com.appnext.base.a.b.c t(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(e.a.Equals);
        List a2 = super.a(dS, new String[]{"key"}, new String[]{str}, (String) null, arrayList);
        if (a2 == null || a2.size() <= 0) {
            return null;
        }
        return (com.appnext.base.a.b.c) a2.get(0);
    }

    /* access modifiers changed from: protected */
    public final String[] at() {
        return this.dU;
    }

    protected static com.appnext.base.a.b.c c(Cursor cursor) {
        return new com.appnext.base.a.b.c(cursor.getString(cursor.getColumnIndex("status")), cursor.getString(cursor.getColumnIndex("sample")), cursor.getString(cursor.getColumnIndex("sample_type")), cursor.getString(cursor.getColumnIndex("cycle")), cursor.getString(cursor.getColumnIndex("cycle_type")), cursor.getString(cursor.getColumnIndex("key")), cursor.getString(cursor.getColumnIndex("service_key")), cursor.getString(cursor.getColumnIndex("data")));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ d b(Cursor cursor) {
        return new com.appnext.base.a.b.c(cursor.getString(cursor.getColumnIndex("status")), cursor.getString(cursor.getColumnIndex("sample")), cursor.getString(cursor.getColumnIndex("sample_type")), cursor.getString(cursor.getColumnIndex("cycle")), cursor.getString(cursor.getColumnIndex("cycle_type")), cursor.getString(cursor.getColumnIndex("key")), cursor.getString(cursor.getColumnIndex("service_key")), cursor.getString(cursor.getColumnIndex("data")));
    }
}
