package com.tappx.a;

import android.net.Uri;
import com.tappx.a.a0;
import com.tappx.a.d0;
import com.tappx.a.f0;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class s extends d0<y1> {
    private static final String i = f.b("7lAm3cGZz4MMRzfjNT4s5w");
    private final String f;
    private final x g;
    private final String h;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final x f571a;

        public a(x xVar) {
            this.f571a = xVar;
        }

        /* access modifiers changed from: package-private */
        public s a(String str, String str2, f0.b<y1> bVar, f0.a aVar) {
            return new s(this.f571a, str, str2, bVar, aVar);
        }
    }

    s(x xVar, String str, String str2, f0.b<y1> bVar, f0.a aVar) {
        super(bVar, aVar);
        this.g = xVar;
        this.f = str + n.d();
        this.h = str2;
        a(false);
        a(new g0(10000, 1, 1.0f));
    }

    public byte[] a() {
        String str = this.h;
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            j0.d("mcDfyrZIyDh7srkDi3vhYS4jCqm7NCw5DOnMQ6j4pn8", new Object[0]);
            return null;
        }
    }

    public Map<String, String> c() {
        return h();
    }

    public d0.a d() {
        return d0.a.POST;
    }

    public String g() {
        Uri.Builder buildUpon = Uri.parse(this.f).buildUpon();
        buildUpon.appendQueryParameter(i, String.valueOf(System.currentTimeMillis() / 1000));
        return buildUpon.build().toString();
    }

    /* access modifiers changed from: protected */
    public f0<y1> a(c0 c0Var) {
        try {
            j0.d("zEfD4hGYqgGlREP0sIIg/hstxJ7mtJesC+XfeD//U5A", String.valueOf(c0Var.c), this.f);
            w1 a2 = this.g.a(c0Var);
            if (a2.f()) {
                return f0.a(new a0(a0.a.NO_FILL));
            }
            u1 u1Var = a2.b().get(0);
            if (u1Var instanceof y1) {
                return f0.a((y1) u1Var);
            }
            throw new c2();
        } catch (c2 unused) {
            return f0.a(new a0(a0.a.PARSE_ERROR));
        }
    }
}
