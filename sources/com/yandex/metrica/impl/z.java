package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.j;
import java.util.Map;

public class z extends b {
    z(Context context, e eVar, ay ayVar) {
        super(context, eVar.getApiKey(), ayVar, new aw());
        this.b.a(new an(eVar.getPreloadInfo()));
    }

    /* access modifiers changed from: package-private */
    public void a(dw dwVar) {
        super.a(dwVar);
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        super.a(jVar);
    }

    public void reportEvent(String str) {
        super.reportEvent(str);
    }

    public void reportEvent(String str, String str2) {
        super.reportEvent(str, str2);
        c(str, str2);
    }

    public void reportEvent(String str, Map<String, Object> map) {
        String str2;
        super.reportEvent(str, map);
        if (map == null) {
            str2 = null;
        } else {
            str2 = map.toString();
        }
        c(str, str2);
    }

    private static void c(String str, String str2) {
        StringBuilder sb = new StringBuilder("Event received: ");
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(". With value: ");
            sb.append(str2);
        }
        j.f().a(sb.toString());
    }

    public void reportError(String str, Throwable th) {
        super.reportError(str, th);
        j.f().a("Error received: %s", str);
    }

    public void a(Application application) {
        bk.a((Object) application, "Application");
        if (Build.VERSION.SDK_INT >= 14) {
            j.f().a("Enable activity auto tracking");
            application.registerActivityLifecycleCallbacks(new m(this));
            return;
        }
        j.f().b("Could not enable activity auto tracking. API level should be more than 14 (ICE_CREAM_SANDWICH)");
    }

    public void b(Activity activity) {
        b(d(activity));
    }

    public void c(Activity activity) {
        c(d(activity));
    }

    /* access modifiers changed from: package-private */
    public String d(Activity activity) {
        if (activity != null) {
            return activity.getClass().getSimpleName();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(e eVar, boolean z) {
        this.b.b().a(eVar);
        d(this.b.b().l());
        if (z) {
            b();
        }
        b(eVar.j());
        a(eVar.getErrorEnvironment());
    }

    public void c(boolean z) {
        this.b.b().a(z);
    }

    public void d(boolean z) {
        this.c.a(z, this.b);
    }

    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            j.f().b("Invalid App Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.b(str, str2);
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            j.f().b("Invalid Error Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.a(str, str2);
    }

    public boolean f() {
        return this.b.b().k();
    }
}
