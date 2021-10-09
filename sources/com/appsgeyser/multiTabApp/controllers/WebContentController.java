package com.appsgeyser.multiTabApp.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.browser.BrowserDownloadListener;
import com.appsgeyser.multiTabApp.browser.BrowserWebChromeClient;
import com.appsgeyser.multiTabApp.browser.BrowserWebViewClient;
import com.appsgeyser.multiTabApp.browser.SimpleWebViewClient;
import com.appsgeyser.multiTabApp.configuration.IncludeScriptConfigEntity;
import com.appsgeyser.multiTabApp.javascriptinterface.JavascriptInterface;
import com.appsgeyser.multiTabApp.media.WebViewJsAudioPlayer;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.pdfreader.PdfLoader;
import com.appsgeyser.multiTabApp.plugins.PluginsLoader;
import com.appsgeyser.multiTabApp.ui.navigationwidget.INavigationWidget;
import com.appsgeyser.multiTabApp.ui.views.PdfContent;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.appsgeyser.multiTabApp.ui.views.WebContent;
import com.appsgeyser.multiTabApp.utils.FileManager;
import com.appsgeyser.multiTabApp.utils.WildcardMatcher;
import com.appsgeyser.sdk.AppsgeyserSDK;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class WebContentController implements View.OnTouchListener, ITabContentController {
    private static long lastTimeFirtsBannerWasShown = -1;
    /* access modifiers changed from: private */
    public static long lastTimeFullScreenBannerWasShown = -1;
    private String _bannerJsInjection = "";
    /* access modifiers changed from: private */
    public WebView _browser;
    private MainNavigationActivity _mainActivity = null;
    private PdfContent _pdfContent;
    private ProgressBar _progressBar;
    private int _tabsPadding = 0;
    /* access modifiers changed from: private */
    public WebContent _webContent = null;
    private WidgetEntity _widgetInfo;
    private boolean firstBannerWasShown = false;
    private BrowserWebChromeClient mBrowserWebChromeClient = null;
    /* access modifiers changed from: private */
    public WebView popupView = null;

    public WebContentController(WidgetEntity widgetEntity) {
        this._widgetInfo = widgetEntity;
    }

    /* renamed from: com.appsgeyser.multiTabApp.controllers.WebContentController$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.appsgeyser.multiTabApp.ui.views.TabContent$TabType[] r0 = com.appsgeyser.multiTabApp.ui.views.TabContent.TabType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType = r0
                com.appsgeyser.multiTabApp.ui.views.TabContent$TabType r1 = com.appsgeyser.multiTabApp.ui.views.TabContent.TabType.WEB     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appsgeyser.multiTabApp.ui.views.TabContent$TabType r1 = com.appsgeyser.multiTabApp.ui.views.TabContent.TabType.PDF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.multiTabApp.controllers.WebContentController.AnonymousClass5.<clinit>():void");
        }
    }

    private void init(TabContent.TabType tabType) {
        int i = AnonymousClass5.$SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType[tabType.ordinal()];
        if (i == 1) {
            this._webContent.init(this);
        } else if (i == 2) {
            this._pdfContent.init(this._widgetInfo.getLink(), new PdfLoader.PdfEventListener() {
                public void handleEvent() {
                    WebContentController.this.showBanner(MainNavigationActivity.UserEvent.PDF_EVENT, true);
                }
            }, this._widgetInfo.getTabId());
            return;
        }
        this._webContent.setLoadingCurtainType(this._widgetInfo.getLoadingCurtainType());
        this._browser = this._webContent.getBrowser();
        this._progressBar = this._webContent.getProgressBar();
        this._browser.requestFocus(130);
        this._browser.setWebViewClient(new BrowserWebViewClient(this, this._browser));
        AnonymousClass2 r8 = new BrowserWebChromeClient(this) {
            public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
                Message obtainMessage = webView.getHandler().obtainMessage();
                webView.requestFocusNodeHref(obtainMessage);
                obtainMessage.getData().getString("url");
                WebContentController.this._browser.removeAllViews();
                WebView unused = WebContentController.this.popupView = new WebView(webView.getContext());
                WebContentController.this.popupView.setWebViewClient(new SimpleWebViewClient(WebContentController.this.popupView.getContext()));
                WebSettings settings = WebContentController.this.popupView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setAppCacheEnabled(true);
                settings.setDomStorageEnabled(true);
                if (Build.VERSION.SDK_INT >= 21) {
                    settings.setMixedContentMode(2);
                }
                settings.setCacheMode(-1);
                settings.setAllowFileAccess(true);
                WebContentController.this.popupView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                WebContentController.this.popupView.setWebChromeClient(new WebChromeClient() {
                    public void onCloseWindow(WebView webView) {
                        WebContentController.this._browser.removeView(webView);
                        WebView unused = WebContentController.this.popupView = null;
                    }
                });
                WebContentController.this._browser.addView(WebContentController.this.popupView);
                WebContentController.this._browser.scrollTo(0, 0);
                ((WebView.WebViewTransport) message.obj).setWebView(WebContentController.this.popupView);
                message.sendToTarget();
                return true;
            }
        };
        this.mBrowserWebChromeClient = r8;
        this._browser.setWebChromeClient(r8);
        this._browser.setDownloadListener(new BrowserDownloadListener(this));
        this._browser.setOnTouchListener(this);
        this._browser.addJavascriptInterface(new JavascriptInterface(this), "AppsgeyserJSInterface");
        WebView webView = this._browser;
        webView.addJavascriptInterface(new WebViewJsAudioPlayer(webView), WebViewJsAudioPlayer.JS_INTERFACE_NAME);
        PluginsLoader.loadPlugins(this._mainActivity, this._browser);
        WebSettings settings = this._browser.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        CookieManager.getInstance().setAcceptCookie(true);
        settings.setAppCacheMaxSize(209715200);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        Context applicationContext = Factory.getInstance().getMainNavigationActivity().getApplicationContext();
        String path = applicationContext.getDir("appcache", 0).getPath();
        String path2 = applicationContext.getDir("databases", 0).getPath();
        String path3 = applicationContext.getDir("geolocation", 0).getPath();
        settings.setAppCachePath(path);
        settings.setDatabasePath(path2);
        settings.setGeolocationDatabasePath(path3);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSaveFormData(true);
        settings.setSavePassword(true);
        settings.setCacheMode(-1);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        this._browser.setVerticalScrollBarEnabled(false);
        this._browser.setHorizontalScrollBarEnabled(false);
        if (this._widgetInfo.getLink().indexOf("file:///android_asset/content") == 0) {
            this._browser.setInitialScale(calculateScale());
        } else {
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            this._browser.setInitialScale(0);
        }
        if (Factory.getInstance().getWidgetsController().widgetsCount() > 1) {
            this._tabsPadding = 61;
        }
        this._bannerJsInjection = Factory.getInstance().getMainNavigationActivity().getSharedPreferences("AppsgeyserPrefs", 0).getString("bannerJs", "");
        this._webContent.navigate(this._widgetInfo.getLink());
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this._browser, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }
    }

    public void setMainNavigationActivity(MainNavigationActivity mainNavigationActivity) {
        this._mainActivity = mainNavigationActivity;
    }

    public MainNavigationActivity getMainNavigationActivity() {
        return this._mainActivity;
    }

    public View createTabContent(LayoutInflater layoutInflater, ViewGroup viewGroup, TabContent.TabType tabType) throws Exception {
        View tabContent = Factory.getInstance().getTabContent(tabType, layoutInflater, viewGroup);
        int i = AnonymousClass5.$SwitchMap$com$appsgeyser$multiTabApp$ui$views$TabContent$TabType[tabType.ordinal()];
        if (i == 1) {
            this._webContent = (WebContent) tabContent;
            init(TabContent.TabType.WEB);
        } else if (i == 2) {
            this._pdfContent = (PdfContent) tabContent;
            init(TabContent.TabType.PDF);
        }
        return tabContent;
    }

    public boolean onBackKeyDown() {
        WebView webView = this.popupView;
        if (webView == null || !webView.canGoBack()) {
            WebView webView2 = this.popupView;
            if (webView2 != null && !webView2.canGoBack()) {
                this._browser.removeView(this.popupView);
                this.popupView = null;
                return true;
            } else if (!this._browser.canGoBack()) {
                return false;
            } else {
                this._browser.goBack();
                return true;
            }
        } else {
            this.popupView.goBack();
            return true;
        }
    }

    public WidgetEntity getWidgetInfo() {
        return this._widgetInfo;
    }

    public final WebView getWebView() {
        return this._browser;
    }

    public void showProgressBarPanel() {
        this._webContent.showProgressBarPanel();
    }

    public void setProgressBarState(int i) {
        this._progressBar.setSecondaryProgress(i);
        this._progressBar.setProgress(i);
        if (i >= 100) {
            new Handler().postDelayed(new HandlerThread("progressFinishDelay") {
                public void run() {
                    WebContentController.this._webContent.hideProgressBarPanel();
                }
            }, 300);
        }
    }

    public INavigationWidget getNavigationWidget() {
        return this._webContent.getNavigationWidget();
    }

    private int calculateScale() {
        float f;
        WindowManager windowManager = (WindowManager) Factory.getInstance().getMainNavigationActivity().getSystemService("window");
        int i = Build.VERSION.SDK_INT > 6 ? 10 : 20;
        PrintStream printStream = System.out;
        printStream.println("dBrowserWidth = " + i);
        int width = windowManager.getDefaultDisplay().getWidth() - i;
        float height = ((float) (windowManager.getDefaultDisplay().getHeight() + -50)) / (((float) (this._widgetInfo.getHeight() + 5)) + ((float) this._tabsPadding));
        float width2 = ((float) width) / ((float) this._widgetInfo.getWidth());
        if (height <= 1.0f || width2 <= 1.0f) {
            f = Math.min(height, width2);
        } else {
            f = Math.min(height, width2);
        }
        return (int) (f * 100.0f);
    }

    private int calculateScale(int i, int i2) {
        return (int) (Math.min(((float) this._browser.getHeight()) / ((float) i), ((float) this._browser.getWidth()) / ((float) i2)) * 100.0f);
    }

    public String getInjectJSContent(String str) {
        String stringFromAssetsFileWithFileName = FileManager.getStringFromAssetsFileWithFileName(this._widgetInfo.getInjectJS(), this._mainActivity);
        ArrayList<IncludeScriptConfigEntity> injectScripts = this._widgetInfo.getInjectScripts();
        if (injectScripts != null) {
            Iterator<IncludeScriptConfigEntity> it = injectScripts.iterator();
            while (it.hasNext()) {
                IncludeScriptConfigEntity next = it.next();
                String regex = next.getRegex();
                String pattern = next.getPattern();
                if (regex != null && regex.length() > 0 && str.matches(regex)) {
                    stringFromAssetsFileWithFileName = (stringFromAssetsFileWithFileName + FileManager.getStringFromAssetsFileWithFileName(next.getFile(), this._mainActivity)) + " \n ";
                } else if (pattern != null && pattern.length() > 0 && WildcardMatcher.match(str, pattern)) {
                    stringFromAssetsFileWithFileName = (stringFromAssetsFileWithFileName + FileManager.getStringFromAssetsFileWithFileName(next.getFile(), this._mainActivity)) + " \n ";
                }
            }
        }
        return stringFromAssetsFileWithFileName;
    }

    public String getBannerInjectionJs() {
        return this._bannerJsInjection;
    }

    public void setBannerInjectionJs(String str) {
        this._bannerJsInjection = str;
        WebView webView = this._browser;
        if (webView != null && webView.getProgress() >= 100 && this._browser.getUrl() != null && !this._browser.getUrl().startsWith("https://")) {
            WebView webView2 = this._browser;
            webView2.loadUrl("javascript:(function(){ " + str + " })()");
        }
        SharedPreferences.Editor edit = Factory.getInstance().getMainNavigationActivity().getSharedPreferences("AppsgeyserPrefs", 0).edit();
        edit.putString("bannerJs", str);
        edit.commit();
    }

    public void setScaleForPageWithSize(int i, int i2) {
        this._browser.setInitialScale(calculateScale(i, i2));
    }

    public void zoomIn() {
        this._browser.zoomIn();
    }

    public void setPageRefreshJsCode(String str) {
        this._webContent.setPageRefreshJsCode(str);
    }

    public void showNavigationBars() {
        this._webContent.showNavigationBars();
    }

    public void showBanner(final MainNavigationActivity.UserEvent userEvent, final boolean z) {
        this._mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (userEvent == MainNavigationActivity.UserEvent.TOUCH || userEvent == MainNavigationActivity.UserEvent.MENU_ITEM_CLICK || userEvent == MainNavigationActivity.UserEvent.PDF_EVENT || userEvent == MainNavigationActivity.UserEvent.TAB_CHANGED || userEvent == MainNavigationActivity.UserEvent.WEB_PAGES_CHANGED) {
                    long unused = WebContentController.lastTimeFullScreenBannerWasShown = System.currentTimeMillis();
                    AppsgeyserSDK.getFastTrackAdsController().showFullscreen("on_timeout", Factory.getInstance().getMainNavigationActivity(), userEvent.getPlacementTag(), z);
                }
            }
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        showBanner(MainNavigationActivity.UserEvent.TOUCH, true);
        return false;
    }
}
