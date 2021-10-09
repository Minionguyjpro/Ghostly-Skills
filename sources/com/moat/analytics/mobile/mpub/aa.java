package com.moat.analytics.mobile.mpub;

import android.view.ViewGroup;
import android.webkit.WebView;

class aa extends b implements WebAdTracker {
    aa(ViewGroup viewGroup) {
        this(ab.a(viewGroup, false).c(null));
        if (viewGroup == null) {
            p.a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, " + "Target ViewGroup is null");
            this.f1154a = new n("Target ViewGroup is null");
        }
        if (this.b == null) {
            p.a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, " + "No WebView to track inside of ad container");
            this.f1154a = new n("No WebView to track inside of ad container");
        }
    }

    aa(WebView webView) {
        super(webView, false, false);
        p.a(3, "WebAdTracker", (Object) this, "Initializing.");
        if (webView == null) {
            p.a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, " + "WebView is null");
            this.f1154a = new n("WebView is null");
            return;
        }
        try {
            super.a(webView);
            p.a("[SUCCESS] ", a() + " created for " + g());
        } catch (n e) {
            this.f1154a = e;
        }
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "WebAdTracker";
    }
}
