package com.yandex.metrica.impl.ob;

import android.content.Context;
import java.util.Map;

public class dc extends dd {
    private final dk c = new dk("init_event_pref_key", j());
    private final dk d = new dk("init_event_pref_key");
    private final dk e = new dk("first_event_pref_key", j());
    private final dk f = new dk("fitst_event_description_key", j());

    /* access modifiers changed from: protected */
    public String f() {
        return "_initpreferences";
    }

    public dc(Context context, String str) {
        super(context, str);
    }

    public void a() {
        a(this.c.b(), "DONE").k();
    }

    public String a(String str) {
        return this.b.getString(this.d.b(), str);
    }

    public String b(String str) {
        return this.b.getString(this.c.b(), str);
    }

    public String c(String str) {
        return this.b.getString(this.e.b(), str);
    }

    public void b() {
        a(this.d);
    }

    public void d(String str) {
        a(new dk("init_event_pref_key", str));
    }

    public void c() {
        a(this.c);
    }

    public void d() {
        a(this.e);
    }

    public String e(String str) {
        return this.b.getString(this.f.b(), str);
    }

    public void e() {
        a(this.f);
    }

    private void a(dk dkVar) {
        this.b.edit().remove(dkVar.b()).apply();
    }

    /* access modifiers changed from: package-private */
    public Map<String, ?> g() {
        return this.b.getAll();
    }

    static String f(String str) {
        return new dk("init_event_pref_key", str).b();
    }

    static String g(String str) {
        return str.replace("init_event_pref_key", "");
    }
}
