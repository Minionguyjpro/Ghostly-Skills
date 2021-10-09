package com.integralads.avid.library.mopub.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.mopub.session.internal.trackingwebview.AvidTrackingWebViewManager;

public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager trackingWebViewManager;
    private final WebView webView;

    public InternalAvidManagedAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        WebView webView2 = new WebView(context.getApplicationContext());
        this.webView = webView2;
        this.trackingWebViewManager = new AvidTrackingWebViewManager(webView2);
    }

    public WebView getWebView() {
        return this.webView;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.trackingWebViewManager;
    }

    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.trackingWebViewManager.loadHTML();
    }
}
