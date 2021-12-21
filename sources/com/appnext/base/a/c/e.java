package com.appnext.base.a.c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import com.appnext.base.a.a.a;
import com.appnext.base.a.b.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public abstract class e<MODEL extends d> {
    private static final int eh = -1;
    private static final String ei = " DESC";

    /* access modifiers changed from: protected */
    public abstract String[] at();

    /* access modifiers changed from: protected */
    public abstract MODEL b(Cursor cursor);

    protected enum a {
        Equals(" = ? "),
        GreaterThan(" >= ? "),
        LessThan(" <= ? ");
        
        private String mOperator;

        private a(String str) {
            this.mOperator = str;
        }

        public final String au() {
            return this.mOperator;
        }
    }

    protected static long a(String str, ContentValues contentValues) {
        try {
            long insert = com.appnext.base.a.a.a.ac().ad().insert(str, (String) null, contentValues);
            com.appnext.base.a.a.a.ac().ae();
            return insert;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, th);
            return -1;
        }
    }

    protected static long b(String str, ContentValues contentValues) {
        try {
            long insertWithOnConflict = com.appnext.base.a.a.a.ac().ad().insertWithOnConflict(str, (String) null, contentValues, 5);
            com.appnext.base.a.a.a.ac().ae();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, th);
            return -1;
        }
    }

    protected static long a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, 5);
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, th);
            return -1;
        }
    }

    protected static long b(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        try {
            return sQLiteDatabase.insert(str, (String) null, contentValues);
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, th);
            return -1;
        }
    }

    private static ContentValues b(JSONObject jSONObject) {
        try {
            ContentValues contentValues = new ContentValues();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                contentValues.put(next, jSONObject.getString(next));
            }
            return contentValues;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final long a(String str, JSONObject jSONObject) {
        try {
            long insertWithOnConflict = com.appnext.base.a.a.a.ac().ad().insertWithOnConflict(str, (String) null, b(jSONObject), 5);
            com.appnext.base.a.a.a.ac().ae();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, th);
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.endTransaction();
        com.appnext.base.a.a.a.ac().ae();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(java.lang.String r7, org.json.JSONArray r8) {
        /*
            r6 = this;
            r0 = -1
            if (r8 == 0) goto L_0x003a
            r2 = 0
            int r3 = r8.length()     // Catch:{ all -> 0x0036 }
            com.appnext.base.a.a.a r4 = com.appnext.base.a.a.a.ac()     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r2 = r4.ad()     // Catch:{ all -> 0x0036 }
            r2.beginTransaction()     // Catch:{ all -> 0x0036 }
            r4 = 0
        L_0x0015:
            if (r4 >= r3) goto L_0x0026
            org.json.JSONObject r5 = r8.getJSONObject(r4)     // Catch:{ all -> 0x0036 }
            android.content.ContentValues r5 = b((org.json.JSONObject) r5)     // Catch:{ all -> 0x0036 }
            long r0 = a(r2, r7, r5)     // Catch:{ all -> 0x0036 }
            int r4 = r4 + 1
            goto L_0x0015
        L_0x0026:
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x003a
        L_0x002b:
            r2.endTransaction()
            com.appnext.base.a.a.a r7 = com.appnext.base.a.a.a.ac()
            r7.ae()
            goto L_0x003a
        L_0x0036:
            if (r2 == 0) goto L_0x003a
            goto L_0x002b
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.a.c.e.a(java.lang.String, org.json.JSONArray):long");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.endTransaction();
        com.appnext.base.a.a.a.ac().ae();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long b(java.lang.String r7, org.json.JSONArray r8) {
        /*
            r6 = this;
            r0 = -1
            if (r8 == 0) goto L_0x003a
            r2 = 0
            int r3 = r8.length()     // Catch:{ all -> 0x0036 }
            com.appnext.base.a.a.a r4 = com.appnext.base.a.a.a.ac()     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r2 = r4.ad()     // Catch:{ all -> 0x0036 }
            r2.beginTransaction()     // Catch:{ all -> 0x0036 }
            r4 = 0
        L_0x0015:
            if (r4 >= r3) goto L_0x0026
            org.json.JSONObject r5 = r8.getJSONObject(r4)     // Catch:{ all -> 0x0036 }
            android.content.ContentValues r5 = b((org.json.JSONObject) r5)     // Catch:{ all -> 0x0036 }
            long r0 = b(r2, r7, r5)     // Catch:{ all -> 0x0036 }
            int r4 = r4 + 1
            goto L_0x0015
        L_0x0026:
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x003a
        L_0x002b:
            r2.endTransaction()
            com.appnext.base.a.a.a r7 = com.appnext.base.a.a.a.ac()
            r7.ae()
            goto L_0x003a
        L_0x0036:
            if (r2 == 0) goto L_0x003a
            goto L_0x002b
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.a.c.e.b(java.lang.String, org.json.JSONArray):long");
    }

    /* access modifiers changed from: protected */
    public final void delete(String str) {
        a(str, (String[]) null, (String[]) null, (List<a>) null);
    }

    /* access modifiers changed from: protected */
    public final int a(String str, String[] strArr, String[] strArr2, List<a> list) {
        try {
            SQLiteDatabase ad = com.appnext.base.a.a.a.ac().ad();
            String str2 = null;
            if (strArr != null) {
                str2 = a(strArr, list);
            }
            int delete = ad.delete(str, str2, strArr2);
            com.appnext.base.a.a.a.ac().ae();
            return delete;
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
            return 0;
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, new Exception(th.getMessage()));
            return 0;
        }
    }

    protected static void e(String str, String str2) {
        try {
            com.appnext.base.a.a.a.ac().ad().delete(str, str2, (String[]) null);
            com.appnext.base.a.a.a.ac().ae();
        } catch (SQLiteFullException e) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.DatabaseOrDiskFull$53629b42, new Exception(e.getMessage()));
        } catch (Throwable th) {
            com.appnext.base.a.a.a.ac();
            com.appnext.base.a.a.a.a(a.C0037a.Global$53629b42, new Exception(th.getMessage()));
        }
    }

    /* access modifiers changed from: protected */
    public final List<MODEL> y(String str) {
        try {
            List<MODEL> e = e(com.appnext.base.a.a.a.ac().ad().query(str, at(), (String) null, (String[]) null, (String) null, (String) null, (String) null));
            com.appnext.base.a.a.a.ac().ae();
            return e;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final List<MODEL> a(String str, String[] strArr, String[] strArr2, String str2, List<a> list) {
        try {
            List<MODEL> e = e(com.appnext.base.a.a.a.ac().ad().query(str, at(), a(strArr, list), strArr2, (String) null, (String) null, str2));
            com.appnext.base.a.a.a.ac().ae();
            return e;
        } catch (Throwable unused) {
            return null;
        }
    }

    protected static String z(String str) {
        return str + ei;
    }

    private List<MODEL> e(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                arrayList.add(b(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private static String a(String[] strArr, List<a> list) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(" AND ");
                }
                sb.append(strArr[i]);
                sb.append(list.get(i).au());
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }
}
