package com.startapp.android.publish.adsCommon.g.a;

import android.content.Context;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public final class c {
    public static void a(String str, WebView webView) {
        g.a("MraidJsFunctions", 3, "setPlacementType: " + str);
        i.a(webView, "mraid.setPlacementType", str);
    }

    public static void a(d dVar, WebView webView) {
        g.a("MraidJsFunctions", 3, "fireStateChangeEvent: " + dVar);
        i.a(webView, "mraid.fireStateChangeEvent", dVar.toString());
    }

    public static void a(Context context, int i, int i2, WebView webView) {
        g.a("MraidJsFunctions", 3, "setScreenSize " + i + AvidJSONUtil.KEY_X + i2);
        i.a(webView, "mraid.setScreenSize", Integer.valueOf(h.b(context, i)), Integer.valueOf(h.b(context, i2)));
    }

    public static void b(Context context, int i, int i2, WebView webView) {
        g.a("MraidJsFunctions", 3, "setMaxSize " + i + AvidJSONUtil.KEY_X + i2);
        i.a(webView, "mraid.setMaxSize", Integer.valueOf(h.b(context, i)), Integer.valueOf(h.b(context, i2)));
    }

    public static void a(Context context, int i, int i2, int i3, int i4, WebView webView) {
        g.a("MraidJsFunctions", 3, "setCurrentPosition [" + i + "," + i2 + "] (" + i3 + AvidJSONUtil.KEY_X + i4 + ")");
        i.a(webView, "mraid.setCurrentPosition", Integer.valueOf(h.b(context, i)), Integer.valueOf(h.b(context, i2)), Integer.valueOf(h.b(context, i3)), Integer.valueOf(h.b(context, i4)));
    }

    public static void b(Context context, int i, int i2, int i3, int i4, WebView webView) {
        g.a("MraidJsFunctions", 3, "setDefaultPosition [" + i + "," + i2 + "] (" + i3 + AvidJSONUtil.KEY_X + i4 + ")");
        i.a(webView, "mraid.setDefaultPosition", Integer.valueOf(h.b(context, i)), Integer.valueOf(h.b(context, i2)), Integer.valueOf(h.b(context, i3)), Integer.valueOf(h.b(context, i4)));
    }

    public static void a(WebView webView) {
        g.a("MraidJsFunctions", 3, "fireReadyEvent");
        i.a(webView, "mraid.fireReadyEvent", new Object[0]);
    }

    public static void a(WebView webView, boolean z) {
        g.a("MraidJsFunctions", 3, "fireViewableChangeEvent " + z);
        i.a(webView, "mraid.fireViewableChangeEvent", Boolean.valueOf(z));
    }

    public static void a(WebView webView, String str, boolean z) {
        g.a("MraidJsFunctions", 3, "setSupports feature: " + str + ", isSupported:" + z);
        i.a(webView, false, "mraid.setSupports", str, Boolean.valueOf(z));
    }
}
