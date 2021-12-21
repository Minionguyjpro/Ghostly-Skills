package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.text.TextUtils;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.SSLSocketFactory;

public class co {

    /* renamed from: a  reason: collision with root package name */
    String f833a;
    /* access modifiers changed from: private */
    public String b;
    private SSLSocketFactory c;
    private fj d = new fj() {
        public String a() {
            return co.this.b;
        }
    };

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        static final co f835a = new co();
    }

    public static co a() {
        return a.f835a;
    }

    co() {
    }

    public synchronized SSLSocketFactory b() {
        return this.c;
    }

    public synchronized boolean c() {
        return this.c != null;
    }

    private static X509Certificate d() {
        try {
            String[] a2 = a.a();
            if (a2 == null || a2.length <= 0) {
                return null;
            }
            return ex.a(a2[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public synchronized void a(Context context, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "https://certificate.mobile.yandex.net/api/v1/pins";
        } else {
            str3 = str2 + "/api/v1/pins";
        }
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            if (!(c() && str3.equals(this.f833a))) {
                z = true;
            }
        }
        if (z) {
            this.b = str;
            this.f833a = str3;
            fd fdVar = new fd(this.d, true, true);
            X509Certificate d2 = d();
            if (d2 != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(d2);
                fdVar.a(this.f833a, arrayList);
                try {
                    this.c = new ez(new ew(context, fdVar)).a().getSocketFactory();
                } catch (Exception unused) {
                }
            }
        }
    }
}
