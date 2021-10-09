package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.ad;
import com.yandex.metrica.impl.ba;
import com.yandex.metrica.impl.bj;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.j;
import java.io.Closeable;
import java.util.concurrent.Executor;

public class t implements u {

    /* renamed from: a  reason: collision with root package name */
    private boolean f938a;
    private boolean b;
    private final HandlerThread c;
    private final Handler d;
    private final Context e;
    private final r f;
    private ca g;
    private cc h;
    private by i;
    private cd j;
    private CounterConfiguration k;
    private final ba l;
    private bj m;
    private bn n;
    private v o;
    private a p;
    private q q;
    private long r;
    private long s;
    private int t;
    private int u;
    private volatile bi v;
    private final j w;
    private Runnable x;

    public bi a() {
        return this.v;
    }

    public t(Context context, Executor executor, r rVar, CounterConfiguration counterConfiguration, q qVar) {
        this(context, executor, rVar, counterConfiguration, qVar, new ba());
    }

    t(Context context, Executor executor, r rVar, CounterConfiguration counterConfiguration, q qVar, ba baVar) {
        this.f938a = false;
        this.b = false;
        this.w = new j();
        this.x = new Runnable() {
            public void run() {
                t.this.c();
            }
        };
        this.l = baVar;
        this.e = context.getApplicationContext();
        this.f = rVar;
        this.k = counterConfiguration;
        if (J()) {
            bq b2 = bp.a(this.e).b(l());
            this.g = new ca(b2);
            this.i = new by(b2);
        }
        bp a2 = bp.a(this.e);
        this.h = new cc(a2.b());
        this.j = new cd(a2.d(), l().b());
        int libraryApiLevel = YandexMetrica.getLibraryApiLevel();
        if (J()) {
            long j2 = (long) libraryApiLevel;
            if (this.g.d() < j2) {
                new s(this, new db(D())).a();
                this.g.u(j2).h();
            }
        }
        if (J()) {
            this.n = new bn(this, bp.a(this.e).a(l()));
            this.r = this.g.c(0);
            this.s = this.g.d(0);
            this.t = this.g.a(-1);
            this.u = bk.b(context, rVar.b());
            this.v = new bi(this, this.g);
            this.q = qVar;
            this.p = qVar.a(this, this.g);
            if (p().b()) {
                p().a("Read app environment for component %s. Value: %s", l().toString(), this.p.b().f702a);
            }
        }
        HandlerThread handlerThread = new HandlerThread("TaskHandler [" + rVar.b() + "]");
        this.c = handlerThread;
        handlerThread.start();
        this.d = new Handler(this.c.getLooper());
        this.l.a(this);
        this.m = new bj(this, executor);
        bn bnVar = this.n;
        if (bnVar != null) {
            bnVar.a((u) this);
        }
        this.o = new ab(new y(this));
    }

    public void a(h hVar) {
        if (p().b()) {
            p().a(hVar, "Event received on service");
        }
        if (bk.b(this.l.a())) {
            this.l.c(this);
            this.o.a(hVar);
        }
    }

    public void b(h hVar) {
        this.o.a(hVar);
    }

    private boolean J() {
        return !this.f.c();
    }

    public void c(h hVar) {
        b(hVar, this.v.e());
    }

    public void d(h hVar) {
        if (this.v.b(hVar)) {
            if (this.i.d()) {
                b(h.a(hVar, p.a.EVENT_TYPE_START), this.v.d());
            } else if (hVar.c() == p.a.EVENT_TYPE_FIRST_ACTIVATION.a()) {
                b(hVar, this.v.d());
                b(h.a(hVar, p.a.EVENT_TYPE_START), this.v.d());
                return;
            }
        }
        b(hVar, this.v.d());
    }

    private void b(h hVar, bj bjVar) {
        if (TextUtils.isEmpty(hVar.k())) {
            hVar.a(g());
        }
        this.n.a(hVar, bjVar, this.p.b());
        this.m.b();
    }

    public synchronized void a(CounterConfiguration counterConfiguration) {
        this.k = counterConfiguration;
        this.l.e(this);
    }

    public void b() {
        if ((this.n.a() >= ((long) this.k.c())) || this.f938a) {
            f();
            this.f938a = false;
        }
    }

