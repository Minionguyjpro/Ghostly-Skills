package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.google.android.gms.common.util.PlatformVersion;

@zzadh
final class zzasy {
    private static Boolean zzdfk;

    private zzasy() {
    }

    static void zza(WebView webView, String str) {
        if (!PlatformVersion.isAtLeastKitKat() || !zzb(webView)) {
            String valueOf = String.valueOf(str);
            webView.loadUrl(valueOf.length() != 0 ? "javascript:".concat(valueOf) : new String("javascript:"));
            return;
        }
        webView.evaluateJavascript(str, (ValueCallback) null);
    }

    private static boolean zzb(WebView webView) {
        boolean booleanValue;
        synchronized (zzasy.class) {
            if (zzdfk == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", (ValueCallback) null);
                    zzdfk = true;
                } catch (IllegalStateException unused) {
                    zzdfk = false;
                }
            }
            booleanValue = zzdfk.booleanValue();
        }
        return booleanValue;
    }
}
