package com.appsgeyser.multiTabApp.utils;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import com.appsgeyser.multiTabApp.controllers.WebContentController;

public class JavaScriptInjector {
    private final long INJECT_TRIES_INTERVAL = 200;
    public final String JS_INJECTION_PREFIX = "javascript:(function(){ ";
    public final String JS_INJECTION_SUFFIX = " })()";
    private final Context _context;
    /* access modifiers changed from: private */
    public Handler _handler = new Handler();
    /* access modifiers changed from: private */
    public final WebContentController _webContentController;
    /* access modifiers changed from: private */
    public final WebView _webView;
    private final StoppableRunnable injectContentRunnable = new StoppableRunnable() {
        public void run() {
            if (!this.stop) {
                String injectJSContent = JavaScriptInjector.this._webContentController.getInjectJSContent(JavaScriptInjector.this._webView.getUrl());
                String bannerInjectionJs = JavaScriptInjector.this._webContentController.getBannerInjectionJs();
                WebView access$100 = JavaScriptInjector.this._webView;
                access$100.loadUrl("javascript:(function(){ if(!window.jsInjectionDoneOnThisPage){" + injectJSContent + "}" + " })()");
                WebView access$1002 = JavaScriptInjector.this._webView;
                access$1002.loadUrl("javascript:(function(){ if(!window.jsInjectionDoneOnThisPage){" + bannerInjectionJs + "}" + " })()");
                JavaScriptInjector.this._webView.loadUrl("javascript:(function(){ if(!window.jsInjectionDoneOnThisPage) { AppsgeyserJSInjectorInterface.injectedSuccessfully(); window.jsInjectionDoneOnThisPage = true; } })()");
                JavaScriptInjector.this._handler.postDelayed(this, 200);
            }
        }
    };

    public void InjectJavaScript() {
    }

    private abstract class StoppableRunnable implements Runnable {
        public boolean stop;

        private StoppableRunnable() {
            this.stop = false;
        }
    }

    public JavaScriptInjector(WebView webView, WebContentController webContentController, Context context) {
        this._webView = webView;
        this._context = context;
        this._webContentController = webContentController;
        webView.addJavascriptInterface(this, "AppsgeyserJSInjectorInterface");
    }

    @JavascriptInterface
    public void injectedSuccessfully() {
        this._handler.removeCallbacks(this.injectContentRunnable);
        this.injectContentRunnable.stop = true;
    }

    @JavascriptInterface
    public void showToast(String str) {
        Toast.makeText(this._webView.getContext(), str, 0).show();
    }
}
