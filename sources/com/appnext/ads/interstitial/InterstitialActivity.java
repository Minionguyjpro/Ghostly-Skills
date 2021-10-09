package com.appnext.ads.interstitial;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.appnext.R;
import com.appnext.ads.AdsError;
import com.appnext.core.Ad;
import com.appnext.core.AppnextActivity;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.e;
import com.appnext.core.f;
import com.appnext.core.k;
import com.appnext.core.p;
import com.appnext.core.q;
import com.appnext.core.result.ResultPageActivity;
import com.appnext.core.result.c;
import com.appnext.core.result.d;
import com.appnext.core.webview.AppnextWebView;
import com.appnext.core.webview.WebAppInterface;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.mopub.network.ImpressionData;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialActivity extends AppnextActivity {
    /* access modifiers changed from: private */
    public AppnextAd aE;
    private com.appnext.ads.b aF;
    /* access modifiers changed from: private */
    public ArrayList<AppnextAd> ads;
    /* access modifiers changed from: private */
    public Boolean autoPlay;
    protected WebView bP;
    private boolean bQ = false;
    /* access modifiers changed from: private */
    public Interstitial bR;
    /* access modifiers changed from: private */
    public InterstitialAd bS;
    private String bT = "";
    private int bU = 0;
    /* access modifiers changed from: private */
    public Handler bV;
    private e.a bW;
    private WebAppInterface bX;
    private boolean bY = false;
    private boolean bZ = false;
    /* access modifiers changed from: private */
    public String ca;
    private Boolean canClose;
    /* access modifiers changed from: private */
    public String cc = "";
    /* access modifiers changed from: private */
    public boolean cd = false;
    /* access modifiers changed from: private */
    public Runnable ce = new Runnable() {
        public final void run() {
            InterstitialActivity.l(InterstitialActivity.this);
        }
    };
    private boolean closed = false;
    private Boolean mute;

    protected static void a(String str, String str2, String str3) {
    }

    static /* synthetic */ int u(InterstitialActivity interstitialActivity) {
        int i = interstitialActivity.bU;
        interstitialActivity.bU = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setRequestedOrientation(7);
        super.onCreate(bundle);
        if (Interstitial.currentAd == null) {
            finish();
            return;
        }
        this.bR = new Interstitial(Interstitial.currentAd);
        if (getRequestedOrientation() == 6) {
            report(com.appnext.ads.a.ai);
        } else {
            report(com.appnext.ads.a.aj);
        }
        this.gl = new RelativeLayout(this);
        setContentView(this.gl);
        this.gl.getLayoutParams().width = -1;
        this.gl.getLayoutParams().height = -1;
        this.gl.setBackgroundColor(-1);
        try {
            this.placementID = getIntent().getExtras().getString("id");
            if (getIntent().hasExtra("auto_play")) {
                Boolean valueOf = Boolean.valueOf(getIntent().getBooleanExtra("auto_play", true));
                this.autoPlay = valueOf;
                if (valueOf.booleanValue()) {
                    report(com.appnext.ads.a.ae);
                } else {
                    report(com.appnext.ads.a.af);
                }
            }
            if (getIntent().hasExtra("can_close")) {
                this.canClose = Boolean.valueOf(getIntent().getBooleanExtra("can_close", false));
            }
            if (getIntent().hasExtra("mute")) {
                Boolean valueOf2 = Boolean.valueOf(getIntent().getBooleanExtra("mute", true));
                this.mute = valueOf2;
                if (valueOf2.booleanValue()) {
                    report(com.appnext.ads.a.ag);
                } else {
                    report(com.appnext.ads.a.ah);
                }
            }
            if (getIntent().hasExtra("pview")) {
                this.gj = getIntent().getStringExtra("pview");
                this.banner = getIntent().getStringExtra("banner");
                this.guid = getIntent().getStringExtra("guid");
            }
            if (getIntent().getSerializableExtra("ads") != null) {
                this.ads = (ArrayList) getIntent().getSerializableExtra("ads");
            }
            this.bV = new Handler();
            AppnextWebView.u(this).a(this.bR.getPageUrl(), (AppnextWebView.c) new AppnextWebView.c() {
                public final void f(String str) {
                    InterstitialActivity.a(InterstitialActivity.this);
                }

                public final void error(String str) {
                    InterstitialActivity.a(InterstitialActivity.this);
                }
            });
            this.bW = new e.a() {
                public final void onMarket(String str) {
                    new StringBuilder("marketUrl ").append(str);
                    if (InterstitialActivity.this.handler != null) {
                        InterstitialActivity.this.handler.removeCallbacks((Runnable) null);
                    }
                    InterstitialActivity.this.ba();
                }

                public final void error(String str) {
                    if (InterstitialActivity.this.handler != null) {
                        InterstitialActivity.this.handler.removeCallbacks((Runnable) null);
                    }
                    InterstitialActivity.this.ba();
                    String unused = InterstitialActivity.this.placementID;
                    StringBuilder sb = new StringBuilder();
                    sb.append(new InterstitialAd(InterstitialActivity.this.aE).getAppURL());
                    sb.append(" ");
                    sb.append(str);
                }
            };
            this.userAction = new q(this, new q.a() {
                public final void report(String str) {
                }

                public final Ad e() {
                    return InterstitialActivity.this.bR;
                }

                public final AppnextAd f() {
                    return InterstitialActivity.this.aE;
                }

                public final p g() {
                    return InterstitialActivity.this.getConfig();
                }
            });
            new Thread(new Runnable() {
                public final void run() {
                    InterstitialActivity interstitialActivity = InterstitialActivity.this;
                    String unused = interstitialActivity.cc = f.b((Context) interstitialActivity, true);
                }
            }).start();
        } catch (Throwable unused) {
            finish();
        }
    }

    private void v() {
        try {
            AppnextWebView u = AppnextWebView.u(this);
            this.bP = u.ai(this.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            WebView a2 = u.a(this, this.bR.getPageUrl(), w(), this.bR.getFallback(), this.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            this.bP = a2;
            a2.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str == null) {
                        return false;
                    }
                    if (!str.startsWith(Constants.HTTP)) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    webView.loadUrl(str);
                    return true;
                }

                public final void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    InterstitialActivity.this.bV.removeCallbacksAndMessages((Object) null);
                    InterstitialActivity.l(InterstitialActivity.this);
                }
            });
            this.bP.setWebChromeClient(new WebChromeClient() {
                public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    new StringBuilder("console ").append(consoleMessage.message());
                    if (consoleMessage.message().contains("pause")) {
                        return true;
                    }
                    if (!consoleMessage.message().contains("TypeError") && !consoleMessage.message().contains("has no method") && !consoleMessage.message().contains("is not a function")) {
                        return true;
                    }
                    InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                    InterstitialActivity.this.finish();
                    return true;
                }
            });
        } catch (Throwable unused) {
            onError(AppnextError.INTERNAL_ERROR);
            finish();
        }
    }

    private void pageFinished() {
        Handler handler = this.bV;
        if (handler != null) {
            handler.removeCallbacks(this.ce);
        }
        this.bQ = true;
        String string = getIntent().getExtras().getString("creative");
        this.bT = string;
        if (string == null || string.equals(Interstitial.TYPE_MANAGED)) {
            this.bT = d("creative");
        }
        new Thread(new Runnable() {
            public final void run() {
                InterstitialActivity.this.x();
            }
        }).start();
        WebView webView = this.bP;
        if (webView == null) {
            onError(AppnextError.INTERNAL_ERROR);
            finish();
            return;
        }
        if (webView.getParent() != null) {
            ((ViewGroup) this.bP.getParent()).removeView(this.bP);
        }
        this.gl.addView(this.bP);
        this.bP.getLayoutParams().width = -1;
        this.bP.getLayoutParams().height = -1;
    }

    /* access modifiers changed from: protected */
    public final p getConfig() {
        return c.K();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Boolean bool;
        super.onResume();
        if (this.cd) {
            onClose();
            finish();
            return;
        }
        if (this.bQ && (bool = this.autoPlay) != null && bool.booleanValue()) {
            loadJS("Appnext.Layout.Video.play();");
        }
        try {
            this.bP.loadUrl("javascript:(function() { try{Appnext.countToClose();}catch(e){}})()");
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        WebView webView;
        super.onPause();
        if (!this.closed && (webView = this.bP) != null) {
            webView.loadUrl("javascript:(function() { Appnext.Layout.Video.pause();})()");
        }
    }

    public void onBackPressed() {
        Boolean bool = this.canClose;
        if (bool != null) {
            if (!bool.booleanValue()) {
                return;
            }
        } else if (!Boolean.parseBoolean(d("can_close"))) {
            return;
        }
        loadJS("Appnext.Layout.destroy('internal');");
        this.closed = true;
        onClose();
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onError(final String str) {
        runOnUiThread(new Runnable() {
            public final void run() {
                if (InterstitialActivity.this.bR != null && InterstitialActivity.this.bR.getOnAdErrorCallback() != null) {
                    InterstitialActivity.this.bR.getOnAdErrorCallback().adError(str);
                }
            }
        });
    }

    private void e(String str) {
        a.G();
        AppnextAd appnextAd = (AppnextAd) a.parseAd(str);
        if (appnextAd != null) {
            this.aE = new InterstitialAd(appnextAd);
            Interstitial interstitial = this.bR;
            if (!(interstitial == null || interstitial.getOnAdClickedCallback() == null)) {
                this.bR.getOnAdClickedCallback().adClicked();
            }
            b(this.aE, this.bW);
            report(com.appnext.ads.a.V);
            String bannerID = this.aE.getBannerID();
            InterstitialAd interstitialAd = this.bS;
            if (bannerID.equals(interstitialAd != null ? interstitialAd.getBannerID() : "")) {
                if (!this.bY) {
                    this.bY = true;
                    report(com.appnext.ads.a.al);
                }
            } else if (!this.bZ) {
                this.bZ = true;
                report(com.appnext.ads.a.ak);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void b(AppnextAd appnextAd, e.a aVar) {
        a((ViewGroup) this.gl, getResources().getDrawable(R.drawable.apnxt_loader));
        super.b(appnextAd, aVar);
    }

    /* access modifiers changed from: protected */
    public final WebAppInterface w() {
        if (this.bX == null) {
            this.bX = new WebInterface();
        }
        return this.bX;
    }

    protected class WebInterface extends WebAppInterface {
        @JavascriptInterface
        public String filterAds(String str) {
            return str;
        }

        @JavascriptInterface
        public void gotoAppWall() {
        }

        @JavascriptInterface
        public String loadAds() {
            return "";
        }

        @JavascriptInterface
        public void videoPlayed() {
        }

        public WebInterface() {
            super(InterstitialActivity.this);
        }

        @JavascriptInterface
        public void destroy(String str) {
            if (str.equals("c_close")) {
                boolean unused = InterstitialActivity.this.cd = true;
                InterstitialActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        try {
                            JSONObject jSONObject = new JSONObject(InterstitialActivity.this.bS.getAdJSON());
                            jSONObject.put("urlApp", jSONObject.getString("urlApp") + "&tem_id=156");
                            InterstitialActivity.b(InterstitialActivity.this, jSONObject.toString());
                        } catch (JSONException unused) {
                            InterstitialActivity.b(InterstitialActivity.this, InterstitialActivity.this.bS.getAdJSON());
                        }
                    }
                });
            } else if (str.equals("close")) {
                InterstitialActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        InterstitialActivity.this.onClose();
                        InterstitialActivity.this.finish();
                    }
                });
            } else {
                InterstitialActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        InterstitialActivity.this.onError(AdsError.AD_NOT_READY);
                        InterstitialActivity.this.finish();
                    }
                });
            }
        }

        @JavascriptInterface
        public void notifyImpression(String str) {
            super.notifyImpression(str);
            if (InterstitialActivity.this.bS != null) {
                InterstitialActivity.this.bS.setImpressionURL(str);
                InterstitialActivity.this.handler.postDelayed(new a(str), Long.parseLong(InterstitialActivity.this.getConfig().get("postpone_impression_sec")) * 1000);
            }
            if (InterstitialActivity.this.autoPlay != null && InterstitialActivity.this.autoPlay.booleanValue()) {
                play();
            }
        }

        @JavascriptInterface
        public void postView(String str) {
            if (Boolean.parseBoolean(InterstitialActivity.this.ads != null ? "false" : InterstitialActivity.this.d("pview"))) {
                InterstitialActivity.this.handler.postDelayed(new b(str), Long.parseLong(InterstitialActivity.this.getConfig().get("postpone_vta_sec")) * 1000);
            }
        }

        @JavascriptInterface
        public void openStore(final String str) {
            InterstitialActivity.this.runOnUiThread(new Runnable() {
                public final void run() {
                    InterstitialActivity.b(InterstitialActivity.this, str);
                }
            });
        }

        @JavascriptInterface
        public void play() {
            String unused = InterstitialActivity.this.placementID;
            InterstitialActivity.this.loadJS("Appnext.Layout.Video.play();");
        }

        @JavascriptInterface
        public void openLink(String str) {
            InterstitialActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }

        @JavascriptInterface
        public void logSTP(String str, String str2) {
            f.a((Ad) InterstitialActivity.this.bR, (AppnextAd) InterstitialActivity.this.bS, str, str2, InterstitialActivity.this.getConfig());
        }

        @JavascriptInterface
        public void jsError(String str) {
            if (!TextUtils.isEmpty(str) || (!str.contains("is not a function") && !str.contains("has no method"))) {
                new StringBuilder("jsError ").append(str);
                InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                InterstitialActivity.this.finish();
            } else if (InterstitialActivity.u(InterstitialActivity.this) < 5) {
                InterstitialActivity.this.bV.postDelayed(InterstitialActivity.this.ce, 500);
            } else {
                InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                InterstitialActivity.this.finish();
            }
        }

        @JavascriptInterface
        public void openResultPage(String str) {
            d.br().a(new c() {
                public final String z() {
                    return "160";
                }

                public final JSONObject getConfigParams() throws JSONException {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("vid", "2.5.1.472");
                    String str = "";
                    jSONObject.put("tid", InterstitialActivity.this.bR == null ? str : InterstitialActivity.this.bR.getTID());
                    if (InterstitialActivity.this.bR != null) {
                        str = InterstitialActivity.this.bR.getAUID();
                    }
                    jSONObject.put("auid", str);
                    jSONObject.put("osid", "100");
                    jSONObject.put("tem_id", "1601");
                    jSONObject.put("id", getPlacementId());
                    jSONObject.put("cat", InterstitialActivity.this.bS.getCategories());
                    jSONObject.put("pview", InterstitialActivity.this.getConfig().get("pview"));
                    jSONObject.put("devn", f.be());
                    jSONObject.put("dosv", Build.VERSION.SDK_INT);
                    jSONObject.put("dds", "0");
                    jSONObject.put("ads_type", "banner");
                    jSONObject.put(ImpressionData.COUNTRY, InterstitialActivity.this.bS.getCountry());
                    jSONObject.put("gdpr", k.a((AppnextAd) InterstitialActivity.this.bS, InterstitialActivity.this.getConfig()));
                    return jSONObject;
                }

                public final AppnextAd getSelectedAd() {
                    return InterstitialActivity.this.bS;
                }

                public final String getPlacementId() {
                    return InterstitialActivity.this.placementID;
                }

                public final String A() {
                    return a.G().l((Ad) InterstitialActivity.this.bR);
                }

                public final String B() {
                    return InterstitialActivity.this.ca;
                }

                public final p C() {
                    return InterstitialActivity.this.getConfig();
                }

                public final Ad D() {
                    return InterstitialActivity.this.bR;
                }

                public final com.appnext.core.result.a E() {
                    return new com.appnext.core.result.a() {
                        public final Object F() {
                            return null;
                        }

                        public final String getFallbackScript() {
                            return null;
                        }

                        public final String getJSurl() {
                            return "https://cdn.appnext.com/tools/sdk/interstitial/v75/result.min.js";
                        }

                        public final WebViewClient getWebViewClient() {
                            return null;
                        }
                    };
                }
            });
            Intent intent = new Intent(InterstitialActivity.this, ResultPageActivity.class);
            intent.putExtra("shouldClose", false);
            intent.setFlags(65536);
            InterstitialActivity.this.startActivity(intent);
        }
    }

    private class a implements Runnable {
        AppnextAd ch;

        a(String str) {
            InterstitialAd interstitialAd = new InterstitialAd(InterstitialActivity.this.bS);
            this.ch = interstitialAd;
            interstitialAd.setImpressionURL(str);
        }

        public final void run() {
            try {
                if (InterstitialActivity.this.userAction != null) {
                    InterstitialActivity.this.userAction.e(this.ch);
                    InterstitialActivity.this.report(com.appnext.ads.a.G);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private class b implements Runnable {
        AppnextAd ch;

        b(String str) {
            try {
                a.G();
                this.ch = (AppnextAd) a.parseAd(str);
            } catch (Throwable unused) {
            }
        }

        public final void run() {
            InterstitialActivity.this.a(this.ch, (e.a) null);
        }
    }

    private void play() {
        loadJS("Appnext.Layout.Video.play();");
    }

    private void stop() {
        WebView webView = this.bP;
        if (webView != null) {
            webView.loadUrl("javascript:(function() { Appnext.Layout.Video.pause();})()");
        }
    }

    /* access modifiers changed from: private */
    public void onClose() {
        Interstitial interstitial = this.bR;
        if (interstitial != null && interstitial.getOnAdClosedCallback() != null) {
            this.bR.getOnAdClosedCallback().onAdClosed();
        }
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        Interstitial interstitial = this.bR;
        if (interstitial != null) {
            String tid = interstitial.getTID();
            String vid = this.bR.getVID();
            String auid = this.bR.getAUID();
            String placementID = this.bR.getPlacementID();
            String sessionId = this.bR.getSessionId();
            InterstitialAd interstitialAd = this.bS;
            String bannerID = interstitialAd != null ? interstitialAd.getBannerID() : "";
            InterstitialAd interstitialAd2 = this.bS;
            f.a(tid, vid, auid, placementID, sessionId, str, "current_interstitial", bannerID, interstitialAd2 != null ? interstitialAd2.getCampaignID() : "");
        }
    }

    /* access modifiers changed from: private */
    public void loadJS(final String str) {
        runOnUiThread(new Runnable() {
            public final void run() {
                if (InterstitialActivity.this.bP != null) {
                    WebView webView = InterstitialActivity.this.bP;
                    webView.loadUrl("javascript:(function() { try { " + str + "} catch(err){ Appnext.jsError(err.message); }})()");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b8 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d6 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ee A[Catch:{ all -> 0x0142 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void x() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.appnext.ads.interstitial.a r0 = com.appnext.ads.interstitial.a.G()     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.Interstitial r1 = r8.bR     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = r8.bT     // Catch:{ all -> 0x0142 }
            java.util.ArrayList r0 = r0.b(r8, r1, r2)     // Catch:{ all -> 0x0142 }
            if (r0 != 0) goto L_0x0019
            r8.finish()     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = "No Ads"
            r8.onError(r0)     // Catch:{ all -> 0x0142 }
            monitor-exit(r8)
            return
        L_0x0019:
            com.appnext.ads.interstitial.a r1 = com.appnext.ads.interstitial.a.G()     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = r1.a(r0)     // Catch:{ all -> 0x0142 }
            if (r1 != 0) goto L_0x002d
            r8.finish()     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = "No Ads"
            r8.onError(r0)     // Catch:{ all -> 0x0142 }
            monitor-exit(r8)
            return
        L_0x002d:
            java.lang.String r2 = " "
            java.lang.String r3 = "\\u2028"
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = " "
            java.lang.String r3 = "\\u2029"
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ all -> 0x0142 }
            r8.ca = r1     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.InterstitialAd r2 = new com.appnext.ads.interstitial.InterstitialAd     // Catch:{ all -> 0x0142 }
            r3 = 0
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x0142 }
            com.appnext.core.AppnextAd r4 = (com.appnext.core.AppnextAd) r4     // Catch:{ all -> 0x0142 }
            r2.<init>(r4)     // Catch:{ all -> 0x0142 }
            r8.bS = r2     // Catch:{ all -> 0x0142 }
            android.content.res.Resources r2 = r8.getResources()     // Catch:{ all -> 0x0142 }
            android.content.res.Configuration r2 = r2.getConfiguration()     // Catch:{ all -> 0x0142 }
            int r2 = r2.orientation     // Catch:{ all -> 0x0142 }
            org.json.JSONObject r2 = r8.y()     // Catch:{ all -> 0x0142 }
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x0142 }
            com.appnext.core.AppnextAd r4 = (com.appnext.core.AppnextAd) r4     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = r4.getVideoUrl()     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = ""
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0142 }
            r6 = 1
            if (r5 == 0) goto L_0x0095
            java.lang.String r5 = r4.getVideoUrlHigh()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = ""
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0142 }
            if (r5 == 0) goto L_0x0095
            java.lang.String r5 = r4.getVideoUrl30Sec()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = ""
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0142 }
            if (r5 == 0) goto L_0x0095
            java.lang.String r4 = r4.getVideoUrlHigh30Sec()     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = ""
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0142 }
            if (r4 != 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r4 = 0
            goto L_0x0096
        L_0x0095:
            r4 = 1
        L_0x0096:
            if (r4 == 0) goto L_0x00b8
            java.lang.String r4 = "remote_auto_play"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            java.lang.Boolean r7 = r8.autoPlay     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x00ac
            java.lang.Boolean r7 = r8.autoPlay     // Catch:{ all -> 0x0142 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            r6 = 0
        L_0x00ad:
            r5.append(r6)     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0142 }
            r2.put(r4, r5)     // Catch:{ all -> 0x0142 }
            goto L_0x00bf
        L_0x00b8:
            java.lang.String r4 = "remote_auto_play"
            java.lang.String r5 = "false"
            r2.put(r4, r5)     // Catch:{ all -> 0x0142 }
        L_0x00bf:
            com.appnext.ads.interstitial.InterstitialAd r4 = new com.appnext.ads.interstitial.InterstitialAd     // Catch:{ all -> 0x0142 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0142 }
            com.appnext.core.AppnextAd r0 = (com.appnext.core.AppnextAd) r0     // Catch:{ all -> 0x0142 }
            r4.<init>(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r4.getButtonText()     // Catch:{ all -> 0x0142 }
            java.lang.String r3 = ""
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x00ee
            java.lang.String r0 = r4.getAdPackage()     // Catch:{ all -> 0x0142 }
            boolean r0 = com.appnext.core.f.c(r8, r0)     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x00e7
            java.lang.String r0 = "existing_button_text"
            java.lang.String r0 = r8.d((java.lang.String) r0)     // Catch:{ all -> 0x0142 }
            goto L_0x00f2
        L_0x00e7:
            java.lang.String r0 = "new_button_text"
            java.lang.String r0 = r8.d((java.lang.String) r0)     // Catch:{ all -> 0x0142 }
            goto L_0x00f2
        L_0x00ee:
            java.lang.String r0 = r4.getButtonText()     // Catch:{ all -> 0x0142 }
        L_0x00f2:
            java.lang.String r3 = "b_title"
            r2.put(r3, r0)     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            java.lang.String r3 = "Appnext.setParams("
            r0.<init>(r3)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0142 }
            r0.append(r2)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = ");"
            r0.append(r2)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0142 }
            r8.loadJS(r0)     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = "Appnext.loadInterstitial("
            r0.<init>(r2)     // Catch:{ all -> 0x0142 }
            r0.append(r1)     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = ");"
            r0.append(r1)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0142 }
            r8.loadJS(r0)     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.a r0 = com.appnext.ads.interstitial.a.G()     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.InterstitialAd r1 = r8.bS     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = r1.getBannerID()     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.Interstitial r2 = com.appnext.ads.interstitial.Interstitial.currentAd     // Catch:{ all -> 0x0142 }
            r0.a((java.lang.String) r1, (com.appnext.core.Ad) r2)     // Catch:{ all -> 0x0142 }
            android.os.Handler r0 = r8.handler     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.InterstitialActivity$3 r1 = new com.appnext.ads.interstitial.InterstitialActivity$3     // Catch:{ all -> 0x0142 }
            r1.<init>()     // Catch:{ all -> 0x0142 }
            r0.post(r1)     // Catch:{ all -> 0x0142 }
            monitor-exit(r8)
            return
        L_0x0142:
            r0 = move-exception
            r8.finish()     // Catch:{ all -> 0x0150 }
            java.lang.String r1 = "Internal error"
            r8.onError(r1)     // Catch:{ all -> 0x0150 }
            com.appnext.core.f.b((java.lang.Throwable) r0)     // Catch:{ all -> 0x0150 }
            monitor-exit(r8)
            return
        L_0x0150:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.InterstitialActivity.x():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r3.put("icon_color", "");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x027d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject y() throws org.json.JSONException {
        /*
            r11 = this;
            java.lang.String r0 = "icon_color"
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getButtonColor()
            java.lang.String r2 = ""
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0017
            java.lang.String r1 = "button_color"
            java.lang.String r1 = r11.d((java.lang.String) r1)
            goto L_0x001d
        L_0x0017:
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getButtonColor()
        L_0x001d:
            java.lang.String r3 = "#"
            boolean r3 = r1.startsWith(r3)
            r4 = 1
            if (r3 == 0) goto L_0x002a
            java.lang.String r1 = r1.substring(r4)
        L_0x002a:
            java.lang.Boolean r3 = r11.autoPlay
            java.lang.String r5 = "auto_play"
            if (r3 != 0) goto L_0x003e
            java.lang.String r3 = r11.d((java.lang.String) r5)
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r11.autoPlay = r3
        L_0x003e:
            java.lang.Boolean r3 = r11.mute
            java.lang.String r6 = "mute"
            if (r3 != 0) goto L_0x0052
            java.lang.String r3 = r11.d((java.lang.String) r6)
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r11.mute = r3
        L_0x0052:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r7 = r11.placementID
            java.lang.String r8 = "id"
            r3.put(r8, r7)
            com.appnext.ads.interstitial.Interstitial r7 = r11.bR
            java.lang.String r7 = r7.getCategories()
            java.lang.String r8 = "cat"
            r3.put(r8, r7)
            com.appnext.ads.interstitial.Interstitial r7 = r11.bR
            java.lang.String r7 = r7.getPostback()
            java.lang.String r8 = "pbk"
            r3.put(r8, r7)
            java.lang.String r7 = "b_color"
            r3.put(r7, r1)
            java.util.ArrayList<com.appnext.core.AppnextAd> r1 = r11.ads
            java.lang.String r7 = "show_desc"
            if (r1 != 0) goto L_0x010d
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getSkipText()
            boolean r1 = r1.equals(r2)
            java.lang.String r8 = "skip_title"
            if (r1 == 0) goto L_0x0092
            java.lang.String r1 = r11.d((java.lang.String) r8)
            goto L_0x0098
        L_0x0092:
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getSkipText()
        L_0x0098:
            r3.put(r8, r1)
            java.util.ArrayList<com.appnext.core.AppnextAd> r1 = r11.ads
            java.lang.String r8 = "pview"
            if (r1 == 0) goto L_0x00a4
            java.lang.String r1 = "false"
            goto L_0x00a8
        L_0x00a4:
            java.lang.String r1 = r11.d((java.lang.String) r8)
        L_0x00a8:
            r3.put(r8, r1)
            java.lang.String r1 = "video_length"
            java.lang.String r8 = r11.d((java.lang.String) r1)
            r3.put(r1, r8)
            java.lang.String r1 = "min_internet_connection"
            java.lang.String r8 = r11.d((java.lang.String) r1)
            r3.put(r1, r8)
            java.lang.String r1 = "min_internet_connection_video"
            java.lang.String r8 = r11.d((java.lang.String) r1)
            r3.put(r1, r8)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.Boolean r8 = r11.mute
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r3.put(r6, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.Boolean r6 = r11.autoPlay
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r3.put(r5, r1)
            java.lang.String r1 = "remove_poster_on_auto_play"
            java.lang.String r5 = r11.d((java.lang.String) r1)
            r3.put(r1, r5)
            java.lang.String r1 = "show_rating"
            java.lang.String r5 = r11.d((java.lang.String) r1)
            r3.put(r1, r5)
            java.lang.String r1 = r11.d((java.lang.String) r7)
            r3.put(r7, r1)
            java.lang.String r1 = r11.bT
            java.lang.String r5 = "creative"
            r3.put(r5, r1)
            java.lang.String r1 = "remote_auto_play"
            r3.put(r1, r4)
        L_0x010d:
            java.lang.String r1 = "stp_flag"
            java.lang.String r5 = r11.d((java.lang.String) r1)
            r3.put(r1, r5)
            java.lang.String r1 = "ext"
            java.lang.String r5 = "t"
            r3.put(r1, r5)
            java.lang.String r1 = com.appnext.core.f.o((android.content.Context) r11)
            java.lang.String r5 = "dct"
            r3.put(r5, r1)
            java.lang.String r1 = r11.cc
            java.lang.String r5 = "did"
            r3.put(r5, r1)
            java.lang.String r1 = com.appnext.core.f.be()
            java.lang.String r5 = "devn"
            r3.put(r5, r1)
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r5 = "dosv"
            r3.put(r5, r1)
            java.lang.String r1 = "dds"
            java.lang.String r5 = "0"
            r3.put(r1, r5)
            java.lang.String r1 = "urlApp_protection"
            java.lang.String r5 = r11.d((java.lang.String) r1)
            r3.put(r1, r5)
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getVID()
            java.lang.String r5 = "vid"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getTID()
            java.lang.String r5 = "tid"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getAUID()
            java.lang.String r5 = "auid"
            r3.put(r5, r1)
            java.lang.String r1 = "osid"
            java.lang.String r5 = "100"
            r3.put(r1, r5)
            java.lang.String r1 = "ads_type"
            java.lang.String r5 = "interstitial"
            r3.put(r1, r5)
            com.appnext.ads.interstitial.InterstitialAd r1 = r11.bS
            java.lang.String r1 = r1.getCountry()
            java.lang.String r5 = "country"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.InterstitialAd r1 = r11.bS
            com.appnext.ads.interstitial.c r5 = com.appnext.ads.interstitial.c.K()
            boolean r1 = com.appnext.core.k.a((com.appnext.core.AppnextAd) r1, (com.appnext.core.p) r5)
            java.lang.String r5 = "gdpr"
            r3.put(r5, r1)
            org.json.JSONObject r1 = new org.json.JSONObject
            com.appnext.core.a.b r5 = com.appnext.core.a.b.bp()
            java.lang.String r5 = r5.bq()
            r1.<init>(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r5 = "lang_settings"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.Interstitial r1 = r11.bR
            java.lang.String r1 = r1.getLanguage()
            if (r1 == 0) goto L_0x01ba
            boolean r5 = r1.equals(r2)
            if (r5 == 0) goto L_0x01c6
        L_0x01ba:
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r1 = r1.getLanguage()
            java.lang.String r1 = r1.toUpperCase()
        L_0x01c6:
            java.lang.String r5 = "lang"
            r3.put(r5, r1)
            org.json.JSONArray r1 = new org.json.JSONArray
            java.lang.String r5 = "S1"
            java.lang.String r5 = r11.d((java.lang.String) r5)
            r1.<init>(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r5 = "tem"
            r3.put(r5, r1)
            java.lang.String r1 = "clickType_A"
            java.lang.String r1 = r11.d((java.lang.String) r1)
            java.lang.String r5 = "click_x"
            r3.put(r5, r1)
            android.content.Intent r1 = r11.getIntent()
            if (r1 == 0) goto L_0x0205
            android.content.Intent r1 = r11.getIntent()
            boolean r1 = r1.hasExtra(r7)
            if (r1 == 0) goto L_0x0205
            android.content.Intent r1 = r11.getIntent()
            java.lang.String r1 = r1.getStringExtra(r7)
            r3.put(r7, r1)
        L_0x0205:
            com.appnext.ads.interstitial.InterstitialAd r1 = r11.bS     // Catch:{ all -> 0x0285 }
            java.lang.String r1 = r1.getImageURL()     // Catch:{ all -> 0x0285 }
            android.graphics.Bitmap r1 = com.appnext.core.f.Y(r1)     // Catch:{ all -> 0x0285 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0285 }
            r5.<init>()     // Catch:{ all -> 0x0285 }
            android.graphics.Bitmap$CompressFormat r6 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0285 }
            r7 = 100
            r1.compress(r6, r7, r5)     // Catch:{ all -> 0x0285 }
            byte[] r5 = r5.toByteArray()     // Catch:{ all -> 0x0285 }
            r6 = 0
            java.lang.String r5 = android.util.Base64.encodeToString(r5, r6)     // Catch:{ all -> 0x0285 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0285 }
            java.lang.String r8 = "data:image/"
            r7.<init>(r8)     // Catch:{ all -> 0x0285 }
            com.appnext.ads.interstitial.InterstitialAd r8 = r11.bS     // Catch:{ all -> 0x0285 }
            java.lang.String r8 = r8.getImageURL()     // Catch:{ all -> 0x0285 }
            com.appnext.ads.interstitial.InterstitialAd r9 = r11.bS     // Catch:{ all -> 0x0285 }
            java.lang.String r9 = r9.getImageURL()     // Catch:{ all -> 0x0285 }
            r10 = 46
            int r9 = r9.lastIndexOf(r10)     // Catch:{ all -> 0x0285 }
            int r9 = r9 + r4
            java.lang.String r8 = r8.substring(r9)     // Catch:{ all -> 0x0285 }
            r7.append(r8)     // Catch:{ all -> 0x0285 }
            java.lang.String r8 = ";base64,"
            r7.append(r8)     // Catch:{ all -> 0x0285 }
            r7.append(r5)     // Catch:{ all -> 0x0285 }
            java.lang.String r5 = r7.toString()     // Catch:{ all -> 0x0285 }
            androidx.palette.graphics.Palette$Builder r1 = androidx.palette.graphics.Palette.from(r1)     // Catch:{ all -> 0x027d }
            androidx.palette.graphics.Palette r1 = r1.generate()     // Catch:{ all -> 0x027d }
            androidx.palette.graphics.Palette$Swatch r1 = r1.getVibrantSwatch()     // Catch:{ all -> 0x027d }
            if (r1 == 0) goto L_0x0279
            int r1 = r1.getRgb()     // Catch:{ all -> 0x027d }
            java.lang.String r7 = "#%06X"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x027d }
            r8 = 16777215(0xffffff, float:2.3509886E-38)
            r1 = r1 & r8
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x027d }
            r4[r6] = r1     // Catch:{ all -> 0x027d }
            java.lang.String r1 = java.lang.String.format(r7, r4)     // Catch:{ all -> 0x027d }
            r3.put(r0, r1)     // Catch:{ all -> 0x027d }
            goto L_0x0280
        L_0x0279:
            r3.put(r0, r2)     // Catch:{ all -> 0x027d }
            goto L_0x0280
        L_0x027d:
            r3.put(r0, r2)     // Catch:{ all -> 0x0285 }
        L_0x0280:
            java.lang.String r0 = "icon_src"
            r3.put(r0, r5)     // Catch:{ all -> 0x0285 }
        L_0x0285:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.InterstitialActivity.y():org.json.JSONObject");
    }

    private static String a(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & 16777215)});
    }

    private static boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            a.G().g(this.bR);
            this.bR.destroy();
            this.bR = null;
            if (this.bV != null) {
                this.bV.removeCallbacksAndMessages((Object) null);
            }
            this.bV = null;
            this.ce = null;
            this.bS = null;
            if (this.bP != null) {
                this.bP.stopLoading();
                if (this.bP.getParent() != null) {
                    ((ViewGroup) this.bP.getParent()).removeView(this.bP);
                }
                this.bP.setWebChromeClient((WebChromeClient) null);
                this.bP.setWebViewClient((WebViewClient) null);
                this.bP.destroy();
                this.bP = null;
            }
            AppnextWebView.u(this).a(w());
            this.bX = null;
            this.bW = null;
            if (this.aF != null) {
                this.aF.a((Context) this);
                this.aF = null;
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: private */
    public String d(String str) {
        String str2 = c.K().get(str);
        return str2 == null ? "" : str2;
    }

    static /* synthetic */ void a(InterstitialActivity interstitialActivity) {
        try {
            AppnextWebView u = AppnextWebView.u(interstitialActivity);
            interstitialActivity.bP = u.ai(interstitialActivity.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            WebView a2 = u.a(interstitialActivity, interstitialActivity.bR.getPageUrl(), interstitialActivity.w(), interstitialActivity.bR.getFallback(), interstitialActivity.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            interstitialActivity.bP = a2;
            a2.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str == null) {
                        return false;
                    }
                    if (!str.startsWith(Constants.HTTP)) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    webView.loadUrl(str);
                    return true;
                }

                public final void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    InterstitialActivity.this.bV.removeCallbacksAndMessages((Object) null);
                    InterstitialActivity.l(InterstitialActivity.this);
                }
            });
            interstitialActivity.bP.setWebChromeClient(new WebChromeClient() {
                public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    new StringBuilder("console ").append(consoleMessage.message());
                    if (consoleMessage.message().contains("pause")) {
                        return true;
                    }
                    if (!consoleMessage.message().contains("TypeError") && !consoleMessage.message().contains("has no method") && !consoleMessage.message().contains("is not a function")) {
                        return true;
                    }
                    InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                    InterstitialActivity.this.finish();
                    return true;
                }
            });
        } catch (Throwable unused) {
            interstitialActivity.onError(AppnextError.INTERNAL_ERROR);
            interstitialActivity.finish();
        }
    }

    static /* synthetic */ void l(InterstitialActivity interstitialActivity) {
        Handler handler = interstitialActivity.bV;
        if (handler != null) {
            handler.removeCallbacks(interstitialActivity.ce);
        }
        interstitialActivity.bQ = true;
        String string = interstitialActivity.getIntent().getExtras().getString("creative");
        interstitialActivity.bT = string;
        if (string == null || string.equals(Interstitial.TYPE_MANAGED)) {
            interstitialActivity.bT = interstitialActivity.d("creative");
        }
        new Thread(new Runnable() {
            public final void run() {
                InterstitialActivity.this.x();
            }
        }).start();
        WebView webView = interstitialActivity.bP;
        if (webView == null) {
            interstitialActivity.onError(AppnextError.INTERNAL_ERROR);
            interstitialActivity.finish();
            return;
        }
        if (webView.getParent() != null) {
            ((ViewGroup) interstitialActivity.bP.getParent()).removeView(interstitialActivity.bP);
        }
        interstitialActivity.gl.addView(interstitialActivity.bP);
        interstitialActivity.bP.getLayoutParams().width = -1;
        interstitialActivity.bP.getLayoutParams().height = -1;
    }

    static /* synthetic */ void b(InterstitialActivity interstitialActivity, String str) {
        a.G();
        AppnextAd appnextAd = (AppnextAd) a.parseAd(str);
        if (appnextAd != null) {
            interstitialActivity.aE = new InterstitialAd(appnextAd);
            Interstitial interstitial = interstitialActivity.bR;
            if (!(interstitial == null || interstitial.getOnAdClickedCallback() == null)) {
                interstitialActivity.bR.getOnAdClickedCallback().adClicked();
            }
            interstitialActivity.b(interstitialActivity.aE, interstitialActivity.bW);
            interstitialActivity.report(com.appnext.ads.a.V);
            String bannerID = interstitialActivity.aE.getBannerID();
            InterstitialAd interstitialAd = interstitialActivity.bS;
            if (bannerID.equals(interstitialAd != null ? interstitialAd.getBannerID() : "")) {
                if (!interstitialActivity.bY) {
                    interstitialActivity.bY = true;
                    interstitialActivity.report(com.appnext.ads.a.al);
                }
            } else if (!interstitialActivity.bZ) {
                interstitialActivity.bZ = true;
                interstitialActivity.report(com.appnext.ads.a.ak);
            }
        }
    }
}
