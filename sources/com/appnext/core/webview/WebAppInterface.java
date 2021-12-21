package com.appnext.core.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    Context context;

    @JavascriptInterface
    public void destroy() {
    }

    @JavascriptInterface
    public int getAdCount() {
        return 0;
    }

    @JavascriptInterface
    public void gotoAppWall() {
    }

    @JavascriptInterface
    public String init() {
        return "";
    }

    @JavascriptInterface
    public String loadAds() {
        return "";
    }

    @JavascriptInterface
    public void notifyImpression(String str) {
    }

    @JavascriptInterface
    public void play() {
    }

    @JavascriptInterface
    public void videoPlayed() {
    }

    public WebAppInterface(Context context2) {
        this.context = context2;
    }

    public WebAppInterface() {
    }

    @JavascriptInterface
    public String getAdAt(int i) {
        new StringBuilder("getAdAt ").append(i);
        return "";
    }

    @JavascriptInterface
    public void destroy(String str) {
        new StringBuilder("destroy with error code: ").append(str);
    }

    @JavascriptInterface
    public void postView(String str) {
        new StringBuilder("postView: ").append(str);
    }

    @JavascriptInterface
    public void openStore(String str) {
        new StringBuilder("openStore: ").append(str);
    }

    @JavascriptInterface
    public String filterAds(String str) {
        new StringBuilder("filterAds: ").append(str);
        return str;
    }

    @JavascriptInterface
    public void openLink(String str) {
        new StringBuilder("openLink ").append(str);
    }

    @JavascriptInterface
    public void jsError(String str) {
        new StringBuilder("jsError ").append(str);
    }

    @JavascriptInterface
    public void openResultPage(String str) {
        new StringBuilder("openResultPage ").append(str);
    }

    @JavascriptInterface
    public void logSTP(String str, String str2) {
        new StringBuilder("logSTP ").append(str);
    }
}
