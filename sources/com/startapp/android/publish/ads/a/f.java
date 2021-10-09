package com.startapp.android.publish.ads.a;

import android.os.Handler;
import android.webkit.WebView;
import com.mopub.common.AdType;

/* compiled from: StartAppSDK */
public class f extends c {
    public void a(WebView webView) {
        super.a(webView);
        if (g().equals(AdType.INTERSTITIAL)) {
            webView.setBackgroundColor(0);
        }
    }

    /* access modifiers changed from: protected */
    public void c(final WebView webView) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    webView.setBackgroundColor(0);
                } catch (Exception unused) {
                }
            }
        }, 1000);
    }
}
