package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.mpub.a.b.a;
import com.moat.analytics.mobile.mpub.w;
import java.lang.ref.WeakReference;

class f {

    /* renamed from: a  reason: collision with root package name */
    private static WebAdTracker f1156a;
    private static WeakReference<Activity> b = new WeakReference<>((Object) null);

    f() {
    }

    private static void a() {
        if (f1156a != null) {
            p.a(3, "GMAInterstitialHelper", b.get(), "Stopping to track GMA interstitial");
            f1156a.stopTracking();
            f1156a = null;
        }
    }

    static void a(Activity activity) {
        try {
            if (w.a().f1186a != w.d.OFF) {
                if (!b(activity)) {
                    a();
                    b = new WeakReference<>((Object) null);
                } else if (b.get() == null || b.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        a<WebView> a2 = ab.a((ViewGroup) decorView, true);
                        if (a2.c()) {
                            b = new WeakReference<>(activity);
                            a(a2.b());
                            return;
                        }
                        p.a(3, "GMAInterstitialHelper", (Object) activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            n.a(e);
        }
    }

    private static void a(WebView webView) {
        p.a(3, "GMAInterstitialHelper", b.get(), "Starting to track GMA interstitial");
        WebAdTracker createWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
        f1156a = createWebAdTracker;
        createWebAdTracker.startTracking();
    }

    private static boolean b(Activity activity) {
        String name = activity.getClass().getName();
        p.a(3, "GMAInterstitialHelper", (Object) activity, "Activity name: " + name);
        return name.contains(AdActivity.CLASS_NAME);
    }
}
