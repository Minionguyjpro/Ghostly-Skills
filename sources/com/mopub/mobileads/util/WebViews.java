package com.mopub.mobileads.util;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;

public class WebViews {
    public static void onPause(WebView webView, boolean z) {
        if (z) {
            webView.stopLoading();
            webView.loadUrl("");
        }
        webView.onPause();
    }

    public static void setDisableJSChromeClient(WebView webView) {
        webView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, str2);
                jsResult.confirm();
                return true;
            }

            public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, str2);
                jsResult.confirm();
                return true;
            }

            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, str2);
                jsPromptResult.confirm();
                return true;
            }

            public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
                MoPubLog.log(MoPubLog.SdkLogEvent.CUSTOM, str2);
                jsResult.confirm();
                return true;
            }
        });
    }

    public static void manageWebCookies() {
        CookieManager instance = CookieManager.getInstance();
        if (MoPub.canCollectPersonalInformation()) {
            instance.setAcceptCookie(true);
            CookieManager.setAcceptFileSchemeCookies(true);
            return;
        }
        instance.setAcceptCookie(false);
        CookieManager.setAcceptFileSchemeCookies(false);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.removeSessionCookies((ValueCallback) null);
            instance.removeAllCookies((ValueCallback) null);
            instance.flush();
            return;
        }
        instance.removeSessionCookie();
        instance.removeAllCookie();
    }

    public static void manageThirdPartyCookies(WebView webView) {
        Preconditions.checkNotNull(webView);
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(webView, MoPub.canCollectPersonalInformation());
        }
    }
}
