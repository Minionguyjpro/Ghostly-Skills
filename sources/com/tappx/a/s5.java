package com.tappx.a;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tappx.a.a6;
import com.tappx.a.h5;
import com.tappx.a.u5;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

public abstract class s5<T> implements Comparable<s5<T>> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final a6.a f579a;
    private final int b;
    private final String c;
    private final int d;
    private final Object e;
    private u5.a f;
    private Integer g;
    private t5 h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private w5 m;
    private h5.a n;
    private Object o;
    private b p;

    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f580a;
        final /* synthetic */ long b;

        a(String str, long j) {
            this.f580a = str;
            this.b = j;
        }

        public void run() {
            s5.this.f579a.a(this.f580a, this.b);
            s5.this.f579a.a(s5.this.toString());
        }
    }

    interface b {
        void a(s5<?> s5Var);

        void a(s5<?> s5Var, u5<?> u5Var);
    }

    public enum c {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public s5(int i2, String str, u5.a aVar) {
        this.f579a = a6.a.c ? new a6.a() : null;
        this.e = new Object();
        this.i = true;
        this.j = false;
        this.k = false;
        this.l = false;
        this.n = null;
        this.b = i2;
        this.c = str;
        this.f = aVar;
        a((w5) new k5());
        this.d = c(str);
    }

    private static int c(String str) {
        Uri parse;
        String host;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    public s5<?> a(w5 w5Var) {
        this.m = w5Var;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract u5<T> a(q5 q5Var);

    /* access modifiers changed from: protected */
    public abstract void a(T t);

    /* access modifiers changed from: protected */
    public z5 b(z5 z5Var) {
        return z5Var;
    }

    public h5.a d() {
        return this.n;
    }

    public String e() {
        String r = r();
        int g2 = g();
        if (g2 == 0 || g2 == -1) {
            return r;
        }
        return Integer.toString(g2) + '-' + r;
    }

    public Map<String, String> f() {
        return Collections.emptyMap();
    }

    public int g() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> h() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String i() {
        return "UTF-8";
    }

    @Deprecated
    public byte[] j() {
        Map<String, String> k2 = k();
        if (k2 == null || k2.size() <= 0) {
            return null;
        }
        return a(k2, l());
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Map<String, String> k() {
        return h();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public String l() {
        return i();
    }

    public c m() {
        return c.NORMAL;
    }

    public w5 n() {
        return this.m;
    }

    public Object o() {
        return this.o;
    }

    public final int p() {
        return n().a();
    }

    public int q() {
        return this.d;
    }

    public String r() {
        return this.c;
    }

    public boolean s() {
        boolean z;
        synchronized (this.e) {
            z = this.k;
        }
        return z;
    }

    public boolean t() {
        boolean z;
        synchronized (this.e) {
            z = this.j;
        }
        return z;
    }

    public String toString() {
        String str = "0x" + Integer.toHexString(q());
        StringBuilder sb = new StringBuilder();
        sb.append(t() ? "[X] " : "[ ] ");
        sb.append(r());
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(m());
        sb.append(" ");
        sb.append(this.g);
        return sb.toString();
    }

    public void u() {
        synchronized (this.e) {
            this.k = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void v() {
        b bVar;
        synchronized (this.e) {
            bVar = this.p;
        }
        if (bVar != null) {
            bVar.a(this);
        }
    }

    public final boolean w() {
        return this.i;
    }

    public final boolean x() {
        return this.l;
    }

    public void a(String str) {
        if (a6.a.c) {
            this.f579a.a(str, Thread.currentThread().getId());
        }
    }

    public s5<?> b(Object obj) {
        this.o = obj;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        t5 t5Var = this.h;
        if (t5Var != null) {
            t5Var.b(this);
        }
        if (a6.a.c) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new a(str, id));
                return;
            }
            this.f579a.a(str, id);
            this.f579a.a(toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        t5 t5Var = this.h;
        if (t5Var != null) {
            t5Var.a(this, i2);
        }
    }

    public s5<?> a(t5 t5Var) {
        this.h = t5Var;
        return this;
    }

    public s5<?> a(h5.a aVar) {
        this.n = aVar;
        return this;
    }

    public String c() {
        return "application/x-www-form-urlencoded; charset=" + i();
    }

    public void a() {
        synchronized (this.e) {
            this.j = true;
            this.f = null;
        }
    }

    private byte[] a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : map.entrySet()) {
                if (next.getKey() == null || next.getValue() == null) {
                    throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", new Object[]{next.getKey(), next.getValue()}));
                }
                sb.append(URLEncoder.encode((String) next.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode((String) next.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Encoding not supported: " + str, e2);
        }
    }

    public final s5<?> b(int i2) {
        this.g = Integer.valueOf(i2);
        return this;
    }

    public byte[] b() {
        Map<String, String> h2 = h();
        if (h2 == null || h2.size() <= 0) {
            return null;
        }
        return a(h2, i());
    }

    public final s5<?> b(boolean z) {
        this.l = z;
        return this;
    }

    public final s5<?> a(boolean z) {
        this.i = z;
        return this;
    }

    public void a(z5 z5Var) {
        u5.a aVar;
        synchronized (this.e) {
            aVar = this.f;
        }
        if (aVar != null) {
            aVar.a(z5Var);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(b bVar) {
        synchronized (this.e) {
            this.p = bVar;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(u5<?> u5Var) {
        b bVar;
        synchronized (this.e) {
            bVar = this.p;
        }
        if (bVar != null) {
            bVar.a(this, u5Var);
        }
    }

    /* renamed from: a */
    public int compareTo(s5<T> s5Var) {
        c m2 = m();
        c m3 = s5Var.m();
        return m2 == m3 ? this.g.intValue() - s5Var.g.intValue() : m3.ordinal() - m2.ordinal();
    }
}
