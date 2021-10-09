package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.ob.dr;
import com.yandex.metrica.impl.ob.ds;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.i;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.q;
import com.yandex.metrica.impl.ob.r;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.k;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

public class ag implements ae {
    private static final Executor c = new cp();
    private static final ExecutorService d = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public static final Map<String, t> e = new HashMap();
    private static final q f = new q();

    /* renamed from: a  reason: collision with root package name */
    private Context f715a;
    private MetricaService.b b;

    static /* synthetic */ boolean a(CounterConfiguration counterConfiguration) {
        return counterConfiguration == null;
    }

    public ag(Context context, MetricaService.b bVar) {
        this.f715a = context;
        this.b = bVar;
    }

    public void a() {
        new bd(this.f715a).a(this.f715a);
        k.a().a(this.f715a);
        GoogleAdvertisingIdGetter.b.f698a.a(this.f715a);
        cd cdVar = new cd(bp.a(this.f715a).d(), this.f715a.getPackageName());
        co.a().a(this.f715a, cdVar.b((String) null), cdVar.h((String) null));
        a(cdVar);
        ci.a().a(this.f715a);
    }

    public void a(Intent intent, int i) {
        b(intent, i);
    }

    public void a(Intent intent, int i, int i2) {
        b(intent, i2);
    }

    public void a(Intent intent) {
        dr.a(this.f715a).a();
        y.a(this.f715a).a((Object) this);
        ef.a(this.f715a).a();
    }

