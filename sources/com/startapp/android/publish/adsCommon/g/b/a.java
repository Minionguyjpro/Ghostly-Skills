package com.startapp.android.publish.adsCommon.g.b;

import android.content.Context;
import android.webkit.WebView;
import com.startapp.android.publish.adsCommon.g.a.c;

/* compiled from: StartAppSDK */
public final class a {
    public static void a(Context context, WebView webView, b bVar) {
        if (bVar == null) {
            bVar = new b(context);
        }
        c.a(webView, "mraid.SUPPORTED_FEATURES.CALENDAR", bVar.a());
        c.a(webView, "mraid.SUPPORTED_FEATURES.INLINEVIDEO", bVar.b());
        c.a(webView, "mraid.SUPPORTED_FEATURES.SMS", bVar.c());
        c.a(webView, "mraid.SUPPORTED_FEATURES.STOREPICTURE", bVar.d());
        c.a(webView, "mraid.SUPPORTED_FEATURES.TEL", bVar.e());
    }
}
