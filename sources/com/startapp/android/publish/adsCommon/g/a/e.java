package com.startapp.android.publish.adsCommon.g.a;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.startapp.android.publish.adsCommon.g.d.a;
import com.startapp.android.publish.adsCommon.g.d.b;
import com.startapp.common.a.g;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

/* compiled from: StartAppSDK */
public class e extends WebViewClient {

    /* renamed from: a  reason: collision with root package name */
    private b f249a;
    private boolean b = false;

    public e(b bVar) {
        this.f249a = bVar;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        return str != null && str.startsWith("mraid://");
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        g.a("MraidWebViewClient", 3, "shouldOverrideUrlLoading: " + str);
        if (a(str)) {
            return c(str);
        }
        return this.f249a.open(str);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        g.a("MraidWebViewClient", 3, "shouldInterceptRequest: " + str);
        if (this.b || !b(str)) {
            return super.shouldInterceptRequest(webView, str);
        }
        this.b = true;
        return a();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        g.a("MraidWebViewClient", 6, "onReceivedError: " + str);
        super.onReceivedError(webView, i, str, str2);
    }

    public boolean b(String str) {
        try {
            return "mraid.js".equals(Uri.parse(str.toLowerCase(Locale.US)).getLastPathSegment());
        } catch (Exception e) {
            g.a("MraidWebViewClient", 6, "matchesInjectionUrl Exception: " + e.getMessage());
            return false;
        }
    }

    private WebResourceResponse a() {
        return new WebResourceResponse("text/javascript", "UTF-8", new ByteArrayInputStream(("javascript:" + a.a()).getBytes()));
    }

    public boolean c(String str) {
        g.a("MraidWebViewClient", 3, "invokeMraidMethod " + str);
        String[] strArr = {"close", "resize"};
        String[] strArr2 = {"createCalendarEvent", "expand", "open", "playVideo", "storePicture", "useCustomClose"};
        String[] strArr3 = {"setOrientationProperties", "setResizeProperties"};
        try {
            Map<String, String> a2 = b.a(str);
            String str2 = a2.get("command");
            if (Arrays.asList(strArr).contains(str2)) {
                b.class.getDeclaredMethod(str2, new Class[0]).invoke(this.f249a, new Object[0]);
            } else if (Arrays.asList(strArr2).contains(str2)) {
                Method declaredMethod = b.class.getDeclaredMethod(str2, new Class[]{String.class});
                char c = 65535;
                int hashCode = str2.hashCode();
                String str3 = "useCustomClose";
                if (hashCode != -733616544) {
                    if (hashCode == 1614272768) {
                        if (str2.equals(str3)) {
                            c = 1;
                        }
                    }
                } else if (str2.equals("createCalendarEvent")) {
                    c = 0;
                }
                if (c == 0) {
                    str3 = "eventJSON";
                } else if (c != 1) {
                    str3 = "url";
                }
                declaredMethod.invoke(this.f249a, new Object[]{a2.get(str3)});
            } else if (Arrays.asList(strArr3).contains(str2)) {
                b.class.getDeclaredMethod(str2, new Class[]{Map.class}).invoke(this.f249a, new Object[]{a2});
            }
            g.a("MraidWebViewClient", 3, "successfully invoked " + str);
            return true;
        } catch (Exception e) {
            g.a("MraidWebViewClient", 6, "failed to invoke " + str + ". " + e.getMessage());
            return false;
        }
    }
}
