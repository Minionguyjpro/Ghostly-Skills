package com.yandex.metrica.impl.ob;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class bu implements bt {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, String[]> f808a;

    public bu(HashMap<String, String[]> hashMap) {
        this.f808a = hashMap;
    }

    public boolean a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor;
        boolean z = true;
        try {
            for (Map.Entry next : this.f808a.entrySet()) {
                cursor = null;
                cursor = sQLiteDatabase.query((String) next.getKey(), (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
                if (cursor == null) {
                    bk.a(cursor);
                    return false;
                }
                z &= a(cursor, (String[]) next.getValue());
                bk.a(cursor);
            }
            return z;
        } catch (Exception unused) {
            return false;
        } catch (Throwable th) {
            bk.a(cursor);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Cursor cursor, String[] strArr) {
        String[] columnNames = cursor.getColumnNames();
        Arrays.sort(columnNames);
        Arrays.sort(strArr);
        return Arrays.equals(columnNames, strArr);
    }
}
