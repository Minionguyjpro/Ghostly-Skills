package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.yandex.metrica.impl.ob.cw;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.p;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h {

    /* renamed from: a  reason: collision with root package name */
    String f767a;
    String b;
    int c;
    int d;
    int e;
    private a f = new a((byte) 0);
    private String g;
    private String h;
    private String i;
    private Bundle j;
    private int k = 2;
    private String l;

    public h() {
    }

    public h(h hVar) {
        if (hVar != null) {
            this.f767a = hVar.a();
            this.b = hVar.b();
            this.c = hVar.c();
            this.d = hVar.d();
            this.g = hVar.k();
            this.i = hVar.l();
            this.h = hVar.i();
            this.f.f768a = hVar.e();
            this.f.b = hVar.f();
            this.f.c = hVar.h();
            this.j = hVar.j();
            this.e = hVar.o();
            this.k = hVar.p();
            this.l = hVar.q();
        }
    }

    public h(String str, String str2, int i2) {
        this.f767a = str2;
        this.c = i2;
        this.b = str;
    }

    public String a() {
        return this.f767a;
    }

    public h b(String str) {
        this.f767a = str;
        return this;
    }

    public String b() {
        return this.b;
    }

    public h c(String str) {
        this.b = str;
        return this;
    }

    public int c() {
        return this.c;
    }

    public h a(int i2) {
        this.c = i2;
        return this;
    }

    public int d() {
        return this.d;
    }

    public h b(int i2) {
        this.d = i2;
        return this;
    }

    public Location e() {
        return this.f.f768a;
    }

    /* access modifiers changed from: package-private */
    public h a(Location location) {
        this.f.f768a = location;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return this.f.b;
    }

    /* access modifiers changed from: package-private */
    public JSONArray g() {
        try {
            return new JSONArray(this.f.b);
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    /* access modifiers changed from: package-private */
    public h d(String str) {
        this.f.b = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Integer h() {
        return this.f.c;
    }

    /* access modifiers changed from: package-private */
    public h a(Integer num) {
        this.f.c = num;
        return this;
    }

    /* access modifiers changed from: package-private */
    public String i() {
        return this.h;
    }

    public Bundle j() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public h e(String str) {
        this.h = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    public h a(String str, String str2) {
        if (this.j == null) {
            this.j = new Bundle();
        }
        this.j.putString(str, str2);
        return this;
    }

    public String k() {
        return this.g;
    }

    public h a(String str) {
        this.g = str;
        return this;
    }

    public String l() {
        return this.i;
    }

    public h f(String str) {
        this.i = str;
        return this;
    }

    /* access modifiers changed from: protected */
    public h c(int i2) {
        this.e = i2;
        return this;
    }

    /* access modifiers changed from: protected */
    public h d(int i2) {
        this.k = i2;
        return this;
    }

    /* access modifiers changed from: protected */
    public h g(String str) {
        this.l = str;
        return this;
    }

    public boolean m() {
        return this.f767a == null;
    }

    public boolean n() {
        return p.a.EVENT_TYPE_UNDEFINED.a() == this.c;
    }

    public int o() {
        return this.e;
    }

    public int p() {
        return this.k;
    }

    public String q() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("CounterReport.Event", this.f767a);
        bundle2.putString("CounterReport.Value", this.b);
        bundle2.putInt("CounterReport.Type", this.c);
        bundle2.putInt("CounterReport.CustomType", this.d);
        bundle2.putString("CounterReport.Wifi", this.f.b);
        bundle2.putByteArray("CounterReport.GeoLocation", y.b(this.f.f768a));
        bundle2.putInt("CounterReport.TRUNCATED", this.e);
        bundle2.putInt("CounterReport.ConnectionType", this.k);
        bundle2.putString("CounterReport.CellularConnectionType", this.l);
        if (this.f.c != null) {
            bundle2.putInt("CounterReport.CellId", this.f.c.intValue());
        }
        String str = this.h;
        if (str != null) {
            bundle2.putString("CounterReport.Environment", str);
        }
        String str2 = this.g;
        if (str2 != null) {
            bundle2.putString("CounterReport.UserInfo", str2);
        }
        String str3 = this.i;
        if (str3 != null) {
            bundle2.putString("CounterReport.PackageName", str3);
        }
        Bundle bundle3 = this.j;
        if (bundle3 != null) {
            bundle2.putBundle("CounterReport.AppEnvironmentDiff", bundle3);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBundle("CounterReport.Object", bundle2);
        return bundle;
    }

    public static h b(Bundle bundle) {
        Bundle bundle2 = bundle.containsKey("CounterReport.Object") ? bundle.getBundle("CounterReport.Object") : new Bundle();
        Object obj = bundle2.get("CounterReport.TRUNCATED");
        int i2 = 0;
        if (obj != null) {
            if (obj instanceof Boolean) {
                i2 = ((Boolean) obj).booleanValue();
            } else if (obj instanceof Integer) {
                i2 = ((Integer) obj).intValue();
            }
        }
        h f2 = new h().a(bundle2.getInt("CounterReport.Type", p.a.EVENT_TYPE_UNDEFINED.a())).b(bundle2.getInt("CounterReport.CustomType")).a(y.a(bundle2.getByteArray("CounterReport.GeoLocation"))).c(bi.b(bundle2.getString("CounterReport.Value"), "")).a(bundle2.getString("CounterReport.UserInfo")).e(bundle2.getString("CounterReport.Environment")).d(bundle2.getString("CounterReport.Wifi")).a((Integer) bundle2.get("CounterReport.CellId")).b(bundle2.getString("CounterReport.Event")).f(bundle2.getString("CounterReport.PackageName"));
        f2.j = bundle2.getBundle("CounterReport.AppEnvironmentDiff");
        return f2.c(i2).d(bundle2.getInt("CounterReport.ConnectionType")).g(bundle2.getString("CounterReport.CellularConnectionType"));
    }

    public static h a(h hVar, p.a aVar) {
        h hVar2 = new h(hVar);
        hVar2.b(aVar.b());
        hVar2.a(aVar.a());
        return hVar2;
    }

    public static h a(t tVar, h hVar) {
        Context m = tVar.m();
        t a2 = new t(hVar.b()).a();
        try {
            if (tVar.G()) {
                a2.a(m);
            }
            if (tVar.h().H()) {
                a2.a(m, tVar.h().I());
            }
        } catch (Exception unused) {
        }
        h hVar2 = new h(hVar);
        hVar2.a(p.a.EVENT_TYPE_IDENTITY.a()).c(a2.d());
        return hVar2;
    }

    public static h a(h hVar, List<cw> list) {
        String str;
        h hVar2 = new h(hVar);
        try {
            JSONArray jSONArray = new JSONArray();
            for (cw next : list) {
                jSONArray.put(new JSONObject().put("name", next.b()).put("granted", next.a()));
            }
            str = new JSONObject().put("permissions", jSONArray).toString();
        } catch (JSONException unused) {
            str = "";
        }
        return hVar2.a(p.a.EVENT_TYPE_PERMISSIONS.a()).c(str);
    }

    public String toString() {
        return String.format(Locale.US, "[event: %s, type: %d, value: %s]", new Object[]{this.f767a, Integer.valueOf(this.c), this.b});
    }

    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        Location f768a;
        String b;
        Integer c;

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }
}
