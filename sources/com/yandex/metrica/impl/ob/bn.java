package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.appnext.base.b.d;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.k;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.m;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class bn implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final ReentrantReadWriteLock f800a;
    private final Lock b;
    private final Lock c = this.f800a.writeLock();
    private final bo d;
    private final a e;
    /* access modifiers changed from: private */
    public final Object f = new Object();
    /* access modifiers changed from: private */
    public final List<ContentValues> g = new ArrayList(3);
    /* access modifiers changed from: private */
    public ContentValues h;
    private final Context i;
    private final u j;
    private final AtomicLong k = new AtomicLong();

    public bn(u uVar, bo boVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f800a = reentrantReadWriteLock;
        this.b = reentrantReadWriteLock.readLock();
        this.d = boVar;
        this.i = uVar.m();
        this.j = uVar;
        this.k.set(b());
        a aVar = new a();
        this.e = aVar;
        aVar.setName("DatabaseWorker [" + uVar.l() + "]");
        this.e.start();
        c();
    }

    public void a(u uVar) {
        this.e.a(uVar);
    }

    public void a(long j2, bl blVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(j2));
        contentValues.put("start_time", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("server_time_offset", Long.valueOf(m.a()));
        contentValues.put("type", Integer.valueOf(blVar.a()));
        new k(this.i).a(this.j).a(contentValues).a();
        a(contentValues);
    }

    public void a(h hVar, bj bjVar, a.C0030a aVar) {
        ContentValues contentValues = new ContentValues(20);
        contentValues.put("number", Long.valueOf(bjVar.c()));
        contentValues.put(d.fl, Long.valueOf(bjVar.d()));
        contentValues.put("session_id", Long.valueOf(bjVar.a()));
        contentValues.put("session_type", Integer.valueOf(bjVar.b().a()));
        new k(this.i).a(this.j).a(contentValues).a(hVar, aVar);
        b(contentValues);
    }

    private static long a(Cursor cursor) {
        try {
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            bk.a(cursor);
            return 0;
        } finally {
            bk.a(cursor);
        }
    }

    /* JADX INFO: finally extract failed */
    private long b() {
        this.b.lock();
        try {
            long a2 = a(this.d.getReadableDatabase().rawQuery("SELECT count() FROM reports", (String[]) null));
            this.b.unlock();
            return a2;
        } catch (Exception unused) {
            this.b.unlock();
            return 0;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    public void a(ContentValues contentValues) {
        synchronized (this.f) {
            this.h = contentValues;
        }
        synchronized (this.e) {
            this.e.notifyAll();
        }
    }

    public void b(ContentValues contentValues) {
        synchronized (this.f) {
            this.g.add(contentValues);
        }
        synchronized (this.e) {
            this.e.notifyAll();
        }
    }

    public int a(long[] jArr) {
        Cursor cursor;
        Cursor cursor2;
        this.c.lock();
        int i2 = 0;
        try {
            if (bm.f794a.booleanValue()) {
                this.b.lock();
                Cursor cursor3 = null;
                try {
                    SQLiteDatabase readableDatabase = this.d.getReadableDatabase();
                    Cursor rawQuery = readableDatabase.rawQuery(" SELECT DISTINCT id From sessions order by id asc ", new String[0]);
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("All sessions in db: ");
                        while (rawQuery.moveToNext()) {
                            stringBuffer.append(rawQuery.getString(0));
                            stringBuffer.append(", ");
                        }
                        Cursor rawQuery2 = readableDatabase.rawQuery(" SELECT DISTINCT session_id From reports order by session_id asc ", new String[0]);
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("All sessions in reports db: ");
                        while (rawQuery2.moveToNext()) {
                            stringBuffer2.append(rawQuery2.getString(0));
                            stringBuffer2.append(", ");
                        }
                        this.b.unlock();
                        bk.a(rawQuery);
                        bk.a(rawQuery2);
                    } catch (Exception unused) {
                        cursor = null;
                        cursor3 = rawQuery;
                        this.b.unlock();
                        bk.a(cursor3);
                        bk.a(cursor);
                        i2 = this.d.getWritableDatabase().delete("sessions", bm.z.c, bk.a(jArr));
                        this.c.unlock();
                        return i2;
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = null;
                        cursor3 = rawQuery;
                        this.b.unlock();
                        bk.a(cursor3);
                        bk.a(cursor2);
                        throw th;
                    }
                } catch (Exception unused2) {
                    cursor = null;
                    this.b.unlock();
                    bk.a(cursor3);
                    bk.a(cursor);
                    i2 = this.d.getWritableDatabase().delete("sessions", bm.z.c, bk.a(jArr));
                    this.c.unlock();
                    return i2;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = null;
                    this.b.unlock();
                    bk.a(cursor3);
                    bk.a(cursor2);
                    throw th;
                }
            }
            i2 = this.d.getWritableDatabase().delete("sessions", bm.z.c, bk.a(jArr));
        } catch (Exception unused3) {
        } catch (Throwable th3) {
            this.c.unlock();
            throw th3;
        }
        this.c.unlock();
        return i2;
    }

    private void c() {
        try {
            this.c.lock();
            SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
            if (new File(writableDatabase.getPath()).length() > 5242880) {
                this.k.addAndGet((long) (-a(writableDatabase)));
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.c.unlock();
            throw th;
        }
        this.c.unlock();
    }

    /* access modifiers changed from: package-private */
    public int a(SQLiteDatabase sQLiteDatabase) {
        try {
            Integer[] numArr = new Integer[p.f946a.size()];
            Iterator it = p.f946a.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                numArr[i2] = Integer.valueOf(((p.a) it.next()).a());
                i2++;
            }
            return sQLiteDatabase.delete("reports", String.format("%1$s NOT IN (%2$s) AND (%3$s IN (SELECT %3$s FROM %4$s ORDER BY %5$s, %6$s LIMIT (SELECT count() FROM %4$s) / %7$s ) OR %5$s < %8$s )", new Object[]{"type", TextUtils.join(",", numArr), "id", "reports", "session_id", "number", 10, Long.valueOf((System.currentTimeMillis() / 1000) - TimeUnit.DAYS.toSeconds(14))}), (String[]) null);
        } catch (Throwable th) {
            YandexMetrica.getReporter(this.i, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("deleteExcessiveReports exception", th);
            return 0;
        }
    }

    public void a(long j2, int i2, int i3) throws SQLiteException {
        ArrayList arrayList;
        Cursor cursor;
        if (i3 > 0) {
            this.c.lock();
            Cursor cursor2 = null;
            try {
                SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
                String format = String.format(Locale.US, "%1$s = %2$s AND %3$s = %4$s AND %5$s <= (SELECT %5$s FROM %6$s WHERE %1$s = %2$s AND %3$s = %4$s ORDER BY %5$s ASC LIMIT %7$s, 1)", new Object[]{"session_id", Long.toString(j2), "session_type", Integer.toString(i2), "id", "reports", Integer.toString(i3 - 1)});
                if (this.j.p().b()) {
                    cursor = a(format);
                    if (cursor != null) {
                        try {
                            if (cursor.getCount() > 0) {
                                arrayList = new ArrayList(cursor.getCount());
                                while (cursor.moveToNext()) {
                                    ContentValues contentValues = new ContentValues();
                                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                                    arrayList.add(contentValues);
                                }
                            }
                        } catch (Exception unused) {
                            cursor2 = cursor;
                            bk.a(cursor2);
                            this.c.unlock();
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursor;
                            bk.a(cursor2);
                            this.c.unlock();
                            throw th;
                        }
                    }
                    arrayList = null;
                } else {
                    cursor = null;
                    arrayList = null;
                }
                int delete = writableDatabase.delete("reports", format, (String[]) null);
                if (arrayList != null) {
                    a((List<ContentValues>) arrayList, "Event removed from db");
                }
                this.k.addAndGet((long) (-delete));
                bk.a(cursor);
            } catch (Exception unused2) {
                bk.a(cursor2);
                this.c.unlock();
            } catch (Throwable th2) {
                th = th2;
                bk.a(cursor2);
                this.c.unlock();
                throw th;
            }
            this.c.unlock();
        }
    }

    private Cursor a(String str) {
        try {
            return this.d.getReadableDatabase().query("reports", (String[]) null, str, (String[]) null, (String) null, (String) null, (String) null, (String) null);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    public Cursor a(Map<String, String> map) {
        this.b.lock();
        try {
            Cursor query = this.d.getReadableDatabase().query("sessions", (String[]) null, a("id >= ?", map), a(new String[]{Long.toString(0)}, map), (String) null, (String) null, "id ASC", (String) null);
            this.b.unlock();
            return query;
        } catch (Exception unused) {
            this.b.unlock();
            return null;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public Cursor a(long j2, Map<String, String> map) {
        this.b.lock();
        try {
            Cursor query = this.d.getReadableDatabase().query("sessions", (String[]) null, a("id = ?", map), a(new String[]{Long.toString(j2)}, map), (String) null, (String) null, (String) null, (String) null);
            this.b.unlock();
            return query;
        } catch (Exception unused) {
            this.b.unlock();
            return null;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public Cursor b(long j2, bl blVar) throws SQLiteException {
        this.b.lock();
        try {
            Cursor query = this.d.getReadableDatabase().query("reports", (String[]) null, "session_id = ? AND session_type = ?", new String[]{Long.toString(j2), Integer.toString(blVar.a())}, (String) null, (String) null, "number ASC", (String) null);
            this.b.unlock();
            return query;
        } catch (Exception unused) {
            this.b.unlock();
            return null;
        } catch (Throwable th) {
            this.b.unlock();
            throw th;
        }
    }

    private void a(List<ContentValues> list, String str) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            a(list.get(i2), str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        com.yandex.metrica.impl.bk.a((android.database.Cursor) null);
        r7.b.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x003b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<android.content.ContentValues> a(java.lang.Long r8) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.concurrent.locks.Lock r1 = r7.b
            r1.lock()
            r1 = 0
            com.yandex.metrica.impl.ob.bo r2 = r7.d     // Catch:{ Exception -> 0x003b }
            android.database.sqlite.SQLiteDatabase r2 = r2.getReadableDatabase()     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = "SELECT DISTINCT report_request_parameters FROM sessions WHERE id >= 0"
            if (r8 == 0) goto L_0x0023
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ Exception -> 0x003b }
            java.lang.String r4 = "SELECT DISTINCT report_request_parameters FROM sessions WHERE id = %s"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x003b }
            r6 = 0
            r5[r6] = r8     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = java.lang.String.format(r3, r4, r5)     // Catch:{ Exception -> 0x003b }
        L_0x0023:
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch:{ Exception -> 0x003b }
        L_0x0027:
            boolean r8 = r1.moveToNext()     // Catch:{ Exception -> 0x003b }
            if (r8 == 0) goto L_0x0040
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ Exception -> 0x003b }
            r8.<init>()     // Catch:{ Exception -> 0x003b }
            android.database.DatabaseUtils.cursorRowToContentValues(r1, r8)     // Catch:{ Exception -> 0x003b }
            r0.add(r8)     // Catch:{ Exception -> 0x003b }
            goto L_0x0027
        L_0x0039:
            r8 = move-exception
            goto L_0x0049
        L_0x003b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0039 }
            r0.<init>()     // Catch:{ all -> 0x0039 }
        L_0x0040:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
            java.util.concurrent.locks.Lock r8 = r7.b
            r8.unlock()
            return r0
        L_0x0049:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
            java.util.concurrent.locks.Lock r0 = r7.b
            r0.unlock()
            goto L_0x0053
        L_0x0052:
            throw r8
        L_0x0053:
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bn.a(java.lang.Long):java.util.List");
    }

    public ContentValues c(long j2, bl blVar) {
        ContentValues contentValues = new ContentValues();
        this.b.lock();
        Cursor cursor = null;
        try {
            cursor = this.d.getReadableDatabase().rawQuery(String.format(Locale.US, "SELECT report_request_parameters FROM sessions WHERE id = %s AND type = %s ORDER BY id DESC LIMIT 1", new Object[]{Long.valueOf(j2), Integer.valueOf(blVar.a())}), (String[]) null);
            if (cursor.moveToNext()) {
                ContentValues contentValues2 = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues2);
                contentValues = contentValues2;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            bk.a((Cursor) null);
            this.b.unlock();
            throw th;
        }
        bk.a(cursor);
        this.b.unlock();
        return contentValues;
    }

    private static String a(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        for (String next : map.keySet()) {
            sb.append(sb.length() > 0 ? " AND " : "");
            sb.append(next + " = ? ");
        }
        if (bi.a(sb.toString())) {
            return null;
        }
        return sb.toString();
    }

    private static String[] a(String[] strArr, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(strArr));
        for (Map.Entry<String, String> value : map.entrySet()) {
            arrayList.add(value.getValue());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String b(ContentValues contentValues, String str) {
        return bi.b(contentValues.getAsString(str), "");
    }

    public long a() {
        this.b.lock();
        try {
            return this.k.get();
        } finally {
            this.b.unlock();
        }
    }

    private class a extends Thread {
        private final List<ContentValues> b = new ArrayList();
        private u c;

        public a() {
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (this) {
                        if (bn.this.d()) {
                            wait();
                        }
                    }
                } catch (Exception unused) {
                    Thread.currentThread().interrupt();
                }
                synchronized (bn.this.f) {
                    this.b.clear();
                    this.b.addAll(bn.this.g);
                    bn.this.g.clear();
                    bn.a(bn.this, bn.this.h);
                    bn.a(bn.this, (List) this.b);
                    ContentValues unused2 = bn.this.h = null;
                }
                b();
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void a() {
            interrupt();
            this.c = null;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(u uVar) {
            this.c = uVar;
        }

        /* access modifiers changed from: package-private */
        public synchronized void b() {
            if (this.c != null && !this.c.o()) {
                this.c.b();
            }
        }
    }

    public void close() {
        this.g.clear();
        this.e.a();
    }

    /* access modifiers changed from: private */
    public boolean d() {
        boolean z;
        synchronized (this.f) {
            z = this.h == null && this.g.isEmpty();
        }
        return z;
    }

    private void a(ContentValues contentValues, String str) {
        int i2;
        Integer asInteger = contentValues.getAsInteger("type");
        if (asInteger != null) {
            i2 = asInteger.intValue();
        } else {
            i2 = -1;
        }
        if (p.b(i2)) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(": ");
            sb.append(b(contentValues, "name"));
            String b2 = b(contentValues, "value");
            if (p.c(contentValues.getAsInteger("type").intValue()) && !TextUtils.isEmpty(b2)) {
                sb.append(" with value ");
                sb.append(b2);
            }
            this.j.p().a(sb.toString());
        }
    }

    static /* synthetic */ void a(bn bnVar, ContentValues contentValues) {
        if (contentValues != null) {
            bnVar.c.lock();
            try {
                bnVar.d.getWritableDatabase().insertOrThrow("sessions", (String) null, contentValues);
            } catch (Exception unused) {
            } catch (Throwable th) {
                bnVar.c.unlock();
                throw th;
            }
            bnVar.c.unlock();
        }
    }

    static /* synthetic */ void a(bn bnVar, List list) {
        if (list != null && !list.isEmpty()) {
            bnVar.c.lock();
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase writableDatabase = bnVar.d.getWritableDatabase();
                try {
                    if (bnVar.k.get() % 10 == 0) {
                        bnVar.c();
                    }
                    writableDatabase.beginTransaction();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ContentValues contentValues = (ContentValues) it.next();
                        writableDatabase.insertOrThrow("reports", (String) null, contentValues);
                        bnVar.a(contentValues, "Event saved to db");
                    }
                    writableDatabase.setTransactionSuccessful();
                    bnVar.k.incrementAndGet();
                    bk.a(writableDatabase);
                } catch (Exception unused) {
                    sQLiteDatabase = writableDatabase;
                    bk.a(sQLiteDatabase);
                    bnVar.c.unlock();
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = writableDatabase;
                    bk.a(sQLiteDatabase);
                    bnVar.c.unlock();
                    throw th;
                }
            } catch (Exception unused2) {
                bk.a(sQLiteDatabase);
                bnVar.c.unlock();
            } catch (Throwable th2) {
                th = th2;
                bk.a(sQLiteDatabase);
                bnVar.c.unlock();
                throw th;
            }
            bnVar.c.unlock();
        }
    }
}
