package com.appsgeyser.multiTabApp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.controllers.INavigationController;
import com.appsgeyser.multiTabApp.controllers.ITabContentController;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.appsgeyser.multiTabApp.ui.navigationwidget.BottomNavigationWidget;
import com.appsgeyser.multiTabApp.ui.navigationwidget.INavigationWidget;
import com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;

public class WebContent extends TabContent {
    private BrowserWebView _browser = null;
    private String _defaultUrl = "";
    private INavigationWidget _navigationWidget = null;
    private ProgressBar _progressBar = null;
    private ITabContentController _tabContentController = null;
    private INavigationController _tabsController = Factory.getInstance().getTabsController();
    private String pageRefreshJsCode = "";

    public void showNavigationBars() {
    }

    public WebContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WebContent(Context context) {
        super(context);
    }

    public void init(ITabContentController iTabContentController) {
        this._tabContentController = iTabContentController;
        BrowserWebView browserWebView = (BrowserWebView) findViewById(R.id.webView);
        this._browser = browserWebView;
        browserWebView.requestFocus(130);
        this._browser.getContentHeight();
        this._progressBar = (ProgressBar) findViewById(R.id.progressbar);
        WebWidgetConfiguration config = this._tabContentController.getMainNavigationActivity().getConfig();
        WebWidgetConfiguration.UrlBarStates urlOverlayState = config.getUrlOverlayState();
        boolean z = false;
        if (urlOverlayState != WebWidgetConfiguration.UrlBarStates.DISABLED) {
            String link = this._tabContentController.getWidgetInfo().getLink();
            ArrayList<UrlBarMenuButton> urlBarMenuButtons = config.getUrlBarMenuButtons();
            if (config.getUrlBarStyle() == WebWidgetConfiguration.UrlBarStyles.TOP) {
                TopNavigationWidget topNavigationWidget = new TopNavigationWidget(this, link, this._browser, urlBarMenuButtons);
                this._navigationWidget = topNavigationWidget;
                topNavigationWidget.initHistory();
            } else {
                BottomNavigationWidget bottomNavigationWidget = new BottomNavigationWidget(this, link, this._browser, urlBarMenuButtons);
                this._navigationWidget = bottomNavigationWidget;
                bottomNavigationWidget.attachAutocomplete();
            }
            INavigationWidget iNavigationWidget = this._navigationWidget;
            if (urlOverlayState == WebWidgetConfiguration.UrlBarStates.ENABLED_ON_EXTERNAL_URLS) {
                z = true;
            }
            iNavigationWidget.setHideOnInternalUrls(z);
            Factory.getInstance().setNavigationWidget(this._navigationWidget);
            return;
        }
        try {
            ((RelativeLayout.LayoutParams) this._browser.getLayoutParams()).setMargins(0, 0, 0, 0);
        } catch (ClassCastException e) {
            Log.e("WebContent", "" + e);
        }
    }

    public void navigate(String str) {
        this._browser.loadUrl(str);
        this._defaultUrl = str;
        INavigationWidget iNavigationWidget = this._navigationWidget;
        if (iNavigationWidget != null) {
            iNavigationWidget.setUrl(str);
        }
        this._progressBar.setProgress(0);
    }

    public INavigationWidget getNavigationWidget() {
        return this._navigationWidget;
    }

    public WebView getBrowser() {
        return this._browser;
    }

    public ProgressBar getProgressBar() {
        return this._progressBar;
    }

    public void hideProgressBarPanel() {
        findViewById(R.id.progressbarPanel).setVisibility(8);
    }

    public void showProgressBarPanel() {
        findViewById(R.id.progressbarPanel).setVisibility(0);
    }

    public void setLoadingCurtainType(WidgetEntity.LoadingCurtainType loadingCurtainType) {
        if (loadingCurtainType == WidgetEntity.LoadingCurtainType.NONE) {
            findViewById(R.id.loadingCurtainDefault).setVisibility(8);
        } else if (loadingCurtainType == WidgetEntity.LoadingCurtainType.DEFAULT) {
            findViewById(R.id.loadingCurtainDefault).setVisibility(0);
        }
    }

    public String getPageRefreshJsCode() {
        return this.pageRefreshJsCode;
    }

    public void setPageRefreshJsCode(String str) {
        this.pageRefreshJsCode = str;
    }
}
