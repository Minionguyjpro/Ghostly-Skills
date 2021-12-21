package com.startapp.android.publish.ads.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.b.a.a.a.b.b;
import com.mopub.mobileads.resource.DrawableConstants;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.i;
import com.startapp.android.publish.adsCommon.m;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.html.JsInterface;
import com.startapp.common.a.g;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class c extends b {
    protected WebView d;
    protected b e;
    protected RelativeLayout f;
    protected Runnable g = new Runnable() {
        public void run() {
            c.this.B();
            c.this.p();
        }
    };
    protected Runnable h = new Runnable() {
        public void run() {
            boolean unused = c.this.n = true;
            c cVar = c.this;
            cVar.b(cVar.d);
        }
    };
    private Long i;
    private Long j;
    private long k = 0;
    private long l = 0;
    private i m;
    /* access modifiers changed from: private */
    public boolean n = true;
    /* access modifiers changed from: private */
    public boolean o = false;

    /* access modifiers changed from: protected */
    public void c(WebView webView) {
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        if (bundle == null) {
            if (a().hasExtra("lastLoadTime")) {
                this.i = (Long) a().getSerializableExtra("lastLoadTime");
            }
            if (a().hasExtra("adCacheTtl")) {
                this.j = (Long) a().getSerializableExtra("adCacheTtl");
                return;
            }
            return;
        }
        if (bundle.containsKey("postrollHtml")) {
            a(bundle.getString("postrollHtml"));
        }
        if (bundle.containsKey("lastLoadTime")) {
            this.i = (Long) bundle.getSerializable("lastLoadTime");
        }
        if (bundle.containsKey("adCacheTtl")) {
            this.j = (Long) bundle.getSerializable("adCacheTtl");
        }
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        if (f() != null) {
            bundle.putString("postrollHtml", f());
        }
        Long l2 = this.i;
        if (l2 != null) {
            bundle.putLong("lastLoadTime", l2.longValue());
        }
        Long l3 = this.j;
        if (l3 != null) {
            bundle.putLong("adCacheTtl", l3.longValue());
        }
    }

    public void u() {
        if (G()) {
            g.a("InterstitialMode", 3, "Ad Cache TTL passed, finishing");
            p();
            return;
        }
        m.a().a(true);
        if (this.m == null) {
            this.m = new i(b(), h(), D(), F());
        }
        WebView webView = this.d;
        if (webView == null) {
            RelativeLayout relativeLayout = new RelativeLayout(b());
            this.f = relativeLayout;
            relativeLayout.setContentDescription("StartApp Ad");
            this.f.setId(AdsConstants.STARTAPP_AD_MAIN_LAYOUT_ID);
            b().setContentView(this.f);
            try {
                WebView webView2 = new WebView(b().getApplicationContext());
                this.d = webView2;
                webView2.setBackgroundColor(DrawableConstants.CtaButton.BACKGROUND_COLOR);
                b().getWindow().getDecorView().findViewById(16908290).setBackgroundColor(7829367);
                this.d.setVerticalScrollBarEnabled(false);
                this.d.setHorizontalScrollBarEnabled(false);
                this.d.getSettings().setJavaScriptEnabled(true);
                com.startapp.common.a.c.a(this.d);
                if (this.c) {
                    com.startapp.common.a.c.a(this.d, (Paint) null);
                }
                this.d.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                this.d.setLongClickable(false);
                this.d.addJavascriptInterface(y(), "startappwall");
                z();
                a(this.d);
                com.startapp.android.publish.adsCommon.Utils.i.a((Context) b(), this.d, f());
                this.o = "true".equals(com.startapp.android.publish.adsCommon.Utils.i.a(f(), "@jsTag@", "@jsTag@"));
                x();
                this.f.addView(this.d, new RelativeLayout.LayoutParams(-1, -1));
                a(this.f);
            } catch (Exception e2) {
                f.a(b(), d.EXCEPTION, "InterstitialMode.onResume - WebView failed", e2.getMessage(), "");
                p();
            }
        } else {
            com.startapp.common.a.c.c(webView);
            this.m.a();
        }
        this.k = System.currentTimeMillis();
    }

    public void v() {
        super.v();
        b bVar = this.e;
        if (bVar != null) {
            bVar.b();
            this.e = null;
        }
        com.startapp.android.publish.adsCommon.Utils.i.a((Object) this.d, 1000);
    }

    /* access modifiers changed from: protected */
    public void x() {
        this.d.setWebViewClient(new a());
        this.d.setWebChromeClient(new WebChromeClient());
    }

    private boolean G() {
        if (w() instanceof com.startapp.android.publish.ads.b.c) {
            return ((com.startapp.android.publish.ads.b.c) w()).hasAdCacheTtlPassed();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public JsInterface y() {
        Activity b = b();
        Runnable runnable = this.g;
        return new JsInterface(b, runnable, runnable, this.h, C(), a(0));
    }

    /* access modifiers changed from: protected */
    public void z() {
        this.m.a();
    }

    public void a(WebView webView) {
        this.n = false;
        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean unused = c.this.n = true;
                if (motionEvent.getAction() == 2) {
                    return true;
                }
                return false;
            }
        });
    }

    public void b(WebView webView) {
        if (webView != null) {
            webView.setOnTouchListener((View.OnTouchListener) null);
        }
    }

    /* access modifiers changed from: protected */
    public void A() {
        View a2;
        if (MetaData.getInstance().isOmsdkEnabled() && this.e == null) {
            b a3 = com.startapp.android.publish.omsdk.a.a(this.d);
            this.e = a3;
            if (a3 != null && this.d != null) {
                if (!(this.f28a == null || (a2 = this.f28a.a()) == null)) {
                    this.e.b(a2);
                }
                this.e.a(this.d);
                this.e.a();
                com.b.a.a.a.b.a.a(this.e).a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, Object... objArr) {
        com.startapp.android.publish.adsCommon.Utils.i.a(this.d, str, objArr);
    }

    /* compiled from: StartAppSDK */
    class a extends WebViewClient {
        a() {
        }

        public void onPageFinished(WebView webView, String str) {
            c.this.c(webView);
            c cVar = c.this;
            cVar.a("gClientInterface.setMode", cVar.g());
            c.this.a("enableScheme", "externalLinks");
            c.this.A();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            g.a(2, "MyWebViewClient::shouldOverrideUrlLoading - [" + str + "]");
            if (!c.this.o || c.this.n) {
                return c.this.a(str, false);
            }
            return false;
        }
    }

    private boolean b(Ad ad) {
        return com.startapp.android.publish.adsCommon.Utils.i.a(8) && (ad instanceof com.startapp.android.publish.ads.splash.b);
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, boolean z) {
        this.m.a(true);
        boolean z2 = com.startapp.android.publish.adsCommon.c.a(b().getApplicationContext(), this.b) && !b(w());
        if (b(str)) {
            try {
                int a2 = com.startapp.android.publish.adsCommon.c.a(str);
                if (!d()[a2] || z2) {
                    g.a(6, "forceExternal - interMode - redirect");
                    b(str, a2, z);
                } else {
                    g.a(6, "forceExternal -interMode - smartredirect");
                    a(str, a2, z);
                }
            } catch (Exception unused) {
                g.a(6, "Error while trying parsing index from url");
                return false;
            }
        } else if (!d()[0] || z2) {
            g.a(6, "forceExternal - interMode - smartredirect");
            b(str, 0, z);
        } else {
            g.a(6, "forceExternal - interMode - redirectr");
            a(str, 0, z);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(String str) {
        return !this.o && str.contains("index=");
    }

    /* access modifiers changed from: protected */
    public void B() {
        String[] l2 = l();
        if (l2 != null && l2.length > 0 && l()[0] != null) {
            com.startapp.android.publish.adsCommon.c.b((Context) b(), l()[0], C());
        }
    }

    private void a(String str, int i2, boolean z) {
        int i3 = i2;
        Activity b = b();
        String str2 = null;
        String str3 = i3 < i().length ? i()[i3] : null;
        if (i3 < j().length) {
            str2 = j()[i3];
        }
        com.startapp.android.publish.adsCommon.c.a(b, str, str3, str2, C(), com.startapp.android.publish.adsCommon.b.a().A(), com.startapp.android.publish.adsCommon.b.a().B(), a(i3), b(i3), z, new Runnable() {
            public void run() {
                c.this.p();
            }
        });
    }

    private void b(String str, int i2, boolean z) {
        com.startapp.common.b.a((Context) b()).a(new Intent("com.startapp.android.OnClickCallback"));
        com.startapp.android.publish.adsCommon.c.a(b(), str, i2 < i().length ? i()[i2] : null, C(), a(i2) && !com.startapp.android.publish.adsCommon.c.a(b().getApplicationContext(), this.b), z);
        p();
    }

    public void p() {
        super.p();
        m.a().a(false);
        i iVar = this.m;
        if (iVar != null) {
            iVar.a(false);
        }
        b().runOnUiThread(new Runnable() {
            public void run() {
                if (c.this.d != null) {
                    com.startapp.common.a.c.b(c.this.d);
                }
            }
        });
    }

    public void s() {
        i iVar = this.m;
        if (iVar != null) {
            iVar.b();
        }
        if (this.f28a != null && this.f28a.b()) {
            this.f28a.d();
        }
        WebView webView = this.d;
        if (webView != null) {
            com.startapp.common.a.c.b(webView);
        }
        if (g().equals("back")) {
            p();
        }
    }

    /* access modifiers changed from: protected */
    public com.startapp.android.publish.adsCommon.d.b C() {
        return new com.startapp.android.publish.adsCommon.d.a(E(), n());
    }

    /* access modifiers changed from: protected */
    public com.startapp.android.publish.adsCommon.d.b D() {
        return new com.startapp.android.publish.adsCommon.d.b(n());
    }

    /* access modifiers changed from: protected */
    public String E() {
        long currentTimeMillis = System.currentTimeMillis();
        this.l = currentTimeMillis;
        double d2 = (double) (currentTimeMillis - this.k);
        Double.isNaN(d2);
        return String.valueOf(d2 / 1000.0d);
    }

    public boolean r() {
        B();
        m.a().a(false);
        this.m.a(false);
        return false;
    }

    /* access modifiers changed from: protected */
    public long F() {
        if (o() != null) {
            return TimeUnit.SECONDS.toMillis(o().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }
}
