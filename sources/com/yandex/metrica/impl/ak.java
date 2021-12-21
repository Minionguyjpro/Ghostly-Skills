package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.cq;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class ak {
    protected String d;
    protected String e;
    protected int f = 1;
    protected byte[] g;
    protected int h;
    protected byte[] i;
    protected Map<String, List<String>> j;
    protected boolean k = false;
    protected int l = -1;

    static final class a {

        /* renamed from: a  reason: collision with root package name */
        static final long f721a = TimeUnit.SECONDS.toMillis(5);
        static final long b = TimeUnit.SECONDS.toMillis(15);
    }

    public abstract boolean b();

    public abstract boolean c();

    /* access modifiers changed from: package-private */
    public abstract cq d();

    public void f() {
    }

    public void g() {
    }

    public boolean n() {
        return false;
    }

    public abstract boolean o();

    public long p() {
        return 0;
    }

    public void e() {
        this.l++;
    }

    public String h() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public int i() {
        return this.f;
    }

    public byte[] j() {
        return this.g;
    }

    public void a(byte[] bArr) {
        this.f = 2;
        this.g = bArr;
    }

    public int k() {
        return this.h;
    }

    public void a(int i2) {
        this.h = i2;
    }

    public void b(byte[] bArr) {
        this.i = bArr;
    }

    /* access modifiers changed from: package-private */
    public Map<String, List<String>> l() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public void a(Map<String, List<String>> map) {
        this.j = map;
    }

    public String m() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String a() {
        return getClass().getName();
    }

    public int q() {
        return this.l;
    }

    public boolean r() {
        return this.k;
    }
}
