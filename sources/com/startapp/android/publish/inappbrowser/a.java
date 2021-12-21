package com.startapp.android.publish.inappbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.a.b;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.common.a.g;

/* compiled from: StartAppSDK */
public class a extends b implements View.OnClickListener {
    protected static boolean j = false;
    protected RelativeLayout d;
    protected b e;
    protected WebView f;
    protected AnimatingProgressBar g;
    protected FrameLayout h;
    protected String i;

    public void s() {
    }

    public void u() {
    }

    public a(String str) {
        this.i = str;
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        j = false;
        this.d = new RelativeLayout(b());
        b(this.i);
        if (bundle != null) {
            c(bundle);
        }
        b().setContentView(this.d, new RelativeLayout.LayoutParams(-2, -2));
    }

    private void b(String str) {
        g.a("IABrowserMode", 3, "initUi");
        if (this.e == null) {
            b bVar = new b(b());
            this.e = bVar;
            bVar.a();
            this.e.b();
            this.e.setButtonsListener(this);
        }
        this.d.addView(this.e);
        this.g = new AnimatingProgressBar(b(), (AttributeSet) null, 16842872);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(Color.parseColor("#45d200"));
        this.g.setProgressDrawable(new ClipDrawable(shapeDrawable, 3, 1));
        this.g.setBackgroundColor(-1);
        this.g.setId(2108);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, h.a((Context) b(), 4));
        layoutParams.addRule(3, 2101);
        this.d.addView(this.g, layoutParams);
        this.h = new FrameLayout(b());
        if (this.f == null) {
            try {
                y();
                this.f.loadUrl(str);
            } catch (Exception e2) {
                this.e.c();
                c.c(b(), str);
                f.a(b(), d.EXCEPTION, "IABrowserMode.initUi - Webvie  failed", e2.getMessage(), "");
                b().finish();
            }
        }
        this.h.addView(this.f);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(15);
        layoutParams2.addRule(3, 2108);
        this.d.addView(this.h, layoutParams2);
    }

    public void b(Bundle bundle) {
        this.f.saveState(bundle);
    }

    public void c(Bundle bundle) {
        this.f.restoreState(bundle);
    }

    private void y() {
        this.f = new WebView(b());
        z();
        this.f.setWebViewClient(new C0008a(b(), this.e, this.g, this));
        this.f.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                a.this.g.setProgress(i);
            }

            public void onReceivedTitle(WebView webView, String str) {
                if (str != null && !str.equals("")) {
                    a.this.e.getTitleTxt().setText(str);
                }
            }
        });
    }

    private void z() {
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.getSettings().setUseWideViewPort(true);
        this.f.getSettings().setLoadWithOverviewMode(true);
        this.f.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.f.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 11) {
            this.f.getSettings().setDisplayZoomControls(false);
        }
    }

    /* renamed from: com.startapp.android.publish.inappbrowser.a$a  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    private static class C0008a extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        private Context f322a;
        private a b;
        private b c;
        private AnimatingProgressBar d;
        private int e = 0;
        private boolean f = false;

        public C0008a(Context context, b bVar, AnimatingProgressBar animatingProgressBar, a aVar) {
            this.f322a = context;
            this.d = animatingProgressBar;
            this.c = bVar;
            this.b = aVar;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!a.j) {
                g.a("IABrowserMode", 3, "IABWebViewClient::onPageStarted - [" + str + "]" + "REDIRECTED  -> " + this.e + " Can go back " + webView.canGoBack());
                if (this.f) {
                    this.e = 1;
                    this.d.a();
                    this.c.a(webView);
                } else {
                    this.e = Math.max(this.e, 1);
                }
                this.d.setVisibility(0);
                this.c.getUrlTxt().setText(str);
                this.c.a(webView);
                super.onPageStarted(webView, str, bitmap);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            g.a("IABrowserMode", 3, "IABWebViewClient::shouldOverrideUrlLoading - [" + str + "]");
            if (!a.j) {
                if (!this.f) {
                    this.f = true;
                    this.d.a();
                    this.e = 0;
                }
                this.e++;
                if (c.d(str) && !c.b(str)) {
                    return false;
                }
                this.e = 1;
                c.c(this.f322a, str);
                a aVar = this.b;
                if (aVar != null) {
                    aVar.x();
                }
            }
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!a.j) {
                g.a("IABrowserMode", 3, "IABWebViewClient::onPageFinished - [" + str + "]");
                this.c.a(webView);
                int i = this.e + -1;
                this.e = i;
                if (i == 0) {
                    this.f = false;
                    this.d.a();
                    if (this.d.isShown()) {
                        this.d.setVisibility(8);
                    }
                    this.c.a(webView);
                }
                super.onPageFinished(webView, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            g.a("IABrowserMode", 3, "IABWebViewClient::onReceivedError - [" + str + "], [" + str2 + "]");
            this.d.a();
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2103:
                x();
                return;
            case 2104:
                if (this.f != null) {
                    c.c(b(), this.f.getUrl());
                    x();
                    return;
                }
                return;
            case 2105:
                WebView webView = this.f;
                if (webView != null && webView.canGoBack()) {
                    this.g.a();
                    this.f.goBack();
                    return;
                }
                return;
            case 2106:
                WebView webView2 = this.f;
                if (webView2 != null && webView2.canGoForward()) {
                    this.g.a();
                    this.f.goForward();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i2 != 4) {
            return super.a(i2, keyEvent);
        }
        WebView webView = this.f;
        if (webView == null || !webView.canGoBack()) {
            g.a("IABrowserMode", 3, "IABWebViewClient::KEYCODE_BACK canT go back");
            x();
            return true;
        }
        g.a("IABrowserMode", 3, "IABWebViewClient::KEYCODE_BACK can go back");
        this.g.a();
        this.f.goBack();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void x() {
        A();
        this.e.c();
        b().finish();
    }

    private void A() {
        try {
            j = true;
            this.f.stopLoading();
            this.f.removeAllViews();
            this.f.postInvalidate();
            com.startapp.common.a.c.b(this.f);
            this.f.destroy();
            this.f = null;
        } catch (Exception e2) {
            g.a("IABrowserMode", 6, "IABrowserMode::destroyWebview error " + e2.getMessage());
        }
    }
}
