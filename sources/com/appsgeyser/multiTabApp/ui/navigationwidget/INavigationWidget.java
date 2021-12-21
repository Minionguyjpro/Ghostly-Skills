package com.appsgeyser.multiTabApp.ui.navigationwidget;

import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.HashMap;

public interface INavigationWidget {
    void attachAutocomplete();

    HashMap<String, NavigationWidgetCustomIcon> getCustomIcons();

    ViewGroup getNawigationWidgetView();

    boolean isVisible();

    void onPageFinished(WebView webView, String str);

    void onPageStart(WebView webView, String str);

    void setHideOnInternalUrls(boolean z);

    void setUrl(String str);
}
