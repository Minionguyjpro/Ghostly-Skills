package com.startapp.android.publish.omsdk;

import android.content.Context;
import android.webkit.WebView;
import com.b.a.a.a.b.b;
import com.b.a.a.a.b.c;
import com.b.a.a.a.b.d;
import com.b.a.a.a.b.g;
import com.b.a.a.a.b.h;
import com.startapp.android.publish.GeneratedConstants;
import com.startapp.android.publish.adsCommon.f.f;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class a {
    private static String a() {
        return "";
    }

    public static b a(WebView webView) {
        if (!a(webView.getContext())) {
            return null;
        }
        return a(d.a(b(), webView, a()), false);
    }

    public static b a(Context context, AdVerification adVerification) {
        if (!a(context)) {
            return null;
        }
        if (adVerification == null) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "OMSDK: Verification details can't be null!", "", "");
            return null;
        }
        String a2 = b.a();
        List<VerificationDetails> adVerification2 = adVerification.getAdVerification();
        ArrayList arrayList = new ArrayList(adVerification2.size());
        for (VerificationDetails next : adVerification2) {
            URL a3 = a(context, next.getVerificationScriptUrl());
            if (a3 != null) {
                arrayList.add(h.a(next.getVendorKey(), a3, next.getVerificationParameters()));
            }
        }
        return a(d.a(b(), a2, arrayList, a()), true);
    }

    private static b a(d dVar, boolean z) {
        return b.a(c.a(com.b.a.a.a.b.f.NATIVE, z ? com.b.a.a.a.b.f.NATIVE : com.b.a.a.a.b.f.NONE, false), dVar);
    }

    private static g b() {
        return g.a("StartApp", GeneratedConstants.INAPP_VERSION);
    }

    private static URL a(Context context, String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            com.startapp.android.publish.adsCommon.f.d dVar = com.startapp.android.publish.adsCommon.f.d.EXCEPTION;
            f.a(context, dVar, "OMSDK: can't create URL - " + str, e.getMessage(), "");
            return null;
        }
    }

    private static boolean a(Context context) {
        try {
            if (com.b.a.a.a.a.b() || com.b.a.a.a.a.a(com.b.a.a.a.a.a(), context)) {
                return true;
            }
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "OMSDK: Failed to activate sdk.", "", "");
            return false;
        } catch (Exception e) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "OMSDK: Failed to activate sdk.", e.getMessage(), "");
            return false;
        }
    }
}
