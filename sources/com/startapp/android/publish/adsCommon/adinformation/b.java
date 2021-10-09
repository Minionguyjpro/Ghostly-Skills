package com.startapp.android.publish.adsCommon.adinformation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.startapp.android.publish.adsCommon.Utils.i;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationConfig;
import com.startapp.android.publish.adsCommon.c;
import com.startapp.android.publish.adsCommon.f.d;
import com.startapp.android.publish.adsCommon.f.e;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.k;
import com.startapp.android.publish.adsCommon.l;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.h;
import com.startapp.android.publish.common.model.AdPreferences;

/* compiled from: StartAppSDK */
public class b implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    Context f204a;
    RelativeLayout b;
    RelativeLayout c;
    AdInformationConfig d;
    h e;
    private d f;
    private WebView g;
    private Dialog h = null;
    private AdPreferences.Placement i;
    private Handler j = new Handler();
    private a k = a.REGULAR;
    private boolean l = false;
    private c m;
    private Runnable n = new Runnable() {
        public void run() {
            try {
                b.this.d();
                b.this.e.a(b.this.f204a, true);
                b.this.d.a(b.this.f204a, true);
                b.this.a(false);
            } catch (Exception e) {
                f.a(b.this.f204a, new e(d.EXCEPTION, "AdInformationObject.acceptRunnable failed", e.getMessage()), "");
            }
        }
    };
    private Runnable o = new Runnable() {
        public void run() {
            try {
                b.this.d();
                l.b();
                b.this.e.a(b.this.f204a, false);
                b.this.d.a(b.this.f204a, true);
                b.this.a(false);
            } catch (Exception e) {
                f.a(b.this.f204a, new e(d.EXCEPTION, "AdInformationObject.declineRunnable failed", e.getMessage()), "");
            }
        }
    };
    private Runnable p = new Runnable() {
        public void run() {
            try {
                b.this.d();
                b.this.c();
                b.this.a(false);
            } catch (Exception e) {
                f.a(b.this.f204a, new e(d.EXCEPTION, "AdInformationObject.fullPrivacyPolicy failed", e.getMessage()), "");
            }
        }
    };

    /* compiled from: StartAppSDK */
    public enum a {
        REGULAR,
        LAYOUT
    }

    /* renamed from: com.startapp.android.publish.adsCommon.adinformation.b$b  reason: collision with other inner class name */
    /* compiled from: StartAppSDK */
    public enum C0003b {
        SMALL(AdInformationConfig.ImageResourceType.INFO_S, AdInformationConfig.ImageResourceType.INFO_EX_S),
        LARGE(AdInformationConfig.ImageResourceType.INFO_L, AdInformationConfig.ImageResourceType.INFO_EX_L);
        
        private AdInformationConfig.ImageResourceType infoExtendedType;
        private AdInformationConfig.ImageResourceType infoType;

        private C0003b(AdInformationConfig.ImageResourceType imageResourceType, AdInformationConfig.ImageResourceType imageResourceType2) {
            this.infoType = imageResourceType;
            this.infoExtendedType = imageResourceType2;
        }

        public AdInformationConfig.ImageResourceType a() {
            return this.infoType;
        }
    }

    public b(Context context, C0003b bVar, AdPreferences.Placement placement, c cVar) {
        this.f204a = context;
        this.i = placement;
        this.m = cVar;
        this.d = e();
        this.e = MetaData.getInstance().getSimpleTokenConfig();
        this.f = new d(context, bVar, placement, cVar, this);
    }

    public void a(RelativeLayout relativeLayout) {
        boolean z;
        if (f() == null || !f().e()) {
            z = e().a(this.f204a);
        } else {
            z = f().b();
        }
        if (z) {
            this.c = relativeLayout;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            if (f() == null || !f().d()) {
                e().a(this.i).addRules(layoutParams);
            } else {
                f().c().addRules(layoutParams);
            }
            this.c.addView(this.f, layoutParams);
        }
    }

    public View a() {
        return this.f;
    }

    public boolean b() {
        return this.l;
    }

    public static AdInformationConfig a(Context context) {
        return a.b().a();
    }

    private AdInformationConfig e() {
        return a.b().a();
    }

    private c f() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (!this.i.isInterstitial()) {
            Context context = this.f204a;
            if (context instanceof Activity) {
                i.a((Activity) context, z);
            }
        }
    }

    public void onClick(View view) {
        if (!this.e.b(this.f204a) || !(this.f204a instanceof Activity)) {
            c();
            return;
        }
        a(true);
        this.b = new RelativeLayout(this.f204a);
        try {
            WebView webView = new WebView(this.f204a);
            this.g = webView;
            webView.setWebViewClient(new WebViewClient());
            this.g.setWebChromeClient(new WebChromeClient());
            this.g.getSettings().setJavaScriptEnabled(true);
            this.g.setHorizontalScrollBarEnabled(false);
            this.g.setVerticalScrollBarEnabled(false);
            this.g.loadUrl(a(this.d.f()));
            this.g.addJavascriptInterface(new AdInformationJsInterface(this.f204a, this.n, this.o, this.p), "startappwall");
            Point point = new Point(1, 1);
            try {
                com.startapp.android.publish.adsCommon.Utils.h.a((WindowManager) this.f204a.getSystemService("window"), point);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13);
                this.g.setPadding(0, 0, 0, 0);
                layoutParams.setMargins(0, 0, 0, 0);
                this.b.addView(this.g, layoutParams);
                g();
                int i2 = AnonymousClass6.f210a[this.k.ordinal()];
                if (i2 == 1) {
                    b(this.b, point);
                } else if (i2 == 2) {
                    a(this.b, point);
                }
            } catch (Exception e2) {
                f.a(this.f204a, d.EXCEPTION, "AdInformationObject.onClick - system service failed", e2.getMessage(), "");
                a(false);
            }
        } catch (Exception e3) {
            f.a(this.f204a, d.EXCEPTION, "AdInformationObject.onClick - webview instantiation failed", e3.getMessage(), "");
            a(false);
        }
    }

    /* renamed from: com.startapp.android.publish.adsCommon.adinformation.b$6  reason: invalid class name */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class AnonymousClass6 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f210a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.startapp.android.publish.adsCommon.adinformation.b$a[] r0 = com.startapp.android.publish.adsCommon.adinformation.b.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f210a = r0
                com.startapp.android.publish.adsCommon.adinformation.b$a r1 = com.startapp.android.publish.adsCommon.adinformation.b.a.LAYOUT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f210a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.adinformation.b$a r1 = com.startapp.android.publish.adsCommon.adinformation.b.a.REGULAR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.adinformation.b.AnonymousClass6.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (!i.a(256) || !MetaData.getInstance().isInAppBrowser()) {
            c.c(this.f204a, this.d.b());
        } else {
            c.b(this.f204a, this.d.b(), "");
        }
    }

    public void d() {
        this.l = false;
        int i2 = AnonymousClass6.f210a[this.k.ordinal()];
        if (i2 == 1) {
            this.j.post(new Runnable() {
                public void run() {
                    b.this.c.removeView(b.this.b);
                }
            });
        } else if (i2 == 2) {
            this.h.dismiss();
        }
    }

    private void g() {
        String a2 = c.a(this.f204a, (String) null);
        if (a2 != null) {
            WebView webView = this.g;
            webView.loadUrl("javascript:window.onload=function(){document.getElementById('titlePlacement').innerText='" + a2 + "';}");
        }
    }

    private void a(ViewGroup viewGroup, Point point) {
        this.l = true;
        Dialog dialog = new Dialog(this.f204a);
        this.h = dialog;
        dialog.requestWindowFeature(1);
        this.h.setContentView(viewGroup);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.h.getWindow().getAttributes());
        layoutParams.width = (int) (((float) point.x) * 0.9f);
        layoutParams.height = (int) (((float) point.y) * 0.85f);
        this.h.show();
        this.h.getWindow().setAttributes(layoutParams);
    }

    private void b(final ViewGroup viewGroup, Point point) {
        this.l = true;
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((float) point.x) * 0.9f), (int) (((float) point.y) * 0.85f));
        layoutParams.addRule(13);
        this.j.post(new Runnable() {
            public void run() {
                b.this.c.addView(viewGroup, layoutParams);
            }
        });
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (b(this.f204a)) {
            sb.append("?le=true");
        }
        return sb.toString();
    }

    public static boolean b(Context context) {
        return k.a(context, "shared_prefs_using_location", (Boolean) false).booleanValue();
    }
}
