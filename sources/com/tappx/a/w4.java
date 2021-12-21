package com.tappx.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class w4 {

    static class a extends WebChromeClient {
        a() {
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            j0.c(str2, new Object[0]);
            jsResult.confirm();
            return true;
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            j0.c(str2, new Object[0]);
            jsResult.confirm();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            j0.c(str2, new Object[0]);
            jsResult.confirm();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            j0.c(str2, new Object[0]);
            jsPromptResult.confirm();
            return true;
        }
    }

    public static void a(WebView webView) {
        webView.setWebChromeClient(new a());
    }

    public static void b(View view) {
        if (view != null && view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    public static View a(Context context, View view) {
        View a2 = a(context);
        return a2 != null ? a2 : a(view);
    }

    private static View a(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        return ((Activity) context).getWindow().getDecorView().findViewById(16908290);
    }

    private static View a(View view) {
        View rootView;
        if (view == null || (rootView = view.getRootView()) == null) {
            return null;
        }
        View findViewById = rootView.findViewById(16908290);
        return findViewById != null ? findViewById : rootView;
    }
}
