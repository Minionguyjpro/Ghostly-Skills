package com.tappx.sdk.android;

import android.content.Context;
import com.tappx.a.o;
import com.tappx.a.o2;

public abstract class Tappx {
    public static TappxPrivacyManager getPrivacyManager(Context context) {
        return o2.a(context).a();
    }

    public static String getVersion() {
        return BuildConfig.SDK_VERSION;
    }

    protected static void sbmp(boolean z) {
        o.f535a = z;
        o.b = z;
    }
}
