package com.moat.analytics.mobile.mpub;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.a.b.a;
import com.moat.analytics.mobile.mpub.v;
import com.moat.analytics.mobile.mpub.x;
import java.lang.ref.WeakReference;
import java.util.Map;

class o extends MoatFactory {
    o() {
        if (!a()) {
            p.a("[ERROR] ", 3, "Factory", this, "Failed to initialize MoatFactory" + ", SDK was not started");
            throw new n("Failed to initialize MoatFactory");
        }
    }

    private NativeDisplayTracker a(View view, final Map<String, String> map) {
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) x.a(new x.a<NativeDisplayTracker>() {
            public a<NativeDisplayTracker> a() {
                View view = (View) weakReference.get();
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create NativeDisplayTracker for " + p.a(view));
                return a.a(new t(view, map));
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker a(final String str) {
        return (NativeVideoTracker) x.a(new x.a<NativeVideoTracker>() {
            public a<NativeVideoTracker> a() {
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create NativeVideoTracker");
                return a.a(new u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker a(ViewGroup viewGroup) {
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() {
            public a<WebAdTracker> a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create WebAdTracker for adContainer " + p.a((View) viewGroup));
                return a.a(new aa(viewGroup));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker a(WebView webView) {
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) x.a(new x.a<WebAdTracker>() {
            public a<WebAdTracker> a() {
                WebView webView = (WebView) weakReference.get();
                p.a("[INFO] ", 3, "Factory", this, "Attempting to create WebAdTracker for " + p.a((View) webView));
                return a.a(new aa(webView));
            }
        }, WebAdTracker.class);
    }

    private <T> T a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }

    private boolean a() {
        return ((k) k.getInstance()).a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return a(moatPlugin);
        } catch (Exception e) {
            n.a(e);
            return moatPlugin.b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
        try {
            return a(view, map);
        } catch (Exception e) {
            n.a(e);
            return new v.c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return a(str);
        } catch (Exception e) {
            n.a(e);
            return new v.d();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return a(viewGroup);
        } catch (Exception e) {
            n.a(e);
            return new v.e();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return a(webView);
        } catch (Exception e) {
            n.a(e);
            return new v.e();
        }
    }
}
