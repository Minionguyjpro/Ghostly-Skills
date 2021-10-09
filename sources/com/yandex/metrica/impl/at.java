package com.yandex.metrica.impl;

import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.c;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.ak;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.bn;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.ct;
import com.yandex.metrica.impl.ob.d;
import com.yandex.metrica.impl.ob.ee;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.eg;
import com.yandex.metrica.impl.ob.eh;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.utils.f;
import com.yandex.metrica.impl.utils.j;
import com.yandex.metrica.impl.utils.m;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class at extends l {
    c.a m;
    ba n;
    bn o;
    t p;
    List<Long> q;
    int r = 0;
    int s = -1;
    private c t;
    private final f u = new f();
    private boolean v;

    /* access modifiers changed from: protected */
    public boolean a(long j) {
        return -2 == j;
    }

    public at(t tVar) {
        this.p = tVar;
        this.o = tVar.i();
        this.n = tVar.h();
        this.r = com.yandex.metrica.impl.ob.b.b(1, (d) ap.a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(m.a())));
    }

    /* access modifiers changed from: package-private */
    public void v() {
        Uri.Builder buildUpon = Uri.parse(this.n.C()).buildUpon();
        buildUpon.path("report");
        buildUpon.appendQueryParameter("deviceid", bi.c(this.c.c(), this.n.c()));
        buildUpon.appendQueryParameter("uuid", bi.c(this.c.b(), this.n.b()));
        buildUpon.appendQueryParameter("analytics_sdk_version", bi.c(this.c.h(), this.n.h()));
        buildUpon.appendQueryParameter("client_analytics_sdk_version", bi.c(this.c.i(), this.n.i()));
        buildUpon.appendQueryParameter("app_version_name", bi.c(this.c.x(), this.n.x()));
        buildUpon.appendQueryParameter("app_build_number", bi.c(this.c.z(), this.n.z()));
        buildUpon.appendQueryParameter("os_version", bi.c(this.c.q(), this.n.q()));
        if (this.c.r() > 0) {
            buildUpon.appendQueryParameter("os_api_level", String.valueOf(this.c.r()));
        }
        if (!TextUtils.isEmpty(this.c.k())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_number", this.c.k());
        }
        if (!TextUtils.isEmpty(this.c.l())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_type", this.c.l());
        }
        if (!TextUtils.isEmpty(this.c.N())) {
            buildUpon.appendQueryParameter("app_debuggable", this.c.N());
        }
        buildUpon.appendQueryParameter("locale", bi.c(this.c.w(), this.n.w()));
        buildUpon.appendQueryParameter("is_rooted", bi.c(this.c.F(), this.n.F()));
        buildUpon.appendQueryParameter("app_framework", bi.c(this.c.d(), this.n.d()));
        buildUpon.appendQueryParameter(this.n.j() >= 200 ? "api_key_128" : "api_key", y());
        buildUpon.appendQueryParameter("app_id", this.p.l().b());
        buildUpon.appendQueryParameter("app_platform", this.n.m());
        buildUpon.appendQueryParameter("protocol_version", this.n.f());
        buildUpon.appendQueryParameter("model", this.n.p());
        buildUpon.appendQueryParameter("manufacturer", this.n.o());
        buildUpon.appendQueryParameter("screen_width", String.valueOf(this.n.s()));
        buildUpon.appendQueryParameter("screen_height", String.valueOf(this.n.t()));
        buildUpon.appendQueryParameter("screen_dpi", String.valueOf(this.n.u()));
        buildUpon.appendQueryParameter("scalefactor", String.valueOf(this.n.v()));
        buildUpon.appendQueryParameter("device_type", this.n.G());
        buildUpon.appendQueryParameter("android_id", this.n.n());
        String a2 = this.n.a(this.p.m());
        if (!TextUtils.isEmpty(a2)) {
            buildUpon.appendQueryParameter("adv_id", a2);
        }
        String y = this.n.y();
        if (!TextUtils.isEmpty(y)) {
            buildUpon.appendQueryParameter("clids_set", y);
        }
        a(buildUpon.build().toString());
    }

    /* access modifiers changed from: package-private */
    public c.a a(c cVar, c.a.C0024c[] cVarArr) {
        c.a aVar = new c.a();
        a(aVar);
        aVar.c = (c.a.d[]) cVar.f731a.toArray(new c.a.d[cVar.f731a.size()]);
        aVar.d = a(cVar.c);
        aVar.e = cVarArr;
        this.r += com.yandex.metrica.impl.ob.b.g(8);
        return aVar;
    }

    /* access modifiers changed from: package-private */
    public void a(final c.a aVar) {
        ef.a(this.p.m()).a((eh) new eh() {
            public void a(eg egVar) {
                c.a aVar = aVar;
                List<String> c = egVar.c();
                if (!bk.a((Collection) c)) {
                    aVar.f = new String[c.size()];
                    for (int i = 0; i < c.size(); i++) {
                        String str = c.get(i);
                        if (!TextUtils.isEmpty(str)) {
                            aVar.f[i] = str;
                            at.this.r += com.yandex.metrica.impl.ob.b.b(aVar.f[i]);
                            at.this.r += com.yandex.metrica.impl.ob.b.g(9);
                        }
                    }
                }
                c.a aVar2 = aVar;
                List<ee> a2 = egVar.a();
                if (!bk.a((Collection) a2)) {
                    aVar2.g = new c.a.e[a2.size()];
                    for (int i2 = 0; i2 < a2.size(); i2++) {
                        aVar2.g[i2] = ap.a(a2.get(i2));
                        at.this.r += com.yandex.metrica.impl.ob.b.b((d) aVar2.g[i2]);
                        at.this.r += com.yandex.metrica.impl.ob.b.g(10);
                    }
                }
            }
        });
    }

    public boolean b() {
        if (!this.n.M()) {
            return false;
        }
        this.q = null;
        this.v = this.p.H();
        c.a.C0024c[] w = w();
        c x = x();
        this.t = x;
        if (x.f731a.isEmpty()) {
            return false;
        }
        this.m = a(this.t, w);
        v();
        this.q = this.t.b;
        return true;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
            r5 = this;
            super.e()
            com.yandex.metrica.c$a r0 = r5.m
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            long r2 = com.yandex.metrica.impl.utils.m.a()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            com.yandex.metrica.c$a$f r1 = com.yandex.metrica.impl.ap.a(r1, r2)
            r0.b = r1
            com.yandex.metrica.c$a r0 = r5.m
            byte[] r0 = com.yandex.metrica.impl.ob.d.a((com.yandex.metrica.impl.ob.d) r0)
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r2 = 0
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch:{ Exception -> 0x0051 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0051 }
            r2 = 0
            int r4 = r0.length     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            r3.write(r0, r2, r4)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            r3.finish()     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            byte[] r2 = r1.toByteArray()     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            r5.a((byte[]) r2)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            java.lang.String r2 = "gzip"
            r5.b((java.lang.String) r2)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r1)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r3)
            return
        L_0x004a:
            r0 = move-exception
            r2 = r3
            goto L_0x0060
        L_0x004d:
            r2 = r3
            goto L_0x0051
        L_0x004f:
            r0 = move-exception
            goto L_0x0060
        L_0x0051:
            r5.a((byte[]) r0)     // Catch:{ all -> 0x004f }
            java.lang.String r0 = "identity"
            r5.b((java.lang.String) r0)     // Catch:{ all -> 0x004f }
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r1)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r2)
            return
        L_0x0060:
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r1)
            com.yandex.metrica.impl.bk.a((java.io.Closeable) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.at.e():void");
    }

    /* access modifiers changed from: package-private */
    public c.a.C0024c[] w() {
        c.a.C0024c[] a2 = ap.a(this.p.m());
        if (a2 != null) {
            int length = a2.length;
            for (int i = 0; i < length; i++) {
                this.r += com.yandex.metrica.impl.ob.b.b((d) a2[i]);
            }
        }
        return a2;
    }

    private static c.a.C0023a[] a(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length <= 0) {
            return null;
        }
        c.a.C0023a[] aVarArr = new c.a.C0023a[length];
        Iterator<String> keys = jSONObject.keys();
        int i = 0;
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                c.a.C0023a aVar = new c.a.C0023a();
                aVar.b = next;
                aVar.c = jSONObject.getString(next);
                aVarArr[i] = aVar;
            } catch (JSONException unused) {
            }
            i++;
        }
        return aVarArr;
    }

    public boolean c() {
        boolean z = true;
        this.k = k() == 200;
        boolean z2 = k() == 400;
        if (!this.k && !z2) {
            z = false;
        }
        if (z) {
            c.a.d[] dVarArr = this.m.c;
            for (int i = 0; i < dVarArr.length; i++) {
                c.a.d dVar = dVarArr[i];
                this.o.a(this.q.get(i).longValue(), ap.a(dVar.c.d).a(), dVar.d.length);
                ap.a();
            }
            this.o.a(this.p.a().c());
        }
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public cq d() {
        return new ct().a(h());
    }

    public void f() {
        if (this.k) {
            j p2 = this.p.p();
            if (p2.b()) {
                for (int i = 0; i < this.t.f731a.size(); i++) {
                    p2.a(this.t.f731a.get(i), "Event sent");
                }
            }
        }
        this.t = null;
    }

    public boolean o() {
        boolean z = true;
        boolean z2 = (!r()) & (q() < 3);
        if (400 == k()) {
            z = false;
        }
        return z2 & z;
    }

    public long p() {
        return (q() + 1) % 3 != 0 ? ak.a.f721a : ak.a.b;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.yandex.metrica.impl.a$a} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:(1:13)(2:14|(1:39))|16|17|18|19|20|21|(1:42)(1:37)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0093 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099 A[EDGE_INSN: B:37:0x0099->B:23:0x0099 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0014 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.at.c x() {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            r3 = 0
            android.database.Cursor r4 = r12.z()     // Catch:{ Exception -> 0x00a7, all -> 0x00a2 }
        L_0x0014:
            boolean r5 = r4.moveToNext()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r5 == 0) goto L_0x0099
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r5.<init>()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.impl.utils.e.a(r4, r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            java.lang.String r6 = "id"
            java.lang.Long r6 = r5.getAsLong(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            long r6 = r6.longValue()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            java.lang.String r8 = "type"
            java.lang.Integer r8 = r5.getAsInteger(r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.impl.ob.bl r8 = com.yandex.metrica.impl.ob.bl.a(r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            boolean r9 = r12.a((long) r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r9 != 0) goto L_0x0014
            com.yandex.metrica.c$a$f r5 = com.yandex.metrica.impl.ap.a((android.content.ContentValues) r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = com.yandex.metrica.impl.ap.a((com.yandex.metrica.impl.ob.bl) r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.impl.ba r9 = r12.n     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            java.lang.String r9 = r9.w()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.c$a$d$b r5 = com.yandex.metrica.impl.ap.a(r9, r8, r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = r12.r     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r9 = 1
            r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r9 = com.yandex.metrica.impl.ob.b.c((int) r9, (long) r10)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = r8 + r9
            r12.r = r8     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r9 = 2
            int r9 = com.yandex.metrica.impl.ob.b.b((int) r9, (com.yandex.metrica.impl.ob.d) r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = r8 + r9
            r12.r = r8     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r9 = 250880(0x3d400, float:3.51558E-40)
            if (r8 >= r9) goto L_0x0099
            com.yandex.metrica.impl.at$b r5 = r12.a((long) r6, (com.yandex.metrica.c.a.d.b) r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r5 == 0) goto L_0x0014
            if (r3 != 0) goto L_0x0075
            com.yandex.metrica.impl.a$a r3 = r5.b     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            goto L_0x007d
        L_0x0075:
            com.yandex.metrica.impl.a$a r8 = r5.b     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            boolean r8 = r3.equals(r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r8 == 0) goto L_0x0099
        L_0x007d:
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r1.add(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.c$a$d r6 = r5.f730a     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r0.add(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0093 }
            com.yandex.metrica.impl.a$a r7 = r5.b     // Catch:{ JSONException -> 0x0093 }
            java.lang.String r7 = r7.f702a     // Catch:{ JSONException -> 0x0093 }
            r6.<init>(r7)     // Catch:{ JSONException -> 0x0093 }
            r2 = r6
        L_0x0093:
            boolean r5 = r5.c     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r5 != 0) goto L_0x0099
            goto L_0x0014
        L_0x0099:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r4)
            goto L_0x00aa
        L_0x009d:
            r0 = move-exception
            r3 = r4
            goto L_0x00a3
        L_0x00a0:
            r3 = r4
            goto L_0x00a7
        L_0x00a2:
            r0 = move-exception
        L_0x00a3:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r3)
            throw r0
        L_0x00a7:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r3)
        L_0x00aa:
            com.yandex.metrica.impl.at$c r3 = new com.yandex.metrica.impl.at$c
            r3.<init>(r0, r1, r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.at.x():com.yandex.metrica.impl.at$c");
    }

    private static int a(a.C0030a aVar) {
        try {
            c.a.C0023a[] a2 = a(new JSONObject(aVar.f702a));
            if (a2 == null) {
                return 0;
            }
            int i = 0;
            for (c.a.C0023a b2 : a2) {
                i += com.yandex.metrica.impl.ob.b.b(7, (d) b2);
            }
            return i;
        } catch (JSONException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x010b, code lost:
        r1 = r10;
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0165, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0166, code lost:
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0168, code lost:
        r12 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0165 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0015] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.at.b a(long r10, com.yandex.metrica.c.a.d.b r12) {
        /*
            r9 = this;
            com.yandex.metrica.c$a$d r0 = new com.yandex.metrica.c$a$d
            r0.<init>()
            r0.b = r10
            r0.c = r12
            int r12 = r12.d
            com.yandex.metrica.impl.ob.bl r12 = com.yandex.metrica.impl.ap.a((int) r12)
            r1 = 0
            r2 = 0
            android.database.Cursor r10 = r9.a((long) r10, (com.yandex.metrica.impl.ob.bl) r12)     // Catch:{ Exception -> 0x0170, all -> 0x016b }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x0168, all -> 0x0165 }
            r11.<init>()     // Catch:{ Exception -> 0x0168, all -> 0x0165 }
            r12 = r1
        L_0x001b:
            boolean r3 = r10.moveToNext()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r3 == 0) goto L_0x0149
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r3.<init>()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.utils.e.a(r10, r3)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r4 = "type"
            java.lang.Integer r4 = r3.getAsInteger(r4)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            boolean r5 = r9.v     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = com.yandex.metrica.impl.ap.b.a(r4, r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "custom_type"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.b((java.lang.Integer) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "name"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.a((java.lang.String) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "value"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.b((java.lang.String) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "time"
            java.lang.Long r5 = r3.getAsLong(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            long r5 = r5.longValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.a((long) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "number"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.a((int) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "cell_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.e(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "location_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.c((java.lang.String) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "wifi_network_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.d(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "error_environment"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.g(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "user_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.h(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "truncated"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.b((int) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "connection_type"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.c((int) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "cellular_connection_type"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.i(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "wifi_access_point"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.f(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.Integer r5 = r4.c()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r5 == 0) goto L_0x00e0
            com.yandex.metrica.c$a$d$a r4 = r4.e()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            goto L_0x00e1
        L_0x00e0:
            r4 = r1
        L_0x00e1:
            if (r4 == 0) goto L_0x001b
            com.yandex.metrica.impl.a$a r5 = new com.yandex.metrica.impl.a$a     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r6 = "app_environment"
            java.lang.String r6 = r3.getAsString(r6)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r7 = "app_environment_revision"
            java.lang.Long r3 = r3.getAsLong(r7)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            long r7 = r3.longValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r12 != 0) goto L_0x010e
            int r12 = r9.s     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            if (r12 >= 0) goto L_0x0109
            int r12 = a((com.yandex.metrica.impl.a.C0030a) r5)     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            r9.s = r12     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            int r3 = r9.r     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            int r3 = r3 + r12
            r9.r = r3     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
        L_0x0109:
            r12 = r5
            goto L_0x0116
        L_0x010b:
            r1 = r10
            r12 = r5
            goto L_0x0171
        L_0x010e:
            boolean r3 = r12.equals(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r3 != 0) goto L_0x0116
            r2 = 1
            goto L_0x0149
        L_0x0116:
            com.yandex.metrica.impl.utils.f r3 = r9.u     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            byte[] r5 = r4.f     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r6 = 245760(0x3c000, float:3.44383E-40)
            byte[] r3 = r3.a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            byte[] r5 = r4.f     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            boolean r5 = r5.equals(r3)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r5 != 0) goto L_0x0135
            r4.f = r3     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r4.k     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            byte[] r6 = r4.f     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r6 = r6.length     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r3 = r3.length     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r6 = r6 - r3
            int r5 = r5 + r6
            r4.k = r5     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
        L_0x0135:
            int r3 = r9.r     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r5 = 3
            int r5 = com.yandex.metrica.impl.ob.b.b((int) r5, (com.yandex.metrica.impl.ob.d) r4)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r3 = r3 + r5
            r9.r = r3     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r5 = 250880(0x3d400, float:3.51558E-40)
            if (r3 >= r5) goto L_0x0149
            r11.add(r4)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            goto L_0x001b
        L_0x0149:
            int r3 = r11.size()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r3 <= 0) goto L_0x0161
            int r1 = r11.size()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.c$a$d$a[] r1 = new com.yandex.metrica.c.a.d.C0025a[r1]     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.Object[] r11 = r11.toArray(r1)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.c$a$d$a[] r11 = (com.yandex.metrica.c.a.d.C0025a[]) r11     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r0.d = r11     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r10)
            goto L_0x0174
        L_0x0161:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r10)
            return r1
        L_0x0165:
            r11 = move-exception
            r1 = r10
            goto L_0x016c
        L_0x0168:
            r12 = r1
        L_0x0169:
            r1 = r10
            goto L_0x0171
        L_0x016b:
            r11 = move-exception
        L_0x016c:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
            throw r11
        L_0x0170:
            r12 = r1
        L_0x0171:
            com.yandex.metrica.impl.bk.a((android.database.Cursor) r1)
        L_0x0174:
            com.yandex.metrica.impl.at$b r10 = new com.yandex.metrica.impl.at$b
            r10.<init>(r0, r12, r2)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.at.a(long, com.yandex.metrica.c$a$d$b):com.yandex.metrica.impl.at$b");
    }

    /* access modifiers changed from: protected */
    public String y() {
        return this.n.a();
    }

    /* access modifiers changed from: protected */
    public Cursor z() {
        return this.o.a((Map<String, String>) this.b);
    }

    /* access modifiers changed from: protected */
    public Cursor a(long j, bl blVar) {
        return this.o.b(j, blVar);
    }

    static final class c {

        /* renamed from: a  reason: collision with root package name */
        final List<c.a.d> f731a;
        final List<Long> b;
        final JSONObject c;

        c(List<c.a.d> list, List<Long> list2, JSONObject jSONObject) {
            this.f731a = list;
            this.b = list2;
            this.c = jSONObject;
        }
    }

    static final class b {

        /* renamed from: a  reason: collision with root package name */
        final c.a.d f730a;
        final a.C0030a b;
        final boolean c;

        b(c.a.d dVar, a.C0030a aVar, boolean z) {
            this.f730a = dVar;
            this.b = aVar;
            this.c = z;
        }
    }

    public static a A() {
        return new a();
    }

    static class a {
        a() {
        }

        /* access modifiers changed from: package-private */
        public at a(t tVar) {
            return new at(tVar);
        }
    }
}
