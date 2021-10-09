package com.yandex.metrica.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.a;
import com.yandex.metrica.impl.ai;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cc;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.da;
import com.yandex.metrica.impl.ob.df;
import com.yandex.metrica.impl.ob.dg;
import com.yandex.metrica.impl.ob.dh;
import com.yandex.metrica.impl.ob.di;
import com.yandex.metrica.impl.ob.dj;
import com.yandex.metrica.impl.ob.dl;
import com.yandex.metrica.impl.ob.r;

public class bd extends ai {

    /* renamed from: a  reason: collision with root package name */
    private final cc f747a;

    public bd(Context context) {
        this.f747a = new cc(bp.a(context).b());
    }

    /* access modifiers changed from: package-private */
    public SparseArray<ai.a> a() {
        return new SparseArray<ai.a>() {
            {
                put(29, new a((byte) 0));
                put(39, new b((byte) 0));
                put(46, new c());
            }
        };
    }

    /* access modifiers changed from: protected */
    public int a(dg dgVar) {
        int a2 = dgVar.a();
        return a2 == -1 ? this.f747a.a(-1) : a2;
    }

    /* access modifiers changed from: protected */
    public void a(dg dgVar, int i) {
        this.f747a.b(i).h();
        dgVar.b().k();
    }

    private static class a implements ai.a {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public void a(Context context) {
            String a2 = new di(context).a((String) null);
            if (!TextUtils.isEmpty(a2) && TextUtils.isEmpty(ci.a().c(context, a2))) {
                di.b(context);
            }
        }
    }

    private static class b implements ai.a {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public void a(Context context) {
            df dfVar = new df(context, context.getPackageName());
            SharedPreferences a2 = dl.a(context, "_boundentrypreferences");
            String string = a2.getString(df.c.a(), (String) null);
            long j = a2.getLong(df.d.a(), -1);
            if (string != null && j != -1) {
                dfVar.a(new a.C0030a(string, j)).k();
                a2.edit().remove(df.c.a()).remove(df.d.a()).apply();
            }
        }
    }

    static class c implements ai.a {
        c() {
        }

        public void a(Context context) {
            cc ccVar = new cc(bp.a(context).b());
            dj djVar = new dj(context);
            if (djVar.a()) {
                ccVar.a(true);
                djVar.b();
            }
            dh dhVar = new dh(context, context.getPackageName());
            long a2 = dhVar.a(0);
            if (a2 != 0) {
                ccVar.a(a2);
            }
            dhVar.a();
            df dfVar = new df(context, r.a(context.getPackageName()).toString());
            CounterConfiguration.a b = dfVar.b();
            if (b != CounterConfiguration.a.UNDEFINED) {
                ccVar.a(b);
            }
            String b2 = dfVar.b((String) null);
            if (!TextUtils.isEmpty(b2)) {
                ccVar.b(b2);
            }
            dfVar.e().c().k();
            ccVar.h();
            da daVar = new da(context);
            daVar.a();
            daVar.b();
            ci.a().c(context, new cd(bp.a(context).d(), context.getPackageName()).a(""));
        }
    }
}