    public void b(Intent intent) {
        dr.a(this.f715a).a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x001a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.content.Intent r6) {
        /*
            r5 = this;
            android.net.Uri r6 = r6.getData()
            java.lang.String r6 = r6.getEncodedAuthority()
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r0 = e
            monitor-enter(r0)
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x0060 }
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r2 = e     // Catch:{ all -> 0x0060 }
            r1.<init>(r2)     // Catch:{ all -> 0x0060 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x0060 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0060 }
        L_0x001a:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x004d
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0060 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0060 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x0060 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0060 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0060 }
            com.yandex.metrica.impl.ob.t r2 = (com.yandex.metrica.impl.ob.t) r2     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x003f
            if (r2 == 0) goto L_0x003f
            boolean r4 = r3.startsWith(r6)     // Catch:{ all -> 0x0060 }
            if (r4 == 0) goto L_0x003d
            goto L_0x003f
        L_0x003d:
            r4 = 0
            goto L_0x0040
        L_0x003f:
            r4 = 1
        L_0x0040:
            if (r4 == 0) goto L_0x001a
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r4 = e     // Catch:{ all -> 0x0060 }
            r4.remove(r3)     // Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x001a
            r2.c()     // Catch:{ all -> 0x0060 }
            goto L_0x001a
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r6 = e
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x005f
            android.content.Context r6 = r5.f715a
            com.yandex.metrica.impl.ob.dr r6 = com.yandex.metrica.impl.ob.dr.a(r6)
            r6.b()
        L_0x005f:
            return
        L_0x0060:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0063:
            throw r6
        L_0x0064:
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ag.c(android.content.Intent):void");
    }

    public void a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        a(i, new h(str2, str, i2), bundle);
    }

    public void a(int i, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        a(i, h.b(bundle), bundle);
    }

    /* access modifiers changed from: package-private */
    public void a(cd cdVar) {
        String l = cdVar.l();
        if (TextUtils.isEmpty(l)) {
            g.a().a((Class<? extends i>) o.class);
            return;
        }
        try {
            g.a().b((i) new o(new ds(l)));
        } catch (JSONException unused) {
        }
    }

    private void b(Intent intent, int i) {
        if (intent != null) {
            intent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
            boolean z = false;
            if (!(intent == null || intent.getData() == null)) {
                h b2 = h.b(intent.getExtras());
                if (b2.n()) {
                    b2.a(intent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", p.a.EVENT_TYPE_UNDEFINED.a())).b(intent.getStringExtra("EXTRA_KEY_KEY_START_EVENT")).c("");
                }
                if (!b2.m() && !b2.n()) {
                    Bundle bundleExtra = intent.getBundleExtra("EXTRA_KEY_LIB_CFG");
                    if (bundleExtra == null) {
                        bundleExtra = intent.getExtras();
                    }
                    CounterConfiguration b3 = CounterConfiguration.b(bundleExtra);
                    if (b3 == null) {
                        z = true;
                    }
                    if (!z) {
                        String encodedAuthority = intent.getData().getEncodedAuthority();
                        b(b3, encodedAuthority);
                        b(b3);
                        y.a(this.f715a).a(b2.e());
                        try {
                            t tVar = new t(this.f715a, c, r.a(this.f715a, b3, (Integer) null, encodedAuthority), b3, f);
                            tVar.a(b2);
                            tVar.d();
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        this.b.a(i);
    }

    private final class a implements Runnable {
        private final int b;
        private final h c;
        private final Bundle d;
        private final Context e;

        a(Context context, h hVar, Bundle bundle, int i) {
            this.e = context.getApplicationContext();
            this.b = i;
            this.c = hVar;
            this.d = bundle;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c6, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r9 = this;
                android.os.Bundle r0 = r9.d
                com.yandex.metrica.CounterConfiguration r0 = com.yandex.metrica.CounterConfiguration.b((android.os.Bundle) r0)
                boolean r1 = com.yandex.metrica.impl.ag.a((com.yandex.metrica.CounterConfiguration) r0)
                if (r1 == 0) goto L_0x000d
                return
            L_0x000d:
                com.yandex.metrica.impl.ag r1 = com.yandex.metrica.impl.ag.this
                com.yandex.metrica.impl.h r2 = r9.c
                int r3 = r9.b
                com.yandex.metrica.impl.ob.r r1 = r1.a((com.yandex.metrica.impl.h) r2, (com.yandex.metrica.CounterConfiguration) r0, (int) r3)
                if (r1 != 0) goto L_0x001a
                return
            L_0x001a:
                java.lang.String r2 = r1.b()
                com.yandex.metrica.impl.ag.b((com.yandex.metrica.CounterConfiguration) r0, (java.lang.String) r2)
                java.util.Map r2 = com.yandex.metrica.impl.ag.e
                monitor-enter(r2)
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.ag.this     // Catch:{ all -> 0x00c7 }
                r3.b((com.yandex.metrica.CounterConfiguration) r0)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.ag.this     // Catch:{ all -> 0x00c7 }
                android.content.Context r4 = r9.e     // Catch:{ all -> 0x00c7 }
                java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x00c7 }
                java.lang.String r5 = r0.f()     // Catch:{ all -> 0x00c7 }
                boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00c7 }
                boolean r5 = r0.m()     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.y.a(r3.f715a).a(r3, r4, r5)     // Catch:{ all -> 0x00c7 }
                java.lang.String r3 = r0.j()     // Catch:{ all -> 0x00c7 }
                android.os.Bundle r4 = r9.d     // Catch:{ all -> 0x00c7 }
                java.lang.String r5 = "COUNTER_MIGRATION_CFG_OBJ"
                boolean r4 = r4.containsKey(r5)     // Catch:{ all -> 0x00c7 }
                if (r4 == 0) goto L_0x008a
                android.os.Bundle r4 = r9.d     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.CounterConfiguration r4 = a(r4)     // Catch:{ all -> 0x00c7 }
                if (r4 == 0) goto L_0x008a
                boolean r5 = r4.D()     // Catch:{ all -> 0x00c7 }
                if (r5 == 0) goto L_0x008a
                android.content.Context r5 = r9.e     // Catch:{ all -> 0x00c7 }
                int r6 = r9.b     // Catch:{ all -> 0x00c7 }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00c7 }
                r7 = 0
                com.yandex.metrica.impl.ob.r r5 = com.yandex.metrica.impl.ob.r.a(r5, r4, r6, r7)     // Catch:{ all -> 0x00c7 }
                java.util.Map r6 = com.yandex.metrica.impl.ag.e     // Catch:{ all -> 0x00c7 }
                java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x00c7 }
                boolean r6 = r6.containsKey(r8)     // Catch:{ all -> 0x00c7 }
                if (r6 != 0) goto L_0x008a
                com.yandex.metrica.CounterConfiguration r6 = new com.yandex.metrica.CounterConfiguration     // Catch:{ all -> 0x00c7 }
                r6.<init>((com.yandex.metrica.CounterConfiguration) r4)     // Catch:{ all -> 0x00c7 }
                r6.a((java.lang.String) r3)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.ag.this     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ob.t r3 = com.yandex.metrica.impl.ag.a(r3, r5, r6, r7)     // Catch:{ all -> 0x00c7 }
                r3.f()     // Catch:{ all -> 0x00c7 }
            L_0x008a:
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.ag.this     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.h r4 = r9.c     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ob.t r1 = com.yandex.metrica.impl.ag.a(r3, r1, r0, r4)     // Catch:{ all -> 0x00c7 }
                boolean r3 = com.yandex.metrica.impl.ag.a((com.yandex.metrica.impl.ob.t) r1)     // Catch:{ all -> 0x00c7 }
                if (r3 == 0) goto L_0x009a
                monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
                return
            L_0x009a:
                android.content.Context r3 = r9.e     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.y r3 = com.yandex.metrica.impl.y.a((android.content.Context) r3)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.h r4 = r9.c     // Catch:{ all -> 0x00c7 }
                android.location.Location r4 = r4.e()     // Catch:{ all -> 0x00c7 }
                r3.a((android.location.Location) r4)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.h r3 = r9.c     // Catch:{ all -> 0x00c7 }
                int r3 = r3.c()     // Catch:{ all -> 0x00c7 }
                boolean r3 = com.yandex.metrica.impl.p.a((int) r3)     // Catch:{ all -> 0x00c7 }
                if (r3 != 0) goto L_0x00b8
                r1.a((com.yandex.metrica.CounterConfiguration) r0)     // Catch:{ all -> 0x00c7 }
            L_0x00b8:
                com.yandex.metrica.impl.h r0 = r9.c     // Catch:{ all -> 0x00c7 }
                boolean r0 = com.yandex.metrica.impl.ag.a((com.yandex.metrica.impl.ob.t) r1, (com.yandex.metrica.impl.h) r0)     // Catch:{ all -> 0x00c7 }
                if (r0 != 0) goto L_0x00c5
                com.yandex.metrica.impl.h r0 = r9.c     // Catch:{ all -> 0x00c7 }
                r1.a((com.yandex.metrica.impl.h) r0)     // Catch:{ all -> 0x00c7 }
            L_0x00c5:
                monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
                return
            L_0x00c7:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ag.a.run():void");
        }

        private static CounterConfiguration a(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            try {
                return (CounterConfiguration) bundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(CounterConfiguration counterConfiguration) {
        if (TextUtils.isEmpty(counterConfiguration.h())) {
            c(counterConfiguration);
            return;
        }
        String d2 = ci.a().d();
        if (!(TextUtils.isEmpty(d2) || TextUtils.equals(counterConfiguration.h(), d2))) {
            c(counterConfiguration);
        }
    }

    private void c(CounterConfiguration counterConfiguration) {
        String g = ci.g(this.f715a, counterConfiguration.f());
        if (!TextUtils.isEmpty(g)) {
            counterConfiguration.e(g);
        }
    }

    /* access modifiers changed from: private */
    public static void b(CounterConfiguration counterConfiguration, String str) {
        if (TextUtils.isEmpty(counterConfiguration.f())) {
            counterConfiguration.c(str);
        }
    }

    /* access modifiers changed from: package-private */
    public r a(h hVar, CounterConfiguration counterConfiguration, int i) {
        if (!p.a(hVar)) {
            return r.a(this.f715a, counterConfiguration, Integer.valueOf(i), (String) null);
        }
        String l = hVar.l();
        boolean z = false;
        Iterator<ApplicationInfo> it = this.f715a.getPackageManager().getInstalledApplications(0).iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().packageName.equals(l)) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            return r.a(l);
        }
        return null;
    }

    private void a(int i, h hVar, Bundle bundle) {
        if (!hVar.n()) {
            d.execute(new a(this.f715a, hVar, bundle, i));
        }
    }

    public void b() {
        y.a(this.f715a).b((Object) this);
        ef.a(this.f715a).b();
    }

    static /* synthetic */ t a(ag agVar, r rVar, CounterConfiguration counterConfiguration, h hVar) {
        if (rVar == null) {
            return null;
        }
        t tVar = e.get(rVar.toString());
        if (tVar == null) {
            tVar = new t(agVar.f715a, c, rVar, counterConfiguration, f);
            if (hVar == null || !p.a(hVar)) {
                e.put(rVar.toString(), tVar);
            }
        } else {
            tVar.b(counterConfiguration);
        }
        return tVar;
    }

    static /* synthetic */ boolean a(t tVar) {
        return tVar == null || tVar.o();
    }

    static /* synthetic */ boolean a(t tVar, h hVar) {
        if (p.a.EVENT_TYPE_STARTUP.a() == hVar.c()) {
            tVar.e();
            return true;
        } else if (p.a.EVENT_TYPE_REFERRER_RECEIVED.a() != hVar.c()) {
            return false;
        } else {
            tVar.b(hVar);
            return true;
        }
    }
}
