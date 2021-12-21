package com.yandex.metrica;

import android.app.Application;
import android.content.Context;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.bo;

public final class YandexMetrica {
    public static int getLibraryApiLevel() {
        return 58;
    }

    public static void activate(Context context, String str) {
        bo.a(context, e.a(str).b());
    }

    public static void enableActivityAutoTracking(Application application) {
        bo.c().a(application);
    }

    public static void reportEvent(String str, String str2) {
        bo.c().reportEvent(str, str2);
    }

    public static IReporter getReporter(Context context, String str) {
        bk.a(str);
        bo.a(context);
        return bo.b().a(str);
    }
}
