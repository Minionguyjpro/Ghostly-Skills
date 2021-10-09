package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.utils.g;
import java.util.List;

public class cd extends cb {

    /* renamed from: a  reason: collision with root package name */
    static final dk f817a = new dk("PREF_KEY_UID_");
    static final dk b = new dk("PREF_KEY_DEVICE_ID_");
    private static final dk c = new dk("PREF_KEY_HOST_URL_");
    private static final dk d = new dk("PREF_KEY_HOST_URLS_FROM_STARTUP");
    private static final dk e = new dk("PREF_KEY_HOST_URLS_FROM_CLIENT");
    private static final dk f = new dk("PREF_KEY_REPORT_URL_");
    private static final dk g = new dk("PREF_KEY_GET_AD_URL");
    private static final dk h = new dk("PREF_KEY_REPORT_AD_URL");
    private static final dk i = new dk("PREF_KEY_STARTUP_OBTAIN_TIME_");
    private static final dk j = new dk("PREF_KEY_STARTUP_ENCODED_CLIDS_");
    private static final dk k = new dk("PREF_KEY_DISTRIBUTION_REFERRER_");
    private static final dk l = new dk("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");
    private static final dk m = new dk("PREF_KEY_PINNING_UPDATE_URL");
    private static final dk n = new dk("PREF_KEY_EASY_COLLECTING_ENABLED_");
    private static final dk o = new dk("PREF_KEY_COLLECTING_PACKAGE_INFO_ENABLED_");
    private static final dk p = new dk("PREF_KEY_PERMISSIONS_COLLECTING_ENABLED_");
    private static final dk q = new dk("PREF_KEY_FEATURES_COLLECTING_ENABLED_");
    private static final dk r = new dk("SOCKET_CONFIG_");
    private static final dk s = new dk("LAST_STARTUP_REQUEST_CLIDS");
    private static final dk t = new dk("LAST_STARTUP_CLIDS_SAVE_TIME");
    private dk A;
    private dk B;
    private dk C;
    private dk D;
    private dk E;
    private dk F;
    private dk G;
    private dk H;
    private dk I;
    private dk J;
    private dk K;
    private dk L;
    private dk M;
    private dk u;
    private dk v;
    private dk w;
    private dk x;
    private dk y;
    private dk z;

    public cd(bq bqVar, String str) {
        super(bqVar, str);
    }

    /* access modifiers changed from: protected */
    public void f() {
        super.f();
        this.u = new dk(b.a());
        this.v = q(f817a.a());
        this.w = q(c.a());
        this.x = q(d.a());
        this.y = q(e.a());
        this.z = q(f.a());
        this.A = q(g.a());
        this.B = q(h.a());
        this.C = q(i.a());
        this.D = q(j.a());
        this.E = q(k.a());
        this.F = q(l.a());
        this.G = q(n.a());
        this.H = q(o.a());
        this.I = q(p.a());
        this.J = q(q.a());
        this.L = q(s.a());
        this.M = q(t.a());
        this.K = q(r.a());
    }

    public long a(long j2) {
        return b(this.C.b(), j2);
    }

    public String a(String str) {
        return b(this.u.b(), str);
    }

    public String b(String str) {
        return b(this.v.b(), str);
    }

    public String c(String str) {
        return b(this.w.b(), str);
    }

    public List<String> a() {
        return g.b(b(this.x.b(), (String) null));
    }

    public List<String> b() {
        return g.b(b(this.y.b(), (String) null));
    }

    public String d(String str) {
        return b(this.D.b(), str);
    }

    public String e(String str) {
        return b(this.z.b(), str);
    }

    public String f(String str) {
        return b(this.A.b(), str);
    }

    public String g(String str) {
        return b(this.B.b(), str);
    }

    public String c() {
        return b(this.E.b(), (String) null);
    }

    public boolean d() {
        return b(this.F.b(), true);
    }

    public boolean e() {
        return b(this.G.b(), false);
    }

    public boolean i() {
        return b(this.H.b(), false);
    }

    public boolean j() {
        return b(this.I.b(), false);
    }

    public boolean k() {
        return b(this.J.b(), false);
    }

    public String l() {
        return b(this.K.b(), (String) null);
    }

    public String h(String str) {
        return b(m.b(), str);
    }

    public cd i(String str) {
        return (cd) a(m.b(), str);
    }

    public cd j(String str) {
        return (cd) a(this.v.b(), str);
    }

    public cd k(String str) {
        return (cd) a(this.u.b(), str);
    }

    public cd l(String str) {
        return (cd) a(this.z.b(), str);
    }

    public cd m(String str) {
        return (cd) a(this.B.b(), str);
    }

    public cd n(String str) {
        return (cd) a(this.A.b(), str);
    }

    public cd o(String str) {
        return (cd) a(this.w.b(), str);
    }

    public cd a(List<String> list) {
        return (cd) a(this.x.b(), g.a(list));
    }

    public cd b(List<String> list) {
        return (cd) a(this.y.b(), g.a(list));
    }

    public cd a(boolean z2) {
        return (cd) a(this.G.b(), z2);
    }

    public cd b(boolean z2) {
        return (cd) a(this.H.b(), z2);
    }

    public cd c(boolean z2) {
        return (cd) a(this.I.b(), z2);
    }

    public cd d(boolean z2) {
        return (cd) a(this.J.b(), z2);
    }

    public cd b(long j2) {
        return (cd) a(this.C.b(), j2);
    }

    public cd p(String str) {
        return (cd) a(this.D.b(), str);
    }

    public cd s(String str) {
        return (cd) a(this.E.b(), str);
    }

    public cd e(boolean z2) {
        return (cd) a(this.F.b(), z2);
    }

    public cd t(String str) {
        return (cd) a(this.K.b(), str);
    }

    public String m() {
        return b(this.L.b(), (String) null);
    }

    public long n() {
        return b(this.M.b(), -1);
    }

    public cd u(String str) {
        return (cd) a(this.L.b(), str).a(this.M.b(), System.currentTimeMillis());
    }
}
