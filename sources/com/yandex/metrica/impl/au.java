package com.yandex.metrica.impl;

import android.database.Cursor;
import com.yandex.metrica.c;
import com.yandex.metrica.impl.at;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.t;
import java.util.Map;

class au extends at {
    /* access modifiers changed from: protected */
    public boolean a(long j) {
        return false;
    }

    /* access modifiers changed from: protected */
    public long s() {
        return Long.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public long t() {
        return Long.MIN_VALUE;
    }

    public au(t tVar) {
        super(tVar);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r1.getCount() != 0) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.at.c x() {
        /*
            r7 = this;
            r0 = 0
            android.database.Cursor r1 = r7.z()     // Catch:{ Exception -> 0x0051, all -> 0x0048 }
            if (r1 == 0) goto L_0x0013
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 == 0) goto L_0x0013
            int r2 = r1.getCount()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 != 0) goto L_0x0038
        L_0x0013:
            com.yandex.metrica.impl.ob.bn r2 = r7.o     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            long r3 = r7.s()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            com.yandex.metrica.impl.ob.bl r5 = com.yandex.metrica.impl.ob.bl.BACKGROUND     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            android.database.Cursor r0 = r2.b((long) r3, (com.yandex.metrica.impl.ob.bl) r5)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r0 == 0) goto L_0x0038
            boolean r2 = r0.moveToFirst()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 == 0) goto L_0x0038
            int r2 = r0.getCount()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            if (r2 <= 0) goto L_0x0038
            com.yandex.metrica.impl.ob.bn r2 = r7.o     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            long r3 = r7.s()     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            com.yandex.metrica.impl.ob.bl r5 = com.yandex.metrica.impl.ob.bl.BACKGROUND     // Catch:{ Exception -> 0x0044, all -> 0x003f }
            r2.a((long) r3, (com.yandex.metrica.impl.ob.bl) r5)     // Catch:{ Exception -> 0x0044, all -> 0x003f }
        L_0x0038:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r0)
            goto L_0x0058
        L_0x003f:
            r2 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x004a
        L_0x0044:
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0052
        L_0x0048:
            r2 = move-exception
            r1 = r0
        L_0x004a:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r0)
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
            throw r2
        L_0x0051:
            r1 = r0
        L_0x0052:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r0)
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
        L_0x0058:
            com.yandex.metrica.impl.at$c r0 = super.x()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.au.x():com.yandex.metrica.impl.at$c");
    }

    /* access modifiers changed from: protected */
    public Cursor z() {
        return this.o.a(s(), (Map<String, String>) this.b);
    }

    /* access modifiers changed from: protected */
    public Cursor a(long j, bl blVar) {
        return this.o.b(s(), blVar);
    }

    /* access modifiers changed from: protected */
    public at.b a(long j, c.a.d.b bVar) {
        return super.a(t(), bVar);
    }

    public String a() {
        return super.a() + " [" + s() + "]";
    }
}
