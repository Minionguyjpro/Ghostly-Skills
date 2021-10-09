package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import java.util.HashMap;
import java.util.Map;

public class bq {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Object> f804a;
    /* access modifiers changed from: private */
    public final Map<String, Object> b;
    private final String c;
    private final a d;
    /* access modifiers changed from: private */
    public volatile boolean e;
    private final bv f;

    static {
        bq.class.getSimpleName();
    }

    public bq(bo boVar, String str) {
        this(str, (bv) new bx(boVar));
    }

    protected bq(String str, bv bvVar) {
        this.f804a = new HashMap();
        this.b = new HashMap();
        this.f = bvVar;
        this.c = str;
        a aVar = new a(this, (byte) 0);
        this.d = aVar;
        aVar.start();
    }

    private class a extends Thread {
        private a() {
        }

        /* synthetic */ a(bq bqVar, byte b) {
            this();
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
                com.yandex.metrica.impl.ob.bq r0 = com.yandex.metrica.impl.ob.bq.this
                java.util.Map r0 = r0.f804a
                monitor-enter(r0)
                com.yandex.metrica.impl.ob.bq r1 = com.yandex.metrica.impl.ob.bq.this     // Catch:{ all -> 0x005e }
                com.yandex.metrica.impl.ob.bq.b((com.yandex.metrica.impl.ob.bq) r1)     // Catch:{ all -> 0x005e }
                com.yandex.metrica.impl.ob.bq r1 = com.yandex.metrica.impl.ob.bq.this     // Catch:{ all -> 0x005e }
                r2 = 1
                boolean unused = r1.e = r2     // Catch:{ all -> 0x005e }
                com.yandex.metrica.impl.ob.bq r1 = com.yandex.metrica.impl.ob.bq.this     // Catch:{ all -> 0x005e }
                java.util.Map r1 = r1.f804a     // Catch:{ all -> 0x005e }
                r1.notifyAll()     // Catch:{ all -> 0x005e }
                monitor-exit(r0)     // Catch:{ all -> 0x005e }
            L_0x001c:
                boolean r0 = r3.isInterrupted()
                if (r0 != 0) goto L_0x005d
                monitor-enter(r3)
                com.yandex.metrica.impl.ob.bq r0 = com.yandex.metrica.impl.ob.bq.this     // Catch:{ all -> 0x005a }
                java.util.Map r0 = r0.b     // Catch:{ all -> 0x005a }
                int r0 = r0.size()     // Catch:{ all -> 0x005a }
                if (r0 != 0) goto L_0x0036
                r3.wait()     // Catch:{ InterruptedException -> 0x0033 }
                goto L_0x0036
            L_0x0033:
                r3.interrupt()     // Catch:{ all -> 0x005a }
            L_0x0036:
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x005a }
                com.yandex.metrica.impl.ob.bq r1 = com.yandex.metrica.impl.ob.bq.this     // Catch:{ all -> 0x005a }
                java.util.Map r1 = r1.b     // Catch:{ all -> 0x005a }
                r0.<init>(r1)     // Catch:{ all -> 0x005a }
                com.yandex.metrica.impl.ob.bq r1 = com.yandex.metrica.impl.ob.bq.this     // Catch:{ all -> 0x005a }
                java.util.Map r1 = r1.b     // Catch:{ all -> 0x005a }
                r1.clear()     // Catch:{ all -> 0x005a }
                monitor-exit(r3)     // Catch:{ all -> 0x005a }
                int r1 = r0.size()
                if (r1 <= 0) goto L_0x001c
                com.yandex.metrica.impl.ob.bq r1 = com.yandex.metrica.impl.ob.bq.this
                com.yandex.metrica.impl.ob.bq.a((com.yandex.metrica.impl.ob.bq) r1, (java.util.Map) r0)
                r0.clear()
                goto L_0x001c
            L_0x005a:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x005a }
                throw r0
            L_0x005d:
                return
            L_0x005e:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x005e }
                goto L_0x0062
            L_0x0061:
                throw r1
            L_0x0062:
                goto L_0x0061
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bq.a.run():void");
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.c;
    }

    public void b() {
        synchronized (this.d) {
            this.d.notifyAll();
        }
    }

    private void a(ContentValues[] contentValuesArr) {
        if (contentValuesArr != null) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                SQLiteDatabase a2 = this.f.a();
                try {
                    a2.beginTransaction();
                    for (ContentValues contentValues : contentValuesArr) {
                        if (contentValues.getAsString("value") == null) {
                            a2.delete(a(), "key = ?", new String[]{contentValues.getAsString("key")});
                        } else {
                            a2.insertWithOnConflict(a(), (String) null, contentValues, 5);
                        }
                    }
                    a2.setTransactionSuccessful();
                    bk.a(a2);
                    this.f.a(a2);
                } catch (Exception unused) {
                    sQLiteDatabase = a2;
                    bk.a(sQLiteDatabase);
                    this.f.a(sQLiteDatabase);
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = a2;
                    bk.a(sQLiteDatabase);
                    this.f.a(sQLiteDatabase);
                    throw th;
                }
            } catch (Exception unused2) {
                bk.a(sQLiteDatabase);
                this.f.a(sQLiteDatabase);
            } catch (Throwable th2) {
                th = th2;
                bk.a(sQLiteDatabase);
                this.f.a(sQLiteDatabase);
                throw th;
            }
        }
    }

    public String a(String str, String str2) {
        Object b2 = b(str);
        return b2 instanceof String ? (String) b2 : str2;
    }

    public int a(String str, int i) {
        Object b2 = b(str);
        return b2 instanceof Integer ? ((Integer) b2).intValue() : i;
    }

    public long a(String str, long j) {
        Object b2 = b(str);
        return b2 instanceof Long ? ((Long) b2).longValue() : j;
    }

    public boolean a(String str, boolean z) {
        Object b2 = b(str);
        return b2 instanceof Boolean ? ((Boolean) b2).booleanValue() : z;
    }

    public bq a(String str) {
        synchronized (this.f804a) {
            c();
            this.f804a.remove(str);
        }
        synchronized (this.d) {
            this.b.put(str, this);
            this.d.notifyAll();
        }
        return this;
    }

    public synchronized bq b(String str, String str2) {
        a(str, (Object) str2);
        return this;
    }

    public bq b(String str, long j) {
        a(str, (Object) Long.valueOf(j));
        return this;
    }

    public synchronized bq b(String str, int i) {
        a(str, (Object) Integer.valueOf(i));
        return this;
    }

    public bq b(String str, boolean z) {
        a(str, (Object) Boolean.valueOf(z));
        return this;
    }

    private void a(String str, Object obj) {
        synchronized (this.f804a) {
            c();
            this.f804a.put(str, obj);
        }
        synchronized (this.d) {
            this.b.put(str, obj);
            this.d.notifyAll();
        }
    }

    private Object b(String str) {
        Object obj;
        synchronized (this.f804a) {
            c();
            obj = this.f804a.get(str);
        }
        return obj;
    }

    private void c() {
        if (!this.e) {
            try {
                this.f804a.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        if (r8 != 4) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072 A[Catch:{ Exception -> 0x0084, all -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x001f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void b(com.yandex.metrica.impl.ob.bq r13) {
        /*
            java.lang.String r0 = "type"
            java.lang.String r1 = "value"
            java.lang.String r2 = "key"
            r3 = 0
            com.yandex.metrica.impl.ob.bv r4 = r13.f     // Catch:{ Exception -> 0x0093, all -> 0x0088 }
            android.database.sqlite.SQLiteDatabase r4 = r4.a()     // Catch:{ Exception -> 0x0093, all -> 0x0088 }
            java.lang.String r6 = r13.a()     // Catch:{ Exception -> 0x0094, all -> 0x0086 }
            java.lang.String[] r7 = new java.lang.String[]{r2, r1, r0}     // Catch:{ Exception -> 0x0094, all -> 0x0086 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r5 = r4
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0094, all -> 0x0086 }
        L_0x001f:
            boolean r6 = r5.moveToNext()     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            if (r6 == 0) goto L_0x0078
            int r6 = r5.getColumnIndex(r2)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            int r7 = r5.getColumnIndex(r1)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            java.lang.String r7 = r5.getString(r7)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            int r8 = r5.getColumnIndex(r0)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            int r8 = r5.getInt(r8)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            boolean r9 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            if (r9 != 0) goto L_0x001f
            r9 = 1
            if (r8 == r9) goto L_0x005b
            r9 = 2
            if (r8 == r9) goto L_0x0056
            r9 = 3
            if (r8 == r9) goto L_0x0051
            r9 = 4
            if (r8 == r9) goto L_0x0070
        L_0x004f:
            r7 = r3
            goto L_0x0070
        L_0x0051:
            java.lang.Long r7 = com.yandex.metrica.impl.utils.i.a(r7)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            goto L_0x0070
        L_0x0056:
            java.lang.Integer r7 = com.yandex.metrica.impl.utils.i.b(r7)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            goto L_0x0070
        L_0x005b:
            java.lang.String r8 = "true"
            boolean r8 = r8.equals(r7)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            if (r8 == 0) goto L_0x0066
            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            goto L_0x0070
        L_0x0066:
            java.lang.String r8 = "false"
            boolean r7 = r8.equals(r7)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            if (r7 == 0) goto L_0x004f
            java.lang.Boolean r7 = java.lang.Boolean.FALSE     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
        L_0x0070:
            if (r7 == 0) goto L_0x001f
            java.util.Map<java.lang.String, java.lang.Object> r8 = r13.f804a     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            r8.put(r6, r7)     // Catch:{ Exception -> 0x0084, all -> 0x0081 }
            goto L_0x001f
        L_0x0078:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r5)
        L_0x007b:
            com.yandex.metrica.impl.ob.bv r13 = r13.f
            r13.a(r4)
            return
        L_0x0081:
            r0 = move-exception
            r3 = r5
            goto L_0x008a
        L_0x0084:
            r3 = r5
            goto L_0x0094
        L_0x0086:
            r0 = move-exception
            goto L_0x008a
        L_0x0088:
            r0 = move-exception
            r4 = r3
        L_0x008a:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r3)
            com.yandex.metrica.impl.ob.bv r13 = r13.f
            r13.a(r4)
            throw r0
        L_0x0093:
            r4 = r3
        L_0x0094:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r3)
            goto L_0x007b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bq.b(com.yandex.metrica.impl.ob.bq):void");
    }

    static /* synthetic */ void a(bq bqVar, Map map) {
        ContentValues[] contentValuesArr = new ContentValues[map.size()];
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            ContentValues contentValues = new ContentValues();
            Object value = entry.getValue();
            contentValues.put("key", (String) entry.getKey());
            if (value == bqVar) {
                contentValues.putNull("value");
            } else if (value instanceof String) {
                contentValues.put("value", (String) value);
                contentValues.put("type", 4);
            } else if (value instanceof Long) {
                contentValues.put("value", (Long) value);
                contentValues.put("type", 3);
            } else if (value instanceof Integer) {
                contentValues.put("value", (Integer) value);
                contentValues.put("type", 2);
            } else if (value instanceof Boolean) {
                contentValues.put("value", String.valueOf(((Boolean) value).booleanValue()));
                contentValues.put("type", 1);
            } else if (value != null) {
                throw new UnsupportedOperationException();
            }
            contentValuesArr[i] = contentValues;
            i++;
        }
        bqVar.a(contentValuesArr);
    }
}
