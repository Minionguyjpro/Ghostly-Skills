package com.tappx.a;

import android.net.Uri;
import com.tappx.a.a0;
import com.tappx.a.d0;
import com.tappx.a.f0;
import java.util.Locale;
import java.util.Map;

public class q extends d0<j2> {
    private static final String h = f.b("Atea2vjkWMaKJqXPDr3CPg");
    private final String f = n.b();
    private final x g;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final x f560a;

        public a(x xVar) {
            this.f560a = xVar;
        }

        /* access modifiers changed from: package-private */
        public q a(f0.b<j2> bVar, f0.a aVar) {
            return new q(this.f560a, bVar, aVar);
        }
    }

    q(x xVar, f0.b<j2> bVar, f0.a aVar) {
        super(bVar, aVar);
        this.g = xVar;
        a(true);
        a(new g0(10000, 1, 1.0f));
    }

    private String j() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return "ES";
        }
        return locale.getLanguage().toUpperCase(Locale.US);
    }

    /* access modifiers changed from: protected */
    public f0<j2> a(c0 c0Var) {
        try {
            return f0.a(this.g.b(c0Var));
        } catch (c2 unused) {
            return f0.a(new a0(a0.a.PARSE_ERROR));
        }
    }

    public byte[] a() {
        return null;
    }

    public Map<String, String> c() {
        return h();
    }

    public d0.a d() {
        return d0.a.GET;
    }

    public String g() {
        Uri.Builder buildUpon = Uri.parse(this.f).buildUpon();
        buildUpon.appendQueryParameter(h, j());
        return buildUpon.build().toString();
    }
}
