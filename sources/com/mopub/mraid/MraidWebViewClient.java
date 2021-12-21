package com.mopub.mraid;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.mobileads.resource.MraidJavascript;
import java.io.ByteArrayInputStream;
import java.util.Locale;

public class MraidWebViewClient extends WebViewClient {
    private static final String MRAID_INJECTION_JAVASCRIPT = ("javascript:" + MraidJavascript.JAVASCRIPT_SOURCE);
    private static final String MRAID_JS = "mraid.js";

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (matchesInjectionUrl(str)) {
            return createMraidInjectionResponse();
        }
        return super.shouldInterceptRequest(webView, str);
    }

    /* access modifiers changed from: package-private */
    public boolean matchesInjectionUrl(String str) {
        return MRAID_JS.equals(Uri.parse(str.toLowerCase(Locale.US)).getLastPathSegment());
    }

    private WebResourceResponse createMraidInjectionResponse() {
        return new WebResourceResponse("text/javascript", "UTF-8", new ByteArrayInputStream(MRAID_INJECTION_JAVASCRIPT.getBytes()));
    }
}
