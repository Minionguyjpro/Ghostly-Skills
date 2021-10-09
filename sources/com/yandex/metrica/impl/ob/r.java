package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;

public class r {

    /* renamed from: a  reason: collision with root package name */
    private boolean f932a;
    private final String b;
    private final String c;

    public r(String str, String str2, boolean z) {
        this.f932a = z;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return !this.f932a && !bk.b(this.c);
    }

    public boolean d() {
        return this.f932a;
    }

    public String toString() {
        String str = this.b;
        if (this.f932a) {
            return str;
        }
        return str + "_" + this.c;
    }

    public static r a(Context context, CounterConfiguration counterConfiguration, Integer num, String str) {
        String f = counterConfiguration.f();
        if (!bi.a(f)) {
            str = f;
        } else if (num != null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(num.intValue());
            str = (packagesForUid == null || packagesForUid.length <= 0) ? null : packagesForUid[0];
        }
        if (!bi.a(str)) {
            return new r(str, bk.a(context, counterConfiguration, str), counterConfiguration.C());
        }
        return null;
    }

    public static r a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new r(str, (String) null, true);
        }
        return null;
    }
}
