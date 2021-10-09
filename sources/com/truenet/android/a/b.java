package com.truenet.android.a;

import a.a.b.b.h;
import android.content.res.Configuration;
import android.os.Build;
import java.util.Locale;

/* compiled from: StartAppSDK */
public final class b {
    public static final Locale a(Configuration configuration) {
        h.b(configuration, "$receiver");
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }
}
