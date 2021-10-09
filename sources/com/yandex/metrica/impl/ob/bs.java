package com.yandex.metrica.impl.ob;

import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.yandex.metrica.impl.ob.bm;

public class bs {

    /* renamed from: a  reason: collision with root package name */
    private final bm.l f807a;
    private final bm.l b;
    private final SparseArray<bm.l> c;
    private final bt d;

    public bs(bm.l lVar, bm.l lVar2, SparseArray<bm.l> sparseArray, bt btVar) {
        this.f807a = lVar;
        this.b = lVar2;
        this.c = sparseArray;
        this.d = btVar;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            if (this.d != null && !this.d.a(sQLiteDatabase)) {
                a(sQLiteDatabase, this.f807a, this.b);
            }
        } catch (Exception unused) {
        }
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        a(this.f807a, sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void a(bm.l lVar, SQLiteDatabase sQLiteDatabase) {
        try {
            lVar.a(sQLiteDatabase);
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.database.sqlite.SQLiteDatabase r4, int r5, int r6) {
        /*
            r3 = this;
            r0 = 1
            if (r6 <= r5) goto L_0x0017
            r1 = 0
            int r5 = r5 + r0
        L_0x0005:
            if (r5 > r6) goto L_0x0018
            android.util.SparseArray<com.yandex.metrica.impl.ob.bm$l> r2 = r3.c     // Catch:{ Exception -> 0x0017 }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ Exception -> 0x0017 }
            com.yandex.metrica.impl.ob.bm$l r2 = (com.yandex.metrica.impl.ob.bm.l) r2     // Catch:{ Exception -> 0x0017 }
            if (r2 == 0) goto L_0x0014
            r2.a(r4)     // Catch:{ Exception -> 0x0017 }
        L_0x0014:
            int r5 = r5 + 1
            goto L_0x0005
        L_0x0017:
            r1 = 1
        L_0x0018:
            com.yandex.metrica.impl.ob.bt r5 = r3.d
            boolean r5 = r5.a(r4)
            r5 = r5 ^ r0
            r5 = r5 | r1
            if (r5 == 0) goto L_0x0029
            com.yandex.metrica.impl.ob.bm$l r5 = r3.f807a
            com.yandex.metrica.impl.ob.bm$l r6 = r3.b
            r3.a((android.database.sqlite.SQLiteDatabase) r4, (com.yandex.metrica.impl.ob.bm.l) r5, (com.yandex.metrica.impl.ob.bm.l) r6)
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bs.a(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void a(SQLiteDatabase sQLiteDatabase, bm.l lVar, bm.l lVar2) {
        try {
            lVar2.a(sQLiteDatabase);
        } catch (Exception unused) {
        }
        a(lVar, sQLiteDatabase);
    }
}
