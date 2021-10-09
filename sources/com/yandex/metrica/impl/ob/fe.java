package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

class fe {

    /* renamed from: a  reason: collision with root package name */
    private String f900a;
    private String b;
    private String c;

    fe(Context context) {
        try {
            this.f900a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            this.f900a = "0.0";
        }
        this.b = context.getFilesDir().getAbsolutePath();
        this.c = context.getPackageName();
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.f900a;
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public fs a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        return fg.a(list);
    }

    /* access modifiers changed from: package-private */
    public fs d() throws GeneralSecurityException, IOException {
        ArrayList arrayList = new ArrayList();
        for (String a2 : a.a()) {
            arrayList.add(ex.a(a2));
        }
        return fg.a((List<X509Certificate>) arrayList);
    }
}
