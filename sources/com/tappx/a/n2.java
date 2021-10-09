package com.tappx.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.tappx.a.i2;
import com.tappx.a.k2;
import com.tappx.sdk.android.PrivacyConsentActivity;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class n2 {
    private static final long f = TimeUnit.DAYS.toSeconds(365);
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final q2 f527a;
    private final k2 b;
    /* access modifiers changed from: private */
    public final m2 c;
    private final i2 d;
    /* access modifiers changed from: private */
    public boolean e;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ WeakReference f528a;
        final /* synthetic */ Runnable b;

        a(WeakReference weakReference, Runnable runnable) {
            this.f528a = weakReference;
            this.b = runnable;
        }

        public void run() {
            n2.this.a((WeakReference<Context>) this.f528a);
            if (this.b != null) {
                n2.this.c.a(this.b);
            }
        }
    }

    class d implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f531a;
        final /* synthetic */ String b;

        d(Context context, String str) {
            this.f531a = context;
            this.b = str;
        }

        public void a() {
        }

        public void a(String str, String str2) {
            n2.this.a(this.f531a, this.b, str2);
        }

        public void b() {
        }
    }

    class e implements i2.c {
        e() {
        }

        public void a(boolean z) {
            if (z) {
                n2.this.f527a.b(false);
            }
            boolean unused = n2.this.e = false;
        }
    }

    public interface f {
        void a();

        void a(String str, String str2);

        void b();
    }

    n2(q2 q2Var, k2 k2Var, m2 m2Var, i2 i2Var) {
        this.f527a = q2Var;
        this.b = k2Var;
        this.c = m2Var;
        this.d = i2Var;
    }

    private void c(f fVar) {
        boolean m = this.f527a.m();
        Boolean d2 = this.f527a.d();
        if (Boolean.FALSE.equals(d2) && !m) {
            fVar.b();
        } else if (!Boolean.TRUE.equals(d2) || m) {
            a(fVar);
        } else {
            b(fVar);
        }
    }

    private void k() {
        long e2 = this.f527a.e();
        if (e2 > 0 && Math.abs(l() - e2) > f) {
            this.f527a.b();
        }
    }

    private long l() {
        return System.currentTimeMillis() / 1000;
    }

    public void d() {
        this.f527a.c(true);
    }

    public boolean e() {
        return this.f527a.l();
    }

    public void f() {
        a(p2.DENIED_USER);
    }

    public void g() {
        a(p2.GRANTED_USER);
    }

    public void h() {
        this.f527a.a();
        a(p2.DENIED_DEVELOPER);
    }

    public void i() {
        this.f527a.a();
        a(p2.GRANTED_DEVELOPER);
    }

    public boolean j() {
        if (!Boolean.TRUE.equals(this.f527a.d())) {
            return false;
        }
        return this.f527a.g().b();
    }

    class c implements k2.c {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ f f530a;

        c(f fVar) {
            this.f530a = fVar;
        }

        public void a(String str, String str2) {
            n2.this.f527a.a(true, str);
            this.f530a.a(str, str2);
        }

        public void b() {
            n2.this.f527a.a(false, (String) null);
            this.f530a.b();
        }

        public void a() {
            this.f530a.a();
        }
    }

    private void b(f fVar) {
        p2 g = this.f527a.g();
        String i = this.f527a.i();
        if (g != p2.MISSING_ANSWER) {
            fVar.b();
        } else if (i == null) {
            a(fVar);
        } else {
            fVar.a(i, (String) null);
        }
    }

    private void a(p2 p2Var) {
        if (this.f527a.g() != p2Var) {
            this.f527a.a(p2Var);
            this.f527a.c(false);
            this.f527a.b(true);
            this.f527a.a(l());
            a();
        }
    }

    class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ WeakReference f529a;

        b(WeakReference weakReference) {
            this.f529a = weakReference;
        }

        public void a(String str, String str2) {
            Context context = (Context) this.f529a.get();
            if (context == null) {
                n2.this.c.a();
            } else {
                n2.this.a(context, str, str2);
            }
        }

        public void b() {
            n2.this.c.a();
        }

        public void a() {
            n2.this.c.a();
        }
    }

    public void b(String str) {
        this.f527a.c(str);
    }

    public void a(boolean z) {
        this.f527a.a(z);
    }

    public String b() {
        String h = this.f527a.h();
        if (h == null || h.length() <= 5) {
            return null;
        }
        return h;
    }

    public s2 c() {
        return new s2(this.f527a.d(), this.f527a.g(), this.f527a.f(), this.f527a.k(), this.f527a.e());
    }

    public void a(Runnable runnable) {
        this.c.a(runnable);
    }

    public void a(Context context, Runnable runnable) {
        WeakReference weakReference = new WeakReference(context);
        k();
        this.c.a(new a(weakReference, runnable));
    }

    /* access modifiers changed from: private */
    public void a(WeakReference<Context> weakReference) {
        this.c.b();
        c(new b(weakReference));
    }

    /* access modifiers changed from: private */
    public void a(Context context, String str, String str2) {
        Intent a2 = l2.a(context, str, str2);
        if (!(context instanceof Activity)) {
            a2.addFlags(268435456);
        }
        try {
            context.startActivity(a2);
        } catch (Exception unused) {
            String name = PrivacyConsentActivity.class.getName();
            String simpleName = PrivacyConsentActivity.class.getSimpleName();
            j0.b(f.b("dfKcWOaG8KPoMfm5zts08Qlu05+R8BIzO3YcOMbimy7M7b66oYD1J20myZSpOoOWRYcUsjDmTjtwSPWh2TgTXA"), name, simpleName);
        }
    }

    private void a(f fVar) {
        this.b.a((k2.c) new c(fVar));
    }

    public void a(Context context) {
        Boolean d2 = this.f527a.d();
        boolean equals = Boolean.TRUE.equals(d2);
        String i = this.f527a.i();
        if (equals && i != null) {
            a(context, i, (String) null);
        } else if (!Boolean.FALSE.equals(d2)) {
            a((f) new d(context, i));
        }
    }

    public void a(String str) {
        this.f527a.b(str);
    }

    public void a(boolean z, int i, String str) {
        this.f527a.a(Boolean.valueOf(z));
        if (str != null) {
            this.f527a.a(str);
        }
        if (this.f527a.j() != i) {
            this.f527a.a(i);
            d();
        }
    }

    public void a() {
        p2 g;
        if (!this.e && this.f527a.c() && (g = this.f527a.g()) != p2.MISSING_ANSWER) {
            this.e = true;
            this.d.a(g, Math.max(l() - this.f527a.e(), 0), new e());
        }
    }
}
