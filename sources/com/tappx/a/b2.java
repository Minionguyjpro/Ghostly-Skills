package com.tappx.a;

import com.facebook.ads.AdError;

public class b2 {

    /* renamed from: a  reason: collision with root package name */
    private String f378a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g = -1;
    private int h = -1;
    private String i;
    private String j;
    private boolean k;

    private int a(int i2, int i3, int i4, int i5) {
        return !(i2 >= i3 && i2 <= i4) ? i5 : i2;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.h;
    }

    public String d() {
        return this.f378a;
    }

    public String e() {
        return this.i;
    }

    public void f(String str) {
        this.j = str;
    }

    public String g() {
        return this.j;
    }

    public String h() {
        return this.e;
    }

    public String i() {
        return this.d;
    }

    public int j() {
        return this.g;
    }

    public boolean k() {
        return this.k;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.f378a = str;
    }

    public void d(String str) {
        this.i = str;
    }

    public void e(String str) {
        this.f = str;
    }

    public String f() {
        return this.f;
    }

    public void g(String str) {
        this.e = str;
    }

    public void h(String str) {
        this.d = str;
    }

    public void a(int i2) {
        this.h = a(i2, 0, 120, -1);
    }

    public void b(int i2) {
        this.g = a(i2, 1900, AdError.BROKEN_MEDIA_ERROR_CODE, -1);
    }

    public void a(boolean z) {
        this.k = z;
    }
}
