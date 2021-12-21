package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.ai;
import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.de;
import com.yandex.metrica.impl.ob.dg;
import java.util.Map;

public class f extends ai {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final bz f762a;

    public f(bz bzVar) {
        this.f762a = bzVar;
    }

    /* access modifiers changed from: package-private */
    public SparseArray<ai.a> a() {
        return new SparseArray<ai.a>() {
            {
                put(46, new a(f.this.f762a));
            }
        };
    }

    /* access modifiers changed from: protected */
    public int a(dg dgVar) {
        return (int) this.f762a.c(-1);
    }

    /* access modifiers changed from: protected */
    public void a(dg dgVar, int i) {
        this.f762a.f((long) i);
        dgVar.c().k();
    }

    static class a implements ai.a {

        /* renamed from: a  reason: collision with root package name */
        private bz f764a;

        private static boolean a(long j, long j2, long j3) {
            return j != j3 && j2 == j3;
        }

        public a(bz bzVar) {
            this.f764a = bzVar;
        }

        public void a(Context context) {
            de deVar = new de(context);
            if (bk.a((Map) deVar.c())) {
                return;
            }
            if (this.f764a.a((String) null) == null || this.f764a.b((String) null) == null) {
                String b = deVar.b((String) null);
                if (a(b, this.f764a.b((String) null))) {
                    this.f764a.g(b);
                }
                String a2 = deVar.a();
                if (a(a2, this.f764a.a())) {
                    this.f764a.k(a2);
                }
                String a3 = deVar.a((String) null);
                if (a(a3, this.f764a.a((String) null))) {
                    this.f764a.f(a3);
                }
                String c = deVar.c((String) null);
                if (a(c, this.f764a.c((String) null))) {
                    this.f764a.h(c);
                }
                String d = deVar.d((String) null);
                if (a(d, this.f764a.d((String) null))) {
                    this.f764a.i(d);
                }
                String e = deVar.e((String) null);
                if (a(e, this.f764a.e((String) null))) {
                    this.f764a.j(e);
                }
                long a4 = deVar.a(-1);
                if (a(a4, this.f764a.a(-1), -1)) {
                    this.f764a.d(a4);
                }
                long b2 = deVar.b(-1);
                if (a(b2, this.f764a.b(-1), -1)) {
                    this.f764a.e(b2);
                }
                this.f764a.h();
                deVar.b().k();
            }
        }

        private static boolean a(String str, String str2) {
            return !TextUtils.isEmpty(str) && TextUtils.isEmpty(str2);
        }
    }
}
