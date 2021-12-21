package com.tappx.a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.appnext.base.b.d;
import com.tappx.a.p3;
import com.tappx.sdk.android.PrivacyConsentActivity;
import java.util.concurrent.TimeUnit;

public final class l2 {
    private static final long h = TimeUnit.SECONDS.toMillis(4);
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final n2 f505a;
    /* access modifiers changed from: private */
    public p3 b;
    /* access modifiers changed from: private */
    public final PrivacyConsentActivity c;
    private WebView d;
    private final m2 e;
    private p3.f f = new b();
    private final WebViewClient g = new c();

    class a implements Runnable {
        a() {
        }

        public void run() {
            l2.this.b.setCloseEnabled(true);
        }
    }

    class b implements p3.f {
        b() {
        }

        public void a() {
            l2.this.d();
        }
    }

    class c extends WebViewClient {
        c() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if ("tappx://consent?yes".equals(str)) {
                l2.this.f505a.g();
                return true;
            } else if ("tappx://consent?no".equals(str)) {
                l2.this.f505a.f();
                return true;
            } else if ("tappx://close".equals(str)) {
                l2.this.d();
                return true;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    l2.this.c.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        }
    }

    public l2(PrivacyConsentActivity privacyConsentActivity) {
        this.c = privacyConsentActivity;
        o2 a2 = o2.a(privacyConsentActivity);
        this.f505a = a2.c();
        this.e = a2.b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getLanguage();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String e() {
        /*
            r1 = this;
            java.util.Locale r0 = java.util.Locale.getDefault()
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = r0.getLanguage()
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = r0.toUpperCase()
            return r0
        L_0x0011:
            java.lang.String r0 = "EN"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.a.l2.e():java.lang.String");
    }

    private String f() {
        String stringExtra = this.c.getIntent().getStringExtra("GR8QbFbIwPD6k5hAnMxS6Za9cNsNHXXZzG7GWfNC");
        if (stringExtra == null) {
            return null;
        }
        return Uri.parse(stringExtra).buildUpon().appendQueryParameter(f.b("Atea2vjkWMaKJqXPDr3CPg"), e()).build().toString();
    }

    private View g() {
        this.b = new p3(this.c);
        WebView c2 = c();
        this.d = c2;
        this.b.addView(c2, 0, new FrameLayout.LayoutParams(-1, -1));
        return this.b;
    }

    private void h() {
        this.b.setCloseEnabled(false);
        this.b.setCloseListener(this.f);
        this.b.postDelayed(new a(), h);
    }

    private void i() {
        this.c.requestWindowFeature(1);
        this.c.getWindow().addFlags(d.fb);
        this.c.setContentView(g());
        h();
    }

    private void j() {
        String stringExtra = this.c.getIntent().getStringExtra("kuutYDJOjEGYmzrvCGMIZqwyDXtIZYWxcXzXexLx");
        String f2 = f();
        if (stringExtra != null && !stringExtra.isEmpty()) {
            this.d.loadDataWithBaseURL("https://tappx.com/", stringExtra, "text/html", "UTF-8", (String) null);
        } else if (f2 != null) {
            this.d.loadUrl(f2);
        } else {
            this.c.finish();
        }
    }

    public static Intent a(Context context, String str, String str2) {
        Intent intent = new Intent(context, PrivacyConsentActivity.class);
        intent.putExtra("GR8QbFbIwPD6k5hAnMxS6Za9cNsNHXXZzG7GWfNC", str);
        intent.putExtra("kuutYDJOjEGYmzrvCGMIZqwyDXtIZYWxcXzXexLx", str2);
        return intent;
    }

    private WebView c() {
        WebView webView = new WebView(this.c);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.c.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            webView.setId(View.generateViewId());
        }
        webView.setWebViewClient(this.g);
        return webView;
    }

    /* access modifiers changed from: private */
    public void d() {
        this.c.finish();
    }

    public void b() {
        this.e.a();
    }

    public void a(Bundle bundle) {
        i();
        j();
    }

    public boolean a() {
        return !this.b.b();
    }
}
