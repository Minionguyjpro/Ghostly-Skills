package com.b.a.a.a.g;

import android.os.Handler;
import android.webkit.WebView;
import com.b.a.a.a.b.h;
import com.b.a.a.a.c.d;
import java.util.List;

public class c extends a {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public WebView f1004a;
    private List<h> b;
    private final String c;

    public c(List<h> list, String str) {
        this.b = list;
        this.c = str;
    }

    public void a() {
        super.a();
        j();
    }

    public void b() {
        super.b();
        new Handler().postDelayed(new Runnable() {
            private WebView b = c.this.f1004a;

            public void run() {
                this.b.destroy();
            }
        }, 2000);
        this.f1004a = null;
    }

    /* access modifiers changed from: package-private */
    public void j() {
        WebView webView = new WebView(com.b.a.a.a.c.c.a().b());
        this.f1004a = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        a(this.f1004a);
        d.a().a(this.f1004a, this.c);
        for (h b2 : this.b) {
            d.a().b(this.f1004a, b2.b().toExternalForm());
        }
    }
}
