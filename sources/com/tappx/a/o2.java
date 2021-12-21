package com.tappx.a;

import android.content.Context;
import com.tappx.sdk.android.TappxPrivacyManager;

public final class o2 {
    private static volatile o2 c;

    /* renamed from: a  reason: collision with root package name */
    private final Context f537a;
    private final m2 b = new m2();

    private o2(Context context) {
        this.f537a = context;
    }

    public static o2 a(Context context) {
        o2 o2Var = c;
        if (o2Var == null) {
            synchronized (o2.class) {
                o2Var = c;
                if (o2Var == null) {
                    c = new o2(context.getApplicationContext());
                    o2 o2Var2 = c;
                    return o2Var2;
                }
            }
        }
        return o2Var;
    }

    private c d() {
        return c.a(this.f537a);
    }

    private v e() {
        return d().l();
    }

    private t f() {
        return new t(d().j());
    }

    private i2 g() {
        return new i2(e());
    }

    private q2 h() {
        return new q2(d().j());
    }

    /* access modifiers changed from: package-private */
    public m2 b() {
        return this.b;
    }

    public n2 c() {
        return new n2(h(), new k2(e(), f()), b(), g());
    }

    public TappxPrivacyManager a() {
        return new r2(c());
    }
}
