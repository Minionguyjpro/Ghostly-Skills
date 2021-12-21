package com.tappx.a;

import java.util.ArrayList;
import java.util.List;

public class w1 {

    /* renamed from: a  reason: collision with root package name */
    private int f617a = -1;
    private int b;
    private boolean c;
    private String d;
    private final List<u1> e = new ArrayList();

    public void a(int i) {
        this.f617a = i;
    }

    public List<u1> b() {
        return this.e;
    }

    public int c() {
        return this.f617a;
    }

    public String d() {
        return this.d;
    }

    public int e() {
        return this.b;
    }

    public boolean f() {
        return this.e.isEmpty();
    }

    public boolean a() {
        return this.c;
    }

    public void a(boolean z, int i, String str) {
        this.c = z;
        this.b = i;
        this.d = str;
    }
}
