package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class da {

    /* renamed from: a  reason: collision with root package name */
    private Context f843a;

    public da(Context context) {
        this.f843a = context;
    }

    public void a() {
        SharedPreferences a2 = dl.a(this.f843a, "_bidoptpreferences");
        if (a2.getAll().size() > 0) {
            String string = a2.getString(di.c.a(), (String) null);
            di diVar = new di(this.f843a);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(diVar.a((String) null))) {
                diVar.j(string).k();
                a2.edit().remove(di.c.a()).apply();
            }
            Map<String, ?> all = a2.getAll();
            if (all.size() > 0) {
                for (String next : a(all, di.d.a())) {
                    String string2 = a2.getString(new dk(di.d.a(), next).b(), (String) null);
                    di diVar2 = new di(this.f843a, next);
                    if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(diVar2.b((String) null))) {
                        diVar2.i(string2).k();
                    }
                }
            }
            a2.edit().clear().apply();
        }
    }

    private static List<String> a(Map<String, ?> map, String str) {
        ArrayList arrayList = new ArrayList();
        for (String next : map.keySet()) {
            if (next.startsWith(str)) {
                arrayList.add(next.replace(str, ""));
            }
        }
        return arrayList;
    }

    public void b() {
        bq d = bp.a(this.f843a).d();
        SharedPreferences a2 = dl.a(this.f843a, "_startupserviceinfopreferences");
        cd cdVar = new cd(d, (String) null);
        String string = a2.getString(di.c.a(), (String) null);
        if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(cdVar.a((String) null))) {
            cdVar.k(string).h();
            a2.edit().remove(di.c.a()).apply();
        }
        cd cdVar2 = new cd(d, this.f843a.getPackageName());
        boolean z = a2.getBoolean(di.e.a(), false);
        if (z) {
            cdVar2.e(z).h();
        }
        cd cdVar3 = new cd(d, (String) null);
        String string2 = a2.getString(di.f.a(), (String) null);
        if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(cdVar3.h((String) null))) {
            cdVar3.i(string2).h();
        }
        a(d, this.f843a.getPackageName());
        for (String a3 : a(a2.getAll(), di.d.a())) {
            a(d, a3);
        }
    }

    private void a(bq bqVar, String str) {
        cd cdVar = new cd(bqVar, str);
        di diVar = new di(this.f843a, str);
        String b = diVar.b((String) null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.j(b);
        }
        String a2 = diVar.a();
        if (!TextUtils.isEmpty(a2)) {
            cdVar.s(a2);
        }
        String d = diVar.d((String) null);
        if (!TextUtils.isEmpty(d)) {
            cdVar.p(d);
        }
        String f = diVar.f((String) null);
        if (!TextUtils.isEmpty(f)) {
            cdVar.n(f);
        }
        String g = diVar.g((String) null);
        if (!TextUtils.isEmpty(g)) {
            cdVar.m(g);
        }
        String c = diVar.c((String) null);
        if (!TextUtils.isEmpty(c)) {
            cdVar.o(c);
        }
        long a3 = diVar.a(-1);
        if (a3 != -1) {
            cdVar.b(a3);
        }
        String e = diVar.e((String) null);
        if (!TextUtils.isEmpty(e)) {
            cdVar.l(e);
        }
        cdVar.h();
        diVar.b();
    }
}
