package com.moat.analytics.mobile.mpub;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.moat.analytics.mobile.mpub.j;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class g {

    /* renamed from: a  reason: collision with root package name */
    WebView f1157a;
    j b;
    final String c = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    private final a d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public Handler f;
    /* access modifiers changed from: private */
    public Runnable g;

    enum a {
        DISPLAY,
        VIDEO
    }

    g(Context context, a aVar) {
        this.d = aVar;
        WebView webView = new WebView(context);
        this.f1157a = webView;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        try {
            this.b = new j(this.f1157a, aVar == a.VIDEO ? j.a.NATIVE_VIDEO : j.a.NATIVE_DISPLAY);
        } catch (n e2) {
            n.a(e2);
        }
    }

    private static String a(String str, String str2, Integer num, Integer num2, JSONObject jSONObject, Integer num3) {
        try {
            return Base64.encodeToString(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str, str2, jSONObject.toString(), num3}).getBytes(), 1);
        } catch (Exception e2) {
            n.a(e2);
            return "";
        }
    }

    private static String b(String str) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>";
    }

    /* access modifiers changed from: package-private */
    public void a() {
        p.a(3, "GlobalWebView", (Object) this, "Cleaning up");
        this.b.b();
        this.b = null;
        this.f1157a.destroy();
        this.f1157a = null;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (this.d == a.DISPLAY) {
            this.f1157a.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    if (!g.this.e) {
                        try {
                            boolean unused = g.this.e = true;
                            g.this.b.a();
                        } catch (Exception e) {
                            n.a(e);
                        }
                    }
                }
            });
            this.f1157a.loadData(b(str), "text/html", "utf-8");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.d == a.VIDEO) {
            if (Build.VERSION.SDK_INT >= 19) {
                p.a(3, "GlobalWebView", (Object) this, "Starting off polling interval to check for Video API instance presence");
                this.f = new Handler();
                AnonymousClass2 r0 = new Runnable() {
                    public void run() {
                        try {
                            if (g.this.f1157a != null && Build.VERSION.SDK_INT >= 19) {
                                WebView webView = g.this.f1157a;
                                webView.evaluateJavascript("typeof " + g.this.c + " !== 'undefined'", new ValueCallback<String>() {
                                    /* renamed from: a */
                                    public void onReceiveValue(String str) {
                                        if ("true".equals(str)) {
                                            p.a(3, "GlobalWebView", (Object) this, String.format("Video API instance %s detected. Flushing event queue", new Object[]{g.this.c}));
                                            try {
                                                boolean unused = g.this.e = true;
                                                g.this.b.a();
                                                g.this.b.c(g.this.c);
                                            } catch (Exception e) {
                                                n.a(e);
                                            }
                                        } else {
                                            g.this.f.postDelayed(g.this.g, 200);
                                        }
                                    }
                                });
                            }
                        } catch (Exception e) {
                            n.a(e);
                        }
                    }
                };
                this.g = r0;
                this.f.post(r0);
            } else {
                p.a(3, "GlobalWebView", (Object) this, "Android API version is less than KitKat: " + Build.VERSION.SDK_INT);
                this.f1157a.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView webView, String str) {
                        if (!g.this.e) {
                            p.a(3, "GlobalWebView", (Object) this, "onPageFinished is called for the first time. Flushing event queue");
                            try {
                                boolean unused = g.this.e = true;
                                g.this.b.a();
                                g.this.b.c(g.this.c);
                            } catch (Exception e) {
                                n.a(e);
                            }
                        }
                    }
                });
            }
            this.f1157a.loadData(a(this.c, str, num, num2, new JSONObject(map), num3), "text/html", "base64");
        }
    }
}
