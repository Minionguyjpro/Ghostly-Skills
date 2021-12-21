package com.startapp.android.publish.ads.video.c.a.a;

import com.startapp.common.a.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f120a;
    private Integer b;
    private Integer c;
    private Integer d;
    private Integer e;
    private Integer f;
    private Integer g;
    private String h;
    private Integer i;
    private List<d> j;
    private String k;
    private List<String> l;
    private List<String> m;

    private boolean a(int i2) {
        return i2 > 0;
    }

    public void a(String str) {
        this.f120a = str;
    }

    public Integer a() {
        return this.b;
    }

    public void a(Integer num) {
        this.b = num;
    }

    public Integer b() {
        return this.c;
    }

    public void b(Integer num) {
        this.c = num;
    }

    public Integer c() {
        return this.d;
    }

    public void c(Integer num) {
        this.d = num;
    }

    public Integer d() {
        return this.e;
    }

    public void d(Integer num) {
        this.e = num;
    }

    public void e(Integer num) {
        this.f = num;
    }

    public void f(Integer num) {
        this.g = num;
    }

    public void b(String str) {
        this.h = str;
    }

    public void g(Integer num) {
        this.i = num;
    }

    public List<d> e() {
        if (this.j == null) {
            this.j = new ArrayList();
        }
        return this.j;
    }

    public void c(String str) {
        this.k = str;
    }

    public List<String> f() {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        return this.l;
    }

    public List<String> g() {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        return this.m;
    }

    public boolean h() {
        Integer b2 = b();
        Integer a2 = a();
        if (b2 == null || a2 == null || !a(b2.intValue()) || !a(a2.intValue())) {
            g.a("VASTIcon", 3, "Validator error: VASTIcon invalid size");
            return false;
        }
        Integer c2 = c();
        Integer d2 = d();
        if (c2 == null || d2 == null || !a(c2.intValue()) || !a(d2.intValue())) {
            g.a("VASTIcon", 3, "Validator error: VASTIcon invalid position");
            return false;
        } else if (e().size() != 0) {
            return true;
        } else {
            g.a("VASTIcon", 3, "Validator error: VASTIcon no resources");
            return false;
        }
    }
}
