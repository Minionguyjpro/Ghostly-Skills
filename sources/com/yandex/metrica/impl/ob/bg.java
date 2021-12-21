package com.yandex.metrica.impl.ob;

import android.os.SystemClock;
import android.text.TextUtils;
import com.yandex.metrica.impl.ba;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

public abstract class bg {

    /* renamed from: a  reason: collision with root package name */
    protected t f787a;
    protected bk b;
    protected long c = this.b.a(-1);
    protected long d;
    protected AtomicLong e = new AtomicLong(this.b.e(0));
    private boolean f = this.b.b(true);
    private volatile a g;

    /* access modifiers changed from: protected */
    public abstract bl a();

    /* access modifiers changed from: protected */
    public abstract int b();

    bg(t tVar, bk bkVar) {
        this.f787a = tVar;
        this.b = bkVar;
        this.d = bkVar.c(SystemClock.elapsedRealtime());
        this.b.d(this.d).a();
    }

    /* access modifiers changed from: package-private */
    public long c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return this.b.g(0) - TimeUnit.MILLISECONDS.toSeconds(this.d);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean f() {
        /*
            r10 = this;
            long r0 = r10.c
            r2 = 0
            r4 = 1
            r5 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto L_0x004f
            com.yandex.metrica.impl.ob.bk r0 = r10.b
            long r0 = r0.g(r2)
            long r6 = android.os.SystemClock.elapsedRealtime()
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            long r6 = r6 - r0
            long r0 = r10.g()
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 < 0) goto L_0x0032
            int r2 = r10.b()
            long r2 = (long) r2
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x0032
            long r2 = com.yandex.metrica.impl.ob.bh.c
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r0 = 0
            goto L_0x0033
        L_0x0032:
            r0 = 1
        L_0x0033:
            if (r0 != 0) goto L_0x004f
            com.yandex.metrica.impl.ob.bg$a r0 = r10.l()
            if (r0 == 0) goto L_0x0049
            com.yandex.metrica.impl.ob.t r1 = r10.f787a
            com.yandex.metrica.impl.ba r1 = r1.h()
            boolean r0 = r0.a(r1)
            if (r0 != 0) goto L_0x0049
            r0 = 1
            goto L_0x004a
        L_0x0049:
            r0 = 0
        L_0x004a:
            if (r0 == 0) goto L_0x004d
            goto L_0x004f
        L_0x004d:
            r0 = 0
            goto L_0x0050
        L_0x004f:
            r0 = 1
        L_0x0050:
            if (r0 != 0) goto L_0x0053
            return r4
        L_0x0053:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bg.f():boolean");
    }

    /* access modifiers changed from: package-private */
    public long g() {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.d);
    }

    /* access modifiers changed from: package-private */
    public synchronized void h() {
        this.b.h(-2147483648L).a();
        this.g = null;
    }

    /* access modifiers changed from: package-private */
    public void i() {
        this.b.h(SystemClock.elapsedRealtime() / 1000).a();
    }

    /* access modifiers changed from: package-private */
    public long j() {
        long andIncrement = this.e.getAndIncrement();
        this.b.f(this.e.get()).a();
        return andIncrement;
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return this.f && c() > 0;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (this.f != z) {
            this.f = z;
            this.b.a(z).a();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|(3:6|7|(1:9))|10|11) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0033 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.yandex.metrica.impl.ob.bg.a l() {
        /*
            r4 = this;
            com.yandex.metrica.impl.ob.bg$a r0 = r4.g
            if (r0 != 0) goto L_0x0038
            monitor-enter(r4)
            com.yandex.metrica.impl.ob.bg$a r0 = r4.g     // Catch:{ all -> 0x0035 }
            if (r0 != 0) goto L_0x0033
            com.yandex.metrica.impl.ob.t r0 = r4.f787a     // Catch:{ Exception -> 0x0033 }
            com.yandex.metrica.impl.ob.bn r0 = r0.i()     // Catch:{ Exception -> 0x0033 }
            long r1 = r4.c()     // Catch:{ Exception -> 0x0033 }
            com.yandex.metrica.impl.ob.bl r3 = r4.a()     // Catch:{ Exception -> 0x0033 }
            android.content.ContentValues r0 = r0.c(r1, r3)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = "report_request_parameters"
            java.lang.String r0 = r0.getAsString(r1)     // Catch:{ Exception -> 0x0033 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0033 }
            if (r1 != 0) goto L_0x0033
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0033 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0033 }
            com.yandex.metrica.impl.ob.bg$a r0 = new com.yandex.metrica.impl.ob.bg$a     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0033 }
            r4.g = r0     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            monitor-exit(r4)     // Catch:{ all -> 0x0035 }
            goto L_0x0038
        L_0x0035:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0035 }
            throw r0
        L_0x0038:
            com.yandex.metrica.impl.ob.bg$a r0 = r4.g
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bg.l():com.yandex.metrica.impl.ob.bg$a");
    }

    static class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f788a;
        private final String b;
        private final String c;
        private final String d;
        private final String e;
        private final String f;
        private final int g;

        a(JSONObject jSONObject) {
            this.f788a = jSONObject.optString("kitVer");
            this.b = jSONObject.optString("clientKitVer");
            this.c = jSONObject.optString("kitBuildNumber");
            this.d = jSONObject.optString("appVer");
            this.e = jSONObject.optString("appBuild");
            this.f = jSONObject.optString("osVer");
            this.g = jSONObject.optInt("osApiLev", -1);
        }

        /* access modifiers changed from: package-private */
        public boolean a(ba baVar) {
            return TextUtils.equals(baVar.h(), this.f788a) && TextUtils.equals(baVar.i(), this.b) && TextUtils.equals(baVar.k(), this.c) && TextUtils.equals(baVar.x(), this.d) && TextUtils.equals(baVar.z(), this.e) && TextUtils.equals(baVar.q(), this.f) && this.g == baVar.r();
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void d() {
        this.c = System.currentTimeMillis() / 1000;
        this.e.set(0);
        this.d = SystemClock.elapsedRealtime();
        this.g = null;
        this.b.i(this.c).h(SystemClock.elapsedRealtime() / 1000).d(this.d).f(this.e.get()).a();
        this.f787a.i().a(this.c, a());
        a(true);
    }
}
