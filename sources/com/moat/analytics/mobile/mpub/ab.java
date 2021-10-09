package com.moat.analytics.mobile.mpub;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.a.b.a;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

class ab {

    /* renamed from: a  reason: collision with root package name */
    private static final LinkedHashSet<String> f1153a = new LinkedHashSet<>();

    ab() {
    }

    static a<WebView> a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            try {
                return a.a();
            } catch (Exception unused) {
                return a.a();
            }
        } else if (viewGroup instanceof WebView) {
            return a.a((WebView) viewGroup);
        } else {
            LinkedList linkedList = new LinkedList();
            linkedList.add(viewGroup);
            WebView webView = null;
            int i = 0;
            while (!linkedList.isEmpty() && i < 100) {
                i++;
                ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                int childCount = viewGroup2.getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        break;
                    }
                    View childAt = viewGroup2.getChildAt(i2);
                    if (childAt instanceof WebView) {
                        p.a(3, "WebViewHound", (Object) childAt, "Found WebView");
                        if (z || a(String.valueOf(childAt.hashCode()))) {
                            if (webView != null) {
                                p.a(3, "WebViewHound", (Object) childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                p.a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                webView = null;
                                break;
                            }
                            webView = (WebView) childAt;
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                    i2++;
                }
            }
            return a.b(webView);
        }
    }

    private static boolean a(String str) {
        try {
            boolean add = f1153a.add(str);
            if (f1153a.size() > 50) {
                Iterator it = f1153a.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            p.a(3, "WebViewHound", (Object) null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            n.a(e);
            return false;
        }
    }
}
