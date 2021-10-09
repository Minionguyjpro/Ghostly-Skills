package com.tappx.a;

import android.os.Build;
import android.webkit.WebView;
import com.tappx.a.s4;

public class x4 {
    public static void a(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onResume();
            return;
        }
        try {
            new s4.a(webView, "onResume").b().a();
        } catch (Exception unused) {
        }
    }

    public static void a(WebView webView, boolean z) {
        if (z) {
            webView.stopLoading();
            webView.loadUrl("");
        }
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onPause();
            return;
        }
        try {
            new s4.a(webView, "onPause").b().a();
        } catch (Exception unused) {
        }
    }
}
