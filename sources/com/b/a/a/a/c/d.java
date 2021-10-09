package com.b.a.a.a.c;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import com.b.a.a.a.e.c;
import org.json.JSONObject;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private static d f993a = new d();

    private d() {
    }

    public static d a() {
        return f993a;
    }

    public void a(WebView webView) {
        a(webView, "finishSession", new Object[0]);
    }

    public void a(WebView webView, float f) {
        a(webView, "setDeviceVolume", Float.valueOf(f));
    }

    public void a(WebView webView, String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            a(webView, "publishVideoEvent", str, jSONObject);
            return;
        }
        a(webView, "publishVideoEvent", str);
    }

    public void a(WebView webView, String str, JSONObject jSONObject, JSONObject jSONObject2) {
        a(webView, "startSession", str, jSONObject, jSONObject2);
    }

    /* access modifiers changed from: package-private */
    public void a(WebView webView, String str, Object... objArr) {
        if (webView != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("javascript: if(window.omidBridge!==undefined){omidBridge.");
            sb.append(str);
            sb.append("(");
            a(sb, objArr);
            sb.append(")}");
            a(webView, sb);
            return;
        }
        c.a("The WebView is null for " + str);
    }

    /* access modifiers changed from: package-private */
    public void a(final WebView webView, StringBuilder sb) {
        final String sb2 = sb.toString();
        Handler handler = webView.getHandler();
        if (handler == null || Looper.myLooper() == handler.getLooper()) {
            webView.loadUrl(sb2);
        } else {
            handler.post(new Runnable() {
                public void run() {
                    webView.loadUrl(sb2);
                }
            });
        }
    }

    public void a(WebView webView, JSONObject jSONObject) {
        a(webView, "init", jSONObject);
    }

    /* access modifiers changed from: package-private */
    public void a(StringBuilder sb, Object[] objArr) {
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                if (obj == null) {
                    sb.append('\"');
                } else {
                    if (obj instanceof String) {
                        String obj2 = obj.toString();
                        if (obj2.startsWith("{")) {
                            sb.append(obj2);
                        } else {
                            sb.append('\"');
                            sb.append(obj2);
                        }
                    } else {
                        sb.append(obj);
                    }
                    sb.append(",");
                }
                sb.append('\"');
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        }
    }

    public boolean a(WebView webView, String str) {
        if (webView == null || TextUtils.isEmpty(str)) {
            return false;
        }
        webView.loadUrl("javascript: " + str);
        return true;
    }

    public void b(WebView webView) {
        a(webView, "publishImpressionEvent", new Object[0]);
    }

    public void b(WebView webView, String str) {
        if (str != null) {
            a(webView, "var script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);".replace("%SCRIPT_SRC%", str));
        }
    }

    public void c(WebView webView, String str) {
        a(webView, "setNativeViewHierarchy", str);
    }

    public void d(WebView webView, String str) {
        a(webView, "setState", str);
    }
}
