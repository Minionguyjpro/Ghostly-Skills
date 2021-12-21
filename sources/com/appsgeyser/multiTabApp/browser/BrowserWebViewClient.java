package com.appsgeyser.multiTabApp.browser;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.configuration.DefaultUrlsHolder;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.ui.dialog.SslErrorDialog;
import com.appsgeyser.multiTabApp.ui.navigationwidget.INavigationWidget;
import com.appsgeyser.multiTabApp.ui.views.TabContent;
import com.appsgeyser.multiTabApp.utils.JavaScriptInjector;
import com.appsgeyser.sdk.AppsgeyserSDK;

public class BrowserWebViewClient extends SimpleWebViewClient {
    private WebWidgetConfiguration.RedirectionTypes _isRedirectEnabled = WebWidgetConfiguration.RedirectionTypes.NO_REDIRECT;
    private JavaScriptInjector _javaScriptInjector = null;
    private MainNavigationActivity _mainActivity = Factory.getInstance().getMainNavigationActivity();
    private WebContentController _webContentController;

    public BrowserWebViewClient(WebContentController webContentController, WebView webView) {
        super(Factory.getInstance().getMainNavigationActivity());
        this._webContentController = webContentController;
        DefaultUrlsHolder.getInstance().addUrl(webContentController.getWidgetInfo().getLink());
        this._isRedirectEnabled = this._mainActivity.getConfig().getIsRedirectEnabled();
        this._javaScriptInjector = new JavaScriptInjector(webView, this._webContentController, this._context);
        this._context = this._mainActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        WebContentController selectedTab;
        INavigationWidget navigationWidget;
        if (str.equals(webView.getUrl())) {
            String str2 = "javascript:(function(){ " + this._webContentController.getInjectJSContent(str) + " })()";
            System.out.println(str2);
            webView.loadUrl(str2);
            this._webContentController.getBannerInjectionJs();
            String additionalJsCode = AppsgeyserSDK.getAdditionalJsCode();
            if (additionalJsCode != null && !additionalJsCode.isEmpty()) {
                webView.loadUrl("javascript:(function(){ " + additionalJsCode + " })()");
            }
        }
        webView.getSettings().setBlockNetworkImage(false);
        super.onPageFinished(webView, str);
        if (this._isRedirectEnabled == WebWidgetConfiguration.RedirectionTypes.REDIRECT_ALL && (selectedTab = Factory.getInstance().getTabsController().getSelectedTab()) != null && selectedTab.getWidgetInfo().getTabType().equals(TabContent.TabType.WEB.toString()) && (navigationWidget = selectedTab.getNavigationWidget()) != null) {
            navigationWidget.onPageFinished(webView, str);
        }
        this._webContentController.showBanner(MainNavigationActivity.UserEvent.WEB_PAGES_CHANGED, true);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        webView.getSettings().setBlockNetworkImage(true);
        if ((this._isRedirectEnabled != WebWidgetConfiguration.RedirectionTypes.NO_REDIRECT || DefaultUrlsHolder.getInstance().isDefaultUrl(str)) && (this._isRedirectEnabled != WebWidgetConfiguration.RedirectionTypes.REDIRECT_EXTERNAL || DefaultUrlsHolder.getInstance().isPermittedDomain(str))) {
            webView.getSettings().setBlockNetworkImage(false);
            this._webContentController.showProgressBarPanel();
            INavigationWidget navigationWidget = this._webContentController.getNavigationWidget();
            if (navigationWidget != null) {
                navigationWidget.onPageStart(webView, str);
            }
            if (!str.contains("file:///android_asset/content")) {
                WebSettings settings = webView.getSettings();
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
                webView.setInitialScale(0);
            }
            this._webContentController.setPageRefreshJsCode("");
            this._javaScriptInjector.InjectJavaScript();
            super.onPageStarted(webView, str, bitmap);
        } else {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                webView.stopLoading();
                if (this._mainActivity.getPackageManager().resolveActivity(intent, 0) != null) {
                    this._mainActivity.startActivity(intent);
                }
            } catch (ActivityNotFoundException e) {
                Log.e("ANFE", "onPageStarted() :" + e.getMessage());
            }
        }
        this._webContentController.showBanner(MainNavigationActivity.UserEvent.WEB_PAGES_CHANGED, true);
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        super.doUpdateVisitedHistory(webView, str, z);
        INavigationWidget navigationWidget = this._webContentController.getNavigationWidget();
        if (navigationWidget != null) {
            navigationWidget.setUrl(str);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        WebContentController selectedTab;
        String url = webView.getUrl();
        if (this._isRedirectEnabled == WebWidgetConfiguration.RedirectionTypes.REDIRECT_ALL && (selectedTab = Factory.getInstance().getTabsController().getSelectedTab()) != null && selectedTab.getWidgetInfo().getTabType().equals(TabContent.TabType.WEB.toString())) {
            selectedTab.getNavigationWidget();
        }
        if (url != null) {
            boolean equalsIgnoreCase = url.equalsIgnoreCase(str2);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        new SslErrorDialog(this._context).execute(webView, sslErrorHandler, sslError);
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        String str3;
        String[] httpAuthUsernamePassword;
        String str4 = null;
        if (!httpAuthHandler.useHttpAuthUsernamePassword() || webView == null || (httpAuthUsernamePassword = webView.getHttpAuthUsernamePassword(str, str2)) == null || httpAuthUsernamePassword.length != 2) {
            str3 = null;
        } else {
            str4 = httpAuthUsernamePassword[0];
            str3 = httpAuthUsernamePassword[1];
        }
        if (str4 == null || str3 == null) {
            this._mainActivity.showHttpAuthentication(this._webContentController.getWebView(), httpAuthHandler, str, str2, (String) null, (String) null, (String) null, 0);
        } else {
            httpAuthHandler.proceed(str4, str3);
        }
    }
}