    public synchronized void c() {
        this.b = true;
        bk.a((Closeable) this.m);
        bk.a((Closeable) this.n);
        this.d.removeCallbacksAndMessages((Object) null);
        this.c.quit();
    }

    public void d() {
        this.d.postDelayed(this.x, ad.f703a);
    }

    public synchronized void e() {
        this.m.c();
    }

    public synchronized void f() {
        this.m.a();
    }

    public String g() {
        return this.g.a((String) null);
    }

    public ba h() {
        return this.l;
    }

    public bn i() {
        return this.n;
    }

    public CounterConfiguration j() {
        return this.k;
    }

    public ResultReceiver k() {
        CounterConfiguration counterConfiguration = this.k;
        if (counterConfiguration != null) {
            return counterConfiguration.a();
        }
        return null;
    }

    public r l() {
        return this.f;
    }

    public Context m() {
        return this.e;
    }

    public Handler n() {
        return this.d;
    }

    public synchronized boolean o() {
        return this.b;
    }

    public void a(CounterConfiguration.a aVar) {
        this.g.a(aVar).h();
        if (this.e.getPackageName().equals(this.f.b())) {
            this.h.a(aVar).h();
        }
    }

    public j p() {
        CounterConfiguration counterConfiguration;
        if (!this.w.b() && (counterConfiguration = this.k) != null && counterConfiguration.s()) {
            this.w.a();
        }
        return this.w;
    }

    public void e(h hVar) {
        b(true);
        d(hVar);
        v();
    }

    public void f(h hVar) {
        d(hVar);
        K();
    }

    public void g(h hVar) {
        d(hVar);
        w();
    }

    public void q() {
        K();
    }

    public void r() {
        w();
    }

    public void h(h hVar) {
        this.p.a(hVar.j());
        a.C0030a b2 = this.p.b();
        if (this.q.a(b2, this.g) && p().b()) {
            p().a("Save new app environment for %s. Value: %s", l(), b2.f702a);
        }
    }

    public void s() {
        this.p.a();
        this.q.b(this.p.b(), this.g);
    }

    public void a(String str) {
        this.g.b(str).h();
    }

    public void b(String str) {
        this.h.b(str).h();
        this.j.s(str).h();
    }

    public String t() {
        return this.h.a((String) null);
    }

    public void u() {
        this.h.b().h();
    }

    /* access modifiers changed from: package-private */
    public void w() {
        Context context = this.e;
        int b2 = bk.b(context, context.getPackageName());
        this.t = b2;
        this.g.b(b2);
    }

    /* access modifiers changed from: package-private */
    public boolean y() {
        return this.t < this.u;
    }

    public boolean A() {
        return j().x() && x() && h().J() && h().M();
    }

    public boolean B() {
        return y() && h().K() && h().M();
    }

    public by C() {
        return this.i;
    }

    public dc D() {
        return new dc(this.e, this.f.a());
    }

    public cd E() {
        return this.j;
    }

    public ca F() {
        return this.g;
    }

    public void a(boolean z) {
        this.j.e(z).h();
    }

    public boolean H() {
        return !this.k.w() || !this.j.d();
    }

    public void b(boolean z) {
        this.f938a = z;
    }

    public void b(CounterConfiguration counterConfiguration) {
        this.k.a(counterConfiguration);
    }

    public ca I() {
        return this.g;
    }

    public void a(h hVar, bj bjVar) {
        b(h.a(hVar, p.a.EVENT_TYPE_ALIVE), bjVar);
    }

    public void v() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.r = currentTimeMillis;
        this.g.m(currentTimeMillis).h();
    }

    private void K() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.s = currentTimeMillis;
        this.g.n(currentTimeMillis).h();
    }

    /* access modifiers changed from: package-private */
    public boolean x() {
        return (System.currentTimeMillis() / 1000) - this.s > bh.b;
    }

    public boolean z() {
        return ((((System.currentTimeMillis() / 1000) - this.r) > bh.f789a ? 1 : (((System.currentTimeMillis() / 1000) - this.r) == bh.f789a ? 0 : -1)) > 0) && h().M();
    }

    public boolean G() {
        return this.h.a() == CounterConfiguration.a.TRUE && this.g.c() == CounterConfiguration.a.TRUE;
    }
}
