package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.appupdate.g;
import com.google.android.play.core.common.a;
import com.google.android.play.core.common.c;
import com.google.android.play.core.internal.cb;
import com.google.android.play.core.internal.cc;
import com.google.android.play.core.internal.ce;
import com.google.android.play.core.splitinstall.p;
import java.util.concurrent.Executor;

public final class bh implements a {
    private ce<b> A;

    /* renamed from: a  reason: collision with root package name */
    private final l f1046a;
    private ce<Context> b;
    private ce<cv> c;
    private ce<au> d;
    private ce<bo> e;
    private ce<an> f;
    private ce<String> g = cc.b(new q(this.b));
    private ce<t> h = new cb();
    private ce<Executor> i;
    private ce<ca> j;
    private ce<ar> k;
    private ce<bj> l;
    private ce<dd> m;
    private ce<co> n;
    private ce<a> o;
    private ce<ct> p;
    private ce<cx> q;
    private ce<bc> r;
    private ce<cd> s;
    private ce<bl> t;
    private ce<be> u;
    private ce<Executor> v;
    private ce<cj> w;
    private ce<p> x;
    private ce<i> y;
    private ce<Object> z;

    /* synthetic */ bh(l lVar) {
        l lVar2 = lVar;
        this.f1046a = lVar2;
        p pVar = new p(lVar2);
        this.b = pVar;
        ce<cv> b2 = cc.b(new cp((ce<Context>) pVar, (char[]) null));
        this.c = b2;
        this.d = cc.b(new n(this.b, b2, (short[]) null));
        ce<bo> b3 = cc.b(bp.f1054a);
        this.e = b3;
        this.f = cc.b(new n(this.b, b3, (char[]) null));
        ce<Executor> b4 = cc.b(m.f1099a);
        this.i = b4;
        this.j = cc.b(new cb(this.d, this.h, this.e, b4));
        cb cbVar = new cb();
        this.k = cbVar;
        this.l = cc.b(new cb(this.d, this.h, (ce<ar>) cbVar, this.e, (byte[]) null));
        this.m = cc.b(new cp(this.d, (short[]) null));
        this.n = cc.b(new cp(this.d));
        ce<a> b5 = cc.b(c.b());
        this.o = b5;
        this.p = cc.b(new cu(this.d, this.h, this.j, this.i, this.e, b5));
        this.q = cc.b(new n(this.d, this.h, (int[]) null));
        ce<bc> b6 = cc.b(new cp(this.h, (byte[]) null));
        this.r = b6;
        ce<cd> b7 = cc.b(new cb(this.j, this.d, b6, this.o, (char[]) null));
        this.s = b7;
        this.t = cc.b(new bm(this.j, this.h, this.l, this.m, this.n, this.p, this.q, b7));
        this.u = cc.b(bf.f1044a);
        ce<Executor> b8 = cc.b(r.f1105a);
        this.v = b8;
        cb.b(this.k, cc.b(new bm(this.b, this.j, this.t, this.h, this.e, this.u, this.i, b8, (byte[]) null)));
        ce<cj> b9 = cc.b(new cu(this.g, this.k, this.e, this.b, this.c, this.i, (byte[]) null));
        this.w = b9;
        cb.b(this.h, cc.b(new o(this.b, this.f, b9)));
        ce<p> b10 = cc.b(g.b(this.b));
        this.x = b10;
        ce<i> b11 = cc.b(new j(this.d, this.h, this.k, b10, this.j, this.e, this.u, this.i, this.o));
        this.y = b11;
        this.z = cc.b(new n(b11, this.b));
        this.A = cc.b(new n(this.b, this.d, (byte[]) null));
    }

    public final void b(AssetPackExtractionService assetPackExtractionService) {
        assetPackExtractionService.f1020a = this.A.a();
    }

    public final void c(ExtractionForegroundService extractionForegroundService) {
        extractionForegroundService.f1022a = p.c(this.f1046a);
        extractionForegroundService.b = this.y.a();
    }
}
