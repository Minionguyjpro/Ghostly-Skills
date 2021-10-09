package com.startapp.android.publish.ads.banner.bannerstandard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerBase;
import com.startapp.android.publish.ads.banner.BannerInterface;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.ads.banner.BannerOptions;
import com.startapp.android.publish.ads.banner.c;
import com.startapp.android.publish.ads.banner.d;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.Utils.h;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adinformation.b;
import com.startapp.android.publish.adsCommon.f.f;
import com.startapp.android.publish.adsCommon.i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.html.JsInterface;
import com.startapp.android.publish.omsdk.a;
import com.startapp.common.a.g;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class BannerStandard extends BannerBase implements BannerInterface, AdEventListener {
    private static final int ID_WEBVIEW = 159868225;
    private static final String TAG = "BannerHtml";
    protected a adHtml;
    /* access modifiers changed from: private */
    public RelativeLayout adInformationContatiner;
    private b adInformationLayout;
    protected AdPreferences adPreferences;
    /* access modifiers changed from: private */
    public com.b.a.a.a.b.b adSession;
    private boolean callbackSent;
    private boolean defaultLoad;
    private boolean initBannerCalled;
    /* access modifiers changed from: private */
    public boolean jsTag;
    protected BannerListener listener;
    private boolean loaded;
    private BannerOptions options;
    private i scheduledImpression;
    private d size;
    private boolean visible;
    protected WebView webView;
    /* access modifiers changed from: private */
    public boolean webViewTouched;

    /* access modifiers changed from: protected */
    public String getBannerName() {
        return "StartApp Banner";
    }

    /* access modifiers changed from: protected */
    public int getBannerType() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getHeightInDp() {
        return 50;
    }

    /* access modifiers changed from: protected */
    public int getWidthInDp() {
        return 300;
    }

    public BannerStandard(Activity activity) {
        this((Context) activity);
    }

    public BannerStandard(Activity activity, AdPreferences adPreferences2) {
        this((Context) activity, adPreferences2);
    }

    public BannerStandard(Activity activity, BannerListener bannerListener) {
        this((Context) activity, bannerListener);
    }

    public BannerStandard(Activity activity, AdPreferences adPreferences2, BannerListener bannerListener) {
        this((Context) activity, adPreferences2, bannerListener);
    }

    public BannerStandard(Activity activity, boolean z) {
        this((Context) activity, z);
    }

    public BannerStandard(Activity activity, boolean z, AdPreferences adPreferences2) {
        this((Context) activity, z, adPreferences2);
    }

    public BannerStandard(Activity activity, AttributeSet attributeSet) {
        this((Context) activity, attributeSet);
    }

    public BannerStandard(Activity activity, AttributeSet attributeSet, int i) {
        this((Context) activity, attributeSet, i);
    }

    @Deprecated
    public BannerStandard(Context context) {
        this(context, true, (AdPreferences) null);
    }

    @Deprecated
    public BannerStandard(Context context, AdPreferences adPreferences2) {
        this(context, true, adPreferences2);
    }

    @Deprecated
    public BannerStandard(Context context, BannerListener bannerListener) {
        this(context, true, (AdPreferences) null);
        setBannerListener(bannerListener);
    }

    @Deprecated
    public BannerStandard(Context context, AdPreferences adPreferences2, BannerListener bannerListener) {
        this(context, true, adPreferences2);
        setBannerListener(bannerListener);
    }

    @Deprecated
    public BannerStandard(Context context, boolean z) {
        this(context, z, (AdPreferences) null);
    }

    @Deprecated
    public BannerStandard(Context context, boolean z, AdPreferences adPreferences2) {
        super(context);
        this.loaded = false;
        this.webViewTouched = true;
        this.jsTag = false;
        this.defaultLoad = true;
        this.visible = true;
        this.initBannerCalled = false;
        this.callbackSent = false;
        this.adInformationLayout = null;
        this.adInformationContatiner = null;
        try {
            this.defaultLoad = z;
            this.adPreferences = adPreferences2;
            init();
        } catch (Exception e) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "BannerStandard.constructor - unexpected error occurd", e.getMessage(), "");
        }
    }

    @Deprecated
    public BannerStandard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public BannerStandard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.loaded = false;
        this.webViewTouched = true;
        this.jsTag = false;
        this.defaultLoad = true;
        this.visible = true;
        this.initBannerCalled = false;
        this.callbackSent = false;
        this.adInformationLayout = null;
        this.adInformationContatiner = null;
        try {
            init();
        } catch (Exception e) {
            f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "BannerStandard.constructor - unexpected error occurd", e.getMessage(), "");
        }
    }

    private void addAdInformationLayout() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        layoutParams.addRule(13);
        if (this.adInformationLayout == null && this.adInformationContatiner == null) {
            this.adInformationContatiner = new RelativeLayout(getContext());
            b bVar = new b(getContext(), b.C0003b.SMALL, AdPreferences.Placement.INAPP_BANNER, this.adHtml.getAdInfoOverride());
            this.adInformationLayout = bVar;
            bVar.a(this.adInformationContatiner);
        }
        try {
            ViewGroup viewGroup = (ViewGroup) this.adInformationContatiner.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.adInformationContatiner);
            }
        } catch (Exception unused) {
        }
        addView(this.adInformationContatiner, layoutParams);
    }

    public void hideBanner() {
        this.visible = false;
        setVisibility(8);
    }

    public void showBanner() {
        this.visible = true;
        setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void initRuntime() {
        try {
            this.options = new BannerOptions();
            this.adHtml = new a(getContext(), getOffset());
            if (this.adPreferences == null) {
                this.adPreferences = new AdPreferences();
            }
            this.size = new d(getWidthInDp(), getHeightInDp());
            this.webView = new WebView(getContext());
            if (getId() == -1) {
                setId(getBannerId());
            }
            this.webView.setId(ID_WEBVIEW);
            setVisibility(8);
            this.webView.setBackgroundColor(0);
            this.webView.setHorizontalScrollBarEnabled(false);
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.setVerticalScrollBarEnabled(false);
            this.webView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    boolean unused = BannerStandard.this.webViewTouched = true;
                    if (motionEvent.getAction() == 2) {
                        return true;
                    }
                    return false;
                }
            });
            this.webView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return true;
                }
            });
            this.webView.setLongClickable(false);
            this.options = c.a().c();
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    com.startapp.common.a.c.a(BannerStandard.this.getViewTreeObserver(), (ViewTreeObserver.OnGlobalLayoutListener) this);
                    BannerStandard bannerStandard = BannerStandard.this;
                    bannerStandard.setHardwareAcceleration(bannerStandard.adPreferences);
                    BannerStandard.this.initBanner();
                }
            });
        } catch (Exception e) {
            f.a(getContext(), com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "BannerStandard.init - webview failed", e.getMessage(), "");
            g.a(TAG, 6, "webVIew exception");
            hideBanner();
            onFailedToReceiveBanner("BannerStandard.init - webview failed");
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (layoutParams.width > 0 && layoutParams.height > 0) {
            new Handler().post(new Runnable() {
                public void run() {
                    BannerStandard.this.initBanner();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void initBanner() {
        if (!this.initBannerCalled && this.webView != null) {
            g.a(TAG, 3, "Initializing BannerHtml");
            this.initBannerCalled = true;
            int a2 = h.a(getContext(), this.size.a());
            int a3 = h.a(getContext(), this.size.b());
            setMinimumWidth(a2);
            setMinimumHeight(a3);
            this.webView.addJavascriptInterface(new JsInterface(getContext(), (Runnable) new Runnable() {
                public void run() {
                }
            }, new com.startapp.android.publish.adsCommon.d.b(getAdTag()), this.adHtml.e(0)), "startappwall");
            this.webView.setWebViewClient(new MyWebViewClient());
            if (this.loaded) {
                g.a(TAG, 3, "BannerHTML already Loaded");
                onReceiveAd(this.adHtml);
            } else if (this.defaultLoad) {
                loadBanner();
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a3);
            layoutParams.addRule(13);
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.addView(this.webView, layoutParams);
            if (getLayoutParams() != null) {
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(getLayoutParams().width, getLayoutParams().height);
                layoutParams2.addRule(13);
                addView(relativeLayout, layoutParams2);
                return;
            }
            addView(relativeLayout);
        }
    }

    /* access modifiers changed from: protected */
    public void reload() {
        g.a(TAG, 3, "Loading from network");
        com.b.a.a.a.b.b bVar = this.adSession;
        if (bVar != null) {
            bVar.b();
            this.adSession = null;
        }
        if (this.adPreferences == null) {
            this.adPreferences = new AdPreferences();
        }
        Point availableSize = getAvailableSize();
        this.adHtml.setState(Ad.AdState.UN_INITIALIZED);
        this.adHtml.a(availableSize.x, availableSize.y);
        this.adHtml.a(getBannerType());
        this.adHtml.load(this.adPreferences, this);
    }

    private Point getAvailableSize() {
        Point point = new Point();
        if (getLayoutParams() != null && getLayoutParams().width > 0) {
            point.x = h.b(getContext(), getLayoutParams().width + 1);
        }
        if (getLayoutParams() != null && getLayoutParams().height > 0) {
            point.y = h.b(getContext(), getLayoutParams().height + 1);
        }
        if (getLayoutParams() != null && getLayoutParams().width > 0 && getLayoutParams().height > 0) {
            this.adHtml.a(true);
        }
        if (getLayoutParams() == null || getLayoutParams().width <= 0 || getLayoutParams().height <= 0) {
            Context context = getContext();
            if (context instanceof Activity) {
                View decorView = ((Activity) context).getWindow().getDecorView();
                try {
                    View view = (View) getParent();
                    if (view instanceof Banner) {
                        view = (View) view.getParent();
                    }
                    while (view != null && (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0)) {
                        if (view.getMeasuredWidth() > 0) {
                            setPointWidthIfNotSet(point, h.b(getContext(), (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()));
                        }
                        if (view.getMeasuredHeight() > 0) {
                            setPointHeightIfNotSet(point, h.b(getContext(), (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()));
                        }
                        view = (View) view.getParent();
                    }
                    if (view == null) {
                        determineSizeByScreen(point, decorView);
                    } else {
                        setPointWidthIfNotSet(point, h.b(getContext(), (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()));
                        setPointHeightIfNotSet(point, h.b(getContext(), (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()));
                    }
                } catch (Exception unused) {
                    determineSizeByScreen(point, decorView);
                }
            } else {
                try {
                    WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
                    setPointWidthIfNotSet(point, getWidthInDp());
                    setPointHeightIfNotSet(point, getHeightInDp());
                    if (!(windowManager == null || context == null)) {
                        h.a(context, windowManager, point);
                    }
                } catch (Exception e) {
                    f.a(context, com.startapp.android.publish.adsCommon.f.d.EXCEPTION, "BannerStandard.getAvailableSize - system service failed", e.getMessage(), "");
                }
            }
        }
        g.a(TAG, 3, "============ exit Application Size [" + point.x + "," + point.y + "] =========");
        return point;
    }

    private void determineSizeByScreen(Point point, View view) {
        setPointWidthIfNotSet(point, h.b(getContext(), view.getMeasuredWidth()));
        setPointHeightIfNotSet(point, h.b(getContext(), view.getMeasuredHeight()));
    }

    private void setPointWidthIfNotSet(Point point, int i) {
        if (point.x <= 0) {
            point.x = i;
        }
    }

    private void setPointHeightIfNotSet(Point point, int i) {
        if (point.y <= 0) {
            point.y = i;
        }
    }

    public void onReceiveAd(Ad ad) {
        g.a(TAG, 3, " Html Ad Recievied OK");
        this.webViewTouched = false;
        removeView(this.adInformationContatiner);
        a aVar = this.adHtml;
        if (aVar == null || aVar.f() == null || this.adHtml.f().compareTo("") == 0) {
            g.a(TAG, 6, "No Banner recieved");
            onFailedToReceiveBanner("No Banner received");
            return;
        }
        this.jsTag = "true".equals(com.startapp.android.publish.adsCommon.Utils.i.a(this.adHtml.f(), "@jsTag@", "@jsTag@"));
        loadHtml();
        try {
            if (setSize(Integer.parseInt(com.startapp.android.publish.adsCommon.Utils.i.a(this.adHtml.f(), "@width@", "@width@")), Integer.parseInt(com.startapp.android.publish.adsCommon.Utils.i.a(this.adHtml.f(), "@height@", "@height@")))) {
                this.loaded = true;
                addAdInformationLayout();
                makeImpression();
                addDisplayEventOnLoad();
                if (this.listener != null && !this.callbackSent) {
                    this.callbackSent = true;
                    this.listener.onReceiveAd(this);
                }
                if (this.visible) {
                    setVisibility(0);
                }
                g.a(TAG, 3, "Done Loading HTML Banner");
                return;
            }
            onFailedToReceiveBanner("Banner cannot be displayed (not enough room)");
        } catch (NumberFormatException unused) {
            g.a(TAG, 6, "Error Casting width & height from HTML");
            onFailedToReceiveBanner("Error Casting width & height from HTML");
        } catch (Exception e) {
            g.a(TAG, 6, "Unknown error occurred " + e.getMessage());
            onFailedToReceiveBanner(e.getMessage());
        }
    }

    private void onFailedToReceiveBanner(String str) {
        setErrorMessage(str);
        BannerListener bannerListener = this.listener;
        if (bannerListener != null && !this.callbackSent) {
            this.callbackSent = true;
            bannerListener.onFailedToReceiveAd(this);
        }
    }

    private void loadHtml() {
        com.startapp.android.publish.adsCommon.Utils.i.a(getContext(), this.webView, this.adHtml.f());
    }

    /* access modifiers changed from: protected */
    public void makeImpression() {
        g.a(TAG, 3, "BannerStandard Scheduling visibility check");
        i iVar = new i(getContext(), this.adHtml.l(), new com.startapp.android.publish.adsCommon.d.b(getAdTag()), getImpressionDelayMillis());
        this.scheduledImpression = iVar;
        startVisibilityRunnable(iVar);
    }

    private long getImpressionDelayMillis() {
        if (this.adHtml.p() != null) {
            return TimeUnit.SECONDS.toMillis(this.adHtml.p().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }

    private boolean setSize(int i, int i2) {
        Point availableSize = getAvailableSize();
        if (availableSize.x < i || availableSize.y < i2) {
            Point point = new Point(0, 0);
            ViewGroup.LayoutParams layoutParams = this.webView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(point.x, point.y);
            } else {
                layoutParams.width = point.x;
                layoutParams.height = point.y;
            }
            this.webView.setLayoutParams(layoutParams);
            return false;
        }
        this.size.a(i, i2);
        int a2 = h.a(getContext(), this.size.a());
        int a3 = h.a(getContext(), this.size.b());
        setMinimumWidth(a2);
        setMinimumHeight(a3);
        ViewGroup.LayoutParams layoutParams2 = this.webView.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams2 = new RelativeLayout.LayoutParams(a2, a3);
        } else {
            layoutParams2.width = a2;
            layoutParams2.height = a3;
        }
        this.webView.setLayoutParams(layoutParams2);
        return true;
    }

    public void onFailedToReceiveAd(Ad ad) {
        onFailedToReceiveBanner(ad.getErrorMessage());
    }

    /* compiled from: StartAppSDK */
    class MyWebViewClient extends WebViewClient {
        private boolean callbackSent = false;

        MyWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            if (MetaData.getInstance().isOmsdkEnabled()) {
                BannerStandard bannerStandard = BannerStandard.this;
                com.b.a.a.a.b.b unused = bannerStandard.adSession = a.a(bannerStandard.webView);
                if (BannerStandard.this.adSession != null && BannerStandard.this.webView != null) {
                    if (BannerStandard.this.adInformationContatiner != null) {
                        BannerStandard.this.adSession.b(BannerStandard.this.adInformationContatiner);
                    }
                    BannerStandard.this.adSession.a(BannerStandard.this.webView);
                    BannerStandard.this.adSession.a();
                    com.b.a.a.a.b.a.a(BannerStandard.this.adSession).a();
                }
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (BannerStandard.this.jsTag && !BannerStandard.this.webViewTouched) {
                return false;
            }
            if (!this.callbackSent) {
                this.callbackSent = true;
                if (BannerStandard.this.listener != null) {
                    BannerStandard.this.listener.onClick(BannerStandard.this);
                }
            }
            BannerStandard.this.cancelScheduledImpression(true);
            boolean a2 = com.startapp.android.publish.adsCommon.c.a(BannerStandard.this.getContext(), AdPreferences.Placement.INAPP_BANNER);
            if (BannerStandard.this.jsTag) {
                String str2 = str;
            } else if (str.contains("index=")) {
                try {
                    int a3 = com.startapp.android.publish.adsCommon.c.a(str);
                    String str3 = null;
                    if (!BannerStandard.this.adHtml.d(a3) || a2) {
                        Context context = BannerStandard.this.getContext();
                        if (a3 < BannerStandard.this.adHtml.m().length) {
                            str3 = BannerStandard.this.adHtml.m()[a3];
                        }
                        com.startapp.android.publish.adsCommon.c.a(context, str, str3, new com.startapp.android.publish.adsCommon.d.b(BannerStandard.this.getAdTag()), BannerStandard.this.adHtml.e(a3) && !a2, false);
                        BannerStandard.this.webView.stopLoading();
                        BannerStandard.this.setClicked(true);
                        return true;
                    }
                    Context context2 = BannerStandard.this.getContext();
                    String str4 = a3 < BannerStandard.this.adHtml.m().length ? BannerStandard.this.adHtml.m()[a3] : null;
                    if (a3 < BannerStandard.this.adHtml.o().length) {
                        str3 = BannerStandard.this.adHtml.o()[a3];
                    }
                    com.startapp.android.publish.adsCommon.c.a(context2, str, str4, str3, new com.startapp.android.publish.adsCommon.d.b(BannerStandard.this.getAdTag()), com.startapp.android.publish.adsCommon.b.a().A(), com.startapp.android.publish.adsCommon.b.a().B(), BannerStandard.this.adHtml.e(a3), BannerStandard.this.adHtml.f(a3), false);
                    BannerStandard.this.webView.stopLoading();
                    BannerStandard.this.setClicked(true);
                    return true;
                } catch (Exception unused) {
                    g.a(BannerStandard.TAG, 6, "Error while trying parsing index from url");
                    return false;
                }
            }
            if (!BannerStandard.this.adHtml.d(0) || a2) {
                com.startapp.android.publish.adsCommon.c.a(BannerStandard.this.getContext(), str, BannerStandard.this.adHtml.m()[0], new com.startapp.android.publish.adsCommon.d.b(BannerStandard.this.getAdTag()), BannerStandard.this.adHtml.e(0) && !a2, false);
                BannerStandard.this.webView.stopLoading();
                BannerStandard.this.setClicked(true);
                return true;
            }
            com.startapp.android.publish.adsCommon.c.a(BannerStandard.this.getContext(), str, BannerStandard.this.adHtml.m()[0], BannerStandard.this.adHtml.o()[0], new com.startapp.android.publish.adsCommon.d.b(BannerStandard.this.getAdTag()), com.startapp.android.publish.adsCommon.b.a().A(), com.startapp.android.publish.adsCommon.b.a().B(), BannerStandard.this.adHtml.e(0), BannerStandard.this.adHtml.f(0), false);
            BannerStandard.this.webView.stopLoading();
            BannerStandard.this.setClicked(true);
            return true;
        }
    }

    private void onResume() {
        WebView webView2 = this.webView;
        if (webView2 != null) {
            com.startapp.common.a.c.c(webView2);
        }
    }

    private void onPause() {
        WebView webView2 = this.webView;
        if (webView2 != null) {
            com.startapp.common.a.c.b(webView2);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        onResume();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onPause();
        cancelScheduledImpression(false);
        com.b.a.a.a.b.b bVar = this.adSession;
        if (bVar != null) {
            bVar.b();
            this.adSession = null;
            com.startapp.android.publish.adsCommon.Utils.i.a((Object) this.webView, 1000);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            onResume();
        } else {
            onPause();
        }
    }

    public void setBannerListener(BannerListener bannerListener) {
        this.listener = bannerListener;
    }

    /* access modifiers changed from: protected */
    public int getRefreshRate() {
        return this.options.i();
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        a aVar = this.adHtml;
        if (aVar == null) {
            return 0;
        }
        return aVar.a();
    }

    /* access modifiers changed from: protected */
    public int getBannerId() {
        return this.innerBannerStandardId;
    }

    /* access modifiers changed from: protected */
    public void setBannerId(int i) {
        this.innerBannerStandardId = i;
    }

    public void setAdTag(String str) {
        this.adTag = str;
    }

    /* access modifiers changed from: protected */
    public void cancelScheduledImpression(boolean z) {
        i iVar = this.scheduledImpression;
        if (iVar != null) {
            iVar.a(z);
        }
    }
}
